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
import x10me.opt.ir.*;
import x10me.opt.ir.operand.BranchOperand;
import x10me.opt.ir.operand.BranchProfileOperand;
import x10me.opt.ir.operand.ConditionOperand;
import x10me.opt.ir.operand.IntConstantOperand;
import x10me.opt.ir.operand.Operand;
import x10me.opt.ir.operand.RegisterOperand;
import x10me.opt.ir.operand.TrueGuardOperand;

/**
 * Simplify and canonicalize conditional branches with constant operands.
 * 
 * <p>
 * This module performs no analysis, it simply attempts to simplify any
 * branching instructions of a basic block that have constant operands. The
 * intent is that analysis modules can call this transformation engine, allowing
 * us to share the simplification code among multiple analysis modules.
 */
public abstract class BranchSimplifier {

  /**
   * Given a basic block, attempt to simplify any conditional branch
   * instructions with constant operands. The instruction will be mutated in
   * place. The control flow graph will be updated, but the caller is
   * responsible for calling BranchOptmizations after simplify has been called
   * on all basic blocks in the IR to remove unreachable code.
   * 
   * @param bb
   *          the basic block to simplify
   * @return true if we do something, false otherwise.
   */
  public static boolean simplify(BasicBlock bb, IR ir) {
    boolean didSomething = false;

    for (InstructionEnumeration branches = bb.enumerateBranchInstructions(); branches
	.hasMoreElements();) {
      Instruction s = branches.next();
      if (s instanceof Goto) {
	// nothing to do, but a common case so test first
      } else if (s instanceof IfCmp) {
	IfCmp t = (IfCmp) s;
	if (processIfCmp(ir, bb, t)) {
	  // hack. Just start over since Enumeration has changed.
	  branches = bb.enumerateBranchInstructions();
	  bb.recomputeNormalOut(ir);
	  didSomething = true;
	}
      } else if (s instanceof IfCmp2) {
	IfCmp2 t = (IfCmp2) s;
	if (processIfCmp2(ir, bb, t)) {
	  // hack. Just start over since Enumeration has changed.
	  branches = bb.enumerateBranchInstructions();
	  bb.recomputeNormalOut(ir);
	  didSomething = true;
	}
      } else if (s instanceof LookupSwitch) {
	LookupSwitch t = (LookupSwitch) s;
	if (processLookupSwitch(ir, bb, t)) {
	  // hack. Just start over since Enumeration has changed.
	  branches = bb.enumerateBranchInstructions();
	  bb.recomputeNormalOut(ir);
	  didSomething = true;
	}
      } else if (s instanceof TableSwitch) {
	TableSwitch t = (TableSwitch) s;
	if (processTableSwitch(ir, bb, t)) {
	  // hack. Just start over since Enumeration has changed.
	  branches = bb.enumerateBranchInstructions();
	  bb.recomputeNormalOut(ir);
	  didSomething = true;
	}
      } else if (s instanceof InlineGuard) {
	InlineGuard t = (InlineGuard) s;
	if (processInlineGuard(ir, bb, t)) {
	  // hack. Just start over since Enumeration has changed.
	  branches = bb.enumerateBranchInstructions();
	  bb.recomputeNormalOut(ir);
	  didSomething = true;
	}
      }
    }
    return didSomething;
  }

  /** Process IfCmp branch instruction */
  static boolean processIfCmp(IR ir, BasicBlock bb, IfCmp s) {
    RegisterOperand guard = s.getGuardResult();
    Operand val1 = s.getVal1();
    Operand val2 = s.getVal2();
    {
      int cond = s.getCond().evaluate(val1, val2);
      if (cond != ConditionOperand.UNKNOWN) {
	// constant fold
	if (cond == ConditionOperand.TRUE) { // branch taken
	  insertTrueGuard(s, guard);
	  replaceWithGoto(s, s.getClearTarget());
	  removeBranchesAfterGotos(bb);
	} else {
	  // branch not taken
	  insertTrueGuard(s, guard);
	  s.remove();
	}
	return true;
      }
    }
    if (val1.isConstant() && !val2.isConstant()) {
      // Canonicalize by making second argument the constant
      s.setVal1(val2);
      s.setVal2(val1);
      s.setCond(s.getCond().flipOperands());
    }

    if (val2.isIntConstant()) {
      // Tricks to get compare against zero.
      int value = ((IntConstantOperand) val2).value;
      ConditionOperand cond = s.getCond();
      if (value == 1) {
	if (cond.isLESS()) {
	  s.setCond(ConditionOperand.LESS_EQUAL());
	  s.setVal2(new IntConstantOperand(0));
	} else if (cond.isGREATER_EQUAL()) {
	  s.setCond(ConditionOperand.GREATER());
	  s.setVal2(new IntConstantOperand(0));
	}
      } else if (value == -1) {
	if (cond.isGREATER()) {
	  s.setCond(ConditionOperand.GREATER_EQUAL());
	  s.setVal2(new IntConstantOperand(0));
	} else if (cond.isLESS_EQUAL()) {
	  s.setCond(ConditionOperand.LESS());
	  s.setVal2(new IntConstantOperand(0));
	}
      }
    }
    return false;
  }

  /** Process IfCmp2 branch instruction */
  static boolean processIfCmp2(IR ir, BasicBlock bb, IfCmp2 s) {
    RegisterOperand guard = s.getGuardResult();
    Operand val1 = s.getVal1();
    Operand val2 = s.getVal2();
    int cond1 = s.getCond1().evaluate(val1, val2);
    int cond2 = s.getCond2().evaluate(val1, val2);
    if (cond1 == ConditionOperand.TRUE) {
      // target 1 taken
      insertTrueGuard(s, guard);
      replaceWithGoto(s, s.getTarget1());
      removeBranchesAfterGotos(bb);
    } else if ((cond1 == ConditionOperand.FALSE)
	&& (cond2 == ConditionOperand.TRUE)) {
      // target 2 taken
      insertTrueGuard(s, guard);
      replaceWithGoto(s, s.getTarget2());
      removeBranchesAfterGotos(bb);
    } else if ((cond1 == ConditionOperand.FALSE)
	&& (cond2 == ConditionOperand.FALSE)) {
      // not taken
      insertTrueGuard(s, guard);
      s.remove();
    } else if ((cond1 == ConditionOperand.FALSE)
	&& (cond2 == ConditionOperand.UNKNOWN)) {
      // target 1 not taken, simplify test to ifcmp
      replaceWithIntIfcmp(s, guard, val1, val2, s.getCond2(), s.getTarget2(), s
	  .getBranchProfile2());
    } else if ((cond1 == ConditionOperand.UNKNOWN)
	&& (cond2 == ConditionOperand.FALSE)) {
      // target 1 taken possibly, simplify test to ifcmp
      replaceWithIntIfcmp(s, guard, val1, val2, s.getCond1(), s.getTarget1(), s
	  .getBranchProfile1());
    } else if ((cond1 == ConditionOperand.UNKNOWN)
	&& (cond2 == ConditionOperand.TRUE)) {
      // target 1 taken possibly, simplify first test to ifcmp and
      // insert goto after
      s.insertAfter(new Goto(s.getTarget2()));
      replaceWithIntIfcmp(s, guard, val1, val2, s.getCond1(), s.getTarget1(), s
	  .getBranchProfile1());
      removeBranchesAfterGotos(bb);
    } else {
      if (val1.isConstant() && !val2.isConstant()) {
	// Canonicalize by making second argument the constant
	s.setVal1(val2);
	s.setVal2(val1);
	s.setCond1(s.getCond1().flipOperands());
	s.setCond2(s.getCond2().flipOperands());
      }
      // we did no optimisation, return false
      return false;
    }
    return true;
  }

  /** Process LookupSwitch branch instruction */
  static boolean processLookupSwitch(IR ir, BasicBlock bb, LookupSwitch s) {
    Operand val = s.getValue();
    int numMatches = s.getNumberOf();
    if (numMatches == 0) {
      // Can only goto default
      replaceWithGoto(s, s.getDefaultTarget());
    } else if (val.isConstant()) {
      // lookup value is constant
      int value = ((IntConstantOperand) val).value;
      BranchOperand target = s.getDefaultTarget();
      for (int i = 0; i < numMatches; i++) {
	if (value == s.getMatch(i).value) {
	  target = s.getTarget(i);
	  break;
	}
      }
      replaceWithGoto(s, target);
    } else if (numMatches == 1) {
      // only 1 match, simplify to ifcmp
      BranchOperand defaultTarget = s.getDefaultTarget();
      replaceWithIntIfcmp(s, ir.regpool.makeTempValidation(), val, s
	  .getMatch(0), ConditionOperand.EQUAL(), s.getTarget(0), s
	  .getBranchProfile(0));
      s.insertAfter(new Goto(defaultTarget));
    } else {
      // no optimisation, just continue
      return false;
    }
    return true;
  }

  /** Process TableSwitch branch instruction */
  static boolean processTableSwitch(IR ir, BasicBlock bb, TableSwitch s) {
    Operand val = s.getValue();
    int low = s.getLow().value;
    int high = s.getHigh().value;
    if (val.isConstant()) {
      int value = ((IntConstantOperand) val).value;
      BranchOperand target = s.getDefaultTarget();
      if (value >= low && value <= high) {
	target = s.getTarget(value - low);
      }
      replaceWithGoto(s, target);
    } else if (low == high) {
      // only 1 match, simplify to ifcmp
      BranchOperand defaultTarget = s.getDefaultTarget();
      replaceWithIntIfcmp(s, ir.regpool.makeTempValidation(), val,
	  new IntConstantOperand(low), ConditionOperand.EQUAL(),
	  s.getTarget(0), s.getBranchProfile(0));
      s.insertAfter(new Goto(defaultTarget));
    } else {
      // no optimisation, just continue
      return false;
    }
    return true;
  }

  /** Process InlineGuard branch instruction */
  static boolean processInlineGuard(IR ir, BasicBlock bb, InlineGuard s) {
    Operand val = s.getValue();
    if (val.isNullConstant()) {
      // branch not taken
      s.remove();
      return true;
    } else if (val.isObjectConstant()) {
      // TODO:
      // VM.sysWrite("TODO: should constant fold MethodIfCmp on ObjectConstant");
    }
    return false;
  }

  /**
   * To maintain IR integrity, remove any branches that are after the first GOTO
   * in the basic block.
   */
  private static void removeBranchesAfterGotos(BasicBlock bb) {
    // identify the first GOTO instruction in the basic block
    Instruction firstGoto = null;
    Instruction end = bb.lastRealInstruction();
    for (InstructionEnumeration branches = bb.enumerateBranchInstructions(); branches
	.hasMoreElements();) {
      Instruction s = branches.next();
      if (s instanceof Goto) {
	firstGoto = s;
	break;
      }
    }
    // remove all instructions after the first GOTO instruction
    if (firstGoto != null) {
      InstructionEnumeration ie = IREnumeration.forwardIntraBlockIE(firstGoto,
	  end);
      ie.next();
      while (ie.hasMoreElements()) {
	Instruction s = ie.next();
	if (s instanceof HasGuardResult) {
	  insertTrueGuard(s, ((HasGuardResult) s).getGuardResult());
	}
	s.remove();
      }
    }
  }

  private static void insertTrueGuard(Instruction inst, RegisterOperand guard) {
    if (guard == null)
      return; // Probably bad style but correct IR
    Instruction trueGuard = new GuardMove(guard.copyD2D(),
	new TrueGuardOperand());
    trueGuard.position = inst.position;
    trueGuard.bcIndex = inst.bcIndex;
    inst.insertBefore(trueGuard);
    DefUse.updateDUForNewInstruction(trueGuard);
  }

  /**
   * Replace s with a goto using o.
   * 
   * @param s
   *          is the instruction to be replaced.
   * @param o
   *          the operand of the new goto.
   */
  private static void replaceWithGoto(Instruction s, BranchOperand o) {
    s.replace(new Goto(o));
  }

  /**
   * Replace s with a new IntIfcmp instruction.
   * 
   * @param s
   *          the instruction to be replaced.
   * @param guardResult
   * @param val1
   * @param val2
   * @param cond
   * @param target
   * @param branchProfile
   */
  private static void replaceWithIntIfcmp(Instruction s,
      RegisterOperand guardResult, Operand val1, Operand val2,
      ConditionOperand cond, BranchOperand target,
      BranchProfileOperand branchProfile) {
    s.replace(new IntIfcmp(guardResult, val1, val2, cond, target,
	branchProfile));
  }
}
