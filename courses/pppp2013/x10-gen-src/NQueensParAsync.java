@x10.runtime.impl.java.X10Generated public class NQueensParAsync extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<NQueensParAsync> $RTT = x10.rtt.NamedType.<NQueensParAsync> make(
    "NQueensParAsync", NQueensParAsync.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensParAsync $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + NQueensParAsync.class + " calling"); } 
        $_obj.N = $deserializer.readLong();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        NQueensParAsync $_obj = new NQueensParAsync((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write(this.N);
        
    }
    
    // constructor just for allocation
    public NQueensParAsync(final java.lang.System[] $dummy) { 
    }
    
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
private static x10.core.Rail<x10.core.Long> expectedSolutions;
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public long N;
        
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
// creation method for java code (1-phase java constructor)
        public NQueensParAsync(final long N){this((java.lang.System[]) null);
                                                 NQueensParAsync$$init$S(N);}
        
        // constructor for non-virtual call
        final public NQueensParAsync NQueensParAsync$$init$S(final long N) { {
                                                                                    
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
;
                                                                                    
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"

                                                                                    
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.__fieldInitializers_NQueensParAsync();
                                                                                    
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.N = N;
                                                                                }
                                                                                return this;
                                                                                }
        
        
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public void
                                                                                                                                           start(
                                                                                                                                           ){
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final NQueensParAsync.Board t47661 =
              ((NQueensParAsync.Board)(new NQueensParAsync.Board((java.lang.System[]) null).NQueensParAsync$Board$$init$S(this)));
            
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47661.search();
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
@x10.runtime.impl.java.X10Generated public static class Board extends x10.core.Ref implements x10.serialization.X10JavaSerializable
                                                                                                                                          {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<Board> $RTT = x10.rtt.NamedType.<Board> make(
            "NQueensParAsync.Board", Board.class
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensParAsync.Board $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Board.class + " calling"); } 
                $_obj.q = $deserializer.readRef();
                $_obj.out$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                NQueensParAsync.Board $_obj = new NQueensParAsync.Board((java.lang.System[]) null);
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
            
                
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public NQueensParAsync out$;
                
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public x10.core.Rail<x10.core.Long> q;
                
                
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
// creation method for java code (1-phase java constructor)
                public Board(final NQueensParAsync out$){this((java.lang.System[]) null);
                                                             NQueensParAsync$Board$$init$S(out$);}
                
                // constructor for non-virtual call
                final public NQueensParAsync.Board NQueensParAsync$Board$$init$S(final NQueensParAsync out$) { {
                                                                                                                      
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.out$ = out$;
                                                                                                                      
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
;
                                                                                                                      
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"

                                                                                                                      
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.__fieldInitializers_NQueensParAsync_Board();
                                                                                                                      
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.fun.Fun_0_1 t47662 =
                                                                                                                        ((x10.core.fun.Fun_0_1)(new NQueensParAsync.Board.$Closure$56()));
                                                                                                                      
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.Rail t47663 =
                                                                                                                        ((x10.core.Rail)(new x10.core.Rail<x10.core.Long>(x10.rtt.Types.LONG, ((long)(0L)),
                                                                                                                                                                          ((x10.core.fun.Fun_0_1)(t47662)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                                      
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.q = ((x10.core.Rail)(t47663));
                                                                                                                  }
                                                                                                                  return this;
                                                                                                                  }
                
                
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
// creation method for java code (1-phase java constructor)
                public Board(final NQueensParAsync out$,
                             final x10.core.Rail<x10.core.Long> old,
                             final long newItem, __1$1x10$lang$Long$2 $dummy){this((java.lang.System[]) null);
                                                                                  NQueensParAsync$Board$$init$S(out$,old,newItem, (NQueensParAsync.Board.__1$1x10$lang$Long$2) null);}
                
                // constructor for non-virtual call
                final public NQueensParAsync.Board NQueensParAsync$Board$$init$S(final NQueensParAsync out$,
                                                                                 final x10.core.Rail<x10.core.Long> old,
                                                                                 final long newItem, __1$1x10$lang$Long$2 $dummy) { {
                                                                                                                                           
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.out$ = out$;
                                                                                                                                           
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
;
                                                                                                                                           
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"

                                                                                                                                           
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.__fieldInitializers_NQueensParAsync_Board();
                                                                                                                                           
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long n =
                                                                                                                                             ((x10.core.Rail<x10.core.Long>)old).
                                                                                                                                               size;
                                                                                                                                           
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47667 =
                                                                                                                                             ((n) + (((long)(1L))));
                                                                                                                                           
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.fun.Fun_0_1 t47668 =
                                                                                                                                             ((x10.core.fun.Fun_0_1)(new NQueensParAsync.Board.$Closure$57(n,
                                                                                                                                                                                                           old,
                                                                                                                                                                                                           newItem, (NQueensParAsync.Board.$Closure$57.__1$1x10$lang$Long$2) null)));
                                                                                                                                           
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.Rail t47669 =
                                                                                                                                             ((x10.core.Rail)(new x10.core.Rail<x10.core.Long>(x10.rtt.Types.LONG, t47667,
                                                                                                                                                                                               ((x10.core.fun.Fun_0_1)(t47668)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                                                                                                                                           
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.q = ((x10.core.Rail)(t47669));
                                                                                                                                       }
                                                                                                                                       return this;
                                                                                                                                       }
                
                
                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public boolean
                                                                                                                                                    safe$O(
                                                                                                                                                    final long j){
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.Rail t47670 =
                      ((x10.core.Rail)(q));
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long n =
                      ((x10.core.Rail<x10.core.Long>)t47670).
                        size;
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long i47629min47750 =
                      0L;
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long i47629max47751 =
                      ((n) - (((long)(1L))));
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
long i47747 =
                      i47629min47750;
                    
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
for (;
                                                                                                                                                           true;
                                                                                                                                                           ) {
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47748 =
                          i47747;
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final boolean t47749 =
                          ((t47748) <= (((long)(i47629max47751))));
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (!(t47749)) {
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
break;
                        }
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long k47744 =
                          i47747;
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.Rail t47734 =
                          ((x10.core.Rail)(q));
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47735 =
                          ((long[])t47734.value)[(int)k47744];
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
boolean t47736 =
                          ((long) j) ==
                        ((long) t47735);
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (!(t47736)) {
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47737 =
                              ((n) - (((long)(k47744))));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47738 =
                              java.lang.Math.abs(((long)(t47737)));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.Rail t47739 =
                              ((x10.core.Rail)(q));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47740 =
                              ((long[])t47739.value)[(int)k47744];
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47741 =
                              ((j) - (((long)(t47740))));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47742 =
                              java.lang.Math.abs(((long)(t47741)));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47736 = ((long) t47738) ==
                            ((long) t47742);
                        }
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final boolean t47743 =
                          t47736;
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (t47743) {
                            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return false;
                        }
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47745 =
                          i47747;
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47746 =
                          ((t47745) + (((long)(1L))));
                        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
i47747 = t47746;
                    }
                    
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return true;
                }
                
                
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public void
                                                                                                                                                    search(
                                                                                                                                                    final x10.lang.LongRange range){
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.lang.LongRange i47645domain47648 =
                      ((x10.lang.LongRange)(range));
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long i47645min47646 =
                      i47645domain47648.
                        min;
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long i47645max47647 =
                      i47645domain47648.
                        max;
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
long i47759 =
                      i47645min47646;
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
for (;
                                                                                                                                                           true;
                                                                                                                                                           ) {
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47760 =
                          i47759;
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final boolean t47761 =
                          ((t47760) <= (((long)(i47645max47647))));
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (!(t47761)) {
                            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
break;
                        }
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long k47756 =
                          i47759;
                        
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final boolean t47752 =
                          this.safe$O((long)(k47756));
                        
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (t47752) {
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new NQueensParAsync.Board.$Closure$58(this,
                                                                                                                                                                                                                                                          q,
                                                                                                                                                                                                                                                          k47756, (NQueensParAsync.Board.$Closure$58.__1$1x10$lang$Long$2) null))));
                        }
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47757 =
                          i47759;
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47758 =
                          ((t47757) + (((long)(1L))));
                        
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
i47759 = t47758;
                    }
                }
                
                
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public void
                                                                                                                                                    search(
                                                                                                                                                    ){
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.Rail t47695 =
                      ((x10.core.Rail)(q));
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47697 =
                      ((x10.core.Rail<x10.core.Long>)t47695).
                        size;
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final NQueensParAsync t47696 =
                      this.
                        out$;
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47698 =
                      t47696.
                        N;
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final boolean t47703 =
                      ((long) t47697) ==
                    ((long) t47698);
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (t47703) {
                        
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
x10.lang.Runtime.<x10.core.Long> makeOffer__0x10$lang$Runtime$$T(x10.rtt.Types.LONG, x10.core.Long.$box(1L));
                    } else {
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final NQueensParAsync t47699 =
                          this.
                            out$;
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47700 =
                          t47699.
                            N;
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47701 =
                          ((t47700) - (((long)(1L))));
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.lang.LongRange t47702 =
                          ((x10.lang.LongRange)(new x10.lang.LongRange((java.lang.System[]) null).x10$lang$LongRange$$init$S(((long)(0L)), ((long)(t47701)))));
                        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
this.search(((x10.lang.LongRange)(t47702)));
                    }
                }
                
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final public NQueensParAsync.Board
                                                                                                                                                    NQueensParAsync$Board$$this$NQueensParAsync$Board(
                                                                                                                                                    ){
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return NQueensParAsync.Board.this;
                }
                
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final public NQueensParAsync
                                                                                                                                                    NQueensParAsync$Board$$this$NQueensParAsync(
                                                                                                                                                    ){
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final NQueensParAsync t47704 =
                      this.
                        out$;
                    
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return t47704;
                }
                
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final public void
                                                                                                                                                    __fieldInitializers_NQueensParAsync_Board(
                                                                                                                                                    ){
                    
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$56 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$56> $RTT = x10.rtt.StaticFunType.<$Closure$56> make(
                    $Closure$56.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.LONG)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensParAsync.Board.$Closure$56 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$56.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensParAsync.Board.$Closure$56 $_obj = new NQueensParAsync.Board.$Closure$56((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$56(final java.lang.System[] $dummy) { 
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
                          final long id$19037){
                            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return 0L;
                        }
                        
                        public $Closure$56() { {
                                                      
                                                  }}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$57 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$57> $RTT = x10.rtt.StaticFunType.<$Closure$57> make(
                    $Closure$57.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.LONG)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensParAsync.Board.$Closure$57 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$57.class + " calling"); } 
                        $_obj.n = $deserializer.readLong();
                        $_obj.old = $deserializer.readRef();
                        $_obj.newItem = $deserializer.readLong();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensParAsync.Board.$Closure$57 $_obj = new NQueensParAsync.Board.$Closure$57((java.lang.System[]) null);
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
                    public $Closure$57(final java.lang.System[] $dummy) { 
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
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final boolean t47664 =
                              ((i) < (((long)(this.
                                                n))));
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
long t47665 =
                               0;
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (t47664) {
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47665 = ((long[])this.
                                                                                                                                                                                      old.value)[(int)i];
                            } else {
                                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47665 = this.
                                                                                                                                                                             newItem;
                            }
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47666 =
                              t47665;
                            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return t47666;
                        }
                        
                        public long n;
                        public x10.core.Rail<x10.core.Long> old;
                        public long newItem;
                        
                        public $Closure$57(final long n,
                                           final x10.core.Rail<x10.core.Long> old,
                                           final long newItem, __1$1x10$lang$Long$2 $dummy) { {
                                                                                                     this.n = n;
                                                                                                     this.old = ((x10.core.Rail)(old));
                                                                                                     this.newItem = newItem;
                                                                                                 }}
                        // synthetic type for parameter mangling
                        public static final class __1$1x10$lang$Long$2 {}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$58 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$58> $RTT = x10.rtt.StaticVoidFunType.<$Closure$58> make(
                    $Closure$58.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensParAsync.Board.$Closure$58 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$58.class + " calling"); } 
                        $_obj.out$$ = $deserializer.readRef();
                        $_obj.q = $deserializer.readRef();
                        $_obj.k47756 = $deserializer.readLong();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensParAsync.Board.$Closure$58 $_obj = new NQueensParAsync.Board.$Closure$58((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                        } else {
                        $serializer.write(this.out$$);
                        }
                        if (q instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.q);
                        } else {
                        $serializer.write(this.q);
                        }
                        $serializer.write(this.k47756);
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$58(final java.lang.System[] $dummy) { 
                    }
                    
                        
                        public void
                          $apply(
                          ){
                            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
try {{
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final NQueensParAsync t47753 =
                                  this.
                                    out$$.
                                    out$;
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.Rail t47754 =
                                  ((x10.core.Rail)(this.
                                                     q));
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final NQueensParAsync.Board t47755 =
                                  ((NQueensParAsync.Board)(new NQueensParAsync.Board((java.lang.System[]) null).NQueensParAsync$Board$$init$S(t47753,
                                                                                                                                              ((x10.core.Rail)(t47754)),
                                                                                                                                              this.
                                                                                                                                                k47756, (NQueensParAsync.Board.__1$1x10$lang$Long$2) null)));
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47755.search();
                            }}catch (java.lang.Error __lowerer__var__0__) {
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
throw __lowerer__var__0__;
                            }catch (java.lang.Throwable __lowerer__var__1__) {
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                            }
                        }
                        
                        public NQueensParAsync.Board out$$;
                        public x10.core.Rail<x10.core.Long> q;
                        public long k47756;
                        
                        public $Closure$58(final NQueensParAsync.Board out$$,
                                           final x10.core.Rail<x10.core.Long> q,
                                           final long k47756, __1$1x10$lang$Long$2 $dummy) { {
                                                                                                    this.out$$ = out$$;
                                                                                                    this.q = ((x10.core.Rail)(q));
                                                                                                    this.k47756 = k47756;
                                                                                                }}
                        // synthetic type for parameter mangling
                        public static final class __1$1x10$lang$Long$2 {}
                        
                    }
                    
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Long$2 {}
                
                }
                
                
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
private static NQueensParAsync.Anonymous$1153 sum;
                
                
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
                private static final long serialVersionUID = 1L;
                public static void main(java.lang.String[] args)  {
                // start native runtime
                new $Main().start(args);
                }
                
                // called by native runtime inside main x10 thread
                public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
                // call the original app-main method
                NQueensParAsync.main(args);
                }
                }
                
                // the original app-main method
                public static void main(final x10.core.Rail<java.lang.String> args)  {
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47705 =
                      ((x10.core.Rail<java.lang.String>)args).
                        size;
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final boolean t47707 =
                      ((t47705) > (((long)(0L))));
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
long t47708 =
                       0;
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (t47707) {
                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47706 =
                          ((java.lang.String[])args.value)[(int)0L];
                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47708 = java.lang.Long.parseLong(t47706);
                    } else {
                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47708 = 12L;
                    }
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long n =
                      t47708;
                    
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.io.Printer t47709 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47710 =
                      (("N=") + ((x10.core.Long.$box(n))));
                    
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47709.println(((java.lang.Object)(t47710)));
                    
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final NQueensParAsync nq =
                      ((NQueensParAsync)(new NQueensParAsync((java.lang.System[]) null).NQueensParAsync$$init$S(((long)(n)))));
                    
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47711 =
                      x10.lang.System.nanoTime$O();
                    
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
long start =
                      (-(t47711));
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long count;
                    {
                        
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.lang.FinishState x10$__var15 =
                          ((x10.lang.FinishState)(x10.lang.Runtime.<x10.core.Long> startCollectingFinish__0$1x10$lang$Runtime$$T$2(x10.rtt.Types.LONG, ((x10.lang.Reducible)(NQueensParAsync.get$sum())))));
                        
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
try {{
                            {
                                
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
nq.start();
                            }
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
throw new java.lang.RuntimeException();
                        }finally {{
                             
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
count = x10.core.Long.$unbox(x10.lang.Runtime.<x10.core.Long> stopCollectingFinish$G(x10.rtt.Types.LONG, ((x10.lang.FinishState)(x10$__var15))));
                         }}
                        }
                    
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.core.Rail t47712 =
                      ((x10.core.Rail)(NQueensParAsync.get$expectedSolutions()));
                    
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47713 =
                      nq.
                        N;
                    
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47714 =
                      ((long[])t47712.value)[(int)t47713];
                    
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final boolean result =
                      ((long) count) ==
                    ((long) t47714);
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47715 =
                      start;
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47716 =
                      x10.lang.System.nanoTime$O();
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47717 =
                      ((t47715) + (((long)(t47716))));
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
start = t47717;
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47718 =
                      start;
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47719 =
                      ((t47718) / (((long)(1000000L))));
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
start = t47719;
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final x10.io.Printer t47731 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47720 =
                      nq.
                        N;
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47721 =
                      (("NQueensParAsync ") + ((x10.core.Long.$box(t47720))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47722 =
                      ((t47721) + (") has "));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47723 =
                      ((t47722) + ((x10.core.Long.$box(count))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47725 =
                      ((t47723) + (" solutions"));
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
java.lang.String t47724 =
                       null;
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
if (result) {
                        
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47724 = " (ok)";
                    } else {
                        
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47724 = " (wrong)";
                    }
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47726 =
                      t47724;
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47727 =
                      ((t47725) + (t47726));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47728 =
                      ((t47727) + (" (t="));
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47729 =
                      start;
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47730 =
                      ((t47728) + ((x10.core.Long.$box(t47729))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final java.lang.String t47732 =
                      ((t47730) + ("ms)."));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
t47731.println(((java.lang.Object)(t47732)));
                    }
                
                
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final public NQueensParAsync
                                                                                                                                                   NQueensParAsync$$this$NQueensParAsync(
                                                                                                                                                   ){
                    
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return NQueensParAsync.this;
                }
                
                
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final public void
                                                                                                                                                   __fieldInitializers_NQueensParAsync(
                                                                                                                                                   ){
                    
                }
                
                
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
@x10.runtime.impl.java.X10Generated final public static class Anonymous$1153 extends x10.core.Ref implements x10.lang.Reducible, x10.serialization.X10JavaSerializable
                                                                                                                                                  {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<Anonymous$1153> $RTT = x10.rtt.NamedType.<Anonymous$1153> make(
                    "NQueensParAsync.Anonymous$1153", Anonymous$1153.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.lang.Reducible.$RTT, x10.rtt.Types.LONG)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(NQueensParAsync.Anonymous$1153 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Anonymous$1153.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        NQueensParAsync.Anonymous$1153 $_obj = new NQueensParAsync.Anonymous$1153((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public Anonymous$1153(final java.lang.System[] $dummy) { 
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
                    
                        
                        
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public long
                                                                                                                                                            zero$O(
                                                                                                                                                            ){
                            
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return 0L;
                        }
                        
                        
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
public long
                                                                                                                                                            $apply$O(
                                                                                                                                                            final long i,
                                                                                                                                                            final long j){
                            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
final long t47733 =
                              ((i) + (((long)(j))));
                            
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
return t47733;
                        }
                        
                        
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/NQueensParAsync.x10"
// creation method for java code (1-phase java constructor)
                        public Anonymous$1153(){this((java.lang.System[]) null);
                                                    NQueensParAsync$Anonymous$1153$$init$S();}
                        
                        // constructor for non-virtual call
                        final public NQueensParAsync.Anonymous$1153 NQueensParAsync$Anonymous$1153$$init$S() { {
                                                                                                                      
                                                                                                                  }
                                                                                                                  return this;
                                                                                                                  }
                        
                    
                }
                
                final private static x10.core.concurrent.AtomicInteger initStatus$sum = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
                private static x10.lang.ExceptionInInitializer exception$sum;
                final private static x10.core.concurrent.AtomicInteger initStatus$expectedSolutions = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
                private static x10.lang.ExceptionInInitializer exception$expectedSolutions;
                
                public static x10.core.Rail
                  get$expectedSolutions(
                  ){
                    if (((int) NQueensParAsync.initStatus$expectedSolutions.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                        return NQueensParAsync.expectedSolutions;
                    }
                    if (((int) NQueensParAsync.initStatus$expectedSolutions.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensParAsync.expectedSolutions")));
                        }
                        throw NQueensParAsync.exception$expectedSolutions;
                    }
                    if (NQueensParAsync.initStatus$expectedSolutions.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                                   (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                        try {{
                            NQueensParAsync.expectedSolutions = ((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.core.Long> makeRailFromJavaArray(x10.rtt.Types.LONG, new long[] {0L, 1L, 0L, 0L, 2L, 10L, 4L, 40L, 92L, 352L, 724L, 2680L, 14200L, 73712L, 365596L, 2279184L, 14772512L})));
                        }}catch (java.lang.Throwable exc$47770) {
                            NQueensParAsync.exception$expectedSolutions = new x10.lang.ExceptionInInitializer(exc$47770);
                            NQueensParAsync.initStatus$expectedSolutions.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                            throw NQueensParAsync.exception$expectedSolutions;
                        }
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: NQueensParAsync.expectedSolutions")));
                        }
                        NQueensParAsync.initStatus$expectedSolutions.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                        x10.runtime.impl.java.InitDispatcher.lockInitialized();
                        x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    } else {
                        if (NQueensParAsync.initStatus$expectedSolutions.get() <
                            x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            while (NQueensParAsync.initStatus$expectedSolutions.get() <
                                   x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                                x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                            }
                            x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                            if (((int) NQueensParAsync.initStatus$expectedSolutions.get()) ==
                                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                      ((boolean) true)) {
                                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensParAsync.expectedSolutions")));
                                }
                                throw NQueensParAsync.exception$expectedSolutions;
                            }
                        }
                    }
                    return NQueensParAsync.expectedSolutions;
                }
                
                public static NQueensParAsync.Anonymous$1153
                  get$sum(
                  ){
                    if (((int) NQueensParAsync.initStatus$sum.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                        return NQueensParAsync.sum;
                    }
                    if (((int) NQueensParAsync.initStatus$sum.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensParAsync.sum")));
                        }
                        throw NQueensParAsync.exception$sum;
                    }
                    if (NQueensParAsync.initStatus$sum.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                     (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                        try {{
                            NQueensParAsync.sum = ((NQueensParAsync.Anonymous$1153)(new NQueensParAsync.Anonymous$1153((java.lang.System[]) null).NQueensParAsync$Anonymous$1153$$init$S()));
                        }}catch (java.lang.Throwable exc$47771) {
                            NQueensParAsync.exception$sum = new x10.lang.ExceptionInInitializer(exc$47771);
                            NQueensParAsync.initStatus$sum.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                            throw NQueensParAsync.exception$sum;
                        }
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: NQueensParAsync.sum")));
                        }
                        NQueensParAsync.initStatus$sum.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                        x10.runtime.impl.java.InitDispatcher.lockInitialized();
                        x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    } else {
                        if (NQueensParAsync.initStatus$sum.get() <
                            x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                            x10.runtime.impl.java.InitDispatcher.lockInitialized();
                            while (NQueensParAsync.initStatus$sum.get() <
                                   x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                                x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                            }
                            x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                            if (((int) NQueensParAsync.initStatus$sum.get()) ==
                                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                                      ((boolean) true)) {
                                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: NQueensParAsync.sum")));
                                }
                                throw NQueensParAsync.exception$sum;
                            }
                        }
                    }
                    return NQueensParAsync.sum;
                }
                
            }
            
            