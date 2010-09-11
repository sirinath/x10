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
 * Minimal test for conditional atomics.
 */
public class ConditionalAtomicTest extends x10Test {

	var value1: int = 0;
	var value2: int = 0;

	public def run(): boolean = {
		val c: Clock = Clock.make();
		async clocked(c) {
			// this activity waits until value1 and
			// value2 are equal, then atomically makes
			// value1 two higher then value2
			while (true) {
				var temp: int;
				atomic temp = value1;
				if (temp >= 42) break;
				when (value1 == value2) { value1++; value2--; }
			}
		}
		async clocked(c) {
			// this activity waits until value1 is
			// two higher than value2, then atomically raises
			// value2 to value1's level so they become equal
			while (true) {
				var temp: int;
				atomic temp = value2;
				if (temp >= 42) break;
				when (value1 == value2 + 2)
				{ value2 = value1; }
				or (value1 != value2+2 &&
						value1 != value2) //something went wrong
				{ value1 = value2 = 43; /* error */ };
			}
		}
		next; // wait until both activities end

		var temp: int;
		atomic temp = value1;
		return temp == 42;
	}

	public static def main(Array[String](1)) {
		new ConditionalAtomicTest().executeAsync();
	}
}
