/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Minimal test for dists.
 */

public class DistributionTest extends x10Test {

    public def run(): boolean = {
        val r = 0..100; //(low, high)
        val R = [r,r] to Region;
        val d  = R->here;
        return ((d.rank == 2) &&
                (R.rank == 2) &&
                (R.rank(1).high() - R.rank(1).low() + 1 == 101));
    }

    public static def main(var args: Rail[String]): void = {
        new DistributionTest().execute();
    }
}
