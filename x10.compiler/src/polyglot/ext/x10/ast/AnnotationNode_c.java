/*
 *
 * (C) Copyright IBM Corporation 2007
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.ast;

import polyglot.ast.Node;
import polyglot.ast.Node_c;
import polyglot.ast.TypeNode;
import polyglot.ext.x10.types.X10ClassType;
import polyglot.ext.x10.types.X10Context;
import polyglot.types.ClassType;
import polyglot.types.Context;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.Translator;
import polyglot.visit.TypeChecker;

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

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		System.out.println("Type checking " + this);
		if (! tn.type().isClass() || ! tn.type().toClass().flags().isInterface()) {
			throw new SemanticException("Annotation must be an interface type.", position());
		}
		return super.typeCheck(tc);
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
