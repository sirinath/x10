package x10.runtime;

import java.util.ArrayList;
import java.util.Stack;

import x10.lang.Future;
import x10.lang.place;
/**
 * A LocalPlace_c is an implementation of a place
 * that runs on this Java Virtual Machine.  In the
 * future we will have RemotePlaces that refer to
 * Places on other machines.
 * 
 * The major resource controlled by this place is a threadpool, the total number
 * of threads is controlled by Configuration.NUMBER_OF_ACTIVITIES_PER_PLACE. New
 * threads can be created freely up to this number.  Thread is an instance of
 * <code>PoolRunner</code>, and thus is link by its <code>next</code> field.  
 * PoolRunner has a state variable <code>isactive</code> which when clear will
 * finish the Thread.  <code>shutdown</code> will shut down this place, and 
 * leave all the threads finish its current job and then terminate. 
 * @author xinb
 * 
 * @author Christian Grothoff
 * @author vj
 */
public class LocalPlace_c extends Place {
	/**
	 * Is this place shutdown?
	 */
	boolean shutdown;
	
	/**
	 * How many threads are truely running (not blocked) in this place?
	 */
	protected int runningThreads;
	
	/**
	 * Linked list of threads in the thread pool that are not currently
	 * assigned to an Activity.  Package scoped to allow sampling.
	 */
	PoolRunner threadQueue_;
	
	/**
	 * List of all of the threads of this place.
	 */
	//dead code ? final ArrayList myThreads = new ArrayList(); // <PoolRunner>
	

	/**
	 * List of activities that are waiting for a thread, to be
	 * launched when the local place becomes idle.
	 */
	protected final Stack waitingActivities = new Stack();
	
	//dead code?
	/*
	synchronized void addThread( PoolRunner p) {
		synchronized (myThreads) { myThreads.add(p); }
	}
	*/
	
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
				Report.report(5, PoolRunner.logString() + " shutting down " + threadQueue_);
				PoolRunner list = threadQueue_;
				while (list != null) {
					if (Report.should_report("activity", 7)) {
						Report.report(7, Thread.currentThread() +  "@" + list.homePlace + ":" + System.currentTimeMillis() 
								+"  threadpool contains " + list);
					}
					list = list.next;
				}
			}
		}
		while (this.threadQueue_ != null) {
			if (Report.should_report("activity", 5)) {
				Report.report(5, PoolRunner.logString() + " shutting down " + threadQueue_);
			}
			
			threadQueue_.shutdown();
			
			try {
				threadQueue_.join();
				if (Report.should_report("activity", 5)) {
					Report.report(5, PoolRunner.logString() + " " + threadQueue_ + " shut down.");
				}
			} catch (InterruptedException ie) {
				throw new Error(ie); // should never happen...
			}
			
			threadQueue_ = threadQueue_.next;
		}
	}
	
	public boolean isShutdown() { 
		return shutdown; 
	}
	/**
	 * Run the given activity asynchronously.
	 * vj 5/17/05. This has been completely revamped,
	 * with Activity given much more responsibility for its execution.
	 */   
	public void runAsync(final Activity a) {
			prepareAsync(a);
			runAsync( a, false);	
	}

	public void runAsyncLater(Activity a) {
		if (Configuration.OPTIMIZE_FOREACH) {
				prepareAsync(a);
				waitingActivities.push(a);
				changeRunningStatus(0);
		} else {
			runAsync(a);
		}
	}	
	
	private void prepareAsync(Activity a) {
		Thread currentThread = Thread.currentThread();  
		if (currentThread instanceof ActivityRunner) {
			// This will not be executed only for bootActivity.
			Activity parent = ((ActivityRunner) currentThread).getActivity();
			parent.finalizeActivitySpawn(a);
		}
		a.initializeActivity();
	}
    
	/**
	 * Run this activity asynchronously, as if it is wrapped in a finish.
	 * That is, wait for its global termination.
	 * @param a
	 */
	public void finishAsync( final Activity a) {
		//System.out.println("X X X X X X X  finishAsync");
		prepareAsync(a);
		runAsync( a, true);
	}
	
	protected void runAsync(final Activity a, final boolean finish) {
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
							
				try {
					if (finish) {
						a.finishRun();
					} else {
						a.run();
					}
				} catch (Throwable e) {
					a.finalizeTermination(e);
					return;
				}
				//System.out.println("finish async");
				a.finalizeTermination(); //should not throw an exception.
			}
			public String toString() { return "<Executor " + a + ">";}
			
		});
	}
	
	/**
	 * Run the given activity asynchronously.  Return a handle that
	 * can be used to force the future result.
	 */
	public Future runFuture(final Activity.Expr a) {
		Future_c result = a.future = new Future_c();
		runAsync(a);
		return result;
	}
	 
    private int threadInPool_ = 0;
    
    public synchronized boolean shouldRunnerTerminate() {
        boolean ret;
        ret = (threadInPool_ > Configuration.NUMBER_OF_ACTIVITIES_PER_PLACE && threadQueue_ != null);
        if (ret)
            threadInPool_--;
        return ret;
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
	    //System.out.println("starting execute for "+r);
            synchronized(this) {
            
                // if threadQueue_ is empty, we do create a new thread right away if 
                // the more than MAX_NUM_THREADS_PER_PLACE are already created 
                // and not all threads are blocked! 
            
                // The following numbers I chose are 'magic', 
                // i.e. they seem to work resonably well on my thinkpad.
                long time_to_wait = 20 +  
                    ((threadInPool_ > 50) ? 1000 : 
                     (long) Math.pow(1.15, (double) threadInPool_));
                long start = -1;
                long next_turn = time_to_wait;
            
                while ( threadQueue_ == null &&
                        threadInPool_ >= Configuration.NUMBER_OF_ACTIVITIES_PER_PLACE) {
                    try {
                        if (start == -1)                         
                            start = System.currentTimeMillis();
                        wait(next_turn);
                    } catch (Exception e) {
                    	e.printStackTrace();
                        // should not happen 
                        assert (false);
                    }
                    next_turn = (start + time_to_wait) - System.currentTimeMillis();
                    if (next_turn <= 0)                     
                        break;
                }
            
                if (threadQueue_ == null) {
                    threadInPool_ ++;
                    t = new PoolRunner(this);               
                    t.setDaemon(false);
                    t.start();
                    if (Report.should_report("activity", 5)) {
                    	//when called from a non PoolRunner thread, commented will fail
                        Report.report(5, /*PoolRunner.logString() + */"LocalPlace starts " 
                                      + (t.isDaemon() ? "" : "non") + "daemon thread " + t 
                                      + "in group " + Thread.currentThread().getThreadGroup() 
                                      + ".");
                    }				
                } else {
                    t = threadQueue_;
                    threadQueue_ = t.next;
                }
            }
	    //System.out.println("starting run for "+r);
            t.run(r);
	    //System.out.println("finished run for "+r);
	}
	
	/**
	 * Method called by a PoolRunner to add a thread back
	 * to the thread pool (the PoolRunner is done running
	 * the job).
	 * 
	 * @param r
	 */
	/*package*/ synchronized final void repool(PoolRunner r) {
		if (Report.should_report("activity", 5)) {
			Report.report(5, PoolRunner.logString() + " repools (shutdown=" + shutdown + ").");
		}
		
		r.next = threadQueue_;
		threadQueue_ = r;        
        // try wakeup threads that are waiting to be serviced (method execute)
        notify();
	}	
	
	/**
	 * Change the 'running' status of a thread.
	 * @param delta +1 for thread starts to run (unblocked), -1 for thread is blocked
	 */
	/*package*/ synchronized void changeRunningStatus(int delta) {
		if (runningThreads == 0) {
			assert delta >= 0;
		}
		runningThreads += delta;
		if (runningThreads == 0) {
			assert delta <= 0;
			if (! waitingActivities.isEmpty()) {
				Activity a = (Activity) waitingActivities.pop();
				runAsync(a, false);				
			}
			
			//System.out.print(this+"Notifying All...");
			//System.out.println("isShutdown: "+isShutdown()+"runningThreads = "+runningThreads + "#waitingActivities = "+waitingActivities.size());
			notifyAll(); //@see ClusterPlace#shutdown
		}
	}
	
	public String longName() {
		return this.toString() + "(shutdown="+shutdown+")";
	}
	
	public static void shutdownAll() {
		Place[] places = x10.lang.Runtime.places();
		for (int i=places.length-1;i>=0;i--) {
			if (places[i] instanceof LocalPlace_c) {
				((LocalPlace_c) places[i]).shutdown();
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////
	//lifecycle API
	
	/**
	 * The states of current place, depends on <code>runningThreads</code>, <code>
	 * waitingActivities</code> and whether the <code>shutdown</code> command is
	 * issued.
	 * @author xinb
	 */
	public synchronized PlaceState getState() {		
		//only make sense if this place is Local to current VM
		//System.out.println(this+" isShutdown: "+isShutdown()+"runningThreads = "+runningThreads + "#waitingActivities = "+waitingActivities.size());
		if(runningThreads > 0 || ! waitingActivities.empty()) {
			if(isShutdown())
				return PlaceState.WAIT;
			else 
				return PlaceState.BUSY;
		} else {
			if(isShutdown()) 
				return PlaceState.TERMINATED;
			else
				return PlaceState.IDLE;
		}
	}
	
	
} // end of LocalPlace_c

