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

import x10.io.Printer;
import x10.io.StringWriter;

import harness.x10Test;

abstract public class TestPoint extends x10Test {
    
    var os: StringWriter;
    var out: Printer;
    val testName = "TestPo";

    def this() {
        System.setProperty("line.separator", "\n");
        try {
            val o = new StringWriter();
            os = o;
            out = new Printer(o);
        } catch (e:Exception) {
            //e.printStackTrace();
            x10.io.Console.OUT.println(e.toString());
        }
    }

    abstract def expected():String;

    def status() {
        val got = os.result();
        if (got.equals(expected())) {
            return true;
        } else {
            x10.io.Console.OUT.println("=== got:\n" + got);
            x10.io.Console.OUT.println("=== expected:\n" + expected());
            x10.io.Console.OUT.println("=== ");
            return false;
        }
    }

    def prPoint(test: String, p: Point): void = {
        var sum: int = 0;
        for (var i: int = 0; i<p.rank; i++)
            sum += p(i);
        pr(test + " " + p + " sum=" + sum);
    }

    def pr(s: String): void = {
        out.println(s);
    }

}
