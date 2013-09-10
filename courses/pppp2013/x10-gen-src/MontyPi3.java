
@x10.runtime.impl.java.X10Generated public class MontyPi3 extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<MontyPi3> $RTT = x10.rtt.NamedType.<MontyPi3> make(
    "MontyPi3", MontyPi3.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi3 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + MontyPi3.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        MontyPi3 $_obj = new MontyPi3((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public MontyPi3(final java.lang.System[] $dummy) { 
    }
    
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
private static MontyPi3.Anonymous$138 summer;
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        MontyPi3.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> s)  {
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
assert ((((x10.core.Rail<java.lang.String>)s).
                                                                                                                                                  size) >= (((long)(1L)))): "Usage: MontyPi [<number of points per place:Int>]";
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t47983 =
              ((x10.core.Rail<java.lang.String>)s).
                size;
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final boolean t47985 =
              ((t47983) > (((long)(0L))));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
int t47986 =
               0;
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
if (t47985) {
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final java.lang.String t47984 =
                  ((java.lang.String[])s.value)[(int)0L];
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
t47986 = java.lang.Integer.parseInt(t47984);
            } else {
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
t47986 = 10000;
            }
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final int N =
              t47986;
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t47987 =
              x10.lang.System.nanoTime$O();
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t47988 =
              (-(t47987));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
double start =
              ((double)(long)(((long)(t47988))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double sum;
            {
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final x10.lang.FinishState x10$__var16 =
                  ((x10.lang.FinishState)(x10.lang.Runtime.<x10.core.Double> startCollectingFinish__0$1x10$lang$Runtime$$T$2(x10.rtt.Types.DOUBLE, ((x10.lang.Reducible)(MontyPi3.get$summer())))));
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
try {{
                    {
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final x10.lang.PlaceGroup t47990 =
                          x10.lang.Place.places();
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final x10.lang.Iterator p47982 =
                          ((x10.lang.Iterable<x10.lang.Place>)t47990).iterator();
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
for (;
                                                                                                                                                        true;
                                                                                                                                                        ) {
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final boolean t48003 =
                              ((x10.lang.Iterator<x10.lang.Place>)p47982).hasNext$O();
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
if (!(t48003)) {
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
break;
                            }
                            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final x10.lang.Place p48039 =
                              ((x10.lang.Place)(((x10.lang.Iterator<x10.lang.Place>)p47982).next$G()));
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
x10.lang.Runtime.runAsync(((x10.lang.Place)(p48039)),
                                                                                                                                                                                 ((x10.core.fun.VoidFun_0_0)(new MontyPi3.$Closure$59(N))),
                                                                                                                                                                                 ((x10.lang.Runtime.Profile)(null)));
                        }
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
sum = x10.core.Double.$unbox(x10.lang.Runtime.<x10.core.Double> stopCollectingFinish$G(x10.rtt.Types.DOUBLE, ((x10.lang.FinishState)(x10$__var16))));
                 }}
                }
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48004 =
              ((double)(int)(((int)(4))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48008 =
              ((t48004) * (((double)(sum))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t48005 =
              ((long)(((int)(N))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t48006 =
              x10.lang.Place.get$MAX_PLACES();
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t48007 =
              ((t48005) * (((long)(t48006))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48009 =
              ((double)(long)(((long)(t48007))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double pi =
              ((t48008) / (((double)(t48009))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48011 =
              start;
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t48010 =
              x10.lang.System.nanoTime$O();
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48012 =
              ((double)(long)(((long)(t48010))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48013 =
              ((t48011) + (((double)(t48012))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
start = t48013;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final x10.io.Printer t48021 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final java.lang.String t48014 =
              (("The value of pi is ") + ((x10.core.Double.$box(pi))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final java.lang.String t48018 =
              ((t48014) + (" (t="));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48016 =
              start;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final int t48015 =
              1000000;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48017 =
              ((double)(int)(((int)(t48015))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48019 =
              ((t48016) / (((double)(t48017))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final java.lang.String t48020 =
              ((t48018) + ((x10.core.Double.$box(t48019))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final java.lang.String t48022 =
              ((t48020) + (" ms)."));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
t48021.println(((java.lang.Object)(t48022)));
            }
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final public MontyPi3
                                                                                                                                    MontyPi3$$this$MontyPi3(
                                                                                                                                    ){
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
return MontyPi3.this;
        }
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
// creation method for java code (1-phase java constructor)
        public MontyPi3(){this((java.lang.System[]) null);
                              MontyPi3$$init$S();}
        
        // constructor for non-virtual call
        final public MontyPi3 MontyPi3$$init$S() { {
                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
;
                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"

                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
this.__fieldInitializers_MontyPi3();
                                                      }
                                                      return this;
                                                      }
        
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final public void
                                                                                                                                    __fieldInitializers_MontyPi3(
                                                                                                                                    ){
            
        }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
@x10.runtime.impl.java.X10Generated final public static class Anonymous$138 extends x10.core.Ref implements x10.lang.Reducible, x10.serialization.X10JavaSerializable
                                                                                                                                  {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<Anonymous$138> $RTT = x10.rtt.NamedType.<Anonymous$138> make(
            "MontyPi3.Anonymous$138", Anonymous$138.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.lang.Reducible.$RTT, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi3.Anonymous$138 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Anonymous$138.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPi3.Anonymous$138 $_obj = new MontyPi3.Anonymous$138((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public Anonymous$138(final java.lang.System[] $dummy) { 
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
            
                
                
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
public double
                                                                                                                                            zero$O(
                                                                                                                                            ){
                    
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
return 0.0;
                }
                
                
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
public double
                                                                                                                                            $apply$O(
                                                                                                                                            final double i,
                                                                                                                                            final double j){
                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48023 =
                      ((i) + (((double)(j))));
                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
return t48023;
                }
                
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
// creation method for java code (1-phase java constructor)
                public Anonymous$138(){this((java.lang.System[]) null);
                                           MontyPi3$Anonymous$138$$init$S();}
                
                // constructor for non-virtual call
                final public MontyPi3.Anonymous$138 MontyPi3$Anonymous$138$$init$S() { {
                                                                                              
                                                                                          }
                                                                                          return this;
                                                                                          }
                
            
        }
        
        final private static x10.core.concurrent.AtomicInteger initStatus$summer = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
        private static x10.lang.ExceptionInInitializer exception$summer;
        
        public static MontyPi3.Anonymous$138
          get$summer(
          ){
            if (((int) MontyPi3.initStatus$summer.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                return MontyPi3.summer;
            }
            if (((int) MontyPi3.initStatus$summer.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: MontyPi3.summer")));
                }
                throw MontyPi3.exception$summer;
            }
            if (MontyPi3.initStatus$summer.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                         (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                try {{
                    MontyPi3.summer = ((MontyPi3.Anonymous$138)(new MontyPi3.Anonymous$138((java.lang.System[]) null).MontyPi3$Anonymous$138$$init$S()));
                }}catch (java.lang.Throwable exc$48053) {
                    MontyPi3.exception$summer = new x10.lang.ExceptionInInitializer(exc$48053);
                    MontyPi3.initStatus$summer.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    throw MontyPi3.exception$summer;
                }
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: MontyPi3.summer")));
                }
                MontyPi3.initStatus$summer.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
            } else {
                if (MontyPi3.initStatus$summer.get() <
                    x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    while (MontyPi3.initStatus$summer.get() <
                           x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                        x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                    }
                    x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                    if (((int) MontyPi3.initStatus$summer.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: MontyPi3.summer")));
                        }
                        throw MontyPi3.exception$summer;
                    }
                }
            }
            return MontyPi3.summer;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$59 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$59> $RTT = x10.rtt.StaticVoidFunType.<$Closure$59> make(
            $Closure$59.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi3.$Closure$59 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$59.class + " calling"); } 
                $_obj.N = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPi3.$Closure$59 $_obj = new MontyPi3.$Closure$59((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.N);
                
            }
            
            // constructor just for allocation
            public $Closure$59(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
try {{
                        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final x10.util.Random r48040 =
                          ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S()));
                        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
double a48041 =
                          0.0;
                        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long i47966min48037 =
                          1L;
                        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long i47966max48038 =
                          ((long)(((int)(this.
                                           N))));
                        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
long i48034 =
                          i47966min48037;
                        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
for (;
                                                                                                                                                        true;
                                                                                                                                                        ) {
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t48035 =
                              i48034;
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final boolean t48036 =
                              ((t48035) <= (((long)(i47966max48038))));
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
if (!(t48036)) {
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
break;
                            }
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double x48024 =
                              r48040.nextDouble$O();
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double y48025 =
                              r48040.nextDouble$O();
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48026 =
                              ((x48024) * (((double)(x48024))));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48027 =
                              ((y48025) * (((double)(y48025))));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48028 =
                              ((t48026) + (((double)(t48027))));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final boolean t48029 =
                              ((t48028) <= (((double)(1.0))));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
if (t48029) {
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48030 =
                                  a48041;
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48031 =
                                  ((t48030) + (((double)(1.0))));
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
a48041 = t48031;
                            }
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t48032 =
                              i48034;
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final long t48033 =
                              ((t48032) + (((long)(1L))));
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
i48034 = t48033;
                        }
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
final double t48042 =
                          a48041;
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
x10.lang.Runtime.<x10.core.Double> makeOffer__0x10$lang$Runtime$$T(x10.rtt.Types.DOUBLE, x10.core.Double.$box(t48042));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi3.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public int N;
                
                public $Closure$59(final int N) { {
                                                         this.N = N;
                                                     }}
                
            }
            
        
        }
        
        