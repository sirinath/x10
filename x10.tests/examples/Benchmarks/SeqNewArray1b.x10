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

import x10.simplearray.Array;

/**
 * @author bdlucas
 */
public class SeqNewArray1b extends Benchmark {

    //
    // parameters
    //

    val N = 1000000L;
    val M = 20L;
    def expected() = N*M as double;
    def operations() = N*M as double;

    //
    // the benchmark
    //

    val a = new Array[int](N+M-1, 1);

    def once() {
        var sum: int = 0;
        for (var k:long=0; k<M; k++)
            for (var i:long=0; i<N; i++)
                sum += a(i+k);
        return sum as double;
    }

    //
    // boilerplate
    //

    public static def main(Rail[String]) {
        new SeqNewArray1b().execute();
    }
}
