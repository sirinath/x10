@x10.runtime.impl.java.X10Generated public class IntegratePar extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<IntegratePar> $RTT = x10.rtt.NamedType.<IntegratePar> make(
    "IntegratePar", IntegratePar.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(IntegratePar $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + IntegratePar.class + " calling"); } 
        $_obj.fun = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        IntegratePar $_obj = new IntegratePar((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
        
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
    
        if (fun instanceof x10.serialization.X10JavaSerializable) {
        $serializer.write((x10.serialization.X10JavaSerializable) this.fun);
        } else {
        $serializer.write(this.fun);
        }
        
    }
    
    // constructor just for allocation
    public IntegratePar(final java.lang.System[] $dummy) { 
    }
    
        
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final public static double epsilon = 1.0E-12;
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
public x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double> fun;
        
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
// creation method for java code (1-phase java constructor)
        public IntegratePar(final x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double> f, __0$1x10$lang$Double$3x10$lang$Double$2 $dummy){this((java.lang.System[]) null);
                                                                                                                                               IntegratePar$$init$S(f, (IntegratePar.__0$1x10$lang$Double$3x10$lang$Double$2) null);}
        
        // constructor for non-virtual call
        final public IntegratePar IntegratePar$$init$S(final x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double> f, __0$1x10$lang$Double$3x10$lang$Double$2 $dummy) { {
                                                                                                                                                                               
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
;
                                                                                                                                                                               
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"

                                                                                                                                                                               
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
this.__fieldInitializers_IntegratePar();
                                                                                                                                                                               
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
this.fun = ((x10.core.fun.Fun_0_1)(f));
                                                                                                                                                                           }
                                                                                                                                                                           return this;
                                                                                                                                                                           }
        
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
public double
                                                                                                                                        computeArea$O(
                                                                                                                                        final double left,
                                                                                                                                        final double right){
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final x10.core.fun.Fun_0_1 t41632 =
              ((x10.core.fun.Fun_0_1)(fun));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41634 =
              x10.core.Double.$unbox(((x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double>)t41632).$apply(x10.core.Double.$box(left),x10.rtt.Types.DOUBLE));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final x10.core.fun.Fun_0_1 t41633 =
              ((x10.core.fun.Fun_0_1)(fun));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41635 =
              x10.core.Double.$unbox(((x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double>)t41633).$apply(x10.core.Double.$box(right),x10.rtt.Types.DOUBLE));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41636 =
              ((double)(long)(((long)(0L))));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41637 =
              this.eval$O((double)(left),
                          (double)(t41634),
                          (double)(right),
                          (double)(t41635),
                          (double)(t41636));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
return t41637;
        }
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
private double
                                                                                                                                        eval$O(
                                                                                                                                        final double l,
                                                                                                                                        final double fl,
                                                                                                                                        final double r,
                                                                                                                                        final double fr,
                                                                                                                                        final double a){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41638 =
              ((r) - (((double)(l))));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41639 =
              ((double)(long)(((long)(2L))));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double h =
              ((t41638) / (((double)(t41639))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41640 =
              ((double)(long)(((long)(2L))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double hh =
              ((h) / (((double)(t41640))));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double c =
              ((l) + (((double)(h))));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final x10.core.fun.Fun_0_1 t41641 =
              ((x10.core.fun.Fun_0_1)(fun));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double fc =
              x10.core.Double.$unbox(((x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double>)t41641).$apply(x10.core.Double.$box(c),x10.rtt.Types.DOUBLE));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41642 =
              ((fl) + (((double)(fc))));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double al =
              ((t41642) * (((double)(hh))));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41643 =
              ((fr) + (((double)(fc))));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double ar =
              ((t41643) * (((double)(hh))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double alr =
              ((al) + (((double)(ar))));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41644 =
              ((alr) - (((double)(a))));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41645 =
              java.lang.Math.abs(((double)(t41644)));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41646 =
              IntegratePar.epsilon;
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final boolean t41650 =
              ((t41645) < (((double)(t41646))));
            
//#line 17 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
if (t41650) {
                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
return alr;
            } else {
                
//#line 20 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double expr1;
                
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double expr2;
                {
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
x10.lang.Runtime.ensureNotInAtomic();
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final x10.lang.FinishState x10$__var0 =
                      x10.lang.Runtime.startFinish();
                    {
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double[] $expr141682 =
                          new double[1];
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
try {{
                            {
                                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
x10.lang.Runtime.runAsync(((x10.core.fun.VoidFun_0_0)(new IntegratePar.$Closure$0(this,
                                                                                                                                                                                                                                                 c,
                                                                                                                                                                                                                                                 fc,
                                                                                                                                                                                                                                                 r,
                                                                                                                                                                                                                                                 fr,
                                                                                                                                                                                                                                                 ar,
                                                                                                                                                                                                                                                 $expr141682))));
                                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41648 =
                                  this.eval$O((double)(l),
                                              (double)(fl),
                                              (double)(c),
                                              (double)(fc),
                                              (double)(al));
                                
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
expr2 = t41648;
                            }
                        }}catch (java.lang.Throwable __lowerer__var__0__) {
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
x10.lang.Runtime.pushException(((java.lang.Throwable)(__lowerer__var__0__)));
                            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
throw new java.lang.RuntimeException();
                        }finally {{
                             
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
x10.lang.Runtime.stopFinish(((x10.lang.FinishState)(x10$__var0)));
                         }}
                        
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
expr1 = ((double)$expr141682[(int)0]);
                        }
                    }
                
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41649 =
                  ((expr1) + (((double)(expr2))));
                
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
return t41649;
                }
            }
        
        public static double
          eval$P$O(
          final double l,
          final double fl,
          final double r,
          final double fr,
          final double a,
          final IntegratePar IntegratePar){
            return IntegratePar.eval$O((double)(l),
                                       (double)(fl),
                                       (double)(r),
                                       (double)(fr),
                                       (double)(a));
        }
        
        
//#line 30 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        IntegratePar.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final x10.core.fun.Fun_0_1 t41653 =
              ((x10.core.fun.Fun_0_1)(new IntegratePar.$Closure$1()));
            
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final IntegratePar obj =
              ((IntegratePar)(new IntegratePar((java.lang.System[]) null).IntegratePar$$init$S(((x10.core.fun.Fun_0_1)(t41653)), (IntegratePar.__0$1x10$lang$Double$3x10$lang$Double$2) null)));
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final long t41654 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final boolean t41656 =
              ((t41654) > (((long)(0L))));
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
int t41657 =
               0;
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
if (t41656) {
                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final java.lang.String t41655 =
                  ((java.lang.String[])args.value)[(int)0L];
                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
t41657 = java.lang.Integer.parseInt(t41655);
            } else {
                
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
t41657 = 10;
            }
            
//#line 32 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final int xMax =
              t41657;
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final long t41658 =
              x10.lang.System.nanoTime$O();
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final long t41659 =
              (-(t41658));
            
//#line 33 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
double start =
              ((double)(long)(((long)(t41659))));
            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41660 =
              ((double)(long)(((long)(0L))));
            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41661 =
              ((double)(int)(((int)(xMax))));
            
//#line 34 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double area =
              obj.computeArea$O((double)(t41660),
                                (double)(t41661));
            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41663 =
              start;
            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final long t41662 =
              x10.lang.System.nanoTime$O();
            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41664 =
              ((double)(long)(((long)(t41662))));
            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41665 =
              ((t41663) + (((double)(t41664))));
            
//#line 35 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
start = t41665;
            
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final x10.io.Printer t41676 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final java.lang.String t41666 =
              (("The area of (x*x +1) * x from 0 to ") + ((x10.core.Int.$box(xMax))));
            
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final java.lang.String t41667 =
              ((t41666) + (" is "));
            
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final java.lang.String t41668 =
              ((t41667) + ((x10.core.Double.$box(area))));
            
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final java.lang.String t41673 =
              ((t41668) + (" (t="));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41671 =
              start;
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final long t41669 =
              1000000L;
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final float t41670 =
              ((float)(long)(((long)(t41669))));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41672 =
              ((double)(float)(((float)(t41670))));
            
//#line 38 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41674 =
              ((t41671) / (((double)(t41672))));
            
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final java.lang.String t41675 =
              ((t41673) + ((x10.core.Double.$box(t41674))));
            
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final java.lang.String t41677 =
              ((t41675) + (" ms)."));
            
//#line 36 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
t41676.println(((java.lang.Object)(t41677)));
        }
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final public IntegratePar
                                                                                                                                        IntegratePar$$this$IntegratePar(
                                                                                                                                        ){
            
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
return IntegratePar.this;
        }
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final public void
                                                                                                                                        __fieldInitializers_IntegratePar(
                                                                                                                                        ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$0 extends x10.core.Ref implements x10.core.fun.VoidFun_0_0, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$0> $RTT = x10.rtt.StaticVoidFunType.<$Closure$0> make(
            $Closure$0.class, new x10.rtt.Type[] {x10.core.fun.VoidFun_0_0.$RTT}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(IntegratePar.$Closure$0 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$0.class + " calling"); } 
                $_obj.out$$ = $deserializer.readRef();
                $_obj.c = $deserializer.readDouble();
                $_obj.fc = $deserializer.readDouble();
                $_obj.r = $deserializer.readDouble();
                $_obj.fr = $deserializer.readDouble();
                $_obj.ar = $deserializer.readDouble();
                $_obj.$expr141682 = $deserializer.readRef();
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                IntegratePar.$Closure$0 $_obj = new IntegratePar.$Closure$0((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                if (out$$ instanceof x10.serialization.X10JavaSerializable) {
                $serializer.write((x10.serialization.X10JavaSerializable) this.out$$);
                } else {
                $serializer.write(this.out$$);
                }
                $serializer.write(this.c);
                $serializer.write(this.fc);
                $serializer.write(this.r);
                $serializer.write(this.fr);
                $serializer.write(this.ar);
                $serializer.write(this.$expr141682);
                
            }
            
            // constructor just for allocation
            public $Closure$0(final java.lang.System[] $dummy) { 
            }
            
                
                public void
                  $apply(
                  ){
                    
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
try {{
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41647 =
                          this.
                            out$$.eval$O((double)(this.
                                                    c),
                                         (double)(this.
                                                    fc),
                                         (double)(this.
                                                    r),
                                         (double)(this.
                                                    fr),
                                         (double)(this.
                                                    ar));
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
this.
                                                                                                                                                         $expr141682[(int)0]=t41647;
                    }}catch (java.lang.Error __lowerer__var__0__) {
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
throw __lowerer__var__0__;
                    }catch (java.lang.Throwable __lowerer__var__1__) {
                        
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
throw x10.rtt.Types.EXCEPTION.isInstance(__lowerer__var__1__) ? (java.lang.RuntimeException)(__lowerer__var__1__) : new x10.lang.WrappedThrowable(__lowerer__var__1__);
                    }
                }
                
                public IntegratePar out$$;
                public double c;
                public double fc;
                public double r;
                public double fr;
                public double ar;
                public double[] $expr141682;
                
                public $Closure$0(final IntegratePar out$$,
                                  final double c,
                                  final double fc,
                                  final double r,
                                  final double fr,
                                  final double ar,
                                  final double[] $expr141682) { {
                                                                       this.out$$ = out$$;
                                                                       this.c = c;
                                                                       this.fc = fc;
                                                                       this.r = r;
                                                                       this.fr = fr;
                                                                       this.ar = ar;
                                                                       this.$expr141682 = $expr141682;
                                                                   }}
                
            }
            
        @x10.runtime.impl.java.X10Generated final public static class $Closure$1 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$1> $RTT = x10.rtt.StaticFunType.<$Closure$1> make(
            $Closure$1.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.DOUBLE, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(IntegratePar.$Closure$1 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$1.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                IntegratePar.$Closure$1 $_obj = new IntegratePar.$Closure$1((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$1(final java.lang.System[] $dummy) { 
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return x10.core.Double.$box($apply$O(x10.core.Double.$unbox(a1)));
            }
            // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
            public double $apply$D(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply$O(x10.core.Double.$unbox(a1));
            }
            
                
                public double
                  $apply$O(
                  final double x){
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41651 =
                      ((x) * (((double)(x))));
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
final double t41652 =
                      ((t41651) + (((double)(x))));
                    
//#line 31 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/IntegratePar.x10"
return t41652;
                }
                
                public $Closure$1() { {
                                             
                                         }}
                
            }
            
        // synthetic type for parameter mangling
        public static final class __0$1x10$lang$Double$3x10$lang$Double$2 {}
        
        }
        
        