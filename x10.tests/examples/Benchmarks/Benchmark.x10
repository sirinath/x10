// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test.

import harness.x10Test;

import x10.io.StringWriter;
import x10.io.Printer;

import x10.compiler.Native;
import x10.compiler.NativeRep;


/**
 * @author bdlucas
 */

import x10.io.Printer;
import x10.io.Console;

abstract class Benchmark extends x10Test {

    abstract def once(): double;
    abstract def expected(): double;
    abstract def operations(): double;

    def now() = (System.nanoTime() as double) * 1e-9;
    val out:Printer;

    @Native("java", "\"java\"")
    @Native("c++", "String::Lit(\"cpp\")")
    const lg = "";

    const WARMUP = 30.0;  // java warmup time in secs
    const TIMING = 10.0;  // how long to run tests in secs

    def this() {
        out = x10.io.Console.OUT;
    }

    public def run():boolean {

        // functional check
        out.println("functional check");
        val warmup = now();
        val result = once();
        if (result!=expected()) {
            out.println("got " + result + "; expected " + expected());
            return false;
        }

        // if java do warmup
        if (lg.equals("java")) {
            out.println("warmup for >" + WARMUP + "s");
            while (now()-warmup < WARMUP)
                once();
        }
        
        // run tests
        out.println("timing for >" + TIMING + "s");
        var avg:double = 0.0;
        var min:double = double.POSITIVE_INFINITY;
        var count:int = 0;
        while (avg < TIMING) {
            val start = now();
            once();
            val t = now() - start;
            if (t<min)
                min = t;
            avg += t;
            count++;
        }
        avg /= count;

        // print info
        val ops = operations() / avg;
        out.printf("time: %.3f; count: %d; min/time: %.2f\n", avg, count, min/avg);
        if (ops<1e6)      out.printf("%.3g kop/s\n", ops/1e3);
        else if (ops<1e9) out.printf("%.3g Mop/s\n", ops/1e6);
        else              out.printf("%.3g Gop/s\n", ops/1e9);
        out.printf("test=%s lg=x10-%s ops=%g\n", className(), lg, ops);
            
        // test succeeded
        return true;
    }
}
