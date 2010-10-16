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
 * Two or more methods of a class or interface may have the same name
 * if they have a different number of type parameters, or they have
 * value parameters of different types.
 *
 * @author bdlucas 8/2008
 */

public class GenericOverloading06 extends GenericTest {

    static class A[T] {
        def m() = 0;
        def m(T) = 1;
        def m(int,T) = 2;
    }

    val a = new A[int]();

    public def run(): boolean = {

        check("a.m()", a.m(), 0);
        check("a.m(0)", a.m(0), 1);
        check("a.m(0,0)", a.m(0,0), 2);

        return result;
    }

    public static def main(var args: Array[String](1)): void = {
        new GenericOverloading06().execute();
    }
}
