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
import x10.array.Dist;

/**
 * Comparing objects should not be rewritten to boxed calls.
 * Distilled from the old CompilerNullPointerException test.
 *
 * @author Igor Peshansky
 */
public class ObjectEquality extends x10Test {

	var objField: Object;

	public def run(): boolean = {
		val obj: Object = new Object();
		if (obj == objField)
			return false;
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new ObjectEquality().execute();
	}
}
