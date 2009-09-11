/*
 *
 * (C) Copyright IBM Corporation 2008
 *
 *  This file is part of X10 runtime. It is 
 *  governed by the licence under which 
 *  X10 is released.
 *
 */
package x10.util.concurrent.atomic;

import x10.compiler.Native;
import x10.compiler.NativeRep;

/**
   vj: Making this a struct is a hack to get around place types.
 */
@NativeRep("java", "java.util.concurrent.atomic.AtomicInteger", null, null)
@NativeRep("c++", "x10aux::ref<x10::util::concurrent::atomic::AtomicInteger>", "x10::util::concurrent::atomic::AtomicInteger", null)
public struct AtomicInteger {
	
	public native def this():AtomicInteger;
	public native def this(val:int):AtomicInteger;

	@Native("java", "#0.get()")
	@Native("c++", "(#0)->get()")
	public native def get():int;
	
	@Native("java", "#0.set(#1)")
	@Native("c++", "(#0)->set(#1)")
	public native def set(newVal:int):void;
	
	@Native("java", "#0.compareAndSet(#1,#2)")
	@Native("c++", "(#0)->compareAndSet(#1,#2)")
	public native def compareAndSet(expect:int, update:int):boolean;

	@Native("java", "#0.weakCompareAndSet(#1,#2)")
	@Native("c++", "(#0)->weakCompareAndSet(#1,#2)")
	public native def weakCompareAndSet(expect:int, update:int):boolean;
	
	@Native("java", "#0.getAndIncrement()")
	@Native("c++", "(#0)->getAndIncrement()")
	public native def getAndIncrement():int;

	@Native("java", "#0.getAndDecrement()")
	@Native("c++", "(#0)->getAndDecrement()")
	public native def getAndDecrement():int;
	
	@Native("java", "#0.getAndAdd(#1)")
	@Native("c++", "(#0)->getAndAdd(#1)")
	public native def getAndAdd(delta:int):int;
	
	@Native("java", "#0.incrementAndGet()")
	@Native("c++", "(#0)->incrementAndGet()")
	public native def incrementAndGet():int;

	@Native("java", "#0.decrementAndGet()")
	@Native("c++", "(#0)->decrementAndGet()")
	public native def decrementAndGet():int;
	
	@Native("java", "#0.addAndGet(#1)")
	@Native("c++", "(#0)->addAndGet(#1)")
	public native def addAndGet(delta:int):int;
	
	@Native("java", "#0.toString()")
	@Native("c++", "(#0)->toString()")
	public native def toString():String;

	@Native("java", "#0.intValue()")
	@Native("c++", "(#0)->intValue()")
	public native def intValue():int;

	@Native("java", "#0.longValue()")
	@Native("c++", "(#0)->longValue()")
	public native def longValue():long;
	
	@Native("java", "#0.floatValue()")
	@Native("c++", "(#0)->floatValue()")
	public native def floatValue():float;
	
	@Native("java", "#0.doubleValue()")
	@Native("c++", "(#0)->doubleValue()")
	public native def doubleValue():double;
}
