package pppp.lib.stream;

@x10.runtime.impl.java.X10Generated public class ConstantStream<$T>  extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<ConstantStream> $RTT = x10.rtt.NamedType.<ConstantStream> make(
    "pppp.lib.stream.ConstantStream", ConstantStream.class, 1
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.ConstantStream<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + ConstantStream.class + " calling"); } 
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.k = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.ConstantStream $_obj = new pppp.lib.stream.ConstantStream((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        if (k instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.k);
        } else {
        $serializer.write(this.k);
        }
        
    }
    
    // constructor just for allocation
    public ConstantStream(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    pppp.lib.stream.ConstantStream.$initParams(this, $T);
    }
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final ConstantStream $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
public $T k;
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
// creation method for java code (1-phase java constructor)
        public ConstantStream(final x10.rtt.Type $T,
                              final $T k, __0pppp$lib$stream$ConstantStream$$T $dummy){this((java.lang.System[]) null, $T);
                                                                                           pppp$lib$stream$ConstantStream$$init$S(k, (pppp.lib.stream.ConstantStream.__0pppp$lib$stream$ConstantStream$$T) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.ConstantStream<$T> pppp$lib$stream$ConstantStream$$init$S(final $T k, __0pppp$lib$stream$ConstantStream$$T $dummy) { {
                                                                                                                                                                 
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
;
                                                                                                                                                                 
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"

                                                                                                                                                                 
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
this.__fieldInitializers_pppp_lib_stream_ConstantStream();
                                                                                                                                                                 
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
((pppp.lib.stream.ConstantStream<$T>)this).k = (($T)(k));
                                                                                                                                                             }
                                                                                                                                                             return this;
                                                                                                                                                             }
        
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
public $T
                                                                                                                                                          $apply$G(
                                                                                                                                                          ){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
final $T t45738 =
              (($T)(k));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
return t45738;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
public java.lang.String
                                                                                                                                                           toString(
                                                                                                                                                           ){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
final $T t45739 =
              (($T)(k));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
final java.lang.String t45740 =
              (("<ConstantStream ") + (t45739));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
final java.lang.String t45741 =
              ((t45740) + (">"));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
return t45741;
        }
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
final public pppp.lib.stream.ConstantStream
                                                                                                                                                          pppp$lib$stream$ConstantStream$$this$pppp$lib$stream$ConstantStream(
                                                                                                                                                          ){
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
return pppp.lib.stream.ConstantStream.this;
        }
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/ConstantStream.x10"
final public void
                                                                                                                                                          __fieldInitializers_pppp_lib_stream_ConstantStream(
                                                                                                                                                          ){
            
        }
    // synthetic type for parameter mangling
    public static final class __0pppp$lib$stream$ConstantStream$$T {}
    
}

