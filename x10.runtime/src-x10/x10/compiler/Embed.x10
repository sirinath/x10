/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

package x10.compiler;

import x10.lang.annotations.ExpressionAnnotation;
import x10.lang.annotations.StatementAnnotation;

/** An annotation that requests the compiler to embed an object inside another object
 * must be used *exactly* as the following for now:
 * class C {
 *   @Embed val v:T;
 *   def this() {
 *     v = @Embed new T(...);
 *   }
 * }
 * EXPERIMENTAL
 * @author Olivier Tardieu
 */
public interface Embed extends StatementAnnotation,ExpressionAnnotation { }
