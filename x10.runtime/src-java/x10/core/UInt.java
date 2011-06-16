/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2011.
 */

package x10.core;

import x10.rtt.Type;
import x10.rtt.Types;

/**
 * Represents a boxed UInt value. Boxed representation is used when casting
 * a UInt value into type Any or parameter type T.
 */
final public class UInt extends Numeric implements java.lang.Comparable<UInt>,
    x10.lang.Arithmetic<UInt>, x10.lang.Bitwise<UInt>, x10.util.Ordered<UInt>
{
    private static final long serialVersionUID = 1L;
    
    public static final x10.rtt.RuntimeType<?> $RTT = Types.UINT;
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    public x10.rtt.Type<?> $getParam(int i) {return null;}

    final int $value;

    private UInt(int value) {
        this.$value = value;
    }

    // value of x10.lang.UInt.Cache.high property
    private static java.lang.String cacheHighPropValue = System.getProperty("x10.lang.UInt.Cache.high");

    private abstract static class Cache {
        static final int high;
        static final UInt cache[];
        static {
            // high value may be configured by property
            int h = 255;
            if (cacheHighPropValue != null) {
                // Use Long.decode here to avoid invoking methods that
                // require Integer's autoboxing cache to be initialized
                int i = java.lang.Long.decode(cacheHighPropValue).intValue();
                i = Math.max(i, h);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE);
            }
            high = h;

            cache = new UInt[high + 1];
            for (int i = 0; i < cache.length; ++i) {
                cache[i] = new UInt(i);
            }
        }
    }

    public static UInt $box(int value) {
        if (0 <= value && value <= Cache.high) {
            return Cache.cache[value];
        }
        return new UInt(value);
    }

    public static int $unbox(UInt obj) {
        return obj.$value;
    }
    
    public static int $unbox(Object obj) {
        return ((UInt)obj).$value;
    }
    
    // make $box/$unbox idempotent
    public static UInt $box(UInt obj) {
        return obj;
    }

    public static int $unbox(int value) {
        return value;
    }
    
    public boolean _struct_equals$O(Object o) {
        if (o instanceof UInt && ((UInt) o).$value == $value)
            return true;
        return false;
    }
    
    // inherit default implementation
//    @Override
//    public boolean equals(Object o) {
//        return _struct_equals$O(o);
//    }
  
    @Override
    public int hashCode() {
        return $value;
    }

    @Override
    public java.lang.String toString() {
        if ($value >= 0)
            return java.lang.Integer.toString($value);
        else
            return java.lang.Long.toString((long)$value & 0xFFFFffffL);
    }
	
	// implements Comparable<UInt>
    public int compareTo(UInt o) {
        int a = Unsigned.inject($value);
        int b = Unsigned.inject(o.$value);
        if (a > b) return 1;
        else if (a < b) return -1;
        return 0;
    }
    
    // implements Arithmetic<UInt>
    public UInt $plus$G() { return this; }
    public UInt $minus$G() { return UInt.$box(-$value); }
    public UInt $plus(UInt a, Type t) { return UInt.$box($value + a.$value); }
    public UInt $minus(UInt a, Type t) { return UInt.$box($value - a.$value); }
    public UInt $times(UInt a, Type t) { return UInt.$box($value * a.$value); }
    public UInt $over(UInt a, Type t) { return UInt.$box(Unsigned.div($value,a.$value)); }
    
    // implements Bitwise<UInt>
    public UInt $tilde$G() { return UInt.$box(~$value); }
    public UInt $ampersand(UInt a, Type t) { return UInt.$box($value & a.$value); }
    public UInt $bar(UInt a, Type t) { return UInt.$box($value | a.$value); }
    public UInt $caret(UInt a, Type t) { return UInt.$box($value ^ a.$value); }
    public UInt $left$G(final int count) { return UInt.$box($value << count); }
    public UInt $right$G(final int count) { return UInt.$box($value >>> count); } // UInt is always unsigned
    public UInt $unsigned_right$G(final int count) { return UInt.$box($value >>> count); }        
    
    // implements Ordered<UInt>. Rely on autoboxing of booleans
    public Object $lt(UInt a, Type t) { return Unsigned.lt($value,a.$value); }
    public Object $gt(UInt a, Type t) { return Unsigned.gt($value,a.$value); }
    public Object $le(UInt a, Type t) { return Unsigned.le($value,a.$value); }
    public Object $ge(UInt a, Type t) { return Unsigned.ge($value,a.$value); }
    
    // extends abstract class java.lang.Number
    @Override
    public int intValue() {
        return (int)$value;
    }
    @Override
    public long longValue() {
        return (long)(((long)$value)&0xffffFFFFl);
    }
    @Override
    public float floatValue() {
        return (float)(((long)$value)&0xffffFFFFl);
    }
    @Override
    public double doubleValue() {
        return (double)(((long)$value)&0xffffFFFFl);
    }
}
