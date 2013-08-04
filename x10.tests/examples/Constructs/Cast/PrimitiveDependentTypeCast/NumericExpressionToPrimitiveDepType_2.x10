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
 * Purpose: Checks the numeric expression is not evaluated several time while checking for constraint.
 * @author vcave
 **/
public class NumericExpressionToPrimitiveDepType_2 extends x10Test {

	public def run(): boolean = {
		var j: int = -1n;
		var i: int{self == 0n} = 0n;
		try {
			// j is incremented after the test is done;
			i = (j++) as int{self == 0n};
		} catch (e: ClassCastException) {
			return (j==0n) && (i==0n);
		}

		return false;
	}

	public static def main(var args: Rail[String]): void = {
		new NumericExpressionToPrimitiveDepType_2().execute();
	}

}
