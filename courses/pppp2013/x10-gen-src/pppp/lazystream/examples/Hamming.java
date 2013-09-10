package pppp.lazystream.examples;


@x10.runtime.impl.java.X10Generated public class Hamming extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Hamming> $RTT = x10.rtt.NamedType.<Hamming> make(
    "pppp.lazystream.examples.Hamming", Hamming.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Hamming $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Hamming.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lazystream.examples.Hamming $_obj = new pppp.lazystream.examples.Hamming((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Hamming(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
public static pppp.lazystream.Stream
                                                                                                                                                             h(
                                                                                                                                                             ){
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final x10.core.fun.Fun_0_0 t45090 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.examples.Hamming.$Closure$32()));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final pppp.lazystream.Stream t45091 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<x10.core.Long> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(x10.rtt.Types.LONG, ((x10.core.fun.Fun_0_0)(t45090)))));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
return t45091;
        }
        
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
private static pppp.lazystream.Stream<x10.core.Long> x;
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        Hamming.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final long t45092 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final boolean t45094 =
              ((t45092) > (((long)(0L))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
int t45095 =
               0;
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
if (t45094) {
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final java.lang.String t45093 =
                  ((java.lang.String[])args.value)[(int)0L];
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
t45095 = java.lang.Integer.parseInt(t45093);
            } else {
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
t45095 = 10;
            }
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final int N =
              t45095;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final x10.io.Printer t45097 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final java.lang.String t45096 =
              (("hamming ") + ((x10.core.Int.$box(N))));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final java.lang.String t45098 =
              ((t45096) + (" = "));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
t45097.print(((java.lang.String)(t45098)));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
long time =
              x10.lang.System.nanoTime$O();
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final pppp.lazystream.Stream t45099 =
              pppp.lazystream.examples.Hamming.h();
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
((pppp.lazystream.Stream<x10.core.Long>)t45099).force((int)(N));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final x10.io.Printer t45110 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final long t45100 =
              x10.lang.System.nanoTime$O();
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final long t45101 =
              time;
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final long t45102 =
              ((t45100) - (((long)(t45101))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final double t45103 =
              ((double)(long)(((long)(t45102))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final double t45106 =
              ((t45103) * (((double)(1.0))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final long t45104 =
              1000000L;
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final long t45105 =
              1000000000L;
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final double t45107 =
              ((double)(long)(((long)(t45105))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final double t45108 =
              ((t45106) / (((double)(t45107))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final java.lang.String t45109 =
              (("Time:") + ((x10.core.Double.$box(t45108))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final java.lang.String t45111 =
              ((t45109) + (" s."));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
t45110.println(((java.lang.Object)(t45111)));
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final public pppp.lazystream.examples.Hamming
                                                                                                                                                             pppp$lazystream$examples$Hamming$$this$pppp$lazystream$examples$Hamming(
                                                                                                                                                             ){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
return pppp.lazystream.examples.Hamming.this;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
// creation method for java code (1-phase java constructor)
        public Hamming(){this((java.lang.System[]) null);
                             pppp$lazystream$examples$Hamming$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lazystream.examples.Hamming pppp$lazystream$examples$Hamming$$init$S() { {
                                                                                                          
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
;
                                                                                                          
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"

                                                                                                          
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
this.__fieldInitializers_pppp_lazystream_examples_Hamming();
                                                                                                      }
                                                                                                      return this;
                                                                                                      }
        
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final public void
                                                                                                                                                             __fieldInitializers_pppp_lazystream_examples_Hamming(
                                                                                                                                                             ){
            
        }
        
        final private static x10.core.concurrent.AtomicInteger initStatus$x = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
        private static x10.lang.ExceptionInInitializer exception$x;
        
        public static pppp.lazystream.Stream
          get$x(
          ){
            if (((int) pppp.lazystream.examples.Hamming.initStatus$x.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                return pppp.lazystream.examples.Hamming.x;
            }
            if (((int) pppp.lazystream.examples.Hamming.initStatus$x.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.examples.Hamming.x")));
                }
                throw pppp.lazystream.examples.Hamming.exception$x;
            }
            if (pppp.lazystream.examples.Hamming.initStatus$x.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                            (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                try {{
                    pppp.lazystream.examples.Hamming.x = ((pppp.lazystream.Stream<x10.core.Long>)
                                                           ((pppp.lazystream.Stream<x10.core.Long>)((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                     ((pppp.lazystream.Stream<x10.core.Long>)((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                                                               ((pppp.lazystream.Stream<x10.core.Long>)pppp.lazystream.examples.Hamming.h()).$inv_times__0pppp$lazystream$Stream$$T(x10.core.Long.$box(5L)))).$inv_caret__0$1pppp$lazystream$Stream$$T$2(((pppp.lazystream.Stream)(((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                                                                                                                                                                                                                                                                                     ((pppp.lazystream.Stream<x10.core.Long>)((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                                                                                                                                                                                                                                                                                                                               ((pppp.lazystream.Stream<x10.core.Long>)pppp.lazystream.examples.Hamming.h()).$inv_times__0pppp$lazystream$Stream$$T(x10.core.Long.$box(3L)))).$inv_caret__0$1pppp$lazystream$Stream$$T$2(((pppp.lazystream.Stream)(((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ((pppp.lazystream.Stream<x10.core.Long>)pppp.lazystream.examples.Hamming.h()).$inv_times__0pppp$lazystream$Stream$$T(x10.core.Long.$box(2L)))))))))))).$inv_tilde__0pppp$lazystream$Stream$$T(x10.core.Long.$box(1L)));
                }}catch (java.lang.Throwable exc$45112) {
                    pppp.lazystream.examples.Hamming.exception$x = new x10.lang.ExceptionInInitializer(exc$45112);
                    pppp.lazystream.examples.Hamming.initStatus$x.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    throw pppp.lazystream.examples.Hamming.exception$x;
                }
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: pppp.lazystream.examples.Hamming.x")));
                }
                pppp.lazystream.examples.Hamming.initStatus$x.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
            } else {
                if (pppp.lazystream.examples.Hamming.initStatus$x.get() <
                    x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    while (pppp.lazystream.examples.Hamming.initStatus$x.get() <
                           x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                        x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                    }
                    x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                    if (((int) pppp.lazystream.examples.Hamming.initStatus$x.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.examples.Hamming.x")));
                        }
                        throw pppp.lazystream.examples.Hamming.exception$x;
                    }
                }
            }
            return pppp.lazystream.examples.Hamming.x;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$32 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$32> $RTT = x10.rtt.StaticFunType.<$Closure$32> make(
            $Closure$32.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.Types.LONG))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Hamming.$Closure$32 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$32.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.Hamming.$Closure$32 $_obj = new pppp.lazystream.examples.Hamming.$Closure$32((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$32(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final pppp.lazystream.Stream t45088 =
                      ((pppp.lazystream.Stream)(pppp.lazystream.examples.Hamming.get$x()));
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
final pppp.lazystream.Cons t45089 =
                      ((pppp.lazystream.Cons<x10.core.Long>)
                        ((pppp.lazystream.Stream<x10.core.Long>)t45088).$apply());
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Hamming.x10"
return t45089;
                }
                
                public $Closure$32() { {
                                              
                                          }}
                
            }
            
        
    }
    
    