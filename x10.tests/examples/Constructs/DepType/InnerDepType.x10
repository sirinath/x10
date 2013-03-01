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
 * The test checks that property syntax is accepted.
 *
 * @author vj
 */
public class InnerDepType extends x10Test {
    class Test(i:int) {
       public def this(i:int):Test{self.i==i} { property(i); }
    }
	public def run(): boolean = {
	 
	    var x: Test{self.i==0} = new Test(0); 
	    return true;
	}
	public static def main(var args: Rail[String]): void = {
		new InnerDepType().execute();
	}
}
