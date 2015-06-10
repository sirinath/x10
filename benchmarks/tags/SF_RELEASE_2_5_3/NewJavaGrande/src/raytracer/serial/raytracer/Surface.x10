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
public class Surface {
	public val color: Vec;
	public val kd: double;
	public val ks: double;
	public val shine: double;
	public val kt: double;
	public val ior: double;

	public def this(): Surface {
		color = new Vec(1, 0, 0);
		kd = 1.0;
		ks = 0.0;
		shine = 0.0;
		kt = 0.0;
		ior = 1.0;
	}

	public def this(var shine_: double, var ks_: double, var kt_: double, var c_: Vec): Surface {
		kd = 1.0;
		ks = ks_;
		shine = shine_;
		kt = kt_;
		ior = 1.0;
		color = c_;
	}

	public def toString(): String {
		return "Surface { color = " + color + " }";
	}
}
