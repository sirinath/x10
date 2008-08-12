package polyglot.ext.x10.types;

import java.util.List;

import polyglot.types.ClassType;
import polyglot.types.MemberDef;
import polyglot.types.Package;
import polyglot.types.ProcedureDef;
import polyglot.types.Ref;
import polyglot.types.Type;
import x10.constraint.XConstraint;

public interface TypeDef extends X10Def, MemberDef, X10ProcedureDef {
	public MacroType asType();

	public Ref<? extends Package> package_();
	public void setPackage(Ref<? extends Package> pkg);
	
	public String name();
	public void setName(String name);

	public List<Ref<? extends Type>> typeParameters();
	public void setTypeParameters(List<Ref<? extends Type>> typeParameters);
	
	public List<Ref<? extends Type>> formalTypes();
	public void setFormalTypes(List<Ref<? extends Type>> formalTypes);

	public Ref<XConstraint> whereClause();
	public void setWhereClause(Ref<XConstraint> whereClause);

	public Ref<? extends Type> definedType();
	public void setType(Ref<? extends Type> type);
}