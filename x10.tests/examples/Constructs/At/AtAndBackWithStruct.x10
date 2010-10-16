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
 *
 * Same basic scenario as AtAndBack, but 
 * with a user-defined struct instead of a built-in.
 */
public class AtAndBackWithStruct extends x10Test {

    static struct S {
        val x:int;
        val y:int;
        def this(a:int, b:int) { x = a; y = b; }
    }

    public def run():boolean {
        var b:S = S(10,20);
        val w = here;
        at (here.next()) {
	    at (w) {
	        chk(b.x == 10);
	        chk(b.y == 20);
	
	        b = S(100,200);

	        chk(b.x == 100);
	        chk(b.y == 200);
	    }
        }
        chk(b.x == 100);
        chk(b.y == 200);
        return true;
    }
        
    public static def main(Array[String](1)) {
        new AtAndBackWithStruct().execute();
    }
}
