/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * Created on Sep 29, 2004
 */
package polyglot.ext.x10.ast;

import java.util.List;

import polyglot.ast.Expr;
import polyglot.ast.Stmt;

/** The node constructed for the X10 construct async (P) {S}.
 * @author Christian Grothoff
 */
public interface Async extends Stmt, RemoteActivityInvocation {
    
    /** Set the Async's body */
    Async body(Stmt body);

    /** Get the body of the Async. */
    Stmt body();
    
    /** Expression */
	public List clocks();

	/** clock */
	public Clocked clocks(List clocks);
}