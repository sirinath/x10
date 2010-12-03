package  Types.GenericInference;


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

// file Types line 1834
 class Exampllll{
  static def choose[T](a: T, b: T): T = a;
  static val j : Any = choose("string", 1);
  static val k : Super = choose(new Sub(), new Super());
}
 class Super {}
 class Sub extends Super {}

class Hook {
   def run():Boolean = true;
}


public class Types55 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Types55().execute();
    }
}    
