package pppp.lib.stream.examples;


@x10.runtime.impl.java.X10Generated public class Hamming extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Hamming> $RTT = x10.rtt.NamedType.<Hamming> make(
    "pppp.lib.stream.examples.Hamming", Hamming.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Hamming $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Hamming.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.examples.Hamming $_obj = new pppp.lib.stream.examples.Hamming((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Hamming(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
public static pppp.lib.stream.Stream
                                                                                                                                                             omerge__1$1x10$lang$Int$2__2$1x10$lang$Int$2(
                                                                                                                                                             final int n_,
                                                                                                                                                             final pppp.lib.stream.Stream<x10.core.Int> a,
                                                                                                                                                             final pppp.lib.stream.Stream<x10.core.Int> b){
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47014 =
              (("omerge(") + (a));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47015 =
              ((t47014) + (","));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47016 =
              ((t47015) + (b));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47017 =
              ((t47016) + (")"));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.BoundedStreamImp s =
              ((pppp.lib.stream.BoundedStreamImp)(new pppp.lib.stream.BoundedStreamImp<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).pppp$lib$stream$BoundedStreamImp$$init$S(t47017)));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new pppp.lib.stream.examples.Hamming.$Closure$47(s,
                                                                                                                                                                                                                                                                      a,
                                                                                                                                                                                                                                                                      b,
                                                                                                                                                                                                                                                                      n_, (pppp.lib.stream.examples.Hamming.$Closure$47.__0$1x10$lang$Int$2__1$1x10$lang$Int$2__2$1x10$lang$Int$2) null))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
return s;
        }
        
        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
public static pppp.lib.stream.Stream
                                                                                                                                                             kmult__1$1x10$lang$Int$2(
                                                                                                                                                             final int k,
                                                                                                                                                             final pppp.lib.stream.Stream<x10.core.Int> a){
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47033 =
              (("kmult(") + ((x10.core.Int.$box(k))));
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47034 =
              ((t47033) + (","));
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47035 =
              ((t47034) + (a));
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47036 =
              ((t47035) + (")"));
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.BoundedStreamImp s =
              ((pppp.lib.stream.BoundedStreamImp)(new pppp.lib.stream.BoundedStreamImp<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).pppp$lib$stream$BoundedStreamImp$$init$S(t47036)));
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new pppp.lib.stream.examples.Hamming.$Closure$48(s,
                                                                                                                                                                                                                                                                      a,
                                                                                                                                                                                                                                                                      k, (pppp.lib.stream.examples.Hamming.$Closure$48.__0$1x10$lang$Int$2__1$1x10$lang$Int$2) null))));
            
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
return s;
        }
        
        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
public static pppp.lib.stream.Stream
                                                                                                                                                             hamming(
                                                                                                                                                             final int n){
            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final x10.core.fun.Fun_0_1 t47042 =
              ((x10.core.fun.Fun_0_1)(new pppp.lib.stream.examples.Hamming.$Closure$49()));
            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final x10.core.Rail hx =
              new x10.core.Rail<pppp.lib.stream.Spring<x10.core.Int>>(x10.rtt.ParameterizedType.make(pppp.lib.stream.Spring.$RTT, x10.rtt.Types.INT), ((long)(4L)),
                                                                      ((x10.core.fun.Fun_0_1)(t47042)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null);
            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47049 =
              ((n) - (((int)(1))));
            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Spring t47043 =
              ((pppp.lib.stream.Spring[])hx.value)[(int)0L];
            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Stream t47050 =
              pppp.lib.stream.examples.Hamming.kmult__1$1x10$lang$Int$2((int)(2),
                                                                        ((pppp.lib.stream.Stream)(t47043)));
            
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47046 =
              ((n) - (((int)(1))));
            
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Spring t47044 =
              ((pppp.lib.stream.Spring[])hx.value)[(int)1L];
            
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Stream t47047 =
              pppp.lib.stream.examples.Hamming.kmult__1$1x10$lang$Int$2((int)(3),
                                                                        ((pppp.lib.stream.Stream)(t47044)));
            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Spring t47045 =
              ((pppp.lib.stream.Spring[])hx.value)[(int)2L];
            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Stream t47048 =
              pppp.lib.stream.examples.Hamming.kmult__1$1x10$lang$Int$2((int)(5),
                                                                        ((pppp.lib.stream.Stream)(t47045)));
            
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Stream t47051 =
              pppp.lib.stream.examples.Hamming.omerge__1$1x10$lang$Int$2__2$1x10$lang$Int$2((int)(t47046),
                                                                                            ((pppp.lib.stream.Stream)(t47047)),
                                                                                            ((pppp.lib.stream.Stream)(t47048)));
            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Stream t47052 =
              pppp.lib.stream.examples.Hamming.omerge__1$1x10$lang$Int$2__2$1x10$lang$Int$2((int)(t47049),
                                                                                            ((pppp.lib.stream.Stream)(t47050)),
                                                                                            ((pppp.lib.stream.Stream)(t47051)));
            
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.FBy t47053 =
              ((pppp.lib.stream.FBy)(new pppp.lib.stream.FBy<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).pppp$lib$stream$FBy$$init$S((x10.core.Int.$box(1)),
                                                                                                                                                     t47052, (pppp.lib.stream.FBy.__0pppp$lib$stream$FBy$$T__1$1pppp$lib$stream$FBy$$T$2) null)));
            
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
pppp.lib.stream.XDucer.<x10.core.Int,
            pppp.lib.stream.Spring<x10.core.Int>> copy__0$1pppp$lib$stream$XDucer$$T$2__1$1pppp$lib$stream$XDucer$$X$2(x10.rtt.Types.INT, x10.rtt.ParameterizedType.make(pppp.lib.stream.Spring.$RTT, x10.rtt.Types.INT), ((pppp.lib.stream.Stream)(t47053)),
                                                                                                                       ((x10.core.Rail)(hx)));
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Spring t47054 =
              ((pppp.lib.stream.Spring[])hx.value)[(int)3L];
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
return t47054;
        }
        
        
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
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
            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final long t47055 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final boolean t47057 =
              ((t47055) < (((long)(1L))));
            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
if (t47057) {
                
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final x10.io.Printer t47056 =
                  ((x10.io.Printer)(x10.io.Console.get$ERR()));
                
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
t47056.print(((java.lang.String)("Usage: Hamming <N>\n")));
                
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
return;
            }
            {
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
x10.lang.Runtime.ensureNotInAtomic();
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final x10.lang.FinishState x10$__var13 =
                  x10.lang.Runtime.startFinish();
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
try {{
                    {
                        
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final x10.core.fun.Fun_0_0 t47058 =
                          ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.examples.Hamming.$Closure$50()));
                        
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
pppp.util.Logger.log__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t47058)));
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47059 =
                          ((java.lang.String[])args.value)[(int)0L];
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47060 =
                          java.lang.Integer.parseInt(t47059);
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.Stream t47061 =
                          pppp.lib.stream.examples.Hamming.hamming((int)(t47060));
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
pppp.lib.stream.XDucer.<x10.core.Int> print__0$1pppp$lib$stream$XDucer$$T$2(x10.rtt.Types.INT, ((pppp.lib.stream.Stream)(t47061)));
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var13)));
                 }}
                }
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final x10.io.Printer t47062 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
t47062.println(((java.lang.Object)("...done.")));
            }
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final public pppp.lib.stream.examples.Hamming
                                                                                                                                                            pppp$lib$stream$examples$Hamming$$this$pppp$lib$stream$examples$Hamming(
                                                                                                                                                            ){
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
return pppp.lib.stream.examples.Hamming.this;
        }
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
// creation method for java code (1-phase java constructor)
        public Hamming(){this((java.lang.System[]) null);
                             pppp$lib$stream$examples$Hamming$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.examples.Hamming pppp$lib$stream$examples$Hamming$$init$S() { {
                                                                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
;
                                                                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"

                                                                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
this.__fieldInitializers_pppp_lib_stream_examples_Hamming();
                                                                                                      }
                                                                                                      return this;
                                                                                                      }
        
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final public void
                                                                                                                                                            __fieldInitializers_pppp_lib_stream_examples_Hamming(
                                                                                                                                                            ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$47 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$47> $RTT = x10.rtt.StaticVoidFunType.<$Closure$47> make(
            $Closure$47.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Hamming.$Closure$47 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$47.class + " calling"); } 
                $_obj.s = $deserializer.readRef();
                $_obj.a = $deserializer.readRef();
                $_obj.b = $deserializer.readRef();
                $_obj.n_ = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.examples.Hamming.$Closure$47 $_obj = new pppp.lib.stream.examples.Hamming.$Closure$47((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (s instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.s);
                } else {
                $serializer.write(this.s);
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
                $serializer.write(this.n_);
                
            }
            
            // constructor just for allocation
            public $Closure$47(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
try {{
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47018 =
                          (("Starting ") + (this.
                                              s));
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
pppp.util.Logger.log(((java.lang.String)(t47018)));
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
int av =
                          x10.core.Int.$unbox(((pppp.lib.stream.Stream<x10.core.Int>)this.
                                                                                       a).$apply$G());
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
int bv =
                          x10.core.Int.$unbox(((pppp.lib.stream.Stream<x10.core.Int>)this.
                                                                                       b).$apply$G());
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
try {{
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
int n =
                              this.
                                n_;
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
while (true) {
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47019 =
                                  n;
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47020 =
                                  ((t47019) - (((int)(1))));
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47021 =
                                  n = t47020;
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47022 =
                                  ((t47021) + (((int)(1))));
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final long t47023 =
                                  ((long)(((int)(t47022))));
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final boolean t47032 =
                                  ((t47023) > (((long)(0L))));
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
if (!(t47032)) {
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
break;
                                }
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47063 =
                                  av;
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47064 =
                                  bv;
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int item47065 =
                                  pppp.util.Utils.min$O((int)(t47063),
                                                        (int)(t47064));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
((pppp.util.BBuffer<x10.core.Int>)this.
                                                                                                                                                                                                                       s).$set__0pppp$util$BBuffer$$T(x10.core.Int.$box(item47065));
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47066 =
                                  av;
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final boolean t47067 =
                                  ((int) t47066) ==
                                ((int) item47065);
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
if (t47067) {
                                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47068 =
                                      x10.core.Int.$unbox(((pppp.lib.stream.Stream<x10.core.Int>)this.
                                                                                                   a).$apply$G());
                                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
av = t47068;
                                }
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47069 =
                                  bv;
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final boolean t47070 =
                                  ((int) t47069) ==
                                ((int) item47065);
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
if (t47070) {
                                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47071 =
                                      x10.core.Int.$unbox(((pppp.lib.stream.Stream<x10.core.Int>)this.
                                                                                                   b).$apply$G());
                                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
bv = t47071;
                                }
                            }
                        }}catch (final pppp.lib.stream.StreamClosedException x) {
                            
                        }finally {{
                             
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
((pppp.lib.stream.BoundedStreamImp<x10.core.Int>)this.
                                                                                                                                                                                                                                   s).close();
                         }}
                        }}catch (java.lang.Error __lowerer__var__0__) {
                            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
throw __lowerer__var__0__;
                        }catch (java.lang.Throwable __lowerer__var__1__) {
                            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                        }
                    }
                
                public pppp.lib.stream.BoundedStreamImp<x10.core.Int> s;
                public pppp.lib.stream.Stream<x10.core.Int> a;
                public pppp.lib.stream.Stream<x10.core.Int> b;
                public int n_;
                
                public $Closure$47(final pppp.lib.stream.BoundedStreamImp<x10.core.Int> s,
                                   final pppp.lib.stream.Stream<x10.core.Int> a,
                                   final pppp.lib.stream.Stream<x10.core.Int> b,
                                   final int n_, __0$1x10$lang$Int$2__1$1x10$lang$Int$2__2$1x10$lang$Int$2 $dummy) { {
                                                                                                                            this.s = ((pppp.lib.stream.BoundedStreamImp)(s));
                                                                                                                            this.a = ((pppp.lib.stream.Stream)(a));
                                                                                                                            this.b = ((pppp.lib.stream.Stream)(b));
                                                                                                                            this.n_ = n_;
                                                                                                                        }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Int$2__1$1x10$lang$Int$2__2$1x10$lang$Int$2 {}
                
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$48 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$48> $RTT = x10.rtt.StaticVoidFunType.<$Closure$48> make(
                $Closure$48.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Hamming.$Closure$48 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$48.class + " calling"); } 
                    $_obj.s = $deserializer.readRef();
                    $_obj.a = $deserializer.readRef();
                    $_obj.k = $deserializer.readInt();
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    pppp.lib.stream.examples.Hamming.$Closure$48 $_obj = new pppp.lib.stream.examples.Hamming.$Closure$48((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    if (s instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.s);
                    } else {
                    $serializer.write(this.s);
                    }
                    if (a instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.a);
                    } else {
                    $serializer.write(this.a);
                    }
                    $serializer.write(this.k);
                    
                }
                
                // constructor just for allocation
                public $Closure$48(final java.lang.System[] $dummy) { 
                }
                
                    
                    public void
                      $apply(
                      ){
                        
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
try {{
                            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
try {{
                                
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47037 =
                                  (("Starting ") + (this.
                                                      s));
                                
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
pppp.util.Logger.log(((java.lang.String)(t47037)));
                                
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
while (true) {
                                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int t47038 =
                                      x10.core.Int.$unbox(((pppp.lib.stream.Stream<x10.core.Int>)this.
                                                                                                   a).$apply$G());
                                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final int item =
                                      ((this.
                                          k) * (((int)(t47038))));
                                    
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
((pppp.util.BBuffer<x10.core.Int>)this.
                                                                                                                                                                                                                           s).$set__0pppp$util$BBuffer$$T(x10.core.Int.$box(item));
                                }
                            }}catch (final pppp.lib.stream.StreamClosedException x) {
                                
                            }finally {{
                                 
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
((pppp.lib.stream.BoundedStreamImp<x10.core.Int>)this.
                                                                                                                                                                                                                                       s).close();
                             }}
                            }}catch (java.lang.Error __lowerer__var__0__) {
                                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
throw __lowerer__var__0__;
                            }catch (java.lang.Throwable __lowerer__var__1__) {
                                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                            }
                        }
                    
                    public pppp.lib.stream.BoundedStreamImp<x10.core.Int> s;
                    public pppp.lib.stream.Stream<x10.core.Int> a;
                    public int k;
                    
                    public $Closure$48(final pppp.lib.stream.BoundedStreamImp<x10.core.Int> s,
                                       final pppp.lib.stream.Stream<x10.core.Int> a,
                                       final int k, __0$1x10$lang$Int$2__1$1x10$lang$Int$2 $dummy) { {
                                                                                                            this.s = ((pppp.lib.stream.BoundedStreamImp)(s));
                                                                                                            this.a = ((pppp.lib.stream.Stream)(a));
                                                                                                            this.k = k;
                                                                                                        }}
                    // synthetic type for parameter mangling
                    public static final class __0$1x10$lang$Int$2__1$1x10$lang$Int$2 {}
                    
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$49 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$49> $RTT = x10.rtt.StaticFunType.<$Closure$49> make(
                    $Closure$49.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.ParameterizedType.make(pppp.lib.stream.BoundedStreamImp.$RTT, x10.rtt.Types.INT))}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Hamming.$Closure$49 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$49.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        pppp.lib.stream.examples.Hamming.$Closure$49 $_obj = new pppp.lib.stream.examples.Hamming.$Closure$49((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$49(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public pppp.lib.stream.BoundedStreamImp
                          $apply(
                          final long i){
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47039 =
                              ((" hamming(") + ((x10.core.Long.$box(i))));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final java.lang.String t47040 =
                              ((t47039) + (")"));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
final pppp.lib.stream.BoundedStreamImp t47041 =
                              ((pppp.lib.stream.BoundedStreamImp)(new pppp.lib.stream.BoundedStreamImp<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).pppp$lib$stream$BoundedStreamImp$$init$S(t47040)));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
return t47041;
                        }
                        
                        public $Closure$49() { {
                                                      
                                                  }}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$50 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$50> $RTT = x10.rtt.StaticFunType.<$Closure$50> make(
                    $Closure$50.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.examples.Hamming.$Closure$50 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$50.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        pppp.lib.stream.examples.Hamming.$Closure$50 $_obj = new pppp.lib.stream.examples.Hamming.$Closure$50((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$50(final java.lang.System[] $dummy) { 
                    }
                    // bridge for method abstract public ()=>U.operator()():U
                    public java.lang.String
                      $apply$G(){return $apply();}
                    
                        
                        public java.lang.String
                          $apply(
                          ){
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/examples/Hamming.x10"
return "Starting hamming.";
                        }
                        
                        public $Closure$50() { {
                                                      
                                                  }}
                        
                    }
                    
                
                }
                
                