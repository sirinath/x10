/* Current test harness gets confused by packages, but it would be in package Statements2d4h;
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



public class Statements2d4h extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(args:Rail[String]):void {
        new Statements2d4h().execute();
    }


// file Statements line 705
 static  class Example {
static def sum(a: Rail[Int]): Int {
  var s : Int = 0;
  for(v in a) s += v;
  return s;
}
}

 static class Hook {
   def run():Boolean = true;
}

}
