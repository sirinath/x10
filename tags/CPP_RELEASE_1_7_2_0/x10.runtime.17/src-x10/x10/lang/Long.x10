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

@NativeRep("java", "long", "x10.core.BoxedLong", "x10.types.Type.LONG")
@NativeRep("c++", "x10_long", "x10_long", null)
public final value Long implements Integer, Signed {
    // Binary and unary operations and conversions are built-in.  No need to declare them here.
    
    @Native("java", "java.lang.Long.MIN_VALUE")
    @Native("c++", "0x8000000000000000LL")
    public const MIN_VALUE = 0x8000000000000000L;
    
    @Native("java", "java.lang.Long.MAX_VALUE")
    @Native("c++", "0x7fffffffffffffffLL")
    public const MAX_VALUE = 0x7fffffffffffffffL;

    @Native("java", "java.lang.Long.toString(#0, #1)")
    @Native("c++", "x10aux::long_utils::toString(#0, #1)")
    public native def toString(radix: Int): String;
    
    @Native("java", "java.lang.Long.toHexString(#0)")
    @Native("c++", "x10aux::long_utils::toHexString(#0)")
    public native def toHexString(): String;    
    
    @Native("java", "java.lang.Long.toOctalString(#0)")
    @Native("c++", "x10aux::long_utils::toOctalString(#0)")
    public native def toOctalString(): String;  
      
    @Native("java", "java.lang.Long.toBinaryString(#0)")
    @Native("c++", "x10aux::long_utils::toBinaryString(#0)")
    public native def toBinaryString(): String;    
    
    @Native("java", "java.lang.Long.toString(#0)")
    @Native("c++", "x10aux::long_utils::toString(#0)")
    public native def toString(): String;
    
    @Native("java", "java.lang.Long.parseLong(#1, #2)")
    @Native("c++", "x10aux::long_utils::parseLong(#1, #2)")
    public native static def parseLong(String, radix: Int): Long throws NumberFormatException;
    
    @Native("java", "java.lang.Long.parseLong(#1)")
    @Native("c++", "x10aux::long_utils::parseLong(#1)")
    public native static def parseLong(String): Long throws NumberFormatException;

    @Native("java", "java.lang.Long.highestOneBit(#0)")
    @Native("c++", "x10aux::long_utils::highestOneBit(#0)")
    public native def highestOneBit(): Long;
    
    @Native("java", "java.lang.Long.lowestOneBit(#0)")
    @Native("c++", "x10aux::long_utils::lowestOneBit(#0)")
    public native def lowestOneBit(): Long;

    @Native("java", "java.lang.Long.numberOfLeadingZeros(#0)")
    @Native("c++", "x10aux::long_utils::numberOfLeadingZeros(#0)")
    public native def numberOfLeadingZeros(): Int;

    @Native("java", "java.lang.Long.numberOfTrailingZeros(#0)")
    @Native("c++", "x10aux::long_utils::numberOfTrailingZeros(#0)")
    public native def numberOfTrailingZeros(): Int;

    @Native("java", "java.lang.Long.bitCount(#0)")
    @Native("c++", "x10aux::long_utils::bitCount(#0)")
    public native def bitCount(): Int;

    @Native("java", "java.lang.Long.rotateLeft(#0)")
    @Native("c++", "x10aux::long_utils::rotateLeft(#0)")
    public native def rotateLeft(): Long;
    
    @Native("java", "java.lang.Long.rotateRight(#0)")
    @Native("c++", "x10aux::long_utils::rotateRight(#0)")
    public native def rotateRight(): Long;
    
    @Native("java", "java.lang.Long.reverse(#0)")
    @Native("c++", "x10aux::long_utils::reverse(#0)")
    public native def reverse(): Long;
    
    @Native("java", "java.lang.Long.signum(#0)")
    @Native("c++", "x10aux::long_utils::signum(#0)")
    public native def signum(): Int;
    
    @Native("java", "java.lang.Long.reverseBytes(#0)")
    @Native("c++", "x10aux::long_utils::reverseBytes(#0)")
    public native def reverseBytes(): Long;
}
