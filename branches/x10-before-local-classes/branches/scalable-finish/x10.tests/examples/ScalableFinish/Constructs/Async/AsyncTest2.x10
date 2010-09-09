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
 * Testing complex async bodies.
 *
 * @author Kemal Ebcioglu 4/2005
 */
public class AsyncTest2   {

	public def run(): boolean = {
		val NP: int = Place.MAX_PLACES;
		val A: DistArray[int]{rank==1} = DistArray.make[int](Dist.makeUnique());
		finish
			for (val (k): Point in 0..NP-1)
                                async (A.dist(k))
					ateach (val (i): Point{rank==A.rank} in A.dist)
                                                atomic A(i) += i;
		finish ateach (val (i): Point{rank==A.rank} in A.dist) { chk(A(i) == i*NP); }

		return true;
	}

	public static def main(Rail[String]) {
		new AsyncTest2().run ();
	}
}
