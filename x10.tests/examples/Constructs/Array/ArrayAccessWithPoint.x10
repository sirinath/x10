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
import x10.array.Array;

/**
 * Simple array test.
 * Testing whether one can write ia(p) for a point with the same rank as ia.
 */

public class ArrayAccessWithPoint extends x10Test {

    def a(b:int) {}

    public def run(): boolean = {

        val e = 1..10;
        val ia = Array.make[int](e->here, (Point)=>0); // will infer ia:Array[int](1)
        val p = [1] as Point; // will infer p:Point(1).

        a(ia(p)); 

        return true;
    }

    public static def main(Rail[String]) = {
        new ArrayAccessWithPoint().execute();
    }
}
