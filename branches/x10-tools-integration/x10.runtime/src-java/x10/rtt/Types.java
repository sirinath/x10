/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

package x10.rtt;

import x10.core.fun.Fun_0_1;


public class Types {
    public static boolean instanceof$(Type<?> t, Object o) {
        return t.instanceof$(o);
    }

    public static Object cast$(Type<?> t, Object o) {
        if (! instanceof$(t, o))
            throw new ClassCastException(t.toString());
        return o;
    }
    
    // Hack to get around parsing problems for generated Java casts.
    public static <T> T javacast(Object o) {
        return (T) o;
    }

    public static Type runtimeType(Class<?> c) {
        return new RuntimeType<Class<?>>(c);
    }

    public static Type<Boolean> BOOLEAN = new BooleanType();
    public static Type<Byte> BYTE = new ByteType();
    public static Type<Short> SHORT = new ShortType();
    public static Type<Character> CHAR = new CharType();
    public static Type<Integer> INT = new IntType();
    public static Type<Long> LONG = new LongType();
    public static Type<Float> FLOAT = new FloatType();
    public static Type<Double> DOUBLE = new DoubleType();

    public static RuntimeType<Comparable<?>> COMPARABLE;
    static {
        try {
            COMPARABLE = new RuntimeType<Comparable<?>>(Class.forName("x10.lang.Comparable"));
        } catch (ClassNotFoundException e) {}
    }

    public static Type<String> STR = new StringType();          // only with base class (used by code gen)
    protected static Type<String> STR0 = new StringType(        // with based class and parents
        new ParameterizedType(Types.COMPARABLE, Types.STR),
        new ParameterizedType(Fun_0_1._RTT, Types.INT, Types.CHAR),
        x10.rtt.Types.runtimeType(x10.core.Any.class)
    );

    public static Type<?> UBYTE;    // instance created and set in UByte static initializer
    public static Type<?> USHORT;   // instance created and set in UShort static initializer
    public static Type<?> UINT;     // instance created and set in UInt static initializer
    public static Type<?> ULONG;    // instance created and set in ULong static initializer

    public static Type<?> getNativeRepRTT(Object o) {
        assert(o instanceof Number);

        if (o instanceof Boolean) return BOOLEAN;
        if (o instanceof Byte) return BYTE;
        if (o instanceof Character) return CHAR;
        if (o instanceof Short) return SHORT;
        if (o instanceof Integer) return INT;
        if (o instanceof Long) return LONG;
        if (o instanceof Float) return FLOAT;
        if (o instanceof Double) return DOUBLE;
        throw new RuntimeException("RTT not found for "+o.getClass());
    }

    private static boolean isStruct(Type<?> rtt) {
        if (
            rtt == BOOLEAN
            || rtt == BYTE  || rtt == SHORT  || rtt == CHAR || rtt == INT   || rtt == LONG
            || rtt == UBYTE  || rtt == USHORT  || rtt == UINT   || rtt == ULONG
            || rtt == FLOAT || rtt == DOUBLE
            ) {
            return true;
        }
        return false;
    }

    public static boolean asboolean(Object typeParamOrAny) {
        if (typeParamOrAny == null) {nullIsCastedToStruct();}
        if (typeParamOrAny instanceof java.lang.Boolean) {return (java.lang.Boolean) typeParamOrAny;}
        throw new ClassCastException();
    }
    
    public static byte asbyte(Object typeParamOrAny){
        if (typeParamOrAny == null) {nullIsCastedToStruct();}
        if (typeParamOrAny instanceof java.lang.Number) {return((java.lang.Number) typeParamOrAny).byteValue();}
        throw new ClassCastException();
    }
    
    public static short asshort(Object typeParamOrAny){
        if (typeParamOrAny == null) {nullIsCastedToStruct();}
        if (typeParamOrAny instanceof java.lang.Number) {return((java.lang.Number) typeParamOrAny).shortValue();}
        throw new ClassCastException();
    }
    
    public static int asint(Object typeParamOrAny){
        if (typeParamOrAny == null) {nullIsCastedToStruct();}
        if (typeParamOrAny instanceof java.lang.Number) {return((java.lang.Number) typeParamOrAny).intValue();}
        throw new ClassCastException();
    }

    public static long aslong(Object typeParamOrAny){
        if (typeParamOrAny == null) {nullIsCastedToStruct();}
        if (typeParamOrAny instanceof java.lang.Number) {return((java.lang.Number) typeParamOrAny).longValue();}
        throw new ClassCastException();
    }

    public static float asfloat(Object typeParamOrAny){
        if (typeParamOrAny == null) {nullIsCastedToStruct();}
        if (typeParamOrAny instanceof java.lang.Number) {return((java.lang.Number) typeParamOrAny).floatValue();}
        throw new ClassCastException();
    }

    public static double asdouble(Object typeParamOrAny){
        if (typeParamOrAny == null) {nullIsCastedToStruct();}
        if (typeParamOrAny instanceof java.lang.Number) {return((java.lang.Number) typeParamOrAny).doubleValue();}
        throw new ClassCastException();
    }

    public static char aschar(Object typeParamOrAny) {
        if (typeParamOrAny == null) {nullIsCastedToStruct();}
        if (typeParamOrAny instanceof java.lang.Character) {return (java.lang.Character) typeParamOrAny;}
        throw new ClassCastException();
    }
    
    
    public static Object conversion(Type<?> rtt, Object primOrTypeParam) {
        if (primOrTypeParam == null && isStruct(rtt)) {nullIsCastedToStruct();}
        
        if (rtt == BYTE) {
            if (primOrTypeParam instanceof java.lang.Number) return ((java.lang.Number) primOrTypeParam).byteValue();
            if (primOrTypeParam instanceof java.lang.Byte) return primOrTypeParam;
            return primOrTypeParam;
        }
        if (rtt == SHORT) {
            if (primOrTypeParam instanceof java.lang.Number) return ((java.lang.Number) primOrTypeParam).shortValue();
            if (primOrTypeParam instanceof java.lang.Short) return primOrTypeParam;
            return primOrTypeParam;
        }
        if (rtt == INT) {
            if (primOrTypeParam instanceof java.lang.Number) return ((java.lang.Number) primOrTypeParam).intValue();
            if (primOrTypeParam instanceof java.lang.Integer) return primOrTypeParam;
            return primOrTypeParam;
        }
        if (rtt == LONG) {
            if (primOrTypeParam instanceof java.lang.Number) return ((java.lang.Number) primOrTypeParam).longValue();
            if (primOrTypeParam instanceof java.lang.Long) return primOrTypeParam;
            return primOrTypeParam;
        }
        if (rtt == FLOAT) {
            if (primOrTypeParam instanceof java.lang.Number) return ((java.lang.Number) primOrTypeParam).floatValue();
            if (primOrTypeParam instanceof java.lang.Float) return primOrTypeParam;
            return primOrTypeParam;
        }
        if (rtt == DOUBLE) {
            if (primOrTypeParam instanceof java.lang.Number) return ((java.lang.Number) primOrTypeParam).doubleValue();
            if (primOrTypeParam instanceof java.lang.Double) return primOrTypeParam;
            return primOrTypeParam;
        }
        
        return primOrTypeParam;
    }

    public static void nullIsCastedToStruct(){throw new java.lang.ClassCastException();}
}
