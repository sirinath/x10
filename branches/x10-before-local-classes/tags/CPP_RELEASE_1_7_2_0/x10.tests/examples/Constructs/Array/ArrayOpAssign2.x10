/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Simple test for operator assignment of array elements.
 * Tests post and pre increment/decrement;
 */

public class ArrayOpAssign2 extends x10Test {

    var i: int = 1;
    var j: int = 1;

    public def run(): boolean = {

        val R:Region(2) = [1..10, 1..10];
        var ia: Array[int] = Array.make[int](R->here, (Point)=>0);

        ia(i, j) = 1;

        chk(ia(i, j) == 1);
        chk((ia(i, j)++) == 1);
        chk(ia(i, j) == 2);
        chk((ia(i, j)--) == 2);
        chk(ia(i, j) == 1);
        chk((++ia(i, j)) == 2);
        chk(ia(i, j) == 2);
        chk((--ia(i, j)) == 1);
        chk(ia(i, j) == 1);

        return true;
    }

    public static def main(var args: Rail[String]): void = {
        new ArrayOpAssign2().execute();
    }
}
