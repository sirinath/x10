
@x10.runtime.impl.java.X10Generated public class AllReduceClock extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<AllReduceClock> $RTT = x10.rtt.NamedType.<AllReduceClock> make(
    "AllReduceClock", AllReduceClock.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(AllReduceClock $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + AllReduceClock.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        AllReduceClock $_obj = new AllReduceClock((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public AllReduceClock(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
public static boolean
                                                                                                                                           even$O(
                                                                                                                                           final int p){
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44833 =
              ((p) % (((int)(2))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final boolean t44834 =
              ((int) t44833) ==
            ((int) 0);
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
return t44834;
        }
        
        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
public static int
                                                                                                                                           allReduce__0$1x10$lang$Int$2__1$1x10$lang$Int$2$O(
                                                                                                                                           final x10.regionarray.DistArray<x10.core.Int> red,
                                                                                                                                           final x10.regionarray.DistArray<x10.core.Int> black){
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long P =
              x10.lang.Place.get$MAX_PLACES();
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44835 =
              ((int)(long)(((long)(P))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int phases =
              pppp.util.Utils.log2$O((int)(t44835));
            {
                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10.lang.Clock x10$__var6 =
                  null;
                {
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10.lang.Runtime.ensureNotInAtomic();
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.lang.FinishState x10$__var7 =
                      x10.lang.Runtime.startFinish();
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
try {{
                        {
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
try {{
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.lang.Clock x10$__var5 =
                                  x10.lang.Clock.make();
                                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10$__var6 = x10$__var5;
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.regionarray.Dist t44837 =
                                  ((x10.regionarray.Dist)(((x10.regionarray.DistArray<x10.core.Int>)red).
                                                            dist));
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.lang.PlaceGroup t44838 =
                                  t44837.places();
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.lang.Iterator p44832 =
                                  ((x10.lang.Iterable<x10.lang.Place>)t44838).iterator();
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
for (;
                                                                                                                                                                      true;
                                                                                                                                                                      ) {
                                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final boolean t44858 =
                                      ((x10.lang.Iterator<x10.lang.Place>)p44832).hasNext$O();
                                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
if (!(t44858)) {
                                        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
break;
                                    }
                                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.lang.Place p44897 =
                                      ((x10.lang.Place)(((x10.lang.Iterator<x10.lang.Place>)p44832).next$G()));
                                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10.lang.Runtime.runAsync__1$1x10$lang$Clock$2(((x10.lang.Place)(p44897)),
                                                                                                                                                                                                                    ((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.lang.Clock> makeRailFromJavaArray(x10.lang.Clock.$RTT, new x10.lang.Clock[] {x10$__var5}))),
                                                                                                                                                                                                                    ((x10.core.fun.VoidFun_0_0)(new AllReduceClock.$Closure$29(phases,
                                                                                                                                                                                                                                                                               p44897,
                                                                                                                                                                                                                                                                               P,
                                                                                                                                                                                                                                                                               black,
                                                                                                                                                                                                                                                                               red, (AllReduceClock.$Closure$29.__3$1x10$lang$Int$2__4$1x10$lang$Int$2) null))),
                                                                                                                                                                                                                    ((x10.lang.Runtime.Profile)(null)));
                                }
                            }}finally {{
                                  
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
if (((x10$__var6) != (null))) {
                                      
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10$__var6.drop();
                                  }
                              }}
                            }
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
throw new java.lang.RuntimeException();
                        }finally {{
                             
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var7)));
                         }}
                    }
                }
                
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44859 =
                  ((phases) - (((int)(1))));
                
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final boolean t44862 =
                  AllReduceClock.even$O((int)(t44859));
                
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
int t44863 =
                   0;
                
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
if (t44862) {
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long t44860 =
                      ((long)(((int)(0))));
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
t44863 = x10.core.Int.$unbox(((x10.regionarray.DistArray<x10.core.Int>)red).$apply$G((long)(t44860)));
                } else {
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long t44861 =
                      ((long)(((int)(0))));
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
t44863 = x10.core.Int.$unbox(((x10.regionarray.DistArray<x10.core.Int>)black).$apply$G((long)(t44861)));
                }
                
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44864 =
                  t44863;
                
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
return t44864;
            }
            
            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
public static void
                                                                                                                                               main__0$1x10$lang$String$2(
                                                                                                                                               final x10.regionarray.Array<java.lang.String> id$18813){
                
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
assert pppp.util.Utils.powerOf2$O((int)(((int)(long)(((long)(x10.lang.Place.get$MAX_PLACES())))))): " Must run on power of 2 places.";
                
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.regionarray.Dist D =
                  ((x10.regionarray.Dist)(x10.regionarray.Dist.makeUnique()));
                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.core.fun.Fun_0_1 t44867 =
                  ((x10.core.fun.Fun_0_1)(new AllReduceClock.$Closure$30()));
                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.regionarray.DistArray black =
                  ((x10.regionarray.DistArray)(x10.regionarray.DistArray.<x10.core.Int> make__1$1x10$lang$Point$3x10$regionarray$DistArray$$T$2(x10.rtt.Types.INT, ((x10.regionarray.Dist)(D)),
                                                                                                                                                ((x10.core.fun.Fun_0_1)(t44867)))));
                
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.core.fun.Fun_0_1 t44868 =
                  ((x10.core.fun.Fun_0_1)(new AllReduceClock.$Closure$31()));
                
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.regionarray.DistArray red =
                  ((x10.regionarray.DistArray)(x10.regionarray.DistArray.<x10.core.Int> make__1$1x10$lang$Point$3x10$regionarray$DistArray$$T$2(x10.rtt.Types.INT, ((x10.regionarray.Dist)(D)),
                                                                                                                                                ((x10.core.fun.Fun_0_1)(t44868)))));
                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int result =
                  AllReduceClock.allReduce__0$1x10$lang$Int$2__1$1x10$lang$Int$2$O(((x10.regionarray.DistArray)(red)),
                                                                                   ((x10.regionarray.DistArray)(black)));
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.io.Printer t44869 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final java.lang.String t44870 =
                  (("allReduce = ") + ((x10.core.Int.$box(result))));
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
t44869.println(((java.lang.Object)(t44870)));
            }
            
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final public AllReduceClock
                                                                                                                                               AllReduceClock$$this$AllReduceClock(
                                                                                                                                               ){
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
return AllReduceClock.this;
            }
            
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
// creation method for java code (1-phase java constructor)
            public AllReduceClock(){this((java.lang.System[]) null);
                                        AllReduceClock$$init$S();}
            
            // constructor for non-virtual call
            final public AllReduceClock AllReduceClock$$init$S() { {
                                                                          
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
;
                                                                          
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"

                                                                          
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
this.__fieldInitializers_AllReduceClock();
                                                                      }
                                                                      return this;
                                                                      }
            
            
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final public void
                                                                                                                                               __fieldInitializers_AllReduceClock(
                                                                                                                                               ){
                
            }
            
            @x10.runtime.impl.java.X10Generated final public static class $Closure$27 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$27> $RTT = x10.rtt.StaticVoidFunType.<$Closure$27> make(
                $Closure$27.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(AllReduceClock.$Closure$27 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$27.class + " calling"); } 
                    $_obj.black = $deserializer.readRef();
                    $_obj.destId44876 = $deserializer.readLong();
                    $_obj.elem44878 = $deserializer.readInt();
                    $_obj.red = $deserializer.readRef();
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    AllReduceClock.$Closure$27 $_obj = new AllReduceClock.$Closure$27((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    if (black instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.black);
                    } else {
                    $serializer.write(this.black);
                    }
                    $serializer.write(this.destId44876);
                    $serializer.write(this.elem44878);
                    if (red instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.red);
                    } else {
                    $serializer.write(this.red);
                    }
                    
                }
                
                // constructor just for allocation
                public $Closure$27(final java.lang.System[] $dummy) { 
                }
                
                    
                    public void
                      $apply(
                      ){
                        
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
try {{
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44880 =
                              x10.core.Int.$unbox(((x10.regionarray.DistArray<x10.core.Int>)this.
                                                                                              black).$apply$G((long)(this.
                                                                                                                       destId44876)));
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44881 =
                              ((this.
                                  elem44878) + (((int)(t44880))));
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
((x10.regionarray.DistArray<x10.core.Int>)this.
                                                                                                                                                                                                         red).$set__1x10$regionarray$DistArray$$T$G((long)(this.
                                                                                                                                                                                                                                                             destId44876),
                                                                                                                                                                                                                                                    x10.core.Int.$box(t44881));
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
int __lowerer__var__1__ =
                              (x10.core.Int.$unbox(x10.lang.Runtime.<x10.core.Int> wrapAtChecked$G(x10.rtt.Types.INT, ((java.lang.Throwable)(__lowerer__var__0__)))));
                        }
                    }
                    
                    public x10.regionarray.DistArray<x10.core.Int> black;
                    public long destId44876;
                    public int elem44878;
                    public x10.regionarray.DistArray<x10.core.Int> red;
                    
                    public $Closure$27(final x10.regionarray.DistArray<x10.core.Int> black,
                                       final long destId44876,
                                       final int elem44878,
                                       final x10.regionarray.DistArray<x10.core.Int> red, __0$1x10$lang$Int$2__3$1x10$lang$Int$2 $dummy) { {
                                                                                                                                                  this.black = ((x10.regionarray.DistArray)(black));
                                                                                                                                                  this.destId44876 = destId44876;
                                                                                                                                                  this.elem44878 = elem44878;
                                                                                                                                                  this.red = ((x10.regionarray.DistArray)(red));
                                                                                                                                              }}
                    // synthetic type for parameter mangling
                    public static final class __0$1x10$lang$Int$2__3$1x10$lang$Int$2 {}
                    
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$28 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$28> $RTT = x10.rtt.StaticVoidFunType.<$Closure$28> make(
                $Closure$28.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(AllReduceClock.$Closure$28 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$28.class + " calling"); } 
                    $_obj.red = $deserializer.readRef();
                    $_obj.destId44876 = $deserializer.readLong();
                    $_obj.elem44883 = $deserializer.readInt();
                    $_obj.black = $deserializer.readRef();
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    AllReduceClock.$Closure$28 $_obj = new AllReduceClock.$Closure$28((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    if (red instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.red);
                    } else {
                    $serializer.write(this.red);
                    }
                    $serializer.write(this.destId44876);
                    $serializer.write(this.elem44883);
                    if (black instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.black);
                    } else {
                    $serializer.write(this.black);
                    }
                    
                }
                
                // constructor just for allocation
                public $Closure$28(final java.lang.System[] $dummy) { 
                }
                
                    
                    public void
                      $apply(
                      ){
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
try {{
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44885 =
                              x10.core.Int.$unbox(((x10.regionarray.DistArray<x10.core.Int>)this.
                                                                                              red).$apply$G((long)(this.
                                                                                                                     destId44876)));
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44886 =
                              ((this.
                                  elem44883) + (((int)(t44885))));
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
((x10.regionarray.DistArray<x10.core.Int>)this.
                                                                                                                                                                                                         black).$set__1x10$regionarray$DistArray$$T$G((long)(this.
                                                                                                                                                                                                                                                               destId44876),
                                                                                                                                                                                                                                                      x10.core.Int.$box(t44886));
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
int __lowerer__var__1__ =
                              (x10.core.Int.$unbox(x10.lang.Runtime.<x10.core.Int> wrapAtChecked$G(x10.rtt.Types.INT, ((java.lang.Throwable)(__lowerer__var__0__)))));
                        }
                    }
                    
                    public x10.regionarray.DistArray<x10.core.Int> red;
                    public long destId44876;
                    public int elem44883;
                    public x10.regionarray.DistArray<x10.core.Int> black;
                    
                    public $Closure$28(final x10.regionarray.DistArray<x10.core.Int> red,
                                       final long destId44876,
                                       final int elem44883,
                                       final x10.regionarray.DistArray<x10.core.Int> black, __0$1x10$lang$Int$2__3$1x10$lang$Int$2 $dummy) { {
                                                                                                                                                    this.red = ((x10.regionarray.DistArray)(red));
                                                                                                                                                    this.destId44876 = destId44876;
                                                                                                                                                    this.elem44883 = elem44883;
                                                                                                                                                    this.black = ((x10.regionarray.DistArray)(black));
                                                                                                                                                }}
                    // synthetic type for parameter mangling
                    public static final class __0$1x10$lang$Int$2__3$1x10$lang$Int$2 {}
                    
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$29 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$29> $RTT = x10.rtt.StaticVoidFunType.<$Closure$29> make(
                $Closure$29.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(AllReduceClock.$Closure$29 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$29.class + " calling"); } 
                    $_obj.phases = $deserializer.readInt();
                    $_obj.p44897 = $deserializer.readRef();
                    $_obj.P = $deserializer.readLong();
                    $_obj.black = $deserializer.readRef();
                    $_obj.red = $deserializer.readRef();
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    AllReduceClock.$Closure$29 $_obj = new AllReduceClock.$Closure$29((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    $serializer.write(this.phases);
                    if (p44897 instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.p44897);
                    } else {
                    $serializer.write(this.p44897);
                    }
                    $serializer.write(this.P);
                    if (black instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.black);
                    } else {
                    $serializer.write(this.black);
                    }
                    if (red instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.red);
                    } else {
                    $serializer.write(this.red);
                    }
                    
                }
                
                // constructor just for allocation
                public $Closure$29(final java.lang.System[] $dummy) { 
                }
                
                    
                    public void
                      $apply(
                      ){
                        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
try {{
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
int shift_44898 =
                              1;
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int i44816min44895 =
                              0;
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int i44816max44896 =
                              ((this.
                                  phases) - (((int)(1))));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
int i44892 =
                              i44816min44895;
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
for (;
                                                                                                                                                                  true;
                                                                                                                                                                  ) {
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44893 =
                                  i44892;
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final boolean t44894 =
                                  ((t44893) <= (((int)(i44816max44896))));
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
if (!(t44894)) {
                                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
break;
                                }
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int phase44889 =
                                  i44892;
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final boolean e44871 =
                                  AllReduceClock.even$O((int)(phase44889));
                                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long t44872 =
                                  this.
                                    p44897.
                                    id;
                                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44873 =
                                  shift_44898;
                                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long t44874 =
                                  ((long)(((int)(t44873))));
                                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long t44875 =
                                  ((t44872) + (((long)(t44874))));
                                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long destId44876 =
                                  ((t44875) % (((long)(this.
                                                         P))));
                                
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
if (e44871) {
                                    
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long t44877 =
                                      this.
                                        p44897.
                                        id;
                                    
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int elem44878 =
                                      x10.core.Int.$unbox(((x10.regionarray.DistArray<x10.core.Int>)this.
                                                                                                      black).$apply$G((long)(t44877)));
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.lang.Place t44879 =
                                      ((x10.lang.Place)(x10.lang.Place.place((long)(destId44876))));
                                    {
                                        
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10.lang.Runtime.runAt(((x10.lang.Place)(t44879)),
                                                                                                                                                                                                ((x10.core.fun.VoidFun_0_0)(new AllReduceClock.$Closure$27(((x10.regionarray.DistArray)(this.
                                                                                                                                                                                                                                                                                          black)),
                                                                                                                                                                                                                                                           destId44876,
                                                                                                                                                                                                                                                           elem44878,
                                                                                                                                                                                                                                                           ((x10.regionarray.DistArray)(this.
                                                                                                                                                                                                                                                                                          red)), (AllReduceClock.$Closure$27.__0$1x10$lang$Int$2__3$1x10$lang$Int$2) null))),
                                                                                                                                                                                                ((x10.lang.Runtime.Profile)(null)));
                                    }
                                } else {
                                    
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long t44882 =
                                      this.
                                        p44897.
                                        id;
                                    
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int elem44883 =
                                      x10.core.Int.$unbox(((x10.regionarray.DistArray<x10.core.Int>)this.
                                                                                                      red).$apply$G((long)(t44882)));
                                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final x10.lang.Place t44884 =
                                      ((x10.lang.Place)(x10.lang.Place.place((long)(destId44876))));
                                    {
                                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10.lang.Runtime.runAt(((x10.lang.Place)(t44884)),
                                                                                                                                                                                                ((x10.core.fun.VoidFun_0_0)(new AllReduceClock.$Closure$28(((x10.regionarray.DistArray)(this.
                                                                                                                                                                                                                                                                                          red)),
                                                                                                                                                                                                                                                           destId44876,
                                                                                                                                                                                                                                                           elem44883,
                                                                                                                                                                                                                                                           ((x10.regionarray.DistArray)(this.
                                                                                                                                                                                                                                                                                          black)), (AllReduceClock.$Closure$28.__0$1x10$lang$Int$2__3$1x10$lang$Int$2) null))),
                                                                                                                                                                                                ((x10.lang.Runtime.Profile)(null)));
                                    }
                                }
                                
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44887 =
                                  shift_44898;
                                
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44888 =
                                  ((t44887) * (((int)(2))));
                                
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
shift_44898 = t44888;
                                
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
x10.lang.Clock.advanceAll();
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44890 =
                                  i44892;
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44891 =
                                  ((t44890) + (((int)(1))));
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
i44892 = t44891;
                            }
                        }}catch (java.lang.Error __lowerer__var__0__) {
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
throw __lowerer__var__0__;
                        }catch (java.lang.Throwable __lowerer__var__1__) {
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                        }
                    }
                    
                    public int phases;
                    public x10.lang.Place p44897;
                    public long P;
                    public x10.regionarray.DistArray<x10.core.Int> black;
                    public x10.regionarray.DistArray<x10.core.Int> red;
                    
                    public $Closure$29(final int phases,
                                       final x10.lang.Place p44897,
                                       final long P,
                                       final x10.regionarray.DistArray<x10.core.Int> black,
                                       final x10.regionarray.DistArray<x10.core.Int> red, __3$1x10$lang$Int$2__4$1x10$lang$Int$2 $dummy) { {
                                                                                                                                                  this.phases = phases;
                                                                                                                                                  this.p44897 = ((x10.lang.Place)(p44897));
                                                                                                                                                  this.P = P;
                                                                                                                                                  this.black = ((x10.regionarray.DistArray)(black));
                                                                                                                                                  this.red = ((x10.regionarray.DistArray)(red));
                                                                                                                                              }}
                    // synthetic type for parameter mangling
                    public static final class __3$1x10$lang$Int$2__4$1x10$lang$Int$2 {}
                    
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$30 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$30> $RTT = x10.rtt.StaticFunType.<$Closure$30> make(
                $Closure$30.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.lang.Point.$RTT, x10.rtt.Types.INT)}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(AllReduceClock.$Closure$30 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$30.class + " calling"); } 
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    AllReduceClock.$Closure$30 $_obj = new AllReduceClock.$Closure$30((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    
                }
                
                // constructor just for allocation
                public $Closure$30(final java.lang.System[] $dummy) { 
                }
                // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                return x10.core.Int.$box($apply$O((x10.lang.Point)a1));
                }
                // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1) {
                return $apply$O((x10.lang.Point)a1);
                }
                
                    
                    public int
                      $apply$O(
                      final x10.lang.Point p){
                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final long t44865 =
                          p.$apply$O((long)(0L));
                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
final int t44866 =
                          ((int)(long)(((long)(t44865))));
                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
return t44866;
                    }
                    
                    public $Closure$30() { {
                                                  
                                              }}
                    
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$31 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$31> $RTT = x10.rtt.StaticFunType.<$Closure$31> make(
                $Closure$31.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.lang.Point.$RTT, x10.rtt.Types.INT)}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static x10.serialization.X10JavaSerializable $_deserialize_body(AllReduceClock.$Closure$31 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$31.class + " calling"); } 
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    AllReduceClock.$Closure$31 $_obj = new AllReduceClock.$Closure$31((java.lang.System[]) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    
                }
                
                // constructor just for allocation
                public $Closure$31(final java.lang.System[] $dummy) { 
                }
                // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                return x10.core.Int.$box($apply$O((x10.lang.Point)a1));
                }
                // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1) {
                return $apply$O((x10.lang.Point)a1);
                }
                
                    
                    public int
                      $apply$O(
                      final x10.lang.Point id$18814){
                        
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/AllReduceClock.x10"
return 0;
                    }
                    
                    public $Closure$31() { {
                                                  
                                              }}
                    
                }
                
            
            }
            
            