package  Arrays_Arrays_wombatsfromlemuria;


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

// file Arrays line 341
class Example{
def example(){
val A1 = new Array[Int](1..10, 0);
A1(4) = A1(4) + 1;
val A4 = new Array[Int]((1..2)*(1..3)*(1..4)*(1..5), 0);
A4(2,3,4,5) = A4(1,1,1,1)+1;
}}

class Hook {
   def run():Boolean = true;
}


public class Arrays25 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Arrays25().execute();
    }
}    
