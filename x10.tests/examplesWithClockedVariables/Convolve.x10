import clocked.Clocked;

class Convolve {

    static def run(w:ValRail[Int], x:ValRail[Int]) {
    
    		finish {
 			 val c = Clock.make();       
             val n = x.length;
	     val op = Math.noOp.(Int, Int);
             val yi = Rail.make[Int @ Clocked[int] (c, op, 0)](w.length, (Int)=>0);
             val xz: int @ Clocked[int] (c, op, 0) = 0;
             async clocked (c) {
                       for (v in x) {
                                xz = v;
                                next; // end of one phase, now you can read the values
                        } 
               } 
               for ((i) in 0..w.length-1) async clocked (c)  {
                    for ((j) in 1..n) {
                        next; // end of one phase
                        val v =( (i==0? 0:yi(i-1)) + w(i)*xz) as Int;

                        yi(i)=v;
                    }
                } 

                next;
                Console.OUT.print("y = ");
                for ((j) in 1..n) {
                        next; // end of read phase, now you can write the values
                        Console.OUT.print(yi(w.length-1)+ " " );
                }
                 Console.OUT.println();
        	}
    }

    public static def main(args: Rail[String]) {
        Console.OUT.print("Should get "); for (a in [3,8,14,20,26,32]) Console.OUT.print(a+ " ");
        Console.OUT.println();
        run([1,2,3], [1,2,3,4,5,6]);

    }
}
