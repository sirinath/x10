/*
 * Created on Nov 30, 2004
 */
package polyglot.ext.x10.types;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import polyglot.ext.jl.types.UnknownType_c;
import polyglot.ext.x10.ast.DepParameterExpr;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.TypeSystem;

/**
 * @author vj
 *
 */
public class X10UnknownType_c extends UnknownType_c implements X10UnknownType {

	 /** Used for deserializing types. */
    protected X10UnknownType_c() { }
    
    /** Creates a new type in the given a TypeSystem. */
    public X10UnknownType_c( TypeSystem ts ) {
        super(ts);
    }

    protected DepParameterExpr depClause;
    protected List/*<GenParameterExpr>*/ typeParameters;
    protected X10Type baseType = this;
    public X10Type baseType() { return baseType;}
    public boolean isParametric() { return typeParameters != null && ! typeParameters.isEmpty();}
    public List typeParameters() { return typeParameters;}
    public DepParameterExpr depClause() { return depClause; }
    
    public boolean typeEqualsImpl(Type o) {
        return equalsImpl(o);
    }
    public int hashCode() {
        return 
          (baseType == this ? super.hashCode() : baseType.hashCode() ) 
        + (depClause != null ? depClause.hashCode() : 0)
        + ((typeParameters !=null && ! typeParameters.isEmpty()) ? typeParameters.hashCode() :0);
        
    }
    public X10Type makeVariant(DepParameterExpr d, List l) { 
        if (d == null && (l == null || l.isEmpty()))
                return this;
        X10UnknownType_c n = (X10UnknownType_c) copy();
        // n.baseType = baseType; // this may not be needed.
        n.typeParameters = l;
        n.depClause = d;
        return n;
    }
   
    public boolean equalsImpl(TypeObject o) {
        //    Report.report(3,"X10ParsedClassType_c: equals |" + this + "| and |" + o+"|");
        if (o == this) return true;
        if (! (o instanceof X10UnknownType_c)) return false;
        X10UnknownType_c other = (X10UnknownType_c) o;
        if ( baseType != other.baseType) return false;
        
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
   
    public List properties() { return Collections.EMPTY_LIST;}
    
    public NullableType toNullable() { return X10Type_c.toNullable(this);}
    public FutureType toFuture() { return X10Type_c.toFuture(this);}
}
