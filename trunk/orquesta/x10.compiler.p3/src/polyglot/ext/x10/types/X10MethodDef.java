package polyglot.ext.x10.types;

import java.util.List;

import polyglot.types.MethodDef;
import polyglot.types.ReferenceType;
import polyglot.types.SemanticException;
import polyglot.types.Type;

public interface X10MethodDef extends MethodDef, X10ProcedureDef {

    /** Set a flag indicating we should infer the return type. */
    boolean inferReturnType();
    void inferReturnType(boolean r);
    
    /**
     * Return an instance of this, specialized with (a) any references
     * to this in the dependent type of the result replaced by
     * selfVar of thisType or an EQV of thisType (with propagation) 
     * (b) any references to this in the dependent
     * type T of an argument replaced by selfVar of thisType or an EQV
     * at T, with no propagation.
     * @param thisType
     * @param argTypes TODO
     * @return
     * @throws SemanticException 
     */
//    X10MethodInstance instantiateForThis(ReferenceType thisType, List<Type> argTypes) throws SemanticException;
}
