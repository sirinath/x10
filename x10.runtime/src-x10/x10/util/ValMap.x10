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

package x10.util;

public interface ValMap[K,V] {
	public safe def containsKey(k: K): boolean;
	public safe def get(k: K): Box[V];
    public safe def getOrElse(k: K, orelse: V): V;
    public safe def getOrThrow(k: K): V throws NoSuchElementException;
	public safe def keySet(): Set[K];
	public safe def entries(): Set[Entry[K,V]];

	public static interface Entry[Key,Val] {
	    public safe def getKey(): Key;
	    public safe def getValue(): Val;
	}
}
