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
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/
import sparsematmult.*;
import jgfutil.*;
import harness.x10Test;

public class JGFSparseMatmultBenchSizeA extends x10Test {

	public boolean run() {
		JGFInstrumentor.printHeader(2, 0);
		JGFSparseMatmultBench smm = new JGFSparseMatmultBench();
		smm.JGFrun(0);
		return true;
	}

	public static void main(String[] args) {
		new JGFSparseMatmultBenchSizeA().execute();
	}
}

