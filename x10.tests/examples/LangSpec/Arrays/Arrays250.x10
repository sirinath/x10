/* Current test harness gets confused by packages, but it would be in package Arrays250;
*/
// Warning: This file is auto-generated from the TeX source of the language spec.
// If you need it changed, work with the specification writers.


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



public class Arrays250 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Arrays250().execute();
    }


// file Arrays line 360
 static class Example{
static def example(A:Array[Int]) {
for (p in A) A(p) = 2*A(p);
}}
 static  class Hook{ def run() { val a = [1,2]; Example.example(a); return a(0)==2 && a(1)==4; }}

}