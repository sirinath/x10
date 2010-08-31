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
 * Check a constructor can specify a has type.
 *
 * @author vj
 */
public class HasTypeConstructor(n:int)   {
	def this(): HasTypeConstructor{self.n==0} {
		property(0);
	}
	def this(x:int)  {
		property(x);
	}
	public def run() {
		
		val x <: HasTypeConstructor{self.n==1} = new HasTypeConstructor(1);
		return true;
	}

	public static def main(Rail[String])  {
		new HasTypeConstructor().run ();
	}
}
