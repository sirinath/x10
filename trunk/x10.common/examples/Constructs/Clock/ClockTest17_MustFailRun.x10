/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * OLD SEMANTICS:
 * Clarification of language definition needed here:
 * Whether async clocked(c) S occurs inside a finish
 * body is hard to detect at compile time.
 * E.g. if the finish body contains an indirect function invocation
 * or a library routine.
 * How should this test behave?
 *
 * Currently this test causes a deadlock.
 *
 * Tentatively declaring that this is an error
 * that must be caught at compile time (may not be possible).
 *
 * NEW SEMANTICS: Clock Use Exception such as
 *
 *  'Transmission of c (to a child) requires that I am registered with c'
 *  'Transmission of c requires that I am not between c.resume() and a next'
 *  'The immediate body of finish  can never transmit any clocks'
 *
 * are now caught at run time. The compiler
 * can remove the run time checks using static techniques,
 * and can issue warnings when it is statically detected that
 * clock use exceptions will
 * definitely occur, or will likely occur.
 *
 * Hence this file is renamed as *MustFailRun.x10
 *
 * @author kemal 5/2005
 */
public class ClockTest17_MustFailRun extends x10Test {

	public boolean run() {
		/*A0*/
		final clock c0 = clock.factory.clock();
		X x = new X();
		// f0 does not transmit clocks to subactivity
		foo f0 = new foo() {
			public void apply() {
				async {
					System.out.println("hello from finish async S");
				}
			}
		};
		// f1 transmits clocks to subactivity
		foo f1 = new foo() {
			public void apply() {
				/*Activity A1*/
				async clocked(c0) {
					System.out.println("#1 before next");
					next;
					System.out.println("#1 after next");
				}
			}
		};

		foo[] fooArray = new foo[] { f0, f1 };

		// This is invoking Y.test(f0) but not clear to a compiler
		Y.test(fooArray[x.zero()]);
		// Finish in Y.test completes and then the following executes.
		//No deadlock occurs here.
		System.out.println("#0a before next");
		next;
		System.out.println("#0a after next");

		// This is invoking Y.test(f1) but not clear to a compiler
		Y.test(fooArray[x.one()]);
		// Execution never reaches here (deadlock occurs) since:
		// A1 inside Y.test(f1) must first finish, but it
		// cannot since A0 has not executed next on clock c0 yet.
		System.out.println("#0b before next");
		next;
		System.out.println("#0b after next");

		return true;
	}

	public static void main(String[] args) {
		new ClockTest17_MustFailRun().execute();
	}

	/**
	 * A class to invoke a 'function pointer' inside of finish
	 */
	static class Y {
		static void test(foo f) {
			finish {
				f.apply(); // it is hard to determine f does an async clocked(c) S
			}
		}
	}

	/**
	 * An interface to use like a simple 'function pointer'
	 *
	 * foo f1 = new foo() { public void apply() S1 }; //assign body S1 to f1
	 *
	 * // values of free final variables of S1 are also captured in f1
	 *
	 * f1.apply(); // invoke S1 indirectly using its captured
	 *
	 * // free variables
	 */
	static interface foo {
		public void apply();
	}

	/**
	 * Dummy class to make static memory disambiguation difficult
	 * for a typical compiler
	 */
	static class X {
		public int[] z = { 1, 0 };
		int zero() { return z[z[z[1]]]; }
		int one() { return z[z[z[0]]]; }
		void modify() { z[0] += 1; }
	}
}

