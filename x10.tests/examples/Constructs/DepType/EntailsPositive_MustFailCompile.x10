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
 * The language now specifies more aggressive type-inference than when this test was originally written.
 * Now the return type of a constructor is inferred from the property invocation within the constructor.
 * That is: 
 * public def this(ii:int, jj:int):EntailsPositive_MustFailCompile = { property(ii,jj);}
 * is interpreted as
 * public def this(ii:int, jj:int) <: EntailsPositive_MustFailCompile = { property(ii,jj);}
 *
 *So it is necessary to hide the 1 behind a method call to get this test to check the behavior we want.
 * @author vj
 */
public class EntailsPositive_MustFailCompile(i:int, j:int) extends x10Test {

	public def this(ii:int, jj:int) { property(ii,jj);}
	public def run():boolean  {
	    val x:EntailsPositive_MustFailCompile{self.i==1}  =  new EntailsPositive_MustFailCompile(one(),2);  // ERR
	    return true;
	}
	def one():Int = 1;
	public static def main(Array[String](1)) {
		new EntailsPositive_MustFailCompile(1,2).execute();
	}
}
