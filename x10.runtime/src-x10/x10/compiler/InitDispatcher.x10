/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

package x10.compiler;

import x10.compiler.NativeRep;

/**
 * Used for generating static initialization code in Java backend.
 * 
 */
@NativeRep("java", "x10.runtime.impl.java.InitDispatcher", null, null)
class InitDispatcher {
}

// vim:shiftwidth=4:tabstop=4:expandtab
