/* Current test harness gets confused by packages, but it would be in package Expressions3e9h;
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



public class Expressions3e9h extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Expressions3e9h().execute();
    }


// file Expressions line 1162
 static  class Example { static def example() {
   val ob : Object = "a String" as Object; // upcast
   val st : String = ob as String;         // downcast
   assert st == ob;
 } }
 static  class Hook{ def run() { Example.example(); return true;} }

}