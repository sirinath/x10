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

import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.ast.Stmt_c;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import x10.types.X10Context;
import x10.types.X10TypeSystem;

/** The concrete class implementing the X10 construct await (c);
 * TODO: fill out the methods and the passes.
 * Typechecking rules: 
 * (1) expr must be a boolean condition.
 * (2) Can optimize await( true ); to the empty statement.
 * (3) Can optimize await( false ); to the DEADLOCK statement. 
 * (4) Condition must be pure -- its evaluation cannot cause side-effect.
 * (4) Pragmatic restriction in 0.5: Condition should check only a single variable. condition must be pure.
 * @author vj
 *
 * 
 */
public class Await_c extends Stmt_c implements Await {
	private Expr expr_;

	/**
	 * @param pos
	 */
	public Await_c(Position pos) {
		super(pos);
	}

	public Await_c(Position pos, Expr expr) {
        super( pos );
        this.expr_ = expr;
    }
	/* (non-Javadoc)
	 * @see polyglot.ast.Term#entry()
	 */
	public Term firstChild() {
		// TODO Auto-generated method stub
		return expr_;
	}

	/* (non-Javadoc)
	 * @see polyglot.ast.Term#acceptCFG(polyglot.visit.CFGBuilder, java.util.List)
	 */
	public List acceptCFG(CFGBuilder v, List succs) {
		v.visitCFG(expr_, this, EXIT);
		return succs;
	}

	/** Return a copy of this node with this.expr equal to the given expr.
	 * @see x10.ast.Await#expr(polyglot.ast.Expr)
	 */
	public Await expr( Expr expr ) {
			Await_c n = (Await_c) copy();
			n.expr_ = expr;
			return n;
	}
	
	public Expr expr() {
	    return expr_;
	}
	
	  public Node typeCheck(ContextVisitor tc) throws SemanticException {
    	X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
    	
    	if (! ts.isSubtype(expr_.type(), ts.Boolean(), tc.context()) )
    		throw new SemanticException("The argument to await, " + expr_ +", must be of type boolean.", position());
    	X10Context c = (X10Context) tc.context();
    	if (c.inNonBlockingCode())
    		throw new SemanticException("The await statement cannot be used in nonblocking code.", position());
    	return super.typeCheck(tc);
    }


	/** Visit the children of the statement. */
	public Node visitChildren( NodeVisitor v ) {
		Expr expr = (Expr) visitChild(this.expr_, v);
		return expr(expr);
	}
}
