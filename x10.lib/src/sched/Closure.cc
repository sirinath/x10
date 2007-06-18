/*
============================================================================
 Name        : Closure.cpp
 Author      : Rajkishore Barik
 Version     :
 Copyright   : IBM Corporation 2007
 Description : Exe source file
============================================================================
*/


#include "Closure.h"
#include "Worker.h"
#include "Lock.h"
#include "Cache.h"
#include "Job.h"
#include "Frame.h"
#include "Sys.h"
#include "StealAbort.h"
#include <assert.h>

using namespace std;
using namespace x10lib_cws;

int Closure::getstatus() const { return status;}
Frame *Closure::parentFrame() const { return parent->frame; }
Closure *Closure::getparent() const { return parent; }

// Closure::Closure() { }

Closure::Closure(Frame *frame) {
	done = false;
	this->frame = frame;
	lock_var = new PosixLock();

	//public members to be init
	cache = NULL;
	parent = NULL;
	joinCount = 0;
	nextReady = prevReady = NULL;
	ownerReadyQueue = NULL;
	status = -1; //It is to be set to a valid value by someone; Not me!

	//protected members to be init
	lockOwner = NULL;
	outlet = NULL;
	done = false;

	initialize();
}

Closure::~Closure() { 
	delete lock_var; 
	completeInlets.clear(); 
	incompleteInlets.clear();
	// TODO -- verify these
	//delete frame;
}

bool Closure::hasChildren() const {
	return joinCount > 0;
}
void Closure::lock(Worker *agent) { 
	LOCK(lock_var);
	lockOwner = agent;
}
void Closure::unlock() { 
	lockOwner=NULL;
	UNLOCK(lock_var);
}
void Closure::addCompletedInlet(Closure *child) {
		/*if (completeInlets == NULL)
			completeInlets = new list<Closure>();*/
		completeInlets.push_back(child);
}
	
void Closure::removeChild(Closure *child) {
		//if (incompleteInlets != NULL) 
		 incompleteInlets.remove(child);
		// for (Inlet i : incompleteInlets) {
		//	if (i.target == child) return i;
		//}
		//return NULL;
}
	
	/**
	 * This code is executed by the thief on the parent while holding 
	 * the lock on the parent and on the victim.
	 * @param thief  -- The worker performing the steal.
	 * @param victim -- The worker from who work has been stolen.
	 * @return the child closure
	 */
Closure *Closure::promoteChild(Worker *thief, Worker *victim) {
		
		assert(lockOwner == thief);
		assert(status == RUNNING);
		assert(ownerReadyQueue == victim);
		assert(victim->lockOwner == thief);
		assert(nextReady==NULL);
		assert(cache->exceptionOutstanding());

		Frame *childFrame = cache->childFrame();
		Closure *child = childFrame->makeClosure();
		
		Frame *pFrame = cache->topFrame();
		pFrame->setOutletOn(child);
		
		// Leave the parent link in there.
		// It will not be used by globally quiescent computations.
		child->parent = this;
		child->joinCount = 0;
		child->cache = cache;
		child->status = RUNNING;
		child->ownerReadyQueue = NULL;
		cache->incHead();
		
		victim->addBottom(thief, child);
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
void Closure::finishPromote(Worker *thief, Closure *child) {
		
		assert(lockOwner == thief);
		assert(child == NULL || child->lockOwner != thief);
		
		/* Add the child to the parent. */
		if (! child->requiresGlobalQuiescence())
			++joinCount;
		// No need to add child to parent's incomplete inlets
		// unless aborts are being propagated.
		//if (incompleteInlets == NULL)
		//	incompleteInlets = new ArrayList<Closure>();
		//incompleteInlets.add(child);
		
		/* Set the parent's cache to NULL and its status to READY */
		status=READY;
		cache=NULL;
}

	/**
	 * Do the thief part of Dekker's protocol.  Return true upon success,
	 * false otherwise.  The protocol fails when the victim already popped
	 * T so that E=T.
	 * Must be the case that Thread.currentThread()==thief.
	 */
bool Closure::dekker(Worker *thief) {
		assert(lockOwner==thief);
		assert(status==RUNNING);
		return cache->dekker(thief);
}
    
/*
void Closure::decrementExceptionPointer(Worker *ws) {
    	assert(lockOwner == ws);
    	assert(status == RUNNING);
    	cache->decrementExceptionPointer();
}
    
void Closure::incrementExceptionPointer(Worker *ws) {
    	assert(lockOwner == ws);
    	assert(status == RUNNING);
    	
    	cache->incrementExceptionPointer();
}
void Closure::resetExceptionPointer(Worker *ws) {
    	 assert(lockOwner==ws);
    	 
    	 cache->resetExceptionPointer();
}
    
*/
bool Closure::handleException(Worker *ws) {
    	cache->resetExceptionPointer(ws);
    	
    	assert (status == RUNNING || status == RETURNING);
    	if (cache->empty()) {
    		assert(joinCount==0);
    		status = RETURNING;
        	return true;
    	}
    	return false;
    	
}
  
void Closure::signalImmediateException(Worker *ws) {
    	assert(lockOwner == ws);
    	assert(status == RUNNING);
    	cache->signalImmediateException();
}



 /**
     * Return protocol. The closure has completed its computation. Its return value
     * is now propagated to the parent. The closure to be executed next, possibly NULL,
     * is returned.  
     * This closure must not be locked (by this worker??) and must not be in any deque.
     * Required that ws==Thread.currentThread().
     * @return the parent, if this is the last child joining.
     */
Closure *Closure::closureReturn(Worker *w) {
    	
    	// Short circuit for globally quiescent computations.
    	if (requiresGlobalQuiescence() && parent != NULL) {
    		w->currentJob()->accumulateResultInt(resultInt());
    		return NULL;
    	}
    	assert (joinCount==0);
    	assert (ownerReadyQueue==NULL);
    	assert (lockOwner != w);
    	
    	if (! requiresGlobalQuiescence())
    		completed();
    	Closure *parent = this->parent;
    	if (parent == NULL) {
    		// Must be a top level closure.
    		return NULL;
    	}
    	return parent->acceptChild(w, this);
}
     
Closure *Closure::acceptChild(Worker *ws, Closure *child) {
		Closure *res;
    	lock(ws);
    	assert(status != RETURNING);
    	assert(frame != NULL);
    		//removeChild(child);
    	assert (joinCount > 0); // ADDED by RAJ;
    	MEM_BARRIER();
    	--joinCount;
    	MEM_BARRIER();
    	child->lock(ws);
    	assert (lockOwner == ws);
    	addCompletedInlet(child);
    	res = provablyGoodStealMaybe(ws, child);
    	child->unlock();
    	unlock();
    	// TODO RAJ: Please free up child now -- its no longer needed
    	return res;
    	// The child should be garbage at this point.
}
    
    /**
     * Suspend this closure. Called during execution of slow sync
     * when there is at least one outstanding child.
     * ws must be the worker executing suspend.
     * Assume: ws=Thread.currentThread();
     */
void Closure::suspend(Worker *ws) {
    	assert(lockOwner == ws);
    	assert(status == RUNNING);
    	
    	status = SUSPENDED;
    	
    	// throw away the bottommost closure on the worker.
    	// the only references left to this closure are from
    	// its children.
    	Closure *cl = ws->extractBottom(ws);
    	assert(cl==this);
    	
//    	Setting ownedReadyQueue to NULL even though Cilk does not do it.
    	cl->ownerReadyQueue=NULL;
}
    
    /**
     * Return the parent provided that its joinCount is zero 
     * and it is suspended. The parent should now be considered
     * stolen by the worker that just finished returning the
     * value of a child
     * @return parent or NULL
     */
Closure *Closure::provablyGoodStealMaybe(Worker *ws, Closure *child) {
    	assert(child->lockOwner==ws);
    	//assert parent != NULL;
    	Closure *result = NULL;
    	
    	if (joinCount==0 && status == SUSPENDED) {
    		result = this;
    		pollInlets(ws);
    		ownerReadyQueue = NULL;
    		status=READY;
    		cache=NULL;
    		/*if (Worker.reporting) {
    			System.out.println(ws + " awakens " + this);
    		}*/
    	} 
    	return result;
    }
	
   
	/**
	 * Run all completed inlets.
	 * TODO: Figure out why pollInlets are being run incrementally
	 * and not just once, after joinCount==0. Perhaps because
	 * this method is supposed to perform abort processing.
	 */
void Closure::pollInlets(Worker *ws) {
		list<Closure *>::iterator i;
		assert(lockOwner==ws);
		
		if (status==RUNNING && ! cache->atTopOfStack()) {
			assert(ws->lockOwner == ws);
		}
		if (!completeInlets.empty())
			/* TODO LIST TRAVERSAL -- RAJ*/
			for (i=completeInlets.begin(); i != completeInlets.end(); ++i) 
				(*i)->executeAsInlet();
			
		completeInlets.clear();
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
   
Closure *Closure::returnValue(Worker *ws) {
    	assert(status==RETURNING);
    	
    	return closureReturn(ws);
}
	
	/**
	 * Execute this closure. Performed after the closure has been
	 * stolen from a victim. Will eventually invoke compute(frame),
	 * after setting things up.
	 * @param w -- the current thread, must be equal to Thread.currentThread()
	 */
Closure *Closure::execute(Worker *w) {
		Closure *res = NULL;
		assert(lockOwner != w);
		
		lock(w);
		
		Frame *f = frame;
		switch (status) {
		case READY:	
			status = RUNNING;
		    // load the cache from the worker's state.
		    cache = w->cache;
		    cache->pushFrame(frame);
		    cache->resetExceptionPointer(w);
			assert(f != NULL);
			pollInlets(w);
			
			unlock();
			
			w->lock(w);
			w->addBottom(w, this);
			w->unlock();
			
			try {
				compute(w, f); 
			}
			catch(StealAbort *saEx) {
				//Nothing much to do. It just unwound the stack as we wanted
				delete saEx; 
			}
			res = NULL;
			break;
		
		case RETURNING:
			unlock();
			res = returnValue(w);
			break;
		default:
			abort(); // TODO RAJ
			//throw new Error(w + "executes " + status + " " + this + ": error!");
		}
		return res;
}

	/**
	 * Return your value to the parent closure. Typically the
	 * closure will be created with information about where
	 * to deposit its result.
	 */
void Closure::executeAsInlet() {
		if (outlet != NULL) {
			outlet->run();
		}
}
//	=============== The methods intended to be called by client code =======
//	=============== that subclasses Closure.========
	
	/**
	 * Slow execution entry point for the scheduler. Invoked by the thief
	 * running in the scheduler after it has stolen the closure from a victim.
	 * @param w -- The thread invoking the compute, i.e. w == Thread.currentThread()
	 * @param frame -- frame within which to execute
	 */
void Closure::compute(Worker *w, Frame *frame) {abort();}
    
	/**
	 * Subclasses should override this as appropriate. 
	 * But they should alwas first call super.initialize();
	 *
	 */
void Closure::initialize() {
		// need to handle abort processing.
}

    /* Public method intended to be invoked from within 
     * slow methods of client code.
     * @return true -- iff the closure must suspend because not 
     *                 all children have returned
     */
bool Closure::sync(Worker *ws) {
		return ws->sync();
}
	
	
	/**
	 * Invoked by client code before a return from slow code. It will
	 * mark the current closure as RETURNING so it wont be stolen. It will
	 * pop the current frame. 
	 * Before invoking this call, client code is responsible for setting the appropriate state
	 * on the closure so that the value to be returned is known.
	 * 
	 */
void Closure::setupReturn(Worker *w) {
		// Do not trust client code to pass this parameter in.
		//Worker *w = (Worker) Thread.currentThread(); // TODO how to handle this RAJ
		done = true;
		w->lock(w);
		
		if (requiresGlobalQuiescence()) {
			w->extractBottom(w);
			// speed the result on its way.
			if (parent != NULL)
			w->currentJob()->accumulateResultInt(resultInt());
			return;
		}
		Closure *t = w->peekBottom(w);
		assert(t==this);
		lock(w);
			
		assert(status==RUNNING);
		status=RETURNING;
		//frame=NULL;
		w->popFrame();
			
		unlock();
			
		w->unlock();
}

void Closure::setupGQReturnNoArg(Worker *w) {
		// Do not trust client code to pass this parameter in.
		//Worker *w = (Worker) Thread.currentThread(); //TODO RAJ
		w->lock(w); 
		w->extractBottom(w);
		w->popFrame();
		w->unlock();
		
}

void Closure::setupGQReturn(Worker *w) {
		// Do not trust client code to pass this parameter in.
		//Worker *w = (Worker) Thread.currentThread(); // TODO RAJ
		// do not set done to true. This will be 
		// done when global quiescence is recognized.
		w->lock(w);
		Closure *t = w->peekBottom(w);
		assert(t==this);
		lock(w);
		assert(status==RUNNING);
		status=RETURNING;
		//frame=NULL;
		w->popFrame();
		unlock();
		w->unlock();
		
}
	
	
void Closure::setOutlet(Outlet *o) { outlet=o;}
/** Replace the frame by a copy. 
	 * 
	 * <p> Called during
	 * a steal to ensure that frames are not shared between the caches
	 * of two different workers. Must be called with the current thread,
	 * w, holding the lock on the victim. This ensures that the 
	 * victim is not running freely, modifying the frame being
	 * copied. Why? Because the Theft Protocol ensures that the
	 * victim cannot return to processing the frame until it has checked
	 * whether this frame has been stolen. If the frame has been stolen,
	 * the victim must grab the lock on itself. So therefore we must
	 * ensure that this method is called by the thief before it releases 
	 * the lock on the victim. 
	 * 
	 * Note that the victim is by definition the owner of this closure.
	 * ownerReadyQueue must not be null.
	 *
	 */
void Closure::copyFrame(Worker *w) {
		assert(ownerReadyQueue->lockOwner==w);
		frame = frame->copy();
}	
bool Closure::isDone() const { return done;}
	
//RuntimeException Closure::getException() { return NULL;} // TODO RAJ
	
	/**
	 * Invoked on completion of the computation associated with this closure. 
	 * May be overridden by client code. Note: This method may be invoked more than once
	 * by the scheduler.
	 *
	 */
void Closure::completed() volatile {
		done = true;
}
	
	// At most one of the following pairs of methods should be overridden by subclassing
	// closures. No pair may be overridden if the closure does not have an associated
	// return value. These methods are not abstract so that Closure can be used directly
	// when there is no reason to subclass it.
void Closure::setResultInt(int x) { abort(); }
void Closure::accumulateResultInt(int x) { abort();}
int Closure::resultInt() { abort(); return 0;}
	
void Closure::setResultFloat(float x) {abort();}
void accumulateResultFloat(float x) { abort();}
float Closure::resultFloat() { abort(); return 0.0;}
	
void Closure::setResultLong(long x) {abort();}
void Closure::accumulateResultLong(long x) { abort();}
long Closure::resultLong() { abort(); return 0l;}
	
void Closure::setResultDouble(double x) {abort();}
void Closure::accumulateResultDouble(double x) { abort();}
double Closure::resultDouble() { abort(); return 0.0;}
	
void Closure::setResultObject(void *x) {abort();}
void *Closure::resultObject() { abort(); return NULL; }
bool Closure::requiresGlobalQuiescence() volatile { abort(); return false; }
