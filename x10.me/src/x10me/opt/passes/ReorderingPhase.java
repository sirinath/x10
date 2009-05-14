/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file was derived from code developed by the
 *  Jikes RVM project (http://jikesrvm.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  See the COPYRIGHT.txt file distributed with this work for information
 *  regarding copyright ownership.
 */

package x10me.opt.passes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import x10me.opt.controlflow.BasicBlock;
import x10me.opt.controlflow.BasicBlockEnumeration;
import x10me.opt.driver.CompilerPhase;
import x10me.opt.driver.OptOptions;
import x10me.opt.ir.Goto;
import x10me.opt.ir.IR;
import x10me.opt.ir.Instruction;
import x10me.opt.ir.WeightedBranchTargets;
import x10me.opt.ir.operand.BranchOperand;

/**
 * Reorder code layout of basic blocks for improved I-cache locality and
 * branch prediction. This code assumes that basic block frequencies have
 * been computed and blocks have been marked infrequent.
 * This pass actually implements two code placement algorithms:
 * (1) A simple 'fluff' removal pass that moves all infrequent basic blocks
 *     to the end of the code order.
 * (2) Pettis and Hansen Algo2.
 */
public final class ReorderingPhase extends CompilerPhase {

  public ReorderingPhase(IR ir) {
    super(ir);
  }
  
  public boolean shouldPerform(OptOptions options) {
    return options.REORDER_CODE;
  }

  public String getName() {
    return "Code Reordering";
  }

  /**
   * Reorder basic blocks either by trivially moving infrequent blocks
   * to the end of the code order or by applying Pettis and Hansen Algo2.
   *
   * We will rearrange basic blocks and insert/remove
   * unconditional GOTO's if needed.  It does not clean up branches,
   * by reversing the branch condition, however.  That is saved for
   * BranchOptimizations.
   */
  public void perform() {
    ir.cfg.entry().clearInfrequent();
    if (ir.options.REORDER_CODE_PH) {
      // Do Pettis and Hansen PLDI'90 Algo2
      doPettisHansenAlgo2(ir);
    } else {
      // Simple algorithm: just move infrequent code to the end
      exileInfrequentBlocks(ir);
    }
  }

  /////////////////////////
  // Code for trivial algorithm
  /////////////////////////

  /**
   * Select a new basic block ordering via a simple heuristic
   * that moves all infrequent basic blocks to the end.
   * @param ir the IR object to reorder
   */
  private void exileInfrequentBlocks(IR ir) {
    // (1) Look to see if there are infrequent blocks
    //     Also count how many blocks there are.
    int numBlocks = 0;
    boolean foundSome = false;
    for (BasicBlockEnumeration e = ir.getBasicBlocks(); e.hasMoreElements();) {
      BasicBlock bb = e.next();
      numBlocks++;
      foundSome |= bb.getInfrequent();
    }
    if (!foundSome) return; // Nothing to do

    // Reorder the blocks to exile the infrequent blocks.
    // Relative order within the set of frequent and infrequent is unchanged.
    BasicBlock[] newOrdering = new BasicBlock[numBlocks];
    int idx = 0;
    // First append frequent blocks to newOrdering
    for (BasicBlock bb = ir.cfg.firstInCodeOrder(); bb != null; bb = bb.nextBasicBlockInCodeOrder()) {
      if (!bb.getInfrequent()) {
        newOrdering[idx++] = bb;
      }
    }
    // Next append infrequent blocks to newOrdering
    for (BasicBlock bb = ir.cfg.firstInCodeOrder(); bb != null; bb = bb.nextBasicBlockInCodeOrder()) {
      if (bb.getInfrequent()) {
        newOrdering[idx++] = bb;
      }
    }

    assert(idx == numBlocks); // don't lose blocks!
    assert(ir.cfg.entry() == newOrdering[0]);

    // Add/remove unconditional goto's as needed.
    for (int i = 0; i < newOrdering.length; i++) {
      Instruction lastInstr = newOrdering[i].lastRealInstruction();
      // Append a GOTO if needed to maintain old fallthrough semantics.
      BasicBlock fallthroughBlock = newOrdering[i].getFallThroughBlock();
      if (fallthroughBlock != null) {
        if (i == newOrdering.length - 1 || fallthroughBlock != newOrdering[i + 1]) {
          // Add unconditional goto to preserve old fallthrough semantics
          newOrdering[i].appendInstruction(fallthroughBlock.makeGOTO());
        }
      }
      // Remove last instruction if it is a redundant GOTO that
      // can be implemented by a fallthrough edge in the new ordering.
      // (Only possible if newOrdering[i] is not the last basic block.)
      if (i < newOrdering.length - 1 && lastInstr != null && lastInstr instanceof Goto) {
        BranchOperand op = ((Goto)lastInstr).getTarget();
        if (op.target.getBasicBlock() == newOrdering[i + 1]) {
          // unconditional goto is redundant in new ordering
          lastInstr.remove();
        }
      }
    }

    // Re-insert all basic blocks according to new ordering
    ir.cfg.clearCodeOrder();
    for (BasicBlock bb : newOrdering) {
      ir.cfg.addLastInCodeOrder(bb);
    }
  }

  /////////////////////////
  // Code for P&H Algo2
  /////////////////////////

  /**
   * Reorder code using Algo2 (Bottom-Up Positioning) from
   * Pettis and Hansen PLDI'90.
   * @param ir the IR to reorder.
   */
  private void doPettisHansenAlgo2(IR ir) {
    // (1) Setup:
    //     (a) Count the blocks
    //     (b) Create a sorted set of CFG edges
    //     (c) Create a set of blocks
    //     (d) Make fallthroughs explict by adding GOTOs
    int numBlocks = 0;
    TreeSet<Edge> edges = new TreeSet<Edge>();
    HashSet<BasicBlock> chainHeads = new HashSet<BasicBlock>();
    BasicBlock entry = ir.cfg.entry();
    assert(ir.cfg.entry() == ir.cfg.firstInCodeOrder());

    for (BasicBlock bb = entry; bb != null; bb = bb.nextBasicBlockInCodeOrder()) {
      numBlocks++;
      chainHeads.add(bb);
      bb.scratchObject = bb;
      BasicBlock ft = bb.getFallThroughBlock();
      if (ft != null) {
        bb.appendInstruction(new Goto(ft.makeJumpTarget()));
      }
      float bw = bb.getExecutionFrequency();
      for (WeightedBranchTargets wbt = new WeightedBranchTargets(bb); wbt.hasMoreElements(); wbt.advance()) {
        edges.add(new Edge(bb, wbt.curBlock(), wbt.curWeight() * bw));
      }
    }

    if (ir.dumpFile.current != null) ir.dumpFile.current.println("Edges = " + edges);

    // (2) Build chains
    ir.cfg.clearCodeOrder();
    for (Edge e : edges) {
      // If the source of the edge is the last block in its chain
      // and the target of the edge is the first block in its chain
      // then merge the chains.
      if (ir.dumpFile.current != null) ir.dumpFile.current.println("Processing edge " + e);
      if (e.target == entry) {
        if (ir.dumpFile.current != null) ir.dumpFile.current.println("\tCan't put entry block in interior of chain");
        continue;
      }
      if (e.source.nextBasicBlockInCodeOrder() != null) {
        if (ir.dumpFile.current != null) ir.dumpFile.current.println("\tSource is not at end of a chain");
        continue;
      }
      if (e.target.prevBasicBlockInCodeOrder() != null) {
        if (ir.dumpFile.current != null) ir.dumpFile.current.println("\tTarget is not at start of a chain");
        continue;
      }
      if (e.source.scratchObject == e.target.scratchObject) {
        if (ir.dumpFile.current != null) ir.dumpFile.current.println("\tSource and target are in same chain");
        continue;
      }
      if (ir.dumpFile.current != null) ir.dumpFile.current.println("\tMerging chains");
      chainHeads.remove(e.target);
      ir.cfg.linkInCodeOrder(e.source, e.target);
      // Yuck....we should really use near-linear time union find here
      // Doing this crappy thing makes us O(N^2) in the worst case.
      BasicBlock newChain = (BasicBlock) e.source.scratchObject;
      for (BasicBlock ptr = e.target; ptr != null; ptr = ptr.nextBasicBlockInCodeOrder()) {
        ptr.scratchObject = newChain;
      }
    }

    if (ir.dumpFile.current != null) ir.dumpFile.current.println("Chains constructed ");
    HashMap<BasicBlock, ChainInfo> chainInfo = new HashMap<BasicBlock, ChainInfo>();
    for (BasicBlock head : chainHeads) {
      if (ir.dumpFile.current != null) dumpChain(head);
      chainInfo.put(head, new ChainInfo(head));
    }

    // (3) Summarize inter-chain edges.
    for (Edge e : edges) {
      if (e.source.scratchObject != e.target.scratchObject) {
        Object sourceChain = e.source.scratchObject;
        Object targetChain = e.target.scratchObject;
        ChainInfo sourceInfo = chainInfo.get(sourceChain);
        ChainInfo targetInfo = chainInfo.get(targetChain);
        if (ir.dumpFile.current != null) ir.dumpFile.current.println("Inter-chain edge " + sourceChain + "->" + targetChain + " (" + e.weight + ")");
        Object value = sourceInfo.outWeights.get(targetInfo);
        float weight = e.weight;
        if (value != null) {
          weight += (Float) value;
        }
        sourceInfo.outWeights.put(targetInfo, weight);
        targetInfo.inWeight += e.weight;
        if (ir.dumpFile.current != null) ir.dumpFile.current.println("\t" + targetInfo + "," + sourceInfo.outWeights.get(targetInfo));
      }
    }

    if (ir.dumpFile.current != null) ir.dumpFile.current.println("Chain Info " + chainInfo);

    // (4) Construct a total order of the chains, guided by the interchain edge weights.
    //     Constructing an optimal order is NP-Hard, so we apply the following heuristic.
    //     The chain that starts with the entry node is placed first.
    //     At each step, pick the chain with the maximal placedWeight (incoming edges from chains
    //     that are already placed) and minimal inWeight (incoming edges from chains that are not
    //     already placed). Prefer a node with non-zero placedWeight and inWeight to one that has
    //     zeros for both. (A node with both zero placedWeight and zero inWeight is something that
    //     the profile data predicts is not reachable via normal control flow from the entry node).
    BasicBlock lastNode = null;
    ChainInfo nextChoice = chainInfo.get(entry);
    int numPlaced = 0;
    ir.cfg.setFirstNode(entry);
    while (true) {
      if (ir.dumpFile.current != null) ir.dumpFile.current.println("Placing chain " + nextChoice);
      // Append nextChoice to the previous chain
      if (lastNode != null) ir.cfg.linkInCodeOrder(lastNode, nextChoice.head);
      for (BasicBlock ptr = nextChoice.head; ptr != null; ptr = ptr.nextBasicBlockInCodeOrder()) {
        numPlaced++;
        lastNode = ptr;
      }
      // update ChainInfo
      chainInfo.remove(nextChoice.head);
      if (chainInfo.isEmpty()) break; // no chains left to place.
      for (ChainInfo target : nextChoice.outWeights.keySet()) {
        if (ir.dumpFile.current != null) ir.dumpFile.current.print("\toutedge " + target);
        float weight = (Float) nextChoice.outWeights.get(target);
        if (ir.dumpFile.current != null) ir.dumpFile.current.println(" = " + weight);
        target.placedWeight += weight;
        target.inWeight -= weight;
      }

      if (ir.dumpFile.current != null) ir.dumpFile.current.println("Chain Info " + chainInfo);

      // Find the next chain to append.
      nextChoice = null;
      for (ChainInfo cand : chainInfo.values()) {
        if (cand.placedWeight > 0f) {
          if (nextChoice == null) {
            if (ir.dumpFile.current != null) ir.dumpFile.current.println("First reachable candidate " + cand);
            nextChoice = cand;
          } else if (cand.inWeight > nextChoice.inWeight ||
                     (cand.inWeight == nextChoice.inWeight && cand.placedWeight > nextChoice.placedWeight)) {
            if (ir.dumpFile.current != null) ir.dumpFile.current.println(cand + " is a better choice than " + nextChoice);
            nextChoice = cand;
          }
        }
      }
      if (nextChoice != null) continue;

      // All remaining chains are fluff (not reachable from entry).
      // Pick one with minimal inWeight and continue.
      for (ChainInfo cand : chainInfo.values()) {
        if (nextChoice == null) {
          if (ir.dumpFile.current != null) ir.dumpFile.current.println("First candidate " + cand);
          nextChoice = cand;
        } else if (cand.inWeight < nextChoice.inWeight) {
          if (ir.dumpFile.current != null) ir.dumpFile.current.println(cand + " is a better choice than " + nextChoice);
          nextChoice = cand;
        }
      }
    }

    assert(numPlaced == numBlocks); // Don't lose blocks!!
    ir.cfg.setLastNode(lastNode);
  }

  private void dumpChain(BasicBlock head) {
    ir.dumpFile.current.print("{" + head);
    for (BasicBlock next = head.nextBasicBlockInCodeOrder(); next != null; next = next.nextBasicBlockInCodeOrder())
    {
      ir.dumpFile.current.print(", " + next);
    }
    ir.dumpFile.current.println("}");
  }

  private static class ChainInfo {
    final BasicBlock head;
    float placedWeight;
    float inWeight;
    final HashMap<ChainInfo, Object> outWeights = new HashMap<ChainInfo, Object>();

    ChainInfo(BasicBlock h) {
      head = h;
    }

    public String toString() {
      return "[" + head + "," + placedWeight + "," + inWeight + "] " + outWeights.size();
    }
  }

  private static final class Edge implements Comparable<Edge> {
    final BasicBlock source;
    final BasicBlock target;
    final float weight;

    Edge(BasicBlock s, BasicBlock t, float w) {
      source = s;
      target = t;
      weight = w;
    }

    public String toString() {
      return weight + ": " + source.toString() + " -> " + target.toString();
    }

    public int compareTo(Edge that) {
      if (weight < that.weight) {
        return 1;
      } else if (weight > that.weight) {
        return -1;
      } else {
        // Equal weights.
        // Sort based on original code ordering, which is implied by block number
        if (source.getNumber() < that.source.getNumber()) {
          return 1;
        } else if (source.getNumber() > that.source.getNumber()) {
          return -1;
        } else {
          if (target.getNumber() > that.target.getNumber()) {
            return 1;
          } else if (source.getNumber() < that.target.getNumber()) {
            return -1;
          } else {
            return 0;
          }
        }
      }
    }
  }
}
