/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2011-2014.
 */

package pagerank;

import x10.matrix.Debug;
import x10.matrix.Matrix;
import x10.matrix.DenseMatrix;
import x10.matrix.Vector;
import x10.matrix.blas.DenseMatrixBLAS;

/**
 * Sequential implementation of page rank algorithm based on GML dense/sparse 
 * matrix.
 */
public class SeqPageRank {
	val rowG:Long;

	val iterations:Long;
	val nzDensity:Double;
	val alpha:Double= 0.85;

	// Input and output data
	public val G:DenseMatrix(rowG, rowG);
	public val P:Vector(rowG);
	public val E:Vector(rowG);
	public val U:Vector(rowG);
	
	// Temp data and matrix 
	val GP:Vector(rowG);
	
	public def this(g:DenseMatrix, p:Vector, 
					e:Vector, u:Vector, 
					it:Long, nz:Double) {
		rowG = g.M;
		//
		G = g as DenseMatrix(rowG, rowG); 
		P = p as Vector(rowG); 
		E = e as Vector(rowG); 
		U = u as Vector(rowG);
		//
		iterations = it;
		nzDensity = nz;
		//
		GP = Vector.make(rowG);
	}
	
	public def run():Vector {
		Debug.flushln("Start sequential PageRank");
		for (i in 1..iterations) {
			GP.mult(G, P, false).scale(alpha);			
			//DenseMatrixBLAS.comp(G, P, GP);
			//GP.scale(alpha);
			//
			P.mult(E, U.dotProd(P)).scale(1-alpha).cellAdd(GP);
			//DenseMatrixBLAS.comp(U, P, UP);
			//DenseMatrixBLAS.comp(E, UP, P);
			//P.scale(1-alpha);
			//P.cellAdd(GP);
		}
		Debug.flushln("Sequential PageRank completes");
		return P;
	}
}
