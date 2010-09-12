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

/**
 * Version of Stream with a collection of local arrays implementing a
 * global array.
 */

public class SeqStream1 extends Benchmark {

    static alpha = 1.5;
    static beta = 2.5;
    static gamma = 3.0;

    static NUM_TIMES = 10;
    static PARALLELISM = 2;
    static localSize = 512*1024;

    public def operations() = 1.0 * localSize * PARALLELISM * NUM_TIMES;
    public def expected() = (localSize+1)*(alpha+gamma*beta);

    //
    //
    //

    val as_ = ValRail.make[Rail[double]](PARALLELISM, (p:int) =>
        Rail.make[double](localSize)
    );
    
    val bs = ValRail.make[ValRail[double]](PARALLELISM, (p:int) => 
        ValRail.make[double](localSize, (i:int)=>alpha*(p*localSize+i))
    );

    val cs = ValRail.make[ValRail[double]](PARALLELISM, (p:int)=>
        ValRail.make[double](localSize, (i:int)=>beta*(p*localSize+i))
    );

    public def once() {
        for (var p:int=0; p<PARALLELISM; p++) {
            x10.io.Console.OUT.println("p " + p);
            val a = as_(p);
            val b = bs(p);
            val c = cs(p);
            for (var tt:int=0; tt<NUM_TIMES; tt++) // XTENLANG-311
                for (var i:int=0; i<localSize; i++)
                    a(i) = b(i) + gamma*c(i);
        }
        return as_(1)(1);
    }

    //
    //
    //

    public static def main(Array[String](1)) {
        new SeqStream1().execute();
    }

}
