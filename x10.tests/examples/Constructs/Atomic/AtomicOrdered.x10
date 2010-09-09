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

import harness.x10Test;

/**
 * Use Dekker's test to confirm strong ordering of
 * atomic sections. (Failure of test proves lack of
 * ordering).
 *
 * @author kemal 4/2005
 */

public class AtomicOrdered extends x10Test {

	public const CACHESIZE: int = 32*1024/4;
	public const LINESIZE: int = 128/4;
	public const MAX_ASSOC: int = 8;

	val A = new Array[int](0..CACHESIZE*(MAX_ASSOC+2)-1);

	public def run(): boolean = {
		val r  = new pair();
		finish {
			async  {
				finish { } // delay
				atomic A(0) = 1;
				var t: int;
				atomic t = A(LINESIZE);
				r.v1 = t;
			}
			async  {
				finish { } // delay
				atomic A(LINESIZE) = 1;
				var t: int;
				atomic t = A(0);
				r.v2 = t;
			}
		}
		x10.io.Console.OUT.println("v1 = "+r.v1+" v2 = "+r.v2);
		// not both could have read the old value
		atomic chk(!(r.v1 == 0 && r.v2 == 0));
		return true;
	}

	public static def main(Rail[String])  {
		new AtomicOrdered().execute();
	}

	static class pair {
		var v1: int;
		var v2: int;
	}
}
