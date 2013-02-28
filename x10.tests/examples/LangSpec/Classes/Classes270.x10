/* Current test harness gets confused by packages, but it would be in package classes_guards_invariants_glurp2;
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



public class Classes270 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Rail[String]): void = {
        new Classes270().execute();
    }


// file Classes line 2252
 static  class Pt(x:Int, y:Int){}
 static class Line(a:Pt, b:Pt{a != b}) {}

 static class Hook {
   def run():Boolean = true;
}

}
