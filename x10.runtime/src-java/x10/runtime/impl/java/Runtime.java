/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */
package x10.runtime.impl.java;

import com.ibm.pgas.*;

public abstract class Runtime implements Runnable {
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

        PGASRT.boot(1);

        PGASRT.barrier();

        if(0 == PGASRT.here().getId()) {
            // start and join main x10 thread in place 0
            try { Class.forName("x10.lang.Place"); } catch (ClassNotFoundException e) { }
            // execute root x10 activity
            main(x10.core.RailFactory.<java.lang.String>makeRailFromJavaArray(args));
        }

        // remove this!
        PGASRT.barrier();

		// shutdown
		System.exit(exitCode);
	}

	/**
	 * User code provided by Main template
	 * - start xrx runtime
	 * - run main activity
	 */
	public abstract void main(x10.core.Rail<java.lang.String> args);

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
	 * Process place checks?
	 */
	public static final boolean PLACE_CHECKS = !Boolean.getBoolean("x10.NO_PLACE_CHECKS");

    /**
     * Disable steals?
     */
    public static final boolean NO_STEALS = Boolean.getBoolean("x10.NO_STEALS");

	/**
	 * The number of places in the system
	 */
	public static final int MAX_PLACES = Integer.getInteger("x10.NUMBER_OF_LOCAL_PLACES", 4);

	/**
	 * The number of threads to allocate in the thread pool
	 */
	public static final int INIT_THREADS = Integer.getInteger("x10.INIT_THREADS_PER_PLACE", java.lang.Runtime.getRuntime().availableProcessors());

	/**
	 * Synchronously executes body at place(id)
	 */
	public static void runAt(int id, x10.core.fun.VoidFun_0_0 body) {
		final Thread thread = Thread.currentThread();
		final int ret = thread.location();
		thread.location(id); // update thread place
		try {
			body.apply();
		} finally {
			thread.location(ret); // restore thread place
		}
	}

	/**
	 * Return true if place(id) is local to this node
	 */
	public static boolean local(int id) {
		return true; // single process implementation
	}
}
