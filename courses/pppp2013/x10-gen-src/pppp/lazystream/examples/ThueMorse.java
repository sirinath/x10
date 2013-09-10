package pppp.lazystream.examples;


@x10.runtime.impl.java.X10Generated public class ThueMorse extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<ThueMorse> $RTT = x10.rtt.NamedType.<ThueMorse> make(
    "pppp.lazystream.examples.ThueMorse", ThueMorse.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.ThueMorse $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + ThueMorse.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lazystream.examples.ThueMorse $_obj = new pppp.lazystream.examples.ThueMorse((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public ThueMorse(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
public static pppp.lazystream.Stream
                                                                                                                                                               m(
                                                                                                                                                               ){
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final x10.core.fun.Fun_0_0 t47284 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.examples.ThueMorse.$Closure$54()));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final pppp.lazystream.Stream t47285 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<x10.core.Int> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(x10.rtt.Types.INT, ((x10.core.fun.Fun_0_0)(t47284)))));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
return t47285;
        }
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
private static pppp.lazystream.Stream<x10.core.Int> x;
        
        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        ThueMorse.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final long t47286 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final boolean t47288 =
              ((t47286) > (((long)(0L))));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
int t47289 =
               0;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
if (t47288) {
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final java.lang.String t47287 =
                  ((java.lang.String[])args.value)[(int)0L];
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
t47289 = java.lang.Integer.parseInt(t47287);
            } else {
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
t47289 = 10;
            }
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final int N =
              t47289;
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final long time =
              x10.lang.System.nanoTime$O();
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final x10.io.Printer t47291 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final java.lang.String t47290 =
              (("Thue Morse ") + ((x10.core.Int.$box(N))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final java.lang.String t47292 =
              ((t47290) + (" = "));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
t47291.print(((java.lang.String)(t47292)));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final pppp.lazystream.Stream t47293 =
              pppp.lazystream.examples.ThueMorse.m();
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
((pppp.lazystream.Stream<x10.core.Int>)t47293).print((int)(N));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final x10.io.Printer t47303 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final long t47294 =
              x10.lang.System.nanoTime$O();
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final long t47295 =
              ((t47294) - (((long)(time))));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final double t47296 =
              ((double)(long)(((long)(t47295))));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final double t47299 =
              ((t47296) * (((double)(1.0))));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final long t47297 =
              1000000L;
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final long t47298 =
              1000000000L;
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final double t47300 =
              ((double)(long)(((long)(t47298))));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final double t47301 =
              ((t47299) / (((double)(t47300))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final java.lang.String t47302 =
              (("Time:") + ((x10.core.Double.$box(t47301))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final java.lang.String t47304 =
              ((t47302) + (" s."));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
t47303.println(((java.lang.Object)(t47304)));
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final public pppp.lazystream.examples.ThueMorse
                                                                                                                                                               pppp$lazystream$examples$ThueMorse$$this$pppp$lazystream$examples$ThueMorse(
                                                                                                                                                               ){
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
return pppp.lazystream.examples.ThueMorse.this;
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
// creation method for java code (1-phase java constructor)
        public ThueMorse(){this((java.lang.System[]) null);
                               pppp$lazystream$examples$ThueMorse$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lazystream.examples.ThueMorse pppp$lazystream$examples$ThueMorse$$init$S() { {
                                                                                                              
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
;
                                                                                                              
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"

                                                                                                              
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
this.__fieldInitializers_pppp_lazystream_examples_ThueMorse();
                                                                                                          }
                                                                                                          return this;
                                                                                                          }
        
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final public void
                                                                                                                                                               __fieldInitializers_pppp_lazystream_examples_ThueMorse(
                                                                                                                                                               ){
            
        }
        
        final private static x10.core.concurrent.AtomicInteger initStatus$x = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
        private static x10.lang.ExceptionInInitializer exception$x;
        
        public static pppp.lazystream.Stream
          get$x(
          ){
            if (((int) pppp.lazystream.examples.ThueMorse.initStatus$x.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                return pppp.lazystream.examples.ThueMorse.x;
            }
            if (((int) pppp.lazystream.examples.ThueMorse.initStatus$x.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.examples.ThueMorse.x")));
                }
                throw pppp.lazystream.examples.ThueMorse.exception$x;
            }
            if (pppp.lazystream.examples.ThueMorse.initStatus$x.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                              (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                try {{
                    pppp.lazystream.examples.ThueMorse.x = ((pppp.lazystream.Stream<x10.core.Int>)
                                                             ((pppp.lazystream.Stream<x10.core.Int>)((pppp.lazystream.Stream<x10.core.Int>)
                                                                                                      ((pppp.lazystream.Stream<x10.core.Int>)((pppp.lazystream.Stream<x10.core.Int>)
                                                                                                                                               ((pppp.lazystream.Stream<x10.core.Int>)((pppp.lazystream.Stream<x10.core.Int>)
                                                                                                                                                                                        ((pppp.lazystream.Stream<x10.core.Int>)pppp.lazystream.examples.ThueMorse.m()).even())).$apply__0$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(((x10.core.fun.Fun_0_1)(new pppp.lazystream.examples.ThueMorse.$Closure$55()))))).zip__0$1pppp$lazystream$Stream$$T$2(((pppp.lazystream.Stream)(((pppp.lazystream.Stream<x10.core.Int>)
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ((pppp.lazystream.Stream<x10.core.Int>)pppp.lazystream.examples.ThueMorse.m()).cdr())))))).$inv_tilde__0pppp$lazystream$Stream$$T(x10.core.Int.$box(0)));
                }}catch (java.lang.Throwable exc$47305) {
                    pppp.lazystream.examples.ThueMorse.exception$x = new x10.lang.ExceptionInInitializer(exc$47305);
                    pppp.lazystream.examples.ThueMorse.initStatus$x.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    throw pppp.lazystream.examples.ThueMorse.exception$x;
                }
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: pppp.lazystream.examples.ThueMorse.x")));
                }
                pppp.lazystream.examples.ThueMorse.initStatus$x.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
            } else {
                if (pppp.lazystream.examples.ThueMorse.initStatus$x.get() <
                    x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    while (pppp.lazystream.examples.ThueMorse.initStatus$x.get() <
                           x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                        x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                    }
                    x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                    if (((int) pppp.lazystream.examples.ThueMorse.initStatus$x.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.examples.ThueMorse.x")));
                        }
                        throw pppp.lazystream.examples.ThueMorse.exception$x;
                    }
                }
            }
            return pppp.lazystream.examples.ThueMorse.x;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$54 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$54> $RTT = x10.rtt.StaticFunType.<$Closure$54> make(
            $Closure$54.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.Types.INT))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.ThueMorse.$Closure$54 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$54.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.ThueMorse.$Closure$54 $_obj = new pppp.lazystream.examples.ThueMorse.$Closure$54((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$54(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final pppp.lazystream.Stream t47282 =
                      ((pppp.lazystream.Stream)(pppp.lazystream.examples.ThueMorse.get$x()));
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
final pppp.lazystream.Cons t47283 =
                      ((pppp.lazystream.Cons<x10.core.Int>)
                        ((pppp.lazystream.Stream<x10.core.Int>)t47282).$apply());
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
return t47283;
                }
                
                public $Closure$54() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$55 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$55> $RTT = x10.rtt.StaticFunType.<$Closure$55> make(
            $Closure$55.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.INT, x10.rtt.Types.INT)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.ThueMorse.$Closure$55 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$55.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.ThueMorse.$Closure$55 $_obj = new pppp.lazystream.examples.ThueMorse.$Closure$55((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$55(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return x10.core.Int.$box($apply$O(x10.core.Int.$unbox(a1)));
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply$O(x10.core.Int.$unbox(a1));
            }
            
                
                public int
                  $apply$O(
                  final int i){
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/ThueMorse.x10"
return ((1) - (((int)(i))));
                }
                
                public $Closure$55() { {
                                              
                                          }}
                
            }
            
        
        }
        
        