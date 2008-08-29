// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * Methods and closures may return values using a return statement. If
 * the method�s return type is expliclty declared Void, the method may
 * return without a value; otherwise, it must return a value of the
 * appropriate type.
 */

public class ClosureReturnType2_MustFailCompile extends ClosureTest {

    def foo() = {}

    public def run(): boolean = {
        
        val f = ():int => {foo();};
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureReturnType2_MustFailCompile().execute();
    }
}
