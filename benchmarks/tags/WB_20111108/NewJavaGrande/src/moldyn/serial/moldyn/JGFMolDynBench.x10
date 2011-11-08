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
package moldyn.serial.moldyn;

import jgfutil.*;

/**
 * Moldyn ported to x10. Sequential version.
 *
 * @author kemal 3/2005
 */
public class JGFMolDynBench extends md implements JGFSection3 {

	//int size;

	public def this(): JGFMolDynBench = {
	}

	public def JGFsetsize(var size: int): void = {
		this.size = size;
	}

	public def JGFinitialise(): void = {
		initialise();
	}

	public def JGFapplication(): void = {
		JGFInstrumentor.startTimer("Section3:MolDyn:Run");
		runiters();
		JGFInstrumentor.stopTimer("Section3:MolDyn:Run");
	}

	public def JGFvalidate(): void = {
		var myNode: md = this;
		// double refval[] = { 1731.4306625334357, 7397.392307839352 };
		var refval: Array[double] = [ 275.97175611773514, 7397.392307839352 ];
		var dev: double = Math.abs(myNode.ek - refval(size));
		if (dev > 1.0e-10 ) {
			Console.OUT.println("Validation failed");
			Console.OUT.println("Kinetic Energy = " + myNode.ek + "  " + dev + "  " + refval(size));
			throw new Error("Validation failed");
		}
	}

	public def JGFtidyup(): void = {
		//System.gc();
	}

	public def JGFrun(var size: int): void = {
		JGFInstrumentor.addTimer("Section3:MolDyn:Total", "Solutions", size);
		JGFInstrumentor.addTimer("Section3:MolDyn:Run", "Interactions", size);

		JGFsetsize(size);

		JGFInstrumentor.startTimer("Section3:MolDyn:Total");

		JGFinitialise();
		JGFapplication();
		JGFvalidate();
		JGFtidyup();

		JGFInstrumentor.stopTimer("Section3:MolDyn:Total");

		JGFInstrumentor.addOpsToTimer("Section3:MolDyn:Run", interactions as double);
		JGFInstrumentor.addOpsToTimer("Section3:MolDyn:Total", 1);

		JGFInstrumentor.printTimer("Section3:MolDyn:Run");
		JGFInstrumentor.printTimer("Section3:MolDyn:Total");
	}
}
