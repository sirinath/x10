
@x10.runtime.impl.java.X10Generated public class Count3sInArray extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Count3sInArray> $RTT = x10.rtt.NamedType.<Count3sInArray> make(
    "Count3sInArray", Count3sInArray.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(Count3sInArray $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Count3sInArray.class + " calling"); } 
        $_obj.size = $deserializer.readInt();
        $_obj.pctThrees = $deserializer.readDouble();
        $_obj.numAsyncs = $deserializer.readInt();
        $_obj.a = $deserializer.readRef();
        $_obj.count = $deserializer.readInt();
        $_obj.expectedCount = $deserializer.readInt();
        $_obj.countTime = $deserializer.readLong();
        $_obj.populateTime = $deserializer.readLong();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        Count3sInArray $_obj = new Count3sInArray((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write(this.size);
        $serializer.write(this.pctThrees);
        $serializer.write(this.numAsyncs);
        if (a instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.a);
        } else {
        $serializer.write(this.a);
        }
        $serializer.write(this.count);
        $serializer.write(this.expectedCount);
        $serializer.write(this.countTime);
        $serializer.write(this.populateTime);
        
    }
    
    // constructor just for allocation
    public Count3sInArray(final java.lang.System[] $dummy) { 
    }
    
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final public static int Meg = 1000000;
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final public static java.lang.String indent = ((java.lang.String)("\t"));
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public int size;
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public double pctThrees;
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public int numAsyncs;
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public x10.core.Rail<x10.core.Int> a;
        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public int count;
        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public int expectedCount;
        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public long countTime;
        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public long populateTime;
        
        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
// creation method for java code (1-phase java constructor)
        public Count3sInArray(final int size,
                              final int numAsyncs,
                              final double pctThrees){this((java.lang.System[]) null);
                                                          Count3sInArray$$init$S(size,numAsyncs,pctThrees);}
        
        // constructor for non-virtual call
        final public Count3sInArray Count3sInArray$$init$S(final int size,
                                                           final int numAsyncs,
                                                           final double pctThrees) { {
                                                                                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
;
                                                                                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"

                                                                                            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.__fieldInitializers_Count3sInArray();
                                                                                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
assert ((numAsyncs) >= (((int)(1))));
                                                                                            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.size = size;
                                                                                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.numAsyncs = numAsyncs;
                                                                                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55000 =
                                                                                              ((long)(((int)(size))));
                                                                                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.core.Rail t55001 =
                                                                                              ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, t55000,
                                                                                                                                               (x10.core.Int.$box(0)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.a = ((x10.core.Rail)(t55001));
                                                                                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.pctThrees = pctThrees;
                                                                                        }
                                                                                        return this;
                                                                                        }
        
        
        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public void
                                                                                                                                           run(
                                                                                                                                           ){
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.populate();
            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long time =
              x10.lang.System.nanoTime$O();
            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.count = 0;
            
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55002 =
              numAsyncs;
            
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55012 =
              ((int) t55002) ==
            ((int) 1);
            
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (t55012) {
                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55003 =
                  this.count3s$O();
                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.count = t55003;
            } else {
                {
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x10.lang.Runtime.ensureNotInAtomic();
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.lang.FinishState x10$__var25 =
                      x10.lang.Runtime.startFinish();
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
try {{
                        {
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int i54943min54944 =
                              0;
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55004 =
                              numAsyncs;
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int i54943max54945 =
                              ((t55004) - (((int)(1))));
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
int i55203 =
                              i54943min54944;
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
for (;
                                                                                                                                                                  true;
                                                                                                                                                                  ) {
                                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55204 =
                                  i55203;
                                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55205 =
                                  ((t55204) <= (((int)(i54943max54945))));
                                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (!(t55205)) {
                                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
break;
                                }
                                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int i55200 =
                                  i55203;
                                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new Count3sInArray.$Closure$127(this,
                                                                                                                                                                                                                                                       i55200))));
                                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55201 =
                                  i55203;
                                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55202 =
                                  ((t55201) + (((int)(1))));
                                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
i55203 = t55202;
                            }
                        }
                    }}catch (java.lang.Throwable __lowerer__var__0__) {
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
throw new java.lang.RuntimeException();
                    }finally {{
                         
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var25)));
                     }}
                    }
                }
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final Count3sInArray x54992 =
              ((Count3sInArray)(this));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55013 =
              x10.lang.System.nanoTime$O();
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55014 =
              ((t55013) - (((long)(time))));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55015 =
              ((long)(((int)(1000000))));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long y54993 =
              ((t55014) / (((long)(t55015))));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55016 =
              x54992.
                countTime;
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55017 =
              ((t55016) + (((long)(y54993))));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x54992.countTime = t55017;
            }
        
        
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public void
                                                                                                                                           runB(
                                                                                                                                           ){
            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.populate();
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long time =
              x10.lang.System.nanoTime$O();
            
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.count = 0;
            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55018 =
              numAsyncs;
            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55020 =
              ((int) t55018) ==
            ((int) 1);
            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (t55020) {
                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55019 =
                  this.count3s$O();
                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.count = t55019;
            } else {
                
            }
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final Count3sInArray x54994 =
              ((Count3sInArray)(this));
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55021 =
              x10.lang.System.nanoTime$O();
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55022 =
              ((t55021) - (((long)(time))));
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55023 =
              ((long)(((int)(1000000))));
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long y54995 =
              ((t55022) / (((long)(t55023))));
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55024 =
              x54994.
                countTime;
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55025 =
              ((t55024) + (((long)(y54995))));
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x54994.countTime = t55025;
        }
        
        
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public int
                                                                                                                                           count3s$O(
                                                                                                                                           final int id){
            
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55026 =
              size;
            
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55027 =
              numAsyncs;
            
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int chunkSize =
              ((t55026) / (((int)(t55027))));
            
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int start =
              ((chunkSize) * (((int)(id))));
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55028 =
              numAsyncs;
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55029 =
              ((t55028) - (((int)(1))));
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55032 =
              ((int) id) ==
            ((int) t55029);
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
int t55033 =
               0;
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (t55032) {
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55030 =
                  size;
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55033 = ((t55030) - (((int)(1))));
            } else {
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55031 =
                  ((start) + (((int)(chunkSize))));
                
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55033 = ((t55031) - (((int)(1))));
            }
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int end =
              t55033;
            
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
try {{
                
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x10.lang.Runtime.enterAtomic();
                {
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55041 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55034 =
                      ((java.lang.String)(Count3sInArray.indent));
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55035 =
                      "\t[task ";
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55036 =
                      ((t55035) + ((x10.core.Int.$box(id))));
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55037 =
                      ((t55036) + (" counting from "));
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55038 =
                      ((t55037) + ((x10.core.Int.$box(start))));
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55039 =
                      ((t55038) + (" to "));
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55040 =
                      ((t55039) + ((x10.core.Int.$box(end))));
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55042 =
                      ((t55040) + ("]"));
                    
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55041.println(((java.lang.Object)(t55042)));
                }
            }}finally {{
                  
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x10.lang.Runtime.exitAtomic();
              }}
            
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
int privateCount =
              0;
            
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.core.Rail a_ =
              ((x10.core.Rail)(this.
                                 a));
            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
int i55214 =
              start;
            {
                
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int[] a_$value55269 =
                  ((int[])a_.value);
                
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
for (;
                                                                                                                                                      true;
                                                                                                                                                      ) {
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55215 =
                      i55214;
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55216 =
                      ((t55215) <= (((int)(end))));
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (!(t55216)) {
                        
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
break;
                    }
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55206 =
                      i55214;
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55207 =
                      ((long)(((int)(t55206))));
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55208 =
                      ((int)a_$value55269[(int)t55207]);
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55209 =
                      ((int) t55208) ==
                    ((int) 3);
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (t55209) {
                        
//#line 78 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55210 =
                          privateCount;
                        
//#line 78 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55211 =
                          ((t55210) + (((int)(1))));
                        
//#line 78 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
privateCount = t55211;
                    }
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55212 =
                      i55214;
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55213 =
                      ((t55212) + (((int)(1))));
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
i55214 = t55213;
                }
            }
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55054 =
              privateCount;
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
return t55054;
            }
        
        
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public int
                                                                                                                                           count3s$O(
                                                                                                                                           ){
            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int start =
              0;
            
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55055 =
              size;
            
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int end =
              ((t55055) - (((int)(1))));
            
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
int privateCount =
              0;
            
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.core.Rail a_ =
              ((x10.core.Rail)(this.
                                 a));
            
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
int i55225 =
              start;
            {
                
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int[] a_$value55270 =
                  ((int[])a_.value);
                
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
for (;
                                                                                                                                                      true;
                                                                                                                                                      ) {
                    
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55226 =
                      i55225;
                    
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55227 =
                      ((t55226) <= (((int)(end))));
                    
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (!(t55227)) {
                        
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
break;
                    }
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55217 =
                      i55225;
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55218 =
                      ((long)(((int)(t55217))));
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55219 =
                      ((int)a_$value55270[(int)t55218]);
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55220 =
                      ((int) t55219) ==
                    ((int) 3);
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (t55220) {
                        
//#line 90 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55221 =
                          privateCount;
                        
//#line 90 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55222 =
                          ((t55221) + (((int)(1))));
                        
//#line 90 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
privateCount = t55222;
                    }
                    
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55223 =
                      i55225;
                    
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55224 =
                      ((t55223) + (((int)(1))));
                    
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
i55225 = t55224;
                }
            }
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55067 =
              privateCount;
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
return t55067;
        }
        
        
//#line 94 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public void
                                                                                                                                           populate(
                                                                                                                                           ){
            
//#line 96 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long time =
              x10.lang.System.nanoTime$O();
            
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.util.Random r =
              ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S()));
            
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.expectedCount = 0;
            
//#line 99 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.core.Rail t55068 =
              ((x10.core.Rail)(a));
            
//#line 99 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55069 =
              ((x10.core.Rail<x10.core.Int>)t55068).
                size;
            
//#line 99 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final double t55070 =
              ((double)(long)(((long)(t55069))));
            
//#line 99 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final double t55071 =
              pctThrees;
            
//#line 99 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final double t55072 =
              ((t55070) * (((double)(t55071))));
            
//#line 99 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int target =
              ((int)(double)(((double)(t55072))));
            
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55076 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55073 =
              ((java.lang.String)(Count3sInArray.indent));
            
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55074 =
              "\t[target=";
            
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55075 =
              ((t55074) + ((x10.core.Int.$box(target))));
            
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55077 =
              ((t55075) + ("]"));
            
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55076.println(((java.lang.Object)(t55077)));
            
//#line 103 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.core.Rail a_ =
              ((x10.core.Rail)(this.
                                 a));
            
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long i54959min55246 =
              ((long)(((int)(0))));
            
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.core.Rail t55247 =
              ((x10.core.Rail)(a));
            
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55248 =
              ((x10.core.Rail<x10.core.Int>)t55247).
                size;
            
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55249 =
              ((long)(((int)(1))));
            
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long i54959max55250 =
              ((t55248) - (((long)(t55249))));
            
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
long i55231 =
              i54959min55246;
            {
                
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int[] a_$value55271 =
                  ((int[])a_.value);
                
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
for (;
                                                                                                                                                       true;
                                                                                                                                                       ) {
                    
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55232 =
                      i55231;
                    
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55233 =
                      ((t55232) <= (((long)(i54959max55250))));
                    
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (!(t55233)) {
                        
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
break;
                    }
                    
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long i55228 =
                      i55231;
                    
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
a_$value55271[(int)i55228]=0;
                    
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55229 =
                      i55231;
                    
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55230 =
                      ((t55229) + (((long)(1L))));
                    
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
i55231 = t55230;
                }
            }
            
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
int i55251 =
              1;
            {
                
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int[] a_$value55272 =
                  ((int[])a_.value);
                
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
for (;
                                                                                                                                                       true;
                                                                                                                                                       ) {
                    
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55252 =
                      i55251;
                    
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55253 =
                      ((t55252) <= (((int)(target))));
                    
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (!(t55253)) {
                        
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
break;
                    }
                    
//#line 106 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55234 =
                      ((x10.core.Rail<x10.core.Int>)a_).
                        size;
                    
//#line 106 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55235 =
                      ((int)(long)(((long)(t55234))));
                    
//#line 106 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int index55236 =
                      r.nextInt$O((int)(t55235));
                    
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55237 =
                      ((long)(((int)(index55236))));
                    
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55238 =
                      ((int)a_$value55272[(int)t55237]);
                    
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55239 =
                      ((int) t55238) !=
                    ((int) 3);
                    
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (t55239) {
                        
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55240 =
                          ((long)(((int)(index55236))));
                        
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
a_$value55272[(int)t55240]=3;
                        
//#line 109 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final Count3sInArray x55241 =
                          ((Count3sInArray)(this));
                        
//#line 109 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
;
                        
//#line 109 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55242 =
                          x55241.
                            expectedCount;
                        
//#line 109 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55243 =
                          ((t55242) + (((int)(1))));
                        
//#line 109 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x55241.expectedCount = t55243;
                    }
                    
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55244 =
                      i55251;
                    
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55245 =
                      ((t55244) + (((int)(1))));
                    
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
i55251 = t55245;
                }
            }
            
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final Count3sInArray x54998 =
              ((Count3sInArray)(this));
            
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55099 =
              x10.lang.System.nanoTime$O();
            
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55100 =
              ((t55099) - (((long)(time))));
            
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55101 =
              ((long)(((int)(1000000))));
            
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long y54999 =
              ((t55100) / (((long)(t55101))));
            
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55102 =
              x54998.
                populateTime;
            
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55103 =
              ((t55102) + (((long)(y54999))));
            
//#line 112 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x54998.populateTime = t55103;
        }
        
        
//#line 115 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public void
                                                                                                                                            validate(
                                                                                                                                            ){
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55108 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55104 =
              ((java.lang.String)(Count3sInArray.indent));
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55105 =
              "\t[expectedCount=";
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55106 =
              expectedCount;
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55107 =
              ((t55105) + ((x10.core.Int.$box(t55106))));
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55109 =
              ((t55107) + ("]"));
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55108.println(((java.lang.Object)(t55109)));
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55114 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55110 =
              ((java.lang.String)(Count3sInArray.indent));
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55111 =
              "\t[count=";
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55112 =
              count;
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55113 =
              ((t55111) + ((x10.core.Int.$box(t55112))));
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55115 =
              ((t55113) + ("]"));
            
//#line 117 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55114.println(((java.lang.Object)(t55115)));
            
//#line 118 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55116 =
              expectedCount;
            
//#line 118 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55117 =
              count;
            
//#line 118 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55127 =
              ((int) t55116) !=
            ((int) t55117);
            
//#line 118 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (t55127) {
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55125 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55118 =
                  ((java.lang.String)(Count3sInArray.indent));
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55119 =
                  "\tError: expected ";
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55120 =
                  expectedCount;
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55121 =
                  ((t55119) + ((x10.core.Int.$box(t55120))));
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55122 =
                  ((t55121) + (", obtained "));
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55123 =
                  count;
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55124 =
                  ((t55122) + ((x10.core.Int.$box(t55123))));
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55126 =
                  ((t55124) + ("."));
                
//#line 119 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55125.println(((java.lang.Object)(t55126)));
            }
        }
        
        
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public long
                                                                                                                                            populateTime$O(
                                                                                                                                            ){
            
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55128 =
              populateTime;
            
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
return t55128;
        }
        
        
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public long
                                                                                                                                            countTime$O(
                                                                                                                                            ){
            
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55129 =
              countTime;
            
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
return t55129;
        }
        
        
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public void
                                                                                                                                            reset(
                                                                                                                                            ){
            
//#line 125 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55130 =
              ((long)(((int)(0))));
            
//#line 125 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.populateTime = t55130;
            
//#line 126 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55131 =
              ((long)(((int)(0))));
            
//#line 126 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.countTime = t55131;
            
//#line 127 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.count = 0;
            
//#line 128 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.expectedCount = 0;
        }
        
        
//#line 133 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        Count3sInArray.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55132 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55133 =
              ((long)(((int)(3))));
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55135 =
              ((t55132) < (((long)(t55133))));
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (t55135) {
                
//#line 135 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55134 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 135 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55134.println(((java.lang.Object)("Usage: Count3sInArray <Nmod4: size is 4*Nmod4> <NumAsync:Int> <PctThrees:Double>")));
                
//#line 136 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
return;
            }
            
//#line 138 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55136 =
              ((long)(((int)(0))));
            
//#line 138 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55137 =
              ((java.lang.String[])args.value)[(int)t55136];
            
//#line 138 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int Nmod4 =
              java.lang.Integer.parseInt(t55137);
            
//#line 139 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int size =
              ((Nmod4) * (((int)(4))));
            
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55138 =
              ((long)(((int)(1))));
            
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55139 =
              ((java.lang.String[])args.value)[(int)t55138];
            
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int numAsyncs =
              java.lang.Integer.parseInt(t55139);
            
//#line 141 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55140 =
              ((long)(((int)(2))));
            
//#line 141 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55141 =
              ((java.lang.String[])args.value)[(int)t55140];
            
//#line 141 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final double pctThrees =
              java.lang.Double.parseDouble(t55141);
            
//#line 142 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55143 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 142 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55142 =
              (("[size=") + ((x10.core.Int.$box(size))));
            
//#line 142 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55144 =
              ((t55142) + ("]"));
            
//#line 142 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55143.println(((java.lang.Object)(t55144)));
            
//#line 143 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55146 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 143 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55145 =
              (("[numAsyncs=") + ((x10.core.Int.$box(numAsyncs))));
            
//#line 143 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55147 =
              ((t55145) + ("]"));
            
//#line 143 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55146.println(((java.lang.Object)(t55147)));
            
//#line 144 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55149 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 144 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55148 =
              (("[pctThrees=") + ((x10.core.Double.$box(pctThrees))));
            
//#line 144 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55150 =
              ((t55148) + ("]"));
            
//#line 144 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55149.println(((java.lang.Object)(t55150)));
            
//#line 145 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int numInvoke =
              5;
            
//#line 146 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55151 =
              x10.lang.System.nanoTime$O();
            
//#line 146 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
long createTime =
              (-(t55151));
            
//#line 147 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final Count3sInArray counter =
              ((Count3sInArray)(new Count3sInArray((java.lang.System[]) null).Count3sInArray$$init$S(((int)(size)),
                                                                                                     ((int)(numAsyncs)),
                                                                                                     ((double)(pctThrees)))));
            
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55152 =
              createTime;
            
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55153 =
              x10.lang.System.nanoTime$O();
            
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55154 =
              ((t55152) + (((long)(t55153))));
            
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
createTime = t55154;
            
//#line 149 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55156 =
              createTime;
            
//#line 149 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55155 =
              Count3sInArray.Meg;
            
//#line 149 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55157 =
              ((long)(((int)(t55155))));
            
//#line 149 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55158 =
              ((t55156) / (((long)(t55157))));
            
//#line 149 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
createTime = t55158;
            
//#line 151 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55159 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 151 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55159.println(((java.lang.Object)("[Warming up]")));
            
//#line 152 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
counter.run();
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55170 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55160 =
              ((java.lang.String)(Count3sInArray.indent));
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55161 =
              "\t[Done, creation time=";
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55162 =
              createTime;
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55163 =
              ((t55161) + ((x10.core.Long.$box(t55162))));
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55164 =
              ((t55163) + (" time="));
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55165 =
              counter.populateTime$O();
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55166 =
              ((t55164) + ((x10.core.Long.$box(t55165))));
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55167 =
              ((t55166) + (" + "));
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55168 =
              counter.countTime$O();
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55169 =
              ((t55167) + ((x10.core.Long.$box(t55168))));
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55171 =
              ((t55169) + ("]."));
            
//#line 153 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55170.println(((java.lang.Object)(t55171)));
            
//#line 154 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
counter.validate();
            
//#line 155 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
counter.reset();
            
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55172 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55172.println(((java.lang.Object)("[Running]")));
            
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int i54975min55263 =
              1;
            
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int i54975max55264 =
              numInvoke;
            
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
int i55260 =
              i54975min55263;
            
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55261 =
                  i55260;
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final boolean t55262 =
                  ((t55261) <= (((int)(i54975max55264))));
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
if (!(t55262)) {
                    
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
break;
                }
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int i55257 =
                  i55260;
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55254 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55255 =
                  (("[Trial #") + ((x10.core.Int.$box(i55257))));
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55256 =
                  ((t55255) + ("]"));
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55254.println(((java.lang.Object)(t55256)));
                
//#line 161 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
counter.run();
                
//#line 162 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
counter.validate();
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55258 =
                  i55260;
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55259 =
                  ((t55258) + (((int)(1))));
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
i55260 = t55259;
            }
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final x10.io.Printer t55193 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55181 =
              "[Done. Over 5";
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55182 =
              "[Done. Over 5 trials, average time";
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55185 =
              "[Done. Over 5 trials, average time to populate is  ";
            
//#line 165 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55183 =
              counter.populateTime$O();
            
//#line 165 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55184 =
              ((long)(((int)(numInvoke))));
            
//#line 165 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55186 =
              ((t55183) / (((long)(t55184))));
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55187 =
              ((t55185) + ((x10.core.Long.$box(t55186))));
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55190 =
              ((t55187) + (" and to count is "));
            
//#line 166 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55188 =
              counter.countTime$O();
            
//#line 166 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55189 =
              ((long)(((int)(numInvoke))));
            
//#line 166 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final long t55191 =
              ((t55188) / (((long)(t55189))));
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55192 =
              ((t55190) + ((x10.core.Long.$box(t55191))));
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final java.lang.String t55194 =
              ((t55192) + (".]"));
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
t55193.println(((java.lang.Object)(t55194)));
        }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final public Count3sInArray
                                                                                                                                          Count3sInArray$$this$Count3sInArray(
                                                                                                                                          ){
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
return Count3sInArray.this;
        }
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final public void
                                                                                                                                          __fieldInitializers_Count3sInArray(
                                                                                                                                          ){
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.a = null;
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.count = 0;
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.expectedCount = 0;
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.countTime = 0L;
            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
this.populateTime = 0L;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$127 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$127> $RTT = x10.rtt.StaticVoidFunType.<$Closure$127> make(
            $Closure$127.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(Count3sInArray.$Closure$127 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$127.class + " calling"); } 
                $_obj.out$$ = $deserializer.readRef();
                $_obj.i55200 = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                Count3sInArray.$Closure$127 $_obj = new Count3sInArray.$Closure$127((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                $serializer.write(this.i55200);
                
            }
            
            // constructor just for allocation
            public $Closure$127(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
try {{
                        
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int mc55195 =
                          this.
                            out$$.count3s$O((int)(this.
                                                    i55200));
                        
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
try {{
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x10.lang.Runtime.enterAtomic();
                            {
                                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final Count3sInArray x55196 =
                                  ((Count3sInArray)(this.
                                                      out$$));
                                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int y55197 =
                                  mc55195;
                                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55198 =
                                  x55196.
                                    count;
                                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
final int t55199 =
                                  ((t55198) + (((int)(y55197))));
                                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x55196.count = t55199;
                            }
                        }}finally {{
                              
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
x10.lang.Runtime.exitAtomic();
                          }}
                        }}catch (java.lang.Error __lowerer__var__0__) {
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
throw __lowerer__var__0__;
                        }catch (java.lang.Throwable __lowerer__var__1__) {
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Count3sInArray.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                        }
                    }
                
                public Count3sInArray out$$;
                public int i55200;
                
                public $Closure$127(final Count3sInArray out$$,
                                    final int i55200) { {
                                                               this.out$$ = out$$;
                                                               this.i55200 = i55200;
                                                           }}
                
                }
                
        
        }
        
        