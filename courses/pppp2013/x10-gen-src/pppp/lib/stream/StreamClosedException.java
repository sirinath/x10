package pppp.lib.stream;

@x10.runtime.impl.java.X10Generated public class StreamClosedException extends java.lang.RuntimeException implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<StreamClosedException> $RTT = x10.rtt.NamedType.<StreamClosedException> make(
    "pppp.lib.stream.StreamClosedException", StreamClosedException.class, new x10.rtt.Type[] {x10.rtt.Types.EXCEPTION}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.StreamClosedException $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + StreamClosedException.class + " calling"); } 
        $deserializer.deserializeClassUsingReflection(java.lang.RuntimeException.class, $_obj, 0);
        $_obj.s = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.StreamClosedException $_obj = new pppp.lib.stream.StreamClosedException((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.serializeClassUsingReflection(this, java.lang.RuntimeException.class);
        if (s instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.s);
        } else {
        $serializer.write(this.s);
        }
        
    }
    
    // constructor just for allocation
    public StreamClosedException(final java.lang.System[] $dummy) { 
    }
    
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/StreamClosedException.x10"
public java.lang.Object s;
        
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/StreamClosedException.x10"
final public pppp.lib.stream.StreamClosedException
                                                                                                                                                                 pppp$lib$stream$StreamClosedException$$this$pppp$lib$stream$StreamClosedException(
                                                                                                                                                                 ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/StreamClosedException.x10"
return pppp.lib.stream.StreamClosedException.this;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/StreamClosedException.x10"
public StreamClosedException(final java.lang.Object s) {super();
                                                                                                                                                                                                                            {
                                                                                                                                                                                                                               
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/StreamClosedException.x10"
this.s = s;
                                                                                                                                                                                                                               
                                                                                                                                                                                                                               
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/StreamClosedException.x10"
this.__fieldInitializers_pppp_lib_stream_StreamClosedException();
                                                                                                                                                                                                                           }}
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/StreamClosedException.x10"
final public void
                                                                                                                                                                 __fieldInitializers_pppp_lib_stream_StreamClosedException(
                                                                                                                                                                 ){
            
        }
        
    }
    
    