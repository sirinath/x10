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
 * Ensures double arrays are implemented.
 */

public class Array3Double extends x10Test {

    public def run(): boolean = {
        val r  = [1..10, 1..10] as Region;
        val ia = new Array[Double](r, (x:Point)=>0.0D);
        ia(1, 1) = 42.0D;
        x10.io.Console.OUT.println("ia(1,1)=" + ia(1,1));
        return 42.0D == ia(1,1);
    }

    public static def main(Rail[String]) = {
        new Array3Double().execute();
    }
}
