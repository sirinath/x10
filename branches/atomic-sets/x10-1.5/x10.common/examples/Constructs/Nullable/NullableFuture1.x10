/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * When nullable future T x is not null at run time,
 * ((future T)x) should not cause exception
 * and ((future T).x).force() should return a T.
 */
public class NullableFuture1 extends x10Test {
	public boolean run() {
		nullable<future<int>> x;
		if (X.t()) {
			x = future { 42 };
		} else {
			x = null;
		}
		return x != null && ((future<int>)x).force() == 42;
	}

	public static void main(String[] args) {
		new NullableFuture1().execute();
	}

	static class X {
		public static boolean t() { return true; }
	}
}

