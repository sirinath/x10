/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Remote accesses must be flagged by the compiler.
 *
 * NEW SEMANTICS: non-local accesses cause a BadPlaceException
 * to be thrown at run time.
 * Compiler techniques can be used to reduce or remove
 * the run time checks.
 * Compiler can issue a warning when it detects that
 * a BadPlaceException will always occur or will likely
 * occur, but the error is still caught at run time.
 *
 * @author Kemal 4/2005
 */
public class AsyncTest3 extends x10Test {

	public boolean run() {
		final int[.] A = new int[dist.factory.unique()];
		chk(place.MAX_PLACES >= 2);
		chk(A.distribution[0] == here);
		chk(A.distribution[1] != here);
		final X x = new X();

		finish async(here) { A[0] += 1; }
		A[0] += 1;

		
		
			finish async(here) { A[1] += 1; }
			A[1] += 1; //  remote communication
			System.out.println("1");
			
		finish async(here) { A[x.zero()] += 1; }
		A[x.zero()] += 1;

		
		
			finish async(here) { A[0] += A[x.one()]; }
			A[0] += A[x.one()];//  remote communication
			System.out.println("2");
		

			chk(A[0] == 8 && A[1] == 2);
			System.out.println("3");
		

		return true;
	}

	public static void main(String[] args) {
		new AsyncTest3().execute();
	}

	/**
	 * Dummy class to make static memory disambiguation difficult
	 * for a typical compiler
	 */
	static class X {
		public int[] z = { 1, 0 };
		int zero() { return z[z[z[1]]]; }
		int one() { return z[z[z[0]]]; }
		void modify() { z[0]++; }
	}
}

