
@x10.runtime.impl.java.X10Generated public class MontyPi extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<MontyPi> $RTT = x10.rtt.NamedType.<MontyPi> make(
    "MontyPi", MontyPi.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + MontyPi.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        MontyPi $_obj = new MontyPi((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public MontyPi(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        MontyPi.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> s)  {
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
assert ((((x10.core.Rail<java.lang.String>)s).
                                                                                                                                                size) >= (((long)(((long)(((int)(1)))))))): "Usage: MontyPi [<number of points per place:Int>]";
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51509 =
              ((x10.core.Rail<java.lang.String>)s).
                size;
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51510 =
              ((long)(((int)(0))));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final boolean t51513 =
              ((t51509) > (((long)(t51510))));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
int t51514 =
               0;
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
if (t51513) {
                
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51511 =
                  ((long)(((int)(0))));
                
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final java.lang.String t51512 =
                  ((java.lang.String[])s.value)[(int)t51511];
                
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
t51514 = java.lang.Integer.parseInt(t51512);
            } else {
                
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
t51514 = 10000;
            }
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final int N =
              t51514;
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51515 =
              x10.lang.System.nanoTime$O();
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51516 =
              (-(t51515));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
double start =
              ((double)(long)(((long)(t51516))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final x10.core.fun.Fun_0_1 initializer =
              ((x10.core.fun.Fun_0_1)(new MontyPi.$Closure$85(N)));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final x10.regionarray.Dist t51529 =
              ((x10.regionarray.Dist)(x10.regionarray.Dist.makeUnique()));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final x10.regionarray.DistArray result =
              ((x10.regionarray.DistArray)(x10.regionarray.DistArray.<x10.core.Double> make__1$1x10$lang$Point$3x10$regionarray$DistArray$$T$2(x10.rtt.Types.DOUBLE, ((x10.regionarray.Dist)(t51529)),
                                                                                                                                               ((x10.core.fun.Fun_0_1)(initializer)))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51532 =
              ((double)(int)(((int)(4))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final x10.core.fun.Fun_0_2 t51531 =
              ((x10.core.fun.Fun_0_2)(new MontyPi.$Closure$86()));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51533 =
              x10.core.Double.$unbox(((x10.regionarray.DistArray<x10.core.Double>)result).reduce__0$1x10$regionarray$DistArray$$T$3x10$regionarray$DistArray$$T$3x10$regionarray$DistArray$$T$2__1x10$regionarray$DistArray$$T$G(((x10.core.fun.Fun_0_2)(t51531)),
                                                                                                                                                                                                                                 x10.core.Double.$box(0.0)));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51537 =
              ((t51532) * (((double)(t51533))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51534 =
              ((long)(((int)(N))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51535 =
              x10.lang.Place.get$MAX_PLACES();
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51536 =
              ((t51534) * (((long)(t51535))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51538 =
              ((double)(long)(((long)(t51536))));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double pi =
              ((t51537) / (((double)(t51538))));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51540 =
              start;
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final long t51539 =
              x10.lang.System.nanoTime$O();
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51541 =
              ((double)(long)(((long)(t51539))));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51542 =
              ((t51540) + (((double)(t51541))));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
start = t51542;
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final x10.io.Printer t51550 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final java.lang.String t51543 =
              (("The value of pi is ") + ((x10.core.Double.$box(pi))));
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final java.lang.String t51547 =
              ((t51543) + (" (t="));
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51545 =
              start;
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final int t51544 =
              1000000;
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51546 =
              ((double)(int)(((int)(t51544))));
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51548 =
              ((t51545) / (((double)(t51546))));
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final java.lang.String t51549 =
              ((t51547) + ((x10.core.Double.$box(t51548))));
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final java.lang.String t51551 =
              ((t51549) + (" ms)."));
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
t51550.println(((java.lang.Object)(t51551)));
        }
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final public MontyPi
                                                                                                                                   MontyPi$$this$MontyPi(
                                                                                                                                   ){
            
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
return MontyPi.this;
        }
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
// creation method for java code (1-phase java constructor)
        public MontyPi(){this((java.lang.System[]) null);
                             MontyPi$$init$S();}
        
        // constructor for non-virtual call
        final public MontyPi MontyPi$$init$S() { {
                                                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
;
                                                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"

                                                        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
this.__fieldInitializers_MontyPi();
                                                    }
                                                    return this;
                                                    }
        
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final public void
                                                                                                                                   __fieldInitializers_MontyPi(
                                                                                                                                   ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$85 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$85> $RTT = x10.rtt.StaticFunType.<$Closure$85> make(
            $Closure$85.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.lang.Point.$RTT, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi.$Closure$85 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$85.class + " calling"); } 
                $_obj.N = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPi.$Closure$85 $_obj = new MontyPi.$Closure$85((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.N);
                
            }
            
            // constructor just for allocation
            public $Closure$85(final java.lang.System[] $dummy) { 
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
                  final x10.lang.Point i){
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final x10.util.Random r =
                      ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S()));
                    
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
double result =
                      0.0;
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final int i51494min51566 =
                      1;
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final int i51494max51567 =
                      this.
                        N;
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
int i51563 =
                      i51494min51566;
                    
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final int t51564 =
                          i51563;
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final boolean t51565 =
                          ((t51564) <= (((int)(i51494max51567))));
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
if (!(t51565)) {
                            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
break;
                        }
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final int j51560 =
                          i51563;
                        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double x51552 =
                          r.nextDouble$O();
                        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double y51553 =
                          r.nextDouble$O();
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51554 =
                          ((x51552) * (((double)(x51552))));
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51555 =
                          ((y51553) * (((double)(y51553))));
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51556 =
                          ((t51554) + (((double)(t51555))));
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final boolean t51557 =
                          ((t51556) <= (((double)(1.0))));
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
if (t51557) {
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51558 =
                              result;
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51559 =
                              ((t51558) + (((double)(1.0))));
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
result = t51559;
                        }
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final int t51561 =
                          i51563;
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final int t51562 =
                          ((t51561) + (((int)(1))));
                        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
i51563 = t51562;
                    }
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51528 =
                      result;
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
return t51528;
                }
                
                public int N;
                
                public $Closure$85(final int N) { {
                                                         this.N = N;
                                                     }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$86 extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$86> $RTT = x10.rtt.StaticFunType.<$Closure$86> make(
            $Closure$86.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, x10.rtt.Types.DOUBLE, x10.rtt.Types.DOUBLE, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(MontyPi.$Closure$86 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$86.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                MontyPi.$Closure$86 $_obj = new MontyPi.$Closure$86((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$86(final java.lang.System[] $dummy) { 
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
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
final double t51530 =
                      ((x) + (((double)(y))));
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/MontyPi.x10"
return t51530;
                }
                
                public $Closure$86() { {
                                              
                                          }}
                
            }
            
        
        }
        
        