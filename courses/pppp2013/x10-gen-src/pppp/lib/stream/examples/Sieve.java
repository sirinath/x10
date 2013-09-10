package pppp.lib.stream.examples;


@x10.runtime.impl.java.X10Generated public class Sieve extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Sieve> $RTT = x10.rtt.NamedType.<Sieve> make(
    "pppp.lib.stream.examples.Sieve", Sieve.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Sieve $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Sieve.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.examples.Sieve $_obj = new pppp.lib.stream.examples.Sieve((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Sieve(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        Sieve.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> s)  {
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final long t48910 =
              ((x10.core.Rail<java.lang.String>)s).
                size;
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final boolean t48912 =
              ((t48910) > (((long)(0L))));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
int t48913 =
               0;
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
if (t48912) {
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48911 =
                  ((java.lang.String[])s.value)[(int)0L];
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
t48913 = java.lang.Integer.parseInt(t48911);
            } else {
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
t48913 = 100;
            }
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final int N =
              t48913;
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final long time =
              x10.lang.System.nanoTime$O();
            {
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
x10.lang.Runtime.ensureNotInAtomic();
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final x10.lang.FinishState x10$__var18 =
                  x10.lang.Runtime.startFinish();
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
try {{
                    {
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
pppp.util.Logger.log(((java.lang.String)("Starting prime")));
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final pppp.lib.stream.Stream t48914 =
                          pppp.lib.stream.examples.Sieve.gen((int)(N));
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final pppp.lib.stream.Stream t48915 =
                          pppp.lib.stream.examples.Sieve.primes__0$1x10$lang$Int$2(((pppp.lib.stream.Stream)(t48914)));
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
pppp.lib.stream.XDucer.<x10.core.Int> print__0$1pppp$lib$stream$XDucer$$T$2(x10.rtt.Types.INT, ((pppp.lib.stream.Stream)(t48915)),
                                                                                                                                                                                                                                                     (int)(10));
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var18)));
                 }}
                }
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final x10.io.Printer t48925 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final long t48916 =
              x10.lang.System.nanoTime$O();
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final long t48917 =
              ((t48916) - (((long)(time))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final double t48918 =
              ((double)(long)(((long)(t48917))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final double t48921 =
              ((t48918) * (((double)(1.0))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final long t48919 =
              1000000L;
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final long t48920 =
              1000000000L;
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final double t48922 =
              ((double)(long)(((long)(t48920))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final double t48923 =
              ((t48921) / (((double)(t48922))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48924 =
              (("Time: ") + ((x10.core.Double.$box(t48923))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48926 =
              ((t48924) + (" s."));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
t48925.println(((java.lang.Object)(t48926)));
            }
        
        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
public static pppp.lib.stream.Stream
                                                                                                                                                           gen(
                                                                                                                                                           final int N){
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final x10.lang.IntRange t48927 =
              ((x10.lang.IntRange)(new x10.lang.IntRange((java.lang.System[]) null).x10$lang$IntRange$$init$S(((int)(2)), ((int)(N)))));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final pppp.lib.stream.FBy t48928 =
              ((pppp.lib.stream.FBy)(new pppp.lib.stream.FBy<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).pppp$lib$stream$FBy$$init$S(((x10.lang.IntRange)(t48927)))));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
return t48928;
        }
        
        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
public static pppp.lib.stream.Stream
                                                                                                                                                           primes__0$1x10$lang$Int$2(
                                                                                                                                                           final pppp.lib.stream.Stream<x10.core.Int> nums){
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
try {{
                
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final int prime =
                  x10.core.Int.$unbox(((pppp.lib.stream.Stream<x10.core.Int>)nums).$apply$G());
                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final x10.core.fun.Fun_0_0 t48930 =
                  ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.examples.Sieve.$Closure$64(prime)));
                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
pppp.util.Logger.debug__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t48930)));
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final x10.core.fun.Fun_0_0 t48934 =
                  ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.examples.Sieve.$Closure$65(prime,
                                                                                         nums, (pppp.lib.stream.examples.Sieve.$Closure$65.__1$1x10$lang$Int$2) null)));
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48933 =
                  (("prime(") + ((x10.core.Int.$box(prime))));
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48935 =
                  ((t48933) + (")"));
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final pppp.lib.stream.FByPush s =
                  ((pppp.lib.stream.FByPush)(new pppp.lib.stream.FByPush<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).pppp$lib$stream$FByPush$$init$S((x10.core.Int.$box(prime)),
                                                                                                                                                                     ((x10.core.fun.Fun_0_0)(t48934)),
                                                                                                                                                                     t48935, (pppp.lib.stream.FByPush.__0pppp$lib$stream$FByPush$$T__1$1pppp$lib$stream$Stream$1pppp$lib$stream$FByPush$$T$2$2) null)));
                
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
((pppp.lib.stream.FByPush<x10.core.Int>)s).run();
                
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
return s;
            }}catch (final pppp.lib.stream.StreamClosedException z) {
                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final x10.core.fun.Fun_0_0 t48938 =
                  ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.examples.Sieve.$Closure$66(nums, (pppp.lib.stream.examples.Sieve.$Closure$66.__0$1x10$lang$Int$2) null)));
                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
pppp.util.Logger.debug__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t48938)));
                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
throw z;
            }
        }
        
        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
public static pppp.lib.stream.Stream
                                                                                                                                                           sieve__1$1x10$lang$Int$2(
                                                                                                                                                           final int prime,
                                                                                                                                                           final pppp.lib.stream.Stream<x10.core.Int> nums){
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final pppp.lib.stream.BoundedStreamImp s =
              ((pppp.lib.stream.BoundedStreamImp)(new pppp.lib.stream.BoundedStreamImp<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).pppp$lib$stream$BoundedStreamImp$$init$S()));
            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new pppp.lib.stream.examples.Sieve.$Closure$68(prime,
                                                                                                                                                                                                                                                                  nums,
                                                                                                                                                                                                                                                                  s, (pppp.lib.stream.examples.Sieve.$Closure$68.__1$1x10$lang$Int$2__2$1x10$lang$Int$2) null))));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
return s;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final public pppp.lib.stream.examples.Sieve
                                                                                                                                                           pppp$lib$stream$examples$Sieve$$this$pppp$lib$stream$examples$Sieve(
                                                                                                                                                           ){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
return pppp.lib.stream.examples.Sieve.this;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
// creation method for java code (1-phase java constructor)
        public Sieve(){this((java.lang.System[]) null);
                           pppp$lib$stream$examples$Sieve$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.examples.Sieve pppp$lib$stream$examples$Sieve$$init$S() { {
                                                                                                      
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
;
                                                                                                      
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"

                                                                                                      
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
this.__fieldInitializers_pppp_lib_stream_examples_Sieve();
                                                                                                  }
                                                                                                  return this;
                                                                                                  }
        
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final public void
                                                                                                                                                           __fieldInitializers_pppp_lib_stream_examples_Sieve(
                                                                                                                                                           ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$64 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$64> $RTT = x10.rtt.StaticFunType.<$Closure$64> make(
            $Closure$64.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Sieve.$Closure$64 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$64.class + " calling"); } 
                $_obj.prime = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.examples.Sieve.$Closure$64 $_obj = new pppp.lib.stream.examples.Sieve.$Closure$64((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.prime);
                
            }
            
            // constructor just for allocation
            public $Closure$64(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48929 =
                      (("Prime: ") + ((x10.core.Int.$box(this.
                                                           prime))));
                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
return t48929;
                }
                
                public int prime;
                
                public $Closure$64(final int prime) { {
                                                             this.prime = prime;
                                                         }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$65 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$65> $RTT = x10.rtt.StaticFunType.<$Closure$65> make(
            $Closure$65.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lib.stream.Stream.$RTT, x10.rtt.Types.INT))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Sieve.$Closure$65 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$65.class + " calling"); } 
                $_obj.prime = $deserializer.readInt();
                $_obj.nums = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.examples.Sieve.$Closure$65 $_obj = new pppp.lib.stream.examples.Sieve.$Closure$65((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.prime);
                if (nums instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.nums);
                } else {
                $serializer.write(this.nums);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$65(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lib.stream.Stream
              $apply$G(){return $apply();}
            
                
                public pppp.lib.stream.Stream
                  $apply(
                  ){
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final pppp.lib.stream.Stream t48931 =
                      pppp.lib.stream.examples.Sieve.sieve__1$1x10$lang$Int$2((int)(this.
                                                                                      prime),
                                                                              ((pppp.lib.stream.Stream)(this.
                                                                                                          nums)));
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final pppp.lib.stream.Stream t48932 =
                      pppp.lib.stream.examples.Sieve.primes__0$1x10$lang$Int$2(((pppp.lib.stream.Stream)(t48931)));
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
return t48932;
                }
                
                public int prime;
                public pppp.lib.stream.Stream<x10.core.Int> nums;
                
                public $Closure$65(final int prime,
                                   final pppp.lib.stream.Stream<x10.core.Int> nums, __1$1x10$lang$Int$2 $dummy) { {
                                                                                                                         this.prime = prime;
                                                                                                                         this.nums = ((pppp.lib.stream.Stream)(nums));
                                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Int$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$66 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$66> $RTT = x10.rtt.StaticFunType.<$Closure$66> make(
            $Closure$66.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Sieve.$Closure$66 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$66.class + " calling"); } 
                $_obj.nums = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.examples.Sieve.$Closure$66 $_obj = new pppp.lib.stream.examples.Sieve.$Closure$66((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (nums instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.nums);
                } else {
                $serializer.write(this.nums);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$66(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48936 =
                      (("Primes(") + (this.
                                        nums));
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48937 =
                      ((t48936) + (") throws exception."));
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
return t48937;
                }
                
                public pppp.lib.stream.Stream<x10.core.Int> nums;
                
                public $Closure$66(final pppp.lib.stream.Stream<x10.core.Int> nums, __0$1x10$lang$Int$2 $dummy) { {
                                                                                                                         this.nums = ((pppp.lib.stream.Stream)(nums));
                                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Int$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$67 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$67> $RTT = x10.rtt.StaticFunType.<$Closure$67> make(
            $Closure$67.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Sieve.$Closure$67 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$67.class + " calling"); } 
                $_obj.prime = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.examples.Sieve.$Closure$67 $_obj = new pppp.lib.stream.examples.Sieve.$Closure$67((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.prime);
                
            }
            
            // constructor just for allocation
            public $Closure$67(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48939 =
                      (("Starting sieve(") + ((x10.core.Int.$box(this.
                                                                   prime))));
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final java.lang.String t48940 =
                      ((t48939) + (")"));
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
return t48940;
                }
                
                public int prime;
                
                public $Closure$67(final int prime) { {
                                                             this.prime = prime;
                                                         }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$68 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$68> $RTT = x10.rtt.StaticVoidFunType.<$Closure$68> make(
            $Closure$68.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Sieve.$Closure$68 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$68.class + " calling"); } 
                $_obj.prime = $deserializer.readInt();
                $_obj.nums = $deserializer.readRef();
                $_obj.s = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.examples.Sieve.$Closure$68 $_obj = new pppp.lib.stream.examples.Sieve.$Closure$68((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.prime);
                if (nums instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.nums);
                } else {
                $serializer.write(this.nums);
                }
                if (s instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.s);
                } else {
                $serializer.write(this.s);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$68(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
try {{
                        
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final x10.core.fun.Fun_0_0 t48941 =
                          ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.examples.Sieve.$Closure$67(((int)(this.
                                                                                                          prime)))));
                        
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
pppp.util.Logger.log__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t48941)));
                        
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
try {{
                            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
while (true) {
                                
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final int item =
                                  x10.core.Int.$unbox(((pppp.lib.stream.Stream<x10.core.Int>)this.
                                                                                               nums).$apply$G());
                                
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final int t48942 =
                                  ((item) % (((int)(this.
                                                      prime))));
                                
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
final boolean t48943 =
                                  ((int) t48942) !=
                                ((int) 0);
                                
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
if (t48943) {
                                    
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
((pppp.util.BBuffer<x10.core.Int>)this.
                                                                                                                                                                                                                         s).$set__0pppp$util$BBuffer$$T(x10.core.Int.$box(item));
                                }
                            }
                        }}catch (final pppp.lib.stream.StreamClosedException id$19075) {
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
((pppp.lib.stream.BoundedStreamImp<x10.core.Int>)this.
                                                                                                                                                                                                                                s).close();
                        }
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Sieve.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public int prime;
                public pppp.lib.stream.Stream<x10.core.Int> nums;
                public pppp.lib.stream.BoundedStreamImp<x10.core.Int> s;
                
                public $Closure$68(final int prime,
                                   final pppp.lib.stream.Stream<x10.core.Int> nums,
                                   final pppp.lib.stream.BoundedStreamImp<x10.core.Int> s, __1$1x10$lang$Int$2__2$1x10$lang$Int$2 $dummy) { {
                                                                                                                                                   this.prime = prime;
                                                                                                                                                   this.nums = ((pppp.lib.stream.Stream)(nums));
                                                                                                                                                   this.s = ((pppp.lib.stream.BoundedStreamImp)(s));
                                                                                                                                               }}
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Int$2__2$1x10$lang$Int$2 {}
                
            }
            
        
        }
        
        