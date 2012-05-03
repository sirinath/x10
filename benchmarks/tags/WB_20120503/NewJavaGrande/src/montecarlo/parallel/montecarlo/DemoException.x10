/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
package montecarlo.parallel.montecarlo;

/**
 * X10 port of montecarlo benchmark from Section 2 of Java Grande Forum Benchmark Suite (Version 2.0).
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 *
 * Porting issues identified:
 */
public class DemoException extends x10.lang.Exception {

	/**
	 * Flag for selecting whether to print the stack-trace dump.
	 */
	public static val debug: boolean = true;

	/**
	 * Default constructor.
	 */
	public def this(): DemoException = {
		super();
		if (debug) {
			//printStackTrace();
		}
	}

	/**
	 * Default constructor for reporting an error message.
	 */
	public def this(var s: String): DemoException = {
		super(s);
		if (debug) {
			//printStackTrace();
		}
	}

	/**
	 * Default constructor for reporting an error code.
	 */
	public def this(var ierr: int): DemoException = {
		super(String.valueOf(ierr));
		if (debug) {
			//printStackTrace();
		}
	}
}
