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

package x10.types.matcher;

import polyglot.types.Context;
import polyglot.types.FieldInstance;
import polyglot.types.Name;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem_c;
import polyglot.types.Types;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import x10.constraint.XConstraint;
import x10.constraint.XFailure;
import x10.constraint.XTerm;
import x10.constraint.XVar;
import x10.types.constraints.ConstraintManager;
import x10.constraint.XVar;
import x10.errors.Errors;
import x10.types.ParameterType;
import polyglot.types.Context;
import x10.types.X10FieldInstance;
import polyglot.types.TypeSystem;
import x10.types.constraints.CConstraint;

public class X10FieldMatcher {
    public static Type instantiateAccess(Type container, Type t, XVar<Type> oldThis, 
    		boolean contextKnowsReceiver) throws SemanticException {
        assert container!=null && t!=null;
        CConstraint c = Types.xclause(container);
        // lshadare why not just set this to self of c?
        // Let v be the symbolic name for the target. If there is none, we make one up.
        // Let t = T{tc}, and ct = U{c}.
        // If c does not have a selfVarBinding, then we want to set t to
        // t = T{exists vv. (tc,this==vv),ct[vv/self]}
        // If c does have a selfVarBinding, v, then we want to set t to
        // t = T{exists v. (tc, this=v, ct)}
        XTerm<Type> v = Types.selfVarBinding(container);
        XVar<Type> vv = null;
        if (v == null) {
        	// lshadare why are we making a universally quantified variable here?
        	v = vv =ConstraintManager.getConstraintSystem().makeUQV(Types.baseType(container));
        }
        if (oldThis != null && v == null && vv==null)
        	assert false;
        /*if (c != null)
        	c = c.copy().instantiateSelf(v);*/

        { // Update t
            CConstraint tconst = Types.realX(t);
       
            if (! contextKnowsReceiver) {
            	tconst.addIn(v, c);
            	//CConstraint tc2 = tconst.copy().project(tconst.self());
            	CConstraint tc2 = tconst.exists();
            	t = Subst.addIn(t, tc2);
            }
            t = Types.constrainedType(Types.baseType(t), tconst);
            
            t = Subst.subst(t,
                            new XTerm[] {v},
                            new XTerm[] {oldThis},
                            new Type[] {}, new ParameterType[] {});
            // lshadare the only way vv is not null is if it is equal to v so we are essentially 
            // substituting oldThis by a new EQV
            if (vv != null) { // Hide vv, i.e. substitute in an anonymous EQV
                t = Subst.subst(t,
                                new XTerm[] {ConstraintManager.getConstraintSystem().makeEQV(vv.type())},
                                new XTerm[] {vv},
                                new Type[] {}, new ParameterType[] {});
            }
            final CConstraint tmpTc = Types.realX(t).copy();
            tmpTc.addIn(v,c);
            if (! tmpTc.consistent()) {
                throw new Errors.InconsistentType(t, t.position());
            }
        }
        return t;
    }
    public static X10FieldInstance instantiateAccess(X10FieldInstance fi, Name name, Type container, boolean contextKnowsReceiver, Context context) throws SemanticException {
	    if (! fi.name().equals(name)) {
		return null;
	    }
        TypeSystem ts = fi.typeSystem();
        Type t = fi.type();
        Type rt = fi.rightType();
        
        // Now need to figure out the type of the field, from the declaration of the field
        // in the container, and the type of the container.
        // The task is to transfer constraints from the target to the field.
        Type ct = container != null ? container : fi.container();
        CConstraint c = Types.xclause(ct);

        // Let v be the symbolic name for the target. If there is none, we make one up.
        // Let t = T{tc}, and ct = U{c}.
        // If c does not have a selfVarBinding, then we want to set t to
        // t = T{exists vv. (tc,this==vv),ct[vv/self]}
        // If c does have a selfVarBinding, v, then we want to set t to
        // t = T{exists v. (tc, this=v, ct)}
        // lshadare: dead code
        //XTerm<Type> v = Types.selfVarBinding(ct);
        //XTerm<Type> vv = null;
        //if (v == null) {
        //	v = vv =ConstraintManager.getConstraintSystem().makeUQV(Types.baseType(ct));
        //}
        XVar<Type> oldThis = fi.x10Def().thisVar();
//        if (oldThis != null && v == null && vv==null)
//        	assert false;
        /*if (c != null)
        	c = c.copy().instantiateSelf(v);*/

        t = instantiateAccess(ct,t,oldThis, contextKnowsReceiver);
        rt = instantiateAccess(ct,rt,oldThis, contextKnowsReceiver);
        
        //rt = Subst.subst(rt, (new XVar[] { w }), (new XVar[] { oldThis }), new Type[] {}, new ParameterType[] {});
        //if (v != null)
        //	rt = X10TypeMixin.setThisVar(rt, v);
        // }

        if (!ts.consistent(t, context)) {
            throw new Errors.InconsistentType(t, Position.COMPILER_GENERATED);
        }
        if (!ts.consistent(rt, context)) {
            throw new Errors.InconsistentType(rt, Position.COMPILER_GENERATED);
        }

        return fi.type(t, rt);
    }
}