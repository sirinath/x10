package pppp.lazystream.examples;


@x10.runtime.impl.java.X10Generated public class Fib extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Fib> $RTT = x10.rtt.NamedType.<Fib> make(
    "pppp.lazystream.examples.Fib", Fib.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Fib $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Fib.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lazystream.examples.Fib $_obj = new pppp.lazystream.examples.Fib((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Fib(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
public static pppp.lazystream.Stream
                                                                                                                                                         fib(
                                                                                                                                                         ){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final x10.core.fun.Fun_0_0 t53528 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.examples.Fib.$Closure$126()));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final pppp.lazystream.Stream t53529 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<x10.core.Long> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(x10.rtt.Types.LONG, ((x10.core.fun.Fun_0_0)(t53528)))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
return t53529;
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
private static pppp.lazystream.Stream<x10.core.Long> x;
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        Fib.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final long t53530 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final boolean t53532 =
              ((t53530) > (((long)(0L))));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
int t53533 =
               0;
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
if (t53532) {
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final java.lang.String t53531 =
                  ((java.lang.String[])args.value)[(int)0L];
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
t53533 = java.lang.Integer.parseInt(t53531);
            } else {
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
t53533 = 10;
            }
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final int N =
              t53533;
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final x10.io.Printer t53535 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final java.lang.String t53534 =
              (("fib ") + ((x10.core.Int.$box(N))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final java.lang.String t53536 =
              ((t53534) + (" = "));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
t53535.print(((java.lang.String)(t53536)));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
long time =
              x10.lang.System.nanoTime$O();
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final pppp.lazystream.Stream t53537 =
              pppp.lazystream.examples.Fib.fib();
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
((pppp.lazystream.Stream<x10.core.Long>)t53537).force((int)(N));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final x10.io.Printer t53548 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final long t53538 =
              x10.lang.System.nanoTime$O();
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final long t53539 =
              time;
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final long t53540 =
              ((t53538) - (((long)(t53539))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final double t53541 =
              ((double)(long)(((long)(t53540))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final double t53544 =
              ((t53541) * (((double)(1.0))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final long t53542 =
              1000000L;
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final long t53543 =
              1000000000L;
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final double t53545 =
              ((double)(long)(((long)(t53543))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final double t53546 =
              ((t53544) / (((double)(t53545))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final java.lang.String t53547 =
              (("Time:") + ((x10.core.Double.$box(t53546))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final java.lang.String t53549 =
              ((t53547) + (" s."));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
t53548.println(((java.lang.Object)(t53549)));
        }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final public pppp.lazystream.examples.Fib
                                                                                                                                                        pppp$lazystream$examples$Fib$$this$pppp$lazystream$examples$Fib(
                                                                                                                                                        ){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
return pppp.lazystream.examples.Fib.this;
        }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
// creation method for java code (1-phase java constructor)
        public Fib(){this((java.lang.System[]) null);
                         pppp$lazystream$examples$Fib$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lazystream.examples.Fib pppp$lazystream$examples$Fib$$init$S() { {
                                                                                                  
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
;
                                                                                                  
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"

                                                                                                  
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
this.__fieldInitializers_pppp_lazystream_examples_Fib();
                                                                                              }
                                                                                              return this;
                                                                                              }
        
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final public void
                                                                                                                                                        __fieldInitializers_pppp_lazystream_examples_Fib(
                                                                                                                                                        ){
            
        }
        
        final private static x10.core.concurrent.AtomicInteger initStatus$x = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
        private static x10.lang.ExceptionInInitializer exception$x;
        
        public static pppp.lazystream.Stream
          get$x(
          ){
            if (((int) pppp.lazystream.examples.Fib.initStatus$x.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                return pppp.lazystream.examples.Fib.x;
            }
            if (((int) pppp.lazystream.examples.Fib.initStatus$x.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.examples.Fib.x")));
                }
                throw pppp.lazystream.examples.Fib.exception$x;
            }
            if (pppp.lazystream.examples.Fib.initStatus$x.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                        (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                try {{
                    pppp.lazystream.examples.Fib.x = ((pppp.lazystream.Stream<x10.core.Long>)
                                                       ((pppp.lazystream.Stream<x10.core.Long>)((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                 ((pppp.lazystream.Stream<x10.core.Long>)((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                                                           ((pppp.lazystream.Stream<x10.core.Long>)((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                                                                                                     ((pppp.lazystream.Stream<x10.core.Long>)pppp.lazystream.examples.Fib.fib()).cdr())).$inv_plus__0$1pppp$lazystream$Stream$$T$2(((pppp.lazystream.Stream)(pppp.lazystream.examples.Fib.fib()))))).$inv_tilde__0pppp$lazystream$Stream$$T(x10.core.Long.$box(2L)))).$inv_tilde__0pppp$lazystream$Stream$$T(x10.core.Long.$box(1L)));
                }}catch (java.lang.Throwable exc$53550) {
                    pppp.lazystream.examples.Fib.exception$x = new x10.lang.ExceptionInInitializer(exc$53550);
                    pppp.lazystream.examples.Fib.initStatus$x.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    throw pppp.lazystream.examples.Fib.exception$x;
                }
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: pppp.lazystream.examples.Fib.x")));
                }
                pppp.lazystream.examples.Fib.initStatus$x.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
            } else {
                if (pppp.lazystream.examples.Fib.initStatus$x.get() <
                    x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    while (pppp.lazystream.examples.Fib.initStatus$x.get() <
                           x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                        x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                    }
                    x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                    if (((int) pppp.lazystream.examples.Fib.initStatus$x.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.examples.Fib.x")));
                        }
                        throw pppp.lazystream.examples.Fib.exception$x;
                    }
                }
            }
            return pppp.lazystream.examples.Fib.x;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$126 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$126> $RTT = x10.rtt.StaticFunType.<$Closure$126> make(
            $Closure$126.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.Types.LONG))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Fib.$Closure$126 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$126.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.Fib.$Closure$126 $_obj = new pppp.lazystream.examples.Fib.$Closure$126((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$126(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final pppp.lazystream.Stream t53526 =
                      ((pppp.lazystream.Stream)(pppp.lazystream.examples.Fib.get$x()));
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
final pppp.lazystream.Cons t53527 =
                      ((pppp.lazystream.Cons<x10.core.Long>)
                        ((pppp.lazystream.Stream<x10.core.Long>)t53526).$apply());
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fib.x10"
return t53527;
                }
                
                public $Closure$126() { {
                                               
                                           }}
                
            }
            
        
    }
    
    