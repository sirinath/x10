/* Current test harness gets confused by packages, but it would be in package expsome_Expressions26;
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



public class Expressions260 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Expressions260().execute();
    }


// file Expressions line 1091

 static class Expressions26TestExp{
  def check()  = -12 as Byte;  }

 static class Hook {
   def run():Boolean = true;
}

}