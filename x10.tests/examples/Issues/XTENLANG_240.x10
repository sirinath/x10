// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

/**
 * @author bdlucas 12/2008
 */

class XTENLANG_240 extends x10Test {

    
    class A {
        final def set(v:double, i0: int) {}
        final public def apply(i0:int) = 0;
    }
        
    def foo(a:A!) {
        for (var i:int=0; i<0; i++)
            a(i) = 0.0;
    }

    public def run(): boolean {
        return true;
    }

    public static def main(Rail[String]) {
        new XTENLANG_240().execute();
    }
}
