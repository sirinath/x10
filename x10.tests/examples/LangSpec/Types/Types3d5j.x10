/* Current test harness gets confused by packages, but it would be in package Types3d5j;
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



public class Types3d5j extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Types3d5j().execute();
    }


// file Types line 2673
 static  class Example {
static def first[T](x:Array[T](1)) = x(0);
static def example() {
  val ss <: Array[String] = ["X10", "Scala", "Thorn"];
  val s1 = first(ss);
  assert s1.equals("X10");
}
}
 static  class Hook{ def run() {Example.example(); return true;}}

}
