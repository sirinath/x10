/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.types.constr;

import polyglot.ast.Special;
import polyglot.ast.TypeNode;
import polyglot.ext.x10.ast.X10Special;
import polyglot.types.Type;

public class C_Special_c extends C_Var_c implements C_Special {
	
	
	public final Type qualifier;
	public final C_Kind kind;
	
	public C_Special_c(X10Special t) {
		super(t.type(), t.kind().equals(Special.THIS), t.kind().equals(Special.SUPER));
		kind= C_Special.C_Kind.trans(t.kind());
		TypeNode tn = t.qualifier();
		qualifier = tn==null? null : tn.type();
	}
	

	public C_Special_c(X10Special.Kind k, Type t) {
		super(t);
		kind= C_Special.C_Kind.trans(k);
		qualifier=null;
	}
	
	public C_Var rootVar() {
		return this;
	}
	public boolean isEQV() {
		return false;
	}
	protected Path path = new Path(new String[0]);
	protected C_Var[] vars = new C_Var[] { this };
	public C_Var[] vars() { return vars; }
	public Path path() {
		return path;
	}
	
	public C_Kind kind() {
		return kind;
	}
	public String name() { return kind.toString();}
	public Type qualifier() {
		return qualifier;
	}
	public int hashCode() {
		return 0;
		//return ((qualifier == null) ? 0 : qualifier.hashCode());
		//+ (kind==null ?  0 : kind.hashCode());
	}
	public boolean equals(Object o) {
		if (this==o) return true;
		if (! (o instanceof C_Special_c))
			return false;
		C_Special_c other = (C_Special_c) o;
		boolean val = (qualifier == null ?  other.qualifier==null : qualifier.equals(other.qualifier))
		&& kind.equals(other.kind);
		return val;
	}
	public boolean prefixes(C_Term t) {
		if (equals(t)) return true;
		if (! (t instanceof C_Var)) return false;
		C_Var[] vars = ((C_Var) t).vars();
		return equals(vars[0]);
	}
	public String toString() { return (qualifier==null? "" : qualifier + ".") + kind.toString();}
}
