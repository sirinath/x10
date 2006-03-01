/*
 * Created on Nov 30, 2004
 */
package polyglot.ext.x10.types;

import polyglot.ext.jl.types.UnknownType_c;
import polyglot.main.Report;
import polyglot.types.Type;
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

	
//	 ----------------------------- begin manual mixin code from X10Type_c
	/* (non-Javadoc)
	 * @see polyglot.ext.x10.types.X10Type#isNullable()
	 */
	public boolean isNullable() {
			return false;
	}

	/* (non-Javadoc)
	 * @see polyglot.ext.x10.types.X10Type#isFuture()
	 */
	public boolean isFuture() {
		return false;
	}

	/* (non-Javadoc)
	 * @see polyglot.ext.x10.types.X10Type#toNullable()
	 */
	public NullableType toNullable() {
			return null;
	}

	/* (non-Javadoc)
	 * @see polyglot.ext.x10.types.X10Type#toFuture()
	 */
	public FutureType toFuture() {
			return null;
	}

	public boolean isDistribution() { return false; }
	public boolean isDistributedArray() { return false; }
	public boolean isPrimitiveTypeArray() { return false; }
	public boolean isBooleanArray() { return false; }
    public boolean isCharArray() { return false; }
    public boolean isByteArray() { return false; }
    public boolean isShortArray() { return false; }
    public boolean isLongArray() { return false; }
    public boolean isIntArray() { return false; }
    public boolean isFloatArray() { return false; }
    public boolean isDoubleArray() { return false; }
	public boolean isClock() { return false; }
	public boolean isRegion() { return false; }
	public boolean isPlace() { return false;}
	public boolean isPoint() { return false; }
	public boolean isX10Array() { return false; }


	public  boolean isSubtypeImpl( Type t) {
    	X10Type target = (X10Type) t;
    	
    	if (Report.should_report("debug", 5))
			Report.report( 5, "[X10UnknownType_c] isSubTypeImpl |" + this +  "| of |" + t + "|?");	
    	
    	boolean result = ts.equals(this, target) || ts.descendsFrom(this, target);
    	
       	if (result) {
       		if (Report.should_report("debug", 5))
    			Report.report( 5, "[X10UnknownType_c] ..." + result+".");	
     		return result;
    	}
    	if (target.isNullable()) {
    		NullableType toType = target.toNullable();
    		Type baseType = toType.base();
    		result = isSubtypeImpl( baseType );
    		if (Report.should_report("debug", 5))
    			Report.report( 5, "[X10UnknownType_c] ..." + result+".");	
    		return result;
    	}
    	if (Report.should_report("debug", 5))
			Report.report( 5, "[X10UnknownType_c] ..." + result+".");	
    	return false;
    }
	// ----------------------------- end manual mixin code from X10Type_c
	
	public boolean isValueType() {
		return false;
	}
}
