/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * Created on Nov 30, 2004
 */
package polyglot.ext.x10.types;

import java.util.Iterator;
import java.util.List;

import polyglot.types.ReferenceType_c;
import polyglot.ext.x10.ast.GenParameterExpr;
import polyglot.ext.x10.types.constr.C_Term;
import polyglot.ext.x10.types.constr.C_Var;
import polyglot.ext.x10.types.constr.Constraint;
import polyglot.ext.x10.types.constr.Constraint_c;
import polyglot.main.Report;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.TypeSystem;
import polyglot.util.Position;

/** Implements an X10ReferenceType. We have it inherit from ReferenceType_c because
 * there is a lot of code there, and manually "mix-in" the code from X10Type_c.
 * @author vj
 *
 * 
 */
public abstract class X10ReferenceType_c extends ReferenceType_c implements
		X10ReferenceType {
	
    
    protected X10ReferenceType_c() { super();}
    public X10ReferenceType_c(TypeSystem ts) { this(ts, null);}
    public X10ReferenceType_c(TypeSystem ts, Position pos) { super(ts, pos);}
    
    protected Constraint depClause;
    protected List/*<GenParameterExpr>*/ typeParameters;
    protected X10Type rootType = this;
    public X10Type rootType() { return rootType;}
    public boolean isRootType() { return this==rootType;}
    public boolean isParametric() { return typeParameters != null && ! typeParameters.isEmpty();}
    public List typeParameters() { return typeParameters;}
    public Constraint depClause() { return depClause; }
    public Constraint realClause() { return depClause; }
    public boolean isConstrained() { return depClause !=null && ! depClause.valid();}
    public C_Var selfVar() { return depClause()==null ? null : depClause().selfVar();}
    public void setSelfVar(C_Var v) {
		Constraint c = depClause();
		if (c==null) {
			depClause=new Constraint_c();
		}
		depClause.setSelfVar(v);
	}
    public void setDepGen(Constraint d, List/*<GenParameterExpr>*/ l) {
		depClause = d;
		typeParameters = l;
	}
    public void addBinding(C_Var t1, C_Var t2) {
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
    	X10ReferenceType_c n = (X10ReferenceType_c) copy();
    	n.typeParameters = (l==null || l.isEmpty())? typeParameters : l;
    	n.depClause = d;
    	return n;
    }
    public X10Type makeNoClauseVariant() {
    	X10ReferenceType_c n = (X10ReferenceType_c) copy();
    	n.depClause = new Constraint_c();
    	n.typeParameters = typeParameters;
    	return n;
    }
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
        // Report.report(3,"X10ReferenceType_c: equals |" + this + "| and |" + o+"|");
      
        if (o == this) return true;
        if (! (o instanceof X10ReferenceType_c)) return false;
        X10ReferenceType_c other = (X10ReferenceType_c) o;
       return ((X10TypeSystem) typeSystem()).equivClause(this, other);
    }
    public boolean equalsWithoutClauseImpl(X10Type o) {
        // Report.report(3,"X10ReferenceType_c: equals |" + this + "| and |" + o+"|");
      
        if (o == this) return true;
        if (! (o instanceof X10ReferenceType_c)) return false;
        X10ReferenceType_c other = (X10ReferenceType_c) o;
       
       return rootType == other.rootType;
    }
   
    public boolean isCanonical() {
        if (typeParameters != null) {
            Iterator it = typeParameters.iterator();
            while (it.hasNext()) {
                Type t = (Type) it.next();
                if (!t.isCanonical())
                    return false;
            }
        }
        return true;
        
    }    
    
	public boolean descendsFromImpl(Type ancestor) {
		// Check subtype relation for supertype.
		if (superType() == null) {
			return false;
		}

		if (ts.isSubtype(superType(), ancestor)) {
			return true;
		}

		// Next check default behavior.
		return super.descendsFromImpl(ancestor);
	}

	// ----------------------------- end manual mixin code from X10Type_c
	
	
	
}
