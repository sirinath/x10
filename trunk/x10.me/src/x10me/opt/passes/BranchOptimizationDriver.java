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

import x10me.opt.DefUse;
import x10me.opt.controlflow.BasicBlock;
import x10me.opt.controlflow.BasicBlockEnumeration;
import x10me.opt.driver.CompilerPhase;
import x10me.opt.driver.OptOptions;
import x10me.opt.ir.*;
import x10me.opt.util.SpaceEffGraphNode;

/**
 * IR level independent driver for
 * simple peephole optimizations of branches.
 */
public abstract class BranchOptimizationDriver extends CompilerPhase {

  /**
   * Optimization level at which phase should be performed.
   */
  private final int level;

  /**
   * Optimization level at which phase should be performed.
   */
  private final boolean simplify;

  /**
   * @param level the minimum optimization level at which the branch
   * optimizations should be performed.
   * @param simplify perform simplification prior to optimization?
   */
  BranchOptimizationDriver(IR ir, int level, boolean simplify) {
    super(ir);
    this.level = level;
    this.simplify = simplify;
  }

  /**
   * @param level the minimum optimization level at which the branch
   * optimizations should be performed.
   */
  BranchOptimizationDriver(IR ir,int level) {
    super(ir);
    this.level = level;
    this.simplify = false;
  }

  /** Interface */
  public final boolean shouldPerform(OptOptions options) {
    return options.getOptLevel() >= level;
  }

  public final String getName() {
    return "Branch Optimizations";
  }

  public final boolean printingEnabled(OptOptions options, boolean before) {
    return false;
  }

  /**
   * This phase contains no per-compilation instance fields.
   */
  public final CompilerPhase newExecution(IR ir) {
    return this;
  }

 
  @Override
  public final void perform() {
    perform(true);
  }

  public final void perform(boolean renumber) {
    if (simplify) {
      applySimplify(ir);
    }

    maximizeBasicBlocks(ir);
    boolean didSomething = false;
    boolean didSomethingThisTime = true;
    while (didSomethingThisTime) {
      didSomething |= applyPeepholeBranchOpts(ir);
      didSomethingThisTime = removeUnreachableCode(ir);
      didSomething |= didSomethingThisTime;
    }
    if (didSomething) {
      maximizeBasicBlocks(ir);
    }

    ir.cfg.compactNodeNumbering();
    ir.pruneExceptionalOut();
 
  }

  /**
   * Perform branch simplifications.
   *
   * @param ir the IR to optimize
   * @return was something reduced
   */
  private static boolean applySimplify(IR ir) {
    boolean didSomething = false;
    for (BasicBlockEnumeration e = ir.getBasicBlocks(); e.hasMoreElements();) {
      BasicBlock bb = e.next();
      didSomething |= BranchSimplifier.simplify(bb, ir);
    }
    return didSomething;
  }

  /**
   * This pass performs peephole branch optimizations.
   * See Muchnick ~p.590
   *
   * @param ir the IR to optimize
   */
  protected boolean applyPeepholeBranchOpts(IR ir) {
    boolean didSomething = false;
    for (BasicBlockEnumeration e = ir.getBasicBlocks(); e.hasMoreElements();) {
      BasicBlock bb = e.next();
      if (!bb.isEmpty()) {
        for (InstructionEnumeration ie = bb.enumerateBranchInstructions(); ie.hasMoreElements();) {
          Instruction s = ie.next();
          if (optimizeBranchInstruction(ir, s, bb)) {
            didSomething = true;
            // hack: we may have modified the instructions; start over
            ie = bb.enumerateBranchInstructions();
          }
        }
      }
    }
    return didSomething;
  }

  /**
   * This method actually does the work of attempting to
   * peephole optimize a branch instruction.
   * @param ir the containing IR
   * @param s the branch instruction to optimize
   * @param bb the containing basic block
   * @return true if an optimization was applied, false otherwise
   */
  protected abstract boolean optimizeBranchInstruction(IR ir, Instruction s, BasicBlock bb);

  /**
   * Remove unreachable code
   *
   * @param ir the IR to optimize
   * @return true if did something, false otherwise
   */
  protected final boolean removeUnreachableCode(IR ir) {
    boolean result = false;

    // (1) All code in a basic block after an unconditional
    //     trap instruction is dead.
    for (Instruction s = ir.firstInstructionInCodeOrder(); s != null; s = s.nextInstructionInCodeOrder()) {
      if (s instanceof Trap) {
        Instruction p = s.nextInstructionInCodeOrder();
        if (p instanceof BBend) {
          BasicBlock bb = s.getBasicBlock();
          do {
            Instruction q = p;
            p = p.nextInstructionInCodeOrder();
            q.remove();
          } while (!(p instanceof BBend));
          bb.recomputeNormalOut(ir);
          result = true;
        }
      }
    }

    // (2) perform a Depth-first search of the control flow graph,
    //     and remove any nodes not reachable from entry.
    BasicBlock entry = ir.cfg.entry();
    ir.cfg.clearDFS();
    entry.sortDFS();
    for (SpaceEffGraphNode node = entry; node != null;) {
      // save it now before removeFromCFGAndCodeOrder nulls it out!!!
      SpaceEffGraphNode nextNode = node.getNext();
      if (!node.dfsVisited()) {
        BasicBlock bb = (BasicBlock) node;
        ir.cfg.removeFromCFGAndCodeOrder(bb);
        result = true;
      }
      node = nextNode;
    }
    return result;
  }

  /**
   * Merge adjacent basic blocks
   *
   * @param ir the IR to optimize
   */
  protected final void maximizeBasicBlocks(IR ir) {
    for (BasicBlock currBB = ir.cfg.firstInCodeOrder(); currBB != null;) {
      if (currBB.mergeFallThrough(ir)) {
        // don't advance currBB; it may have a new trivial fallthrough to swallow
      } else {
        currBB = currBB.nextBasicBlockInCodeOrder();
      }
    }
  }

  // Helper functions

  /**
   * Given an instruction s, return the first LABEL instruction
   * following s.
   */
  protected final Instruction firstLabelFollowing(Instruction s) {
    for (s = s.nextInstructionInCodeOrder(); s != null; s = s.nextInstructionInCodeOrder()) {
      if (s instanceof Label) {
        return s;
      }
    }
    return null;
  }

  /**
   * Given an instruction s, return the first real (non-label) instruction
   * following s
   */
  protected final Instruction firstRealInstructionFollowing(Instruction s) {
    for (s = s.nextInstructionInCodeOrder(); s != null; s = s.nextInstructionInCodeOrder()) {
      if ((!(s instanceof Label)) && (!(s instanceof BBend))) {
        return s;
      }
    }
    return s;
  }
}
