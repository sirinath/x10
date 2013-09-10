package pppp.util;

@x10.runtime.impl.java.X10Generated public class Logger extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Logger> $RTT = x10.rtt.NamedType.<Logger> make(
    "pppp.util.Logger", Logger.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Logger $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Logger.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.util.Logger $_obj = new pppp.util.Logger((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        
    }
    
    // constructor just for allocation
    public Logger(final java.lang.System[] $dummy) { 
    }
    
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final public static long DEBUG = 0L;
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final public static long LOG = 1L;
        
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final public static long INFO = 2L;
        
//#line 7 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final public static long LEVEL = 2L;
        
        
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
public static void
                                                                                                                                            out(
                                                                                                                                            final java.lang.String s){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final x10.io.Printer t43922 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43916 =
              x10.lang.System.nanoTime$O();
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43917 =
              (("[") + ((x10.core.Long.$box(t43916))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43918 =
              ((t43917) + (", "));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final int t43919 =
              x10.lang.Runtime.workerId$O();
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43920 =
              ((t43918) + ((x10.core.Int.$box(t43919))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43921 =
              ((t43920) + ("] "));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43923 =
              ((t43921) + (s));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
t43922.println(((java.lang.Object)(t43923)));
        }
        
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
public static void
                                                                                                                                             log__0$1x10$lang$String$2(
                                                                                                                                             final x10.core.fun.Fun_0_0<java.lang.String> s){
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43924 =
              pppp.util.Logger.LOG;
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43925 =
              pppp.util.Logger.LEVEL;
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final boolean t43927 =
              false;
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
if (t43927) {
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43926 =
                  ((x10.core.fun.Fun_0_0<java.lang.String>)s).$apply$G();
                
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
pppp.util.Logger.out(((java.lang.String)(t43926)));
            }
        }
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
public static void
                                                                                                                                             log(
                                                                                                                                             final java.lang.String s){
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43928 =
              pppp.util.Logger.LOG;
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43929 =
              pppp.util.Logger.LEVEL;
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final boolean t43930 =
              false;
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
if (t43930) {
                
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
pppp.util.Logger.out(((java.lang.String)(s)));
            }
        }
        
        
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
public static <$T> void
                                                                                                                                             log__0$1pppp$util$Logger$$T$3x10$lang$String$2__1pppp$util$Logger$$T(
                                                                                                                                             final x10.rtt.Type $T,
                                                                                                                                             final x10.core.fun.Fun_0_1<$T,java.lang.String> s,
                                                                                                                                             final $T t){
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43931 =
              pppp.util.Logger.LOG;
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43932 =
              pppp.util.Logger.LEVEL;
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final boolean t43934 =
              false;
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
if (t43934) {
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43933 =
                  ((java.lang.String)
                    ((x10.core.fun.Fun_0_1<$T,java.lang.String>)s).$apply(t,$T));
                
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
pppp.util.Logger.out(((java.lang.String)(t43933)));
            }
        }
        
        
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
public static void
                                                                                                                                             debug__0$1x10$lang$String$2(
                                                                                                                                             final x10.core.fun.Fun_0_0<java.lang.String> s){
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43935 =
              pppp.util.Logger.DEBUG;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43936 =
              pppp.util.Logger.LEVEL;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final boolean t43938 =
              false;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
if (t43938) {
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43937 =
                  ((x10.core.fun.Fun_0_0<java.lang.String>)s).$apply$G();
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
pppp.util.Logger.out(((java.lang.String)(t43937)));
            }
        }
        
        
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
public static <$T> void
                                                                                                                                             debug__0$1pppp$util$Logger$$T$3x10$lang$String$2__1pppp$util$Logger$$T(
                                                                                                                                             final x10.rtt.Type $T,
                                                                                                                                             final x10.core.fun.Fun_0_1<$T,java.lang.String> s,
                                                                                                                                             final $T t){
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43939 =
              pppp.util.Logger.DEBUG;
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43940 =
              pppp.util.Logger.LEVEL;
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final boolean t43942 =
              false;
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
if (t43942) {
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final java.lang.String t43941 =
                  ((java.lang.String)
                    ((x10.core.fun.Fun_0_1<$T,java.lang.String>)s).$apply(t,$T));
                
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
pppp.util.Logger.out(((java.lang.String)(t43941)));
            }
        }
        
        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
public static void
                                                                                                                                             debug(
                                                                                                                                             final java.lang.String s){
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43943 =
              pppp.util.Logger.DEBUG;
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final long t43944 =
              pppp.util.Logger.LEVEL;
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final boolean t43945 =
              false;
            
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
if (t43945) {
                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
pppp.util.Logger.out(((java.lang.String)(s)));
            }
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final public pppp.util.Logger
                                                                                                                                            pppp$util$Logger$$this$pppp$util$Logger(
                                                                                                                                            ){
            
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
return pppp.util.Logger.this;
        }
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
// creation method for java code (1-phase java constructor)
        public Logger(){this((java.lang.System[]) null);
                            pppp$util$Logger$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.util.Logger pppp$util$Logger$$init$S() { {
                                                                          
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
;
                                                                          
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"

                                                                          
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
this.__fieldInitializers_pppp_util_Logger();
                                                                      }
                                                                      return this;
                                                                      }
        
        
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Logger.x10"
final public void
                                                                                                                                            __fieldInitializers_pppp_util_Logger(
                                                                                                                                            ){
            
        }
    
}

