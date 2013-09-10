package pppp.lib.stream;


@x10.runtime.impl.java.X10Generated public class FilteredStream<$T>  extends x10.core.Ref implements pppp.lib.stream.Stream, x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<FilteredStream> $RTT = x10.rtt.NamedType.<FilteredStream> make(
    "pppp.lib.stream.FilteredStream", FilteredStream.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(pppp.lib.stream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0))}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.FilteredStream<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + FilteredStream.class + " calling"); } 
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.f = $deserializer.readRef();
        $_obj.a = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.FilteredStream $_obj = new pppp.lib.stream.FilteredStream((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        if (f instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.f);
        } else {
        $serializer.write(this.f);
        }
        if (a instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.a);
        } else {
        $serializer.write(this.a);
        }
        
    }
    
    // constructor just for allocation
    public FilteredStream(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    pppp.lib.stream.FilteredStream.$initParams(this, $T);
    }
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final FilteredStream $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
public x10.core.fun.Fun_0_1<$T,x10.core.Boolean> f;
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
public pppp.lib.stream.Stream<$T> a;
        
        
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
public $T
                                                                                                                                                          $apply$G(
                                                                                                                                                          ){
            
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
try {{
                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final pppp.lib.stream.Stream t41812 =
                  ((pppp.lib.stream.Stream)(a));
                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
$T x =
                  (($T)(((pppp.lib.stream.Stream<$T>)t41812).$apply$G()));
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41813 =
                  ((this) + (" reads "));
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final $T t41814 =
                  (($T)(x));
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41815 =
                  ((t41813) + (t41814));
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
pppp.util.Logger.log(((java.lang.String)(t41815)));
                
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
while (true) {
                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final x10.core.fun.Fun_0_1 t41816 =
                      ((x10.core.fun.Fun_0_1)(f));
                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final $T t41817 =
                      (($T)(x));
                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final boolean t41818 =
                      x10.core.Boolean.$unbox(((x10.core.fun.Fun_0_1<$T,x10.core.Boolean>)t41816).$apply(t41817,$T));
                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final boolean t41824 =
                      !(t41818);
                    
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
if (!(t41824)) {
                        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
break;
                    }
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41836 =
                      ((this) + (" discards "));
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final $T t41837 =
                      (($T)(x));
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41838 =
                      ((t41836) + (t41837));
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
pppp.util.Logger.log(((java.lang.String)(t41838)));
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final pppp.lib.stream.Stream t41839 =
                      ((pppp.lib.stream.Stream)(a));
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final $T t41840 =
                      (($T)(((pppp.lib.stream.Stream<$T>)t41839).$apply$G()));
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
x = (($T)(t41840));
                }
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41825 =
                  ((this) + (" returns "));
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final $T t41826 =
                  (($T)(x));
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41827 =
                  ((t41825) + (t41826));
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
pppp.util.Logger.log(((java.lang.String)(t41827)));
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final $T t41828 =
                  (($T)(x));
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
return t41828;
            }}catch (final pppp.lib.stream.StreamClosedException z) {
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41829 =
                  ((this) + (" input stream closed. Throwing exception"));
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
pppp.util.Logger.log(((java.lang.String)(t41829)));
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
throw z;
            }
        }
        
        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
public java.lang.String
                                                                                                                                                           toString(
                                                                                                                                                           ){
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final pppp.lib.stream.Stream t41830 =
              ((pppp.lib.stream.Stream)(a));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41831 =
              (("<") + (t41830));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41832 =
              ((t41831) + (" filteredBy "));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final x10.core.fun.Fun_0_1 t41833 =
              ((x10.core.fun.Fun_0_1)(f));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41834 =
              ((t41832) + (t41833));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final java.lang.String t41835 =
              ((t41834) + (">"));
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
return t41835;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final public pppp.lib.stream.FilteredStream
                                                                                                                                                          pppp$lib$stream$FilteredStream$$this$pppp$lib$stream$FilteredStream(
                                                                                                                                                          ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
return pppp.lib.stream.FilteredStream.this;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
// creation method for java code (1-phase java constructor)
        public FilteredStream(final x10.rtt.Type $T,
                              final x10.core.fun.Fun_0_1<$T,x10.core.Boolean> f,
                              final pppp.lib.stream.Stream<$T> a, __0$1pppp$lib$stream$FilteredStream$$T$3x10$lang$Boolean$2__1$1pppp$lib$stream$FilteredStream$$T$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                                                                                 pppp$lib$stream$FilteredStream$$init$S(f,a, (pppp.lib.stream.FilteredStream.__0$1pppp$lib$stream$FilteredStream$$T$3x10$lang$Boolean$2__1$1pppp$lib$stream$FilteredStream$$T$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.FilteredStream<$T> pppp$lib$stream$FilteredStream$$init$S(final x10.core.fun.Fun_0_1<$T,x10.core.Boolean> f,
                                                                                               final pppp.lib.stream.Stream<$T> a, __0$1pppp$lib$stream$FilteredStream$$T$3x10$lang$Boolean$2__1$1pppp$lib$stream$FilteredStream$$T$2 $dummy) { {
                                                                                                                                                                                                                                                       
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
;
                                                                                                                                                                                                                                                       
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
this.f = f;
                                                                                                                                                                                                                                                       this.a = a;
                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                       
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
this.__fieldInitializers_pppp_lib_stream_FilteredStream();
                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                   return this;
                                                                                                                                                                                                                                                   }
        
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/FilteredStream.x10"
final public void
                                                                                                                                                          __fieldInitializers_pppp_lib_stream_FilteredStream(
                                                                                                                                                          ){
            
        }
    // synthetic type for parameter mangling
    public static final class __0$1pppp$lib$stream$FilteredStream$$T$3x10$lang$Boolean$2__1$1pppp$lib$stream$FilteredStream$$T$2 {}
    
}

