@x10.runtime.impl.java.X10Generated public class NQueensPar extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<NQueensPar> $RTT = x10.rtt.NamedType.<NQueensPar> make(
    "NQueensPar", NQueensPar.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensPar $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + NQueensPar.class + " calling"); } 
        $_obj.N = $deserializer.readLong();
        $_obj.P = $deserializer.readLong();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        NQueensPar $_obj = new NQueensPar((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write(this.N);
        $serializer.write(this.P);
        
    }
    
    // constructor just for allocation
    public NQueensPar(final java.lang.System[] $dummy) { 
    }
    
        
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
private static x10.core.Rail<x10.core.Long> expectedSolutions;
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public long N;
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public long P;
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
// creation method for java code (1-phase java constructor)
        public NQueensPar(final long N,
                          final long P){this((java.lang.System[]) null);
                                            NQueensPar$$init$S(N,P);}
        
        // constructor for non-virtual call
        final public NQueensPar NQueensPar$$init$S(final long N,
                                                   final long P) { {
                                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
;
                                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"

                                                                          
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.__fieldInitializers_NQueensPar();
                                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.N = N;
                                                                          
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.P = P;
                                                                      }
                                                                      return this;
                                                                      }
        
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public void
                                                                                                                                      start(
                                                                                                                                      ){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar.Board t46283 =
              ((NQueensPar.Board)(new NQueensPar.Board((java.lang.System[]) null).NQueensPar$Board$$init$S(this)));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46283.search();
        }
        
        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public static x10.core.Rail
                                                                                                                                       block(
                                                                                                                                       final x10.lang.LongRange R,
                                                                                                                                       final long P){
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
assert ((P) >= (((long)(0L))));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long low =
              R.
                min;
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long high =
              R.
                max;
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46284 =
              ((high) - (((long)(low))));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46285 =
              ((long)(((int)(1))));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long count =
              ((t46284) + (((long)(t46285))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long baseSize =
              ((count) / (((long)(P))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46286 =
              ((baseSize) * (((long)(P))));
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long extra =
              ((count) - (((long)(t46286))));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.fun.Fun_0_1 t46298 =
              ((x10.core.fun.Fun_0_1)(new NQueensPar.$Closure$41(baseSize,
                                                                 low,
                                                                 extra)));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46299 =
              ((x10.core.Rail)(new x10.core.Rail<x10.lang.LongRange>(x10.lang.LongRange.$RTT, ((long)(P)),
                                                                     ((x10.core.fun.Fun_0_1)(t46298)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
            
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return t46299;
        }
        
        
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
@x10.runtime.impl.java.X10Generated public static class Board extends x10.core.Ref implements x10.serialization.X10JavaSerializable
                                                                                                                                     {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<Board> $RTT = x10.rtt.NamedType.<Board> make(
            "NQueensPar.Board", Board.class
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensPar.Board $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Board.class + " calling"); } 
                $_obj.q = $deserializer.readRef();
                $_obj.out$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                NQueensPar.Board $_obj = new NQueensPar.Board((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (q instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.q);
                } else {
                $serializer.write(this.q);
                }
                if (out$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$);
                } else {
                $serializer.write(this.out$);
                }
                
            }
            
            // constructor just for allocation
            public Board(final java.lang.System[] $dummy) { 
            }
            
                
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public NQueensPar out$;
                
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public x10.core.Rail<x10.core.Long> q;
                
                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
// creation method for java code (1-phase java constructor)
                public Board(final NQueensPar out$){this((java.lang.System[]) null);
                                                        NQueensPar$Board$$init$S(out$);}
                
                // constructor for non-virtual call
                final public NQueensPar.Board NQueensPar$Board$$init$S(final NQueensPar out$) { {
                                                                                                       
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.out$ = out$;
                                                                                                       
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
;
                                                                                                       
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"

                                                                                                       
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.__fieldInitializers_NQueensPar_Board();
                                                                                                       
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.fun.Fun_0_1 t46300 =
                                                                                                         ((x10.core.fun.Fun_0_1)(new NQueensPar.Board.$Closure$38()));
                                                                                                       
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46301 =
                                                                                                         ((x10.core.Rail)(new x10.core.Rail<x10.core.Long>(x10.rtt.Types.LONG, ((long)(0L)),
                                                                                                                                                           ((x10.core.fun.Fun_0_1)(t46300)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                       
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.q = ((x10.core.Rail)(t46301));
                                                                                                   }
                                                                                                   return this;
                                                                                                   }
                
                
                
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
// creation method for java code (1-phase java constructor)
                public Board(final NQueensPar out$,
                             final x10.core.Rail<x10.core.Long> old,
                             final long newItem, __1$1x10$lang$Long$2 $dummy){this((java.lang.System[]) null);
                                                                                  NQueensPar$Board$$init$S(out$,old,newItem, (NQueensPar.Board.__1$1x10$lang$Long$2) null);}
                
                // constructor for non-virtual call
                final public NQueensPar.Board NQueensPar$Board$$init$S(final NQueensPar out$,
                                                                       final x10.core.Rail<x10.core.Long> old,
                                                                       final long newItem, __1$1x10$lang$Long$2 $dummy) { {
                                                                                                                                 
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.out$ = out$;
                                                                                                                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
;
                                                                                                                                 
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"

                                                                                                                                 
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.__fieldInitializers_NQueensPar_Board();
                                                                                                                                 
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long n =
                                                                                                                                   ((x10.core.Rail<x10.core.Long>)old).
                                                                                                                                     size;
                                                                                                                                 
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46305 =
                                                                                                                                   ((n) + (((long)(1L))));
                                                                                                                                 
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.fun.Fun_0_1 t46306 =
                                                                                                                                   ((x10.core.fun.Fun_0_1)(new NQueensPar.Board.$Closure$39(n,
                                                                                                                                                                                            old,
                                                                                                                                                                                            newItem, (NQueensPar.Board.$Closure$39.__1$1x10$lang$Long$2) null)));
                                                                                                                                 
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46307 =
                                                                                                                                   ((x10.core.Rail)(new x10.core.Rail<x10.core.Long>(x10.rtt.Types.LONG, t46305,
                                                                                                                                                                                     ((x10.core.fun.Fun_0_1)(t46306)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                                                 
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.q = ((x10.core.Rail)(t46307));
                                                                                                                             }
                                                                                                                             return this;
                                                                                                                             }
                
                
                
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public boolean
                                                                                                                                               safe$O(
                                                                                                                                               final long j){
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46308 =
                      ((x10.core.Rail)(q));
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long n =
                      ((x10.core.Rail<x10.core.Long>)t46308).
                        size;
                    
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
long k46428 =
                      0L;
                    
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
for (;
                                                                                                                                                      true;
                                                                                                                                                      ) {
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46429 =
                          k46428;
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46430 =
                          ((t46429) < (((long)(n))));
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (!(t46430)) {
                            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
break;
                        }
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46413 =
                          ((x10.core.Rail)(q));
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46414 =
                          k46428;
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46415 =
                          ((long[])t46413.value)[(int)t46414];
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
boolean t46416 =
                          ((long) j) ==
                        ((long) t46415);
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (!(t46416)) {
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46417 =
                              k46428;
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46418 =
                              ((n) - (((long)(t46417))));
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46419 =
                              java.lang.Math.abs(((long)(t46418)));
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46420 =
                              ((x10.core.Rail)(q));
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46421 =
                              k46428;
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46422 =
                              ((long[])t46420.value)[(int)t46421];
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46423 =
                              ((j) - (((long)(t46422))));
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46424 =
                              java.lang.Math.abs(((long)(t46423)));
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46416 = ((long) t46419) ==
                            ((long) t46424);
                        }
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46425 =
                          t46416;
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (t46425) {
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return false;
                        }
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46426 =
                          k46428;
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46427 =
                          ((t46426) + (((long)(1L))));
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
k46428 = t46427;
                    }
                    
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return true;
                }
                
                
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public void
                                                                                                                                               search(
                                                                                                                                               final x10.lang.LongRange range){
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.lang.LongRange i46251domain46254 =
                      ((x10.lang.LongRange)(range));
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long i46251min46252 =
                      i46251domain46254.
                        min;
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long i46251max46253 =
                      i46251domain46254.
                        max;
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
long i46438 =
                      i46251min46252;
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
for (;
                                                                                                                                                      true;
                                                                                                                                                      ) {
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46439 =
                          i46438;
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46440 =
                          ((t46439) <= (((long)(i46251max46253))));
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (!(t46440)) {
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
break;
                        }
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long k46435 =
                          i46438;
                        
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46431 =
                          this.safe$O((long)(k46435));
                        
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (t46431) {
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar t46432 =
                              this.
                                out$;
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46433 =
                              ((x10.core.Rail)(q));
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar.Board t46434 =
                              ((NQueensPar.Board)(new NQueensPar.Board((java.lang.System[]) null).NQueensPar$Board$$init$S(t46432,
                                                                                                                           ((x10.core.Rail)(t46433)),
                                                                                                                           ((long)(k46435)), (NQueensPar.Board.__1$1x10$lang$Long$2) null)));
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46434.search();
                        }
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46436 =
                          i46438;
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46437 =
                          ((t46436) + (((long)(1L))));
                        
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
i46438 = t46437;
                    }
                }
                
                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public void
                                                                                                                                               search(
                                                                                                                                               ){
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46336 =
                      ((x10.core.Rail)(q));
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46338 =
                      ((x10.core.Rail<x10.core.Long>)t46336).
                        size;
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar t46337 =
                      this.
                        out$;
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46339 =
                      t46337.
                        N;
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46340 =
                      ((long) t46338) ==
                    ((long) t46339);
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (t46340) {
                        
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
x10.lang.Runtime.<x10.core.Int> makeOffer__0x10$lang$Runtime$$T(x10.rtt.Types.INT, x10.core.Int.$box(1));
                        
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return;
                    }
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46341 =
                      ((x10.core.Rail)(q));
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46342 =
                      ((x10.core.Rail<x10.core.Long>)t46341).
                        size;
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46361 =
                      ((long) t46342) ==
                    ((long) 0L);
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (t46361) {
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar t46343 =
                          this.
                            out$;
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46344 =
                          t46343.
                            N;
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46345 =
                          ((t46344) - (((long)(1L))));
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.lang.LongRange t46347 =
                          ((x10.lang.LongRange)(new x10.lang.LongRange((java.lang.System[]) null).x10$lang$LongRange$$init$S(((long)(0L)), ((long)(t46345)))));
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar t46346 =
                          this.
                            out$;
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46348 =
                          t46346.
                            P;
                        
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail R =
                          NQueensPar.block(((x10.lang.LongRange)(t46347)),
                                           (long)(t46348));
                        
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long i46268min46448 =
                          0L;
                        
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar t46449 =
                          this.
                            out$;
                        
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46450 =
                          t46449.
                            P;
                        
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long i46268max46451 =
                          ((t46450) - (((long)(1L))));
                        
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
long i46445 =
                          i46268min46448;
                        
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
for (;
                                                                                                                                                          true;
                                                                                                                                                          ) {
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46446 =
                              i46445;
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46447 =
                              ((t46446) <= (((long)(i46268max46451))));
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (!(t46447)) {
                                
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
break;
                            }
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long q46442 =
                              i46445;
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new NQueensPar.Board.$Closure$40(this,
                                                                                                                                                                                                                                                R,
                                                                                                                                                                                                                                                q46442, (NQueensPar.Board.$Closure$40.__1$1x10$lang$LongRange$2) null))));
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46443 =
                              i46445;
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46444 =
                              ((t46443) + (((long)(1L))));
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
i46445 = t46444;
                        }
                    } else {
                        
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar t46357 =
                          this.
                            out$;
                        
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46358 =
                          t46357.
                            N;
                        
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46359 =
                          ((t46358) - (((long)(1L))));
                        
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.lang.LongRange t46360 =
                          ((x10.lang.LongRange)(new x10.lang.LongRange((java.lang.System[]) null).x10$lang$LongRange$$init$S(((long)(0L)), ((long)(t46359)))));
                        
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.search(((x10.lang.LongRange)(t46360)));
                    }
                }
                
                
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final public NQueensPar.Board
                                                                                                                                               NQueensPar$Board$$this$NQueensPar$Board(
                                                                                                                                               ){
                    
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return NQueensPar.Board.this;
                }
                
                
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final public NQueensPar
                                                                                                                                               NQueensPar$Board$$this$NQueensPar(
                                                                                                                                               ){
                    
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar t46362 =
                      this.
                        out$;
                    
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return t46362;
                }
                
                
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final public void
                                                                                                                                               __fieldInitializers_NQueensPar_Board(
                                                                                                                                               ){
                    
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$38 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$38> $RTT = x10.rtt.StaticFunType.<$Closure$38> make(
                    $Closure$38.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.LONG)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensPar.Board.$Closure$38 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$38.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensPar.Board.$Closure$38 $_obj = new NQueensPar.Board.$Closure$38((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$38(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return x10.core.Long.$box($apply$O(x10.core.Long.$unbox(a1)));
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public long $apply$J(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply$O(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public long
                          $apply$O(
                          final long id$18948){
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return 0L;
                        }
                        
                        public $Closure$38() { {
                                                      
                                                  }}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$39 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$39> $RTT = x10.rtt.StaticFunType.<$Closure$39> make(
                    $Closure$39.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.LONG)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensPar.Board.$Closure$39 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$39.class + " calling"); } 
                        $_obj.n = $deserializer.readLong();
                        $_obj.old = $deserializer.readRef();
                        $_obj.newItem = $deserializer.readLong();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensPar.Board.$Closure$39 $_obj = new NQueensPar.Board.$Closure$39((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        $serializer.write(this.n);
                        if (old instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.old);
                        } else {
                        $serializer.write(this.old);
                        }
                        $serializer.write(this.newItem);
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$39(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return x10.core.Long.$box($apply$O(x10.core.Long.$unbox(a1)));
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public long $apply$J(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply$O(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public long
                          $apply$O(
                          final long i){
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46302 =
                              ((i) < (((long)(this.
                                                n))));
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
long t46303 =
                               0;
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (t46302) {
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46303 = ((long[])this.
                                                                                                                                                                                 old.value)[(int)i];
                            } else {
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46303 = this.
                                                                                                                                                                        newItem;
                            }
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46304 =
                              t46303;
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return t46304;
                        }
                        
                        public long n;
                        public x10.core.Rail<x10.core.Long> old;
                        public long newItem;
                        
                        public $Closure$39(final long n,
                                           final x10.core.Rail<x10.core.Long> old,
                                           final long newItem, __1$1x10$lang$Long$2 $dummy) { {
                                                                                                     this.n = n;
                                                                                                     this.old = ((x10.core.Rail)(old));
                                                                                                     this.newItem = newItem;
                                                                                                 }}
                        // synthetic type for parameter mangling
                        public static final class __1$1x10$lang$Long$2 {}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$40 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$40> $RTT = x10.rtt.StaticVoidFunType.<$Closure$40> make(
                    $Closure$40.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensPar.Board.$Closure$40 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$40.class + " calling"); } 
                        $_obj.out$$ = $deserializer.readRef();
                        $_obj.R = $deserializer.readRef();
                        $_obj.q46442 = $deserializer.readLong();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensPar.Board.$Closure$40 $_obj = new NQueensPar.Board.$Closure$40((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                        } else {
                        $serializer.write(this.out$$);
                        }
                        if (R instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.R);
                        } else {
                        $serializer.write(this.R);
                        }
                        $serializer.write(this.q46442);
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$40(final java.lang.System[] $dummy) { 
                    }
                    
                        
                        public void
                          $apply(
                          ){
                            
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
try {{
                                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.lang.LongRange t46441 =
                                  ((x10.lang.LongRange[])this.
                                                           R.value)[(int)this.
                                                                           q46442];
                                
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
this.
                                                                                                                                                               out$$.search(((x10.lang.LongRange)(t46441)));
                            }}catch (java.lang.Error __lowerer__var__0__) {
                                
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
throw __lowerer__var__0__;
                            }catch (java.lang.Throwable __lowerer__var__1__) {
                                
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                            }
                        }
                        
                        public NQueensPar.Board out$$;
                        public x10.core.Rail<x10.lang.LongRange> R;
                        public long q46442;
                        
                        public $Closure$40(final NQueensPar.Board out$$,
                                           final x10.core.Rail<x10.lang.LongRange> R,
                                           final long q46442, __1$1x10$lang$LongRange$2 $dummy) { {
                                                                                                         this.out$$ = out$$;
                                                                                                         this.R = ((x10.core.Rail)(R));
                                                                                                         this.q46442 = q46442;
                                                                                                     }}
                        // synthetic type for parameter mangling
                        public static final class __1$1x10$lang$LongRange$2 {}
                        
                    }
                    
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Long$2 {}
                
                }
                
                
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
private static NQueensPar.Anonymous$1934 reducer;
                
                
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
                private static final long serialVersionUID = 1L;
                public static void main(java.lang.String[] args)  {
                // start native runtime
                new $Main().start(args);
                }
                
                // called by native runtime inside main x10 thread
                public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
                // call the original app-main method
                NQueensPar.main(args);
                }
                }
                
                // the original app-main method
                public static void main(final x10.core.Rail<java.lang.String> args)  {
                    
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46363 =
                      ((x10.core.Rail<java.lang.String>)args).
                        size;
                    
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46365 =
                      ((t46363) > (((long)(0L))));
                    
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
long t46366 =
                       0;
                    
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (t46365) {
                        
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46364 =
                          ((java.lang.String[])args.value)[(int)0L];
                        
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46366 = java.lang.Long.parseLong(t46364);
                    } else {
                        
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46366 = 12L;
                    }
                    
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long n =
                      t46366;
                    
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.io.Printer t46367 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46368 =
                      (("N=") + ((x10.core.Long.$box(n))));
                    
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46367.println(((java.lang.Object)(t46368)));
                    
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long x;
                    {
                        
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.lang.FinishState x10$__var9 =
                          ((x10.lang.FinishState)(x10.lang.Runtime.<x10.core.Long> startCollectingFinish__0$1x10$lang$Runtime$$T$2(x10.rtt.Types.LONG, ((x10.lang.Reducible)(NQueensPar.get$reducer())))));
                        
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
try {{
                            {
                                
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar t46369 =
                                  ((NQueensPar)(new NQueensPar((java.lang.System[]) null).NQueensPar$$init$S(((long)(12L)),
                                                                                                             ((long)(1L)))));
                                
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46369.start();
                            }
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                            
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
throw new java.lang.RuntimeException();
                        }finally {{
                             
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
x = x10.core.Long.$unbox(x10.lang.Runtime.<x10.core.Long> stopCollectingFinish$G(x10.rtt.Types.LONG, ((x10.lang.FinishState)(x10$__var9))));
                         }}
                        }
                    
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail ps =
                      ((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.core.Long> makeRailFromJavaArray(x10.rtt.Types.LONG, new long[] {1L, 2L, 4L, 8L})));
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
int i46493 =
                      0;
                    {
                        
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long[] ps$value46511 =
                          ((long[])ps.value);
                        
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
for (;
                                                                                                                                                          true;
                                                                                                                                                          ) {
                            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final int t46494 =
                              i46493;
                            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46495 =
                              ((long)(((int)(t46494))));
                            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46496 =
                              ((x10.core.Rail<x10.core.Long>)ps).
                                size;
                            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46497 =
                              ((t46495) < (((long)(t46496))));
                            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (!(t46497)) {
                                
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
break;
                            }
                            
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.io.Printer t46452 =
                              ((x10.io.Printer)(x10.io.Console.get$OUT()));
                            
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final int t46453 =
                              i46493;
                            
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46454 =
                              ((long)(((int)(t46453))));
                            
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46455 =
                              ((long)ps$value46511[(int)t46454]);
                            
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46456 =
                              (("starting ") + ((x10.core.Long.$box(t46455))));
                            
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46457 =
                              ((t46456) + (" threads"));
                            
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46452.println(((java.lang.Object)(t46457)));
                            
//#line 78 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final int t46458 =
                              i46493;
                            
//#line 78 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46459 =
                              ((long)(((int)(t46458))));
                            
//#line 78 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46460 =
                              ((long)ps$value46511[(int)t46459]);
                            
//#line 78 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final NQueensPar nq46461 =
                              ((NQueensPar)(new NQueensPar((java.lang.System[]) null).NQueensPar$$init$S(((long)(n)),
                                                                                                         t46460)));
                            
//#line 79 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46462 =
                              x10.lang.System.nanoTime$O();
                            
//#line 79 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
long start46463 =
                              (-(t46462));
                            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long count46464;
                            {
                                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.lang.FinishState x10$__var10 =
                                  ((x10.lang.FinishState)(x10.lang.Runtime.<x10.core.Long> startCollectingFinish__0$1x10$lang$Runtime$$T$2(x10.rtt.Types.LONG, ((x10.lang.Reducible)(NQueensPar.get$reducer())))));
                                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
try {{
                                    {
                                        
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
nq46461.start();
                                    }
                                }}catch (java.lang.Throwable __lowerer__var__1__) {
                                    
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__1__)));
                                    
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
throw new java.lang.RuntimeException();
                                }finally {{
                                     
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
count46464 = x10.core.Long.$unbox(x10.lang.Runtime.<x10.core.Long> stopCollectingFinish$G(x10.rtt.Types.LONG, ((x10.lang.FinishState)(x10$__var10))));
                                 }}
                                }
                            
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.core.Rail t46465 =
                              ((x10.core.Rail)(NQueensPar.get$expectedSolutions()));
                            
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46466 =
                              ((long[])t46465.value)[(int)n];
                            
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean result46467 =
                              ((long) count46464) ==
                            ((long) t46466);
                            
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46468 =
                              start46463;
                            
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46469 =
                              x10.lang.System.nanoTime$O();
                            
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46470 =
                              ((t46468) + (((long)(t46469))));
                            
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
start46463 = t46470;
                            
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46471 =
                              start46463;
                            
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46472 =
                              ((t46471) / (((long)(1000000L))));
                            
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
start46463 = t46472;
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.io.Printer t46473 =
                              ((x10.io.Printer)(x10.io.Console.get$OUT()));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46474 =
                              nq46461.
                                N;
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46475 =
                              (("NQueensPar ") + ((x10.core.Long.$box(t46474))));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46476 =
                              ((t46475) + ("(P="));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final int t46477 =
                              i46493;
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46478 =
                              ((long)(((int)(t46477))));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46479 =
                              ((long)ps$value46511[(int)t46478]);
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46480 =
                              ((t46476) + ((x10.core.Long.$box(t46479))));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46481 =
                              ((t46480) + (") has "));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46482 =
                              ((t46481) + ((x10.core.Long.$box(count46464))));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46483 =
                              ((t46482) + (" solutions"));
                            
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
java.lang.String t46484 =
                               null;
                            
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (result46467) {
                                
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46484 = " (ok)";
                            } else {
                                
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46484 = " (wrong)";
                            }
                            
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46485 =
                              t46484;
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46486 =
                              ((t46483) + (t46485));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46487 =
                              ((t46486) + (" (t="));
                            
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46488 =
                              start46463;
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46489 =
                              ((t46487) + ((x10.core.Long.$box(t46488))));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final java.lang.String t46490 =
                              ((t46489) + ("ms)."));
                            
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46473.println(((java.lang.Object)(t46490)));
                            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final int t46491 =
                              i46493;
                            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final int t46492 =
                              ((t46491) + (((int)(1))));
                            
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
i46493 = t46492;
                            }
                        }
                    }
                    
                    
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final public NQueensPar
                                                                                                                                                  NQueensPar$$this$NQueensPar(
                                                                                                                                                  ){
                        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return NQueensPar.this;
                    }
                    
                    
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final public void
                                                                                                                                                  __fieldInitializers_NQueensPar(
                                                                                                                                                  ){
                        
                    }
                    
                    
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
@x10.runtime.impl.java.X10Generated final public static class Anonymous$1934 extends x10.core.Ref implements x10.lang.Reducible, x10.serialization.X10JavaSerializable
                                                                                                                                                 {
                        private static final long serialVersionUID = 1L;
                        public static final x10.rtt.RuntimeType<Anonymous$1934> $RTT = x10.rtt.NamedType.<Anonymous$1934> make(
                        "NQueensPar.Anonymous$1934", Anonymous$1934.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.lang.Reducible.$RTT, x10.rtt.Types.LONG)}
                        );
                        public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                        
                        public x10.rtt.Type<?> $getParam(int i) {return null;}
                        private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                        public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensPar.Anonymous$1934 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                        
                            if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Anonymous$1934.class + " calling"); } 
                            return $_obj;
                        }
                        
                        public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                        
                            NQueensPar.Anonymous$1934 $_obj = new NQueensPar.Anonymous$1934((java.lang.System[]) null);
                            $deserializer.record_reference($_obj);
                            return $_deserialize_body($_obj, $deserializer);
                            
                        }
                        
                        public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                        
                            
                        }
                        
                        // constructor just for allocation
                        public Anonymous$1934(final java.lang.System[] $dummy) { 
                        }
                        // dispatcher for method abstract public x10.lang.Reducible.operator()(T,T):T
                        public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
                        return x10.core.Long.$box($apply$O(x10.core.Long.$unbox(a1), x10.core.Long.$unbox(a2)));
                        }
                        // dispatcher for method abstract public x10.lang.Reducible.operator()(T,T):T
                        public long $apply$J(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
                        return $apply$O(x10.core.Long.$unbox(a1), x10.core.Long.$unbox(a2));
                        }
                        // bridge for method abstract public x10.lang.Reducible.zero():T
                        public x10.core.Long
                          zero$G(){return x10.core.Long.$box(zero$O());}
                        
                            
                            
//#line 67 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public long
                                                                                                                                                           zero$O(
                                                                                                                                                           ){
                                
//#line 67 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return 0L;
                            }
                            
                            
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
public long
                                                                                                                                                           $apply$O(
                                                                                                                                                           final long i,
                                                                                                                                                           final long j){
                                
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46412 =
                                  ((i) + (((long)(j))));
                                
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return t46412;
                            }
                            
                            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
// creation method for java code (1-phase java constructor)
                            public Anonymous$1934(){this((java.lang.System[]) null);
                                                        NQueensPar$Anonymous$1934$$init$S();}
                            
                            // constructor for non-virtual call
                            final public NQueensPar.Anonymous$1934 NQueensPar$Anonymous$1934$$init$S() { {
                                                                                                                
                                                                                                            }
                                                                                                            return this;
                                                                                                            }
                            
                        
                    }
                    
                    final private static x10.core.concurrent.AtomicInteger initStatus$reducer = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
                    private static x10.lang.ExceptionInInitializer exception$reducer;
                    final private static x10.core.concurrent.AtomicInteger initStatus$expectedSolutions = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
                    private static x10.lang.ExceptionInInitializer exception$expectedSolutions;
                    
                    public static x10.core.Rail
                      get$expectedSolutions(
                      ){
                        if (((int) NQueensPar.initStatus$expectedSolutions.get()) ==
                            ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                            return NQueensPar.expectedSolutions;
                        }
                        if (((int) NQueensPar.initStatus$expectedSolutions.get()) ==
                            ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                            if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                  ((boolean) true)) {
                                x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensPar.expectedSolutions")));
                            }
                            throw NQueensPar.exception$expectedSolutions;
                        }
                        if (NQueensPar.initStatus$expectedSolutions.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                                  (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                            try {{
                                NQueensPar.expectedSolutions = ((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.core.Long> makeRailFromJavaArray(x10.rtt.Types.LONG, new long[] {0L, 1L, 0L, 0L, 2L, 10L, 4L, 40L, 92L, 352L, 724L, 2680L, 14200L, 73712L, 365596L, 2279184L, 14772512L})));
                            }}catch (java.lang.Throwable exc$46509) {
                                NQueensPar.exception$expectedSolutions = new x10.lang.ExceptionInInitializer(exc$46509);
                                NQueensPar.initStatus$expectedSolutions.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                                throw NQueensPar.exception$expectedSolutions;
                            }
                            if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                  ((boolean) true)) {
                                x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: NQueensPar.expectedSolutions")));
                            }
                            NQueensPar.initStatus$expectedSolutions.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                        } else {
                            if (NQueensPar.initStatus$expectedSolutions.get() <
                                x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                                while (NQueensPar.initStatus$expectedSolutions.get() <
                                       x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                                    x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                                }
                                x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                                if (((int) NQueensPar.initStatus$expectedSolutions.get()) ==
                                    ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                                    if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                          ((boolean) true)) {
                                        x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensPar.expectedSolutions")));
                                    }
                                    throw NQueensPar.exception$expectedSolutions;
                                }
                            }
                        }
                        return NQueensPar.expectedSolutions;
                    }
                    
                    public static NQueensPar.Anonymous$1934
                      get$reducer(
                      ){
                        if (((int) NQueensPar.initStatus$reducer.get()) ==
                            ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                            return NQueensPar.reducer;
                        }
                        if (((int) NQueensPar.initStatus$reducer.get()) ==
                            ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                            if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                  ((boolean) true)) {
                                x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensPar.reducer")));
                            }
                            throw NQueensPar.exception$reducer;
                        }
                        if (NQueensPar.initStatus$reducer.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                        (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                            try {{
                                NQueensPar.reducer = ((NQueensPar.Anonymous$1934)(new NQueensPar.Anonymous$1934((java.lang.System[]) null).NQueensPar$Anonymous$1934$$init$S()));
                            }}catch (java.lang.Throwable exc$46510) {
                                NQueensPar.exception$reducer = new x10.lang.ExceptionInInitializer(exc$46510);
                                NQueensPar.initStatus$reducer.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                                throw NQueensPar.exception$reducer;
                            }
                            if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                  ((boolean) true)) {
                                x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: NQueensPar.reducer")));
                            }
                            NQueensPar.initStatus$reducer.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                        } else {
                            if (NQueensPar.initStatus$reducer.get() <
                                x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                                while (NQueensPar.initStatus$reducer.get() <
                                       x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                                    x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                                }
                                x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                                if (((int) NQueensPar.initStatus$reducer.get()) ==
                                    ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                                    if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                          ((boolean) true)) {
                                        x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensPar.reducer")));
                                    }
                                    throw NQueensPar.exception$reducer;
                                }
                            }
                        }
                        return NQueensPar.reducer;
                    }
                    
                    @x10.runtime.impl.java.X10Generated final public static class $Closure$41 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                    {
                        private static final long serialVersionUID = 1L;
                        public static final x10.rtt.RuntimeType<$Closure$41> $RTT = x10.rtt.StaticFunType.<$Closure$41> make(
                        $Closure$41.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.lang.LongRange.$RTT)}
                        );
                        public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                        
                        public x10.rtt.Type<?> $getParam(int i) {return null;}
                        private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                        public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensPar.$Closure$41 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                        
                            if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$41.class + " calling"); } 
                            $_obj.baseSize = $deserializer.readLong();
                            $_obj.low = $deserializer.readLong();
                            $_obj.extra = $deserializer.readLong();
                            return $_obj;
                        }
                        
                        public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                        
                            NQueensPar.$Closure$41 $_obj = new NQueensPar.$Closure$41((java.lang.System[]) null);
                            $deserializer.record_reference($_obj);
                            return $_deserialize_body($_obj, $deserializer);
                            
                        }
                        
                        public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                        
                            $serializer.write(this.baseSize);
                            $serializer.write(this.low);
                            $serializer.write(this.extra);
                            
                        }
                        
                        // constructor just for allocation
                        public $Closure$41(final java.lang.System[] $dummy) { 
                        }
                        // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                        public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                        return $apply(x10.core.Long.$unbox(a1));
                        }
                        
                            
                            public x10.lang.LongRange
                              $apply(
                              final long i){
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46287 =
                                  ((i) * (((long)(this.
                                                    baseSize))));
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46290 =
                                  ((this.
                                      low) + (((long)(t46287))));
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46288 =
                                  ((i) < (((long)(this.
                                                    extra))));
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
long t46289 =
                                   0;
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (t46288) {
                                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46289 = i;
                                } else {
                                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46289 = this.
                                                                                                                                                                            extra;
                                }
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46291 =
                                  t46289;
                                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long start =
                                  ((t46290) + (((long)(t46291))));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46294 =
                                  ((start) + (((long)(this.
                                                        baseSize))));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final boolean t46292 =
                                  ((i) < (((long)(this.
                                                    extra))));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
long t46293 =
                                   0;
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
if (t46292) {
                                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46293 = 0L;
                                } else {
                                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
t46293 = -1L;
                                }
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46295 =
                                  t46293;
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final long t46296 =
                                  ((t46294) + (((long)(t46295))));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
final x10.lang.LongRange t46297 =
                                  ((x10.lang.LongRange)(new x10.lang.LongRange((java.lang.System[]) null).x10$lang$LongRange$$init$S(((long)(start)), ((long)(t46296)))));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensPar.x10"
return t46297;
                            }
                            
                            public long baseSize;
                            public long low;
                            public long extra;
                            
                            public $Closure$41(final long baseSize,
                                               final long low,
                                               final long extra) { {
                                                                          this.baseSize = baseSize;
                                                                          this.low = low;
                                                                          this.extra = extra;
                                                                      }}
                            
                        }
                        
                    
                }
                
                