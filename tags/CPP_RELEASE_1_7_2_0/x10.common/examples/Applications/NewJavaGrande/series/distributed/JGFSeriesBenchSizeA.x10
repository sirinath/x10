/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - MPJ Version 1.0         *
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
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 2001.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/
import series.*;
import jgfutil.*;
import harness.x10Test;

public class JGFSeriesBenchSizeA extends x10Test {

	public boolean run() {
		JGFInstrumentor.printHeader(2, 0, place.MAX_PLACES);
		JGFSeriesBench se = new JGFSeriesBench();
		se.JGFrun(0);
		return true;
	}

	public static void main(String[] args) {
		new JGFSeriesBenchSizeA().execute();
	}
}

