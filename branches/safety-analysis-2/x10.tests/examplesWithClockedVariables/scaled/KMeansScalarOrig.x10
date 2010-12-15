import x10.util.Random;


public class KMeansScalarOrig {
    
    static val DIM=2,  K=4, POINTS=1048576, ITERATIONS=50, THREADS=64;
  
   static def  add (r1: Rail[int]!, r2: Rail[int]!): Rail[int] {
       var r3 : Rail[int]! = Rail.make[int](DIM);
       var i: Int;
        for (i = 0 ;  i <= DIM-1; i++)
                r3(i) =  r1(i) + r2(i);
        return r3;

  } 
 

    def dist(vec1: Double, vec2: Double ) {
         
                val tmp = vec1-vec2;
                val dist = tmp*tmp;
         
            	return dist;
   }
     


    def computeMeans() {
      val c = Clock.make();
       val rnd = new Random(0);
       val iop = Int.+;
       val fop = Double.+;
       var rC: Rail[Double]! =
          Rail.make[Double](K);
       var bC :Rail[Double]! =
          Rail.make[Double](K, (Int)=>rnd.nextDouble());
       val count =
            Rail.make[int] (K, (Int) => 0) ; 
          
       val points = Rail.make[Double](POINTS, (Int)=>rnd.nextDouble()); 
      
    for ((i) in 1..ITERATIONS) {
          
            val tmp = rC;
            rC = bC;
            bC = tmp;
            val redCluster = rC;
            val blackCluster = bC;
	   val slice = POINTS/THREADS;
            for ((p) in 0..THREADS-1) { 
               async (this) clocked(c) { 
		  for ((l) in ((p*slice)..((p+1)*slice -1))) {
                   val point = points(l);
                  var closest:Int = -1;
                   var closestDist:Double = Double.MAX_VALUE;
                   for ((k) in 0..K-1) { // compute closest mean in cluster.
                       val distance =this.dist(point, redCluster(k));
                       if (distance < closestDist) {
                          closestDist = distance;
                          closest = k;
                       } // end if
                   } // end for
                        
                       atomic blackCluster(closest) += point;
                       atomic count(closest) += 1;
		  } // l 
                 } // end async
               } // end for 
            next;  // Main activity waits for all the activities to finish
            
            for ((k) in 0..K-1) {
            	  blackCluster(k) = blackCluster(k)/count(k);
            	  redCluster(k) = 0;
            	  count(k) = 0; 			
            }
     
            	 
         
            next;
 
        
        } // end for
         
       for ((k) in 0..K-1) Console.OUT.println(bC(k));
       
    }
  
    public static def main (args : Rail[String]) {
      
    	val start_time = System.currentTimeMillis(); 
      	val k = new KMeansScalarOrig();
        k.computeMeans();
    	val compute_time = (System.currentTimeMillis() - start_time);
    	Console.OUT.println( compute_time + " ");
       
       
    }
}

