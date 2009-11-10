/**
 * This is one of a series of programs showing how to express
 * different forms of parallelism in X10.</p>
 *
 * All of the example programs in the series are computing
 * the same thing:  solving a set of 2D partial differential
 * equations that can be expressed as an iterative 4-point
 * stencil operation.  See the X10 2.0 tutorial for
 * for more details and some pictures.</p>
 *
 * This program is illustrating the the "ZPL style", where
 * the computation is expressed directly in terms of 
 * computation on global arrays.<p>
 */
public class HeatTransfer {
    static type Real=Double;
    const n = 3, epsilon = 1.0e-5;

    const BigD = Dist.makeBlock([0..n+1, 0..n+1], 0);
    const D = BigD | ([1..n, 1..n] as Region);
    const LastRow = [0..0, 1..n] as Region;
    const A = Array.make[Real](BigD,(p:Point)=>{ LastRow.contains(p) ? 1.0 : 0.0 });
    const Temp = Array.make[Real](BigD);

    static def stencil_1((x,y):Point(2)) = (([x-1..x+1,y..y] as Region(2)) || [x..x,y-1..y+1]) - [x..x,y..y];

    static def subtract(a:Array[Real],b:Array[Real]) = Array.make[Real](a.dist, (p:Point)=>a(p as Point(a.rank))-b(p as Point(b.rank)));

    def run() {
        var delta:Real = 1.0;
        do {
            finish ateach (p in D)
                Temp(p) = (A | stencil_1(p)).reduce(Double.+, 0.0)/4;

            delta = subtract(A|D.region,Temp|D.region).lift(Math.abs.(Double)).reduce(Math.max.(Double,Double), 0.0);
            finish ateach (p in D) A(p) = Temp(p);
        } while (delta > epsilon);
    }

    static def format(x:double, numDecimals:int) {
        return String.format("%1."+numDecimals+"f", [x as Box[Double]]);
    }

    public static def main(Rail[String]) {
	Console.OUT.println("HeatTransfer Tutorial example with n="+n+" and epsilon="+epsilon);
	Console.OUT.println("Initializing data structures");
        val s = new HeatTransfer();
	Console.OUT.print("Beginning computation...");
	val start = System.nanoTime();
        s.run();
	val stop = System.nanoTime();
	Console.OUT.println("...completed in "+format(((stop-start as double)/1e9), 3)+" seconds.");
        for ((i) in s.A.region.projection(0)) {
            for ((j) in s.A.region.projection(1))
                Console.OUT.print(format(s.A(i,j), 4)+" ");
            Console.OUT.println();
        }
    }
}
