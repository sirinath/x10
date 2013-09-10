package pppp.lazystream.examples;


@x10.runtime.impl.java.X10Generated public class Sieve extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Sieve> $RTT = x10.rtt.NamedType.<Sieve> make(
    "pppp.lazystream.examples.Sieve", Sieve.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Sieve $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Sieve.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lazystream.examples.Sieve $_obj = new pppp.lazystream.examples.Sieve((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Sieve(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
public static pppp.lazystream.Stream
                                                                                                                                                           primes(
                                                                                                                                                           ){
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Stream t49372 =
              pppp.lazystream.Stream.nats((int)(2));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Stream t49373 =
              pppp.lazystream.examples.Sieve.primes__0$1x10$lang$Int$2(((pppp.lazystream.Stream)(t49372)));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
return t49373;
        }
        
        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
public static x10.core.fun.Fun_0_1
                                                                                                                                                           primeFor(
                                                                                                                                                           final int p){
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final x10.core.fun.Fun_0_1 t49376 =
              ((x10.core.fun.Fun_0_1)(new pppp.lazystream.examples.Sieve.$Closure$71(p)));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
return t49376;
        }
        
        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
public static pppp.lazystream.Stream
                                                                                                                                                           primes__0$1x10$lang$Int$2(
                                                                                                                                                           final pppp.lazystream.Stream<x10.core.Int> r){
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final x10.core.fun.Fun_0_0 t49382 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.examples.Sieve.$Closure$72(r, (pppp.lazystream.examples.Sieve.$Closure$72.__0$1x10$lang$Int$2) null)));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Stream t49383 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<x10.core.Int> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(x10.rtt.Types.INT, ((x10.core.fun.Fun_0_0)(t49382)))));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
return t49383;
        }
        
        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        Sieve.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final long t49384 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final boolean t49386 =
              ((t49384) > (((long)(0L))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
int t49387 =
               0;
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
if (t49386) {
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final java.lang.String t49385 =
                  ((java.lang.String[])args.value)[(int)0L];
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
t49387 = java.lang.Integer.parseInt(t49385);
            } else {
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
t49387 = 10;
            }
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final int N =
              t49387;
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final x10.io.Printer t49389 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final java.lang.String t49388 =
              (("primes ") + ((x10.core.Int.$box(N))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final java.lang.String t49390 =
              ((t49388) + (" = "));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
t49389.print(((java.lang.String)(t49390)));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
long time =
              x10.lang.System.nanoTime$O();
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Stream t49391 =
              pppp.lazystream.examples.Sieve.primes();
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
((pppp.lazystream.Stream<x10.core.Int>)t49391).force((int)(N));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final x10.io.Printer t49402 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final long t49392 =
              x10.lang.System.nanoTime$O();
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final long t49393 =
              time;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final long t49394 =
              ((t49392) - (((long)(t49393))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final double t49395 =
              ((double)(long)(((long)(t49394))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final double t49398 =
              ((t49395) * (((double)(1.0))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final long t49396 =
              1000000L;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final long t49397 =
              1000000000L;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final double t49399 =
              ((double)(long)(((long)(t49397))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final double t49400 =
              ((t49398) / (((double)(t49399))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final java.lang.String t49401 =
              (("Time:") + ((x10.core.Double.$box(t49400))));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final java.lang.String t49403 =
              ((t49401) + (" s."));
            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
t49402.println(((java.lang.Object)(t49403)));
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final public pppp.lazystream.examples.Sieve
                                                                                                                                                           pppp$lazystream$examples$Sieve$$this$pppp$lazystream$examples$Sieve(
                                                                                                                                                           ){
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
return pppp.lazystream.examples.Sieve.this;
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
// creation method for java code (1-phase java constructor)
        public Sieve(){this((java.lang.System[]) null);
                           pppp$lazystream$examples$Sieve$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.lazystream.examples.Sieve pppp$lazystream$examples$Sieve$$init$S() { {
                                                                                                      
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
;
                                                                                                      
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"

                                                                                                      
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
this.__fieldInitializers_pppp_lazystream_examples_Sieve();
                                                                                                  }
                                                                                                  return this;
                                                                                                  }
        
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final public void
                                                                                                                                                           __fieldInitializers_pppp_lazystream_examples_Sieve(
                                                                                                                                                           ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$71 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$71> $RTT = x10.rtt.StaticFunType.<$Closure$71> make(
            $Closure$71.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.INT, x10.rtt.Types.BOOLEAN)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Sieve.$Closure$71 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$71.class + " calling"); } 
                $_obj.p = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.Sieve.$Closure$71 $_obj = new pppp.lazystream.examples.Sieve.$Closure$71((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write(this.p);
                
            }
            
            // constructor just for allocation
            public $Closure$71(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return x10.core.Boolean.$box($apply$O(x10.core.Int.$unbox(a1)));
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public boolean $apply$Z(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply$O(x10.core.Int.$unbox(a1));
            }
            
                
                public boolean
                  $apply$O(
                  final int x){
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final int t49374 =
                      ((x) % (((int)(this.
                                       p))));
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final boolean t49375 =
                      ((int) t49374) !=
                    ((int) 0);
                    
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
return t49375;
                }
                
                public int p;
                
                public $Closure$71(final int p) { {
                                                         this.p = p;
                                                     }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$72 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$72> $RTT = x10.rtt.StaticFunType.<$Closure$72> make(
            $Closure$72.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.Types.INT))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.examples.Sieve.$Closure$72 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$72.class + " calling"); } 
                $_obj.r = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.examples.Sieve.$Closure$72 $_obj = new pppp.lazystream.examples.Sieve.$Closure$72((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (r instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.r);
                } else {
                $serializer.write(this.r);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$72(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Cons rc =
                      ((pppp.lazystream.Cons<x10.core.Int>)
                        ((pppp.lazystream.Stream<x10.core.Int>)this.
                                                                 r).$apply());
                    
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final int p =
                      x10.core.Int.$unbox(((pppp.lazystream.Cons<x10.core.Int>)rc).
                                            h);
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Stream t49377 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<x10.core.Int>)rc).
                                                  t));
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final x10.core.fun.Fun_0_1 t49378 =
                      pppp.lazystream.examples.Sieve.primeFor((int)(p));
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Stream t49379 =
                      ((pppp.lazystream.Stream<x10.core.Int>)
                        ((pppp.lazystream.Stream<x10.core.Int>)t49377).$percent__0$1pppp$lazystream$Stream$$T$3x10$lang$Boolean$2(((x10.core.fun.Fun_0_1)(t49378))));
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Stream t49380 =
                      pppp.lazystream.examples.Sieve.primes__0$1x10$lang$Int$2(((pppp.lazystream.Stream)(t49379)));
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
final pppp.lazystream.Cons t49381 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<x10.core.Int>((java.lang.System[]) null, x10.rtt.Types.INT).pppp$lazystream$Cons$$init$S((x10.core.Int.$box(p)),
                                                                                                                                                                t49380, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/examples/Sieve.x10"
return t49381;
                }
                
                public pppp.lazystream.Stream<x10.core.Int> r;
                
                public $Closure$72(final pppp.lazystream.Stream<x10.core.Int> r, __0$1x10$lang$Int$2 $dummy) { {
                                                                                                                      this.r = ((pppp.lazystream.Stream)(r));
                                                                                                                  }}
                // synthetic type for parameter mangling
                public static final class __0$1x10$lang$Int$2 {}
                
            }
            
        
        }
        
        