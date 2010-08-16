/**
 * 
 */
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

package x10.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import polyglot.types.ConstructorDef;
import polyglot.types.ConstructorInstance;
import polyglot.types.ConstructorInstance_c;
import polyglot.types.Context;
import polyglot.types.DerefTransform;
import polyglot.types.ErrorRef_c;
import polyglot.types.LocalDef;
import polyglot.types.LocalInstance;
import polyglot.types.MethodDef;
import polyglot.types.MethodInstance;
import polyglot.types.ProcedureInstance;
import polyglot.types.Ref;
import polyglot.types.ReferenceType;
import polyglot.types.SemanticException;
import polyglot.types.StructType;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.CollectionUtil;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.util.Transformation;
import polyglot.util.TransformingList;
import x10.types.constraints.CConstraint;
import x10.types.constraints.TypeConstraint;


/**
 * An X10ConstructorInstance_c varies from a ConstructorInstance_c only in that it
 * maintains a returnType. If an explicit returnType is not declared in the constructor
 * then the returnType is simply a noClause variant of the container.
 * @author vj
 *
 */
public class X10ConstructorInstance_c extends ConstructorInstance_c implements X10ConstructorInstance {

    public X10ConstructorInstance_c(TypeSystem ts, Position pos, 
    		Ref<? extends X10ConstructorDef> def) {
        super(ts, pos, def);
    }
    
    @Override
    public boolean moreSpecific(ProcedureInstance<ConstructorDef> p, Context context) {
        return X10TypeMixin.moreSpecificImpl(this, p, context);
    }

    public X10ConstructorDef x10Def() {
        return (X10ConstructorDef) def();
    }
    
    public Ref<? extends Type> offerType() {
    	return x10Def().offerType();
    }
    
    public List<Type> annotations() {
        return X10TypeObjectMixin.annotations(this);
    }

    public List<Type> annotationsMatching(Type t) {
        return X10TypeObjectMixin.annotationsMatching(this, t);
    }

    /* (non-Javadoc)
     * @see x10.types.X10ConstructorInstance#depClause()
     */
    public CConstraint constraint() { return X10TypeMixin.realX(returnType()); }

    public Ref<? extends Type> returnType;
    
    public Ref<? extends Type> returnTypeRef() { 
        if (returnType == null) {
            return x10Def().returnType();
        }
	return returnType;
    }
    
    public Type returnType() { 
        if (returnType == null) {
            return x10Def().returnType().get();
        }
        return Types.get(returnType);
    }
    
    public X10ConstructorInstance returnType(Type retType) {
        return returnTypeRef(Types.ref(retType));
    }
    public X10ConstructorInstance returnTypeRef(Ref<? extends Type> retType) {
        X10ConstructorInstance_c n = (X10ConstructorInstance_c) copy();
        n.returnType = retType;
        return n;
    }

    /** Constraint on superclass constructor call return type. */
    public CConstraint supClause() { 
        return Types.get(x10Def().supClause());
        }

    CConstraint guard;
    
    /** Constraint on formal parameters. */
    public CConstraint guard() {
        if (guard == null) 
            return Types.get(x10Def().guard());
        return guard;
    }

    public X10ConstructorInstance guard(CConstraint c) {
        X10ConstructorInstance_c n = (X10ConstructorInstance_c) copy();
        n.guard = c;
        return n;
    }

    /** Constraint on type parameters. */
    protected TypeConstraint typeGuard;
    public TypeConstraint typeGuard() { return typeGuard; }
    public X10ConstructorInstance typeGuard(TypeConstraint s) { 
        X10ConstructorInstance_c n = (X10ConstructorInstance_c) copy();
        n.typeGuard = s; 
        return n;
    }
    
    
    @Override
    public boolean callValid(Type thisType, List<Type> argTypes, Context context) {
    	// this should have been instantiated correctly; if so, the call is valid
    	return true;
    }
    
    public List<Type> typeParameters;

    public List<Type> typeParameters() {
        return Collections.emptyList();
    }

    public X10ConstructorInstance typeParameters(List<Type> typeParameters) {
        if (typeParameters.size() != 0)
            throw new InternalCompilerError("Attempt to set type parameters of a constructor instance: "+this, this.position());
        return this;
    }

    public List<LocalInstance> formalNames;
    
    public List<LocalInstance> formalNames() {
	if (this.formalNames == null) {
	    return new TransformingList(x10Def().formalNames(), new Transformation<LocalDef,LocalInstance>() {
		public LocalInstance transform(LocalDef o) {
		    return o.asInstance();
		}
	    });
	}
	
	return formalNames;
    }
    
    public X10ConstructorInstance formalNames(List<LocalInstance> formalNames) {
	X10ConstructorInstance_c n = (X10ConstructorInstance_c) copy();
	n.formalNames = formalNames;
	return n;
    }

    private SemanticException error;

    public SemanticException error() {
        return error;
    }

    public X10ConstructorInstance error(SemanticException e) {
        X10ConstructorInstance_c n = (X10ConstructorInstance_c) copy();
        n.error = e;
        return n;
    }

    public String toString() {
	    String s = designator() + " " + X10Flags.toX10Flags(flags()).prettyPrint() + container() + "." + signature();
	
	    if (! throwTypes().isEmpty()) {
		    s += " throws " + CollectionUtil.listToString(throwTypes());
	    }
	
	    return s;
    }
    
    public String signature() {
	StringBuilder sb = new StringBuilder();
	sb.append("this");
	List<String> params = new ArrayList<String>();
	if (typeParameters != null) {
	    for (int i = 0; i < typeParameters.size(); i++) {
		params.add(typeParameters.get(i).toString());
	    }
	}
	else {
	    for (int i = 0; i < x10Def().typeParameters().size(); i++) {
		params.add(x10Def().typeParameters().get(i).toString());
	    }
	}
	if (params.size() > 0) {
	    sb.append("[");
	    sb.append(CollectionUtil.listToString(params));
	    sb.append("]");
	}
	List<String> formals = new ArrayList<String>();
	if (formalTypes != null) {
	    for (int i = 0; i < formalTypes.size(); i++) {
		String s = "";
		String t = formalTypes.get(i).toString();
		if (formalNames != null && i < formalNames.size()) {
		    LocalInstance a = formalNames.get(i);
		    if (a != null && ! a.name().toString().equals(""))
			s = a.name() + ": " + t; 
		    else
			s = t;
		}
		else {
		    s = t;
		}
		formals.add(s);
	    }
	}
	else {
	    for (int i = 0; i < def().formalTypes().size(); i++) {
		formals.add(def().formalTypes().get(i).toString());
	    }
	}
	sb.append("(");
	sb.append(CollectionUtil.listToString(formals));
	sb.append(")");
	if (guard != null)
	    sb.append(guard);
	if (returnType != null && returnType.known()) {
	    sb.append(": ");
	    sb.append(returnType);
	}
	return sb.toString();
    }
    
    public boolean isValid() {
        return !(def instanceof ErrorRef_c<?>);
    }
}
