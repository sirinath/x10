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
 !                               I n t e r p                               !
 !                                                                         !
 !-------------------------------------------------------------------------!
 !                                                                         !
 !    Interp implements thread for Interp subroutine of MG benchmark.      !
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

public class Interp extends MGWorker {

	double z1[], z2[], z3[];

	public Interp(MG mg, int i) {
		super(mg, i);
		int m = 535;
		z1 = new double[m];
		z2 = new double[m];
		z3 = new double[m];
	}

	public void step(boolean done, int wstart, int wend, int mm1,
					 int mm2, int mm3, int n1, int n2, int n3, int zoff, int uoff)
	{
		getWork(wstart,wend);
		if (work == 0) return;
		interp(u, zoff, mm1, mm2, mm3, uoff, n1, n2, n3, z1, z2, z3, start, end);
	}

	public static void interp(double u[], int zoff, int mm1, int mm2, int mm3,
							  int uoff, int n1, int n2, int n3, double[] z1,
							  double[] z2, double[] z3, int start, int end)
	{
//		c---------------------------------------------------------------------
//		c     interp adds the trilinear interpolation of the correction
//		c     from the coarser grid to the current approximation:  u = u + Qu'
//		c
//		c     Observe that this  implementation costs  16A + 4M, where
//		c     A and M denote the costs of Addition and Multiplication.
//		c     Note that this vectorizes, and is also fine for cache
//		c     based machines.  Vector machines may get slightly better
//		c     performance however, with 8 separate "do i1" loops, rather than 4.
//		c---------------------------------------------------------------------
//		double precision z(mm1,mm2,mm3),u(n1,n2,n3)

//		c note that m = 1037 in globals.h but for this only need to be
//		c 535 to handle up to 1024^3
//		c      integer m
//		c      parameter(m = 535)

		if (n1 != 3 && n2 != 3 && n3 != 3) {
			for (point [i3,i2]: [start:end,1:mm2-1]) {
				for (point [i1]: [1:mm1]) {
					z1[i1-1] = u[zoff+i1-1+mm1*(i2+mm2*(i3-1))]
						+ u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))];
					z2[i1-1] = u[zoff+i1-1+mm1*(i2-1+mm2*i3)]
						+ u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))];
					z3[i1-1] = u[zoff+i1-1+mm1*(i2+mm2*i3)]
						+ u[zoff+i1-1+mm1*(i2-1+mm2*i3)] + z1[i1-1];
				}

				for (point [i1]: [1:mm1-1]) {
					u[uoff+2*i1-2+n1*(2*i2-2+n2*(2*i3-2))] +=
						u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))];
					u[uoff+2*i1-1+n1*(2*i2-2+n2*(2*i3-2))] +=
						0.5*(u[zoff+i1+mm1*(i2-1+mm2*(i3-1))]
								+u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))]);
				}
				for (point [i1]: [1:mm1-1]) {
					u[uoff+2*i1-2+n1*(2*i2-1+n2*(2*i3-2))] += 0.5 * z1[i1-1];
					u[uoff+2*i1-1+n1*(2*i2-1+n2*(2*i3-2))] += 0.25*(z1[i1-1] + z1[i1]);
				}
				for (point [i1]: [1:mm1-1]) {
					u[uoff+2*i1-2+n1*(2*i2-2+n2*(2*i3-1))] += 0.5 * z2[i1-1];
					u[uoff+2*i1-1+n1*(2*i2-2+n2*(2*i3-1))] += 0.25*(z2[i1-1] + z2[i1]);
				}
				for (point [i1]: [1:mm1-1]) {
					u[uoff+2*i1-2+n1*(2*i2-1+n2*(2*i3-1))] += 0.25*z3[i1-1];
					u[uoff+2*i1-1+n1*(2*i2-1+n2*(2*i3-1))] += 0.125*(z3[i1-1] + z3[i1]);
				}
			}
		} else {
			final int d1 = (n1 == 3) ? 2 : 1;
			final int t1 = (n1 == 3) ? 1 : 0;
			final int d2 = (n2 == 3) ? 2 : 1;
			final int t2 = (n2 == 3) ? 1 : 0;
			final int d3 = (n3 == 3) ? 2 : 1;
			final int t3 = (n3 == 3) ? 1 : 0;

			for (point [i3] : [1:mm3-1]) {
				for (point [i2] : [1:mm2-1]) {
					for (point [i1] : [1:mm1-1]) {
						u[uoff+2*i1-1-d1+n1*(2*i2-1-d2+n2*(2*i3-1-d3))] +=
							u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))];
					}
					for (point [i1] : [1:mm1-1]) {
						u[uoff+2*i1-1-t1+n1*(2*i2-1-d2+n2*(2*i3-1-d3))] +=
							0.5*(u[zoff+i1+mm1*(i2-1+mm2*(i3-1))]
									+u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))]);
					}
				}
				for (point [i2] : [1:mm2-1]) {
					for (point [i1] : [1:mm1-1]) {
						u[uoff+2*i1-1-d1+n1*(2*i2-1-t2+n2*(2*i3-1-d3))] +=
							0.5*(u[zoff+i1-1+mm1*(i2+mm2*(i3-1))]
									+u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))]);
					}
					for (point [i1] : [1:mm1-1]) {
						u[uoff+2*i1-1-t1+n1*(2*i2-1-t2+n2*(2*i3-1-d3))] +=
							0.25*(u[zoff+i1+mm1*(i2+mm2*(i3-1))]
									+u[zoff+i1+mm1*(i2-1+mm2*(i3-1))]
									+u[zoff+i1-1+mm1*(i2+mm2*(i3-1))]
									+u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))]);
					}
				}
			}

			for (point [i3] : [1:mm3-1]) {
				for (point [i2] : [1:mm2-1]) {
					for (point [i1] : [1:mm1-1]) {
						u[uoff+2*i1-1-d1+n1*(2*i2-1-d2+n2*(2*i3-1-t3))] =
							0.5*(u[zoff+i1-1+mm1*(i2-1+mm2*i3)]
									+u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))]);
					}
					for (point [i1] : [1:mm1-1]) {
						u[uoff+2*i1-1-t1+n1*(2*i2-1-d2+n2*(2*i3-1-t3))] +=
							0.25*(u[zoff+i1+mm1*(i2-1+mm2*i3)]
									+u[zoff+i1-1+mm1*(i2-1+mm2*i3)]
									+u[zoff+i1+mm1*(i2-1+mm2*(i3-1))]
									+u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))]);
					}
				}
				for (point [i2] : [1:mm2-1]) {
					for (point [i1] : [1:mm1-1]) {
						u[uoff+2*i1-1-d1+n1*(2*i2-1-t2+n2*(2*i3-1-t3))] +=
							0.25*(u[zoff+i1-1+mm1*(i2+mm2*i3)]
									+u[zoff+i1-1+mm1*(i2-1+mm2*i3)]
									+u[zoff+i1-1+mm1*(i2+mm2*(i3-1))]
									+u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))]);
					}
					for (point [i1] : [1:mm1-1]) {
						u[uoff+2*i1-1-t1+n1*(2*i2-1-t2+n2*(2*i3-1-t3))] +=
							0.125*(u[zoff+i1+mm1*(i2+mm2*i3)]
									+u[zoff+i1+mm1*(i2-1+mm2*i3)]
									+u[zoff+i1-1+mm1*(i2+mm2*i3)]
									+u[zoff+i1-1+mm1*(i2-1+mm2*i3)]
									+u[zoff+i1+mm1*(i2+mm2*(i3-1))]
									+u[zoff+i1+mm1*(i2-1+mm2*(i3-1))]
									+u[zoff+i1-1+mm1*(i2+mm2*(i3-1))]+u[zoff+i1-1+mm1*(i2-1+mm2*(i3-1))]);
					}
				}
			}
		}
	}
}

