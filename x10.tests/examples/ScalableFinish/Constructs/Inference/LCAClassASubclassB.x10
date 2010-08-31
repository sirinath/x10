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
 * Inference of least common ancestor type.
 *
 * @author vj 2/29/2010
 */
public class LCAClassASubclassB   {
	class A {}
	class B extends A {}
	
	public def run() {
	  val x = new A();
      val y = new B();
	// should succeed. LCA of A and B is A.
      val z:ValRail[A] = [x,y];
	  return true;
	}

	public static def main(Rail[String]) {
		new LCAClassASubclassB().run ();
	}
}

