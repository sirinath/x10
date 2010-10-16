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

import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.AscriptionVisitor;
import polyglot.visit.CFGBuilder;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import x10.types.Context;
import x10.types.SemanticException;
import x10.types.Type;
import x10.types.TypeSystem;
import x10.types.Context;
import x10.types.X10NamedType;
import x10.types.TypeSystem;

/**
 * @author Philippe Charles
 * @author vj
 */
public class Atomic_c extends Stmt_c 
implements Atomic {
	
	public Stmt body;
	
	public Atomic_c(Position p, Expr place, Stmt body) {
		super(p);
		this.body = body;
	}
	
	public Atomic_c(Position p) {
		super(p);
	}
	
	/* (non-Javadoc)
	 * @see x10.ast.Future#body()
	 */
	public Stmt body() {
		return body;
	}
	
	/** Set the body of the statement. */
	public Atomic body(Stmt body) {
		Atomic_c n = (Atomic_c) copy();
		n.body = body;
		return n;
	}
	/** Reconstruct the statement. */
	protected Atomic reconstruct( Stmt body ) {
		if ( body != this.body ) {
			Atomic_c n = (Atomic_c) copy();
			n.body = body;
			return n;
		}
		
		return this;
	}
	
	/** Visit the children of the statement. */
	public Node visitChildren( NodeVisitor v ) {
		Stmt body = (Stmt) visitChild(this.body, v);
		return reconstruct(body);
	}
	
	/** Type check the statement. */
	public Node typeCheck(ContextVisitor tc) throws SemanticException {		
		/*
		 if (! ts.isSubtype(expr.type(), ts.Object()) ) {
		 throw new SemanticException(
		 "Cannot synchronize on an expression of type \"" +
		 expr.type() + "\".", expr.position());
		 }
		 */
		return this;
	}
	
	public Context enterScope(Context c) {
		Context cc = (Context) super.enterScope(c);
		 cc = cc.pushAtomicBlock();
		return cc;
		    
	}
	public String toString() {
		return "atomic " + body;
	}
	
	/** Write the statement to an output file. */
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.write("atomic ");
		printSubStmt(body, w, tr);
	}
	
	/**
	 * Return the first (sub)term performed when evaluating this
	 * term.
	 */
	public Term firstChild() {
		return body;
	}
	
	/**
	 * Visit this term in evaluation order.
	 */
	public <S> List<S> acceptCFG(CFGBuilder v, List<S> succs) {
		v.push(this).visitCFG(body, this, EXIT);
		return succs;
	}
	
}
