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
 * Purpose: Cast's dependent type constraint must be satisfied by the primitive.
 * Issue: Value to cast does not meet constraint requirement of target type.
 * @author vcave
 **/
public class AssignmentPrimitiveConstrainedIdentity   {

	public def run(): boolean = {
		
		try { 
         var i: int(0) = 0;
         var j: int(0) = 0;
         return i == j;
		}catch(e: Throwable) {
			return false;
		}

      
	}

   public static def main(Rail[String]) {
		new AssignmentPrimitiveConstrainedIdentity().run ();
	}

}
