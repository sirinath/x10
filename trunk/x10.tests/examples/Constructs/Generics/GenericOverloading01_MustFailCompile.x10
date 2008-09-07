// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

import x10.compiler.ArithmeticOps;

/**
 * @author bdlucas 8/2008
 */

public class GenericOverloading01_MustFailCompile extends GenericTest {

    class A[T,U] {
        def m(T) = 0;
        def m(U) = 1;
    }

    public def run(): boolean = true;

    public static def main(var args: Rail[String]): void = {
        new GenericOverloading01_MustFailCompile().execute();
    }
}
