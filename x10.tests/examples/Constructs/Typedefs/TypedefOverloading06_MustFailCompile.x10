// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

/**
 * It is illegal for a package, class, or interface to contain a type
 * definition with no type or value parameters and also a member class
 * or interface with the same name.
 *
 * @author bdlucas 9/2008
 */

public class TypedefOverloading06_MustFailCompile extends TypedefTest {

    interface A[T] {};
    static type A = String;

    public def run(): boolean = {

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new TypedefOverloading06_MustFailCompile().execute();
    }
}

