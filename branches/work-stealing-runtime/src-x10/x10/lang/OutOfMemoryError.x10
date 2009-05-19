/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.lang;

import x10.compiler.Native;
import x10.compiler.NativeRep;

@NativeRep("java", "java.lang.OutOfMemoryError", null, null)
@NativeRep("c++", "x10aux::ref<x10::lang::OutOfMemoryError>", "x10::lang::OutOfMemoryError", null)
public value OutOfMemoryError extends Error {
    public def this() { super(); } 
    public def this(message: String) { super(message); } 
}
