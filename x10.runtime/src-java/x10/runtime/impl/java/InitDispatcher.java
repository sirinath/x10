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

package x10.runtime.impl.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import x10.core.ThrowableUtilities;

public abstract class InitDispatcher {

    public static final int UNINITIALIZED = 0;
    public static final int INITIALIZING = 1;
    public static final int INITIALIZED = 2;

    private static List<Method> initializeMethods = new ArrayList<Method>();
    private static List<Method> deserializeMethods = new ArrayList<Method>();
    private static int fieldId = 0;

    private static final String initializerPrefix = "getInitialized$";
    private static final String deserializerPrefix = "getDeserialized$";

    /**
     * Executed only in place 0
     */
    static class $Closure$Initialize implements x10.core.fun.VoidFun_0_0 {
    	private final Method initializer;
        public void $apply() {
            // execute X10-level static initialization
            try {
                initializer.invoke(null, (Object[])null);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new java.lang.Error(e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new java.lang.Error(e);
            }
        }
        $Closure$Initialize(Method initializer) {
        	this.initializer = initializer;
        }
        public x10.rtt.RuntimeType<?> $getRTT() {
            return $RTT;
        }
        public x10.rtt.Type<?> $getParam(int i) {
            return null;
        }
    }
    public static void runInitializer() {
        for (final Method initializer : initializeMethods) {
            // System.out.println("runInitializer executes " + initializer.getName());
        	// create an initialization closure
        	x10.core.fun.VoidFun_0_0 body = new $Closure$Initialize(initializer);
            // execute the closure asynchronously to resolve any dependencies
            x10.lang.Runtime.runAsync(body);
        }
        // static initialization all finished
        fieldId = -1;
    }

    public static int addInitializer(String className, String fieldName) {
        if (fieldId < 0) {
            System.err.println("Adding initializer too late! : " + className + "." + fieldName);
            System.exit(-1);
        }

        // System.out.println("addInitializer(id="+fieldId+"): "+className+"."+fieldName);
        try {
            Class<?> clazz = Class.forName(className);
            // register initializer and deserializer methods
            Method initializer = clazz.getMethod(initializerPrefix+fieldName, (Class<?>[])null);
            initializeMethods.add(initializer);

            Method deserializer = clazz.getMethod(deserializerPrefix+fieldName, byte[].class);
            deserializeMethods.add(deserializer);

            fieldId++;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new java.lang.Error(e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new java.lang.Error(e);
        }
        return fieldId-1;
    }

    static class $Closure$Deserialize implements x10.core.fun.VoidFun_0_0 {
    	private final int fieldId;
    	private final byte[] buf;
        public void $apply() {
            // execute deserializer for fieldValue
            try {
                deserializeMethods.get(fieldId).invoke(null, buf);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new java.lang.Error(e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new java.lang.Error(e);
            }
        }
        $Closure$Deserialize(int fieldId, byte[] buf) {
        	this.fieldId = fieldId;
        	this.buf = buf;
        }
        public x10.rtt.RuntimeType<?> $getRTT() {
            return $RTT;
        }
        public x10.rtt.Type<?> $getParam(int i) {
            return null;
        }
    }
    public static void broadcastStaticField(final Object fieldValue, final int fieldId) {
    	// no need for broadcast while running on a single place
    	if (Runtime.MAX_PLACES <= 1) {
    		return;
    	}
    	
        // if (X10RT.VERBOSE) System.out.println("@MultiVM: broadcastStaticField(id="+fieldId+"):"+fieldValue);

        // serialize to bytearray
        final byte[] buf = serializeField(fieldValue);
        
        // create a deserialization closure
        x10.core.fun.VoidFun_0_0 body = new $Closure$Deserialize(fieldId, buf);
        
        // Invoke the closure at all places except here
        Runtime.runAtAll(false, body);
    }
    // for Emitter.mangleSignedNumeric
    public static void broadcastStaticField$s1(final Object fieldValue, final int fieldId) {
        // no need for broadcast while running on a single place
        if (Runtime.MAX_PLACES <= 1) {
                return;
        }
        
        // if (X10RT.VERBOSE) System.out.println("@MultiVM: broadcastStaticField(id="+fieldId+"):"+fieldValue);

        // serialize to bytearray
        final byte[] buf = serializeField(fieldValue);
        
        // create a deserialization closure
        x10.core.fun.VoidFun_0_0 body = new $Closure$Deserialize(fieldId, buf);
        
        // Invoke the closure at all places except here
        Runtime.runAtAll(false, body);
    }

    private static byte[] serializeField(Object object) {
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(baos);
            out.writeObject(object);
            out.close();
            return baos.toByteArray();
        } catch (java.io.IOException e) {
            x10.core.Throwable xe = ThrowableUtilities.getCorrespondingX10Exception(e);
            xe.printStackTrace();
            throw xe;
        }
    }

    public static Object deserializeField(byte[] buf) {
        try {
            java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(buf);
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(bais);
            Object val = in.readObject();
            in.close();
            return val;
        } catch (java.io.IOException e) {
            x10.core.Throwable xe = ThrowableUtilities.getCorrespondingX10Exception(e);
            xe.printStackTrace();
            throw xe;
        } catch (ClassNotFoundException e) {
            x10.core.Throwable xe = ThrowableUtilities.getCorrespondingX10Exception(e);
            xe.printStackTrace();
            throw xe;            
        }
    }

    public static void lockInitialized() {
        x10.lang.Runtime.StaticInitBroadcastDispatcherLock();
    }

    public static void unlockInitialized() {
        x10.lang.Runtime.StaticInitBroadcastDispatcherUnlock();
    }

    public static void awaitInitialized() {
        x10.lang.Runtime.StaticInitBroadcastDispatcherAwait();
    }

    public static void notifyInitialized() {
        x10.lang.Runtime.StaticInitBroadcastDispatcherNotify();
    }
}
