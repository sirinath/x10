/* Current test harness gets confused by packages, but it would be in package Vars_For_Glares;
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



public class Vars80 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Vars80().execute();
    }


// file Vars line 348
 static  class DestructuringEx1 {
 def whyJustForLocals() {
val [i] : Point = Point.make(11);
val p[j,k] = Point.make(22,33);
val q[l,m] = [44,55]; // coerces an array to a point.
}}

 static class Hook {
   def run():Boolean = true;
}

}