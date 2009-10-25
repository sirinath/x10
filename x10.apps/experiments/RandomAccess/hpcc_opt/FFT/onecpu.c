/* -*- mode: C; tab-width: 2; indent-tabs-mode: nil; fill-column: 79; coding: iso-latin-1-unix -*- */

#include <hpcc.h>

int
HPCC_StarFFT(HPCC_Params *params) {
  int commRank, commSize;
  int rv, errCount, failure = 0, failureAll = 0;
  double localGflops, minGflops, maxGflops, avgGflops;
  int n;
  FILE *outputFile;
  MPI_Comm comm = MPI_COMM_WORLD;

  localGflops = minGflops = maxGflops = avgGflops = 0.0;

  MPI_Comm_size( comm, &commSize );
  MPI_Comm_rank( comm, &commRank );

  rv = HPCC_TestFFT( params, 0 == commRank, &localGflops, &n, &failure );
  params->FFT_N = n;

  MPI_Reduce( &rv, &errCount, 1, MPI_INT, MPI_SUM, 0, comm );
  MPI_Allreduce( &failure, &failureAll, 1, MPI_INT, MPI_MAX, comm );
  if (failureAll) params->Failure = 1;

  MPI_Reduce( &localGflops, &minGflops, 1, MPI_DOUBLE, MPI_MIN, 0, comm );
  MPI_Reduce( &localGflops, &avgGflops, 1, MPI_DOUBLE, MPI_SUM, 0, comm );
  MPI_Reduce( &localGflops, &maxGflops, 1, MPI_DOUBLE, MPI_MAX, 0, comm );
  avgGflops /= commSize;

  MPI_Bcast( &avgGflops, 1, MPI_DOUBLE, 0, comm ); params->StarFFTGflops = avgGflops;

  BEGIN_IO( commRank, params->outFname, outputFile);
  fprintf( outputFile, "Node(s) with error %d\n", errCount );
  fprintf( outputFile, "Minimum Gflop/s %.6f\n", minGflops );
  fprintf( outputFile, "Average Gflop/s %.6f\n", avgGflops );
  fprintf( outputFile, "Maximum Gflop/s %.6f\n", maxGflops );
  END_IO( commRank, outputFile );

  return 0;
}

int
HPCC_SingleFFT(HPCC_Params *params) {
  int commRank, commSize;
  int rv, errCount, rank, failure = 0;
  int n;
  double localGflops;
  double scl = 1.0 / RAND_MAX;
  FILE *outputFile;
  MPI_Comm comm = MPI_COMM_WORLD;

  localGflops = 0.0;

  MPI_Comm_size( comm, &commSize );
  MPI_Comm_rank( comm, &commRank );

  srand(time(NULL));
  scl *= commSize;

  /* select a node at random, but not node 0 (unless there is just one node) */
  if (1 == commSize)
    rank = 0;
  else
    for (rank = 0; ; rank = (int)(scl * rand())) {
      if (rank > 0 && rank < commSize) break;
    }

  MPI_Bcast( &rank, 1, MPI_INT, 0, comm ); /* broadcast the rank selected on node 0 */

  if (commRank == rank) /* if this node has been selected */
    rv = HPCC_TestFFT( params, 0 == commRank, &localGflops, &n, &failure );

  MPI_Bcast( &rv, 1, MPI_INT, rank, comm ); /* broadcast error code */
  errCount = rv;
  MPI_Bcast( &failure, 1, MPI_INT, rank, comm ); /* broadcast failure indication */
  if (failure) params->Failure = 1;

  /* broadcast results */
  MPI_Bcast( &localGflops, 1, MPI_DOUBLE, rank, comm );
  params->SingleFFTGflops = localGflops;

  BEGIN_IO( commRank, params->outFname, outputFile);
  fprintf( outputFile, "Node(s) with error %d\n", errCount );
  fprintf( outputFile, "Node selected %d\n", rank );
  fprintf( outputFile, "Single FFT Gflop/s %.6f\n", localGflops );
  END_IO( commRank, outputFile );

  return 0;
}
