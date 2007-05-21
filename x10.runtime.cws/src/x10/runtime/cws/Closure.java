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


import static x10.runtime.cws.ClosureStatus.*;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.List;
import java.util.ArrayList;


/**
 * A closure corresponds to a slow invocation of a task. This 
 * class is intended to be subclassed by client code. Subclasses
 * may specify extra state (e.g. fields such as result),
 * and will supply implementations of the abstract methods compute
 * and executeAsInlet. The code for a slow invocation of a task
 * should live in compute. The code for executeAsInlet should
 * specify where the result of the closure is to be deposited
 * in the parent closure.
 * 
 * Each worker contains a closure at the top level. When the
 * topmost closure of a victim is stolen, a new child closure is
 * created from the topmost frame in the closure  and left
 * behind on the victim. The old closure is now a parent
 * and is moved to the thief, which starts executing it.
 * 
 * @author vj 04/18/07
 *
 */
public abstract class Closure  {

	protected volatile Cache cache;
	public Frame frame;
	protected Closure parent;
	protected volatile ClosureStatus status;
	protected Lock lock;
	protected Worker lockOwner;
	protected int joinCount;
	
	/**
	 * Inlets are not represented explicitly as separate pieces of code --
	 * they will be once we figure out if we want to support them. (We 
	 * probably do.) Rather we just use a child closure directly as an inlet.
	 */
	protected List<Closure> completeInlets;
	protected List<Closure> incompleteInlets;
	
	protected Worker ownerReadyQueue;
	/**
	 * The ready deque is maintained through a pair of references.
	 */
	protected Closure nextReady, prevReady;
	
	protected ClosureStatus status() { return status;}
	
	protected Closure() {
		super();
		lock = new ReentrantLock();
		initialize();
	}
	
	/**
	 * Returns true if the closure has children
	 * that have not yet joined.
	 * @return
	 */
	protected boolean hasChildren() {
		return joinCount > 0;
	}
	
	
	void lock(Worker agent) { 
		lock.lock();
		lockOwner =agent;
	}
	
	void unlock() { 
		
		lockOwner=null;
		lock.unlock();
	}

	void addCompletedInlet(Closure child) {
		if (completeInlets == null)
			completeInlets = new ArrayList<Closure>();
		completeInlets.add(child);
	}
	

	void makeReady() {
		status=READY;
		cache=null;
	}
	
	void completeAndEnque(Worker ws, Closure child) {
		
		assert (lockOwner == ws);
		addCompletedInlet(child);
		
	}
	
	void removeChild(Closure child) {
		
		if (incompleteInlets != null) 
		 incompleteInlets.remove(child);
		// for (Inlet i : incompleteInlets) {
		//	if (i.target == child) return i;
		//}
		//return null;
	}
	
	/**
	 * This code is executed by the thief on the parent while holding 
	 * the lock on the parent and on the victim.
	 * @param thief  -- The worker performing the steal.
	 * @param victim -- The worker from who work has been stolen.
	 * @return the child closure
	 */
	Closure promoteChild(Worker thief, Worker victim) {
		
		assert lockOwner == thief;
		assert status == RUNNING;
		assert ownerReadyQueue == victim;
		assert victim.lockOwner == thief;
		assert nextReady==null;
		assert cache.head <= cache.exception;

		Frame f = cache.childFrame();
		Closure child = f.makeClosure();
		child.parent = this;
		child.joinCount = 0;
		child.cache = cache;
		child.status = RUNNING;
		child.frame = f;
		child.ownerReadyQueue=null;
		++child.cache.head;
		// TODO: cache.head is never reduced. Hmm. We need to implement
		// wrap around.
		victim.addBottom(thief, child);
		return child;
	}
	/**
	 * This code is executed by the thief on the parent while holding 
	 * the lock on the parent. The lock on the victim has been given up.
	 * Therefore other thiefs may be hitting upon the victim simultaneously.
	 * 
	 * @param thief  -- The worker performing the steal.
	 * @param child -- The child closure being promoted.
	 */
	void finishPromote(Worker thief, Closure child) {
		
		assert lockOwner == thief;
		assert child.lockOwner != thief;
		
		/* Add the child to the parent. */
		++joinCount;
		if (incompleteInlets == null)
			incompleteInlets = new ArrayList<Closure>();
		incompleteInlets.add(child);
		
		/* Set the parent's cache to null and its status to READY */
		makeReady();
		
	}
	/**
	 * Do the thief part of Dekker's protocol.  Return true upon success,
	 * false otherwise.  The protocol fails when the victim already popped
	 * T so that E=T.
	 * Must be the case that Thread.currentThread()==thief.
	 */
	boolean dekker(Worker thief) {
		assert lockOwner==thief;
		
		incrementExceptionPointer(thief);
		Cache c = cache;
		int tail = c.tail;
		int head = c.head;
		if ((head + 1) >= tail) {
			decrementExceptionPointer(thief);
			return false;
		}
		// so there must be at least two elements in the framestack for a theft.
		return true;
	}
    
    private void decrementExceptionPointer(Worker ws) {
    	assert lockOwner == ws;
    	assert status == RUNNING;
    	
    	cache.decrementExceptionPointer();
    }
    
    private void incrementExceptionPointer(Worker ws) {
    	assert lockOwner == ws;
    	assert status == RUNNING;
    	
    	cache.incrementExceptionPointer();
    }
    private void resetExceptionPointer(Worker ws) {
    	 assert lockOwner==ws;
    	 cache.resetExceptionPointer();
     }
    boolean handleException(Worker ws) {
    	resetExceptionPointer(ws);
    	ClosureStatus s = status;
    	assert s == RUNNING || s == RETURNING;
    	//assert (cache.head >= cache.tail);
    	// Other kinds of exceptions are not being handled now.
    	// a steal has happened.
    	status = RETURNING;
    	// The result is going to be moved to the desired location
    	// by application code. 
    	return true;
    	
    }
  
    private void signalImmediateException(Worker ws) {
    	assert lockOwner == ws;
    	assert status == RUNNING;
    	cache.signalImmediateException();
    }
   
    /**
     * Return protocol. The closure has completed its computation. Its return value
     * is now propagated to the parent. The closure to be executed next, possibly null,
     * is returned.  
     * This closure must not be locked (by this worker??) and must not be in any deque.
     * Required that ws==Thread.currentThread().
     * @return the parent, if this is the last child joining.
     */
     private Closure closureReturn(Worker ws) {
    	
    	assert (joinCount==0);
    	assert (status == RETURNING);
    	assert (ownerReadyQueue==null);
    	assert (lockOwner != ws);
    	
    	Closure parent = this.parent;
    	if (parent == null) {
    		// Must be a top level closure.
    		return null;
    	}
    	assert (parent != null);
    	
    	parent.lock(ws);
    	try {
    		assert parent.status != RETURNING;
    		assert parent.frame != null;
    		parent.removeChild(this);
    		lock(ws);
    		parent.completeAndEnque( ws, this);
    		try {
    			if (parent.status == RUNNING) {
    				parent.signalImmediateException(ws);
    			} else
    				parent.pollInlets(ws);
    			// Is a fence() needed?
    			--parent.joinCount;
    			return parent.provablyGoodStealMaybe(ws);
    		} finally {
    			unlock();
    		}
    		
    	} finally {
    		parent.unlock();
    	}
    	// The child should be garbage at this point.
    }
    
    /**
     * Suspend this closure. Called during execution of slow sync
     * when there is at least one outstanding child.
     * ws must be the worker executing suspend.
     * Assume: ws=Thread.currentThread();
     */
   void suspend(Worker ws) {
    	
    	assert lockOwner == ws;
    	assert status == RUNNING;
    	
    	status = SUSPENDED;
    	// throw away the bottommost closure on the worker.
    	// the only references left to this closure are from
    	// its children.
    	Closure cl = ws.extractBottom(ws);
    	assert cl==this;
    	
//    	Setting ownedReadyQueue to null even though Cilk does not do it.
    	cl.ownerReadyQueue=null;
    	
    	
    }
    
    /**
     * Return the parent provided that its joinCount is zero 
     * and it is suspended. The parent should now be considered
     * stolen by the worker that just finished returning the
     * value of a child
     * @return parent or null
     */
    private Closure provablyGoodStealMaybe(Worker ws) {
    	
    	assert lockOwner==ws;
    	assert parent != null;
    	
    	Closure result = null;
    	if (parent.joinCount==0 &&parent.status == SUSPENDED) {
    		result = parent;
    		parent.pollInlets(ws);
    		parent.ownerReadyQueue =null;
    		parent.makeReady();
    	}
    	return result;
    }
	
   
	/**
	 * Run all completed inlets.
	 * TODO: Figure out why pollInlets are being run incrementally
	 * and not just once, after joinCount==0. Perhaps because
	 * this method is supposed to perform abort processing.
	 */
	void pollInlets(Worker ws) {
		
		assert lockOwner==ws;
		
		if (status==RUNNING && ! cache.atTopOfStack()) {
			if (ws.lockOwner !=ws) {
				System.out.println("Cache is " + cache.dump());
			}
			assert ws.lockOwner == ws;
		}
		if (completeInlets != null)
			for (Closure i : completeInlets) {
				i.executeAsInlet();
			}
		completeInlets = null;
	}
	
	 /**
     * Must be called by every slow procedure after it sets the 
     * return value but before it returns.
     * This method ensures that the closure's value is returned
     * to the parent. If this is the last child joining the parent,
     * and the parent is suspended, return the parent (this is a
     * provably good steal).
     * @return
     */
   
    Closure returnValue(Worker ws) {
    	
    	assert status==RETURNING;
    	
    	return closureReturn(ws);
    }
	
	/**
	 * Execute this closure. Performed after the closure has been
	 * stolen from a victim. Will eventually invoke compute(frame),
	 * after setting things up.
	 * @param ws -- the current thread, must be equal to Thread.currentThread()
	 */
	Closure execute(Worker ws) {
		
		assert lockOwner != ws;
		
		lock(ws);
		ClosureStatus s = status;
		Frame f = frame;
		if (s == READY) {
			try {
				status = RUNNING;
		    	// load the cache from the worker's state.
		    	cache = ws.cache;
		    	cache.pushFrame(frame);
		    	cache.resetExceptionPointer();
				assert f != null;
				pollInlets(ws);
			} finally {
				unlock();
			}
			ws.lock(ws);
			try { 
				ws.addBottom(ws, this);
			} finally {
				ws.unlock();
			}
			compute(ws, f);
			return null;
		}
		if (s == RETURNING) {
			unlock();
			return returnValue(ws);
		}
		assert false;
		throw new Error(ws + "executes " + status + " " + this + ": error!");
	}
	
//	=============== The methods intended to be called by client code =======
//	=============== that subclasses Closure.========
	
	/**
	 * Slow execution entry point for the scheduler. Invoked by the thief
	 * running in the scheduler after it has stolen the closure from a victim.
	 * @param w -- The thread invoking the compute, i.e. w == Thread.currentThread()
	 * @param frame -- frame within which to execute
	 */
	abstract protected void compute(Worker w, Frame frame);
    
	/**
	 * Subclasses should override this as appropriate. 
	 * But they should alwas first call super.initialize();
	 *
	 */
	protected void initialize() {
		// need to handle abort processing.
	}

    /* Public method intended to be invoked from within 
     * slow methods of client code.
     * @return true -- iff the closure must suspend because not 
     *                 all children have returned
     */
	final protected boolean sync() {
		Worker ws = ((Worker) Thread.currentThread());
		return ws.sync();
	}
	/**
	 * Invoked by client code before a return from slow code. It will
	 * mark the current closure as RETURNING so it wont be stolen. It will
	 * pop the current frame. 
	 * Before invoking this call, client code is responsible for setting the appropriate state
	 * on the closure so that the value to be returned is known.
	 * 
	 */
	final protected void setupReturn() {
		// Do not trust client code to pass this parameter in.
		Worker ws = (Worker) Thread.currentThread();
		ws.lock(ws);
		try {
			
			Closure t = ws.peekBottom(ws);
			assert t==this;
			lock(ws);
			try {
				assert status==RUNNING;
				status=RETURNING;
				frame=null;
				ws.popFrame();
			} finally {
				unlock();
			}
		} finally {
			ws.unlock();
		}
	}

	
	/**
	 * Return your value to the parent closure. Typically the
	 * closure will be created with information about where
	 * to deposit its result.
	 */
	abstract protected void executeAsInlet();

}
