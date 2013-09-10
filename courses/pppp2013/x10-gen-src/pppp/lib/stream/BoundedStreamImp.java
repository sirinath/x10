package pppp.lib.stream;


@x10.runtime.impl.java.X10Generated public class BoundedStreamImp<$T>  extends pppp.util.BBuffer<$T> implements pppp.lib.stream.Spring, x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<BoundedStreamImp> $RTT = x10.rtt.NamedType.<BoundedStreamImp> make(
    "pppp.lib.stream.BoundedStreamImp", BoundedStreamImp.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(pppp.lib.stream.Spring.$RTT, x10.rtt.UnresolvedType.PARAM(0)), x10.rtt.ParameterizedType.make(pppp.util.BBuffer.$RTT, x10.rtt.UnresolvedType.PARAM(0))}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.BoundedStreamImp<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + BoundedStreamImp.class + " calling"); } 
        pppp.util.BBuffer.$_deserialize_body($_obj, $deserializer);
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.closed = $deserializer.readBoolean();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lib.stream.BoundedStreamImp $_obj = new pppp.lib.stream.BoundedStreamImp((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        super.$_serialize($serializer);
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        $serializer.write(this.closed);
        
    }
    
    // constructor just for allocation
    public BoundedStreamImp(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    super($dummy, $T);
    pppp.lib.stream.BoundedStreamImp.$initParams(this, $T);
    }
    // dispatcher for method abstract public pppp.lib.stream.Source[T].operator()=(t:T):void
    public java.lang.Object $set(final java.lang.Object a1, final x10.rtt.Type t1) {
    $set__0pppp$util$BBuffer$$T(($T)a1);return null;
    }
    // dispatcher for method abstract public pppp.lib.stream.Source[T].operator()=(t:T):void
    public void $set$V(final java.lang.Object a1, final x10.rtt.Type t1) {
    $set__0pppp$util$BBuffer$$T(($T)a1);
    }
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final BoundedStreamImp $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final public static int DEFAULT_SIZE = 200;
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
public boolean closed;
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
// creation method for java code (1-phase java constructor)
        public BoundedStreamImp(final x10.rtt.Type $T){this((java.lang.System[]) null, $T);
                                                           pppp$lib$stream$BoundedStreamImp$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.BoundedStreamImp<$T> pppp$lib$stream$BoundedStreamImp$$init$S() { {
                                                                                                              
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final int t49413 =
                                                                                                                pppp.lib.stream.BoundedStreamImp.DEFAULT_SIZE;
                                                                                                              
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final $T t49414 =
                                                                                                                (($T)(($T) x10.rtt.Types.zeroValue($T)));
                                                                                                              
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
/*this.*/pppp$lib$stream$BoundedStreamImp$$init$S(((java.lang.String)(null)),
                                                                                                                                                                                                                                                                                                                   ((int)(t49413)),
                                                                                                                                                                                                                                                                                                                   t49414, (pppp.lib.stream.BoundedStreamImp.__2pppp$lib$stream$BoundedStreamImp$$T) null);
                                                                                                          }
                                                                                                          return this;
                                                                                                          }
        
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
// creation method for java code (1-phase java constructor)
        public BoundedStreamImp(final x10.rtt.Type $T,
                                final java.lang.String s){this((java.lang.System[]) null, $T);
                                                              pppp$lib$stream$BoundedStreamImp$$init$S(s);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.BoundedStreamImp<$T> pppp$lib$stream$BoundedStreamImp$$init$S(final java.lang.String s) { {
                                                                                                                                      
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final int t49415 =
                                                                                                                                        pppp.lib.stream.BoundedStreamImp.DEFAULT_SIZE;
                                                                                                                                      
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final $T t49416 =
                                                                                                                                        (($T)(($T) x10.rtt.Types.zeroValue($T)));
                                                                                                                                      
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
/*this.*/pppp$lib$stream$BoundedStreamImp$$init$S(((java.lang.String)(s)),
                                                                                                                                                                                                                                                                                                                                           ((int)(t49415)),
                                                                                                                                                                                                                                                                                                                                           t49416, (pppp.lib.stream.BoundedStreamImp.__2pppp$lib$stream$BoundedStreamImp$$T) null);
                                                                                                                                  }
                                                                                                                                  return this;
                                                                                                                                  }
        
        
        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
// creation method for java code (1-phase java constructor)
        public BoundedStreamImp(final x10.rtt.Type $T,
                                final java.lang.String s,
                                final int N,
                                final $T zero, __2pppp$lib$stream$BoundedStreamImp$$T $dummy){this((java.lang.System[]) null, $T);
                                                                                                  pppp$lib$stream$BoundedStreamImp$$init$S(s,N,zero, (pppp.lib.stream.BoundedStreamImp.__2pppp$lib$stream$BoundedStreamImp$$T) null);}
        
        // constructor for non-virtual call
        final public pppp.lib.stream.BoundedStreamImp<$T> pppp$lib$stream$BoundedStreamImp$$init$S(final java.lang.String s,
                                                                                                   final int N,
                                                                                                   final $T zero, __2pppp$lib$stream$BoundedStreamImp$$T $dummy) { {
                                                                                                                                                                          
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
/*super.*/pppp$util$BBuffer$$init$S(((int)(N)),
                                                                                                                                                                                                                                                                                                                                                                 (($T)(zero)), (pppp.util.BBuffer.__1pppp$util$BBuffer$$T) null);
                                                                                                                                                                          
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"

                                                                                                                                                                          
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
this.__fieldInitializers_pppp_lib_stream_BoundedStreamImp();
                                                                                                                                                                          
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
this.setName(((java.lang.String)(s)));
                                                                                                                                                                      }
                                                                                                                                                                      return this;
                                                                                                                                                                      }
        
        
        
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
public void
                                                                                                                                                             close(
                                                                                                                                                             ){
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final x10.core.fun.Fun_0_0 t49418 =
              ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.BoundedStreamImp.$Closure$73<$T>($T, ((pppp.lib.stream.BoundedStreamImp<$T>)(this)), (pppp.lib.stream.BoundedStreamImp.$Closure$73.__0$1pppp$lib$stream$BoundedStreamImp$$Closure$73$$T$2) null)));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
pppp.util.Logger.log__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t49418)));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final pppp.util.Monitor t49420 =
              ((pppp.util.Monitor)(monitor));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final x10.core.fun.Fun_0_0 t49421 =
              ((x10.core.fun.Fun_0_0)(new pppp.lib.stream.BoundedStreamImp.$Closure$74<$T>($T, ((pppp.lib.stream.BoundedStreamImp<$T>)(this)), (pppp.lib.stream.BoundedStreamImp.$Closure$74.__0$1pppp$lib$stream$BoundedStreamImp$$Closure$74$$T$2) null)));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
((pppp.util.Monitor)t49420).<pppp.util.Unit> atomicBlock__0$1pppp$util$Monitor$$T$2$G(pppp.util.Unit.$RTT, ((x10.core.fun.Fun_0_0)(t49421)));
        }
        
        
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
public boolean
                                                                                                                                                             isOpen$O(
                                                                                                                                                             ){
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
boolean t49423 =
              closed;
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
if (t49423) {
                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final int t49422 =
                  size;
                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
t49423 = ((int) t49422) ==
                ((int) 0);
            }
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final boolean t49424 =
              t49423;
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final boolean t49425 =
              !(t49424);
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
return t49425;
        }
        
        
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
public boolean
                                                                                                                                                             hasSpace$O(
                                                                                                                                                             ){
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final boolean t49427 =
              closed;
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
if (t49427) {
                
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final pppp.lib.stream.StreamClosedException t49426 =
                  ((pppp.lib.stream.StreamClosedException)(new pppp.lib.stream.StreamClosedException(((java.lang.Object)(this)))));
                
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
throw t49426;
            }
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final boolean t49428 =
              super.hasSpace$O();
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
return t49428;
        }
        
        
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
public boolean
                                                                                                                                                             hasValue$O(
                                                                                                                                                             ){
            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final boolean t49429 =
              this.isOpen$O();
            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final boolean t49431 =
              !(t49429);
            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
if (t49431) {
                
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final pppp.lib.stream.StreamClosedException t49430 =
                  ((pppp.lib.stream.StreamClosedException)(new pppp.lib.stream.StreamClosedException(((java.lang.Object)(this)))));
                
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
throw t49430;
            }
            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final boolean t49432 =
              super.hasValue$O();
            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
return t49432;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final public pppp.lib.stream.BoundedStreamImp
                                                                                                                                                             pppp$lib$stream$BoundedStreamImp$$this$pppp$lib$stream$BoundedStreamImp(
                                                                                                                                                             ){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
return pppp.lib.stream.BoundedStreamImp.this;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final public void
                                                                                                                                                             __fieldInitializers_pppp_lib_stream_BoundedStreamImp(
                                                                                                                                                             ){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
((pppp.lib.stream.BoundedStreamImp<$T>)this).closed = false;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$73<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$73> $RTT = x10.rtt.StaticFunType.<$Closure$73> make(
            $Closure$73.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.BoundedStreamImp.$Closure$73<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$73.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.BoundedStreamImp.$Closure$73 $_obj = new pppp.lib.stream.BoundedStreamImp.$Closure$73((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$73(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lib.stream.BoundedStreamImp.$Closure$73.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$73 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final java.lang.String t49417 =
                      (("closing ") + (this.
                                         out$$));
                    
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
return t49417;
                }
                
                public pppp.lib.stream.BoundedStreamImp<$T> out$$;
                
                public $Closure$73(final x10.rtt.Type $T,
                                   final pppp.lib.stream.BoundedStreamImp<$T> out$$, __0$1pppp$lib$stream$BoundedStreamImp$$Closure$73$$T$2 $dummy) {pppp.lib.stream.BoundedStreamImp.$Closure$73.$initParams(this, $T);
                                                                                                                                                          {
                                                                                                                                                             ((pppp.lib.stream.BoundedStreamImp.$Closure$73<$T>)this).out$$ = out$$;
                                                                                                                                                         }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lib$stream$BoundedStreamImp$$Closure$73$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$74<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$74> $RTT = x10.rtt.StaticFunType.<$Closure$74> make(
            $Closure$74.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, pppp.util.Unit.$RTT)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lib.stream.BoundedStreamImp.$Closure$74<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$74.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lib.stream.BoundedStreamImp.$Closure$74 $_obj = new pppp.lib.stream.BoundedStreamImp.$Closure$74((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$74(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lib.stream.BoundedStreamImp.$Closure$74.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.util.Unit
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$74 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.util.Unit
                  $apply(
                  ){
                    
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
((pppp.lib.stream.BoundedStreamImp<$T>)this.
                                                                                                                                                                                                                out$$).closed = true;
                    
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
final pppp.util.Unit t49419 =
                      new pppp.util.Unit((java.lang.System[]) null).pppp$util$Unit$$init$S();
                    
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lib/stream/BoundedStreamImp.x10"
return t49419;
                }
                
                public pppp.lib.stream.BoundedStreamImp<$T> out$$;
                
                public $Closure$74(final x10.rtt.Type $T,
                                   final pppp.lib.stream.BoundedStreamImp<$T> out$$, __0$1pppp$lib$stream$BoundedStreamImp$$Closure$74$$T$2 $dummy) {pppp.lib.stream.BoundedStreamImp.$Closure$74.$initParams(this, $T);
                                                                                                                                                          {
                                                                                                                                                             ((pppp.lib.stream.BoundedStreamImp.$Closure$74<$T>)this).out$$ = out$$;
                                                                                                                                                         }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lib$stream$BoundedStreamImp$$Closure$74$$T$2 {}
                
            }
            
        
        public boolean
          pppp$util$BBuffer$hasSpace$S$O(
          ){
            return super.hasSpace$O();
        }
        
        public boolean
          pppp$util$BBuffer$hasValue$S$O(
          ){
            return super.hasValue$O();
        }
        // synthetic type for parameter mangling
        public static final class __2pppp$lib$stream$BoundedStreamImp$$T {}
        
        }
        
        