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

package x10.matrix.distblock.summa;

import x10.util.Timer;
import x10.util.ArrayList;

import x10.matrix.Matrix;
import x10.matrix.DenseMatrix;
import x10.matrix.Debug;
import x10.matrix.MathTool;

import x10.matrix.sparse.CompressArray;
import x10.matrix.sparse.SparseCSC;

import x10.matrix.block.Grid;
import x10.matrix.block.MatrixBlock;
import x10.matrix.block.BlockBlockMult;

import x10.matrix.distblock.DistMap;
import x10.matrix.distblock.BlockSet;
import x10.matrix.distblock.DistBlockMatrix;
import x10.matrix.distblock.DupBlockMatrix;

/**
 * SUMMA implementation on distributed block matrix
 */
public class SummaMult {
	//val alpha:Double;
	val beta:Double;
	val panelSize :Int;
	//
	val A:DistBlockMatrix;
	val B:DistBlockMatrix;
	val C:DistBlockMatrix;
	//
	val work1:PlaceLocalHandle[BlockSet];
	val work2:PlaceLocalHandle[BlockSet];
	//
	//------------------------------------------------

	public var commTime:Long=0;
	public var calcTime:Long=0;
	
	//=====================================================================
	// Constructor
	//=====================================================================
	public def this(
			ps:Int, be:Double,
			a:DistBlockMatrix, 
			b:DistBlockMatrix, 
			c:DistBlockMatrix,
			w1:PlaceLocalHandle[BlockSet],
			w2:PlaceLocalHandle[BlockSet]) {
		//Check panelsize
		work1 = w1;
		work2 = w2;
		
		panelSize = ps;
		A = a; B=b; C=c;
		
		//alpha = al;
		beta  = be;
	}
	/**
	 * Estimate the panel size.
	 */
	public static def estPanelSize(ps:Int, ga:Grid, gb:Grid):Int {
		
		val maxps = Math.min(ga.colBs(0), gb.rowBs(0));
		var estps:Int = 128;//estCommuDataSize/ldm;
		estps = Math.min(ps, estps);
		
		if (estps < 1)      estps = 1;
		if (estps > maxps)	estps = maxps;
		
		estps = Math.min(Math.min(estps, ga.getMinColSize()),
				Math.min(estps, gb.getMinRowSize()));	
		
		return estps;
	}	
	//--------------------------------------------------------------------
	public static def mult(			
			A:DistBlockMatrix, 
			B:DistBlockMatrix, 
			C:DistBlockMatrix, plus:Boolean) {
		mult(10, plus?1.0:0.0, A, B, C);
	}
	
	//-----------------------------------------------------
	public static def mult(
			var ps:Int,  /* Panel size*/
			beta:Double, 
			A:DistBlockMatrix, 
			B:DistBlockMatrix, 
			C:DistBlockMatrix) {
		
		val pansz = estPanelSize(ps, A.getGrid(), B.getGrid());
		val w1 = A.makeTempFrontRowBlocks(pansz);
		val w2 = B.makeTempFrontColBlocks(pansz); 
		val s = new SummaMult(pansz, beta, A, B, C, w1, w2);

		s.parallelMult();
		
	}
	
	//=====================================================================
	//
	/**
	 * Distributed matrix multiplication using SUMMA alogrithm
	 * 
	 * @param work1		temporary space used for ring cast each row blocks
	 * @param work2 	temporary space used for ring cast each column blocks
	 */
	public def parallelMult() {
		//
		val K = A.N;
		//------------------------
		var itRow:Int = 0;
		var itCol:Int = 0; //Current processing iteration
		//
		var iwrk:Int = 0;
		var ii:Int = 0;
		var jj:Int = 0;
		var st:Long= 0;
		//
		val gA = A.getGrid();
		val gB = B.getGrid();
		//---------------------------------------------------

		//Scaling the matrixesx
		if (MathTool.isZero(beta)) C.reset();
		
		for (var kk:Int=0; kk<K; kk+=iwrk) {
			iwrk = Math.min(panelSize, gB.rowBs(itRow)-ii);
			iwrk = Math.min(iwrk,      gA.colBs(itCol)-jj); 
			val klen = iwrk;

			//Debug.flushln("Root place starts iteration "+kk+" panel size:"+klen); 
			//
			//-------------------------------------------------------------------
			//Packing columns and rows and broadcast to same row and column block
			/* TIMING */ st = Timer.milliTime();
			AllGridCast.startRowCast(jj, iwrk, itCol, A, work1);
			AllGridCast.startColCast(ii, iwrk, itRow, B, work2);
			/* TIMING */ commTime += Timer.milliTime() - st;
			//Debug.flushln("Row and column blocks bcast ends");
			
			//-----------------------------------------------------------------
			/* TIMING */ st = Timer.milliTime();
			finish 	ateach (Dist.makeUnique()) {
				/* update local block */
				val mypid = here.id();
				val wk1 = work1();
				val wk2 = work2();
				val cbs = C.handleBS();
				val itr = cbs.iterator();
				while (itr.hasNext()) {
					val cblk = itr.next();
					val cmat = cblk.getMatrix();
					val ablk = wk1.findFrontRowBlock(cblk.myRowId); 
					val bblk = wk2.findFrontColBlock(cblk.myColId);
					
					//--------------------------------------------
					val amat:Matrix;
					val bmat:Matrix;
					if (ablk.isDense()) {
						amat = new DenseMatrix(ablk.getMatrix().M, klen, ablk.getData()) as Matrix;
					} else {
						amat = new SparseCSC(ablk.getMatrix().M, klen, ablk.getCompressArray()) as Matrix;
					}
					if (bblk.isDense()) {
						bmat = new DenseMatrix(klen, bblk.getMatrix().N, bblk.getData()) as Matrix;
					} else {
						bmat = new SparseCSC(klen, bblk.getMatrix().N, bblk.getCompressArray()) as Matrix;
					}

					cmat.mult(amat as Matrix(cmat.M), bmat as Matrix(amat.N, cmat.N), true);
				}
			 }

			/* TIMING */ calcTime += Timer.milliTime() - st;
			/* update icurcol, icurrow, ii, jj */
			ii += iwrk;
			jj += iwrk;
			if ( jj>=gA.colBs(itCol)) { itCol++; jj = 0; };
			if ( ii>=gB.rowBs(itRow)) { itRow++; ii = 0; };
		}
	}
	//--------------------------------------------
	
}
