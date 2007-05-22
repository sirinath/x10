/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import polyglot.types.FieldInstance_c;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ext.x10.ast.X10LocalDecl_c;
import polyglot.ext.x10.types.constr.C_Field;
import polyglot.ext.x10.types.constr.C_Field_c;
import polyglot.ext.x10.types.constr.C_Local_c;
import polyglot.ext.x10.types.constr.C_Special_c;
import polyglot.ext.x10.types.constr.C_Var;
import polyglot.ext.x10.types.constr.Constraint;
import polyglot.ext.x10.types.constr.Constraint_c;
import polyglot.ext.x10.types.constr.TypeTranslator;
import polyglot.main.Report;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.ReferenceType;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.visit.TypeChecker;

/**
 * An implementation of PropertyInstance
 * @author vj
 *
 */
public class X10FieldInstance_c extends FieldInstance_c implements X10FieldInstance {

	protected List<X10ClassType> annotations;
	
	public List<X10ClassType> annotations() {
		if (annotations == null) return Collections.EMPTY_LIST;
		return Collections.<X10ClassType>unmodifiableList(annotations);
	}
	
	public void setAnnotations(List<X10ClassType> annotations) {
		this.annotations = new ArrayList<X10ClassType>(annotations);
	}
	public X10TypeObject annotations(List<X10ClassType> annotations) {
		X10ReferenceType_c n = (X10ReferenceType_c) copy();
		n.setAnnotations(annotations);
		return n;
	}
	public X10ClassType annotationNamed(String name) {
		for (Iterator<X10ClassType> i = annotations.iterator(); i.hasNext(); ) {
			X10ClassType ct = i.next();
			if (ct.fullName().equals(name)) {
				return ct;
			}
		}
		return null;
	}
	
    public X10FieldInstance_c() {
        super();
    }

    protected boolean depTypeSet;
    public X10FieldInstance_c(TypeSystem ts, Position pos,
            ReferenceType container, Flags flags, Type type, String name) {
        super(ts, pos, container, flags, type, name);
        
    }
    
    public X10FieldInstance_c(TypeSystem ts, Position pos,
			   ReferenceType container,
	                   Flags flags,  String name, String initValue) {
     super(ts, pos, container, flags, ts.String(), name);
     setConstantValue(initValue);
 }
    
    public void setDepType(Type type) {
    	setType(type);
    	this.depTypeSet=true;
    }
    public boolean depTypeSet() { 
    	return depTypeSet;
    }
    /**
     * A PropertyInstance is equal to another TypeObject only if the other TypeObject
     * represents a property, and the two are equal when viewed as fields.
     */
    public boolean equalsImpl(TypeObject o) {
        if (o instanceof X10FieldInstance) {
        X10FieldInstance i = (X10FieldInstance) o;
        return super.equalsImpl(i);
    }

    return false;
    }
    
    boolean isPropertyInitialized = false;
    boolean isProperty = false;
    
    public void setProperty() {
    	isPropertyInitialized = isProperty = true;
    }
    public boolean isProperty() {
    	
    	if (isPropertyInitialized) return isProperty;
    	
    	if (!(container instanceof X10ParsedClassType)) 
    		return isProperty=false;
    	
    	X10ParsedClassType p = (X10ParsedClassType) container;
    	List props = p.properties();
    	// this must occur after the call to properties(), which may throw an exception.
    	isPropertyInitialized = true;
    	for (Iterator i=props.iterator(); i.hasNext(); ) {
    		
    		FieldInstance f = (FieldInstance) i.next();
    		
    		if (f.equals(this)) 
    			return isProperty=true;
    		
    	}
    	return isProperty=false;
    }
	
    public boolean setSelfClauseIfFinal() {
		// If the field is final, replace T by T(:self==t), 
		// do this even if depclause==null
		boolean changed = false;
		if ( flags().isFinal()) {
			X10Type t = (X10Type) type();
			C_Field self = new C_Field_c(this, C_Special_c.This);
			Constraint c = Constraint_c.addSelfBinding(self, t.depClause(), (X10TypeSystem) ts);
			X10Type newType = t.makeVariant(c,t.typeParameters());
			setType(newType);
			changed = true;
		}
		return changed;
		
	}

    
}
