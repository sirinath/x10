package pppp.util;


@x10.runtime.impl.java.X10Generated public class Monitor extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Monitor> $RTT = x10.rtt.NamedType.<Monitor> make(
    "pppp.util.Monitor", Monitor.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Monitor.class + " calling"); } 
        $_obj.lock = $deserializer.readRef();
        $_obj.threads = $deserializer.readRef();
        $_obj.size = $deserializer.readInt();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        pppp.util.Monitor $_obj = new pppp.util.Monitor((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        if (lock instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.lock);
        } else {
        $serializer.write(this.lock);
        }
        if (threads instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.threads);
        } else {
        $serializer.write(this.threads);
        }
        $serializer.write(this.size);
        
    }
    
    // constructor just for allocation
    public Monitor(final java.lang.System[] $dummy) { 
    }
    
        
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public x10.util.concurrent.Lock lock;
        
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public x10.core.Rail<x10.lang.Runtime.Worker> threads;
        
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public int size;
        
        
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public void
                                                                                                                                              lock(
                                                                                                                                              ){
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.util.concurrent.Lock t44647 =
              ((x10.util.concurrent.Lock)(lock));
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final boolean t44648 =
              t44647.tryLock();
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final boolean t44650 =
              !(t44648);
            
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
if (t44650) {
                
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
x10.lang.Runtime.increaseParallelism();
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.util.concurrent.Lock t44649 =
                  ((x10.util.concurrent.Lock)(lock));
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
t44649.lock();
                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
x10.lang.Runtime.decreaseParallelism((int)(1));
            }
        }
        
        
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public void
                                                                                                                                              unlock(
                                                                                                                                              ){
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.util.concurrent.Lock t44651 =
              ((x10.util.concurrent.Lock)(lock));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
t44651.unlock();
        }
        
        
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public static x10.core.fun.Fun_0_0<x10.core.Boolean> TRUE = ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$14()));
        
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public static x10.core.fun.Fun_0_0<pppp.util.Unit> NOTHING = ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$15()));
        
        
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public void
                                                                                                                                              awaken(
                                                                                                                                              ){
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44652 =
              ((x10.core.fun.Fun_0_0)(pppp.util.Monitor.TRUE));
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44653 =
              ((x10.core.fun.Fun_0_0)(pppp.util.Monitor.NOTHING));
            
//#line 37 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.<pppp.util.Unit> on__0$1x10$lang$Boolean$2__1$1pppp$util$Monitor$$T$2$G(pppp.util.Unit.$RTT, ((x10.core.fun.Fun_0_0)(t44652)),
                                                                                                                                                                                                                             ((x10.core.fun.Fun_0_0)(t44653)));
        }
        
        
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public void
                                                                                                                                              await__0$1x10$lang$Boolean$2(
                                                                                                                                              final x10.core.fun.Fun_0_0 cond){
            
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44654 =
              ((x10.core.fun.Fun_0_0)(pppp.util.Monitor.NOTHING));
            
//#line 41 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.<pppp.util.Unit> on__0$1x10$lang$Boolean$2__1$1pppp$util$Monitor$$T$2$G(pppp.util.Unit.$RTT, ((x10.core.fun.Fun_0_0)(cond)),
                                                                                                                                                                                                                             ((x10.core.fun.Fun_0_0)(t44654)));
        }
        
        
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public <$T> $T
                                                                                                                                              atomicBlock__0$1pppp$util$Monitor$$T$2$G(
                                                                                                                                              final x10.rtt.Type $T,
                                                                                                                                              final x10.core.fun.Fun_0_0 action){
            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44655 =
              ((x10.core.fun.Fun_0_0)(pppp.util.Monitor.TRUE));
            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final $T t44656 =
              (($T)(this.<$T> on__0$1x10$lang$Boolean$2__1$1pppp$util$Monitor$$T$2$G($T, ((x10.core.fun.Fun_0_0)(t44655)),
                                                                                     ((x10.core.fun.Fun_0_0)(action)))));
            
//#line 46 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return t44656;
        }
        
        
//#line 60 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
public <$T> $T
                                                                                                                                              on__0$1x10$lang$Boolean$2__1$1pppp$util$Monitor$$T$2$G(
                                                                                                                                              final x10.rtt.Type $T,
                                                                                                                                              final x10.core.fun.Fun_0_0 cond,
                                                                                                                                              final x10.core.fun.Fun_0_0 action){
            
//#line 61 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
try {{
                
//#line 62 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.lock();
                
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44660 =
                  ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$16<$T>($T, this,
                                                                                cond, (pppp.util.Monitor.$Closure$16.__1$1x10$lang$Boolean$2) null)));
                
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
pppp.util.Logger.debug__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t44660)));
                
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
while (true) {
                    
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final boolean t44661 =
                      x10.core.Boolean.$unbox(((x10.core.fun.Fun_0_0<x10.core.Boolean>)cond).$apply$G());
                    
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final boolean t44674 =
                      !(t44661);
                    
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
if (!(t44674)) {
                        
//#line 65 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
break;
                    }
                    
//#line 66 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.lang.Runtime.Worker thisWorker44718 =
                      x10.lang.Runtime.worker();
                    
//#line 67 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int s44719 =
                      size;
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.Rail t44720 =
                      ((x10.core.Rail)(threads));
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final pppp.util.Monitor x44721 =
                      ((pppp.util.Monitor)(this));
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
;
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44722 =
                      x44721.
                        size;
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44723 =
                      ((t44722) + (((int)(1))));
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44724 =
                      x44721.size = t44723;
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44725 =
                      ((t44724) - (((int)(1))));
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final long t44726 =
                      ((long)(((int)(t44725))));
                    
//#line 68 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
((x10.lang.Runtime.Worker[])t44720.value)[(int)t44726] = thisWorker44718;
                    
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
while (true) {
                        
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.Rail t44727 =
                          ((x10.core.Rail)(threads));
                        
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final long t44728 =
                          ((long)(((int)(s44719))));
                        
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.lang.Runtime.Worker t44729 =
                          ((x10.lang.Runtime.Worker[])t44727.value)[(int)t44728];
                        
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final boolean t44730 =
                          x10.rtt.Equality.equalsequals((t44729),(thisWorker44718));
                        
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
if (!(t44730)) {
                            
//#line 69 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
break;
                        }
                        
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44716 =
                          ((x10.core.fun.Fun_0_0)(pppp.util.Monitor.suspending));
                        
//#line 70 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
pppp.util.Logger.log__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t44716)));
                        
//#line 71 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.unlock();
                        
//#line 72 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
x10.lang.Runtime.Worker.park();
                        
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44717 =
                          ((x10.core.fun.Fun_0_0)(pppp.util.Monitor.retrying));
                        
//#line 73 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
pppp.util.Logger.debug__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t44717)));
                        
//#line 74 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.lock();
                    }
                }
                
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44678 =
                  ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$17<$T>($T, this,
                                                                                action, (pppp.util.Monitor.$Closure$17.__1$1pppp$util$Monitor$$Closure$17$$T$2) null)));
                
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
pppp.util.Logger.debug__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t44678)));
                
//#line 78 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final $T result =
                  (($T)(((x10.core.fun.Fun_0_0<$T>)action).$apply$G()));
                
//#line 80 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int m =
                  size;
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44682 =
                  ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$18<$T>($T, this,
                                                                                m)));
                
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
pppp.util.Logger.debug__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t44682)));
                
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
int i44756 =
                  0;
                
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
for (;
                                                                                                                                                         true;
                                                                                                                                                         ) {
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44757 =
                      i44756;
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final boolean t44758 =
                      ((t44757) < (((int)(m))));
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
if (!(t44758)) {
                        
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
break;
                    }
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final pppp.util.Monitor x44731 =
                      ((pppp.util.Monitor)(this));
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
;
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44732 =
                      x44731.
                        size;
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44733 =
                      ((t44732) - (((int)(1))));
                    
//#line 83 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
x44731.size = t44733;
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_1 t44734 =
                      ((x10.core.fun.Fun_0_1)(new pppp.util.Monitor.$Closure$19<$T>($T, this,
                                                                                    threads, (pppp.util.Monitor.$Closure$19.__1$1x10$lang$Runtime$Worker$2) null)));
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44746 =
                      i44756;
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
pppp.util.Logger.<x10.core.Int> debug__0$1pppp$util$Logger$$T$3x10$lang$String$2__1pppp$util$Logger$$T(x10.rtt.Types.INT, ((x10.core.fun.Fun_0_1)(t44734)),
                                                                                                                                                                                                                                                               x10.core.Int.$box(t44746));
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.Rail t44747 =
                      ((x10.core.Rail)(threads));
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44748 =
                      size;
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final long t44749 =
                      ((long)(((int)(t44748))));
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.lang.Runtime.Worker t44750 =
                      ((x10.lang.Runtime.Worker[])t44747.value)[(int)t44749];
                    
//#line 86 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
t44750.unpark();
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.Rail t44751 =
                      ((x10.core.Rail)(threads));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44752 =
                      size;
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final long t44753 =
                      ((long)(((int)(t44752))));
                    
//#line 87 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
((x10.lang.Runtime.Worker[])t44751.value)[(int)t44753] = null;
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44754 =
                      i44756;
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44755 =
                      ((t44754) + (((int)(1))));
                    
//#line 82 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
i44756 = t44755;
                }
                
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.fun.Fun_0_0 t44711 =
                  ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$20<$T>($T, this)));
                
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
pppp.util.Logger.debug__0$1x10$lang$String$2(((x10.core.fun.Fun_0_0)(t44711)));
                
//#line 90 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return result;
            }}finally {{
                  
//#line 92 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.unlock();
              }}
            }
        
        
//#line 95 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public static x10.core.fun.Fun_0_1<java.lang.String,java.lang.String> waking = ((x10.core.fun.Fun_0_1)(new pppp.util.Monitor.$Closure$21()));
        
//#line 96 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public static x10.core.fun.Fun_0_0<java.lang.String> trying = ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$22()));
        
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public static x10.core.fun.Fun_0_0<java.lang.String> retrying = ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$23()));
        
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public static x10.core.fun.Fun_0_0<java.lang.String> suspending = ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$24()));
        
//#line 99 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public static x10.core.fun.Fun_0_0<java.lang.String> acting = ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$25()));
        
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public static x10.core.fun.Fun_0_0<java.lang.String> finished = ((x10.core.fun.Fun_0_0)(new pppp.util.Monitor.$Closure$26()));
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public pppp.util.Monitor
                                                                                                                                              pppp$util$Monitor$$this$pppp$util$Monitor(
                                                                                                                                              ){
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return pppp.util.Monitor.this;
        }
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
// creation method for java code (1-phase java constructor)
        public Monitor(){this((java.lang.System[]) null);
                             pppp$util$Monitor$$init$S();}
        
        // constructor for non-virtual call
        final public pppp.util.Monitor pppp$util$Monitor$$init$S() { {
                                                                            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
;
                                                                            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"

                                                                            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.__fieldInitializers_pppp_util_Monitor();
                                                                        }
                                                                        return this;
                                                                        }
        
        
        
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final public void
                                                                                                                                              __fieldInitializers_pppp_util_Monitor(
                                                                                                                                              ){
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.util.concurrent.Lock t44712 =
              ((x10.util.concurrent.Lock)(new x10.util.concurrent.Lock()));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.lock = ((x10.util.concurrent.Lock)(t44712));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44713 =
              x10.lang.Runtime.get$MAX_THREADS();
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final long t44714 =
              ((long)(((int)(t44713))));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.Rail t44715 =
              ((x10.core.Rail)(new x10.core.Rail<x10.lang.Runtime.Worker>(x10.lang.Runtime.Worker.$RTT, t44714)));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.threads = ((x10.core.Rail)(t44715));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
this.size = 0;
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$14 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$14> $RTT = x10.rtt.StaticFunType.<$Closure$14> make(
            $Closure$14.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.BOOLEAN)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$14 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$14.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$14 $_obj = new pppp.util.Monitor.$Closure$14((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$14(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public x10.core.Boolean
              $apply$G(){return x10.core.Boolean.$box($apply$O());}
            
                
                public boolean
                  $apply$O(
                  ){
                    
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return true;
                }
                
                public $Closure$14() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$15 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$15> $RTT = x10.rtt.StaticFunType.<$Closure$15> make(
            $Closure$15.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, pppp.util.Unit.$RTT)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$15 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$15.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$15 $_obj = new pppp.util.Monitor.$Closure$15((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$15(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public pppp.util.Unit
              $apply$G(){return $apply();}
            
                
                public pppp.util.Unit
                  $apply(
                  ){
                    
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return new pppp.util.Unit((java.lang.System[]) null).pppp$util$Unit$$init$S();
                }
                
                public $Closure$15() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$16<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$16> $RTT = x10.rtt.StaticFunType.<$Closure$16> make(
            $Closure$16.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$16<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$16.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.cond = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$16 $_obj = new pppp.util.Monitor.$Closure$16((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (cond instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.cond);
                } else {
                $serializer.write(this.cond);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$16(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.Monitor.$Closure$16.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$16 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44657 =
                      (("Monitor: ") + (this.
                                          out$$));
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44658 =
                      ((t44657) + (" 0 trying cond "));
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44659 =
                      ((t44658) + (this.
                                     cond));
                    
//#line 63 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return t44659;
                }
                
                public pppp.util.Monitor out$$;
                public x10.core.fun.Fun_0_0<x10.core.Boolean> cond;
                
                public $Closure$16(final x10.rtt.Type $T,
                                   final pppp.util.Monitor out$$,
                                   final x10.core.fun.Fun_0_0<x10.core.Boolean> cond, __1$1x10$lang$Boolean$2 $dummy) {pppp.util.Monitor.$Closure$16.$initParams(this, $T);
                                                                                                                            {
                                                                                                                               ((pppp.util.Monitor.$Closure$16<$T>)this).out$$ = out$$;
                                                                                                                               ((pppp.util.Monitor.$Closure$16<$T>)this).cond = ((x10.core.fun.Fun_0_0)(cond));
                                                                                                                           }}
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Boolean$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$17<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$17> $RTT = x10.rtt.StaticFunType.<$Closure$17> make(
            $Closure$17.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$17<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$17.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.action = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$17 $_obj = new pppp.util.Monitor.$Closure$17((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (action instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.action);
                } else {
                $serializer.write(this.action);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$17(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.Monitor.$Closure$17.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$17 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44675 =
                      (("Monitor: ") + (this.
                                          out$$));
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44676 =
                      ((t44675) + (" 1 action "));
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44677 =
                      ((t44676) + (this.
                                     action));
                    
//#line 77 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return t44677;
                }
                
                public pppp.util.Monitor out$$;
                public x10.core.fun.Fun_0_0<$T> action;
                
                public $Closure$17(final x10.rtt.Type $T,
                                   final pppp.util.Monitor out$$,
                                   final x10.core.fun.Fun_0_0<$T> action, __1$1pppp$util$Monitor$$Closure$17$$T$2 $dummy) {pppp.util.Monitor.$Closure$17.$initParams(this, $T);
                                                                                                                                {
                                                                                                                                   ((pppp.util.Monitor.$Closure$17<$T>)this).out$$ = out$$;
                                                                                                                                   ((pppp.util.Monitor.$Closure$17<$T>)this).action = ((x10.core.fun.Fun_0_0)(action));
                                                                                                                               }}
                // synthetic type for parameter mangling
                public static final class __1$1pppp$util$Monitor$$Closure$17$$T$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$18<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$18> $RTT = x10.rtt.StaticFunType.<$Closure$18> make(
            $Closure$18.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$18<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$18.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.m = $deserializer.readInt();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$18 $_obj = new pppp.util.Monitor.$Closure$18((java.lang.System[]) null, (x10.rtt.Type) null);
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
                $serializer.write(this.m);
                
            }
            
            // constructor just for allocation
            public $Closure$18(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.Monitor.$Closure$18.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$18 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44679 =
                      (("Monitor : ") + (this.
                                           out$$));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44680 =
                      ((t44679) + (" 2 awakening size="));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44681 =
                      ((t44680) + ((x10.core.Int.$box(this.
                                                        m))));
                    
//#line 81 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return t44681;
                }
                
                public pppp.util.Monitor out$$;
                public int m;
                
                public $Closure$18(final x10.rtt.Type $T,
                                   final pppp.util.Monitor out$$,
                                   final int m) {pppp.util.Monitor.$Closure$18.$initParams(this, $T);
                                                      {
                                                         ((pppp.util.Monitor.$Closure$18<$T>)this).out$$ = out$$;
                                                         ((pppp.util.Monitor.$Closure$18<$T>)this).m = m;
                                                     }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$19<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$19> $RTT = x10.rtt.StaticFunType.<$Closure$19> make(
            $Closure$19.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.INT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$19<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$19.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                $_obj.threads = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$19 $_obj = new pppp.util.Monitor.$Closure$19((java.lang.System[]) null, (x10.rtt.Type) null);
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
                if (threads instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.threads);
                } else {
                $serializer.write(this.threads);
                }
                
            }
            
            // constructor just for allocation
            public $Closure$19(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.Monitor.$Closure$19.$initParams(this, $T);
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply(x10.core.Int.$unbox(a1));
            }
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$19 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  final int i44735){
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44736 =
                      (("Monitor: ") + (this.
                                          out$$));
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44737 =
                      ((t44736) + (" 3 ("));
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44738 =
                      ((t44737) + ((x10.core.Int.$box(i44735))));
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44739 =
                      ((t44738) + (") waking "));
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.core.Rail t44740 =
                      ((x10.core.Rail)(this.
                                         threads));
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final int t44741 =
                      this.
                        out$$.
                        size;
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final long t44742 =
                      ((long)(((int)(t44741))));
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final x10.lang.Runtime.Worker t44743 =
                      ((x10.lang.Runtime.Worker[])t44740.value)[(int)t44742];
                    
//#line 85 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44744 =
                      x10.rtt.Types.toString(t44743);
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44745 =
                      ((t44739) + (t44744));
                    
//#line 84 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return t44745;
                }
                
                public pppp.util.Monitor out$$;
                public x10.core.Rail<x10.lang.Runtime.Worker> threads;
                
                public $Closure$19(final x10.rtt.Type $T,
                                   final pppp.util.Monitor out$$,
                                   final x10.core.Rail<x10.lang.Runtime.Worker> threads, __1$1x10$lang$Runtime$Worker$2 $dummy) {pppp.util.Monitor.$Closure$19.$initParams(this, $T);
                                                                                                                                      {
                                                                                                                                         ((pppp.util.Monitor.$Closure$19<$T>)this).out$$ = out$$;
                                                                                                                                         ((pppp.util.Monitor.$Closure$19<$T>)this).threads = ((x10.core.Rail)(threads));
                                                                                                                                     }}
                // synthetic type for parameter mangling
                public static final class __1$1x10$lang$Runtime$Worker$2 {}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$20<$T>  extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$20> $RTT = x10.rtt.StaticFunType.<$Closure$20> make(
            $Closure$20.class, 1, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {if (i ==0)return $T;return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static <$T> x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$20<$T> $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$20.class + " calling"); } 
                $_obj.$T = (x10.rtt.Type) $deserializer.readRef();
                $_obj.out$$ = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$20 $_obj = new pppp.util.Monitor.$Closure$20((java.lang.System[]) null, (x10.rtt.Type) null);
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
            public $Closure$20(final java.lang.System[] $dummy, final x10.rtt.Type $T) { 
            pppp.util.Monitor.$Closure$20.$initParams(this, $T);
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                private x10.rtt.Type $T;
                // initializer of type parameters
                public static void $initParams(final $Closure$20 $this, final x10.rtt.Type $T) {
                $this.$T = $T;
                }
                
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44709 =
                      (("Monitor: ") + (this.
                                          out$$));
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
final java.lang.String t44710 =
                      ((t44709) + (" 4 done."));
                    
//#line 89 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return t44710;
                }
                
                public pppp.util.Monitor out$$;
                
                public $Closure$20(final x10.rtt.Type $T,
                                   final pppp.util.Monitor out$$) {pppp.util.Monitor.$Closure$20.$initParams(this, $T);
                                                                        {
                                                                           ((pppp.util.Monitor.$Closure$20<$T>)this).out$$ = out$$;
                                                                       }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$21 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$21> $RTT = x10.rtt.StaticFunType.<$Closure$21> make(
            $Closure$21.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.STRING, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$21 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$21.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$21 $_obj = new pppp.util.Monitor.$Closure$21((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$21(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply((java.lang.String)a1);
            }
            
                
                public java.lang.String
                  $apply(
                  final java.lang.String t){
                    
//#line 95 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return (("Monitor: waking ") + (t));
                }
                
                public $Closure$21() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$22 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$22> $RTT = x10.rtt.StaticFunType.<$Closure$22> make(
            $Closure$22.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$22 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$22.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$22 $_obj = new pppp.util.Monitor.$Closure$22((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$22(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 96 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return "Monitor: Trying cond ";
                }
                
                public $Closure$22() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$23 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$23> $RTT = x10.rtt.StaticFunType.<$Closure$23> make(
            $Closure$23.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$23 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$23.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$23 $_obj = new pppp.util.Monitor.$Closure$23((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$23(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 97 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return "Monitor: Retrying cond ";
                }
                
                public $Closure$23() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$24 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$24> $RTT = x10.rtt.StaticFunType.<$Closure$24> make(
            $Closure$24.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$24 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$24.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$24 $_obj = new pppp.util.Monitor.$Closure$24((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$24(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 98 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return "Monitor: Suspending. ";
                }
                
                public $Closure$24() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$25 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$25> $RTT = x10.rtt.StaticFunType.<$Closure$25> make(
            $Closure$25.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$25 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$25.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$25 $_obj = new pppp.util.Monitor.$Closure$25((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$25(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 99 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return "Monitor: Trying action.";
                }
                
                public $Closure$25() { {
                                              
                                          }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$26 extends x10.core.Ref implements x10.core.fun.Fun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$26> $RTT = x10.rtt.StaticFunType.<$Closure$26> make(
            $Closure$26.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_0.$RTT, x10.rtt.Types.STRING)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(pppp.util.Monitor.$Closure$26 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$26.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                pppp.util.Monitor.$Closure$26 $_obj = new pppp.util.Monitor.$Closure$26((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$26(final java.lang.System[] $dummy) { 
            }
            // bridge for method abstract public ()=>U.operator()():U
            public java.lang.String
              $apply$G(){return $apply();}
            
                
                public java.lang.String
                  $apply(
                  ){
                    
//#line 100 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/pppp/util/Monitor.x10"
return "Monitor: done";
                }
                
                public $Closure$26() { {
                                              
                                          }}
                
            }
            
        
        }
        
        