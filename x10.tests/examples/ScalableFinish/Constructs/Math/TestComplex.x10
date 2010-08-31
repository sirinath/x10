/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 *  (C) Copyright Australian National University 2009-2010.
 */

 

/**
 * Test operations on complex numbers.
 * @author milthorpe
 */
class TestComplex   {
    public def run(): boolean {
        val a = Complex(2.0, 2.0);
        chk ((-(-a)) == a);
        chk (a.abs() == Math.sqrt(8.0));

        val b = a.conjugate();
        chk (b.conjugate() == a);

        val c = Complex(1.0, 4.0);
        chk (a + c - c == a, "a + c - c = a");
        /* Note: this identity does not always hold, given peculiarities of Smith's algorithm for division */
        chk ((a * c) / c == a, "a * c / c = a");
        

        val d = Complex(4.0, -1.0);
        chk (a + d - d == a, "a + d - d = a");
        
        chk ((a * d) / d == a, "a * d / d = a");

        val e = a / Complex(0.0, 0.0);
        chk (e.isNaN());

        val f = Complex(3.0, 4.0);
        val fAbs = f.abs();
        chk (fAbs == 5.0); // abs(3 + 4i) = 5 by Pythagoras' theorem

        return true;
    }

    public static def main(Rail[String]) {
        new TestComplex().run ();
    }

}
