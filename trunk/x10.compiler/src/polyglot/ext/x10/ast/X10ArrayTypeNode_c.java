/*
 * Created by vj on Dec 9, 2004
 *
 * 
 */
package polyglot.ext.x10.ast;

import polyglot.ast.Expr;
import polyglot.ast.NodeFactory;
import polyglot.ast.TypeNode;
import polyglot.ext.jl.ast.TypeNode_c;
import polyglot.util.CodeWriter;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.ast.Node;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.ExceptionChecker;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.Translator;
import polyglot.visit.TypeChecker;
import polyglot.ext.x10.ast.DepParameterExpr;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.visit.TypeBuilder;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;

/** An immutable AST representation of an X10 array type.
 * @author vj Dec 9, 2004
 * 
 */
public class X10ArrayTypeNode_c extends TypeNode_c implements
		X10ArrayTypeNode {
    protected TypeNode base;
	protected boolean isValueType;
	protected DepParameterExpr indexedSet;
	
	/**
	 * @param pos
	 * @param base
	 */
	public X10ArrayTypeNode_c(Position pos, TypeNode base) {
		this(pos, base, false);
	}
	
	public X10ArrayTypeNode_c(Position pos, TypeNode base, boolean isValueType) {
		this(pos, base, isValueType, null);
	}
	
	/** Create an ArrayTypeNode for the X10 construct base[ expr ].
	 * expr must be a region or a distribution.
	 * 
	 * @param pos
	 * @param base
	 * @param isValueType
	 * @param indexedSet -- the region or distribution.
	 */
	public X10ArrayTypeNode_c(Position pos, TypeNode base, boolean isValueType, 
			DepParameterExpr indexedSet ) {
		super(pos);
		this.base = base;
		this.isValueType = isValueType;
		this.indexedSet = indexedSet;
	}
	
	/* (non-Javadoc)
	 * @see polyglot.ext.x10.ast.X10ArrayTypeNode#isValueArray()
	 */
	public boolean isValueType() {
		return this.isValueType;
	}

	public TypeNode base() {
		return this.base;
	}
	public X10ArrayTypeNode base(TypeNode base ) {
		X10ArrayTypeNode_c n = (X10ArrayTypeNode_c) copy();
		n.base = base;
		return n;
	}
	/** Returns the indexset if any associated with this type.
	 * May return null.
	 * @see polyglot.ext.x10.ast.X10ArrayTypeNode#indexSet()
	 */
	public Expr indexSet() {
		return this.indexedSet;
	}

	 protected X10ArrayTypeNode_c reconstruct(TypeNode base,  DepParameterExpr indexedSet) {
        if (base != this.base || (isValueType != this.isValueType) 
        		|| (indexedSet != this.indexedSet)) {
	    X10ArrayTypeNode_c n = (X10ArrayTypeNode_c) copy();
	    n.base = base;
	    n.indexedSet = indexedSet;
	    return n;
        }

        return this;
	 }

	 public Node visitChildren(NodeVisitor v) {
		TypeNode base = (TypeNode) visitChild(this.base, v);
		DepParameterExpr indexedSet = (DepParameterExpr) visitChild(this.indexedSet, v);
		return reconstruct(base, indexedSet);
	}

	   public Node buildTypes(TypeBuilder tb) throws SemanticException {
		X10TypeSystem ts = (X10TypeSystem) tb.typeSystem();
	        return type(indexedSet == null 
	        		? ts.x10arrayOf(position(), base.type())
	        				: ts.x10arrayOf(position(), base.type(), indexedSet)
	        		);
	    }
	  

    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
    	X10TypeSystem ts = (X10TypeSystem) ar.typeSystem();
    	NodeFactory nf = ar.nodeFactory();
    	
    	Type baseType = base.type();
    	
    	if (! baseType.isCanonical()) {
    		throw new SemanticException(
    				"Base type " + baseType + " of array could not be resolved.",
					base.position());
    	}
    	
    	return nf.CanonicalTypeNode(position(),
    			ts.x10arrayOf(position(), baseType));
    }

    public Node typeCheck(TypeChecker tc) throws SemanticException {
	throw new InternalCompilerError(position(),
	    "Cannot type check ambiguous node " + this + ".");
    }

    public Node exceptionCheck(ExceptionChecker ec) throws SemanticException {
	throw new InternalCompilerError(position(),
	    "Cannot exception check ambiguous node " + this + ".");
    }

    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
        print(base, w, tr);
        w.write( (isValueType ? " value " : "") + "[." 
        		+ (indexedSet == null ? "" : indexedSet.toString()) + "]");
    }

    public void translate(CodeWriter w, Translator tr) {
      throw new InternalCompilerError(position(),
                                      "Cannot translate ambiguous node "
                                      + this + ".");
    }

    public String toString() {
        return base.toString() 
		+ (isValueType ? " value " : "")
		+ "[." + (indexedSet == null ? "" : indexedSet.toString()) + "]";
    }

}
