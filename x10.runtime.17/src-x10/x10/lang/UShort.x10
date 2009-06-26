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

@NativeRep("java", "short", "x10.core.BoxedShort", "x10.types.Types.USHORT")
//                  v-- when used
@NativeRep("c++", "uint16_t", "uint16_t", null)
//                            ^ when constructed
public final value UShort {
    // Binary and unary operations and conversions are built-in.  No need to declare them here.

    @Native("java", "x10.core.Unsigned.lt(#1, #2)")
    @Native("c++",  "((#1) < (#2))")
    public native static operator (x:UShort) < (y:UShort): Boolean;

    @Native("java", "x10.core.Unsigned.gt(#1, #2)")
    @Native("c++",  "((#1) > (#2))")
    public native static operator (x:UShort) > (y:UShort): Boolean;

    @Native("java", "x10.core.Unsigned.le(#1, #2)")
    @Native("c++",  "((#1) <= (#2))")
    public native static operator (x:UShort) <= (y:UShort): Boolean;

    @Native("java", "x10.core.Unsigned.ge(#1, #2)")
    @Native("c++",  "((#1) >= (#2))")
    public native static operator (x:UShort) >= (y:UShort): Boolean;

    @Native("java", "((#1) + (#2))")
    @Native("c++",  "((#1) + (#2))")
    public native static operator (x:UShort) + (y:UShort): UInt;

    @Native("java", "((#1) - (#2))")
    @Native("c++",  "((#1) - (#2))")
    public native static operator (x:UShort) - (y:UShort): Int;

    @Native("java", "((#1) * (#2))")
    @Native("c++",  "((#1) * (#2))")
    public native static operator (x:UShort) * (y:UShort): UInt;

    @Native("java", "x10.core.Unsigned.div(#1, #2)")
    @Native("c++",  "((#1) / (#2))")
    public native static operator (x:UShort) / (y:UShort): UInt;

    @Native("java", "x10.core.Unsigned.rem(#1, #2)")
    @Native("c++",  "((#1) % (#2))")
    public native static operator (x:UShort) % (y:UShort): UInt;
    
    @Native("java", "((#1) & (#2))")
    @Native("c++",  "((#1) & (#2))")
    public native static operator (x:UShort) & (y:UShort): UInt;
    
    @Native("java", "((#1) ^ (#2))")
    @Native("c++",  "((#1) ^ (#2))")
    public native static operator (x:UShort) ^ (y:UShort): UInt;
    
    @Native("java", "((#1) | (#2))")
    @Native("c++",  "((#1) | (#2))")
    public native static operator (x:UShort) | (y:UShort): UInt;
    
    @Native("java", "((#1) << (#2))")
    @Native("c++",  "((#1) << (#2))")
    public native static operator (x:UShort) << (y:Int): UInt;
    
    @Native("java", "((#1) >>> (#2))")
    @Native("c++",  "((#1) >> (#2))")
    public native static operator (x:UShort) >> (y:Int): UInt;

    @Native("java", "((#1) >>> (#2))")
    @Native("c++",  "((#1) >> (#2))")
    public native static operator (x:UShort) >>> (y:Int): UInt;
    
    @Native("java", "+(#1)")
    @Native("c++",  "+(#1)")
    public native static operator + (x:UShort): UInt;
    
    @Native("java", "-(#1)")
    @Native("c++",  "-(#1)")
    public native static operator - (x:UShort): Int;
    
    @Native("java", "~(#1)")
    @Native("c++",  "~(#1)")
    public native static operator ~ (x:UShort): UInt;
    
    @Native("java", "((short) ((#1) & 0xff))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:UByte): UShort;

    @Native("java", "((short) (#1))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:UInt) as UShort;

    @Native("java", "((short) (#1))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:ULong) as UShort;
    
    @Native("java", "((short) (#1))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:Byte) as UShort;

    @Native("java", "((short) (#1))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:Short) as UShort;

    @Native("java", "((short) (#1))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:Int) as UShort;

    @Native("java", "((short) (#1))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:Long) as UShort;
    
    @Native("java", "((short) (#1))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:Float) as UShort;
    
    @Native("java", "((short) (#1))")
    @Native("c++",  "((uint16_t) (#1))")
    public native static operator (x:Double) as UShort;
    

    @Native("java", "0")
    @Native("c++", "0U")
    public const MIN_VALUE = 0;
    
    @Native("java", "0xffff")
    @Native("c++", "0xffffU")
    public const MAX_VALUE = 0xffff;

    @Native("java", "java.lang.Integer.toString(#0 & 0xffff)")
    @Native("c++", "x10aux::to_string(#0)")
    public native def toString(): String;
    
    @Native("java", "java.lang.Integer.toString(#0 & 0xffff, #1)")
    @Native("c++", "x10aux::int_utils::toString(#0, #1)")
    public native def toString(radix: Int): String;
    
    @Native("java", "java.lang.Integer.toHexString(#0)")
    @Native("c++", "x10aux::int_utils::toHexString(#0)")
    public native def toHexString(): String;    
    
    @Native("java", "java.lang.Integer.toOctalString(#0)")
    @Native("c++", "x10aux::int_utils::toOctalString(#0)")
    public native def toOctalString(): String;  
      
    @Native("java", "java.lang.Integer.toBinaryString(#0)")
    @Native("c++", "x10aux::int_utils::toBinaryString(#0)")
    public native def toBinaryString(): String;    
    
    @Native("java", "java.lang.Integer.parseInt(#1, #2)")
    @Native("c++", "x10aux::int_utils::parseInt(#1, #2)")
    public native static def parseInt(String, radix: Int): Int throws NumberFormatException;
    
    @Native("java", "java.lang.Integer.parseInt(#1)")
    @Native("c++", "x10aux::int_utils::parseInt(#1)")
    public native static def parseInt(String): Int throws NumberFormatException;

    @Native("java", "java.lang.Integer.highestOneBit(#0)")
    @Native("c++", "x10aux::int_utils::highestOneBit(#0)")
    public native def highestOneBit(): Int;
    
    @Native("java", "java.lang.Integer.lowestOneBit(#0)")
    @Native("c++", "x10aux::int_utils::lowestOneBit(#0)")
    public native def lowestOneBit(): Int;

    @Native("java", "java.lang.Integer.numberOfLeadingZeros(#0)")
    @Native("c++", "x10aux::int_utils::numberOfLeadingZeros(#0)")
    public native def numberOfLeadingZeros(): Int;

    @Native("java", "java.lang.Integer.numberOfTrailingZeros(#0)")
    @Native("c++", "x10aux::int_utils::numberOfTrailingZeros(#0)")
    public native def numberOfTrailingZeros(): Int;

    @Native("java", "java.lang.Integer.bitCount(#0)")
    @Native("c++", "x10aux::int_utils::bitCount(#0)")
    public native def bitCount(): Int;

    @Native("java", "java.lang.Integer.rotateLeft(#0)")
    @Native("c++", "x10aux::int_utils::rotateLeft(#0)")
    public native def rotateLeft(): UShort;
    
    @Native("java", "java.lang.Integer.rotateRight(#0)")
    @Native("c++", "x10aux::int_utils::rotateRight(#0)")
    public native def rotateRight(): UShort;
    
    @Native("java", "java.lang.Integer.reverse(#0)")
    @Native("c++", "x10aux::int_utils::reverse(#0)")
    public native def reverse(): UShort;
    
    @Native("java", "((#0==0) ? 0 : 1)")
    @Native("c++",  "((#0==0U) ? 0 : 1)")
    public native def signum(): Int;
    
    @Native("java", "java.lang.Integer.reverseBytes(#0)")
    @Native("c++", "x10aux::int_utils::reverseBytes(#0)")
    public native def reverseBytes(): UShort;
}
