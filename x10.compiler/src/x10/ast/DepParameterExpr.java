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

package x10.ast;

import java.util.List;

import polyglot.ast.Ambiguous;
import polyglot.ast.Expr;
import polyglot.ast.Formal;
import polyglot.ast.TypeNode;
import polyglot.types.Ref;
import x10.types.constraints.CConstraint;
import x10.types.constraints.TypeConstraint;

/**
 * @author vj Jan 9, 2005
 * 
 * [T](e){x:T; c}
 */
public interface DepParameterExpr extends Ambiguous {
	
	/** Add the place clause self.at(here) to the conditions
	 * unless the conditions already have a place clause. 
	 * 
	 * This must be called only from within the parser.
	 * @param e
	 */
	void addPlaceClauseIfNeeded();
	
	List<Formal> formals();
	DepParameterExpr formals(List<Formal> formals);

	List<Expr> condition();
	DepParameterExpr condition(List<Expr> cond);

	Ref<CConstraint> valueConstraint();
	DepParameterExpr valueConstraint(Ref<CConstraint> c);
	
	Ref<TypeConstraint> typeConstraint();
	DepParameterExpr typeConstraint(Ref<TypeConstraint> c);
}
