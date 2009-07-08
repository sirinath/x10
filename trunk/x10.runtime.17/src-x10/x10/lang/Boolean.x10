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

@NativeRep("java", "boolean", "x10.core.BoxedBoolean", "x10.types.Type.BOOLEAN")
@NativeRep("c++", "x10_boolean", "x10_boolean", null)
public final value Boolean {

    @Native("java", "!(#1)")
    @Native("c++",  "!(#1)")
    public native static operator ! (x:Boolean): Boolean;
    
    @Native("java", "((#1) & (#2))")
    @Native("c++",  "((x10_boolean) (((#1) ? 1 : 0) & ((#2) ? 1 : 0)))")
    public native static operator (x:Boolean) & (y:Boolean): Boolean;
    
    @Native("java", "((#1) ^ (#2))")
    @Native("c++",  "((x10_boolean) (((#1) ? 1 : 0) ^ ((#2) ? 1 : 0)))")
    public native static operator (x:Boolean) ^ (y:Boolean): Boolean;
    
    @Native("java", "((#1) | (#2))")
    @Native("c++",  "((x10_boolean) (((#1) ? 1 : 0) | ((#2) ? 1 : 0)))")
    public native static operator (x:Boolean) | (y:Boolean): Boolean;
    
    
    @Native("java", "true")
    @Native("c++", "true")
    public const TRUE = true;

    @Native("java", "false")
    @Native("c++", "false")
    public const FALSE = false;

    @Native("java", "java.lang.Boolean.toString(#0)")
    @Native("c++", "x10aux::to_string(#0)")
    public native def toString(): String;
    
    @Native("java", "java.lang.Boolean.parseBoolean(#1)")
    @Native("c++", "x10aux::boolean_utils::parseBoolean(#1)")
    public native static def parseBoolean(String): Boolean;

    // These operations are built-in.  Declaring them will prevent the
    // short-circuiting behavior.

//    @Native("java", "((#0) && (#1))")
//    @Native("c++",  "((x10_boolean) (((#1) ? 1 : 0) && ((#2) ? 1 : 0)))")
//    public native static operator (x:Boolean) && (y:Boolean): Boolean;
//
//    @Native("java", "((#0) || (#1))")
//    @Native("c++",  "((x10_boolean) (((#1) ? 1 : 0) || ((#2) ? 1 : 0)))")
//    public native static operator (x:Boolean) || (y:Boolean): Boolean;
}
