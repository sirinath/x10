/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

package x10.constraint.tests;

//import polyglot.ext.x10.types.X10TypeMixin;
import junit.framework.TestCase;
import x10.constraint.XConstraint;
import x10.constraint.XFailure;
import x10.constraint.XField;
import x10.constraint.XTerm;
import x10.constraint.XTerms;
import x10.constraint.XVar;

public class EntailmentTest extends TestCase {
	public EntailmentTest() {
		super("EntailmentTest");
	}
	XTerm zero = XTerms.makeLit(new Integer(0));
	XTerm one = XTerms.makeLit(new Integer(1));
	XTerm two = XTerms.makeLit(new Integer(2));
	XVar v0 = XTerms.makeUQV("v0");
	XVar v1 = XTerms.makeUQV("v1");
	XVar v2 = XTerms.makeUQV("v2");

	/**
	 * v0=v1,v1=v2 |- v0=v2
	 * @throws Throwable
	 */
	public void test1() throws Throwable {
		XConstraint c = new XConstraint();
		c.addBinding(v0, v1);
		c.addBinding(v1, v2);

		XConstraint d = new XConstraint();
		d.addBinding(v0, v2);
		assertTrue(c.entails(d));
	}
	
	/**
	 * v0=v1 |- exists x. x=v2
	 * @throws Throwable
	 */
	public void test2() throws Throwable {
		XConstraint c = new XConstraint();
		c.addBinding(v0,v1);
		
		XConstraint d = new XConstraint();
		XVar x = XTerms.makeEQV();
		d.addBinding(x, v2);
		
		//XVar x1 = c0.genEQV(XTerms.makeUQV("x1"), true);
		//XTerm x1f = XTerms.makeField(x1, XTerms.makeUQV(new Object(), "f"));
		assertTrue(c.entails(d));
	}
	
	/**
	 * v0=v1 |- exists x, y. x=y
	 * @throws Throwable
	 */
	public void test3() throws Throwable {
		XConstraint c = new XConstraint();
		c.addBinding(v0,v1);
		
		XConstraint d = new XConstraint();
		XVar x = XTerms.makeEQV();
		XVar y = XTerms.makeEQV();
		d.addBinding(x, y);
		
		//XVar x1 = c0.genEQV(XTerms.makeUQV("x1"), true);
		//XTerm x1f = XTerms.makeField(x1, XTerms.makeUQV(new Object(), "f"));
		assertTrue(c.entails(d));
	}
	
	/**
	 * v0=v1 |- v0.f=v1.f
	 * @throws Throwable
	 */
	public void test4() throws Throwable {
		XConstraint c = new XConstraint();
		c.addBinding(v0,v1);
		
		XConstraint d = new XConstraint();
		XTerm v0f = XTerms.makeField(v0, "f");
		XTerm v1f = XTerms.makeField(v1, "f");
		d.addBinding(v0f, v1f);
		
		//XVar x1 = c0.genEQV(XTerms.makeUQV("x1"), true);
		boolean result = c.entails(d);
		assertTrue(result);
	}
	/**
	 * v0=v0.f |- v1=v2  (antecedent is false)
	 * @throws Throwable
	 */
	public void test5() throws Throwable {
		XConstraint c = new XConstraint();
		
			XTerm v0f = XTerms.makeField(v0, "f");
			c.addBinding(v0f, v0);
			assertFalse(c.consistent());
		

		XConstraint d = new XConstraint();
		d.addBinding(v1,v2);

		//XVar x1 = c0.genEQV(XTerms.makeUQV("x1"), true);
		boolean result = c.entails(d);
		assertTrue(result);
		
	}
	/**
	 *  |/= exists x1. v1=x1, v2=x2
	 * @throws Throwable
	 */
	public void test6() throws Throwable {
		XConstraint c = new XConstraint();
		

		XConstraint d = new XConstraint();
		XVar x1 = XTerms.makeEQV();
		d.addBinding(v1,x1);
		d.addBinding(v2,x1);

		//XVar x1 = c0.genEQV(XTerms.makeUQV("x1"), true);
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	public void test7() throws Throwable {
		XConstraint c = new XConstraint();
		

		XConstraint d = new XConstraint();
		XVar x1 = XTerms.makeEQV();
		XVar x2 = XTerms.makeEQV();
		d.addBinding(v1,x1);
		d.addBinding(x2,x1);
		d.addBinding(v2,x2);

		//XVar x1 = c0.genEQV(XTerms.makeUQV("x1"), true);
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	public void test8() throws Throwable {
		XConstraint c = new XConstraint();
		

		XConstraint d = new XConstraint();
		XVar x1 = XTerms.makeEQV();
		XVar x2 = XTerms.makeEQV();
		d.addBinding(x2,x1);
		d.addBinding(v1,x1);
		d.addBinding(v2,x2);

		//XVar x1 = c0.genEQV(XTerms.makeUQV("x1"), true);
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	/**
	 *  |- exists x1. x1=v1, x1.f=v2 -- should fail
	 * @throws Throwable
	 */
	public void test9() throws Throwable {
		XConstraint c = new XConstraint();
		
		XConstraint d = new XConstraint();
		XVar x1 = XTerms.makeEQV();
		XTerm x1f = XTerms.makeField(x1, "f");
		d.addBinding(v1,x1);
		d.addBinding(v2,x1f);

		//XVar x1 = c0.genEQV(XTerms.makeUQV("x1"), true);
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	/**
	 *  |- exists x1. x1=v1, x2=x1.f, x2=v2 -- should fail
	 * @throws Throwable
	 */
	public void test10() throws Throwable {
		XConstraint c = new XConstraint();
		

		XConstraint d = new XConstraint();
		XVar x1 = XTerms.makeEQV();
		XVar x2 = XTerms.makeEQV();
		XTerm x1f = XTerms.makeField(x1, "f");
		d.addBinding(v1,x1);
		d.addBinding(x2,x1f);
		d.addBinding(x2,v2);

		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	/**
	 *  |- exists x1. x1=v1, x2=v1.f, x2=v2.f -- should fail
	 * @throws Throwable
	 */
	public void test11() throws Throwable {
		XConstraint c = new XConstraint();
		

		XConstraint d = new XConstraint();
		XVar x1 = XTerms.makeEQV();
		XVar x2 = XTerms.makeEQV();
		XTerm v1f = XTerms.makeField(v1,  "f");
		XTerm v2f = XTerms.makeField(v2, "f");
		d.addBinding(v1,x1);
		d.addBinding(x2,v1f);
		d.addBinding(x2,v2f);

		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	/**
	 *   exists x1. x1=v1, x2=v1.f, x2=v2.f |- v1=v2 --- should fail
	 * @throws Throwable
	 */
	public void test12() throws Throwable {
		XConstraint c = new XConstraint();
		XVar x1 = XTerms.makeEQV();
		XVar x2 = XTerms.makeEQV();
		XTerm v1f = XTerms.makeField(v1, "f");
		XTerm v2f = XTerms.makeField(v2, "f");
		c.addBinding(v1,x1);
		c.addBinding(x2,v1f);
		c.addBinding(x2,v2f);
		
		XConstraint d = new XConstraint();
		d.addBinding(v1,v2);
		
		boolean result = c.entails(d);
		assertFalse(result);
		
	}
	
	/**
	 *   v1=a |- exists x. x=a, x=v1
	 * @throws Throwable
	 */
	public void test13() throws Throwable {
		XConstraint c = new XConstraint();
		c.addBinding(v1,zero);
		
		
		XConstraint d = new XConstraint();
		XVar x1 = XTerms.makeEQV();
		d.addBinding(zero,x1);
		d.addBinding(v1,x1);
		
		boolean result = c.entails(d);
		assertTrue(result);
		
	}
	
	/**
         * v0 : _{self=v1} |- v0=v1
         * @throws Throwable
        
        public void test14() throws Throwable {
            final XVar v0 = XTerms.makeUQV("v0");
            final XVar v1 = XTerms.makeUQV("v1");

            final XConstraint s = new XConstraint();
            s.addBinding(v1, v0);

            XConstraint c = new XConstraint();
            c.addTerm(v0);
            
            XConstraint d = new XConstraint();
            d.addBinding(v0, v1);
            System.out.println();
            System.out.println("EntailmentTest.test14: v0 : _{self=v1} |- v0=v1");
            System.out.println("c:" + c);
            System.out.println("s:" + s);
            System.out.println("d: " + d + " ext:" + d.extConstraints());
            assertTrue(c.entails(d, s));
        }
         */
        /**
         * |- exists x. x.a=x.b
         * @throws Throwable
         */
        public void test15() throws Throwable {
           
            XConstraint c = new XConstraint();
            XConstraint d = new XConstraint();
            XVar X = XTerms.makeEQV();
            XField<String> Xa = XTerms.makeField(X, "a");
            XField<String> Xb = XTerms.makeField(X, "b");
            d.addBinding(Xa, Xb); 
            assertTrue(c.entails(d));
        }

	
}
