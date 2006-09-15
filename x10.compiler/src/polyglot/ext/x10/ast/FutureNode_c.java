/*
 * Created on Nov 26, 2004
 *
 *
 */
package polyglot.ext.x10.ast;

import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ext.x10.types.X10Context;
import polyglot.ext.x10.types.X10NamedType;
import polyglot.ext.x10.types.X10ParsedClassType;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.types.Context;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;


/** A FutureNode is an TypeNode that has been marked with a future
 * qualifier.  Note that the base TypeNode may be ambiguous and may need to be resolved.
 * @author vj
 *
 */
public class FutureNode_c extends X10TypeNode_c implements FutureNode {

	// Recall the field this.type is defined at the supertype.
	// The Typenode representing the argument type X.
	protected TypeNode base;

	public FutureNode_c(Position pos, TypeNode base) {
		super( pos );
		this.base = base;
	}

	public TypeNode base() {
		return this.base;
	}

	public FutureNode_c base( TypeNode base ) {
		FutureNode_c n = (FutureNode_c) copy();
		n.base = base;
		return n;
	}

	protected FutureNode_c reconstruct( TypeNode base ) {
		return (base != this.base) ? base( base ) :  this;
	}

	public Node buildTypes(TypeBuilder tb) throws SemanticException {
		X10TypeSystem ts = (X10TypeSystem) tb.typeSystem();
		return type(ts.unknownType(position()));
	}

	  public Context enterChildScope(Node child, Context c) {
	        if (child == this.dep) {
	            TypeSystem ts = c.typeSystem();
	            if (type instanceof X10ParsedClassType)
	            c = ((X10Context) c).pushDepType((X10ParsedClassType) type);
	        }
	        return super.enterChildScope(child, c);
	    }
	public Node visitChildren( NodeVisitor v ) {
		TypeNode base = (TypeNode) visitChild(this.base, v);
		return ((FutureNode_c) super.visitChildren(v)).reconstruct( base );
	}

	public Node disambiguateBase(AmbiguityRemover sc) throws SemanticException {
		// Report.report(5,"[FutureNode_c] Disambiguating |" + this + "|(#" + this.hashCode() +"):");
		TypeNode newType = (TypeNode) base.disambiguate( sc );
		// Report.report(5,"[FutureNode_c] ... yields type |" + type + "|.");
		
		Type baseType =  newType.type();
		// RMF 11/2/2005 - Don't proceed further if the base type hasn't yet been disambiguated...
		if (!baseType.isCanonical())
			return this;

		if (null == baseType) {
			throw new SemanticException("The type constructor future cannot be applied to a <null> type",
					position());
		}
	
		X10TypeSystem ts = (X10TypeSystem) baseType.typeSystem();
		this.type = ts.createFutureType( position(), (X10NamedType) baseType );
		Node result = reconstruct( newType );
		// Report.report(5,"[FutureNode_c] ... returns |" + result +"|(#" + result.hashCode() +").");
		return result;
	}

    /**
     * Typecheck the type-argument.
     */
	public Node typeCheckBase( TypeChecker tc) throws SemanticException {
		// Report.report(5,"[FutureNode_c] Type checking |" + this +"|:");
		Node n = base.typeCheck( tc );
		// Report.report(5,"[FutureNode_c] ... yields node |" + n +"|.");
		if (n instanceof TypeNode) {
			TypeNode arg = (TypeNode) n;
			X10NamedType argType = (X10NamedType) arg.type();

			X10TypeSystem ts = (X10TypeSystem) argType.typeSystem();
			this.type = ts.createFutureType( position(), argType );

			// this.base = null?
			// Report.report(5, "[FutureNode_c] ... sets type to |" + this.type + "|.");
			// Report.report(5, "[FutureNode_c] ... returns |" + this + "|.");
			return this;
		}
		throw new SemanticException("Argument to future type-constructor does not type-check" + position());
	}

	public String toString() {
		if (this.type == null)
			return "unknown";
		return this.type.toString();
	}

	/**
	 * Write out Java code for this node.
	 */
	public void prettyPrint(CodeWriter w, PrettyPrinter ignore) {
		w.write(this.type.toString());
	}

	// translate??
	// prettyPrint?
	// dump?
}
