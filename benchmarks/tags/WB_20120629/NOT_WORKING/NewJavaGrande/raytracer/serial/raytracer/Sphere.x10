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
package raytracer.serial.raytracer;

//ok
public class Sphere extends Primitive {
	val c: Vec;
	val r: double;
	val r2: double;

	public def this(var center: Vec, var radius: double, var s: Surface): Sphere = {
		super(s);
		c = center;
		r = radius;
		r2 = r*r;
	}

	public def this(var center: Vec, var radius: double): Sphere = {
		this(center, radius, new Surface());
	}

	public def intersect(var ry: Ray): Isect = {
		var v: Vec = Vec.sub(c, ry.p);
		var b: double = Vec.dot(v, ry.d);
		var disc: double = b*b - Vec.dot(v, v) + r2;
		if (disc < 0.0) {
			return null;
		}
		disc = Math.sqrt(disc);
		var t: double = (b - disc < 1e-6) ? b + disc : b - disc;
		if (t < 1e-6) {
			return null;
		}
		var ip: Isect = new Isect(t, Vec.dot(v, v) > r2 + 1e-6 ? 1 : 0, this, surf);
		return ip;
	}

	public def normal(var p: Vec): Vec = {
		return Vec.sub(p, c).normalized();
	}

	public def toString(): String = {
		return "Sphere { " + c.toString() + "," + r + " }";
	}

	public def getCenter(): Vec = {
		return c;
	}
}
