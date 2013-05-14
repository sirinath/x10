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
public class View {
	public val from: Vec;
	public val att: Vec;
	public val up: Vec;
	public val dist: double;
	public val angle: double;
	public val aspect: double;

	public def this(var from_: Vec, var at_: Vec, var up_: Vec, var dist_: double, var angle_: double, var aspect_: double): View = {
		from = from_;
		att = at_;
		up = up_;
		dist = dist_;
		angle = angle_;
		aspect = aspect_;
	}
}
