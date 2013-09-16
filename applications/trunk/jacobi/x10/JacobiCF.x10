import x10.array.*;

/************************************************************
* program to solve a finite difference 
* discretization of Helmholtz equation :  
* (d2/dx2)u + (d2/dy2)u - alpha u = f 
* using Jacobi iterative method. 
*
* Modified: Sanjiv Shah,       Kuck and Associates, Inc. (KAI), 1998
* Author:   Joseph Robicheaux, Kuck and Associates, Inc. (KAI), 1998
* This X10 version by David Grove, IBM Research, was translated in September 2013
* from the C translation written by Chunhua Liao, University of Houston, Jan, 2005.
* 
* Input :  n - grid dimension in x direction 
*          m - grid dimension in y direction
*          alpha - Helmholtz constant (always greater than 0.0)
*          tol   - error tolerance for iterative solver
*          relax - Successive over relaxation parameter
*          mits  - Maximum iterations for iterative solver
*
* On output 
*       : u(n,m) - Dependent variable (solutions)
*       : f(n,m) - Right hand side function 
*************************************************************/

public class JacobiCF {
   
    static val MSIZE = 500;

    static val relax = 1.0;
    static val alpha = 0.0543;

    val n:long;
    val m:long;
    val u:Array_2[double];
    val uold:Array_2[double];
    val f:Array_2[double];

    val P:long = Runtime.NTHREADS;

    public static def main(Rail[String]) {
        val n=MSIZE;
        val m=MSIZE;
        val tol=0.0000000001;
        val mits=5000;

        val jb = new JacobiCF(m, n);
        Console.OUT.println("Running using "+jb.P+" threads...");

        val start = System.nanoTime();
        jb.jacobi(tol, mits);
        val end = System.nanoTime();

        Console.OUT.println("------------------------");
        Console.OUT.println("Execution time = "+((end-start)/1E9));
        jb.errorCheck();
    }

    /** 
     * Initializes data assuming exact solution is u(x,y) = (1-x^2)*(1-y^2)
     */
    def this(n:long, m:long) {
        val dx = 2.0 / (n-1);
        val dy = 2.0 / (m-1);
        this.m = m;
        this.n = n;
        this.u = new Array_2[double](n,m);
        this.uold = new Array_2[double](n,m);
	this.f = new Array_2[double](n, m, (i:long,j:long) => {
            val xx = (-1.0 + dx * (i-1)) as int;
            val yy = (-1.0 + dy * (j-1)) as int;
            -1.0*alpha *(1.0-xx*xx)*(1.0-yy*yy) -2.0*(1.0-xx*xx)-2.0*(1.0-yy*yy)
        });
    }

   /******************************************************************
    * Subroutine HelmholtzJ
    * Solves poisson equation on rectangular grid assuming : 
    * (1) Uniform discretization in each direction, and 
    * (2) Dirichlect boundary conditions 
    * 
    * Jacobi method is used in this routine 
    *
    * Input : n,m   Number of grid points in the X/Y directions 
    *         dx,dy Grid spacing in the X/Y directions 
    *         alpha Helmholtz eqn. coefficient 
    *         omega Relaxation factor 
    *         f(n,m) Right hand side function 
    *         u(n,m) Dependent variable/Solution
    *         tol    Tolerance for iterative solver 
    *         maxit  Maximum number of iterations 
    *
    * Output : u(n,m) - Solution 
    *****************************************************************/
    def jacobi(tol:double, mits:long) {
        val omega=relax;
        val dx = 2.0 / (n-1);
        val dy = 2.0 / (m-1);

        // Initialize coefficients
        val ax = 1.0/(dx*dx); /* X-direction coef */
        val ay = 1.0/(dy*dy); /* Y-direction coef */
        val b  = -2.0/(dx*dx)-2.0/(dy*dy) - alpha; /* Central coeff */ 

        val i_is = new Rail[DenseIterationSpace_1](P, (i:long)=>BlockingUtils.partitionBlock(1,n-2,P, i));
        var error:double = 10.0 * tol;
        var k:long = 1;

        while ((k<=mits)&&(error>tol)) {
            Array.copy(u, uold);

	    error = finish(Reducible.SumReducer[Double]()) {
                 for (block in i_is) {
                     async {
		         var my_error:double = 0.0;
                         for ([i] in block) {
                             for (j in 1..(m-2)) {
                                 val resid = (ax*(uold(i-1, j) + uold(i+1, j)) +
                                              ay*(uold(i, j-1) + uold(i, j+1)) + 
                                              b * uold(i, j) - f(i, j))/b;  
                                 u(i, j) = uold(i, j) - omega * resid;  
                                 my_error += resid*resid;   
                             }
                         }
                         offer my_error;
                     }
                 }
            };

            k = k + 1;
            if (k%500==0) Console.OUT.println("Finished "+k+" iteration.");
            error = Math.sqrt(error)/(n*m);
        }

        Console.OUT.println("Total Number of Iterations:"+k);
        Console.OUT.println("Residual:"+error);
    }


    /************************************************************
    * Checks error between numerical and exact solution 
    ************************************************************/ 
    def errorCheck() {
        val dx = 2.0 / (n-1);
        val dy = 2.0 / (m-1);
        var error:double = 0.0 ;

        for (i in 0..(n-1)) {
            for (j in 0..(m-1)) {
               val xx = -1.0 + dx * (i-1);
               val yy = -1.0 + dy * (j-1);
               val temp  = u(i,j) - (1.0-xx*xx)*(1.0-yy*yy);
               error = error + temp*temp; 
            }
        }
        error = Math.sqrt(error)/(n*m);
        Console.OUT.println("Solution Error :"+error);
    }
}
