/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

package x10.lang;
import x10.compiler.ClockedVar;
import x10.compiler.ClockableVar;
import x10.util.HashSet;
import x10.lang.Any;
import x10.lang._;

/**
 * @author tardieu
 */
public class Clock(name:String) {
    public static def make(): Clock = make("");

    public const FIRST_PHASE = 1;

    public static def make(name:String):Clock {
        val clock = new Clock(name);
        Runtime.clockPhases().put(clock, FIRST_PHASE);
        return clock;
    }
    
    public def this():Clock {
        property("");
        Runtime.clockPhases().put(this, FIRST_PHASE);
    }

    private var count:Int = 1;
    private var alive:Int = 1;
    private var phase:Int = FIRST_PHASE;
    private val clockedVars: HashSet[ClockableVar] = new HashSet[ClockableVar]();
    
    private def this(name:String) {
        property(name);
    }

    private global def get() = Runtime.clockPhases().get(this).value;

    private global def put(ph:Int) = Runtime.clockPhases().put(this, ph);

    private global def remove() = Runtime.clockPhases().remove(this).value;

    private atomic def resumeLocal() {
        if (--alive == 0) {
    	    for (cv: ClockableVar in clockedVars) {
    		(cv as ClockableVar!).move();
    	    }
            alive = count;
            ++phase;
        }
    }

    global def register() {
        if (dropped()) throw new ClockUseException();
        val ph = get();
        at (this) atomic {
            ++count;
            if (-ph != phase) ++alive;
        }
        return ph;
    }
    
    public def addClockedVar[T](cv: ClockedVar[T]) {
        val clockedVariables = clockedVars as HashSet[ClockableVar]!;
    	clockedVariables.add(cv);
    }
    
     public def addClockedVar(cv: ClockableVar) {
        val clockedVariables = clockedVars as HashSet[ClockableVar]!;
    	clockedVariables.add(cv);
    }
    
    
    global def resumeUnsafe() {
        val ph = get();
        if (ph < 0) return;
        at (this) resumeLocal();
        put(-ph);
    }

    global def nextUnsafe() {
        val ph = get();
        val abs = Math.abs(ph);
        at (this) {
            if (ph > 0) resumeLocal();
            await (abs < phase);
        }
        put(abs + 1);

    }

    global def dropUnsafe() {
        val ph = remove();
        async (this) atomic {
            --count;
            if (-ph != phase) 
            	resumeLocal();
        }
    }

    public global def registered():Boolean = Runtime.clockPhases().containsKey(this);

    public global def dropped():Boolean = !registered();

    public global def phase():Int {
        if (dropped()) throw new ClockUseException();
        return Math.abs(get());
    }

    public global def resume():Void {
        if (dropped()) throw new ClockUseException();
        resumeUnsafe();
    }

    public global def next():Void {
        if (dropped()) throw new ClockUseException();
        nextUnsafe();
    }

    public global def drop():Void {
        if (dropped()) throw new ClockUseException();
        dropUnsafe();
    }
}

// vim:shiftwidth=4:tabstop=4:expandtab
