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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import polyglot.frontend.Job;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import x10.types.ConstructorInstance;
import x10.types.FieldDef;
import x10.types.FieldInstance;
import x10.types.Flags;
import x10.types.MethodDef;
import x10.types.Name;
import x10.types.QName;
import x10.types.Ref;
import x10.types.SemanticException;
import x10.types.Type;
import x10.types.TypeSystem;
import x10.types.Types;
import x10.types.X10ClassType;
import x10.types.X10Def;
import x10.types.X10TypeMixin;
import x10.types.X10TypeSystem;
import x10.ast.Assign;
import x10.ast.Block;
import x10.ast.Call;
import x10.ast.ClassBody;
import x10.ast.ClassDecl;
import x10.ast.ClassMember;
import x10.ast.ConstructorCall;
import x10.ast.ConstructorDecl;
import x10.ast.Eval;
import x10.ast.Expr;
import x10.ast.FieldAssign;
import x10.ast.FieldDecl;
import x10.ast.Id;
import x10.ast.MethodDecl;
import x10.ast.MethodDecl_c;
import x10.ast.Node;
import x10.ast.NodeFactory;
import x10.ast.Special;
import x10.ast.Stmt;
import x10.ast.TypeNode;
import x10.ast.X10MethodDecl_c;
import x10.ast.AssignPropertyCall;

/**
 * Visitor that moves field initializers to the constructor
 * and insert explicit super constructor invocations
 * unless constructor is annotated @NoSuperCall.
 *
Yoav added:
 * Field initializers are placed after the AssignProperty call, because we have a 3-phase init: super, properties, ctor-code
 * Note that the type of a field may refer to a property, so even field assignment is prohibited until after the AssignProperty call.
 *
 * Finally, we do not move constant fields because it is possible to switch over a constant field (final fields with compile-time known value):
 * E.g., in Java (and similarly in X10):
class Test {
    final int i=2+1;
    void test() {
        switch(3*4) {
            case i:
            case 4:
        }
    }
}
 */
public class FieldInitializerMover extends ContextVisitor {
    X10TypeSystem xts;

    public FieldInitializerMover(Job job, TypeSystem ts, NodeFactory nf) {
        super(job, ts, nf);
        xts = (X10TypeSystem) ts;
    }
    
    protected ConstructorCall superCall(Type superType) throws SemanticException {
        Position CG = Position.COMPILER_GENERATED;
        assert (superType.isClass());
        Expr qualifier = null;
        if (superType.toClass().def().isMember() && !superType.toClass().flags().isStatic())
            qualifier = nf.This(CG, nf.CanonicalTypeNode(CG, superType.toClass().outer()));
        ConstructorCall cc = nf.SuperCall(CG, qualifier, Collections.<Expr>emptyList());
        ConstructorInstance ci = ts.findConstructor(superType, ts.ConstructorMatcher(superType, Collections.<Type>emptyList(), context));
        return cc.constructorInstance(ci);
    }
    
    protected boolean mustCallSuper(ConstructorDecl cdecl) throws SemanticException {
        Type t = (Type) xts.systemResolver().find(QName.make("x10.compiler.NoSuperCall"));
        return ((X10Def) cdecl.constructorDef()).annotationsMatching(t).isEmpty();
    }
    class FindProperty extends NodeVisitor {
        private final Stmt evalCall;
        private boolean didFindProperty = false;
        private FindProperty(Stmt evalCall) {
            this.evalCall = evalCall;
        }
        @Override public Node leave(Node old, Node n, NodeVisitor v) {
            if (n instanceof AssignPropertyCall) {
                AssignPropertyCall propCall = (AssignPropertyCall) n;
                didFindProperty = true;
                return nf.Block(n.position().markCompilerGenerated(),propCall,evalCall);
            }
            return n;
        }
    }
    private ClassDecl changeClass(ClassDecl cdecl) throws SemanticException {
        final ClassBody cb = cdecl.body();
        final List<ClassMember> members = new ArrayList<ClassMember>();

        final Position p = cdecl.position().markCompilerGenerated();
        final Special this_ = (Special) nf.This(p).type(cdecl.classDef().asType());

        List<Stmt> assignments = new ArrayList<Stmt>();
        for (ClassMember cm : cb.members()) {
            if (cm instanceof FieldDecl) {
                FieldDecl fd = (FieldDecl) cm;
                FieldDef def = fd.fieldDef();

                if (fd.init() != null && !def.flags().isStatic() && !def.isConstant()) {
                    final FieldInstance fieldInstance = def.asInstance();

                    FieldAssign a = nf.FieldAssign(p, this_, nf.Id(p, def.name()), Assign.ASSIGN, fd.init());
                    a = a.fieldInstance(fieldInstance);
                    a = (FieldAssign) a.type(fieldInstance.type());

                    assert this_.type() != null;
                    assert a.type() != null;

                    Eval eval = nf.Eval(p, a);
                    assignments.add(eval);

                    fd = fd.init(null);
                }

                members.add(fd);
            }
            else {
                members.add(cm);
            }
        }
        Stmt evalCall = nf.Block(p); // an empty statement
        if (assignments.size()>0) {
            // create a private method that includes all the field initializers
            final Ref<Type> refRet = Types.ref(ts.Void());
            TypeNode returnType = nf.TypeNodeFromQualifiedName(p,QName.make("void")).typeRef(refRet);
            final Name name = Name.makeFresh("__fieldInitializers");
            final Id nameId = nf.Id(p, name);
            final Flags flags = Flags.PRIVATE.Final();
            MethodDecl method = nf.MethodDecl(p,nf.FlagsNode(p, flags),returnType, nameId,
                    Collections.EMPTY_LIST, nf.Block(p,assignments));
            MethodDef md = ts.methodDef(p,Types.ref(cdecl.classDef().asType()), flags,refRet,name,Collections.EMPTY_LIST);
            method = method.methodDef(md);
            members.add(method);

            // create the call to __fieldInitializers
            Call call = nf.Call(p,this_,nameId,Collections.EMPTY_LIST).methodInstance(md.asInstance());
            call = (Call) call.type(ts.Void());
            evalCall = nf.Eval(p, call);
        }

        final List<ClassMember> members2 = new ArrayList<ClassMember>();

        for (ClassMember cm : members) {
            if (cm instanceof ConstructorDecl) {
                ConstructorDecl cd = (ConstructorDecl) cm;

                Block body = cd.body();
                if (body == null) {
                    body = nf.Block(p);
                }

                // if there is a property(...) call, then we replace it with: { property(...); __fieldInitializers(); }
                FindProperty findProperty = new FindProperty(evalCall);
                body = (Block) body.visit(findProperty);
                boolean didFindProperty = findProperty.didFindProperty;

                List<Stmt> stmts = body.statements();
                if (stmts.size() > 0) {
                    Stmt s = stmts.get(0);
                    if (s instanceof ConstructorCall) {
                        ConstructorCall cc = (ConstructorCall) s;
                        if (cc.kind() == ConstructorCall.SUPER) {
                            List<Stmt> ss = new ArrayList<Stmt>();
                            ss.add(s);
                            if (!didFindProperty) ss.add(evalCall);
                            ss.addAll(stmts.subList(1, stmts.size()));
                            body = body.statements(ss);
                        }
                    }
                    else {
                        // implicit super call
                        List<Stmt> ss = new ArrayList<Stmt>();
                        if (cdecl.superClass() != null && mustCallSuper(cd))
                            ss.add(superCall(cdecl.superClass().type()));
                        if (!didFindProperty) ss.add(evalCall);
                        ss.addAll(stmts);
                        body = body.statements(ss);
                    }
                }
                else {
                    // implicit super call
                    List<Stmt> ss = new ArrayList<Stmt>();
                    if (cdecl.superClass() != null && mustCallSuper(cd))
                        ss.add(superCall(cdecl.superClass().type()));
                    if (!didFindProperty) ss.add(evalCall);
                    ss.addAll(stmts);
                    body = body.statements(ss);
                }

                if (body != cd.body()) {
                    cd = (ConstructorDecl) cd.body(body);
                }

                members2.add(cd);
            }
            else {
                members2.add(cm);
            }
        }


        return cdecl.body(cb.members(members2));
    }
    @Override
    public Node leaveCall(Node old, Node n, NodeVisitor v) throws SemanticException {
        if (n instanceof ClassDecl) {
            final ClassDecl cdecl = (ClassDecl) n;
            if (!cdecl.flags().flags().isInterface())
                return changeClass(cdecl);            
        }
        
        return super.leaveCall(old, n, v);
    }
}
