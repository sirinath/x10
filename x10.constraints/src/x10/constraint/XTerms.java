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

package x10.constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
   Collection of static methods to create different kinds of XTerms
   (names, vars, atomic formulas, literals, local variables, fields
   etc).
   
   @author njnystrom
   (Comments by vj 09/06/08)

 */
public class XTerms {
   
	public static final XLit NULL = new XLit(null);
	public static final XLit TRUE = new XLit(true);
	public static final XLit FALSE = new XLit(false);
	public static final XLit OPERATOR = new XLit(new Object()) { 
		public String toString() { return "o"; } 
	};

	static final XName equalsName = new XNameWrapper<String>("===");
	static final XName disEqualsName = new XNameWrapper<String>("!==");
	static final XName andName = new XNameWrapper<String>("&&&");
	static final XName notName = new XNameWrapper<String>("!!!");
	static final XName arrayAccessName = new XNameWrapper<String>("(.)");
	static final XName plusName = new XNameWrapper<String>("+");
	static final XName minusName = new XNameWrapper<String>("-");
	static final XName modName = new XNameWrapper<String>("%");

    // used in generating a new name.
	static int nextId = 0;
	
	public static final XName makeFreshName() {
	    return makeFreshName("_");
	}
	
	public static XEQV makeEQV() {
		return makeEQV(makeFreshName());
	}
	public static XEQV makeUQV() {
		return makeUQV(makeFreshName());
	}
	public static XEQV makeEQV(XName name) {
		return new XEQV(name, true);
	}
	public static XEQV makeUQV(XName name) {
		return new XEQV(name, false);
	}
	public static final XEQV makeEQV(String prefix) {
		return new XEQV(makeFreshName(prefix), true);
	}
	public static final XEQV makeUQV(String prefix) {
		return new XEQV(makeFreshName(prefix), false);
	}
	public static XEQV makeEQV(XName name, boolean hidden) {
		return new XEQV(name, hidden);
	}
	    
	public static final XLocal makeFreshLocal() {
		return makeLocal(XTerms.makeFreshName());
	}
	public static final XLocal makeFreshLocal(String prefix) {
		return makeLocal(XTerms.makeFreshName(prefix));
	}
	public static final XName makeFreshName(String prefix) {
	    return new XNameWrapper<Object>(new Object(), prefix + (nextId++));
	}
	
	public static final <T> XName makeName(T name) {
		return new XNameWrapper<T>(name);
	}
	
	public static final <T> XName makeName(T name, String s) {
		return new XNameWrapper<T>(name, s);
	}
	
	public static final XLocal makeLocal(XName name) {
		return new XLocal(name);
	}
	

	public static final XField makeField(XVar receiver, XName field) {
		return new XField(receiver, field);
	}
	
	

    /** Make and return a literal containing o. true and false are
     * interned.
     */
	public static final XLit makeLit(Object o) {
		if (o == null) return NULL;
		if (o.equals(true)) return TRUE;
		if (o.equals(false)) return FALSE;
		return new XLit(o);
	}
	
	/**
    Make and return op(terms1,..., termsn) -- an atomic formula
    with operator op and terms terms. Uses varargs.
	 */
	public static XTerm makeAtom(XName op, XTerm... terms) {
		return makeAtom(op, true, Arrays.asList(terms));
	}

	/**
    Make and return op(terms1,..., termsn) -- a function application 
    with function name op and arguments terms. Uses varargs.
	 */
	public static XTerm makeAtom(XName op, List<XTerm> terms) {
		return makeAtom(op, true, terms);
	}
	/**
       Make and return op(terms1,..., termsn) -- an expression 
       with operator op and arguments terms. If atomicFormula is true
       then this is marked as an atomicFormula, else it is considered a term 
       (a function application term).
	 */

	public static XTerm makeAtom(XName op, boolean atomicFormula, List<XTerm> terms) {
		assert op != null;
		assert terms != null;
		XFormula f = new XFormula(op, terms);
		if (atomicFormula) {
			f.markAsAtomicFormula();
		}
		return f;
	}



	/**
    Make and return op(terms1,..., termsn) -- a function application 
    with function name op and arguments terms. Uses varargs.
	 */
	public static XTerm makeTerm(XName op, XTerm... terms) {
		if (op.equals(plusName)) {
			return new XPlus(terms);
		}
		if (op.equals(minusName)) {
			return new XMinus(terms);
		}
		
		return makeAtom(op, false, Arrays.asList(terms));
	}
 
    /**
       Make and return left == right.
     */
	public static XTerm makeEquals(XTerm left, XTerm right) {
		assert left != null;
		assert right != null;
		if (left instanceof XLit && right instanceof XLit) {
		        if (left.equals(right))
		            return XTerms.TRUE;
		        else
		            return XTerms.FALSE;
		}
		return new XEquals(left, right);
	}
	
	public static XTerm makeDisEquals(XTerm left, XTerm right) {
		assert left != null;
		assert right != null;
		if (left instanceof XLit && right instanceof XLit) {
			
		        if (left.equals(right))
		            return XTerms.FALSE;
		        else
		            return XTerms.TRUE;
		}
		return new XDisEquals(left, right);
	}

    /**
       Make and return left,right -- the logical conjunction.
       left and right should be boolean terms. (not checked.)
     */
	public static XTerm makeAnd(XTerm left, XTerm right) {
		assert left != null;
		assert right != null;
		return new XAnd(left, right);
	}

    /** Make and return not (arg). arg should be a boolean term. (not
     * checked.)
     */
	public static XTerm makeNot(XTerm arg) {
		assert arg != null;
		return new XNot(arg);
	}
	/**
	 * Return the constraint true.
	 * @return
	 */
	public static XConstraint makeTrueConstraint() {
		return new XConstraint();
	}
	
}
