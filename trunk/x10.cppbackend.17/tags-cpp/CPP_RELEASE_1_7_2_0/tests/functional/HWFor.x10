public class HWFor {
	static final String HW = "Hello, World!";
	static final region R = [0:12];
	static final char[.] chars = new char[R] (point p[i]) { return HW.charAt(i); };
	public static void main(String[] a) {
		for (point p : chars) {
			System.out.println("" + chars[p]);
		}
		System.out.println();
	}
}

//@@X101X@@========== X10/C++ TEST HARNESS ==========<<START>>
//@@X101X@@VER@@0.1.0 [POLYGLOT 2.3.0]
//@@X101X@@TSTAMP@@20082605.110808
//@@X101X@@TCASE@@HWFor
//@@X101X@@VCODE@@FAIL_BUILD
//@@X101X@@TOUT@@0 60
//@@X101X@@SRCS@@HWFor.cc
//@@X101X@@========== X10/C++ TEST HARNESS ==========<<END>>
