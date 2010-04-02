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

import x10.array.Array;
import x10.util.Random;
import x10.io.Console;

public class Histogram {

  /**
    * Compute the histogram of the array a in the rail b.
    */
    public static def run(a:Array[int](1), b: Rail[int]!) {
	finish 
	    foreach((i) in a.region) {
	       val bin = a(i)% b.length;
	       atomic b(bin)++;
	    }
    }
    public static def main(args:Rail[String]!) {
	if (args.length != 2) {
	    Console.OUT.println("Usage: Histogram SizeOfArray Buckets");
	    at (Place.FIRST_PLACE) System.setExitCode(-1);
	    return;
        }
	val N = int.parse(args(0));
	val S = int.parse(args(1));
	val a = new Array[int](0..N-1, ((i):Point)=> i);
	val b = Rail.make[int](S);
	run(a, b);
	val v = b(0);
        var ok:boolean = true;
	for (x in b) ok &= (x==v);
	if (ok) {
	    Console.OUT.println("Test ok.");
	} else {
	    Console.OUT.println("Test failed.");
	}
    }
}
