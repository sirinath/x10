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
 * Only final free variables can be passed to async body.
 *
 * @author kemal 4/2005
 */
public class AsyncTest4_MustFailCompile extends x10Test {

	public static N: int = 20;

	public def run(): boolean = {
		var s: int = 0;
		for (var i: int = 0; i < N; i++) {
			//==> compiler error expected here
			finish async x10.io.Console.OUT.println("s="+s+" i="+i);
			s += i;
		}
		// no compiler error here
		s = 0;
		for (var i: int = 0; i < N; i++) {
			{
				val i1: int = i;
				val s1: int = s;
				finish async x10.io.Console.OUT.println("s1="+s1+" i1="+i1);
			}
			s += i;
		}
		val y: int;
		//==> Compiler error expected here
		finish async { async y = 3; }
		x10.io.Console.OUT.println("y="+y);
		return true;
	}

	public static def main(Array[String](1)) {
		new AsyncTest4_MustFailCompile().execute();
	}
}
