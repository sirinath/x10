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
 * Check you can offer the result of a method invocation.
 * @author vj
 */
public class CF2   {

    static struct Reducer implements Reducible[Int] {
    	safe public global def zero()=0;
    	safe public global def apply(a:Int,b:Int)=a+b;
    }
    def m() = 6;
	public def run() {

		val x = finish (Reducer()){
			offer m();
		};
		return 6==x;
	}

	public static def main(Rail[String]) {
		new CF2().run ();
	}
}
