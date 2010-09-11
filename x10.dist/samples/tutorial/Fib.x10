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

import x10.io.Console;

/**
 * This is a small program to illustrate the use of 
 * <code>async</code> and <code>finish</code> in a 
 * prototypical recursive divide-and-conquer algorithm.  
 * It is obviously not intended to show a efficient way to
 * compute Fibonacci numbers in X10.<p>
 *
 * The heart of the example is the <code>run</code> method,
 * which directly embodies the recursive definition of 
 * <pre>
 *   Fib(n) = Fib(n-1)+Fib(n-
 * </pre>
 * by using an <code>async</code> to compute <code>Fib(n-1)</code> while
 * the current activity computes <code>Fib(n-2)</code>.  A <code>finish</code>
 * is used to ensure that both computations are complete before 
 * their results are added together to compute <code>Fib(n)</code>
 */
public class Fib {
  /**
   * Used as an in-out parameter to the computation.
   * When the Fib object is created, r indicates the number to compute.
   * After the computation has completed, r holds the result (Fib(r)).
   */
  var r:int;

  public def this(x:int) {
    r = x;
  }

  public def run() {
    if (r<2) return; // r already contains Fib(r)
    
    val f1 = new Fib(r-1);
    val f2 = new Fib(r-2);
    finish {
      async f1.run();
      f2.run();
    }
    r = f1.r + f2.r;
  }

  public static def main(args:Array[String](1)) {
    val n = (args.size > 0) ? int.parse(args(0)) : 10;
    Console.OUT.println("Computing Fib("+n+")");
    val f = new Fib(n);
    f.run();
    Console.OUT.println("Fib("+n+") = "+f.r);
  }
}

