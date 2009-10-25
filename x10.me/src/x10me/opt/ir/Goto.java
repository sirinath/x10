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

package x10me.opt.ir;

import x10me.opt.ir.operand.*;

/**
 * Class file for Goto Instruction class.
 */
public final class Goto extends GotoParent {

  /**
    * Constructor for Goto.
    *
    * @param target
    */
  public Goto (BranchOperand target) {
    super (target);
  }

  /**
    * Return the name of the instruction.
    */
  public String nameOf() {
    return "Goto";
  }

  @Override
  public char getOpcode() {
    return Operators.Goto;
  }

  @Override
  public boolean isBranch() {
    return true;
  }

  @Override
  public boolean isUnconditionalBranch() {
    return true;
  }

}
