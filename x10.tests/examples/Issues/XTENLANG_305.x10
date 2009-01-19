// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

/**
 * @author bdlucas 12/2008
 */

import x10.util.concurrent.atomic.AtomicInteger;

class XTENLANG_305 extends x10Test {

    class A[T] implements (nat,nat,nat)=>T {
        public def set(v:T, i:int, j:int, k:int): T = 0 to T;
        public def apply(i:int, j:int, k:int): T = 0 to T;
    }

    def foo(a:A[double]) {
        a(0,0,0)++;
    }

    public def run(): boolean {
        foo(new A[double]());
    }

    public static def main(Rail[String]) {
        new XTENLANG_305().execute();
    }
}
