/*
 *
 * (C) Copyright IBM Corporation 2007-2008
 *
 *  This file is part of X10 Language.
 *
 */
package x10.ast;

import polyglot.ast.Node;
import polyglot.ast.Node_c;
import polyglot.ast.TypeNode;
import polyglot.types.Context;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.Translator;
import x10.types.X10ClassType;
import x10.types.X10Context;

/**
 * A node representing an annotation.  Every X10 Node has an associated list of AnnotationNodes.
 * An annotation is simply an interface type.
 * @author nystrom
 */
public class AnnotationNode_c extends Node_c implements AnnotationNode {
	TypeNode tn;
	
    /**
	 * 
	 */
	public AnnotationNode_c(Position pos, TypeNode tn) {
		super(pos);
		this.tn = tn;
	}

	public TypeNode annotationType() {
		return tn;
	}
	
	public AnnotationNode annotationType(TypeNode tn) {
		AnnotationNode_c n = (AnnotationNode_c) copy();
		n.tn = tn;
		return n;
	}
	
	public X10ClassType annotationInterface() {
		return (X10ClassType) annotationType().type();
	}
	
	@Override
	public Node visitChildren(NodeVisitor v) {
		TypeNode tn = (TypeNode) this.visitChild(this.tn, v);
		if (tn != this.tn) {
			return annotationType(tn);
		}
		return this;
	}
	
	@Override
	public Context enterChildScope(Node child, Context c) {
		c = c.pushBlock();
		((X10Context) c).setAnnotation();
		return super.enterChildScope(child, c);
	}
	
//	public Node disambiguateOverride(AmbiguityRemover ar) throws SemanticException {
//		if (ar.job().extensionInfo().scheduler().currentGoal() instanceof SignaturesDisambiguated) {
//			return this;
//		}
//		if (ar.job().extensionInfo().scheduler().currentGoal() instanceof SupertypesDisambiguated) {
//			return this;
//		}
//		return super.disambiguate(ar);
//	}
	
	@Override
	public Node typeCheck(ContextVisitor tc) throws SemanticException {
//		System.out.println("Type checking " + this);
		if (! tn.type().isClass() || ! tn.type().toClass().flags().isInterface()) {
			throw new SemanticException("Annotation must be an interface type.", position());
		}
		
		return this;
	}
	
	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter pp) {
		w.write("@");
		print(tn, w, pp);
	}

	@Override
	public void translate(CodeWriter w, Translator tr) {
		/** Do nothing! */
	}
	
	@Override
	public String toString() {
		return "@" + tn.toString();
	}
}
