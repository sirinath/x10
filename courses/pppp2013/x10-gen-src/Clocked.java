@x10.runtime.impl.java.X10Generated public class Clocked<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Clocked> $RTT = x10.rtt.NamedType.<Clocked> make(
    "Clocked", Clocked.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.UnresolvedType.PARAM(0))}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(Clocked<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Clocked.class + " calling"); } 
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.a = $deserializer.readRef();
        $_obj.name = $deserializer.readRef();
        $_obj.clock = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        Clocked $_obj = new Clocked((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        if (a instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.a);
        } else {
        $serializer.write(this.a);
        }
        $serializer.write(this.name);
        if (clock instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.clock);
        } else {
        $serializer.write(this.clock);
        }
        
    }
    
    // constructor just for allocation
    public Clocked(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    Clocked.$initParams(this, $T);
    }
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final Clocked $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
public x10.lang.Clock clock;
        
        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
public x10.core.Rail<$T> a;
        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
public java.lang.String name;
        
        
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
// creation method for java code (1-phase java constructor)
        public Clocked(final x10.rtt.Type $T,
                       final $T x, __0Clocked$$T $dummy){this((java.lang.System[]) null, $T);
                                                             Clocked$$init$S(x, (Clocked.__0Clocked$$T) null);}
        
        // constructor for non-virtual call
        final public Clocked<$T> Clocked$$init$S(final $T x, __0Clocked$$T $dummy) { {
                                                                                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final x10.lang.Clock t44759 =
                                                                                              x10.lang.Clock.make();
                                                                                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
/*this.*/Clocked$$init$S((($T)(x)),
                                                                                                                                                                                                                                               t44759,
                                                                                                                                                                                                                                               ((java.lang.String)("")), (Clocked.__0Clocked$$T) null);
                                                                                        }
                                                                                        return this;
                                                                                        }
        
        
        
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
// creation method for java code (1-phase java constructor)
        public Clocked(final x10.rtt.Type $T,
                       final $T x,
                       final x10.lang.Clock c, __0Clocked$$T $dummy){this((java.lang.System[]) null, $T);
                                                                         Clocked$$init$S(x,c, (Clocked.__0Clocked$$T) null);}
        
        // constructor for non-virtual call
        final public Clocked<$T> Clocked$$init$S(final $T x,
                                                 final x10.lang.Clock c, __0Clocked$$T $dummy) { {
                                                                                                        
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
/*this.*/Clocked$$init$S((($T)(x)),
                                                                                                                                                                                                                                                           ((x10.lang.Clock)(c)),
                                                                                                                                                                                                                                                           ((java.lang.String)("")), (Clocked.__0Clocked$$T) null);
                                                                                                    }
                                                                                                    return this;
                                                                                                    }
        
        
        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
// creation method for java code (1-phase java constructor)
        public Clocked(final x10.rtt.Type $T,
                       final $T x,
                       final x10.lang.Clock c,
                       final java.lang.String s, __0Clocked$$T $dummy){this((java.lang.System[]) null, $T);
                                                                           Clocked$$init$S(x,c,s, (Clocked.__0Clocked$$T) null);}
        
        // constructor for non-virtual call
        final public Clocked<$T> Clocked$$init$S(final $T x,
                                                 final x10.lang.Clock c,
                                                 final java.lang.String s, __0Clocked$$T $dummy) { {
                                                                                                          
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
;
                                                                                                          
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
this.clock = c;
                                                                                                          
                                                                                                          
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
this.__fieldInitializers_Clocked();
                                                                                                          
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final x10.core.Rail t44760 =
                                                                                                            ((x10.core.Rail)(new x10.core.Rail<$T>($T, ((long)(2L)),
                                                                                                                                                   (($T)(x)), (x10.core.Rail.__1x10$lang$Rail$$T) null)));
                                                                                                          
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
((Clocked<$T>)this).a = ((x10.core.Rail)(t44760));
                                                                                                          
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
((Clocked<$T>)this).name = ((java.lang.String)(s));
                                                                                                      }
                                                                                                      return this;
                                                                                                      }
        
        
        
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
public void
                                                                                                                                    next(
                                                                                                                                    ){
            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final x10.lang.Clock t44761 =
              ((x10.lang.Clock)(clock));
            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
t44761.advance();
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final x10.core.Rail t44763 =
              ((x10.core.Rail)(a));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final x10.core.Rail t44762 =
              ((x10.core.Rail)(a));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final $T t44764 =
              (($T)(((x10.core.Rail<$T>)t44762).$apply$G((long)(1L))));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
((x10.core.Rail<$T>)t44763).$set__1x10$lang$Rail$$T$G((long)(0L),
                                                                                                                                                                                            (($T)(t44764)));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final x10.lang.Clock t44765 =
              ((x10.lang.Clock)(clock));
            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
t44765.advance();
        }
        
        
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
public $T
                                                                                                                                    $apply$G(
                                                                                                                                    ){
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final x10.core.Rail t44766 =
              ((x10.core.Rail)(a));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final $T t44767 =
              (($T)(((x10.core.Rail<$T>)t44766).$apply$G((long)(0L))));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
return t44767;
        }
        
        
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
public void
                                                                                                                                    $set__0Clocked$$T(
                                                                                                                                    final $T x){
            
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final x10.core.Rail t44768 =
              ((x10.core.Rail)(a));
            
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
((x10.core.Rail<$T>)t44768).$set__1x10$lang$Rail$$T$G((long)(1L),
                                                                                                                                                                                            (($T)(x)));
        }
        
        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
public java.lang.String
                                                                                                                                    toString(
                                                                                                                                    ){
            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final java.lang.String t44769 =
              ((java.lang.String)(name));
            
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
return t44769;
        }
        
        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final public Clocked
                                                                                                                                    Clocked$$this$Clocked(
                                                                                                                                    ){
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
return Clocked.this;
        }
        
        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
final public void
                                                                                                                                    __fieldInitializers_Clocked(
                                                                                                                                    ){
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Clocked.x10"
((Clocked<$T>)this).a = null;
        }
    // synthetic type for parameter mangling
    public static final class __0Clocked$$T {}
    
}

