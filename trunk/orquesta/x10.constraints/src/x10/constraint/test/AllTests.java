package x10.constraint.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for x10.constraint.test");
		//$JUnit-BEGIN$
		suite.addTest(new FormulaTest());
		//$JUnit-END$
		return suite;
	}

}
