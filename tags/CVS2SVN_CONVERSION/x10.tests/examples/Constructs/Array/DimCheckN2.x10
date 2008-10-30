/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * This must compile and run fine. Checks that the initializer may not specify
 * the arity of the region.
 *
 * @author vj 12 2006
 */

public class DimCheckN2 extends x10Test {

    def m(var d: Dist(2)): void = {
        val a1 = Array.make[int](d, (p(i): Point): int => { return i; });
    }

    public def run(): boolean = {
        val d  = Dist.makeConstant([0..2, 0..3] to Region, here);
        m(d);
        return true;
    }

    public static def main(var args: Rail[String]): void = {
        new DimCheckN2().execute();
    }
}
