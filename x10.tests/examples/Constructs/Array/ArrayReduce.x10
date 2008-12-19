// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test.

import harness.x10Test;

/**
 * @author bdlucas
 */

public class ArrayReduce extends TestArray {

    public const N: int = 9;

    public def run(): boolean {

        val dist = Dist.makeBlockCyclic(0..N, 0, 2);
        prDist("dist", dist);

        pr("--- original");
        val a = Array.make[double](dist, (p:Point)=>p(0) to double);
        for (pt:Point(1) in a) {
            val x = (future(a.dist(pt)) a(pt)).force();
            out.print(x + " ");
        }
        out.println();

        pr("--- reduced");

        val sum = (a:double,b:double) => a+b;
        out.println("sum: " + a.reduce(sum, 0.0));

        val min = (a:double,b:double) => Math.min(a,b);
        out.println("min: " + a.reduce(min, double.POSITIVE_INFINITY));

        val max = (a:double,b:double) => Math.max(a,b);
        out.println("max: " + a.reduce(max, double.NEGATIVE_INFINITY));

        return status();
    }

    public static def main(var args: Rail[String]): void = {
        new ArrayReduce().execute();
    }

    def expected() = 
        "--- dist: Dist(0->([0..1] || [8..9]),1->[2..3],2->[4..5],3->[6..7])\n" +
        "0 0 1 1 2 2 3 3 0 0 \n" +
        "--- original\n" +
        "0.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 \n" +
        "--- reduced\n" +
        "sum: 45.0\n" +
        "min: 0.0\n" +
        "max: 9.0\n";
}
