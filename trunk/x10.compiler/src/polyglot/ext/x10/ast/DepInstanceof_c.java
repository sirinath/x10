/**
 * 
 */
package polyglot.ext.x10.ast;

import polyglot.ast.Expr;
import polyglot.ast.Instanceof;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ast.TypeNode_c;
import polyglot.ext.x10.types.X10Context;
import polyglot.ext.x10.types.X10NamedType;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.types.Context;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;
import x10.runtime.Report;

/**
 * @author vj
 *
 */
public class DepInstanceof_c extends X10Instanceof_c implements DepInstanceof,
		X10DepCastInfo {

	protected boolean depTypeCheckingNeeded = false;

	protected DepParameterExpr dep;

	public DepInstanceof_c(Position pos, TypeNode compareType,
			DepParameterExpr d, Expr expr) {
			super(pos, expr,compareType);
			this.dep = d;
			
		    }
	  
	public boolean isDepTypeCheckingNeeded() {
		return this.depTypeCheckingNeeded;
	}

	Type lookaheadType = null;
	public NodeVisitor disambiguateEnter(AmbiguityRemover sc) throws SemanticException {
		lookaheadType = ((TypeNode_c) compareType.disambiguate(sc)).type();
		return sc;
	}
	public NodeVisitor typeCheckEnter(TypeChecker tc) throws SemanticException {
		//Report.report(1, "X10CanonicalType: typecheckEnter " + this + " dep=|" + this.dep + "|");
		lookaheadType = ((TypeNode_c) compareType.typeCheck(tc)).type();
		return tc;
	}
	
	public Context enterChildScope(Node child, Context c) {
		if (child == this.dep) {
			TypeSystem ts = c.typeSystem();
			if (lookaheadType instanceof X10NamedType) {
				c = ((X10Context) c).pushDepType((X10NamedType) lookaheadType);
			}
		}
		Context cc = super.enterChildScope(child, c);
		return cc;
	}
	  public Node typeCheck(TypeChecker tc) throws SemanticException {
		  Report.report(1, "DepInstance_of: " + this + " dep=" + dep);
		super.typeCheck(tc);
		Instanceof n = (Instanceof) node();
		Type toType = n.compareType().type();
		Type fromType = n.expr().type();
		X10TypeSystem xts = (X10TypeSystem) tc.typeSystem();
		
		this.depTypeCheckingNeeded = false;

		// if toType is nullable and is constrained then expr must be not null
		this.notNullRequired = this.isToTypeNullable() && xts.isTypeConstrained(toType);
		
		this.depTypeCheckingNeeded = 
			xts.isTypeConstrained(toType) && !xts.isTypeConstrained(fromType);
		
		return n.type(tc.typeSystem().Boolean());
	  }

	public DepParameterExpr depParameterExpr() {
		return (DepParameterExpr) this.dep.copy();
}
    /** Reconstruct the expression. */
    protected DepInstanceof_c reconstruct(Expr expr, TypeNode compareType, DepParameterExpr dep) {
    	DepInstanceof_c superCopy = (DepInstanceof_c) super.reconstruct(expr, compareType);
    	if (dep != this.dep) {
    		DepInstanceof_c copy = (DepInstanceof_c) superCopy.copy();
    		copy.dep = dep;
    		return copy;
    	}
    	return (superCopy == this) ? this : superCopy;
    }

    /** Visit the children of the expression. */
    public Node visitChildren(NodeVisitor v) {
		Expr expr = (Expr) visitChild(this.expr, v)  ;
		TypeNode compareType = (TypeNode) visitChild(this.compareType, v);
//		DepParameterExpr depExpr = (DepParameterExpr) visitChild(this.dep, v);
		DepParameterExpr depExpr = (DepParameterExpr) dep.copy();
		return reconstruct(expr, compareType, depExpr);
    }
}
