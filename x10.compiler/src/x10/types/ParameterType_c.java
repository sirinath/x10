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

import polyglot.types.Def;
import polyglot.types.Flags;
import polyglot.types.Named;
import polyglot.types.ProcedureDef;
import polyglot.types.QName;
import polyglot.types.Ref;
import polyglot.types.Resolver;
import polyglot.types.Name;
import polyglot.types.TypeObject;
import polyglot.types.Type_c;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;

public class ParameterType_c extends Type_c implements ParameterType {
    private static final long serialVersionUID = 995307749745291345L;

    Name name;
	Ref<? extends Def> def;
	
	public ParameterType_c(X10TypeSystem ts, Position pos, Name name, Ref<? extends Def> def) {
		super(ts, pos);
		this.name = name;
		this.def = def;
	}

    public Variance getVariance() {
        Def def = def().get();
        // either method generic param (which are always invariant) or class param
        if (!(def instanceof X10ClassDef_c)) return Variance.INVARIANT;
        X10ClassDef_c classDef = (X10ClassDef_c) def;
        return classDef.getVariance(this);
    }
	public boolean isGloballyAccessible() {
	    return false;
	}
	
	public QName fullName() {
		return QName.make(null, name);
	}
	
	public Name name() {
		return name;
	}

	@Override
	public String toString() {
	    return name.toString();
	}

	@Override
	public String translate(Resolver c) {
		return name.toString();
	}

	public boolean isSafe() {
		return false;
	}
	
	@Override
	public boolean equalsImpl(TypeObject t) {
		if (t instanceof ParameterType) {
			ParameterType pt = (ParameterType) t;
			return def == pt.def() && name.equals(pt.name());
		}
		return false;
	}

	public Ref<? extends Def> def() {
		return def;
	}

	public ParameterType def(Ref<? extends Def> def) {
		ParameterType_c n = (ParameterType_c) copy();
		n.def = def;
		return n;
	}

    public boolean permitsNull() { return false;}

    
}
