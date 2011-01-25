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


import x10.util.Box;

import harness.x10Test;


abstract public class TestRegion extends x10Test {
    
    val os: GlobalRef[StringWriter];
    val out: GlobalRef[Printer];

    def testName() {
        var cn:String = typeName();
        val init = cn.substring(0,6); // XTENLANG-???
        if (init.equals("class "))
            cn = cn.substring(6, cn.length());
        return cn;
    }

    def this() {
        System.setProperty("line.separator", "\n");
        val tmp = new StringWriter();
        os = GlobalRef[StringWriter](tmp);
        out = GlobalRef[Printer](new Printer(tmp));
    }

    abstract def expected():String;

    def status() {
        val sw : StringWriter = (this.os as GlobalRef[StringWriter]{self.home==here})();
        val got = sw.result();
        if (got.equals(expected())) {
            return true;
        } else {
            x10.io.Console.OUT.println("=== got:\n" + got);
            x10.io.Console.OUT.println("=== expected:\n" + expected());
            x10.io.Console.OUT.println("=== ");
            return false;
        }
    }

    //
    //
    //

    class Grid {

        var os: Rail[Object] = Rail.make[Object](10);

        def set(i0: int, vue: double): void = {
            os(i0) = vue as Box[double]; 
        }

        def set(i0: int, i1: int, vue: double): void = {
            if (os(i0)==null) os(i0) = new Grid();
            val grid = os(i0) as Grid;
            grid.set(i1, vue);
        }

        def set(i0: int, i1: int, i2: int, vue: double): void = {
            if (os(i0)==null) os(i0) = new Grid();
            val grid = os(i0) as Grid;
            grid.set(i1, i2, vue);
        }

        def pr(rank: int): void = {
            var min: int = os.length;
            var max: int = 0;
            for (var i: int = 0; i<os.length; i++) {
                if (os(i)!=null) {
                    if (i<min) min = i;
                    else if (i>max) max = i;
                }
            }
            Out : Printer = (out as GlobalRef[Printer]{self.home==here})();
            for (var i: int = 0; i<os.length; i++) {
                var o: Object = os(i);
                if (o==null) {
                    if (rank==1)
                        Out.print(".");
                    else if (rank==2) {
                        if (min<=i && i<=max)
                            Out.print("    " + i + "\n");
                    }
                } else if (o instanceof Grid) {
                    if (rank==2)
                        Out.print("    " + i + "  ");
                    else if (rank>=3) {
                        Out.print("    ");
                        for (var j: int = 0; j<rank; j++)
                            Out.print("-");
                        Out.print(" " + i + "\n");
                    }
                    (o as Grid).pr(rank-1);
                } else {
                    // XTENLANG-34, XTENLANG-211
                    val d = (o as Box[double]).value;
                    Out.print(""+(d as int));
                }

                if (rank==1)
                    Out.print(" ");
            }
            if (rank==1)
                Out.print("\n");
        }
    }

    def prArray(test: String, r: Region): Array[double]{rank==r.rank} = {
        return prArray(test, r, false);
    }

    def prArray(test: String, r: Region, bump: boolean): Array[double]{rank==r.rank} = {

        val init1 : (Point(r.rank))=>double  = (pt: Point(r.rank)) => {
            var v: int = 1;
            for (var i: int = 0; i<pt.rank; i++)
                v *= pt(i);
            return v%10 as double;
        };

        val init0 : (Point(r.rank))=> double = (Point(r.rank)) => 0.0D as double;

        val a = new Array[double](r, bump? init0 : init1);
        prArray(test, a, bump);

        return a as Array[double]{rank==r.rank};
    }

    def prUnbounded(test: String, r: Region): void = {
        try {
            prRegion(test, r);
            var i: Iterator[Point] = r.iterator();
        } catch (e: UnboundedRegionException) {
            pr(e.toString());
        }
    }

    def pr(test: String, myRun: ()=>String) {
        var r: String;
        try {
            r = myRun();
        } catch (e: Throwable) {
            r = e.getMessage();
        }
        pr(test + " " + r);
    }
            
    def prRegion(test: String, r: Region): void = {

        pr("--- " + testName() + ": " + test);

        pr("rank",       () => r.rank.toString());
        pr("rect",       () => r.rect.toString());
        pr("zeroBased",  () => r.zeroBased.toString());
        pr("rail",       () => r.rail.toString());

        pr("isConvex()", () => r.isConvex().toString());
        pr("size()",     () => r.size().toString());

        pr("region: " + r);
    }

    def prArray(test: String, a: Array[double]): void = {
        prArray(test, a, false);
    }

    def prArray(test: String, a: Array[double], bump: boolean): void = {

        val r: Region = a.region;

        prRegion(test, r);

        pr("  iterator");
        var grid: Grid = new Grid();
        for (p:Point in a.region) {
            if (p.rank==1) {
                val a2 = a as Array[double](1);
                if (bump) a2(p(0)) = a2(p(0)) + 1;
                grid.set(p(0), a2(p(0)));
            } else if (p.rank==2) {
                val a2 = a as Array[double](2);
                if (bump) a2(p(0), p(1)) = a2(p(0), p(1)) + 1;
                grid.set(p(0), p(1), a2(p(0),p(1)));
            } else if (p.rank==3) {
                val a2 = a as Array[double](3);
                if (bump) a2(p(0), p(1), p(2)) = a2(p(0), p(1), p(2)) + 1;
                grid.set(p(0), p(1), p(2), a2(p(0),p(1),p(2)));
            }
        }
        grid.pr(a.rank);
    }


    def pr(s: String): void = {
        (out as GlobalRef[Printer]{self.home==here})().println(s);
    }

    def r(a: int, b: int, c: int, d: int): Region(2) {
        return (a..b)*(c..d);
    }

    // a simple mechanism of somewhat dubious utility to allow
    // semi-symbolic specification of halfspaces. For example
    // X0-Y1 >= n is specified as addHalfspace(X(0)-Y(1), GE, n)
    //
    // XXX coefficients must be -1,0,+1; can allow larger coefficients
    // by increasing # bits per coeff

    static ZERO = 0xAAAAAAA;
    static GE = 0;
    static LE = 1;
    def X(axis: int) = 0x1<<2*axis;

    public def reg(rank: int, var coeff: int, op: int, k: int): Region(rank) {
        coeff += ZERO;
        val as_ = Rail.make[int](rank);
        for (var i: int = 0; i<rank; i++) {
            var a: int = (coeff&3) - 2;
            as_(i) = op==LE? a : - a;
            coeff = coeff >> 2;
        }
        return Region.makeHalfspace(as_, op==LE? -k : k);
    }



}
