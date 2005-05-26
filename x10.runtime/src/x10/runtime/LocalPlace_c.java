package x10.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import x10.lang.Future;


/**
 * A LocalPlace_c is an implementation of a place
 * that runs on this Java Virtual Machine.  In the
 * future we will have RemotePlaces that refer to
 * Places on other machines.
 * 
 * @author Christian Grothoff
 * @author vj
 */
public class LocalPlace_c extends Place {

    /**
     * Compute the number of simulated cycles spent
     * globally at this point.
     * 
     * this method must not be called in the constructor of 
     * LocalPlace_c, and not before the runtime is initialized 
     * completely.
     *  
     * @return max of all simulatedPlaceTimes of all places
     */
    public static long systemNow() {
        long max = 0;
        Place[] places = x10.lang.Runtime.places();
        for (int i=places.length-1;i>=0;i--) {
            long val = 0;
            if (places[i] instanceof LocalPlace_c) {
                val = ((LocalPlace_c) places[i]).getSimulatedPlaceTime();
            }
            if (val > max)
                max = val;
        }
        return max;
    }
    
    public static void initAllPlaceTimes(Place[] places) {
        long max = 0;
        for (int i=places.length-1;i>=0;i--) {
            assert (places[i] instanceof LocalPlace_c);
            long val = 0;
            LocalPlace_c lp = (LocalPlace_c) places[i];
            val = lp.getSimulatedPlaceTime();
            if (val > max)
                max = val;
        }
        for (int i=places.length-1;i>=0;i--) {
            LocalPlace_c lp = (LocalPlace_c) places[i];
            lp.startBlock = max; 
        }
    }
    
   
    /**
     * Is this place shutdown?
     */
    boolean shutdown;

    /**
     * How many threads are truely running (not blocked) in this place?
     */
    int runningThreads;
    
    /**
     * Linked list of threads in the thread pool that are not currently
     * assigned to an Activity.  Package scoped to allow sampling.
     */
    PoolRunner threadQueue_;

    /**
     * List of all of the threads of this place.
     */
    final ArrayList myThreads = new ArrayList(); // <PoolRunner>
    
    /**
     * The amount of cycles that this places was blocked waiting
     * for activities at other places to complete.  
     */
    long blockedTime;
    
    /**
     * "global" time at which this place was blocked (that is, all
     * activities at this place were blocked).
     */
    private long startBlock; 
    
    LocalPlace_c() {
        super();
    }
    
    /**
     * Get how many cycles were spent in computation or blocked at this 
     * place so far.  Only (sort of) works on JikesRVM where we can get
     * per-thread cycle counts.
     *
     * @return
     */
    public long getSimulatedPlaceTime() {
        long ret = blockedTime;
        synchronized (myThreads) {
            for (int i = myThreads.size()-1;i>=0;i--)
                ret += ((PoolRunner)myThreads.get(i)).getThreadRunTime();
        }
        return ret;
    }
    
    synchronized void addThread( PoolRunner p) {
    	synchronized (myThreads) { myThreads.add(p); }
    }
    /**
     * Shutdown this place, the current X10 runtime will exit.    Assumes
     * that all activities have already completed.  Threads beloging
     * to activities that are not done at this point will be left
     * running (which is probably a good policy, least for the thread
     * that calls shutdown :-).
     */
    public synchronized void shutdown() {
    	synchronized (this) {
    		shutdown = true;
    		if (Report.should_report("activity", 5)) {
    			Report.report(5, Thread.currentThread() +  "@" + System.currentTimeMillis() 
    					+" shutting down " + threadQueue_);
    			PoolRunner list = threadQueue_;
    			while (list != null) {
    				if (Report.should_report("activity", 5)) {
    					Report.report(5, Thread.currentThread() +  "@" + list.place + ":" + System.currentTimeMillis() 
    						+"  threadpool contains " + list);
    				}
    				list = list.next;
    			}
    		}
    	}
        while (this.threadQueue_ != null) {
        	if (Report.should_report("activity", 5)) {
        		Report.report(5, Thread.currentThread() +  "@" + System.currentTimeMillis() +" shutting down " + threadQueue_);
        	}
        	
            threadQueue_.shutdown();
            
            try {
                threadQueue_.join();
            	if (Report.should_report("activity", 5)) {
            		Report.report(5, Thread.currentThread() +  "@" + System.currentTimeMillis() + " " + threadQueue_ + " shut down.");
            	}
            } catch (InterruptedException ie) {
                throw new Error(ie); // should never happen...
            }
            
            threadQueue_ = threadQueue_.next;
        }
    }
boolean isShutdown() { return shutdown; }
    /**
     * Run the given activity asynchronously.
     * vj 5/17/05. This has been completely revamped,
     * with Activity given much more responsibility for its execution.
     */
    public void runAsync(final Activity a) {
    	runAsync( a, false);
    }
    /**
     * Run this activity asynchronously, as if it is wrapped in a finish.
     * That is, wait for its global termination.
     * @param a
     */
    public void finishAsync( final Activity a) {
    	runAsync( a, true);
    }
    protected void runAsync(final Activity a, final boolean finish) {
   
      //  final StartSignal startSignal = new StartSignal();
        Thread currentThread = Thread.currentThread();
        if (currentThread instanceof ActivityRunner) {
        	Activity parent = ((ActivityRunner) currentThread).getActivity();
        	parent.finalizeActivitySpawn(a);
        }
        a.initializeActivity();
        this.execute(new Runnable() {
            public void run() {
            	// Get a thread to run this activity.
                PoolRunner t = (PoolRunner) Thread.currentThread();
                if (Report.should_report("activity", 5)) {
            		Report.report(5, t + " is running " + this);
            	}
 
                // Install the activity.
                t.setActivity(a);
                a.setPlace(LocalPlace_c.this);
                
            /*  synchronized(startSignal) {
                    startSignal.go = true;
                    startSignal.notifyAll();
                }*/
                
                try {
                	if (finish) {
                		a.finishRun();
                	} else {
                		a.run();
                	}
                	a.finalizeTermination();
                } catch (Throwable e) {
                	// e.printStackTrace();
                	// System.err.println("LocalPlace_c::runAsync - unexpected exception " + e);
                	// can never arrive here if finish=true
                	a.finalizeAbruptTermination(e);
                } 
            }
            public String toString() { return "<Executor " + a+">";}
            
        });
        // vj: 5/17 Check why this needs to be done.
        // we now need to wait at least (!) until the 
        // "reg_.registerActivityStart(...)" line has been
        // reached.  Hence we wait on the start signal.
       /*synchronized (startSignal) {
            try {
                while (! startSignal.go) {
                    startSignal.wait();
                }
            } catch (InterruptedException ie) {
                System.err.println("LocalPlace_c::runAsync - unexpected exception " + ie);
                throw new Error(ie); // should never happen!
            }
        }*/
    }
    
    /**
     * Run the given activity asynchronously.  Return a handle that
     * can be used to force the future result.
     */
    public Future runFuture(final Activity.Expr a) {
    	Future_c result = a.future = new Future_c(a);
    	runAsync(a);
    	return result;
    }

    /**
     * Run the given Runnable using one of the threads in the thread pool.
     * Note that the activity is guaranteed to be assigned a thread 
     * right away, the pool can never be exhausted (the size is infinite).
     * 
     * @param r the activity to run
     * @throws InterruptedException
     */
    protected void execute(Runnable r) {
        PoolRunner t;
        synchronized(this) {
        	if (threadQueue_ == null) {
        		t = new PoolRunner(this);
        		t.setDaemon(false);
        		t.start();
        		if (Report.should_report("activity", 5)) {
            		Report.report(5, Thread.currentThread() +  "@" + System.currentTimeMillis() +"LocalPlace starts " 
            				+ (t.isDaemon() ? "" : "non") + "daemon thread " + t 
							+ "in group " + Thread.currentThread().getThreadGroup() 
							 +".");
            	}
        		
        	} else {
        		t = threadQueue_;
        		threadQueue_ = t.next;
        	}
        }
        t.run(r);
    }
     
    /**
     * Method called by a PoolRunner to add a thread back
     * to the thread pool (the PoolRunner is done running
     * the job).
     * 
     * @param r
     */
    synchronized final void repool(PoolRunner r) {
    	if (Report.should_report("activity", 5)) {
    		Report.report(5, Thread.currentThread() +  "@" + System.currentTimeMillis() +" repools (shutdown=" + shutdown + ").");
    	}
    	
        r.next = threadQueue_;
        threadQueue_ = r;
    }
    
  
    /**
     * Change the 'running' status of a thread.
     * @param delta +1 for thread starts to run (unblocked), -1 for thread is blocked
     */
    /*package*/ synchronized void changeRunningStatus(int delta) {
        if (runningThreads == 0) {
            assert delta > 0;
            this.blockedTime += systemNow() - startBlock;
        }
        runningThreads += delta;
        if (runningThreads == 0) {
            assert delta < 0;
            startBlock = systemNow();
        }
    }
    
    public String longName() {
    	return this.toString() + "(shutdown="+shutdown+")";
    }
    
    static class StartSignal {
        boolean go;
    }    
    
} // end of LocalPlace_c