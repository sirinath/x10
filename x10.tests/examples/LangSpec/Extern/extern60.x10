/* Current test harness gets confused by packages, but it would be in package Extern_or_Die;
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

import x10.compiler.Native;

public class extern60 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new extern60().execute();
    }


// file extern line 114
 static class Ability {
  static val a : Int = 1;
  @Native("java", "a")
  static native def fromStatic():Int;
}

 static class Hook {
   def run():Boolean = true;
}

}