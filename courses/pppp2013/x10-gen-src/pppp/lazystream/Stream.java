package pppp.lazystream;


@x10.runtime.impl.java.X10Generated public class Stream<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Stream> $RTT = x10.rtt.NamedType.<Stream> make(
    "pppp.lazystream.Stream", Stream.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Stream.class + " calling"); } 
        $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
        $_obj.evaluated = $deserializer.readBoolean();
        $_obj.cache = $deserializer.readRef();
        $_obj.s = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.lazystream.Stream $_obj = new pppp.lazystream.Stream((java.lang.System[]) null, (x10.rtt.Type) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
        $serializer.write(this.evaluated);
        if (cache instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.cache);
        } else {
        $serializer.write(this.cache);
        }
        if (s instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.s);
        } else {
        $serializer.write(this.s);
        }
        
    }
    
    // constructor just for allocation
    public Stream(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
    pppp.lazystream.Stream.$initParams(this, $T);
    }
    // bridge for method abstract public ()=>U.operator()(){}:U
    public pppp.lazystream.Cons
      $apply$G(){return $apply();}
    
        private x10.rtt.Type $T;
        // initializer of type parameters
        public static void $initParams(final Stream $this, final x10.rtt.Type $T) {
        $this.$T = $T;
        }
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public x10.core.fun.Fun_0_0<pppp.lazystream.Cons<$T>> s;
        
        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public boolean evaluated;
        
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Cons<$T> cache;
        
        
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Cons
                                                                                                                                                   $apply(
                                                                                                                                                   ){
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52479 =
              evaluated;
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52482 =
              !(t52479);
            
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52482) {
                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52480 =
                  ((x10.core.fun.Fun_0_0)(s));
                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52481 =
                  ((x10.core.fun.Fun_0_0<pppp.lazystream.Cons<$T>>)t52480).$apply$G();
                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
((pppp.lazystream.Stream<$T>)this).cache = ((pppp.lazystream.Cons)(t52481));
                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
((pppp.lazystream.Stream<$T>)this).evaluated = true;
            }
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52483 =
              ((pppp.lazystream.Cons)(cache));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52483;
        }
        
        
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static <$T> pppp.lazystream.Stream
                                                                                                                                                   $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2(
                                                                                                                                                   final x10.rtt.Type $T,
                                                                                                                                                   final x10.core.fun.Fun_0_0<pppp.lazystream.Cons<$T>> s){
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52484 =
              ((pppp.lazystream.Stream)(new pppp.lazystream.Stream<$T>((java.lang.System[]) null, $T).pppp$lazystream$Stream$$init$S(((x10.core.fun.Fun_0_0)(s)), (pppp.lazystream.Stream.__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2) null)));
            
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52484;
        }
        
        
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static <$T> pppp.lazystream.Stream
                                                                                                                                                   $implicit_convert__0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$T$2$2(
                                                                                                                                                   final x10.rtt.Type $T,
                                                                                                                                                   final x10.core.fun.Fun_0_0<pppp.lazystream.Stream<$T>> x){
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52487 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$90<$T>($T, x, (pppp.lazystream.Stream.$Closure$90.__0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$Closure$90$$T$2$2) null)));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52488 =
              ((pppp.lazystream.Stream)(new pppp.lazystream.Stream<$T>((java.lang.System[]) null, $T).pppp$lazystream$Stream$$init$S(((x10.core.fun.Fun_0_0)(t52487)), (pppp.lazystream.Stream.__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2) null)));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52488;
        }
        
        
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_tilde__0pppp$lazystream$Stream$$T(
                                                                                                                                                   final $T t){
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52490 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$91<$T>($T, ((pppp.lazystream.Stream)(this)),
                                                                                 t, (pppp.lazystream.Stream.$Closure$91.__0$1pppp$lazystream$Stream$$Closure$91$$T$2__1pppp$lazystream$Stream$$Closure$91$$T) null)));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52491 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52490)))));
            
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52491;
        }
        
        
//#line 57 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_arrow__0$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                   final pppp.lazystream.Stream x){
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52497 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$92<$T>($T, ((pppp.lazystream.Stream)(this)),
                                                                                 x, (pppp.lazystream.Stream.$Closure$92.__0$1pppp$lazystream$Stream$$Closure$92$$T$2__1$1pppp$lazystream$Stream$$Closure$92$$T$2) null)));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52498 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52497)))));
            
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52498;
        }
        
        
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $apply__0$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(
                                                                                                                                                   final x10.core.fun.Fun_0_1 f){
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52505 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$93<$T>($T, ((pppp.lazystream.Stream)(this)),
                                                                                 f, (pppp.lazystream.Stream.$Closure$93.__0$1pppp$lazystream$Stream$$Closure$93$$T$2__1$1pppp$lazystream$Stream$$Closure$93$$T$3pppp$lazystream$Stream$$Closure$93$$T$2) null)));
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52506 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52505)))));
            
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52506;
        }
        
        
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_times__0pppp$lazystream$Stream$$T(
                                                                                                                                                   final $T x){
            
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_1 t52508 =
              ((x10.core.fun.Fun_0_1)(new pppp.lazystream.Stream.$Closure$94<$T>($T, x, (pppp.lazystream.Stream.$Closure$94.__0pppp$lazystream$Stream$$Closure$94$$T) null)));
            
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52509 =
              ((pppp.lazystream.Stream<$T>)
                this.$apply__0$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(((x10.core.fun.Fun_0_1)(t52508))));
            
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52509;
        }
        
        
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_over__0pppp$lazystream$Stream$$T(
                                                                                                                                                   final $T x){
            
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_1 t52511 =
              ((x10.core.fun.Fun_0_1)(new pppp.lazystream.Stream.$Closure$95<$T>($T, x, (pppp.lazystream.Stream.$Closure$95.__0pppp$lazystream$Stream$$Closure$95$$T) null)));
            
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52512 =
              ((pppp.lazystream.Stream<$T>)
                this.$apply__0$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(((x10.core.fun.Fun_0_1)(t52511))));
            
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52512;
        }
        
        
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_plus__0pppp$lazystream$Stream$$T(
                                                                                                                                                   final $T x){
            
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_1 t52514 =
              ((x10.core.fun.Fun_0_1)(new pppp.lazystream.Stream.$Closure$96<$T>($T, x, (pppp.lazystream.Stream.$Closure$96.__0pppp$lazystream$Stream$$Closure$96$$T) null)));
            
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52515 =
              ((pppp.lazystream.Stream<$T>)
                this.$apply__0$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(((x10.core.fun.Fun_0_1)(t52514))));
            
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52515;
        }
        
        
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_minus__0pppp$lazystream$Stream$$T(
                                                                                                                                                   final $T x){
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_1 t52517 =
              ((x10.core.fun.Fun_0_1)(new pppp.lazystream.Stream.$Closure$97<$T>($T, x, (pppp.lazystream.Stream.$Closure$97.__0pppp$lazystream$Stream$$Closure$97$$T) null)));
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52518 =
              ((pppp.lazystream.Stream<$T>)
                this.$apply__0$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(((x10.core.fun.Fun_0_1)(t52517))));
            
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52518;
        }
        
        
//#line 79 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public <$U, $V> pppp.lazystream.Stream
                                                                                                                                                   $apply__0$1pppp$lazystream$Stream$$U$2__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$U$3pppp$lazystream$Stream$$V$2(
                                                                                                                                                   final x10.rtt.Type $U,
                                                                                                                                                   final x10.rtt.Type $V,
                                                                                                                                                   final pppp.lazystream.Stream o,
                                                                                                                                                   final x10.core.fun.Fun_0_2 f){
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52526 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$98<$T, $U, $V>($T, $U, $V, ((pppp.lazystream.Stream)(this)),
                                                                                         o,
                                                                                         f, (pppp.lazystream.Stream.$Closure$98.__0$1pppp$lazystream$Stream$$Closure$98$$T$2__1$1pppp$lazystream$Stream$$Closure$98$$U$2__2$1pppp$lazystream$Stream$$Closure$98$$T$3pppp$lazystream$Stream$$Closure$98$$U$3pppp$lazystream$Stream$$Closure$98$$V$2) null)));
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52527 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$V> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($V, ((x10.core.fun.Fun_0_0)(t52526)))));
            
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52527;
        }
        
        
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_times__0$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                   final pppp.lazystream.Stream t){
            
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_2 t52529 =
              ((x10.core.fun.Fun_0_2)(new pppp.lazystream.Stream.$Closure$99<$T>($T)));
            
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52530 =
              ((pppp.lazystream.Stream<$T>)
                this.<$T,
              $T> $apply__0$1pppp$lazystream$Stream$$U$2__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$U$3pppp$lazystream$Stream$$V$2($T, $T, ((pppp.lazystream.Stream)(t)),
                                                                                                                                               ((x10.core.fun.Fun_0_2)(t52529))));
            
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52530;
        }
        
        
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_over__0$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                   final pppp.lazystream.Stream t){
            
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_2 t52532 =
              ((x10.core.fun.Fun_0_2)(new pppp.lazystream.Stream.$Closure$100<$T>($T)));
            
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52533 =
              ((pppp.lazystream.Stream<$T>)
                this.<$T,
              $T> $apply__0$1pppp$lazystream$Stream$$U$2__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$U$3pppp$lazystream$Stream$$V$2($T, $T, ((pppp.lazystream.Stream)(t)),
                                                                                                                                               ((x10.core.fun.Fun_0_2)(t52532))));
            
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52533;
        }
        
        
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_plus__0$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                   final pppp.lazystream.Stream t){
            
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_2 t52535 =
              ((x10.core.fun.Fun_0_2)(new pppp.lazystream.Stream.$Closure$101<$T>($T)));
            
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52536 =
              ((pppp.lazystream.Stream<$T>)
                this.<$T,
              $T> $apply__0$1pppp$lazystream$Stream$$U$2__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$U$3pppp$lazystream$Stream$$V$2($T, $T, ((pppp.lazystream.Stream)(t)),
                                                                                                                                               ((x10.core.fun.Fun_0_2)(t52535))));
            
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52536;
        }
        
        
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_minus__0$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                   final pppp.lazystream.Stream t){
            
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_2 t52538 =
              ((x10.core.fun.Fun_0_2)(new pppp.lazystream.Stream.$Closure$102<$T>($T)));
            
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52539 =
              ((pppp.lazystream.Stream<$T>)
                this.<$T,
              $T> $apply__0$1pppp$lazystream$Stream$$U$2__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$U$3pppp$lazystream$Stream$$V$2($T, $T, ((pppp.lazystream.Stream)(t)),
                                                                                                                                               ((x10.core.fun.Fun_0_2)(t52538))));
            
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52539;
        }
        
        
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public <$T52446> $T52446
                                                                                                                                                   min__0pppp$lazystream$Stream$$T52446__1pppp$lazystream$Stream$$T52446$G(
                                                                                                                                                   final x10.rtt.Type $T52446,
                                                                                                                                                   final $T52446 x,
                                                                                                                                                   final $T52446 y){
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52540 =
              x10.core.Boolean.$unbox(((x10.util.Ordered<$T52446>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.util.Ordered.$RTT, $T52446),x)).$lt$Z((($T52446)(y)),$T52446));
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
$T52446 t52541 =
               null;
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52540) {
                
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52541 = (($T52446)(x));
            } else {
                
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52541 = (($T52446)(y));
            }
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T52446 t52542 =
              (($T52446)(t52541));
            
//#line 91 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52542;
        }
        
        
//#line 95 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                   $inv_caret__0$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                   final pppp.lazystream.Stream a){
            
//#line 96 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52555 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$103<$T>($T, ((pppp.lazystream.Stream)(this)),
                                                                                  a, (pppp.lazystream.Stream.$Closure$103.__0$1pppp$lazystream$Stream$$Closure$103$$T$2__1$1pppp$lazystream$Stream$$Closure$103$$T$2) null)));
            
//#line 96 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52556 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52555)))));
            
//#line 96 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52556;
        }
        
        
//#line 104 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                    $percent__0$1pppp$lazystream$Stream$$T$3x10$lang$Boolean$2(
                                                                                                                                                    final x10.core.fun.Fun_0_1 f){
            
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52570 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$104<$T>($T, ((pppp.lazystream.Stream)(this)),
                                                                                  f, (pppp.lazystream.Stream.$Closure$104.__0$1pppp$lazystream$Stream$$Closure$104$$T$2__1$1pppp$lazystream$Stream$$Closure$104$$T$3x10$lang$Boolean$2) null)));
            
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52571 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52570)))));
            
//#line 105 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52571;
        }
        
        
//#line 114 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public void
                                                                                                                                                    force(
                                                                                                                                                    final int N){
            
//#line 115 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
pppp.lazystream.Stream me =
              this;
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long i52448min52704 =
              1L;
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long i52448max52705 =
              ((long)(((int)(N))));
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
long i52701 =
              i52448min52704;
            
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
for (;
                                                                                                                                                           true;
                                                                                                                                                           ) {
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52702 =
                  i52701;
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52703 =
                  ((t52702) <= (((long)(i52448max52705))));
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (!(t52703)) {
                    
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
break;
                }
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52696 =
                  me;
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52697 =
                  ((pppp.lazystream.Cons<$T>)
                    ((pppp.lazystream.Stream<$T>)t52696).$apply());
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52698 =
                  ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52697).
                                              t));
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
me = t52698;
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52699 =
                  i52701;
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52700 =
                  ((t52699) + (((long)(1L))));
                
//#line 116 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
i52701 = t52700;
            }
        }
        
        
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                    cdr(
                                                                                                                                                    ){
            
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52582 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$105<$T>($T, ((pppp.lazystream.Stream)(this)), (pppp.lazystream.Stream.$Closure$105.__0$1pppp$lazystream$Stream$$Closure$105$$T$2) null)));
            
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52583 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52582)))));
            
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52583;
        }
        
        
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                    cddr(
                                                                                                                                                    ){
            
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52588 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$106<$T>($T, ((pppp.lazystream.Stream)(this)), (pppp.lazystream.Stream.$Closure$106.__0$1pppp$lazystream$Stream$$Closure$106$$T$2) null)));
            
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52589 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52588)))));
            
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52589;
        }
        
        
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                    cdddr(
                                                                                                                                                    ){
            
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52596 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$107<$T>($T, ((pppp.lazystream.Stream)(this)), (pppp.lazystream.Stream.$Closure$107.__0$1pppp$lazystream$Stream$$Closure$107$$T$2) null)));
            
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52597 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52596)))));
            
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52597;
        }
        
        
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                    cddddr(
                                                                                                                                                    ){
            
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52606 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$108<$T>($T, ((pppp.lazystream.Stream)(this)), (pppp.lazystream.Stream.$Closure$108.__0$1pppp$lazystream$Stream$$Closure$108$$T$2) null)));
            
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52607 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52606)))));
            
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52607;
        }
        
        
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                    even(
                                                                                                                                                    ){
            
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52613 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$109<$T>($T, ((pppp.lazystream.Stream)(this)), (pppp.lazystream.Stream.$Closure$109.__0$1pppp$lazystream$Stream$$Closure$109$$T$2) null)));
            
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52614 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52613)))));
            
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52614;
        }
        
        
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                    odd(
                                                                                                                                                    ){
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52615 =
              ((pppp.lazystream.Stream<$T>)
                this.cdr());
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52616 =
              ((pppp.lazystream.Stream<$T>)
                ((pppp.lazystream.Stream<$T>)t52615).even());
            
//#line 134 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52616;
        }
        
        
//#line 139 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public pppp.lazystream.Stream
                                                                                                                                                    zip__0$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                    final pppp.lazystream.Stream a){
            
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52625 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$111<$T>($T, ((pppp.lazystream.Stream)(this)),
                                                                                  a, (pppp.lazystream.Stream.$Closure$111.__0$1pppp$lazystream$Stream$$Closure$111$$T$2__1$1pppp$lazystream$Stream$$Closure$111$$T$2) null)));
            
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52626 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52625)))));
            
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52626;
        }
        
        
//#line 147 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static <$T, $U> pppp.lazystream.Stream
                                                                                                                                                    pair__0$1pppp$lazystream$Stream$$T$2__1$1pppp$lazystream$Stream$$U$2(
                                                                                                                                                    final x10.rtt.Type $T,
                                                                                                                                                    final x10.rtt.Type $U,
                                                                                                                                                    final pppp.lazystream.Stream<$T> a,
                                                                                                                                                    final pppp.lazystream.Stream<$U> b){
            
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_2 t52628 =
              ((x10.core.fun.Fun_0_2)(new pppp.lazystream.Stream.$Closure$112<$T, $U>($T, $U)));
            
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52629 =
              ((pppp.lazystream.Stream<x10.util.Pair<$T, $U>>)
                ((pppp.lazystream.Stream<$T>)a).<$U,
              x10.util.Pair<$T, $U>> $apply__0$1pppp$lazystream$Stream$$U$2__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$U$3pppp$lazystream$Stream$$V$2($U, x10.rtt.ParameterizedType.make(x10.util.Pair.$RTT, $T, $U), ((pppp.lazystream.Stream)(b)),
                                                                                                                                                                  ((x10.core.fun.Fun_0_2)(t52628))));
            
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52629;
        }
        
        
//#line 150 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final public static int PRINT_N_PER_LINE = 10;
        
        
//#line 155 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public void
                                                                                                                                                    print(
                                                                                                                                                    final int n){
            
//#line 155 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52630 =
              pppp.lazystream.Stream.PRINT_N_PER_LINE;
            
//#line 155 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
this.print((int)(n),
                                                                                                                                                                 (int)(t52630));
        }
        
        
//#line 156 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public void
                                                                                                                                                    print(
                                                                                                                                                    final int n,
                                                                                                                                                    final int numPerLine){
            
//#line 157 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
pppp.lazystream.Stream me =
              this;
            
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long i52464min52727 =
              1L;
            
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long i52464max52728 =
              ((long)(((int)(n))));
            
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
long i52724 =
              i52464min52727;
            
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
for (;
                                                                                                                                                           true;
                                                                                                                                                           ) {
                
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52725 =
                  i52724;
                
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52726 =
                  ((t52725) <= (((long)(i52464max52728))));
                
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (!(t52726)) {
                    
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
break;
                }
                
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long i52721 =
                  i52724;
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52706 =
                  me;
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52707 =
                  ((t52706) == (null));
                
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52707) {
                    
//#line 159 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
break;
                }
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52708 =
                  ((i52721) - (((long)(1L))));
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52709 =
                  ((long)(((int)(numPerLine))));
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52710 =
                  ((t52708) % (((long)(t52709))));
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52711 =
                  ((long) t52710) ==
                ((long) 0L);
                
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52711) {
                    
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.io.Printer t52712 =
                      ((x10.io.Printer)(x10.io.Console.get$OUT()));
                    
//#line 160 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52712.println();
                }
                
//#line 161 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.io.Printer t52713 =
                  ((x10.io.Printer)(x10.io.Console.get$OUT()));
                
//#line 161 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52714 =
                  me;
                
//#line 161 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52715 =
                  ((pppp.lazystream.Cons<$T>)
                    ((pppp.lazystream.Stream<$T>)t52714).$apply());
                
//#line 161 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52716 =
                  (($T)(((pppp.lazystream.Cons<$T>)t52715).
                          h));
                
//#line 161 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final java.lang.String t52717 =
                  ((" ") + (t52716));
                
//#line 161 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52713.print(((java.lang.String)(t52717)));
                
//#line 162 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52718 =
                  me;
                
//#line 162 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52719 =
                  ((pppp.lazystream.Cons<$T>)
                    ((pppp.lazystream.Stream<$T>)t52718).$apply());
                
//#line 162 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52720 =
                  ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52719).
                                              t));
                
//#line 162 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
me = t52720;
                
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52722 =
                  i52724;
                
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52723 =
                  ((t52722) + (((long)(1L))));
                
//#line 158 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
i52724 = t52723;
            }
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.io.Printer t52654 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52651 =
              me;
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52652 =
              ((t52651) == (null));
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
java.lang.String t52653 =
               null;
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52652) {
                
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52653 = ".";
            } else {
                
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52653 = "...";
            }
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final java.lang.String t52655 =
              t52653;
            
//#line 164 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52654.println(((java.lang.Object)(t52655)));
        }
        
        
//#line 166 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public java.lang.String
                                                                                                                                                    toString(
                                                                                                                                                    ){
            
//#line 166 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52656 =
              x10.rtt.Types.hashCode(((java.lang.Object)(this)));
            
//#line 166 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final java.lang.String t52657 =
              (("<Stream #") + ((x10.core.Int.$box(t52656))));
            
//#line 166 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final java.lang.String t52658 =
              ((t52657) + (">"));
            
//#line 166 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52658;
        }
        
        
//#line 172 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static <$T> pppp.lazystream.Stream
                                                                                                                                                    append__0$1pppp$lazystream$Stream$$T$2__1$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                    final x10.rtt.Type $T,
                                                                                                                                                    final x10.core.Rail<$T> x,
                                                                                                                                                    final pppp.lazystream.Stream<$T> f){
            
//#line 172 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52659 =
              pppp.lazystream.Stream.<$T> append__0$1pppp$lazystream$Stream$$T$2__2$1pppp$lazystream$Stream$$T$2($T, ((x10.core.Rail)(x)),
                                                                                                                 (int)(0),
                                                                                                                 ((pppp.lazystream.Stream)(f)));
            
//#line 172 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52659;
        }
        
        
//#line 173 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static <$T> pppp.lazystream.Stream
                                                                                                                                                    append__0$1pppp$lazystream$Stream$$T$2__2$1pppp$lazystream$Stream$$T$2(
                                                                                                                                                    final x10.rtt.Type $T,
                                                                                                                                                    final x10.core.Rail<$T> x,
                                                                                                                                                    final int i,
                                                                                                                                                    final pppp.lazystream.Stream<$T> f){
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52660 =
              ((long)(((int)(i))));
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52661 =
              ((x10.core.Rail<$T>)x).
                size;
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52663 =
              ((t52660) < (((long)(t52661))));
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
pppp.lazystream.Stream t52664 =
               null;
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52663) {
                
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52662 =
                  ((i) + (((int)(1))));
                
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52664 = pppp.lazystream.Stream.<$T> append__0$1pppp$lazystream$Stream$$T$2__2$1pppp$lazystream$Stream$$T$2($T, ((x10.core.Rail)(x)),
                                                                                                                                                                                                                                                                      (int)(t52662),
                                                                                                                                                                                                                                                                      ((pppp.lazystream.Stream)(f)));
            } else {
                
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52664 = f;
            }
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52666 =
              t52664;
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52665 =
              ((long)(((int)(i))));
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52667 =
              (($T)(((x10.core.Rail<$T>)x).$apply$G((long)(t52665))));
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52668 =
              ((pppp.lazystream.Stream<$T>)
                ((pppp.lazystream.Stream<$T>)t52666).$inv_tilde__0pppp$lazystream$Stream$$T((($T)(t52667))));
            
//#line 174 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52668;
        }
        
        
//#line 179 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static pppp.lazystream.Stream
                                                                                                                                                    append__1$1x10$lang$Int$2(
                                                                                                                                                    final x10.lang.IntRange V,
                                                                                                                                                    final pppp.lazystream.Stream<x10.core.Int> f){
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52669 =
              V.
                min;
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52670 =
              V.
                max;
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52675 =
              ((int) t52669) ==
            ((int) t52670);
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
pppp.lazystream.Stream t52676 =
               null;
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52675) {
                
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52676 = f;
            } else {
                
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52671 =
                  V.
                    min;
                
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52672 =
                  ((t52671) + (((int)(1))));
                
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52673 =
                  V.
                    max;
                
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.lang.IntRange t52674 =
                  ((x10.lang.IntRange)(new x10.lang.IntRange((java.lang.System[]) null).x10$lang$IntRange$$init$S(((int)(t52672)), ((int)(t52673)))));
                
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52676 = pppp.lazystream.Stream.append__1$1x10$lang$Int$2(((x10.lang.IntRange)(t52674)),
                                                                                                                                                                                                                    ((pppp.lazystream.Stream)(f)));
            }
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52677 =
              t52676;
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52678 =
              V.
                min;
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52679 =
              ((pppp.lazystream.Stream<x10.core.Int>)
                ((pppp.lazystream.Stream<x10.core.Int>)t52677).$inv_tilde__0pppp$lazystream$Stream$$T(x10.core.Int.$box(t52678)));
            
//#line 180 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52679;
        }
        
        
//#line 186 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static <$T> pppp.lazystream.Stream
                                                                                                                                                    gen__0pppp$lazystream$Stream$$T__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(
                                                                                                                                                    final x10.rtt.Type $T,
                                                                                                                                                    final $T t,
                                                                                                                                                    final x10.core.fun.Fun_0_1<$T,$T> f){
            
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52685 =
              ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$114<$T>($T, f,
                                                                                  t, (pppp.lazystream.Stream.$Closure$114.__0$1pppp$lazystream$Stream$$Closure$114$$T$3pppp$lazystream$Stream$$Closure$114$$T$2__1pppp$lazystream$Stream$$Closure$114$$T) null)));
            
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52686 =
              ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52685)))));
            
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52686;
        }
        
        
//#line 192 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static pppp.lazystream.Stream
                                                                                                                                                    nats(
                                                                                                                                                    final int i){
            
//#line 192 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_1 t52688 =
              ((x10.core.fun.Fun_0_1)(new pppp.lazystream.Stream.$Closure$115()));
            
//#line 192 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52689 =
              pppp.lazystream.Stream.<x10.core.Int> gen__0pppp$lazystream$Stream$$T__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(x10.rtt.Types.INT, x10.core.Int.$box(i),
                                                                                                                                               ((x10.core.fun.Fun_0_1)(t52688)));
            
//#line 192 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52689;
        }
        
        
//#line 193 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
private static pppp.lazystream.Stream<x10.core.Int> nats;
        
        
//#line 196 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
public static pppp.lazystream.Stream
                                                                                                                                                    longNats(
                                                                                                                                                    final long i){
            
//#line 196 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_1 t52691 =
              ((x10.core.fun.Fun_0_1)(new pppp.lazystream.Stream.$Closure$116()));
            
//#line 196 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52692 =
              pppp.lazystream.Stream.<x10.core.Long> gen__0pppp$lazystream$Stream$$T__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(x10.rtt.Types.LONG, x10.core.Long.$box(i),
                                                                                                                                                ((x10.core.fun.Fun_0_1)(t52691)));
            
//#line 196 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52692;
        }
        
        
//#line 197 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
private static pppp.lazystream.Stream<x10.core.Long> longNats;
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final public pppp.lazystream.Stream
                                                                                                                                                   pppp$lazystream$Stream$$this$pppp$lazystream$Stream(
                                                                                                                                                   ){
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return pppp.lazystream.Stream.this;
        }
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
// creation method for java code (1-phase java constructor)
        public Stream(final x10.rtt.Type $T,
                      final x10.core.fun.Fun_0_0<pppp.lazystream.Cons<$T>> s, __0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2 $dummy){this((java.lang.System[]) null, $T);
                                                                                                                                                   pppp$lazystream$Stream$$init$S(s, (pppp.lazystream.Stream.__0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2) null);}
        
        // constructor for non-virtual call
        final public pppp.lazystream.Stream<$T> pppp$lazystream$Stream$$init$S(final x10.core.fun.Fun_0_0<pppp.lazystream.Cons<$T>> s, __0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2 $dummy) { {
                                                                                                                                                                                                                 
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
;
                                                                                                                                                                                                                 
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
this.s = s;
                                                                                                                                                                                                                 
                                                                                                                                                                                                                 
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
this.__fieldInitializers_pppp_lazystream_Stream();
                                                                                                                                                                                                             }
                                                                                                                                                                                                             return this;
                                                                                                                                                                                                             }
        
        
        
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final public void
                                                                                                                                                   __fieldInitializers_pppp_lazystream_Stream(
                                                                                                                                                   ){
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
((pppp.lazystream.Stream<$T>)this).evaluated = false;
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
((pppp.lazystream.Stream<$T>)this).cache = null;
        }
        
        final private static x10.core.concurrent.AtomicInteger initStatus$longNats = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
        private static x10.lang.ExceptionInInitializer exception$longNats;
        final private static x10.core.concurrent.AtomicInteger initStatus$nats = new x10.core.concurrent.AtomicInteger(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED);
        private static x10.lang.ExceptionInInitializer exception$nats;
        
        public static pppp.lazystream.Stream
          get$nats(
          ){
            if (((int) pppp.lazystream.Stream.initStatus$nats.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                return pppp.lazystream.Stream.nats;
            }
            if (((int) pppp.lazystream.Stream.initStatus$nats.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.Stream.nats")));
                }
                throw pppp.lazystream.Stream.exception$nats;
            }
            if (pppp.lazystream.Stream.initStatus$nats.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                     (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                try {{
                    pppp.lazystream.Stream.nats = pppp.lazystream.Stream.nats((int)(0));
                }}catch (java.lang.Throwable exc$52729) {
                    pppp.lazystream.Stream.exception$nats = new x10.lang.ExceptionInInitializer(exc$52729);
                    pppp.lazystream.Stream.initStatus$nats.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    throw pppp.lazystream.Stream.exception$nats;
                }
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: pppp.lazystream.Stream.nats")));
                }
                pppp.lazystream.Stream.initStatus$nats.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
            } else {
                if (pppp.lazystream.Stream.initStatus$nats.get() <
                    x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    while (pppp.lazystream.Stream.initStatus$nats.get() <
                           x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                        x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                    }
                    x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                    if (((int) pppp.lazystream.Stream.initStatus$nats.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.Stream.nats")));
                        }
                        throw pppp.lazystream.Stream.exception$nats;
                    }
                }
            }
            return pppp.lazystream.Stream.nats;
        }
        
        public static pppp.lazystream.Stream
          get$longNats(
          ){
            if (((int) pppp.lazystream.Stream.initStatus$longNats.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.INITIALIZED)) {
                return pppp.lazystream.Stream.longNats;
            }
            if (((int) pppp.lazystream.Stream.initStatus$longNats.get()) ==
                ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.Stream.longNats")));
                }
                throw pppp.lazystream.Stream.exception$longNats;
            }
            if (pppp.lazystream.Stream.initStatus$longNats.compareAndSet((int)(x10.runtime.impl.java.InitDispatcher.UNINITIALIZED),
                                                                         (int)(x10.runtime.impl.java.InitDispatcher.INITIALIZING))) {
                try {{
                    pppp.lazystream.Stream.longNats = pppp.lazystream.Stream.longNats((long)(0L));
                }}catch (java.lang.Throwable exc$52730) {
                    pppp.lazystream.Stream.exception$longNats = new x10.lang.ExceptionInInitializer(exc$52730);
                    pppp.lazystream.Stream.initStatus$longNats.set((int)(x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED));
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    x10.runtime.impl.java.InitDispatcher.notifyInitialized();
                    throw pppp.lazystream.Stream.exception$longNats;
                }
                if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                      ((boolean) true)) {
                    x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Doing static initialization for field: pppp.lazystream.Stream.longNats")));
                }
                pppp.lazystream.Stream.initStatus$longNats.set((int)(x10.runtime.impl.java.InitDispatcher.INITIALIZED));
                x10.runtime.impl.java.InitDispatcher.lockInitialized();
                x10.runtime.impl.java.InitDispatcher.notifyInitialized();
            } else {
                if (pppp.lazystream.Stream.initStatus$longNats.get() <
                    x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                    x10.runtime.impl.java.InitDispatcher.lockInitialized();
                    while (pppp.lazystream.Stream.initStatus$longNats.get() <
                           x10.runtime.impl.java.InitDispatcher.INITIALIZED) {
                        x10.runtime.impl.java.InitDispatcher.awaitInitialized();
                    }
                    x10.runtime.impl.java.InitDispatcher.unlockInitialized();
                    if (((int) pppp.lazystream.Stream.initStatus$longNats.get()) ==
                        ((int) x10.runtime.impl.java.InitDispatcher.EXCEPTION_RAISED)) {
                        if (((boolean) x10.runtime.impl.java.InitDispatcher.TRACE_STATIC_INIT) ==
                              ((boolean) true)) {
                            x10.runtime.impl.java.InitDispatcher.printStaticInitMessage(((java.lang.String)("Rethrowing ExceptionInInitializer for field: pppp.lazystream.Stream.longNats")));
                        }
                        throw pppp.lazystream.Stream.exception$longNats;
                    }
                }
            }
            return pppp.lazystream.Stream.longNats;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$90<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$90> $RTT = x10.rtt.StaticFunType.<$Closure$90> make(
            $Closure$90.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$90<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$90.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$90 $_obj = new pppp.lazystream.Stream.$Closure$90((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$90(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$90.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$90 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52485 =
                      ((x10.core.fun.Fun_0_0<pppp.lazystream.Stream<$T>>)this.
                                                                           x).$apply$G();
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52486 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)t52485).$apply());
                    
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52486;
                }
                
                public x10.core.fun.Fun_0_0<pppp.lazystream.Stream<$T>> x;
                
                public $Closure$90(final x10.rtt.Type $T,
                                   final x10.core.fun.Fun_0_0<pppp.lazystream.Stream<$T>> x, __0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$Closure$90$$T$2$2 $dummy) {pppp.lazystream.Stream.$Closure$90.$initParams(this, $T);
                                                                                                                                                                                  {
                                                                                                                                                                                     ((pppp.lazystream.Stream.$Closure$90<$T>)this).x = ((x10.core.fun.Fun_0_0)(x));
                                                                                                                                                                                 }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$Closure$90$$T$2$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$91<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$91> $RTT = x10.rtt.StaticFunType.<$Closure$91> make(
            $Closure$91.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$91<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$91.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.t = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$91 $_obj = new pppp.lazystream.Stream.$Closure$91((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (t instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.t);
                } else {
                $serializer.write(this.t);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$91(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$91.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$91 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52489 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$T>((java.lang.System[]) null, $T).pppp$lazystream$Cons$$init$S(this.
                                                                                                                                         t,
                                                                                                                                       this.
                                                                                                                                         out$$, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 51 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52489;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                public $T t;
                
                public $Closure$91(final x10.rtt.Type $T,
                                   final pppp.lazystream.Stream<$T> out$$,
                                   final $T t, __0$1pppp$lazystream$Stream$$Closure$91$$T$2__1pppp$lazystream$Stream$$Closure$91$$T $dummy) {pppp.lazystream.Stream.$Closure$91.$initParams(this, $T);
                                                                                                                                                  {
                                                                                                                                                     ((pppp.lazystream.Stream.$Closure$91<$T>)this).out$$ = out$$;
                                                                                                                                                     ((pppp.lazystream.Stream.$Closure$91<$T>)this).t = (($T)(t));
                                                                                                                                                 }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$91$$T$2__1pppp$lazystream$Stream$$Closure$91$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$92<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$92> $RTT = x10.rtt.StaticFunType.<$Closure$92> make(
            $Closure$92.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$92<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$92.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$92 $_obj = new pppp.lazystream.Stream.$Closure$92((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$92(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$92.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$92 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52492 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       x).$apply());
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52494 =
                      (($T)(((pppp.lazystream.Cons<$T>)t52492).
                              h));
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52493 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52495 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52493).
                                                  t));
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52496 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$T>((java.lang.System[]) null, $T).pppp$lazystream$Cons$$init$S(t52494,
                                                                                                                                       t52495, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 58 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52496;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                public pppp.lazystream.Stream<$T> x;
                
                public $Closure$92(final x10.rtt.Type $T,
                                   final pppp.lazystream.Stream<$T> out$$,
                                   final pppp.lazystream.Stream<$T> x, __0$1pppp$lazystream$Stream$$Closure$92$$T$2__1$1pppp$lazystream$Stream$$Closure$92$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$92.$initParams(this, $T);
                                                                                                                                                                              {
                                                                                                                                                                                 ((pppp.lazystream.Stream.$Closure$92<$T>)this).out$$ = out$$;
                                                                                                                                                                                 ((pppp.lazystream.Stream.$Closure$92<$T>)this).x = ((pppp.lazystream.Stream)(x));
                                                                                                                                                                             }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$92$$T$2__1$1pppp$lazystream$Stream$$Closure$92$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$93<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$93> $RTT = x10.rtt.StaticFunType.<$Closure$93> make(
            $Closure$93.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$93<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$93.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.f = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$93 $_obj = new pppp.lazystream.Stream.$Closure$93((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (f instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.f);
                } else {
                $serializer.write(this.f);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$93(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$93.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$93 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52499 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52500 =
                      (($T)(((pppp.lazystream.Cons<$T>)t52499).
                              h));
                    
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52502 =
                      (($T)((($T)
                              ((x10.core.fun.Fun_0_1<$T,$T>)this.
                                                              f).$apply(t52500,$T))));
                    
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52501 =
                      ((pppp.lazystream.Stream<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).cdr());
                    
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52503 =
                      ((pppp.lazystream.Stream<$T>)
                        ((pppp.lazystream.Stream<$T>)t52501).$apply__0$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2(((x10.core.fun.Fun_0_1)(this.
                                                                                                                                                         f))));
                    
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52504 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$T>((java.lang.System[]) null, $T).pppp$lazystream$Cons$$init$S(t52502,
                                                                                                                                       t52503, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 64 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52504;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                public x10.core.fun.Fun_0_1<$T,$T> f;
                
                public $Closure$93(final x10.rtt.Type $T,
                                   final pppp.lazystream.Stream<$T> out$$,
                                   final x10.core.fun.Fun_0_1<$T,$T> f, __0$1pppp$lazystream$Stream$$Closure$93$$T$2__1$1pppp$lazystream$Stream$$Closure$93$$T$3pppp$lazystream$Stream$$Closure$93$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$93.$initParams(this, $T);
                                                                                                                                                                                                                      {
                                                                                                                                                                                                                         ((pppp.lazystream.Stream.$Closure$93<$T>)this).out$$ = out$$;
                                                                                                                                                                                                                         ((pppp.lazystream.Stream.$Closure$93<$T>)this).f = ((x10.core.fun.Fun_0_1)(f));
                                                                                                                                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$93$$T$2__1$1pppp$lazystream$Stream$$Closure$93$$T$3pppp$lazystream$Stream$$Closure$93$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$94<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$94> $RTT = x10.rtt.StaticFunType.<$Closure$94> make(
            $Closure$94.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$94<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$94.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$94 $_obj = new pppp.lazystream.Stream.$Closure$94((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$94(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$94.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply__0pppp$lazystream$Stream$$Closure$94$$T$G(($T)a1);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$94 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply__0pppp$lazystream$Stream$$Closure$94$$T$G(
                  final $T y){
                    
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52507 =
                      (($T)((($T)
                              ((x10.lang.Arithmetic<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.lang.Arithmetic.$RTT, $T),this.
                                                                                                                                                x)).$times((($T)(y)),$T))));
                    
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52507;
                }
                
                public $T x;
                
                public $Closure$94(final x10.rtt.Type $T,
                                   final $T x, __0pppp$lazystream$Stream$$Closure$94$$T $dummy) {pppp.lazystream.Stream.$Closure$94.$initParams(this, $T);
                                                                                                      {
                                                                                                         ((pppp.lazystream.Stream.$Closure$94<$T>)this).x = (($T)(x));
                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __0pppp$lazystream$Stream$$Closure$94$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$95<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$95> $RTT = x10.rtt.StaticFunType.<$Closure$95> make(
            $Closure$95.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$95<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$95.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$95 $_obj = new pppp.lazystream.Stream.$Closure$95((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$95(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$95.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply__0pppp$lazystream$Stream$$Closure$95$$T$G(($T)a1);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$95 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply__0pppp$lazystream$Stream$$Closure$95$$T$G(
                  final $T y){
                    
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52510 =
                      (($T)((($T)
                              ((x10.lang.Arithmetic<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.lang.Arithmetic.$RTT, $T),this.
                                                                                                                                                x)).$over((($T)(y)),$T))));
                    
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52510;
                }
                
                public $T x;
                
                public $Closure$95(final x10.rtt.Type $T,
                                   final $T x, __0pppp$lazystream$Stream$$Closure$95$$T $dummy) {pppp.lazystream.Stream.$Closure$95.$initParams(this, $T);
                                                                                                      {
                                                                                                         ((pppp.lazystream.Stream.$Closure$95<$T>)this).x = (($T)(x));
                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __0pppp$lazystream$Stream$$Closure$95$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$96<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$96> $RTT = x10.rtt.StaticFunType.<$Closure$96> make(
            $Closure$96.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$96<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$96.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$96 $_obj = new pppp.lazystream.Stream.$Closure$96((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$96(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$96.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply__0pppp$lazystream$Stream$$Closure$96$$T$G(($T)a1);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$96 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply__0pppp$lazystream$Stream$$Closure$96$$T$G(
                  final $T y){
                    
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52513 =
                      (($T)((($T)
                              ((x10.lang.Arithmetic<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.lang.Arithmetic.$RTT, $T),this.
                                                                                                                                                x)).$plus((($T)(y)),$T))));
                    
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52513;
                }
                
                public $T x;
                
                public $Closure$96(final x10.rtt.Type $T,
                                   final $T x, __0pppp$lazystream$Stream$$Closure$96$$T $dummy) {pppp.lazystream.Stream.$Closure$96.$initParams(this, $T);
                                                                                                      {
                                                                                                         ((pppp.lazystream.Stream.$Closure$96<$T>)this).x = (($T)(x));
                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __0pppp$lazystream$Stream$$Closure$96$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$97<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$97> $RTT = x10.rtt.StaticFunType.<$Closure$97> make(
            $Closure$97.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$97<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$97.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.x = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$97 $_obj = new pppp.lazystream.Stream.$Closure$97((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (x instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.x);
                } else {
                $serializer.write(this.x);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$97(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$97.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply__0pppp$lazystream$Stream$$Closure$97$$T$G(($T)a1);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$97 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply__0pppp$lazystream$Stream$$Closure$97$$T$G(
                  final $T y){
                    
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52516 =
                      (($T)((($T)
                              ((x10.lang.Arithmetic<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.lang.Arithmetic.$RTT, $T),this.
                                                                                                                                                x)).$minus((($T)(y)),$T))));
                    
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52516;
                }
                
                public $T x;
                
                public $Closure$97(final x10.rtt.Type $T,
                                   final $T x, __0pppp$lazystream$Stream$$Closure$97$$T $dummy) {pppp.lazystream.Stream.$Closure$97.$initParams(this, $T);
                                                                                                      {
                                                                                                         ((pppp.lazystream.Stream.$Closure$97<$T>)this).x = (($T)(x));
                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __0pppp$lazystream$Stream$$Closure$97$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$98<$T, $U, $V>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$98> $RTT = x10.rtt.StaticFunType.<$Closure$98> make(
            $Closure$98.class, 3, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(2)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;if (i ==1)return $U;if (i ==2)return $V;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T, $U, $V> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$98<$T, $U, $V> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$98.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.$U = (x10.rtt.Type) $deserializer.readRef();
                $_obj.$V = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.o = $deserializer.readRef();
                $_obj.f = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$98 $_obj = new pppp.lazystream.Stream.$Closure$98((java.lang.System[]) null, (x10.rtt.Type) null, (x10.rtt.Type) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                $serializer.write((x10.serialization.X10JavaSerializable) this.$U);
                $serializer.write((x10.serialization.X10JavaSerializable) this.$V);
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                if (o instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.o);
                } else {
                $serializer.write(this.o);
                }
                if (f instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.f);
                } else {
                $serializer.write(this.f);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$98(final java.lang.System[] $dummy, final x10.rtt.Type $T, final x10.rtt.Type $U, final x10.rtt.Type $V) { 
            pppp.lazystream.Stream.$Closure$98.$initParams(this, $T, $U, $V);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                private x10.rtt.Type $U;
                private x10.rtt.Type $V;
                // initializer of type parameters
                public static void $initParams(final $Closure$98 $this, final x10.rtt.Type $T, final x10.rtt.Type $U, final x10.rtt.Type $V) {
                $this.$T = $T;
                $this.$U = $U;
                $this.$V = $V;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons tc =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons oc =
                      ((pppp.lazystream.Cons<$U>)
                        ((pppp.lazystream.Stream<$U>)this.
                                                       o).$apply());
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52519 =
                      (($T)(((pppp.lazystream.Cons<$T>)tc).
                              h));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $U t52520 =
                      (($U)(((pppp.lazystream.Cons<$U>)oc).
                              h));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $V t52523 =
                      (($V)((($V)
                              ((x10.core.fun.Fun_0_2<$T,$U,$V>)this.
                                                                 f).$apply(t52519,$T,
                                                                           t52520,$U))));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52521 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)tc).
                                                  t));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52522 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$U>)oc).
                                                  t));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52524 =
                      ((pppp.lazystream.Stream<$V>)
                        ((pppp.lazystream.Stream<$T>)t52521).<$U,
                      $V> $apply__0$1pppp$lazystream$Stream$$U$2__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$U$3pppp$lazystream$Stream$$V$2($U, $V, ((pppp.lazystream.Stream)(t52522)),
                                                                                                                                                       ((x10.core.fun.Fun_0_2)(this.
                                                                                                                                                                                 f))));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52525 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$V>((java.lang.System[]) null, $V).pppp$lazystream$Cons$$init$S(t52523,
                                                                                                                                       t52524, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52525;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                public pppp.lazystream.Stream<$U> o;
                public x10.core.fun.Fun_0_2<$T,$U,$V> f;
                
                public $Closure$98(final x10.rtt.Type $T,
                                   final x10.rtt.Type $U,
                                   final x10.rtt.Type $V,
                                   final pppp.lazystream.Stream<$T> out$$,
                                   final pppp.lazystream.Stream<$U> o,
                                   final x10.core.fun.Fun_0_2<$T,$U,$V> f, __0$1pppp$lazystream$Stream$$Closure$98$$T$2__1$1pppp$lazystream$Stream$$Closure$98$$U$2__2$1pppp$lazystream$Stream$$Closure$98$$T$3pppp$lazystream$Stream$$Closure$98$$U$3pppp$lazystream$Stream$$Closure$98$$V$2 $dummy) {pppp.lazystream.Stream.$Closure$98.$initParams(this, $T, $U, $V);
                                                                                                                                                                                                                                                                                                            {
                                                                                                                                                                                                                                                                                                               ((pppp.lazystream.Stream.$Closure$98<$T, $U, $V>)this).out$$ = out$$;
                                                                                                                                                                                                                                                                                                               ((pppp.lazystream.Stream.$Closure$98<$T, $U, $V>)this).o = ((pppp.lazystream.Stream)(o));
                                                                                                                                                                                                                                                                                                               ((pppp.lazystream.Stream.$Closure$98<$T, $U, $V>)this).f = ((x10.core.fun.Fun_0_2)(f));
                                                                                                                                                                                                                                                                                                           }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$98$$T$2__1$1pppp$lazystream$Stream$$Closure$98$$U$2__2$1pppp$lazystream$Stream$$Closure$98$$T$3pppp$lazystream$Stream$$Closure$98$$U$3pppp$lazystream$Stream$$Closure$98$$V$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$99<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$99> $RTT = x10.rtt.StaticFunType.<$Closure$99> make(
            $Closure$99.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$99<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$99.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$99 $_obj = new pppp.lazystream.Stream.$Closure$99((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                
            }
            
            // constructor just for allocation
            public $Closure$99(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$99.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1,a2:Z2):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply__0pppp$lazystream$Stream$$Closure$99$$T__1pppp$lazystream$Stream$$Closure$99$$T$G(($T)a1, ($T)a2);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$99 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply__0pppp$lazystream$Stream$$Closure$99$$T__1pppp$lazystream$Stream$$Closure$99$$T$G(
                  final $T a,
                  final $T b){
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52528 =
                      (($T)((($T)
                              ((x10.lang.Arithmetic<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.lang.Arithmetic.$RTT, $T),a)).$times((($T)(b)),$T))));
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52528;
                }
                
                public $Closure$99(final x10.rtt.Type $T) {pppp.lazystream.Stream.$Closure$99.$initParams(this, $T);
                                                                {
                                                                   
                                                               }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$100<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$100> $RTT = x10.rtt.StaticFunType.<$Closure$100> make(
            $Closure$100.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$100<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$100.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$100 $_obj = new pppp.lazystream.Stream.$Closure$100((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                
            }
            
            // constructor just for allocation
            public $Closure$100(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$100.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1,a2:Z2):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply__0pppp$lazystream$Stream$$Closure$100$$T__1pppp$lazystream$Stream$$Closure$100$$T$G(($T)a1, ($T)a2);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$100 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply__0pppp$lazystream$Stream$$Closure$100$$T__1pppp$lazystream$Stream$$Closure$100$$T$G(
                  final $T a,
                  final $T b){
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52531 =
                      (($T)((($T)
                              ((x10.lang.Arithmetic<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.lang.Arithmetic.$RTT, $T),a)).$over((($T)(b)),$T))));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52531;
                }
                
                public $Closure$100(final x10.rtt.Type $T) {pppp.lazystream.Stream.$Closure$100.$initParams(this, $T);
                                                                 {
                                                                    
                                                                }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$101<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$101> $RTT = x10.rtt.StaticFunType.<$Closure$101> make(
            $Closure$101.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$101<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$101.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$101 $_obj = new pppp.lazystream.Stream.$Closure$101((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                
            }
            
            // constructor just for allocation
            public $Closure$101(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$101.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1,a2:Z2):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply__0pppp$lazystream$Stream$$Closure$101$$T__1pppp$lazystream$Stream$$Closure$101$$T$G(($T)a1, ($T)a2);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$101 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply__0pppp$lazystream$Stream$$Closure$101$$T__1pppp$lazystream$Stream$$Closure$101$$T$G(
                  final $T a,
                  final $T b){
                    
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52534 =
                      (($T)((($T)
                              ((x10.lang.Arithmetic<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.lang.Arithmetic.$RTT, $T),a)).$plus((($T)(b)),$T))));
                    
//#line 88 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52534;
                }
                
                public $Closure$101(final x10.rtt.Type $T) {pppp.lazystream.Stream.$Closure$101.$initParams(this, $T);
                                                                 {
                                                                    
                                                                }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$102<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$102> $RTT = x10.rtt.StaticFunType.<$Closure$102> make(
            $Closure$102.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(0))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$102<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$102.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$102 $_obj = new pppp.lazystream.Stream.$Closure$102((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                
            }
            
            // constructor just for allocation
            public $Closure$102(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$102.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1,a2:Z2):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply__0pppp$lazystream$Stream$$Closure$102$$T__1pppp$lazystream$Stream$$Closure$102$$T$G(($T)a1, ($T)a2);
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$102 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public $T
                  $apply__0pppp$lazystream$Stream$$Closure$102$$T__1pppp$lazystream$Stream$$Closure$102$$T$G(
                  final $T a,
                  final $T b){
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52537 =
                      (($T)((($T)
                              ((x10.lang.Arithmetic<$T>)x10.rtt.Types.conversion(x10.rtt.ParameterizedType.make(x10.lang.Arithmetic.$RTT, $T),a)).$minus((($T)(b)),$T))));
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52537;
                }
                
                public $Closure$102(final x10.rtt.Type $T) {pppp.lazystream.Stream.$Closure$102.$initParams(this, $T);
                                                                 {
                                                                    
                                                                }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$103<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$103> $RTT = x10.rtt.StaticFunType.<$Closure$103> make(
            $Closure$103.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$103<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$103.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.a = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$103 $_obj = new pppp.lazystream.Stream.$Closure$103((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (a instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.a);
                } else {
                $serializer.write(this.a);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$103(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$103.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$103 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons ac =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       a).$apply());
                    
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons sc =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52543 =
                      (($T)(((pppp.lazystream.Cons<$T>)ac).
                              h));
                    
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52544 =
                      (($T)(((pppp.lazystream.Cons<$T>)sc).
                              h));
                    
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T m =
                      (($T)(((pppp.lazystream.Stream<$T>)this.
                                                           out$$).<$T> min__0pppp$lazystream$Stream$$T52446__1pppp$lazystream$Stream$$T52446$G($T, (($T)(t52543)),
                                                                                                                                               (($T)(t52544)))));
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52545 =
                      (($T)(((pppp.lazystream.Cons<$T>)sc).
                              h));
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52546 =
                      x10.rtt.Equality.equalsequals((m),(t52545));
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
pppp.lazystream.Stream t52547 =
                       null;
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52546) {
                        
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52547 = ((pppp.lazystream.Cons<$T>)sc).
                                                                                                                                                                            t;
                    } else {
                        
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52547 = this.
                                                                                                                                                                            out$$;
                    }
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52551 =
                      t52547;
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52548 =
                      (($T)(((pppp.lazystream.Cons<$T>)ac).
                              h));
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52549 =
                      x10.rtt.Equality.equalsequals((m),(t52548));
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
pppp.lazystream.Stream t52550 =
                       null;
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (t52549) {
                        
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52550 = ((pppp.lazystream.Cons<$T>)ac).
                                                                                                                                                                            t;
                    } else {
                        
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
t52550 = this.
                                                                                                                                                                            a;
                    }
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52552 =
                      t52550;
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52553 =
                      ((pppp.lazystream.Stream<$T>)
                        ((pppp.lazystream.Stream<$T>)t52551).$inv_caret__0$1pppp$lazystream$Stream$$T$2(((pppp.lazystream.Stream)(t52552))));
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52554 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$T>((java.lang.System[]) null, $T).pppp$lazystream$Cons$$init$S((($T)(m)),
                                                                                                                                       t52553, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52554;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                public pppp.lazystream.Stream<$T> a;
                
                public $Closure$103(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$,
                                    final pppp.lazystream.Stream<$T> a, __0$1pppp$lazystream$Stream$$Closure$103$$T$2__1$1pppp$lazystream$Stream$$Closure$103$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$103.$initParams(this, $T);
                                                                                                                                                                                 {
                                                                                                                                                                                    ((pppp.lazystream.Stream.$Closure$103<$T>)this).out$$ = out$$;
                                                                                                                                                                                    ((pppp.lazystream.Stream.$Closure$103<$T>)this).a = ((pppp.lazystream.Stream)(a));
                                                                                                                                                                                }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$103$$T$2__1$1pppp$lazystream$Stream$$Closure$103$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$104<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$104> $RTT = x10.rtt.StaticFunType.<$Closure$104> make(
            $Closure$104.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$104<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$104.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.f = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$104 $_obj = new pppp.lazystream.Stream.$Closure$104((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (f instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.f);
                } else {
                $serializer.write(this.f);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$104(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$104.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$104 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 106 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
pppp.lazystream.Cons sc =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
while (true) {
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52557 =
                          sc;
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52558 =
                          (($T)(((pppp.lazystream.Cons<$T>)t52557).
                                  h));
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52559 =
                          x10.core.Boolean.$unbox(((x10.core.fun.Fun_0_1<$T,x10.core.Boolean>)this.
                                                                                                f).$apply(t52558,$T));
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final boolean t52563 =
                          !(t52559);
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
if (!(t52563)) {
                            
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
break;
                        }
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52693 =
                          sc;
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52694 =
                          ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52693).
                                                      t));
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52695 =
                          ((pppp.lazystream.Cons<$T>)
                            ((pppp.lazystream.Stream<$T>)t52694).$apply());
                        
//#line 107 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
sc = t52695;
                    }
                    
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52564 =
                      sc;
                    
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52567 =
                      (($T)(((pppp.lazystream.Cons<$T>)t52564).
                              h));
                    
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52565 =
                      sc;
                    
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52566 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52565).
                                                  t));
                    
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52568 =
                      ((pppp.lazystream.Stream<$T>)
                        ((pppp.lazystream.Stream<$T>)t52566).$percent__0$1pppp$lazystream$Stream$$T$3x10$lang$Boolean$2(((x10.core.fun.Fun_0_1)(this.
                                                                                                                                                  f))));
                    
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52569 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$T>((java.lang.System[]) null, $T).pppp$lazystream$Cons$$init$S(t52567,
                                                                                                                                       t52568, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 108 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52569;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                public x10.core.fun.Fun_0_1<$T,x10.core.Boolean> f;
                
                public $Closure$104(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$,
                                    final x10.core.fun.Fun_0_1<$T,x10.core.Boolean> f, __0$1pppp$lazystream$Stream$$Closure$104$$T$2__1$1pppp$lazystream$Stream$$Closure$104$$T$3x10$lang$Boolean$2 $dummy) {pppp.lazystream.Stream.$Closure$104.$initParams(this, $T);
                                                                                                                                                                                                                  {
                                                                                                                                                                                                                     ((pppp.lazystream.Stream.$Closure$104<$T>)this).out$$ = out$$;
                                                                                                                                                                                                                     ((pppp.lazystream.Stream.$Closure$104<$T>)this).f = ((x10.core.fun.Fun_0_1)(f));
                                                                                                                                                                                                                 }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$104$$T$2__1$1pppp$lazystream$Stream$$Closure$104$$T$3x10$lang$Boolean$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$105<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$105> $RTT = x10.rtt.StaticFunType.<$Closure$105> make(
            $Closure$105.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$105<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$105.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$105 $_obj = new pppp.lazystream.Stream.$Closure$105((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$105(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$105.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Stream
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$105 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Stream
                  $apply(
                  ){
                    
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52580 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52581 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52580).
                                                  t));
                    
//#line 121 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52581;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                
                public $Closure$105(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$, __0$1pppp$lazystream$Stream$$Closure$105$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$105.$initParams(this, $T);
                                                                                                                                        {
                                                                                                                                           ((pppp.lazystream.Stream.$Closure$105<$T>)this).out$$ = out$$;
                                                                                                                                       }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$105$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$106<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$106> $RTT = x10.rtt.StaticFunType.<$Closure$106> make(
            $Closure$106.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$106<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$106.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$106 $_obj = new pppp.lazystream.Stream.$Closure$106((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$106(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$106.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Stream
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$106 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Stream
                  $apply(
                  ){
                    
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52584 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52585 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52584).
                                                  t));
                    
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52586 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)t52585).$apply());
                    
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52587 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52586).
                                                  t));
                    
//#line 122 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52587;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                
                public $Closure$106(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$, __0$1pppp$lazystream$Stream$$Closure$106$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$106.$initParams(this, $T);
                                                                                                                                        {
                                                                                                                                           ((pppp.lazystream.Stream.$Closure$106<$T>)this).out$$ = out$$;
                                                                                                                                       }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$106$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$107<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$107> $RTT = x10.rtt.StaticFunType.<$Closure$107> make(
            $Closure$107.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$107<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$107.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$107 $_obj = new pppp.lazystream.Stream.$Closure$107((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$107(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$107.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Stream
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$107 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Stream
                  $apply(
                  ){
                    
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52590 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52591 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52590).
                                                  t));
                    
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52592 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)t52591).$apply());
                    
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52593 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52592).
                                                  t));
                    
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52594 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)t52593).$apply());
                    
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52595 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52594).
                                                  t));
                    
//#line 123 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52595;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                
                public $Closure$107(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$, __0$1pppp$lazystream$Stream$$Closure$107$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$107.$initParams(this, $T);
                                                                                                                                        {
                                                                                                                                           ((pppp.lazystream.Stream.$Closure$107<$T>)this).out$$ = out$$;
                                                                                                                                       }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$107$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$108<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$108> $RTT = x10.rtt.StaticFunType.<$Closure$108> make(
            $Closure$108.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$108<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$108.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$108 $_obj = new pppp.lazystream.Stream.$Closure$108((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$108(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$108.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Stream
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$108 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Stream
                  $apply(
                  ){
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52598 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52599 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52598).
                                                  t));
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52600 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)t52599).$apply());
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52601 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52600).
                                                  t));
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52602 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)t52601).$apply());
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52603 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52602).
                                                  t));
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52604 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)t52603).$apply());
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52605 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52604).
                                                  t));
                    
//#line 124 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52605;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                
                public $Closure$108(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$, __0$1pppp$lazystream$Stream$$Closure$108$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$108.$initParams(this, $T);
                                                                                                                                        {
                                                                                                                                           ((pppp.lazystream.Stream.$Closure$108<$T>)this).out$$ = out$$;
                                                                                                                                       }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$108$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$109<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$109> $RTT = x10.rtt.StaticFunType.<$Closure$109> make(
            $Closure$109.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$109<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$109.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$109 $_obj = new pppp.lazystream.Stream.$Closure$109((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$109(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$109.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$109 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52608 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52610 =
                      (($T)(((pppp.lazystream.Cons<$T>)t52608).
                              h));
                    
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52609 =
                      ((pppp.lazystream.Stream<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).cddr());
                    
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52611 =
                      ((pppp.lazystream.Stream<$T>)
                        ((pppp.lazystream.Stream<$T>)t52609).even());
                    
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52612 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$T>((java.lang.System[]) null, $T).pppp$lazystream$Cons$$init$S(t52610,
                                                                                                                                       t52611, (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 129 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52612;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                
                public $Closure$109(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$, __0$1pppp$lazystream$Stream$$Closure$109$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$109.$initParams(this, $T);
                                                                                                                                        {
                                                                                                                                           ((pppp.lazystream.Stream.$Closure$109<$T>)this).out$$ = out$$;
                                                                                                                                       }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$109$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$110<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$110> $RTT = x10.rtt.StaticFunType.<$Closure$110> make(
            $Closure$110.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$110<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$110.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.a = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$110 $_obj = new pppp.lazystream.Stream.$Closure$110((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (a instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.a);
                } else {
                $serializer.write(this.a);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$110(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$110.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Stream
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$110 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Stream
                  $apply(
                  ){
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52618 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52619 =
                      ((pppp.lazystream.Stream)(((pppp.lazystream.Cons<$T>)t52618).
                                                  t));
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52620 =
                      ((pppp.lazystream.Stream<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       a).zip__0$1pppp$lazystream$Stream$$T$2(((pppp.lazystream.Stream)(t52619))));
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52620;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                public pppp.lazystream.Stream<$T> a;
                
                public $Closure$110(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$,
                                    final pppp.lazystream.Stream<$T> a, __0$1pppp$lazystream$Stream$$Closure$110$$T$2__1$1pppp$lazystream$Stream$$Closure$110$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$110.$initParams(this, $T);
                                                                                                                                                                                 {
                                                                                                                                                                                    ((pppp.lazystream.Stream.$Closure$110<$T>)this).out$$ = out$$;
                                                                                                                                                                                    ((pppp.lazystream.Stream.$Closure$110<$T>)this).a = ((pppp.lazystream.Stream)(a));
                                                                                                                                                                                }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$110$$T$2__1$1pppp$lazystream$Stream$$Closure$110$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$111<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$111> $RTT = x10.rtt.StaticFunType.<$Closure$111> make(
            $Closure$111.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$111<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$111.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.a = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$111 $_obj = new pppp.lazystream.Stream.$Closure$111((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (a instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.a);
                } else {
                $serializer.write(this.a);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$111(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$111.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$111 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52617 =
                      ((pppp.lazystream.Cons<$T>)
                        ((pppp.lazystream.Stream<$T>)this.
                                                       out$$).$apply());
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52622 =
                      (($T)(((pppp.lazystream.Cons<$T>)t52617).
                              h));
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52621 =
                      ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$110<$T>($T, ((pppp.lazystream.Stream)(this.
                                                                                                                          out$$)),
                                                                                          ((pppp.lazystream.Stream)(this.
                                                                                                                      a)), (pppp.lazystream.Stream.$Closure$110.__0$1pppp$lazystream$Stream$$Closure$110$$T$2__1$1pppp$lazystream$Stream$$Closure$110$$T$2) null)));
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52623 =
                      ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52621)))));
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52624 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$T>((java.lang.System[]) null, $T).pppp$lazystream$Cons$$init$S(t52622,
                                                                                                                                       ((pppp.lazystream.Stream)(t52623)), (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 140 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52624;
                }
                
                public pppp.lazystream.Stream<$T> out$$;
                public pppp.lazystream.Stream<$T> a;
                
                public $Closure$111(final x10.rtt.Type $T,
                                    final pppp.lazystream.Stream<$T> out$$,
                                    final pppp.lazystream.Stream<$T> a, __0$1pppp$lazystream$Stream$$Closure$111$$T$2__1$1pppp$lazystream$Stream$$Closure$111$$T$2 $dummy) {pppp.lazystream.Stream.$Closure$111.$initParams(this, $T);
                                                                                                                                                                                 {
                                                                                                                                                                                    ((pppp.lazystream.Stream.$Closure$111<$T>)this).out$$ = out$$;
                                                                                                                                                                                    ((pppp.lazystream.Stream.$Closure$111<$T>)this).a = ((pppp.lazystream.Stream)(a));
                                                                                                                                                                                }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$111$$T$2__1$1pppp$lazystream$Stream$$Closure$111$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$112<$T, $U>  extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$112> $RTT = x10.rtt.StaticFunType.<$Closure$112> make(
            $Closure$112.class, 2, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(1), x10.rtt.ParameterizedType.make(x10.util.Pair.$RTT, x10.rtt.UnresolvedType.PARAM(0), x10.rtt.UnresolvedType.PARAM(1)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;if (i ==1)return $U;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T, $U> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$112<$T, $U> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$112.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.$U = (x10.rtt.Type) $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$112 $_obj = new pppp.lazystream.Stream.$Closure$112((java.lang.System[]) null, (x10.rtt.Type) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                $serializer.write((x10.serialization.X10JavaSerializable) this.$U);
                
            }
            
            // constructor just for allocation
            public $Closure$112(final java.lang.System[] $dummy, final x10.rtt.Type $T, final x10.rtt.Type $U) { 
            pppp.lazystream.Stream.$Closure$112.$initParams(this, $T, $U);
            }
            // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1,a2:Z2):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply__0pppp$lazystream$Stream$$Closure$112$$T__1pppp$lazystream$Stream$$Closure$112$$U(($T)a1, ($U)a2);
            }
            
                private x10.rtt.Type $T;
                private x10.rtt.Type $U;
                // initializer of type parameters
                public static void $initParams(final $Closure$112 $this, final x10.rtt.Type $T, final x10.rtt.Type $U) {
                $this.$T = $T;
                $this.$U = $U;
                }
                
                
                public x10.util.Pair
                  $apply__0pppp$lazystream$Stream$$Closure$112$$T__1pppp$lazystream$Stream$$Closure$112$$U(
                  final $T t,
                  final $U u){
                    
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.util.Pair t52627 =
                      new x10.util.Pair<$T, $U>((java.lang.System[]) null, $T, $U).x10$util$Pair$$init$S((($T)(t)),
                                                                                                         (($U)(u)), (x10.util.Pair.__0x10$util$Pair$$T__1x10$util$Pair$$U) null);
                    
//#line 148 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52627;
                }
                
                public $Closure$112(final x10.rtt.Type $T,
                                    final x10.rtt.Type $U) {pppp.lazystream.Stream.$Closure$112.$initParams(this, $T, $U);
                                                                 {
                                                                    
                                                                }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$113<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$113> $RTT = x10.rtt.StaticFunType.<$Closure$113> make(
            $Closure$113.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Stream.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$113<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$113.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.f = $deserializer.readRef();
                $_obj.t = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$113 $_obj = new pppp.lazystream.Stream.$Closure$113((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (f instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.f);
                } else {
                $serializer.write(this.f);
                }
                if (t instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.t);
                } else {
                $serializer.write(this.t);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$113(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$113.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Stream
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$113 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Stream
                  $apply(
                  ){
                    
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final $T t52680 =
                      (($T)((($T)
                              ((x10.core.fun.Fun_0_1<$T,$T>)this.
                                                              f).$apply(this.
                                                                          t,$T))));
                    
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52681 =
                      pppp.lazystream.Stream.<$T> gen__0pppp$lazystream$Stream$$T__1$1pppp$lazystream$Stream$$T$3pppp$lazystream$Stream$$T$2($T, (($T)(t52680)),
                                                                                                                                             ((x10.core.fun.Fun_0_1)(this.
                                                                                                                                                                       f)));
                    
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52681;
                }
                
                public x10.core.fun.Fun_0_1<$T,$T> f;
                public $T t;
                
                public $Closure$113(final x10.rtt.Type $T,
                                    final x10.core.fun.Fun_0_1<$T,$T> f,
                                    final $T t, __0$1pppp$lazystream$Stream$$Closure$113$$T$3pppp$lazystream$Stream$$Closure$113$$T$2__1pppp$lazystream$Stream$$Closure$113$$T $dummy) {pppp.lazystream.Stream.$Closure$113.$initParams(this, $T);
                                                                                                                                                                                             {
                                                                                                                                                                                                ((pppp.lazystream.Stream.$Closure$113<$T>)this).f = ((x10.core.fun.Fun_0_1)(f));
                                                                                                                                                                                                ((pppp.lazystream.Stream.$Closure$113<$T>)this).t = (($T)(t));
                                                                                                                                                                                            }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$113$$T$3pppp$lazystream$Stream$$Closure$113$$T$2__1pppp$lazystream$Stream$$Closure$113$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$114<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$114> $RTT = x10.rtt.StaticFunType.<$Closure$114> make(
            $Closure$114.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.ParameterizedType.make(pppp.lazystream.Cons.$RTT, x10.rtt.UnresolvedType.PARAM(0)))}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$114<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$114.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.f = $deserializer.readRef();
                $_obj.t = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$114 $_obj = new pppp.lazystream.Stream.$Closure$114((java.lang.System[]) null, (x10.rtt.Type) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                $serializer.write((x10.serialization.X10JavaSerializable) this.$T);
                if (f instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.f);
                } else {
                $serializer.write(this.f);
                }
                if (t instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.t);
                } else {
                $serializer.write(this.t);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$114(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.lazystream.Stream.$Closure$114.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.lazystream.Cons
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$114 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public pppp.lazystream.Cons
                  $apply(
                  ){
                    
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final x10.core.fun.Fun_0_0 t52682 =
                      ((x10.core.fun.Fun_0_0)(new pppp.lazystream.Stream.$Closure$113<$T>($T, ((x10.core.fun.Fun_0_1)(this.
                                                                                                                        f)),
                                                                                          (($T)(this.
                                                                                                  t)), (pppp.lazystream.Stream.$Closure$113.__0$1pppp$lazystream$Stream$$Closure$113$$T$3pppp$lazystream$Stream$$Closure$113$$T$2__1pppp$lazystream$Stream$$Closure$113$$T) null)));
                    
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Stream t52683 =
                      ((pppp.lazystream.Stream)(pppp.lazystream.Stream.<$T> $implicit_convert__0$1pppp$lazystream$Stream$1pppp$lazystream$Stream$$T$2$2($T, ((x10.core.fun.Fun_0_0)(t52682)))));
                    
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final pppp.lazystream.Cons t52684 =
                      ((pppp.lazystream.Cons)(new pppp.lazystream.Cons<$T>((java.lang.System[]) null, $T).pppp$lazystream$Cons$$init$S(this.
                                                                                                                                         t,
                                                                                                                                       ((pppp.lazystream.Stream)(t52683)), (pppp.lazystream.Cons.__0pppp$lazystream$Cons$$T__1$1pppp$lazystream$Cons$$T$2) null)));
                    
//#line 187 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52684;
                }
                
                public x10.core.fun.Fun_0_1<$T,$T> f;
                public $T t;
                
                public $Closure$114(final x10.rtt.Type $T,
                                    final x10.core.fun.Fun_0_1<$T,$T> f,
                                    final $T t, __0$1pppp$lazystream$Stream$$Closure$114$$T$3pppp$lazystream$Stream$$Closure$114$$T$2__1pppp$lazystream$Stream$$Closure$114$$T $dummy) {pppp.lazystream.Stream.$Closure$114.$initParams(this, $T);
                                                                                                                                                                                             {
                                                                                                                                                                                                ((pppp.lazystream.Stream.$Closure$114<$T>)this).f = ((x10.core.fun.Fun_0_1)(f));
                                                                                                                                                                                                ((pppp.lazystream.Stream.$Closure$114<$T>)this).t = (($T)(t));
                                                                                                                                                                                            }}
                // synthetic type for parameter mangling
                public static final class __0$1pppp$lazystream$Stream$$Closure$114$$T$3pppp$lazystream$Stream$$Closure$114$$T$2__1pppp$lazystream$Stream$$Closure$114$$T {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$115 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$115> $RTT = x10.rtt.StaticFunType.<$Closure$115> make(
            $Closure$115.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.INT, x10.rtt.Types.INT)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$115 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$115.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$115 $_obj = new pppp.lazystream.Stream.$Closure$115((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$115(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return x10.core.Int.$box($apply$O(x10.core.Int.$unbox(a1)));
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public int $apply$I(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply$O(x10.core.Int.$unbox(a1));
            }
            
                
                public int
                  $apply$O(
                  final int n){
                    
//#line 192 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final int t52687 =
                      ((n) + (((int)(1))));
                    
//#line 192 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52687;
                }
                
                public $Closure$115() { {
                                               
                                           }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$116 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$116> $RTT = x10.rtt.StaticFunType.<$Closure$116> make(
            $Closure$116.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.LONG, x10.rtt.Types.LONG)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.lazystream.Stream.$Closure$116 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$116.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.lazystream.Stream.$Closure$116 $_obj = new pppp.lazystream.Stream.$Closure$116((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$116(final java.lang.System[] $dummy) { 
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
                  final long n){
                    
//#line 196 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
final long t52690 =
                      ((n) + (((long)(1L))));
                    
//#line 196 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/lazystream/Stream.x10"
return t52690;
                }
                
                public $Closure$116() { {
                                               
                                           }}
                
            }
            
        // synthetic type for parameter mangling
        public static final class __0$1pppp$lazystream$Cons$1pppp$lazystream$Stream$$T$2$2 {}
        
        }
        
        