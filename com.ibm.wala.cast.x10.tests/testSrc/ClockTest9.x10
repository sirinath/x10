//OPTIONS: -PLUGINS=x10.klock.plugin.KlockPlugin
//CLASSPATH: ../classes/klock.jar
/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
// Automatically generated by the command
// m4 ClockTest9.m4 > ClockTest9.x10
// Do not edit

/**
 * Nested barriers test.
 *
 * N outer activities each
 * create M inner activities that do
 * barrier syncs. Then the outer activities do a barrier sync
 * and check the results.
 *
 * @author kemal 4/2005
 */
public class ClockTest9 {

	const int N = 8;
	const int M = 8;
	final int[.] val = new int[[0:N-1]];

	public boolean run() {
		finish async {
			final clock c = clock.factory.clock();

			// outer barrier loop
			foreach (point [i]: [0:N-1]) clocked(c) {
				foreachBody(i, c);
			}
		}
		return true;
	}

	void foreachBody(final int i, final clock c) {
		async(here) clocked(c) finish async(here) {
			final clock d = clock.factory.clock();

			// inner barrier loop
			foreach (point [j]: [0:M-1]) clocked(d) {
				foreachBodyInner(i, j, d);
			}
		}
		System.out.println("#0a i = "+i);
		next;
		// at this point each val[k] must be 0
		//async(here) clocked(c) finish async(here) for (point [k]: val) chk(val[k] == 0);
		System.out.println("#0b i = "+i);
		next;
	}

	void foreachBodyInner(final int i, final int j, final clock d) {
		// activity i, j increments val[i] by j
		async(here) clocked(d) finish async(here) { atomic val[i] += j; }
		System.out.println("#1 i = "+i+" j = "+j);
		next;
		// val[i] must now be SUM(j = 0 to M-1)(j)
		async(here) clocked(d) finish async(here) { int tmp; atomic tmp = val[i];
			// chk(tmp == M*(M-1)/2);
			 }
		System.out.println("#2 i = "+i+" j = "+j);
		next;
		// decrement val[i] by the same amount
		async(here) clocked(d) finish async(here) { atomic val[i] -= j; }
		System.out.println("#3 i = "+i+" j = "+j);
		next;
		// val[i] should be 0 by now
	}

	public static void main(String[] args) {
		new ClockTest9().run();
	}
}

