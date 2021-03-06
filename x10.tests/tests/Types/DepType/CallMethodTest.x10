/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

import harness.x10Test;
import x10.regionarray.Region;

/** 
 * Check that the return type of a call to a method which has a deptype as its return
 * type is handled correctly. 
 *
 *@author vj,10/20/2006
 */
public class CallMethodTest extends x10Test { 
  
  public def run(): boolean { 
       val r:Region(2) = Region.makeRectangular(1..10, 1..10);
       return true;
    }
   public static def main(var args: Rail[String]): void {
        new CallMethodTest().execute();
    }
}
