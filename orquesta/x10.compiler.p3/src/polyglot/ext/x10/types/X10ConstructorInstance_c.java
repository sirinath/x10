/**
 * 
 */
package polyglot.ext.x10.types;

import java.util.List;

import polyglot.types.ConstructorDef;
import polyglot.types.ConstructorInstance;
import polyglot.types.ConstructorInstance_c;
import polyglot.types.DerefTransform;
import polyglot.types.MethodInstance;
import polyglot.types.ProcedureInstance;
import polyglot.types.Ref;
import polyglot.types.ReferenceType;
import polyglot.types.SemanticException;
import polyglot.types.StructType;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.CollectionUtil;
import polyglot.util.Position;
import polyglot.util.TransformingList;
import x10.constraint.XConstraint;

/**
 * An X10ConstructorInstance_c varies from a ConstructorInstance_c only in that it
 * maintains a returnType. If an explicit returnType is not declared in the constructor
 * then the returnType is simply a noClause variant of the container.
 * @author vj
 *
 */
public class X10ConstructorInstance_c extends ConstructorInstance_c implements X10ConstructorInstance {

    public X10ConstructorInstance_c(TypeSystem ts, Position pos, Ref<? extends X10ConstructorDef> def) {
        super(ts, pos, def);
    }
    
    public X10ConstructorDef x10Def() {
        return (X10ConstructorDef) def();
    }
    
    public List<X10ClassType> annotations() {
        return X10TypeObjectMixin.annotations(this);
    }

    public List<X10ClassType> annotationsMatching(Type t) {
        return X10TypeObjectMixin.annotationsMatching(this, t);
    }

    /* (non-Javadoc)
     * @see polyglot.ext.x10.types.X10ConstructorInstance#depClause()
     */
    public XConstraint constraint() { return X10TypeMixin.realX(returnType()); }

    public Type returnType;
    
    public Type returnType() { 
        if (returnType == null) {
            returnType = x10Def().returnType().get();
        }
	return returnType;
    }
    
    public X10ConstructorInstance returnType(Type retType) {
        X10ConstructorInstance_c n = (X10ConstructorInstance_c) copy();
        n.returnType = retType;
        return n;
    }

    /** Constraint on superclass constructor call return type. */
    public XConstraint supClause() { 
        return Types.get(x10Def().supClause());
        }

    XConstraint whereClause;
    
    /** Constraint on formal parameters. */
    public XConstraint whereClause() {
        if (whereClause == null) 
            whereClause = Types.get(x10Def().whereClause());
        return whereClause;
    }

    public X10ConstructorInstance whereClause(XConstraint c) {
        X10ConstructorInstance_c n = (X10ConstructorInstance_c) copy();
        n.whereClause = c;
        return n;
    }

    public boolean callValidNoClauses(Type thisType, List<Type> argTypes) {
        X10ConstructorInstance_c me = (X10ConstructorInstance_c) this.formalTypes(new TransformingList<Type,Type>(this.formalTypes(), new X10MethodInstance_c.NoClauseVariant()));
        return me.superCallValid(thisType, new TransformingList<Type,Type>(argTypes, new X10MethodInstance_c.NoClauseVariant()));
    }
    
    protected boolean superCallValid(Type thisType, List<Type> argTypes) {
        return super.callValid(thisType, argTypes);
    }
    
    @Override
    public boolean callValid(Type thisType, List<Type> argTypes) {
        return X10MethodInstance_c.callValidImpl(this, thisType, argTypes);
    }
    
    public List<Type> typeParameters;

    public List<Type> typeParameters() {
	    if (this.typeParameters == null) {
		    this.typeParameters = new TransformingList<Ref<? extends Type>, Type>(x10Def().typeParameters(), new DerefTransform<Type>());
	    }

	    return typeParameters;
    }

    public X10ConstructorInstance typeParameters(List<Type> typeParameters) {
	    X10ConstructorInstance_c n = (X10ConstructorInstance_c) copy();
	    n.typeParameters = typeParameters;
	    return n;
    }
    
    @Override
    public ConstructorInstance instantiate(StructType receiverType,
    		List<Type> argumentTypes) throws SemanticException {

	    return X10MethodInstance_c.instantiate(this, receiverType, argumentTypes);
    }

    public String toString() {
	    String s = designator() + " " + flags().translate() + container() + "." + signature() + (whereClause() != null ? whereClause() : "") + ": " + returnType();
	
	    if (! throwTypes().isEmpty()) {
		    s += " throws " + CollectionUtil.listToString(throwTypes());
	    }
	
	    return s;
    }
    
    public String signature() {
	    X10ConstructorDef_c def = (X10ConstructorDef_c) def();
	    return "this" + (typeParameters().isEmpty() ? "" : "[" + CollectionUtil.listToString(typeParameters()) + "]") + "(" + CollectionUtil.listToString(formalTypes()) + ")";
    }
}
