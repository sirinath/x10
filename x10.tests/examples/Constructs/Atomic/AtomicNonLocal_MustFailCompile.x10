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

//LIMITATION:
//This test case will not meet expectations. It is a limitation of the current release.
/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;
import x10.array.Dist;
import x10.array.Array;

/**
 * Remote accesses in atomic must be checked by the compiler.
 *
 * occur, but the error is still caught at run time.
 *
 * @author Kemal 4/2005, vj
 */
public class AtomicNonLocal_MustFailCompile extends x10Test {

	public def run(): boolean = {
		val A: Array[int] = new Array[int](Dist.makeUnique());
		chk(Place.MAX_PLACES >= 2);
		chk(A.dist(0) == here);
		chk(A.dist(1) != here);
		

		finish async(here) { A(0) += 1; }
		A(0) += 1;

		finish async(here) { A(1) += 1; }
		atomic { A(1) += 1; } 
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new AtomicNonLocal_MustFailCompile().execute();
	}

}
