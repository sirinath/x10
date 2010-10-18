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
 * Check that rooted methods can only be called on rooted receivers.
 */
public class RootedCall3_MustFailCompile extends x10Test {

    class A {
	rooted val x =1;
	def m() { //must generate a compile-time error, since x is rooted.
		val a = new A();
		at (here.next()) {
		   val y = a.x;  // illegal. a is no longer rooted here.
		}
    }
    }

    public def run(): boolean = {
	return true;
    }

    public static def main(Array[String](1))  {
	new RootedCall3_MustFailCompile().execute();
    }
}
