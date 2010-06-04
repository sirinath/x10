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

import harness.x10Test;



/**
 * STATIC SEMANTICS RULE: If a class C overrides a method of a class or
 * interface B, the guard of the method in B must entail the guard of the
 * method in C.
 *
 * @author bdlucas 8/2008
 */

public class GenericOverriding09_MustFailCompile extends GenericTest {

    class A[T] {
        def m() = 0;
    }
        
    class B[T] extends A[T] {
        def m(){T<:String} = 1;
    }

    public def run() = true;

    public static def main(var args: Rail[String]): void = {
        new GenericOverriding09_MustFailCompile().execute();
    }
}
