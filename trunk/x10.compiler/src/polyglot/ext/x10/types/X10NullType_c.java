/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * Created on Nov 28, 2004
 *
 */
package polyglot.ext.x10.types;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import polyglot.types.NullType_c;
import polyglot.ext.x10.ast.GenParameterExpr;
import polyglot.ext.x10.types.constr.C_Term;
import polyglot.ext.x10.types.constr.C_Var;
import polyglot.ext.x10.types.constr.Constraint;
import polyglot.ext.x10.types.constr.Constraint_c;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.TypeSystem;

/** Every X10 term must have a type. This is the type of the X10 term null.
 * Note that there is no X10 type called Null; only the term null.
 * @author vj
 *
 */
public class X10NullType_c extends NullType_c implements X10NullType {
	 /** Used for deserializing types. */
    protected X10NullType_c() {}
    public X10NullType_c( TypeSystem ts ) {super(ts);}
   
    protected Constraint depClause;
    protected List/*<GenParameterExpr>*/ typeParameters;
    protected X10Type rootType = this;
    public X10Type rootType() { return rootType;}
    public boolean isRootType() { return rootType==this;}
    public boolean isParametric() { return typeParameters != null && ! typeParameters.isEmpty();}
    public List typeParameters() { return typeParameters;}
    public Constraint depClause() { return depClause; }
    public Constraint realClause() { return depClause; }
    public boolean isConstrained() { return depClause !=null && ! depClause.valid();}
	public C_Var selfVar() { return depClause()==null ? null : depClause().selfVar();}
	public boolean propertiesElaborated() { return true; }
    public void setDepGen(Constraint d, List/*<GenParameterExpr>*/ l) {
		depClause = d;
		typeParameters = l;
	}
    public void addBinding(C_Term t1, C_Term t2) {
		if (depClause == null)
			depClause = new Constraint_c();
		depClause = depClause.addBinding(t1, t2);
	}
    public boolean consistent() {
    	return depClause== null || depClause.consistent();
    }
    public X10Type makeDepVariant(Constraint d, List<Type> l) { 
        return makeVariant(d, l);
      }
   
    public X10Type makeVariant(Constraint d, List<Type> l) { 
    	// Need to pick up the typeparameters from this
    	// made, and the realClause from the root type.
    	if (d == null && (l == null || l.isEmpty())) return this;
    	X10NullType_c n = (X10NullType_c) copy();
    	n.typeParameters = (l==null || l.isEmpty())? typeParameters : l;
    	n.depClause = d;
    	return n;
    }
      public X10Type makeNoClauseVariant() {
  		X10NullType_c n = (X10NullType_c) copy();
  		n.depClause = new Constraint_c();
  		n.typeParameters = typeParameters;
  		return n;
  	}
    
    public String name() { return "NULL_TYPE";}
    public String fullName() { return "NULL_TYPE";}
    
    public C_Term propVal(String name) {
		return (depClause==null) ? null : depClause.find(name);
	}
    public boolean typeEqualsImpl(Type o) {
        return equalsImpl(o);
    }
    public int hashCode() {
        return 
          (rootType == this ? super.hashCode() : rootType.hashCode() ) 
          + (isConstrained() ? depClause.hashCode() : 0)
  		+ (isParametric() ? typeParameters.hashCode() :0);
        
    }
    
    public boolean equalsImpl(TypeObject o) {
        if (o == this) return true;
        if (! (o instanceof X10NullType_c)) return false;
        X10NullType_c other = (X10NullType_c) o;
        if (rootType != other.rootType) return false;
        
        if (depClause == null && other.depClause != null) return false;
        if (depClause != null && ! depClause.equals(other.depClause)) return false;
        
        if (typeParameters == null) return other.typeParameters == null;
        if (typeParameters.isEmpty()) return other.typeParameters == null || other.typeParameters.isEmpty();
        if (typeParameters.size() != other.typeParameters.size()) return false;
        Iterator it1 = typeParameters.iterator();
        Iterator it2 = other.typeParameters.iterator();
        while (it1.hasNext()) {
            Type t1 = (Type) it1.next();
            Type t2 = (Type) it2.next();
            if (!t1.equals(t2)) return false;
        }
        return true;
    }
    public boolean equalsWithoutClauseImpl(X10Type o) {
        if (o == this) return true;
        if (! (o instanceof X10NullType_c)) return false;
        X10NullType_c other = (X10NullType_c) o;
    
        return rootType == other.rootType;
    }
    
    /**
     * This is different from the definition of jl.Nullable. X10 does not 
     * assume that every reference type contains null. The type must be nullable
     * for it to contain null.
     * TODO: Check if the result should be just: targetType.isNullable().
     */
    public boolean isImplicitCastValidImpl(Type toType) {
    	X10Type targetType = (X10Type) toType;
    	return toType.isNull() || ((X10TypeSystem) ts).isNullable(targetType);
    }	

    /** 
     * TODO: vj -- check if this implementation is correct.
     * The definition of descendsFrom in TypeSystem is
     * Returns true iff child is not ancestor, but child descends from ancestor. 
     * In the X10 type system, the Null type should not descend from any type.
     */
    public boolean descendsFromImpl(Type ancestor) {
    	return ts.equals(ancestor, ts.Object());
        // if (ancestor.isNull()) return false;
        // if (ancestor.isReference()) return true;
        // return false;
    }

    /**
     * Same as isImplicitCastValidImpl.
     **/
    public boolean isCastValidImpl(Type toType) {
    	X10Type targetType = (X10Type) toType;
        return toType.isNull() || ((X10TypeSystem) ts).isNullable(targetType);
    }

    public List properties() { return Collections.EMPTY_LIST;}
    public List definedProperties() { return Collections.EMPTY_LIST;}
    
    public NullableType toNullable() { return X10Type_c.toNullable(this);}
    public FutureType toFuture() { return X10Type_c.toFuture(this);}
    public boolean safe() { return true;}
    public String toStringForDisplay() { 
    	return "null";
    }
	
}
