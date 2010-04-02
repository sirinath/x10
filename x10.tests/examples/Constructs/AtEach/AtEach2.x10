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
import x10.array.Dist;

/**
 * Slightly different test for ateach.
 *
 * @author kemal, 12/2004
 * @author vj
 */
public class AtEach2 extends x10Test {
    var nplaces: int = 0;

    public def run(): boolean = {
        val d: Dist = Dist.makeUnique(Place.places);
        finish ateach (p in d) {
            // remember if here and d[i] disagree
            // at any activity at any place
            chk(here == d(p));
            async (this) 
             atomic nplaces++;  
        }
        // ensure that an activity ran in each place
        return nplaces == Place.MAX_PLACES;
    }

    public static def main(Rail[String])  {
        new AtEach2().execute();
    }
}
