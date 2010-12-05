/* Current test harness gets confused by packages, but it would be in package classes_not_muskrats_instead_of_blueberry_cake_with_honey;
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

// file Classes line 590
 class Waif(rect:Boolean, onePlace:Place, zeroBased:Boolean) {
property rail: Boolean = rect && onePlace == here && zeroBased;
}

class Hook {
   def run():Boolean = true;
}


public class Classes12 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Classes12().execute();
    }
}    
