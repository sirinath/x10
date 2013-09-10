package pppp.lib.stream;


@x10.runtime.impl.java.X10Generated public class XDucer extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<XDucer> $RTT = x10.rtt.NamedType.<XDucer> make(
    "pppp.lib.stream.XDucer", XDucer.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.XDucer $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + XDucer.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.XDucer $_obj = new pppp.lib.stream.XDucer((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public XDucer(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
public static <$T, $X> void
                                                                                                                                                   copy__0$1pppp$lib$stream$XDucer$$T$2__1$1pppp$lib$stream$XDucer$$X$2(
                                                                                                                                                   final x10.rtt.Type $T,
                                                                                                                                                   final x10.rtt.Type $X,
                                                                                                                                                   final pppp.lib.stream.Stream<$T> source,
                                                                                                                                                   final x10.core.Rail<$X> sinks){
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new pppp.lib.stream.XDucer.$Closure$3<$T, $X>($T, $X, source,
                                                                                                                                                                                                                                                         sinks, (pppp.lib.stream.XDucer.$Closure$3.__0$1pppp$lib$stream$XDucer$$Closure$3$$T$2__1$1pppp$lib$stream$XDucer$$Closure$3$$X$2) null))));
        }
        
        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
public static <$T> void
                                                                                                                                                   print__0$1pppp$lib$stream$XDucer$$T$2(
                                                                                                                                                   final x10.rtt.Type $T,
                                                                                                                                                   final pppp.lib.stream.Stream<$T> ix){
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final x10.io.Printer t42254 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
pppp.lib.stream.XDucer.<$T> print__1$1pppp$lib$stream$XDucer$$T$2($T, ((x10.io.Printer)(t42254)),
                                                                                                                                                                                                                       ((pppp.lib.stream.Stream)(ix)),
                                                                                                                                                                                                                       (int)(10));
        }
        
        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
public static <$T> void
                                                                                                                                                   print__0$1pppp$lib$stream$XDucer$$T$2(
                                                                                                                                                   final x10.rtt.Type $T,
                                                                                                                                                   final pppp.lib.stream.Stream<$T> ix,
                                                                                                                                                   final int k){
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final x10.io.Printer t42255 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
pppp.lib.stream.XDucer.<$T> print__1$1pppp$lib$stream$XDucer$$T$2($T, ((x10.io.Printer)(t42255)),
                                                                                                                                                                                                                       ((pppp.lib.stream.Stream)(ix)),
                                                                                                                                                                                                                       (int)(k));
        }
        
        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
public static <$T> void
                                                                                                                                                   print__1$1pppp$lib$stream$XDucer$$T$2(
                                                                                                                                                   final x10.rtt.Type $T,
                                                                                                                                                   final x10.io.Printer p,
                                                                                                                                                   final pppp.lib.stream.Stream<$T> ix,
                                                                                                                                                   final int k){
            
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new pppp.lib.stream.XDucer.$Closure$5<$T>($T, p,
                                                                                                                                                                                                                                                     k,
                                                                                                                                                                                                                                                     ix, (pppp.lib.stream.XDucer.$Closure$5.__2$1pppp$lib$stream$XDucer$$Closure$5$$T$2) null))));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
p.println();
        }
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final public pppp.lib.stream.XDucer
                                                                                                                                                   pppp$lib$stream$XDucer$$this$pppp$lib$stream$XDucer(
                                                                                                                                                   ){
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
return pppp.lib.stream.XDucer.this;
        }
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
// creation method for java code (1-phase java constructor)
        public XDucer(){this((java.lang.System[]) null);
                            pppp$lib$stream$XDucer$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.XDucer pppp$lib$stream$XDucer$$init$S() { {
                                                                                      
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
;
                                                                                      
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"

                                                                                      
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
this.__fieldInitializers_pppp_lib_stream_XDucer();
                                                                                  }
                                                                                  return this;
                                                                                  }
        
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final public void
                                                                                                                                                   __fieldInitializers_pppp_lib_stream_XDucer(
                                                                                                                                                   ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$3<$T, $X>  extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$3> $RTT = x10.rtt.StaticVoidFunType.<$Closure$3> make(
            $Closure$3.class, 2, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;if (i ==1)return $X;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T, $X> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.XDucer.$Closure$3<$T, $X> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$3.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.$X = (x10.rtt.Type) $deserializer.readRef();
                $_obj.source = $deserializer.readRef();
                $_obj.sinks = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.XDucer.$Closure$3 $_obj = new pppp.lib.stream.XDucer.$Closure$3((java.lang.System[]) null, (x10.rtt.Type) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                $serializer.write((x10.serialization.X10JavaSerializable) this.$X);
                if (source instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.source);
                } else {
                $serializer.write(this.source);
                }
                if (sinks instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.sinks);
                } else {
                $serializer.write(this.sinks);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$3(final java.lang.System[] $dummy, final x10.rtt.Type $T, final x10.rtt.Type $X) { 
            pppp.lib.stream.XDucer.$Closure$3.$initParams(this, $T, $X);
            }
            
                private x10.rtt.Type $T;
                private x10.rtt.Type $X;
                // initializer of type parameters
                public static void $initParams(final $Closure$3 $this, final x10.rtt.Type $T, final x10.rtt.Type $X) {
                $this.$T = $T;
                $this.$X = $X;
                }
                
                
                public void
                  $apply(
                  ){
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
try {{
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final java.lang.String t42239 =
                          (("Starting copy ") + (this.
                                                   source));
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final java.lang.String t42240 =
                          ((t42239) + (" ==> "));
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final java.lang.String t42241 =
                          ((t42240) + (this.
                                         sinks));
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
pppp.util.Logger.log(((java.lang.String)(t42241)));
                        
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
try {{
                            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
while (true) {
                                
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final $T x =
                                  (($T)(((pppp.lib.stream.Stream<$T>)this.
                                                                       source).$apply$G()));
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final x10.core.Rail rail42273 =
                                  ((x10.core.Rail)(this.
                                                     sinks));
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long size42274 =
                                  ((x10.core.Rail<$X>)rail42273).
                                    size;
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
long idx42270 =
                                  0L;
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
for (;
                                                                                                                                                                              true;
                                                                                                                                                                              ) {
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long t42271 =
                                      idx42270;
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final boolean t42272 =
                                      ((t42271) < (((long)(size42274))));
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
if (!(t42272)) {
                                        
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
break;
                                    }
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long t42266 =
                                      idx42270;
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final $X o42267 =
                                      (($X)(((x10.core.Rail<$X>)rail42273).$apply$G((long)(t42266))));
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
((pppp.lib.stream.Source<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(pppp.lib.stream.Source.$RTT, $T),o42267)).$set$V((($T)(x)),$T);
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long t42268 =
                                      idx42270;
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long t42269 =
                                      ((t42268) + (((long)(1L))));
                                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
idx42270 = t42269;
                                }
                            }
                        }}catch (final pppp.lib.stream.StreamClosedException z) {
                            
                        }finally {{
                             
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final x10.core.Rail rail42219 =
                               ((x10.core.Rail)(this.
                                                  sinks));
                             
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long size42220 =
                               ((x10.core.Rail<$X>)rail42219).
                                 size;
                             
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
long idx42279 =
                               0L;
                             
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
for (;
                                                                                                                                                                           true;
                                                                                                                                                                           ) {
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long t42280 =
                                   idx42279;
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final boolean t42281 =
                                   ((t42280) < (((long)(size42220))));
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
if (!(t42281)) {
                                     
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
break;
                                 }
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long t42275 =
                                   idx42279;
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final $X o42276 =
                                   (($X)(((x10.core.Rail<$X>)rail42219).$apply$G((long)(t42275))));
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
((pppp.lib.stream.Source<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(pppp.lib.stream.Source.$RTT, $T),o42276)).close();
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long t42277 =
                                   idx42279;
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final long t42278 =
                                   ((t42277) + (((long)(1L))));
                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
idx42279 = t42278;
                             }
                         }}
                        }}catch (java.lang.Error __lowerer__var__0__) {
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
throw __lowerer__var__0__;
                        }catch (java.lang.Throwable __lowerer__var__1__) {
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                        }
                    }
                
                public pppp.lib.stream.Stream<$T> source;
                public x10.core.Rail<$X> sinks;
                
                public $Closure$3(final x10.rtt.Type $T,
                                  final x10.rtt.Type $X,
                                  final pppp.lib.stream.Stream<$T> source,
                                  final x10.core.Rail<$X> sinks, __0$1pppp$lib$stream$XDucer$$Closure$3$$T$2__1$1pppp$lib$stream$XDucer$$Closure$3$$X$2 $dummy) {pppp.lib.stream.XDucer.$Closure$3.$initParams(this, $T, $X);
                                                                                                                                                                      {
                                                                                                                                                                         ((pppp.lib.stream.XDucer.$Closure$3<$T, $X>)this).source = ((pppp.lib.stream.Stream)(source));
                                                                                                                                                                         ((pppp.lib.stream.XDucer.$Closure$3<$T, $X>)this).sinks = ((x10.core.Rail)(sinks));
                                                                                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lib$stream$XDucer$$Closure$3$$T$2__1$1pppp$lib$stream$XDucer$$Closure$3$$X$2 {}
                
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$4<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$4> $RTT = x10.rtt.StaticFunType.<$Closure$4> make(
                $Closure$4.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.XDucer.$Closure$4<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$4.class + " calling"); } 
                    $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                    $_obj.p = $deserializer.readRef();
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    pppp.lib.stream.XDucer.$Closure$4 $_obj = new pppp.lib.stream.XDucer.$Closure$4((java.lang.System[]) null, (x10.rtt.Type) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                    if (p instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.p);
                    } else {
                    $serializer.write(this.p);
                    }
                    
                }
                
                // constructor just for allocation
                public $Closure$4(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
                pppp.lib.stream.XDucer.$Closure$4.$initParams(this, $T);
                }
                // bridge for method abstract public ()=>U.operator()():U
                public java.lang.String
                  $apply$G(){return $apply();}
                
                    private x10.rtt.Type $T;
                    // initializer of type parameters
                    public static void $initParams(final $Closure$4 $this, final x10.rtt.Type $T) {
                    $this.$T = $T;
                    }
                    
                    
                    public java.lang.String
                      $apply(
                      ){
                        
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final java.lang.String t42256 =
                          (("Starting print of ") + (this.
                                                       p));
                        
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
return t42256;
                    }
                    
                    public x10.io.Printer p;
                    
                    public $Closure$4(final x10.rtt.Type $T,
                                      final x10.io.Printer p) {pppp.lib.stream.XDucer.$Closure$4.$initParams(this, $T);
                                                                    {
                                                                       ((pppp.lib.stream.XDucer.$Closure$4<$T>)this).p = ((x10.io.Printer)(p));
                                                                   }}
                    
                }
                
            @x10.runtime.impl.java.X10Generated final public static class $Closure$5<$T>  extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
            {
                private static final long serialVersionUID = 1L;
                public static final x10.rtt.RuntimeType<$Closure$5> $RTT = x10.rtt.StaticVoidFunType.<$Closure$5> make(
                $Closure$5.class, 1, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                );
                public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                
                public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
                private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.XDucer.$Closure$5<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$5.class + " calling"); } 
                    $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                    $_obj.p = $deserializer.readRef();
                    $_obj.k = $deserializer.readInt();
                    $_obj.ix = $deserializer.readRef();
                    return $_obj;
                }
                
                public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                
                    pppp.lib.stream.XDucer.$Closure$5 $_obj = new pppp.lib.stream.XDucer.$Closure$5((java.lang.System[]) null, (x10.rtt.Type) null);
                    $deserializer.record_reference($_obj);
                    return $_deserialize_body($_obj, $deserializer);
                    
                }
                
                public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                
                    $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                    if (p instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.p);
                    } else {
                    $serializer.write(this.p);
                    }
                    $serializer.write(this.k);
                    if (ix instanceof x10.serialization.X10JavaSerializable) {
                    $serializer.write((x10.serialization.X10JavaSerializable) this.ix);
                    } else {
                    $serializer.write(this.ix);
                    }
                    
                }
                
                // constructor just for allocation
                public $Closure$5(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
                pppp.lib.stream.XDucer.$Closure$5.$initParams(this, $T);
                }
                
                    private x10.rtt.Type $T;
                    // initializer of type parameters
                    public static void $initParams(final $Closure$5 $this, final x10.rtt.Type $T) {
                    $this.$T = $T;
                    }
                    
                    
                    public void
                      $apply(
                      ){
                        
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
try {{
                            
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
try {{
                                
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
int n =
                                  0;
                                
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final x10.core.fun.Fun_0_0 t42257 =
                                  ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.XDucer.$Closure$4<$T>($T, ((x10.io.Printer)(this.
                                                                                                                            p)))));
                                
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
pppp.util.Logger.log__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t42257)));
                                
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
while (true) {
                                    
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final int t42258 =
                                      n;
                                    
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final int t42259 =
                                      ((t42258) + (((int)(1))));
                                    
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
n = t42259;
                                    
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final int t42260 =
                                      n;
                                    
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final int t42261 =
                                      ((t42260) % (((int)(this.
                                                            k))));
                                    
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final boolean t42265 =
                                      ((int) t42261) ==
                                    ((int) 0);
                                    
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
if (t42265) {
                                        
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final $T t42262 =
                                          (($T)(((pppp.lib.stream.Stream<$T>)this.
                                                                               ix).$apply$G()));
                                        
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
this.
                                                                                                                                                                                   p.println(((java.lang.Object)(t42262)));
                                    } else {
                                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final $T t42263 =
                                          (($T)(((pppp.lib.stream.Stream<$T>)this.
                                                                               ix).$apply$G()));
                                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
final java.lang.String t42264 =
                                          ((t42263) + (" "));
                                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
this.
                                                                                                                                                                                   p.print(((java.lang.String)(t42264)));
                                    }
                                }
                            }}catch (final pppp.lib.stream.StreamClosedException z) {
                                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
pppp.util.Logger.log(((java.lang.String)("print catches exception. Terminates.")));
                            }
                        }}catch (java.lang.Error __lowerer__var__0__) {
                            
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
throw __lowerer__var__0__;
                        }catch (java.lang.Throwable __lowerer__var__1__) {
                            
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/XDucer.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                        }
                    }
                    
                    public x10.io.Printer p;
                    public int k;
                    public pppp.lib.stream.Stream<$T> ix;
                    
                    public $Closure$5(final x10.rtt.Type $T,
                                      final x10.io.Printer p,
                                      final int k,
                                      final pppp.lib.stream.Stream<$T> ix, __2$1pppp$lib$stream$XDucer$$Closure$5$$T$2 $dummy) {pppp.lib.stream.XDucer.$Closure$5.$initParams(this, $T);
                                                                                                                                     {
                                                                                                                                        ((pppp.lib.stream.XDucer.$Closure$5<$T>)this).p = ((x10.io.Printer)(p));
                                                                                                                                        ((pppp.lib.stream.XDucer.$Closure$5<$T>)this).k = k;
                                                                                                                                        ((pppp.lib.stream.XDucer.$Closure$5<$T>)this).ix = ((pppp.lib.stream.Stream)(ix));
                                                                                                                                    }}
                    // synthetic type for parameter mangling
                    public static final class __2$1pppp$lib$stream$XDucer$$Closure$5$$T$2 {}
                    
                }
                
            
            }
            
            