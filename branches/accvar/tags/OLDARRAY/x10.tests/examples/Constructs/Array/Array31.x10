/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Simple array test #3. Tests declaration of arrays, storing in local
 * variables, accessing and updating for 1-d arrays.
 */

public class Array31 extends x10Test {

    public def run(): boolean = {
        val r:Region(1) = 1..10;
        var ia: Array[Int](1) = Array.makeFromRegion[Int](r, (Point)=>0);
        ia(1) = 42;
        return 42 == ia(1);
    }

    public static def main(var args: Rail[String]): void = {
        new Array31().execute();
    }
}
