/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

import x10.util.OptionsParser;
import x10.util.Option;
import x10.util.Random;

/**
 * Resilient calculation of PI using MonteCarlo simulation
 * with checkpointing via a resilient map provided by X10RT.
 * To run using Hazelcast, invoke x10 script like: 
 * <pre>
 * X10_RESILIENT_MODE=1 X10_NPLACES=4 x10 -DX10RT_IMPL=JavaSockets -DX10RT_DATASTORE=Hazelcast ResilientMontePi
 * </pre>
 *
 * TODO: Rewrite this example to use x10.util.resilient APIs instead of HC directly.
 */
public class ResilientMontePi {

    static def getMap(s:String) {
        return x10.x10rt.X10RT.getResilientMap(s);
    }

    static class Result {
        var inCircle:long;
        var total:long;

        def accum(r:Result) {
            inCircle += r.inCircle;
            total += r.total;
        }

        def toString() = inCircle + " / " + total;

        def this(c:long, t:long) { 
            inCircle = c;
            total = t;
        }
    }

    public static def main (args:Rail[String]) { here == Place.FIRST_PLACE } {
        if (Place.numPlaces() < 2) {
             Console.ERR.println("This program must be run with at least 2 places");
             System.setExitCode(-1n);
             return;
        }

        val opts = new OptionsParser(args, [
            Option("h","help","this information")
        ], [
            Option("s","samples","total number of samples"),
            Option("c","checkpoints","number of checkpoints per place"),
            Option("v","victim","place to kill partway through execution")
        ]);
        if (opts.filteredArgs().size!=0L) {
            Console.ERR.println("Unexpected arguments: "+opts.filteredArgs());
            Console.ERR.println("Use -h or --help.");
            System.setExitCode(1n);
            return;
        }
        if (opts("-h")) {
            Console.OUT.println(opts.usage(""));
            return;
        }

        val num_samples = opts("-s",1000000000);
        val num_checkpoints = opts("-c", 1000);
        val victim = opts("-v", Place.numPlaces()-1);

        val iters_per_place = num_samples / (Place.numPlaces()-1);
        val inner_iters = num_checkpoints < 1 ? iters_per_place : iters_per_place / num_checkpoints;

        val result = GlobalRef(new Result(0,0)); 
        finish for (p in Place.places()) if (p != here) async {
            try {
                at (p) {
		    val myResultsMap = getMap("result");
                    val rand = new Random(System.nanoTime());
                    var inCircle:Long = 0;
                    var total:Long = 0;
                    for (cp in 1..num_checkpoints) {
                        for (1..inner_iters) {
                            val x = rand.nextDouble();
                            val y = rand.nextDouble();
                            if (x*x + y*y <= 1.0) inCircle++;
                            total++;
                        }
                        val tmp = new Result(inCircle, total);
                        myResultsMap.put(here.id, tmp); 
                        if (here.id == victim && cp > num_checkpoints/2) {
                            Console.OUT.println("BOOM: at "+here+" with partial result "+tmp);
                            System.killHere();
			}
                    }
                    val myResult = new Result(inCircle, total);
                    Console.OUT.println("Work done at: "+here +" "+myResult);
                    at (result) atomic {
                        result().accum(myResult);
                    }
                }
            } catch (e:DeadPlaceException) {
                Console.OUT.println("Got DeadPlaceException from "+e.place);
		val v = getMap("result").get(e.place.id) as Result;
                Console.OUT.println("Retrieved from "+e.place + " "  + v);
		atomic {
                    result().accum(v);
		}
            }
        }

        val pi = (4.0 * result().inCircle) / result().total;
        Console.OUT.println("pi = "+pi+"   calculated with "+result().total+" samples.");
    }

}

// vim: shiftwidth=4:tabstop=4:expandtab

