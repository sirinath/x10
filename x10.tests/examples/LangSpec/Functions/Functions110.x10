/* Current test harness gets confused by packages, but it would be in package Functions_Operatorfunctionsgracklegrackle;
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



public class Functions110 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Functions110().execute();
    }


// file Functions line 467
 static  class JustATest {
 val dummy = [((x:Int,y:Int) => x+y),  // ShouldNotBeERR: Semantic Error: No least common ancestor found for types "(a1:x10.lang.String, a2:x10.lang.String)=> x10.lang.String" and "(a1:x10.lang.Long, a2:x10.lang.Long)=> x10.lang.Long".
  (x:Double,y:Double) => x-y
  ];
 }

 static class Hook {
   def run():Boolean = true;
}

}