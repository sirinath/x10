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

package x10.ast;

import polyglot.ast.Node;
import polyglot.ast.Special_c;
import polyglot.ast.TypeNode;
import polyglot.types.ClassType;
import polyglot.types.CodeDef;
import polyglot.types.LazyRef_c;
import polyglot.types.Ref_c;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import x10.constraint.XFailure;
import x10.constraint.XVar;
import x10.constraint.XTerm;
import x10.errors.Errors;
import x10.errors.Errors.IllegalConstraint;
import x10.types.ConstrainedType;
import x10.types.X10ConstructorDef;
import polyglot.types.Context;
import x10.types.ThisDef;

import x10.types.X10ClassDef;
import x10.types.X10MemberDef;
import x10.types.X10MethodDef;
import x10.types.X10ParsedClassType;
import x10.types.X10ParsedClassType_c;
import x10.types.X10ProcedureDef;

import polyglot.types.TypeSystem;
import x10.types.XTypeTranslator;
import x10.types.checker.PlaceChecker;
import x10.types.constraints.CConstraint;
import x10.types.constraints.CConstraint;
import x10.types.constraints.CThis;
import x10.types.constraints.QualifiedVar;
import x10.types.constraints.XConstrainedTerm;
import x10.util.X10TypeUtils;

public class X10Special_c extends Special_c implements X10Special {

    public X10Special_c(Position pos, Kind kind, TypeNode qualifier) {
        super(pos, kind, qualifier);
    }


    public static X10Special self(Position pos) {
        X10Special_c self = new X10Special_c(pos, SELF, null);
        return self;
    }

    public boolean isSelf() { return kind == SELF; }
    
    /**
     * In data-centric synchronization, the type of this is:
     * 1. if the container class does not have atomic field, the same
     * 2. else, become linked ContainerClass, with contex: ContainerClass
     * 
     * The purpose of this method is to change the type of this.
     * */
    public Node checkAtomicity(ContextVisitor tc) {
    	if(this.qualifier == null && this.kind == THIS) {
    		X10ParsedClassType_c thisClassType = Types.fetchX10ClassType(type);
    		if(thisClassType != null) {
    			X10ClassDef thisClazzDef = thisClassType.def();
    			//for safety, accumulate it first
    			X10ClassDecl_c.accumulateAtomicFields(thisClazzDef);
    			
    			/**
    			 * The following code alters the type of this. In the design of AJ, 
    			 * this is linked to itself. In our later design, I removed this property,
    			 * because doing so will make a large amount of code becoming un-compilable,
    			 * such as:  a.method_foo(this). 
    			 * */
    			
//    			if(thisClazzDef.hasAtomicFields(false)
//    					&& !X10TypeUtils.skipProcessingClass(thisClassType)) {
//    				//we changed the type here
//    				//FIXME can be wrong
//    				assert type instanceof ConstrainedType;
//    				ConstrainedType cType = (ConstrainedType)type;
//    				X10ParsedClassType_c baseType = (X10ParsedClassType_c)cType.baseType().get();
//    				X10ParsedClassType_c copiedBaseType = baseType.copy();
//    				copiedBaseType.setAtomicContext(copiedBaseType);
//    				//create a copied type node here
//    				ConstrainedType copiedCType = cType.baseType(new Ref_c<X10ParsedClassType_c>(copiedBaseType));
//    				//return a new node
//    				return this.type(copiedCType);
//    			}
    		}
    	}
    	
    	return super.checkAtomicity(tc);
    }

    /** Type check the expression. */
    public Node typeCheck(ContextVisitor tc) {
        TypeSystem ts = (TypeSystem) tc.typeSystem();
        Context c = (Context) tc.context();

        if (isSelf()) {
            Type tt = c.currentDepType();

            if (tt == null) {
                Errors.issue(tc.job(),
                        new Errors.SelfMayOnlyBeUsedWithinDependentType(position()));
                tt = ts.unknownType(position());
            }

            // The type of self should not include a dep clause; otherwise
            // self in C{c} could have type C{c}, causing an infinite regress
            // later in type checking.
            tt = Types.baseType(tt);

            assert tt != null;
            return type(tt);
        }
        
        Type t = null;

        CodeDef code = c.currentCode();
        if (code instanceof X10MemberDef) {
            ThisDef thisDef = ((X10MemberDef) code).thisDef();
            if (null == thisDef) {
                throw new InternalCompilerError(position(), "X10Special_c.typeCheck: thisDef is null for containing code " +code);
            }
            assert (thisDef != null);
            c.recordCapturedVariable(thisDef.asInstance());
        }

        if (qualifier == null) {
            // an unqualified "this" 
            t = c.currentClass();

            // If in the class header declaration, make this refer to the current class, not the enclosing class (or null).
            if (c.inSuperTypeDeclaration()) {
                if (kind == SUPER) {
                    Errors.issue(tc.job(),
                            new Errors.CannotReferToSuperFromDeclarationHeader(position()),
                            this);
                }
                t = c.supertypeDeclarationType().asType();
            }

            // Use the constructor return type, not the base type.
            if (c.currentDepType() == null)
                if (code instanceof X10ConstructorDef) {
                    X10ConstructorDef cd = (X10ConstructorDef) code;
                    Type returnType =  (Type) cd.returnType().get();
                    returnType =  ts.expandMacros(returnType);
                    t = returnType;
                }
        }
        else {
            if (qualifier.type().isClass()) {
                ClassType ct = qualifier.type().toClass();
                t=ct;
                if (!c.currentClass().hasEnclosingInstance(ct)) {
                    Errors.issue(tc.job(),
                            new Errors.NestedClassMissingEclosingInstance(c.currentClass(), ct, qualifier.position()),
                            this);
                }
                
            }
            else {
                Errors.issue(tc.job(),
                        new Errors.InvalidQualifierForSuper(qualifier.position()),
                        this);
            }
        }
        
        if (t == null || (c.inStaticContext() && ts.typeEquals(t, c.currentClass(), c))) {
            // trying to access "this" or "super" from a static context.
            Errors.issue(tc.job(),
                    new Errors.CannotAccessNonStaticFromStaticContext(position()));
        }

        X10Special result = this;
        TypeSystem xts = (TypeSystem) ts;
        assert (t.isClass());
        // Instantiate with the class's type arguments
        t = Types.instantiateTypeParametersExplicitly(t);

        if (kind == THIS) {
            Type tt = Types.baseType(t);
            CConstraint cc = Types.xclause(t);
            cc = cc == null ? new CConstraint() : cc.copy();
            try {
                // In case there is a qualifier, bind self to
                // both the thisVar of the corresponding outer context
                // and to qualifier.this, where this is the current this
                // variable
                XTypeTranslator xt = xts.xtypeTranslator();
                XVar var = (XVar)  xt.translate(cc, this, c);
              /*  XVar qualifiedVar = (XVar)  xt.translateSpecialAsQualified(cc, this, c);
                if (qualifiedVar != null && qualifiedVar != var
                        && qualifiedVar instanceof QualifiedVar) {
                    QualifiedVar qVar = (QualifiedVar) qualifiedVar;
                    if (qVar.receiver() != var) {
                        cc.addSelfBinding(qVar);
                    }
                }*/
                if (var != null)  {
                    cc.addSelfBinding(var);
                }
                //PlaceChecker.AddThisHomeEqualsPlaceTerm(cc, var, c);
            } catch (IllegalConstraint z) {
                Errors.issue(tc.job(), z);
            }
            
            tt = Types.xclause(Types.baseType(tt), cc);
            
            result = (X10Special) type(tt);
        }
        else if (kind == SUPER) {
            Type superClass =  Types.superClass(t);
            Type tt = Types.baseType(superClass);
            CConstraint cc = Types.xclause(superClass);
            cc = cc == null ? new CConstraint() : cc.copy();
            try {
                XVar var = (XVar) xts.xtypeTranslator().translate(cc, this, c);
                if (var != null) {
                    cc.addSelfBinding(var);
                    //PlaceChecker.AddThisHomeEqualsPlaceTerm(cc, var, c);
                }
            } catch (IllegalConstraint z) {
            	Errors.issue(tc.job(), z);
            }
           
            tt = Types.xclause(Types.baseType(tt), cc);
            result = (X10Special) type(tt);
        }
       
        assert result.type() != null;

        if (code instanceof X10ProcedureDef) {
            X10ProcedureDef pi = (X10ProcedureDef) code;
            CConstraint guard = Types.get(pi.guard());
            if (guard != null && ! guard.valid()) {
                Type newType = result.type();
                CConstraint dep = Types.xclause(newType);
                if (dep == null)
                    dep = guard.copy();
                else 
                    dep.addIn(guard);
                if (! dep.consistent()) {
                    Errors.issue(tc.job(), new Errors.InconsistentType(newType, position()));
                }
                newType = Types.xclause(Types.baseType(newType), dep);
                return result.type(newType);
            }
        }

        return result;
    }

    public String toString() {
        String typeString = "";
        Type type = type();
        if (type != null && type.isClass()) {
            typeString = "(:" + type.toClass().toString() + ")";
        }

        return (qualifier == null ? "" : qualifier.toString() + ".") + kind + typeString;
    }
}
