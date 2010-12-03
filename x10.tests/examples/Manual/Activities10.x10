package  Activities.Atomic.Redund.One;


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

// file Activities line 763
 import x10.util.*;
 class Redund[T] {
   val list = new ArrayList[T]();
   var size : Int = 0;
def add(x:T) { // Incorrect
  this.list.add(x);
  this.size = this.size + 1;
}
}

class Hook {
   def run():Boolean = true;
}


public class Activities10 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Activities10().execute();
    }
}    
