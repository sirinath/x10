/**
 * 
 */
package polyglot.ext.x10.types;

import java.util.Collections;
import java.util.List;

import polyglot.types.ClassType;
import polyglot.types.ConstructorDef_c;
import polyglot.types.Flags;
import polyglot.types.LocalDef;
import polyglot.types.Ref;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.CollectionUtil;
import polyglot.util.Position;
import polyglot.util.TypedList;
import x10.constraint.XConstraint;

/**
 * An X10ConstructorInstance_c varies from a ConstructorInstance_c only in that it
 * maintains a returnType. If an explicit returnType is not declared in the constructor
 * then the returnType is simply a noClause variant of the container.
 * @author vj
 *
 */
public class X10ConstructorDef_c extends ConstructorDef_c implements X10ConstructorDef {
    Ref<? extends ClassType> returnType;
    protected Ref<XConstraint> supClause;
    protected Ref<XConstraint> whereClause;
    List<LocalDef> formalNames;

    public X10ConstructorDef_c(TypeSystem ts, Position pos,
            Ref<? extends ClassType> container,
            Flags flags, 
            Ref<? extends ClassType> returnType,
            List<Ref<? extends Type>> typeParameters,
            List<Ref<? extends Type>> formalTypes, 
            List<LocalDef> formalNames, Ref<XConstraint> whereClause,
            List<Ref<? extends Type>> throwTypes) {
        super(ts, pos, container, flags, formalTypes, throwTypes);
        this.returnType = returnType;
        this.typeParameters = TypedList.copyAndCheck(typeParameters, Ref.class, true);
	this.formalNames = TypedList.copyAndCheck(formalNames, LocalDef.class, true);
	this.whereClause = whereClause;
    }

    // BEGIN ANNOTATION MIXIN
    List<Ref<? extends X10ClassType>> annotations;

    public List<Ref<? extends X10ClassType>> defAnnotations() {
        return Collections.unmodifiableList(annotations);
    }
    
    public void setDefAnnotations(List<Ref<? extends X10ClassType>> annotations) {
        this.annotations = TypedList.<Ref<? extends X10ClassType>>copyAndCheck(annotations, Ref.class, true);
    }
    
    public List<X10ClassType> annotations() {
        return X10TypeObjectMixin.annotations(this);
    }
    
    public List<X10ClassType> annotationsMatching(Type t) {
        return X10TypeObjectMixin.annotationsMatching(this, t);
    }
    
    public List<X10ClassType> annotationsNamed(String fullName) {
        return X10TypeObjectMixin.annotationsNamed(this, fullName);
    }
    // END ANNOTATION MIXIN
    
    public Ref<? extends Type> returnType() {
        return this.returnType;
    }

    public void setReturnType(Ref<? extends ClassType> r) {
        this.returnType = r;
    }
	
    public List<LocalDef> formalNames() {
	return Collections.unmodifiableList(formalNames);
    }

    public void setFormalNames(List<LocalDef> formalNames) {
	this.formalNames = TypedList.copyAndCheck(formalNames, LocalDef.class, true);
    }
    
    /** Constraint on superclass constructor call return type. */
    public Ref<XConstraint> supClause() {
	return supClause;
    }
    
    public void setSupClause(Ref<XConstraint> s) {
	this.supClause = s;
    }

    /** Constraint on formal parameters. */
    public Ref<XConstraint> whereClause() {
        return whereClause;
    }

    public void setWhereClause(Ref<XConstraint> s) {
        this.whereClause = s;
    }
	
    List<Ref<? extends Type>> typeParameters;
    public List<Ref<? extends Type>> typeParameters() {
	        return Collections.unmodifiableList(typeParameters);
    }

    public void setTypeParameters(List<Ref<? extends Type>> typeParameters) {
	    this.typeParameters = TypedList.copyAndCheck(typeParameters, Ref.class, true);
    }

    public String toString() {
	    String s = designator() + " " + flags().translate() + container() + "." + signature() + (whereClause() != null ? whereClause() : "") + ": " + returnType();

	    if (!throwTypes().isEmpty()) {
		    s += " throws " + CollectionUtil.listToString(throwTypes());
	    }

	    return s;
    }

    public String signature() {
	    return "this" + (typeParameters.isEmpty() ? "" : typeParameters.toString()) + "(" + CollectionUtil.listToString(formalTypes) + ")";
    }
}
