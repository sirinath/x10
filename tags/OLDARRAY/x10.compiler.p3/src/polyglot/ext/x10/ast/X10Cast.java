/*
 *
 * (C) Copyright IBM Corporation 2006-2008
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.ast;

import polyglot.ast.Cast;

public interface X10Cast extends Cast {
    public static enum ConversionType {
        COERCION,
        UNKNOWN_CONVERSION,
        CALL,
        BOXING,
        UNBOXING,
        PRIMITIVE,
        TRUNCATION,
    }
    
    public ConversionType conversionType();
    public X10Cast conversionType(ConversionType convert);
    public boolean isConversion();
}
