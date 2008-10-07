/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Constant promotions to arrays: (D n)
 * disjoint union and overlay of arrays
 * array lift, scan and reduce.
 *
 * This one tests arrays of booleans.
 *
 * @author kemal 4/2005
 */

public class ArrayAlgebra2 extends x10Test {

    public const N: int = 24;
    def makeArray(D: Dist, k: boolean): Array[boolean](D) = Array.make[Boolean](D, (Point)=>k);

    public def run(): boolean = {

        val D = Dist.makeBlockCyclic(0..N-1, 2);
        val D01 = D | 0..N/2-1;
        val D23 = D | (N/2)..N-1;
        val D0 = D | 0..N/4-1;
        val D1: Dist = D | (N/4)..N/2-1;
        val D2: Dist = D | (N/2)..3*N/4-1;
        val D3: Dist = D | (3*N/4)..N-1;
        val ia1: Array[boolean] =
            makeArray(D, false)
                .overlay((makeArray(D01, true) || makeArray(D23, false))
                .overlay(makeArray(D3, true))
                .overlay(makeArray(D0, false)));

        arrEq(ia1 | D0, makeArray(D0, false));
        arrEq(ia1 | D1, makeArray(D1, true));
        arrEq(ia1 | D2, makeArray(D2, false));
        arrEq(ia1 | D3, makeArray(D3, true));

        // We should eventually support the following:
        //chk(ia1.or() == true);
        //chk(ia1.and() == false);
        //chk(ia1.xor() == false);
        //TODO: scan does not need a unit operand

        arrEq(ia1.scan(booleanArray.or,false),
              Array.make[boolean](D, (p(i): Point(1)) => (ia1 | 0..i).reduce(booleanArray.or,false)

        arrEq((makeArray(D0, true) || makeArray(D1,false)).lift(booleanArray.xor,makeArray(D01, true)),
              (makeArray(D0, false) || makeArray(D1,true)));

        // a1 || a2 where a1, a2 are boolean arrays
        //causes ambiguity with array disjoint union
        //arrEq(makeArray(D01, false) | makeArray(D01, false),
        //      makeArray(D01,false) & makeArray(D01, true));

        return true;
    }

    /**
     * Throw an error iff x and y are not arrays with same
     * content and dist
     */
    static def arrEq(val x: Array[boolean], val y: Array[boolean]): void = {
        chk(x.dist.equals(y.dist));
        finish ateach (val p: Point in x) chk(x(p) == y(p));
    }

    public static def main(var args: Rail[String]): void = {
        new ArrayAlgebra2().execute();
    }
}
