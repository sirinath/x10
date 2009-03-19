package polyglot.ext.x10.ast;

import java.util.List;

import polyglot.ext.x10.types.X10ConstructorInstance;
import polyglot.types.FieldInstance;

public interface AssignPropertyBody extends StmtSeq {
	public X10ConstructorInstance constructorInstance();
	public List<FieldInstance> fieldInstances();
}
