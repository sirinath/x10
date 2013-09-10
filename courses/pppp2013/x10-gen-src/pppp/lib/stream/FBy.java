package pppp.lib.stream;


@x10.runtime.impl.java.X10Generated public class FBy<$T>  extends x10.core.Ref implements pppp.lib.stream.Stream, x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<FBy> $RTT = x10.rtt.NamedType.<FBy> make(
    "pppp.lib.stream.FBy", FBy.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(pppp.lib.stream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0))}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.FBy<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + FBy.class + " calling"); } 
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.i = $deserializer.readInt();
        $_obj.a = $deserializer.readRef();
        $_obj.b = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.FBy $_obj = new pppp.lib.stream.FBy((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        $serializer.write(this.i);
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
    public FBy(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    pppp.lib.stream.FBy.$initParams(this, $T);
    }
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final FBy $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
public x10.core.Rail<$T> a;
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
public pppp.lib.stream.Stream<$T> b;
        
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
// creation method for java code (1-phase java constructor)
        public FBy(final x10.rtt.Type $T,
                   final $T a, __0pppp$lib$stream$FBy$$T $dummy){this((java.lang.System[]) null, $T);
                                                                     pppp$lib$stream$FBy$$init$S(a, (pppp.lib.stream.FBy.__0pppp$lib$stream$FBy$$T) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FBy<$T> pppp$lib$stream$FBy$$init$S(final $T a, __0pppp$lib$stream$FBy$$T $dummy) { {
                                                                                                                                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final x10.core.Rail t50806 =
                                                                                                                                  ((x10.core.Rail)(new x10.core.Rail<$T>($T, ((long)(1L)),
                                                                                                                                                                         (($T)(a)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                                                                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
/*this.*/pppp$lib$stream$FBy$$init$S(((x10.core.Rail)(t50806)), (pppp.lib.stream.FBy.__0$1pppp$lib$stream$FBy$$T$2) null);
                                                                                                                            }
                                                                                                                            return this;
                                                                                                                            }
        
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
// creation method for java code (1-phase java constructor)
        public FBy(final x10.rtt.Type $T,
                   final x10.core.Rail<$T> a, __0$1pppp$lib$stream$FBy$$T$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                        pppp$lib$stream$FBy$$init$S(a, (pppp.lib.stream.FBy.__0$1pppp$lib$stream$FBy$$T$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FBy<$T> pppp$lib$stream$FBy$$init$S(final x10.core.Rail<$T> a, __0$1pppp$lib$stream$FBy$$T$2 $dummy) { {
                                                                                                                                                   
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final pppp.lib.stream.Stream t50807 =
                                                                                                                                                     ((pppp.lib.stream.Stream<$T>)
                                                                                                                                                       (null));
                                                                                                                                                   
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
/*this.*/pppp$lib$stream$FBy$$init$S(((x10.core.Rail)(a)),
                                                                                                                                                                                                                                                                                                                              t50807, (pppp.lib.stream.FBy.__0$1pppp$lib$stream$FBy$$T$2__1$1pppp$lib$stream$FBy$$T$2) null);
                                                                                                                                               }
                                                                                                                                               return this;
                                                                                                                                               }
        
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
// creation method for java code (1-phase java constructor)
        public FBy(final x10.rtt.Type $T,
                   final x10.lang.IntRange x){this((java.lang.System[]) null, $T);
                                                  pppp$lib$stream$FBy$$init$S(x);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FBy<$T> pppp$lib$stream$FBy$$init$S(final x10.lang.IntRange x) { {
                                                                                                             
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50808 =
                                                                                                               x.
                                                                                                                 max;
                                                                                                             
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50809 =
                                                                                                               x.
                                                                                                                 min;
                                                                                                             
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50810 =
                                                                                                               ((t50808) - (((int)(t50809))));
                                                                                                             
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final long t50811 =
                                                                                                               ((long)(((int)(t50810))));
                                                                                                             
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final long t50815 =
                                                                                                               ((t50811) + (((long)(1L))));
                                                                                                             
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final x10.core.fun.Fun_0_1 t50816 =
                                                                                                               ((x10.core.fun.Fun_0_1)(new pppp.lib.stream.FBy.$Closure$82<$T>($T, x)));
                                                                                                             
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final x10.core.Rail t50817 =
                                                                                                               ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, t50815,
                                                                                                                                                                ((x10.core.fun.Fun_0_1)(t50816)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                             
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
/*this.*/pppp$lib$stream$FBy$$init$S(((x10.core.Rail)(t50817)), (pppp.lib.stream.FBy.__0$1pppp$lib$stream$FBy$$T$2) null);
                                                                                                         }
                                                                                                         return this;
                                                                                                         }
        
        
        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
// creation method for java code (1-phase java constructor)
        public FBy(final x10.rtt.Type $T,
                   final x10.core.Rail<$T> a,
                   final pppp.lib.stream.Stream<$T> b, __0$1pppp$lib$stream$FBy$$T$2__1$1pppp$lib$stream$FBy$$T$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                              pppp$lib$stream$FBy$$init$S(a,b, (pppp.lib.stream.FBy.__0$1pppp$lib$stream$FBy$$T$2__1$1pppp$lib$stream$FBy$$T$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FBy<$T> pppp$lib$stream$FBy$$init$S(final x10.core.Rail<$T> a,
                                                                         final pppp.lib.stream.Stream<$T> b, __0$1pppp$lib$stream$FBy$$T$2__1$1pppp$lib$stream$FBy$$T$2 $dummy) { {
                                                                                                                                                                                         
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
;
                                                                                                                                                                                         
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
this.a = a;
                                                                                                                                                                                         this.b = b;
                                                                                                                                                                                         
                                                                                                                                                                                         
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
this.__fieldInitializers_pppp_lib_stream_FBy();
                                                                                                                                                                                     }
                                                                                                                                                                                     return this;
                                                                                                                                                                                     }
        
        
        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
// creation method for java code (1-phase java constructor)
        public FBy(final x10.rtt.Type $T,
                   final $T x,
                   final pppp.lib.stream.Stream<$T> b, __0pppp$lib$stream$FBy$$T__1$1pppp$lib$stream$FBy$$T$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                          pppp$lib$stream$FBy$$init$S(x,b, (pppp.lib.stream.FBy.__0pppp$lib$stream$FBy$$T__1$1pppp$lib$stream$FBy$$T$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FBy<$T> pppp$lib$stream$FBy$$init$S(final $T x,
                                                                         final pppp.lib.stream.Stream<$T> b, __0pppp$lib$stream$FBy$$T__1$1pppp$lib$stream$FBy$$T$2 $dummy) { {
                                                                                                                                                                                     
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final x10.core.Rail t50818 =
                                                                                                                                                                                       ((x10.core.Rail)(new x10.core.Rail<$T>($T, ((long)(1L)),
                                                                                                                                                                                                                              (($T)(x)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                                                                                                                     
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
/*this.*/pppp$lib$stream$FBy$$init$S(((x10.core.Rail)(t50818)),
                                                                                                                                                                                                                                                                                                                                                                ((pppp.lib.stream.Stream<$T>)(b)), (pppp.lib.stream.FBy.__0$1pppp$lib$stream$FBy$$T$2__1$1pppp$lib$stream$FBy$$T$2) null);
                                                                                                                                                                                 }
                                                                                                                                                                                 return this;
                                                                                                                                                                                 }
        
        
        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
public int i;
        
        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
public $T
                                                                                                                                                $apply$G(
                                                                                                                                                ){
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50819 =
              i;
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final long t50821 =
              ((long)(((int)(t50819))));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final x10.core.Rail t50820 =
              ((x10.core.Rail)(a));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final long t50822 =
              ((x10.core.Rail<$T>)t50820).
                size;
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final boolean t50832 =
              ((t50821) < (((long)(t50822))));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
if (t50832) {
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final x10.core.Rail t50827 =
                  ((x10.core.Rail)(a));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final pppp.lib.stream.FBy x50804 =
                  ((pppp.lib.stream.FBy)(this));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
;
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50823 =
                  ((pppp.lib.stream.FBy<$T>)x50804).
                    i;
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50824 =
                  ((t50823) + (((int)(1))));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50825 =
                  ((pppp.lib.stream.FBy<$T>)x50804).i = t50824;
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50826 =
                  ((t50825) - (((int)(1))));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final long t50828 =
                  ((long)(((int)(t50826))));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final $T item =
                  (($T)(((x10.core.Rail<$T>)t50827).$apply$G((long)(t50828))));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50829 =
                  (("FBy ") + (this));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50830 =
                  ((t50829) + ("==>"));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50831 =
                  ((t50830) + (item));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
pppp.util.Logger.log(((java.lang.String)(t50831)));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
return item;
            }
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final pppp.lib.stream.Stream t50833 =
              ((pppp.lib.stream.Stream)(b));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final boolean t50838 =
              ((t50833) != (null));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
if (t50838) {
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final pppp.lib.stream.Stream t50834 =
                  ((pppp.lib.stream.Stream)(b));
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final $T item =
                  (($T)(((pppp.lib.stream.Stream<$T>)t50834).$apply$G()));
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50835 =
                  (("FBy ") + (this));
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50836 =
                  ((t50835) + ("==>"));
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50837 =
                  ((t50836) + (item));
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
pppp.util.Logger.log(((java.lang.String)(t50837)));
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
return item;
            }
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50839 =
              (("FBy ") + (this));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50840 =
              ((t50839) + (" end of stream."));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
pppp.util.Logger.log(((java.lang.String)(t50840)));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final pppp.lib.stream.StreamClosedException t50841 =
              ((pppp.lib.stream.StreamClosedException)(new pppp.lib.stream.StreamClosedException(((java.lang.Object)(this)))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
throw t50841;
        }
        
        
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final public java.lang.String
                                                                                                                                                toString(
                                                                                                                                                ){
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final x10.core.Rail t50842 =
              ((x10.core.Rail)(a));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50847 =
              (("<") + (t50842));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final pppp.lib.stream.Stream t50843 =
              ((pppp.lib.stream.Stream)(b));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final boolean t50845 =
              ((t50843) == (null));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
java.lang.String t50846 =
               null;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
if (t50845) {
                
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
t50846 = "";
            } else {
                
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final pppp.lib.stream.Stream t50844 =
                  ((pppp.lib.stream.Stream)(b));
                
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
t50846 = ((" -> ") + (t50844));
            }
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50848 =
              t50846;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50849 =
              ((t50847) + (t50848));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final java.lang.String t50850 =
              ((t50849) + (">"));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
return t50850;
        }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final public pppp.lib.stream.FBy
                                                                                                                                               pppp$lib$stream$FBy$$this$pppp$lib$stream$FBy(
                                                                                                                                               ){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
return pppp.lib.stream.FBy.this;
        }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final public void
                                                                                                                                               __fieldInitializers_pppp_lib_stream_FBy(
                                                                                                                                               ){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
((pppp.lib.stream.FBy<$T>)this).i = 0;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$82<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$82> $RTT = x10.rtt.StaticFunType.<$Closure$82> make(
            $Closure$82.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.INT)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.FBy.$Closure$82<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$82.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.FBy.$Closure$82 $_obj = new pppp.lib.stream.FBy.$Closure$82((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$82(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lib.stream.FBy.$Closure$82.$initParams(this, $T);
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
                public static void $initParams(final $Closure$82 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public int
                  $apply$O(
                  final long i){
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50812 =
                      this.
                        x.
                        min;
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50813 =
                      ((int)(long)(((long)(i))));
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
final int t50814 =
                      ((t50812) + (((int)(t50813))));
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FBy.x10"
return t50814;
                }
                
                public x10.lang.IntRange x;
                
                public $Closure$82(final x10.rtt.Type $T,
                                   final x10.lang.IntRange x) {pppp.lib.stream.FBy.$Closure$82.$initParams(this, $T);
                                                                    {
                                                                       ((pppp.lib.stream.FBy.$Closure$82<$T>)this).x = ((x10.lang.IntRange)(x));
                                                                   }}
                
            }
            
        // synthetic type for parameter mangling
        public static final class __0pppp$lib$stream$FBy$$T {}
        // synthetic type for parameter mangling
        public static final class __0$1pppp$lib$stream$FBy$$T$2 {}
        // synthetic type for parameter mangling
        public static final class __0$1pppp$lib$stream$FBy$$T$2__1$1pppp$lib$stream$FBy$$T$2 {}
        // synthetic type for parameter mangling
        public static final class __0pppp$lib$stream$FBy$$T__1$1pppp$lib$stream$FBy$$T$2 {}
        
    }
    
    