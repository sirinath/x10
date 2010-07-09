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


/**
 * Represents t1 - t2
 * 
 * Intended as placeholder for special treatment for propagation rules and simplification rules
 * for -.
 * @author vj
 *
 */
public class XMinus extends XFormula {

	public XMinus(XTerm... args) {
		super(XTerms.minusName, args);
	}
	public String toString() {
		return arguments.get(0) + "-" + arguments.get(1);
	}
}

