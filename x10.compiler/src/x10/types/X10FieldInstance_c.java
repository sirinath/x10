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

import java.util.List;

import polyglot.util.CollectionUtil;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import x10.constraint.XFailure;
import x10.constraint.XLocal;
import x10.constraint.XVar;
import x10.constraint.XTerm;
import x10.types.constraints.CConstraint;
import x10.types.constraints.CConstraint;

/**
 * 
 * @author vj
 *
 */
public class X10FieldInstance_c extends FieldInstance_c implements X10FieldInstance {
    private static final long serialVersionUID = 2257055113898437924L;

    public X10FieldInstance_c(TypeSystem ts, Position pos, Ref<? extends X10FieldDef> def) {
        super(ts, pos, def);
    }
    
    public X10FieldDef x10Def() {
        return (X10FieldDef) def();
    }

    public List<Type> annotations() {
        return X10TypeObjectMixin.annotations(this);
    }
    public List<Type> annotationsMatching(Type t) {
        return X10TypeObjectMixin.annotationsMatching(this, t);
    }

    public boolean isProperty() {
        return x10Def().isProperty();
    }
    
//    public Type leftType() {
//        return super.type();
//    }
    
    @Override
    public X10FieldInstance type(Type type) {
        // clear the right type so we recompute it.
        return type(type, null);
    }

    public X10FieldInstance type(Type type, Type rightType) {
        if (type == this.type) return this;
        X10FieldInstance_c fi =  (X10FieldInstance_c) super.type(type);
        assert fi != this;
        fi.rightType = rightType;
        return fi;
    }
    
    @Override
    public X10FieldInstance container(StructType container) {
        if (container == this.container) return this;
        return (X10FieldInstance) super.container(container);
    }

    Type rightType;

    public Type rightType() {
        X10TypeSystem xts = (X10TypeSystem) ts;
        
        // vj: Force a recomputation if rightType is UnknownType.
        // this.type() may have changed -- though this.type might not!!
        // However we have no way of resetting rightType when
        // this.type() changes. However, this.type() should change only
        // monotonically from uknown to some known value. 
        // Hence force a recompute each time rightType() is called
        // if .rightType is an UnknownType.
        if (rightType == null || xts.isUnknown(rightType)) {
            Type t = type();

            // If the field is final, replace T by T{self==t} 
            Flags flags = flags();

            if (flags.isFinal()) {
                if (xts.isUnknown(t)) {
                    rightType = t;
                }
                else {
                    CConstraint rc = X10TypeMixin.xclause(t);
                    if (rc == null)
                        rc = new CConstraint();

                    XTerm receiver;

                    if (flags.isStatic()) {
                        receiver = xts.xtypeTranslator().trans(container());
                    }
                    else {
                        receiver = x10Def().thisVar();
                        assert receiver != null;
                    }

                    try {
                        CConstraint c = rc.copy();

                        // ### pass in the type rather than letting XField call fi.type();
                        // otherwise, we'll get called recursively.
                        XTerm self = xts.xtypeTranslator().trans(c, receiver, this, t);
                        // Add {self = receiver.field} clause.
                        c.addSelfBinding(self);
                        if (receiver instanceof XVar) {
                        	// this is the case if we are not in static context.
                        	c.setThisVar((XVar) receiver);
                        }

                        rightType = X10TypeMixin.xclause(X10TypeMixin.baseType(t), c);
                    }
                    catch (XFailure f) {
                        throw new InternalCompilerError("Could not add self binding: " + f.getMessage(), f);
                    }
                    catch (SemanticException f) {
                        throw new InternalCompilerError(f);
                    }
                }
            }
            else {
                rightType = t;
            }
            
            assert rightType != null;
        }

        return rightType;
    }

    private SemanticException error;

    public SemanticException error() {
        return error;
    }

    public X10FieldInstance error(SemanticException e) {
        if (e == this.error) return this;
        X10FieldInstance_c n = (X10FieldInstance_c) copy();
        n.error = e;
        return n;
    }
    public String containerString() {
	Type container = container();
	container = X10TypeMixin.baseType(container);
	if (container instanceof FunctionType) {
	    return "(" + container.toString() + ")";
	}
	if (container instanceof Named) {
	    Named n = (Named) container;
	    return n.fullName().toString();
	}
	return container.toString();
    }

    public String toString() {
	Type type = type();
	String typeString = type != null ? type.toString() : def().type().toString();
	String s = "field " + X10Flags.toX10Flags(flags()).prettyPrint() + containerString() + "." + name() + ": " + typeString;
	return s;
    }

    public boolean isValid() {
        return !(def instanceof ErrorRef_c<?>);
    }
}
