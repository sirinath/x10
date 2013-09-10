@x10.runtime.impl.java.X10Generated public class NQueens extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<NQueens> $RTT = x10.rtt.NamedType.<NQueens> make(
    "NQueens", NQueens.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueens $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + NQueens.class + " calling"); } 
        $_obj.nSolutions = $deserializer.readLong();
        $_obj.N = $deserializer.readInt();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        NQueens $_obj = new NQueens((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write(this.nSolutions);
        $serializer.write(this.N);
        
    }
    
    // constructor just for allocation
    public NQueens(final java.lang.System[] $dummy) { 
    }
    
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public long nSolutions;
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
private static x10.core.Rail<x10.core.Long> expectedSolutions;
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public int N;
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
// creation method for java code (1-phase java constructor)
        public NQueens(final int N){this((java.lang.System[]) null);
                                        NQueens$$init$S(N);}
        
        // constructor for non-virtual call
        final public NQueens NQueens$$init$S(final int N) { {
                                                                   
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
;
                                                                   
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"

                                                                   
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.__fieldInitializers_NQueens();
                                                                   
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.N = N;
                                                               }
                                                               return this;
                                                               }
        
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public void
                                                                                                                                   start(
                                                                                                                                   ){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final NQueens.Board t51188 =
              ((NQueens.Board)(new NQueens.Board((java.lang.System[]) null).NQueens$Board$$init$S(this)));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51188.search();
        }
        
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
@x10.runtime.impl.java.X10Generated public static class Board extends x10.core.Ref implements x10.serialization.X10JavaSerializable
                                                                                                                                  {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<Board> $RTT = x10.rtt.NamedType.<Board> make(
            "NQueens.Board", Board.class
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueens.Board $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Board.class + " calling"); } 
                $_obj.q = $deserializer.readRef();
                $_obj.out$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                NQueens.Board $_obj = new NQueens.Board((java.lang.System[]) null);
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
            
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public NQueens out$;
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public x10.core.Rail<x10.core.Int> q;
                
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
// creation method for java code (1-phase java constructor)
                public Board(final NQueens out$){this((java.lang.System[]) null);
                                                     NQueens$Board$$init$S(out$);}
                
                // constructor for non-virtual call
                final public NQueens.Board NQueens$Board$$init$S(final NQueens out$) { {
                                                                                              
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.out$ = out$;
                                                                                              
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
;
                                                                                              
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"

                                                                                              
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.__fieldInitializers_NQueens_Board();
                                                                                              
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51189 =
                                                                                                ((long)(((int)(0))));
                                                                                              
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.fun.Fun_0_1 t51190 =
                                                                                                ((x10.core.fun.Fun_0_1)(new NQueens.Board.$Closure$83()));
                                                                                              
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.Rail t51191 =
                                                                                                ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, t51189,
                                                                                                                                                 ((x10.core.fun.Fun_0_1)(t51190)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                              
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.q = ((x10.core.Rail)(t51191));
                                                                                          }
                                                                                          return this;
                                                                                          }
                
                
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
// creation method for java code (1-phase java constructor)
                public Board(final NQueens out$,
                             final x10.core.Rail<x10.core.Int> old,
                             final int newItem, __1$1x10$lang$Int$2 $dummy){this((java.lang.System[]) null);
                                                                                NQueens$Board$$init$S(out$,old,newItem, (NQueens.Board.__1$1x10$lang$Int$2) null);}
                
                // constructor for non-virtual call
                final public NQueens.Board NQueens$Board$$init$S(final NQueens out$,
                                                                 final x10.core.Rail<x10.core.Int> old,
                                                                 final int newItem, __1$1x10$lang$Int$2 $dummy) { {
                                                                                                                         
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.out$ = out$;
                                                                                                                         
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
;
                                                                                                                         
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"

                                                                                                                         
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.__fieldInitializers_NQueens_Board();
                                                                                                                         
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long n =
                                                                                                                           ((x10.core.Rail<x10.core.Int>)old).
                                                                                                                             size;
                                                                                                                         
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51195 =
                                                                                                                           ((n) + (((long)(1L))));
                                                                                                                         
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.fun.Fun_0_1 t51196 =
                                                                                                                           ((x10.core.fun.Fun_0_1)(new NQueens.Board.$Closure$84(n,
                                                                                                                                                                                 old,
                                                                                                                                                                                 newItem, (NQueens.Board.$Closure$84.__1$1x10$lang$Int$2) null)));
                                                                                                                         
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.Rail t51197 =
                                                                                                                           ((x10.core.Rail)(new x10.core.Rail<x10.core.Int>(x10.rtt.Types.INT, t51195,
                                                                                                                                                                            ((x10.core.fun.Fun_0_1)(t51196)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                                         
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.q = ((x10.core.Rail)(t51197));
                                                                                                                     }
                                                                                                                     return this;
                                                                                                                     }
                
                
                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public boolean
                                                                                                                                            safe$O(
                                                                                                                                            final int j){
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.Rail t51198 =
                      ((x10.core.Rail)(q));
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51199 =
                      ((x10.core.Rail<x10.core.Int>)t51198).
                        size;
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int n =
                      ((int)(long)(((long)(t51199))));
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int i51154min51287 =
                      0;
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int i51154max51288 =
                      ((n) - (((int)(1))));
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
int i51284 =
                      i51154min51287;
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51285 =
                          i51284;
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final boolean t51286 =
                          ((t51285) <= (((int)(i51154max51288))));
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (!(t51286)) {
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
break;
                        }
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int k51281 =
                          i51284;
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.Rail t51269 =
                          ((x10.core.Rail)(q));
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51270 =
                          ((long)(((int)(k51281))));
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51271 =
                          ((int[])t51269.value)[(int)t51270];
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
boolean t51272 =
                          ((int) j) ==
                        ((int) t51271);
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (!(t51272)) {
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51273 =
                              ((n) - (((int)(k51281))));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51274 =
                              java.lang.Math.abs(((int)(t51273)));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.Rail t51275 =
                              ((x10.core.Rail)(q));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51276 =
                              ((long)(((int)(k51281))));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51277 =
                              ((int[])t51275.value)[(int)t51276];
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51278 =
                              ((j) - (((int)(t51277))));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51279 =
                              java.lang.Math.abs(((int)(t51278)));
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51272 = ((int) t51274) ==
                            ((int) t51279);
                        }
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final boolean t51280 =
                          t51272;
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (t51280) {
                            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
return false;
                        }
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51282 =
                          i51284;
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51283 =
                          ((t51282) + (((int)(1))));
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
i51284 = t51283;
                    }
                    
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
return true;
                }
                
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public void
                                                                                                                                            search(
                                                                                                                                            final x10.lang.IntRange R){
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.lang.IntRange i51170domain51173 =
                      ((x10.lang.IntRange)(R));
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int i51170min51171 =
                      i51170domain51173.
                        min;
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int i51170max51172 =
                      i51170domain51173.
                        max;
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
int i51296 =
                      i51170min51171;
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51297 =
                          i51296;
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final boolean t51298 =
                          ((t51297) <= (((int)(i51170max51172))));
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (!(t51298)) {
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
break;
                        }
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int k51293 =
                          i51296;
                        
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final boolean t51289 =
                          this.safe$O((int)(k51293));
                        
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (t51289) {
                            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final NQueens t51290 =
                              this.
                                out$;
                            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.Rail t51291 =
                              ((x10.core.Rail)(q));
                            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final NQueens.Board t51292 =
                              ((NQueens.Board)(new NQueens.Board((java.lang.System[]) null).NQueens$Board$$init$S(t51290,
                                                                                                                  ((x10.core.Rail)(t51291)),
                                                                                                                  ((int)(k51293)), (NQueens.Board.__1$1x10$lang$Int$2) null)));
                            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51292.search();
                        }
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51294 =
                          i51296;
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51295 =
                          ((t51294) + (((int)(1))));
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
i51296 = t51295;
                    }
                }
                
                
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public void
                                                                                                                                            search(
                                                                                                                                            ){
                    
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.Rail t51226 =
                      ((x10.core.Rail)(q));
                    
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51227 =
                      ((x10.core.Rail<x10.core.Int>)t51226).
                        size;
                    
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51229 =
                      ((int)(long)(((long)(t51227))));
                    
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final NQueens t51228 =
                      this.
                        out$;
                    
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51230 =
                      t51228.
                        N;
                    
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final boolean t51233 =
                      ((int) t51229) ==
                    ((int) t51230);
                    
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (t51233) {
                        
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final NQueens x51186 =
                          ((NQueens)(this.
                                       out$));
                        
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
;
                        
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51231 =
                          x51186.
                            nSolutions;
                        
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51232 =
                          ((t51231) + (((long)(1L))));
                        
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
x51186.nSolutions = t51232;
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
return;
                    }
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final NQueens t51234 =
                      this.
                        out$;
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51235 =
                      t51234.
                        N;
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51236 =
                      ((t51235) - (((int)(1))));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.lang.IntRange t51237 =
                      ((x10.lang.IntRange)(new x10.lang.IntRange((java.lang.System[]) null).x10$lang$IntRange$$init$S(((int)(0)), ((int)(t51236)))));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.search(((x10.lang.IntRange)(t51237)));
                }
                
                
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final public NQueens.Board
                                                                                                                                            NQueens$Board$$this$NQueens$Board(
                                                                                                                                            ){
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
return NQueens.Board.this;
                }
                
                
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final public NQueens
                                                                                                                                            NQueens$Board$$this$NQueens(
                                                                                                                                            ){
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final NQueens t51238 =
                      this.
                        out$;
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
return t51238;
                }
                
                
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final public void
                                                                                                                                            __fieldInitializers_NQueens_Board(
                                                                                                                                            ){
                    
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$83 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$83> $RTT = x10.rtt.StaticFunType.<$Closure$83> make(
                    $Closure$83.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.INT)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueens.Board.$Closure$83 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$83.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueens.Board.$Closure$83 $_obj = new NQueens.Board.$Closure$83((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$83(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return x10.core.Int.$box($apply$O(x10.core.Long.$unbox(a1)));
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply$O(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public int
                          $apply$O(
                          final long id$19113){
                            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
return 0;
                        }
                        
                        public $Closure$83() { {
                                                      
                                                  }}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$84 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$84> $RTT = x10.rtt.StaticFunType.<$Closure$84> make(
                    $Closure$84.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.INT)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueens.Board.$Closure$84 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$84.class + " calling"); } 
                        $_obj.n = $deserializer.readLong();
                        $_obj.old = $deserializer.readRef();
                        $_obj.newItem = $deserializer.readInt();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueens.Board.$Closure$84 $_obj = new NQueens.Board.$Closure$84((java.lang.System[]) null);
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
                    public $Closure$84(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return x10.core.Int.$box($apply$O(x10.core.Long.$unbox(a1)));
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply$O(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public int
                          $apply$O(
                          final long i){
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final boolean t51192 =
                              ((i) < (((long)(this.
                                                n))));
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
int t51193 =
                               0;
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (t51192) {
                                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51193 = ((int[])this.
                                                                                                                                                                             old.value)[(int)i];
                            } else {
                                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51193 = this.
                                                                                                                                                                     newItem;
                            }
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51194 =
                              t51193;
                            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
return t51194;
                        }
                        
                        public long n;
                        public x10.core.Rail<x10.core.Int> old;
                        public int newItem;
                        
                        public $Closure$84(final long n,
                                           final x10.core.Rail<x10.core.Int> old,
                                           final int newItem, __1$1x10$lang$Int$2 $dummy) { {
                                                                                                   this.n = n;
                                                                                                   this.old = ((x10.core.Rail)(old));
                                                                                                   this.newItem = newItem;
                                                                                               }}
                        // synthetic type for parameter mangling
                        public static final class __1$1x10$lang$Int$2 {}
                        
                    }
                    
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Int$2 {}
                
                }
                
            
            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
            private static final long serialVersionUID = 1L;
            public static void main(java.lang.String[] args)  {
            // start native runtime
            new $Main().start(args);
            }
            
            // called by native runtime inside main x10 thread
            public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
            // call the original app-main method
            NQueens.main(args);
            }
            }
            
            // the original app-main method
            public static void main(final x10.core.Rail<java.lang.String> args)  {
                
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51239 =
                  ((x10.core.Rail<java.lang.String>)args).
                    size;
                
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final boolean t51241 =
                  ((t51239) > (((long)(0L))));
                
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
int t51242 =
                   0;
                
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (t51241) {
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51240 =
                      ((java.lang.String[])args.value)[(int)0L];
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51242 = java.lang.Integer.parseInt(t51240);
                } else {
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51242 = 8;
                }
                
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int n =
                  t51242;
                
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51243 =
                  (("N=") + ((x10.core.Int.$box(n))));
                
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
NQueens.println(((java.lang.String)(t51243)));
                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final NQueens nq =
                  ((NQueens)(new NQueens((java.lang.System[]) null).NQueens$$init$S(((int)(n)))));
                
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51244 =
                  x10.lang.System.nanoTime$O();
                
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
long start =
                  (-(t51244));
                
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
nq.start();
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51248 =
                  nq.
                    nSolutions;
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.core.Rail t51246 =
                  ((x10.core.Rail)(NQueens.get$expectedSolutions()));
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51245 =
                  nq.
                    N;
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51247 =
                  ((long)(((int)(t51245))));
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51249 =
                  ((long[])t51246.value)[(int)t51247];
                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final boolean result =
                  ((long) t51248) ==
                ((long) t51249);
                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51250 =
                  start;
                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51251 =
                  x10.lang.System.nanoTime$O();
                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51252 =
                  ((t51250) + (((long)(t51251))));
                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
start = t51252;
                
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51253 =
                  start;
                
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51254 =
                  ((t51253) / (((long)(1000000L))));
                
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
start = t51254;
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final int t51255 =
                  nq.
                    N;
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51256 =
                  (("NQueens ") + ((x10.core.Int.$box(t51255))));
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51257 =
                  ((t51256) + (" has "));
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51258 =
                  nq.
                    nSolutions;
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51259 =
                  ((t51257) + ((x10.core.Long.$box(t51258))));
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51261 =
                  ((t51259) + (" solutions"));
                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
java.lang.String t51260 =
                   null;
                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
if (result) {
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51260 = " (ok)";
                } else {
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51260 = " (wrong)";
                }
                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51262 =
                  t51260;
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51263 =
                  ((t51261) + (t51262));
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51264 =
                  ((t51263) + (" (t="));
                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final long t51265 =
                  start;
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51266 =
                  ((t51264) + ((x10.core.Long.$box(t51265))));
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final java.lang.String t51267 =
                  ((t51266) + ("ms)."));
                
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
NQueens.println(((java.lang.String)(t51267)));
            }
            
            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
public static void
                                                                                                                                        println(
                                                                                                                                        final java.lang.String s){
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final x10.io.Printer t51268 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
t51268.println(((java.lang.Object)(s)));
            }
            
            
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final public NQueens
                                                                                                                                       NQueens$$this$NQueens(
                                                                                                                                       ){
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
return NQueens.this;
            }
            
            
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
final public void
                                                                                                                                       __fieldInitializers_NQueens(
                                                                                                                                       ){
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueens.x10"
this.nSolutions = 0L;
            }
            
            final private static x10.core.concurrent.AtomicInteger initStatus$expectedSolutions = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
            private static x10.lang.ExceptionInInitializer exception$expectedSolutions;
            
            public static x10.core.Rail
              get$expectedSolutions(
              ){
                if (((int) NQueens.initStatus$expectedSolutions.get()) ==
                    ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                    return NQueens.expectedSolutions;
                }
                if (((int) NQueens.initStatus$expectedSolutions.get()) ==
                    ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                    if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                          ((boolean) true)) {
                        x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueens.expectedSolutions")));
                    }
                    throw NQueens.exception$expectedSolutions;
                }
                if (NQueens.initStatus$expectedSolutions.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                       (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                    try {{
                        NQueens.expectedSolutions = ((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.core.Long> makeRailFromJavaArray(x10.rtt.Types.LONG, new long[] {0L, 1L, 0L, 0L, 2L, 10L, 4L, 40L, 92L, 352L, 724L, 2680L, 14200L, 73712L, 365596L, 2279184L, 14772512L})));
                    }}catch (java.lang.Throwable exc$51299) {
                        NQueens.exception$expectedSolutions = new x10.lang.ExceptionInInitializer(exc$51299);
                        NQueens.initStatus$expectedSolutions.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                        x10.runtime.impl.java.InitDispatcher.lockInitialized();
                        x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                        throw NQueens.exception$expectedSolutions;
                    }
                    if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                          ((boolean) true)) {
                        x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: NQueens.expectedSolutions")));
                    }
                    NQueens.initStatus$expectedSolutions.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                } else {
                    if (NQueens.initStatus$expectedSolutions.get() <
                        x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                        x10.runtime.impl.java.InitDispatcher.lockInitialized();
                        while (NQueens.initStatus$expectedSolutions.get() <
                               x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                            x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                        }
                        x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                        if (((int) NQueens.initStatus$expectedSolutions.get()) ==
                            ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                            if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                  ((boolean) true)) {
                                x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueens.expectedSolutions")));
                            }
                            throw NQueens.exception$expectedSolutions;
                        }
                    }
                }
                return NQueens.expectedSolutions;
            }
        
        }
        
        