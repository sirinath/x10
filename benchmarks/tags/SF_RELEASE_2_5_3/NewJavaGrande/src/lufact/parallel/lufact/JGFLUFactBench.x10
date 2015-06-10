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
package lufact.parallel.lufact;

import jgfutil.*;


/**
 * Ported to x10 March 18th 2005
 * @author ahk
 * @author cmd
 */
public class JGFLUFactBench extends Linpack implements JGFSection2 {

	private var size: int;
	//private int datasizes[] = { 150, 1000, 2000 };
	private var datasizes: Array[int] = [ 50, 1000, 2000 ];
	public def JGFsetsize(var size: int): void {
		this.size = size;
	}

	public def JGFinitialise(): void {
		n = datasizes(size);
		Console.OUT.println("ATTENTION: Running with smaller size (" + n + " instead of 500)");
		ldaa = n;
		lda = ldaa + 1;

		var vectorRegion: Region = 0..ldaa;
		var rectangularRegion: Region = (0..ldaa)*(0..lda);
		var slimRegion: Region = (0..0)*(0..lda); //fake out because we don't support array sections
		var rectangular_distribution: Dist = Dist.makeConstant(rectangularRegion, here); // distmakeBlockCyclic(rectangularRegion, lda+1);

		a = DistArray.make[double](rectangular_distribution);
		b = DistArray.make[double](Dist.makeConstant(slimRegion, here));
		x = DistArray.make[double](Dist.makeConstant(slimRegion, here));
		ipvt = new Array[int](ldaa);

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
			x(0, i) = b(0, i);
		}
		norma = matgen(a, lda, n, b);
		for (i = 0; i < n; i++) {
			b(0, i) = -b(0, i);
		}

		dmxpy(n, b, n, lda, x, a);
		resid = 0.0;
		normx = 0.0;
		for (i = 0; i < n; i++) {
			resid = (resid > abs(b(0, i))) ? resid : abs(b(0, i));
			normx = (normx > abs(x(0, i))) ? normx : abs(x(0, i));
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

		/* CMD
		* this causes problems in X10, and strictly spreaking, is
		* unnecessary

		a = null;
		b = null;
		x = null;
		ipvt = null;
		System.gc();
		*/
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
