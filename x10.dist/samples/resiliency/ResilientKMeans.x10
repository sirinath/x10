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
import x10.regionarray.*;
import x10.util.*;

/**
 * Resilient KMeans which supports DeadPlaceException
 * Partially based on KMeansDist.x10
 * @author kawatiya
 * 
 * For Managed X10:
 *   $ x10 ResilientKMeans.x10
 *   $ X10_RESILIENT_PLACE_ZERO=1 X10_NPLACES=4 run.sh x10 ResilientKMeans [num_points] [num_clusters]
 * For Native X10:
 *   $ x10c++ ResilientKMeans.x10 -o ResilientKMeans
 *   $ X10_RESILIENT_PLACE_ZERO=1 X10_NPLACES=4 run.sh runx10 ResilientKMeans [num_points] [num_clusters]
 */
class ResilientKMeans {
    
    static val DIM=2;
    //static val CLUSTERS=4;
    //static val POINTS=1000000L;
    static val ITERATIONS=50;
    
    public static def main(args:Rail[String]) {
        val POINTS = (args.size>=1) ? Long.parseLong(args(0)) : 1000000L;
        val CLUSTERS = (args.size>=2) ? Int.parseInt(args(1)): 4;
        Console.OUT.println("KMeans: Divide " + DIM + " dim " + POINTS + " points into "
                            + CLUSTERS + " clusters, using " + Place.MAX_PLACES + " places");
        
        val place0 = here;
        
        /*
         * Create a points array and deliver it to other places
         * Note: Coordinates of the i-th point is [points(i,0), points(i,1)]
         */
        val rnd = new Random(0); // new Random(System.nanoTime());
        val points_region = Region.make(0..(POINTS-1), 0..(DIM-1));
        val points_master = new Array[Float](points_region, (p:Point)=>rnd.nextFloat());
        val points_local = PlaceLocalHandle.make[Array[Float]](PlaceGroup.WORLD, ()=>points_master);
        
        /*
         * Cluster data to be calculated
         * Note: Cordinates of n-th cluster point is [clusters(n*2), clusters(n*2+1)]
         */
        /* Use the first CLUSTERS points as initial cluster values */
        val central_clusters = new Rail[Float](CLUSTERS*DIM, (i:Long) => points_master(i/DIM, i%DIM));
        val old_central_clusters = new Rail[Float](CLUSTERS*DIM);
        val central_cluster_counts = new Rail[Int](CLUSTERS);
        val processed_points = new Cell[Long](0L);
        /* GlobalRefs to access the structures from other places */
        val central_clusters_gr = GlobalRef(central_clusters);
        val central_cluster_counts_gr = GlobalRef(central_cluster_counts);
        val processed_points_gr = GlobalRef(processed_points);
        /* For local calculation */
        val local_curr_clusters = PlaceLocalHandle.make[Rail[Float]](PlaceGroup.WORLD, ()=>new Rail[Float](CLUSTERS*DIM));
        val local_new_clusters = PlaceLocalHandle.make[Rail[Float]](PlaceGroup.WORLD, ()=>new Rail[Float](CLUSTERS*DIM));
        val local_cluster_counts = PlaceLocalHandle.make[Rail[Int]](PlaceGroup.WORLD, ()=>new Rail[Int](CLUSTERS));
        
        /*
         * Calculate KMeans using multiple places
         */
        for (i in 1..ITERATIONS) {
            
            Console.OUT.println("---- Iteration: "+i);
            
            /* 
             * Deliver the central_clusters
             */
            try {
                finish for (pl in Place.places()) {
                    if (pl.isDead()) continue; // skip the dead place
                    at (pl) async {
                        for (var j:Int=0 ; j<CLUSTERS*DIM ; ++j) {
                            local_curr_clusters()(j) = central_clusters(j);
                            local_new_clusters()(j) = 0f;
                        }
                        for (var j:Int=0 ; j<CLUSTERS ; ++j) {
                            local_cluster_counts()(j) = 0;
                        }
                    }
                }
            } catch (es:MultipleExceptions) {
                for (e in es.exceptions()) {
                    if (e instanceof DeadPlaceException) {
                        Console.OUT.println("DeadPlaceException thrown from " + (e as DeadPlaceException).place);
                        // No recovery is necessary, values should be delivered to live places
                    } else {
                        Console.OUT.println("Unmanagable exception " + e); throw e;
                    }
                }
            }
            /* Clear the central_clusters to collect results */
            for (var j:Int=0 ; j<CLUSTERS*DIM ; ++j) {
                old_central_clusters(j) = central_clusters(j);
                central_clusters(j) = 0f;
            }
            for (var j:Int=0 ; j<CLUSTERS ; ++j) {
                central_cluster_counts(j) = 0;
            }
            processed_points() = 0L;
            
            /* 
             * Compute new clusters and counters at each place
             */
            val numAvail = Place.MAX_PLACES - Place.numDead(); // number of available places
            val div = POINTS/numAvail; // share for each place
            val rem = POINTS%numAvail; // extra share for Place0
            var start:Long = 0L; // next point to be processed
            try {
                finish for (pl in Place.places()) {
                    if (pl.isDead()) continue; // skip the dead place
                    
                    var end:Long = start + div; if (pl==place0) end += rem;
                    
                    /* At place pl, process points [start,end) */
                    Console.OUT.println(pl + ": process points [" + start + "," + end + ")");
                    val s = start, e = end;
                    at (pl) async {
                        //TODO: finish
                        for (var j:Long = s; j < e; ++j) {
                            val p = j;
                            //TODO: async
                            { // process the p-th point
                                val points = points_local();
                                var closest:Int = -1;
                                var closest_dist:Float = Float.MAX_VALUE;
                                for (var k:Int=0 ; k<CLUSTERS ; ++k) { 
                                    var dist:Float = 0;
                                    for (var d:Int=0 ; d<DIM ; ++d) { 
                                        val tmp = points(p,d) - local_curr_clusters()(k*DIM+d);
                                        dist += tmp * tmp;
                                    }
                                    if (dist < closest_dist) {
                                        closest_dist = dist;
                                        closest = k;
                                    }
                                }
                                atomic {
                                    for (var d:Int=0 ; d<DIM ; ++d) { 
                                        local_new_clusters()(closest*DIM+d) += points(p,d);
                                    }
                                    local_cluster_counts()(closest)++;
                                }
                            }
                        } /* for (j) */
                        /* All assigned points processed, return the local results */
                        val tmp_new_clusters = local_new_clusters();
                        val tmp_cluster_counts = local_cluster_counts();
                        val tmp_processed_points = e - s;
                        at (place0) atomic {
                            for (var j:Int=0 ; j<CLUSTERS*DIM; ++j) {
                                central_clusters_gr()(j) += tmp_new_clusters(j);
                            }
                            for (var j:Int=0 ; j<CLUSTERS ; ++j) {
                                central_cluster_counts_gr()(j) += tmp_cluster_counts(j);
                            }
                            processed_points_gr()() += tmp_processed_points;
                        }
                    } /* at (pl) async */
                    start = end; // point to be processed at the next place
                } /* finish for (pl) */
            } catch (es:MultipleExceptions) {
                for (e in es.exceptions()) {
                    if (e instanceof DeadPlaceException) {
                        Console.OUT.println("DeadPlaceException thrown from " + (e as DeadPlaceException).place);
                        // No recovery is necessary, completeness will be checked by the value of processed_points
                    } else {
                        Console.OUT.println("Unmanagable exception " + e); throw e;
                    }
                }
            }
            
            /*
             * Compute new cluster values and test for convergence
             */
            for (var k:Int=0 ; k<CLUSTERS ; ++k) { 
                for (var d:Int=0 ; d<DIM ; ++d) { 
                    central_clusters(k*DIM+d) /= central_cluster_counts(k);
                }
            }
            if (processed_points() != POINTS) { /* if all points are not processed, skip the convergence test */
                Console.OUT.println("Incomplete calculation: " + (POINTS-processed_points()) + " points are not processed");
            } else { /* test for convergence */
                var b:Boolean = true;
                for (var j:Int=0 ; j<CLUSTERS*DIM; ++j) { 
                    if (Math.abs(old_central_clusters(j)-central_clusters(j))>0.0001) {
                        b = false; break;
                    }
                }
                if (b) {
                    Console.OUT.println("Result converged"); break;
                }
            }
            
        } /* for (i) */
        
        /*
         * Print the result
         */
        Console.OUT.println("---- Result of " + CLUSTERS + " clustering");
        for (var d:Int=0 ; d<DIM ; ++d) { 
            for (var k:Int=0 ; k<CLUSTERS ; ++k) { 
                if (k>0) Console.OUT.print(" ");
                Console.OUT.print(central_clusters(k*DIM+d));
            }
            Console.OUT.println();
        }
    } /* main */
}
