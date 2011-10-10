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
package raytracer.distributed.raytracer;

final public class Ray {
	public var p: Vec;
	public var d: Vec;

	public def this(var pnt: Vec, var dir: Vec): Ray = {
		this(pnt, dir, true);
	}

	public def this(var pnt: Vec, var dir: Vec, var normalize: boolean): Ray = {
		if (normalize) {
			p = new Vec(pnt.x, pnt.y, pnt.z);
			d =  new Vec(dir.x, dir.y, dir.z).normalized();
		} else {
			p = pnt;
			d = dir;
		}
	}

	public def this(): Ray = {
		p = new Vec();
		d = new Vec();
	}

	public def d(var d_: Vec): Ray = {
		return new Ray(p, d_, false);
	}

	public def point(var t: double): Vec = {
		return new Vec(p.x + d.x * t, p.y + d.y * t, p.z + d.z * t);
	}

	public def toString(): String = {
		return " { " + p.toString() + "->" + d.toString() + " } ";
	}
}
