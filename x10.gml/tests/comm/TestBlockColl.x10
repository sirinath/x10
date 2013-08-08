/*
 *  This file is part of the X10 Applications project.
 *
 *  (C) Copyright IBM Corporation 2011.
 */

import x10.compiler.Ifndef;
import x10.util.Timer;

import x10.matrix.Matrix;
import x10.matrix.Debug;
import x10.matrix.block.BlockMatrix;
import x10.matrix.block.Grid;
import x10.matrix.distblock.DistBlockMatrix;

import x10.matrix.comm.BlockBcast;
import x10.matrix.comm.BlockScatter;
import x10.matrix.comm.BlockGather;
import x10.matrix.comm.BlockReduce;

/**
 * This class contains test cases for collective communication for block matrices.
 */
public class TestBlockColl{
    public static def main(args:Rail[String]) {
		val m = args.size > 0 ? Long.parse(args(0)):2;
		val n = args.size > 1 ? Long.parse(args(1)):3;
		val bm= args.size > 2 ? Long.parse(args(2)):2;
		val bn= args.size > 3 ? Long.parse(args(3)):2;
		val d = args.size > 4 ? Double.parse(args(4)):0.9;
		val testcase = new BlockCollTest(m, n, bm, bn, d);
		testcase.run();
	}
}

class BlockCollTest {
	public val M:Long;
	public val N:Long;
	public val nzdensity:Double;
	public val bM:Long;
	public val bN:Long;
	
	public val numplace:Long;

	public val dbmat:DistBlockMatrix;
	public val sbmat:DistBlockMatrix;
	public val tmpmat:DistBlockMatrix;

	public val dblks:BlockMatrix;
	public val sblks:BlockMatrix;
	
	public val rootbid:Long = 0;
	
    public def this(m:Long, n:Long, bm:Long, bn:Long, d:Double) {
		M=m; N=n;
		nzdensity = d;
		bM = bm; bN = bn;
				
		dbmat = DistBlockMatrix.makeDense(m*bm, n*bn, bm, bn);
		tmpmat = DistBlockMatrix.makeDense(m*bm, n*bn, bm, bn);
		sbmat = DistBlockMatrix.makeSparse(m*bm, n*bn, bm, bn, nzdensity);
		
		dbmat.initBlock(rootbid, (x:Long, y:Long)=>(1.0+x+y));
		sbmat.initBlock(rootbid, (x:Long, y:Long)=>1.0*(x+y)*((x+y)%2));
		
		dblks = BlockMatrix.makeDense(dbmat.getGrid());
		sblks = BlockMatrix.makeSparse(sbmat.getGrid(), nzdensity);
		
		numplace = Place.numPlaces();
	}
	
	public def run(): void {
 		// Set the matrix function
		var retval:Boolean = true;
	@Ifndef("MPI_COMMU") { // TODO Deadlocks!

		Console.OUT.println("****************************************************************");
		Console.OUT.println("Test dense blocks collective commu in distributed block matrix");
		Console.OUT.println("****************************************************************");

		retval &= testBcast(dbmat);
		retval &= testGather(dbmat, dblks);
		retval &= testScatter(dblks, dbmat);

		Console.OUT.println("****************************************************************");
		Console.OUT.println("Test sparse blocks collective commu in distributed block matrix");	
		Console.OUT.println("****************************************************************");
		retval &= testBcast(sbmat);
		retval &= testGather(sbmat, sblks);
		retval &= testScatter(sblks, sbmat);

		if (retval) 
			Console.OUT.println("Block communication test collective commu passed!");
		else
			Console.OUT.println("------------Block communication test collective commu failed!-----------");
    }
	}

	public def testBcast(bmat:DistBlockMatrix):Boolean {
		var ret:Boolean = true;
		var ds:Long = 0L;
		var avgt:Double=0;
		Console.OUT.println("\nTest Bcast on dist block matrix, each block ("+M+"x"+N+") "+
				"("+bM+","+bN+") blocks over "+ numplace+" places");
		
		for (var rtbid:Long=0; rtbid < bmat.getGrid().size && ret; rtbid++) {
			bmat.reset();
			bmat.initBlock(rtbid, (r:Long, c:Long)=>(1.0+r+c)*((r+c)%3));
			val st:Long =  Timer.milliTime();
			BlockBcast.bcast(bmat.handleBS, rtbid);
			avgt += (Timer.milliTime() - st);
			ret &= bmat.checkAllBlocksEqual();
		}
	
		Console.OUT.printf("Bcast %d bytes average time: %.3f ms\n", 
						   ds*8, avgt/bmat.getGrid().size);
		
		//ret = dbmat.syncCheck();
		if (ret)
			Console.OUT.println("Bcast dist block matrix passed!");
		else
			Console.OUT.println("--------Bcast block matrix test failed!--------");
		
		return ret;

	} 	
	
	public def testGather(distmat:DistBlockMatrix, blksmat:BlockMatrix):Boolean {
		var ret:Boolean = true;
		
		Console.OUT.printf("\nTest gather of dist block matrix over %d places\n", numplace);
		blksmat.reset();
		
		Debug.flushln("Start gathering "+numplace+" places");
		var st:Long =  Timer.milliTime();
		BlockGather.gather(distmat.handleBS, blksmat.listBs);
		//Debug.flushln("Done");
		ret = distmat.equals(blksmat as Matrix(distmat.M, distmat.N));
		Debug.flushln("Done verification");

		if (ret)
			Console.OUT.println("Test gather for dist block matrix test passed!");
		else
			Console.OUT.println("-----Test gather for dist block matrix failed!-----");
		return ret;
	}
	
	public def testScatter(blksmat:BlockMatrix, distmat:DistBlockMatrix):Boolean {
		var ret:Boolean = true;
		
		Console.OUT.printf("\nTest scatter of dist block matrix over %d places\n", numplace);
		distmat.reset();
		
		Debug.flushln("Start scatter among "+numplace+" places");
		var st:Long =  Timer.milliTime();
		BlockScatter.scatter(blksmat.listBs, distmat.handleBS);
		//Debug.flushln("Done");
		
		ret = distmat.equals(blksmat as Matrix(distmat.M,distmat.N));
		Debug.flushln("Done verification");

		if (ret)
			Console.OUT.println("Test scatter for dist block matrix test passed!");
		else
			Console.OUT.println("-----Test scatter for dist block matrix failed!-----");
		return ret;
	}
}
