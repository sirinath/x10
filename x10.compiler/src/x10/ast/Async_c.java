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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import polyglot.ast.Expr;
import polyglot.ast.Formal;
import polyglot.ast.Node;
import polyglot.ast.Stmt;
import polyglot.ast.Stmt_c;
import polyglot.ast.Term;
import polyglot.main.Report;
import polyglot.types.Context;
import polyglot.types.Ref;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.VarDef_c;
import polyglot.types.VarDef;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.visit.AscriptionVisitor;
import polyglot.visit.CFGBuilder;
import polyglot.visit.ContextVisitor;
import polyglot.visit.FlowGraph;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.PruningVisitor;
import x10.constraint.XConstraint;
import x10.constraint.XFailure;
import x10.constraint.XTerm;
import x10.constraint.XTerms;
import x10.errors.Errors;
import x10.types.ParameterType;
import x10.types.X10Context;
import x10.types.X10MethodDef;
import x10.types.X10TypeSystem;
import x10.types.checker.PlaceChecker;
import x10.types.constraints.CConstraint;
import x10.types.constraints.XConstrainedTerm;

/**
 * Created on Oct 5, 2004
 *
 * @author Christian Grothoff
 * @author Philippe Charles
 * @author vj
 */

public class Async_c extends Stmt_c implements Async {
    /*
    For example:
    val x:Int;
    finish async { x = 42; } // "x" can either be boxed or be passed (to the async closure) by ref, because we need to assign to it. It cannot be passed by value.
    async { val y = x; } // "x" can be passed by value or be be boxed. It cannot be passed by ref because the async outlives the var "x".
     */
    public Set<VarDef> asyncInitVal = null; // used by the backend to know whether to pass a var to an async closure by ref or by value

	
	public Stmt body;
	protected List<Expr> clocks;
	protected boolean clocked; // should be equal to (clocks != null && clocks.size() > 0)

	public Async_c(Position pos, List<Expr> clocks, Stmt body) {
		super(pos);
		this.clocks = clocks;
		this.body = body;
	}
	public Async_c(Position pos, Stmt body, boolean clocked) {
		super(pos);
		this.clocked = true;
		this.body = body;
		// temporary. Needs to be initialized with clock from environment.
		this.clocks = new ArrayList<Expr>();
	}

	public Async_c(Position p) {
		super(p);
	}

	/* (non-Javadoc)
	 * @see x10.ast.Future#body()
	 */
	public Stmt body() {
		return body;
	}

	/** Expression */
	public List<Expr> clocks() {
		return this.clocks;
	}

	/** clock */
	public Clocked clocks(List<Expr> clocks) {
		Async_c n = (Async_c) copy();
		n.clocks = clocks;
		return n;
	}

	/**
	 * Set the body of the statement.
	 */
	public Async body(Stmt body) {
		Async_c n = (Async_c) copy();
		n.body = body;
		return n;
	}
	


	/** Reconstruct the statement. */
	protected Async reconstruct(List clocks, Stmt body) {
		if ( body != this.body || clocks != this.clocks) {
			Async_c n = (Async_c) copy();
			n.clocks = clocks;
			n.body = body;
			return n;
		}
		return this;
	}


	/** Visit the children of the statement. */
	public Node visitChildren(NodeVisitor v) {
		List clocks = (List) visitList(this.clocks, v);
		Stmt body = (Stmt) visitChild(this.body, v);
		return reconstruct(clocks, body);
	}

	
	
	@Override
	public Node typeCheckOverride(Node parent, ContextVisitor tc) {
	    X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
	    NodeVisitor v = tc.enter(parent, this);

	    if (v instanceof PruningVisitor) {
	        return this;
	    }


	    // now that placeTerm is set in this node, continue visiting children
	    // enterScope will ensure that placeTerm is installed in the context.

	    return null;
	}

	/**
	 * The evaluation of place and list of clocks is not in the scope of the async.
	 */
	public Context enterChildScope(Node child, Context c) {
	    if (Report.should_report(TOPICS, 5))
	        Report.report(5, "enter async scope");
	    X10Context xc = (X10Context) c;
	    if (child == this.body) {
	        X10TypeSystem ts = (X10TypeSystem) c.typeSystem();
	        X10MethodDef asyncInstance = (X10MethodDef) ts.asyncCodeInstance(c.inStaticContext());
	        if (xc.currentCode() instanceof X10MethodDef) {
	            X10MethodDef outer = (X10MethodDef) c.currentCode();
	            List<ParameterType> capturedTypes = outer.typeParameters();
	            if (!capturedTypes.isEmpty()) {
	                asyncInstance = ((X10MethodDef) asyncInstance.copy());
	                asyncInstance.setTypeParameters(capturedTypes);
	            }
	        }
	        xc = (X10Context) xc.pushCode(asyncInstance);
	    }
	    return xc;
	}

	public Node typeCheck(ContextVisitor tc) {
		X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
		X10NodeFactory nf = (X10NodeFactory) tc.nodeFactory();

		X10Context c = (X10Context) tc.context();
		if (c.inSequentialCode())
			Errors.issue(tc.job(),
			        new SemanticException("async may not be invoked in sequential code.", position()));
			
        for (Iterator i = clocks().iterator(); i.hasNext(); ) {
            Expr tn = (Expr) i.next();
            Type t = tn.type();
            if (! t.isSubtype(ts.Clock(), tc.context())) {
                Errors.issue(tc.job(),
                        new SemanticException("Type \"" + t + "\" must be x10.lang.clock.", tn.position()));
            }
        }

		return this;
	}

	
	public Type childExpectedType(Expr child, AscriptionVisitor av) {
		X10TypeSystem ts = (X10TypeSystem) av.typeSystem();
		return child.type();
	}

	public String toString() {
		return "async  { ... }";
	}

	/** Write the statement to an output file. */
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.write("async ");
		if (clocks != null && ! clocks.isEmpty()) {
			w.write("clocked (");
			w.begin(0);

			for (Iterator i = clocks.iterator(); i.hasNext(); ) {
			    Formal f = (Formal) i.next();
			    print(f, w, tr);

			    if (i.hasNext()) {
				w.write(",");
				w.allowBreak(0, " ");
			    }
			}

			w.end();
			w.write(")");
			
		}
		printSubStmt(body, w, tr);
	}

	/**
	 * Return the first (sub)term performed when evaluating this
	 * term.
	 */
	public Term firstChild() {
		
		if (clocks() == null || clocks().isEmpty()) {
                        return body;
		} else {
                        return (Term) clocks().get(0);
		}
	}

	 

	/**
	 * Visit this term in evaluation order.
	 * [IP] Treat this as a conditional to make sure the following
	 *      statements are always reachable.
	 * FIXME: We should really build our own CFG, push a new context,
	 * and disallow uses of "continue", "break", etc. in asyncs.
	 */
	public <S> List<S> acceptCFG(CFGBuilder v, List<S> succs) {
		if (clocks() == null || clocks().isEmpty()) {
			v.visitCFG(body, this, EXIT);
		} else {
			v.visitCFGList(clocks, body, ENTRY);
			v.visitCFG(body, this, EXIT);
		}
        v.edge(v,this,ENTRY,this,EXIT,FlowGraph.EDGE_KEY_FALSE); // a trick to make sure we treat Async like a conditional for the purpose of initialization. see InitChecker.
		
		return succs;
	}

	private static final Collection<String> TOPICS =
		CollectionUtil.list(Report.types, Report.context);
}

