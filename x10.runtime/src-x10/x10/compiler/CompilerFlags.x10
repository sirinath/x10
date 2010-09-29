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

/** 
 * A class that allows transmission of compiler command line 
 * flags to to code in the class libraries that wants to check
 * compilation modes.
 */
public class CompilerFlags { 

    /**
     * @return <code>true</code> if the compiler was invoked with
     *          the -NO_CHECKS flags, <code>false</code> otherwise.
     */
    @Native("java", "(!`NO_CHECKS`)")
    @Native("c++", "BOUNDS_CHECK_BOOL")
    public static native def checkBounds():boolean;

    /**
     * @return <code>true</code> if the compiler was invoked with
     *          the -NO_CHECKS flags, <code>false</code> otherwise.
     */
    @Native("java", "(!`NO_CHECKS`)")
    @Native("c++", "PLACE_CHECK_BOOL")
    public static native def checkPlace():boolean;
}
