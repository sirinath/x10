/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.types;

import java.util.List;

import polyglot.types.FieldInstance_c;
import polyglot.types.Flags;
import polyglot.types.Named;
import polyglot.types.Ref;
import polyglot.types.ReferenceType;
import polyglot.types.SemanticException;
import polyglot.types.StructType;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.types.UnknownType;
import polyglot.util.CollectionUtil;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import x10.constraint.XConstraint;
import x10.constraint.XConstraint_c;
import x10.constraint.XFailure;
import x10.constraint.XTerm;

/**
 * An implementation of PropertyInstance
 * @author vj
 *
 */
public class X10FieldInstance_c extends FieldInstance_c implements X10FieldInstance {

    public X10FieldInstance_c(TypeSystem ts, Position pos, Ref<? extends X10FieldDef> def) {
        super(ts, pos, def);
    }
    
    public X10FieldDef x10Def() {
        return (X10FieldDef) def();
    }

    /** Constraint on formal parameters. */
    protected XConstraint guard;
    public XConstraint guard() { return guard; }
    public X10FieldInstance guard(XConstraint s) { 
	X10FieldInstance_c n = (X10FieldInstance_c) copy();
	n.guard = s; 
	return n;
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
    
    public Type type() {
        X10TypeSystem xts = (X10TypeSystem) ts;

        if (type == null) {
            Type t = super.type();

            // If the field is final, replace T by T(:self==t). 
            Flags flags = flags();

            if (flags.isFinal()) {
                if (t instanceof UnknownType) {
                    type = t;
                }
                else {
                    XConstraint rc = X10TypeMixin.xclause(t);
                    if (rc == null)
                        rc = new XConstraint_c();

                    try {
                        XTerm receiver;

                        if (flags.isStatic()) {
                            receiver = xts.xtypeTranslator().trans(container());
                        }
                        else {
                            receiver = xts.xtypeTranslator().transThis(container());
                        }

                        XConstraint c = rc.copy();

                        // ### pass in the type rather than letting XField call fi.type();
                        // otherwise, we'll get called recursively.
                        XTerm self = xts.xtypeTranslator().trans(c, receiver, this, t);
                        c.addSelfBinding(self);

                        type = X10TypeMixin.xclause(t, c);
                    }
                    catch (XFailure f) {
                        throw new InternalCompilerError("Could not add self binding.", f);
                    }
                    catch (SemanticException f) {
                        throw new InternalCompilerError("Could not add self binding.", f);
                    }
                }
            }
            else {
                type = t;
            }
            
            assert type != null;
        }

        return type;
    }

    public String containerString() {
	Type container = container();
	container = X10TypeMixin.baseType(container);
	if (container instanceof ClosureType) {
	    return "(" + container.toString() + ")";
	}
	if (container instanceof Named) {
	    Named n = (Named) container;
	    return n.fullName().toString();
	}
	return container.toString();
    }

    public String toString() {
	String typeString = type != null ? type.toString() : def().type().toString();
	String s = "field " + X10Flags.toX10Flags(flags()).prettyPrint() + containerString() + "." + name() + (guard() != null ? guard() : "") + ": " + typeString;
	return s;
    }

    
}
