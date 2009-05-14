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

package x10me.opt.ir.operand;

import x10me.types.Address;
import x10me.types.Extent;
import x10me.types.KnownTypes;
import x10me.types.Offset;
import x10me.types.Type;
import x10me.types.Word;

/**
 * Represents an address constant operand.
 *
 * @see Operand
 */
public final class AddressConstantOperand extends ConstantOperand {

  /**
   * Value of this operand.
   */
  public final Address value;

  /**
   * Constructs a new address constant operand with the specified value.
   *
   * @param v value
   */
  public AddressConstantOperand(Address v) {
    value = v;
  }

  /**
   * Constructs a new address constant operand with the specified offset value.
   *
   * @param v value
   * TODO: make a separte OffsetConstantOperand
   */
  public AddressConstantOperand(Offset v) {
    this(v.toWord().toAddress());
  }

  /**
   * Constructs a new address constant operand with the specified offset value.
   *
   * @param v value
   * TODO: make a separte OffsetConstantOperand
   */
  public AddressConstantOperand(Extent v) {
    this(v.toWord().toAddress());
  }

  /**
   * Constructs a new address constant operand with the specified offset value.
   *
   * @param v value
   * TODO: make a separte OffsetConstantOperand
   */
  public AddressConstantOperand(Word v) {
    this(v.toAddress());
  }

  /**
   * Return a new operand that is semantically equivalent to <code>this</code>.
   *
   * @return a copy of <code>this</code>
   */
  public Operand copy() {
    return new AddressConstantOperand(value);
  }

  /**
   * Return the {@link TypeReference} of the value represented by the operand.
   *
   * @return TypeReference.Address
   */
  public Type getType() {
    return KnownTypes.ADDRESS_TYPE;
  }

  /**
   * Does the operand represent a value of the address data type?
   *
   * @return <code>true</code>
   */
  public boolean isAddress() {
    return true;
  }

  /**
   * Are two operands semantically equivalent?
   *
   * @param op other operand
   * @return   <code>true</code> if <code>this</code> and <code>op</code>
   *           are semantically equivalent or <code>false</code>
   *           if they are not.
   */
  public boolean similar(Operand op) {
    return equals(op);
  }

  public boolean equals(Object o) {
    return (o instanceof AddressConstantOperand) && (value.EQ(((AddressConstantOperand) o).value));
  }

  public int hashCode() {
    return value.toInt() >>> 3;
  }

  /**
   * Returns the string representation of this operand.
   *
   * @return a string representation of this operand.
   */
  public String toString() {
    return "Addr " + Long.toHexString(value.toLong());
  }
}
