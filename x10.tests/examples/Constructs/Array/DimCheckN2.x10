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
import x10.array.Array;
import x10.array.Region;

/**
 * This must compile and run fine. Checks that the initializer may not specify
 * the arity of the region.
 *
 * @author vj 12 2006
 */

public class DimCheckN2 extends x10Test {

    def m(var d: Dist(2)): void = {
        val a1 = Array.make[int](d, (p(i): Point): int => { return i; });
    }

    public def run(): boolean = {
        val d  = Dist.makeConstant([0..2, 0..3] as Region, here);
        m(d);
        return true;
    }

    public static def main(var args: Rail[String]): void = {
        new DimCheckN2().execute();
    }
}
