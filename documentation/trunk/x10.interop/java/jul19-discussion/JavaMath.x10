import x10.interop.Java;
public class JavaMath {
    public static def main(Array[String]) {
        val x:Double = 0.5;
        val pi:Double = Math.PI;
        val j_pi:Double = java.lang.Math.PI;
        val tpi:Double = Math.cos(pi);
        val j_tpi:Double = java.lang.Math.cos(pi);
        Console.OUT.println("X10 pi: "+pi+"\nJava pi: "+j_pi);
        Console.OUT.println("X10 cos(pi): "+tpi+"\nJava cos(pi): "+j_tpi);
    }
}
