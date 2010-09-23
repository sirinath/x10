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

/**
 * Check you can offer the result of a method invocation, where the reducer is of a generic type.
 * @author vj
 */
public class CF3Generic extends x10Test {

    static struct Reducer implements Reducible[Int] {
     	public  def zero()=0;
     	public  def apply(a:Int,b:Int)=a+b;
    }
    static class C[T] {
    	 def m(b:T) offers T {
    	    	offer b;
    	    }
        def m(a:Reducible[T], b:T):T {
      	  val x = finish(a) {
      		  m(b);
      		  m(b);
      	  };
      	  return x;
        }
        
  	}
	public def run() = 6==new C[Int]().m(Reducer(), 3);

	public static def main(Array[String](1)) {
		new CF3Generic().execute();
	}
}
