@x10.runtime.impl.java.X10Generated public class Integrate extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    private static final long serialVersionUID = 1L;
    public static final x10.rtt.RuntimeType<Integrate> $RTT = x10.rtt.NamedType.<Integrate> make(
    "Integrate", Integrate.class
    );
    public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
    
    public x10.rtt.Type<?> $getParam(int i) {return null;}
    private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
    public static x10.serialization.X10JavaSerializable $_deserialize_body(Integrate $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Integrate.class + " calling"); } 
        $_obj.fun = $deserializer.readRef();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
    
        Integrate $_obj = new Integrate((java.lang.System[]) null);
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
    public Integrate(final java.lang.System[] $dummy) { 
    }
    
        
//#line 2 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final public static double epsilon = 1.0E-12;
        
//#line 3 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
public x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double> fun;
        
        
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
// creation method for java code (1-phase java constructor)
        public Integrate(final x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double> f, __0$1x10$lang$Double$3x10$lang$Double$2 $dummy){this((java.lang.System[]) null);
                                                                                                                                            Integrate$$init$S(f, (Integrate.__0$1x10$lang$Double$3x10$lang$Double$2) null);}
        
        // constructor for non-virtual call
        final public Integrate Integrate$$init$S(final x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double> f, __0$1x10$lang$Double$3x10$lang$Double$2 $dummy) { {
                                                                                                                                                                         
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
;
                                                                                                                                                                         
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"

                                                                                                                                                                         
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
this.__fieldInitializers_Integrate();
                                                                                                                                                                         
//#line 4 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
this.fun = ((x10.core.fun.Fun_0_1)(f));
                                                                                                                                                                     }
                                                                                                                                                                     return this;
                                                                                                                                                                     }
        
        
        
//#line 5 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
public double
                                                                                                                                     computeArea$O(
                                                                                                                                     final double left,
                                                                                                                                     final double right){
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final x10.core.fun.Fun_0_1 t50157 =
              ((x10.core.fun.Fun_0_1)(fun));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50159 =
              x10.core.Double.$unbox(((x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double>)t50157).$apply(x10.core.Double.$box(left),x10.rtt.Types.DOUBLE));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final x10.core.fun.Fun_0_1 t50158 =
              ((x10.core.fun.Fun_0_1)(fun));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50160 =
              x10.core.Double.$unbox(((x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double>)t50158).$apply(x10.core.Double.$box(right),x10.rtt.Types.DOUBLE));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50161 =
              ((double)(long)(((long)(0L))));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50162 =
              this.eval$O((double)(left),
                          (double)(t50159),
                          (double)(right),
                          (double)(t50160),
                          (double)(t50161));
            
//#line 6 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
return t50162;
        }
        
        
//#line 8 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
private double
                                                                                                                                     eval$O(
                                                                                                                                     final double l,
                                                                                                                                     final double fl,
                                                                                                                                     final double r,
                                                                                                                                     final double fr,
                                                                                                                                     final double a){
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50163 =
              ((r) - (((double)(l))));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50164 =
              ((double)(long)(((long)(2L))));
            
//#line 9 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double h =
              ((t50163) / (((double)(t50164))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50165 =
              ((double)(long)(((long)(2L))));
            
//#line 10 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double hh =
              ((h) / (((double)(t50165))));
            
//#line 11 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double c =
              ((l) + (((double)(h))));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final x10.core.fun.Fun_0_1 t50166 =
              ((x10.core.fun.Fun_0_1)(fun));
            
//#line 12 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double fc =
              x10.core.Double.$unbox(((x10.core.fun.Fun_0_1<x10.core.Double,x10.core.Double>)t50166).$apply(x10.core.Double.$box(c),x10.rtt.Types.DOUBLE));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50167 =
              ((fl) + (((double)(fc))));
            
//#line 13 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double al =
              ((t50167) * (((double)(hh))));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50168 =
              ((fr) + (((double)(fc))));
            
//#line 14 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double ar =
              ((t50168) * (((double)(hh))));
            
//#line 15 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double alr =
              ((al) + (((double)(ar))));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50169 =
              ((alr) - (((double)(a))));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50170 =
              java.lang.Math.abs(((double)(t50169)));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50171 =
              Integrate.epsilon;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final boolean t50174 =
              ((t50170) < (((double)(t50171))));
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
double t50175 =
               0;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
if (t50174) {
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
t50175 = alr;
            } else {
                
//#line 18 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50172 =
                  this.eval$O((double)(c),
                              (double)(fc),
                              (double)(r),
                              (double)(fr),
                              (double)(ar));
                
//#line 19 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50173 =
                  this.eval$O((double)(l),
                              (double)(fl),
                              (double)(c),
                              (double)(fc),
                              (double)(al));
                
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
t50175 = ((t50172) + (((double)(t50173))));
            }
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50176 =
              t50175;
            
//#line 16 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
return t50176;
        }
        
        public static double
          eval$P$O(
          final double l,
          final double fl,
          final double r,
          final double fr,
          final double a,
          final Integrate Integrate){
            return Integrate.eval$O((double)(l),
                                    (double)(fl),
                                    (double)(r),
                                    (double)(fr),
                                    (double)(a));
        }
        
        
//#line 21 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
public static class $Main extends x10.runtime.impl.java.Runtime {
        private static final long serialVersionUID = 1L;
        public static void main(java.lang.String[] args)  {
        // start native runtime
        new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args)  {
        // call the original app-main method
        Integrate.main(args);
        }
        }
        
        // the original app-main method
        public static void main(final x10.core.Rail<java.lang.String> args)  {
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final x10.core.fun.Fun_0_1 t50179 =
              ((x10.core.fun.Fun_0_1)(new Integrate.$Closure$79()));
            
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final Integrate obj =
              ((Integrate)(new Integrate((java.lang.System[]) null).Integrate$$init$S(((x10.core.fun.Fun_0_1)(t50179)), (Integrate.__0$1x10$lang$Double$3x10$lang$Double$2) null)));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final long t50180 =
              ((x10.core.Rail<java.lang.String>)args).
                size;
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final boolean t50182 =
              ((t50180) > (((long)(0L))));
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
int t50183 =
               0;
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
if (t50182) {
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final java.lang.String t50181 =
                  ((java.lang.String[])args.value)[(int)0L];
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
t50183 = java.lang.Integer.parseInt(t50181);
            } else {
                
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
t50183 = 10;
            }
            
//#line 23 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final int xMax =
              t50183;
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final long t50184 =
              x10.lang.System.nanoTime$O();
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final long t50185 =
              (-(t50184));
            
//#line 24 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
double start =
              ((double)(long)(((long)(t50185))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50186 =
              ((double)(long)(((long)(0L))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50187 =
              ((double)(int)(((int)(xMax))));
            
//#line 25 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double area =
              obj.computeArea$O((double)(t50186),
                                (double)(t50187));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50189 =
              start;
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final long t50188 =
              x10.lang.System.nanoTime$O();
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50190 =
              ((double)(long)(((long)(t50188))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50191 =
              ((t50189) + (((double)(t50190))));
            
//#line 26 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
start = t50191;
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final x10.io.Printer t50202 =
              ((x10.io.Printer)(x10.io.Console.get$OUT()));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final java.lang.String t50192 =
              (("The area of (x*x +1) * x from 0 to ") + ((x10.core.Int.$box(xMax))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final java.lang.String t50193 =
              ((t50192) + (" is "));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final java.lang.String t50194 =
              ((t50193) + ((x10.core.Double.$box(area))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final java.lang.String t50199 =
              ((t50194) + (" (t="));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50197 =
              start;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final long t50195 =
              1000000L;
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final float t50196 =
              ((float)(long)(((long)(t50195))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50198 =
              ((double)(float)(((float)(t50196))));
            
//#line 29 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50200 =
              ((t50197) / (((double)(t50198))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final java.lang.String t50201 =
              ((t50199) + ((x10.core.Double.$box(t50200))));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final java.lang.String t50203 =
              ((t50201) + (" ms)."));
            
//#line 27 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
t50202.println(((java.lang.Object)(t50203)));
        }
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final public Integrate
                                                                                                                                     Integrate$$this$Integrate(
                                                                                                                                     ){
            
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
return Integrate.this;
        }
        
        
//#line 1 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final public void
                                                                                                                                     __fieldInitializers_Integrate(
                                                                                                                                     ){
            
        }
        
        @x10.runtime.impl.java.X10Generated final public static class $Closure$79 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
        {
            private static final long serialVersionUID = 1L;
            public static final x10.rtt.RuntimeType<$Closure$79> $RTT = x10.rtt.StaticFunType.<$Closure$79> make(
            $Closure$79.class, new x10.rtt.Type[] {x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, x10.rtt.Types.DOUBLE, x10.rtt.Types.DOUBLE)}
            );
            public x10.rtt.RuntimeType<?> $getRTT() {return $RTT;}
            
            public x10.rtt.Type<?> $getParam(int i) {return null;}
            private void writeObject(java.io.ObjectOutputStream oos) throws java.io.IOException { if (x10.runtime.impl.java.Runtime.TRACE_SER) { java.lang.System.out.println("Serializer: writeObject(ObjectOutputStream) of " + this + " calling"); } oos.defaultWriteObject(); }
            public static x10.serialization.X10JavaSerializable $_deserialize_body(Integrate.$Closure$79 $_obj , x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$79.class + " calling"); } 
                return $_obj;
            }
            
            public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            
                Integrate.$Closure$79 $_obj = new Integrate.$Closure$79((java.lang.System[]) null);
                $deserializer.record_reference($_obj);
                return $_deserialize_body($_obj, $deserializer);
                
            }
            
            public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
                
            }
            
            // constructor just for allocation
            public $Closure$79(final java.lang.System[] $dummy) { 
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
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50177 =
                      ((x) * (((double)(x))));
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
final double t50178 =
                      ((t50177) + (((double)(x))));
                    
//#line 22 "/Users/vsaraswa/Downloads/x10dt 2.4 alpha1/x10dt.app/Contents/MacOS/~/pppp13-test1/pppp2013/src/Integrate.x10"
return t50178;
                }
                
                public $Closure$79() { {
                                              
                                          }}
                
            }
            
        // synthetic type for parameter mangling
        public static final class __0$1x10$lang$Double$3x10$lang$Double$2 {}
        
    }
    
    