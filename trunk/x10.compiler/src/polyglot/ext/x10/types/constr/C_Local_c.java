package polyglot.ext.x10.types.constr;

import polyglot.main.Report;
import polyglot.types.LocalInstance;

/**
 * The representation of a local variable reference.
 * @author vj
 *
 */
public class C_Local_c extends C_Var_c implements C_Local {
	public final LocalInstance li;
	public boolean isSelfVar;
	public C_Local_c(LocalInstance li) {
		this(li, false);
	}
	public C_Local_c(LocalInstance li, boolean isSelfVar) {
		super(li.type());
		this.li = li;
		this.isSelfVar=isSelfVar;
	}
	public static final C_Local makeSelfVar(LocalInstance li) {
		return new C_Local_c(li, true);
	}
	public int hashCode() {
		String name = name();
		return ((name == null) ? 0 : name.hashCode()) + (li==null ?  0 : li.hashCode());
	}
	public boolean equals(Object o) {
	//	Report.report(1, "C_Local_c: equals" + this + " " + o + o.getClass());
		if (this==o) return true;
		if (! (o instanceof C_Local_c)) 
			return false;
		C_Local_c other = (C_Local_c) o;
		
		boolean val = // isSelfVar? other.isSelfVar && li.toString().equals(other.toString()) 
				li.typeSystem().equals(li, other.li);
		return val;
	}
	public boolean isSelfVar() { return isSelfVar;}
	public String name() { return li.name();}
	public String toString() { return  name();}
}
