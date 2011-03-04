/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import montecarlo.*;

import jgfutil.*;
import harness.x10Test;

/**
 * X10 port of montecarlo benchmark from Section 2 of Java Grande Forum Benchmark Suite (Version 2.0).
 *
 * Single-place, multi-threaded version.
 * @author vj
 */
public class JGFMonteCarloBenchSizeA extends x10Test {

	public boolean run() {
		JGFInstrumentor.printHeader(3, 0);
		JGFMonteCarloBench mc = new JGFMonteCarloBench();
		mc.JGFrun(0);
		return true;
	}

	public static void main(String[] args) {
		new JGFMonteCarloBenchSizeA().execute();
	}
}

