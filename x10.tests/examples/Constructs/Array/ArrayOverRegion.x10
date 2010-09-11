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
 * Region r occuring in a distribution context is converted to r->here
 */

public class ArrayOverRegion extends x10Test {

    public def run(): boolean = {

        val r = [1..10, 1..10] as Region;
        val ia = new Array[double](r, (var (i,j): Point)=> i+j as Double);

        chk(ia.region.equals(r));

        for (val p(i,j): Point(2) in r) chk(ia(p) == i+j);

        return true;
    }

    public static def main(var args: Array[String](1)): void = {
        new ArrayOverRegion().execute();
    }
}
