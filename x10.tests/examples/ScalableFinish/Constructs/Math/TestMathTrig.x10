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
 * @author milthorpe 6/2009
 */
class TestMathTrig   {

    public def run(): boolean {
        /*
         * Validate x10.lang.Math functions by use of trigonometric 
         * and hyperbolic identities.
         */
        val theta : double = Math.PI / 4.0;
        val cos : double = Math.cos(theta);
        val sin : double = Math.sin(theta);
        val tan : double = Math.tan(theta);
        chk(tan == sin / cos);

        var theta2 : double = Math.atan(tan);
        chk(theta2 == theta);

        val x : double = 0.6;
        chk(Math.asin(x) + Math.acos(x) == Math.PI / 2.0);
        chk(Math.atan(x) + Math.atan(1.0/x) == Math.PI / 2.0);

        val sinh : double = Math.sinh(theta);
        val cosh : double = Math.cosh(theta);
        val tanh : double = Math.tanh(theta);
        chk(tanh == sinh / cosh);   

        /* cartesian (1.0, 1.0) = polar (PI/4, sqrt(2)) */ 
        theta2 = Math.atan2(1.0, 1.0);
        chk(theta2 == theta);
        val hypot : double = Math.hypot(1.0, 1.0);
        chk(hypot == Math.sqrt(2.0));     

        /* Validate cube root against a cube */
        val piCubed : double = Math.PI * Math.PI * Math.PI;
        chk (Math.cbrt(piCubed) == Math.PI);

        /* Validate expm1 against log1p */
        val d : double = 0.02;
        val e : double = Math.expm1(d);
        chk(Math.log1p(e) == d);

        return true;
    }

    public static def main(Rail[String]) {
        new TestMathTrig().run ();
    }
}

