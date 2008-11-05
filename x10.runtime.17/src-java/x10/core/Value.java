/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.omg.CORBA.portable.BoxedValueHelper;

import x10.types.Equality;
import x10.types.RuntimeType;
import x10.types.Type;

// Base class of all X10 value objects -- should be generated, but we need this class to get Box to compile.
public class Value {
    
    public static class RTT extends RuntimeType<Value> {
    	public static final RTT it = new RTT();

    	public RTT() {
            super(Value.class);
        }

        @Override
        public boolean instanceof$(Object o) {
            return o instanceof Value;
        }
    }
    
    public static class BoxedValue extends Box<Value> {
		BoxedValue(Value v) {
			super(Value.RTT.it, v);
		}
	}
    
    
	public Ref box$() {
		return new BoxedValue(this);
	}
	
    public final boolean equals(Object o) {
        return structEquals(o);
    }
    
    public int hashCode() {
        return structHash();
    }

    public final int structHash() {
        Class<?> c = this.getClass();
        Object o = this;
        int hash = 17;
        try {
            while (c != null) {
                Field[] fs = c.getDeclaredFields();
                for (int i = fs.length - 1; i >= 0; i--) {
                    Field f = fs[i];
                    if (Modifier.isStatic(f.getModifiers()))
                        continue;
                    f.setAccessible(true);
                    if (f.getType().isPrimitive()) {
                        hash = hash * 37 + f.get(o).hashCode();
                    }
                    else if (f.getType().isArray()) {
                        java.lang.Object a = f.get(o);
                        int len = Array.getLength(a);
                        for (int j = 0; j < len; j++) {
                            hash = hash * 37 + Array.get(a, j).hashCode();
                        }
                    }
                    else {
                        // I assume here that value types are immutable
                        // and can thus not contain mutually recursive
                        // structures.  If that is wrong, we would have to do
                        // more work here to avoid dying with a StackOverflow.
                        hash = hash * 37 + f.get(o).hashCode();
                    }
                }
                c = c.getSuperclass();
            }
        }
        catch (IllegalAccessException iae) {
            throw new Error(iae); // fatal, should never happen
        }
        return hash;
    }

    public final boolean structEquals(Object o) {
        if (o == null)
            return false;
        Class<?> c = this.getClass();
        Object o1 = this;
        Object o2 = o;
        if (c != o2.getClass())
            return false;
        try {
            while (c != null) {
                Field[] fs = c.getDeclaredFields();
                for (int i = fs.length - 1; i >= 0; i--) {
                    Field f = fs[i];
                    if (Modifier.isStatic(f.getModifiers()))
                        continue;
                    f.setAccessible(true);
                    if (f.getType().isPrimitive()) {
                        if (!f.get(o1).equals(f.get(o2)))
                            return false;
                    }
                    else if (f.getType().isArray()) {
                        java.lang.Object a1 = f.get(o1);
                        java.lang.Object a2 = f.get(o2);
                        int len = Array.getLength(a1);
                        if (len != Array.getLength(a2))
                            return false;
                        for (int j = 0; j < len; j++)
                            if (!Array.get(a1, j).equals(Array.get(a2, j)))
                                return false;
                    }
                    else {
                        // I assume here that value types are immutable
                        // and can thus not contain mutually recursive
                        // structures.  If that is wrong, we would have to do
                        // more work here to avoid dying with a StackOverflow.
                        if (!Equality.equalsequals(f.get(o1), f.get(o2)))
                            return false;
                    }
                }
                c = c.getSuperclass();
            }
        }
        catch (IllegalAccessException iae) {
            throw new Error(iae); // fatal, should never happen
        }
        return true;
    }
}