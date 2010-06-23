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
 * Description: 
 * Expected Result: run() returns true if successful, false otherwise.
 * @author Baolin Shao (bshao@us.ibm.com)
 */
public class LoopTest4 extends x10Test {

	var flag: boolean = false;

	public def run() {
		
                //TODO: test code
		val R: Region = 1..100;
    val d:Dist = R -> here;
    finish ateach(p in d){
            finish async(p){
                    while(true){
                            async{}
                    }
            }
    }

                //default successful condition
                var b: boolean = false;
		atomic { b = flag; }
		return b;
	}

	public static def main(args: Rail[String]) {
		new Template().execute();
	}
}

 

