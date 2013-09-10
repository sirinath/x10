package pppp.util;


@x10.runtime.impl.java.X10Generated public class BBuffer<$T>  extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<BBuffer> $RTT = x10.rtt.NamedType.<BBuffer> make(
    "pppp.util.BBuffer", BBuffer.class, 1
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.BBuffer<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + BBuffer.class + " calling"); } 
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.data = $deserializer.readRef();
        $_obj.nextVal = $deserializer.readInt();
        $_obj.size = $deserializer.readInt();
        $_obj.monitor = $deserializer.readRef();
        $_obj.name = $deserializer.readRef();
        $_obj.N = $deserializer.readInt();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.util.BBuffer $_obj = new pppp.util.BBuffer((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        if (data instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.data);
        } else {
        $serializer.write(this.data);
        }
        $serializer.write(this.nextVal);
        $serializer.write(this.size);
        if (monitor instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.monitor);
        } else {
        $serializer.write(this.monitor);
        }
        $serializer.write(this.name);
        $serializer.write(this.N);
        
    }
    
    // constructor just for allocation
    public BBuffer(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    pppp.util.BBuffer.$initParams(this, $T);
    }
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final BBuffer $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public int N;
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public x10.core.Rail<$T> data;
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public int nextVal;
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public int size;
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public pppp.util.Monitor monitor;
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public java.lang.String name;
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
// creation method for java code (1-phase java constructor)
        public BBuffer(final x10.rtt.Type $T,
                       final int N){this((java.lang.System[]) null, $T);
                                        pppp$util$BBuffer$$init$S(N);}
        
        // constructor for non-virtual call
        final public pppp.util.BBuffer<$T> pppp$util$BBuffer$$init$S(final int N) { {
                                                                                           
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final $T t53314 =
                                                                                             (($T)(($T) x10.rtt.Types.zeroValue($T)));
                                                                                           
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
/*this.*/pppp$util$BBuffer$$init$S(((int)(N)),
                                                                                                                                                                                                                                                                  t53314, (pppp.util.BBuffer.__1pppp$util$BBuffer$$T) null);
                                                                                       }
                                                                                       return this;
                                                                                       }
        
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
// creation method for java code (1-phase java constructor)
        public BBuffer(final x10.rtt.Type $T,
                       final int N,
                       final $T t, __1pppp$util$BBuffer$$T $dummy){this((java.lang.System[]) null, $T);
                                                                       pppp$util$BBuffer$$init$S(N,t, (pppp.util.BBuffer.__1pppp$util$BBuffer$$T) null);}
        
        // constructor for non-virtual call
        final public pppp.util.BBuffer<$T> pppp$util$BBuffer$$init$S(final int N,
                                                                     final $T t, __1pppp$util$BBuffer$$T $dummy) { {
                                                                                                                          
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
;
                                                                                                                          
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
this.N = N;
                                                                                                                          
                                                                                                                          
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
this.__fieldInitializers_pppp_util_BBuffer();
                                                                                                                          
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final long t53315 =
                                                                                                                            ((long)(((int)(N))));
                                                                                                                          
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.Rail t53316 =
                                                                                                                            ((x10.core.Rail)(new x10.core.Rail<$T>($T, t53315,
                                                                                                                                                                   (($T)(t)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                                                          
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)this).data = ((x10.core.Rail)(t53316));
                                                                                                                      }
                                                                                                                      return this;
                                                                                                                      }
        
        
        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public boolean
                                                                                                                                              hasSpace$O(
                                                                                                                                              ){
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53317 =
              size;
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53318 =
              N;
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final boolean t53319 =
              ((t53317) < (((int)(t53318))));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53319;
        }
        
        
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public pppp.util.Unit
                                                                                                                                              add__0pppp$util$BBuffer$$T(
                                                                                                                                              final $T t){
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53320 =
              (("Bbuffer: ") + (this));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53321 =
              ((t53320) + (" assigning at "));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53322 =
              nextVal;
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53323 =
              ((t53321) + ((x10.core.Int.$box(t53322))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53324 =
              ((t53323) + (" size= "));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53325 =
              size;
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53326 =
              ((t53324) + ((x10.core.Int.$box(t53325))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
pppp.util.Logger.debug(((java.lang.String)(t53326)));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53327 =
              nextVal;
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53328 =
              size;
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
int nextSlot =
              ((t53327) + (((int)(t53328))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53329 =
              nextSlot;
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53330 =
              N;
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final boolean t53334 =
              ((t53329) >= (((int)(t53330))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
if (t53334) {
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53331 =
                  nextSlot;
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53332 =
                  N;
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53333 =
                  ((t53331) % (((int)(t53332))));
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
nextSlot = t53333;
            }
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.Rail t53336 =
              ((x10.core.Rail)(data));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53335 =
              nextSlot;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final long t53337 =
              ((long)(((int)(t53335))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((x10.core.Rail<$T>)t53336).$set__1x10$lang$Rail$$T$G((long)(t53337),
                                                                                                                                                                                                      (($T)(t)));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.BBuffer x53306 =
              ((pppp.util.BBuffer)(this));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
;
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53338 =
              ((pppp.util.BBuffer<$T>)x53306).
                size;
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53339 =
              ((t53338) + (((int)(1))));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)x53306).size = t53339;
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.fun.Fun_0_0 t53342 =
              ((x10.core.fun.Fun_0_0)(new pppp.util.BBuffer.$Closure$119<$T>($T, ((pppp.util.BBuffer<$T>)(this)), (pppp.util.BBuffer.$Closure$119.__0$1pppp$util$BBuffer$$Closure$119$$T$2) null)));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
pppp.util.Logger.debug__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t53342)));
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.Unit t53343 =
              new pppp.util.Unit((java.lang.System[]) null).pppp$util$Unit$$init$S();
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53343;
        }
        
        
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final public void
                                                                                                                                              setName(
                                                                                                                                              final java.lang.String s){
            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)this).name = ((java.lang.String)(s));
        }
        
        
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public void
                                                                                                                                              $set__0pppp$util$BBuffer$$T(
                                                                                                                                              final $T t){
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.fun.Fun_0_0 t53346 =
              ((x10.core.fun.Fun_0_0)(new pppp.util.BBuffer.$Closure$120<$T>($T, ((pppp.util.BBuffer<$T>)(this)),
                                                                             t, (pppp.util.BBuffer.$Closure$120.__0$1pppp$util$BBuffer$$Closure$120$$T$2__1pppp$util$BBuffer$$Closure$120$$T) null)));
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
pppp.util.Logger.log__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t53346)));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.Monitor t53349 =
              ((pppp.util.Monitor)(monitor));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.fun.Fun_0_0 t53350 =
              ((x10.core.fun.Fun_0_0)(new pppp.util.BBuffer.$Closure$121<$T>($T, ((pppp.util.BBuffer<$T>)(this)), (pppp.util.BBuffer.$Closure$121.__0$1pppp$util$BBuffer$$Closure$121$$T$2) null)));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.fun.Fun_0_0 t53351 =
              ((x10.core.fun.Fun_0_0)(new pppp.util.BBuffer.$Closure$122<$T>($T, ((pppp.util.BBuffer<$T>)(this)),
                                                                             t, (pppp.util.BBuffer.$Closure$122.__0$1pppp$util$BBuffer$$Closure$122$$T$2__1pppp$util$BBuffer$$Closure$122$$T) null)));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.Monitor)t53349).<pppp.util.Unit> on__0$1x10$lang$Boolean$2__1$1pppp$util$Monitor$$T$2$G(pppp.util.Unit.$RTT, ((x10.core.fun.Fun_0_0)(t53350)),
                                                                                                                                                                                                                                                    ((x10.core.fun.Fun_0_0)(t53351)));
        }
        
        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public boolean
                                                                                                                                              hasValue$O(
                                                                                                                                              ){
            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53352 =
              size;
            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final long t53353 =
              ((long)(((int)(t53352))));
            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final boolean t53354 =
              ((t53353) > (((long)(0L))));
            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53354;
        }
        
        
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public $T
                                                                                                                                              get$G(
                                                                                                                                              ){
            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.Rail t53356 =
              ((x10.core.Rail)(data));
            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53355 =
              nextVal;
            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final long t53357 =
              ((long)(((int)(t53355))));
            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final $T result =
              (($T)(((x10.core.Rail<$T>)t53356).$apply$G((long)(t53357))));
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.BBuffer x53308 =
              ((pppp.util.BBuffer)(this));
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
;
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53358 =
              ((pppp.util.BBuffer<$T>)x53308).
                nextVal;
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53359 =
              ((t53358) + (((int)(1))));
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53360 =
              ((pppp.util.BBuffer<$T>)x53308).nextVal = t53359;
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53361 =
              N;
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final boolean t53364 =
              ((t53360) >= (((int)(t53361))));
            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
if (t53364) {
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.BBuffer x53310 =
                  ((pppp.util.BBuffer)(this));
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int y53311 =
                  N;
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53362 =
                  ((pppp.util.BBuffer<$T>)x53310).
                    nextVal;
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53363 =
                  ((t53362) % (((int)(y53311))));
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)x53310).nextVal = t53363;
            }
            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.BBuffer x53312 =
              ((pppp.util.BBuffer)(this));
            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
;
            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53365 =
              ((pppp.util.BBuffer<$T>)x53312).
                size;
            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53366 =
              ((t53365) - (((int)(1))));
            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)x53312).size = t53366;
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return result;
        }
        
        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public $T
                                                                                                                                              $apply$G(
                                                                                                                                              ){
            
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.Monitor t53369 =
              ((pppp.util.Monitor)(monitor));
            
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.fun.Fun_0_0 t53370 =
              ((x10.core.fun.Fun_0_0)(new pppp.util.BBuffer.$Closure$123<$T>($T, ((pppp.util.BBuffer<$T>)(this)), (pppp.util.BBuffer.$Closure$123.__0$1pppp$util$BBuffer$$Closure$123$$T$2) null)));
            
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.fun.Fun_0_0 t53371 =
              ((x10.core.fun.Fun_0_0)(new pppp.util.BBuffer.$Closure$124<$T>($T, ((pppp.util.BBuffer<$T>)(this)), (pppp.util.BBuffer.$Closure$124.__0$1pppp$util$BBuffer$$Closure$124$$T$2) null)));
            
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final $T t =
              (($T)(((pppp.util.Monitor)t53369).<$T> on__0$1x10$lang$Boolean$2__1$1pppp$util$Monitor$$T$2$G($T, ((x10.core.fun.Fun_0_0)(t53370)),
                                                                                                            ((x10.core.fun.Fun_0_0)(t53371)))));
            
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final x10.core.fun.Fun_0_0 t53374 =
              ((x10.core.fun.Fun_0_0)(new pppp.util.BBuffer.$Closure$125<$T>($T, ((pppp.util.BBuffer<$T>)(this)),
                                                                             t, (pppp.util.BBuffer.$Closure$125.__0$1pppp$util$BBuffer$$Closure$125$$T$2__1pppp$util$BBuffer$$Closure$125$$T) null)));
            
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
pppp.util.Logger.log__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t53374)));
            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t;
        }
        
        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public void
                                                                                                                                              awaken(
                                                                                                                                              ){
            
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.Monitor t53375 =
              ((pppp.util.Monitor)(monitor));
            
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
t53375.awaken();
        }
        
        
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
public java.lang.String
                                                                                                                                              toString(
                                                                                                                                              ){
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53376 =
              ((java.lang.String)(name));
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final boolean t53380 =
              ((t53376) == (null));
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
java.lang.String t53381 =
               null;
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
if (t53380) {
                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53377 =
                  x10.rtt.Types.typeName(((java.lang.Object)(this)));
                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53378 =
                  ((t53377) + (" #"));
                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53379 =
                  x10.rtt.Types.hashCode(((java.lang.Object)(this)));
                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
t53381 = ((t53378) + ((x10.core.Int.$box(t53379))));
            } else {
                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
t53381 = name;
            }
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53382 =
              t53381;
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53383 =
              (("<") + (t53382));
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53384 =
              ((t53383) + (" "));
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final int t53385 =
              size;
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53386 =
              ((t53384) + ((x10.core.Int.$box(t53385))));
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53387 =
              ((t53386) + (">"));
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53387;
        }
        
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final public pppp.util.BBuffer
                                                                                                                                             pppp$util$BBuffer$$this$pppp$util$BBuffer(
                                                                                                                                             ){
            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return pppp.util.BBuffer.this;
        }
        
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final public void
                                                                                                                                             __fieldInitializers_pppp_util_BBuffer(
                                                                                                                                             ){
            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)this).nextVal = 0;
            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)this).size = 0;
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.Monitor t53388 =
              ((pppp.util.Monitor)(new pppp.util.Monitor((java.lang.System[]) null).pppp$util$Monitor$$init$S()));
            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)this).monitor = ((pppp.util.Monitor)(t53388));
            
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
((pppp.util.BBuffer<$T>)this).name = null;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$119<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$119> $RTT = x10.rtt.StaticFunType.<$Closure$119> make(
            $Closure$119.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.BBuffer.$Closure$119<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$119.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.BBuffer.$Closure$119 $_obj = new pppp.util.BBuffer.$Closure$119((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$119(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.BBuffer.$Closure$119.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$119 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53340 =
                      (("BBuffer: ") + (this.
                                          out$$));
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53341 =
                      ((t53340) + (" exiting put. "));
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53341;
                }
                
                public pppp.util.BBuffer<$T> out$$;
                
                public $Closure$119(final x10.rtt.Type $T,
                                    final pppp.util.BBuffer<$T> out$$, __0$1pppp$util$BBuffer$$Closure$119$$T$2 $dummy) {pppp.util.BBuffer.$Closure$119.$initParams(this, $T);
                                                                                                                              {
                                                                                                                                 ((pppp.util.BBuffer.$Closure$119<$T>)this).out$$ = out$$;
                                                                                                                             }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$util$BBuffer$$Closure$119$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$120<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$120> $RTT = x10.rtt.StaticFunType.<$Closure$120> make(
            $Closure$120.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.BBuffer.$Closure$120<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$120.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.t = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.BBuffer.$Closure$120 $_obj = new pppp.util.BBuffer.$Closure$120((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                if (t instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.t);
                } else {
                $serializer.write(this.t);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$120(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.BBuffer.$Closure$120.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$120 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53344 =
                      ((this.
                          t) + (" ==> "));
                    
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53345 =
                      ((t53344) + (this.
                                     out$$));
                    
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53345;
                }
                
                public pppp.util.BBuffer<$T> out$$;
                public $T t;
                
                public $Closure$120(final x10.rtt.Type $T,
                                    final pppp.util.BBuffer<$T> out$$,
                                    final $T t, __0$1pppp$util$BBuffer$$Closure$120$$T$2__1pppp$util$BBuffer$$Closure$120$$T $dummy) {pppp.util.BBuffer.$Closure$120.$initParams(this, $T);
                                                                                                                                           {
                                                                                                                                              ((pppp.util.BBuffer.$Closure$120<$T>)this).out$$ = out$$;
                                                                                                                                              ((pppp.util.BBuffer.$Closure$120<$T>)this).t = (($T)(t));
                                                                                                                                          }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$util$BBuffer$$Closure$120$$T$2__1pppp$util$BBuffer$$Closure$120$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$121<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$121> $RTT = x10.rtt.StaticFunType.<$Closure$121> make(
            $Closure$121.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.BOOLEAN)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.BBuffer.$Closure$121<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$121.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.BBuffer.$Closure$121 $_obj = new pppp.util.BBuffer.$Closure$121((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$121(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.BBuffer.$Closure$121.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public x10.core.Boolean
              $apply$G(){return x10.core.Boolean.$box($apply$O());}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$121 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public boolean
                  $apply$O(
                  ){
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final boolean t53347 =
                      ((pppp.util.BBuffer<$T>)this.
                                                out$$).hasSpace$O();
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53347;
                }
                
                public pppp.util.BBuffer<$T> out$$;
                
                public $Closure$121(final x10.rtt.Type $T,
                                    final pppp.util.BBuffer<$T> out$$, __0$1pppp$util$BBuffer$$Closure$121$$T$2 $dummy) {pppp.util.BBuffer.$Closure$121.$initParams(this, $T);
                                                                                                                              {
                                                                                                                                 ((pppp.util.BBuffer.$Closure$121<$T>)this).out$$ = out$$;
                                                                                                                             }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$util$BBuffer$$Closure$121$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$122<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$122> $RTT = x10.rtt.StaticFunType.<$Closure$122> make(
            $Closure$122.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, pppp.util.Unit.$RTT)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.BBuffer.$Closure$122<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$122.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.t = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.BBuffer.$Closure$122 $_obj = new pppp.util.BBuffer.$Closure$122((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                if (t instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.t);
                } else {
                $serializer.write(this.t);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$122(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.BBuffer.$Closure$122.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.util.Unit
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$122 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.util.Unit
                  $apply(
                  ){
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final pppp.util.Unit t53348 =
                      ((pppp.util.BBuffer<$T>)this.
                                                out$$).add__0pppp$util$BBuffer$$T((($T)(this.
                                                                                          t)));
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53348;
                }
                
                public pppp.util.BBuffer<$T> out$$;
                public $T t;
                
                public $Closure$122(final x10.rtt.Type $T,
                                    final pppp.util.BBuffer<$T> out$$,
                                    final $T t, __0$1pppp$util$BBuffer$$Closure$122$$T$2__1pppp$util$BBuffer$$Closure$122$$T $dummy) {pppp.util.BBuffer.$Closure$122.$initParams(this, $T);
                                                                                                                                           {
                                                                                                                                              ((pppp.util.BBuffer.$Closure$122<$T>)this).out$$ = out$$;
                                                                                                                                              ((pppp.util.BBuffer.$Closure$122<$T>)this).t = (($T)(t));
                                                                                                                                          }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$util$BBuffer$$Closure$122$$T$2__1pppp$util$BBuffer$$Closure$122$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$123<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$123> $RTT = x10.rtt.StaticFunType.<$Closure$123> make(
            $Closure$123.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.BOOLEAN)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.BBuffer.$Closure$123<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$123.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.BBuffer.$Closure$123 $_obj = new pppp.util.BBuffer.$Closure$123((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$123(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.BBuffer.$Closure$123.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public x10.core.Boolean
              $apply$G(){return x10.core.Boolean.$box($apply$O());}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$123 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public boolean
                  $apply$O(
                  ){
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final boolean t53367 =
                      ((pppp.util.BBuffer<$T>)this.
                                                out$$).hasValue$O();
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53367;
                }
                
                public pppp.util.BBuffer<$T> out$$;
                
                public $Closure$123(final x10.rtt.Type $T,
                                    final pppp.util.BBuffer<$T> out$$, __0$1pppp$util$BBuffer$$Closure$123$$T$2 $dummy) {pppp.util.BBuffer.$Closure$123.$initParams(this, $T);
                                                                                                                              {
                                                                                                                                 ((pppp.util.BBuffer.$Closure$123<$T>)this).out$$ = out$$;
                                                                                                                             }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$util$BBuffer$$Closure$123$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$124<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$124> $RTT = x10.rtt.StaticFunType.<$Closure$124> make(
            $Closure$124.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.BBuffer.$Closure$124<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$124.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.BBuffer.$Closure$124 $_obj = new pppp.util.BBuffer.$Closure$124((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$124(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.BBuffer.$Closure$124.$initParams(this, $T);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$124 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply$G(
                  ){
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final $T t53368 =
                      (($T)(((pppp.util.BBuffer<$T>)this.
                                                      out$$).get$G()));
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53368;
                }
                
                public pppp.util.BBuffer<$T> out$$;
                
                public $Closure$124(final x10.rtt.Type $T,
                                    final pppp.util.BBuffer<$T> out$$, __0$1pppp$util$BBuffer$$Closure$124$$T$2 $dummy) {pppp.util.BBuffer.$Closure$124.$initParams(this, $T);
                                                                                                                              {
                                                                                                                                 ((pppp.util.BBuffer.$Closure$124<$T>)this).out$$ = out$$;
                                                                                                                             }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$util$BBuffer$$Closure$124$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$125<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$125> $RTT = x10.rtt.StaticFunType.<$Closure$125> make(
            $Closure$125.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.BBuffer.$Closure$125<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$125.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.t = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.BBuffer.$Closure$125 $_obj = new pppp.util.BBuffer.$Closure$125((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                if (t instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.t);
                } else {
                $serializer.write(this.t);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$125(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.BBuffer.$Closure$125.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$125 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53372 =
                      ((this.
                          out$$) + (" ==> "));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
final java.lang.String t53373 =
                      ((t53372) + (this.
                                     t));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/BBuffer.x10"
return t53373;
                }
                
                public pppp.util.BBuffer<$T> out$$;
                public $T t;
                
                public $Closure$125(final x10.rtt.Type $T,
                                    final pppp.util.BBuffer<$T> out$$,
                                    final $T t, __0$1pppp$util$BBuffer$$Closure$125$$T$2__1pppp$util$BBuffer$$Closure$125$$T $dummy) {pppp.util.BBuffer.$Closure$125.$initParams(this, $T);
                                                                                                                                           {
                                                                                                                                              ((pppp.util.BBuffer.$Closure$125<$T>)this).out$$ = out$$;
                                                                                                                                              ((pppp.util.BBuffer.$Closure$125<$T>)this).t = (($T)(t));
                                                                                                                                          }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$util$BBuffer$$Closure$125$$T$2__1pppp$util$BBuffer$$Closure$125$$T {}
                
            }
            
        // synthetic type for parameter mangling
        public static final class __1pppp$util$BBuffer$$T {}
        
        }
        
        