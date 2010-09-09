/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Minimal test for atomic.  If the atomic code is not executed atomically
 * the other activity can check that the value difference is not N.
 */
public class AtomicTest extends x10Test {

	long val = 0;
	static final long N = 1000;
	long startCount = 0;
	long endCount = 0;

	public boolean run() {
		boolean b; // temp
		async(this) {
			atomic {
				startCount = val;
				for (int i = 0; i < N; i++) val++;
				endCount = val;
			}
		}
		for (long i = 0; i < N*100; i++) {
			atomic { val = i; b = (endCount != 0); }
			if (b) break;
		}
		// need a memory fence here
		atomic { b = (startCount + N == endCount); }
		return b;
	}

	public static void main(String[] args) {
		new AtomicTest().execute();
	}
}

