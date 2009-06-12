/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.lang;

import x10.compiler.Native;

public final value Math {
   public const E = 2.718281828459045D;
   public const PI = 3.141592653589793D;

   public static safe def abs(a:Double):Double = a<=0.0 ? 0.0-a : a;
   public static safe def abs(a:Int):Int = a<0 ? -a : a;
   public static safe def abs(a:Float):Float = a<=0.0f ? 0.0f-a : a;
   public static safe def abs(a:Long):Long = a<0l ? -a : a;

   @Native("java", "java.lang.Math.ceil(#1)")
   @Native("c++", "x10aux::math_utils::ceil(#1)")
   public static native safe def ceil(a:Double):Double;

   @Native("java", "java.lang.Math.floor(#1)")
   @Native("c++", "x10aux::math_utils::floor(#1)")
   public static native safe def floor(a:Double):Double;

   @Native("java", "java.lang.Math.round(#1)")
   @Native("c++", "x10aux::math_utils::round(#1)")
   public static native safe def round(a:Double):Double;

   @Native("java", "java.lang.Math.pow(#1, #2)")
   @Native("c++", "x10aux::math_utils::pow(#1,#2)")
   public static native def pow(a:Double, b:Double):Double;

   @Native("java", "java.lang.Math.exp(#1)")
   @Native("c++", "x10aux::math_utils::exp(#1)")
   public static native def exp(a:Double):Double;

   @Native("java", "java.lang.Math.cos(#1)")
   @Native("c++", "x10aux::math_utils::cos(#1)")
   public static native def cos(a:Double):Double;

   @Native("java", "java.lang.Math.sin(#1)")
   @Native("c++", "x10aux::math_utils::sin(#1)")
   public static native def sin(a:Double):Double;

   @Native("java", "java.lang.Math.tan(#1)")
   @Native("c++", "x10aux::math_utils::tan(#1)")
   public static native def tan(a:Double):Double;

   @Native("java", "java.lang.Math.acos(#1)")
   @Native("c++", "x10aux::math_utils::acos(#1)")
   public static native def acos(a:Double):Double;

   @Native("java", "java.lang.Math.asin(#1)")
   @Native("c++", "x10aux::math_utils::asin(#1)")
   public static native def asin(a:Double):Double;

   @Native("java", "java.lang.Math.atan(#1)")
   @Native("c++", "x10aux::math_utils::atan(#1)")
   public static native def atan(a:Double):Double;

   @Native("java", "java.lang.Math.atan2(#1,#2)")
   @Native("c++", "x10aux::math_utils::atan2(#1,#2)")
   public static native def atan2(a:Double, b:Double):Double;

   @Native("java", "java.lang.Math.cosh(#1)")
   @Native("c++", "x10aux::math_utils::cosh(#1)")
   public static native def cosh(a:Double):Double;

   @Native("java", "java.lang.Math.sinh(#1)")
   @Native("c++", "x10aux::math_utils::sinh(#1)")
   public static native def sinh(a:Double):Double;

   @Native("java", "java.lang.Math.tanh(#1)")
   @Native("c++", "x10aux::math_utils::tanh(#1)")
   public static native def tanh(a:Double):Double;

   @Native("java", "java.lang.Math.sqrt(#1)")
   @Native("c++", "x10aux::math_utils::sqrt(#1)")
   public static native def sqrt(a:Double):Double;

   @Native("java", "java.lang.Math.log(#1)")
   @Native("c++", "x10aux::math_utils::log(#1)")
   public static native def log(a:Double):Double;

   @Native("java", "java.lang.Math.log10(#1)")
   @Native("c++", "x10aux::math_utils::log10(#1)")
   public static native def log10(a:Double):Double;

   @Native("java", "java.lang.Math.log1p(#1)")
   @Native("c++", "x10aux::math_utils::log1p(#1)")
   public static native def log1p(a:Double):Double;

   public static safe def max(a:Int, b:Int)= a<b?b:a;
   public static safe def max(a:Double, b:Double)= a<b?b:a;
   public static safe def min(a:Int, b:Int)= a<b?a:b;
   public static safe def min(a:Double, b:Double)= a<b?a:b;

    public static safe def powerOf2(var p:int) {
        if (p <= 0) return false;
        while (true) {
           if (p%2 == 1) return false;
           p = p/2;
           if (p == 1) return true;
        }
    }
    public static safe def log2(var p:Int):Int {
        assert powerOf2(p);
        var i:Int = 0;
        while (p > 1) { p = p/2; i++; }
        return i;
    }
    // returns 2^(max(0,i))
    public static safe def pow2(i:Int) {
        var p:Int = 1;
        for (var j:Int = i; j > 0; j--) p *= 2;
        return p;
    }
}
