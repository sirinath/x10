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

@NativeRep("java", "java.lang.Exception", null, null)
@NativeRep("c++", "x10aux::ref<x10::lang::Exception>", "x10aux::ref<x10::lang::Exception>", null)
public value Exception extends Throwable {
    public def this() { super(); }
    public def this(message: String) { super(message); } 
    public def this(message: String, cause: Throwable) { super(message, cause); } 
    public def this(cause: Throwable) { super(cause); } 
}
