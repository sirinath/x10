package pppp.lib.stream;


@x10.runtime.impl.java.X10Generated public class FByPush<$T>  extends x10.core.Ref implements pppp.lib.stream.Stream, x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<FByPush> $RTT = x10.rtt.NamedType.<FByPush> make(
    "pppp.lib.stream.FByPush", FByPush.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(pppp.lib.stream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0))}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.FByPush<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + FByPush.class + " calling"); } 
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.s = $deserializer.readRef();
        $_obj.name = $deserializer.readRef();
        $_obj.a = $deserializer.readRef();
        $_obj.b = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.FByPush $_obj = new pppp.lib.stream.FByPush((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        if (s instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.s);
        } else {
        $serializer.write(this.s);
        }
        $serializer.write(this.name);
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
    public FByPush(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    pppp.lib.stream.FByPush.$initParams(this, $T);
    }
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final FByPush $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
public x10.core.Rail<$T> a;
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
public x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b;
        
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
// creation method for java code (1-phase java constructor)
        public FByPush(final x10.rtt.Type $T,
                       final $T a, __0pppp$lib$stream$FByPush$$T $dummy){this((java.lang.System[]) null, $T);
                                                                             pppp$lib$stream$FByPush$$init$S(a, (pppp.lib.stream.FByPush.__0pppp$lib$stream$FByPush$$T) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FByPush<$T> pppp$lib$stream$FByPush$$init$S(final $T a, __0pppp$lib$stream$FByPush$$T $dummy) { {
                                                                                                                                            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.Rail t45393 =
                                                                                                                                              ((x10.core.Rail)(new x10.core.Rail<$T>($T, ((long)(1L)),
                                                                                                                                                                                     (($T)(a)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                                                                            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
/*this.*/pppp$lib$stream$FByPush$$init$S(((x10.core.Rail)(t45393)), (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2) null);
                                                                                                                                        }
                                                                                                                                        return this;
                                                                                                                                        }
        
        
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
// creation method for java code (1-phase java constructor)
        public FByPush(final x10.rtt.Type $T,
                       final x10.core.Rail<$T> a, __0$1pppp$lib$stream$FByPush$$T$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                pppp$lib$stream$FByPush$$init$S(a, (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FByPush<$T> pppp$lib$stream$FByPush$$init$S(final x10.core.Rail<$T> a, __0$1pppp$lib$stream$FByPush$$T$2 $dummy) { {
                                                                                                                                                               
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.Stream t45394 =
                                                                                                                                                                 ((pppp.lib.stream.Stream<$T>)
                                                                                                                                                                   (null));
                                                                                                                                                               
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
/*this.*/pppp$lib$stream$FByPush$$init$S(((x10.core.Rail)(a)),
                                                                                                                                                                                                                                                                                                                                                  t45394, (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$FByPush$$T$2) null);
                                                                                                                                                           }
                                                                                                                                                           return this;
                                                                                                                                                           }
        
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
// creation method for java code (1-phase java constructor)
        public FByPush(final x10.rtt.Type $T,
                       final x10.lang.IntRange x){this((java.lang.System[]) null, $T);
                                                      pppp$lib$stream$FByPush$$init$S(x);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FByPush<$T> pppp$lib$stream$FByPush$$init$S(final x10.lang.IntRange x) { {
                                                                                                                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final int t45395 =
                                                                                                                       x.
                                                                                                                         max;
                                                                                                                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final int t45396 =
                                                                                                                       x.
                                                                                                                         min;
                                                                                                                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final int t45397 =
                                                                                                                       ((t45395) - (((int)(t45396))));
                                                                                                                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final long t45398 =
                                                                                                                       ((long)(((int)(t45397))));
                                                                                                                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final long t45402 =
                                                                                                                       ((t45398) + (((long)(1L))));
                                                                                                                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.fun.Fun_0_1 t45403 =
                                                                                                                       ((x10.core.fun.Fun_0_1)(new pppp.lib.stream.FByPush.$Closure$33<$T>($T, x)));
                                                                                                                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.Rail t45404 =
                                                                                                                       ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, t45402,
                                                                                                                                                                        ((x10.core.fun.Fun_0_1)(t45403)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
/*this.*/pppp$lib$stream$FByPush$$init$S(((x10.core.Rail)(t45404)), (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2) null);
                                                                                                                 }
                                                                                                                 return this;
                                                                                                                 }
        
        
        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
// creation method for java code (1-phase java constructor)
        public FByPush(final x10.rtt.Type $T,
                       final x10.core.Rail<$T> a,
                       final pppp.lib.stream.Stream<$T> b, __0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$FByPush$$T$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                                          pppp$lib$stream$FByPush$$init$S(a,b, (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$FByPush$$T$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FByPush<$T> pppp$lib$stream$FByPush$$init$S(final x10.core.Rail<$T> a,
                                                                                 final pppp.lib.stream.Stream<$T> b, __0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$FByPush$$T$2 $dummy) { {
                                                                                                                                                                                                         
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.fun.Fun_0_0 t45405 =
                                                                                                                                                                                                           ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.FByPush.$Closure$34<$T>($T, b, (pppp.lib.stream.FByPush.$Closure$34.__0$1pppp$lib$stream$FByPush$$Closure$34$$T$2) null)));
                                                                                                                                                                                                         
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
/*this.*/pppp$lib$stream$FByPush$$init$S(((x10.core.Rail)(a)),
                                                                                                                                                                                                                                                                                                                                                                                            ((x10.core.fun.Fun_0_0)(t45405)), (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2) null);
                                                                                                                                                                                                     }
                                                                                                                                                                                                     return this;
                                                                                                                                                                                                     }
        
        
        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
// creation method for java code (1-phase java constructor)
        public FByPush(final x10.rtt.Type $T,
                       final $T x,
                       final x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b,
                       final java.lang.String s, __0pppp$lib$stream$FByPush$$T__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                                                      pppp$lib$stream$FByPush$$init$S(x,b,s, (pppp.lib.stream.FByPush.__0pppp$lib$stream$FByPush$$T__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FByPush<$T> pppp$lib$stream$FByPush$$init$S(final $T x,
                                                                                 final x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b,
                                                                                 final java.lang.String s, __0pppp$lib$stream$FByPush$$T__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2 $dummy) { {
                                                                                                                                                                                                                     
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.Rail t45406 =
                                                                                                                                                                                                                       ((x10.core.Rail)(new x10.core.Rail<$T>($T, ((long)(1L)),
                                                                                                                                                                                                                                                              (($T)(x)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                                                                                                                                                     
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
/*this.*/pppp$lib$stream$FByPush$$init$S(((x10.core.Rail)(t45406)),
                                                                                                                                                                                                                                                                                                                                                                                                        ((x10.core.fun.Fun_0_0)(b)),
                                                                                                                                                                                                                                                                                                                                                                                                        ((java.lang.String)(s)), (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2) null);
                                                                                                                                                                                                                 }
                                                                                                                                                                                                                 return this;
                                                                                                                                                                                                                 }
        
        
        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
public pppp.lib.stream.BoundedStreamImp<$T> s;
        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
public java.lang.String name;
        
        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
// creation method for java code (1-phase java constructor)
        public FByPush(final x10.rtt.Type $T,
                       final x10.core.Rail<$T> a,
                       final x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b, __0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                                                                                          pppp$lib$stream$FByPush$$init$S(a,b, (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FByPush<$T> pppp$lib$stream$FByPush$$init$S(final x10.core.Rail<$T> a,
                                                                                 final x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b, __0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2 $dummy) { {
                                                                                                                                                                                                                                                         
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
/*this.*/pppp$lib$stream$FByPush$$init$S(((x10.core.Rail)(a)),
                                                                                                                                                                                                                                                                                                                                                                                                                                            ((x10.core.fun.Fun_0_0)(b)),
                                                                                                                                                                                                                                                                                                                                                                                                                                            ((java.lang.String)(null)), (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2) null);
                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                     return this;
                                                                                                                                                                                                                                                     }
        
        
        
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
// creation method for java code (1-phase java constructor)
        public FByPush(final x10.rtt.Type $T,
                       final x10.core.Rail<$T> a,
                       final x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b,
                       final java.lang.String s, __0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                                                          pppp$lib$stream$FByPush$$init$S(a,b,s, (pppp.lib.stream.FByPush.__0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FByPush<$T> pppp$lib$stream$FByPush$$init$S(final x10.core.Rail<$T> a,
                                                                                 final x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b,
                                                                                 final java.lang.String s, __0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2 $dummy) { {
                                                                                                                                                                                                                         
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
;
                                                                                                                                                                                                                         
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
this.a = a;
                                                                                                                                                                                                                         this.b = b;
                                                                                                                                                                                                                         
                                                                                                                                                                                                                         
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
this.__fieldInitializers_pppp_lib_stream_FByPush();
                                                                                                                                                                                                                         
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
((pppp.lib.stream.FByPush<$T>)this).name = ((java.lang.String)(s));
                                                                                                                                                                                                                     }
                                                                                                                                                                                                                     return this;
                                                                                                                                                                                                                     }
        
        
        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
public void
                                                                                                                                                    run(
                                                                                                                                                    ){
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new pppp.lib.stream.FByPush.$Closure$35<$T>($T, ((pppp.lib.stream.FByPush<$T>)(this)),
                                                                                                                                                                                                                                                        a,
                                                                                                                                                                                                                                                        s,
                                                                                                                                                                                                                                                        b, (pppp.lib.stream.FByPush.$Closure$35.__0$1pppp$lib$stream$FByPush$$Closure$35$$T$2__1$1pppp$lib$stream$FByPush$$Closure$35$$T$2__2$1pppp$lib$stream$FByPush$$Closure$35$$T$2__3$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$Closure$35$$T$2$2) null))));
        }
        
        
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
public $T
                                                                                                                                                    $apply$G(
                                                                                                                                                    ){
            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.BoundedStreamImp t45427 =
              ((pppp.lib.stream.BoundedStreamImp)(s));
            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final $T item =
              (($T)(((pppp.util.BBuffer<$T>)t45427).$apply$G()));
            
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45428 =
              ((this) + (" returns "));
            
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45429 =
              ((t45428) + (item));
            
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
pppp.util.Logger.debug(((java.lang.String)(t45429)));
            
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
return item;
        }
        
        
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final public java.lang.String
                                                                                                                                                    toString(
                                                                                                                                                    ){
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.Rail t45430 =
              ((x10.core.Rail)(a));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45438 =
              (("<") + (t45430));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.fun.Fun_0_0 t45431 =
              ((x10.core.fun.Fun_0_0)(b));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final boolean t45436 =
              ((t45431) == (null));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
java.lang.String t45437 =
               null;
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
if (t45436) {
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
t45437 = "";
            } else {
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45432 =
                  ((java.lang.String)(name));
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final boolean t45433 =
                  ((t45432) == (null));
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
java.lang.Object t45434 =
                   null;
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
if (t45433) {
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
t45434 = ((java.lang.Object)(b));
                } else {
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
t45434 = ((java.lang.Object)(name));
                }
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.Object t45435 =
                  t45434;
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
t45437 = ((" -> ") + (t45435));
            }
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45439 =
              t45437;
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45440 =
              ((t45438) + (t45439));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45441 =
              ((t45440) + (">"));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
return t45441;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final public pppp.lib.stream.FByPush
                                                                                                                                                    pppp$lib$stream$FByPush$$this$pppp$lib$stream$FByPush(
                                                                                                                                                    ){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
return pppp.lib.stream.FByPush.this;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final public void
                                                                                                                                                    __fieldInitializers_pppp_lib_stream_FByPush(
                                                                                                                                                    ){
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.BoundedStreamImp t45442 =
              ((pppp.lib.stream.BoundedStreamImp)(new pppp.lib.stream.BoundedStreamImp<$T>((java.lang.System[]) null, $T).pppp$lib$stream$BoundedStreamImp$$init$S()));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
((pppp.lib.stream.FByPush<$T>)this).s = ((pppp.lib.stream.BoundedStreamImp)(t45442));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
((pppp.lib.stream.FByPush<$T>)this).name = null;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$33<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$33> $RTT = x10.rtt.StaticFunType.<$Closure$33> make(
            $Closure$33.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.INT)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.FByPush.$Closure$33<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$33.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.FByPush.$Closure$33 $_obj = new pppp.lib.stream.FByPush.$Closure$33((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$33(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lib.stream.FByPush.$Closure$33.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return x10.core.Int.$box($apply$O(x10.core.Long.$unbox(a1)));
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply$O(x10.core.Long.$unbox(a1));
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$33 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public int
                  $apply$O(
                  final long i){
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final int t45399 =
                      this.
                        x.
                        min;
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final int t45400 =
                      ((int)(long)(((long)(i))));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final int t45401 =
                      ((t45399) + (((int)(t45400))));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
return t45401;
                }
                
                public x10.lang.IntRange x;
                
                public $Closure$33(final x10.rtt.Type $T,
                                   final x10.lang.IntRange x) {pppp.lib.stream.FByPush.$Closure$33.$initParams(this, $T);
                                                                    {
                                                                       ((pppp.lib.stream.FByPush.$Closure$33<$T>)this).x = ((x10.lang.IntRange)(x));
                                                                   }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$34<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$34> $RTT = x10.rtt.StaticFunType.<$Closure$34> make(
            $Closure$34.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lib.stream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.FByPush.$Closure$34<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$34.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.b = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.FByPush.$Closure$34 $_obj = new pppp.lib.stream.FByPush.$Closure$34((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (b instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.b);
                } else {
                $serializer.write(this.b);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$34(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lib.stream.FByPush.$Closure$34.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lib.stream.Stream
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$34 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lib.stream.Stream
                  $apply(
                  ){
                    
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
return this.
                                                                                                                                                                       b;
                }
                
                public pppp.lib.stream.Stream<$T> b;
                
                public $Closure$34(final x10.rtt.Type $T,
                                   final pppp.lib.stream.Stream<$T> b, __0$1pppp$lib$stream$FByPush$$Closure$34$$T$2 $dummy) {pppp.lib.stream.FByPush.$Closure$34.$initParams(this, $T);
                                                                                                                                   {
                                                                                                                                      ((pppp.lib.stream.FByPush.$Closure$34<$T>)this).b = ((pppp.lib.stream.Stream)(b));
                                                                                                                                  }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lib$stream$FByPush$$Closure$34$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$35<$T>  extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$35> $RTT = x10.rtt.StaticVoidFunType.<$Closure$35> make(
            $Closure$35.class, 1, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.FByPush.$Closure$35<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$35.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.a = $deserializer.readRef();
                $_obj.s = $deserializer.readRef();
                $_obj.b = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.FByPush.$Closure$35 $_obj = new pppp.lib.stream.FByPush.$Closure$35((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                if (a instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.a);
                } else {
                $serializer.write(this.a);
                }
                if (s instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.s);
                } else {
                $serializer.write(this.s);
                }
                if (b instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.b);
                } else {
                $serializer.write(this.b);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$35(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lib.stream.FByPush.$Closure$35.$initParams(this, $T);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$35 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public void
                  $apply(
                  ){
                    
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
try {{
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45407 =
                          ((pppp.lib.stream.FByPush<$T>)this.
                                                          out$$).toString();
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45408 =
                          (("Starting ") + (t45407));
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
pppp.util.Logger.log(((java.lang.String)(t45408)));
                        
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.Rail rail45451 =
                          ((x10.core.Rail)(this.
                                             a));
                        
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final long size45452 =
                          ((x10.core.Rail<$T>)rail45451).
                            size;
                        
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
long idx45448 =
                          0L;
                        
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
for (;
                                                                                                                                                                       true;
                                                                                                                                                                       ) {
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final long t45449 =
                              idx45448;
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final boolean t45450 =
                              ((t45449) < (((long)(size45452))));
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
if (!(t45450)) {
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
break;
                            }
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final long t45444 =
                              idx45448;
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final $T av45445 =
                              (($T)(((x10.core.Rail<$T>)rail45451).$apply$G((long)(t45444))));
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.BoundedStreamImp t45443 =
                              ((pppp.lib.stream.BoundedStreamImp)(this.
                                                                    s));
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
((pppp.util.BBuffer<$T>)t45443).$set__0pppp$util$BBuffer$$T((($T)(av45445)));
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final long t45446 =
                              idx45448;
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final long t45447 =
                              ((t45446) + (((long)(1L))));
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
idx45448 = t45447;
                        }
                        
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.fun.Fun_0_0 t45416 =
                          ((x10.core.fun.Fun_0_0)(this.
                                                    b));
                        
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final boolean t45426 =
                          ((t45416) == (null));
                        
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
if (t45426) {
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.BoundedStreamImp t45417 =
                              ((pppp.lib.stream.BoundedStreamImp)(this.
                                                                    s));
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
((pppp.lib.stream.BoundedStreamImp<$T>)t45417).close();
                            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.StreamClosedException t45418 =
                              ((pppp.lib.stream.StreamClosedException)(new pppp.lib.stream.StreamClosedException(((java.lang.Object)(this.
                                                                                                                                       out$$)))));
                            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
throw t45418;
                        } else {
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
try {{
                                
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final x10.core.fun.Fun_0_0 t45419 =
                                  ((x10.core.fun.Fun_0_0)(this.
                                                            b));
                                
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.Stream source =
                                  ((x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>>)t45419).$apply$G();
                                
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
while (true) {
                                    
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final $T item =
                                      (($T)(((pppp.lib.stream.Stream<$T>)source).$apply$G()));
                                    
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45420 =
                                      ((item) + (" FByPush ==> "));
                                    
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45421 =
                                      ((t45420) + (this.
                                                     out$$));
                                    
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
pppp.util.Logger.log(((java.lang.String)(t45421)));
                                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.BoundedStreamImp t45422 =
                                      ((pppp.lib.stream.BoundedStreamImp)(this.
                                                                            s));
                                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
((pppp.util.BBuffer<$T>)t45422).$set__0pppp$util$BBuffer$$T((($T)(item)));
                                }
                            }}catch (final pppp.lib.stream.StreamClosedException z) {
                                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45423 =
                                  (("Input stream for ") + (this.
                                                              out$$));
                                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final java.lang.String t45424 =
                                  ((t45423) + (" is closed."));
                                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
pppp.util.Logger.debug(((java.lang.String)(t45424)));
                            }finally {{
                                 
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
final pppp.lib.stream.BoundedStreamImp t45425 =
                                   ((pppp.lib.stream.BoundedStreamImp)(this.
                                                                         s));
                                 
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
((pppp.lib.stream.BoundedStreamImp<$T>)t45425).close();
                             }}
                            }
                        }}catch (java.lang.Error __lowerer__var__0__) {
                            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
throw __lowerer__var__0__;
                        }catch (java.lang.Throwable __lowerer__var__1__) {
                            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FByPush.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                        }
                    }
                
                public pppp.lib.stream.FByPush<$T> out$$;
                public x10.core.Rail<$T> a;
                public pppp.lib.stream.BoundedStreamImp<$T> s;
                public x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b;
                
                public $Closure$35(final x10.rtt.Type $T,
                                   final pppp.lib.stream.FByPush<$T> out$$,
                                   final x10.core.Rail<$T> a,
                                   final pppp.lib.stream.BoundedStreamImp<$T> s,
                                   final x10.core.fun.Fun_0_0<pppp.lib.stream.Stream<$T>> b, __0$1pppp$lib$stream$FByPush$$Closure$35$$T$2__1$1pppp$lib$stream$FByPush$$Closure$35$$T$2__2$1pppp$lib$stream$FByPush$$Closure$35$$T$2__3$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$Closure$35$$T$2$2 $dummy) {pppp.lib.stream.FByPush.$Closure$35.$initParams(this, $T);
                                                                                                                                                                                                                                                                                                                          {
                                                                                                                                                                                                                                                                                                                             ((pppp.lib.stream.FByPush.$Closure$35<$T>)this).out$$ = out$$;
                                                                                                                                                                                                                                                                                                                             ((pppp.lib.stream.FByPush.$Closure$35<$T>)this).a = ((x10.core.Rail)(a));
                                                                                                                                                                                                                                                                                                                             ((pppp.lib.stream.FByPush.$Closure$35<$T>)this).s = ((pppp.lib.stream.BoundedStreamImp)(s));
                                                                                                                                                                                                                                                                                                                             ((pppp.lib.stream.FByPush.$Closure$35<$T>)this).b = ((x10.core.fun.Fun_0_0)(b));
                                                                                                                                                                                                                                                                                                                         }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lib$stream$FByPush$$Closure$35$$T$2__1$1pppp$lib$stream$FByPush$$Closure$35$$T$2__2$1pppp$lib$stream$FByPush$$Closure$35$$T$2__3$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$Closure$35$$T$2$2 {}
                
                }
                
        // synthetic type for parameter mangling
        public static final class __0pppp$lib$stream$FByPush$$T {}
        // synthetic type for parameter mangling
        public static final class __0$1pppp$lib$stream$FByPush$$T$2 {}
        // synthetic type for parameter mangling
        public static final class __0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$FByPush$$T$2 {}
        // synthetic type for parameter mangling
        public static final class __0pppp$lib$stream$FByPush$$T__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2 {}
        // synthetic type for parameter mangling
        public static final class __0$1pppp$lib$stream$FByPush$$T$2__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2 {}
        
        }
        
        