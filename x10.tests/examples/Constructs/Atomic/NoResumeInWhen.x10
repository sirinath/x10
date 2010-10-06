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
 * A resume statement cannot occur in a when statement.
 * @vj
 */
public class NoResumeInWhen extends x10Test {
	
	public def run(): boolean = {
	   val c = Clock.make();
	   val b=true;
	   try {
	     when (b==true) 
		    resume;
	   } catch (IllegalOperationException) {
		   return true;
	   }
	   return false;
	}

	public static def main(Array[String](1)){
		new NoResumeInWhen().execute();
	}
}
