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
package moldyn.serial;

import moldyn.serial.moldyn.*;
import jgfutil.*;
import harness.x10Test;

/**
 * Moldyn ported to x10. Sequential version.
 *
 * @author kemal 3/2005
 */
public class JGFMolDynBenchSizeA extends x10Test {

	public def run(): boolean {
		JGFInstrumentor.printHeader(3, 0);
		var mold: JGFMolDynBench = new JGFMolDynBench();
		mold.JGFrun(0);
		return true;
	}

	public static def main(var args: Rail[String]): void {
		new JGFMolDynBenchSizeA().execute();
	}
}
