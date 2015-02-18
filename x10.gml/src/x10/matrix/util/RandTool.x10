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

package x10.matrix.util;

import x10.util.Random;
import x10.lang.Math;
import x10.matrix.ElemType;

/**
 * Provide random value generation methods which
 * can be used to generate random numbers in normal or unified distribution.
 * Note: not thread-safe
 */
public class RandTool {
	private static val tool:RandTool = new RandTool();

	private var randomGen:Random = new Random(here.id());

	/**
	 * Return the static Random type variable used to generate random values
	 * in integer and double.
	 */
	public static def getRandGen():Random {
        return tool.reSeed(tool.randomGen.nextLong() + here.id());
	}
	
	public static def reSeed(seed:Long):Random {
		tool.randomGen = new Random(seed);
		return tool.randomGen;
	}

	/**
	 * Get a random double number
	 */
	public static def nextDouble():Double = tool.randomGen.nextDouble();

        /**
	 * Get a random float number
	 */
	public static def nextFloat():Float = tool.randomGen.nextFloat();

	/**
	 * Get a random integer
	 */
	public static def nextInt(upbound:Int):Int = tool.randomGen.nextInt(upbound);

	/**
	 * Get a random Long integer
	 */
	public static def nextLong(upbound:Long):Long = tool.randomGen.nextLong(upbound);

    /**
       Support for parametric x10.matrix.ElemType. This is intended to be set to Float or 
       Double before compilation. This method permits a random number of type T to be 
       generated by casing on the type T.

     */
    public static def nextElemType[T]():T = nextElemType[T](tool.randomGen);
    public static def nextElemType[T](r:Random):T {
	if (T==Float) return r.nextFloat() as T;
	if (T==Double) return r.nextDouble() as T;
	if (T==Int) return r.nextInt() as T;
	if (T==Long) return r.nextLong() as T;
	throw new Exception("Unexpected type T: should be one of Float, Double, Int or Long.");
    }

	/**
	 * Return a non-negative random integer in normal distribution, using
	 * the mean specified as avg. The standard deviation is avg/2.
	 */
	public static def nextNormalRandDst(avg:Double): Int {
		val rg= tool.randomGen;
		var p:Double;
		var d:Int;
		while (true) {
		    val u = (2.0*rg.nextDouble()-1.0) as ElemType; //change to [-1, 1]
		    val v = (2.0*rg.nextDouble()-1.0) as ElemType;
		    val s = (u*u + v*v) as ElemType;

		    if (MathTool.isZero(s)|| (s>=(1.0 as ElemType))) continue; // try another set

		    val lns:Double = Math.log(s);
		    p = Math.sqrt(-2.0*lns/s) * u;
		    if (p < -1.0 || p >  1.0)  p = 0.0;
		    
		    // mean + std_deviation * p;
		    d = Math.floor((avg + avg/2.0*p) + 0.5) as Int ;
		    if (d < 1n) d = 1n;
		    break;
		}
		return d;
	}
	
	/**
	 * Return a non-negative random integer in uniform distribution.
	 * The mean is specified by avg within the range of [1, 2*avg]
	 */
	public static def nextUniRandDst(max:Double): Int {
		val rg = RandTool.getRandGen();
		val retval = 1n + Math.floor(rg.nextFloat()*max) as Int;
		return retval; 
	}
}
