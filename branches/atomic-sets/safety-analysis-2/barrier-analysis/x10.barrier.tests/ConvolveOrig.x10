

class ConvolveOrig {

    static def run(w:ValRail[Int], x:ValRail[Int]) {
 			    val c = new Clock();       
             val n = x.length;
             val op = Int.+;
             val yi = Rail.make[Int ](w.length, (Int)=>0);
             val xz = Rail.make[Int](1, (Int) => 0);
             async clocked(c)  {
                       for (v in x) {
                                xz(0) = v;
                                next; // end of one phase, now you can read the values
                                next; // now write
                        } 
               } 
               for ((i) in 0..w.length-1) async clocked (c)  {
                    for ((j) in 1..n) {
                        next; // end of one phase
                        val v = (i==0? 0: yi(i-1)) + w(i)*xz(0);
                         next;
                        yi(i)=v;
                       
                    }
                } 

           
                Console.OUT.print("y = ");
                next;
                next;
                for ((j) in 1..n) {
                        next; // end of read phase, now you can write the values
                        Console.OUT.print(yi(w.length-1)+ " " );
                        next;
                }
                 Console.OUT.println();
        
    }

    public static def main(args: Rail[String]) {
        Console.OUT.print("Should get "); for (a in [3,8,14,20,26,32]) Console.OUT.print(a+ " ");
        Console.OUT.println();
        run([1,2,3], [1,2,3,4,5,6]);

    }
}