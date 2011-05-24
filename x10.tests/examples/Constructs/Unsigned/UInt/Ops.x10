/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2011.
 */

import harness.x10Test;

/**
 * Test equality of UInts.
 *
 * @author Salikh Zakirov 5/2011
 */
public class Ops extends x10Test {
    public def run(): boolean = {
	val u1 = 1u;
	if (1u + 1u != 2u) return false;
	if (u1 + 1u != 2u) return false;
	if (u1 + u1 != 2u) return false;
	if (u1 > 2u) return false;
	if (2u < u1) return false;
	if (u1 >= 2u) return false;
	if (2u <= u1) return false;
	if (2u - u1 != 1u) return false;
	if (1u - 2u != 0xFFFFffffU) return false;
	if (3u * 4u != 12u) return false;
	if (4u / 3u != 1u) return false;
	if (4u % 3u != 1u) return false;

	if (3u.compareTo(3u) != 0) 			{ p(" (3u.compareTo(3u) != 0) 		    "); return false; }
	if (4u.compareTo(0xFFFFffffU) != -1)		{ p(" (4u.compareTo(0xFFFFffffU) != -1)	    "); return false; }
	if (0xF0000001u.compareTo(0xF0000000u) != 1)    { p(" (0xF0000001u.compareTo(0xF0000000u) != 1 "); return false; }

	val u3 = 3u as Comparable[UInt];
	val uf = 0xFFFFffffU as Comparable[UInt];
	val u4 = 4u as Comparable[UInt];
	if (u3.compareTo(3u) != 0) 			{ p(" (u3.compareTo(3u) != 0) 		    "); return false; }
	if (u4.compareTo(0xFFFFffffU) != -1)		{ p(" (u4.compareTo(0xFFFFffffU) != -1)	    "); return false; }
	if (uf.compareTo(0xF0000000u) != 1)    		{ p(" (uf.compareTo(0xF0000000u) != 1 "); return false; }

	return true;
    }

    public static def main(Array[String]) {
        new Ops().execute();
    }

    public static def p(msg:Any) {
	Console.OUT.println(msg);
    }
}
