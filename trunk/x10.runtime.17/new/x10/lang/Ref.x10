package x10.lang;

import x10.compiler.Native;
import x10.compiler.NativeRep;

/**
 * The base class for all reference classes.
 */
@NativeRep("java", "x10.core.Ref")
public class Ref(
    @Native("java", "x10.runtime.Runtime.location(#0)")
    location: Place
    ) {
    
    public native def this();
    
    @Native("java", "(#0).equals(#1)")
    public native def equals(Object): boolean;
    
    @Native("java", "(#0).hashCode()")
    public native def hashCode(): int;
    
    @Native("java", "(#0).toString()")
    public native def toString(): String;
    
    @Native("java", "(#0).getClass().toString()")
    public native def className():String;
    
   
}
