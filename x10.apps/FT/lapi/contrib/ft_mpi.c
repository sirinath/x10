/* MPI Version of the UPC code. Same operations as UPC version but uses 
   MPI communication library
   
*/

#include <inttypes.h>
#ifdef __INTEL_COMPILER
#include <stdint.h>
#endif

#include <sys/time.h>
#include <stdio.h>
#include <math.h>
#include <assert.h>
#include <string.h>
#include <mpi.h>
#include <stdlib.h>

#include "params.h"
#include "ft.h"

#ifdef M_PI
#  define PI	M_PI
#elif !defined(PI)
#  define PI           3.14159265358979323846  
#endif

#define MPI_SAFE(fncall) do {     \
   int retcode = (fncall);        \
   if (retcode != MPI_SUCCESS) {  \
     fprintf(stderr, "MPI Error: %s\n  returned error code %i\n", #fncall, retcode); \
     abort();                     \
   }                              \
 } while (0)


/*
 * Configurable defines are
 *
 * TIMERS:	if set to 1, timer information will be printed
 * FT_NONBLOCK:	if set to 1 and Berkeley UPC compiler is used, non-blocking
 *		operations will be used.
 *
 */

#ifndef FT_PROFILE
#define FT_PROFILE 1
#endif

#ifndef FT_COMM
#define FT_COMM FT_COMM_SLABS
#endif

#if FT_COMM == FT_COMM_PENCILS
static char *ft_comm_descr = "Pencils";
#define FT_1DFFT FFT1DLocal
#elif FT_COMM == FT_COMM_SLABS
static char *ft_comm_descr = "Slabs";
#define FT_1DFFT FFT1DLocalTranspose
#else
static char *ft_comm_descr = "Alltoall";
#define FT_1DFFT FFT1DLocalTranspose
#endif
  
/*MPI Specific stuff same name as UPC to simplify things*/
int THREADS;
int MYTHREAD;
/**************/

#define MAKE_TAG(p,i)  ((unsigned int)((((unsigned int)p)<<16) | (unsigned int)(i)))
#define MAKE_RTAG(p,i)	MPI_ANY_TAG
void	allocSharedPlanes(ComplexPtr_t *local2d, ComplexPtr_t *local1d, void **lp2o, void **lp1o);
void    computeInitialConditions(ComplexPtr_t cp);
void    FFT2DComm(ComplexPtr_t local2d, ComplexPtr_t local1d, int dir);

void parabolic2(ComplexPtr_t out, ComplexPtr_t in, double *ex, int t, double alpha);
void mult(ComplexPtr_t C, int nsize, double val);
void local_checksum (ComplexPtr_t, double*, double*, int);	
void global_checksum_verify (ComplexPtr_t, double*, double*);


/*
 * Random number generation, as provided by c_randdp.c
 */
extern void   init_seed(double seed);

extern void   init_seed(double seed);
extern double randlc (double *x, double a);
extern void vranlc(int n, double *x, double a, double *y);
extern double ipow46(double, int, int);


int current_orientation = PLANES_ORIENTED_X_Y_Z;
int dims[3][3];

#define FT_BARRIER do { timer_profile(T_BARRIER_WAIT, FT_TIME_BEGIN); \
		        MPI_Barrier(MPI_COMM_WORLD);		      \
			timer_profile(T_BARRIER_WAIT, FT_TIME_END); } while (0)


/*
 * Index Map Function: Allows to abstract away indexing details
 * Args: in: original x  orignal y  original z
 * Input arguments are assumed to represent the global view. That is z refers
 * to actual plane in z... the function will figure out what plane that locally
 * means Args: out: new x new y new z based on changing cube
 */
							
#define CDIM(i)	(dims[current_orientation][i])
#define indexmap(i,j,k)	(i*CDIM(1) + j + (k%(CDIM(2)/THREADS))*(CDIM(0)*CDIM(1)))
#define NEXT_ORIENTATION ((current_orientation+1)%3)

int origindexmap(int x, int y, int z) {
  switch(current_orientation) {
  case PLANES_ORIENTED_X_Y_Z:
    return indexmap(x,y,z);
  case PLANES_ORIENTED_Y_Z_X:
    return indexmap(y,z,x);
  case PLANES_ORIENTED_Z_X_Y:
    return indexmap(z,x,y);
  default:
    return indexmap(x,y,z); /*should never get here*/
  }
}


int getowner(int x, int y, int z) {
  if(current_orientation == PLANES_ORIENTED_X_Y_Z) {
    return (z/(NZ/THREADS));
  } else if(current_orientation == PLANES_ORIENTED_Y_Z_X) {
    return (x/(NX/THREADS));
  } else if(current_orientation == PLANES_ORIENTED_Z_X_Y) {
    return (y/(NY/THREADS));
  }
  return -1;
}
/***************************************/
void switch_view() {
  current_orientation = NEXT_ORIENTATION;
}
void set_view(int new_orientation) {
  current_orientation = new_orientation;
}

void dump_values(ComplexPtr_t in, char* str) {
  int i,j,k;
  int dim0, dim1, dim2;
  dim0 = dims[current_orientation][0];
  dim1 = dims[current_orientation][1];
  dim2 = dims[current_orientation][2];
  for(k = MYTHREAD*(dim2/THREADS); k<(MYTHREAD+1)*(dim2/THREADS); k++) {
    for(i=0; i<dim0; i++) {
      for(j=0; j<dim1; j++) {
	printf("%d> %s: %d %d %d = %f %f\n", MYTHREAD, str, i, j, k, in[indexmap(i,j,k)].real, in[indexmap(i,j,k)].imag);
      }
    }
  }
}


void dump_values_orig(ComplexPtr_t in, char* str) {
  int i,j,k;
  int dim0, dim1, dim2;
  dim0 = NX;
  dim1 = NY;
  dim2 = NZ;
  for(k = MYTHREAD*(dim2/THREADS); k<(MYTHREAD+1)*(dim2/THREADS); k++) {
    for(i=0; i<dim0; i++) {
      for(j=0; j<dim1; j++) {
	printf("%d> %s: %d %d %d = %f %f\n", MYTHREAD, str, i, j, k, in[origindexmap(i,j,k)].real, in[origindexmap(i,j,k)].imag);
      }
    }
  }
}

/* Allocate main timer structure on T0 */

void
print_timers_T0()
{
  int i;
  
  double avg;
  double mflops;
  /* Update all timers on T0 */
  double FTTimers_T0[T_NUMTIMERS]; 
  double total_timers[T_NUMTIMERS];

  for (i=0; i < T_NUMTIMERS; i++)
    total_timers[i] = timer_val(i);
  
  MPI_Reduce(total_timers, FTTimers_T0, T_NUMTIMERS, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

  if (MYTHREAD == 0) {
    char buf[64];

    for (i=0; i < T_NUMTIMERS; i++) {
      avg = FTTimers_T0[i]/(1.0e6*THREADS);
      printf(" 0> %32s: %10.4f (%10.4f)s\n", timer_descr(i), avg, 
	     ((double)FTTimers_T0[i])/(1.0e6));
    } 
	
    avg = (double) (FTTimers_T0[T_TOTAL] / (1.0e6*THREADS));
	
    mflops = (1.0e-6*((double)NX*NY*NZ) *
	      (14.8157 + 7.19641*log((double)NX*NY*NZ)
	       + (5.23518 + 7.21113*log((double)NX*NY*NZ))*MAX_ITER)) /avg;
    snprintf(buf, 64, "%d Threads MFlops Rate", THREADS);
    printf(" 0> %32s: %10.4f mflops\n", buf, mflops);
  }
}


int main(int argc, char **argv) 
{
  /* Local Planes */    
  ComplexPtr_t    localPlanes2d; 
  ComplexPtr_t    localPlanes1d;
  ComplexPtr_t    V;
  
  void *lp2o, *lp1o, *Vo, *exo; 
  double  *ex;
  
  double  checksum_real[MAX_ITER];
  double  checksum_imag[MAX_ITER];
  
  int iter;
  int saved_orientation;
  int i,p;

  /*
   * We only support a 1d layout, for now
   */
  dims[0][0] = NX;
  dims[0][1] = NY+CPAD_COLS;
  dims[0][2] = NZ;

  dims[1][0] = NY;
  dims[1][1] = NZ+CPAD_COLS;
  dims[1][2] = NX;

  dims[2][0] = NZ;
  dims[2][1] = NX+CPAD_COLS;
  dims[2][2] = NY;

  /** MPI initialization process */
  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &THREADS);
  MPI_Comm_rank(MPI_COMM_WORLD, &MYTHREAD);
  MPI_Errhandler_set(MPI_COMM_WORLD, MPI_ERRORS_RETURN);
    
  /*
   * Allocate memory in shared space, cache THREAD allocations and return our
   * local pointer in the shared space.  Then initialize the pointers to our
   * local data and all other thread's pointers to their data.
   */
  allocSharedPlanes(&localPlanes2d, &localPlanes1d, &lp2o, &lp1o);

  FFTInit(THREADS, dims, FT_COMM, localPlanes2d, localPlanes1d);

  V = (ComplexPtr_t) malloc_align(&Vo, SIZEOF_COMPLEX*MAX_PADDED_SIZE);
  ex = (double *) 
    malloc_align(&exo, sizeof(double)*(NX/THREADS)*NY*NZ);
   
  init_exp(ex, 1.0e-6, THREADS, MYTHREAD, NX, NY, NZ);

  /*
   * Run the entire problem once to make sure all the data is touched. THis
   * reduces variable startup costs, which is important for short benchmarks
   * (AS IS from the original Fortrain MPI FT benchmark)
   */
  computeInitialConditions(localPlanes2d);

  FFT2DComm(localPlanes2d, localPlanes1d, FFT_FWD);
  MPI_Barrier(MPI_COMM_WORLD);    /* All 2D FFTs (and communication) must be done */

  FT_1DFFT(localPlanes1d, localPlanes2d, FFT_FWD, PLANES_ORIENTED_X_Y_Z);
  MPI_Barrier(MPI_COMM_WORLD);    /* All threads must have completed their 1D FFTs */
 
  /*
   * Start over from the beginning and benchmark everything
   */

  if (MYTHREAD == 0) {
    printf("\n 0> Berkeley MPI NAS FT 2.3 (NonBlocking=YES Comm=%s FFTBackend=%s"
	   " Class=%c Procs=%d)\n"
	   " 0> %s\n\n", ft_comm_descr, FFTName, class_id_char, THREADS, 
	   class_id_str);
    fflush(stdout);
  }
    
  MPI_Barrier(MPI_COMM_WORLD);

  timer_clear();

  timer_total(T_TOTAL, FT_TIME_BEGIN);
  set_view(PLANES_ORIENTED_X_Y_Z);
    
  timer_profile(T_SETUP, FT_TIME_BEGIN);
  computeInitialConditions(localPlanes2d);
  init_exp(ex, 1.0e-6, THREADS, MYTHREAD, NX, NY, NZ);
  timer_profile(T_SETUP, FT_TIME_END);

  /*
   * Compute 2D FFTs over local planes and distribute the results to all
   * threads in preparation for their 1d FFT
   */


  FFT2DComm(localPlanes2d, localPlanes1d, FFT_FWD);
  FT_BARRIER;

  FT_1DFFT(localPlanes1d, V, FFT_FWD, current_orientation);
  FT_BARRIER;

  switch_view();
  saved_orientation = current_orientation;

  for (iter=1; iter<=MAX_ITER; iter++) {
      
    set_view(saved_orientation);
    parabolic2(localPlanes2d, V, ex, iter, 1.0e-6);

    FFT2DComm(localPlanes2d, localPlanes1d, FFT_BWD);
    FT_BARRIER;

    FT_1DFFT(localPlanes1d, localPlanes2d, FFT_BWD, current_orientation);
    switch_view();

    /* Do local checksums on each thread and store the results in the
       checksum arrays. */
    local_checksum (localPlanes2d, checksum_real, checksum_imag, iter);
  }

  timer_total(T_TOTAL, FT_TIME_END);

  MPI_Barrier(MPI_COMM_WORLD);   

  /* Do a global checksum using all the local checksums and verify results. */
  global_checksum_verify (localPlanes2d, checksum_real, checksum_imag);

  print_timers_T0();
  MPI_Finalize();

  return 0;
}

/*
 * local1d is organized as NZ contiguous blocks of NX*NY/THREADS.
 */

#if FT_COMM == FT_COMM_SLABS
void    
FFT2DComm(ComplexPtr_t local2d, ComplexPtr_t local1d, int dir)
{
  int	    p, i, t;
  int	    dim0, dim1, dim2;
  size_t  plane_size;
  int	    CHUNK_SZ;
  MPI_Request *send_handles;
  MPI_Status	*statuses;
  MPI_Request *recv_handles;
  MPI_Request *t_snd_hand;
  MPI_Request *t_rec_hand;

  dim0 = CDIM(0);
  dim1 = CDIM(1);
  dim2 = CDIM(2);
  plane_size = dim0*dim1;
  CHUNK_SZ = (dim0/THREADS)*dim1;

  send_handles = 
    (MPI_Request*) malloc(sizeof(MPI_Request)*THREADS*(dim2/THREADS)*2);
  statuses = 
    (MPI_Status*) malloc(sizeof(MPI_Status)*THREADS*(dim2/THREADS)*2);
  recv_handles = send_handles + dim2;
  t_snd_hand = send_handles;
  t_rec_hand = recv_handles;
    
  /* 
     initial implementation... all processors work on planes in lock step
     post recieves for local data and then process and send out 
  */
  timer_profile(T_EXCH, FT_TIME_BEGIN);
  MPI_Barrier(MPI_COMM_WORLD);
  for(p=0; p < dim2/THREADS; p++) {
    for(i=0; i<THREADS; i++) {
      unsigned int tag = MAKE_RTAG(p, i);
     /* fprintf (stderr, "Thread %d receiving from thread %d at %d\n", MYTHREAD, i, p);*/
      MPI_SAFE(
	MPI_Irecv(local1d+i*dim2/THREADS*CHUNK_SZ+p*CHUNK_SZ, 
		  CHUNK_SZ*SIZEOF_COMPLEX, MPI_BYTE, i, tag, 
		  MPI_COMM_WORLD, t_rec_hand));
      t_rec_hand++;
    }
  }

  MPI_Barrier(MPI_COMM_WORLD);
  timer_profile(T_EXCH, FT_TIME_END);

  for (p=0; p < dim2/THREADS; p++) {
    ComplexPtr_t Pxy_b = local2d + plane_size*p;

    timer_profile(T_FFT1DCOLS, FT_TIME_BEGIN);
    FFT2DLocalCols(Pxy_b, dir, current_orientation, 
		   &dims[current_orientation][0]);
    timer_profile(T_FFT1DCOLS, FT_TIME_END);

    for (t = 0; t < THREADS; t++) {
      int		    thread = (MYTHREAD + t) % THREADS;
      int		    numrows = dim0 / THREADS;
      ComplexPtr_t    Pxy = Pxy_b + thread*CHUNK_SZ;
      unsigned int    tag = MAKE_TAG(p,MYTHREAD);
	
      timer_profile(T_FFT1DROWS, FT_TIME_BEGIN);
      for (i = 0; i < numrows; i++)
	FFT2DLocalRow(Pxy+i*dim1, dir, current_orientation, 
		      &dims[current_orientation][0]);
      timer_profile(T_FFT1DROWS, FT_TIME_END);

      timer_profile(T_EXCH, FT_TIME_BEGIN);

      MPI_SAFE(
	MPI_Isend(Pxy, CHUNK_SZ*SIZEOF_COMPLEX, MPI_BYTE, thread, 
		  tag, MPI_COMM_WORLD, t_snd_hand));

	    

      t_snd_hand++;
      timer_profile(T_EXCH, FT_TIME_END);
    }
  }

  timer_profile(T_EXCH_WAIT, FT_TIME_BEGIN);
  /* send_handles contains send and receive handles */
  MPI_SAFE(
    MPI_Waitall(THREADS*(dim2/THREADS)*2, send_handles, MPI_STATUSES_IGNORE));
    
  timer_profile(T_EXCH_WAIT, FT_TIME_END);

  free(send_handles);
}

#elif FT_COMM == FT_COMM_PENCILS

void    
FFT2DComm(ComplexPtr_t local2d, ComplexPtr_t local1d, int dir)
{
  int		p, i, t;
  int dim0, dim1, dim2;
  int	slab_sz;
  MPI_Request   *send_handles;
  MPI_Request   *recv_handles;
  MPI_Request *t_snd_hand;
  MPI_Request *t_rec_hand;

  dim0 = dims[current_orientation][0];
  dim1 = dims[current_orientation][1];
  dim2 = dims[current_orientation][2];

  slab_sz = dim0/THREADS*dim1;
    
  send_handles = (MPI_Request*) malloc(sizeof(MPI_Request)*
				       dim0*(dim2/THREADS)*2);
  recv_handles = send_handles + dim0*(dim2/THREADS);
  t_snd_hand = send_handles;
  t_rec_hand = recv_handles;
    
  /* 
     initial implementation... all processors work on planes in lock step
     post recieves for local data and then process and send out 
  */
  timer_profile(T_EXCH, FT_TIME_BEGIN);
  MPI_Barrier(MPI_COMM_WORLD);
  for(p=0; p < dim2/THREADS; p++) {
    for(t=0; t<THREADS; t++) {
      for (i=0; i < dim0/THREADS; i++) {	
	unsigned int tag = MAKE_RTAG(p*THREADS+i, t);

	MPI_SAFE(
	  MPI_Irecv(local1d + t*dim2/THREADS*dim1 + p*dim1 + i*dim1*dim2,
		    dim1*SIZEOF_COMPLEX, MPI_BYTE, t, tag,
		    MPI_COMM_WORLD, t_rec_hand));
	t_rec_hand++;
      }
    }
  }
  MPI_Barrier(MPI_COMM_WORLD);
  timer_profile(T_EXCH, FT_TIME_END);
    
  for (p=0; p < dim2/THREADS; p++) {
    ComplexPtr_t Pxy_b = local2d + dim0*dim1*p;
	
    timer_profile(T_FFT1DCOLS, FT_TIME_BEGIN);
    FFT2DLocalCols(Pxy_b, dir, current_orientation, &dims[current_orientation][0]);
    timer_profile(T_FFT1DCOLS, FT_TIME_END);

    for (i = 0; i < dim0/THREADS; i++) {
      for (t = 0; t < THREADS; t++) {
	int		    thread = (MYTHREAD + t) % THREADS;
	ComplexPtr_t    Pxy = Pxy_b + thread*slab_sz + i*dim1;
	unsigned int    tag = MAKE_TAG(p*THREADS+i,MYTHREAD);

	timer_profile(T_FFT1DROWS, FT_TIME_BEGIN);
	FFT2DLocalRow(Pxy, dir, current_orientation, &dims[current_orientation][0]);
	timer_profile(T_FFT1DROWS, FT_TIME_END);

	timer_profile(T_EXCH, FT_TIME_BEGIN);
	MPI_SAFE(
	  MPI_Isend(Pxy, dim1*SIZEOF_COMPLEX, MPI_BYTE, thread, 
		    tag, MPI_COMM_WORLD, t_snd_hand));
	t_snd_hand++;
	timer_profile(T_EXCH, FT_TIME_END);
      }
    }
  }

  /*
    printf("%d> sent=%8d,recv=%8d,watifor=%8d\n",
    MYTHREAD, (int)(t_snd_hand-send_handles), (int)(t_rec_hand-recv_handles),
    dim0*(dim2/THREADS)*2);
    fflush(stdout);
  */

  timer_profile(T_EXCH_WAIT, FT_TIME_BEGIN);
  /* send_handles contains send and receive handles */
  MPI_SAFE(
    MPI_Waitall(dim0*(dim2/THREADS)*2, send_handles, MPI_STATUSES_IGNORE));
  timer_profile(T_EXCH_WAIT, FT_TIME_END);
  free(send_handles);
}
#else

void
TransposeGlobal(int dim0, int dim1, int dim2, ComplexPtr_t in, ComplexPtr_t out)
{
  size_t   perthread = (dim1*(dim0/THREADS)*(dim2/THREADS));

  timer_profile(T_EXCH, FT_TIME_BEGIN);
  MPI_SAFE(
    MPI_Alltoall(in, perthread*SIZEOF_COMPLEX, MPI_BYTE, 
    out, perthread*SIZEOF_COMPLEX, MPI_BYTE, MPI_COMM_WORLD));
  timer_profile(T_EXCH, FT_TIME_END);

  FT_BARRIER;
}

void
Transpose2Local(int dim0, int dim1, int dim2, ComplexPtr_t in, ComplexPtr_t out)
{
  int i, j;
  int planesz = dim0*dim1;
  int slab01 = dim1*(dim0/THREADS);
  int planes2 = dim2/THREADS;

  for (j = 0; j < planes2; j++) {
    for (i = 0; i < THREADS; i++) {
      memcpy(out + i*planes2*slab01 + j*slab01, 
	     in + i*slab01 + j*planesz,
	     slab01*SIZEOF_COMPLEX);
    }
  }
}

void
FFT2DComm(ComplexPtr_t local2d, ComplexPtr_t local1d, int dir)
{
  int	    p,i;
  int	    dim0,dim1,dim2;
  int	    loc_dim[3];
  static ComplexPtr_t X = NULL;
  static void   *Xorig;

  dim0 = dims[current_orientation][0];
  dim1 = dims[current_orientation][1];
  dim2 = dims[current_orientation][2];
  loc_dim[0] = dim0;
  loc_dim[1] = dim1;
  loc_dim[2] = dim2;

  if (X == NULL) {
    X = (ComplexPtr_t) 
      malloc_align(&Xorig, SIZEOF_COMPLEX*MAX_PADDED_SIZE);
  }

  for (p = 0; p < dim2/THREADS; p++) {
    ComplexPtr_t Pxy_b = local2d + dim0*dim1*p;

    timer_profile(T_FFT1DCOLS, FT_TIME_BEGIN);
    FFT2DLocalCols(Pxy_b, dir, current_orientation, loc_dim);
    timer_profile(T_FFT1DCOLS, FT_TIME_END);

    timer_profile(T_FFT1DROWS, FT_TIME_BEGIN);
    for (i = 0; i < dim0; i++) {
      FFT2DLocalRow(Pxy_b+i*dim1, dir, current_orientation, loc_dim);
    }
    timer_profile(T_FFT1DROWS, FT_TIME_END);
  }

  Transpose2Local(dim0,dim1,dim2,local2d,X);
  TransposeGlobal(dim0,dim1,dim2,X,local1d);
}
  
#endif

/* 
 * Each thread requires twice the size of its partition of the 3D CUBE.
 * 
 * If the FT Cube has 128 planes, each thread has 128/THREADS planes of
 * NX*NY elements, and must allocate two of these.
 * 1. One contiguous chunk of memory to compute 128/THREADS 2D FFTs
 * 2. One contiguous chunk of memory to hold the result of the all-to-all
 *    transpose from the previous 2D FFT.  This memory will be locally
 *    transposed across the X-Z axis and a set of 1D FFTs is computed.
 *
 * Each thread allocates 2*NZ/THREADS of NX*NY complexes and updates the
 * shared pointer in a directory of shared pointers located at 0.  Once all
 * threads have updated the directory at 0, they each cache a copy of
 * shared pointers to the first plane on each thread.
 *
 * Planes (0..PLANE-1) are used to calculate the 2D FFT.
 * Planes (PLANE..2*PLANE-1) are used to distributed the results of the
 *                           local 2D FFTs to all processors.  The 2D
 *                           communication is overlapped with computation.
 * 
 */

void
allocSharedPlanes(ComplexPtr_t *local2d, ComplexPtr_t *local1d, void **lp2o, void **lp1o)
{
  ComplexPtr_t    alloc;

  alloc = (ComplexPtr_t) malloc_align(lp2o, (MAX_PADDED_SIZE+3) * SIZEOF_COMPLEX);
  *local2d = (alloc + 3);

  *local1d =  (ComplexPtr_t)
    malloc_align(lp1o, MAX_PADDED_SIZE * SIZEOF_COMPLEX);
  return;
}

/*
 * Compute initial conditions in NZ/THREADS planes starting at cp;
 *
 */
void 
computeInitialConditions(ComplexPtr_t cp)
{

  /*--------------------------------------------------------------------
    c Fill in array u0 with initial conditions from 
    c random number generator 
    c-------------------------------------------------------------------*/

  double  x0, start, an;
  double  *tmp;
  void *tmp_orig;
  int	    i, j, k, pid;
  size_t  plane_off = NX*NY;

  ComplexPtr_t	c0;
  tmp = (double*) malloc_align(&tmp_orig, sizeof(double)*2*NX);
  start = SEED;

  /*--------------------------------------------------------------------
    c Jump to the starting element for our first plane.
    c
    c 
    c-------------------------------------------------------------------*/
  init_seed(start);
  
  pid = MYTHREAD * NZ/THREADS;	   /* First plane for this thread */

  /*
   * For plane IDs greater than 0, the random number generator needs to be
   * advanced to the correct offset
   */

  an    = ipow46 (A, 2*NX, pid * NY );
  randlc (&start, an);
  an = ipow46 (A, 2 * NX , NY);
	

  /*--------------------------------------------------------------------
    c Go through by z planes filling in one square at a time.
    c-------------------------------------------------------------------*/
   
    
  for (k = 0; k < NZ/THREADS; k++) {
    x0 = start;	
    c0 = cp + k*NX*NYp;

    for (j=0; j<NY; j++) {
      double *tmp_ptr;
      vranlc (2 * NX, &x0, A, tmp);
      tmp_ptr = tmp;
	    
      for(i=0; i<NX; i++) {
	c0[i*(NYp)+j].real = tmp_ptr[i*2+0]; 	
	c0[i*(NYp)+j].imag = tmp_ptr[i*2+1]; 
      }
    }
    if(k!=(NZ/THREADS - 1)) randlc(&start, an);
  }	
  free(tmp_orig);
}

#if 0
void init_exp(double *ex, double alpha)
{
  double   kon;
  int i,j,k,n;
  int it,jt,kt;
  int i_start, i_end;
  double ii,jj;
  
  n=0;
  kon  = -4.*alpha*PI*PI;
  i_start = (NX/THREADS)*MYTHREAD;
  i_end = (NX/THREADS)*(MYTHREAD+1);
  

  for(i=i_start; i < i_end; i++) {
    if (i >= NX/2)
      it = i - NX;
    else
      it = i;
    ii = it*it;

    for(j=0; j < NY; j++) {
      if (j >= NY/2)
        jt = j - NY;
      else
        jt = j;
      jj = jt*jt;

      for (k=0; k < NZ; k++) {
        if (k >= NZ/2)
          kt = k - NZ;
	else
	  kt = k;
	ex[n++] = exp(kon*(ii + jj + kt*kt));
      }
    }
  }
}
#endif

void parabolic2(ComplexPtr_t out, ComplexPtr_t in, 
	        double *ex, int t, double alpha)
{
  int i,j,k,ii,ii2,jj,kk;
  int dim2 = CDIM(2);
  int dim1 = CDIM(1); /* Padded dimension */
  int dim1p= CDIM(1)-CPAD_COLS;
  int dim0 = CDIM(0);
  double mult;
  
  timer_profile(T_EVOLVE, FT_TIME_BEGIN);

  for (k=0; k < dim2/THREADS; k++) {
    kk = k*dim0*dim1;
    for (i=0; i < dim0; i++) {
      ii  = kk + i*dim1;
      ii2 = k*dim0*dim1p + i*dim1p;
      for (j = 0; j < dim1p; j++) {
	jj = ii + j;
	mult = ex[ii2 + j];
	in[jj].real *= mult;
	out[jj].real = in[jj].real;
	in[jj].imag *= mult;
	out[jj].imag = in[jj].imag;
      }
    }
  }

  timer_profile(T_EVOLVE, FT_TIME_END);
}


/**************************************************************************
 *
 *  Do a local checksum on all the processes.
 *
 *************************************************************************/
void local_checksum (ComplexPtr_t C, double *real, double *imag, int iter)
{
  int j, q, r, s;
  
  int proc;
  ComplexPtr_t tmp;

  timer_profile (T_CHECKSUM, FT_TIME_BEGIN);

  real[iter-1] = 0.0;
  imag[iter-1] = 0.0;

  for (j = 1; j <= 1024; ++j) {
    q = j % NX;
    r = (3*j) % NY;
    s = (5*j) % NZ;
      
    proc = getowner (q,r,s);

    if (MYTHREAD==proc) 
    {
      tmp = C + origindexmap (q,r,s);
	
      real[iter-1] += (tmp->real);
      imag[iter-1] += (tmp->imag);
    } 
  }

  timer_profile (T_CHECKSUM, FT_TIME_END);
}



/**************************************************************************
 *
 *  Do a global checksum using all of the historical local checksums,
 *  and print out the results of the checksums (as the original benchmark
 *  does.
 *
 *************************************************************************/
void global_checksum_verify (ComplexPtr_t C, double *real, double *imag)
{
  double checksum_real[MAX_ITER];
  double checksum_imag[MAX_ITER];
  double c[2];
  int iter;

  timer_profile (T_CHECKSUM, FT_TIME_BEGIN);

  for (iter = 1; iter <= MAX_ITER; iter++)
  {
    double sum[2] = {0.0, 0.0}; 

    c[0] = real[iter-1];
    c[1] = imag[iter-1];

    MPI_Reduce (c, sum, 2, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);
    checksum_real[iter-1] = ((sum[0]/NX)/NY)/NZ;
    checksum_imag[iter-1] = ((sum[1]/NX)/NY)/NZ;

    if (MYTHREAD==0) 
    {
      fprintf (stdout, " 0> %30s %2d: %#17.14g %#17.14g\n",
	       "Checksum", iter, checksum_real[iter-1], checksum_imag[iter-1]);
    }
  }

  if (MYTHREAD == 0)
    checksum_verify (NX, NY, NZ, MAX_ITER, checksum_real, checksum_imag);

  timer_profile (T_CHECKSUM, FT_TIME_END);
}
