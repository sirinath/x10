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

/**
 * The base class for all reference classes.
 */
@NativeRep("java", "java.lang.Object", null, null)
@NativeRep("c++", "x10aux::ref<x10::lang::Ref>", "x10::lang::Ref", null)
public class Object (
        @Native("java", "x10.lang.Place.place(x10.core.Ref.home(#0))")
        @Native("c++", "x10::lang::Place_methods::place((#0)->location)")
        home: Place) 
        implements Any 
//        implements Equals
    /* @EQ implements Equals[Ref] */
{

//    @Native("java", "new x10.lang.Box<#2>(#3, #4)")
//    @Native("c++", "new (x10aux::alloc<x10::lang::Box>()) x10::lang::Box<#2>(#3, #4)")
//    @Native("c++", "x10::lang::Box<#1 >::_make(#4)")
//   public static operator [T](x: T): Box[T] /* {T <: Value} */ = new Box[T](x);

    public native def this();

    @Native("java", "#0.equals(#1)")
    @Native("c++", "x10aux::equals(#0,#1)")
    public global safe native def equals(Any): boolean;

    @Native("java", "#0.hashCode()")
    @Native("c++", "x10aux::hash_code(#0)")
    public global safe native def hashCode() : Int;

    @Native("java", "#0.toString()")
    @Native("c++", "x10aux::to_string(#0)")
    public global safe native def toString() : String;

 //   @Native("java", "x10.lang.Place.place(x10.core.Ref.home(#0))")
//   @Native("c++", "x10::lang::Place_methods::place((#0)->location)")
//   public property safe def loc() = location;

    @Native("java", "x10.core.Ref.at(#0, #1.id)")
    @Native("c++", "((#0)->location == (#1)->FMGL(id))")
    public property safe def at(p:Place) = home==p;

    @Native("java", "x10.core.Ref.at(#0, #1)")
    @Native("c++", "((#0)->home == (#1)->home)")
    public property safe def at(r:Object) = home==r.home;
    
    @Native("java", "x10.core.Ref.typeName(#0)")
    @Native("c++", "x10aux::type_name(#0)")
    public global safe native final def typeName():String;
}
