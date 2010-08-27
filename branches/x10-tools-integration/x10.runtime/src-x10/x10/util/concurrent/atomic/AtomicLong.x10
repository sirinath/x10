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

package x10.util.concurrent.atomic;

import x10.compiler.Native;
import x10.compiler.NativeRep;

@NativeRep("java", "java.util.concurrent.atomic.AtomicLong", null, null)
@NativeRep("c++", "x10aux::ref<x10::util::concurrent::atomic::AtomicLong>", "x10::util::concurrent::atomic::AtomicLong", null)
public final class AtomicLong {
	
	public native def this():AtomicLong;
	public native def this(v:long):AtomicLong;
	
	@Native("java", "#0.get()")
	@Native("c++", "(#0)->get()")
	public safe native def get():long;
	
	@Native("java", "#0.set(#1)")
	@Native("c++", "(#0)->set(#1)")
	public safe native def set(newV:long):void;
	
	@Native("java", "#0.compareAndSet(#1,#2)")
	@Native("c++", "(#0)->compareAndSet(#1,#2)")
	public safe native def compareAndSet(expect:long, update:long):boolean;

	@Native("java", "#0.weakCompareAndSet(#1,#2)")
	@Native("c++", "(#0)->weakCompareAndSet(#1,#2)")
	public safe native def weakCompareAndSet(expect:long, update:long):boolean;
	
	@Native("java", "#0.getAndIncrement()")
	@Native("c++", "(#0)->getAndIncrement()")
	public safe native def getAndIncrement():long;

	@Native("java", "#0.getAndDecrement()")
	@Native("c++", "(#0)->getAndDecrement()")
	public safe native def getAndDecrement():long;
	
	@Native("java", "#0.getAndAdd(#1)")
	@Native("c++", "(#0)->getAndAdd(#1)")
	public safe native def getAndAdd(delta:long):long;
	
	@Native("java", "#0.incrementAndGet()")
	@Native("c++", "(#0)->incrementAndGet()")
	public safe native def incrementAndGet():long;

	@Native("java", "#0.decrementAndGet()")
	@Native("c++", "(#0)->decrementAndGet()")
	public safe native def decrementAndGet():long;
	
	@Native("java", "#0.addAndGet(#1)")
	@Native("c++", "(#0)->addAndGet(#1)")
	public safe native def addAndGet(delta:long):long;
	
	@Native("java", "#0.toString()")
	@Native("c++", "(#0)->toString()")
	public native global safe def toString():String;

	@Native("java", "#0.intValue()")
	@Native("c++", "(#0)->intValue()")
	public safe native def intValue():int;

	@Native("java", "#0.longValue()")
	@Native("c++", "(#0)->longValue()")
	public safe native def longValue():long;
	
	@Native("java", "#0.floatValue()")
	@Native("c++", "(#0)->floatValue()")
	public safe native def floatValue():float;
	
	@Native("java", "#0.doubleValue()")
	@Native("c++", "(#0)->doubleValue()")
	public safe native def doubleValue():double;
}
