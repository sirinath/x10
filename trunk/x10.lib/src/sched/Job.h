/*
============================================================================
 Name        : Job.h
 Author      : Rajkishore Barik
 Version     :
 Copyright   : IBM Corporation 2007
 Description : Exe source file
============================================================================
*/
#ifndef x10lib_Job_h
#define x10lib_Job_h

#include "Frame.h"
#include "Closure.h"
#include "Sys.h"
#include <assert.h>

namespace x10lib_cws {

class Outlet;
//class Closure;
class Worker;
class Pool;
class JobFrame;
//class Frame;
class ResultOutlet;
class GFrame;
class Job;

enum { LABEL_0=0,LABEL_1=1, LABEL_2=2, LABEL_3=3};
class JobFrame : public Frame {
public :
	volatile int PC;
	int x;

	JobFrame(): PC(LABEL_0), x(0) {}
	virtual ~JobFrame() {}
	virtual Closure *makeClosure();
	virtual void setOutletOn(Closure *c);
	virtual JobFrame *copy();
 protected:
	JobFrame(const JobFrame& jf) 
	  : Frame(jf), PC(jf.PC), x(jf.x) {}
};

class Outlet {
 public:
  Outlet() { incCons(); }

  virtual void run() { assert(0); }
  virtual ~Outlet() { incDestruct(); }

 public:
#if defined(MEM_DEBUG) && (MEM_DEBUG!=0)
  static volatile int nCons, nDestruct;
#endif
  inline void incCons() {
# if defined(MEM_DEBUG) && (MEM_DEBUG!=0)
    atomic_add(&nCons, 1);
# endif
  }
  inline void incDestruct() {
# if defined(MEM_DEBUG) && (MEM_DEBUG!=0)
    atomic_add(&nDestruct, 1);
# endif
  }
};

class ResultOutlet : public Outlet {
private:
public:
	Closure *closure;
	JobFrame *jframe;
	virtual void run();
/* 	ResultOutlet(); */
	ResultOutlet(Closure *c=NULL, JobFrame *f=NULL);
	~ResultOutlet() {}
};

class GFrame : public JobFrame {
public:
	volatile int PC;
	virtual void setOutletOn(Closure *c);
	virtual Closure *makeClosure();
	GFrame();
	virtual ~GFrame() {}
	virtual GFrame *copy();
 protected:
	GFrame(const GFrame& gf)
	  : JobFrame(gf), PC(gf.PC) {}
};

class Job : public Closure {
	
protected:
	virtual void compute(Worker *w, Frame *frame) ;
	volatile bool jobDone;
	
public:
	Pool *pool;
	
	virtual void completed();
	virtual void waitForCompletion();
	virtual bool isCancelled() const ;
	virtual bool cancel(bool b) const;
	pthread_cond_t cond_done;

/* 	Job(); */
/* 	Job(Pool *pool); */
/* 	Job(Frame *f, Pool *pool); */
	Job(Pool *pool, Frame *f = new JobFrame());
	virtual ~Job();
	virtual int spawnTask(Worker *ws) ;
 
	int getInt();	
	virtual void jobCompleted();
	bool isJobDone() volatile;
};

class GloballyQuiescentJob : public Job {
public:
/* 	GloballyQuiescentJob(Pool *pool); */
	GloballyQuiescentJob(Pool *pool, Frame *f = new GFrame());
	virtual bool requiresGlobalQuiescence();
	virtual void compute(Worker *w, Frame *frame) ;
	virtual ~GloballyQuiescentJob(){}
};

class GloballyQuiescentVoidJob : public GloballyQuiescentJob {
 public:
  GloballyQuiescentVoidJob(Pool *pool, Frame *f)
    : GloballyQuiescentJob(pool, f) {}
  
 protected:
  void compute(Worker *w, Frame *frame);
  
 public:
  int spawnTask(Worker *ws) {
    assert(0);
    abort();
    return 0;
  }
  virtual ~GloballyQuiescentVoidJob() {}
};


}
#endif
