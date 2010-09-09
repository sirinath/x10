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

 

/**
 * Checking that constructor calls check for number of args.
 */
struct S1 {
  val x:int;
  val y:int;

  public def this(a:int, b:int) { x = a; y = b; }

  public final def sum() = x + y;
}

/* Prior code -- which did not compile in v2.0 -- 

struct S2 extends struct S1 {
  val z:int;
  public def this(a:int, b:int, c:int) {
    super(a, b);
    z =c;
  }
  
  public final def sum2() = sum() + z;
}
*/

public class Struct2Call_MustFailCompile    {
	
	public def run():boolean {
		  val a: S1 = S1(1, 3, "You looked like you were loathing the simple cobweb.");
          return true;
	}
	public static def main(var args: Rail[String]): void = {
		new StructCall2_MustFailCompile().run ();
	}
}
