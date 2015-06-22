/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2015.
 */
/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package apgas.pool;

import java.security.AccessControlContext;
import java.security.ProtectionDomain;

/**
 * A thread managed by a {@link ForkJoinPool}, which executes
 * {@link ForkJoinTask}s.
 * This class is subclassable solely for the sake of adding
 * functionality -- there are no overridable methods dealing with
 * scheduling or execution.  However, you can override initialization
 * and termination methods surrounding the main task processing loop.
 * If you do create such a subclass, you will also need to supply a
 * custom {@link ForkJoinPool.ForkJoinWorkerThreadFactory} to
 * {@linkplain ForkJoinPool#ForkJoinPool use it} in a {@code ForkJoinPool}.
 *
 * @since 1.7
 * @author Doug Lea
 */
public class ForkJoinWorkerThread extends Thread {
    /*
     * ForkJoinWorkerThreads are managed by ForkJoinPools and perform
     * ForkJoinTasks. For explanation, see the internal documentation
     * of class ForkJoinPool.
     *
     * This class just maintains links to its pool and WorkQueue.  The
     * pool field is set immediately upon construction, but the
     * workQueue field is not set until a call to registerWorker
     * completes. This leads to a visibility race, that is tolerated
     * by requiring that the workQueue field is only accessed by the
     * owning thread.
     *
     * Support for (non-public) subclass InnocuousForkJoinWorkerThread
     * requires that we break quite a lot of encapsulation (via Unsafe)
     * both here and in the subclass to access and set Thread fields.
     */

    final ForkJoinPool pool;                // the pool this thread works in
    final ForkJoinPool.WorkQueue workQueue; // work-stealing mechanics

    /**
     * Creates a ForkJoinWorkerThread operating in the given pool.
     *
     * @param pool the pool this thread works in
     * @throws NullPointerException if pool is null
     */
    protected ForkJoinWorkerThread(ForkJoinPool pool) {
        // Use a placeholder until a useful name can be set in registerWorker
        super("aForkJoinWorkerThread");
        this.pool = pool;
        this.workQueue = pool.registerWorker(this);
    }

    /**
     * Version for InnocuousForkJoinWorkerThread
     */
    ForkJoinWorkerThread(ForkJoinPool pool, ThreadGroup threadGroup,
                         AccessControlContext acc) {
        super(threadGroup, null, "aForkJoinWorkerThread");
        U.putOrderedObject(this, INHERITEDACCESSCONTROLCONTEXT, acc);
        eraseThreadLocals(); // clear before registering
        this.pool = pool;
        this.workQueue = pool.registerWorker(this);
    }

    /**
     * Returns the pool hosting this thread.
     *
     * @return the pool
     */
    public ForkJoinPool getPool() {
        return pool;
    }

    /**
     * Returns the unique index number of this thread in its pool.
     * The returned value ranges from zero to the maximum number of
     * threads (minus one) that may exist in the pool, and does not
     * change during the lifetime of the thread.  This method may be
     * useful for applications that track status or collect results
     * per-worker-thread rather than per-task.
     *
     * @return the index number
     */
    public int getPoolIndex() {
        return workQueue.getPoolIndex();
    }

    /**
     * Initializes internal state after construction but before
     * processing any tasks. If you override this method, you must
     * invoke {@code super.onStart()} at the beginning of the method.
     * Initialization requires care: Most fields must have legal
     * default values, to ensure that attempted accesses from other
     * threads work correctly even before this thread starts
     * processing tasks.
     */
    protected void onStart() {
    }

    /**
     * Performs cleanup associated with termination of this worker
     * thread.  If you override this method, you must invoke
     * {@code super.onTermination} at the end of the overridden method.
     *
     * @param exception the exception causing this thread to abort due
     * to an unrecoverable error, or {@code null} if completed normally
     */
    protected void onTermination(Throwable exception) {
    }

    /**
     * This method is required to be public, but should never be
     * called explicitly. It performs the main run loop to execute
     * {@link ForkJoinTask}s.
     */
    public void run() {
        if (workQueue.array == null) { // only run once
            Throwable exception = null;
            try {
                onStart();
                pool.runWorker(workQueue);
            } catch (Throwable ex) {
                exception = ex;
            } finally {
                try {
                    onTermination(exception);
                } catch (Throwable ex) {
                    if (exception == null)
                        exception = ex;
                } finally {
                    pool.deregisterWorker(this, exception);
                }
            }
        }
    }

    /**
     * Erases ThreadLocals by nulling out Thread maps.
     */
    final void eraseThreadLocals() {
        U.putObject(this, THREADLOCALS, null);
        U.putObject(this, INHERITABLETHREADLOCALS, null);
    }

    /**
     * Non-public hook method for InnocuousForkJoinWorkerThread
     */
    void afterTopLevelExec() {
    }

    // Set up to allow setting thread fields in constructor
    private static final sun.misc.Unsafe U;
    private static final long THREADLOCALS;
    private static final long INHERITABLETHREADLOCALS;
    private static final long INHERITEDACCESSCONTROLCONTEXT;
    static {
        try {
            java.lang.reflect.Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (sun.misc.Unsafe) field.get(null);
            THREADLOCALS = U.objectFieldOffset
                (Thread.class.getDeclaredField("threadLocals"));
            INHERITABLETHREADLOCALS = U.objectFieldOffset
                (Thread.class.getDeclaredField("inheritableThreadLocals"));
            INHERITEDACCESSCONTROLCONTEXT = U.objectFieldOffset
                (Thread.class.getDeclaredField("inheritedAccessControlContext"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /**
     * A worker thread that has no permissions, is not a member of any
     * user-defined ThreadGroup, and erases all ThreadLocals after
     * running each top-level task.
     */
    static final class InnocuousForkJoinWorkerThread extends ForkJoinWorkerThread {
        /** The ThreadGroup for all InnocuousForkJoinWorkerThreads */
        private static final ThreadGroup innocuousThreadGroup =
            createThreadGroup();

        /** An AccessControlContext supporting no privileges */
        private static final AccessControlContext INNOCUOUS_ACC =
            new AccessControlContext(
                new ProtectionDomain[] {
                    new ProtectionDomain(null, null)
                });

        InnocuousForkJoinWorkerThread(ForkJoinPool pool) {
            super(pool, innocuousThreadGroup, INNOCUOUS_ACC);
        }

        @Override // to erase ThreadLocals
        void afterTopLevelExec() {
            eraseThreadLocals();
        }

        @Override // to always report system loader
        public ClassLoader getContextClassLoader() {
            return ClassLoader.getSystemClassLoader();
        }

        @Override // to silently fail
        public void setUncaughtExceptionHandler(UncaughtExceptionHandler x) { }

        @Override // paranoically
        public void setContextClassLoader(ClassLoader cl) {
            throw new SecurityException("setContextClassLoader");
        }

        /**
         * Returns a new group with the system ThreadGroup (the
         * topmost, parent-less group) as parent.  Uses Unsafe to
         * traverse Thread.group and ThreadGroup.parent fields.
         */
        private static ThreadGroup createThreadGroup() {
            try {
                sun.misc.Unsafe u = sun.misc.Unsafe.getUnsafe();
                long tg = u.objectFieldOffset
                    (Thread.class.getDeclaredField("group"));
                long gp = u.objectFieldOffset
                    (ThreadGroup.class.getDeclaredField("parent"));
                ThreadGroup group = (ThreadGroup)
                    u.getObject(Thread.currentThread(), tg);
                while (group != null) {
                    ThreadGroup parent = (ThreadGroup)u.getObject(group, gp);
                    if (parent == null)
                        return new ThreadGroup(group,
                                               "InnocuousForkJoinWorkerThreadGroup");
                    group = parent;
                }
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
            // fall through if null as cannot-happen safeguard
            throw new Error("Cannot create ThreadGroup");
        }
    }

}
