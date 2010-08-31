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
 * Purpose: 
 * @author vcave
 **/
public class PrimitiveToPrimitiveConstrained1   {
	 
	public def run() = 3 instanceof int{self==3};
	
	public static def main(var args: Rail[String]): void = {
		new PrimitiveToPrimitiveConstrained1().run ();
	}
}
