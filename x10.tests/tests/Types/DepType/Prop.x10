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
 * The test checks that property syntax is accepted.
 *
 * @author vj
 */
public class Prop(i: int,  j: int) extends x10Test  {

	public def this(i: int, j: int): Prop{self.i==i,self.j==j} {
	    property(i,j);
	}
	public def run(): boolean = true;
	public static def main(var args: Rail[String]): void {
		new Prop(2n,3n).execute();
	}
	}
