/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2012.
 */

package x10.matrix.distblock;

import x10.util.ArrayList;

import x10.matrix.Matrix;
import x10.matrix.DenseMatrix;
import x10.matrix.Debug;
import x10.matrix.block.Grid;
import x10.matrix.block.MatrixBlock;
import x10.matrix.block.DenseBlock;
import x10.matrix.block.SparseBlock;
import x10.matrix.block.BlockMatrix;
import x10.matrix.comm.BlockGather;
import x10.matrix.comm.BlockScatter;


public type DistBlockMatrix(M:Int, N:Int)=DistBlockMatrix{self.M==M, self.N==N};   
public type DistBlockMatrix(M:Int)=DistBlockMatrix{self.M==M}; 
public type DistBlockMatrix(C:DistBlockMatrix)=DistBlockMatrix{self==C}; 

/**
 * Distributed block matrix allows matrix partitioned in any arbitary number of blocks.
 * The number of blocks can differ from the number of places, which is not allowed in
 * DistDenseMatrix and DistSparseMatrix.
 * 
 * <p>The matrix data partitioning is separated from partitioned block distribution.
 * Matrix data partitioning is specified by Grid, and blocks distribution map is defined
 * by DistMap.
 * 
 * <p>PlaceLocalHandle is used to hold blocks assigned to places.  In each place,
 * BlockSet stores all blocks in an ArrayList, with a copy of partitioning info Grid and
 * distribution map DistMap.
 */
public class DistBlockMatrix extends Matrix{

	//public val grid:Grid;
	public val handleBS:PlaceLocalHandle[BlockSet];
	//public val local:PlaceLocalHandle[BlockMatrix(M,N)]; //Repackaged in blockmatrix

	//==============================================
	
	public def this(bs:PlaceLocalHandle[BlockSet]) {
		super(bs().grid.M, bs().grid.N);
		handleBS  = bs;

	}
	//==============================================
	
	/**
	 * Create dist block matrix using specified matrix data partitioning grid and
	 * block distribution map. No actual memory space is allocated for matrix
	 * since the matrix block type (dense/sparse) is not specified 
	 * 
	 * @param g     matrix data partitioning
	 * @param dmpa  partitioning blocks distribution map
	 * @return      DistBlockMatrix object (no memory allocation for matrix data)
	 */
	public static def make(g:Grid, dmap:DistMap) : DistBlockMatrix(g.M, g.N){
		//Remote capture: g, dmap
		val bs = PlaceLocalHandle.make[BlockSet](Dist.makeUnique(), ()=>(new BlockSet(g, dmap)));//Remote capture
		return new DistBlockMatrix(bs) as DistBlockMatrix(g.M,g.N);
	}
	
	/**
	 * Create distributed block matrix given rows and columns of matrix, and 
	 * how matrix is partitioned into blocks, which are distributed among places 
	 * in a specified row and column place grid
	 * 
	 * @param m         number of matrix rows
	 * @param n         number of matrix columns
	 * @param rowBs     number of matrix partitioning row blocks
	 * @param colBs     number of matrix partitioning column blocks
	 * @param rowPs     number of rows of place grid
	 * @param colPs     number of columns of place grid
	 */
	public static def make(m:Int, n:Int, 
			rowBs:Int, colBs:Int, 
			rowPs:Int, colPs:Int):DistBlockMatrix(m,n) {
		Debug.assure(rowPs*colPs==Place.MAX_PLACES, "Block partitioning error");
		val grid = new Grid(m, n, rowBs, colBs);		
		val dstgrid = new DistGrid(grid, rowPs, colPs);
		return DistBlockMatrix.make(grid, dstgrid.dmap);		
	}
	
	/**
	 * Create DistBlockMatrix instance using specified number blocks in row and column
	 * and default block distribution map
	 *
	 * @param m, n           number of matrix rows and columns
	 * @param rowBs, colBs   number of row and column blocks in partitioning
	 * @return DistBlockMatrix instance without memory allocation for matrix data
	 */
	public static def make(m:Int, n:Int, rowBs:Int, colBs:Int):DistBlockMatrix(m,n) {
		val grid = new Grid(m, n, rowBs, colBs);
		val dstgrid = DistGrid.make(grid);
		return DistBlockMatrix.make(grid, dstgrid.dmap);
	}
	
	/**
	 * Create DistBlockMatrix instance using default partitioning and distribution map
	 * 
	 * @param m, n           number of matrix rows and columns
	 * @return DistBlockMatrix instance
	 */
	public static def make(m:Int, n:Int):DistBlockMatrix(m,n) {
		val grid    = Grid.make(m, n);
		val dstgrid = DistGrid.make(grid);
		return DistBlockMatrix.make(grid, dstgrid.dmap);
	}
	
	/**
	 * Create DistBlockMatrix instance by using row and column matrix data partitioning
	 * function and block distribution map function.
	 * 
	 * @param m,n           number of rows and columns
	 * @param rowbs,colbs   number of partitioning row blocks and column blocks
	 * @param rowPartFunc   row block partitioning function, given row block index, returning number of rows in blocks
	 * @param colPartFunc   column blocks partitioning function, given column block index, returning number of column in blocks
	 * @param mapFunc       block distribution map function, given a block ID, returning a place ID.
	 */
	public static def make(
			m:Int, n:Int, 
			rowbs:Int, colbs:Int,
			rowPartFunc:(Int)=>Int, 
			colPartFunc:(Int)=>Int, 
			mapFunc:(Int)=>Int) {
		
		val ttbs = rowbs * colbs;
		val blks = PlaceLocalHandle.make[BlockSet](Dist.makeUnique(), 
				()=>(new BlockSet(Grid.make(rowbs, colbs, rowPartFunc, colPartFunc), DistMap.make(ttbs, mapFunc))));
		return new DistBlockMatrix(blks);
	}
	
	//==============================================
	
	//Make a copy, but sharing partitioning grid and distribution map
	public static def makeDense(d:DistBlockMatrix):DistBlockMatrix(d.M,d.N) {
		val sblks = d.handleBS;
		val dblks = PlaceLocalHandle.make[BlockSet](Dist.makeUnique(), 
				()=>(BlockSet.makeDense(sblks().grid, sblks().dmap)));

		val nm = new DistBlockMatrix(dblks);
		return nm as DistBlockMatrix(d.M,d.N);
	}

	public static def makeDense(g:Grid, d:DistMap):DistBlockMatrix(g.M,g.N) {
		val bs = PlaceLocalHandle.make[BlockSet](Dist.makeUnique(), 
				()=>(BlockSet.makeDense(g, d)));//Remote capture
		return new DistBlockMatrix(bs) as DistBlockMatrix(g.M,g.N);		
	}
	
	public static def makeSparse(g:Grid, d:DistMap, nzp:Double):DistBlockMatrix(g.M,g.N) {
		val bs = PlaceLocalHandle.make[BlockSet](Dist.makeUnique(), 
				()=>(BlockSet.makeSparse(g, d, nzp)));//Remote capture
		return new DistBlockMatrix(bs) as DistBlockMatrix(g.M,g.N);		
	}
	
	public static def makeDense(m:Int, n:Int, rbs:Int, cbs:Int) =
		make(m, n, rbs, cbs).allocDenseBlocks();
	
	public static def makeSparse(m:Int, n:Int, rbs:Int, cbs:Int, npz:Double) =
		make(m, n, rbs, cbs).allocSparseBlocks(npz);
	
	//====================================================
	/**
	 * Allocate dense matrix for all blocks
	 */
	public def allocDenseBlocks():DistBlockMatrix(this) {

		finish ateach (d:Point in Dist.makeUnique()) {
			handleBS().allocDenseBlocks();
		}
		return this;
	}

	/**
	 * Allocate sparse matrix for all blocks
	 */
	public def allocSparseBlocks(nzd:Double):DistBlockMatrix(this) {
		//Remote capture: nnz
		finish ateach (d:Point in Dist.makeUnique()) {
			handleBS().allocSparseBlocks(nzd);
		}
		return this;
	}
	
	//================================================
	
	/**
	 * Used to create temporary space in SUMMA. This method does not creat a complete 
	 * distributed block matrix. It only creates the first blocks in each row of 
	 * the current this block set. It is used to as temp space to store data from
	 * the first operand matrix in SUMMA
	 */
	public def makeTempFrontRowBlocks(rowCnt:Int) =
		PlaceLocalHandle.make[BlockSet](Dist.makeUnique(),
				()=>this.handleBS().makeFrontRowBlockSet(rowCnt));
	
	/**
	 * Used to creat temporary space in SUMMA. This method does not creat a complete 
	 * distributed block matrix. It only creates the first blocks in each column of 
	 * the current this block set. It is used to as temp space to store data from
	 * the second operand matrix in SUMMA
	 */
	public def makeTempFrontColBlocks(colCnt:Int) =
		PlaceLocalHandle.make[BlockSet](Dist.makeUnique(),
				()=>this.handleBS().makeFrontColBlockSet(colCnt));
	
	//================================================
	public def init(dval:Double) : DistBlockMatrix(this){
		//Remote capture: dval 
		finish ateach (d:Point in Dist.makeUnique()) {
			val blks = handleBS();
			val blkitr = blks.iterator();
			while (blkitr.hasNext()) {
				val blk = blkitr.next();
				blk.init(dval);
			}
		}
		return this;
	}
	
	public def initRandom() : DistBlockMatrix(this){
		finish ateach (d:Point in Dist.makeUnique()) {
			val blkitr = handleBS().iterator();
			while (blkitr.hasNext()) {
				val blk = blkitr.next();
				blk.initRandom();
			}
		}
		return this;
	}
	
	public def initRandom(lb:Int, ub:Int) : DistBlockMatrix(this){
		finish ateach (d:Point in Dist.makeUnique()) {
			val blkitr = handleBS().iterator();
			while (blkitr.hasNext()) {
				val blk = blkitr.next();
				blk.initRandom(lb, ub);
			}
		}
		return this;
	}	

	/**
	 * Initial DistBlockMatrix with a given function
	 */
	public def init(f:(Int,Int)=>Double): DistBlockMatrix(this) {
		finish ateach (d:Point in Dist.makeUnique()) {
			val blks   = handleBS();
			val grid   = blks.grid;
			val blkitr = blks.iterator();
			while (blkitr.hasNext()) {
				val blk = blkitr.next();
				val strow = grid.startRow(blk.myRowId);
				val stcol = grid.startColumn(blk.myColId);
				blk.init(strow, stcol, f);
			}
		}
		return this;
	}
	
	/**
	 * Initial specified block
	 */
	public def initBlock(bid:Int, f:(Int,Int)=>Double): DistBlockMatrix(this) {
		val pid = this.getMap().findPlace(bid);
		at (Dist.makeUnique()(pid)) {
			val blk = handleBS().findBlock(bid);
			blk.init(f);
		}
		return this;
	}
	
	public def initBlock(rowId:Int,colId:Int, f:(Int,Int)=>Double) =
		initBlock(getGrid().getBlockId(rowId, colId), f);
	
	//=============================================
	/**
	 * Allocate memory space for new dist block matrix using the same
	 * matrix partitioning and block distribution as this.
	 */
	public def alloc(m:Int, n:Int) : DistBlockMatrix(m,n) {
		Debug.assure(m==M&&n==N, "Matrix dimension is not same");
		val nm = DistBlockMatrix.make(getGrid(), getMap()) as DistBlockMatrix(m,n);
		finish ateach (d:Point in Dist.makeUnique()) {
			val blkitr = handleBS().iterator();
			val nblk   = nm.handleBS();
			while (blkitr.hasNext()) {
				val mb = blkitr.next();
				nblk.add(mb.alloc());
			}
		}
		return nm;
		//throw new UnsupportedOperationException();
	}
	
	public def clone():DistBlockMatrix(M,N) {
		
		val bs = PlaceLocalHandle.make[BlockSet](Dist.makeUnique(), 	
					()=>(this.handleBS().clone()));
		
		return new DistBlockMatrix(bs) as DistBlockMatrix(M,N);
	}
	
	public def reset() {
		finish ateach (d:Point in Dist.makeUnique()) {
			handleBS().reset();
		}
	}
	
	//=============================================
	// Copy
	//=============================================

	public def copyTo(that:DistBlockMatrix(M,N)) {
		finish ateach (d in Dist.makeUnique()) {
			val sit  = this.handleBS().iterator();
			val dit  = that.handleBS().iterator();
			while (sit.hasNext()&&dit.hasNext()) {
				val sblk = sit.next();
				val dblk = dit.next();
				Debug.assure(sblk.myRowId==dblk.myRowId && sblk.myColId==dblk.myColId,
						"Block mismatch in DistBlockMatrix copyTo");
				val smat = sblk.getMatrix();
				val dmat = dblk.getMatrix();
				smat.copyTo(dmat as Matrix(smat.M, smat.N));
			}
		}
	}
	
	public def copyTo(dst:BlockMatrix(M,N)) {
		val srcgrid = this.getGrid();
		Debug.assure(srcgrid.equals(dst.grid),
			"source and destionation matrix partitions are not compatible");
		BlockGather.gather(this.handleBS, dst.listBs);
	}
	
	public def copyTo(dst:Matrix(M,N)):void {
		val grid = getGrid();
		if (dst instanceof DistBlockMatrix) {
			copyTo(dst as DistBlockMatrix(M,N));
		} else if (dst instanceof BlockMatrix) {
			copyTo(dst as BlockMatrix(M,N));
		} else if ((dst.N == 1)&&( dst instanceof DenseMatrix)) {
			BlockGather.gatherVector(this.handleBS, dst as DenseMatrix(M,1));
		} else if (grid.numRowBlocks==1) {
			BlockGather.gatherRowBs(this.handleBS, dst);
		} else {
			Debug.exit("Not supported matrix type for converting DistBlockMatrix");
		}
	}
	
	public def copyTo(den:DenseMatrix(M,N)): void {
		if (den.N == 1) {
			BlockGather.gatherVector(this.handleBS, den as DenseMatrix(M,1));
		} else {
			Debug.exit("DistBlockMatrix does not support direct copyTo densematrix,"+
					"unless it is single-column matrix (vector)."+
					"Workaround is to use inter-media BlockMatrix to save gathered blocks"+
					"and then convert to dense by calling BlockMatrix.copyTo(DenseMatrix)");
		}
	}

	public def copyFrom(src:BlockMatrix(M,N)) {
		val dstgrid = getGrid();
		Debug.assure(dstgrid.equals(src.grid),
		"source and destionation matrix partitions are not compatible");
		BlockScatter.scatter(src.listBs, this.handleBS);
	}
	
	//=============================================
	public  operator this(x:Int, y:Int):Double {
		val grid = handleBS().grid;
		val loc = grid.find(x, y);
		val bid = grid.getBlockId(loc(0), loc(1));
		val bx  = loc(2);
		val by  = loc(3);
		val pid = handleBS().dmap.findPlace(bid);
		//Remote capture: bid, bx, by, 
		val dv = at (Place.place(pid)) {
			val blkset:BlockSet = this.handleBS();
			val blk:MatrixBlock = blkset.find(bid);
			if (blk == null) 
				Debug.exit("Error in search blocks in block set");
			
			blk(bx, by)
		};
		return dv;
	}
	public operator this(x:Int, y:Int)=(d:Double):Double {
		val grid = handleBS().grid;
		val loc = grid.find(x, y);
		val bid = grid.getBlockId(loc(0), loc(1));
		val bx  = loc(2);
		val by  = loc(3);
		val pid = handleBS().dmap.findPlace(bid);
		//Remote capture: bid, bx, by, 
		at (Place.place(pid)) {
			val blkset:BlockSet = handleBS();
			val blk:MatrixBlock = blkset.find(bid);
			if (blk == null) 
				Debug.exit("Error in search blocks in block set");
			
			blk.getMatrix()(bx, by) = d;
		}
		return d;
	}
	
	//--------------------------------------------
	/**
	 * Get block to here. If block is not at local, it will be remote captured
	 * and compied to here.
	 */
	public def fetchBlock(bid:Int):MatrixBlock {
		val map = getMap();
		val pid = map.findPlace(bid);
		val blk = at (Dist.makeUnique()(pid)) handleBS().findBlock(bid);
		return blk;
	}
	public def fetchBlock(rid:Int, cid:Int):MatrixBlock =
		fetchBlock(getGrid().getBlockId(rid, cid));

	public def getGrid():Grid   = this.handleBS().grid;
	public def getMap():DistMap = this.handleBS().dmap;

	//=============================================

	//=============================================
	public def scale(alpha:Double): DistBlockMatrix(this) {
		finish ateach (d:Point in Dist.makeUnique()) {
			val blkitr = this.handleBS().iterator();
			while (blkitr.hasNext()) {
				val blk = blkitr.next();
				blk.getMatrix().scale(alpha);
			}
		}
		return this;
	}
	
	//=============================================

	public def cellAdd(that:Matrix(M,N)): Matrix(this)  {
		if (! likeMe(that))
			throw new UnsupportedOperationException("Distributed matrix not compatible");
		return cellAdd(that as DistBlockMatrix(M,N));
	}
	
	public def cellAdd(dv:Double): DistBlockMatrix(this) {
		finish ateach (p in Dist.makeUnique())  {
			//Remote capture: dv
			val bitr:Iterator[MatrixBlock] = this.handleBS().iterator();
			
			while (bitr.hasNext()) {
				val b:MatrixBlock = bitr.next();
				val mat = b.getMatrix();
				mat.cellAdd(dv);
			}
		}
		return this;
	}
		
	public def cellAdd(A:DistBlockMatrix(M,N)): DistBlockMatrix(this)  {
		if (! likeMe(A))
			throw new UnsupportedOperationException("Distributed matrix not compatible");
		
		finish ateach (p in Dist.makeUnique())  {
			val bsetA= A.handleBS();
			val itr = this.handleBS().iterator();
			while (itr.hasNext()) {
			 	val b1:MatrixBlock = itr.next();
			 	val m1:Matrix      = b1.getMatrix();
			 	val b2:MatrixBlock = bsetA.find(b1.myRowId, b1.myColId);
			 	
			 	if (b2 == null) Debug.exit("Can not find corresponding block");
			 	m1.cellAdd(b2.getMatrix() as Matrix(m1.M, m1.N));
			}
		}
		return this;
	}
	
	protected def cellAddTo(x:DenseMatrix(M,N)):DenseMatrix(x) {
		throw new UnsupportedOperationException("Matrix type missmatch for the operation");				
	}
	
	//=============================================
	public def cellSub(A:Matrix(M,N)): Matrix(this) {
		if (! likeMe(A))
			throw new UnsupportedOperationException("Distributed matrix not compatible for computing");
		return cellSub(A as DistBlockMatrix(M,N));
	}
	
	public def cellSub(A:DistBlockMatrix(M,N)): DistBlockMatrix(this) {
		if (! likeMe(A))
			throw new UnsupportedOperationException("Distributed matrix not compatible for computing");
		
		finish ateach(p in Dist.makeUnique()) {
			val itr = this.handleBS().iterator();
			val blkA = A.handleBS();
			while (itr.hasNext()) {
				val b1 = itr.next();
				val m1 = b1.getMatrix();
				val b2 = blkA.find(b1.myRowId, b1.myColId);
				if (b2 == null) Debug.exit("Can not find corresponding block");
				m1.cellSub(b2.getMatrix() as Matrix(m1.M, m1.N));
			}
		}
		return this;	
	}
	
	protected def cellSubFrom(x:DenseMatrix(M,N)):DenseMatrix(x) {
		throw new UnsupportedOperationException("Matrix type mismatch");						
	}
	
	public def cellSubFrom(dv:Double): DistBlockMatrix(this) {
		finish ateach (p in Dist.makeUnique())  {
			//Remote capture: dv
			val bitr:Iterator[MatrixBlock] = this.handleBS().iterator();
			
			while (bitr.hasNext()) {
				val b:MatrixBlock = bitr.next();
				val mat = b.getMatrix();
				mat.cellSubFrom(dv);
			}
		}
		return this;
	}
	//=============================================
	public def cellMult(A:Matrix(M,N)): Matrix(this) {
		if (! likeMe(A))
			throw new UnsupportedOperationException("Distributed matrix not compatible for computing");
		return cellMult(A as DistBlockMatrix(M,N));	
	}

	public def cellMult(A:DistBlockMatrix(M,N)): DistBlockMatrix(this) {
		if (! likeMe(A))
			throw new UnsupportedOperationException("Distributed matrix not compatible for computing");
		finish ateach(p in Dist.makeUnique()) {
			val itr  = this.handleBS().iterator();
			val blkA = A.handleBS();
			while (itr.hasNext()) {
				val b1 = itr.next();
				val m1 = b1.getMatrix();
				val b2 = blkA.find(b1.myRowId, b1.myColId);
				if (b2 == null) Debug.exit("Can not find corresponding block");
				m1.cellMult(b2.getMatrix() as Matrix(m1.M, m1.N));
			}
		}
		return this;
	}
		
	protected def cellMultTo(x:DenseMatrix(M,N)):DenseMatrix(x) {
		throw new UnsupportedOperationException("Matrix type mismatch");							
	}
	//=============================================
	public def cellDiv(A:Matrix(M,N)):Matrix(this) {
		if (! likeMe(A))
			throw new UnsupportedOperationException("Distributed matrix not compatible for computing");
		return cellDiv(A as DistBlockMatrix(M,N));
	}
	
	public def cellDiv(A:DistBlockMatrix(M,N)):DistBlockMatrix(this) {
		if (! likeMe(A))
			throw new UnsupportedOperationException("Distributed matrix not compatible for computing");
		
		finish ateach(p in Dist.makeUnique()) {
			val blkit1 = this.handleBS().iterator();
			val blkit2 = A.handleBS().iterator();
			while (blkit1.hasNext()&&blkit2.hasNext()) {
				val m1 = blkit1.next().getMatrix();
				val m2 = blkit2.next().getMatrix();
				m1.cellDiv(m2 as Matrix(m1.M, m1.N));
			}
		}
		return this;
	}
	
	protected def cellDivBy(x:DenseMatrix(M,N)):DenseMatrix(x) {
		throw new UnsupportedOperationException();
	}
	
	//=============================================	
	public def mult(A:Matrix(this.M),B:Matrix(A.N,this.N), plus:Boolean):Matrix(this) {
		throw new UnsupportedOperationException();	
	}
	
	public def transMult(A:Matrix{self.N==this.M},B:Matrix(A.M,this.N),plus:Boolean):Matrix(this){
		throw new UnsupportedOperationException();			
	}
	
	public def multTrans(A:Matrix(this.M),B:Matrix(this.N, A.N),plus:Boolean):Matrix(this) {
		throw new UnsupportedOperationException();					
	}
	
	//=============================================
	// Operator overload
	//=============================================
	
	public operator this + (dv:Double) = this.clone().cellAdd(dv) as DistBlockMatrix(M,N);
	public operator this - (dv:Double) = this.clone().cellAdd(-dv) as DistBlockMatrix(M,N);
	public operator (dv:Double) + this = this.clone().cellAdd(dv) as DistBlockMatrix(M,N);
	public operator (dv:Double) - this = this.clone().cellSubFrom(-dv) as DistBlockMatrix(M,N);
	
	public operator this + (that:DistBlockMatrix(M,N)) = this.clone().cellAdd(that) as DistBlockMatrix(M,N);
	public operator this - (that:DistBlockMatrix(M,N)) = this.clone().cellSub(that) as DistBlockMatrix(M,N);
	public operator this * (that:DistBlockMatrix(M,N)) = this.clone().cellMult(that) as DistBlockMatrix(M,N);	
	public operator this / (that:DistBlockMatrix(M,N)) = this.clone().cellDiv(that) as DistBlockMatrix(M,N);
	
	//=============================================
	// Util
	//=============================================
	
	/**
	 * Build block map in all places to allow fast access local block given block row and column id.
	 * Do not call this method, if distribution of blocks is not grid-like.
	 */
	public def buildBlockMap() {
		finish ateach (Dist.makeUnique()) {
			handleBS().buildBlockMap();
		}
	}
	
	// public def buildRowCastPlaceMap() {
	// 	finish ateach (Dist.makeUnique()) {
	// 		val bs = handleBS();
	// 		if (bs.rowCastPlaceMap == null) {
	// 			bs.rowCastPlaceMap = CastPlaceMap.buildRowCastMap(bs.grid, bs.dmap);				
	// 		}
	// 	}
	// }
	// 
	// public def buildColCastPlaceMap() {
	// 	finish ateach (Dist.makeUnique()) {
	// 		val bs = handleBS();
	// 		if (bs.colCastPlaceMap == null) {
	// 			bs.colCastPlaceMap = CastPlaceMap.buildColCastMap(bs.grid, bs.dmap);
	// 		}
	// 	}
	// }	
	
	//===============================================
	public def likeMe(A:Matrix):Boolean {
		if (A instanceof DistBlockMatrix) {
			val srcBs = this.handleBS();
			val dstBs = (A as DistBlockMatrix).handleBS();
			
			return ((dstBs.grid.equals(srcBs.grid)) &&
					(dstBs.dmap.equals(srcBs.dmap)));
		}
		return false;
	}
	//=============================================

	public def localSync() {
		finish ateach (p:Point in Dist.makeUnique()) {
			val bset = handleBS();
			bset.sync(bset.getFirst());
		}
	}
	//=============================================
	
	/**
	 * Check all blocks are same or not
	 */
	public def checkAllBlocksEqual() : Boolean {
		val rtmat:Matrix = handleBS().getFirst().getMatrix();
		var retval:Boolean = true;
		for (var p:Int =0 ; p<Place.MAX_PLACES && retval; p++) {
			//Debug.flushln("Check block local sync at "+p);
			if (here.id() != p) {
				retval &= at (Dist.makeUnique()(p)) {
					//Remote capture: rtmat
					handleBS().allEqual(rtmat)				
				};
			} else {
				retval &= handleBS().allEqual(rtmat);
			}
			
			if (!retval) 
				Console.OUT.println("Integrity check failed at place "+p);
		}
		//Debug.flushln("Check block local sync done");
		return retval;
	}
	
	public def getTotalDataSize():Int {
		var dsz:Int=0;
		for (p in Place.places()) {
			val c:Int =  at (p) { handleBS().getAllBlocksDataSize()};
			dsz += c;
		}
		return dsz;
	}
	
	//==================================================================================
	public def toStringBlock() :String {
		var output:String = "-------- Dist Matrix Block size:["+M+" x "+N+"] ---------\n";
		for (p in Place.places()) {
			output += at (p) { handleBS().toString()};
		}
		output += "--------------------------------------------------\n";
		return output;
	}
	
	public def print() : void { 
		print("");
	}
	
	public def print(msg:String) {
		Console.OUT.print(msg+toStringBlock());
		Console.OUT.flush();
	}
	
}
