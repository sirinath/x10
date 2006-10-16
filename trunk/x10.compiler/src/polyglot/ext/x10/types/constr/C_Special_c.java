package polyglot.ext.x10.types.constr;

import polyglot.ast.TypeNode;
import polyglot.ext.x10.ast.X10Special;
import polyglot.types.Type;

public class C_Special_c extends C_Var_c implements C_Special {
	
	
	public final Type qualifier;
	public final C_Kind kind;
	
	public C_Special_c(X10Special t) {
		super(t.type());
		kind= C_Special.C_Kind.trans(t.kind());
		TypeNode tn = t.qualifier();
		qualifier = tn==null? null : tn.type();
	}
	

	public C_Special_c(X10Special.Kind k, Type t) {
		super(t);
		kind= C_Special.C_Kind.trans(k);
		qualifier=null;
	}
	
 
	
	public C_Kind kind() {
		return kind;
	}
	public String name() { return kind.toString();}
	public Type qualifier() {
		return qualifier;
	}
	public int hashCode() {
		return ((qualifier == null) ? 0 : qualifier.hashCode()) + (kind==null ?  0 : kind.hashCode());
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
	public String toString() { return (qualifier==null? "" : qualifier + ".") + kind.toString();}
}
