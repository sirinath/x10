@x10.runtime.impl.java.X10Generated public class ClockedSieve extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<ClockedSieve> $RTT = x10.rtt.NamedType.<ClockedSieve> make(
    "ClockedSieve", ClockedSieve.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedSieve $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + ClockedSieve.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        ClockedSieve $_obj = new ClockedSieve((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public ClockedSieve(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        ClockedSieve.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> s)  {
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long t46661 =
              ((x10.core.Rail<java.lang.String>)s).
                size;
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final boolean t46663 =
              ((t46661) > (((long)(0L))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
int t46664 =
               0;
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
if (t46663) {
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final java.lang.String t46662 =
                  ((java.lang.String[])s.value)[(int)0L];
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
t46664 = java.lang.Integer.parseInt(t46662);
            } else {
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
t46664 = 100;
            }
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int N =
              t46664;
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long time =
              x10.lang.System.nanoTime$O();
            {
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
x10.lang.Runtime.ensureNotInAtomic();
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final x10.lang.FinishState x10$__var12 =
                  x10.lang.Runtime.startFinish();
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
try {{
                    {
                        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new ClockedSieve.$Closure$43(N))));
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var12)));
                 }}
                }
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final x10.io.Printer t46675 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long t46666 =
              x10.lang.System.nanoTime$O();
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long t46667 =
              ((t46666) - (((long)(time))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final double t46668 =
              ((double)(long)(((long)(t46667))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final double t46671 =
              ((t46668) * (((double)(1.0))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long t46669 =
              1000000L;
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long t46670 =
              1000000000L;
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final double t46672 =
              ((double)(long)(((long)(t46670))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final double t46673 =
              ((t46671) / (((double)(t46672))));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final java.lang.String t46674 =
              (("Time: ") + ((x10.core.Double.$box(t46673))));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final java.lang.String t46676 =
              ((t46674) + (" s."));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
t46675.println(((java.lang.Object)(t46676)));
            }
        
        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
public static Clocked
                                                                                                                                         gen(
                                                                                                                                         final int N){
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final Clocked x =
              ((Clocked)(new Clocked<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).Clocked$$init$S((x10.core.Int.$box(2)), (Clocked.__0Clocked$$T) null)));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
x10.lang.Runtime.runAsync__0$1x10$lang$Clock$2(((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.lang.Clock> makeRailFromJavaArray(x10.lang.Clock.$RTT, new x10.lang.Clock[] {((Clocked<x10.core.Int>)x).
                                                                                                                                                                                                                                                                                                                                clock}))),
                                                                                                                                                                                          ((x10.core.fun.VoidFun_0_0)(new ClockedSieve.$Closure$44(N,
                                                                                                                                                                                                                                                   x, (ClockedSieve.$Closure$44.__1$1x10$lang$Int$2) null))));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
return x;
        }
        
        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
public static void
                                                                                                                                         primes__0$1x10$lang$Int$2(
                                                                                                                                         final Clocked<x10.core.Int> nums){
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
x10.lang.Runtime.runAsync__0$1x10$lang$Clock$2(((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.lang.Clock> makeRailFromJavaArray(x10.lang.Clock.$RTT, new x10.lang.Clock[] {((Clocked<x10.core.Int>)nums).
                                                                                                                                                                                                                                                                                                                                clock}))),
                                                                                                                                                                                          ((x10.core.fun.VoidFun_0_0)(new ClockedSieve.$Closure$45(nums, (ClockedSieve.$Closure$45.__0$1x10$lang$Int$2) null))));
        }
        
        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
public static Clocked
                                                                                                                                         sieve__1$1x10$lang$Int$2(
                                                                                                                                         final int prime,
                                                                                                                                         final Clocked<x10.core.Int> nums){
            
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final Clocked result;
            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
int n =
              x10.core.Int.$unbox(((Clocked<x10.core.Int>)nums).$apply$G());
            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
while (true) {
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46688 =
                  n;
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long t46689 =
                  ((long)(((int)(t46688))));
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final boolean t46691 =
                  ((t46689) < (((long)(0L))));
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
if (t46691) {
                    
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final Clocked t46690 =
                      ((Clocked)(new Clocked<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).Clocked$$init$S((x10.core.Int.$box(-1)), (Clocked.__0Clocked$$T) null)));
                    
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
return t46690;
                }
                
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46692 =
                  n;
                
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46693 =
                  ((t46692) % (((int)(prime))));
                
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final boolean t46696 =
                  ((int) t46693) !=
                ((int) 0);
                
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
if (t46696) {
                    
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46694 =
                      n;
                    
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final Clocked t46695 =
                      ((Clocked)(new Clocked<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).Clocked$$init$S(x10.core.Int.$box(t46694), (Clocked.__0Clocked$$T) null)));
                    
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
result = ((Clocked)(t46695));
                    
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
break;
                }
                
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)nums).next();
                
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46697 =
                  x10.core.Int.$unbox(((Clocked<x10.core.Int>)nums).$apply$G());
                
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
n = t46697;
            }
            
//#line 67 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)nums).next();
            
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
x10.lang.Runtime.runAsync__0$1x10$lang$Clock$2(((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.lang.Clock> makeRailFromJavaArray(x10.lang.Clock.$RTT, new x10.lang.Clock[] {((Clocked<x10.core.Int>)nums).
                                                                                                                                                                                                                                                                                                                                clock, ((Clocked<x10.core.Int>)result).
                                                                                                                                                                                                                                                                                                                                         clock}))),
                                                                                                                                                                                          ((x10.core.fun.VoidFun_0_0)(new ClockedSieve.$Closure$46(nums,
                                                                                                                                                                                                                                                   prime,
                                                                                                                                                                                                                                                   result, (ClockedSieve.$Closure$46.__0$1x10$lang$Int$2__2$1x10$lang$Int$2) null))));
            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
return result;
        }
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final public ClockedSieve
                                                                                                                                        ClockedSieve$$this$ClockedSieve(
                                                                                                                                        ){
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
return ClockedSieve.this;
        }
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
// creation method for java code (1-phase java constructor)
        public ClockedSieve(){this((java.lang.System[]) null);
                                  ClockedSieve$$init$S();}
        
        // constructor for non-virtual call
        final public ClockedSieve ClockedSieve$$init$S() { {
                                                                  
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
;
                                                                  
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"

                                                                  
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
this.__fieldInitializers_ClockedSieve();
                                                              }
                                                              return this;
                                                              }
        
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final public void
                                                                                                                                        __fieldInitializers_ClockedSieve(
                                                                                                                                        ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$43 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$43> $RTT = x10.rtt.StaticVoidFunType.<$Closure$43> make(
            $Closure$43.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedSieve.$Closure$43 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$43.class + " calling"); } 
                $_obj.N = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                ClockedSieve.$Closure$43 $_obj = new ClockedSieve.$Closure$43((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.N);
                
            }
            
            // constructor just for allocation
            public $Closure$43(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
try {{
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final Clocked t46665 =
                          ClockedSieve.gen((int)(this.
                                                   N));
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
ClockedSieve.primes__0$1x10$lang$Int$2(((Clocked)(t46665)));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public int N;
                
                public $Closure$43(final int N) { {
                                                         this.N = N;
                                                     }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$44 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$44> $RTT = x10.rtt.StaticVoidFunType.<$Closure$44> make(
            $Closure$44.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedSieve.$Closure$44 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$44.class + " calling"); } 
                $_obj.N = $deserializer.readInt();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                ClockedSieve.$Closure$44 $_obj = new ClockedSieve.$Closure$44((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.N);
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$44(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
try {{
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int i46646min46712 =
                          3;
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int i46646max46713 =
                          this.
                            N;
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
int i46709 =
                          i46646min46712;
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
for (;
                                                                                                                                                            true;
                                                                                                                                                            ) {
                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46710 =
                              i46709;
                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final boolean t46711 =
                              ((t46710) <= (((int)(i46646max46713))));
                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
if (!(t46711)) {
                                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
break;
                            }
                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int i46706 =
                              i46709;
                            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                     x).$set__0Clocked$$T(x10.core.Int.$box(i46706));
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                     x).next();
                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46707 =
                              i46709;
                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46708 =
                              ((t46707) + (((int)(1))));
                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
i46709 = t46708;
                        }
                        
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                 x).$set__0Clocked$$T(x10.core.Int.$box(-1));
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                 x).next();
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public int N;
                public Clocked<x10.core.Int> x;
                
                public $Closure$44(final int N,
                                   final Clocked<x10.core.Int> x, __1$1x10$lang$Int$2 $dummy) { {
                                                                                                       this.N = N;
                                                                                                       this.x = ((Clocked)(x));
                                                                                                   }}
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Int$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$45 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$45> $RTT = x10.rtt.StaticVoidFunType.<$Closure$45> make(
            $Closure$45.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedSieve.$Closure$45 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$45.class + " calling"); } 
                $_obj.nums = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                ClockedSieve.$Closure$45 $_obj = new ClockedSieve.$Closure$45((java.lang.System[]) null);
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
            public $Closure$45(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
try {{
                        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int n =
                          x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                        nums).$apply$G());
                        
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long t46682 =
                          ((long)(((int)(n))));
                        
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final boolean t46687 =
                          ((t46682) > (((long)(0L))));
                        
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
if (t46687) {
                            
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final x10.io.Printer t46683 =
                              ((x10.io.Printer)(x10.io.Console.get$OUT()));
                            
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final java.lang.String t46684 =
                              ((" ") + ((x10.core.Int.$box(n))));
                            
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
t46683.print(((java.lang.String)(t46684)));
                            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                     nums).next();
                            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final Clocked t46685 =
                              ClockedSieve.sieve__1$1x10$lang$Int$2((int)(n),
                                                                    ((Clocked)(this.
                                                                                 nums)));
                            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
ClockedSieve.primes__0$1x10$lang$Int$2(((Clocked)(t46685)));
                        } else {
                            
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final x10.io.Printer t46686 =
                              ((x10.io.Printer)(x10.io.Console.get$OUT()));
                            
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
t46686.println();
                        }
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public Clocked<x10.core.Int> nums;
                
                public $Closure$45(final Clocked<x10.core.Int> nums, __0$1x10$lang$Int$2 $dummy) { {
                                                                                                          this.nums = ((Clocked)(nums));
                                                                                                      }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Int$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$46 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$46> $RTT = x10.rtt.StaticVoidFunType.<$Closure$46> make(
            $Closure$46.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(ClockedSieve.$Closure$46 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$46.class + " calling"); } 
                $_obj.nums = $deserializer.readRef();
                $_obj.prime = $deserializer.readInt();
                $_obj.result = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                ClockedSieve.$Closure$46 $_obj = new ClockedSieve.$Closure$46((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (nums instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.nums);
                } else {
                $serializer.write(this.nums);
                }
                $serializer.write(this.prime);
                if (result instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.result);
                } else {
                $serializer.write(this.result);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$46(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
try {{
                        
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
int m =
                          x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                        nums).$apply$G());
                        
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
while (true) {
                            
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46698 =
                              m;
                            
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final long t46699 =
                              ((long)(((int)(t46698))));
                            
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final boolean t46700 =
                              ((t46699) < (((long)(0L))));
                            
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
if (t46700) {
                                
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
break;
                            }
                            
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46701 =
                              m;
                            
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46702 =
                              ((t46701) % (((int)(this.
                                                    prime))));
                            
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final boolean t46704 =
                              ((int) t46702) !=
                            ((int) 0);
                            
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
if (t46704) {
                                
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46703 =
                                  m;
                                
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                         result).$set__0Clocked$$T(x10.core.Int.$box(t46703));
                                
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                         result).next();
                            }
                            
//#line 79 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                     nums).next();
                            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
final int t46705 =
                              x10.core.Int.$unbox(((Clocked<x10.core.Int>)this.
                                                                            nums).$apply$G());
                            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
m = t46705;
                        }
                        
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
((Clocked<x10.core.Int>)this.
                                                                                                                                                                                 result).$set__0Clocked$$T(x10.core.Int.$box(-1));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/ClockedSieve.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public Clocked<x10.core.Int> nums;
                public int prime;
                public Clocked<x10.core.Int> result;
                
                public $Closure$46(final Clocked<x10.core.Int> nums,
                                   final int prime,
                                   final Clocked<x10.core.Int> result, __0$1x10$lang$Int$2__2$1x10$lang$Int$2 $dummy) { {
                                                                                                                               this.nums = ((Clocked)(nums));
                                                                                                                               this.prime = prime;
                                                                                                                               this.result = ((Clocked)(result));
                                                                                                                           }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Int$2__2$1x10$lang$Int$2 {}
                
            }
            
        
        }
        
        