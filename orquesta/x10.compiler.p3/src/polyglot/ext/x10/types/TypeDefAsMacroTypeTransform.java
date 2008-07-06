package polyglot.ext.x10.types;

import polyglot.types.Type;
import polyglot.util.InternalCompilerError;
import polyglot.util.Transformation;

public class TypeDefAsMacroTypeTransform implements Transformation<TypeDef, Type> {
    public Type transform(TypeDef def) {
	X10TypeSystem xts = (X10TypeSystem) def.typeSystem();
	MacroType mt = def.asType();
	if (!mt.flags().isStatic()) {
	    throw new InternalCompilerError("unimplemented");
	}
	return mt;
    }
}
