/*
 * Created on Sep 30, 2004
 */
package x10.runtime;

import x10.runtime.Activity.Result;

/**
 * This class encapsulates the return value of a local async
 * call and allows the client to wait for the completion of the
 * async call (force future).
 * 
 * @author Christian Grothoff
 */
final class LocalActivityResult_c extends Result {

    private boolean haveResult_;
    
    private Object result_;    
    
    /**
     * Set the result value returned by this async call.
     * 
     * @param result
     */
    public synchronized void setResult(Object result) {
        assert (! haveResult_); 
		
        this.result_ = result;
        this.haveResult_ = true;
        this.notifyAll(); // wake up 'force' if waiting
    }
    
    /*
     * @see x10.runtime.Activity.Result#force()
     */
    public synchronized Object force() {
        while (! haveResult_) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                throw new Error(ie); // this should never happen...
            }
        }
        return result_;
    }

}
