/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - Version 2.0             *
*                                                                         *
*                            produced by                                  *
*                                                                         *
*                  Java Grande Benchmarking Project                       *
*                                                                         *
*                                at                                       *
*                                                                         *
*                Edinburgh Parallel Computing Centre                      *
*                                                                         *
*                email: epcc-javagrande@epcc.ed.ac.uk                     *
*                                                                         *
*                 Original version of this code by                        *
*            Florian Doyon (Florian.Doyon@sophia.inria.fr)                *
*              and  Wilfried Klauser (wklauser@acm.org)                   *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/
package raytracer.parallel.raytracer;

/**
 * This class reflects the 3d vectors used in 3d computations
 */
public class Vec {

	/**
	 * The x coordinate
	 */
	public val x: double;

	/**
	 * The y coordinate
	 */
	public val y: double;

	/**
	 * The z coordinate
	 */
	public val z: double;

	/**
	 * Constructor
	 * @param a the x coordinate
	 * @param b the y coordinate
	 * @param c the z coordinate
	 */
	public def this(var a: double, var b: double, var c: double): Vec {
		x = a;
		y = b;
		z = c;
	}

	/**
	 * Copy constructor
	 */
	public def this(var a: Vec): Vec {
		x = a.x;
		y = a.y;
		z = a.z;
	}

	/**
	 * Default (0,0,0) constructor
	 */
	public def this(): Vec {
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}

	/**
	 * Add a vector to the current vector
	 * @param: a The vector to be added
	 */
	final public def added(var a: Vec): Vec {
		return new Vec(x+a.x, y+a.y, z+a.z);
	}

	/**
	 * adds: Returns a new vector such as
	 * new = sA + B
	 */
	public static def adds(var s: double, var a: Vec, var b: Vec): Vec {
		return new Vec(s * a.x + b.x, s * a.y + b.y, s * a.z + b.z);
	}

	/**
	 * Adds vector such as:
	 * this += sB
	 * @param: s The multiplier
	 * @param: b The vector to be added
	 */
	final public def adds(var s: double, var b: Vec): Vec {
		return new Vec(x+s*b.x, y+s*b.y, z+s*b.z);
	}

	/**
	 * Substracs two vectors
	 */
	public static def sub(var a: Vec, var b: Vec): Vec {
		return new Vec(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static def mult(var a: Vec, var b: Vec): Vec {
		return new Vec(a.x * b.x, a.y * b.y, a.z * b.z);
	}

	public static def cross(var a: Vec, var b: Vec): Vec {
		return
			new Vec(a.y*b.z - a.z*b.y,
					a.z*b.x - a.x*b.z,
					a.x*b.y - a.y*b.x);
	}

	public static def dot(var a: Vec, var b: Vec): double {
		return a.x*b.x + a.y*b.y + a.z*b.z;
	}

	public static def comb(var a: double, var A: Vec, var b: double, var B: Vec): Vec {
		return
			new Vec(a * A.x + b * B.x,
					a * A.y + b * B.y,
					a * A.z + b * B.z);
	}

	final public def scale(var t: double): Vec {
		return new Vec(x*t, y*t, z*t);
	}

	final public def negate(): Vec {
		return new Vec(-x,-y,-z);
	}

	public def normalized(): Vec {
		var len: double;
		len = Math.sqrt(x*x + y*y + z*z);
		return (len > 0.0) ? new Vec(x /len, y/len, z/len) : this;
	}

	public def length(): double {
		return Math.sqrt(x*x+y*y+z*z);
	}

	final public def toString(): String {
		return "<" + x + "," + y + "," + z + ">";
	}
}
