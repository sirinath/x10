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

@NativeRep("java", "byte", "x10.core.BoxedByte", "x10.types.Type.BYTE")
@NativeRep("c++", "x10_byte", "x10_byte", null)
public final value Byte {
    @Native("java", "((#1) < (#2))")
    @Native("c++",  "((#1) < (#2))")
    public native static operator (x:Byte) < (y:Byte): Boolean;

    @Native("java", "((#1) > (#2))")
    @Native("c++",  "((#1) > (#2))")
    public native static operator (x:Byte) > (y:Byte): Boolean;

    @Native("java", "((#1) <= (#2))")
    @Native("c++",  "((#1) <= (#2))")
    public native static operator (x:Byte) <= (y:Byte): Boolean;

    @Native("java", "((#1) >= (#2))")
    @Native("c++",  "((#1) >= (#2))")
    public native static operator (x:Byte) >= (y:Byte): Boolean;

    @Native("java", "((#1) + (#2))")
    @Native("c++",  "((#1) + (#2))")
    public native static operator (x:Byte) + (y:Byte): Int;

    @Native("java", "((#1) - (#2))")
    @Native("c++",  "((#1) - (#2))")
    public native static operator (x:Byte) - (y:Byte): Int;

    @Native("java", "((#1) * (#2))")
    @Native("c++",  "((#1) * (#2))")
    public native static operator (x:Byte) * (y:Byte): Int;

    @Native("java", "((#1) / (#2))")
    @Native("c++",  "((#1) / (#2))")
    public native static operator (x:Byte) / (y:Byte): Int;

    @Native("java", "((#1) % (#2))")
    @Native("c++",  "((#1) % (#2))")
    public native static operator (x:Byte) % (y:Byte): Int;
    
    @Native("java", "((#1) & (#2))")
    @Native("c++",  "((#1) & (#2))")
    public native static operator (x:Byte) & (y:Byte): Int;
    
    @Native("java", "((#1) ^ (#2))")
    @Native("c++",  "((#1) ^ (#2))")
    public native static operator (x:Byte) ^ (y:Byte): Int;
    
    @Native("java", "((#1) | (#2))")
    @Native("c++",  "((#1) | (#2))")
    public native static operator (x:Byte) | (y:Byte): Int;
    
    @Native("java", "((#1) << (#2))")
    @Native("c++",  "((#1) << (#2))")
    public native static operator (x:Byte) << (y:Int): Int;
    
    @Native("java", "((#1) >> (#2))")
    @Native("c++",  "((#1) >> (#2))")
    public native static operator (x:Byte) >> (y:Int): Int;
    
    @Native("java", "((#1) >>> (#2))")
    @Native("c++",  "((x10_int) ((uint32_t) (#1) >> (#2)))")
    public native static operator (x:Byte) >>> (y:Int): Int;
    
    @Native("java", "((#1) << (#2))")
    @Native("c++",  "((#1) << (#2))")
    public native static operator (x:Byte) << (y:Long): Int;
    
    @Native("java", "((#1) >> (#2))")
    @Native("c++",  "((#1) >> (#2))")
    public native static operator (x:Byte) >> (y:Long): Int;
    
    @Native("java", "((#1) >>> (#2))")
    @Native("c++",  "((x10_int) ((uint32_t) (#1) >> (#2)))")
    public native static operator (x:Byte) >>> (y:Long): Int;
    
    @Native("java", "+(#1)")
    @Native("c++",  "+(#1)")
    public native static operator + (x:Byte): Int;
    
    @Native("java", "-(#1)")
    @Native("c++",  "-(#1)")
    public native static operator - (x:Byte): Int;
    
    @Native("java", "~(#1)")
    @Native("c++",  "~(#1)")
    public native static operator ~ (x:Byte): Int;
    
    @Native("java", "((byte) (#1))")
    @Native("c++",  "((x10_byte) (#1))")
    public native static operator (x:Short) as Byte;
    
    @Native("java", "((byte) (#1))")
    @Native("c++",  "((x10_byte) (#1))")
    public native static operator (x:Int) as Byte;

    @Native("java", "((byte) (#1))")
    @Native("c++",  "((x10_byte) (#1))")
    public native static operator (x:Long) as Byte;

    @Native("java", "((byte) (#1))")
    @Native("c++",  "((x10_byte) (#1))")
    public native static operator (x:Float) as Byte;
    
    @Native("java", "((byte) (#1))")
    @Native("c++",  "((x10_byte) (#1))")
    public native static operator (x:Double) as Byte;

    
    @Native("java", "java.lang.Byte.MIN_VALUE")
    @Native("c++", "(x10_byte)0x80")
    public const MIN_VALUE = 0x80 as Byte;
    
    @Native("java", "java.lang.Byte.MAX_VALUE")
    @Native("c++", "(x10_byte)0x7f")
    public const MAX_VALUE = 0x7f as Byte;

    @Native("java", "java.lang.Integer.toString(#0, #1)")
    @Native("c++", "x10aux::byte_utils::toString(#0, #1)")
    public native def toString(radix: Int): String;
    
    @Native("java", "java.lang.Integer.toHexString(#0)")
    @Native("c++", "x10aux::byte_utils::toHexString(#0)")
    public native def toHexString(): String;    
    
    @Native("java", "java.lang.Integer.toOctalString(#0)")
    @Native("c++", "x10aux::byte_utils::toOctalString(#0)")
    public native def toOctalString(): String;  
      
    @Native("java", "java.lang.Integer.toBinaryString(#0)")
    @Native("c++", "x10aux::byte_utils::toBinaryString(#0)")
    public native def toBinaryString(): String;    
    
    @Native("java", "java.lang.Byte.toString(#0)")
    @Native("c++", "x10aux::to_string(#0)")
    public native def toString(): String;
    
    @Native("java", "java.lang.Byte.parseByte(#1, #2)")
    @Native("c++", "x10aux::byte_utils::parseByte(#1, #2)")
    public native static def parseByte(String, radix: Int): Byte throws NumberFormatException;
    
    @Native("java", "java.lang.Byte.parseByte(#1)")
    @Native("c++", "x10aux::byte_utils::parseByte(#1)")
    public native static def parseByte(String): Byte throws NumberFormatException;
}
