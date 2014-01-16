
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

// SOURCEPATH: x10.dist/samples/CUDA
// NUM_PLACES: 4

public class CUDATopologyTest extends x10Test {
    public def run():boolean {
         val args = new Rail[String](0);
         CUDATopology.main(args);
         return true;
    }

    public static def main(args:Rail[String]) {
	new CUDATopologyTest().execute();
    }
}
