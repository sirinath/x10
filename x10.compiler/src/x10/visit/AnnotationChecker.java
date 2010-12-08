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

package x10.visit;

import java.util.Iterator;
import java.util.List;

import polyglot.ast.ClassDecl;
import polyglot.ast.Expr;
import polyglot.ast.FieldDecl;
import polyglot.ast.Import;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.PackageNode;
import polyglot.ast.SourceFile;
import polyglot.ast.Stmt;
import polyglot.ast.TypeNode;
import polyglot.frontend.Job;
import polyglot.types.ClassType;
import polyglot.types.QName;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import x10.ast.AnnotationNode;
import x10.extension.X10Ext;
import x10.types.X10ClassType;
import x10.errors.Errors;

public class AnnotationChecker extends ContextVisitor {
	public AnnotationChecker(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}
	
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v) {

		if (! (n.ext() instanceof X10Ext)) {
			return n;
		}
		
		init();

		List<AnnotationNode> annotations = ((X10Ext) n.ext()).annotations();
		
		for (Iterator<AnnotationNode> i = annotations.iterator(); i.hasNext(); ) {
			AnnotationNode a = i.next(); 
			X10ClassType at = a.annotationInterface();
			if (n instanceof TypeNode && ! at.isSubtype(TA, context)) {
				Errors.issue(job, new SemanticException("Annotation "+at+" on types must implement " + TA, n.position()));
			}
			else if (n instanceof Expr && ! at.isSubtype(EA, context)) {
				Errors.issue(job, new SemanticException("Annotation "+at+" on expressions must implement " + EA, n.position()));
			}
			else if (n instanceof Stmt && ! at.isSubtype(SA, context)) {
				Errors.issue(job, new SemanticException("Annotation "+at+" on statements must implement " + SA, n.position()));
			}
			else if (n instanceof MethodDecl && ! at.isSubtype(MA, context)) {
				Errors.issue(job, new SemanticException("Annotation "+at+" on method declarations must implement " + MA, n.position()));
			}
			else if (n instanceof FieldDecl && ! at.isSubtype(FA, context)) {
				Errors.issue(job, new SemanticException("Annotation "+at+" on field declarations must implement " + FA, n.position()));
			}
			else if (n instanceof ClassDecl && ! at.isSubtype(CA, context)) {
				Errors.issue(job, new SemanticException("Annotation "+at+" on class declarations must implement " + CA, n.position()));
			}
			else if (n instanceof PackageNode && parent instanceof SourceFile && ! at.isSubtype(PA, context)) {
				Errors.issue(job, new SemanticException("Annotation "+at+" on package declarations must implement " + PA, n.position()));
			}
			else if (n instanceof Import && ! at.isSubtype(IA, context)) {
				Errors.issue(job, new SemanticException("Annotation "+at+" on imports must implement " + IA, n.position()));
			}
			else if (! at.isSubtype(A, context)) {
				Errors.issue(job, new SemanticException("Annotation"+at+" must implement " + A, n.position()));
			}
		}
		
		return n;
	}
	
	ClassType A, TA, EA, SA, MA, FA, CA, IA, PA;
	
	public void init() {
		if (A != null) return;
        try {
            TA = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.TypeAnnotation"));
            EA = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.ExpressionAnnotation"));
            SA = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.StatementAnnotation"));
            MA = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.MethodAnnotation"));
            FA = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.FieldAnnotation"));
            CA = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.ClassAnnotation"));
            IA = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.ImportAnnotation"));
            PA = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.PackageAnnotation"));
            A  = (ClassType) ts.systemResolver().find(QName.make("x10.lang.annotations.Annotation"));
        } catch (SemanticException e) {
            throw new RuntimeException(e);
        }
    }
}
