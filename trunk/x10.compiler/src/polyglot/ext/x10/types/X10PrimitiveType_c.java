/*
 * Created on Oct 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package polyglot.ext.x10.types;

import java.util.Collections;
import java.util.List;

import polyglot.ext.jl.types.PrimitiveType_c;
import polyglot.ext.x10.ast.DepParameterExpr;
import polyglot.main.Report;
import polyglot.types.Type;
import polyglot.types.TypeSystem;

/** X10 has no primitive types. Types such as int etc are all value class types. 
 * However, this particular X10 implementation uses Java primitive types to implement some of
 * X10's value class types, namely, char, boolean, byte, int etc etc. It implements other
 * value class types as Java classes.
 * 
 * Thus this class represents one of specially implemented X10 value class types.
 * @author praun
 * @author vj
 */
public class X10PrimitiveType_c extends PrimitiveType_c implements X10PrimitiveType {

	/** Used for deserializing types. */
    protected X10PrimitiveType_c() { }
protected X10TypeSystem xts;
    public X10PrimitiveType_c(TypeSystem ts, Kind kind) {
        super(ts, kind);
        xts = (X10TypeSystem) ts;
    }
    
    protected DepParameterExpr depClause;
    protected List/*<GenParameterExpr>*/ typeParameters;
    protected X10Type baseType = this;
    public X10Type baseType() { return baseType;}
    public boolean isParametric() { return typeParameters != null && ! typeParameters.isEmpty();}
    public List typeParameters() { return typeParameters;}
    public DepParameterExpr depClause() { return depClause; }
    public X10Type makeVariant(DepParameterExpr d, List/*<GenParameterExpr>*/ l) { 
        if (d == null && (l == null || l.isEmpty()))
            return this;
        X10PrimitiveType_c n = (X10PrimitiveType_c) copy();
        // n.baseType = baseType; // this may not be needed.
        n.typeParameters = l;
        n.depClause = d;
        if (  Report.should_report("debug", 5))
            Report.report(5,"X10PrimitiveType_c.makeVariant: " + this + " creates |" + n + "|");
        return n;
    }

    public boolean typeEqualsImpl(Type o) {
        return equalsImpl(o);
    }
    public int hashCode() {
        return 
          (baseType == this ? super.hashCode() : baseType.hashCode() ) 
        + (depClause != null ? depClause.hashCode() : 0)
        + ((typeParameters !=null && ! typeParameters.isEmpty()) ? typeParameters.hashCode() :0);
        
    }
    public boolean equalsImpl(Object o) {
        if (! (o instanceof X10PrimitiveType_c)) return false;
        X10PrimitiveType_c other = (X10PrimitiveType_c) o;
        if (! super.equals(o)) return false;
        if (depClause == null && other.depClause != null) return false;
        if (depClause != null && ! depClause.equals(other.depClause)) return false;
        
        if (typeParameters == null) return other.typeParameters == null;
        if (typeParameters.isEmpty()) return other.typeParameters == null || other.typeParameters.isEmpty();
        return typeParameters.equals(other.typeParameters);
    }
       
    /** Every X10 value type descends from X10.lang.Object, the base class.
     *
     */
    public boolean descendsFromImpl(Type ancestor) {
    	X10TypeSystem xts = (X10TypeSystem) ts;
        return ts.equals(ancestor, xts.X10Object());
    }

    /** Return true if this type can be assigned to <code>toType</code>. */
    public boolean isImplicitCastValidImpl(Type origType) {
        // System.out.println( "[PrimitiveType_c] isImplicitCastValid |" + this + "| to |" + toType + "|?");
    	X10TypeSystem xts = (X10TypeSystem) ts;
        X10Type toType = (X10Type) origType;
        NullableType targetType = toType.toNullable();
        if (targetType != null) {
            toType = targetType.base();
        }

        return xts.equals(toType, xts.X10Object()) ||
               super.isImplicitCastValidImpl(toType);
    }

    /** Returns true iff a cast from this to <code>toType</code> is valid. */
    public boolean isCastValidImpl(Type origType) {
    	X10TypeSystem xts = (X10TypeSystem) ts;
        X10Type toType = (X10Type) origType;
        NullableType nullType = toType.toNullable();
        if (nullType != null) 
            toType = nullType.base();
        return ts.equals(toType, xts.Object()) || super.isCastValidImpl(toType);
    }
    
 /*   public String toString() { 
        //Report.report(5,"X10ParsedClassType: toString |" + super.toString() + "|(#" 
        //        + this.hashCode() + this.getClass() + ") typeParameters=|" + typeParameters+"|");
        return  
        ((baseType == this) ? super.toString() : ((X10PrimitiveType_c) baseType).toString())
        + (isParametric() ? typeParameters.toString()  : "") 
        + (depClause == null ? "" :  depClause.toString())
        + "(#" + hashCode() + ")";
    }*/
    

    
    /**
     * Note that this (general) mix-in code correctly takes care of ensuring that
     * int is a subtype of nullable int as well as x10.lang.X10Object.
     */
    public boolean isSubtypeImpl(  Type other) { return X10Type_c.isSubtypeImpl(this, other);}
    public boolean isValueType() { return xts.isValueType(this); }
    public List properties() { return Collections.EMPTY_LIST;}
    public NullableType toNullable() { return X10Type_c.toNullable(this);}
    public FutureType toFuture() { return X10Type_c.toFuture(this);}
	// ----------------------------- end manual mixin code from X10Type_c
}
