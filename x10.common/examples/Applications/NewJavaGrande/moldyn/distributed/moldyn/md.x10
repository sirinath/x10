/**************************************************************************
 *                                                                         *
 *             Java Grande Forum Benchmark Suite - MPJ Version 1.0         *
 *                                                                         *
 *                            produced by                                  *
 *                                                                         *
 *                  Java Grande Benchmarking Project                       *
 *                                                                         *
 *                                at                                       *
 *                                                                         *
 *                Edinburgh Parallel Computing Centre                      *
 *                                                                         * 
 *                email: epcc-javagrande@epcc.ed.ac.uk                     *
 *                                                                         *
 *                  Original version of this code by                       *
 *                         Dieter Heermann                                 * 
 *                       converted to Java by                              *
 *                Lorna Smith  (l.smith@epcc.ed.ac.uk)                     *
 *                   (see copyright notice below)                          *
 *                                                                         *
 *      This version copyright (c) The University of Edinburgh, 2001.      *
 *                         All rights reserved.                            *
 *                                                                         *
 **************************************************************************/

/**
 *
 * moldyn with multiple places ported to x10.
 *
 * @author kemal 3/2005
 * 
 * @author xinb 5/2006
 * 	o	aggregate primitive reads/writes through futures/asyncs, which 
 * 		improve performance greatly;
 */

package moldyn;

import java.util.*;
import java.text.NumberFormat;

public class md {
	
	public const int ITERS = 100;
	public const double LENGTH = 50e-10;
	public const double m = 4.0026;
	public const double mu = 1.66056e-27;
	public const double kb = 1.38066e-23;
	public const double TSIM = 50;
	public const double deltat = 5e-16;
	public Particle one [] = new Particle[0];
	public double epot = 0.0;
	public double vir = 0.0;
	public double count = 0.0;
	int size;
	//int datasizes[] = {8,13};
	int datasizes[] = {4,13};
	
	public int interactions = 0;
	
	int i,j,k,lg,mdsize,move,mm;
	
	double l,rcoff,rcoffs,side,sideh,hsq,hsq2,vel; 
	double a,r,sum,tscale,sc,ekin,ek,ts,sp;    
	double den = 0.83134;
	double tref = 0.722;
	double h = 0.064;
	double vaver,vaverh,rand;
	double etot,temp,pres,rp;
	double u1,u2,v1,v2,s;
	
	int ijk,npartm,partsize,iseed,tint;
	int irep = 10;
	int istop = 19;
	int iprint = 10;
	int movemx = 50;
	
	Random randnum;
	int rank;
	int nprocess;
	
	public void initialise(int rank, int nprocess) {
		this.rank=rank;
		this.nprocess=nprocess;
		
		/* Parameter determination */
		
		mm = datasizes[size];
		partsize = mm*mm*mm*4;
		mdsize = partsize;
		one = new Particle [mdsize];
		l = LENGTH;
		
		side = Math.pow((mdsize/den),0.3333333);
		rcoff = mm/4.0;
		
		a = side/mm;
		sideh = side*0.5;
		hsq = h*h;
		hsq2 = hsq*0.5;
		npartm = mdsize - 1;
		rcoffs = rcoff * rcoff;
		tscale = 16.0 / (1.0 * mdsize - 1.0);
		vaver = 1.13 * Math.sqrt(tref / 24.0);
		vaverh = vaver * h;
		
		
		/* Particle Generation */
		
		ijk = 0;
		for (lg=0; lg<=1; lg++) {
			for (i=0; i<mm; i++) {
				for (j=0; j<mm; j++) {
					for (k=0; k<mm; k++) {
						one[ijk] = new Particle((i*a+lg*a*0.5),(j*a+lg*a*0.5),(k*a),
								0.0,0.0,0.0,0.0,0.0,0.0);
						ijk = ijk + 1;
					}
				}
			}
		}
		for (lg=1; lg<=2; lg++) {
			for (i=0; i<mm; i++) {
				for (j=0; j<mm; j++) {
					for (k=0; k<mm; k++) {
						one[ijk] = new Particle((i*a+(2-lg)*a*0.5),(j*a+(lg-1)*a*0.5),
								(k*a+a*0.5),0.0,0.0,0.0,0.0,0.0,0.0);
						ijk = ijk + 1;
					}
				}
			}
		}
		
		/* Initialise velocities */
		
		iseed = 0;
		v1 = 0.0;
		v2 = 0.0;
		
		randnum = new Random(iseed,v1,v2);
		
		for (i=0; i<mdsize; i+=2) {
			r  = randnum.seed();
			one[i].xvelocity = r*randnum.v1;
			one[i+1].xvelocity  = r*randnum.v2;
		}
		
		for (i=0; i<mdsize; i+=2) {
			r  = randnum.seed();
			one[i].yvelocity = r*randnum.v1;
			one[i+1].yvelocity  = r*randnum.v2;
		}
		
		for (i=0; i<mdsize; i+=2) {
			r  = randnum.seed();
			one[i].zvelocity = r*randnum.v1;
			one[i+1].zvelocity  = r*randnum.v2;
		}
		
		/* velocity scaling */
		
		ekin = 0.0;
		sp = 0.0;
		
		for(i=0;i<mdsize;i++) {
			sp = sp + one[i].xvelocity;
		}
		sp = sp / mdsize;
		
		for(i=0;i<mdsize;i++) {
			one[i].xvelocity = one[i].xvelocity - sp;
			ekin = ekin + one[i].xvelocity*one[i].xvelocity;
		}
		
		sp = 0.0;
		for(i=0;i<mdsize;i++) {
			sp = sp + one[i].yvelocity;
		}
		sp = sp / mdsize;
		
		for(i=0;i<mdsize;i++) {
			one[i].yvelocity = one[i].yvelocity - sp;
			ekin = ekin + one[i].yvelocity*one[i].yvelocity;
		}
		
		sp = 0.0;
		for(i=0;i<mdsize;i++) {
			sp = sp + one[i].zvelocity;
		}
		sp = sp / mdsize;
		
		for(i=0;i<mdsize;i++) {
			one[i].zvelocity = one[i].zvelocity - sp;
			ekin = ekin + one[i].zvelocity*one[i].zvelocity;
		}
		
		ts = tscale * ekin;
		sc = h * Math.sqrt(tref/ts);
		
		for(i=0;i<mdsize;i++) {
			
			one[i].xvelocity = one[i].xvelocity * sc;     
			one[i].yvelocity = one[i].yvelocity * sc;     
			one[i].zvelocity = one[i].zvelocity * sc;     
			
		}
		
		/* MD simulation */
		
	}
	
	public void runiters(final clock C, md[.] benchP) {
		
		int n=0;
		move = 0;
		for (move=0;move<movemx;move++) {
			
			for (i=0;i<mdsize;i++) {
				one[i].domove(side);        /* move the particles and update velocities */
			}
			
			epot = 0.0;
			vir = 0.0;
			
			
			for (i=0+rank;i<mdsize;i+=nprocess) {
				one[i].force(side,rcoff,mdsize,i,this);  /* compute forces */
			}
			
			
			/* global reduction on partial sums of the forces, epot, vir and interactions */ 
			next;
			allreduce(benchP);			
			next;
			
			
			sum = 0.0;
			
			for (i=0;i<mdsize;i++) {
				sum = sum + one[i].mkekin(hsq2);    /*scale forces, update velocities */
			}
			
			ekin = sum/hsq;
			
			vel = 0.0;
			count = 0.0;
			
			for (i=0;i<mdsize;i++) {
				vel = vel + one[i].velavg(vaverh,h,this); /* average velocity */
			}
			
			vel = vel / h;
			
			/* tmeperature scale if required */
			
			if((move < istop) && (((move+1) % irep) == 0)) {
				sc = Math.sqrt(tref / (tscale*ekin));
				for (i=0;i<mdsize;i++) {
					one[i].dscal(sc,1);
				}
				ekin = tref / tscale;
			}
			
			/* sum to get full potential energy and virial */
			if(((move+1) % iprint) == 0) {
				ek = 24.0*ekin;
				epot = 4.0*epot;
				etot = ek + epot;
				temp = tscale * ekin;
				pres = den * 16.0 * (ekin - vir) / mdsize;
				vel = vel / mdsize; 
				rp = (count / mdsize) * 100.0;
			}
			
		}
		next;
		
		
		
	}
	
	void allreduce(md[.] benchP) {
		// Place holder for now to emulate allreduce. To be optimized
		
		if (rank!=0)  return;
		final md[.] P= benchP; //JGFMolDynBench.P;
		final md t=new md();
		t.mdsize=mdsize;
		t.one= new Particle[mdsize];
		for(point [k]: [0:(mdsize-1)]) t.one[k]=new Particle(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0);
		// sum reduction
		finish ateach(point [j]: P.distribution) {
			//aggregate
			final double[][] xyzs = new double[3][P[j].one.length];
			for(point [k]: [0:P[j].mdsize-1]) {
				xyzs[0][k] = P[j].one[k].xforce;
				xyzs[1][k] = P[j].one[k].yforce;
				xyzs[2][k] = P[j].one[k].zforce;
			}
			final double virj = P[j].vir;
			final double epotj = P[j].epot;
			final int interactionsj = P[j].interactions;
			//reduce
			async(t) {
				atomic {
					for(point [k]: [0:(t.mdsize-1)]) {
						t.one[k].xforce+= xyzs[0][k]; 
						t.one[k].yforce+= xyzs[1][k]; 
						t.one[k].zforce+= xyzs[2][k]; 
					}
					t.vir += virj; 
					t.epot += epotj; 
					t.interactions += interactionsj; 
				}
			}
		}
		// broadcast
		//aggregate
		final double[][] xyzt = new double[3][mdsize];
		for(point [k]: [0:mdsize-1]) {
			xyzt[0][k] = t.one[k].xforce;
			xyzt[1][k] = t.one[k].yforce;
			xyzt[2][k] = t.one[k].zforce;
		}
		final double virt = t.vir;
		final double epott = t.epot;
		final int interactionst = t.interactions;
		//broadcast
		finish ateach(point [j]: P.distribution) {			
			for(point [k]: [0:(P[j].mdsize-1)]) {
				P[j].one[k].xforce = xyzt[0][k]; 
				P[j].one[k].yforce = xyzt[1][k]; 
				P[j].one[k].zforce = xyzt[2][k]; 
			}
			P[j].vir= virt; 
			P[j].epot= epott; 
			P[j].interactions= interactionst; 
		}
	}
	
	
}
