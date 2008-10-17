/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Tests exceptions and side effects
 * within activities spawned by a future expression.
 *
 * Checks that future { e }.force() acts as a global finish
 * on e, and throws e's  exceptions
 *
 * Events are choreographed using a sleep timer with
 * second granularity.
 *
 * @author kemal 4/2005
 */
public class FutureTest3 extends x10Test {

	public const N: int = 8;
	public const OUTOFRANGE: int = 99;

	/**
	 * Spawns subactivities that cause delayed side-effects.
	 */
	def m1(val A: Array[int], val K: int): int = {
		foreach (val (i): point in A) {
			x10.lang.Runtime.sleep(3000);
			atomic A(i) += 1;
		}
		var t: int;
		atomic t = A(K); //returns old value
		return t;
	}

	/**
	 * Spawns subactivities that cause delayed side-effects
	 * and exceptions.
	 */
	def m2(val A: Array[int], val K: int): int = {
		foreach (val p(i): point in A) {
			x10.lang.Runtime.sleep(3000);
			atomic A(i) += 1;
			atomic A(OUTOFRANGE) = -1;
		}
		var t: int;
		atomic t = A(K); //returns old value
		return t;
	}

	/**
	 * testing future with subactivities with
	 * side effects and exceptions.
	 */
	public def run(): boolean = {
		val A: Array[int] = Array.make[int](Dist.makeConstant(0..N-1, here), (point)=>0);
		val K: int = 3;
		var gotException: boolean;

		// side effect in expression

		// (need atomic here if there is sharing. x10 should support atomic { expression } )
		var r1: int = (future (here) {A(K) += 1}).force();
		System.out.println("1");
		atomic chk(A(K) == 1);
		chk(r1 == 1);

		// exception in expression
		var r2: int = -1;
		gotException = false;
		try {
			r2 = (future (here){A(OUTOFRANGE) += 1}) .force();
		} catch (var e: ArrayIndexOutOfBoundsException) {
			gotException = true;
		}
		System.out.println("2");
		chk(r2 == -1 && gotException);

		//subactivities of e must be finished
		//when future e .force() returns
		var r3: int = -1;
		gotException = false;
		try {
			r3 = (future(here) m1(A, K)) .force();
		} catch (var e: Throwable) {
			gotException = true;
		}
		System.out.println("3");
		chk(r3 == 1 && !gotException);
		// must read new values of A here
		for (val (i): point in A) System.out.println("A["+i+"] = "+A(i));
		chk(A(K) == 2);
		for (val (i): point in A) atomic chk(imp(i != K, A(i) == 1));

		//future { e }.force() must throw
		//exceptions from subactivities of e
		var r4: int = -1;
		gotException = false;
		try {
			r4 = (future(here) m2(A, K)) .force();
		} catch (var e: Throwable) {
			gotException = true;
		}
		System.out.println("4" + gotException + " r4 = " + r4);
		chk(r4 ==-1 && gotException);
		// must read new values of A here
		for (val (i): point in A) System.out.println("A["+i+"] = "+A(i));
		atomic chk(A(K) == 3);
		for (val (i): point in A) atomic chk(imp(i != K, A(i) == 2));

		//Only force() throws the exception,
		//a plain future call just spawns the expression
		val fr5  = future(here) { m2(A, K) };
		System.out.println("5");
		// must read old values of A here
		atomic chk(A(K) == 3);
		for (val (i): point in A) atomic chk(imp(i != K, A(i) == 2));
		var r5: int = -1;
		gotException = false;
		try {
			r5 = fr5.force();
		} catch (var e: Throwable) {
			gotException = true;
		}
		chk(r5 ==-1 && gotException);
		// must read new values of A here
		for (val (i): point in A) System.out.println("A["+i+"] = "+A(i));
		atomic chk(A(K) == 4);
		for (val (i): point in A) atomic chk(imp(i != K, A(i) == 3));

		return true;
	}

	/**
	 * True iff x logically implies y
	 */
	static def imp(var x: boolean, var y: boolean): boolean = {
		return (!x||y);
	}

	public static def main(var args: Rail[String]): void = {
		new FutureTest3().execute();
	}
}
