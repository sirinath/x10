
@x10.runtime.impl.java.X10Generated public class BFS extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<BFS> $RTT = x10.rtt.NamedType.<BFS> make(
    "BFS", BFS.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(BFS $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + BFS.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        BFS $_obj = new BFS((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public BFS(final java.lang.System[] $dummy) { 
    }
    
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
@x10.runtime.impl.java.X10Generated public static class V extends x10.core.Ref implements x10.serialization.X10JavaSerializable
                                                                                                                             {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<V> $RTT = x10.rtt.NamedType.<V> make(
            "BFS.V", V.class
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(BFS.V $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + V.class + " calling"); } 
                $_obj.edges = $deserializer.readRef();
                $_obj.out = $deserializer.readInt();
                $_obj.id = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                BFS.V $_obj = new BFS.V((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (edges instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.edges);
                } else {
                $serializer.write(this.edges);
                }
                $serializer.write(this.out);
                $serializer.write(this.id);
                
            }
            
            // constructor just for allocation
            public V(final java.lang.System[] $dummy) { 
            }
            
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public int id;
                
                
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public x10.core.Rail<BFS.V> edges;
                
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public int out;
                
                
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
// creation method for java code (1-phase java constructor)
                public V(final int id){this((java.lang.System[]) null);
                                           BFS$V$$init$S(id);}
                
                // constructor for non-virtual call
                final public BFS.V BFS$V$$init$S(final int id) { {
                                                                        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
;
                                                                        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.id = id;
                                                                        
                                                                        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.__fieldInitializers_BFS_V();
                                                                    }
                                                                    return this;
                                                                    }
                
                
                
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public void
                                                                                                                                        set__0$1BFS$V$2(
                                                                                                                                        final x10.core.Rail e){
                    
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.edges = ((x10.core.Rail)(e));
                }
                
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public void
                                                                                                                                        mark(
                                                                                                                                        ){
                    
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.out = 0;
                    {
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
x10.lang.Runtime.ensureNotInAtomic();
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.lang.FinishState x10$__var22 =
                          x10.lang.Runtime.startFinish();
                        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
try {{
                            {
                                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new BFS.V.$Closure$75(this))));
                            }
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
throw new java.lang.RuntimeException();
                        }finally {{
                             
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var22)));
                         }}
                        }
                    }
                
                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public boolean
                                                                                                                                        first$O(
                                                                                                                                        final BFS.V v){
                    
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
BFS.V prev =
                       null;
                    
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
try {{
                        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
x10.lang.Runtime.enterAtomic();
                        {
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49751 =
                              out;
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49752 =
                              ((long)(((int)(t49751))));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49755 =
                              ((t49752) < (((long)(0L))));
                            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (t49755) {
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49753 =
                                  v.
                                    out;
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49754 =
                                  ((t49753) + (((int)(1))));
                                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.out = t49754;
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return true;
                            }
                        }
                    }}finally {{
                          
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
x10.lang.Runtime.exitAtomic();
                      }}
                    
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return false;
                    }
                
                
//#line 28 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public void
                                                                                                                                        visit(
                                                                                                                                        final x10.lang.Clock c){
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.Rail rail49664 =
                      ((x10.core.Rail)(edges));
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long size49665 =
                      ((x10.core.Rail<BFS.V>)rail49664).
                        size;
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
long idx49829 =
                      0L;
                    {
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V[] rail49664$value49891 =
                          ((BFS.V[])rail49664.value);
                        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49830 =
                              idx49829;
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49831 =
                              ((t49830) < (((long)(size49665))));
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (!(t49831)) {
                                
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
break;
                            }
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49825 =
                              idx49829;
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V v49826 =
                              ((BFS.V)(((BFS.V)rail49664$value49891[(int)t49825])));
                            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49824 =
                              v49826.first$O(((BFS.V)(this)));
                            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (t49824) {
                                
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
x10.lang.Runtime.runAsync__0$1x10$lang$Clock$2(((x10.core.Rail)(x10.runtime.impl.java.ArrayUtils.<x10.lang.Clock> makeRailFromJavaArray(x10.lang.Clock.$RTT, new x10.lang.Clock[] {c}))),
                                                                                                                                                                                                     ((x10.core.fun.VoidFun_0_0)(new BFS.V.$Closure$76(v49826,
                                                                                                                                                                                                                                                       c))));
                            }
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49827 =
                              idx49829;
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49828 =
                              ((t49827) + (((long)(1L))));
                            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
idx49829 = t49828;
                        }
                    }
                }
                
                
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public java.lang.String
                                                                                                                                        edgeString(
                                                                                                                                        ){
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.Rail t49763 =
                      ((x10.core.Rail)(edges));
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49764 =
                      ((t49763) == (null));
                    
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (t49764) {
                        
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return "";
                    }
                    
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
java.lang.String r =
                      "";
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.Rail rail49843 =
                      ((x10.core.Rail)(edges));
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long size49844 =
                      ((x10.core.Rail<BFS.V>)rail49843).
                        size;
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
long idx49840 =
                      0L;
                    {
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V[] rail49843$value49892 =
                          ((BFS.V[])rail49843.value);
                        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49841 =
                              idx49840;
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49842 =
                              ((t49841) < (((long)(size49844))));
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (!(t49842)) {
                                
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
break;
                            }
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49836 =
                              idx49840;
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V v49837 =
                              ((BFS.V)(((BFS.V)rail49843$value49892[(int)t49836])));
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49832 =
                              r;
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49833 =
                              v49837.
                                id;
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49834 =
                              ((" ") + ((x10.core.Int.$box(t49833))));
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49835 =
                              ((t49832) + (t49834));
                            
//#line 39 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
r = t49835;
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49838 =
                              idx49840;
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49839 =
                              ((t49838) + (((long)(1L))));
                            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
idx49840 = t49839;
                        }
                    }
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49775 =
                      r;
                    
//#line 40 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return t49775;
                }
                
                
//#line 42 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public java.lang.String
                                                                                                                                        toString(
                                                                                                                                        ){
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49776 =
                      id;
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49777 =
                      (("") + ((x10.core.Int.$box(t49776))));
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49778 =
                      ((t49777) + (" ("));
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49779 =
                      this.edgeString();
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49780 =
                      ((t49778) + (t49779));
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49781 =
                      ((t49780) + (") > "));
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49782 =
                      out;
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49783 =
                      ((t49781) + ((x10.core.Int.$box(t49782))));
                    
//#line 43 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return t49783;
                }
                
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final public BFS.V
                                                                                                                                       BFS$V$$this$BFS$V(
                                                                                                                                       ){
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return BFS.V.this;
                }
                
                
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final public void
                                                                                                                                       __fieldInitializers_BFS_V(
                                                                                                                                       ){
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.edges = null;
                    
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.out = -1;
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$75 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$75> $RTT = x10.rtt.StaticVoidFunType.<$Closure$75> make(
                    $Closure$75.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(BFS.V.$Closure$75 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$75.class + " calling"); } 
                        $_obj.out$$ = $deserializer.readRef();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        BFS.V.$Closure$75 $_obj = new BFS.V.$Closure$75((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                        } else {
                        $serializer.write(this.out$$);
                        }
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$75(final java.lang.System[] $dummy) { 
                    }
                    
                        
                        public void
                          $apply(
                          ){
                            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
try {{
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.lang.Clock t49750 =
                                  x10.lang.Clock.make();
                                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.
                                                                                                                                                        out$$.visit(((x10.lang.Clock)(t49750)));
                            }}catch (java.lang.Error __lowerer__var__0__) {
                                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
throw __lowerer__var__0__;
                            }catch (java.lang.Throwable __lowerer__var__1__) {
                                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                            }
                        }
                        
                        public BFS.V out$$;
                        
                        public $Closure$75(final BFS.V out$$) { {
                                                                       this.out$$ = out$$;
                                                                   }}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$76 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$76> $RTT = x10.rtt.StaticVoidFunType.<$Closure$76> make(
                    $Closure$76.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(BFS.V.$Closure$76 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$76.class + " calling"); } 
                        $_obj.v49826 = $deserializer.readRef();
                        $_obj.c = $deserializer.readRef();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        BFS.V.$Closure$76 $_obj = new BFS.V.$Closure$76((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        if (v49826 instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.v49826);
                        } else {
                        $serializer.write(this.v49826);
                        }
                        if (c instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.c);
                        } else {
                        $serializer.write(this.c);
                        }
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$76(final java.lang.System[] $dummy) { 
                    }
                    
                        
                        public void
                          $apply(
                          ){
                            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
try {{
                                
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
x10.lang.Clock.advanceAll();
                                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.
                                                                                                                                                        v49826.visit(((x10.lang.Clock)(this.
                                                                                                                                                                                         c)));
                            }}catch (java.lang.Error __lowerer__var__0__) {
                                
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
throw __lowerer__var__0__;
                            }catch (java.lang.Throwable __lowerer__var__1__) {
                                
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                            }
                        }
                        
                        public BFS.V v49826;
                        public x10.lang.Clock c;
                        
                        public $Closure$76(final BFS.V v49826,
                                           final x10.lang.Clock c) { {
                                                                            this.v49826 = ((BFS.V)(v49826));
                                                                            this.c = ((x10.lang.Clock)(c));
                                                                        }}
                        
                    }
                    
                
                }
                
                
                
//#line 45 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
                private static final long serialVersionUID = 1L;
                public static void main(java.lang.String[] args)  {
                // start native runtime
                new $Main().start(args);
                }
                
                // called by native runtime inside main x10 thread
                public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
                // call the original app-main method
                BFS.main(args);
                }
                }
                
                // the original app-main method
                public static void main(final x10.core.Rail<java.lang.String> args)  {
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49784 =
                      ((java.lang.String[])args.value)[(int)0L];
                    
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int N =
                      java.lang.Integer.parseInt(t49784);
                    
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.io.Printer t49785 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49786 =
                      (("N=") + ((x10.core.Int.$box(N))));
                    
//#line 47 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
t49785.println(((java.lang.Object)(t49786)));
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49789 =
                      ((long)(((int)(N))));
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.fun.Fun_0_1 t49790 =
                      ((x10.core.fun.Fun_0_1)(new BFS.$Closure$77()));
                    
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.Rail vertices =
                      ((x10.core.Rail)(new x10.core.Rail<BFS.V>(BFS.V.$RTT, t49789,
                                                                ((x10.core.fun.Fun_0_1)(t49790)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                    
//#line 49 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.util.Random r =
                      ((x10.util.Random)(new x10.util.Random((java.lang.System[]) null).x10$util$Random$$init$S()));
                    
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.Rail rail49880 =
                      ((x10.core.Rail)(vertices));
                    
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long size49881 =
                      ((x10.core.Rail<BFS.V>)rail49880).
                        size;
                    
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
long idx49868 =
                      0L;
                    {
                        
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V[] rail49880$value49893 =
                          ((BFS.V[])rail49880.value);
                        
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49869 =
                              idx49868;
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49870 =
                              ((t49869) < (((long)(size49881))));
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (!(t49870)) {
                                
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
break;
                            }
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49864 =
                              idx49868;
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V v49865 =
                              ((BFS.V)(((BFS.V)rail49880$value49893[(int)t49864])));
                            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49845 =
                              r.nextInt$O((int)(N));
                            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49846 =
                              java.lang.Math.abs(((int)(t49845)));
                            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49847 =
                              ((t49846) + (((int)(1))));
                            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int N49848 =
                              java.lang.Math.min(((int)(t49847)),((int)(4)));
                            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49849 =
                              ((long)(((int)(N49848))));
                            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.fun.Fun_0_1 t49850 =
                              ((x10.core.fun.Fun_0_1)(new BFS.$Closure$78(v49865,
                                                                          N,
                                                                          r,
                                                                          vertices, (BFS.$Closure$78.__3$1BFS$V$2) null)));
                            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.Rail t49863 =
                              ((x10.core.Rail)(new x10.core.Rail<BFS.V>(BFS.V.$RTT, t49849,
                                                                        ((x10.core.fun.Fun_0_1)(t49850)), (x10.core.Rail.__1$1x10$lang$Long$3x10$lang$Rail$$T$2) null)));
                            
//#line 52 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
v49865.set__0$1BFS$V$2(((x10.core.Rail)(t49863)));
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49866 =
                              idx49868;
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49867 =
                              ((t49866) + (((long)(1L))));
                            
//#line 50 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
idx49868 = t49867;
                        }
                    }
                    
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.io.Printer t49814 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 56 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
t49814.println(((java.lang.Object)("Starting mark.")));
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V t49815 =
                      ((BFS.V[])vertices.value)[(int)0L];
                    
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
t49815.mark();
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.core.Rail rail49882 =
                      ((x10.core.Rail)(vertices));
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long size49883 =
                      ((x10.core.Rail<BFS.V>)rail49882).
                        size;
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
long idx49877 =
                      0L;
                    {
                        
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V[] rail49882$value49894 =
                          ((BFS.V[])rail49882.value);
                        
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
for (;
                                                                                                                                                   true;
                                                                                                                                                   ) {
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49878 =
                              idx49877;
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49879 =
                              ((t49878) < (((long)(size49883))));
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (!(t49879)) {
                                
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
break;
                            }
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49873 =
                              idx49877;
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V v49874 =
                              ((BFS.V)(((BFS.V)rail49882$value49894[(int)t49873])));
                            
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final x10.io.Printer t49871 =
                              ((x10.io.Printer)(x10.io.Console.get$OUT()));
                            
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final java.lang.String t49872 =
                              (("") + (v49874));
                            
//#line 59 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
t49871.println(((java.lang.Object)(t49872)));
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49875 =
                              idx49877;
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49876 =
                              ((t49875) + (((long)(1L))));
                            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
idx49877 = t49876;
                        }
                    }
                }
                
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final public BFS
                                                                                                                                       BFS$$this$BFS(
                                                                                                                                       ){
                    
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return BFS.this;
                }
                
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
// creation method for java code (1-phase java constructor)
                public BFS(){this((java.lang.System[]) null);
                                 BFS$$init$S();}
                
                // constructor for non-virtual call
                final public BFS BFS$$init$S() { {
                                                        
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
;
                                                        
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"

                                                        
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
this.__fieldInitializers_BFS();
                                                    }
                                                    return this;
                                                    }
                
                
                
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final public void
                                                                                                                                       __fieldInitializers_BFS(
                                                                                                                                       ){
                    
                }
                
                @x10.runtime.impl.java.X10Generated final public static class $Closure$77 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$77> $RTT = x10.rtt.StaticFunType.<$Closure$77> make(
                    $Closure$77.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, BFS.V.$RTT)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(BFS.$Closure$77 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$77.class + " calling"); } 
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        BFS.$Closure$77 $_obj = new BFS.$Closure$77((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        
                    }
                    
                    // constructor just for allocation
                    public $Closure$77(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public BFS.V
                          $apply(
                          final long i){
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49787 =
                              ((int)(long)(((long)(i))));
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V t49788 =
                              ((BFS.V)(new BFS.V((java.lang.System[]) null).BFS$V$$init$S(t49787)));
                            
//#line 48 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return t49788;
                        }
                        
                        public $Closure$77() { {
                                                      
                                                  }}
                        
                    }
                    
                @x10.runtime.impl.java.X10Generated final public static class $Closure$78 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
                {
                    private static final long serialVersionUID = 1L;
                    public static final x10.rtt.RuntimeType<$Closure$78> $RTT = x10.rtt.StaticFunType.<$Closure$78> make(
                    $Closure$78.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, BFS.V.$RTT)}
                    );
                    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
                    
                    public x10.rtt.Type<?> $getParam(int i) {return null;}
                    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
                    public static x10.serialization.X10JavaSerializable $_deserialize_body(BFS.$Closure$78 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$78.class + " calling"); } 
                        $_obj.v49865 = $deserializer.readRef();
                        $_obj.N = $deserializer.readInt();
                        $_obj.r = $deserializer.readRef();
                        $_obj.vertices = $deserializer.readRef();
                        return $_obj;
                    }
                    
                    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
                    
                        BFS.$Closure$78 $_obj = new BFS.$Closure$78((java.lang.System[]) null);
                        $deserializer.record_reference($_obj);
                        return $_deserialize_body($_obj, $deserializer);
                        
                    }
                    
                    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
                    
                        if (v49865 instanceof x10.serialization.X10JavaSerializable) {
                        $serializer.write((x10.serialization.X10JavaSerializable) this.v49865);
                        } else {
                        $serializer.write(this.v49865);
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
                    public $Closure$78(final java.lang.System[] $dummy) { 
                    }
                    // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
                    public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
                    return $apply(x10.core.Long.$unbox(a1));
                    }
                    
                        
                        public BFS.V
                          $apply(
                          final long i49851){
                            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49852 =
                              ((long) i49851) ==
                            ((long) 0L);
                            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
int t49853 =
                               0;
                            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (t49852) {
                                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49854 =
                                  this.
                                    v49865.
                                    id;
                                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49855 =
                                  ((this.
                                      N) - (((int)(1))));
                                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final boolean t49856 =
                                  ((int) t49854) ==
                                ((int) t49855);
                                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
int t49857 =
                                   0;
                                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
if (t49856) {
                                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
t49857 = ((this.
                                                                                                                                                                       N) - (((int)(1))));
                                } else {
                                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49858 =
                                      this.
                                        v49865.
                                        id;
                                    
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
t49857 = ((t49858) + (((int)(1))));
                                }
                                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
t49853 = t49857;
                            } else {
                                
//#line 54 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49859 =
                                  this.
                                    r.nextInt$O((int)(this.
                                                        N));
                                
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
t49853 = java.lang.Math.abs(((int)(t49859)));
                            }
                            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final int t49860 =
                              t49853;
                            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final long t49861 =
                              ((long)(((int)(t49860))));
                            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
final BFS.V t49862 =
                              ((BFS.V[])this.
                                          vertices.value)[(int)t49861];
                            
//#line 53 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/BFS.x10"
return t49862;
                        }
                        
                        public BFS.V v49865;
                        public int N;
                        public x10.util.Random r;
                        public x10.core.Rail<BFS.V> vertices;
                        
                        public $Closure$78(final BFS.V v49865,
                                           final int N,
                                           final x10.util.Random r,
                                           final x10.core.Rail<BFS.V> vertices, __3$1BFS$V$2 $dummy) { {
                                                                                                              this.v49865 = ((BFS.V)(v49865));
                                                                                                              this.N = N;
                                                                                                              this.r = ((x10.util.Random)(r));
                                                                                                              this.vertices = ((x10.core.Rail)(vertices));
                                                                                                          }}
                        // synthetic type for parameter mangling
                        public static final class __3$1BFS$V$2 {}
                        
                    }
                    
                
                }
                
                