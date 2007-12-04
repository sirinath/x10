/*
 * (c) Copyright IBM Corporation 2007
 *
 * $Id: Test_transpose_contig.cc,v 1.4 2007-12-04 08:32:51 ganeshvb Exp $ 
 * This file is part of X10 Runtime System.
 */

/* transpose a 2D array */

#include <iostream>
#include <x10/timers.h>
#include <x10/xassert.h>
#include <x10/x10lib.h>
#include "lapi.h"

using namespace std;
using namespace x10lib;

double* data;
double* data2;
const int* lda;

struct __closure__0__args
{
  __closure__0__args (int offset)
    : _offset (offset)
  {}

  int _offset;
};

struct __closure__0 : Closure 
{
  __closure__0() :
    Closure (sizeof(__closure__0), 0),
  _args (0) {}

  __closure__0 (int offset) : 
    Closure (sizeof(__closure__0), 0),
    _args (offset)
  {    
  }

  __closure__0__args _args;
};


void* arrayCopySwitch (int handler, void* buf)
{
  __closure__0__args* closure_args = (__closure__0__args*) buf;
  
  return data + closure_args->_offset;
}

int 
main (int argc, char* argv[])
{
  x10lib::Init(NULL,0);

  if (argc != 2)  {
     cout << "Syntax: ./a.out SQRTN " << endl;
     exit (-1);
  }

  int SQRT_N = atoi (argv[1]);

  int X = SQRT_N /  __x10_num_places;

  int Y = SQRT_N;

  int nRows = SQRT_N / __x10_num_places;

  int N = X * Y; 
  
  data = new double[N];
  data2 = new double[N];

  int P = x10lib::__x10_my_place;

  for (int i = 0; i < X; i++)
    for (int j = 0; j < Y; j++)
      {
	data [i * Y + j] = (i + P * nRows) * Y + j;
       	data2 [i * Y + j] =  0;
      }
  

  int rank = 2;
  Point<2> origin (0, 0);
  Point<2> diagonal (X-1, Y-1);
  RectangularRegion<2> r (origin, diagonal);

  lda = r.lda();

  __closure__0* closure = new __closure__0 [__x10_num_places];

  x10lib::SyncGlobal();
  
  int n = 0;

  double timers[4];
  timers[0] = nanoTime();
  /* tranpose local chunk and copy to contiguous location */
  for (int i = 0; i < X; i++)
   for (int j = 0; j < Y; j++)
      data2 [j * X + i] = data [ i * Y + j];

  x10lib::SyncGlobal();
  timers[1] = nanoTime();

  /* use single arrayCopy for every destination */
  int chunk_size = nRows * nRows;
  for (int k=0; k <__x10_num_places; ++k) { 

      int srcI= k * chunk_size; 
      int destI= P * chunk_size;
     
      (closure + n)->_args._offset = destI;
      asyncArrayCopyRaw (data2 + srcI, closure + n, chunk_size*sizeof(double), k);
      n++;
  }
  
  x10lib::SyncGlobal();
  timers[2] = nanoTime();

  /* scatter the result back, so we get the row contributions from 
   * different processors in contiguous locations
   */
  int n2 = X;
  int n1 = __x10_num_places;
  for (int k = 0; k < n2; k++)
    for (int i = 0; i < n1; i++)
      for (int j = 0; j < n2; j++)
      data2 [k * n2 * n1 + j + i * n2] = data [ i * n2 * n2 + k * n2 + j];
  
  x10lib::SyncGlobal();
  timers[3] = nanoTime();

  for (int i = 0; i < X; i++)
    for (int j = 0; j < Y; j++)
      {
	assert (data2 [i * Y + j] == j * Y + i + P * nRows);       	
      }
 
  cout << "*************** Summary BEGIN ***********************************" << endl
       << "***************** Timing (Seconds) BEGIN************************* " << endl
       << "Total Time: " << (timers[3] - timers[0]) * 1e-9 << endl
       << "local transposition: " << (timers[1] - timers[0]) * 1e-9 << endl
       << "array copy: " << (timers[2] - timers[1]) * 1e-9 << endl
       << "scatter: " << (timers[3] - timers[2]) * 1e-9 << endl
       << "***************** Timing (Seconds) END ************************* " << endl
       << "Total Memory (per place) : " << 2 * X * Y * sizeof(double) / (1024 * 1024) << "Mega Bytes" << endl
       << "*************** Summary END ***********************************" << endl ;

  cout << "Test_transpose_contig PASSED" << endl;

  delete [] closure;
  x10lib::Finalize();
  
  return 0;
}
