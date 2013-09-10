
@x10.runtime.impl.java.X10Generated public class MontyPiCF extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<MontyPiCF> $RTT = x10.rtt.NamedType.<MontyPiCF> make(
    "MontyPiCF", MontyPiCF.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPiCF $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + MontyPiCF.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        MontyPiCF $_obj = new MontyPiCF((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public MontyPiCF(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        MontyPiCF.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> s)  {
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
assert ((((x10.core.Rail<java.lang.String>)s).
                                                                                                                                                  size) >= (((long)(1L)))): "Usage: MontyPi [<number of points per place:Int>]";
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52254 =
              ((x10.core.Rail<java.lang.String>)s).
                size;
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final boolean t52256 =
              ((t52254) > (((long)(0L))));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
int t52257 =
               0;
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
if (t52256) {
                
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final java.lang.String t52255 =
                  ((java.lang.String[])s.value)[(int)0L];
                
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
t52257 = java.lang.Integer.parseInt(t52255);
            } else {
                
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
t52257 = 10000;
            }
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final int N =
              t52257;
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52258 =
              x10.lang.System.nanoTime$O();
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52259 =
              (-(t52258));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
double start =
              ((double)(long)(((long)(t52259))));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final MontyPiCF.Anonymous$381 reducer =
              ((MontyPiCF.Anonymous$381)(new MontyPiCF.Anonymous$381((java.lang.System[]) null).MontyPiCF$Anonymous$381$$init$S()));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double result;
            {
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final x10.lang.FinishState x10$__var24 =
                  ((x10.lang.FinishState)(x10.lang.Runtime.<x10.core.Double> startCollectingFinish__0$1x10$lang$Runtime$$T$2(x10.rtt.Types.DOUBLE, ((x10.lang.Reducible)(reducer)))));
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
try {{
                    {
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final x10.regionarray.Dist t52261 =
                          ((x10.regionarray.Dist)(x10.regionarray.Dist.makeUnique()));
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final x10.lang.PlaceGroup t52262 =
                          t52261.places();
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final x10.lang.Iterator p52253 =
                          ((x10.lang.Iterable<x10.lang.Place>)t52262).iterator();
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
for (;
                                                                                                                                                         true;
                                                                                                                                                         ) {
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final boolean t52275 =
                              ((x10.lang.Iterator<x10.lang.Place>)p52253).hasNext$O();
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
if (!(t52275)) {
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
break;
                            }
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final x10.lang.Place p52312 =
                              ((x10.lang.Place)(((x10.lang.Iterator<x10.lang.Place>)p52253).next$G()));
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
x10.lang.Runtime.runAsync(((x10.lang.Place)(p52312)),
                                                                                                                                                                                  ((x10.core.fun.VoidFun_0_0)(new MontyPiCF.$Closure$89(N))),
                                                                                                                                                                                  ((x10.lang.Runtime.Profile)(null)));
                        }
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
result = x10.core.Double.$unbox(x10.lang.Runtime.<x10.core.Double> stopCollectingFinish$G(x10.rtt.Types.DOUBLE, ((x10.lang.FinishState)(x10$__var24))));
                 }}
                }
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52276 =
              ((double)(int)(((int)(4))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52280 =
              ((t52276) * (((double)(result))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52277 =
              ((long)(((int)(N))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52278 =
              x10.lang.Place.get$MAX_PLACES();
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52279 =
              ((t52277) * (((long)(t52278))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52281 =
              ((double)(long)(((long)(t52279))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double pi =
              ((t52280) / (((double)(t52281))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52283 =
              start;
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52282 =
              x10.lang.System.nanoTime$O();
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52284 =
              ((double)(long)(((long)(t52282))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52285 =
              ((t52283) + (((double)(t52284))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
start = t52285;
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final x10.io.Printer t52293 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final java.lang.String t52286 =
              (("The value of pi is ") + ((x10.core.Double.$box(pi))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final java.lang.String t52290 =
              ((t52286) + (" (t="));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52288 =
              start;
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final int t52287 =
              1000000;
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52289 =
              ((double)(int)(((int)(t52287))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52291 =
              ((t52288) / (((double)(t52289))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final java.lang.String t52292 =
              ((t52290) + ((x10.core.Double.$box(t52291))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final java.lang.String t52294 =
              ((t52292) + (" ms)."));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
t52293.println(((java.lang.Object)(t52294)));
            }
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final public MontyPiCF
                                                                                                                                     MontyPiCF$$this$MontyPiCF(
                                                                                                                                     ){
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
return MontyPiCF.this;
        }
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
// creation method for java code (1-phase java constructor)
        public MontyPiCF(){this((java.lang.System[]) null);
                               MontyPiCF$$init$S();}
        
        // constructor for non-virtual call
        final public MontyPiCF MontyPiCF$$init$S() { {
                                                            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
;
                                                            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"

                                                            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
this.__fieldInitializers_MontyPiCF();
                                                        }
                                                        return this;
                                                        }
        
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final public void
                                                                                                                                     __fieldInitializers_MontyPiCF(
                                                                                                                                     ){
            
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
@x10.runtime.impl.java.X10Generated final public static class Anonymous$381 extends x10.core.Ref implements x10.lang.Reducible, x10.serialization.X10JavaSerializable
                                                                                                                                    {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<Anonymous$381> $RTT = x10.rtt.NamedType.<Anonymous$381> make(
            "MontyPiCF.Anonymous$381", Anonymous$381.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.lang.Reducible.$RTT, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPiCF.Anonymous$381 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Anonymous$381.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPiCF.Anonymous$381 $_obj = new MontyPiCF.Anonymous$381((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public Anonymous$381(final java.lang.System[] $dummy) { 
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
            
                
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
public double
                                                                                                                                              zero$O(
                                                                                                                                              ){
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
return 0.0;
                }
                
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
public double
                                                                                                                                              $apply$O(
                                                                                                                                              final double a,
                                                                                                                                              final double b){
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52295 =
                      ((a) + (((double)(b))));
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
return t52295;
                }
                
                
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
// creation method for java code (1-phase java constructor)
                public Anonymous$381(){this((java.lang.System[]) null);
                                           MontyPiCF$Anonymous$381$$init$S();}
                
                // constructor for non-virtual call
                final public MontyPiCF.Anonymous$381 MontyPiCF$Anonymous$381$$init$S() { {
                                                                                                
                                                                                            }
                                                                                            return this;
                                                                                            }
                
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$89 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$89> $RTT = x10.rtt.StaticVoidFunType.<$Closure$89> make(
            $Closure$89.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPiCF.$Closure$89 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$89.class + " calling"); } 
                $_obj.N = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPiCF.$Closure$89 $_obj = new MontyPiCF.$Closure$89((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.N);
                
            }
            
            // constructor just for allocation
            public $Closure$89(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
try {{
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final x10.util.Random r52313 =
                          ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S()));
                        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
double result52314 =
                          0.0;
                        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long i52237min52310 =
                          1L;
                        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long i52237max52311 =
                          ((long)(((int)(this.
                                           N))));
                        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
long i52307 =
                          i52237min52310;
                        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
for (;
                                                                                                                                                         true;
                                                                                                                                                         ) {
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52308 =
                              i52307;
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final boolean t52309 =
                              ((t52308) <= (((long)(i52237max52311))));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
if (!(t52309)) {
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
break;
                            }
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long j52304 =
                              i52307;
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double x52296 =
                              r52313.nextDouble$O();
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double y52297 =
                              r52313.nextDouble$O();
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52298 =
                              ((x52296) * (((double)(x52296))));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52299 =
                              ((y52297) * (((double)(y52297))));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52300 =
                              ((t52298) + (((double)(t52299))));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final boolean t52301 =
                              ((t52300) <= (((double)(1.0))));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
if (t52301) {
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52302 =
                                  result52314;
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52303 =
                                  ((t52302) + (((double)(1.0))));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
result52314 = t52303;
                            }
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52305 =
                              i52307;
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final long t52306 =
                              ((t52305) + (((long)(1L))));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
i52307 = t52306;
                        }
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
final double t52315 =
                          result52314;
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
x10.lang.Runtime.<x10.core.Double> makeOffer__0x10$lang$Runtime$$T(x10.rtt.Types.DOUBLE, x10.core.Double.$box(t52315));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPiCF.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public int N;
                
                public $Closure$89(final int N) { {
                                                         this.N = N;
                                                     }}
                
            }
            
        
        }
        
        