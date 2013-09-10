package pppp.lazystream.examples;


@x10.runtime.impl.java.X10Generated public class Fact extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Fact> $RTT = x10.rtt.NamedType.<Fact> make(
    "pppp.lazystream.examples.Fact", Fact.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Fact $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Fact.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lazystream.examples.Fact $_obj = new pppp.lazystream.examples.Fact((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Fact(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
public static pppp.lazystream.Stream
                                                                                                                                                          fact(
                                                                                                                                                          ){
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final x10.core.fun.Fun_0_0 t51985 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.examples.Fact.$Closure$87()));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Stream t51986 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<x10.core.Long> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(x10.rtt.Types.LONG, ((x10.core.fun.Fun_0_0)(t51985)))));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
return t51986;
        }
        
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
private static pppp.lazystream.Stream<x10.core.Long> x;
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
public static pppp.lazystream.Stream
                                                                                                                                                          fact2(
                                                                                                                                                          ){
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final x10.core.fun.Fun_0_0 t51991 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.examples.Fact.$Closure$88()));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Stream t51992 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<x10.core.Long> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(x10.rtt.Types.LONG, ((x10.core.fun.Fun_0_0)(t51991)))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
return t51992;
        }
        
        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        Fact.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t51993 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final boolean t51995 =
              ((t51993) > (((long)(0L))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
int t51996 =
               0;
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
if (t51995) {
                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t51994 =
                  ((java.lang.String[])args.value)[(int)0L];
                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
t51996 = java.lang.Integer.parseInt(t51994);
            } else {
                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
t51996 = 10;
            }
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final int N =
              t51996;
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final x10.io.Printer t51998 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t51997 =
              (("fact ") + ((x10.core.Int.$box(N))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t51999 =
              ((t51997) + (" = "));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
t51998.print(((java.lang.String)(t51999)));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
long time =
              x10.lang.System.nanoTime$O();
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Stream t52000 =
              pppp.lazystream.examples.Fact.fact();
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
((pppp.lazystream.Stream<x10.core.Long>)t52000).force((int)(N));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final x10.io.Printer t52011 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52001 =
              x10.lang.System.nanoTime$O();
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52002 =
              time;
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52003 =
              ((t52001) - (((long)(t52002))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final double t52004 =
              ((double)(long)(((long)(t52003))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final double t52007 =
              ((t52004) * (((double)(1.0))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52005 =
              1000000L;
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52006 =
              1000000000L;
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final double t52008 =
              ((double)(long)(((long)(t52006))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final double t52009 =
              ((t52007) / (((double)(t52008))));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t52010 =
              (("Time:") + ((x10.core.Double.$box(t52009))));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t52012 =
              ((t52010) + (" s."));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
t52011.println(((java.lang.Object)(t52012)));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final x10.io.Printer t52014 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t52013 =
              (("fact2 ") + ((x10.core.Int.$box(N))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t52015 =
              ((t52013) + (" = "));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
t52014.print(((java.lang.String)(t52015)));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52016 =
              x10.lang.System.nanoTime$O();
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
time = t52016;
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Stream t52017 =
              pppp.lazystream.examples.Fact.fact2();
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
((pppp.lazystream.Stream<x10.core.Long>)t52017).force((int)(N));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final x10.io.Printer t52028 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52018 =
              x10.lang.System.nanoTime$O();
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52019 =
              time;
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52020 =
              ((t52018) - (((long)(t52019))));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final double t52021 =
              ((double)(long)(((long)(t52020))));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final double t52024 =
              ((t52021) * (((double)(1.0))));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52022 =
              1000000L;
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final long t52023 =
              1000000000L;
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final double t52025 =
              ((double)(long)(((long)(t52023))));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final double t52026 =
              ((t52024) / (((double)(t52025))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t52027 =
              (("Time:") + ((x10.core.Double.$box(t52026))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final java.lang.String t52029 =
              ((t52027) + (" s."));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
t52028.println(((java.lang.Object)(t52029)));
        }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final public pppp.lazystream.examples.Fact
                                                                                                                                                         pppp$lazystream$examples$Fact$$this$pppp$lazystream$examples$Fact(
                                                                                                                                                         ){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
return pppp.lazystream.examples.Fact.this;
        }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
// creation method for java code (1-phase java constructor)
        public Fact(){this((java.lang.System[]) null);
                          pppp$lazystream$examples$Fact$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lazystream.examples.Fact pppp$lazystream$examples$Fact$$init$S() { {
                                                                                                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
;
                                                                                                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"

                                                                                                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
this.__fieldInitializers_pppp_lazystream_examples_Fact();
                                                                                                }
                                                                                                return this;
                                                                                                }
        
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final public void
                                                                                                                                                         __fieldInitializers_pppp_lazystream_examples_Fact(
                                                                                                                                                         ){
            
        }
        
        final private static x10.core.concurrent.AtomicInteger initStatus$x = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
        private static x10.lang.ExceptionInInitializer exception$x;
        
        public static pppp.lazystream.Stream
          get$x(
          ){
            if (((int) pppp.lazystream.examples.Fact.initStatus$x.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                return pppp.lazystream.examples.Fact.x;
            }
            if (((int) pppp.lazystream.examples.Fact.initStatus$x.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.examples.Fact.x")));
                }
                throw pppp.lazystream.examples.Fact.exception$x;
            }
            if (pppp.lazystream.examples.Fact.initStatus$x.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                         (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                try {{
                    pppp.lazystream.examples.Fact.x = ((pppp.lazystream.Stream<x10.core.Long>)
                                                        ((pppp.lazystream.Stream<x10.core.Long>)((pppp.lazystream.Stream<x10.core.Long>)
                                                                                                  ((pppp.lazystream.Stream<x10.core.Long>)pppp.lazystream.examples.Fact.fact()).$inv_times__0$1pppp$lazystream$Stream$$T$2(((pppp.lazystream.Stream)(pppp.lazystream.Stream.longNats((long)(2L))))))).$inv_tilde__0pppp$lazystream$Stream$$T(x10.core.Long.$box(1L)));
                }}catch (java.lang.Throwable exc$52030) {
                    pppp.lazystream.examples.Fact.exception$x = new x10.lang.ExceptionInInitializer(exc$52030);
                    pppp.lazystream.examples.Fact.initStatus$x.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    throw pppp.lazystream.examples.Fact.exception$x;
                }
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: pppp.lazystream.examples.Fact.x")));
                }
                pppp.lazystream.examples.Fact.initStatus$x.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
            } else {
                if (pppp.lazystream.examples.Fact.initStatus$x.get() <
                    x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    while (pppp.lazystream.examples.Fact.initStatus$x.get() <
                           x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                        x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                    }
                    x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                    if (((int) pppp.lazystream.examples.Fact.initStatus$x.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.examples.Fact.x")));
                        }
                        throw pppp.lazystream.examples.Fact.exception$x;
                    }
                }
            }
            return pppp.lazystream.examples.Fact.x;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$87 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$87> $RTT = x10.rtt.StaticFunType.<$Closure$87> make(
            $Closure$87.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.Types.LONG))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Fact.$Closure$87 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$87.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.Fact.$Closure$87 $_obj = new pppp.lazystream.examples.Fact.$Closure$87((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$87(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Stream t51983 =
                      ((pppp.lazystream.Stream)(pppp.lazystream.examples.Fact.get$x()));
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Cons t51984 =
                      ((pppp.lazystream.Cons<x10.core.Long>)
                        ((pppp.lazystream.Stream<x10.core.Long>)t51983).$apply());
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
return t51984;
                }
                
                public $Closure$87() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$88 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$88> $RTT = x10.rtt.StaticFunType.<$Closure$88> make(
            $Closure$88.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.Types.LONG))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Fact.$Closure$88 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$88.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.Fact.$Closure$88 $_obj = new pppp.lazystream.examples.Fact.$Closure$88((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$88(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Stream t51987 =
                      pppp.lazystream.examples.Fact.fact2();
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Stream t51988 =
                      pppp.lazystream.Stream.longNats((long)(2L));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Stream t51989 =
                      ((pppp.lazystream.Stream<x10.core.Long>)
                        ((pppp.lazystream.Stream<x10.core.Long>)t51987).$inv_times__0$1pppp$lazystream$Stream$$T$2(((pppp.lazystream.Stream)(t51988))));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
final pppp.lazystream.Cons t51990 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<x10.core.Long>((java.lang.System[]) null, x10.rtt.Types.LONG).pppp$lazystream$Cons$$init$S((x10.core.Long.$box(1L)),
                                                                                                                                                                  t51989, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Fact.x10"
return t51990;
                }
                
                public $Closure$88() { {
                                              
                                          }}
                
            }
            
        
        }
        
        