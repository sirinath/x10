@x10.runtime.impl.java.X10Generated public class Histogram extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Histogram> $RTT = x10.rtt.NamedType.<Histogram> make(
    "Histogram", Histogram.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(Histogram $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Histogram.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        Histogram $_obj = new Histogram((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Histogram(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
public static void
                                                                                                                                     run__0$1x10$lang$Int$2__1$1x10$lang$Int$2(
                                                                                                                                     final x10.core.Rail<x10.core.Int> a,
                                                                                                                                     final x10.core.Rail<x10.core.Int> b){
            {
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
x10.lang.Runtime.ensureNotInAtomic();
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final x10.lang.FinishState x10$__var23 =
                  x10.lang.Runtime.startFinish();
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
try {{
                    {
                        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final x10.core.Rail rail50216 =
                          ((x10.core.Rail)(a));
                        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long size50217 =
                          ((x10.core.Rail<x10.core.Int>)rail50216).
                            size;
                        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
long idx50300 =
                          0L;
                        {
                            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int[] rail50216$value50316 =
                              ((int[])rail50216.value);
                            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
for (;
                                                                                                                                                            true;
                                                                                                                                                            ) {
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50301 =
                                  idx50300;
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final boolean t50302 =
                                  ((t50301) < (((long)(size50217))));
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
if (!(t50302)) {
                                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
break;
                                }
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50296 =
                                  idx50300;
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int i50297 =
                                  ((int)rail50216$value50316[(int)t50296]);
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new Histogram.$Closure$80(i50297,
                                                                                                                                                                                                                                           a,
                                                                                                                                                                                                                                           b, (Histogram.$Closure$80.__1$1x10$lang$Int$2__2$1x10$lang$Int$2) null))));
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50298 =
                                  idx50300;
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50299 =
                                  ((t50298) + (((long)(1L))));
                                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
idx50300 = t50299;
                            }
                        }
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var23)));
                 }}
                }
            }
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        Histogram.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
assert ((long) ((x10.core.Rail<java.lang.String>)args).
                                                                                                                                                         size) ==
            ((long) 2L): "Usage: Histogram <M:Long> <N:Long>";
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final java.lang.String t50275 =
              ((java.lang.String[])args.value)[(int)0L];
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long M =
              java.lang.Long.parseLong(t50275);
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final java.lang.String t50276 =
              ((java.lang.String[])args.value)[(int)1L];
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long N =
              java.lang.Long.parseLong(t50276);
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
assert ((long) ((M) % (((long)(N))))) ==
            ((long) 0L): "Usage: M must be a multiple of N.";
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final x10.core.fun.Fun_0_1 t50278 =
              ((x10.core.fun.Fun_0_1)(new Histogram.$Closure$81()));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final x10.core.Rail a =
              ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, ((long)(M)),
                                                               ((x10.core.fun.Fun_0_1)(t50278)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final x10.core.Rail b =
              ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, ((long)(N)),
                                                               (x10.core.Int.$box(0)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
Histogram.run__0$1x10$lang$Int$2__1$1x10$lang$Int$2(((x10.core.Rail)(a)),
                                                                                                                                                                                            ((x10.core.Rail)(b)));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int v =
              ((int[])b.value)[(int)0L];
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final x10.core.Rail rail50310 =
              ((x10.core.Rail)(b));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long size50311 =
              ((x10.core.Rail<x10.core.Int>)rail50310).
                size;
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
long idx50307 =
              0L;
            {
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int[] rail50310$value50317 =
                  ((int[])rail50310.value);
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
for (;
                                                                                                                                                 true;
                                                                                                                                                 ) {
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50308 =
                      idx50307;
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final boolean t50309 =
                      ((t50308) < (((long)(size50311))));
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
if (!(t50309)) {
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
break;
                    }
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50303 =
                      idx50307;
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int x50304 =
                      ((int)rail50310$value50317[(int)t50303]);
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
assert ((int) x50304) ==
                    ((int) v);
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50305 =
                      idx50307;
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50306 =
                      ((t50305) + (((long)(1L))));
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
idx50307 = t50306;
                }
            }
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final x10.io.Printer t50285 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
t50285.println(((java.lang.Object)("Test ok.")));
        }
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final public Histogram
                                                                                                                                     Histogram$$this$Histogram(
                                                                                                                                     ){
            
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
return Histogram.this;
        }
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
// creation method for java code (1-phase java constructor)
        public Histogram(){this((java.lang.System[]) null);
                               Histogram$$init$S();}
        
        // constructor for non-virtual call
        final public Histogram Histogram$$init$S() { {
                                                            
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
;
                                                            
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"

                                                            
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
this.__fieldInitializers_Histogram();
                                                        }
                                                        return this;
                                                        }
        
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final public void
                                                                                                                                     __fieldInitializers_Histogram(
                                                                                                                                     ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$80 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$80> $RTT = x10.rtt.StaticVoidFunType.<$Closure$80> make(
            $Closure$80.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(Histogram.$Closure$80 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$80.class + " calling"); } 
                $_obj.i50297 = $deserializer.readInt();
                $_obj.a = $deserializer.readRef();
                $_obj.b = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                Histogram.$Closure$80 $_obj = new Histogram.$Closure$80((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.i50297);
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
                
            }
            
            // constructor just for allocation
            public $Closure$80(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
try {{
                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50288 =
                          ((long)(((int)(this.
                                           i50297))));
                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int t50289 =
                          ((int[])this.
                                    a.value)[(int)t50288];
                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50290 =
                          ((long)(((int)(t50289))));
                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long t50291 =
                          ((x10.core.Rail<x10.core.Int>)this.
                                                          b).
                            size;
                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long bin50292 =
                          ((t50290) % (((long)(t50291))));
                        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
try {{
                            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
x10.lang.Runtime.enterAtomic();
                            {
                                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final x10.core.Rail x50293 =
                                  ((x10.core.Rail)(this.
                                                     b));
                                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final long y50294 =
                                  bin50292;
                                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
;
                                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
int ret50295 =
                                   0;
                                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int t50286 =
                                  ((int[])x50293.value)[(int)y50294];
                                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int r50287 =
                                  ((t50286) + (((int)(1))));
                                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
((int[])x50293.value)[(int)y50294] = r50287;
                                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
ret50295 = r50287;
                            }
                        }}finally {{
                              
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
x10.lang.Runtime.exitAtomic();
                          }}
                        }}catch (java.lang.Error __lowerer__var__0__) {
                            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
throw __lowerer__var__0__;
                        }catch (java.lang.Throwable __lowerer__var__1__) {
                            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                        }
                    }
                
                public int i50297;
                public x10.core.Rail<x10.core.Int> a;
                public x10.core.Rail<x10.core.Int> b;
                
                public $Closure$80(final int i50297,
                                   final x10.core.Rail<x10.core.Int> a,
                                   final x10.core.Rail<x10.core.Int> b, __1$1x10$lang$Int$2__2$1x10$lang$Int$2 $dummy) { {
                                                                                                                                this.i50297 = i50297;
                                                                                                                                this.a = ((x10.core.Rail)(a));
                                                                                                                                this.b = ((x10.core.Rail)(b));
                                                                                                                            }}
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Int$2__2$1x10$lang$Int$2 {}
                
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$81 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$81> $RTT = x10.rtt.StaticFunType.<$Closure$81> make(
                $Closure$81.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.INT)}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(Histogram.$Closure$81 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$81.class + " calling"); } 
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    Histogram.$Closure$81 $_obj = new Histogram.$Closure$81((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    
                }
                
                // constructor just for allocation
                public $Closure$81(final java.lang.System[] $dummy) { 
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
                      final long q){
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
final int t50277 =
                          ((int)(long)(((long)(q))));
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Histogram.x10"
return t50277;
                    }
                    
                    public $Closure$81() { {
                                                  
                                              }}
                    
                }
                
            
        }
        
        