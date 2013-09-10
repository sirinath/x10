package pppp.util;

@x10.runtime.impl.java.X10Generated public class Unit extends x10.core.Struct implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Unit> $RTT = x10.rtt.NamedStructType.<Unit> make(
    "pppp.util.Unit", Unit.class, new x10.rtt.Type[] {x10.rtt.Types.STRUCT}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Unit $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Unit.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.util.Unit $_obj = new pppp.util.Unit((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // zero value constructor
    public Unit(final java.lang.System $dummy) { }
    // constructor just for allocation
    public Unit(final java.lang.System[] $dummy) { 
    }
    
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public java.lang.String
                                                                                                                                          typeName(
                                                                                                                                          ){try {return x10.rtt.Types.typeName(this);}
        catch (java.lang.Throwable exc$55301) {
        throw x10.runtime.impl.java.ThrowableUtils.ensureX10Exception(exc$55301);
        }
        }
        
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public java.lang.String
                                                                                                                                          toString(
                                                                                                                                          ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return "struct pppp.util.Unit";
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public int
                                                                                                                                          hashCode(
                                                                                                                                          ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
int result =
              1;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final int t45137 =
              result;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return t45137;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public boolean
                                                                                                                                          equals(
                                                                                                                                          java.lang.Object other){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final java.lang.Object t45138 =
              other;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final boolean t45139 =
              pppp.util.Unit.$RTT.isInstance(t45138);
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final boolean t45140 =
              !(t45139);
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
if (t45140) {
                
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return false;
            }
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final java.lang.Object t45141 =
              other;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final pppp.util.Unit t45142 =
              ((pppp.util.Unit)x10.rtt.Types.asStruct(pppp.util.Unit.$RTT,t45141));
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final boolean t45143 =
              this.equals$O(((pppp.util.Unit)(t45142)));
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return t45143;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public boolean
                                                                                                                                          equals$O(
                                                                                                                                          pppp.util.Unit other){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return true;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public boolean
                                                                                                                                          _struct_equals$O(
                                                                                                                                          java.lang.Object other){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final java.lang.Object t45144 =
              other;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final boolean t45145 =
              pppp.util.Unit.$RTT.isInstance(t45144);
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final boolean t45146 =
              !(t45145);
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
if (t45146) {
                
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return false;
            }
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final java.lang.Object t45147 =
              other;
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final pppp.util.Unit t45148 =
              ((pppp.util.Unit)x10.rtt.Types.asStruct(pppp.util.Unit.$RTT,t45147));
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final boolean t45149 =
              this._struct_equals$O(((pppp.util.Unit)(t45148)));
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return t45149;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public boolean
                                                                                                                                          _struct_equals$O(
                                                                                                                                          pppp.util.Unit other){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return true;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public pppp.util.Unit
                                                                                                                                          pppp$util$Unit$$this$pppp$util$Unit(
                                                                                                                                          ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
return pppp.util.Unit.this;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
// creation method for java code (1-phase java constructor)
        public Unit(){this((java.lang.System[]) null);
                          pppp$util$Unit$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.util.Unit pppp$util$Unit$$init$S() { {
                                                                      
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
;
                                                                      
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"

                                                                      
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
this.__fieldInitializers_pppp_util_Unit();
                                                                  }
                                                                  return this;
                                                                  }
        
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Unit.x10"
final public void
                                                                                                                                          __fieldInitializers_pppp_util_Unit(
                                                                                                                                          ){
            
        }
    
}

