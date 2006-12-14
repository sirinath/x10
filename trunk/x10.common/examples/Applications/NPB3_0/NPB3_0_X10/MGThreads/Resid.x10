/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
/*
 !-------------------------------------------------------------------------!
 !                                                                        !
 !       N  A  S     P A R A L L E L     B E N C H M A R K S  3.0         !
 !                                                                        !
 !                      J A V A         V E R S I O N                     !
 !                                                                        !
 !                               R e s i d                                 !
 !                                                                         !
 !-------------------------------------------------------------------------!
 !                                                                         !
 !    Resid implements thread for Resid subroutine of MG benchmark.        !
 !                                                                        !
 !    Permission to use, copy, distribute and modify this software        !
 !    for any purpose with or without fee is hereby granted.  We          !
 !    request, however, that all derived work reference the NAS           !
 !    Parallel Benchmarks 3.0. This software is provided "as is"          !
 !    without express or implied warranty.                                !
 !                                                                        !
 !    Information on NPB 3.0, including the Technical Report NAS-02-008   !
 !    "Implementation of the NAS Parallel Benchmarks in Java", !
 !    original specifications, source code, results and information       !
 !    on how to submit new results, is available at:                      !
 !                                                                        !
 !          http://www.nas.nasa.gov/Software/NPB/                         !
 !                                                                        !
 !    Send comments or suggestions to  npb@nas.nasa.gov                   !
 !                                                                        !
 !         NAS Parallel Benchmarks Group                                  !
 !         NASA Ames Research Center                                      !
 !         Mail Stop: T27A-1                                              !
 !         Moffett Field, CA   94035-1000                                 !
 !                                                                        !
 !         E-mail:  npb@nas.nasa.gov                                      !
 !         Fax: (650) 604-3957                                        !
 !                                                                        !
 !-------------------------------------------------------------------------!
 ! Translation to Java and MultiThreaded Code                             !
 !         M. Frumkin                                                     !
 !         M. Schultz                                                     !
 !-------------------------------------------------------------------------!
 */
package NPB3_0_X10.MGThreads;

import NPB3_0_X10.MG;

public class Resid extends MGWorker {
	private boolean visr;
	private int n1, n2, n3, off;
	int uoff = 0;
	double[] u1, u2;

	public Resid(MG mg, int i) {
		super(mg, i);
		u1 = new double[nm+1];
		u2 = new double[nm+1];
	}

	public void step(boolean done, boolean visr, int wstart,
			int wend, int n1, int n2, int n3, int off) {
		getWork(wstart, wend);
		if (work == 0) return;
		resid(u, (visr) ? r : v, r, off, n1, n2, n3, u1, u2, a, start, end);

//		c---------------------------------------------------------------------
//		c     exchange boundary data
//		c---------------------------------------------------------------------
		for (point [i3,i2] : [start:end,1:n2-2]) {
			r[off+n1*(i2+n2*i3)] = r[off+n1-2+n1*(i2+n2*i3)];
			r[off+n1-1+n1*(i2+n2*i3)] = r[off+1+n1*(i2+n2*i3)];
		}

		for (point [i3,i1] : [start:end,0:n1-1]) {
			r[off+i1+n1*n2*i3] = r[off+i1+n1*(n2-2+n2*i3)];
			r[off+i1+n1*(n2-1+n2*i3)] = r[off+i1+n1*(1+n2*i3)];
		}
	}
	public static void resid(double u[], double v[], double r[], int off, int n1, int n2, int n3,
			double[] u1, double[] u2, double[]a, int start, int end) {
		for (point [i3,i2]: [start:end,1:n2-2]) {
			for (point [i1] : [0:n1-1]) {
				u1[i1] = u[off+i1+n1*(i2-1+n3*i3)]
					+ u[off+i1+n1*(i2+1+n3*i3)]
					+ u[off+i1+n1*(i2+n3*(i3-1))]
					+ u[off+i1+n1*(i2+n3*(i3+1))];
				u2[i1] = u[off+i1+n1*(i2-1+n3*(i3-1))]
					+ u[off+i1+n1*(i2+1+n3*(i3-1))]
					+ u[off+i1+n1*(i2-1+n3*(i3+1))]
					+ u[off+i1+n1*(i2+1+n3*(i3+1))];
			}
			for (point [i1] : [1:n1-2]) {
				r[off+i1+n1*(i2+n3*i3)] = v[off+i1+n1*(i2+n3*i3)]
					- a[0] * u[off+i1+n1*(i2+n3*i3)]
//					c---------------------------------------------------------------------
//					c  Assume a(1) = 0 (Enable 2 lines below if a(1) not = 0)
//					c---------------------------------------------------------------------
//					c    >                     - a[1] * (u(i1-1,i2,i3) + u(i1+1,i2,i3)
//					c    >                              + u1(i1))
//					c---------------------------------------------------------------------
					- a[2] * (u2[i1] + u1[i1-1] + u1[i1+1])
					- a[3] * (u2[i1-1] + u2[i1+1]);
			}
		}
	}
}

