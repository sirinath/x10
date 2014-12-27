/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

package x10.matrix.blas;

/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2011-2014.
 * vjTODO: Need to update for ElemType.
 */

/**
 * This Java class provides JNI wrappers for BLAS library routines.
 */
public class WrapBLAS {

	static { 
		System.loadLibrary("jblas");
	}

	public static native void scale(long n, double alpha, double[] x);
	public static native void scale_f(long n, float alpha, float[] x);

	public static native void copy(long n, double[] x, double[] y);
	public static native void copy_f(long n, float[] x, float[] y);

	public static native double dotProd(long n, double[] x, double[] y);
	public static native float  dotProd_f(long n,  float[] x,  float[] y);

	public static native double norm2(long n, double[] x);
	public static native  float norm2_f(long n, float[] x);


	public static native double absSum(long n, double[] x);
	public static native  float absSum_f(long n, float[] x);

        public static native void matmatMultOff(double alpha, double[] A, double[] B, double beta, double[] C, long[] dim, long[] ld, long[] off, int[] trans);
        public static native void matmatMultOff_f( float alpha,  float[] A,  float[] B,  float beta,  float[] C, long[] dim, long[] ld, long[] off, int[] trans);

	public static native void matmatMult(double alpha, double[] A, double[] B, double beta, double[] C, long[] dim, long[] ld, int[] trans);
	public static native void matmatMult_f( float alpha,  float[] A,  float[] B,  float beta,  float[] C, long[] dim, long[] ld, int[] trans);

	public static native void symRankKUpdateOff(double alpha, double[] A, double beta, double[] C, long[] dim, long[] ld, long[] off, boolean upper, boolean trans);
	public static native void symRankKUpdateOff_f( float alpha,  float[] A,  float beta,  float[] C, long[] dim, long[] ld, long[] off, boolean upper, boolean trans);

	public static native void symRankKUpdate(double alpha, double[] A, double beta, double[] C, long[] dim, boolean upper, boolean trans);
	public static native void symRankKUpdate_f( float alpha,  float[] A,  float beta,  float[] C, long[] dim, boolean upper, boolean trans);

	public static native void symmatMult(double alpha, double[] A, double[] B, double beta, double[] C, long[] dim);
	public static native void symmatMult_f( float alpha,  float[] A,  float[] B,  float beta,  float[] C, long[] dim);

	public static native void matsymMult(double[] B, double alpha, double[] A, double beta, double[] C, long[] dim);
	public static native void matsymMult_f( float[] B,  float alpha,  float[] A,  float beta,  float[] C, long[] dim);

	public static native void matvecMultOff(double alpha, double[] A, double[] x, double beta, double[] y, long[] dim, long lda, long[] off, int transA);
	public static native void matvecMultOff_f( float alpha,  float[] A,  float[] x,  float beta,  float[] y, long[] dim, long lda, long[] off, int transA);

	public static native void matvecMult(double alpha, double[] A, double[] x, double beta, double[] y, long[] dim, int transA);
	public static native void matvecMult_f( float alpha,  float[] A,  float[] x,  float beta,  float[] y, long[] dim, int transA);

	public static native void symvecMult(double alpha, double[] A, double[] x, double beta, double[] y, long[] dim);
	public static native void symvecMult_f( float alpha,  float[] A,  float[] x,  float beta,  float[] y, long[] dim);

	public static native void trivecMult(double[] A, int uplo, double[] bx, long lda, int tranA);
	public static native void trivecMult_f( float[] A, int uplo,  float[] bx, long lda, int tranA);

	public static native void rankOneUpdate(double alpha, double[] x, double[] y, double[] A, long[] dim, long[] offset, long[] inc, long lda);
	public static native void rankOneUpdate_f( float alpha,  float[] x,  float[] y,  float[] A, long[] dim, long[] offset, long[] inc, long lda);

	public static native void trimatMult(double[] A, double[] B, long[] dim, int tranA);
	public static native void trimatMult_f( float[] A,  float[] B, long[] dim, int tranA);

	public static native void mattriMult(double[] B, double[] A, long[] dim, int tranA);
	public static native void mattriMult_f( float[] B,  float[] A, long[] dim, int tranA);
	
	public static native void trivecSolve(double[] A, double[] bx, long[] dim, int tranA);
	public static native void trivecSolve_f( float[] A,  float[] bx, long[] dim, int tranA);

	public static native void trimatSolve(double[] A, double[] BX, long[] dim, int tranA);
	public static native void trimatSolve_f( float[] A,  float[] BX, long[] dim, int tranA);

	public static native void mattriSolve(double[] BX, double[] A, long[] dim, int tranA);
	public static native void mattriSolve_f( float[] BX,  float[] A, long[] dim, int tranA);
}
