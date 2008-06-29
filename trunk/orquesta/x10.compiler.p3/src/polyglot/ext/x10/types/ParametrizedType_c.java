package polyglot.ext.x10.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import polyglot.types.Def;
import polyglot.types.DerefTransform;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.MemberDef;
import polyglot.types.MethodInstance;
import polyglot.types.Named;
import polyglot.types.Ref;
import polyglot.types.ReferenceType;
import polyglot.types.ReferenceType_c;
import polyglot.types.Resolver;
import polyglot.types.StructType;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.TypeSystem;
import polyglot.types.Type_c;
import polyglot.types.Types;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.util.Transformation;
import polyglot.util.TransformingList;
import polyglot.util.TypedList;
import x10.constraint.XConstraint;
import x10.constraint.XNameWrapper;
import x10.constraint.XTerms;
import x10.constraint.XVar;

public abstract class ParametrizedType_c extends ReferenceType_c implements ParametrizedType {
	StructType container;
	Flags flags;
	String name;

	public ParametrizedType_c(TypeSystem ts, Position pos) {
		super(ts, pos);
	}
	
	public ParametrizedType container(StructType container) {
		ParametrizedType_c t = (ParametrizedType_c) copy();
		t.container = container;
		return t;
	}

	public StructType container() {
		if (this.container == null) {
			this.container = Types.get(def().container());
		}
		return this.container;
	}
	
	public Flags flags() {
		if (this.flags == null) { 
			this.flags = def().flags();
		}
		return this.flags;
	}

	public ParametrizedType flags(Flags flags) {
		ParametrizedType_c t = (ParametrizedType_c) copy();
		t.flags = flags;
		return t;
	}
	
	public ParametrizedType name(String name) {
		ParametrizedType_c t = (ParametrizedType_c) copy();
		t.name = name;
		return t;
	}

	public boolean safe() {
		return true;
	}

	@Override
	public abstract String translate(Resolver c);

	public abstract MemberDef def();
	
	public String fullName() {
		if (container() instanceof Named) {
			return ((Named) container()).fullName() + "." + name();
		}
		return name();
	}
	
	@Override
	public boolean equalsImpl(TypeObject t) {
		if (t instanceof ParametrizedType) {
			ParametrizedType pt = (ParametrizedType) t;
			if (pt.def() != def()) return false;
			if (! pt.typeParams().equals(typeParams())) return false;
			if (! pt.formals().equals(formals())) return false;
			if (! pt.formalTypes().equals(formalTypes())) return false;
			return true;
		}
		return false;
	}
}
