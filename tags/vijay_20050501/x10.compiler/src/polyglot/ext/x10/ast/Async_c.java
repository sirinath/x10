package polyglot.ext.x10.ast;

import java.util.List;

import polyglot.ast.Block;
import polyglot.ast.Stmt;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.ext.jl.ast.Stmt_c;
import polyglot.util.Position;
import polyglot.util.CodeWriter;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;
import polyglot.visit.AscriptionVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.types.Type;


/*
 * Created on Oct 5, 2004

 * @author Christian Grothoff
 * @author Philippe Charles
 * @author vj
 */

public class Async_c extends Stmt_c 
    implements Async {

    public Expr place; 
    public Stmt body;

    public Async_c( Position pos, Expr place, Stmt body ) {
        super( pos );
        this.place = place;
        this.body = body;
    }
    
    public Async_c(Position p) {
        super(p);
    }
    
    /* (non-Javadoc)
     * @see polyglot.ext.x10.ast.Future#body()
     */
    public Stmt body() {
        return body;
    }

    /** Set the body of the statement. 
     */
    public Async body(Stmt body) {
	Async_c n = (Async_c) copy();
	n.body = body;
	return n;
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

    /** Reconstruct the statement. */
    protected Async reconstruct( Expr place, Stmt body ) {
	if ( place != this.place || body != this.body ) {
	    Async_c n = (Async_c) copy();
	    n.place = place;
	    n.body = body;
	    return n;
	}

	return this;
    }

    /** Visit the children of the statement. */
    public Node visitChildren( NodeVisitor v ) {
	Expr place = (Expr) visitChild(this.place, v);
	Stmt body = (Stmt) visitChild(this.body, v);
	return reconstruct(place, body);
    }

    /** Type check the statement. */
    public Node typeCheck(TypeChecker tc) throws SemanticException {
	TypeSystem ts = tc.typeSystem();

	/*
	if (! ts.isSubtype(expr.type(), ts.Object()) ) {
	     throw new SemanticException(
		 "Cannot synchronize on an expression of type \"" +
		 expr.type() + "\".", expr.position());
	}
	*/
	return this;
    }

    // not sure how this works.. vj. Copied from Synchronized_c.
    public Type childExpectedType(Expr child, AscriptionVisitor av) {
        TypeSystem ts = av.typeSystem();

        if (child == place) {
            return ts.Object();
        }

        return child.type();
    }

    public String toString() {
	return "async (" + place + ") { ... }";
    }

    /** Write the statement to an output file. */
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
	w.write("async (");
	printBlock(place, w, tr);
	w.write(") ");
	printSubStmt(body, w, tr);
    }

    /**
     * Return the first (sub)term performed when evaluating this
     * term.
     */
    public Term entry() {
         return (place != null ? place.entry() : this);
    }

    /**
     * Visit this term in evaluation order.
     */
    public List acceptCFG(CFGBuilder v, List succs) {
    	v.visitCFG(place, body.entry());
        v.visitCFG(body, this);
        return succs;
    }
}
