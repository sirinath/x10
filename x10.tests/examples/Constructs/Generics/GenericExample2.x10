// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

import x10.compiler.ArithmeticOps;

/**
 * Example from spec. If changes need to be made to this code to make
 * it a valid example, update the spec accordingly.
 *
 * @author bdlucas 8/2008
 */

public class GenericExample2 extends GenericTest {

    public def run() = {

        class Cell[X] {
            var x: X;
            def this(x: X) { this.x = x; }
            def get(): X = x;
            def set(x: X) = { this.x = x: }
        }
        
        class Get[+X] {
            var x: X;
            def this(x: X) { this.x = x; }
            def get(): X = x;
        }

        class Set[-X] {
            var x: X;
            def this(x: X) { this.x = x; }
            def set(x: X) = { this.x = x: }
        }

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new GenericExample1().execute();
    }
}
