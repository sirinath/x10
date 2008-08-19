/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * @author Philippe Charles
 * @author vj
 */
package polyglot.ext.x10.ast;

import java.util.Collections;
import java.util.List;

import polyglot.ast.Block;
import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.ext.x10.types.X10Context;
import polyglot.ext.x10.types.X10NamedType;
import polyglot.ext.x10.types.X10TypeMixin;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.visit.ExprFlattener;
import polyglot.ext.x10.visit.ExprFlattener.Flattener;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.Types;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.AscriptionVisitor;
import polyglot.visit.CFGBuilder;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.ReachChecker;


/** A <code>Future </code> is a representation of the X10 future construct:
 * <code>future (place) { expression }<code>
 * stmts are used to represent the fully exploded version of the expression
 * as might be needed in order to inline array expressions.
 */
public class Future_c extends Closure_c
    implements Future {

    public Expr place; 

    public Future_c(Position p, Expr place, TypeNode returnType, Block body) {
	    super(p, Collections.EMPTY_LIST, Collections.EMPTY_LIST, returnType, null, Collections.EMPTY_LIST, body);
        this.place = place;
    }

    /** Get the RemoteActivity's place. */
    public Expr place() {
        return place;
    }
    
    /** Set the RemoteActivity's place. */
    public RemoteActivityInvocation place(Expr place) {
        this.place = place;
        return this;
    }

    /** Visit the children of the expression. 
     * vj: TODO: I use a hack below to bypass 
     * visiting the embedded stmt if the visitor is a ReachChecker.
     * Otherwise a reach error is generated that is in fact spurious.
     * There must be a way to convince the ReachChecker legitimately that this statement
     * is reachable if the future is reachable.
     * */
    public Node visitChildren( NodeVisitor v ) {
    	Expr place = (Expr) visitChild( this.place, v );
    	Future_c n = (Future_c) super.visitChildren(v);
    	if (n.place != place) {
    		if (n == this) n = (Future_c) copy();
    		n.place = place;
    	}
    	return n;
    }

    /** Type check the expression. */
    public Node typeCheck( ContextVisitor tc ) throws SemanticException {
    	X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
    	X10NodeFactory nf = (X10NodeFactory) tc.nodeFactory();

    	Type placeType = place.type();
    	Expr newPlace = place;
    	boolean placeIsPlace = ts.isImplicitCastValid(placeType, ts.Place());
    	if ( ! placeIsPlace ) {
    	    newPlace = (Expr) nf.Field(position(), place, nf.Id(position(), "location")).del().typeCheck(tc);
    	}
    	
    	Future_c n = (Future_c) place(newPlace);
    	n = (Future_c) super.typeCheck(tc);
    	
    	Type t = n.returnType().type();
    	
    	return n.type( ts.futureOf(position(), Types.ref(t)));
    }
    public Type childExpectedType(Expr child, AscriptionVisitor av) {
    	X10TypeSystem ts = (X10TypeSystem) av.typeSystem();
    	if ( child == place ) {
    		return ts.Place();
    	}
    	return child.type();
    }

    public String toString() {
    	return  " future[" + returnType + "](" + place + ") " + body;
    }
   
    /** Write the expression to an output file. */

    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    	w.write("future[");
    	printBlock(returnType, w, tr);
    	w.write("](");
    	printSubExpr(place, false, w, tr);
    	w.write(") ");
    	printBlock(body, w, tr);
    }

    /**
     * Return the first (sub)term performed when evaluating this
     * term.
     */
    public Term firstChild() {
        return place;
    }

    /**
     * Visit this term in evaluation order.
     */
    public List<Term> acceptCFG(CFGBuilder v, List<Term> succs) {
	v.visitCFG(returnType, place, ENTRY);
    	v.visitCFG(place, body, ENTRY);
    	v.visitCFG(body, this, EXIT);
    	return succs;
    }
        
}
