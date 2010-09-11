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
 * Test for foreach.
 * @author kemal, 12/2004
 */
public class Foreach1 extends x10Test {

	public const N: int = 100;
	var nActivities: int = 0;

	public def run(): boolean = {
		val P0  = here; // save current place
		val d = [0..N-1]->here;
		val hasbug  = DistArray.make[boolean](d);

		finish foreach (p(i): Point(1) in d.region) {
			// Ensure each activity spawned by foreach
			// runs at P0
			// and that the hasbug array was
			// all false initially
			hasbug(i) |= !(P0 == d(p) && here == P0);
			atomic this.nActivities++;
		}
		return !hasbug.reduce(boolean.|, false) &&
			nActivities == N;
	}
	public static def main(var args: Array[String](1)): void = {
		new Foreach1().execute();
	}
}
