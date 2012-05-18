/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright Australian National University 2012.
 */
import harness.x10Test;

import x10.util.ArrayList;
import x10.util.ArrayUtils;
import x10.util.Random;

/**
 * Functional test of x10.util.ArrayUtils
 * @author milthorpe 05/2012
 */
public class TestArrayUtils extends x10Test {

	public def run(): Boolean = {
        val N = 30;
        val r = new Random();
        val a = new Array[Double](N);
        for (i in 0..(N-1)) {
            a(i) = r.nextDouble();
        }

        // seed the array for later search
        val magicValue = 0.8;
        a(2) = magicValue; 

        ArrayUtils.sort(a);

        // check that sort actually sorted the array
        var current:Double = -Double.MAX_VALUE;
        for (i in 0..(N-1)) {
            chk(current <= a(i));
            current = a(i);
        }

        val key = 0.4;
        val index = ArrayUtils.binarySearch(a, key);
        if (index >= 0) {
            // key found (unlikely)
            chk(a(index) == key);
        } else {
            // key not found - check insertion index
            chk(a(-index) > key);
            chk(a(-index-1) < key);
        }

        // search for the value that was previously seeded
        val indexMagic = ArrayUtils.binarySearch(a, magicValue);
        chk(a(indexMagic) == magicValue);

        return true;
	}

	public static def main(args: Rail[String]): void = {
		new TestArrayUtils().execute();
	}
}

