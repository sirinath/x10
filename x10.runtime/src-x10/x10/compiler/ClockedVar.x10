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
 
package x10.compiler;
import x10.lang.Clock;



public class ClockedVar[T] implements ClockableVar{

  
    var xRead:T;
    var xWrite: T;
    val op:(T,T)=>T;
    val opInit:T;
    var changed:Boolean;
    
   @NativeClass("java", "java.util.concurrent.locks", "ReentrantLock")
   @NativeClass("c++", "x10.lang", "Lock__ReentrantLock")
    static class Lock {
        public native def this();

        public native def lock():Void;

        public native def tryLock():Void;

        public native def unlock():Void;

        public native def getHoldCount():Int;
    };
    
    
    
    val lock = new Lock();

    

    public def this (c: Clock!, oper: (T,T)=>T!, opInitial:T) {
    	c.addClockedVar(this); 
     	op = oper;
     	opInit = opInitial;
     	changed = false;	
     	xWrite = opInitial;
     }
     
    public def this(c:Clock, oper: (T,T)=>T, opInitial:T, x:T)
     {
    	val clk = c as Clock!; 
    	xRead = x;
        clk.addClockedVar(this); 
        op = oper; 
        opInit = opInitial;
        changed = false;
        xWrite = opInitial; 
      }
      
    public def get():T {
    	  return xRead;
   }



    public def set(x:T) {
    	changed = true;
        lock.lock();
        this.xWrite = op(this.xWrite, x);
        lock.unlock();
    } 
    
    public def setR(x:T){this.xRead=x;}
    
    public def getW(){return xWrite;}
    

    public def move(): Void {
        if (changed)
        	this.xRead = this.xWrite; 
     	this.xWrite = opInit;
    	this.changed = false;
    }

    
}