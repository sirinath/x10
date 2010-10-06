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
 * Check methods offer clauses are correctly tested.
 * @author vj
 */
public class CF3 extends x10Test {

    static struct Reducer implements Reducible[Int] {
     	public   def zero()=0;
     	public   def apply(a:Int,b:Int)=a+b;
    }
    def m() offers Int {
    	offer 6;
    }
	public def run() {

		val x = finish (Reducer()){
			m();
		};
		return 6==x;
	}

	public static def main(Array[String](1)) {
		new CF3().execute();
	}
}
