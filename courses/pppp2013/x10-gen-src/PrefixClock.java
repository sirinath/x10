
@x10.runtime.impl.java.X10Generated public class PrefixClock extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<PrefixClock> $RTT = x10.rtt.NamedType.<PrefixClock> make(
    "PrefixClock", PrefixClock.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(PrefixClock $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + PrefixClock.class + " calling"); } 
        $_obj.P = $deserializer.readInt();
        $_obj.a = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        PrefixClock $_obj = new PrefixClock((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write(this.P);
        if (a instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.a);
        } else {
        $serializer.write(this.a);
        }
        
    }
    
    // constructor just for allocation
    public PrefixClock(final java.lang.System[] $dummy) { 
    }
    
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
public int P;
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
public x10.core.Rail<x10.core.Int> a;
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
// creation method for java code (1-phase java constructor)
        public PrefixClock(final int p){this((java.lang.System[]) null);
                                            PrefixClock$$init$S(p);}
        
        // constructor for non-virtual call
        final public PrefixClock PrefixClock$$init$S(final int p) { {
                                                                           
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
;
                                                                           
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"

                                                                           
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
this.__fieldInitializers_PrefixClock();
                                                                           
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
this.P = p;
                                                                           
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.util.Random r =
                                                                             ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S(((long)(2156L)))));
                                                                           
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49115 =
                                                                             P;
                                                                           
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49117 =
                                                                             ((long)(((int)(t49115))));
                                                                           
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.core.fun.Fun_0_1 t49118 =
                                                                             ((x10.core.fun.Fun_0_1)(new PrefixClock.$Closure$69(r)));
                                                                           
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.core.Rail t49119 =
                                                                             ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, t49117,
                                                                                                                              ((x10.core.fun.Fun_0_1)(t49118)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                           
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
this.a = ((x10.core.Rail)(t49119));
                                                                       }
                                                                       return this;
                                                                       }
        
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
public void
                                                                                                                                        prefixSum(
                                                                                                                                        ){
            {
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
x10.lang.Clock x10$__var20 =
                  null;
                {
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
x10.lang.Runtime.ensureNotInAtomic();
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.lang.FinishState x10$__var21 =
                      x10.lang.Runtime.startFinish();
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
try {{
                        {
                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
try {{
                                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.lang.Clock x10$__var19 =
                                  x10.lang.Clock.make();
                                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
x10$__var20 = x10$__var19;
                                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int i49078min49079 =
                                  1;
                                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49120 =
                                  P;
                                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int i49078max49080 =
                                  ((t49120) - (((int)(1))));
                                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
int i49208 =
                                  i49078min49079;
                                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
for (;
                                                                                                                                                                   true;
                                                                                                                                                                   ) {
                                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49209 =
                                      i49208;
                                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final boolean t49210 =
                                      ((t49209) <= (((int)(i49078max49080))));
                                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
if (!(t49210)) {
                                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
break;
                                    }
                                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49205 =
                                      i49208;
                                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
x10.lang.Runtime.runAsync__0$1x10$lang$Clock$2(((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.lang.Clock> makeRailFromJavaArray(x10.lang.Clock.$RTT, new x10.lang.Clock[] {x10$__var19}))),
                                                                                                                                                                                                                 ((x10.core.fun.VoidFun_0_0)(new PrefixClock.$Closure$70(this,
                                                                                                                                                                                                                                                                         t49205,
                                                                                                                                                                                                                                                                         P,
                                                                                                                                                                                                                                                                         a, (PrefixClock.$Closure$70.__3$1x10$lang$Int$2) null))));
                                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49206 =
                                      i49208;
                                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49207 =
                                      ((t49206) + (((int)(1))));
                                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
i49208 = t49207;
                                }
                            }}finally {{
                                  
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
if (((x10$__var20) != (null))) {
                                      
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
x10$__var20.drop();
                                  }
                              }}
                            }
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
throw new java.lang.RuntimeException();
                        }finally {{
                             
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var21)));
                         }}
                    }
                }
            }
            
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
public void
                                                                                                                                            print(
                                                                                                                                            final java.lang.String prefix){
                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.io.Printer t49153 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
t49153.println(((java.lang.Object)(prefix)));
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long i49094min49223 =
                  0L;
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.core.Rail t49224 =
                  ((x10.core.Rail)(a));
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49225 =
                  ((x10.core.Rail<x10.core.Int>)t49224).
                    size;
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long i49094max49226 =
                  ((t49225) - (((long)(1L))));
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
long i49220 =
                  i49094min49223;
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49221 =
                      i49220;
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final boolean t49222 =
                      ((t49221) <= (((long)(i49094max49226))));
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
if (!(t49222)) {
                        
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
break;
                    }
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long p49217 =
                      i49220;
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.io.Printer t49211 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final java.lang.String t49212 =
                      ((" a(") + ((x10.core.Long.$box(p49217))));
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final java.lang.String t49213 =
                      ((t49212) + (")= "));
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.core.Rail t49214 =
                      ((x10.core.Rail)(a));
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49215 =
                      ((int[])t49214.value)[(int)p49217];
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final java.lang.String t49216 =
                      ((t49213) + ((x10.core.Int.$box(t49215))));
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
t49211.print(((java.lang.String)(t49216)));
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49218 =
                      i49220;
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49219 =
                      ((t49218) + (((long)(1L))));
                    
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
i49220 = t49219;
                }
            }
            
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
            private static final long serialVersionUID = 1L;
            public static void main(java.lang.String[] args)  {
            // start native runtime
            new $Main().start(args);
            }
            
            // called by native runtime inside main x10 thread
            public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
            // call the original app-main method
            PrefixClock.main(args);
            }
            }
            
            // the original app-main method
            public static void main(final x10.core.Rail<java.lang.String> args)  {
                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49167 =
                  ((x10.core.Rail<java.lang.String>)args).
                    size;
                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final boolean t49169 =
                  ((t49167) < (((long)(1L))));
                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
if (t49169) {
                    
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.io.Printer t49168 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
t49168.println(((java.lang.Object)("Usage: <exec> <p:Int>")));
                }
                
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final java.lang.String t49170 =
                  ((java.lang.String[])args.value)[(int)0L];
                
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int P =
                  java.lang.Integer.parseInt(t49170);
                
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
assert pppp.util.Utils.powerOf2$O((int)(P)): " Must run with power of 2 activities.";
                
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final PrefixClock s =
                  ((PrefixClock)(new PrefixClock((java.lang.System[]) null).PrefixClock$$init$S(((int)(P)))));
                
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
s.print(((java.lang.String)("Initial:")));
                
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
s.prefixSum();
                
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
s.print(((java.lang.String)("Final:")));
            }
            
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final public PrefixClock
                                                                                                                                           PrefixClock$$this$PrefixClock(
                                                                                                                                           ){
                
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
return PrefixClock.this;
            }
            
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final public void
                                                                                                                                           __fieldInitializers_PrefixClock(
                                                                                                                                           ){
                
            }
            
            @x10.runtime.impl.java.X10Generated final public static class $Closure$69 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$69> $RTT = x10.rtt.StaticFunType.<$Closure$69> make(
                $Closure$69.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.INT)}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(PrefixClock.$Closure$69 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$69.class + " calling"); } 
                    $_obj.r = $deserializer.readRef();
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    PrefixClock.$Closure$69 $_obj = new PrefixClock.$Closure$69((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    if (r instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.r);
                    } else {
                    $serializer.write(this.r);
                    }
                    
                }
                
                // constructor just for allocation
                public $Closure$69(final java.lang.System[] $dummy) { 
                }
                // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                return x10.core.Int.$box($apply$O(x10.core.Long.$unbox(a1)));
                }
                // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1) {
                return $apply$O(x10.core.Long.$unbox(a1));
                }
                
                    
                    public int
                      $apply$O(
                      final long i){
                        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49116 =
                          this.
                            r.nextInt$O((int)(100));
                        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
return t49116;
                    }
                    
                    public x10.util.Random r;
                    
                    public $Closure$69(final x10.util.Random r) { {
                                                                         this.r = ((x10.util.Random)(r));
                                                                     }}
                    
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$70 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$70> $RTT = x10.rtt.StaticVoidFunType.<$Closure$70> make(
                $Closure$70.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(PrefixClock.$Closure$70 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$70.class + " calling"); } 
                    $_obj.out$$ = $deserializer.readRef();
                    $_obj.t49205 = $deserializer.readInt();
                    $_obj.P = $deserializer.readInt();
                    $_obj.a = $deserializer.readRef();
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    PrefixClock.$Closure$70 $_obj = new PrefixClock.$Closure$70((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                    } else {
                    $serializer.write(this.out$$);
                    }
                    $serializer.write(this.t49205);
                    $serializer.write(this.P);
                    if (a instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.a);
                    } else {
                    $serializer.write(this.a);
                    }
                    
                }
                
                // constructor just for allocation
                public $Closure$70(final java.lang.System[] $dummy) { 
                }
                
                    
                    public void
                      $apply(
                      ){
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
try {{
                            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
int b49203 =
                              this.
                                t49205;
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
int gap49204 =
                              0;
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
int size49199 =
                              1;
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
for (;
                                                                                                                                                               true;
                                                                                                                                                               ) {
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49200 =
                                  size49199;
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49201 =
                                  this.
                                    P;
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final boolean t49202 =
                                  ((t49200) < (((int)(t49201))));
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
if (!(t49202)) {
                                    
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
break;
                                }
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49173 =
                                  gap49204;
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49174 =
                                  b49203;
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49175 =
                                  ((long)(((int)(t49174))));
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49176 =
                                  ((t49175) % (((long)(2L))));
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final boolean t49177 =
                                  ((long) t49176) ==
                                ((long) 0L);
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
int t49178 =
                                   0;
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
if (t49177) {
                                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
t49178 = size49199;
                                } else {
                                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
t49178 = 0;
                                }
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49179 =
                                  t49178;
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49180 =
                                  ((t49173) + (((int)(t49179))));
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
gap49204 = t49180;
                                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49181 =
                                  size49199;
                                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49182 =
                                  gap49204;
                                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49183 =
                                  ((t49181) - (((int)(t49182))));
                                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final boolean t49184 =
                                  ((t49183) > (((int)(0))));
                                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
if (t49184) {
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.core.Rail x49185 =
                                      ((x10.core.Rail)(this.
                                                         a));
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long y49186 =
                                      ((long)(((int)(this.
                                                       t49205))));
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final x10.core.Rail t49187 =
                                      ((x10.core.Rail)(this.
                                                         a));
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49188 =
                                      size49199;
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49189 =
                                      gap49204;
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49190 =
                                      ((t49188) - (((int)(t49189))));
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49191 =
                                      ((this.
                                          t49205) - (((int)(t49190))));
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final long t49192 =
                                      ((long)(((int)(t49191))));
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int z49193 =
                                      ((int[])t49187.value)[(int)t49192];
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
int ret49194 =
                                       0;
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49171 =
                                      ((int[])x49185.value)[(int)y49186];
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int r49172 =
                                      ((t49171) + (((int)(z49193))));
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
((int[])x49185.value)[(int)y49186] = r49172;
                                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
ret49194 = r49172;
                                }
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
x10.lang.Clock.advanceAll();
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49195 =
                                  b49203;
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49196 =
                                  ((t49195) >> (int)(((long)(1L))));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
b49203 = t49196;
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49197 =
                                  size49199;
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
final int t49198 =
                                  ((t49197) * (((int)(2))));
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
size49199 = t49198;
                            }
                        }}catch (java.lang.Error __lowerer__var__0__) {
                            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
throw __lowerer__var__0__;
                        }catch (java.lang.Throwable __lowerer__var__1__) {
                            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/PrefixClock.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                        }
                    }
                    
                    public PrefixClock out$$;
                    public int t49205;
                    public int P;
                    public x10.core.Rail<x10.core.Int> a;
                    
                    public $Closure$70(final PrefixClock out$$,
                                       final int t49205,
                                       final int P,
                                       final x10.core.Rail<x10.core.Int> a, __3$1x10$lang$Int$2 $dummy) { {
                                                                                                                 this.out$$ = out$$;
                                                                                                                 this.t49205 = t49205;
                                                                                                                 this.P = P;
                                                                                                                 this.a = ((x10.core.Rail)(a));
                                                                                                             }}
                    // synthetic type for parameter mangling
                    public static final class __3$1x10$lang$Int$2 {}
                    
                }
                
            
            }
            
            