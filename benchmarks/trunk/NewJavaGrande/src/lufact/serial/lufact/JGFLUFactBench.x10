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
package lufact.serial.lufact;

import jgfutil.*;

public class JGFLUFactBench extends Linpack implements JGFSection2 {

	private var size: int;
	 private var datasizes: Array[int] = [ 500, 1000, 2000 ];
	// private int datasizes[] = { 50, 1000, 2000 };
	public def JGFsetsize(var size: int): void {
		this.size = size;
	}

	public def JGFinitialise(): void {
		n = datasizes(size);
		Console.OUT.println("ATTENTION: Running with smaller size (" + n + " instead of 500)");
		ldaa = n;
		lda = ldaa + 1;

		var RR: Region = (0..(ldaa-1))*(0..(lda-1));
		var R: Region = 0..(ldaa-1);
		a = DistArray.make[double](Dist.makeConstant(RR, here));
		b = DistArray.make[double](Dist.makeConstant(R, here));
		x = DistArray.make[double](Dist.makeConstant(R, here));
		ipvt = DistArray.make[int](Dist.makeConstant(R, here));

		var nl: long = n as long;   //avoid integer overflow
		ops = (2.0*(nl*nl*nl))/3.0 + 2.0*(nl*nl);

		norma = matgen(a, lda, n, b);
	}

	public def JGFkernel(): void {
		JGFInstrumentor.startTimer("Section2:LUFact:Kernel");

		info = dgefa(a, lda, n, ipvt);
		dgesl(a, lda, n, ipvt, b, 0);

		JGFInstrumentor.stopTimer("Section2:LUFact:Kernel");
	}

	public def JGFvalidate(): void {
		var i: int;
		var eps: double;
		var residn: double;
		val ref: Array[double] = [ 6.0, 12.0, 20.0 ];

		for (i = 0; i < n; i++) {
			x(i) = b(i);
		}
		norma = matgen(a, lda, n, b);
		for (i = 0; i < n; i++) {
			b(i) = -b(i);
		}

		dmxpy(n, b, n, lda, x, a);
		resid = 0.0;
		normx = 0.0;
		for (i = 0; i < n; i++) {
			resid = (resid > abs(b(i))) ? resid : abs(b(i));
			normx = (normx > abs(x(i))) ? normx : abs(x(i));
		}

		eps =  epslon(1.0 as double);
		residn = resid/( n*norma*normx*eps );

		if (residn > ref(size)) {
			Console.OUT.println("Validation failed");
			Console.OUT.println("Computed Norm Res = " + residn);
			Console.OUT.println("Reference Norm Res = " + ref(size));
			throw new Error("Validation failed");
		}
	}

	public def JGFtidyup(): void {
		// Make sure large arrays are gc'd.
		/*a = null;  // X10: avoid nullable!
		  b = null;
		  x = null;
		  ipvt = null; */
		//System.gc();
	}

	public def JGFrun(var size: int): void {
		JGFInstrumentor.addTimer("Section2:LUFact:Kernel", "Mflops", size);

		JGFsetsize(size);
		JGFinitialise();
		JGFkernel();
		JGFvalidate();
		JGFtidyup();

		JGFInstrumentor.addOpsToTimer("Section2:LUFact:Kernel", ops/1.0e06);
		JGFInstrumentor.printTimer("Section2:LUFact:Kernel");
	}
}
