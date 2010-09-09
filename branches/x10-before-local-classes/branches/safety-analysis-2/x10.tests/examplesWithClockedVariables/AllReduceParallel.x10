import clocked.*;

public class AllReduceParallel {
  
  static val P = 8;

  public static def allReduce(c: Clock, op: (int,int)=>int, myA:Rail[int @ Clocked[int](c,op, 0)]!) @ClockedM(c) {
  finish {
    val phases = Math.log2(P);
     foreach ((i) in 1..P-1) clocked (c)  {
    	val p = i;
  
        	var shift_:Int=1;
        	for ((phase) in 0..phases-1) {
               	 val destId = (p+shift_)% P;
                val source = here;
                val elem = myA(p);
                myA(destId) = elem + myA(destId);
                next;
                shift_ *=2;
        	}
     	
     }
   var shift_:Int=1;
    for ((phase) in 0..phases-1) {
    	val destId = (0+shift_)% P;
    	val source = here;
    	val elem = myA(0);
    	myA(destId) = elem + myA(destId);   
    	next;
    	shift_ *=2;
    }
   }
   return myA(0);
}

  public static def main(Rail[String]) {

    val c = Clock.make();
    val op = int.+;
    val myA = Rail.make [int @ Clocked[int](c, op, 0)](P, (i:Int)=> i);
    val result = allReduce(c, op, myA);
   
    Console.OUT.println("allReduce = " + result);
  }
}
