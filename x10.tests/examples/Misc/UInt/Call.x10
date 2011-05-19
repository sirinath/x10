/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2011.
 */

import harness.x10Test;

/**
 * Test calling with UInt parameters.
 *
 * @author Salikh Zakirov 5/2011
 */
public class Call extends x10Test {

    public def f(a:UInt) {
	 return "uint = " + a;
    }

    public def f(a:Int) {
	return "int = " + a;
    }

    public def run(): boolean = {
	val i0 = 0;
	val u1 = 1u;
	val s0 = f(i0);
	val s1 = f(u1);
	return (s0.equals("int = 0") && s1.equals("uint = 1"));
    }

    public static def main(Array[String]) {
        new Call().execute();
    }
}
