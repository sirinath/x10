/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import java.util.Random;
import harness.x10Test;

/**
 * Parallel version of Edmiston's algorithm for Sequence Alignment.
 * This code is an X10 port of the Edmiston_Sequential.c program written by
 * Sirisha Muppavarapu (sirisham@cs.unm.edu), U. New Mexico.
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 * @author Kemal Ebcioglu (kemal@us.ibm.com)
 *
 * This version performs SPMD computations.
 */
public class Edmiston_Parallel5 extends x10Test {
	const int N = 10;
	const int M = 10;
	const int EXPECTED_CHECKSUM = 549;

	/**
	 * main run method
	 */
	public boolean run() {
		// create two random character strings
		charStr c1 = new charStr(N, 0);
		charStr c2 = new charStr(M, N);
		//create and compute edit distance matrix
		editDistMatrix m = new editDistMatrix(c1, c2);
		//print matrix
		m.pr("Edit distance matrix:");
		//verify actual result against expected result
		m.verify(EXPECTED_CHECKSUM);
		return true;
	}

	public static void main(String[] args) {
		new Edmiston_Parallel5().execute();
	}

	/**
	 * Operations and distributed data structures related to
	 * an edit distance matrix
	 */
	static class editDistMatrix {
		const int gapPen = 2;
		const int match = 0;
		const int misMatch = -1;
		const dist P = dist.factory.unique();

		final int[.] e; // the edit distance matrix
		final charStr c1;
		final charStr c2;
		final int N;
		final int M;
		final int blockSize;
		final int blocksPerPlace;
		final dist D;
		final dist Dinner;
		final dist Dboundary;

		/**
		 * Create edit distance matrix with Edmiston's algorithm
		 */
		public editDistMatrix(charStr cSeq1, charStr cSeq2) {
			c1 = cSeq1;
			c2 = cSeq2;
			N = c1.s.region.high();
			M = c2.s.region.high();
			// make a (block,*) distribution from region [0:N,0:M]
			// Each place will be assigned a set of contiguous rows
			blockStarResult t = makeBlockStarDist(N, M);
			blockSize = t.nRowsPerPlace;
			D = t.dist;
			//A block is a blockSize**2 submatrix.
			//Number of blocks in each place, last block may be partial
			blocksPerPlace = (M+blockSize)/blockSize;
			// Distribution of matrix not including row or column 0.
			Dinner = D|[1:N,1:M];
			// Distribution of row 0 and column 0.
			Dboundary = (dist(:rank==2)) D- (dist(:rank==2))Dinner;

			// create initial matrix.
			e = new int[D];

			// Now compute the edit distance matrix.
			// This is done with a wavefront computation, where in each step
			// each place computes a blockSize**2 block (submatrix).
			// I.e. in step 0, block (0,0) will be computed in place 0.
			// In step 1, blocks (0,1),(1,0) will be computed in places 0 and 1.
			// In step 2, blocks (0,2),(1,1),(2,0) will be computed
			// in places 0, 1 and 2, and so on.
			// After computation of this wave,
			// lockstep synchronization
			// occurs using a clock, so that the next wave can
			// correctly consume the results of this wave.
			finish async {
				final clock c = clock.factory.clock();
				//SPMD computation in each place i
				ateach (point [i]: P) clocked(c) {
					// Initialize boundary of e's section for this place i.
					// Boundary of the entire e in all places is set to:
					// 0     1*gapPen     2*gapPen     3*gapPen ...
					// 1*gapPen ...
					// 2*gapPen ...
					// 3*gapPen ...
					//  ...
					for (point [x,y]: getBoundaryDist(i)) e[x,y] = (x+y)*gapPen;
					// wait for my wave by executing i next's
					for (point [k]: [1:i]) next;
					// Now do the real Edmiston computation for each
					// block my place from left to right, where
					// each block is computed using the blocks on its west, north
					// and northwest. Barrier synchronization occurs
					// after each block computation (wave).
					for (point [j]: [0:blocksPerPlace-1]) {
						for (point [x,y]: getBlockDist(i, j))
							e[x,y] = min(rd(x-1, y)+gapPen,
									rd(x, y-1)+gapPen,
									rd(x-1, y-1)
									+(c1.s[x] == c2.s[y] ? match : misMatch));
						next;
					}
					c.drop(); //immediately de-register with clock, so future
					//waves will not need to wait for me
				}
			}
		}

		/**
		 * Result of makeBlockStarDist. Emulating multiple return values.
		 */
		static class blockStarResult {
			int nRowsPerPlace;
			dist dist;
			blockStarResult(int nRowsPerPlace, dist D) {
				this.nRowsPerPlace = nRowsPerPlace;
				this.dist = D;
			}
		}

		const dist(:rank==2) emptyDist = [0:-1,0:-1]->here;

		/**
		 * Create a [0:N,0:M] (block,*) distribution.
		 * Returns
		 * nRowsPerPlace = number of rows per place in the (block,*) distribution.
		 * dist = the (block,*) distribution
		 */
		blockStarResult makeBlockStarDist(int N, int M) {
			//nRowsPerPlace = number of rows per place in (block,*) distribution.
			//Last place may have less rows than others
			int nRowsPerPlace = (N+place.MAX_PLACES)/place.MAX_PLACES;
			// Emulate the (block,*) distribution
			dist(:rank==2) d1 = emptyDist;

			for (point [i]: P)
				d1 = d1||
					([(nRowsPerPlace*i):min(nRowsPerPlace*i+nRowsPerPlace-1, N),
					 0:M]
					 ->P[i]);

			return new blockStarResult(nRowsPerPlace, d1);
		}

		/**
		 * return the distribution/region for block for place i, number j.
		 */
		dist getBlockDist(int i, int j) {
			return (Dinner|[0:N,(blockSize*j):min(blockSize*j+blockSize-1, M)])|P[i];
		}

		/**
		 *Return the boundary nodes in the entire place
		 */
		dist getBoundaryDist(int i) {
			return Dboundary|P[i];
		}

		/**
		 * Find the sum of the elements of the edit distance matrix
		 */
		int checkSum() {
			int sum = 0;
			for (point [i,j]: e) sum += rd(i, j);
			return sum;
		}

		/**
		 * Verify that the edit distance matrix has the expected
		 * checksum.
		 */
		public void verify(int expectedChecksum) {
			if (checkSum() != expectedChecksum) throw new Error();
		}

		/**
		 * Print the Edit Distance Matrix
		 */
		public void pr(final String s)
		{
			final int K = 4; // padding amount

			System.out.println(s);

			System.out.print(" "+pad(' ', K));
			for (point [j]: [0:M]) System.out.print(" "+pad(c2.s[j], K));
			System.out.println();

			for (point [i]: [0:N]) {
				System.out.print(" "+pad(c1.s[i], K));
				for (point [j]: [0:M]) System.out.print(" "+pad(rd(i, j), K));
				System.out.println();
			}
		}

		/*
		 * Utility methods.
		 */

		/**
		 * possibly remote read of e[i,j]
		 */
		int rd(final int i, final int j) {
			return future(e.distribution[i,j]) { e[i,j] }.force();
		}

		/**
		 * returns the minimum of x y and z.
		 */
		static int min(int x, int y, int z) {
			int t = (x < y) ? x : y;
			return (t < z) ? t : z;
		}

		/**
		 * returns the minimum of x and y.
		 */
		static int min(int x, int y) {
			return (x < y) ? x : y;
		}

		/**
		 * right justify an integer in a field of n blanks
		 */
		static String pad(int x, int n) {
			String s = ""+x;
			while (s.length() < n) s = " "+s;
			return s;
		}

		/**
		 * right justify a character in a field of n blanks
		 */
		static String pad(char x, int n) {
			String s = ""+x;
			while (s.length() < n) s = " "+s;
			return s;
		}
	}

	/**
	 * A random character array consisting of the letters ACTG
	 * and beginning with -
	 */
	static value class charStr {
		final char value[.] s;
		const char[] aminoAcids = { 'A', 'C', 'G', 'T' };
		public charStr(final int siz, final int randomStart) {
			s = new char value[[0:siz]->here]
				(point [i]) { return i == 0 ? '-' : randomChar(randomStart+i); };
		}

		/**
		 * Function to generate the i'th random character
		 */
		private static char randomChar(int i) {
			// Randomly select one of 'A', 'C', 'G', 'T'
			int n = 0;
			final Random  rand = new Random(1L);
			// find i'th random number.
			// TODO: need to pre-compute random numbers and re-use
			for (point [k]: [1:i]) n = nextChoice(rand);
			return aminoAcids[n];
		}

		/**
		 * Helper function for random number generation
		 */
		private static int nextChoice(Random rand) {
			int k1 = rand.nextBoolean() ? 0 : 1;
			int k2 = rand.nextBoolean() ? 0 : 1;
			return k1*2+k2;
		}
	}
}

