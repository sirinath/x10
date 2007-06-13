/*
============================================================================
 Name        : Job.cpp
 Author      : Rajkishore Barik
 Version     :
 Copyright   : IBM Corporation 2007
 Description : Exe source file
============================================================================
*/



#include "Job.h"

using namespace std;
using namespace x10lib_cws;


ResultOutlet::ResultOutlet():Outlet() { }
ResultOutlet::ResultOutlet(Closure *c, JobFrame *f) {
	closure = c;
	jframe = f;
}
void ResultOutlet::run() {
	jframe->x = closure->resultInt();
}



	
JobFrame::JobFrame():Frame() {}
JobFrame::~JobFrame() {}
Closure *JobFrame::makeClosure() {
	assert(false);
	return NULL;
}
void JobFrame::setOutletOn(Closure *c) {
			c->setOutlet(new ResultOutlet());
}
		




void GFrame::setOutletOn(Closure *c) {}
Closure *GFrame::makeClosure() { return NULL;}
GFrame::GFrame():JobFrame() { }



bool GloballyQuiescentJob::requiresGlobalQuiescence() const { return true;}	
GloballyQuiescentJob::GloballyQuiescentJob(Pool *pool) {
	GloballyQuiescentJob::GloballyQuiescentJob(pool, new GFrame()); 
}
GloballyQuiescentJob::GloballyQuiescentJob(Pool *pool, Frame *f) {
			Job::Job(f,pool);
			parent = NULL;
			joinCount=0;
			status = READY;
}
void GloballyQuiescentJob::compute(Worker *w, Frame *frame) {
			GFrame *f = (GFrame *) frame;
			int PC = f->PC;
			f->PC=LABEL_1;
			MEM_BARRIER(); // TODO Raj
			if (PC==0) {
				// spawning
				int x = spawnTask(w);
				w->abortOnSteal(x);
				f->x=x;
				// Accumulate into result.
				int old = resultInt();
				accumulateResultInt(f->x);
				//if (Worker.reporting)
				//System.out.println( w + " " + this + " adds " + f.x + " to move " + old + " --> " + resultInt());
			}
			setupGQReturn(w);
}





Job::Job() {}
Job::Job(Pool *pool) {
	Job::Job(new JobFrame(), pool);
}
Job::Job(Frame *f, Pool *pool) : Closure(f) {
		this->pool=pool;
		parent = NULL;
		joinCount=0;
		status = READY;
		pthread_cond_init (&cond_done, NULL);
}
Job::~Job() {
		pthread_cond_destroy(&cond_done);
}
void Job::compute(Worker *w, Frame *frame) {
		int x;
		JobFrame *f = (JobFrame *) frame;
		switch (f->PC) {
		case LABEL_0: 
			f->PC=LABEL_1;
			MEM_BARRIER(); // TODO RAJ
			// spawning
			x = spawnTask(w);
			w->abortOnSteal(x);
			f->x=x;
		case LABEL_1: 
			f->PC=LABEL_2;
			MEM_BARRIER(); // TODO RAJ
			if (sync(w)) return;
		case LABEL_2: 
			setResultInt(f->x);
			setupReturn(w);
		default: return;
		}
		return;
}
int Job::spawnTask(Worker *ws) { abort(); } // TODO RAJ child must provide an imple for this
void Job::completed() {
	Closure::completed();
	/*	if ( Worker.reporting)
			System.out.println(Thread.currentThread() + " completed. result=" + resultInt());*/
		/*
		synchronized(this) {
			notifyAll();
		}*/
		pthread_cond_broadcast(&cond_done);
		pool->jobCompleted();
}
void Job::waitForCompletion() {
		while (!isDone()) pthread_cond_wait(&cond_done,NULL);
}
    
bool Job::isCancelled() const { return false;}
    
bool Job::cancel(bool b) const { return false;}

