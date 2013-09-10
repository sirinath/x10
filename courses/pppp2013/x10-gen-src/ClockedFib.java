@x10.runtime.impl.java.X10Generated public class ClockedFib extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<ClockedFib> $RTT = x10.rtt.NamedType.<ClockedFib> make(
    "ClockedFib", ClockedFib.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedFib $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + ClockedFib.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        ClockedFib $_obj = new ClockedFib((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public ClockedFib(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        ClockedFib.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> s)  {
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final long t47107 =
              ((x10.core.Rail<java.lang.String>)s).
                size;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final boolean t47109 =
              ((t47107) > (((long)(0L))));
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
int t47110 =
               0;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
if (t47109) {
                
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final java.lang.String t47108 =
                  ((java.lang.String[])s.value)[(int)0L];
                
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
t47110 = java.lang.Integer.parseInt(t47108);
            } else {
                
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
t47110 = 1000;
            }
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int N =
              t47110;
            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final x10.io.Printer t47111 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
t47111.println(((java.lang.Object)("Started!")));
            {
                
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
x10.lang.Runtime.ensureNotInAtomic();
                
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final x10.lang.FinishState x10$__var14 =
                  x10.lang.Runtime.startFinish();
                
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
try {{
                    {
                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new ClockedFib.$Closure$53(N))));
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var14)));
                 }}
                }
            }
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final public ClockedFib
                                                                                                                                      ClockedFib$$this$ClockedFib(
                                                                                                                                      ){
            
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
return ClockedFib.this;
        }
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
// creation method for java code (1-phase java constructor)
        public ClockedFib(){this((java.lang.System[]) null);
                                ClockedFib$$init$S();}
        
        // constructor for non-virtual call
        final public ClockedFib ClockedFib$$init$S() { {
                                                              
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
;
                                                              
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"

                                                              
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
this.__fieldInitializers_ClockedFib();
                                                          }
                                                          return this;
                                                          }
        
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final public void
                                                                                                                                      __fieldInitializers_ClockedFib(
                                                                                                                                      ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$51 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$51> $RTT = x10.rtt.StaticVoidFunType.<$Closure$51> make(
            $Closure$51.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedFib.$Closure$51 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$51.class + " calling"); } 
                $_obj.y = $deserializer.readRef();
                $_obj.N = $deserializer.readInt();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                ClockedFib.$Closure$51 $_obj = new ClockedFib.$Closure$51((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (y instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.y);
                } else {
                $serializer.write(this.y);
                }
                $serializer.write(this.N);
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$51(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
try {{
                        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
while (true) {
                            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47112 =
                              x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                            y).$apply$G());
                            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final boolean t47116 =
                              ((t47112) < (((int)(this.
                                                    N))));
                            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
if (!(t47116)) {
                                
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
break;
                            }
                            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47126 =
                              x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                            x).$apply$G());
                            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47127 =
                              x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                            y).$apply$G());
                            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47128 =
                              ((t47126) + (((int)(t47127))));
                            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                   x).$set__0Clocked$$T(x10.core.Int.$box(t47128));
                            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                   x).next();
                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                   y).next();
                        }
                        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47117 =
                          java.lang.Integer.MAX_VALUE;
                        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                               x).$set__0Clocked$$T(x10.core.Int.$box(t47117));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public Clocked<x10.core.Int> y;
                public int N;
                public Clocked<x10.core.Int> x;
                
                public $Closure$51(final Clocked<x10.core.Int> y,
                                   final int N,
                                   final Clocked<x10.core.Int> x, __0$1x10$lang$Int$2__2$1x10$lang$Int$2 $dummy) { {
                                                                                                                          this.y = ((Clocked)(y));
                                                                                                                          this.N = N;
                                                                                                                          this.x = ((Clocked)(x));
                                                                                                                      }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Int$2__2$1x10$lang$Int$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$52 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$52> $RTT = x10.rtt.StaticVoidFunType.<$Closure$52> make(
            $Closure$52.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedFib.$Closure$52 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$52.class + " calling"); } 
                $_obj.x = $deserializer.readRef();
                $_obj.N = $deserializer.readInt();
                $_obj.y = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                ClockedFib.$Closure$52 $_obj = new ClockedFib.$Closure$52((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                $serializer.write(this.N);
                if (y instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.y);
                } else {
                $serializer.write(this.y);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$52(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
try {{
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
while (true) {
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47118 =
                              x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                            x).$apply$G());
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final boolean t47123 =
                              ((t47118) < (((int)(this.
                                                    N))));
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
if (!(t47123)) {
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
break;
                            }
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47129 =
                              x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                            x).$apply$G());
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                   y).$set__0Clocked$$T(x10.core.Int.$box(t47129));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final x10.io.Printer t47130 =
                              ((x10.io.Printer)(x10.io.Console.get$OUT()));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47131 =
                              x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                            x).$apply$G());
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final java.lang.String t47132 =
                              ((" ") + ((x10.core.Int.$box(t47131))));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
t47130.print(((java.lang.String)(t47132)));
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                   y).next();
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                   x).next();
                        }
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final int t47124 =
                          java.lang.Integer.MAX_VALUE;
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                               y).$set__0Clocked$$T(x10.core.Int.$box(t47124));
                        
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final x10.io.Printer t47125 =
                          ((x10.io.Printer)(x10.io.Console.get$OUT()));
                        
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
t47125.println();
                    }}catch (java.lang.Error __lowerer__var__2__) {
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
throw __lowerer__var__2__;
                    }catch (java.lang.Throwable __lowerer__var__3__) {
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__3__) ? (java.lang.RuntimeException)(__lowerer__var__3__) : new x10.lang.WrappedThrowable(__lowerer__var__3__);
                    }
                }
                
                public Clocked<x10.core.Int> x;
                public int N;
                public Clocked<x10.core.Int> y;
                
                public $Closure$52(final Clocked<x10.core.Int> x,
                                   final int N,
                                   final Clocked<x10.core.Int> y, __0$1x10$lang$Int$2__2$1x10$lang$Int$2 $dummy) { {
                                                                                                                          this.x = ((Clocked)(x));
                                                                                                                          this.N = N;
                                                                                                                          this.y = ((Clocked)(y));
                                                                                                                      }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Int$2__2$1x10$lang$Int$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$53 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$53> $RTT = x10.rtt.StaticVoidFunType.<$Closure$53> make(
            $Closure$53.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedFib.$Closure$53 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$53.class + " calling"); } 
                $_obj.N = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                ClockedFib.$Closure$53 $_obj = new ClockedFib.$Closure$53((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.N);
                
            }
            
            // constructor just for allocation
            public $Closure$53(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
try {{
                        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final Clocked x =
                          ((Clocked)(new Clocked<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).Clocked$$init$S((x10.core.Int.$box(1)), (Clocked.__0Clocked$$T) null)));
                        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
final Clocked y =
                          ((Clocked)(new Clocked<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).Clocked$$init$S((x10.core.Int.$box(1)), (Clocked.__0Clocked$$T) null)));
                        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
x10.lang.Runtime.runAsync__0$1x10$lang$Clock$2(((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.lang.Clock> makeRailFromJavaArray(x10.lang.Clock.$RTT, new x10.lang.Clock[] {((Clocked<x10.core.Int>)x).
                                                                                                                                                                                                                                                                                                                                         clock, ((Clocked<x10.core.Int>)y).
                                                                                                                                                                                                                                                                                                                                                  clock}))),
                                                                                                                                                                                                   ((x10.core.fun.VoidFun_0_0)(new ClockedFib.$Closure$51(y,
                                                                                                                                                                                                                                                          ((int)(this.
                                                                                                                                                                                                                                                                   N)),
                                                                                                                                                                                                                                                          x, (ClockedFib.$Closure$51.__0$1x10$lang$Int$2__2$1x10$lang$Int$2) null))));
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
x10.lang.Runtime.runAsync__0$1x10$lang$Clock$2(((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.lang.Clock> makeRailFromJavaArray(x10.lang.Clock.$RTT, new x10.lang.Clock[] {((Clocked<x10.core.Int>)x).
                                                                                                                                                                                                                                                                                                                                          clock, ((Clocked<x10.core.Int>)y).
                                                                                                                                                                                                                                                                                                                                                   clock}))),
                                                                                                                                                                                                    ((x10.core.fun.VoidFun_0_0)(new ClockedFib.$Closure$52(x,
                                                                                                                                                                                                                                                           ((int)(this.
                                                                                                                                                                                                                                                                    N)),
                                                                                                                                                                                                                                                           y, (ClockedFib.$Closure$52.__0$1x10$lang$Int$2__2$1x10$lang$Int$2) null))));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedFib.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public int N;
                
                public $Closure$53(final int N) { {
                                                         this.N = N;
                                                     }}
                
            }
            
        
        }
        
        