/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Return from a "when" should work.
 *
 * @author kemal, 5/2005
 * @author igor, 2/2006 -- renamed from Misc/Unreachable2; added another branch
 */
public class WhenReturn extends x10Test {

	int test() {
		int ret = 0;
		when (X.t()) {
			return 1;
		} or (X.t()) {
			ret = 2;
		}
		return ret;
	}

	public boolean run() {
		int x = test();
		return true;
	}

	public static void main(String[] args) {
		new WhenReturn().execute();
	}

	static class X {
		static boolean t() { return true; }
	}
}

