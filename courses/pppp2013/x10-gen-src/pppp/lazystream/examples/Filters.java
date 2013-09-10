package pppp.lazystream.examples;


@x10.runtime.impl.java.X10Generated public class Filters extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Filters> $RTT = x10.rtt.NamedType.<Filters> make(
    "pppp.lazystream.examples.Filters", Filters.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Filters $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Filters.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lazystream.examples.Filters $_obj = new pppp.lazystream.examples.Filters((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Filters(final java.lang.System[] $dummy) { 
    }
    
        
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
public static pppp.lazystream.Stream
                                                                                                                                                             Average__0$1x10$lang$Double$2(
                                                                                                                                                             final pppp.lazystream.Stream<x10.core.Double> a){
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final x10.core.fun.Fun_0_0 t52884 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.examples.Filters.$Closure$117(a, (pppp.lazystream.examples.Filters.$Closure$117.__0$1x10$lang$Double$2) null)));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t52885 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<x10.core.Double> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(x10.rtt.Types.DOUBLE, ((x10.core.fun.Fun_0_0)(t52884)))));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
return t52885;
        }
        
        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
public static pppp.lazystream.Stream
                                                                                                                                                             Interpolant__0$1x10$lang$Double$2(
                                                                                                                                                             final pppp.lazystream.Stream<x10.core.Double> a){
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final x10.core.fun.Fun_0_0 t52910 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.examples.Filters.$Closure$118(a, (pppp.lazystream.examples.Filters.$Closure$118.__0$1x10$lang$Double$2) null)));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t52911 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<x10.core.Double> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(x10.rtt.Types.DOUBLE, ((x10.core.fun.Fun_0_0)(t52910)))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
return t52911;
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final public pppp.lazystream.examples.Filters
                                                                                                                                                             pppp$lazystream$examples$Filters$$this$pppp$lazystream$examples$Filters(
                                                                                                                                                             ){
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
return pppp.lazystream.examples.Filters.this;
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
// creation method for java code (1-phase java constructor)
        public Filters(){this((java.lang.System[]) null);
                             pppp$lazystream$examples$Filters$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lazystream.examples.Filters pppp$lazystream$examples$Filters$$init$S() { {
                                                                                                          
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
;
                                                                                                          
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"

                                                                                                          
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
this.__fieldInitializers_pppp_lazystream_examples_Filters();
                                                                                                      }
                                                                                                      return this;
                                                                                                      }
        
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final public void
                                                                                                                                                             __fieldInitializers_pppp_lazystream_examples_Filters(
                                                                                                                                                             ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$117 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$117> $RTT = x10.rtt.StaticFunType.<$Closure$117> make(
            $Closure$117.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.Types.DOUBLE))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Filters.$Closure$117 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$117.class + " calling"); } 
                $_obj.a = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.Filters.$Closure$117 $_obj = new pppp.lazystream.examples.Filters.$Closure$117((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (a instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.a);
                } else {
                $serializer.write(this.a);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$117(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons ac =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)this.
                                                                    a).$apply());
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t52875 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<x10.core.Double>)ac).
                                                  t));
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons act =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)t52875).$apply());
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52876 =
                      x10.core.Double.$unbox(((pppp.lazystream.Cons<x10.core.Double>)ac).
                                               h);
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52877 =
                      x10.core.Double.$unbox(((pppp.lazystream.Cons<x10.core.Double>)act).
                                               h);
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52878 =
                      ((t52876) + (((double)(t52877))));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52879 =
                      ((double)(long)(((long)(2L))));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52881 =
                      ((t52878) / (((double)(t52879))));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t52880 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<x10.core.Double>)act).
                                                  t));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t52882 =
                      pppp.lazystream.examples.Filters.Average__0$1x10$lang$Double$2(((pppp.lazystream.Stream)(t52880)));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52883 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<x10.core.Double>((java.lang.System[]) null, x10.rtt.Types.DOUBLE).pppp$lazystream$Cons$$init$S(x10.core.Double.$box(t52881),
                                                                                                                                                                      t52882, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
return t52883;
                }
                
                public pppp.lazystream.Stream<x10.core.Double> a;
                
                public $Closure$117(final pppp.lazystream.Stream<x10.core.Double> a, __0$1x10$lang$Double$2 $dummy) { {
                                                                                                                             this.a = ((pppp.lazystream.Stream)(a));
                                                                                                                         }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Double$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$118 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$118> $RTT = x10.rtt.StaticFunType.<$Closure$118> make(
            $Closure$118.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.Types.DOUBLE))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Filters.$Closure$118 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$118.class + " calling"); } 
                $_obj.a = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.Filters.$Closure$118 $_obj = new pppp.lazystream.examples.Filters.$Closure$118((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (a instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.a);
                } else {
                $serializer.write(this.a);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$118(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52886 =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)this.
                                                                    a).$apply());
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double a1 =
                      x10.core.Double.$unbox(((pppp.lazystream.Cons<x10.core.Double>)t52886).
                                               h);
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52887 =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)this.
                                                                    a).$apply());
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t1 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<x10.core.Double>)t52887).
                                                  t));
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52888 =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)t1).$apply());
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double a2 =
                      x10.core.Double.$unbox(((pppp.lazystream.Cons<x10.core.Double>)t52888).
                                               h);
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52889 =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)t1).$apply());
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t2 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<x10.core.Double>)t52889).
                                                  t));
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52890 =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)t2).$apply());
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double a3 =
                      x10.core.Double.$unbox(((pppp.lazystream.Cons<x10.core.Double>)t52890).
                                               h);
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52891 =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)t2).$apply());
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t3 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<x10.core.Double>)t52891).
                                                  t));
                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52892 =
                      ((pppp.lazystream.Cons<x10.core.Double>)
                        ((pppp.lazystream.Stream<x10.core.Double>)t3).$apply());
                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double a4 =
                      x10.core.Double.$unbox(((pppp.lazystream.Cons<x10.core.Double>)t52892).
                                               h);
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52893 =
                      ((double)(long)(((long)(3L))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52894 =
                      ((t52893) * (((double)(a2))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52895 =
                      ((a1) + (((double)(t52894))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52896 =
                      ((double)(long)(((long)(4L))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52903 =
                      ((t52895) / (((double)(t52896))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52897 =
                      ((a2) + (((double)(a3))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52898 =
                      ((double)(long)(((long)(2L))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52904 =
                      ((t52897) / (((double)(t52898))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52899 =
                      ((double)(long)(((long)(3L))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52900 =
                      ((t52899) * (((double)(a3))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52901 =
                      ((t52900) + (((double)(a4))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52902 =
                      ((double)(long)(((long)(4L))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final double t52905 =
                      ((t52901) / (((double)(t52902))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final x10.core.Rail t52906 =
                      ((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.core.Double> makeRailFromJavaArray(x10.rtt.Types.DOUBLE, new double[] {t52903, t52904, t52905})));
                    
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t52907 =
                      pppp.lazystream.examples.Filters.Interpolant__0$1x10$lang$Double$2(((pppp.lazystream.Stream)(t3)));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Stream t52908 =
                      pppp.lazystream.Stream.<x10.core.Double> append__0$1pppp$lazystream$Stream$$T$2__1$1pppp$lazystream$Stream$$T$2(x10.rtt.Types.DOUBLE, ((x10.core.Rail)(t52906)),
                                                                                                                                      ((pppp.lazystream.Stream)(t52907)));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
final pppp.lazystream.Cons t52909 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<x10.core.Double>((java.lang.System[]) null, x10.rtt.Types.DOUBLE).pppp$lazystream$Cons$$init$S((x10.core.Double.$box(a1)),
                                                                                                                                                                      t52908, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Filters.x10"
return t52909;
                }
                
                public pppp.lazystream.Stream<x10.core.Double> a;
                
                public $Closure$118(final pppp.lazystream.Stream<x10.core.Double> a, __0$1x10$lang$Double$2 $dummy) { {
                                                                                                                             this.a = ((pppp.lazystream.Stream)(a));
                                                                                                                         }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Double$2 {}
                
            }
            
        
        }
        
        