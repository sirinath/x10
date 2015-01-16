/* Current test harness gets confused by packages, but it would be in package Classes5l3r_Bad34_MustFailCompile;
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
 *  (C) Copyright IBM Corporation 2006-2015.
 */

import harness.x10Test;



public class Classes5l3r_Bad34_MustFailCompile extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(args:Rail[String]):void {
        new Classes5l3r_Bad34_MustFailCompile().execute();
    }


// file Classes line 1129
//gone!\\  // FOR-ERR-CASE-DELETE: def recip(m:Long){true} = 1.0/m;
 static class Super {
  def recip(n:Long){n != 0} = 1.0/n;
}
 static class Sub extends Super{
 def recip(n:Long){n != 0, n != 3} = 1.0/(n * (n-3)); // ERR
//gone!\\   def recip(m:Long){true} = 1.0/m;
}
 static class Example{
  static def example() {
     val s : Super = new Sub();
     s.recip(3);
  }
}

 static class Hook {
   def run():Boolean = true;
}

}
