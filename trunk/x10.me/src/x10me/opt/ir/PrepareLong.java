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
 * Class file for PrepareLong Instruction class.
 */
public final class PrepareLong extends Prepare {

  /**
    * Constructor for PrepareLong.
    *
    * @param result
    * @param address
    * @param offset
    * @param field
    * @param guard
    */
  public PrepareLong (Operand result, Operand address, Operand offset, FieldOperand field, Operand guard) {
    super (result, address, offset, field, guard);
  }
  /**
    * Constructor for PrepareLong without option parameter.
    *
    * @param result
    * @param address
    * @param offset
    * @param field
    */
  public PrepareLong (Operand result, Operand address, Operand offset, FieldOperand field) {
    super (result, address, offset, field, null);
  }

  /**
    * Return the name of the instruction.
    */
  public String nameOf() {
    return "PrepareLong";
  }

  @Override
  public char getOpcode() {
    return Operators.PrepareLong;
  }

  @Override
  public boolean isExplicitLoad() {
    return true;
  }

  @Override
  public boolean isImplicitLoad() {
    return true;
  }

  @Override
  public boolean isAcquire() {
    return true;
  }

}
