package x10.compiler;

@x10.core.X10Generated public class CompilerFlags extends x10.core.Ref implements x10.x10rt.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    private static final short $_serialization_id = x10.x10rt.DeserializationDispatcher.addDispatcher(x10.x10rt.DeserializationDispatcher.ClosureKind.CLOSURE_KIND_NOT_ASYNC, CompilerFlags.class);
    
    public static final x10.rtt.RuntimeType<CompilerFlags> $RTT = x10.rtt.NamedType.<CompilerFlags> make(
    "x10.compiler.CompilerFlags", /* base class */CompilerFlags.class
    , /* parents */ new x10.rtt.Type[] {x10.rtt.Types.OBJECT}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.x10rt.X10JavaSerializable $_deserialize_body(CompilerFlags $_obj , x10.x10rt.X10JavaDeserializer $deserializer) throws java.io.IOException { 
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + CompilerFlags.class + " calling"); } 
        return $_obj;
        
    }
    
    public static x10.x10rt.X10JavaSerializable $_deserializer(x10.x10rt.X10JavaDeserializer $deserializer) throws java.io.IOException { 
    
        CompilerFlags $_obj = new CompilerFlags((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public short $_get_serialization_id() {
    
         return $_serialization_id;
        
    }
    
    public void $_serialize(x10.x10rt.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public CompilerFlags(final java.lang.System[] $dummy) { 
    super($dummy);
    }
    
        
        
//#line 25 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"
public static boolean
                                                                                                          checkBounds$O(
                                                                                                          ){try {return (!false);}catch (java.lang.Throwable $exc$) { throw x10.core.ThrowableUtilities.convertJavaThrowable($exc$); } }
        
        
        
//#line 34 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"
public static boolean
                                                                                                          checkPlace$O(
                                                                                                          ){try {return (!false);}catch (java.lang.Throwable $exc$) { throw x10.core.ThrowableUtilities.convertJavaThrowable($exc$); } }
        
        
        
//#line 42 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"
public static boolean
                                                                                                          useUnsigned$O(
                                                                                                          ){try {return true;}catch (java.lang.Throwable $exc$) { throw x10.core.ThrowableUtilities.convertJavaThrowable($exc$); } }
        
        
        
//#line 49 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"
public static boolean
                                                                                                          FALSE$O(
                                                                                                          ){try {return false;}catch (java.lang.Throwable $exc$) { throw x10.core.ThrowableUtilities.convertJavaThrowable($exc$); } }
        
        
        
//#line 56 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"
public static boolean
                                                                                                          TRUE$O(
                                                                                                          ){try {return true;}catch (java.lang.Throwable $exc$) { throw x10.core.ThrowableUtilities.convertJavaThrowable($exc$); } }
        
        
        
//#line 19 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"
final public x10.compiler.CompilerFlags
                                                                                                          x10$compiler$CompilerFlags$$x10$compiler$CompilerFlags$this(
                                                                                                          ){
            
//#line 19 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"
return x10.compiler.CompilerFlags.this;
        }
        
        
//#line 19 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"
// creation method for java code (1-phase java constructor)
        public CompilerFlags(){this((java.lang.System[]) null);
                                   $init();}
        
        // constructor for non-virtual call
        final public x10.compiler.CompilerFlags x10$compiler$CompilerFlags$$init$S() { {
                                                                                              
//#line 19 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"

                                                                                              
//#line 19 "/home/lshadare/x10-constraints/x10.runtime/src-x10/x10/compiler/CompilerFlags.x10"

                                                                                          }
                                                                                          return this;
                                                                                          }
        
        // constructor
        public x10.compiler.CompilerFlags $init(){return x10$compiler$CompilerFlags$$init$S();}
        
    
}
