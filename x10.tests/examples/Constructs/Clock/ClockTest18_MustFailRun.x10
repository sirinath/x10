/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * It is not allowed to transmit a clock c to a subactivity,
 * after the current activity has resumed the clock c,
 * until the current activity has executed next.
 * Doing so will result in a run-time error.
 * TODO: rename to ClockTest18 and check for exception
 *
 * @author kemal, 6/2005
 */
public class ClockTest18_MustFailRun extends x10Test {

	public def run(): boolean = {
		/*A0*/
		val c0: Clock = Clock.make();
		var x: X = new X();
		// f0 does not transmit clocks to subactivity
		var f0: foo = new foo() {
			public def apply(): void = {
				/* Activity A3 */
				async {
					System.out.println("#A3: hello from A3");
				}
			}
		};
		// f1 transmits clock c0 to subactivity
		var f1: foo = new foo() {
			public def apply(): void = {
				/*Activity A2*/
				async clocked(c0) {
					System.out.println("#A2 before resume");
					c0.resume();
					System.out.println("#A2 before next");
					next;
					System.out.println("#A2 after next");
				}
			}
		};

		var fooArray: Rail[foo] = [f0,f1];
		System.out.println("#A0 before resume");
		c0.resume();
		System.out.println("#A0 before spawning A3");
		Y.test(fooArray(x.zero()));
		System.out.println("#A0 before spawning A2");
		Y.test(fooArray(x.one()));
		System.out.println("#A0 before spawning A1");
		async clocked(c0) { System.out.println("#A1: hello from A1"); }
		System.out.println("#A0 before next");
		next;
		System.out.println("#A0 after next");

		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new ClockTest18_MustFailRun().execute();
	}

	/**
	 * A class to invoke a 'function pointer'
	 */
	static class Y {
		static def test(var f: foo): void = {
			f.apply(); // it is hard to determine what f does at compile time
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
		public  def apply(): void;
	}

	/**
	 * Dummy class to make static memory disambiguation difficult
	 * for a typical compiler
	 */
	static class X {
		public var z: Rail[int] = [1,0];
		def zero(): int = { return z(z(z(1))); /* that is a 0 */ }
		def one(): int = { return z(z(z(0))); /* that is a 1 */ }
		def modify(): void = { z(0) += 1; }
	}
}
