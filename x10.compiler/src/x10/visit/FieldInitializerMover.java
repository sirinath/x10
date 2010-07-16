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

import polyglot.ast.Assign;
import polyglot.ast.Block;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassDecl;
import polyglot.ast.ClassMember;
import polyglot.ast.ConstructorCall;
import polyglot.ast.ConstructorDecl;
import polyglot.ast.Eval;
import polyglot.ast.Expr;
import polyglot.ast.FieldAssign;
import polyglot.ast.FieldDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Special;
import polyglot.ast.Stmt;
import polyglot.frontend.Job;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldDef;
import polyglot.types.QName;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import x10.types.X10ClassType;
import x10.types.X10Def;
import x10.types.X10TypeMixin;
import x10.types.X10TypeSystem;

/**
 * Visitor that moves field initializers to the constructor
 * and insert explicit super constructor invocations
 * unless constructor is annotated @NoSuperCall.
 */
public class FieldInitializerMover extends ContextVisitor {
    X10TypeSystem xts;

    public FieldInitializerMover(Job job, TypeSystem ts, NodeFactory nf) {
        super(job, ts, nf);
        xts = (X10TypeSystem) ts;
    }
    
    protected ConstructorCall superCall(Type superType) throws SemanticException {
        ConstructorCall cc = nf.ConstructorCall(Position.compilerGenerated(), ConstructorCall.SUPER, Collections.EMPTY_LIST);
        ConstructorInstance ci = ts.findConstructor(superType, ts.ConstructorMatcher(superType, Collections.EMPTY_LIST, context));
        return cc.constructorInstance(ci);
    }
    
    protected boolean mustCallSuper(ConstructorDecl cdecl) throws SemanticException {
        Type t = (Type) xts.systemResolver().find(QName.make("x10.compiler.NoSuperCall"));
        return ((X10Def) cdecl.constructorDef()).annotationsMatching(t).isEmpty();
    }

    @Override
    public Node leaveCall(Node old, Node n, NodeVisitor v) throws SemanticException {
        if (n instanceof ClassDecl) {
            ClassDecl cdecl = (ClassDecl) n;
            ClassBody cb = cdecl.body();
            List<Stmt> assignments = new ArrayList<Stmt>();
            List<ClassMember> members = new ArrayList<ClassMember>();
            
            for (ClassMember cm : cb.members()) {
                if (cm instanceof FieldDecl) {
                    FieldDecl fd = (FieldDecl) cm;
                    FieldDef def = fd.fieldDef();

                    if (fd.init() != null && !def.flags().isStatic() && !def.isConstant()) {
                        Position p = fd.init().position();
                        Special this_ = nf.This(p);
                        this_ = (Special) this_.type(def.asInstance().container());
                        FieldAssign a = nf.FieldAssign(p, this_, nf.Id(p, def.name()), Assign.ASSIGN, fd.init());
                        a = a.fieldInstance(def.asInstance());
                        a = (FieldAssign) a.type(def.asInstance().type());
                        
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
            
            /* if (assignments.size() > 0) */ {
                List<ClassMember> members2 = new ArrayList<ClassMember>();

                for (ClassMember cm : members) {
                    if (cm instanceof ConstructorDecl) {
                        ConstructorDecl cd = (ConstructorDecl) cm;

                        Block body = cd.body();
                        if (body == null) {
                        	body = job().extensionInfo().nodeFactory().Block(cd.position());
                        }
                        List<Stmt> stmts = body.statements();
                        if (stmts.size() > 0) {
                            Stmt s = stmts.get(0);
                            if (s instanceof ConstructorCall) {
                                ConstructorCall cc = (ConstructorCall) s;
                                if (cc.kind() == ConstructorCall.SUPER) {
                                    List<Stmt> ss = new ArrayList<Stmt>();
                                    ss.add(s);
                                    ss.addAll(assignments);
                                    ss.addAll(stmts.subList(1, stmts.size()));
                                    body = body.statements(ss);
                                }
                            }
                            else {
                                // implicit super call
                                List<Stmt> ss = new ArrayList<Stmt>();
                                if (cdecl.superClass() != null && mustCallSuper(cd))
                                    ss.add(superCall(cdecl.superClass().type()));
                                ss.addAll(assignments);
                                ss.addAll(stmts);
                                body = body.statements(ss);
                            }
                        }
                        else {
                            // implicit super call
                            List<Stmt> ss = new ArrayList<Stmt>();
                            if (cdecl.superClass() != null && mustCallSuper(cd))
                                ss.add(superCall(cdecl.superClass().type()));
                            ss.addAll(assignments);
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
                
                members = members2;
            }
            
            return cdecl.body(cb.members(members));
        }
        
        return super.leaveCall(old, n, v);
    }
}
