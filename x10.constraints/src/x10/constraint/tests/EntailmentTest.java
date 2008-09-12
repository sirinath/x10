package x10.constraint.tests;

import junit.framework.TestCase;
import x10.constraint.XConstraint;
import x10.constraint.XConstraint_c;
import x10.constraint.XFailure;
import x10.constraint.XTerm;
import x10.constraint.XTerms;
import x10.constraint.XVar;

public class EntailmentTest extends TestCase {
	public EntailmentTest() {
		super("EntailmentTest");
	}
	XTerm zero = XTerms.makeLit(new Integer(1));
	XTerm one = XTerms.makeLit(new Integer(1));
	XTerm two = XTerms.makeLit(new Integer(2));
	XVar v0 = XTerms.makeLocal(XTerms.makeName("v0"));
	XVar v1 = XTerms.makeLocal(XTerms.makeName("v1"));
	XVar v2 = XTerms.makeLocal(XTerms.makeName("v2"));

	/**
	 * v0=v1,v1=v2 |- v0=v2
	 * @throws Throwable
	 */
	public void test1() throws Throwable {
		XConstraint c = new XConstraint_c();
		c.addBinding(v0, v1);
		c.addBinding(v1, v2);

		XConstraint d = new XConstraint_c();
		d.addBinding(v0, v2);
		assertTrue(c.entails(d));
	}
	
	/**
	 * v0=v1 |- exists x. x=v2
	 * @throws Throwable
	 */
	public void test2() throws Throwable {
		XConstraint c = new XConstraint_c();
		c.addBinding(v0,v1);
		
		XConstraint d = new XConstraint_c();
		XVar x = d.genEQV(XTerms.makeName("x0"), true);
		d.addBinding(x, v2);
		
		//XVar x1 = c0.genEQV(XTerms.makeName("x1"), true);
		//XTerm x1f = XTerms.makeField(x1, XTerms.makeName(new Object(), "f"));
		assertTrue(c.entails(d));
	}
	
	/**
	 * v0=v1 |- exists x, y. x=y
	 * @throws Throwable
	 */
	public void test3() throws Throwable {
		XConstraint c = new XConstraint_c();
		c.addBinding(v0,v1);
		
		XConstraint d = new XConstraint_c();
		XVar x = d.genEQV(XTerms.makeName("x"), true);
		XVar y = d.genEQV(XTerms.makeName("y"), true);
		d.addBinding(x, y);
		
		//XVar x1 = c0.genEQV(XTerms.makeName("x1"), true);
		//XTerm x1f = XTerms.makeField(x1, XTerms.makeName(new Object(), "f"));
		assertTrue(c.entails(d));
	}
	
	/**
	 * v0=v1 |- v0.f=v1.f
	 * @throws Throwable
	 */
	public void test4() throws Throwable {
		XConstraint c = new XConstraint_c();
		c.addBinding(v0,v1);
		
		XConstraint d = new XConstraint_c();
		XTerm v0f = XTerms.makeField(v0, XTerms.makeName(Object.class, "f"));
		XTerm v1f = XTerms.makeField(v1, XTerms.makeName(Object.class, "f"));
		d.addBinding(v0f, v1f);
		
		//XVar x1 = c0.genEQV(XTerms.makeName("x1"), true);
		boolean result = c.entails(d);
		assertTrue(result);
	}
	/**
	 * v0=v0.f |- v1=v2  (antecedent is false)
	 * @throws Throwable
	 */
	public void test5() throws Throwable {
		XConstraint c = new XConstraint_c();
		try {
			XTerm v0f = XTerms.makeField(v0, XTerms.makeName(Object.class, "f"));
			c.addBinding(v0f, v0);
		} catch (XFailure f) {
			c.setInconsistent();
		}

		XConstraint d = new XConstraint_c();
		d.addBinding(v1,v2);

		//XVar x1 = c0.genEQV(XTerms.makeName("x1"), true);
		boolean result = c.entails(d);
		assertTrue(result);
		
	}
	public void test6() throws Throwable {
		XConstraint c = new XConstraint_c();
		

		XConstraint d = new XConstraint_c();
		XVar x1 = d.genEQV(XTerms.makeName("x1"), true);
		d.addBinding(v1,x1);
		d.addBinding(v2,x1);

		//XVar x1 = c0.genEQV(XTerms.makeName("x1"), true);
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	public void test7() throws Throwable {
		XConstraint c = new XConstraint_c();
		

		XConstraint d = new XConstraint_c();
		XVar x1 = d.genEQV(XTerms.makeName("x1"), true);
		XVar x2 = d.genEQV(XTerms.makeName("x2"), true);
		d.addBinding(v1,x1);
		d.addBinding(x2,x1);
		d.addBinding(v2,x2);

		//XVar x1 = c0.genEQV(XTerms.makeName("x1"), true);
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	public void test8() throws Throwable {
		XConstraint c = new XConstraint_c();
		

		XConstraint d = new XConstraint_c();
		XVar x1 = d.genEQV(XTerms.makeName("x1"), true);
		XVar x2 = d.genEQV(XTerms.makeName("x2"), true);
		d.addBinding(x2,x1);
		d.addBinding(v1,x1);
		d.addBinding(v2,x2);

		//XVar x1 = c0.genEQV(XTerms.makeName("x1"), true);
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	/**
	 *  |- exists x1. x1=v1, x1.f=v2 -- should fail
	 * @throws Throwable
	 */
	public void test9() throws Throwable {
		XConstraint c = new XConstraint_c();
		
		XConstraint d = new XConstraint_c();
		XVar x1 = d.genEQV(XTerms.makeName("x1"), true);
		XTerm x1f = XTerms.makeField(x1, XTerms.makeName(Object.class, "f"));
		d.addBinding(v1,x1);
		d.addBinding(v2,x1f);

		//XVar x1 = c0.genEQV(XTerms.makeName("x1"), true);
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	/**
	 *  |- exists x1 x2. x1=v1, x2=x1.f, x2=v2 -- should fail
	 * @throws Throwable
	 */
	public void test10() throws Throwable {
		XConstraint c = new XConstraint_c();
		

		XConstraint d = new XConstraint_c();
		XVar x1 = d.genEQV(XTerms.makeName("x1"), true);
		XVar x2 = d.genEQV(XTerms.makeName("x2"), true);
		XTerm x1f = XTerms.makeField(x1, XTerms.makeName(Object.class, "f"));
		d.addBinding(v1,x1);
		d.addBinding(x2,x1f);
		d.addBinding(x2,v2);

		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	/**
	 *  |- exists x1 x2. x1=v1, x2=v1.f, x2=v2.f -- should fail
	 * @throws Throwable
	 */
	public void test11() throws Throwable {
		XConstraint c = new XConstraint_c();
		

		XConstraint d = new XConstraint_c();
		XVar x1 = d.genEQV(XTerms.makeName("x1"), true);
		XVar x2 = d.genEQV(XTerms.makeName("x2"), true);
		XTerm v1f = XTerms.makeField(v1, XTerms.makeName(Object.class, "f"));
		XTerm v2f = XTerms.makeField(v2, XTerms.makeName(Object.class, "f"));
		d.addBinding(v1,x1);
		d.addBinding(x2,v1f);
		d.addBinding(x2,v2f);

		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	/**
	 *   exists x1 x2. x1=v1, x2=v1.f, x2=v2.f |- v1=v2 -- should fail
	 * @throws Throwable
	 */
	public void test12() throws Throwable {
		XConstraint c = new XConstraint_c();
		XVar x1 = c.genEQV(XTerms.makeName("x1"), true);
		XVar x2 = c.genEQV(XTerms.makeName("x2"), true);
		XTerm v1f = XTerms.makeField(v1, XTerms.makeName(Object.class, "f"));
		XTerm v2f = XTerms.makeField(v2, XTerms.makeName(Object.class, "f"));
		c.addBinding(v1,x1);
		c.addBinding(x2,v1f);
		c.addBinding(x2,v2f);
		
		XConstraint d = new XConstraint_c();
		d.addBinding(v1,v2);
		
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	
}
