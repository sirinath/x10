import x10.util.Random;
import x10.util.RailUtils;
import x10.util.Team;
import x10.regionarray.Array;
import x10.compiler.Native;

public class MC_Cycle {

   public static val BNDRY_REFLECT:Int = -1n;
   public static val BNDRY_PERIODIC:Int = -2n;
   public static val BNDRY_LEAK:Int = -3n;

   private var mc:MC; 

   private static val RAND_MAX:Int = 2147483647n;
   
//   var random:Random;
	static val random =  new Random();
  
   var comm_choice:Int;      
   
   /** how many particles sent to proc i at a stage */
   var mySendCount:Rail[Int] = new Rail[Int](Place.MAX_PLACES);
   
   /** how many particles recv from proc i at a stage */
   var myRecvCount:Rail[Int] = new Rail[Int](Place.MAX_PLACES);

   def this(mcobj:MC, comm_choice:Int) {
      mc = mcobj;      
//		random =  new Random();
    	random.setSeed(mc.seed as Long);
//      MC_Init.srand(mc.seed);
      this.comm_choice = comm_choice;
   }

	@Native("c++", "printf(\"stage %3d, %8lld total np ([min:%6d max:%6d mean:%6.2f  delta:%2.2f])\\n\", (#1), (#2), (#3), (#4), (#5), (#6))")
//	@Native("Java", "System.out.printf(\"stage %3d, %8lld total np ([min:%6d max:%6d mean:%6.2f  delta:%2.2f])\\n\", (#1), (#2), (#3), (#4), (#5), (#6))")
	static native def print(iter:Int,npg:Int,np_min:Int,np_max:Int,np_mean:Float,delta:Float):void;
   
   def cycle():void {
      var i:Int;
      var p:Rail[Particle] = mc.particles();

      /** minimum/maximum number of particles on a proc */
      var np_min:Int = -1n;
      var np_max:Int = -1n;
      
      /** stage */
      var iter:Int = 0n;

      /** global and local particle counts */
      var npg:Int = 0n;
      var npl:Int = 0n;
      /** global particle count on previous iteration */
      var npg_before:Int = 0n;
      
      myRecvCount(mc.mype) = 0n;
      
      var team:Team = Team.WORLD;

      val exchange: (MC, Rail[Int], Rail[Int])=>void 
									= new MC_Comm().init(mc, comm_choice);
		
		var start:Long = 0l;
		team.barrier();
		if (here.id == 0)
	 		start = System.nanoTime();

      while (true) {
         ++iter;
         npl = mc.nparticles;
         npg = 0n;
         np_min = npl + 1n;
         np_max = -1n;

         npg = team.allreduce(npl, Team.ADD);
         np_min = team.allreduce(npl, Team.MIN);
         np_max = team.allreduce(npl, Team.MAX);
/*
         np_min = team.allreduce(mc.nparticles, Team.MIN);
         np_max = team.allreduce(mc.nparticles, Team.MAX);
*/
			
         val np_mean:Float;
         val delta:Float;
			
         npg_before = npg;
         np_mean = npg / mc.nprocs;
         delta = np_max - np_mean;
			
         if (here.id == 0)
				Console.OUT.printf("stage %3d, %8lld total np ([min:%6d max:%6d mean:%6.2f  delta:%2.2f])\n", iter, npg, np_min, np_max, np_mean, delta);
//				Console.OUT.printf("stage %d, %d total np ([min:%d max:%d mean:%6.2f  delta:%2.2f])\n", iter, npg, np_min, np_max, np_mean, delta);

         if (mc.strict == 1n) 
            update_particles_strict();
         else 
         	update_particles();
//         	update_particles(mc);

         /** now that we have updated list of particles, we want
          * to remove all of the absorbed particles from the list
          * and compress the particle array to its new size 
			 */
      	npl = mc.nparticles = pack();
    		 
         /** check if any particles left. if none then we break main loop */
//         np_max = team.allreduce(mc.nparticles, Team.MAX);
         np_max = team.allreduce(npl, Team.MAX);
			
         if (np_max <= 1n)
            break; 
         

//         for (i = 0n; i < mc.nparticles; ++i) {
         for (i = 0n; i < npl; ++i) {
//            if (mc.particles()(i) == null)
            if (p(i) == null)
               continue;
			
//         	++mySendCount(mc.particles()(i).proc);
         	++mySendCount(p(i).proc);
         }
			
         team.alltoall(mySendCount, 0L, myRecvCount, 0L, 1L);

/*
      for (place in Place.places())
         Console.OUT.println("*" + here.id + ":mySendCount[" + place.id  + "]=" + mySendCount(place.id));
*/
			
         check_array_size(comm_choice, myRecvCount);
         
			/** excludes received count of my place to my place */
         myRecvCount(mc.mype) = 0n;
         
         team.barrier();

/*
         for (i = 0n; i < mc.nparticles; ++i) {
				Console.OUT.print("(" + mc.particles()(i).proc + "," + mc.particles()(i).absorbed + "), ");
			}
			Console.OUT.println();
*/
 
         exchange(mc, myRecvCount, mySendCount);
			
         if (comm_choice == mc.MC_NONBLOCK || comm_choice == mc.MC_BLOCK)
            mc.nparticles = elim_sent();
			
/*
         for (i = 0n; i < mc.nparticles; ++i) {
				Console.OUT.print(here.id + ":[" + mc.particles()(i).proc + "," + mc.particles()(i).absorbed + "], ");
			}
			Console.OUT.println();
*/

         for (i = 0n; i < mc.nprocs; ++i)
            mySendCount(i) = 0n;
      }

		team.barrier();
		if (here.id == 0)
	 		Console.ERR.println((System.nanoTime() - start)/1000000d + " (ms)");

   }

	/*---------------------------------------*/
	/* pack particles to remove holes        */
	/* particles aligned as                  */
	/* [not-absorbed staying,                */
	/*  not-absorbed leaving,                */
	/*  absorbed]                            */
	/*---------------------------------------*/
   private def pack(): Int {
      val np:Long = mc.nparticles as Long;
      var count:Int;
      val p:Rail[Particle] = mc.particles();

//    RailUtils.qsort[Particle](p, 0, np - 1, (x:Particle,y:Particle) => Particle.compare(x,y));
//		qsort2(p, 0, np-1);
		qsort3(p, 0, np-1);
		
      for (count = 0n; count < np; ++count) {
         if (p(count).absorbed == 1n)
            break;
      }

      return count; 
   }
   
   private def update_particles_strict():void {
      var np:Int = mc.nparticles;
      var leakage:Double = mc.leakage;
      var boundary_flag:Int = mc.boundary_flag;
      var nabe:Int;
      
      var npa:Int = Math.round((1 -  leakage) * np) as Int;
      var npl:Int = np - npa;
      
      /* force npl to be divisble by 6 and subBlocks by tweaking absorption slightly */
      while ((npl % 6n) != 0n) {
         --npl;
         ++npa;
      }
      
      /* set absorbed particles */
      for (var i:Int = 0n; i < npa; ++i)
         mc.particles()(i).absorbed = 1n;
      
      /* set leaving particles equally */
      for (var i:Int = 0n, j:Int = 0n; i < npl; ++i, j = i % 6n) {
         mc.particles()(npa + i).absorbed = 0n;
         nabe = mc.grid.nabes(j);
         if (nabe == -1n) 
            mc.particles()(npa + i).proc = mc.mype;
         else
            mc.particles()(npa + i).proc = nabe;
      }
   }
   
	
   private def update_particles():void {
		val np:Int = mc.nparticles; 
   	var r:Double; 
      var random_nabe_index:Int;
      var random_nabe:Int;
		val particles = mc.particles();

   	if (mc.boundary_flag == BNDRY_REFLECT) {
   	 	for (var i:Int = 0n; i < np; ++i) {
  	         
	 	  		r = Math.abs(random.random()) / (RAND_MAX + 1d);

  	 	  		if (r > mc.leakage) {
   	  			particles(i).absorbed = 1n;
   	  		}
   	  		else {
   	         random_nabe_index = (Math.abs(random.random()) / ((RAND_MAX + 1d) / 6)) as Int;
   	  			random_nabe = mc.grid.nabes(random_nabe_index);
   	  			particles(i).absorbed = 0n;

   	  			if (random_nabe != -1n) 
   	  			   particles(i).proc = random_nabe;
   	  			else	/* Enforce Boundary Condition */
   	  			   particles(i).proc = mc.mype;  /* bounce back boundary condition */ 
   	  		}
   	  }
    }
     else if (mc.boundary_flag == BNDRY_LEAK) {
   	  	  for (var i:Int = 0n; i < np; ++i) {
  	  		  	r = Math.abs(random.random()) / (RAND_MAX + 1d);
//	  		  	r = random.nextInt(RAND_MAX) / (RAND_MAX + 1d);
//   	  		r = MC_Init.rand() / (RAND_MAX + 1d);
   	  		if (r > mc.leakage) 
   	  			mc.particles()(i).absorbed = 1n;
   	  		else {
   	  			random_nabe_index = (Math.abs(random.random()) / ((RAND_MAX + 1d) / 6)) as Int;
//   	  			random_nabe_index = (Math.abs(random.nextInt(RAND_MAX)) / ((RAND_MAX + 1d) / 6)) as Int;
//   	         random_nabe_index = (Math.abs(MC_Init.rand()) / ((RAND_MAX + 1d) / 6)) as Int;
   	  			random_nabe = mc.grid.nabes(random_nabe_index);
   	  			mc.particles()(i).absorbed = 0n;
   	  			if (random_nabe != -1n) 
   	  				mc.particles()(i).proc = random_nabe;
   	  			else {
   	  				r = Math.abs(random.random()) / (RAND_MAX + 1d);
//   	  				r = random.nextInt(RAND_MAX) / (RAND_MAX + 1d);
//   	  				r = MC_Init.rand() / (RAND_MAX + 1d);
   	  				if (r > mc.leakage) 
   	  					mc.particles()(i).absorbed = 1n;
   	  				else
   	  					mc.particles()(i).proc = mc.mype;
   	  			}
   	  		}
   	  	}
   	  }
   	  else if (mc.boundary_flag == BNDRY_PERIODIC) {
   	  	for (var i:Int = 0n; i < np; ++i) {
   	  		r = Math.abs(random.random()) / (RAND_MAX + 1d);
//   	  		r = random.nextInt(RAND_MAX) / (RAND_MAX + 1d);
//   	  		r = MC_Init.rand() / (RAND_MAX + 1d);
   	  		if (r > mc.leakage) 
   	  			mc.particles()(i).absorbed = 1n;
   	  		else {
   	  			random_nabe_index = (Math.abs(random.random()) / ((RAND_MAX + 1d) / 6)) as Int;
//   	  			random_nabe_index = (Math.abs(random.nextInt(RAND_MAX)) / ((RAND_MAX + 1d) / 6)) as Int;
//   	  			random_nabe_index = (Math.abs(MC_Init.rand()) / ((RAND_MAX + 1n) / 6)) as Int;
   	  			random_nabe = mc.grid.nabes(random_nabe_index);
   	  			mc.particles()(i).absorbed = 0n;
   	  			mc.particles()(i).proc = random_nabe;
   	  		}
   	  	}
   	  }
   }
	
   private def check_array_size(comm_choice:Int, myRecvCount:Rail[Int]):void {
      val total_np:Int;   
   
		if (comm_choice != mc.MC_MADRE)
         total_np = mc.nparticles + isum(myRecvCount, mc.nprocs) - myRecvCount(mc.mype);
      else 
			total_np = isum(myRecvCount, mc.nprocs);
   	  
		if (mc.sizep < total_np) {
   		Console.OUT.printf("WARNING: not enough space to hold: %d particles while receiving: %d particles on proc: %d\n",
   	   total_np as Any,(isum(myRecvCount,mc.nprocs) - myRecvCount(mc.mype)) as Any, mc.mype as Any);
         Console.OUT.printf("reallocating....... ");
         val tmp:Rail[Particle] = mc.particles();
			val total = total_np;
			val plh = PlaceLocalHandle.make[Rail[Particle]](Place.places(), ()=>new Rail[Particle](total + 1n));
         // mc.particles = plh;
         
         mc.sizep = total_np + 1n;
         for (var i:Int = 0n; i < mc.sizep; ++i)
            mc.particles()(i) = tmp(i);
   	}
   }
   
   public static def isum(f:Rail[Int], n:Int):Int {
      var sum:Int = 0n;
      for (var i:Int = 0n; i < n; ++i)
         sum += f(i);
      
      return sum;
   }
   
   /** 
    * After exchanges, eliminate the sent particles and 
    * update the nparticles count 
    */
   private def elim_sent():Int {
      val p:Rail[Particle] = mc.particles();
      val np:Int = mc.nparticles;
      val mype:Int = mc.mype;
      var count:Int = 0n;
      
  	   qsort(mype, p, 0n, np - 1n);

      for (var i:Int = 0n; i < np; ++i) {
         if (p(i).proc == mype && p(i).absorbed == 0n)
            ++count;
      }

      return count;
   }

	static def qsort(val mype:Int, val a:Rail[Particle], val lo:Long, val hi:Long):void {
  		if (hi <= lo) 
			return;

      var l:Long = lo - 1;
      var h:Long = hi;
		var temp:Particle;

      while (true) {
         while (compare_sent(mype, a(++l), a(hi))<0);
         while (compare_sent(mype, a(hi), a(--h))<0 && h>lo);
         if (l >= h) 
				break;

    		temp = a(l);
    		a(l) = a(h);
    		a(h) = temp;
      }
    	temp = a(l);
    	a(l) = a(hi);
    	a(hi) = temp;

		qsort(mype, a, lo, h-1n);
		qsort(mype, a, l+1n, hi);
	}

   static def compare_sent(val mype:Int, val p1:Particle, val p2:Particle):Int {
      var p1_proc_match:Int = 2n;
      var p2_proc_match:Int = 2n;

      if (p1.proc == mype)
         p1_proc_match = 1n;

      if (p2.proc == mype)
         p2_proc_match = 1n;

      if (p1_proc_match > p2_proc_match)
         return 1n;
      else if (p1_proc_match < p2_proc_match)
         return -1n;
      else
         return 0n;
   }


   static def qsort3(a:Rail[Particle], lo:Long, hi:Long):void {
  		if (hi <= lo) 
			return;

      var l:Long = lo - 1;
   	var h:Long = hi;
		var temp:Particle;

		while (true) {
      	while (Particle.compare(a(++l), a(hi))<0);
         while (Particle.compare(a(hi), a(--h))<0 && h>lo);

         if (l >= h) 
				break;

  			temp = a(l);
  			a(l) = a(h);
  			a(h) = temp;
      }
  		temp = a(l);
  		a(l) = a(hi);
 		a(hi) = temp;

		qsort3(a, lo, h-1n);
		qsort3(a, l+1n, hi);
	}
}
