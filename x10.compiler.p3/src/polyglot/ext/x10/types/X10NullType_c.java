/*
 *
 * (C) Copyright IBM Corporation 2006-2008
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * Created on Nov 28, 2004
 *
 */
package polyglot.ext.x10.types;

import polyglot.ast.FlagsNode;
import polyglot.types.Flags;
import polyglot.types.NullType_c;
import polyglot.types.TypeSystem;
import polyglot.util.InternalCompilerError;

/** Every X10 term must have a type. This is the type of the X10 term null.
 * Note that there is no X10 type called Null; only the term null.
 * @author vj
 *
 */
public class X10NullType_c extends NullType_c implements X10NullType {
    public X10NullType_c( TypeSystem ts ) {super(ts);}
    
    public boolean safe() { return true;}

    public Flags flags() {
    	return Flags.NONE;
    }
    public X10Type setFlags(Flags flags) {
    	assert false : "Cannot set flags on X10NullType";
    	throw new InternalCompilerError("Cannot set flags on X10NullType.");
    		
    }
    public X10Type clearFlags(Flags f) {
    	return this;
    }
    public String toString() { 
    	return "null";
    }
    public boolean isRooted() { return false; }
    public boolean isX10Struct() { return false; }
}
