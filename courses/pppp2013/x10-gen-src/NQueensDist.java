@x10.runtime.impl.java.X10Generated public class NQueensDist extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<NQueensDist> $RTT = x10.rtt.NamedType.<NQueensDist> make(
    "NQueensDist", NQueensDist.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensDist $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + NQueensDist.class + " calling"); } 
        $_obj.N = $deserializer.readLong();
        $_obj.P = $deserializer.readLong();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        NQueensDist $_obj = new NQueensDist((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write(this.N);
        $serializer.write(this.P);
        
    }
    
    // constructor just for allocation
    public NQueensDist(final java.lang.System[] $dummy) { 
    }
    
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
private static x10.core.Rail<x10.core.Long> expectedSolutions;
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public long N;
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public long P;
        
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
// creation method for java code (1-phase java constructor)
        public NQueensDist(final long N,
                           final long P){this((java.lang.System[]) null);
                                             NQueensDist$$init$S(N,P);}
        
        // constructor for non-virtual call
        final public NQueensDist NQueensDist$$init$S(final long N,
                                                     final long P) { {
                                                                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
;
                                                                            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"

                                                                            
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.__fieldInitializers_NQueensDist();
                                                                            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.N = N;
                                                                            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.P = P;
                                                                        }
                                                                        return this;
                                                                        }
        
        
        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public void
                                                                                                                                        start(
                                                                                                                                        ){
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist.Board t48526 =
              ((NQueensDist.Board)(new NQueensDist.Board((java.lang.System[]) null).NQueensDist$Board$$init$S(this)));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48526.search();
        }
        
        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public int
                                                                                                                                        run$O(
                                                                                                                                        ){
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final int t48527;
            {
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.FinishState x10$__var17 =
                  ((x10.lang.FinishState)(x10.lang.Runtime.<x10.core.Int> startCollectingFinish__0$1x10$lang$Runtime$$T$2(x10.rtt.Types.INT, ((x10.lang.Reducible)(NQueensDist.get$reducer())))));
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
try {{
                    {
                        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.start();
                    }
                }}catch (java.lang.Throwable __lowerer__var__0__) {
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                    
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
throw new java.lang.RuntimeException();
                }finally {{
                     
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48527 = x10.core.Int.$unbox(x10.lang.Runtime.<x10.core.Int> stopCollectingFinish$G(x10.rtt.Types.INT, ((x10.lang.FinishState)(x10$__var17))));
                 }}
                }
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return t48527;
            }
        
        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public static x10.core.Rail
                                                                                                                                        block(
                                                                                                                                        final x10.lang.LongRange R,
                                                                                                                                        final long P){
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
assert ((P) >= (((long)(0L))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long low =
              R.
                min;
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long high =
              R.
                max;
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48528 =
              ((high) - (((long)(low))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48529 =
              ((long)(((int)(1))));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long count =
              ((t48528) + (((long)(t48529))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long baseSize =
              ((count) / (((long)(P))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48530 =
              ((baseSize) * (((long)(P))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long extra =
              ((count) - (((long)(t48530))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.fun.Fun_0_1 t48542 =
              ((x10.core.fun.Fun_0_1)(new NQueensDist.$Closure$63(baseSize,
                                                                  low,
                                                                  extra)));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48543 =
              ((x10.core.Rail)(new x10.core.Rail<x10.lang.LongRange>(x10.lang.LongRange.$RTT, ((long)(P)),
                                                                     ((x10.core.fun.Fun_0_1)(t48542)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return t48543;
        }
        
        
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
@x10.runtime.impl.java.X10Generated public static class Board extends x10.core.Ref implements x10.serialization.X10JavaSerializable
                                                                                                                                      {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<Board> $RTT = x10.rtt.NamedType.<Board> make(
            "NQueensDist.Board", Board.class
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensDist.Board $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Board.class + " calling"); } 
                $_obj.q = $deserializer.readRef();
                $_obj.out$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                NQueensDist.Board $_obj = new NQueensDist.Board((java.lang.System[]) null);
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
            
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public NQueensDist out$;
                
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public x10.core.Rail<x10.core.Long> q;
                
                
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
// creation method for java code (1-phase java constructor)
                public Board(final NQueensDist out$){this((java.lang.System[]) null);
                                                         NQueensDist$Board$$init$S(out$);}
                
                // constructor for non-virtual call
                final public NQueensDist.Board NQueensDist$Board$$init$S(final NQueensDist out$) { {
                                                                                                          
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.out$ = out$;
                                                                                                          
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
;
                                                                                                          
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"

                                                                                                          
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.__fieldInitializers_NQueensDist_Board();
                                                                                                          
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.fun.Fun_0_1 t48544 =
                                                                                                            ((x10.core.fun.Fun_0_1)(new NQueensDist.Board.$Closure$60()));
                                                                                                          
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48545 =
                                                                                                            ((x10.core.Rail)(new x10.core.Rail<x10.core.Long>(x10.rtt.Types.LONG, ((long)(0L)),
                                                                                                                                                              ((x10.core.fun.Fun_0_1)(t48544)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                          
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.q = ((x10.core.Rail)(t48545));
                                                                                                      }
                                                                                                      return this;
                                                                                                      }
                
                
                
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
// creation method for java code (1-phase java constructor)
                public Board(final NQueensDist out$,
                             final x10.core.Rail<x10.core.Long> old,
                             final long newItem, __1$1x10$lang$Long$2 $dummy){this((java.lang.System[]) null);
                                                                                  NQueensDist$Board$$init$S(out$,old,newItem, (NQueensDist.Board.__1$1x10$lang$Long$2) null);}
                
                // constructor for non-virtual call
                final public NQueensDist.Board NQueensDist$Board$$init$S(final NQueensDist out$,
                                                                         final x10.core.Rail<x10.core.Long> old,
                                                                         final long newItem, __1$1x10$lang$Long$2 $dummy) { {
                                                                                                                                   
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.out$ = out$;
                                                                                                                                   
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
;
                                                                                                                                   
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"

                                                                                                                                   
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.__fieldInitializers_NQueensDist_Board();
                                                                                                                                   
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long n =
                                                                                                                                     ((x10.core.Rail<x10.core.Long>)old).
                                                                                                                                       size;
                                                                                                                                   
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48549 =
                                                                                                                                     ((n) + (((long)(1L))));
                                                                                                                                   
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.fun.Fun_0_1 t48550 =
                                                                                                                                     ((x10.core.fun.Fun_0_1)(new NQueensDist.Board.$Closure$61(n,
                                                                                                                                                                                               old,
                                                                                                                                                                                               newItem, (NQueensDist.Board.$Closure$61.__1$1x10$lang$Long$2) null)));
                                                                                                                                   
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48551 =
                                                                                                                                     ((x10.core.Rail)(new x10.core.Rail<x10.core.Long>(x10.rtt.Types.LONG, t48549,
                                                                                                                                                                                       ((x10.core.fun.Fun_0_1)(t48550)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                                                   
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.q = ((x10.core.Rail)(t48551));
                                                                                                                               }
                                                                                                                               return this;
                                                                                                                               }
                
                
                
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public boolean
                                                                                                                                                safe$O(
                                                                                                                                                final long j){
                    
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48552 =
                      ((x10.core.Rail)(q));
                    
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long n =
                      ((x10.core.Rail<x10.core.Long>)t48552).
                        size;
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long i48492min48648 =
                      0L;
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long i48492max48649 =
                      ((n) - (((long)(1L))));
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
long i48645 =
                      i48492min48648;
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
for (;
                                                                                                                                                       true;
                                                                                                                                                       ) {
                        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48646 =
                          i48645;
                        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48647 =
                          ((t48646) <= (((long)(i48492max48649))));
                        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (!(t48647)) {
                            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
break;
                        }
                        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long k48642 =
                          i48645;
                        
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48632 =
                          ((x10.core.Rail)(q));
                        
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48633 =
                          ((long[])t48632.value)[(int)k48642];
                        
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
boolean t48634 =
                          ((long) j) ==
                        ((long) t48633);
                        
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (!(t48634)) {
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48635 =
                              ((n) - (((long)(k48642))));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48636 =
                              java.lang.Math.abs(((long)(t48635)));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48637 =
                              ((x10.core.Rail)(q));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48638 =
                              ((long[])t48637.value)[(int)k48642];
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48639 =
                              ((j) - (((long)(t48638))));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48640 =
                              java.lang.Math.abs(((long)(t48639)));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48634 = ((long) t48636) ==
                            ((long) t48640);
                        }
                        
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48641 =
                          t48634;
                        
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (t48641) {
                            
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return false;
                        }
                        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48643 =
                          i48645;
                        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48644 =
                          ((t48643) + (((long)(1L))));
                        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
i48645 = t48644;
                    }
                    
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return true;
                }
                
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public void
                                                                                                                                                search(
                                                                                                                                                final x10.lang.LongRange R){
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.LongRange i48508domain48511 =
                      ((x10.lang.LongRange)(R));
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long i48508min48509 =
                      i48508domain48511.
                        min;
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long i48508max48510 =
                      i48508domain48511.
                        max;
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
long i48657 =
                      i48508min48509;
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
for (;
                                                                                                                                                       true;
                                                                                                                                                       ) {
                        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48658 =
                          i48657;
                        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48659 =
                          ((t48658) <= (((long)(i48508max48510))));
                        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (!(t48659)) {
                            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
break;
                        }
                        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long k48654 =
                          i48657;
                        
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48650 =
                          this.safe$O((long)(k48654));
                        
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (t48650) {
                            
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist t48651 =
                              this.
                                out$;
                            
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48652 =
                              ((x10.core.Rail)(q));
                            
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist.Board t48653 =
                              ((NQueensDist.Board)(new NQueensDist.Board((java.lang.System[]) null).NQueensDist$Board$$init$S(t48651,
                                                                                                                              ((x10.core.Rail)(t48652)),
                                                                                                                              ((long)(k48654)), (NQueensDist.Board.__1$1x10$lang$Long$2) null)));
                            
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48653.search();
                        }
                        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48655 =
                          i48657;
                        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48656 =
                          ((t48655) + (((long)(1L))));
                        
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
i48657 = t48656;
                    }
                }
                
                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public void
                                                                                                                                                search(
                                                                                                                                                ){
                    
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48577 =
                      ((x10.core.Rail)(q));
                    
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48579 =
                      ((x10.core.Rail<x10.core.Long>)t48577).
                        size;
                    
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist t48578 =
                      this.
                        out$;
                    
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48580 =
                      t48578.
                        N;
                    
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48581 =
                      ((long) t48579) ==
                    ((long) t48580);
                    
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (t48581) {
                        
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
x10.lang.Runtime.<x10.core.Int> makeOffer__0x10$lang$Runtime$$T(x10.rtt.Types.INT, x10.core.Int.$box(1));
                        
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return;
                    }
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48582 =
                      ((x10.core.Rail)(q));
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48583 =
                      ((x10.core.Rail<x10.core.Long>)t48582).
                        size;
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48599 =
                      ((long) t48583) ==
                    ((long) 0L);
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (t48599) {
                        
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist t48584 =
                          this.
                            out$;
                        
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48585 =
                          t48584.
                            N;
                        
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48586 =
                          ((t48585) - (((long)(1L))));
                        
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.LongRange t48588 =
                          ((x10.lang.LongRange)(new x10.lang.LongRange((java.lang.System[]) null).x10$lang$LongRange$$init$S(((long)(0L)), ((long)(t48586)))));
                        
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist t48587 =
                          this.
                            out$;
                        
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48589 =
                          t48587.
                            P;
                        
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail R =
                          NQueensDist.block(((x10.lang.LongRange)(t48588)),
                                            (long)(t48589));
                        
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.PlaceGroup t48663 =
                          x10.lang.Place.places();
                        
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.Iterator p48664 =
                          ((x10.lang.Iterable<x10.lang.Place>)t48663).iterator();
                        
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
for (;
                                                                                                                                                           true;
                                                                                                                                                           ) {
                            
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48665 =
                              ((x10.lang.Iterator<x10.lang.Place>)p48664).hasNext$O();
                            
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (!(t48665)) {
                                
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
break;
                            }
                            
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.Place p48660 =
                              ((x10.lang.Place)(((x10.lang.Iterator<x10.lang.Place>)p48664).next$G()));
                            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
x10.lang.Runtime.runAsync(((x10.lang.Place)(p48660)),
                                                                                                                                                                                    ((x10.core.fun.VoidFun_0_0)(new NQueensDist.Board.$Closure$62(this,
                                                                                                                                                                                                                                                  p48660,
                                                                                                                                                                                                                                                  R, (NQueensDist.Board.$Closure$62.__2$1x10$lang$LongRange$2) null))),
                                                                                                                                                                                    ((x10.lang.Runtime.Profile)(null)));
                        }
                    } else {
                        
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist t48595 =
                          this.
                            out$;
                        
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48596 =
                          t48595.
                            N;
                        
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48597 =
                          ((t48596) - (((long)(1L))));
                        
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.LongRange t48598 =
                          ((x10.lang.LongRange)(new x10.lang.LongRange((java.lang.System[]) null).x10$lang$LongRange$$init$S(((long)(0L)), ((long)(t48597)))));
                        
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.search(((x10.lang.LongRange)(t48598)));
                    }
                }
                
                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final public NQueensDist.Board
                                                                                                                                                NQueensDist$Board$$this$NQueensDist$Board(
                                                                                                                                                ){
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return NQueensDist.Board.this;
                }
                
                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final public NQueensDist
                                                                                                                                                NQueensDist$Board$$this$NQueensDist(
                                                                                                                                                ){
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist t48600 =
                      this.
                        out$;
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return t48600;
                }
                
                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final public void
                                                                                                                                                __fieldInitializers_NQueensDist_Board(
                                                                                                                                                ){
                    
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$60 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$60> $RTT = x10.rtt.StaticFunType.<$Closure$60> make(
                    $Closure$60.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.LONG)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensDist.Board.$Closure$60 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$60.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensDist.Board.$Closure$60 $_obj = new NQueensDist.Board.$Closure$60((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$60(final java.lang.System[] $dummy) { 
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
                          final long id$19067){
                            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return 0L;
                        }
                        
                        public $Closure$60() { {
                                                      
                                                  }}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$61 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$61> $RTT = x10.rtt.StaticFunType.<$Closure$61> make(
                    $Closure$61.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.LONG)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensDist.Board.$Closure$61 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$61.class + " calling"); } 
                        $_obj.n = $deserializer.readLong();
                        $_obj.old = $deserializer.readRef();
                        $_obj.newItem = $deserializer.readLong();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensDist.Board.$Closure$61 $_obj = new NQueensDist.Board.$Closure$61((java.lang.System[]) null);
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
                    public $Closure$61(final java.lang.System[] $dummy) { 
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
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48546 =
                              ((i) < (((long)(this.
                                                n))));
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
long t48547 =
                               0;
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (t48546) {
                                
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48547 = ((long[])this.
                                                                                                                                                                                  old.value)[(int)i];
                            } else {
                                
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48547 = this.
                                                                                                                                                                         newItem;
                            }
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48548 =
                              t48547;
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return t48548;
                        }
                        
                        public long n;
                        public x10.core.Rail<x10.core.Long> old;
                        public long newItem;
                        
                        public $Closure$61(final long n,
                                           final x10.core.Rail<x10.core.Long> old,
                                           final long newItem, __1$1x10$lang$Long$2 $dummy) { {
                                                                                                     this.n = n;
                                                                                                     this.old = ((x10.core.Rail)(old));
                                                                                                     this.newItem = newItem;
                                                                                                 }}
                        // synthetic type for parameter mangling
                        public static final class __1$1x10$lang$Long$2 {}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$62 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$62> $RTT = x10.rtt.StaticVoidFunType.<$Closure$62> make(
                    $Closure$62.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensDist.Board.$Closure$62 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$62.class + " calling"); } 
                        $_obj.out$$ = $deserializer.readRef();
                        $_obj.p48660 = $deserializer.readRef();
                        $_obj.R = $deserializer.readRef();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensDist.Board.$Closure$62 $_obj = new NQueensDist.Board.$Closure$62((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                        } else {
                        $serializer.write(this.out$$);
                        }
                        if (p48660 instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.p48660);
                        } else {
                        $serializer.write(this.p48660);
                        }
                        if (R instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.R);
                        } else {
                        $serializer.write(this.R);
                        }
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$62(final java.lang.System[] $dummy) { 
                    }
                    
                        
                        public void
                          $apply(
                          ){
                            
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
try {{
                                
//#line 67 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48661 =
                                  this.
                                    p48660.
                                    id;
                                
//#line 67 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.LongRange t48662 =
                                  ((x10.lang.LongRange[])this.
                                                           R.value)[(int)t48661];
                                
//#line 67 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
this.
                                                                                                                                                                out$$.search(((x10.lang.LongRange)(t48662)));
                            }}catch (java.lang.Error __lowerer__var__0__) {
                                
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
throw __lowerer__var__0__;
                            }catch (java.lang.Throwable __lowerer__var__1__) {
                                
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                            }
                        }
                        
                        public NQueensDist.Board out$$;
                        public x10.lang.Place p48660;
                        public x10.core.Rail<x10.lang.LongRange> R;
                        
                        public $Closure$62(final NQueensDist.Board out$$,
                                           final x10.lang.Place p48660,
                                           final x10.core.Rail<x10.lang.LongRange> R, __2$1x10$lang$LongRange$2 $dummy) { {
                                                                                                                                 this.out$$ = out$$;
                                                                                                                                 this.p48660 = ((x10.lang.Place)(p48660));
                                                                                                                                 this.R = ((x10.core.Rail)(R));
                                                                                                                             }}
                        // synthetic type for parameter mangling
                        public static final class __2$1x10$lang$LongRange$2 {}
                        
                    }
                    
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Long$2 {}
                
                }
                
                
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
private static NQueensDist.Anonymous$2351 reducer;
                
                
//#line 75 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
                private static final long serialVersionUID = 1L;
                public static void main(java.lang.String[] args)  {
                // start native runtime
                new $Main().start(args);
                }
                
                // called by native runtime inside main x10 thread
                public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
                // call the original app-main method
                NQueensDist.main(args);
                }
                }
                
                // the original app-main method
                public static void main(final x10.core.Rail<java.lang.String> args)  {
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48601 =
                      ((x10.core.Rail<java.lang.String>)args).
                        size;
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48603 =
                      ((t48601) > (((long)(0L))));
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
long t48604 =
                       0;
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (t48603) {
                        
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48602 =
                          ((java.lang.String[])args.value)[(int)0L];
                        
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48604 = java.lang.Long.parseLong(t48602);
                    } else {
                        
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48604 = 8L;
                    }
                    
//#line 76 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long n =
                      t48604;
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.io.Printer t48605 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48606 =
                      (("N=") + ((x10.core.Long.$box(n))));
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48605.println(((java.lang.Object)(t48606)));
                    
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long P =
                      x10.lang.Place.get$MAX_PLACES();
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final NQueensDist nq =
                      ((NQueensDist)(new NQueensDist((java.lang.System[]) null).NQueensDist$$init$S(((long)(n)),
                                                                                                    ((long)(P)))));
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48607 =
                      x10.lang.System.nanoTime$O();
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
long start =
                      (-(t48607));
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final int t48608 =
                      nq.run$O();
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long answer =
                      ((long)(((int)(t48608))));
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.core.Rail t48609 =
                      ((x10.core.Rail)(NQueensDist.get$expectedSolutions()));
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48610 =
                      ((long[])t48609.value)[(int)n];
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean result =
                      ((long) answer) ==
                    ((long) t48610);
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48611 =
                      start;
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48612 =
                      x10.lang.System.nanoTime$O();
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48613 =
                      ((t48611) + (((long)(t48612))));
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
start = t48613;
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48614 =
                      start;
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48615 =
                      ((t48614) / (((long)(1000000L))));
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
start = t48615;
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.io.Printer t48629 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48616 =
                      nq.
                        N;
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48617 =
                      (("NQueensDist ") + ((x10.core.Long.$box(t48616))));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48618 =
                      ((t48617) + ("(P="));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48619 =
                      ((t48618) + ((x10.core.Long.$box(P))));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48620 =
                      ((t48619) + (") has "));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48621 =
                      ((t48620) + ((x10.core.Long.$box(answer))));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48623 =
                      ((t48621) + (" solutions"));
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
java.lang.String t48622 =
                       null;
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (result) {
                        
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48622 = " (ok)";
                    } else {
                        
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48622 = " (wrong)";
                    }
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48624 =
                      t48622;
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48625 =
                      ((t48623) + (t48624));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48626 =
                      ((t48625) + (" (t="));
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48627 =
                      start;
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48628 =
                      ((t48626) + ((x10.core.Long.$box(t48627))));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final java.lang.String t48630 =
                      ((t48628) + ("ms)."));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48629.println(((java.lang.Object)(t48630)));
                }
                
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final public NQueensDist
                                                                                                                                               NQueensDist$$this$NQueensDist(
                                                                                                                                               ){
                    
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return NQueensDist.this;
                }
                
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final public void
                                                                                                                                               __fieldInitializers_NQueensDist(
                                                                                                                                               ){
                    
                }
                
                
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
@x10.runtime.impl.java.X10Generated final public static class Anonymous$2351 extends x10.core.Ref implements x10.lang.Reducible, x10.serialization.X10JavaSerializable
                                                                                                                                              {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<Anonymous$2351> $RTT = x10.rtt.NamedType.<Anonymous$2351> make(
                    "NQueensDist.Anonymous$2351", Anonymous$2351.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.lang.Reducible.$RTT, x10.rtt.Types.INT)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensDist.Anonymous$2351 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Anonymous$2351.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensDist.Anonymous$2351 $_obj = new NQueensDist.Anonymous$2351((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public Anonymous$2351(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public x10.lang.Reducible.operator()(T,T):T
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
                    return x10.core.Int.$box($apply$O(x10.core.Int.$unbox(a1), x10.core.Int.$unbox(a2)));
                    }
                    // dispatcher for method abstract public x10.lang.Reducible.operator()(T,T):T
                    public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
                    return $apply$O(x10.core.Int.$unbox(a1), x10.core.Int.$unbox(a2));
                    }
                    // bridge for method abstract public x10.lang.Reducible.zero():T
                    public x10.core.Int
                      zero$G(){return x10.core.Int.$box(zero$O());}
                    
                        
                        
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public int
                                                                                                                                                        zero$O(
                                                                                                                                                        ){
                            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return 0;
                        }
                        
                        
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
public int
                                                                                                                                                        $apply$O(
                                                                                                                                                        final int i,
                                                                                                                                                        final int j){
                            
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final int t48631 =
                              ((i) + (((int)(j))));
                            
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return t48631;
                        }
                        
                        
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
// creation method for java code (1-phase java constructor)
                        public Anonymous$2351(){this((java.lang.System[]) null);
                                                    NQueensDist$Anonymous$2351$$init$S();}
                        
                        // constructor for non-virtual call
                        final public NQueensDist.Anonymous$2351 NQueensDist$Anonymous$2351$$init$S() { {
                                                                                                              
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
                    if (((int) NQueensDist.initStatus$expectedSolutions.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                        return NQueensDist.expectedSolutions;
                    }
                    if (((int) NQueensDist.initStatus$expectedSolutions.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensDist.expectedSolutions")));
                        }
                        throw NQueensDist.exception$expectedSolutions;
                    }
                    if (NQueensDist.initStatus$expectedSolutions.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                               (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                        try {{
                            NQueensDist.expectedSolutions = ((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.core.Long> makeRailFromJavaArray(x10.rtt.Types.LONG, new long[] {0L, 1L, 0L, 0L, 2L, 10L, 4L, 40L, 92L, 352L, 724L, 2680L, 14200L, 73712L, 365596L, 2279184L, 14772512L})));
                        }}catch (java.lang.Throwable exc$48676) {
                            NQueensDist.exception$expectedSolutions = new x10.lang.ExceptionInInitializer(exc$48676);
                            NQueensDist.initStatus$expectedSolutions.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                            throw NQueensDist.exception$expectedSolutions;
                        }
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: NQueensDist.expectedSolutions")));
                        }
                        NQueensDist.initStatus$expectedSolutions.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                        x10.runtime.impl.java.InitDispatcher.lockInitialized();
                        x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    } else {
                        if (NQueensDist.initStatus$expectedSolutions.get() <
                            x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            while (NQueensDist.initStatus$expectedSolutions.get() <
                                   x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                                x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                            }
                            x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                            if (((int) NQueensDist.initStatus$expectedSolutions.get()) ==
                                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                      ((boolean) true)) {
                                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensDist.expectedSolutions")));
                                }
                                throw NQueensDist.exception$expectedSolutions;
                            }
                        }
                    }
                    return NQueensDist.expectedSolutions;
                }
                
                public static NQueensDist.Anonymous$2351
                  get$reducer(
                  ){
                    if (((int) NQueensDist.initStatus$reducer.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                        return NQueensDist.reducer;
                    }
                    if (((int) NQueensDist.initStatus$reducer.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensDist.reducer")));
                        }
                        throw NQueensDist.exception$reducer;
                    }
                    if (NQueensDist.initStatus$reducer.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                     (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                        try {{
                            NQueensDist.reducer = ((NQueensDist.Anonymous$2351)(new NQueensDist.Anonymous$2351((java.lang.System[]) null).NQueensDist$Anonymous$2351$$init$S()));
                        }}catch (java.lang.Throwable exc$48677) {
                            NQueensDist.exception$reducer = new x10.lang.ExceptionInInitializer(exc$48677);
                            NQueensDist.initStatus$reducer.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                            throw NQueensDist.exception$reducer;
                        }
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: NQueensDist.reducer")));
                        }
                        NQueensDist.initStatus$reducer.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                        x10.runtime.impl.java.InitDispatcher.lockInitialized();
                        x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    } else {
                        if (NQueensDist.initStatus$reducer.get() <
                            x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            while (NQueensDist.initStatus$reducer.get() <
                                   x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                                x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                            }
                            x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                            if (((int) NQueensDist.initStatus$reducer.get()) ==
                                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                      ((boolean) true)) {
                                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensDist.reducer")));
                                }
                                throw NQueensDist.exception$reducer;
                            }
                        }
                    }
                    return NQueensDist.reducer;
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$63 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$63> $RTT = x10.rtt.StaticFunType.<$Closure$63> make(
                    $Closure$63.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.lang.LongRange.$RTT)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensDist.$Closure$63 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$63.class + " calling"); } 
                        $_obj.baseSize = $deserializer.readLong();
                        $_obj.low = $deserializer.readLong();
                        $_obj.extra = $deserializer.readLong();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensDist.$Closure$63 $_obj = new NQueensDist.$Closure$63((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        $serializer.write(this.baseSize);
                        $serializer.write(this.low);
                        $serializer.write(this.extra);
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$63(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public x10.lang.LongRange
                          $apply(
                          final long i){
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48531 =
                              ((i) * (((long)(this.
                                                baseSize))));
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48534 =
                              ((this.
                                  low) + (((long)(t48531))));
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48532 =
                              ((i) < (((long)(this.
                                                extra))));
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
long t48533 =
                               0;
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (t48532) {
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48533 = i;
                            } else {
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48533 = this.
                                                                                                                                                                         extra;
                            }
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48535 =
                              t48533;
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long start =
                              ((t48534) + (((long)(t48535))));
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48538 =
                              ((start) + (((long)(this.
                                                    baseSize))));
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final boolean t48536 =
                              ((i) < (((long)(this.
                                                extra))));
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
long t48537 =
                               0;
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
if (t48536) {
                                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48537 = 0L;
                            } else {
                                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
t48537 = -1L;
                            }
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48539 =
                              t48537;
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final long t48540 =
                              ((t48538) + (((long)(t48539))));
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
final x10.lang.LongRange t48541 =
                              ((x10.lang.LongRange)(new x10.lang.LongRange((java.lang.System[]) null).x10$lang$LongRange$$init$S(((long)(start)), ((long)(t48540)))));
                            
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensDist.x10"
return t48541;
                        }
                        
                        public long baseSize;
                        public long low;
                        public long extra;
                        
                        public $Closure$63(final long baseSize,
                                           final long low,
                                           final long extra) { {
                                                                      this.baseSize = baseSize;
                                                                      this.low = low;
                                                                      this.extra = extra;
                                                                  }}
                        
                    }
                    
                
            }
            
            