import clocked.Clocked;


public class Pipeline {

   val N = 64;
   public def pipeline() {
   		finish {
  		val c = Clock.make(); 
		val op = Math.noOp.(Int, Int);
         	val a = Rail.make[int @ Clocked[Int](c,op,0)](N, (i:int)=> 0);
   		shared var b: int @ Clocked[int] (c, op, 0) = 0;

 	   	 async clocked(c)  {
                        var i: int;
                        for (i = 0; i < 2*N; i++)  {
                                a(0) = i;
                                next;  /*write phase over */
                        }
                }
		var j: int = 0;
		for (j = 1; j <= N-1; j++) {
		val jj = j;
        	async clocked (c) {
                        var i: int;
                        for (i = 0; i < 2*N; i++)  {
                                a(jj) = a(jj-1) + 1;
                                next; /*write phase over */
                        }
                }
		}
      		var i: int;
      		for (i = 0; i < 2*N; i++)  {
                        val o = a(N-1) + 1;
                        Console.OUT.println(o);
                        next; /*write phase over */
                 	}
          	}
        }

   

    public static def main(args:Rail[String]!) {
         val h = new Pipeline();  // final variable
         h.pipeline();
    }

}
