package pppp.lazystream;

@x10.runtime.impl.java.X10Generated public class Cons<$T>  extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Cons> $RTT = x10.rtt.NamedType.<Cons> make(
    "pppp.lazystream.Cons", Cons.class, 1
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Cons<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Cons.class + " calling"); } 
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.h = $deserializer.readRef();
        $_obj.t = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lazystream.Cons $_obj = new pppp.lazystream.Cons((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        if (h instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.h);
        } else {
        $serializer.write(this.h);
        }
        if (t instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.t);
        } else {
        $serializer.write(this.t);
        }
        
    }
    
    // constructor just for allocation
    public Cons(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    pppp.lazystream.Cons.$initParams(this, $T);
    }
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final Cons $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
public $T h;
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
public pppp.lazystream.Stream<$T> t;
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
// creation method for java code (1-phase java constructor)
        public Cons(final x10.rtt.Type $T,
                    final $T h,
                    final pppp.lazystream.Stream<$T> t, __0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                             pppp$lazystream$Cons$$init$S(h,t, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lazystream.Cons<$T> pppp$lazystream$Cons$$init$S(final $T h,
                                                                           final pppp.lazystream.Stream<$T> t, __0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2 $dummy) { {
                                                                                                                                                                                         
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
;
                                                                                                                                                                                         
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"

                                                                                                                                                                                         
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
this.__fieldInitializers_pppp_lazystream_Cons();
                                                                                                                                                                                         
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
((pppp.lazystream.Cons<$T>)this).h = (($T)(h));
                                                                                                                                                                                         
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
((pppp.lazystream.Cons<$T>)this).t = ((pppp.lazystream.Stream)(t));
                                                                                                                                                                                     }
                                                                                                                                                                                     return this;
                                                                                                                                                                                     }
        
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
final public pppp.lazystream.Cons
                                                                                                                                                pppp$lazystream$Cons$$this$pppp$lazystream$Cons(
                                                                                                                                                ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
return pppp.lazystream.Cons.this;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Cons.x10"
final public void
                                                                                                                                                __fieldInitializers_pppp_lazystream_Cons(
                                                                                                                                                ){
            
        }
    // synthetic type for parameter mangling
    public static final class __0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2 {}
    
}

