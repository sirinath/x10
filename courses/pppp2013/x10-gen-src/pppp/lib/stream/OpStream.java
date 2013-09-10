package pppp.lib.stream;

@x10.runtime.impl.java.X10Generated public class OpStream<$S, $T>  extends x10.core.Ref implements pppp.lib.stream.Stream, x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<OpStream> $RTT = x10.rtt.NamedType.<OpStream> make(
    "pppp.lib.stream.OpStream", OpStream.class, 2, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(pppp.lib.stream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(1))}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $S;if (i ==1)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$S, $T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.OpStream<$S, $T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + OpStream.class + " calling"); } 
        $_obj.$S = (x10.rtt.Type) $deserializer.readRef();
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.f = $deserializer.readRef();
        $_obj.a = $deserializer.readRef();
        $_obj.b = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.OpStream $_obj = new pppp.lib.stream.OpStream((java.lang.System[]) null, (x10.rtt.Type) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$S);
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        if (f instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.f);
        } else {
        $serializer.write(this.f);
        }
        if (a instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.a);
        } else {
        $serializer.write(this.a);
        }
        if (b instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.b);
        } else {
        $serializer.write(this.b);
        }
        
    }
    
    // constructor just for allocation
    public OpStream(final java.lang.System[] $dummy, final x10.rtt.Type $S, final x10.rtt.Type $T) { 
    pppp.lib.stream.OpStream.$initParams(this, $S, $T);
    }
    
        private x10.rtt.Type $S;
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final OpStream $this, final x10.rtt.Type $S, final x10.rtt.Type $T) {
        $this.$S = $S;
        $this.$T = $T;
        }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
public x10.core.fun.Fun_0_2<$S,$S,$T> f;
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
public pppp.lib.stream.Stream<$S> a;
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
public pppp.lib.stream.Stream<$S> b;
        
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
public $T
                                                                                                                                                    $apply$G(
                                                                                                                                                    ){
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final x10.core.fun.Fun_0_2 t51711 =
              ((x10.core.fun.Fun_0_2)(f));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final pppp.lib.stream.Stream t51709 =
              ((pppp.lib.stream.Stream)(a));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final $S t51712 =
              (($S)(((pppp.lib.stream.Stream<$S>)t51709).$apply$G()));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final pppp.lib.stream.Stream t51710 =
              ((pppp.lib.stream.Stream)(b));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final $S t51713 =
              (($S)(((pppp.lib.stream.Stream<$S>)t51710).$apply$G()));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final $T t51714 =
              (($T)((($T)
                      ((x10.core.fun.Fun_0_2<$S,$S,$T>)t51711).$apply(t51712,$S,
                                                                      t51713,$S))));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
return t51714;
        }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
public java.lang.String
                                                                                                                                                    toString(
                                                                                                                                                    ){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final x10.core.fun.Fun_0_2 t51715 =
              ((x10.core.fun.Fun_0_2)(f));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final java.lang.String t51716 =
              (("<") + (t51715));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final java.lang.String t51717 =
              ((t51716) + ("("));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final pppp.lib.stream.Stream t51718 =
              ((pppp.lib.stream.Stream)(a));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final java.lang.String t51719 =
              ((t51717) + (t51718));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final java.lang.String t51720 =
              ((t51719) + (","));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final pppp.lib.stream.Stream t51721 =
              ((pppp.lib.stream.Stream)(b));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final java.lang.String t51722 =
              ((t51720) + (t51721));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final java.lang.String t51723 =
              ((t51722) + (")>"));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
return t51723;
        }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final public pppp.lib.stream.OpStream
                                                                                                                                                    pppp$lib$stream$OpStream$$this$pppp$lib$stream$OpStream(
                                                                                                                                                    ){
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
return pppp.lib.stream.OpStream.this;
        }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
// creation method for java code (1-phase java constructor)
        public OpStream(final x10.rtt.Type $S,
                        final x10.rtt.Type $T,
                        final x10.core.fun.Fun_0_2<$S,$S,$T> f,
                        final pppp.lib.stream.Stream<$S> a,
                        final pppp.lib.stream.Stream<$S> b, __0$1pppp$lib$stream$OpStream$$S$3pppp$lib$stream$OpStream$$S$3pppp$lib$stream$OpStream$$T$2__1$1pppp$lib$stream$OpStream$$S$2__2$1pppp$lib$stream$OpStream$$S$2 $dummy){this((java.lang.System[]) null, $S, $T);
                                                                                                                                                                                                                                         pppp$lib$stream$OpStream$$init$S(f,a,b, (pppp.lib.stream.OpStream.__0$1pppp$lib$stream$OpStream$$S$3pppp$lib$stream$OpStream$$S$3pppp$lib$stream$OpStream$$T$2__1$1pppp$lib$stream$OpStream$$S$2__2$1pppp$lib$stream$OpStream$$S$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.OpStream<$S, $T> pppp$lib$stream$OpStream$$init$S(final x10.core.fun.Fun_0_2<$S,$S,$T> f,
                                                                                       final pppp.lib.stream.Stream<$S> a,
                                                                                       final pppp.lib.stream.Stream<$S> b, __0$1pppp$lib$stream$OpStream$$S$3pppp$lib$stream$OpStream$$S$3pppp$lib$stream$OpStream$$T$2__1$1pppp$lib$stream$OpStream$$S$2__2$1pppp$lib$stream$OpStream$$S$2 $dummy) { {
                                                                                                                                                                                                                                                                                                             
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
;
                                                                                                                                                                                                                                                                                                             
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
this.f = f;
                                                                                                                                                                                                                                                                                                             this.a = a;
                                                                                                                                                                                                                                                                                                             this.b = b;
                                                                                                                                                                                                                                                                                                             
                                                                                                                                                                                                                                                                                                             
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
this.__fieldInitializers_pppp_lib_stream_OpStream();
                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                         return this;
                                                                                                                                                                                                                                                                                                         }
        
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/OpStream.x10"
final public void
                                                                                                                                                    __fieldInitializers_pppp_lib_stream_OpStream(
                                                                                                                                                    ){
            
        }
    // synthetic type for parameter mangling
    public static final class __0$1pppp$lib$stream$OpStream$$S$3pppp$lib$stream$OpStream$$S$3pppp$lib$stream$OpStream$$T$2__1$1pppp$lib$stream$OpStream$$S$2__2$1pppp$lib$stream$OpStream$$S$2 {}
    
}

