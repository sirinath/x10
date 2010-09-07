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

import x10.compiler.NativeRep;
import x10.compiler.Native;
import x10.util.ArrayList;

@NativeRep("java", "x10.core.Iterator<#1>", null, "new x10.rtt.ParameterizedType(x10.core.Iterator._RTT, #2)")
public interface Iterator[+T] {
    @Native("java", "#0.hasNext()")
    public def hasNext(): boolean;
    
    @Native("java", "#0.next$G()")
    public def next():T;
}

class C[T] extends ArrayList[T] {

}
