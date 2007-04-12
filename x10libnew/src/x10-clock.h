#ifndef X10CLOCK_H_
#define X10CLOCK_H_
/** A clock permits phased sequence of operations.
 */

typedef struct {
	// opaque datastructures needed to implement clocks. 
	//X10_clock_t* next;
	double[5] dummy;
} X10_clock_t;


/**
 * Create and return a clock. The thread must be executing an activity.
 * The activity is registered with the clock.
 */
extern X10_clock_t* X10_clock_make();

/**
 * Clocks may be communicated to remote nodes. At the remote node
 * a local proxy clock is created since the original clock cannot
 * be accessed locally. All operations on the original clock are 
 * performed with respect to this proxy clock. parent must be a
 * reference to a remote clock. 
 * 
 * This function is internal to the implementation of clocks.
 */
extern X10_clock_t* X10_clock_make_proxy(X10_gas_ref_t parent);


/**
 * Send a message to the remote proxy to advance it. 
 * 
 * This function is internal to the implementation of clocks.
 */
extern int X10_clock_advance_proxy(X10_gas_ref_t remote);

/** Resume the clock. The thread must be executing an activity
 * that is registered on the clock, otherwise an X10_ILLEGAL_ARG value
 * is returned. 
 * 
 */
extern int X10_resume(X10_clock_t* clock); 

/** Resume all clocks this activity is registered with. The thread must be executing an activity, 
 * otherwise an X10_ILLEGAL_ARG value is returned. 
 * 
 */
extern int X10_resume(void); 

/** Perform a next on the given clock. The thread must be executing an activity
 * that is registered on the clock, otherwise an X10_ILLEGAL_ARG value
 * is returned. 
 * 
 */
extern int X10_next(X10_clock_t* clock); 

/** Perform a next on all clocks this activity is registered with. 
 * The thread must be executing an activity, 
 * otherwise an X10_ILLEGAL_ARG value is returned. 
 * 
 */
extern int X10_next(void); 

/** Try a next on the given clock. The thread must be executing an activity
 * that is registered on the clock, otherwise an X10_ILLEGAL_ARG value
 * is returned. The call returns X10_OK if a next succeeded. It returns
 * X10_NOT_OK if executing a next would have suspended this activity.
 * 
 */
extern int X10_try_next(X10_clock_t* clock); 

/** Try next on all the clocks this activity is registered with. The thread must 
 * be executing an activity, otherwise an X10_ILLEGAL_ARG value
 * is returned. The call returns X10_OK if next succeeded. It returns
 * X10_NOT_OK if executing next() would have suspended this activity.
 * 
 */
extern int X10_try_next(void); 


/** Returns X10_OK if the current activity is registered with the given clock.
 * The thread must be executing an activity
 * that is registered on the clock, otherwise an X10_ILLEGAL_ARG value
 * is returned. 
 * 
 */
extern int X10_registered(X10_clock_t* clock);

/** Returns X10_OK if the current activity is not registered with the given clock.
 * The thread must be executing an activity
 * that is registered on the clock, otherwise an X10_ILLEGAL_ARG value
 * is returned. 
 * 
 */
extern int X10_dropped(X10_clock_t* clock);

/** Drop the registration of the current activity on the given clock.
 * The thread must be executing an activity
 * that is registered on the clock, otherwise an X10_ILLEGAL_ARG value
 * is returned. 
 * 
 */
extern int X10_drop(X10_clock_t* clock);

// collective operations
/** This thread must be executing an activity that is registered on the 
 * given clock, otherwise an X10_ILLEGAL_ARG value is returned. In this phase 
 * of the given clock, all activities registered on the clock must either drop 
 * the clock or execute a X10_next_broadcast with a buffer of the same size.
 * Exactly one of the activities must make this call with sender==1. 
 * The contents of the buffer of this activity are copied into the given
 * buffers for all other activities.
 */
extern int X10_next_broadcast(X10_clock_t* clock, void* buffer, int len, byte sender);

extern int X10_next_reduce(X10_clock_t* clock, void* buffer, int len, byte sender);


#endif /*X10CLOCK_H_*/
