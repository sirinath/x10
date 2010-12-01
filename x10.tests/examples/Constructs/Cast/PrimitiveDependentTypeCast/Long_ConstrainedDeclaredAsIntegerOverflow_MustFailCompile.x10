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
 * Purpose: Shows a constraint value may be overflowed.
 * Issue: Declared constraint is an overflowed integer, which makes assignment fail at runtime.
 * @author vcave, vj
 **/
public class Long_ConstrainedDeclaredAsIntegerOverflow_MustFailCompile extends x10Test {

	 private val overIntMax: long = (x10.lang.Int.MAX_VALUE as long) + 10000;
	 
	 public def run(): boolean = {
		try {
			// This value cannot fit in an integer, so the compiler must flag an error.
			var l2: long{self==2147493647} = overIntMax as long{self==2147493647}; // ERR: Int literal 2147493647 is out of range.
		} catch (var e: ClassCastException) {
			return true;
		}
		return false;
	}

	public static def main(var args: Array[String](1)): void = {
		new  Long_ConstrainedDeclaredAsIntegerOverflow_MustFailCompile().execute();
	}

}
