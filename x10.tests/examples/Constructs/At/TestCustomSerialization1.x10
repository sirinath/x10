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
import x10.io.CustomSerialization;

/*
 * Simple test case to check to see if custom serialization
 * protocol is being called by runtime system
 */
public class TestCustomSerialization1 extends x10Test {
  static class CS implements CustomSerialization {
     val x:int;
     val y:int;
     transient var sum:int;
 
     public def serialize():Any = [x,y];
  
     def this(a:Any) {
        val t = a as Array[int](1);
        x = t(0);
        y = t(1);
        sum = x + y;
     }
 
     def this(a:int, b:int) { x = a; y = b; sum = x + y; }
  }

  public def run():boolean {
    val x = new CS(10,20);
    at (here.next()) {
        // The custom serialization logic re-establishes that sum = x + y
        // Default serialzation would result in sum having the value of zero.
        chk(x.sum == x.x+x.y);
    }
    return true;
  }

  public static def main(Array[String]) {
      new TestCustomSerialization1().execute();
  }

}
  
 