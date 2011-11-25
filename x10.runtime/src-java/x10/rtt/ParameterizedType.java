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

package x10.rtt;

import x10.core.Any;
import x10.x10rt.X10JavaDeserializer;
import x10.x10rt.X10JavaSerializable;
import x10.x10rt.X10JavaSerializer;

import java.io.IOException;


public final class ParameterizedType<T> implements Type<T>, X10JavaSerializable {

    private static final long serialVersionUID = 1L;
    static {
        x10.x10rt.DeserializationDispatcher.addDispatcher(x10.x10rt.DeserializationDispatcher.ClosureKind.CLOSURE_KIND_NOT_ASYNC, ParameterizedType.class);
    }
    private static short _serialization_id;

    public RuntimeType<T> rawType;
    public Type<?>[] actualTypeArguments;
    
    RuntimeType<T> getRawType() {
        return rawType;
    }
    
    Type<?>[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    private ParameterizedType(RuntimeType<T> rawType, Type<?>... actualTypeArguments) {
        this.rawType = rawType;
        this.actualTypeArguments = actualTypeArguments;
    }
    public static <T> ParameterizedType/*<T>*/ make(RuntimeType<T> rawType, Type<?>... actualTypeArguments) {
        return new ParameterizedType<T>(rawType, actualTypeArguments);
    }

    // Constructor just for allocation
    public ParameterizedType() {
    }

    public final boolean isAssignableTo(Type<?> superType) {
        if (this == superType) return true;
        if (superType == Types.ANY) return true;
        if (superType == Types.OBJECT) return !Types.isStructType(this);
        if (!superType.getJavaClass().isAssignableFrom(rawType.getJavaClass())) {
            return false;
        }
        if (superType instanceof ParameterizedType) {
            ParameterizedType<?> pt = (ParameterizedType<?>) superType;
            if (pt.getRawType().isAssignableFrom(pt.actualTypeArguments, rawType, actualTypeArguments)) {
                return true;
            }
        }
        else if (superType instanceof RuntimeType) {
            if (((RuntimeType<?>) superType).isAssignableFrom(null, rawType, actualTypeArguments)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isInstance(Object o) {
        return rawType.isInstance(o, actualTypeArguments);
    }
    
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ParameterizedType<?>) {
            ParameterizedType<?> t = (ParameterizedType<?>) o;
            if (!rawType.equals(t.rawType)) {
                return false;
            }
            Type<?>[] t_actualTypeArguments = t.actualTypeArguments;
            if (actualTypeArguments.length != t_actualTypeArguments.length) {
                return false;
            }
            for (int i = 0; i < actualTypeArguments.length; i++) {
                if (!actualTypeArguments[i].equals(t_actualTypeArguments[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public final int hashCode() {
        return rawType.hashCode();
    }

    public final int arrayLength(Object array) {
        return rawType.arrayLength(array);
    }

    public final T getArray(Object array, int i) {
        return rawType.getArray(array, i);
    }

    public final Class<?> getJavaClass() {
        return rawType.getJavaClass();
    }

    public final Object makeArray(int dim0) {
        return rawType.makeArray(dim0);
    }

    public final Object makeArray(int dim0, int dim1) {
    	return rawType.makeArray(dim0, dim1);
    }

    public final Object makeArray(int dim0, int dim1, int dim2) {
    	return rawType.makeArray(dim0, dim1, dim2);
    }
    
    public final Object makeArray(int dim0, int dim1, int dim2, int dim3) {
    	return rawType.makeArray(dim0, dim1, dim2, dim3);
    }
    
    public final Object makeArray(int... dims) {
        return rawType.makeArray(dims);
    }

    public final void setArray(Object array, int i, T v) {
    	rawType.setArray(array, i, v);
    }

    @Override
    public final String toString() {
        return typeName();
    }

    // Note: this method does not resolve UnresolvedType at runtime
    public final String typeName() {
        return typeName(null);
    }

    private static final String printType(Type<?> t, Object o) {
        if (t instanceof UnresolvedType) {
            int index = ((UnresolvedType) t).getIndex();
            if (index >= 0) {
                t = ((Any) o).$getParam(index);
            } else {
                t = ((Any) o).$getRTT();
            }
        }
        
        if (t instanceof ParameterizedType) {
            return ((ParameterizedType<?>) t).typeName(o);
        } else {
            return t.typeName();
        }
    }
    
    public final String typeName(Object o) {
        if (rawType instanceof FunType) {
            return typeNameForFun(o);
        } else if (rawType instanceof VoidFunType) {
            return typeNameForVoidFun(o);
        } else {
            return typeNameForOthers(o);
        }
    }

    // called from Static{Void}FunType.typeName(Object)
    public final String typeNameForFun(Object o) {
        String str = "(";
        int i;
        for (i = 0; i < actualTypeArguments.length - 1; i++) {
            if (i != 0) str += ",";
            str += printType(actualTypeArguments[i], o);
        }
        str += ")=>";
        str += printType(actualTypeArguments[i], o);
        return str;
    }

    public final String typeNameForVoidFun(Object o) {
        String str = "(";
        if (actualTypeArguments != null && actualTypeArguments.length > 0) {
            for (int i = 0; i < actualTypeArguments.length; i++) {
                if (i != 0) str += ",";
                str += printType(actualTypeArguments[i], o);
            }
        }
        str += ")=>void";
        return str;
    }
    
    public final String typeNameForOthers(Object o) {
        String str = rawType.typeName();
        str += "[";
        for (int i = 0; i < actualTypeArguments.length; i ++) {
            if (i != 0) str += ",";
            str += printType(actualTypeArguments[i], o);
        }
        str += "]";
        return str;
    }

    public void $_serialize(X10JavaSerializer serializer) throws IOException {
        serializer.write(rawType);
        serializer.write(actualTypeArguments);
    }

    public static X10JavaSerializable $_deserializer(X10JavaDeserializer deserializer) throws IOException {
        ParameterizedType pt = new ParameterizedType();
        deserializer.record_reference(pt);
        return $_deserialize_body(pt, deserializer);
    }

    public static X10JavaSerializable $_deserialize_body(ParameterizedType pt, X10JavaDeserializer deserializer) throws IOException {
        RuntimeType rawType = (RuntimeType) deserializer.readRef();
        pt.rawType = rawType;
        int length = deserializer.readInt();
        Type[] actualTypeArguments = new Type[length];
        deserializer.readArray(actualTypeArguments);
        pt.actualTypeArguments = actualTypeArguments;
        return pt;
    }

    public short $_get_serialization_id() {
        return _serialization_id;
    }

    public static void $_set_serialization_id(short id) {
         _serialization_id = id;
    }
}
