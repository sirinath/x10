
@x10.runtime.impl.java.X10Generated public class HistogramAtomicInteger extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<HistogramAtomicInteger> $RTT = x10.rtt.NamedType.<HistogramAtomicInteger> make(
    "HistogramAtomicInteger", HistogramAtomicInteger.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(HistogramAtomicInteger $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + HistogramAtomicInteger.class + " calling"); } 
        $_obj.data = $deserializer.readRef();
        $_obj.size = $deserializer.readInt();
        $_obj.maxVal = $deserializer.readInt();
        $_obj.serialHistogram = $deserializer.readRef();
        $_obj.parallelHistogram = $deserializer.readRef();
        $_obj.numAsyncs = $deserializer.readInt();
        $_obj.populateTime = $deserializer.readLong();
        $_obj.serialTime = $deserializer.readLong();
        $_obj.parallelTime = $deserializer.readLong();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        HistogramAtomicInteger $_obj = new HistogramAtomicInteger((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        if (data instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.data);
        } else {
        $serializer.write(this.data);
        }
        $serializer.write(this.size);
        $serializer.write(this.maxVal);
        if (serialHistogram instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.serialHistogram);
        } else {
        $serializer.write(this.serialHistogram);
        }
        if (parallelHistogram instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.parallelHistogram);
        } else {
        $serializer.write(this.parallelHistogram);
        }
        $serializer.write(this.numAsyncs);
        $serializer.write(this.populateTime);
        $serializer.write(this.serialTime);
        $serializer.write(this.parallelTime);
        
    }
    
    // constructor just for allocation
    public HistogramAtomicInteger(final java.lang.System[] $dummy) { 
    }
    
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public x10.core.Rail<x10.core.Int> data;
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public int size;
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public int maxVal;
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public x10.core.Rail<x10.core.Int> serialHistogram;
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public x10.core.Rail<x10.core.concurrent.AtomicInteger> parallelHistogram;
        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public int numAsyncs;
        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public long populateTime;
        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public long serialTime;
        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public long parallelTime;
        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final public static long M = 1000000L;
        
        
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
// creation method for java code (1-phase java constructor)
        public HistogramAtomicInteger(final int size,
                                      final int maxVal,
                                      final int numAsyncs){this((java.lang.System[]) null);
                                                               HistogramAtomicInteger$$init$S(size,maxVal,numAsyncs);}
        
        // constructor for non-virtual call
        final public HistogramAtomicInteger HistogramAtomicInteger$$init$S(final int size,
                                                                           final int maxVal,
                                                                           final int numAsyncs) { {
                                                                                                         
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
;
                                                                                                         
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"

                                                                                                         
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.__fieldInitializers_HistogramAtomicInteger();
                                                                                                         
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
assert ((((long)(((int)(size))))) >= (((long)(1L))));
                                                                                                         
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
assert ((((long)(((int)(maxVal))))) >= (((long)(0L))));
                                                                                                         
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
assert ((((long)(((int)(numAsyncs))))) >= (((long)(1L))));
                                                                                                         
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.size = size;
                                                                                                         
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.maxVal = maxVal;
                                                                                                         
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.numAsyncs = numAsyncs;
                                                                                                         
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42916 =
                                                                                                           ((long)(((int)(size))));
                                                                                                         
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t42917 =
                                                                                                           ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, t42916,
                                                                                                                                                            (x10.core.Int.$box(0)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                                         
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.data = ((x10.core.Rail)(t42917));
                                                                                                         
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42918 =
                                                                                                           ((long)(((int)(maxVal))));
                                                                                                         
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t42919 =
                                                                                                           ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, t42918,
                                                                                                                                                            (x10.core.Int.$box(0)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                                         
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.serialHistogram = ((x10.core.Rail)(t42919));
                                                                                                         
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42921 =
                                                                                                           ((long)(((int)(maxVal))));
                                                                                                         
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.fun.Fun_0_1 t42922 =
                                                                                                           ((x10.core.fun.Fun_0_1)(new HistogramAtomicInteger.$Closure$6()));
                                                                                                         
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t42923 =
                                                                                                           ((x10.core.Rail)(new x10.core.Rail<x10.core.concurrent.AtomicInteger>(x10.core.concurrent.AtomicInteger.$RTT, t42921,
                                                                                                                                                                                 ((x10.core.fun.Fun_0_1)(t42922)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                         
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.parallelHistogram = ((x10.core.Rail)(t42923));
                                                                                                     }
                                                                                                     return this;
                                                                                                     }
        
        
        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public void
                                                                                                                                                   run(
                                                                                                                                                   ){
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.io.Printer t42924 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t42924.println(((java.lang.Object)("\t[computing histogram serially]")));
            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.serialCompute();
            
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.io.Printer t42925 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t42925.println(((java.lang.Object)("\t[computing histogram in parallel]")));
            
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.parallelCompute();
            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.io.Printer t42926 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t42926.println(((java.lang.Object)("\t[checking]")));
            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.check();
            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.reset();
        }
        
        
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public void
                                                                                                                                                   populate(
                                                                                                                                                   ){
            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long time =
              x10.lang.System.nanoTime$O();
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.util.Random r =
              ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S(((long)(time)))));
            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail data_ =
              ((x10.core.Rail)(this.
                                 data));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42809min43066 =
              0L;
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43067 =
              ((x10.core.Rail)(data));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43068 =
              ((x10.core.Rail<x10.core.Int>)t43067).
                size;
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42809max43069 =
              ((t43068) - (((long)(1L))));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
long i43063 =
              i42809min43066;
            {
                
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int[] data_$value43149 =
                  ((int[])data_.value);
                
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
for (;
                                                                                                                                                              true;
                                                                                                                                                              ) {
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43064 =
                      i43063;
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43065 =
                      ((t43064) <= (((long)(i42809max43069))));
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (!(t43065)) {
                        
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
break;
                    }
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i43060 =
                      i43063;
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43058 =
                      maxVal;
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43059 =
                      r.nextInt$O((int)(t43058));
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
data_$value43149[(int)i43060]=t43059;
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43061 =
                      i43063;
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43062 =
                      ((t43061) + (((long)(1L))));
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
i43063 = t43062;
                }
            }
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final HistogramAtomicInteger x42904 =
              ((HistogramAtomicInteger)(this));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42936 =
              x10.lang.System.nanoTime$O();
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42937 =
              ((t42936) - (((long)(time))));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long y42905 =
              ((t42937) / (((long)(1000000L))));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42938 =
              x42904.
                populateTime;
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42939 =
              ((t42938) + (((long)(y42905))));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
x42904.populateTime = t42939;
        }
        
        
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public void
                                                                                                                                                   serialCompute(
                                                                                                                                                   ){
            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long time =
              x10.lang.System.nanoTime$O();
            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42825min43083 =
              0L;
            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43084 =
              ((x10.core.Rail)(data));
            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43085 =
              ((x10.core.Rail<x10.core.Int>)t43084).
                size;
            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42825max43086 =
              ((t43085) - (((long)(1L))));
            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
long i43080 =
              i42825min43083;
            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
for (;
                                                                                                                                                          true;
                                                                                                                                                          ) {
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43081 =
                  i43080;
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43082 =
                  ((t43081) <= (((long)(i42825max43086))));
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (!(t43082)) {
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
break;
                }
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i43077 =
                  i43080;
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail x43072 =
                  ((x10.core.Rail)(serialHistogram));
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43073 =
                  ((x10.core.Rail)(data));
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43074 =
                  ((int[])t43073.value)[(int)i43077];
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long y43075 =
                  ((long)(((int)(t43074))));
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
;
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
int ret43076 =
                   0;
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43070 =
                  ((int[])x43072.value)[(int)y43075];
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int r43071 =
                  ((t43070) + (((int)(1))));
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
((int[])x43072.value)[(int)y43075] = r43071;
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
ret43076 = r43071;
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43078 =
                  i43080;
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43079 =
                  ((t43078) + (((long)(1L))));
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
i43080 = t43079;
            }
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final HistogramAtomicInteger x42912 =
              ((HistogramAtomicInteger)(this));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42950 =
              x10.lang.System.nanoTime$O();
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42951 =
              ((t42950) - (((long)(time))));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long y42913 =
              ((t42951) / (((long)(1000000L))));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42952 =
              x42912.
                serialTime;
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42953 =
              ((t42952) + (((long)(y42913))));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
x42912.serialTime = t42953;
        }
        
        
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public void
                                                                                                                                                   parallelCompute(
                                                                                                                                                   ){
            
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long time =
              x10.lang.System.nanoTime$O();
            {
                
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
x10.lang.Runtime.ensureNotInAtomic();
                
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.lang.FinishState x10$__var2 =
                  x10.lang.Runtime.startFinish();
                
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
try {{
                    {
                        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42841min42842 =
                          0L;
                        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t42954 =
                          numAsyncs;
                        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42955 =
                          ((long)(((int)(t42954))));
                        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42841max42843 =
                          ((t42955) - (((long)(1L))));
                        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
long i43090 =
                          i42841min42842;
                        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
for (;
                                                                                                                                                                      true;
                                                                                                                                                                      ) {
                            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43091 =
                              i43090;
                            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43092 =
                              ((t43091) <= (((long)(i42841max42843))));
                            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (!(t43092)) {
                                
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
break;
                            }
                            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i43087 =
                              i43090;
                            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new HistogramAtomicInteger.$Closure$7(this,
                                                                                                                                                                                                                                                                 i43087))));
                            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43088 =
                              i43090;
                            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43089 =
                              ((t43088) + (((long)(1L))));
                            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
i43090 = t43089;
                        }
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var2)));
                 }}
                }
            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final HistogramAtomicInteger x42914 =
              ((HistogramAtomicInteger)(this));
            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42961 =
              x10.lang.System.nanoTime$O();
            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42962 =
              ((t42961) - (((long)(time))));
            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long y42915 =
              ((t42962) / (((long)(1000000L))));
            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42963 =
              x42914.
                parallelTime;
            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42964 =
              ((t42963) + (((long)(y42915))));
            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
x42914.parallelTime = t42964;
            }
        
        
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public void
                                                                                                                                                   chunkCompute(
                                                                                                                                                   final long id){
            
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t42965 =
              size;
            
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t42966 =
              numAsyncs;
            
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int chunkSize =
              ((t42965) / (((int)(t42966))));
            
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42967 =
              ((long)(((int)(chunkSize))));
            
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long start =
              ((t42967) * (((long)(id))));
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t42968 =
              numAsyncs;
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42969 =
              ((long)(((int)(t42968))));
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42970 =
              ((t42969) - (((long)(1L))));
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t42975 =
              ((long) id) ==
            ((long) t42970);
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
long t42976 =
               0;
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (t42975) {
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t42971 =
                  size;
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42972 =
                  ((long)(((int)(t42971))));
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t42976 = ((t42972) - (((long)(1L))));
            } else {
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42973 =
                  ((long)(((int)(chunkSize))));
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42974 =
                  ((start) + (((long)(t42973))));
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t42976 = ((t42974) - (((long)(1L))));
            }
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long end =
              t42976;
            
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail data_ =
              ((x10.core.Rail)(this.
                                 data));
            
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
long i43100 =
              start;
            {
                
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int[] data_$value43150 =
                  ((int[])data_.value);
                
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
for (;
                                                                                                                                                              true;
                                                                                                                                                              ) {
                    
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43101 =
                      i43100;
                    
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43102 =
                      ((t43101) <= (((long)(end))));
                    
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (!(t43102)) {
                        
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
break;
                    }
                    
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43093 =
                      ((x10.core.Rail)(parallelHistogram));
                    
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43094 =
                      i43100;
                    
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43095 =
                      ((int)data_$value43150[(int)t43094]);
                    
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43096 =
                      ((long)(((int)(t43095))));
                    
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.concurrent.AtomicInteger t43097 =
                      ((x10.core.concurrent.AtomicInteger[])t43093.value)[(int)t43096];
                    
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
x10.runtime.impl.java.EvalUtils.eval(t43097.incrementAndGet());
                    
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43098 =
                      i43100;
                    
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43099 =
                      ((t43098) + (((long)(1L))));
                    
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
i43100 = t43099;
                }
            }
        }
        
        
//#line 79 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public void
                                                                                                                                                   check(
                                                                                                                                                   ){
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42857min42858 =
              0L;
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t42987 =
              maxVal;
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t42988 =
              ((long)(((int)(t42987))));
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42857max42859 =
              ((t42988) - (((long)(1L))));
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
long i43122 =
              i42857min42858;
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
for (;
                                                                                                                                                          true;
                                                                                                                                                          ) {
                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43123 =
                  i43122;
                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43124 =
                  ((t43123) <= (((long)(i42857max42859))));
                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (!(t43124)) {
                    
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
break;
                }
                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i43119 =
                  i43122;
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43103 =
                  ((x10.core.Rail)(parallelHistogram));
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.concurrent.AtomicInteger t43104 =
                  ((x10.core.concurrent.AtomicInteger[])t43103.value)[(int)i43119];
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43105 =
                  t43104.get();
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43106 =
                  ((x10.core.Rail)(serialHistogram));
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43107 =
                  ((int[])t43106.value)[(int)i43119];
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43108 =
                  ((int) t43105) !=
                ((int) t43107);
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (t43108) {
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.io.Printer t43109 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43110 =
                      (("Error: Incorrect count of ") + ((x10.core.Long.$box(i43119))));
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43111 =
                      ((t43110) + (": expected "));
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43112 =
                      ((x10.core.Rail)(serialHistogram));
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43113 =
                      ((int[])t43112.value)[(int)i43119];
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43114 =
                      ((t43111) + ((x10.core.Int.$box(t43113))));
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43115 =
                      ((t43114) + (", found "));
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43116 =
                      ((x10.core.Rail)(parallelHistogram));
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.concurrent.AtomicInteger t43117 =
                      ((x10.core.concurrent.AtomicInteger[])t43116.value)[(int)i43119];
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43118 =
                      ((t43115) + (t43117));
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t43109.println(((java.lang.Object)(t43118)));
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
assert false;
                }
                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43120 =
                  i43122;
                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43121 =
                  ((t43120) + (((long)(1L))));
                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
i43122 = t43121;
            }
        }
        
        
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public long
                                                                                                                                                   populateTime$O(
                                                                                                                                                   ){
            
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43010 =
              populateTime;
            
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
return t43010;
        }
        
        
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public long
                                                                                                                                                   serialTime$O(
                                                                                                                                                   ){
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43011 =
              serialTime;
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
return t43011;
        }
        
        
//#line 93 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public long
                                                                                                                                                   parallelTime$O(
                                                                                                                                                   ){
            
//#line 93 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43012 =
              parallelTime;
            
//#line 93 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
return t43012;
        }
        
        
//#line 95 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public void
                                                                                                                                                   timerReset(
                                                                                                                                                   ){
            
//#line 96 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.serialTime = 0L;
            
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.parallelTime = 0L;
        }
        
        
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public void
                                                                                                                                                    reset(
                                                                                                                                                    ){
            
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42873min42874 =
              0L;
            
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43013 =
              ((x10.core.Rail)(serialHistogram));
            
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43014 =
              ((x10.core.Rail<x10.core.Int>)t43013).
                size;
            
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42873max42875 =
              ((t43014) - (((long)(1L))));
            
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
long i43131 =
              i42873min42874;
            
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
for (;
                                                                                                                                                           true;
                                                                                                                                                           ) {
                
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43132 =
                  i43131;
                
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43133 =
                  ((t43132) <= (((long)(i42873max42875))));
                
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (!(t43133)) {
                    
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
break;
                }
                
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i43128 =
                  i43131;
                
//#line 102 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43125 =
                  ((x10.core.Rail)(serialHistogram));
                
//#line 102 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
((int[])t43125.value)[(int)i43128] = 0;
                
//#line 103 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.Rail t43126 =
                  ((x10.core.Rail)(parallelHistogram));
                
//#line 103 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.concurrent.AtomicInteger t43127 =
                  ((x10.core.concurrent.AtomicInteger[])t43126.value)[(int)i43128];
                
//#line 103 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t43127.set(((int)(0)));
                
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43129 =
                  i43131;
                
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43130 =
                  ((t43129) + (((long)(1L))));
                
//#line 101 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
i43131 = t43130;
            }
        }
        
        
//#line 110 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        HistogramAtomicInteger.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 111 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43023 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 111 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43025 =
              ((t43023) < (((long)(4L))));
            
//#line 111 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (t43025) {
                
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.io.Printer t43024 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t43024.println(((java.lang.Object)("Usage: Histogram <size of data rail> <max data value> <number of asyncs> <number of invocations>")));
                
//#line 113 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
return;
            }
            
//#line 115 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43026 =
              ((java.lang.String[])args.value)[(int)0L];
            
//#line 115 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43029 =
              java.lang.Integer.parseInt(t43026);
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43027 =
              ((java.lang.String[])args.value)[(int)1L];
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43030 =
              java.lang.Integer.parseInt(t43027);
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43028 =
              ((java.lang.String[])args.value)[(int)2L];
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int t43031 =
              java.lang.Integer.parseInt(t43028);
            
//#line 115 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final HistogramAtomicInteger histogram =
              ((HistogramAtomicInteger)(new HistogramAtomicInteger((java.lang.System[]) null).HistogramAtomicInteger$$init$S(t43029,
                                                                                                                             t43030,
                                                                                                                             t43031)));
            
//#line 120 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.io.Printer t43032 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 120 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t43032.println(((java.lang.Object)("[Warm up run]")));
            
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
histogram.populate();
            
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
histogram.run();
            
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
histogram.timerReset();
            
//#line 126 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43033 =
              ((java.lang.String[])args.value)[(int)3L];
            
//#line 126 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final int numInvoke =
              java.lang.Integer.parseInt(t43033);
            
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42889min43143 =
              1L;
            
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i42889max43144 =
              ((long)(((int)(numInvoke))));
            
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
long i43140 =
              i42889min43143;
            
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
for (;
                                                                                                                                                           true;
                                                                                                                                                           ) {
                
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43141 =
                  i43140;
                
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final boolean t43142 =
                  ((t43141) <= (((long)(i42889max43144))));
                
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
if (!(t43142)) {
                    
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
break;
                }
                
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long i43137 =
                  i43140;
                
//#line 128 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.io.Printer t43134 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 128 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43135 =
                  (("[Trial #") + ((x10.core.Long.$box(i43137))));
                
//#line 128 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43136 =
                  ((t43135) + ("]"));
                
//#line 128 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t43134.println(((java.lang.Object)(t43136)));
                
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
histogram.run();
                
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43138 =
                  i43140;
                
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43139 =
                  ((t43138) + (((long)(1L))));
                
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
i43140 = t43139;
            }
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.io.Printer t43056 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43042 =
              (("[Done.] Over ") + ((x10.core.Int.$box(numInvoke))));
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43043 =
              ((t43042) + (" trials, average time"));
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43044 =
              ((t43043) + (" to populate is "));
            
//#line 132 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43045 =
              histogram.populateTime$O();
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43046 =
              ((t43044) + ((x10.core.Long.$box(t43045))));
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43049 =
              ((t43046) + (", to compute serially is "));
            
//#line 133 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43047 =
              histogram.serialTime$O();
            
//#line 133 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43048 =
              ((long)(((int)(numInvoke))));
            
//#line 133 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43050 =
              ((t43047) / (((long)(t43048))));
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43051 =
              ((t43049) + ((x10.core.Long.$box(t43050))));
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43054 =
              ((t43051) + (", and to compute in parallel is "));
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43052 =
              histogram.parallelTime$O();
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43053 =
              ((long)(((int)(numInvoke))));
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final long t43055 =
              ((t43052) / (((long)(t43053))));
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final java.lang.String t43057 =
              ((t43054) + ((x10.core.Long.$box(t43055))));
            
//#line 131 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
t43056.println(((java.lang.Object)(t43057)));
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final public HistogramAtomicInteger
                                                                                                                                                  HistogramAtomicInteger$$this$HistogramAtomicInteger(
                                                                                                                                                  ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
return HistogramAtomicInteger.this;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final public void
                                                                                                                                                  __fieldInitializers_HistogramAtomicInteger(
                                                                                                                                                  ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.data = null;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.serialHistogram = null;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.parallelHistogram = null;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.populateTime = 0L;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.serialTime = 0L;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.parallelTime = 0L;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$6 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$6> $RTT = x10.rtt.StaticFunType.<$Closure$6> make(
            $Closure$6.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.core.concurrent.AtomicInteger.$RTT)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(HistogramAtomicInteger.$Closure$6 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$6.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                HistogramAtomicInteger.$Closure$6 $_obj = new HistogramAtomicInteger.$Closure$6((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$6(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply(x10.core.Long.$unbox(a1));
            }
            
                
                public x10.core.concurrent.AtomicInteger
                  $apply(
                  final long i){
                    
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
final x10.core.concurrent.AtomicInteger t42920 =
                      ((x10.core.concurrent.AtomicInteger)(new x10.core.concurrent.AtomicInteger(((int)(0)))));
                    
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
return t42920;
                }
                
                public $Closure$6() { {
                                             
                                         }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$7 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$7> $RTT = x10.rtt.StaticVoidFunType.<$Closure$7> make(
            $Closure$7.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(HistogramAtomicInteger.$Closure$7 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$7.class + " calling"); } 
                $_obj.out$$ = $deserializer.readRef();
                $_obj.i43087 = $deserializer.readLong();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                HistogramAtomicInteger.$Closure$7 $_obj = new HistogramAtomicInteger.$Closure$7((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                $serializer.write(this.i43087);
                
            }
            
            // constructor just for allocation
            public $Closure$7(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
try {{
                        
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
this.
                                                                                                                                                                   out$$.chunkCompute((long)(this.
                                                                                                                                                                                               i43087));
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/HistogramAtomicInteger.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public HistogramAtomicInteger out$$;
                public long i43087;
                
                public $Closure$7(final HistogramAtomicInteger out$$,
                                  final long i43087) { {
                                                              this.out$$ = out$$;
                                                              this.i43087 = i43087;
                                                          }}
                
            }
            
        
        }
        
        