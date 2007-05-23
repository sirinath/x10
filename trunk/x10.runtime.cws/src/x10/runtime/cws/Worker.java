/*
 *
 * (C) Copyright IBM Corporation 2007
 *
 *  This file is part of X10 runtime. It is 
 *  governed by the licence under which 
 *  X10 is released.
 *
 */
package x10.runtime.cws;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import static x10.runtime.cws.ClosureStatus.*;

/**
 * The worker for Cilk-style work stealing. Instances of this worker
 * are created by Pool. 
 * API note: Code written by users of the work-stealing API is not intended
 * to subclass this class. Such code can invoke only the public methods of this
 * class. Such code must live outside the x10.runtime.cws package.
 * 
 * Code written by library writers that wish to extend work-stealing may 
 * subclass this class. 
 * @author vj 04/18/07
 *
 */
public class Worker extends Thread {
	/**
	 * The pool. This class could alternatively be an inner class,
	 * but the reference is left explicit to make it easier to see
	 * and check which calls are local to threads vs global to
	 * pool.
	 */
	protected final Pool pool;
	protected Closure top, bottom;
	final public Cache cache;
	protected Lock lock; // dequeue_lock
	protected Thread lockOwner; // the thread holding the lock.
	protected int randNext;
	protected int index;
	protected volatile boolean done;
	protected Closure closure;
	
	protected static volatile Worker[] workers; 
	public static long stealAttempts;
	public static long steals;
	public static boolean reporting = false;
	/**
	 * Creates new Worker.
	 */
	protected Worker(Pool pool, int index) {
		this.pool = pool;
		this.index = index;
		this.lock = new ReentrantLock();
		this.cache = new Cache(this);
		setDaemon(true);
		// Further initialization postponed to init()
	}
	
	
	protected void lock(Thread agent) {
		lock.lock();
		this.lockOwner=agent;
	}
	protected void unlock() {
		this.lockOwner=null;
		lock.unlock();
	}

	protected void setRandSeed(int seed) {
		randNext = seed;
	}
	protected int rand() {
		randNext = randNext*1103515245  + 12345;
		int result = randNext >> 16;
		if (result < 0) result = -result;
		return result;
	}
	/**
	 * Called by thief on victim. In the body of this method
	 * victim==this and thief==Thread.currentThread().
	 * @param thief -- The thread making this invocation.
	 * @return
	 */
	protected Closure steal(Worker thief, boolean retry) {
		
		final Worker victim = this;
		if (false && reporting) {
			++stealAttempts;
			//System.out.println(thief + " attempts to steal from " + thief.index);
		}
		
		lock(thief);
		Closure cl=null;
		try {
			cl = peekTop(thief, victim);
			// vj: I believe the victim's ready deque should have only one
			// closure in it.
			Closure cl1 = peekBottom(thief);
			assert cl1==cl;
		} catch (Throwable z) {
			unlock();
			// wrap in an error.
			throw new Error(z); 
		}
		if (cl == null) {
			try {
				if (false && reporting) {
				//	System.out.println(thief + " steal attempt: queue empty on  " + thief.index);
				}
				return null;
			} finally {
				lock.unlock();
			}
		}
		try {
			cl.lock(thief);
		} catch (Throwable z) {
			unlock();
			throw new Error(z);
		}
		ClosureStatus status = null;
		try {
			status = cl.status();
		} catch (Throwable z) {
			cl.unlock();
			unlock();
			throw new Error(z);
		}
		assert (status == ABORTING || status == READY || status == RUNNING || status == RETURNING);
		if (status == READY) {
			try {
				Closure res = extractTop(thief);
				assert (res == cl);
				if (false && reporting) {
					++ steals;
					System.out.println(thief + " steals ready " + cl + " from "
							+ victim);
				}
				return res;
			} finally {
				cl.unlock();
				lock.unlock();
			}
		}
		if (status == RUNNING) {
			boolean b = false;
			try {
				b=cl.dekker(thief);
			} catch (Throwable z) {
				cl.unlock();
				unlock();
				throw new Error(z);
			}
			
			if (b) {
				Closure child = null;
				Closure res = null;
				try {
					child = cl.promoteChild(thief, victim);
					res = extractTop(thief);
					assert cl==res;
				} finally {
					lock.unlock();
				}
				try {
					cl.finishPromote(thief, child);
				} finally {
					cl.unlock();
				}
				if (false && reporting) {
					++steals;
					System.out.println(thief + " steals running " + cl + " from "
							+ victim);
				}
				return res;
			} 
		}
		// this path taken when status == RETURNING, 
		// status==ABORTING, or status==RUNNING and dekker failed.
		lock.unlock();
		cl.unlock();
		return null;
		
	}

	/* 
	 * Extract the topmost closure in the ready deque of this
	 * worker. May return null.
	 * @aparam ws -- the current thread, i.e. Thread.currentThread()=ws
	 */
	protected Closure extractTop(Thread ws) {
		assert lockOwner==ws;
		Closure cl = top;
		if (cl == null) {
			assert bottom == null;
			return cl;
		}
		top = cl.nextReady;
		if (cl == bottom) {
			assert cl.nextReady == null;
			bottom = null;
		} else {
			assert cl.nextReady !=null;
			cl.nextReady.prevReady = null;
		}
		cl.ownerReadyQueue = null;
		return cl;
	}
	/**
	 * Return the top of the closure deque, without removing it.
	 * @return top of the closure deque
	 * @param ws -- the current thread, i.e. Thread.currentThread()==ws
	 */
	protected Closure peekTop(Worker agent, Worker subject) {
		assert lockOwner==agent;
		Closure cl = top;
		if (cl == null) {
			assert bottom==null;
			return null;
		}
		assert cl.ownerReadyQueue == subject;
		return cl;
	}
	/**
	 * Return the closure at the bottom fo the deque.
	 * Required: ws = Thread.currentThread()
	 * @return
	 */
	protected Closure extractBottom(Thread ws) {
		
		assert lockOwner==ws;
		Closure cl=bottom;
		if (cl == null) {
			assert top==null;
			return null;
		}
		assert cl.ownerReadyQueue ==ws;
		bottom = cl.prevReady;
		if (cl == top) {
			assert cl.prevReady==null;
			top=null;
		} else {
			assert cl.prevReady !=null;
			cl.prevReady.nextReady = null;
		}
		cl.ownerReadyQueue=null;
		return cl;
	}
	/**
	 * Peek at the closure at the bottom of the ready deque.
	 * @param ws -- The current thread, i.e. ws == Thread.currentThread().
	 * @return
	 */
	protected Closure peekBottom(Worker ws) {
		assert lockOwner==ws;
		Closure cl = bottom;
		if (cl==null) {
			assert top==null;
			return cl;
		}
		cl.ownerReadyQueue = this;
		return cl;
	}
	/**
	 * Add the given closure to the bottom of the
	 * worker's ready deque.
	 * @parame ws -- the current thread, i.e. ws==Thread.currentThread().
	 *               Note: current thread may not always be a Worker.
	 * @param cl -- the closure to be added.
	 */
	protected void addBottom(Thread ws, Closure cl) {
		
		assert lockOwner==ws;
		assert cl !=null;
		assert cl.ownerReadyQueue == null;
		
		if (false && reporting)
			System.out.println(ws + " adds " + cl + " to " + this + " bottom.");
		cl.prevReady = bottom;
		cl.nextReady = null;
		cl.ownerReadyQueue = this;
		bottom = cl;
		if (top == null) {
			top = cl;
		} else {
			cl.prevReady.nextReady = cl;
		}
		
	}
	
	
	/**
	 * A slow sync. Must be invoked only by the this Worker, i.e.
	 * Thread.currentThread() == this.
	 * @return true if the closure has to be suspended, false o.w.
	 */
	protected boolean sync() {
		lock(this);
		try { 
			Closure t = peekBottom(this);
			t.lock(this);
			try { 
				assert t.status==RUNNING;
				
				// In slow sync. Therefore must be the case
				// that there is no frame on the stack.
				// i.e. this worker has finished executing any children asyncs. 
				// Other workers may still be working on children.
				assert t.cache.atTopOfStack();
				// Execute all completed inlets.
				// Note these are being executed in a single-threaded
				// context since the lock is held.
				t.pollInlets(this);
				// pollInlets may change bottom of queue, due to abort processing.
				assert t == bottom;
				
				if (t.hasChildren()) {
					// Suspend. Now any child that is joining
					// will get to check inlets and if they 
					// are all done, then execute this task
					// in place.
					if (false && reporting) {
						System.out.println(this + " suspends " + t + "(joincount=" + t.joinCount+")");
					}
					
					t.suspend(this);
					//  Vj: Added this popFrame. Note sure that Cilk does this. Without this
					// caches are left behind with unpopped frames. This interferes with
					// subsequent execution of other closures.
					popFrame();
					return true;
				}
			} finally {
				t.unlock();
			}
			
		} finally {
			unlock();
		}
		return false;
		
	}
	

	public Closure scanTasks() {
		Closure cl = null;
		Worker[] workers = pool.getWorkers();
		int n = workers.length;
		int idx = rand() % n;
		int origin = idx;
		Worker thief = this;
		boolean retry = false;
		for (;;) {
			
			Worker victim = workers[idx];
			if (victim != null && victim !=thief) {
				cl = victim.steal(thief, retry);
				if (cl != null)
					return cl;
			}
			if (++idx >= n) idx = 0;
			if (idx==origin) {
				if (! retry) 
					retry = true; 
				else 
					return null;
			}
		}
		
	}
	@Override
	public void run() {
		assert index >= 0;
		setRandSeed(index*162347);
		Closure cl = closure;
		int yields = 0;
		while (!done) {
			
			if (cl == null) {
//				 Try geting work from the local queue.
				lock(this);
				try {
					cl = extractBottom(this);
				} finally {
					unlock();
				}
			}
			if (cl == null)
				cl = scanTasks();
			
			if (cl == null) {
				cl = pool.getJob(yields);
			}
			
			if (cl !=null) {
				// Found some work! Execute it.
				yields = 0;
				assert cache == null || cache.empty();
				if (false && reporting) {
					System.out.println(this + " executes " + cl +".");
				}
				Closure cl1 = cl.execute(this);
				if (false && reporting && bottom == cl) {
					System.out.println(this + " completes " + cl + ".");
				}
				cl=cl1;
				
				// vj: This avoids having to implement a wrap around.
				// Otherwise, head might increase on a steal, but would
				// never decrease.
				cache.reset();
				
			} else {
				yields++;
				Thread.yield();
			}
		}
	}
	/**
	 * Push a frame onto the stack of the current
	 * closure (the closure at the bottom of the deque).
	 * Called by client code in the body of a procedure
	 * which has a spawn async before the first spawn.
	 * @param frame -- the frame to be pushed.
	 */
	public void pushFrame(Frame frame) {
		cache.pushFrame(frame);
	}
	/**
	 * Pop the last frame from the stack.
	 *
	 */
	public void popFrame() {
		cache.popFrame();
	}
	/**
	 * Called by client code on return from a spawn async
	 * invocation. Performs the victim end of Dekker.
	 * @return  -- the closure at the bottom of the worker's
	 *             deque in case the closure controlling
	 *             the async  has been stolen by a thief
	 *             since async execution started. null otherwise.
	 *            
	 */
	public boolean popFrameCheck(int y) {
		
		if (! cache.popCheck()) 
			// fast path
			return false;
		// slow path. A steal has happened.
		// need to grab the lock on the deque
		// to get the bottom closure.
		boolean result = exceptionHandler(y);
		if (result)
			popFrame();
		return result;
	}
	public String toString() {
		return "Worker("+index+")";
	}
	public Closure bottom() {
		return bottom;
	}
	protected boolean exceptionHandler(int y) {
		lock(this);
		try {
			Closure b = bottom;
			assert b !=null;
			b.lock(this);
			try { 
				b.pollInlets(this);
				Closure c = bottom;
				assert (c!=null);
				if (b != c) {
					b.unlock();
					b=c;
					b.lock(this);
				}
				boolean result = b.handleException(this, y);
				return result;
			} finally {
				b.unlock();
			}
		} finally {
			unlock();
		}
	}
	
	
}


