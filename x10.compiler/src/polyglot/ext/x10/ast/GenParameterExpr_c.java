package polyglot.ext.x10.ast;

import java.util.Iterator;
import java.util.List;

import polyglot.ast.ArrayInit;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ast.Term;
import polyglot.ast.Expr;
import polyglot.ast.TypeNode;
import polyglot.ext.jl.ast.Expr_c;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.TypedList;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

/** An immutable representation of the type parameter list of a parameteric type.
 * The corresponding syntax is <T1,...,TN>
 * This node is created for (type) parameteric X10 types.
 * @author Christian Grothoff
 */
public class GenParameterExpr_c extends Expr_c implements GenParameterExpr {
	/**
         * The argument list of a parametric type. Maybe null.
	 */
	protected List/*TypeNode*/ args;
	
	public GenParameterExpr_c(Position pos, List l) {
		super(pos);
		this.args =  TypedList.copyAndCheck(l, TypeNode.class, false);
	}
	
	public List args() {
		return this.args;
	}
	public GenParameterExpr args( List args ) {
		GenParameterExpr_c n = (GenParameterExpr_c) copy();
		n.args = args;
		return n;
	}
	public GenParameterExpr reconstruct( List args) {
		if (args == this.args) return this;
		GenParameterExpr_c n = (GenParameterExpr_c) copy();
		n.args = args;
		return n;
	}
	public Node visitChildren(NodeVisitor v) {
	    List arguments = visitList(this.args, v);
	    return reconstruct(arguments);
	  }
	
	/** Type check the statement. 
	 * TODO: Implement type checking.
	 * Nothing to do for type checking?
	 */
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
		
		return this;
	}
	/* (non-Javadoc)
	 * @see polyglot.ast.Term#entry()
	 */
	public Term entry() {
		return (args.isEmpty())
		? null : listEntry( args, this);
	}
	
	/* (non-Javadoc)
	 * @see polyglot.ast.Term#acceptCFG(polyglot.visit.CFGBuilder, java.util.List)
	 */
	public List acceptCFG(CFGBuilder v, List succs) {
		if (args != null) {
   		    v.visitCFGList( args, this);
		}
		return succs;
	}
	public String toString() {
		String s = "<";
		int count = 0;

	    for (Iterator i = args.iterator(); i.hasNext(); ) {
	        if (count++ > 2) {
	            s += "...";
	            break;
	        }
	        Expr n = (Expr) i.next();
	        s += n.toString();

	        if (i.hasNext()) {
	            s += ", ";
	        }
	    }
	    return s + ">";
	}
	/** Write the statement to an output file. */
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		if ((args == null) || args.isEmpty()) return;
		w.write("<");
		w.begin(0);
		
		for (Iterator it = args.iterator(); it.hasNext();) {
			Expr e = (Expr) it.next();
			print(e, w, tr);
			
			if (it.hasNext()) {
				w.write(",");
				w.allowBreak(0, " ");
			}	
		}
		w.end();
		w.write(">");
	}
	
}
