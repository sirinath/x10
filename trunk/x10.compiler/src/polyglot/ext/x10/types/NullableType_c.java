/*
 * Created on Nov 26, 2004
 *
 */
package polyglot.ext.x10.types;

import java.util.List;

import polyglot.types.ReferenceType;
import polyglot.ext.jl.types.ReferenceType_c;
import polyglot.types.FieldInstance;
import polyglot.types.Resolver;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.TypeSystem;
import polyglot.types.NullableType;

import polyglot.util.Position;

/**
 * @author vj
 *
 */
public class NullableType_c extends ReferenceType_c implements NullableType {

    protected ReferenceType base;
    protected List fields;
    protected List methods;
    protected List interfaces;

    /** Used for deserializing types. */
    protected NullableType_c() { }

    /** Factory method for producing nullable types. Implements nullable nullable X = nullable X.
     * 
     * @param ts 
     * @param pos 
     * @param base -- the base type for the nullable type.
     * @return
     */
    public static NullableType makeNullable( TypeSystem ts, Position pos, ReferenceType base ) {
    	if ( base instanceof NullableType )
    		return (NullableType) base;
    	return new NullableType_c( ts, pos, base );
    }
    
    /** Constructor is made private. Called only in the factory method above.
     * 
     * @param ts
     * @param pos
     * @param base
     */
    private NullableType_c(TypeSystem ts, Position pos, ReferenceType base) {
    	super(ts, pos);
    	this.base = base;
    	assert base != null;

        this.methods = base.methods();
        this.fields = base.fields();
        this.interfaces = base.interfaces();
    }

   
    public boolean isNullable() {
    	return true;
    }
    
    public NullableType toNullable() {
    	return this;
    }
    
    public ReferenceType base() {
    	return this.base;
    }

    /** Set the base type. */
    public NullableType base( ReferenceType base ) {
        if (base == this.base)
            return this;
        NullableType_c n = (NullableType_c) copy();
        n.base = base;
        return n;
    }


    public String toString() {
    	return "nullable " + base().toString();
    }

    public String translate( Resolver c ) {
		return "nullable " + base().translate(c);
    }

    public boolean isCanonical() {
    	return base().isCanonical();
    }

    public List methods() {
    	return this.methods;
    }

    public List fields() {
    	return this.fields;
    }

    public FieldInstance fieldNamed( String name ) {
    	return base().fieldNamed( name );
    }

    public Type superType() {
    	return ts.Object();
    }

    public List interfaces() {
    	return this.interfaces;
    }

    // vj TODO: check if this is the right thing to do.
    public int hashCode() {
	return base().hashCode() << 2;
    }

    public boolean equalsImpl( TypeObject t ) {
    	if (t instanceof NullableType ) {
    		NullableType a = (NullableType) t;
    		return ts.equals(base(), a.base());
    	}
    	return false;
    }
    public boolean isImplicitCastValidImpl( Type toType ) {
    	if (toType.isNullable() ){
    		NullableType target = toType.toNullable();
    		return base().isImplicitCastValidImpl( target.base() );
    	}
        
        return false;
    }
    public boolean isCastValidImpl( Type toType ) {
    	return toType.isNullable() || base().isCastValidImpl( toType );
    }

}


