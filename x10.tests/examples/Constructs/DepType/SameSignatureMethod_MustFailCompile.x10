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
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
/** Tests that two methods in a class cannot have the same signature.
 *@author pvarma
 *
 */

import harness.x10Test;

public class SameSignatureMethod_MustFailCompile extends x10Test { 

class Test(i: int, j:int) {
		 def tester(k:int(0))=true;
		 def tester(l:int(0)) = l; // ERR
		 
		def this(i:int, j:int):Test = {
			property(i,j);
		}
	}

	
	public def run(): boolean = {
	   return true;
	}  
	
    public static def main(var args: Array[String](1)): void = {
        new SameSignatureMethod_MustFailCompile().execute();
    }
   

		
}
