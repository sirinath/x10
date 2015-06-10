/**************************************************************************
*                                                                         *
*         Java Grande Forum Benchmark Suite - Thread Version 1.0          *
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
package sparsematmult.parallel;

import sparsematmult.parallel.sparsematmult.*;
import jgfutil.*;
import harness.x10Test;;

public class JGFSparseMatmultBenchSizeA extends x10Test {

	public def run(): boolean {
		JGFInstrumentor.printHeader(2, 0);
		var smm: JGFSparseMatmultBench = new JGFSparseMatmultBench();
		smm.JGFrun(0);
		return true;
	}

	public static def main(var args: Rail[String]): void {
		new JGFSparseMatmultBenchSizeA().execute();
	}
}
