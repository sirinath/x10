/* -*- mode: C; tab-width: 2; indent-tabs-mode: nil; -*- */

/*
 * This code has been contributed by the DARPA HPCS program.  Contact
 * David Koester <dkoester@mitre.org> or Bob Lucas <rflucas@isi.edu>
 * if you have questions.
 *
 *
 * GUPS (Giga UPdates per Second) is a measurement that profiles the memory
 * architecture of a system and is a measure of performance similar to MFLOPS.
 * The HPCS HPCchallenge RandomAccess benchmark is intended to exercise the
 * GUPS capability of a system, much like the LINPACK benchmark is intended to
 * exercise the MFLOPS capability of a computer.  In each case, we would
 * expect these benchmarks to achieve close to the "peak" capability of the
 * memory system. The extent of the similarities between RandomAccess and
 * LINPACK are limited to both benchmarks attempting to calculate a peak system
 * capability.
 *
 * GUPS is calculated by identifying the number of memory locations that can be
 * randomly updated in one second, divided by 1 billion (1e9). The term "randomly"
 * means that there is little relationship between one address to be updated and
 * the next, except that they occur in the space of one half the total system
 * memory.  An update is a read-modify-write operation on a table of 64-bit words.
 * An address is generated, the value at that address read from memory, modified
 * by an integer operation (add, and, or, xor) with a literal value, and that
 * new value is written back to memory.
 *
 * We are interested in knowing the GUPS performance of both entire systems and
 * system subcomponents --- e.g., the GUPS rating of a distributed memory
 * multiprocessor the GUPS rating of an SMP node, and the GUPS rating of a
 * single processor.  While there is typically a scaling of FLOPS with processor
 * count, a similar phenomenon may not always occur for GUPS.
 *
 * Select the memory size to be the power of two such that 2^n <= 1/2 of the
 * total memory.  Each CPU operates on its own address stream, and the single
 * table may be distributed among nodes. The distribution of memory to nodes
 * is left to the implementer.  A uniform data distribution may help balance
 * the workload, while non-uniform data distributions may simplify the
 * calculations that identify processor location by eliminating the requirement
 * for integer divides. A small (less than 1%) percentage of missed updates
 * are permitted.
 *
 * When implementing a benchmark that measures GUPS on a distributed memory
 * multiprocessor system, it may be required to define constraints as to how
 * far in the random address stream each node is permitted to "look ahead".
 * Likewise, it may be required to define a constraint as to the number of
 * update messages that can be stored before processing to permit multi-level
 * parallelism for those systems that support such a paradigm.  The limits on
 * "look ahead" and "stored updates" are being implemented to assure that the
 * benchmark meets the intent to profile memory architecture and not induce
 * significant artificial data locality. For the purpose of measuring GUPS,
 * we will stipulate that each thread is permitted to look ahead no more than
 * 1024 random address stream samples with the same number of update messages
 * stored before processing.
 *
 * The supplied MPI-1 code generates the input stream {A} on all processors
 * and the global table has been distributed as uniformly as possible to
 * balance the workload and minimize any Amdahl fraction.  This code does not
 * exploit "look-ahead".  Addresses are sent to the appropriate processor
 * where the table entry resides as soon as each address is calculated.
 * Updates are performed as addresses are received.  Each message is limited
 * to a single 64 bit long integer containing element ai from {A}.
 * Local offsets for T[ ] are extracted by the destination processor.
 *
 * If the number of processors is equal to a power of two, then the global
 * table can be distributed equally over the processors.  In addition, the
 * processor number can be determined from that portion of the input stream
 * that identifies the address into the global table by masking off log2(p)
 * bits in the address.
 *
 * If the number of processors is not equal to a power of two, then the global
 * table cannot be equally distributed between processors.  In the MPI-1
 * implementation provided, there has been an attempt to minimize the differences
 * in workloads and the largest difference in elements of T[ ] is one.  The
 * number of values in the input stream generated by each processor will be
 * related to the number of global table entries on each processor.
 *
 * The MPI-1 version of RandomAccess treats the potential instance where the
 * number of processors is a power of two as a special case, because of the
 * significant simplifications possible because processor location and local
 * offset can be determined by applying masks to the input stream values.
 * The non power of two case uses an integer division to determine the processor
 * location.  The integer division will be more costly in terms of machine
 * cycles to perform than the bit masking operations
 *
 * For additional information on the GUPS metric, the HPCchallenge RandomAccess
 * Benchmark,and the rules to run RandomAccess or modify it to optimize
 * performance -- see http://icl.cs.utk.edu/hpcc/
 *
 */

/* Jan 2005
 *
 * This code has been modified to allow local bucket sorting of updates.
 * The total maximum number of updates in the local buckets of a process
 * is currently defined in "RandomAccess.h" as MAX_TOTAL_PENDING_UPDATES.
 * When the total maximum number of updates is reached, the process selects
 * the bucket (or destination process) with the largest number of
 * updates and sends out all the updates in that bucket. See buckets.c
 * for details about the buckets' implementation.
 *
 * This code also supports posting multiple MPI receive descriptors (based
 * on a contribution by David Addison).
 *
 * In addition, this implementation provides an option for limiting
 * the execution time of the benchmark to a specified time bound
 * (see time_bound.c). The time bound is currently defined in
 * time_bound.h, but it should be a benchmark parameter. By default
 * the benchmark will execute the recommended number of updates,
 * that is, four times the global table size.
 */


#include <hpcc.h>

#include "RandomAccess.h"
#include "buckets.h"
#include "time_bound.h"
#include "verification.h"

#define __OPT__ 1 

/* Allocate main table (in global memory) */
u64Int *HPCC_Table;

u64Int LocalSendBuffer[LOCAL_BUFFER_SIZE];
u64Int LocalRecvBuffer[MAX_RECV*LOCAL_BUFFER_SIZE];

#ifndef LONG_IS_64BITS
static void
Sum64(void *invec, void *inoutvec, int *len, MPI_Datatype *datatype) {
  int i, n = *len; s64Int *invec64 = (s64Int *)invec, *inoutvec64 = (s64Int *)inoutvec;
  for (i = n; i; i--, invec64++, inoutvec64++) *inoutvec64 += *invec64;
}
#endif

static void
AnyNodesMPIRandomAccessUpdate(u64Int logTableSize,
                              u64Int TableSize,
                              s64Int LocalTableSize,
                              u64Int MinLocalTableSize,
                              u64Int GlobalStartMyProc,
                              u64Int Top,
                              int logNumProcs,
                              int NumProcs,
                              int Remainder,
                              int MyProc,
                              s64Int ProcNumUpdates,
                              MPI_Datatype INT64_DT)
{
  s64Int i, j;
  int proc_count;

  s64Int SendCnt;
  u64Int Ran;
  s64Int WhichPe;
  u64Int GlobalOffset, LocalOffset;
  int NumberReceiving = NumProcs - 1;
#ifdef USE_MULTIPLE_RECV
  int index, NumRecvs;
  MPI_Request inreq[MAX_RECV]  = { MPI_REQUEST_NULL };
  MPI_Request outreq = MPI_REQUEST_NULL;
#else
  MPI_Request inreq, outreq = MPI_REQUEST_NULL;
#endif
  u64Int inmsg;
  int bufferBase;

  MPI_Status finish_statuses[NumProcs];
  MPI_Request finish_req[NumProcs];

  MPI_Status status;
  MPI_Status ignoredStatus; /* Cray X1 doesn't have MPI_STATUS_IGNORE */
  int have_done;

  int pe;
  int pendingUpdates;
  int maxPendingUpdates;
  int localBufferSize;
  int peUpdates;
  int recvUpdates;
  Bucket_Ptr Buckets;

  pendingUpdates = 0;
  maxPendingUpdates = MAX_TOTAL_PENDING_UPDATES;
  localBufferSize = LOCAL_BUFFER_SIZE;
  Buckets = HPCC_InitBuckets(NumProcs, maxPendingUpdates);


  /* Initialize main table */
  for (i=0; i<LocalTableSize; i++)
    HPCC_Table[i] = i + GlobalStartMyProc;

  /* Perform updates to main table.  The scalar equivalent is:
   *
   *     u64Int Ran;
   *     Ran = 1;
   *     for (i=0; i<NUPDATE; i++) {
   *       Ran = (Ran << 1) ^ (((s64Int) Ran < 0) ? POLY : 0);
   *       Table[Ran & (TABSIZE-1)] ^= Ran;
   *     }
   */

  SendCnt = ProcNumUpdates; /* SendCnt = (4 * LocalTableSize); */
  Ran = HPCC_starts (4 * GlobalStartMyProc);

  pendingUpdates = 0;
  i = 0;

#ifdef USE_MULTIPLE_RECV
  NumRecvs = (NumProcs > 4) ? (Mmin(4,MAX_RECV)) : 1;
  for (j = 0; j < NumRecvs; j++)
    MPI_Irecv(&LocalRecvBuffer[j*LOCAL_BUFFER_SIZE], localBufferSize,
              INT64_DT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD,
              &inreq[j]);
#else
  MPI_Irecv(&LocalRecvBuffer, localBufferSize, INT64_DT,
            MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &inreq);
#endif

  while (i < SendCnt) {

    /* receive messages */
    do {
#ifdef USE_MULTIPLE_RECV
      MPI_Testany(NumRecvs, inreq, &index, &have_done, &status);
#else
      MPI_Test(&inreq, &have_done, &status);
#endif
      if (have_done) {
        if (status.MPI_TAG == UPDATE_TAG) {
          MPI_Get_count(&status, INT64_DT, &recvUpdates);
#ifdef USE_MULTIPLE_RECV
          bufferBase = index*LOCAL_BUFFER_SIZE;
#else
          bufferBase = 0;
#endif
          for (j=0; j < recvUpdates; j ++) {
            inmsg = LocalRecvBuffer[bufferBase+j];
            LocalOffset = (inmsg & (TableSize - 1)) - GlobalStartMyProc;
            HPCC_Table[LocalOffset] ^= inmsg;
          }

        } else if (status.MPI_TAG == FINISHED_TAG) {
          /* we got a done message.  Thanks for playing... */
          NumberReceiving--;
        } else {
          abort();
        }
#ifdef USE_MULTIPLE_RECV
        MPI_Irecv(&LocalRecvBuffer[index*LOCAL_BUFFER_SIZE], localBufferSize,
                  INT64_DT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD,
                  &inreq[index]);
#else
        MPI_Irecv(&LocalRecvBuffer, localBufferSize, INT64_DT,
                  MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &inreq);
#endif
      }
    } while (have_done && NumberReceiving > 0);


    if (pendingUpdates < maxPendingUpdates) {
      Ran = (Ran << 1) ^ ((s64Int) Ran < ZERO64B ? POLY : ZERO64B);
      GlobalOffset = Ran & (TableSize-1);
      if ( GlobalOffset < Top)
        WhichPe = ( GlobalOffset / (MinLocalTableSize + 1) );
      else
        WhichPe = ( (GlobalOffset - Remainder) / MinLocalTableSize );

      if (WhichPe == MyProc) {
        LocalOffset = (Ran & (TableSize - 1)) - GlobalStartMyProc;
        HPCC_Table[LocalOffset] ^= Ran;
      }
      else {
        HPCC_InsertUpdate(Ran, WhichPe, Buckets);
        pendingUpdates++;
      }
      i++;
    }

    else {
      MPI_Test(&outreq, &have_done, MPI_STATUS_IGNORE);
      if (have_done) {
        outreq = MPI_REQUEST_NULL;
        pe = HPCC_GetUpdates(Buckets, LocalSendBuffer, localBufferSize, &peUpdates);
        MPI_Isend(&LocalSendBuffer, peUpdates, INT64_DT, (int)pe, UPDATE_TAG,
                  MPI_COMM_WORLD, &outreq);
        pendingUpdates -= peUpdates;
      }
    }

  }

  /* send remaining updates in buckets */
  while (pendingUpdates > 0) {

    /* receive messages */
    do {
#ifdef USE_MULTIPLE_RECV
      MPI_Testany(NumRecvs, inreq, &index, &have_done, &status);
#else
      MPI_Test(&inreq, &have_done, &status);
#endif
      if (have_done) {
        if (status.MPI_TAG == UPDATE_TAG) {
          MPI_Get_count(&status, INT64_DT, &recvUpdates);
#ifdef USE_MULTIPLE_RECV
          bufferBase = index*LOCAL_BUFFER_SIZE;
#else
          bufferBase = 0;
#endif
          for (j=0; j < recvUpdates; j ++) {
            inmsg = LocalRecvBuffer[bufferBase+j];
            LocalOffset = (inmsg & (TableSize - 1)) - GlobalStartMyProc;
            HPCC_Table[LocalOffset] ^= inmsg;
          }

        } else if (status.MPI_TAG == FINISHED_TAG) {
          /* we got a done message.  Thanks for playing... */
          NumberReceiving--;
        } else {
          abort();
        }
#ifdef USE_MULTIPLE_RECV
        MPI_Irecv(&LocalRecvBuffer[index*LOCAL_BUFFER_SIZE], localBufferSize,
                  INT64_DT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD,
                  &inreq[index]);
#else
        MPI_Irecv(&LocalRecvBuffer, localBufferSize, INT64_DT,
                  MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &inreq);
#endif
      }
    } while (have_done && NumberReceiving > 0);


    MPI_Test(&outreq, &have_done, MPI_STATUS_IGNORE);
    if (have_done) {
      outreq = MPI_REQUEST_NULL;
      pe = HPCC_GetUpdates(Buckets, LocalSendBuffer, localBufferSize, &peUpdates);
      MPI_Isend(&LocalSendBuffer, peUpdates, INT64_DT, (int)pe, UPDATE_TAG,
                MPI_COMM_WORLD, &outreq);
      pendingUpdates -= peUpdates;
    }

  }

  /* send our done messages */
  for (proc_count = 0 ; proc_count < NumProcs ; ++proc_count) {
    if (proc_count == MyProc) { finish_req[MyProc] = MPI_REQUEST_NULL; continue; }
    /* send garbage - who cares, no one will look at it */
    MPI_Isend(&Ran, 1, INT64_DT, proc_count, FINISHED_TAG,
              MPI_COMM_WORLD, finish_req + proc_count);
  }

  /* Finish everyone else up... */
  while (NumberReceiving > 0) {
#ifdef USE_MULTIPLE_RECV
    MPI_Waitany(NumRecvs, inreq, &index, &status);
#else
    MPI_Wait(&inreq, &status);
#endif
    if (status.MPI_TAG == UPDATE_TAG) {
      MPI_Get_count(&status, INT64_DT, &recvUpdates);
#ifdef USE_MULTIPLE_RECV
      bufferBase = index * LOCAL_BUFFER_SIZE;
#else
      bufferBase = 0;
#endif
      for (j=0; j < recvUpdates; j ++) {
        inmsg = LocalRecvBuffer[bufferBase+j];
        LocalOffset = (inmsg & (TableSize - 1)) - GlobalStartMyProc;
        HPCC_Table[LocalOffset] ^= inmsg;
      }

    } else if (status.MPI_TAG == FINISHED_TAG) {
      /* we got a done message.  Thanks for playing... */
      NumberReceiving--;
    } else {
      abort();
    }
#ifdef USE_MULTIPLE_RECV
    MPI_Irecv(&LocalRecvBuffer[index*LOCAL_BUFFER_SIZE], localBufferSize,
              INT64_DT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD,
              &inreq[index]);
#else
    MPI_Irecv(&LocalRecvBuffer, localBufferSize, INT64_DT,
              MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &inreq);
#endif
  }

  MPI_Waitall( NumProcs, finish_req, finish_statuses);

  /* Be nice and clean up after ourselves */
  HPCC_FreeBuckets(Buckets, NumProcs);
#ifdef USE_MULTIPLE_RECV
  for (j = 0; j < NumRecvs; j++) {
    MPI_Cancel(&inreq[j]);
    MPI_Wait(&inreq[j], &ignoredStatus);
  }
#else
  MPI_Cancel(&inreq);
  MPI_Wait(&inreq, &ignoredStatus);
#endif

  /* end multiprocessor code */
}

static void
Power2NodesMPIRandomAccessUpdate(u64Int logTableSize,
                                 u64Int TableSize,
                                 s64Int LocalTableSize,
                                 u64Int MinLocalTableSize,
                                 u64Int GlobalStartMyProc,
                                 u64Int Top,
                                 int logNumProcs,
                                 int NumProcs,
                                 int Remainder,
                                 int MyProc,
                                 s64Int ProcNumUpdates,
                                 MPI_Datatype INT64_DT)
{
  s64Int i, j;
  int proc_count;

  s64Int SendCnt;
  u64Int Ran;
  s64Int WhichPe;
  u64Int LocalOffset;
  int logLocalTableSize = logTableSize - logNumProcs;
  int NumberReceiving = NumProcs - 1;
#ifdef USE_MULTIPLE_RECV
  int index, NumRecvs;
  MPI_Request inreq[MAX_RECV]  = { MPI_REQUEST_NULL };
  MPI_Request outreq = MPI_REQUEST_NULL;
#else
  MPI_Request inreq, outreq = MPI_REQUEST_NULL;
#endif
  u64Int inmsg;
  int bufferBase;

  MPI_Status finish_statuses[NumProcs];
  MPI_Request finish_req[NumProcs];

  MPI_Status status;
  MPI_Status ignoredStatus; /* Cray X1 doesn't have MPI_STATUS_IGNORE */
  int have_done;

  int pe;
  int pendingUpdates;
  int maxPendingUpdates;
  int localBufferSize;
  int peUpdates;
  int recvUpdates;
  Bucket_Ptr Buckets;

  pendingUpdates = 0;
  maxPendingUpdates = MAX_TOTAL_PENDING_UPDATES;
  localBufferSize = LOCAL_BUFFER_SIZE;
  Buckets = HPCC_InitBuckets(NumProcs, maxPendingUpdates);

  /* Initialize main table */
  for (i=0; i<LocalTableSize; i++)
    HPCC_Table[i] = i + GlobalStartMyProc;

  /* Perform updates to main table.  The scalar equivalent is:
   *
   *     u64Int Ran;
   *     Ran = 1;
   *     for (i=0; i<NUPDATE; i++) {
   *       Ran = (Ran << 1) ^ (((s64Int) Ran < 0) ? POLY : 0);
   *       Table[Ran & (TABSIZE-1)] ^= Ran;
   *     }
   */

  SendCnt = ProcNumUpdates; /*  SendCnt = (4 * LocalTableSize); */
  Ran = HPCC_starts (4 * GlobalStartMyProc);

  pendingUpdates = 0;
  i = 0;

#ifdef USE_MULTIPLE_RECV
  NumRecvs = (NumProcs > 4) ? (Mmin(4,MAX_RECV)) : 1;
  for (j = 0; j < NumRecvs; j++)
    MPI_Irecv(&LocalRecvBuffer[j*LOCAL_BUFFER_SIZE], localBufferSize,
              INT64_DT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD,
              &inreq[j]);
#else
  MPI_Irecv(&LocalRecvBuffer, localBufferSize, INT64_DT,
            MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &inreq);
#endif

  while (i < SendCnt) {

    if (pendingUpdates < maxPendingUpdates) {
      Ran = (Ran << 1) ^ ((s64Int) Ran < ZERO64B ? POLY : ZERO64B);
      WhichPe = (Ran >> logLocalTableSize) & (NumProcs - 1);
      if (WhichPe == MyProc) {
        LocalOffset = (Ran & (TableSize - 1)) - GlobalStartMyProc;
        HPCC_Table[LocalOffset] ^= Ran;
      }
      else {
        HPCC_InsertUpdate(Ran, WhichPe, Buckets);
        pendingUpdates++;
      }
      i++;
    }
    
    else {
      
      MPI_Test(&outreq, &have_done, MPI_STATUS_IGNORE);
      if (have_done) {
        outreq = MPI_REQUEST_NULL;
        pe = HPCC_GetUpdates(Buckets, LocalSendBuffer, localBufferSize, &peUpdates);
        MPI_Isend(&LocalSendBuffer, peUpdates, INT64_DT, (int)pe, UPDATE_TAG,
                  MPI_COMM_WORLD, &outreq);
        pendingUpdates -= peUpdates;
        
        
        do {
#ifdef USE_MULTIPLE_RECV
          MPI_Testany(NumRecvs, inreq, &index, &have_done, &status);
#else
          MPI_Test(&inreq, &have_done, &status);
#endif
          if (have_done) {
            if (status.MPI_TAG == UPDATE_TAG) {
              MPI_Get_count(&status, INT64_DT, &recvUpdates);
#ifdef USE_MULTIPLE_RECV
              bufferBase = index * LOCAL_BUFFER_SIZE;
#else
              bufferBase = 0;
#endif
              for (j=0; j < recvUpdates; j ++) {
                inmsg = LocalRecvBuffer[bufferBase+j];
                HPCC_Table[inmsg & (LocalTableSize-1)] ^= inmsg;
              }
              
            } else if (status.MPI_TAG == FINISHED_TAG) {
              /* we got a done message.  Thanks for playing... */
              NumberReceiving--;
            } else {
              abort();
            }
#ifdef USE_MULTIPLE_RECV
            MPI_Irecv(&LocalRecvBuffer[index*LOCAL_BUFFER_SIZE], localBufferSize,
                      INT64_DT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD,
                      &inreq[index]);
#else
            MPI_Irecv(&LocalRecvBuffer, localBufferSize, INT64_DT,
                      MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &inreq);
#endif
          }
        } while (have_done && NumberReceiving > 0);
      }
    }
  }


  /* send remaining updates in buckets */
  while (pendingUpdates > 0) {

    /* receive messages */
    do {
#ifdef USE_MULTIPLE_RECV
      MPI_Testany(NumRecvs, inreq, &index, &have_done, &status);
#else
      MPI_Test(&inreq, &have_done, &status);
#endif
      if (have_done) {
        if (status.MPI_TAG == UPDATE_TAG) {
          MPI_Get_count(&status, INT64_DT, &recvUpdates);
#ifdef USE_MULTIPLE_RECV
          bufferBase = index * LOCAL_BUFFER_SIZE;
#else
          bufferBase = 0;
#endif
          for (j=0; j < recvUpdates; j ++) {
            inmsg = LocalRecvBuffer[bufferBase+j];
            HPCC_Table[inmsg & (LocalTableSize-1)] ^= inmsg;
          }
        } else if (status.MPI_TAG == FINISHED_TAG) {
          /* we got a done message.  Thanks for playing... */
          NumberReceiving--;
        } else {
          abort();
        }
#ifdef USE_MULTIPLE_RECV
        MPI_Irecv(&LocalRecvBuffer[index*LOCAL_BUFFER_SIZE], localBufferSize,
                  INT64_DT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD,
                  &inreq[index]);
#else
        MPI_Irecv(&LocalRecvBuffer, localBufferSize, INT64_DT,
                  MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &inreq);
#endif
      }
    } while (have_done && NumberReceiving > 0);
 
    MPI_Test(&outreq, &have_done, MPI_STATUS_IGNORE);
    if (have_done) {
      outreq = MPI_REQUEST_NULL;
      pe = HPCC_GetUpdates(Buckets, LocalSendBuffer, localBufferSize, &peUpdates);
      MPI_Isend(&LocalSendBuffer, peUpdates, INT64_DT, (int)pe, UPDATE_TAG,
                MPI_COMM_WORLD, &outreq);
      pendingUpdates -= peUpdates;
    }

  }

  /* send our done messages */
  for (proc_count = 0 ; proc_count < NumProcs ; ++proc_count) {
    if (proc_count == MyProc) { finish_req[MyProc] = MPI_REQUEST_NULL; continue; }
    /* send garbage - who cares, no one will look at it */
    MPI_Isend(&Ran, 1, INT64_DT, proc_count, FINISHED_TAG,
              MPI_COMM_WORLD, finish_req + proc_count);
  }

  /* Finish everyone else up... */
  while (NumberReceiving > 0) {
#ifdef USE_MULTIPLE_RECV
    MPI_Waitany(NumRecvs, inreq, &index, &status);
#else
    MPI_Wait(&inreq, &status);
#endif
    if (status.MPI_TAG == UPDATE_TAG) {
      MPI_Get_count(&status, INT64_DT, &recvUpdates);
#ifdef USE_MULTIPLE_RECV
      bufferBase = index * LOCAL_BUFFER_SIZE;
#else
      bufferBase = 0;
#endif
      for (j=0; j < recvUpdates; j ++) {
        inmsg = LocalRecvBuffer[bufferBase+j];
        HPCC_Table[inmsg & (LocalTableSize-1)] ^= inmsg;
      }

    } else if (status.MPI_TAG == FINISHED_TAG) {
      /* we got a done message.  Thanks for playing... */
      NumberReceiving--;
    } else {
      abort();
    }
#ifdef USE_MULTIPLE_RECV
    MPI_Irecv(&LocalRecvBuffer[index*LOCAL_BUFFER_SIZE], localBufferSize,
              INT64_DT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD,
              &inreq[index]);
#else
    MPI_Irecv(&LocalRecvBuffer, localBufferSize, INT64_DT,
              MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &inreq);
#endif
  }

  MPI_Waitall( NumProcs, finish_req, finish_statuses);

  /* Be nice and clean up after ourselves */
  HPCC_FreeBuckets(Buckets, NumProcs);
#ifdef USE_MULTIPLE_RECV
  for (j = 0; j < NumRecvs; j++) {
    MPI_Cancel(&inreq[j]);
    MPI_Wait(&inreq[j], &ignoredStatus);
  }
#else
  MPI_Cancel(&inreq);
  MPI_Wait(&inreq, &ignoredStatus);
#endif

  /* end multiprocessor code */
}

int
HPCC_MPIRandomAccess(HPCC_Params *params) {
  s64Int i;
  s64Int NumErrors, GlbNumErrors;

  int NumProcs, logNumProcs, MyProc;
  u64Int GlobalStartMyProc;
  int Remainder;            /* Number of processors with (LocalTableSize + 1) entries */
  u64Int Top;               /* Number of table entries in top of Table */
  u64Int LocalTableSize;    /* Local table width */
  u64Int MinLocalTableSize; /* Integer ratio TableSize/NumProcs */
  u64Int logTableSize, TableSize;

  double CPUTime;               /* CPU  time to update table */
  double RealTime;              /* Real time to update table */

  double TotalMem;
  int sAbort, rAbort;
  int PowerofTwo;

  double timeBound;  /* OPTIONAL time bound for execution time */
  u64Int NumUpdates_Default; /* Number of updates to table (suggested: 4x number of table entries) */
  u64Int NumUpdates;  /* actual number of updates to table - may be smaller than
                       * NumUpdates_Default due to execution time bounds */
  s64Int ProcNumUpdates; /* number of updates per processor */
  s64Int GlbNumUpdates;  /* for reduction */

  FILE *outFile = NULL;
  MPI_Op sum64;
  double *GUPs;

  MPI_Datatype INT64_DT;

#ifdef LONG_IS_64BITS
  INT64_DT = MPI_LONG;
#else
  INT64_DT = MPI_LONG_LONG_INT;
#endif

  GUPs = &params->MPIGUPs;

  MPI_Comm_size( MPI_COMM_WORLD, &NumProcs );
  MPI_Comm_rank( MPI_COMM_WORLD, &MyProc );

  if (0 == MyProc) {
    outFile = fopen( params->outFname, "a" );
    if (! outFile) outFile = stderr;
  }

  TotalMem = params->HPLMaxProcMem; /* max single node memory */
  TotalMem *= NumProcs;             /* max memory in NumProcs nodes */
  TotalMem /= sizeof(u64Int);

  /* calculate TableSize --- the size of update array (must be a power of 2) */
  for (TotalMem *= 0.5, logTableSize = 0, TableSize = 1;
       TotalMem >= 1.0;
       TotalMem *= 0.5, logTableSize++, TableSize <<= 1)
    ; /* EMPTY */


  /* determine whether the number of processors is a power of 2 */
  for (i = 1, logNumProcs = 0; ; logNumProcs++, i <<= 1) {
    if (i == NumProcs) {
      PowerofTwo = HPCC_TRUE;
      Remainder = 0;
      Top = 0;
      MinLocalTableSize = (TableSize / NumProcs);
      LocalTableSize = MinLocalTableSize;
      GlobalStartMyProc = (MinLocalTableSize * MyProc);
      break;

    /* number of processes is not a power 2 (too many shifts may introduce negative values or 0) */

    }
    else if (i > NumProcs || i <= 0) {
      PowerofTwo = HPCC_FALSE;
      /* Minimum local table size --- some processors have an additional entry */
      MinLocalTableSize = (TableSize / NumProcs);
      /* Number of processors with (LocalTableSize + 1) entries */
      Remainder = TableSize  - (MinLocalTableSize * NumProcs);
      /* Number of table entries in top of Table */
      Top = (MinLocalTableSize + 1) * Remainder;
      /* Local table size */
      if (MyProc < Remainder) {
          LocalTableSize = (MinLocalTableSize + 1);
          GlobalStartMyProc = ( (MinLocalTableSize + 1) * MyProc);
        }
        else {
          LocalTableSize = MinLocalTableSize;
          GlobalStartMyProc = ( (MinLocalTableSize * MyProc) + Remainder );
        }
      break;

    } /* end else if */
  } /* end for i */


  HPCC_Table = XMALLOC( u64Int, LocalTableSize);
  sAbort = 0; if (! HPCC_Table) sAbort = 1;

  MPI_Allreduce( &sAbort, &rAbort, 1, MPI_INT, MPI_SUM, MPI_COMM_WORLD );
  if (rAbort > 0) {
    if (MyProc == 0) fprintf(outFile, "Failed to allocate memory for the main table.\n");
    goto failed_table;
  }

  params->MPIRandomAccess_N = (s64Int)TableSize;

  /* Default number of global updates to table: 4x number of table entries */
  NumUpdates_Default = 4 * TableSize;

#ifdef RA_TIME_BOUND
  /* estimate number of updates such that execution time does not exceed time bound */
  /* time_bound should be a parameter */
  /* max run time in seconds */
  timeBound = Mmax( 0.25 * params->HPLrdata.time, (double)TIME_BOUND );
  if (PowerofTwo) {
    HPCC_Power2NodesTime(logTableSize, TableSize, LocalTableSize,
                    MinLocalTableSize, GlobalStartMyProc, Top,
                    logNumProcs, NumProcs, Remainder,
                    MyProc, INT64_DT, timeBound, (u64Int *)&ProcNumUpdates);

  } else {
    HPCC_AnyNodesTime(logTableSize, TableSize, LocalTableSize,
                 MinLocalTableSize, GlobalStartMyProc, Top,
                 logNumProcs, NumProcs, Remainder,
                 MyProc, INT64_DT, timeBound, (u64Int *)&ProcNumUpdates);
  }
  /* be conservative: get the smallest number of updates among all procs */
  MPI_Reduce( &ProcNumUpdates, &GlbNumUpdates, 1, INT64_DT,
              MPI_MIN, 0, MPI_COMM_WORLD );
  /* distribute number of updates per proc to all procs */
  MPI_Bcast( &GlbNumUpdates, 1, INT64_DT, 0, MPI_COMM_WORLD );
  ProcNumUpdates = Mmin(GlbNumUpdates, (4*LocalTableSize));
  /* works for both PowerofTwo and AnyNodes */
  NumUpdates = Mmin((ProcNumUpdates*NumProcs), NumUpdates_Default);

#else
  ProcNumUpdates = 4*LocalTableSize;
  NumUpdates = NumUpdates_Default;
#endif

  if (MyProc == 0) {
    fprintf( outFile, "Running on %d processors%s\n", NumProcs, PowerofTwo ? " (PowerofTwo)" : "");
    fprintf( outFile, "Total Main table size = 2^" FSTR64 " = " FSTR64 " words\n",
             logTableSize, TableSize );
    if (PowerofTwo)
        fprintf( outFile, "PE Main table size = 2^" FSTR64 " = " FSTR64 " words/PE\n",
                 (logTableSize - logNumProcs), TableSize/NumProcs );
      else
        fprintf( outFile, "PE Main table size = (2^" FSTR64 ")/%d  = " FSTR64 " words/PE MAX\n",
                 logTableSize, NumProcs, LocalTableSize);

    fprintf( outFile, "Default number of updates (RECOMMENDED) = " FSTR64 "\n", NumUpdates_Default);
#ifdef RA_TIME_BOUND
    fprintf( outFile, "Number of updates EXECUTED = " FSTR64 " (for a TIME BOUND of %.2f secs)\n",
             NumUpdates, timeBound);
#endif
    params->MPIRandomAccess_ExeUpdates = NumUpdates;
    params->MPIRandomAccess_TimeBound = timeBound;
  }

  MPI_Barrier( MPI_COMM_WORLD );

  CPUTime = -CPUSEC();
  RealTime = -RTSEC();

  if (PowerofTwo) {
    Power2NodesMPIRandomAccessUpdate(logTableSize, TableSize, LocalTableSize,
                                     MinLocalTableSize, GlobalStartMyProc, Top,
                                     logNumProcs, NumProcs, Remainder,
                                     MyProc, ProcNumUpdates, INT64_DT);
  } else {
    AnyNodesMPIRandomAccessUpdate(logTableSize, TableSize, LocalTableSize,
                                  MinLocalTableSize, GlobalStartMyProc, Top,
                                  logNumProcs, NumProcs, Remainder,
                                  MyProc, ProcNumUpdates, INT64_DT);
  }


  MPI_Barrier( MPI_COMM_WORLD );

  /* End timed section */
  CPUTime += CPUSEC();
  RealTime += RTSEC();

  /* Print timing results */
  if (MyProc == 0){
    params->MPIRandomAccess_time = RealTime;
    *GUPs = 1e-9*NumUpdates / RealTime;
    fprintf( outFile, "CPU time used = %.6f seconds\n", CPUTime );
    fprintf( outFile, "Real time used = %.6f seconds\n", RealTime );
    fprintf( outFile, "%.9f Billion(10^9) Updates    per second [GUP/s]\n",
             *GUPs );
    fprintf( outFile, "%.9f Billion(10^9) Updates/PE per second [GUP/s]\n",
             *GUPs / NumProcs );
    /* No longer reporting per CPU number */
    /* *GUPs /= NumProcs; */
    
    fprintf (outFile, "GUPS-Procs: %d\n", NumProcs);
    fprintf (outFile, "GUPS: %.9f\n", *GUPs);
    fprintf (outFile, "GUPS-Time: %.6f\n", RealTime);
  }
  /* distribute result to all nodes */
  MPI_Bcast( GUPs, 1, MPI_INT, 0, MPI_COMM_WORLD );


  /* Verification phase */

  /* Begin timing here */
  CPUTime = -CPUSEC();
  RealTime = -RTSEC();

  if (PowerofTwo) {
    HPCC_Power2NodesMPIRandomAccessCheck(logTableSize, TableSize, LocalTableSize,
                                    GlobalStartMyProc,
                                    logNumProcs, NumProcs,
                                    MyProc, ProcNumUpdates,
                                    INT64_DT, &NumErrors);
  }
  else {
    HPCC_AnyNodesMPIRandomAccessCheck(logTableSize, TableSize, LocalTableSize,
                                 MinLocalTableSize, GlobalStartMyProc, Top,
                                 logNumProcs, NumProcs, Remainder,
                                 MyProc, ProcNumUpdates,
                                 INT64_DT, &NumErrors);
  }


#ifdef LONG_IS_64BITS
  MPI_Reduce( &NumErrors, &GlbNumErrors, 1, MPI_LONG, MPI_SUM, 0, MPI_COMM_WORLD );
#else
  /* MPI 1.1 standard (obsolete at this point) doesn't define MPI_SUM
    to work on `long long':
    http://www.mpi-forum.org/docs/mpi-11-html/node78.html and
    therefore LAM 6.5.6 chooses not to implement it (even though there
    is code for it in LAM and for other reductions work OK,
    e.g. MPI_MAX). MPICH 1.2.5 doesn't complain about MPI_SUM but it
    doesn't have MPI_UNSIGNED_LONG_LONG (but has MPI_LONG_LONG_INT):
    http://www.mpi-forum.org/docs/mpi-20-html/node84.htm So I need to
    create a trivial summation operation. */
  MPI_Op_create( Sum64, 1, &sum64 );
  MPI_Reduce( &NumErrors, &GlbNumErrors, 1, INT64_DT, sum64, 0, MPI_COMM_WORLD );
  MPI_Op_free( &sum64 );
#endif

  /* End timed section */
  CPUTime += CPUSEC();
  RealTime += RTSEC();

  if(MyProc == 0){
    params->MPIRandomAccess_CheckTime = RealTime;
    fprintf( outFile, "Verification:  CPU time used = %.6f seconds\n", CPUTime);
    fprintf( outFile, "Verification:  Real time used = %.6f seconds\n", RealTime);
    fprintf( outFile, "Found " FSTR64 " errors in " FSTR64 " locations (%s).\n",
             GlbNumErrors, TableSize, (GlbNumErrors <= 0.01*TableSize) ?
             "passed" : "failed");
    if (GlbNumErrors > 0.01*TableSize) params->Failure = 1;
    params->MPIRandomAccess_Errors = (s64Int)GlbNumErrors;
    params->MPIRandomAccess_ErrorsFraction = (double)GlbNumErrors / (double)TableSize;
  }
  /* End verification phase */


  /* Deallocate memory (in reverse order of allocation which should
     help fragmentation) */

  free( HPCC_Table );

  failed_table:

  if (0 == MyProc) if (outFile != stderr) fclose( outFile );

  MPI_Barrier( MPI_COMM_WORLD );

  return 0;
}
