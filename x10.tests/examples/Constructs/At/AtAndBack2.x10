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
 * References to a var from within an at 
 * access the location, not a copied value.
 */
public class AtAndBack2 extends x10Test {

    public def run():boolean {
	var x:int = 20;
	val h = here;
	at (here.next()) {
	    at (h) {
                x = x + 1;
                x += 1;
		val a = ++x;
	        val b = x++;
                chk(a==b);
            }
        }
	return x == 24; 
    }

    public static def main(Array[String](1)) {
        new AtAndBack2().execute();
    }
}
