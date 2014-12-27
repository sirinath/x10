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

import x10.compiler.Native;
import x10.compiler.NativeCPPInclude;
import x10.compiler.NativeCPPCompilationUnit;
import x10.matrix.ElemType;   

/**
 * This class provides a wrapper around the BLAS routines via native calls.
 * All matrices/vectors use double precision and column-major format.
 * @see http://www.netlib.org/blas/
 *  
 * <p> 
 * NOTE: This class is package protected so that calls of BLAS routines
 * must be made through BLAS or DenseMultBLAS.  This is a workaround for
 * Managed X10 when inlining Java methods from WrapBLAS.java in packages other
 * than x10.matrix.blas, which causes the compiler to complain that WrapBLAS
 * cannot be found.

 * vjTODO: Need to check the native code and make it conform to ElemType rather than
 * building in Double.
 */
@NativeCPPInclude("wrap_blas.h")
@NativeCPPCompilationUnit("wrap_blas.cc")
protected class DriverBLAS {
    // Level One 


    /**
     * Compute x = alpha &#42 x.
     *
     * @param N        number of elements to operate on
     * @param alpha    scalar
     * @param x        data array
     */
    @Native("c++","scale(#1, #2, (#3)->raw)")
    public static native def scale(
            N:Long, 
            alpha:ElemType, 
            x:Rail[ElemType]):void;
    /* {
	if (ElemType==Double) {
	    @Native("java", "WrapBLAS.scale(N, alpha (x).getDoubleArray());"){}
        } else {
	    @Native("java", "WrapBLAS.scale(N, alpha, (x).getFloatArray());"){}
	}
	}*/

    /**
     * Copy N contiguous elements from X to Y.
     *
     * @param N        number of data to copy
     * @param X        source array
     * @param Y        destination array
     * vjTODO: Check the Java portion
     */
    @Native("c++", "copy(#1, (#2)->raw, (#3)->raw)")
    public static native  def copy(
            N:Long, 
            X:Rail[ElemType], 
            Y:Rail[ElemType]):void;
    /* {
	if (ElemType==Double) {
	    @Native("java", "WrapBLAS.copy(N, (X).getDoubleArray(), (Y).getDoubleArray());"){}
        } else {
	    @Native("java", "WrapBLAS.copy(N, (X).getFloatArray(), (Y).getFloatArray());"){}
	}
	}*/

    /**
     * Return dot product of vectors X and Y. 
     *
     * @param n        number of data to operate
     * @param X        right side array of dot product
     * @param Y        left side array of dot product
     * @return         dot-product result
     */
    @Native("c++","dot_prod(#1,(#2)->raw,(#3)->raw)")
    public static native def dot_prod(
            n:Long, 
            X:Rail[ElemType],
            Y:Rail[ElemType]):ElemType;
    /* {
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.dotProd(n, (X).getDoubleArray(),(Y).getDoubleArray())")
		{ 
		    //dummy  to keep the type checker happy
		    return X(0);
		}
        } else {
	    @Native("java","WrapBLAS.dotProd(n, (X).getFloatArray(), (Y).getFloatArray())")
		{
		    //dummy  to keep the type checker happy
		    return X(0);
		}
	}
	}*/

    /**
     * Perform Euclidean norm. Incremental step is 1.
     *
     * @param  n      number of data to operate in array
     * @param  X      data array.
     * @return        Eculidean norm
     */
    @Native("c++","norm2(#1,(#2)->raw)")
    public static native def norm(
            n:Long, 
            X:Rail[ElemType]):ElemType ;
    /*{
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.norm2(n,(X).getDoubleArray())")
	    {
		    //dummy  to keep the type checker happy
		    return X(0);
	    }
        } else {
	    @Native("java","WrapBLAS.norm2(n,(X).getFloatArray())")
	    {
		    //dummy  to keep the type checker happy
		    return X(0);
	    }
	}
	}*/

    /**
     * Sum of absolute values of array X for n number of data.
     * @param  n      number of data to operate in array
     * @param  X      data array.
     * @return        absolute sum
     */
    @Native("c++","abs_sum(#1,(#2)->raw)")
    public static native def abs_sum(
            n:Long, 
            X:Rail[ElemType]):ElemType;
    /*{
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.absSum(n, (X).getDoubleArray())")
	    {
		//dummy  to keep the type checker happy
		return X(0);
	    }
        } else {
	    @Native("java","WrapBLAS.absSum(n, (X).getFloatArray())")
	    {
		//dummy  to keep the type checker happy
		return X(0);
	    }
	}
	}*/

    // Level Three

     /**
     * Compute mC(M,N) = alpha &#42 mA(M,K) &#42 mB(K,N) + beta &#42 mC(M,N).
     *
     * @param alpha  a scalar by which mA is premultiplied
     * @param mA     the first matrix in multiplication
     * @param mB     the second matrix in multiplication
     * @param beta   a scalar by which to scale vector y and add to result.
     *               if beta is 0, y need not be initialized on input
     * @param mC     the output matrix
     * @param dim    dimension array [M, N, K], which are rows of mA, columns of mB, and columns of mC.
     * @param ld     leading dimension array [LDA, LDB, LDC], which are leading dimensions of A, B, C.
     * @param offset row and column offsets [Ar, Ac, Br, Bc, Cr, Cc] into matrices
     * @param trans  integer array for transpose flags on the first and second matrix. 0 for non-transpose.
     */
    @Native("c++","matrix_matrix_mult((#1),(#2)->raw,(#3)->raw,(#4),(#5)->raw,(#6)->raw,(#7)->raw,(#8)->raw,(#9)->raw)")
    public static native def matrix_matrix_mult(
            alpha:ElemType, mA:Rail[ElemType], 
	    mB:Rail[ElemType],
            beta:ElemType,  mC:Rail[ElemType],
            dim:Rail[Long], 
            ld:Rail[Long], 
            offset:Rail[Long],
            trans:Rail[Int]):void;
    /* {
	//	@Native("c++","matrix_matrix_mult((alpha),(mA)->raw,(mB)->raw,(beta),(mC)->raw,(dim)->raw,(ld)->raw,(offset)->raw,(trans)->raw);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.matmatMultOff((alpha),(mA).getDoubleArray(),(mB).getDoubleArray(),(beta),(mC).getDoubleArray(),(dim).getLongArray(),(ld).getLongArray(),(offset).getLongArray(),(trans).getIntArray());")
	    {}
        } else {
	    @Native("java","WrapBLAS.matmatMultOff((alpha),(mA).getFloatArray(),(mB).getFloatArray(),(beta),(mC).getFloatArray(),(dim).getLongArray(),(ld).getLongArray(),(offset).getLongArray(),(trans).getIntArray());")
	    {}
	}
	}*/

    /**
     * Compute mC(M,N) = alpha &#42 mA(M,K) &#42 mB(K,N) + beta &#42 mC(M,N).
     *
     * @param alpha  a scalar by which mA is premultiplied
     * @param mA     the first matrix in multiplication
     * @param mB     the second matrix in multiplication
     * @param beta   a scalar by which to scale matrix C and add to result.
     *               if beta is 0, C need not be initialized on input
     * @param mC     the output matrix
     * @param dim    dimension array [M, N, K], which are rows of mA, columns of mB, and columns of mC.
     * @param ld     leading dimension array [LDA, LDB, LDC], which are leading dimensions of A, B, C.
     * @param trans  integer array for transpose flags on the first and second matrix. 0 for non-transpose.
     */
    @Native("c++","matrix_matrix_mult((#1),(#2)->raw,(#3)->raw,(#4),(#5)->raw,(#6)->raw,(#7)->raw,(#8)->raw)")
    public static native def matrix_matrix_mult(
            alpha:ElemType, mA:Rail[ElemType], 
                          mB:Rail[ElemType],
            beta:ElemType,  mC:Rail[ElemType],
            dim:Rail[Long], 
            ld:Rail[Long], 
            trans:Rail[Int]):void; 
    /* {
	//	@Native("c++","matrix_matrix_mult((alpha),(mA)->raw,(mB)->raw,(beta),(mC)->raw,(dim)->raw,(ld)->raw,(trans)->raw);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.matmatMult((alpha),(mA).getDoubleArray(),(mB).getDoubleArray(),(beta),(mC).getDoubleArray(),(dim).getLongArray(),(ld).getLongArray(),(trans).getIntArray());")
	    {}
        } else {
	    @Native("java","WrapBLAS.matmatMult((alpha),(mA).getFloatArray(),(mB).getFloatArray(),(beta),(mC).getFloatArray(),(dim).getLongArray(),(ld).getLongArray(),(trans).getIntArray());")
	    {}
	}
	}*/

    /**
     * Compute mC(N,N) = alpha &mC2 mA(N,K) &mC2 mA(K,N)' + beta &mC2 mC(N,N).
     *
     * @param alpha  a scalar by which mA is premultiplied
     * @param mA     the first matrix in multiplication
     * @param beta   a scalar by which to scale matrix C and add to result.
     *               if beta is 0, C need not be initialized on input
     * @param mC     the output matrix
     * @param dim    dimension array [N, K], which are rows of mA and columns of mA.
     * @param offset row and column offsets [Ar, Ac, Br, Bc, Cr, Cc] into matrices
     * @param upper  if true, update upper half of mC; otherwise update lower half
     * @param trans  whether to transpose mA
     */
    @Native("c++","sym_rank_k_update((#1),(#2)->raw,(#3),(#4)->raw,(#5)->raw,(#6)->raw,(#7)->raw,(#8),(#9))")
    public static native def sym_rank_k_update(
            alpha:ElemType, mA:Rail[ElemType], 
            beta:ElemType,  mC:Rail[ElemType],
            dim:Rail[Long],
            ld:Rail[Long],
            offset:Rail[Long],
            upper:Boolean,
            trans:Boolean
					       ):void; 
    /*{
	//	@Native("c++","sym_rank_k_update((alpha),(mA)->raw,(beta),(mC)->raw,(dim)->raw,(ld)->raw,(offset)->raw,(upper),(trans));"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.symRankKUpdateOff((alpha),(mA).getDoubleArray(),(beta),(mC).getDoubleArray(),(dim).getLongArray(),(ld).getLongArray(),(offset).getLongArray(),(upper),(trans));")
	    {}
        } else {
	    @Native("java","WrapBLAS.symRankKUpdateOff((alpha),(mA).getFloatArray(),(beta),(mC).getFloatArray(),(dim).getLongArray(),(ld).getLongArray(),(offset).getLongArray(),(upper),(trans));")
	    {}
	}
	}*/

    /**
     * Compute mC(N,N) = alpha &#42 mA(N,K) &#42 mA(K,N)' + beta &#42 mC(N,N).
     *
     * @param alpha  a scalar by which mA is premultiplied
     * @param mA     the first matrix in multiplication
     * @param beta   a scalar by which to scale matrix C and add to result.
     *               if beta is 0, C need not be initialized on input
     * @param mC     the output matrix
     * @param dim    dimension array [N, K], which are rows of mA and columns of mA.
     * @param upper  if true, update upper half of mC; otherwise update lower half
     * @param trans  whether to transpose mA
     */
    @Native("c++","sym_rank_k_update((#1),(#2)->raw,(#3),(#4)->raw,(#5)->raw,(#6),(#7))")
    public static native def sym_rank_k_update(
            alpha:ElemType, mA:Rail[ElemType], 
            beta:ElemType,  mC:Rail[ElemType],
            dim:Rail[Long], 
            upper:Boolean,
            trans:Boolean
					       ):void ; /* {
	//	@Native("c++","sym_rank_k_update((alpha),(mA)->raw,(beta),(mC)->raw,(dim)->raw,(upper),(trans));"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.symRankKUpdate((alpha),(mA).getDoubleArray(),(beta),(mC).getDoubleArray(),(dim).getLongArray(),(upper),(trans));")
	    {}
        } else {
	    @Native("java","WrapBLAS.symRankKUpdate((alpha),(mA).getFloatArray(),(beta),(mC).getFloatArray(),(dim).getLongArray(),(upper),(trans));")
	    {}
	}
	}*/

    /**
     * Compute mC =  alpha*mB &#42 mA + beta*mC, 
     * where mA is lower triangle of symmetric matrix.
     *
     * @param alpha  a scalar by which mB is premultiplied
     * @param mA     the first symmetric matrix in multiplication
     * @param mB     the second triangular matrix in multiplication
     * @param beta   a scalar by which to scale mC and add to result.
     *               if beta is 0, mC need not be initialized on input
     * @param mC     the output matrix
     * @param dim    dimension array [M, N], rows and columns of mC
     */
    @Native("c++","sym_matrix_mult((#1),(#2)->raw,(#3)->raw,(#4),(#5)->raw,(#6)->raw)")
    public static native def sym_matrix_mult(
            alpha:ElemType, mA:Rail[ElemType], 
                          mB:Rail[ElemType], 
            beta:ElemType,  mC:Rail[ElemType],
            dim:Rail[Long]):void; /* {
	//	@Native("c++","sym_matrix_mult((alpha),(mA)->raw,(mB)->raw,(beta),(mC)->raw,(dim)->raw);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.symmatMult(alpha, mA.getDoubleArray(),(mB).getDoubleArray(),(beta),(mC).getDoubleArray(),(dim).getLongArray());")
	    {}
        } else {
	    @Native("java","WrapBLAS.symmatMult((alpha),(mA).getFloatArray(),(mB).getFloatArray(),(beta),(mC).getFloatArray(),(dim).getLongArray());")
	    {}
	}
	}*/

    /**
     * Compute mC = alpha*mA &#42 mB + beta*mC, 
     * where mA is lower triangle of symmetric matrix.
     */
    @Native("c++","matrix_sym_mult((#1)->raw,(#2),(#3)->raw,(#4),(#5)->raw,(#6)->raw)")
    public static native def matrix_sym_mult(
                          mB:Rail[ElemType], 
            alpha:ElemType, mA:Rail[ElemType], 
            beta:ElemType,  mC:Rail[ElemType],
			  dim:Rail[Long]):void ; /*{
	//	@Native("c++","matrix_sym_mult((mB)->raw,(alpha),(mA)->raw,(beta),(mC)->raw,(dim)->raw);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.matsymMult((mB).getDoubleArray(),(alpha),(mA).getDoubleArray(),(beta),(mC).getDoubleArray(),(dim).getLongArray());")
	    {}
        } else {
	    @Native("java","WrapBLAS.matsymMult((mB).getFloatArray(),(alpha),(mA).getFloatArray(),(beta),(mC).getFloatArray(),(dim).getLongArray());")
	    {}
	}
	}*/

    /**
     * Compute mB =  op( mA ) &#42 mB, mA is non-unit triangular matrix.
     *
     * @param mA     Double precision array storing triangular matrix mA.
     * @param mB     Double precision array storing matrix mB, which also is the output.
     * @param dim    dimension array [M, N], which are rows of mB and columns of mB. Lower/upper triangular flag sets the last value.
     * @param tranA  transpose option for mA
     */
    @Native("c++","tri_matrix_mult((#1)->raw,(#2)->raw,(#3)->raw,#4)")
    public static native def tri_matrix_mult(
            mA:Rail[ElemType], 
            mB:Rail[ElemType], 
            dim:Rail[Long], 
            tranA:Int):void; /* {
	//	@Native("c++","tri_matrix_mult((mA)->raw,(mB)->raw,(dim)->raw, tranA);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.trimatMult((mA).getDoubleArray(),(mB).getDoubleArray(),(dim).getLongArray(),tranA);")
	    {}
        } else {
	    @Native("java","WrapBLAS.trimatMult((mA).getFloatArray(),(mB).getFloatArray(),(dim).getLongArray(), tranA);")
	    {}
	}
	}*/

    /**
     * Compute mB =  mA &#42 op( mB ), mB is non-unit triangular matrix.
     *
     * @param mA     ElemType precision array storing matrix mA and output matrix.
     * @param mB     ElemType precision array storing triangular matrix mB.
     * @param dim    dimension array [M, N], which are rows of mB and columns of mB. 
     * @param tranB  transpose option for matrix mB
     */
    @Native("c++","matrix_tri_mult((#1)->raw,(#2)->raw,(#3)->raw,#4)")
    public static native def matrix_tri_mult(mA:Rail[ElemType], 
                                             mB:Rail[ElemType],
                                             dim:Rail[Long], 
                                             tranB:Int):void; /* {
	//	@Native("c++","matrix_tri_mult((mA)->raw,(mB)->raw,(dim)->raw, tranB);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.mattriMult((mA).getDoubleArray(),(mB).getDoubleArray(),(dim).getLongArray(), tranB);")
	    {}
        } else {
	    @Native("java","WrapBLAS.mattriMult((mA).getFloatArray(),(mB).getFloatArray(),(dim).getLongArray(), tranB);")
	    {}
	}
	}*/


    // Level Two

    /**
     * Compute y = alpha &#42 mA &#42 y + beta &#42 y, matrix-vector multiplication.
     *
     * @param alpha  a scalar by which mA is premultiplied
     * @param mA     the first matrix (right-side)
     * @param x      left-side operand vector
     * @param beta   a scalar by which to scale vector y and add to result.
     *               if beta is 0, y need not be initialized on input
     * @param y      output vector
     * @param dim    dimension array [M, N], which are rows and columns of mA
     * @param lda    leading dimension of mA
     * @param offset row and column offsets into mA and offsets into vectors [Ar, Ac, xr, yr] 
     * @param transA transpose flag for matrix mA
     */
    @Native("c++","matrix_vector_mult((#1),(#2)->raw,(#3)->raw,(#4),(#5)->raw,(#6)->raw,#7,(#8)->raw,#9)")
    public static native def matrix_vector_mult(
            alpha:ElemType, mA:Rail[ElemType], 
                          x:Rail[ElemType], 
            beta:ElemType,  y:Rail[ElemType],
            dim:Rail[Long], 
            lda:Long, 
            offset:Rail[Long],
            transA:Int):void; /* {
	//	@Native("c++","matrix_vector_mult((alpha),(mA)->raw,(x)->raw,(beta),(y)->raw,(dim)->raw,lda,(offset)->raw,transA);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.matvecMultOff((alpha),(mA).getDoubleArray(),(x).getDoubleArray(),(beta),(y).getDoubleArray(),(dim).getLongArray(),lda,(offset).getLongArray(),transA);")
	    {}
        } else {
	    @Native("java","WrapBLAS.matvecMultOff((alpha),(mA).getFloatArray(),(x).getFloatArray(),(beta),(y).getFloatArray(),(dim).getLongArray(),lda,(offset).getLongArray(),transA);")
	    {}
	}
	}*/

    /**
     * Compute y = alpha &#42 mA &#42 y + beta &#42 y, matrix-vector multiplication.
     *
     * @param alpha  a scalar by which mA is premultiplied
     * @param mA     the first matrix (right-side)
     * @param x      left-side operand vector
     * @param beta   a scalar by which to scale vector y and add to result.
     *               if beta is 0, y need not be initialized on input
     * @param y      output vector
     * @param dim    dimension array [M, N], which are rows and columns of mA
     * @param transA transpose flag for matrix mA
     */
    @Native("c++","matrix_vector_mult((#1),(#2)->raw,(#3)->raw,(#4),(#5)->raw,(#6)->raw,#7)")
    public static native def matrix_vector_mult(
            alpha:ElemType, mA:Rail[ElemType], 
                          x:Rail[ElemType], 
            beta:ElemType,  y:Rail[ElemType],
            dim:Rail[Long], 
            transA:Int):void; /* {
	//	@Native("c++","matrix_vector_mult((alpha),(mA)->raw,(x)->raw,(beta),(y)->raw,(dim)->raw,transA);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.matvecMult((alpha),(mA).getDoubleArray(),(x).getDoubleArray(),(beta),(y).getDoubleArray(),(dim).getLongArray(),transA);")
	    {}
        } else {
	    @Native("java","WrapBLAS.matvecMult((alpha),(mA).getFloatArray(),(x).getFloatArray(),(beta),(y).getFloatArray(),(dim).getLongArray(),transA);")
	    {}
	}
	}*/

    /**
     * Compute  y = alpha &beta2 mA &beta2 y + beta &beta2 y, 
     * where A is lower triangle of symmetric matrix. Incremental step is 1.
     *
     * @param alpha  a scalar by which mA is premultiplied
     * @param mA     the symmetric matrix (right-side)
     * @param x      left-side operand vector
     * @param beta   a scalar by which to scale vector y and add to result.
     *               if beta is 0, y need not be initialized on input
     * @param y      output vector
     * @param dim    dimension array [M, N], which are rows and columns of mA
     */
    @Native("c++","sym_vector_mult(#1,(#2)->raw,(#3)->raw,#4,(#5)->raw,(#6)->raw)")
    public static native def sym_vector_mult(
            alpha:ElemType, mA:Rail[ElemType], 
                          x:Rail[ElemType], 
            beta:ElemType,  y:Rail[ElemType],
            dim:Rail[Long]):void; /* {
	//	@Native("c++","sym_vector_mult(alpha,(mA)->raw,(x)->raw,beta,(y)->raw,(dim)->raw);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.symvecMult(alpha,(mA).getDoubleArray(),(x).getDoubleArray(),beta,(y).getDoubleArray(),(dim).getLongArray());")
	    {}
        } else {
	    @Native("java","WrapBLAS.symvecMult(alpha,(mA).getFloatArray(),(x).getFloatArray(),beta,(y).getFloatArray(),(dim).getLongArray());")
	    {}
	}
	}*/

    /**
     * Triangular-vector multply:  op(mA) &tA2 x = b, where mA is unit lower-non-diagonal matrix.
     * 
     * @param mA     the lower-non-diagonal matrix
     * @param bx     right-hand side vector as input, and output
     * @param N      leading dimension of mA 
     * @param lda
     * @param tA     transpose option for mA
     */    
    @Native("c++","tri_vector_mult((#1)->raw,#2,(#3)->raw,#4,#5)")
    public static native def tri_vector_mult(
            mA:Rail[ElemType], 
            uplo:Int,
            bx:Rail[ElemType], 
            lda:Long, tA:Int):void; /* {
	//	@Native("c++","tri_vector_mult((mA)->raw,bx,(N)->raw,ldA, tA);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.trivecMult((mA).getDoubleArray(),bx,(N).getDoubleArray(),lda,tA);")
	    {}
        } else {
	    @Native("java","WrapBLAS.trivecMult((mA).getFloatArray(),bx,(N).getFloatArray(),lda, tA);")
	    {}
	}
	}*/


    //A := alpha*x*y**T + A,
    /**
     * Compute A = alpha &#42 x &#42 y &#42 &#42 T + A, general rank-1 update.
     *
     * @param alpha  scalar alpha
     * @param x      vector of dimension at least M+offsetX
     * @param y      output vector
     * @param mA     the first matrix (right-side)
     * @param dim    dimension array [M, N], which are rows and columns of mA
     * @param offset row and column offsets [xr, yr, Ar, Ac] into matrix/vectors
     * @param inc    increments [incX, incY] for elements of X and Y
     * @param lda    leading dimension of A

     */
    @Native("c++","rank_one_update((#1),(#2)->raw,(#3)->raw,(#4)->raw,(#5)->raw,(#6)->raw,(#7)->raw,#8)")
    public static native def rank_one_update(
            alpha:ElemType,
            x:Rail[ElemType],
            y:Rail[ElemType],
            mA:Rail[ElemType],
            dim:Rail[Long],
            offset:Rail[Long],
            inc:Rail[Long],
            lda:Long
					     ):void ; /*{
	//	@Native("c++","rank_one_update((alpha),(x)->raw,(y)->raw,(mA)->raw,(dim)->raw,(offset)->raw,(inc)->raw,lda);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.rankOneUpdate((alpha),(x).getDoubleArray(),(y).getDoubleArray(),(mA).getDoubleArray(),(dim).getLongArray(),(offset).getLongArray(),(inc).getLongArray(),lda);")
	    {}
        } else {
	    @Native("java","WrapBLAS.rankOneUpdate((alpha),(x).getFloatArray(),(y).getFloatArray(),(mA).getFloatArray(),(dim).getLongArray(),(offset).getLongArray(),(inc).getLongArray(),lda);")
	    {}
	}
	}*/

    /**
     * Solve equation  mA &#42 x = b, where mA is unit lower-triangular matrix.
     *
     * @param mA     the unit lower-triangular matrix
     * @param bx     right-hand side vector as input, and output
     * @param dim    leading dimension and order of mA 
     * @param transA transpose option for mA 
     */    
    @Native("c++","tri_vector_solve((#1)->raw,(#2)->raw,(#3)->raw,#4)")
    public static native def tri_vector_solve(
            mA:Rail[ElemType], 
            bx:Rail[ElemType], 
            dim:Rail[Long], transA:Int):void ; /*{
	//	@Native("c++","tri_vector_solve((mA)->raw,(bx)->raw,(dim)->raw,transA);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.trivecSolve((mA).getElemTypeArray(),(bx).getDoubleArray(),(dim).getLongArray(),transA);")
	    {}
        } else {
	    @Native("java","WrapBLAS.trivecSolve((mA).getElemTypeArray(),(bx).getFloatArray(),(dim).getLongArray(),transA);")
	    {}
	}
	}*/

    /**
     * Solve matrix equation  op(mA) &#42 X = B, where mA is unit lower-triangular matrix.
     * 
     * @param mA     the unit lower-triangular matrix
     * @param BX     right-hand side matrix as input, and output
     * @param dim    leading dimension of mA and leading dimension of B 
     * @param transA transpose option for mA 
     */    
    @Native("c++","tri_matrix_solve((#1)->raw,(#2)->raw,(#3)->raw,#4)")
    public static native def tri_matrix_solve(
            mA:Rail[ElemType], 
            BX:Rail[ElemType], 
            dim:Rail[Long], transA:Int):void; /* {
	//	@Native("c++","tri_matrix_solve((mA)->raw,(BX)->raw,(dim)->raw,transA);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.trimatSolve((mA).getDoubleArray(),(BX).getDoubleArray(),(dim).getLongArray(),transA);")
	    {}
        } else {
	    @Native("java","WrapBLAS.trimatSolve((mA).getFloatArray(),(BX).getFloatArray(),(dim).getLongArray(),transA);")
	    {}
	}
	}*/

    /**
     * Solve matrix equation  X &#42 op(mA) = B, where mA is unit lower-triangular matrix.
     * 
     * @param BX     left-hand side matrix as input, and output
     * @param mA     the unit lower-triangular matrix
     * @param dim    leading dimension of mA and leading dimension of B 
     * @param transA transpose option for mA 
     */    
    @Native("c++","matrix_tri_solve((#1)->raw,(#2)->raw,(#3)->raw,#4)")
    public static native def matrix_tri_solve(
            BX:Rail[ElemType], 
            mA:Rail[ElemType], 
            dim:Rail[Long], transA:Int):void; /* {
	//	@Native("c++","matrix_tri_solve((BX)->raw,(mA)->raw,(dim)->raw,transA);"){}
	if (ElemType==Double) {
	    @Native("java","WrapBLAS.mattriSolve((BX).getDoubleArray(),(mA).getDoubleArray(),(dim).getLongArray(),transA);")
	    {}
        } else {
	    @Native("java","WrapBLAS.mattriSolve((BX).getFloatArray(),(mA).getFloatArray(),(dim).getLongArray(),transA);")
	    {}
	}
	}*/

}
