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
 * Return from a "when" should work.
 *
 * @author kemal, 5/2005
 * @author igor, 2/2006 -- renamed from Misc/Unreachable2; added another branch
 */
public class WhenReturn   {

	def test(): int = {
		var ret: int = 0;
		when (X.t()) {
			return 1;
		} or (X.t()) {
			ret = 2;
		}
		return ret;
	}

	public def run(): boolean = {
		var x: int = test();
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new WhenReturn().run ();
	}

	static class X {
		static def t(): boolean = { return true; }
	}
}
