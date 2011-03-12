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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import x10.core.ThrowableUtilities;
import x10.rtt.RuntimeType;
import x10.rtt.Type;
import x10.runtime.impl.java.Thread;
import x10.x10rt.X10RT;

public abstract class Runtime implements x10.core.fun.VoidFun_0_0 {

	private static final long serialVersionUID = 1L;

    public RuntimeType<?> $getRTT() { return null; }
    public Type<?> $getParam(int i) { return null; }

    private String[] args;

	/**
	 * Body of main java thread
	 */
	protected void start(final String[] args) {
		this.args = args;
		

		// load libraries
		String property = System.getProperty("x10.LOAD");
		if (null != property) {
			String[] libs = property.split(":");
			for (int i = libs.length-1; i>=0; i--) System.loadLibrary(libs[i]);
		}

		// @MultiVM, the following is right ?? 
		// FIXME:  By here it is already too late because statics in Runtime 
		//         refer to X10RT. Need to restructure this so that we can call
		//         X10RT.init explicitly from here.
		X10RT.init();
		
		java.lang.Runtime.getRuntime().addShutdownHook(new java.lang.Thread() {
		    public void run() { System.out.flush(); }
		});

		// start and join main x10 thread in place 0
		x10.lang.Runtime.Worker worker = new x10.lang.Runtime.Worker(0);
		worker.body = this;
		worker.start();
		try { worker.join(); } catch (InterruptedException e) {}

		// shutdown
		System.exit(exitCode);
	}

	/**
	 * Body of main x10 thread
	 */
    // static init activity
	static class $Closure$Init implements x10.core.fun.VoidFun_0_0 {
		public void $apply() {
            // execute X10-level static initialization
            x10.runtime.impl.java.InitDispatcher.runInitializer();
		}
		public x10.rtt.RuntimeType<?> $getRTT() {
			return $RTT;
		}
		public x10.rtt.Type<?> $getParam(int i) {
			return null;
		}		
	}
    // body of main activity
	static class $Closure$Main implements x10.core.fun.VoidFun_0_0 {
		private final Runtime out$;
		private final x10.array.Array<String> aargs;
		public void $apply() {
			// catch and rethrow checked exceptions (closures cannot throw checked exceptions)
			try {
				// execute root x10 activity
				out$.runtimeCallback(aargs);
			} catch (java.lang.RuntimeException e) {
				throw e;
			} catch (java.lang.Error e) {
				throw e;
			} catch (java.lang.Throwable t) {
				throw new x10.runtime.impl.java.WrappedThrowable(t);
			}
		}
		$Closure$Main(Runtime out$, x10.array.Array<String> aargs) {
			this.out$ = out$;
			this.aargs = aargs;
		}
		public x10.rtt.RuntimeType<?> $getRTT() {
			return $RTT;
		}
		public x10.rtt.Type<?> $getParam(int i) {
			return null;
		}
	}
	public void $apply() {
//		try { Class.forName("x10.lang.Place"); } catch (ClassNotFoundException e) { }

		// preload classes by default
		if (!Boolean.getBoolean("x10.NO_PRELOAD_CLASSES")) {
		    // System.out.println("start preloading of classes");
		    Class<?> userMain = this.getClass().getEnclosingClass();
		    x10.runtime.impl.java.PreLoader.preLoad(userMain,
                                                  Boolean.getBoolean("x10.PRELOAD_STRINGS"));
		}

		// build up Array[String] for args
		final x10.array.Array<String> aargs = new x10.array.Array<String>(x10.rtt.Types.STRING, args.length);
		for (int i=0; i<args.length; i++) {
		    aargs.$set_1_$$x10$array$Array_T$G(i, args[i]);
		}

		// execute root x10 activity
        try {
            // start xrx
            x10.lang.Runtime.start(
            // static init activity
            new $Closure$Init(),
            // body of main activity
            new $Closure$Main(this, aargs)
            );
        } catch (java.lang.Throwable t) {
            t.printStackTrace();
            setExitCode(1);
        }
	}

	/**
	 * User code provided by Main template
	 * - start xrx runtime
	 * - run main activity
	 */
	public abstract void runtimeCallback(x10.array.Array<java.lang.String> args);

	/**
	 * Application exit code
	 */
	private static int exitCode = 0;

	/**
	 * Set the application exit code
	 */
	public static void setExitCode(int code) {
		exitCode = code;
	}

	/**
	 * The number of places in the system
	 */
	public static int MAX_PLACES = X10RT.numPlaces();

    /**
     * Trace serialization
     */
    public static final boolean TRACE_SER = Boolean.getBoolean("x10.TRACE_SER");

    /**
     * Synchronously executes body at place(id)
     */
    public static void runClosureAt(int place, x10.core.fun.VoidFun_0_0 body) {
    	runAt(place, body);
    }

    /**
     * Synchronously executes body at place(id)
     */
    public static void runClosureCopyAt(int place, x10.core.fun.VoidFun_0_0 body) {
        runAt(place, body);
    }

    /**
     * Copy body (same place)
     */
    public static <T> T deepCopy(T body) {
        try {
            // copy body
            long startTime = 0L;
            if (TRACE_SER) {
                startTime = System.nanoTime();
            }
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
            oos.writeObject(body);
            oos.close();
            byte[] ba = baos.toByteArray();
            if (TRACE_SER) {
                long endTime = System.nanoTime();
                System.out.println("Serializer: serialized " + ba.length + " bytes in " + (endTime - startTime) / 1000 + " microsecs.");
            }
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(ba)); 
            body = (T) ois.readObject();
            ois.close();
        } catch (java.io.IOException e) {
            x10.core.Throwable xe = ThrowableUtilities.getCorrespondingX10Exception(e);
            xe.printStackTrace();
            throw xe;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new java.lang.Error(e);
        }
        return body;
    }

    // @MultiVM, add this method 
    public static void runAt(int place, x10.core.fun.VoidFun_0_0 body) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			(new java.io.ObjectOutputStream(baos)).writeObject(body);
			byte[] msg = baos.toByteArray();
			int msgLen = baos.size();
			if (X10RT.VERBOSE) System.out.println("@MultiVM: sendJavaRemote");
			x10.x10rt.MessageHandlers.runClosureAtSend(place, msgLen, msg);
		} catch (java.io.IOException e){
			e.printStackTrace();
            throw new x10.runtime.impl.java.WrappedThrowable(e);
		} finally {
			if (X10RT.VERBOSE) System.out.println("@MULTIVM: finally section");
		}
	}
    // Special version of runAt for broadcast type communication
    // (Serialize once, run everywhere)
    public static void runAtAll(boolean includeHere, x10.core.fun.VoidFun_0_0 body) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			(new java.io.ObjectOutputStream(baos)).writeObject(body);
			byte[] msg = baos.toByteArray();
			int hereId = X10RT.here();
	        for (int place = hereId + 1; place < Runtime.MAX_PLACES; ++place) {
	        	x10.x10rt.MessageHandlers.runClosureAtSend(place, msg.length, msg);
	        }
	        int endPlace = includeHere ? hereId : hereId - 1;
	        for (int place = 0; place <= endPlace; ++place) {
	        	x10.x10rt.MessageHandlers.runClosureAtSend(place, msg.length, msg);
	        }
		} catch (java.io.IOException e){
			e.printStackTrace();
            throw new x10.runtime.impl.java.WrappedThrowable(e);
		}
    }

	/**
	 * @MultiVM: Return true if place(id) is local to this node
	 */
	public static boolean local(int id) {
		int hereId = X10RT.here();
		return (hereId == id);
	}
	
	
	/**
	 *  @MultiVM: mapped to Runtime.x10 -> event_probe(): void
	 */
	public static void eventProbe(){
		X10RT.probe();
	}

	/**
	 * Load environment variables.
	 */
	public static x10.util.HashMap<String,String> loadenv() {
	    Map<String,String> env = System.getenv();
	    x10.util.HashMap<String,String> map = new x10.util.HashMap<String,String>(x10.rtt.Types.STRING,x10.rtt.Types.STRING);
	    for(Map.Entry<String, String> e : env.entrySet()) {
	        if (e.getKey().startsWith("X10_")) {
	            map.put_0_$$x10$util$HashMap_K_1_$$x10$util$HashMap_V(e.getKey(), e.getValue());
	        }
	    }
	    return map;
	}

	/**
	 * Redirect to the specified user class's main().
	 */
	public static void main(String[] args) throws Throwable {
	    boolean verbose = false;
	    String className = null;
	    for (int i = 0; i < args.length; i++) {
	        String arg = args[i];
	        if (arg.equals("-v") || arg.equals("-verbose") || arg.equals("--verbose")) {
	            verbose=true;
	        } else if (arg.charAt(0)=='-') {
	            int eq = arg.indexOf('=');
	            String key = "x10." + (eq<0 ? arg.substring(1) : arg.substring(1, eq));
	            String value = eq<0 ? "true" : arg.substring(eq+1);
	            System.setProperty(key, value);
	        } else {
	            int dotx10 = arg.indexOf(".x10");
	            className = (dotx10<0 ? arg : arg.substring(0, dotx10)) + "$$Main";
	            int len = args.length-i-1;
	            System.arraycopy(args, i+1, args = new String[len], 0, len);
	        }
	    }
	    if (verbose) {
	        System.err.println("Invoking user class: "+className+" with classpath '"+System.getProperty("java.class.path")+"'");
	    }
	    try {
	        Class.forName(className).getMethod("main", String[].class).invoke(null, (Object)args);
	    } catch (ClassNotFoundException e) {
	        System.err.println("Class not found: "+className);
	    } catch (InvocationTargetException e) {
	        throw e.getCause();
	    } catch (Exception e) {
	        System.err.println("Unable to invoke user program: "+e);
	        if (verbose)
	            e.printStackTrace();
	    }
	}
}
