import x10.compiler.*;
import x10.util.OptionsParser;
import x10.util.Option;
import x10.lang.Math;
import x10.util.Random;
import x10.util.Stack;

public class UTS {

	static type PLH= PlaceLocalHandle[ParUTS];
	private static val NORMALIZER = 2147483648.0; // does not depend on input parameters

  public static struct Constants {
    public static val BINOMIAL = 0;
    public static val GEOMETRIC = 1;
    public static val HYBRID = 2;

    public static val LINEAR = 0;
    public static val EXPDEC = 1;
    public static val CYCLIC = 2;
    public static val FIXED = 3;
  }


	@NativeRep ("c++", "UTS__SHA1Rand", "UTS__SHA1Rand", null)
	@NativeCPPCompilationUnit ("sha1.c")
	@NativeCPPCompilationUnit ("UTS__SHA1Rand.cc")
	public static struct SHA1Rand {
		public def this (seed:Int) { }
	global safe public def toString():String = "<" + hashCode()+">";
    public def this (parent:SHA1Rand) { }
		public def this (parent:SHA1Rand, spawnNumber:Int) { }
		@Native ("c++", "UTS__SHA1Rand_methods::apply(#0)")
		public def apply () : Int = 0;
	}

  // A structure that captures a node in the tree as depth + SHA1Rand
  public static struct TreeNode {
    val depth:Int;
    val rng:SHA1Rand;

    public def this (seed:Int) { 
      this.depth = -1;
      this.rng = SHA1Rand(seed);
    }

    public def this (seed:Int, depth:Int) {
      this.depth = depth;
      this.rng = SHA1Rand (seed);
    }

    public def this (parent:TreeNode, spawnNumber:Int) {
      this.depth = -1;
      this.rng = SHA1Rand (parent.getSHA1Rand(), spawnNumber);
    }

    public def this (parent:TreeNode, spawnNumber:Int, depth:Int) {
      this.depth = depth;
      this.rng = SHA1Rand (parent.getSHA1Rand(), spawnNumber);
    }

    public def apply (): Int = this.rng();

    public def getDepth () : Int = this.depth;

    public def getSHA1Rand (): SHA1Rand = this.rng;
	public global safe  def toString():String = "<" + depth + " " + rng + ">";
  }

    static class SeqUTS {
	val b0:Int;
	val q:Long, m:Int;
	val a:Int, d:Int;
	val treeType:Int;
	val stack:Deque[TreeNode]! = new Deque[TreeNode]();
	var nodesCounter:UInt = 0;
	val stopCount:UInt = 25;

	public def this (b0:Int, q:Long, m:Int) {
	    this.treeType = Constants.BINOMIAL; this.b0 = b0; this.q = q; this.m = m; 
	    this.a = this.d = -1;
	}
  

	public def this (b0:Int, a:Int, d:Int) {
	    this.treeType = Constants.GEOMETRIC; this.b0 = b0; this.a = a; this.d = d; 
	    this.q = -1; this.m = -1;
	}

    public final def processStack () {

	while (stack.size() > 0u) {
	    if (Constants.BINOMIAL==treeType) 
		TreeExpander.binomial (q, m, stack.pop(), stack);
	    else TreeExpander.geometric (a, b0, d, stack.pop(), stack);
	    ++nodesCounter;
	}

    }
  
	public final def main (rootNode:TreeNode) {
	    if (Constants.BINOMIAL==treeType) {
		TreeExpander.processBinomialRoot (b0, rootNode, stack);
	    } else { 
		TreeExpander.geometric (a, b0, d, rootNode, stack);
	    }
	    ++nodesCounter; // root node is counted -- so count it here.

	    this.processStack();

	    Console.OUT.println(nodesCounter+" nodes. ");

	    return nodesCounter;
	}
    }
	
	public static def main (args : Rail[String]!) {
		try {
			val opts = new OptionsParser(args, null,
					[Option("t", "", "Tree type 0: BIN, 1: GEO, 2: HYBRID"),
					 Option("b", "", "Root branching factor"),
					 Option("r", "", "Root seed (0 <= r <= 2^31"),
					 Option("a", "", "Tree shape function"),
					 Option("d", "", "Tree depth"),
					 Option("s", "", "Sequential"),
					 Option("e", "", "Event logs, default 0 (no)."),
					 Option("q", "", "BIN: probability of a non-leaf node"),
					 Option("m", "", "BIN: number of children for non-leaf node"),
					 Option("k", "", "Number of items to steal; default 0. If 0, steal half. "),
					 Option("v", "", "Verbose, default 0 (no)."),
					 Option("n", "", "Number of nodes to process before probing."),
					 Option("w", "", "Number of thieves to send out, less 1. (Default 0, so 1 thief will be sent out."),
           Option("l", "", "Lifeline method: 0 for linear, 1 for hypercube, 2 for sparse chunked, 3 for sparse embedding -- in which case also enter dimension"),
           Option("z", "", "Dimension of the sparse hypercube")
					 ]);

			val t:Int = opts ("-t", 0);
			val b0 = opts ("-b", 4);
			val seq = opts("-s", 0);
			val r:Int = opts ("-r", 0);
			val verbose = opts("-v",0)==1;
			val nu:Int = opts("-n",200);
			val w:Int = opts("-w", 0);
			val e = opts("-e", 0)==1;

			// geometric options
			val a:Int = opts ("-a", 0);
			val d:Int = opts ("-d", 6);

			// binomial options
			val q:Double = opts ("-q", 15.0/64.0);
			val mf:Int = opts ("-m", 4);
			val k:Int = opts ("-k", 0);

			// hybrid options
			val geo_to_bin_shift_depth_ratio:Double = opts ("-f", 0.5);

      // Figure out what kind of connectivity is needed.
      val l:Int = opts ("-l", 3);
      val z:Int = opts ("-z", 1);

			Console.OUT.println("--------");
			Console.OUT.println("Places="+Place.MAX_PLACES);
      if (Constants.BINOMIAL==t) {
				Console.OUT.println("b0=" + b0 +
						"   r=" + r +
						"   m=" + mf +
						"   s=" + seq +
						"   w=" + w +
						"   n=" + nu +
						"   q=" + q +
            "   l=" + l + 
            "   z=" + z +
            (l==3 ?" base=" + (z==0? 0: NetworkGenerator.findW(Place.MAX_PLACES, z)) : "")
             );
      } else if (Constants.GEOMETRIC==t) {
				Console.OUT.println("b0=" + b0 +
						"   r=" + r +
						"   a=" + a +
						"   d=" + d +
						"   s=" + seq +
						"   w=" + w +
						"   n=" + nu +
            "   l=" + l + 
            "   z=" + z +
            (l==3 ?" base=" + NetworkGenerator.findW(Place.MAX_PLACES, z) : "")
             );
      } else {

      }

			val qq = (q*NORMALIZER) as Long;

			if (seq != 0) {
				var time:Long = System.nanoTime();
			  val nodes = (Constants.BINOMIAL==t) ? 
                             new SeqUTS (b0, qq, mf).main(TreeNode(r)):
                             new SeqUTS (b0, a, d).main(TreeNode(r, 0));
			time = System.nanoTime() - time;
			Console.OUT.println("Performance = "+nodes+"/"+(time/1E9)+"="+ (nodes/(time/1E3)) + "M nodes/s");
			} else {
        // Generate the lifelineNetwork
        val lifelineNetwork:ValRail[ValRail[Int]] = 
          (0==l) ? NetworkGenerator.generateRing(Place.MAX_PLACES) :
          (1==l) ? NetworkGenerator.generateHyperCube(Place.MAX_PLACES):
          (2==l) ? NetworkGenerator.generateChunkedGraph (Place.MAX_PLACES, z):
                NetworkGenerator.generateSparseEmbedding (Place.MAX_PLACES, z);
                        

				val st = (Constants.BINOMIAL==t) ? 
     PlaceLocalHandle.make[ParUTS](Dist.makeUnique(), 
	   ()=>new ParUTS(b0, qq, mf,k,nu, w, e, l, lifelineNetwork(here.id))):
     PlaceLocalHandle.make[ParUTS](Dist.makeUnique(), 
	   ()=>new ParUTS(b0, a, d, k, nu, w, e, l, lifelineNetwork(here.id)));

				Console.OUT.println("Starting...");
				var time:Long = System.nanoTime();
				try {
				 if (Constants.BINOMIAL==t) st().main(st, TreeNode(r));
         else st().main(st, TreeNode(r, 0));
				} catch (v:Throwable) {
					v.printStackTrace();
				}
				time = System.nanoTime() - time;
				Console.OUT.println("Finished.");
				st().counter.stats(st, time, verbose);
			}
			Console.OUT.println("--------");

		} catch (e:Throwable) {
			e.printStackTrace(Console.ERR);
		}
	}
}

// vim: ts=2:sw=2:et
