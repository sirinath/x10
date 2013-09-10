
@x10.runtime.impl.java.X10Generated public class SpanningTree extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<SpanningTree> $RTT = x10.rtt.NamedType.<SpanningTree> make(
    "SpanningTree", SpanningTree.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(SpanningTree $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + SpanningTree.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        SpanningTree $_obj = new SpanningTree((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public SpanningTree(final java.lang.System[] $dummy) { 
    }
    
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
@x10.runtime.impl.java.X10Generated public static class V extends x10.core.Ref implements x10.serialization.X10JavaSerializable
                                                                                                                                      {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<V> $RTT = x10.rtt.NamedType.<V> make(
            "SpanningTree.V", V.class
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(SpanningTree.V $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + V.class + " calling"); } 
                $_obj.edges = $deserializer.readRef();
                $_obj.out = $deserializer.readRef();
                $_obj.id = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                SpanningTree.V $_obj = new SpanningTree.V((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (edges instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.edges);
                } else {
                $serializer.write(this.edges);
                }
                if (out instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out);
                } else {
                $serializer.write(this.out);
                }
                $serializer.write(this.id);
                
            }
            
            // constructor just for allocation
            public V(final java.lang.System[] $dummy) { 
            }
            
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public int id;
                
                
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public x10.core.Rail<SpanningTree.V> edges;
                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public SpanningTree.V out;
                
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
// creation method for java code (1-phase java constructor)
                public V(final int id){this((java.lang.System[]) null);
                                           SpanningTree$V$$init$S(id);}
                
                // constructor for non-virtual call
                final public SpanningTree.V SpanningTree$V$$init$S(final int id) { {
                                                                                          
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
;
                                                                                          
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.id = id;
                                                                                          
                                                                                          
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.__fieldInitializers_SpanningTree_V();
                                                                                      }
                                                                                      return this;
                                                                                      }
                
                
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public void
                                                                                                                                                 set__0$1SpanningTree$V$2(
                                                                                                                                                 final x10.core.Rail e){
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.edges = ((x10.core.Rail)(e));
                }
                
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public void
                                                                                                                                                 mark(
                                                                                                                                                 ){
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.out = ((SpanningTree.V)(this));
                    {
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
x10.lang.Runtime.ensureNotInAtomic();
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.lang.FinishState x10$__var3 =
                          x10.lang.Runtime.startFinish();
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
try {{
                            {
                                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.visit();
                            }
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
throw new java.lang.RuntimeException();
                        }finally {{
                             
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var3)));
                         }}
                        }
                    }
                
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public boolean
                                                                                                                                                 first$O(
                                                                                                                                                 final SpanningTree.V v){
                    
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
SpanningTree.V prev =
                       null;
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
try {{
                        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
x10.lang.Runtime.enterAtomic();
                        {
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43581 =
                              ((SpanningTree.V)(out));
                            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
prev = t43581;
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43582 =
                              ((SpanningTree.V)(out));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
boolean t43586 =
                              ((t43582) == (null));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (!(t43586)) {
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43583 =
                                  ((SpanningTree.V)(out));
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43584 =
                                  t43583.
                                    id;
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43585 =
                                  v.
                                    id;
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43586 = ((t43584) > (((int)(t43585))));
                            }
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43587 =
                              t43586;
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
SpanningTree.V t43588 =
                               null;
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (t43587) {
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43588 = ((SpanningTree.V)(v));
                            } else {
                                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43588 = ((SpanningTree.V)(out));
                            }
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43589 =
                              ((SpanningTree.V)(t43588));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.out = ((SpanningTree.V)(t43589));
                        }
                    }}finally {{
                          
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
x10.lang.Runtime.exitAtomic();
                      }}
                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43590 =
                      prev;
                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43591 =
                      ((t43590) == (null));
                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
return t43591;
                    }
                
                
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public void
                                                                                                                                                 visit(
                                                                                                                                                 ){
                    
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.Rail rail43495 =
                      ((x10.core.Rail)(edges));
                    
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long size43496 =
                      ((x10.core.Rail<SpanningTree.V>)rail43495).
                        size;
                    
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
long idx43681 =
                      0L;
                    {
                        
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V[] rail43495$value43740 =
                          ((SpanningTree.V[])rail43495.value);
                        
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
for (;
                                                                                                                                                            true;
                                                                                                                                                            ) {
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43682 =
                              idx43681;
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43683 =
                              ((t43682) < (((long)(size43496))));
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (!(t43683)) {
                                
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
break;
                            }
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43677 =
                              idx43681;
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V v43678 =
                              ((SpanningTree.V)(((SpanningTree.V)rail43495$value43740[(int)t43677])));
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43676 =
                              v43678.first$O(((SpanningTree.V)(this)));
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (t43676) {
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new SpanningTree.V.$Closure$8(v43678))));
                            }
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43679 =
                              idx43681;
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43680 =
                              ((t43679) + (((long)(1L))));
                            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
idx43681 = t43680;
                        }
                    }
                }
                
                
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public java.lang.String
                                                                                                                                                 edgeString(
                                                                                                                                                 ){
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.Rail t43599 =
                      ((x10.core.Rail)(edges));
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43600 =
                      ((t43599) == (null));
                    
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (t43600) {
                        
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
return "";
                    }
                    
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
java.lang.String r =
                      "";
                    
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.Rail rail43695 =
                      ((x10.core.Rail)(edges));
                    
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long size43696 =
                      ((x10.core.Rail<SpanningTree.V>)rail43695).
                        size;
                    
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
long idx43692 =
                      0L;
                    {
                        
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V[] rail43695$value43741 =
                          ((SpanningTree.V[])rail43695.value);
                        
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
for (;
                                                                                                                                                            true;
                                                                                                                                                            ) {
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43693 =
                              idx43692;
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43694 =
                              ((t43693) < (((long)(size43696))));
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (!(t43694)) {
                                
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
break;
                            }
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43688 =
                              idx43692;
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V v43689 =
                              ((SpanningTree.V)(((SpanningTree.V)rail43695$value43741[(int)t43688])));
                            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43684 =
                              r;
                            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43685 =
                              v43689.
                                id;
                            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43686 =
                              ((" ") + ((x10.core.Int.$box(t43685))));
                            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43687 =
                              ((t43684) + (t43686));
                            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
r = t43687;
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43690 =
                              idx43692;
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43691 =
                              ((t43690) + (((long)(1L))));
                            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
idx43692 = t43691;
                        }
                    }
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43611 =
                      r;
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
return t43611;
                }
                
                
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public java.lang.String
                                                                                                                                                 toString(
                                                                                                                                                 ){
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43612 =
                      id;
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43613 =
                      (("") + ((x10.core.Int.$box(t43612))));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43614 =
                      ((t43613) + (" ("));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43615 =
                      this.edgeString();
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43616 =
                      ((t43614) + (t43615));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43622 =
                      ((t43616) + (") > "));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43617 =
                      ((SpanningTree.V)(out));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43620 =
                      ((t43617) == (null));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
java.lang.String t43621 =
                       null;
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (t43620) {
                        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43621 = "null";
                    } else {
                        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43618 =
                          ((SpanningTree.V)(out));
                        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43619 =
                          t43618.
                            id;
                        
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43621 = (("") + ((x10.core.Int.$box(t43619))));
                    }
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43623 =
                      t43621;
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43624 =
                      ((t43622) + (t43623));
                    
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
return t43624;
                }
                
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final public SpanningTree.V
                                                                                                                                                SpanningTree$V$$this$SpanningTree$V(
                                                                                                                                                ){
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
return SpanningTree.V.this;
                }
                
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final public void
                                                                                                                                                __fieldInitializers_SpanningTree_V(
                                                                                                                                                ){
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.edges = null;
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.out = null;
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$8 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$8> $RTT = x10.rtt.StaticVoidFunType.<$Closure$8> make(
                    $Closure$8.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(SpanningTree.V.$Closure$8 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$8.class + " calling"); } 
                        $_obj.v43678 = $deserializer.readRef();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        SpanningTree.V.$Closure$8 $_obj = new SpanningTree.V.$Closure$8((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        if (v43678 instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.v43678);
                        } else {
                        $serializer.write(this.v43678);
                        }
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$8(final java.lang.System[] $dummy) { 
                    }
                    
                        
                        public void
                          $apply(
                          ){
                            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
try {{
                                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.
                                                                                                                                                                 v43678.visit();
                            }}catch (java.lang.Error __lowerer__var__0__) {
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
throw __lowerer__var__0__;
                            }catch (java.lang.Throwable __lowerer__var__1__) {
                                
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                            }
                        }
                        
                        public SpanningTree.V v43678;
                        
                        public $Closure$8(final SpanningTree.V v43678) { {
                                                                                this.v43678 = ((SpanningTree.V)(v43678));
                                                                            }}
                        
                    }
                    
                
                }
                
                
                
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
                private static final long serialVersionUID = 1L;
                public static void main(java.lang.String[] args)  {
                // start native runtime
                new $Main().start(args);
                }
                
                // called by native runtime inside main x10 thread
                public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
                // call the original app-main method
                SpanningTree.main(args);
                }
                }
                
                // the original app-main method
                public static void main(final x10.core.Rail<java.lang.String> args)  {
                    
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43625 =
                      ((java.lang.String[])args.value)[(int)0L];
                    
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int N =
                      java.lang.Integer.parseInt(t43625);
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.io.Printer t43626 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43627 =
                      (("N=") + ((x10.core.Int.$box(N))));
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43626.println(((java.lang.Object)(t43627)));
                    
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43630 =
                      ((long)(((int)(N))));
                    
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.fun.Fun_0_1 t43631 =
                      ((x10.core.fun.Fun_0_1)(new SpanningTree.$Closure$9()));
                    
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.Rail vertices =
                      ((x10.core.Rail)(new x10.core.Rail<SpanningTree.V>(SpanningTree.V.$RTT, t43630,
                                                                         ((x10.core.fun.Fun_0_1)(t43631)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                    
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.util.Random r =
                      ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S()));
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.Rail rail43732 =
                      ((x10.core.Rail)(vertices));
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long size43733 =
                      ((x10.core.Rail<SpanningTree.V>)rail43732).
                        size;
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
long idx43720 =
                      0L;
                    {
                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V[] rail43732$value43742 =
                          ((SpanningTree.V[])rail43732.value);
                        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
for (;
                                                                                                                                                            true;
                                                                                                                                                            ) {
                            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43721 =
                              idx43720;
                            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43722 =
                              ((t43721) < (((long)(size43733))));
                            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (!(t43722)) {
                                
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
break;
                            }
                            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43716 =
                              idx43720;
                            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V v43717 =
                              ((SpanningTree.V)(((SpanningTree.V)rail43732$value43742[(int)t43716])));
                            
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43697 =
                              r.nextInt$O((int)(N));
                            
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43698 =
                              java.lang.Math.abs(((int)(t43697)));
                            
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43699 =
                              ((t43698) + (((int)(1))));
                            
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int N43700 =
                              java.lang.Math.min(((int)(t43699)),((int)(4)));
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43701 =
                              ((long)(((int)(N43700))));
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.fun.Fun_0_1 t43702 =
                              ((x10.core.fun.Fun_0_1)(new SpanningTree.$Closure$10(v43717,
                                                                                   N,
                                                                                   r,
                                                                                   vertices, (SpanningTree.$Closure$10.__3$1SpanningTree$V$2) null)));
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.Rail t43715 =
                              ((x10.core.Rail)(new x10.core.Rail<SpanningTree.V>(SpanningTree.V.$RTT, t43701,
                                                                                 ((x10.core.fun.Fun_0_1)(t43702)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
v43717.set__0$1SpanningTree$V$2(((x10.core.Rail)(t43715)));
                            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43718 =
                              idx43720;
                            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43719 =
                              ((t43718) + (((long)(1L))));
                            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
idx43720 = t43719;
                        }
                    }
                    
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.io.Printer t43655 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43655.println(((java.lang.Object)("Starting mark.")));
                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long time =
                      x10.lang.System.nanoTime$O();
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43656 =
                      ((SpanningTree.V[])vertices.value)[(int)0L];
                    
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43656.mark();
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.io.Printer t43666 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43657 =
                      x10.lang.System.nanoTime$O();
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43658 =
                      ((t43657) - (((long)(time))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final double t43659 =
                      ((double)(long)(((long)(t43658))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final double t43662 =
                      ((t43659) * (((double)(1.0))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43660 =
                      1000000L;
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43661 =
                      1000000000L;
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final double t43663 =
                      ((double)(long)(((long)(t43661))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final double t43664 =
                      ((t43662) / (((double)(t43663))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43665 =
                      (("Time: ") + ((x10.core.Double.$box(t43664))));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43667 =
                      ((t43665) + (" s"));
                    
//#line 55 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43666.println(((java.lang.Object)(t43667)));
                    
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.core.Rail rail43734 =
                      ((x10.core.Rail)(vertices));
                    
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long size43735 =
                      ((x10.core.Rail<SpanningTree.V>)rail43734).
                        size;
                    
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
long idx43729 =
                      0L;
                    {
                        
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V[] rail43734$value43743 =
                          ((SpanningTree.V[])rail43734.value);
                        
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
for (;
                                                                                                                                                            true;
                                                                                                                                                            ) {
                            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43730 =
                              idx43729;
                            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43731 =
                              ((t43730) < (((long)(size43735))));
                            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (!(t43731)) {
                                
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
break;
                            }
                            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43725 =
                              idx43729;
                            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V v43726 =
                              ((SpanningTree.V)(((SpanningTree.V)rail43734$value43743[(int)t43725])));
                            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final x10.io.Printer t43723 =
                              ((x10.io.Printer)(x10.io.Console.get$OUT()));
                            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final java.lang.String t43724 =
                              (("") + (v43726));
                            
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43723.println(((java.lang.Object)(t43724)));
                            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43727 =
                              idx43729;
                            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43728 =
                              ((t43727) + (((long)(1L))));
                            
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
idx43729 = t43728;
                        }
                    }
                }
                
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final public SpanningTree
                                                                                                                                                SpanningTree$$this$SpanningTree(
                                                                                                                                                ){
                    
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
return SpanningTree.this;
                }
                
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
// creation method for java code (1-phase java constructor)
                public SpanningTree(){this((java.lang.System[]) null);
                                          SpanningTree$$init$S();}
                
                // constructor for non-virtual call
                final public SpanningTree SpanningTree$$init$S() { {
                                                                          
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
;
                                                                          
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"

                                                                          
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
this.__fieldInitializers_SpanningTree();
                                                                      }
                                                                      return this;
                                                                      }
                
                
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final public void
                                                                                                                                                __fieldInitializers_SpanningTree(
                                                                                                                                                ){
                    
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$9 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$9> $RTT = x10.rtt.StaticFunType.<$Closure$9> make(
                    $Closure$9.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, SpanningTree.V.$RTT)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(SpanningTree.$Closure$9 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$9.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        SpanningTree.$Closure$9 $_obj = new SpanningTree.$Closure$9((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$9(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public SpanningTree.V
                          $apply(
                          final long i){
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43628 =
                              ((int)(long)(((long)(i))));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43629 =
                              ((SpanningTree.V)(new SpanningTree.V((java.lang.System[]) null).SpanningTree$V$$init$S(t43628)));
                            
//#line 44 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
return t43629;
                        }
                        
                        public $Closure$9() { {
                                                     
                                                 }}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$10 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$10> $RTT = x10.rtt.StaticFunType.<$Closure$10> make(
                    $Closure$10.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, SpanningTree.V.$RTT)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(SpanningTree.$Closure$10 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$10.class + " calling"); } 
                        $_obj.v43717 = $deserializer.readRef();
                        $_obj.N = $deserializer.readInt();
                        $_obj.r = $deserializer.readRef();
                        $_obj.vertices = $deserializer.readRef();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        SpanningTree.$Closure$10 $_obj = new SpanningTree.$Closure$10((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        if (v43717 instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.v43717);
                        } else {
                        $serializer.write(this.v43717);
                        }
                        $serializer.write(this.N);
                        if (r instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.r);
                        } else {
                        $serializer.write(this.r);
                        }
                        if (vertices instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.vertices);
                        } else {
                        $serializer.write(this.vertices);
                        }
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$10(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public SpanningTree.V
                          $apply(
                          final long i43703){
                            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43704 =
                              ((long) i43703) ==
                            ((long) 0L);
                            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
int t43705 =
                               0;
                            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (t43704) {
                                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43706 =
                                  this.
                                    v43717.
                                    id;
                                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43707 =
                                  ((this.
                                      N) - (((int)(1))));
                                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final boolean t43708 =
                                  ((int) t43706) ==
                                ((int) t43707);
                                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
int t43709 =
                                   0;
                                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
if (t43708) {
                                    
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43709 = ((this.
                                                                                                                                                                                N) - (((int)(1))));
                                } else {
                                    
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43710 =
                                      this.
                                        v43717.
                                        id;
                                    
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43709 = ((t43710) + (((int)(1))));
                                }
                                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43705 = t43709;
                            } else {
                                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43711 =
                                  this.
                                    r.nextInt$O((int)(this.
                                                        N));
                                
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
t43705 = java.lang.Math.abs(((int)(t43711)));
                            }
                            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final int t43712 =
                              t43705;
                            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final long t43713 =
                              ((long)(((int)(t43712))));
                            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
final SpanningTree.V t43714 =
                              ((SpanningTree.V[])this.
                                                   vertices.value)[(int)t43713];
                            
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/SpanningTree.x10"
return t43714;
                        }
                        
                        public SpanningTree.V v43717;
                        public int N;
                        public x10.util.Random r;
                        public x10.core.Rail<SpanningTree.V> vertices;
                        
                        public $Closure$10(final SpanningTree.V v43717,
                                           final int N,
                                           final x10.util.Random r,
                                           final x10.core.Rail<SpanningTree.V> vertices, __3$1SpanningTree$V$2 $dummy) { {
                                                                                                                                this.v43717 = ((SpanningTree.V)(v43717));
                                                                                                                                this.N = N;
                                                                                                                                this.r = ((x10.util.Random)(r));
                                                                                                                                this.vertices = ((x10.core.Rail)(vertices));
                                                                                                                            }}
                        // synthetic type for parameter mangling
                        public static final class __3$1SpanningTree$V$2 {}
                        
                    }
                    
                
                }
                
                