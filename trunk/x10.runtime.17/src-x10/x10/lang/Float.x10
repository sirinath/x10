/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.lang;

import x10.compiler.Native;
import x10.compiler.NativeRep;

@NativeRep("java", "float", "x10.core.BoxedFloat", "x10.types.Type.FLOAT")
@NativeRep("c++", "x10_float", "x10_float", null)
public final value Float {
    // Binary and unary operations and conversions are built-in.  No need to declare them here.
    
    @Native("java", "java.lang.Float.POSITIVE_INFINITY")
    public const POSITIVE_INFINITY: Float = Float.fromIntBits(0x7f800000);
    @Native("java", "java.lang.Float.NEGATIVE_INFINITY")
    public const NEGATIVE_INFINITY: Float = Float.fromIntBits(0xff800000);
    @Native("java", "java.lang.Float.NaN")
    public const NaN: Float = Float.fromIntBits(0x7fc00000);
    @Native("java", "java.lang.Float.MAX_VALUE")
    public const MAX_VALUE: Float = Float.fromIntBits(0x7f7fffff);
    @Native("java", "java.lang.Float.MIN_VALUE")
    public const MIN_VALUE: Float = Float.fromIntBits(0x00800000);
    
    @Native("java", "java.lang.Float.toHexString(#0)")
    @Native("c++", "x10aux::float_utils::toHexString(#0)")
    public native def toHexString(): String;    
    
    @Native("java", "java.lang.Float.toString(#0)")
    @Native("c++", "x10aux::float_utils::toString(#0)")
    public native def toString(): String;
    
    @Native("java", "java.lang.Float.parseFloat(#1)")
    @Native("c++", "x10aux::float_utils::parseFloat(#1)")
    public native static def parseFloat(String): Float throws NumberFormatException;

    @Native("java", "java.lang.Float.isNaN(#0)")
    @Native("c++", "x10aux::float_utils::isNaN(#0)")
    public native def isNaN(): boolean;

    @Native("java", "java.lang.Float.isInfinite(#0)")
    @Native("c++", "x10aux::float_utils::isInfinite(#0)")
    public native def isInfinite(): boolean;
    
    @Native("java", "java.lang.Float.floatToIntBits(#0)")
    @Native("c++", "x10aux::float_utils::floatToIntBits(#0)")
    public native def toIntBits(): Int;

    @Native("java", "java.lang.Float.floatToRawIntBits(#0)")
    @Native("c++", "x10aux::float_utils::floatToRawIntBits(#0)")
    public native def toRawIntBits(): Int;

    @Native("java", "java.lang.Float.intBitsToFloat(#1)")
    @Native("c++", "x10aux::float_utils::fromIntBits(#1)")
    public static native def fromIntBits(Int): Float;
}
