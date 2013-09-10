
@x10.runtime.impl.java.X10Generated public class MontyPiAcc extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<MontyPiAcc> $RTT = x10.rtt.NamedType.<MontyPiAcc> make(
    "MontyPiAcc", MontyPiAcc.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPiAcc $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + MontyPiAcc.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        MontyPiAcc $_obj = new MontyPiAcc((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public MontyPiAcc(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        MontyPiAcc.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> s)  {
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
assert ((((x10.core.Rail<java.lang.String>)s).
                                                                                                                                                   size) >= (((long)(1L)))): "Usage: MontyAcc [<number of points per place:Int>]";
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42053 =
              ((x10.core.Rail<java.lang.String>)s).
                size;
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final boolean t42055 =
              ((t42053) > (((long)(0L))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
int t42056 =
               0;
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
if (t42055) {
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final java.lang.String t42054 =
                  ((java.lang.String[])s.value)[(int)0L];
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
t42056 = java.lang.Integer.parseInt(t42054);
            } else {
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
t42056 = 10000;
            }
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final int N =
              t42056;
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42057 =
              x10.lang.System.nanoTime$O();
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42058 =
              (-(t42057));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
double start =
              ((double)(long)(((long)(t42058))));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final MontyPiAcc.Anonymous$369 reducer =
              ((MontyPiAcc.Anonymous$369)(new MontyPiAcc.Anonymous$369((java.lang.System[]) null).MontyPiAcc$Anonymous$369$$init$S()));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.lang.Accumulator result =
              ((x10.lang.Accumulator)(new x10.lang.Accumulator<x10.core.Double>((java.lang.System[]) null, x10.rtt.Types.DOUBLE).x10$lang$Accumulator$$init$S(((x10.lang.Reducible<x10.core.Double>)(reducer)), (x10.lang.Accumulator.__0$1x10$lang$Accumulator$$T$2) null)));
            {
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
x10.lang.Runtime.ensureNotInAtomic();
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.lang.FinishState x10$__var1 =
                  x10.lang.Runtime.startFinish();
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
try {{
                    {
                        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.regionarray.Dist t42060 =
                          ((x10.regionarray.Dist)(x10.regionarray.Dist.makeUnique()));
                        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.lang.PlaceGroup t42061 =
                          t42060.places();
                        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.lang.Iterator p42052 =
                          ((x10.lang.Iterable<x10.lang.Place>)t42061).iterator();
                        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
for (;
                                                                                                                                                          true;
                                                                                                                                                          ) {
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final boolean t42076 =
                              ((x10.lang.Iterator<x10.lang.Place>)p42052).hasNext$O();
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
if (!(t42076)) {
                                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
break;
                            }
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.lang.Place p42114 =
                              ((x10.lang.Place)(((x10.lang.Iterator<x10.lang.Place>)p42052).next$G()));
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
x10.lang.Runtime.runAsync(((x10.lang.Place)(p42114)),
                                                                                                                                                                                   ((x10.core.fun.VoidFun_0_0)(new MontyPiAcc.$Closure$2(N,
                                                                                                                                                                                                                                         result, (MontyPiAcc.$Closure$2.__1$1x10$lang$Double$2) null))),
                                                                                                                                                                                   ((x10.lang.Runtime.Profile)(null)));
                        }
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var1)));
                 }}
                }
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42077 =
              ((double)(int)(((int)(4))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42078 =
              x10.core.Double.$unbox(((x10.lang.Accumulator<x10.core.Double>)result).$apply$G());
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42082 =
              ((t42077) * (((double)(t42078))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42079 =
              ((long)(((int)(N))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42080 =
              x10.lang.Place.get$MAX_PLACES();
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42081 =
              ((t42079) * (((long)(t42080))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42083 =
              ((double)(long)(((long)(t42081))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double pi =
              ((t42082) / (((double)(t42083))));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42085 =
              start;
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42084 =
              x10.lang.System.nanoTime$O();
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42086 =
              ((double)(long)(((long)(t42084))));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42087 =
              ((t42085) + (((double)(t42086))));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
start = t42087;
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.io.Printer t42095 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final java.lang.String t42088 =
              (("The value of pi is ") + ((x10.core.Double.$box(pi))));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final java.lang.String t42092 =
              ((t42088) + (" (t="));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42090 =
              start;
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final int t42089 =
              1000000;
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42091 =
              ((double)(int)(((int)(t42089))));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42093 =
              ((t42090) / (((double)(t42091))));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final java.lang.String t42094 =
              ((t42092) + ((x10.core.Double.$box(t42093))));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final java.lang.String t42096 =
              ((t42094) + (" ms)."));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
t42095.println(((java.lang.Object)(t42096)));
            }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final public MontyPiAcc
                                                                                                                                      MontyPiAcc$$this$MontyPiAcc(
                                                                                                                                      ){
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
return MontyPiAcc.this;
        }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
// creation method for java code (1-phase java constructor)
        public MontyPiAcc(){this((java.lang.System[]) null);
                                MontyPiAcc$$init$S();}
        
        // constructor for non-virtual call
        final public MontyPiAcc MontyPiAcc$$init$S() { {
                                                              
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
;
                                                              
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"

                                                              
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
this.__fieldInitializers_MontyPiAcc();
                                                          }
                                                          return this;
                                                          }
        
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final public void
                                                                                                                                      __fieldInitializers_MontyPiAcc(
                                                                                                                                      ){
            
        }
        
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
@x10.runtime.impl.java.X10Generated final public static class Anonymous$369 extends x10.core.Ref implements x10.lang.Reducible, x10.serialization.X10JavaSerializable
                                                                                                                                     {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<Anonymous$369> $RTT = x10.rtt.NamedType.<Anonymous$369> make(
            "MontyPiAcc.Anonymous$369", Anonymous$369.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.lang.Reducible.$RTT, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPiAcc.Anonymous$369 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Anonymous$369.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPiAcc.Anonymous$369 $_obj = new MontyPiAcc.Anonymous$369((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public Anonymous$369(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public x10.lang.Reducible.operator()(T,T):T
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return x10.core.Double.$box($apply$O(x10.core.Double.$unbox(a1), x10.core.Double.$unbox(a2)));
            }
            // dispatcher for method abstract public x10.lang.Reducible.operator()(T,T):T
            public double $apply$D(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply$O(x10.core.Double.$unbox(a1), x10.core.Double.$unbox(a2));
            }
            // bridge for method abstract public x10.lang.Reducible.zero():T
            public x10.core.Double
              zero$G(){return x10.core.Double.$box(zero$O());}
            
                
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
public double
                                                                                                                                               zero$O(
                                                                                                                                               ){
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
return 0.0;
                }
                
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
public double
                                                                                                                                               $apply$O(
                                                                                                                                               final double a,
                                                                                                                                               final double b){
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42097 =
                      ((a) + (((double)(b))));
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
return t42097;
                }
                
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
// creation method for java code (1-phase java constructor)
                public Anonymous$369(){this((java.lang.System[]) null);
                                           MontyPiAcc$Anonymous$369$$init$S();}
                
                // constructor for non-virtual call
                final public MontyPiAcc.Anonymous$369 MontyPiAcc$Anonymous$369$$init$S() { {
                                                                                                  
                                                                                              }
                                                                                              return this;
                                                                                              }
                
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$2 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$2> $RTT = x10.rtt.StaticVoidFunType.<$Closure$2> make(
            $Closure$2.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPiAcc.$Closure$2 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$2.class + " calling"); } 
                $_obj.N = $deserializer.readInt();
                $_obj.result = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPiAcc.$Closure$2 $_obj = new MontyPiAcc.$Closure$2((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.N);
                if (result instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.result);
                } else {
                $serializer.write(this.result);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$2(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
try {{
                        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.util.Random r42115 =
                          ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S()));
                        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
double z42116 =
                          0.0;
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long i42036min42112 =
                          1L;
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long i42036max42113 =
                          ((long)(((int)(this.
                                           N))));
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
long i42109 =
                          i42036min42112;
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
for (;
                                                                                                                                                          true;
                                                                                                                                                          ) {
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42110 =
                              i42109;
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final boolean t42111 =
                              ((t42110) <= (((long)(i42036max42113))));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
if (!(t42111)) {
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
break;
                            }
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long j42106 =
                              i42109;
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double x42098 =
                              r42115.nextDouble$O();
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double y42099 =
                              r42115.nextDouble$O();
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42100 =
                              ((x42098) * (((double)(x42098))));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42101 =
                              ((y42099) * (((double)(y42099))));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42102 =
                              ((t42100) + (((double)(t42101))));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final boolean t42103 =
                              ((t42102) <= (((double)(1.0))));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
if (t42103) {
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42104 =
                                  z42116;
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42105 =
                                  ((t42104) + (((double)(1.0))));
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
z42116 = t42105;
                            }
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42107 =
                              i42109;
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final long t42108 =
                              ((t42107) + (((long)(1L))));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
i42109 = t42108;
                        }
                        
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42117 =
                          z42116;
                        
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
((x10.lang.Accumulator<x10.core.Double>)this.
                                                                                                                                                                                               result).$larrow__0x10$lang$Accumulator$$T(x10.core.Double.$box(t42117));
                        
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final x10.io.Printer t42118 =
                          ((x10.io.Printer)(x10.io.Console.get$OUT()));
                        
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
final double t42119 =
                          x10.core.Double.$unbox(((x10.lang.Accumulator<x10.core.Double>)this.
                                                                                           result).$apply$G());
                        
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
t42118.println(x10.core.Double.$box(t42119));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiAcc.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public int N;
                public x10.lang.Accumulator<x10.core.Double> result;
                
                public $Closure$2(final int N,
                                  final x10.lang.Accumulator<x10.core.Double> result, __1$1x10$lang$Double$2 $dummy) { {
                                                                                                                              this.N = N;
                                                                                                                              this.result = ((x10.lang.Accumulator)(result));
                                                                                                                          }}
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Double$2 {}
                
            }
            
        
        }
        
        