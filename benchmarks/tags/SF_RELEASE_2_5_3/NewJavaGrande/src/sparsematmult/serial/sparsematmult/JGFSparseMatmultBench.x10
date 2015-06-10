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
package sparsematmult.serial.sparsematmult;

import jgfutil.*;
import x10.util.*;

public class JGFSparseMatmultBench extends SparseMatmult implements JGFSection2 {

	private var size: int;
	private static val RANDOM_SEED: long = 10101010;

	private static val datasizes_M: Array[int] = [ 100, 100000, 500000 ];
	private static val datasizes_N: Array[int] = [ 100, 100000, 500000 ];
	private static val datasizes_nz: Array[int] = [ 500, 500000, 2500000 ];
	private static val SPARSE_NUM_ITER: int = 200;

	val R: Random = new Random(RANDOM_SEED);

	var x: Array[double];
	var y: Array[double];
	var vall: Array[double];
	var col: Array[int];
	var row: Array[int];

	public def JGFsetsize(var size: int): void {
		this.size = size;
	}

	public def JGFinitialise(): void {
		x = RandomVector(datasizes_N(size), R);
		y = new Array[double](datasizes_M(size));

		vall = new Array[double](datasizes_nz(size));
		col = new Array[int](datasizes_nz(size));
		row = new Array[int](datasizes_nz(size));

		for (var i: int = 0; i<datasizes_nz(size); i++) {

			// generate random row index (0, M-1)
			row(i) = Math.abs(R.nextInt()) % datasizes_M(size);

			// generate random column index (0, N-1)
			col(i) = Math.abs(R.nextInt()) % datasizes_N(size);

			vall(i) = R.nextDouble();
		}
	}

	public def JGFkernel(): void {
		SparseMatmult.test(y, vall, row, col, x, SPARSE_NUM_ITER);
	}

	public def JGFvalidate(): void {
		var refval: Array[double] = [ 0.1436496372119012, 150.0130719633895, 749.5245870753752 ];
		var dev: double = Math.abs(SparseMatmult.ytotal.value - refval(size));
		if (dev > 1.0e-12) {
			Console.OUT.println("Validation failed");
			Console.OUT.println("ytotal = " + ytotal.value + "  " + dev + "  " + size);
			throw new Error("Validation failed");
		}
	}

	public def JGFtidyup(): void {
		//System.gc();
	}

	public def JGFrun(var size: int): void {
		JGFInstrumentor.addTimer("Section2:SparseMatmult:Kernel", "Iterations", size);

		JGFsetsize(size);
		JGFinitialise();
		JGFkernel();
		JGFvalidate();
		JGFtidyup();

		JGFInstrumentor.addOpsToTimer("Section2:SparseMatmult:Kernel", (SPARSE_NUM_ITER) as double);

		JGFInstrumentor.printTimer("Section2:SparseMatmult:Kernel");
	}

	private static def RandomVector(var N: int, var R: Random): Array[double] {
		var A: Array[double] = new Array[double](N);

		for (var i: int = 0; i < N; i++)
			A(i) = R.nextDouble() * 1e-6;

		return A;
	}
}
