
@x10.runtime.impl.java.X10Generated public class MontyPi2 extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<MontyPi2> $RTT = x10.rtt.NamedType.<MontyPi2> make(
    "MontyPi2", MontyPi2.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi2 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + MontyPi2.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        MontyPi2 $_obj = new MontyPi2((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public MontyPi2(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        MontyPi2.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> s)  {
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
assert ((((x10.core.Rail<java.lang.String>)s).
                                                                                                                                                 size) >= (((long)(((long)(((int)(1)))))))): "Usage: MontyPi [<number of points per place:Int>]";
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44157 =
              ((x10.core.Rail<java.lang.String>)s).
                size;
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44158 =
              ((long)(((int)(0))));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final boolean t44160 =
              ((t44157) > (((long)(t44158))));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
int t44161 =
               0;
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
if (t44160) {
                
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final java.lang.String t44159 =
                  ((java.lang.String[])s.value)[(int)0L];
                
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
t44161 = java.lang.Integer.parseInt(t44159);
            } else {
                
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
t44161 = 10000;
            }
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final int N =
              t44161;
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44162 =
              x10.lang.System.nanoTime$O();
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44163 =
              (-(t44162));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
double start =
              ((double)(long)(((long)(t44163))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.regionarray.Dist t44164 =
              ((x10.regionarray.Dist)(x10.regionarray.Dist.makeUnique()));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.core.fun.Fun_0_1 t44165 =
              ((x10.core.fun.Fun_0_1)(new MontyPi2.$Closure$11()));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.regionarray.DistArray result =
              ((x10.regionarray.DistArray)(x10.regionarray.DistArray.<x10.core.Double> make__1$1x10$lang$Point$3x10$regionarray$DistArray$$T$2(x10.rtt.Types.DOUBLE, ((x10.regionarray.Dist)(t44164)),
                                                                                                                                               ((x10.core.fun.Fun_0_1)(t44165)))));
            {
                
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
x10.lang.Runtime.ensureNotInAtomic();
                
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.lang.FinishState x10$__var4 =
                  x10.lang.Runtime.startFinish();
                
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
try {{
                    {
                        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.regionarray.Dist t44167 =
                          ((x10.regionarray.Dist)(((x10.regionarray.DistArray<x10.core.Double>)result).
                                                    dist));
                        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.lang.PlaceGroup t44168 =
                          t44167.places();
                        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.lang.Iterator p44156 =
                          ((x10.lang.Iterable<x10.lang.Place>)t44168).iterator();
                        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
for (;
                                                                                                                                                        true;
                                                                                                                                                        ) {
                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final boolean t44182 =
                              ((x10.lang.Iterator<x10.lang.Place>)p44156).hasNext$O();
                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
if (!(t44182)) {
                                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
break;
                            }
                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.lang.Place p44221 =
                              ((x10.lang.Place)(((x10.lang.Iterator<x10.lang.Place>)p44156).next$G()));
                            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
x10.lang.Runtime.runAsync(((x10.lang.Place)(p44221)),
                                                                                                                                                                                 ((x10.core.fun.VoidFun_0_0)(new MontyPi2.$Closure$12(N,
                                                                                                                                                                                                                                      p44221,
                                                                                                                                                                                                                                      result, (MontyPi2.$Closure$12.__2$1x10$lang$Double$2) null))),
                                                                                                                                                                                 ((x10.lang.Runtime.Profile)(null)));
                        }
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var4)));
                 }}
                }
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44185 =
              ((double)(long)(((long)(4L))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.core.fun.Fun_0_2 t44184 =
              ((x10.core.fun.Fun_0_2)(new MontyPi2.$Closure$13()));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44186 =
              x10.core.Double.$unbox(((x10.regionarray.DistArray<x10.core.Double>)result).reduce__0$1x10$regionarray$DistArray$$T$3x10$regionarray$DistArray$$T$3x10$regionarray$DistArray$$T$2__1x10$regionarray$DistArray$$T$G(((x10.core.fun.Fun_0_2)(t44184)),
                                                                                                                                                                                                                                 x10.core.Double.$box(0.0)));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44190 =
              ((t44185) * (((double)(t44186))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44187 =
              ((long)(((int)(N))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44188 =
              x10.lang.Place.get$MAX_PLACES();
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44189 =
              ((t44187) * (((long)(t44188))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44191 =
              ((double)(long)(((long)(t44189))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double pi =
              ((t44190) / (((double)(t44191))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44193 =
              start;
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44192 =
              x10.lang.System.nanoTime$O();
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44194 =
              ((double)(long)(((long)(t44192))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44195 =
              ((t44193) + (((double)(t44194))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
start = t44195;
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.io.Printer t44203 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final java.lang.String t44196 =
              (("The value of pi is ") + ((x10.core.Double.$box(pi))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final java.lang.String t44200 =
              ((t44196) + (" (t="));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44198 =
              start;
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44197 =
              1000000L;
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44199 =
              ((double)(long)(((long)(t44197))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44201 =
              ((t44198) / (((double)(t44199))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final java.lang.String t44202 =
              ((t44200) + ((x10.core.Double.$box(t44201))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final java.lang.String t44204 =
              ((t44202) + (" ms)."));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
t44203.println(((java.lang.Object)(t44204)));
            }
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final public MontyPi2
                                                                                                                                    MontyPi2$$this$MontyPi2(
                                                                                                                                    ){
            
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
return MontyPi2.this;
        }
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
// creation method for java code (1-phase java constructor)
        public MontyPi2(){this((java.lang.System[]) null);
                              MontyPi2$$init$S();}
        
        // constructor for non-virtual call
        final public MontyPi2 MontyPi2$$init$S() { {
                                                          
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
;
                                                          
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"

                                                          
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
this.__fieldInitializers_MontyPi2();
                                                      }
                                                      return this;
                                                      }
        
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final public void
                                                                                                                                    __fieldInitializers_MontyPi2(
                                                                                                                                    ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$11 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$11> $RTT = x10.rtt.StaticFunType.<$Closure$11> make(
            $Closure$11.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.lang.Point.$RTT, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi2.$Closure$11 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$11.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPi2.$Closure$11 $_obj = new MontyPi2.$Closure$11((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$11(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return x10.core.Double.$box($apply$O((x10.lang.Point)a1));
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public double $apply$D(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply$O((x10.lang.Point)a1);
            }
            
                
                public double
                  $apply$O(
                  final x10.lang.Point id$18727){
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
return 0.0;
                }
                
                public $Closure$11() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$12 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$12> $RTT = x10.rtt.StaticVoidFunType.<$Closure$12> make(
            $Closure$12.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi2.$Closure$12 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$12.class + " calling"); } 
                $_obj.N = $deserializer.readInt();
                $_obj.p44221 = $deserializer.readRef();
                $_obj.result = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPi2.$Closure$12 $_obj = new MontyPi2.$Closure$12((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.N);
                if (p44221 instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.p44221);
                } else {
                $serializer.write(this.p44221);
                }
                if (result instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.result);
                } else {
                $serializer.write(this.result);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$12(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
try {{
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final x10.util.Random r44222 =
                          ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S()));
                        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
double a44223 =
                          0.0;
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final int i44140min44219 =
                          1;
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final int i44140max44220 =
                          this.
                            N;
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
int i44216 =
                          i44140min44219;
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
for (;
                                                                                                                                                        true;
                                                                                                                                                        ) {
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final int t44217 =
                              i44216;
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final boolean t44218 =
                              ((t44217) <= (((int)(i44140max44220))));
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
if (!(t44218)) {
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
break;
                            }
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final int j44213 =
                              i44216;
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double x44205 =
                              r44222.nextDouble$O();
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double y44206 =
                              r44222.nextDouble$O();
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44207 =
                              ((x44205) * (((double)(x44205))));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44208 =
                              ((y44206) * (((double)(y44206))));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44209 =
                              ((t44207) + (((double)(t44208))));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final boolean t44210 =
                              ((t44209) <= (((double)(1.0))));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
if (t44210) {
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44211 =
                                  a44223;
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44212 =
                                  ((t44211) + (((double)(1.0))));
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
a44223 = t44212;
                            }
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final int t44214 =
                              i44216;
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final int t44215 =
                              ((t44214) + (((int)(1))));
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
i44216 = t44215;
                        }
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final long t44224 =
                          this.
                            p44221.
                            id;
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44225 =
                          a44223;
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
((x10.regionarray.DistArray<x10.core.Double>)this.
                                                                                                                                                                                                  result).$set__1x10$regionarray$DistArray$$T$G((long)(t44224),
                                                                                                                                                                                                                                                x10.core.Double.$box(t44225));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public int N;
                public x10.lang.Place p44221;
                public x10.regionarray.DistArray<x10.core.Double> result;
                
                public $Closure$12(final int N,
                                   final x10.lang.Place p44221,
                                   final x10.regionarray.DistArray<x10.core.Double> result, __2$1x10$lang$Double$2 $dummy) { {
                                                                                                                                    this.N = N;
                                                                                                                                    this.p44221 = ((x10.lang.Place)(p44221));
                                                                                                                                    this.result = ((x10.regionarray.DistArray)(result));
                                                                                                                                }}
                // synthetic type for parameter mangling
                public static final class __2$1x10$lang$Double$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$13 extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$13> $RTT = x10.rtt.StaticFunType.<$Closure$13> make(
            $Closure$13.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, x10.rtt.Types.DOUBLE, x10.rtt.Types.DOUBLE, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi2.$Closure$13 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$13.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPi2.$Closure$13 $_obj = new MontyPi2.$Closure$13((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$13(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1,a2:Z2):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return x10.core.Double.$box($apply$O(x10.core.Double.$unbox(a1), x10.core.Double.$unbox(a2)));
            }
            // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1,a2:Z2):U
            public double $apply$D(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply$O(x10.core.Double.$unbox(a1), x10.core.Double.$unbox(a2));
            }
            
                
                public double
                  $apply$O(
                  final double x,
                  final double y){
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
final double t44183 =
                      ((x) + (((double)(y))));
                    
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi2.x10"
return t44183;
                }
                
                public $Closure$13() { {
                                              
                                          }}
                
            }
            
        
        }
        
        