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

import x10.compiler.ArithmeticOps;

/**
 * @author bdlucas 8/2008
 */

public class GenericCast05 extends GenericTest {

    interface I[T] {
        def m(T):int;
        def n(T):int;
    }

    interface J[T] {
        def m(T):int;
        def o(T):int;
    }

    class A implements I[int], J[int] {
        public def m(int) = 0;
        public def n(int) = 1;
        public def o(int) = 2;
    }

    public def run() = {

        var a:Object = new A();
        var i:I[int]! = a as I[int];
        var j:J[int]! = a as J[int];

        check("i.m(0)", i.m(0), 0);
        check("i.n(0)", i.n(0), 1);

        check("j.m(0)", j.m(0), 0);
        check("j.o(0)", j.o(0), 2);

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new GenericCast05().execute();
    }
}
