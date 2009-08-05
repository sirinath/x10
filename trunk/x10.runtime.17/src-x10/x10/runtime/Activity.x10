/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.runtime;

import x10.util.Stack;

/**
 * @author tardieu
 */
class Activity(finishState:FinishState, safe:Boolean) {
	// the finish state governing the execution of this activity

	/**
	 * The user-specified code for this activity.
	 */
	private val body:()=>Void;
	
    /**
     * The mapping from registered clocks to phases for this activity.
     * Lazily created.
     */
    var clockPhases:ClockPhases;
    
    /**
     * The finish states for the finish statements currently executed by this activity.  
     * Lazily created.
     */
    var finishStack:Stack[FinishState];

	/**
	 * Create activity.
	 */
    def this(body:()=>Void, finishState:FinishState, safe:Boolean) {
        property(finishState, safe);
        finishState.incr();
        this.body = body;
    }

    def this(body:()=>Void, place:Place, key:Int, safe:Boolean) {
		this(body, Runtime.findFinish(place, key), safe);
	}

    /**
	 * Create clocked activity.
	 */
	def this(body:()=>Void, finishState:FinishState, clocks:ValRail[Clock], phases:ValRail[Int]) {
		this(body, finishState, false);
	    clockPhases = new ClockPhases();
	    clockPhases.register(clocks, phases);
	}

    def this(body:()=>Void, place:Place, key:Int, clocks:ValRail[Clock], phases:ValRail[Int]) {
        this(body, place, key, false);
        clockPhases = new ClockPhases();
        clockPhases.register(clocks, phases);
    }

	/**
	 * Run activity.
	 */
	def run():Void {
        try {
            body();
        } catch (t:Throwable) {
            Runtime.pushException(t);
        }
        if (null != clockPhases) clockPhases.drop();
		finishState.notifySubActivityTermination();
	}
	
    // [DC] The correct thing to do here is do toString() on the closure
	// public def toString():String = name; 
}

// vim:shiftwidth=4:tabstop=4:expandtab
