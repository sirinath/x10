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
public class Interval {
	public val number: int;
	public val width: int;
	public val height: int;
	public val yfrom: int;
	public val yto: int;
	public val total: int;

	public def this(var number_: int, var width_: int, var height_: int, var yfrom_: int, var yto_: int, var total_: int): Interval = {
		number = number_;
		width = width_;
		height = height_;
		yfrom = yfrom_;
		yto = yto_;
		total = total_;
	}
}
