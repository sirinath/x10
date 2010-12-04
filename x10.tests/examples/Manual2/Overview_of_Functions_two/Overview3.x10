package  Overview_of_Functions_two;


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

// file Overview line 133
 class Whatevermore {
  def plzchk(){
    val square = (i:Int) => i*i;
  val r : Array[Int](1) = new Array[Int](10, square);
}}

class Hook {
   def run():Boolean = true;
}


public class Overview3 extends x10Test {
   public def run() : boolean = (new Hook()).run();
   public static def main(var args: Array[String](1)): void = {
        new Overview3().execute();
    }
}    
