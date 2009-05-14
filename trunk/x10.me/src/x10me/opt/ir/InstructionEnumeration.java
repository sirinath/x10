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

import java.util.Enumeration;

/**
 * Extend java.util.Enumeration to avoid downcasts from object.
 * Also provide a preallocated empty instruction enumeration.
 */
public interface InstructionEnumeration extends Enumeration<Instruction> {
  /**
   * Same as nextElement but avoid the need to downcast from Object
   */
  Instruction next();
}

