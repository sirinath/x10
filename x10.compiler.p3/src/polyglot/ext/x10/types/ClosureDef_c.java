/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package polyglot.ext.x10.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import polyglot.types.ClassType;
import polyglot.types.CodeInstance;
import polyglot.types.Def_c;
import polyglot.types.LocalDef;
import polyglot.types.QName;
import polyglot.types.Ref;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.CollectionUtil;
import polyglot.util.Position;
import polyglot.util.TypedList;
import x10.constraint.XConstraint;

public class ClosureDef_c extends Def_c implements ClosureDef {

    protected Ref<? extends CodeInstance<?>> methodContainer;
    protected Ref<? extends ClassType> typeContainer;
    protected Ref<? extends Type> returnType;
    protected List<Ref<? extends Type>> typeParameters;
    protected List<Ref<? extends Type>> formalTypes;
    protected List<LocalDef> formalNames;
    protected List<Ref<? extends Type>> throwTypes;
    protected Ref<XConstraint> guard;
    protected CodeInstance<?> asInstance;

    public ClosureDef_c(TypeSystem ts, Position pos, 
            Ref<? extends ClassType> typeContainer,
            Ref<? extends CodeInstance<?>> methodContainer,
            Ref<? extends Type> returnType,
            List<Ref<? extends Type>> typeParams,
            List<Ref<? extends Type>> formalTypes,
            List<LocalDef> formalNames,
            Ref<XConstraint> guard, 
            List<Ref<? extends Type>> throwTypes) {

        super(ts, pos);
        this.typeContainer = typeContainer;
        this.methodContainer = methodContainer;
        this.returnType = returnType;
        this.typeParameters = TypedList.copyAndCheck(typeParams, Ref.class, true);
        this.formalTypes = TypedList.copyAndCheck(formalTypes, Ref.class, true);
        this.formalNames = TypedList.copyAndCheck(formalNames, LocalDef.class, true);
        this.guard = guard;
        this.throwTypes = TypedList.copyAndCheck(throwTypes, Ref.class, true);
    }
    
    ClosureType asType;
    
    public ClosureType asType() {
	if (asType == null) {
	    X10TypeSystem ts = (X10TypeSystem) this.ts;
	    asType = ts.closureType(position(), returnType, typeParameters, formalTypes, formalNames, guard, throwTypes);
	}
	return asType;
    }
    
    protected boolean inferReturnType;
    public boolean inferReturnType() { return inferReturnType; }
    public void inferReturnType(boolean r) { this.inferReturnType = r; }

    // BEGIN ANNOTATION MIXIN
    List<Ref<? extends Type>> annotations;

    public List<Ref<? extends Type>> defAnnotations() {
	if (annotations == null) return Collections.EMPTY_LIST;
        return Collections.unmodifiableList(annotations);
    }
    
    public void setDefAnnotations(List<Ref<? extends Type>> annotations) {
        this.annotations = TypedList.<Ref<? extends Type>>copyAndCheck(annotations, Ref.class, true);
        this.asInstance = null;
    }
    
    public List<Type> annotations() {
        return X10TypeObjectMixin.annotations(this);
    }
    
    public List<Type> annotationsMatching(Type t) {
        return X10TypeObjectMixin.annotationsMatching(this, t);
    }
    
    public List<Type> annotationsNamed(QName fullName) {
        return X10TypeObjectMixin.annotationsNamed(this, fullName);
    }
    // END ANNOTATION MIXIN

    public ClosureInstance asInstance() {
        if (asInstance == null) {
            asInstance = ((X10TypeSystem) ts).createClosureInstance(position(), Types.ref(this));
        }
        return (ClosureInstance) asInstance;
    }
    
    public Ref<? extends ClassType> typeContainer() {
        return typeContainer;
    }

    public List<Ref<? extends Type>> typeParameters() {
	        return Collections.unmodifiableList(typeParameters);
    }

    public void setTypeParameters(List<Ref<? extends Type>> typeParameters) {
        this.typeParameters = TypedList.copyAndCheck(typeParameters, Ref.class, true);
        this.asInstance = null;
    }
    
    public List<LocalDef> formalNames() {
        return Collections.unmodifiableList(formalNames);
    }

    public void setFormalNames(List<LocalDef> formalNames) {
        this.formalNames = TypedList.copyAndCheck(formalNames, LocalDef.class, true);
        this.asInstance = null;
    }

    public Ref<XConstraint> guard() {
	    return guard;
    }
    
    public void setGuard(Ref<XConstraint> s) {
	    this.guard = s;
        this.asInstance = null;
    }
    
    /**
     * @param container The container to set.
     */
    public void setTypeContainer(Ref<? extends ClassType> container) {
        this.typeContainer = container;
        this.asInstance = null;
    }

    public Ref<? extends CodeInstance<?>> methodContainer() {
        return methodContainer;
    }

    public void setMethodContainer(Ref<? extends CodeInstance<?>> container) {
        methodContainer = container;
        this.asInstance = null;
    }

    public Ref<? extends Type> returnType() {
        return returnType;
    }

    public void setReturnType(Ref<? extends Type> returnType) {
        assert returnType != null;
        this.returnType = returnType;
        this.asInstance = null;
    }


    public List<Ref<? extends Type>> formalTypes() {
        return Collections.unmodifiableList(formalTypes);
    }

    /**
     * @param formalTypes The formalTypes to set.
     */
     public void setFormalTypes(List<Ref<? extends Type>> formalTypes) {
         this.formalTypes = TypedList.copyAndCheck(formalTypes, Ref.class, true);
         this.asInstance = null;
     }

     public List<Ref<? extends Type>> throwTypes() {
         return Collections.unmodifiableList(throwTypes);
     }

     /**
      * @param throwTypes The throwTypes to set.
      */
     public void setThrowTypes(List<Ref<? extends Type>> throwTypes) {
         this.throwTypes = TypedList.copyAndCheck(throwTypes, Ref.class, true);
         this.asInstance = null;
     }

     public String signature() {
         return "(" + CollectionUtil.listToString(formalTypes) + ")";
     }

     public String designator() {
         return "closure";
     }

     public String toString() {
         return designator() + " " + signature() + " => " + returnType();
     }
}
