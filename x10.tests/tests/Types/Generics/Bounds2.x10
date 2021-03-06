/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

import harness.x10Test;

/**
 * Test type parameter bounds.
 *
 * @author nystrom 8/2008
 */
public class Bounds2 extends x10Test {
        class C[T]{T<:String} {
                var x: T;
                def this(y: T) { x = y; }
        }

	public def run()=new C[String]("").x.equals("");

	public static def main(Rail[String]) {
		new Bounds2().execute();
	}
}

