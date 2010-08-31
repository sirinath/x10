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
 * A value of an unconstrained type parameter T cannot be assigned to a variable of type Object.
 * Testing  return value from a method.
 *
 * @author vj 
 */
public class ObjectIsNotParameterType3_MustFailCompile   {
	class GenericWrapper[T] {
		  public def testAssign(x:Object):T = x;
	}
	public def run()=true;

	public static def main(Rail[String]) {
		new ObjectIsNotParameterType3_MustFailCompile().run ();
	}

  
}
