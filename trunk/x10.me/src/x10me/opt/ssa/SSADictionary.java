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

package x10me.opt.ssa;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import x10me.opt.controlflow.BasicBlock;
import x10me.opt.controlflow.BasicBlockEnumeration;
import x10me.opt.ir.*;
import x10me.opt.ir.operand.BasicBlockOperand;
import x10me.opt.ir.operand.FieldOperand;
import x10me.opt.ir.operand.HeapOperand;
import x10me.opt.ir.operand.Operand;
import x10me.types.Field;
import x10me.types.KnownTypes;
import x10me.types.Type;

/*
 * This module tracks heap variables needed for Array SSA form.
 */

/**
 * An <code> SSADictionary </code> structure holds lookaside
 * information regarding Heap Array SSA form for an IR.  The general idea
 * is that all Heap Array SSA form information resides in this lookaside
 * structure.  Note that this is not the case for scalar SSA information.
 *
 * <P> See our SAS 2000 paper
 * <a href="http://www.research.ibm.com/jalapeno/publication.html#sas00">
 *  Unified Analysis of Arrays and Object References in Strongly Typed
 *  Languages </a> for an overview of Array SSA form.  More implementation
 *  details are documented in {@link SSA <code> SSA.java </code>}.
 *
 * @see SSA
 */
@SuppressWarnings("unchecked")
// Generic HeapOperands and HeapVariables make this
// impossible to typecheck properly
public final class SSADictionary {

  /**
   * The object for the heap variable that is used for modelling
   * explicit exception dependencies
   */
  static final String exceptionState = "X-State";

  /**
   * A mapping from <code> Instruction </code> to the set of heap
   * operands (an <code> HeapOperand[]</code>) that this instruction
   * uses.  Note that PHI instructions <STRONG> do not </STRONG> appear in
   * this mapping.
   */
  private final HashMap<Instruction, HeapOperand<Object>[]> uses =
      new HashMap<Instruction, HeapOperand<Object>[]>();

  /**
   * A mapping from <code> Instruction </code> to the set of heap
   * operands (an <code> HeapOperand[]</code>) that this instruction
   * defines.  Note that PHI instructions <STRONG> do not </STRONG> appear in
   * this mapping.
   */
  private final HashMap<Instruction, HeapOperand<Object>[]> defs =
      new HashMap<Instruction, HeapOperand<Object>[]>();

  /**
   * A mapping from <code> HeapKey </code> to the set of heap
   * variables introduced for this IR
   */
  private final HashMap<HeapKey<Object>, HeapVariable<Object>> heapVariables =
      new HashMap<HeapKey<Object>, HeapVariable<Object>>();

  /**
   * A mapping from heap variable type to <code> Integer </code>
   * This structure holds the next number to assign when creating
   * a new heap variable name for a given type
   */
  private HashMap<Object, Integer> nextNumber = new HashMap<Object, Integer>();

  /**
   * A mapping from <code> BasicBlock </code> to <code> ArrayList
   * </code> of <code> Instruction </code>.
   * This map holds the list of heap phi instructions stored as
   * lookaside for each basic block.
   */
  private final HashMap<BasicBlock, ArrayList<Instruction>> heapPhi =
      new HashMap<BasicBlock, ArrayList<Instruction>>(10);

  /**
   * An empty vector, used for utility.
   */
  private static final ArrayList<Instruction> emptyArrayList = new ArrayList<Instruction>(0);

  /**
   * A mapping from <code> HeapVariable </code> to <code> HashSet
   * </code> of <code> HeapOperand </code>.
   * This map holds the set of heap operands which use each heap
   * variable.
   */
  private final HashMap<HeapVariable<Object>, HashSet<HeapOperand<Object>>> UseChain =
      new HashMap<HeapVariable<Object>, HashSet<HeapOperand<Object>>>(10);

  /**
   * A mapping from <code> HeapVariable </code> to <code> HeapOperand </code>.
   * This map holds the set of heap operands which define each heap
   * variable.
   */
  private final HashMap<HeapVariable<Object>, HeapOperand<Object>> DefChain =
      new HashMap<HeapVariable<Object>, HeapOperand<Object>>(10);

  /**
   * A mapping from a heap variable type to a <code> HashSet
   * </code> of <code> Instruction </code>.
   * The set of all uses of a heap variable type
   * <em> before </em> we performed renaming for SSA.
   */
  private final HashMap<Object, HashSet<HeapOperand<Object>>> originalUses =
      new HashMap<Object, HashSet<HeapOperand<Object>>>(10);

  /**
   * A mapping from a heap variable type to a <code> HashSet
   * </code> of <code> Instruction </code>.
   * The set of all definitions of a heap variable type
   * <em> before </em> we performed renaming for SSA.
   */
  private final HashMap<Object, HashSet<HeapOperand<Object>>> originalDefs =
      new HashMap<Object, HashSet<HeapOperand<Object>>>(10);

  /**
   * The set of type to build heap array variables for
   */
  private final Set<Object> heapTypes;

  /**
   * Should the heap array SSA form constructed include uphi functions?
   * That is, does a <em> use </em> create a new name for a heap variable.
   */
  private final boolean uphi;

  /**
   * Should PEIs and stores to the heap be modelled via an explicit exception
   * state heap variable?
   */
  private final boolean insertPEIDeps;

  /**
   * A pointer to the governing IR
   */
  private final IR ir;

  /**
   * Initialize a structure to hold Heap Array SSA information.
   *
   * @param heapTypes only create heap arrays for these locations.
   *                  if null, create all heap arrays
   * @param uphi Should we use uphi functions? (ie. loads create a new
   *                             name for heap arrays)
   */
  SSADictionary(Set<Object> heapTypes, boolean uphi, boolean insertPEIDeps, IR ir) {
    this.heapTypes = heapTypes;
    this.uphi = uphi;
    this.insertPEIDeps = insertPEIDeps;
    this.ir = ir;
  }

  /**
   * Does a particular instruction <em> use </em> any heap variable?
   *
   * @param s the instruction in question
   * @return true iff the instruction uses any heap variable.  false
   * otherwise
   */
  boolean usesHeapVariable(Instruction s) {
    // special case code for Phi instructions
    if (s instanceof Phi) {
      Operand result = ((Phi)s).getResult();
      return (result instanceof HeapOperand);
    }
    HeapOperand<Object>[] o = uses.get(s);
    return (o != null);
  }

  /**
   * Does a particular instruction <em> define </em> any heap variable?
   *
   * @param s the instruction in question
   * @return true iff the instruction defs any heap variable.  false
   * otherwise
   */
  boolean defsHeapVariable(Instruction s) {
    // special case code for Phi instructions
    if (s instanceof Phi) {
      Operand result = ((Phi)s).getResult();
      return (result instanceof HeapOperand);
    }
    HeapOperand<Object>[] o = defs.get(s);
    return (o != null);
  }

  /**
   * Return the heap operands used by an instruction.
   *
   * @param s the instruction in question
   * @return an array of the heap operands this instruction uses
   */
  public HeapOperand<Object>[] getHeapUses(Instruction s) {
    if (s instanceof Phi) {
      if (!usesHeapVariable(s)) return null;
      Phi phi = (Phi)s;
      HeapOperand<Object>[] result = new HeapOperand[phi.getNumberOf()];
      for (int i = 0; i < result.length; i++) {
        result[i] = (HeapOperand) phi.getValue(i);
      }
      return result;
    } else {
      return uses.get(s);
    }
  }

  /**
   * Return the heap operands defined by an instruction.
   *
   * @param s the instruction in question
   * @return an array of the heap operands this instruction defs
   */
  public HeapOperand<Object>[] getHeapDefs(Instruction s) {
    assert s instanceof Phi;
    return defs.get(s);
  }

  /**
   * Return the number of heap operands defined by an instruction
   *
   * @param s the instruction in question
   * @return the number of heap operands this instruction defs
   */
  int getNumberOfHeapDefs(Instruction s) {
    return getHeapDefs(s).length;
  }

  /**
   * Replace all heap variables that an instruction defs with
   * new heap variables.  Each new heap variable has the same
   * type as the old one, but a new number.  Essentially, this
   * function introduces new names required for translation
   * into SSA form.  This instruction will be the single static
   * definition for each new heap variable.
   *
   * @param s the instruction that writes to the new heap variables
   * @param b the basic block containing <code> s </code>
   * @return the new heap variables that the instruction defines
   */
  //@SuppressWarnings("unchecked")
  HeapOperand<Object>[] replaceDefs(Instruction s, BasicBlock b) {
    if (s instanceof Phi) {
      Phi phi = (Phi)s;
      // Note that a PHI
      // instruction defs exactly one heap variable, newH[0]
      HeapOperand<Object> oldDef = (HeapOperand) phi.getResult();
      int number = getNextHeapVariableNumber(oldDef.getHeapType());
      HeapOperand<Object>[] newH = new HeapOperand[1];
      newH[0] = new HeapOperand<Object>(new HeapVariable<Object>(oldDef.getHeapType(), number, ir));
      newH[0].setInstruction(s);
      phi.setResult(newH[0]);
      // record the new heap definition
      newH[0].getHeapVariable().registerDef(b);
      if (ir.dumpFile.current != null)
	ir.dumpFile.current.println("New heap def " + newH[0] + " for " + s);
      // store the new heap variable in relevant data structures
      HeapKey<Object> key = new HeapKey<Object>(number, oldDef.getHeapType());
      heapVariables.put(key, newH[0].getHeapVariable());
      return newH;
    } else {
      // get old heap operands defined by this instruction
      HeapOperand<Object>[] oldH = defs.get(s);
      HeapOperand<Object>[] newH = new HeapOperand[oldH.length];
      // for each old heap variable ..
      for (int i = 0; i < oldH.length; i++) {
        // create a new heap variable
        int number = getNextHeapVariableNumber(oldH[i].getHeapType());
        newH[i] = new HeapOperand<Object>(new HeapVariable<Object>(oldH[i].getHeapType(), number, ir));
        newH[i].setInstruction(s);
        // record the new heap definition
        newH[i].getHeapVariable().registerDef(b);
        if (ir.dumpFile.current != null)
          ir.dumpFile.current.println("New heap def " + newH[i] + " for " + s);
        // store the new heap variable in relevant data structures
        HeapKey<Object> key = new HeapKey<Object>(number, oldH[i].getHeapType());
        heapVariables.put(key, newH[i].getHeapVariable());
      }
      // record the new heap variables this instruction defs
      defs.put(s, newH);
      return newH;
    }
  }

  /**
   * Return an enumeration of the heap variables in this IR.
   *
   * @return the heap variables stored for this IR
   */
  Iterator<HeapVariable<Object>> getHeapVariables() {
    return heapVariables.values().iterator();
  }

  /**
   * Return an enumeration of the control-phi functions for
   * <em> Heap </em> variables at the beginning of a basic block.
   *
   * @param bb the basic block in question
   * @return the phi instructions for heap variables at the beginning of
   * the basic block
   */
  public Iterator<Instruction> getHeapPhiInstructions(BasicBlock bb) {
    ArrayList<Instruction> v = heapPhi.get(bb);
    if (v == null) {
      return emptyArrayList.iterator();
    }
    return v.iterator();
  }

  /**
   * Return an enumeration of all instructions for a basic block, including
   * the control-PHI functions for <em> heap </em> variables stored
   * implicitly here.
   *
   * @param bb the basic block in question
   * @return an enumeration of all instructions in this basic block,
   * including heap phi instructions stored implicitly in this lookaside
   * structure
   */
  AllInstructionEnumeration getAllInstructions(BasicBlock bb) {
    return new AllInstructionEnumeration(bb, this);
  }

  /**
   * Return an enumeration of all uses of a particular heap variable.
   *
   * @param A the heap variable in question
   * @return an iterator over all instructions that use this heap
   * variable
   */
  Iterator<HeapOperand<Object>> iterateHeapUses(HeapVariable<Object> A) {
    HashSet<HeapOperand<Object>> u = UseChain.get(A);
    return u.iterator();
  }

  /**
   * Return the operand that represents a heap variable's unique def.
   *
   * @param A the heap variable in question
   * @return the heap operand that represents this heap variable's unique
   * static definition
   */
  HeapOperand<Object> getUniqueDef(HeapVariable<Object> A) {
    return DefChain.get(A);
  }

  /**
   * Return an enumeration of all the original uses of a heap variable.
   * That is, return an iteration of all uses of the heap variable
   * <em> before </em> we performed renaming for SSA.
   *
   * @param A the heap variable in question
   * @return an iteration of all instructions that used this heap
   * variable, prior to renaming for SSA
   */
  @SuppressWarnings("unused")
  // Useful for debugging ??
  private Iterator<HeapOperand<Object>> iterateOriginalHeapUses(HeapVariable<Object> A) {
    Object type = A.getHeapType();
    HashSet<HeapOperand<Object>> set = findOrCreateOriginalUses(type);
    return set.iterator();
  }

  /**
   * Return an enumeration of all the original definitions of a heap variable.
   * That is, return an iteration of all defs of the heap variable
   * <em> before </em> we performed renaming for SSA.
   *
   * @param A the heap variable in question
   * @return an iteration of all instructions that defined this heap
   * variable, prior to renaming for SSA
   */
  @SuppressWarnings("unused")
  // Useful for debugging ??
  private Iterator<HeapOperand<Object>> iterateOriginalHeapDefs(HeapVariable<Object> A) {
    Object type = A.getHeapType();
    HashSet<HeapOperand<Object>> set = findOrCreateOriginalDefs(type);
    return set.iterator();
  }

  /**
   * Return a set of all the original uses of a heap variable.
   * That is, return the set of all uses of the heap variable
   * <em> before </em> we performed renaming for SSA.
   *
   * @param type   The heap variable in question
   * @return the set of all instructions that used this heap
   * variable, prior to renaming for SSA
   */
  private HashSet<HeapOperand<Object>> findOrCreateOriginalUses(Object type) {
    HashSet<HeapOperand<Object>> result = originalUses.get(type);
    if (result != null) {
      return result;
    }
    // not found: create a new set
    result = new HashSet<HeapOperand<Object>>(2);
    for (Iterator<HeapVariable<Object>> e = getHeapVariables(); e.hasNext();) {
      HeapVariable<Object> B = e.next();
      if (B.getHeapType().equals(type)) {
        HashSet<HeapOperand<Object>> u = UseChain.get(B);
        result.addAll(u);
      }
    }
    // store it in the hash set, and return
    originalUses.put(type, result);
    return result;
  }

  /**
   * Return a set of all the original definitions of a heap variable.
   * That is, return the set of all uses of the heap variable
   * <em> before </em> we performed renaming for SSA.
   *
   * @param type  the heap variable in question
   * @return the set of all instructions that defined this heap
   * variable, prior to renaming for SSA
   */
  private HashSet<HeapOperand<Object>> findOrCreateOriginalDefs(Object type) {
    HashSet<HeapOperand<Object>> result = originalDefs.get(type);
    if (result != null) {
      return result;
    }
    // not found: create a new set
    result = new HashSet<HeapOperand<Object>>(2);
    for (Iterator<HeapVariable<Object>> e = getHeapVariables(); e.hasNext();) {
      HeapVariable<Object> B = e.next();
      if (B.getHeapType().equals(type)) {
        HeapOperand<Object> def = getUniqueDef(B);
        if (def != null) {
          result.add(def);
        }
      }
    }
    // store it in the hash set, and return
    originalDefs.put(type, result);
    return result;
  }

  /**
   * Return the number of uses of a heap variable.
   *
   * @param A the heap variable in question
   * @return the number of uses of the heap variable
   */
  int getNumberOfUses(HeapVariable<Object> A) {
    HashSet<HeapOperand<Object>> u = UseChain.get(A);
    return u.size();
  }

   /**
   * Recompute the chain of uses for each heap variable.
   * NOTE: for now this procedure does <em> not </em> recompute
   * def chains
   */
  void recomputeArrayDU() {
    UseChain.clear();
    DefChain.clear();
    // create a set of uses for each heap variable
    for (Iterator<HeapVariable<Object>> e = getHeapVariables(); e.hasNext();) {
      HeapVariable<Object> H = e.next();
      HashSet<HeapOperand<Object>> u = new HashSet<HeapOperand<Object>>(2);
      UseChain.put(H, u);
    }
    // populate the use chain with each use registered
    for (HeapOperand<Object>[] operands : uses.values()) {
      if (operands == null) continue;
      for (HeapOperand<Object> operand : operands) {
        HeapVariable<Object> v = operand.getHeapVariable();
        HashSet<HeapOperand<Object>> u = UseChain.get(v);
        u.add(operand);
      }
    }
    // record the unique def for each heap variable
    for (HeapOperand<Object>[] operands : defs.values()) {
      if (operands == null) continue;
      for (HeapOperand<Object> operand : operands) {
        HeapVariable<Object> v = operand.getHeapVariable();
        DefChain.put(v, operand);
      }
    }
    // handle each HEAP PHI function.
    for (BasicBlockEnumeration e = ir.getBasicBlocks(); e.hasMoreElements();) {
      BasicBlock bb = e.nextElement();
      for (Iterator<Instruction> hp = getHeapPhiInstructions(bb); hp.hasNext();) {
        Phi phi = (Phi)hp.next();
        HeapOperand<Object> H = (HeapOperand) phi.getResult();
        HeapVariable<Object> v = H.getHeapVariable();
        DefChain.put(v, H);
        for (int i = 0; i < phi.getNumberOf(); i++) {
          HeapOperand<Object> Hu = (HeapOperand) phi.getValue(i);
          HeapVariable<Object> vu = Hu.getHeapVariable();
          HashSet<HeapOperand<Object>> u = UseChain.get(vu);
          u.add(Hu);
        }
      }
    }
  }

  /**
   * Delete an HeapOperand from the use chain of its heap variable
   *
   * @param op the heap operand to be deleted
   */
  void deleteFromUseChain(HeapOperand<Object> op) {
    HeapVariable<Object> hv = op.getHeapVariable();
    HashSet<HeapOperand<Object>> u = UseChain.get(hv);
    u.remove(op);
  }

  /**
   * Add an HeapOperand to the use chain of its heap variable
   *
   * @param op the heap operand to be added
   */
  void addToUseChain(HeapOperand<Object> op) {
    HeapVariable<Object> hv = op.getHeapVariable();
    HashSet<HeapOperand<Object>> u = UseChain.get(hv);
    u.add(op);
  }

  /**
   * Create a heap control phi instruction, and store it at the
   * beginning of a basic block.
   *
   * @param bb the basic block
   * @param H the heap variable to merge
   */
  void createHeapPhiInstruction(BasicBlock bb, HeapVariable<Object> H) {
    Instruction s = makePhiInstruction(H, bb);
    /*
    HeapOperand[] Hprime = new HeapOperand[1];
    Hprime[0] = new HeapOperand(H);
    Hprime[0].setInstruction(s);
    defs.put(s, Hprime);
    */
    ArrayList<Instruction> heapPhis = heapPhi.get(bb);
    if (heapPhis == null) {
      heapPhis = new ArrayList<Instruction>(2);
      heapPhi.put(bb, heapPhis);
    }
    heapPhis.add(s);
    registerInstruction(s, bb);
  }

  /**
   * Register that an instruction now uses the set of heap operands
   *
   * @param s the instruction in question
   * @param H the set of heap operands which s uses
   */
  void replaceUses(Instruction s, HeapOperand<Object>[] H) {
    assert !(s instanceof Phi);
    uses.put(s, H);
    for (HeapOperand<Object> aH : H) {
      aH.setInstruction(s);
    }
  }

  /**
   * Return the total number of heap variables allocated for the IR
   *
   * @return the total number of heap variables allocated for the IR
   */
  int getNumberOfHeapVariables() {
    return heapVariables.size();
  }

  /**
   * Register that an instruction s has unknown side effects.  That is,
   * we conservatively record that s defs and uses all heap variables.
   * <p> NOTE: This function should be called before renaming.
   *
   * @param s the instruction in question
   * @param b the basic block containing s
   */
  @SuppressWarnings("unchecked")
  void registerUnknown(Instruction s, BasicBlock b) {
    assert !(s instanceof Phi);
    // setup an array of all heap variables
    // TODO: for efficiency, cache a copy of 'all'
    Iterator vars = heapVariables.values().iterator();
    HeapOperand<Object>[] all = new HeapOperand[heapVariables.size()];
    for (int i = 0; i < all.length; i++) {
      all[i] = new HeapOperand<Object>((HeapVariable<Object>) vars.next());
      all[i].setInstruction(s);
      // record that all[i] is def'ed in b
      all[i].getHeapVariable().registerDef(b);
    }
    // record that s uses and defs all heap variables
    uses.put(s, all);
    defs.put(s, all);
  }

  /**
   * Record the heap variables that instruction s defines and uses.
   *
   * @param s the instruction in question
   * @param b the basic block containing s
   */
  void registerInstruction(Instruction s, BasicBlock b) {
    if (!s.isImplicitLoad() &&
        !s.isImplicitStore() &&
        !s.isAlloc() &&
        !(s instanceof Phi) &&
        !(insertPEIDeps &&
          (s.isPEI() ||
           s instanceof Label ||
           s instanceof BBend ||
           s instanceof UnintBegin ||
           s instanceof UnintEnd))) {
      return;
    }

    switch (s.getOpcode()) {
      case Operators.Label: // only reached if insertPEIDeps
        labelHelper(s, b);
        break;
      case Operators.BBend: // only reached if insertPEIDeps
        bbendHelper(s, b);
        break;
      case Operators.UnintBegin: // only reached if insertPEIDeps
      case Operators.UnintEnd: // only reached if insertPEIDeps
        registerUse(s, exceptionState);
        registerDef(s, b, exceptionState);
        break;
      case Operators.GetField:
        getFieldHelper(s, b);
        break;
      case Operators.PutField:
        putFieldHelper(s, b);
        break;
      case Operators.GetStatic:
        getStaticHelper(s, b);
        break;
      case Operators.PutStatic:
        putStaticHelper(s, b);
        break;
      case Operators.New:
      case Operators.NewUnresolved:
        newHelper(s, b);
        break;
      case Operators.Newarray:
      case Operators.NewarrayUnresolved:
        newArrayHelper(s, b);
        break;
      case Operators.NewObjMultiArray:
        /* SJF: after talking with Martin, I'm not sure what the
         correct Array SSA representation for an allocation should         be.  Since we do not yet use these heap variables, do
         nothing for now.
         Future: this should probably def the heap variable for every
         field of the object type allocated.
         // treat this opcode like a CALL
         registerUnknown(s,b);
         */
        break;
      case Operators.IntAload:
      case Operators.LongAload:
      case Operators.FloatAload:
      case Operators.DoubleAload:
      case Operators.RefAload:
      case Operators.ByteAload:
      case Operators.UbyteAload:
      case Operators.UshortAload:
      case Operators.ShortAload:
        aloadHelper(s, b);
        break;
      case Operators.IntAstore:
      case Operators.LongAstore:
      case Operators.FloatAstore:
      case Operators.DoubleAstore:
      case Operators.RefAstore:
      case Operators.ByteAstore:
      case Operators.ShortAstore:
        astoreHelper(s, b);
        break;
      case Operators.Arraylength:
        arraylengthHelper(s, b);
        break;
      case Operators.Call:
      case Operators.Syscall:
      case Operators.MonitorEnter:
      case Operators.MonitorExit:
      case Operators.PrepareInt:
      case Operators.PrepareAddr:
      case Operators.AttemptInt:
      case Operators.AttemptAddr:
      case Operators.ReadCeiling:
      case Operators.WriteFloor:
        // do nothing: these cases handled by registerUnknown
        break;
      case Operators.UbyteLoad:
      case Operators.ByteLoad:
      case Operators.UshortLoad:
      case Operators.ShortLoad:
      case Operators.IntLoad:
      case Operators.LongLoad:
      case Operators.FloatLoad:
      case Operators.DoubleLoad:
      case Operators.RefLoad:
        // !!TODO: how to handle this special case?
        break;
      case Operators.ByteStore:
      case Operators.ShortStore:
      case Operators.RefStore:
      case Operators.IntStore:
      case Operators.LongStore:
      case Operators.FloatStore:
      case Operators.DoubleStore:
        // !!TODO: how to handle this special case?
        break;
      case Operators.Phi:
        phiHelper(s, b);
        break;
      default:
        throw new Error("SSADictionary: Unsupported opcode " + s);
    }           // switch
    if (insertPEIDeps) {
      if (s.isImplicitStore()) addExceptionStateToUses(s);
      if (s.isPEI()) addExceptionStateToDefs(s, b);
    }
  }

  /**
   * Record the effects of a getfield instruction on the heap array
   * SSA form.  Register the heap variables that this instruction uses and
   * defs.  Note that if inserting uphi functions, a getfield defs a new
   * heap variable name.
   *
   * @param s the getfield instruction
   * @param b the basic block containing s
   */
  private void getFieldHelper(Instruction s, BasicBlock b) {
    GetField gf = (GetField)s;
    FieldOperand locOp = gf.getField();
    Field field = locOp.getField();
    registerUse(s, field);
    if (uphi) {
      registerDef(s, b, field);
    }
  }

  /**
   * Record the effects of a putfield instruction on the heap array
   * SSA form.  Register the heap variables that this instruction uses and
   * defs.
   *
   * @param s the getfield instruction
   * @param b the basic block containing s
   */
  private void putFieldHelper(Instruction s, BasicBlock b) {
    PutField pf = (PutField)s;
    FieldOperand locOp = pf.getField();
    Field field = locOp.getField();
    registerUse(s, field);
    registerDef(s, b, field);
  }

  /**
   * Record the effects of a getstatic instruction on the heap array
   * SSA form.  Register the heap variables that this instruction uses and
   * defs.  Note that if inserting uphi functions, a getstatic defs a new
   * heap variable name.
   *
   * @param s the getstatic instruction
   * @param b the basic block containing s
   */
  private void getStaticHelper(Instruction s, BasicBlock b) {
    GetStatic gs = (GetStatic)s;
    FieldOperand locOp = gs.getField();
    Field field = locOp.getField();
    registerUse(s, field);
    if (uphi) {
      registerDef(s, b, field);
    }
  }

  /**
   * Record the effects of a putstatic instruction on the heap array
   * SSA form.  Register the heap variables that this instruction uses and
   * defs.
   *
   * @param s the putstatic instruction
   * @param b the basic block containing s
   */
  private void putStaticHelper(Instruction s, BasicBlock b) {
    PutStatic ps = (PutStatic)s;
    FieldOperand locOp = ps.getField();
    Field field = locOp.getField();
    registerUse(s, field);
    registerDef(s, b, field);
  }

  /**
   * Update the heap array SSA form for an allocation instruction
   *
   * @param s the allocation instruction
   * @param b the basic block containing the allocation
   */
  private void newHelper(Instruction s, BasicBlock b) {
    /* SJF: after talking with Martin, I'm not sure what the
    correct Array SSA representation for an allocation should
    be.  Since we do not yet use these heap variables, do
    nothing for now.
    Future: this should probably def the heap variable for every
    field of the object type allocated.
    TypeOperand typeOp = New.getType(s);
    RVMType type = typeOp.type;
    registerUse(s,type);
    registerDef(s,b,type);
    */
  }

  /**
   * Update the heap array SSA form for an array allocation instruction
   *
   * @param s the allocation instruction
   * @param b the basic block containing the allocation
   */
  private void newArrayHelper(Instruction s, BasicBlock b) {
    // TODO: use some class hierarchy analysis
    /* SJF: after talking with Martin, I'm not sure what the
    correct Array SSA representation for an allocation should
    be.  Since we do not yet use these heap variables, do
    nothing for now.
    Future: this should probably def the heap variable for every
    field of the object type allocated.
    TypeOperand typeOp = NewArray.getType(s);
    RVMType type = typeOp.type;
    if (!type.asArray().getElementType().isPrimitiveType())
    type = ClassLoaderProxy.JavaLangObjectArrayType;
    registerUse(s,type);
    registerDef(s,b,type);
    */
  }

  /**
   * Record the effects of a aload instruction on the heap array
   * SSA form.  Register the heap variables that this instruction uses and
   * defs.  Note that if inserting uphi functions, a aload defs a new
   * heap variable name.
   *
   * @param s the aload instruction
   * @param b the basic block containing s
   */
  private void aloadHelper(Instruction s, BasicBlock b) {
    ALoad al = (ALoad)s;
    // TODO: use some class hierarchy analysis
    Type type = al.getArray().getType();

    // After cond branch splitting, operand may be a Null constant
    // filter out it now  -- Feng
    if (type.isArrayType()) {
      if (!type.getArrayElementType().isPrimitiveType()) {
        type = KnownTypes.OBJECT_ARRAY_TYPE;
      }
      registerUse(s, type);
      if (uphi) {
        registerDef(s, b, type);
      }
    }
  }

  /**
   * Record the effects of an astore instruction on the heap array
   * SSA form.  Register the heap variables that this instruction uses and
   * defs.
   *
   * @param s the astore instruction
   * @param b the basic block containing s
   */
  private void astoreHelper(Instruction s, BasicBlock b) {
    // TODO: use some class hierarchy analysis
    AStore as = (AStore)s;
    Type type = as.getArray().getType();

    // After cond branch splitting, operand may be a Null constant
    // filter out it now  -- Feng
    if (type.isArrayType()) {
      if (!type.getArrayElementType().isPrimitiveType()) {
        type = KnownTypes.OBJECT_ARRAY_TYPE;
      }
      registerUse(s, type);
      registerDef(s, b, type);
    }
  }

  /**
   * Record the effects of an arraylength instruction on the heap array
   * SSA form.  Register the heap variable that this instruction uses.
   *
   * @param s the arraylength instruction
   * @param b the basic block containing s
   */
  private void arraylengthHelper(Instruction s, BasicBlock b) {
    // TODO: use some class hierarchy analysis
    GuardedUnary gu = (GuardedUnary)s;
    Type type = gu.getVal().getType();

    // After cond branch splitting, operand may be a Null constant
    // filter it out now  -- Feng
    if (type.isArrayType()) {
      if (!type.getArrayElementType().isPrimitiveType()) {
        type = KnownTypes.OBJECT_ARRAY_TYPE;
      }
      registerUse(s, type);
    }
  }

  /**
   * Record the effects of a phi instruction on the heap array
   * SSA form.  Register the heap variables that this instruction uses
   * and defs.
   *
   * @param s the phi instruction
   * @param b the basic block containing s
   */
  private void phiHelper(Instruction s, BasicBlock b) {
    // for phi function, we dont' register implicit defs and uses
    // since they're explicit in the instruction
    /*
    Object result = Phi.getResult(s);
    if (!(result instanceof HeapOperand))
      return;
    HeapOperand H = (HeapOperand)Phi.getResult(s);
    Object Htype = H.getHeapType();
    if (Htype instanceof RVMType) {
      RVMType t = (RVMType)Htype;
      registerDef(s, b, t);
    }
    else if (Htype instanceof RVMField) {
      RVMField f = (RVMField)Htype;
      registerDef(s, b, f);
    }
    else {
      String a = (String)Htype;
      registerDef(s, b, a);
    }
    for (int i = 0; i < Phi.getNumberOfValues(s); i++) {
      HeapOperand U = (HeapOperand)Phi.getValue(s, i);
      Object Utype = U.getHeapType();
      if (Utype instanceof RVMType) {
        RVMType t = (RVMType)Utype;
        registerUse(s, t);
      }
      else if (Utype instanceof RVMField) {
        RVMField f = (RVMField)Utype;
        registerUse(s, f);
      } else {
        String a = (String)Utype;
        registerUse(s, a);
      }
    }
  */
  }

  /**
   * Record the effects of a label instruction on the heap array
   * SSA form.  Register the exception state that this instruction defs.
   *
   * @param s the label instruction
   * @param b the basic block containing s
   */
  private void labelHelper(Instruction s, BasicBlock b) {
    BasicBlockEnumeration e = b.getIn();
    boolean newHandler = !e.hasMoreElements();
    while (!newHandler && e.hasMoreElements()) {
      if (!(e.next().isExceptionHandlerEquivalent(b))) newHandler = true;
    }
    if (newHandler) registerDef(s, b, exceptionState);
  }

  /**
   * Record the effects of a bbend instruction on the heap array
   * SSA form.  Register the exception state that this instruction uses.
   *
   * @param s the label instruction
   * @param b the basic block containing s
   */
  private void bbendHelper(Instruction s, BasicBlock b) {
    BasicBlockEnumeration e = b.getOut();
    boolean newHandler = !e.hasMoreElements();
    while (!newHandler && e.hasMoreElements()) {
      if (!(e.next().isExceptionHandlerEquivalent(b))) newHandler = true;
    }
    if (newHandler) registerUse(s, exceptionState);
  }

  /**
   * Register that an instruction uses a heap variable of a given
   * type.
   *
   * @param s the instruction in question
   * @param t the type of the heap variable the instruction uses
   */
  private void registerUse(Instruction s, Type t) {
    // if the heapTypes set is defined, then we only build Array
    // SSA for these types.  So, ignore uses of types that are
    // not included in the set
    if (heapTypes != null) {
      if (!heapTypes.contains(t)) {
        return;
      }
    }
    HeapVariable<Object> H = findOrCreateHeapVariable(t);
    HeapOperand<Object>[] Hprime = new HeapOperand[1];
    Hprime[0] = new HeapOperand<Object>(H);
    Hprime[0].setInstruction(s);
    uses.put(s, Hprime);
  }

  /**
   * Register that an instruction writes a heap variable for a given
   * type.
   *
   * @param s the instruction in question
   * @param b s's basic block
   * @param t the type of the heap variable the instruction modifies
   */
  private void registerDef(Instruction s, BasicBlock b, Type t) {
    assert !(s instanceof Phi);
    // if the heapTypes set is defined, then we only build Array
    // SSA for these types.  So, ignore uses of types that are
    // not included in the set
    if (heapTypes != null) {
      if (!heapTypes.contains(t)) {
        return;
      }
    }
    HeapVariable<Object> H = findOrCreateHeapVariable(t);
    H.registerDef(b);
    HeapOperand<Object>[] Hprime = new HeapOperand[1];
    Hprime[0] = new HeapOperand<Object>(H);
    Hprime[0].setInstruction(s);
    defs.put(s, Hprime);
  }

  /**
   * Register that an instruction uses a heap variable for a given
   * field.
   *
   * @param s the instruction in question
   * @param fr the field heap variable the instruction uses
   */
  private void registerUse(Instruction s, Field f) {
    assert !(s instanceof Phi);
    HeapOperand<Object> H;
    if (f == null) {
      // can't resolve field at compile time.
      // This isn't quite correct, but is somewhat close.
      // See defect 3481.
      H = new HeapOperand<Object>(findOrCreateHeapVariable(f));
    } else {
      // if the heapTypes set is defined, then we only build Array
      // SSA for these types.  So, ignore uses of types that are
      // not included in the set
      if (heapTypes != null) {
        if (!heapTypes.contains(f)) {
          return;
        }
      }
      H = new HeapOperand<Object>(findOrCreateHeapVariable(f));
    }
    HeapOperand<Object>[] Hprime = new HeapOperand[1];
    Hprime[0] = H;
    Hprime[0].setInstruction(s);
    uses.put(s, Hprime);
  }

  /**
   * Register that instruction <code>s</code> writes a heap variable for
   * a given field.
   *
   * @param s the instruction in question
   * @param b  <code>s</code>'s basic block
   * @param fr the field heap variable the instruction modifies
   */
  private void registerDef(Instruction s, BasicBlock b, Field f) {
    assert !(s instanceof Phi);
    HeapOperand<Object> H;
    if (f == null) {
      // can't resolve field at compile time.
      // This isn't quite correct, but is somewhat close.
      // See bug #1147433
      H = new HeapOperand<Object>(findOrCreateHeapVariable(f));
    } else {
      // if the heapTypes set is defined, then we only build Array
      // SSA for these types.  So, ignore uses of types that are
      // not included in the set
      if (heapTypes != null) {
        if (!heapTypes.contains(f)) {
          return;
        }
      }
      H = new HeapOperand<Object>(findOrCreateHeapVariable(f));
    }
    H.value.registerDef(b);
    HeapOperand<Object>[] Hprime = new HeapOperand[1];
    Hprime[0] = H;
    Hprime[0].setInstruction(s);
    defs.put(s, Hprime);
  }

  /**
   * Register that an instruction uses a heap variable for a given
   * field.
   *
   * @param s the instruction in question
   * @param a the field heap variable the instruction uses
   */
  @SuppressWarnings("unchecked")
  private void registerUse(Instruction s, String a) {
    assert !(s instanceof Phi);
    // if the heapTypes set is defined, then we only build Array
    // SSA for these types.  So, ignore uses of types that are
    // not included in the set
    if (heapTypes != null) {
      if (!heapTypes.contains(a)) {
        return;
      }
    }
    HeapVariable<Object> H = findOrCreateHeapVariable(a);
    HeapOperand<Object>[] Hprime = new HeapOperand[1];
    Hprime[0] = new HeapOperand<Object>(H);
    Hprime[0].setInstruction(s);
    uses.put(s, Hprime);
  }

  /**
   * Register that the instruction <code>s</code> writes a heap variable for
   * a given field.
   *
   * @param s the instruction in question
   * @param b <code>s</code>'s basic block
   * @param a  XXX TODO Check this XXX The field in question.
   */
  @SuppressWarnings("unchecked")
  private void registerDef(Instruction s, BasicBlock b, String a) {
    assert !(s instanceof Phi);
    // if the heapTypes set is defined, then we only build Array
    // SSA for these types.  So, ignore uses of types that are
    // not included in the set
    if (heapTypes != null) {
      if (!heapTypes.contains(a)) {
        return;
      }
    }
    HeapVariable<Object> H = findOrCreateHeapVariable(a);
    H.registerDef(b);
    HeapOperand<Object>[] Hprime = new HeapOperand[1];
    Hprime[0] = new HeapOperand<Object>(H);
    Hprime[0].setInstruction(s);
    defs.put(s, Hprime);
  }

  /**
   * Returns a copy of H with an additional free slot at position 0
   *
   * @param H the array of HeapOperands to be extended.
   */
  @SuppressWarnings("unchecked")
  private static HeapOperand<Object>[] extendHArray(HeapOperand<Object>[] H) {
    HeapOperand<Object>[] res;

    if (H == null) {
      res = new HeapOperand[1];
    } else {
      res = new HeapOperand[H.length + 1];
      for (int i = 0; i < H.length; ++i) {
        res[i + 1] = H[i];
      }
    }
    return res;
  }

  /**
   * Register that an instruction defines the exception state.
   *
   * @param s the instruction in question
   * @param b s's basic block
   */
  @SuppressWarnings("unchecked")
  void addExceptionStateToDefs(Instruction s, BasicBlock b) {
    assert !(s instanceof Phi);
    HeapVariable<Object> H = findOrCreateHeapVariable(exceptionState);
    H.registerDef(b);
    HeapOperand<Object>[] Hprime = extendHArray(defs.get(s));
    Hprime[0] = new HeapOperand<Object>(H);
    Hprime[0].setInstruction(s);
    defs.put(s, Hprime);
  }

  /**
   * Register that an instruction defines the exception state.
   *
   * @param s the instruction in question
   */
  @SuppressWarnings("unchecked")
  void addExceptionStateToUses(Instruction s) {
    assert !(s instanceof Phi);
    HeapVariable<Object> H = findOrCreateHeapVariable(exceptionState);
    HeapOperand<Object>[] Hprime = extendHArray(uses.get(s));
    Hprime[0] = new HeapOperand<Object>(H);
    Hprime[0].setInstruction(s);
    uses.put(s, Hprime);
  }

  /**
   * Return the heap variable for a given type or field with
   * number 0.
   * If no heap variable yet exits for this type or field, create a new
   * one.
   *
   * @param type the <code> TypeReference </code> or <code> RVMField </code>
   * identifying the desired heap variable
   * @return the desired heap variable
   */
  @SuppressWarnings("unchecked")
  private HeapVariable<Object> findOrCreateHeapVariable(Object type) {
    if (ir.dumpFile.current != null) {
      ir.dumpFile.current.print("findOrCreateHeapVariable " + type);
    }
    HeapKey<Object> key = new HeapKey<Object>(0, type);
    HeapVariable<Object> H = heapVariables.get(key);
    if (ir.dumpFile.current != null) {
      ir.dumpFile.current.println("...  found " + H);
    }
    if (H == null) {
      // note: if not found, then we have never created a heap
      // variable with the correct type. So, create one, with number
      // 0
      int number = getNextHeapVariableNumber(type);        // should return 0
      H = new HeapVariable<Object>(type, number, ir);
      heapVariables.put(key, H);
      if (ir.dumpFile.current != null) {
	ir.dumpFile.current.println("...  created " + heapVariables.get(key));
      }
    }
    return H;
  }

  /**
   * Get the next number to be assigned to a new heap variable
   * for a given type or field.
   *
   * @param type the <code> TypeReference </code> or <code> RVMField </code>
   * identifying the heap variable
   * @return the next integer (monotonically increasing) to identify a new
   * name for this heap variable
   */
  private int getNextHeapVariableNumber(Object type) {
    Integer current = nextNumber.get(type);
    if (current == null) {
      // no number found. Create one.
      Integer one = 1;
      nextNumber.put(type, one);
      return 0;
    }
    // bump up the number
    Integer next = current + 1;
    nextNumber.put(type, next);
    return current;
  }

  /**
   * Create a phi-function instruction for a heap variable
   *
   * @param H a symbolic variable for a Heap variable
   * @param bb the basic block holding the new phi function
   * instruction
   * @return the instruction <code> H = phi H,H,..,H </code>
   */
  private static Instruction makePhiInstruction(HeapVariable<Object> H, BasicBlock bb) {
    int n = bb.getNumberOfIn();
    BasicBlockEnumeration in = bb.getIn();
    HeapOperand<Object> lhs = new HeapOperand<Object>(H);
    Phi phi = new Phi(lhs, n);
    lhs.setInstruction(phi);
    for (int i = 0; i < n; i++) {
      HeapOperand<Object> op = new HeapOperand<Object>(H);
      op.setInstruction(phi);
      phi.setValue(i, op);
      BasicBlock pred = in.next();
      phi.setPred(i, new BasicBlockOperand(pred));
    }
    return phi;
  }

  /**
   * This class represents the name of a heap variable in the heap array
   * SSA form.
   */
  private static final class HeapKey<T> {
    /**
     * The number and type comprise the name of a heap variable in array SSA
     * form
     */
    private final int number;
    /**
     * The number and type comprise the name of a heap variable in array SSA
     * form
     */
    private final T type;

    /**
     * Create a new name for a heap variable.
     *
     * @param     number the number, a unique integer from SSA renaming
     * @param     type   the type (a <code> RVMField </code> or <code>
     * TypeReference </code>
     */
    HeapKey(int number, T type) {
      this.number = number;
      this.type = type;
    }

    /**
     * Test against another key for equality.  This function is used to
     * retrive items from hashtables.
     *
     * @param key the object to compare with
     * @return true or false as appropriate
     */
    public boolean equals(Object key) {
      if (!(key instanceof HeapKey)) {
        return false;
      }
      HeapKey<T> k = (HeapKey) key;
      return ((type.equals(k.type)) && (number == k.number));
    }

    /**
     * Return a hash code for this name.
     *
     * TODO: come up with a better hash function.
     *
     * @return the hash code
     */
    public int hashCode() {
      return type.hashCode() + 8192 * number;
    }
  }

  /**
   * This class implements an <code> Enumeration </code> over all
   * instructions for a basic block. This enumeration includes
   * explicit instructions in the IR and implicit phi instructions
   * for heap variables, which are stored only in this lookaside
   * structure.
   */
  static final class AllInstructionEnumeration implements Enumeration<Instruction> {
    /**
     * An enumeration of the explicit instructions in the IR for a basic
     * block
     */
    private final InstructionEnumeration explicitInstructions;

    /**
     * An enumeration of the implicit instructions in the IR for a basic
     * block.  These instructions appear only in the SSA dictionary
     * lookaside structure.
     */
    private final Iterator<Instruction> implicitInstructions;

    /**
     * The label instruction for the basic block
     */
    private Instruction labelInstruction;

    /**
     * Construct an enumeration for all instructions, both implicit and
     * explicit in the IR, for a given basic block
     *
     * @param     bb the basic block whose instructions this enumerates
     */
    AllInstructionEnumeration(BasicBlock bb, SSADictionary dict) {
      explicitInstructions = bb.forwardInstrEnumerator();
      implicitInstructions = dict.getHeapPhiInstructions(bb);
      labelInstruction = explicitInstructions.next();
    }

    /**
     * Are there more elements in the enumeration?
     *
     * @return true or false
     */
    public boolean hasMoreElements() {
      return (implicitInstructions.hasNext() || explicitInstructions.hasMoreElements());
    }

    /**
     * Get the next instruction in the enumeration
     *
     * @return the next instruction
     */
    public Instruction nextElement() {
      if (labelInstruction != null) {
        Instruction temp = labelInstruction;
        labelInstruction = null;
        return temp;
      }
      if (implicitInstructions.hasNext()) {
        return implicitInstructions.next();
      }
      return explicitInstructions.next();
    }
  }
}
