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

package x10.core;

import java.util.Arrays;

import x10.core.fun.Fun_0_1;
import x10.rtt.ParameterizedType;
import x10.rtt.RuntimeType;
import x10.rtt.Type;
import x10.rtt.Types;
import x10.rtt.UnresolvedType;
import x10.rtt.RuntimeType.Variance;

public final class Rail<T> extends Ref implements AnyRail<T>, x10.lang.Settable<Integer,T> {
    public final int length;
    
    public final Object value;
    public final Type<T> type;
    
	public Rail(Type<T> type, int length) {
        this(type, length, type.makeArray(length));
    }
    
    public Rail(Type<T> type, int length, Object array) {
        this.length = length;
        this.type = type;
        this.value = array;
    }
    
    public void copyToLocal(int src_off, Rail<T> dst, int dst_off, int len) {
        System.arraycopy(value, src_off, dst.value, dst_off, len);
    }

    public void copyFromLocal(int dst_off, Rail<T> src, int src_off, int len) {
        System.arraycopy(src.value, src_off, value, dst_off, len);
    }

    public void copyFromLocal(int  dst_off, ValRail<T> src, int src_off, int len) {
        System.arraycopy(src.value, src_off, value, dst_off, len);
    }

    public x10.lang.Iterator<T> iterator() {
		return new RailIterator();
	}

	protected class RailIterator implements x10.lang.Iterator<T> {
		int i = 0;

		public boolean hasNext() {
			return i < length;
		}

		public T next$G() {
			return apply$G(i++);
		}
	}

    // Methods to get the backing array.   May be called by generated code.
    public Object getBackingArray() { return value; }
    
    public boolean[] getBooleanArray() { return (boolean[]) value; }
    public byte[] getByteArray() { return (byte[]) value; }
    public short[] getShortArray() { return (short[]) value; }
    public char[] getCharArray() { return (char[]) value; }
    public int[] getIntArray() { return (int[]) value; }
    public long[] getLongArray() { return (long[]) value; }
    public float[] getFloatArray() { return (float[]) value; }
    public double[] getDoubleArray() { return (double[]) value; }
    public Object[] getObjectArray() { return (Object[]) value; }
    
    public Object[] getBoxedArray() {
    	if (value instanceof boolean[]) {
    		boolean[] a = (boolean[]) value;
    		Boolean[] b = new Boolean[a.length];
    		for (int i = 0; i < a.length; i++) b[i] = a[i];
    	}
    	if (value instanceof byte[]) {
    		byte[] a = (byte[]) value;
    		Byte[] b = new Byte[a.length];
    		for (int i = 0; i < a.length; i++) b[i] = a[i];
    	}
    	if (value instanceof char[]) {
    		char[] a = (char[]) value;
    		Character[] b = new Character[a.length];
    		for (int i = 0; i < a.length; i++) b[i] = a[i];
    	}
    	if (value instanceof short[]) {
    		short[] a = (short[]) value;
    		Short[] b = new Short[a.length];
    		for (int i = 0; i < a.length; i++) b[i] = a[i];
    	}
    	if (value instanceof int[]) {
    		int[] a = (int[]) value;
    		Integer[] b = new Integer[a.length];
    		for (int i = 0; i < a.length; i++) b[i] = a[i];
    	}
    	if (value instanceof long[]) {
    		long[] a = (long[]) value;
    		Long[] b = new Long[a.length];
    		for (int i = 0; i < a.length; i++) b[i] = a[i];
    	}
    	if (value instanceof float[]) {
    		float[] a = (float[]) value;
    		Float[] b = new Float[a.length];
    		for (int i = 0; i < a.length; i++) b[i] = a[i];
    	}
    	if (value instanceof double[]) {
    		double[] a = (double[]) value;
    		Double[] b = new Double[a.length];
    		for (int i = 0; i < a.length; i++) b[i] = a[i];
    	}
    	return (Object[]) value;
    }

    public int length() {
        return length;
    }
    
    public T apply(Object i, Type t) {
    	return apply$G((int)(Integer)i);
    }

    public T apply$G(int i) {
        return type.getArray(value, i);
    }
    
    protected T set$(T v, Integer i) {
    	return type.setArray(value, i, v);
    }
    
//    public boolean isZero() {
//    	boolean zero = true;
//		for (int i = 0; i < length && zero; ++i) {
//			zero &= apply$G(i) == type.zeroValue();
//		}
//		return zero;
//    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            if (i > 0)
                sb.append(", ");
            sb.append(apply$G(i));
        }
        sb.append("]");
        return sb.toString();
    }

    public T set(T v, Type t1, Integer i, Type t2) {
        return set$G(v, (int)i);
    }

    public T set$G(T v, int i) {
        return type.setArray(value, i, v);
    }

    public void reset(T v) {
        resetLocal(value, v);
    }

    public void reset(Fun_0_1<Integer,T> v) {
        for (int i=0; i<length; i++) {
            set$G(v.apply(i, Types.INT), i);
        }
    }

    public static <T> void resetLocal(Object value, T v) {
        if (value instanceof int[]) {
            Arrays.fill((int[]) value, (Integer) v);
        } else if (value instanceof long[]) {
            Arrays.fill((long[]) value, (Long) v);
        } else if (value instanceof float[]) {
            Arrays.fill((float[]) value, (Float) v);
        } else if (value instanceof double[]) {
            Arrays.fill((double[]) value, (Double) v);
        } else if (value instanceof byte[]) {
            Arrays.fill((byte[]) value, (Byte) v);
        } else if (value instanceof short[]) {
            Arrays.fill((short[]) value, (Short) v);
        } else if (value instanceof char[]) {
            Arrays.fill((char[]) value, (Character) v);
        } else if (value instanceof boolean[]) {
            Arrays.fill((boolean[]) value, (Boolean) v);
        } else {
            Arrays.fill((Object[]) value, v);
        }
    }

    //
    // boxed rail
    //
    
  
    //
    // Runtime type information
    //
    public static final RuntimeType<Rail<?>> _RTT = new RuntimeType<Rail<?>>(
        Rail.class, 
        new Variance[] {Variance.INVARIANT},
        new Type<?>[] {
            new ParameterizedType(x10.lang.Indexable._RTT, Types.INT, new UnresolvedType(0)),
            new ParameterizedType(x10.lang.Iterable._RTT, new UnresolvedType(0)),
            new ParameterizedType(x10.lang.Settable._RTT, Types.INT, new UnresolvedType(0))
        }
    ) {
        @Override
        public String typeName() {
            return "x10.lang.Rail";
        }
    };
    public RuntimeType<Rail<?>> getRTT() {return _RTT;}
    public Type<?> getParam(int i) {
        return i == 0 ? type : null;
    }
}
