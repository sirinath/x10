import x10.util.Random;
import x10.io.Console;
import clocked.Clocked;

public class Histogram {

    public static def main(args:Rail[String]!) {
    	val start_time = System.currentTimeMillis(); 
	val N = 64;
	val S = 10000;
	val c = Clock.make();
	val a = Rail.make[Int](N*S, (i:Int)=> i);
	val op = Int.+;

    val b = Rail.make[Int @  Clocked[Int] (c,op, 0)](S);
    var i: int = 0 ;
	finish for(i = 0; i< N; i++)  {
			val ii = i;
			async clocked(c)  {
			var j: int = 0;
			for (j = ii * S; j < (ii + 1) * S; j++) { 
	       			val bin = a(j) % S;
	      	   	 	b(bin) = 1;
			}
	    
	       }
	}
	next;
    Console.OUT.println("Test ok." + b(0));
    val compute_time = (System.currentTimeMillis() - start_time);
    Console.ERR.print( compute_time + " ");
    }
}
