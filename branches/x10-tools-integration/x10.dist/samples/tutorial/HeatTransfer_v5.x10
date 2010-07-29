/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */


/**
 * This is one of a series of programs showing how to express
 * different forms of parallelism in X10.</p>
 *
 * All of the example programs in the series are computing
 * the same thing:  solving a set of 2D partial differential
 * equations that can be expressed as an iterative 4-point
 * stencil operation.  See the X10 2.0 tutorial for
 * for more details and some pictures.</p>
 *
 * This program is illustrating an SPMD with all-to-all reduction
 * and multiple activities per node ("OpenMP within MPI-style").</p>
 */
public class HeatTransfer_v5 {
    static type Real=Double;
    const n = 3, epsilon = 1.0e-5;
    const P = 1;

    const BigD = Dist.makeBlock([0..n+1, 0..n+1], 0);
    const D = BigD | ([1..n, 1..n] as Region);
    const LastRow = [0..0, 1..n] as Region;
    const A = DistArray.make[Real](BigD,(p:Point)=>{ LastRow.contains(p) ? 1.0 : 0.0 });
    const Temp = DistArray.make[Real](BigD);

    static def stencil_1((x,y):Point(2)): Real {
        return ((at(A.dist(x-1,y)) A(x-1,y)) + 
                (at(A.dist(x+1,y)) A(x+1,y)) + 
                (at(A.dist(x,y-1)) A(x,y-1)) + 
                (at(A.dist(x,y+1)) A(x,y+1))) / 4;
    }

    // TODO: The array library really should provide an efficient 
    //       all-to-all collective reduction.
    //       This is a quick and sloppy implementation, which does way too much work.
    static def reduceMax(z:Point{self.rank==diff.rank}, diff:DistArray[Real], scratch:DistArray[Real]) {
        val max = diff.reduce(Math.max.(Double,Double), 0.0);
        diff(z) = max;
        next;
    }

    // TODO: This is a really inefficient implementation of this abstraction.
    //       Needs to be done properly and integrated into the Dist/Region/DistArray
    //       class library in x10.array.
    static def blockIt(d:Dist(2), numProcs:int):ValRail[Iterable[Point(2)]] {
        val ans = ValRail.make(numProcs, (int) => new x10.util.ArrayList[Point{self.rank==d.rank}]());
	var modulo:int = 0;
        for (p in d) {
	    ans(modulo).add(p);
            modulo = (modulo + 1) % numProcs;
        }
	return ans;
    }

    def run() {
        finish async {
            val c = Clock.make();
            val D_Base = Dist.makeUnique(D.places());
            val diff = DistArray.make[Real](D_Base);
            val scratch = DistArray.make[Real](D_Base);
            ateach (z in D_Base) clocked(c) {
                val blocks:ValRail[Iterable[Point(2)]] = blockIt(D | here, P);
                foreach ((q) in 0..P-1) clocked(c) {
                    var myDiff:Real;
                    do {
                        if (q == 0) diff(z) = 0;
	                myDiff = 0;
                        for (p:Point(2) in blocks(q)) {
                            Temp(p) = stencil_1(p);
                            myDiff = Math.max(myDiff, Math.abs(A(p) - Temp(p)));
                        }
                        atomic diff(z) = Math.max(myDiff, diff(z));
                        next;
                        for (p:Point(2) in blocks(q)) {
                            A(p) = Temp(p);
                        }
                        if (q == 0) reduceMax(z, diff, scratch);
                        myDiff = diff(z);
                        next;
                    } while (myDiff > epsilon);
                }
            }
        }
    }
 
   def prettyPrintResult() {
       for ((i) in A.region.projection(0)) {
           for ((j) in A.region.projection(1)) {
                val pt = Point.make(i,j);
                at (BigD(pt)) { 
                    val tmp = A(pt);
                    at (Place.FIRST_PLACE) Console.OUT.printf("%1.4f ", tmp);
                }
            }
            Console.OUT.println();
        }
    }

    public static def main(Rail[String]) {
        Console.OUT.println("HeatTransfer Tutorial example with n="+n+" and epsilon="+epsilon);
        Console.OUT.println("Initializing data structures");
        val s = new HeatTransfer_v5();
        Console.OUT.print("Beginning computation...");
        val start = System.nanoTime();
        s.run();
        val stop = System.nanoTime();
	Console.OUT.printf("...completed in %1.3f seconds.\n", ((stop-start) as double)/1e9);
        s.prettyPrintResult();
    }
}
