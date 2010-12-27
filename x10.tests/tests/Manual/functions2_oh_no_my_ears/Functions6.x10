/* Current test harness gets confused by packages, but it would be in package functions2_oh_no_my_ears;
*/

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

// file Functions line 225
 import x10.util.*;
 class Finderator {
 static def find[T](f: (T) => Boolean, xs: x10.util.List[T], absent:T): T = {
  for (x: T in xs)
    if (f(x)) return x;
  absent
}
 static def checkery() {
xs: List[Int] = new ArrayList[Int]();
x: Int = find((x: Int) => x>0, xs, 0);  // ShouldNotBeERR
}}

class Hook {
   def run():Boolean = true;
}


public class Functions6 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Functions6().execute();
    }
}    
