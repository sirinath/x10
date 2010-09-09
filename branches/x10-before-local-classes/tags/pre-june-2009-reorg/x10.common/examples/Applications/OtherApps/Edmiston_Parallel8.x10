/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * A parallel version of Edmiston's algorithm for sequence alignment, written in X10.
 *
 * This code is an X10 port of the Edmiston_Sequential.c program written by
 * Sirisha Muppavarapu (sirisham@cs.unm.edu), U. New Mexico.
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 * @author Kemal Ebcioglu (kemal@us.ibm.com)
 *
 * This version splits work across columns of the (N+1)*(M+1)
 * matrix (where N<M), taking advantage of the domain
 * knowledge that only
 * a pre-computation of the preceding 1.5*N columns are
 * necessary, for "warming up" the computation for the current
 * block.
 *
 * Only SPMD parallelism exploited in this version.
 */
public class Edmiston_Parallel8 extends x10Test {
	public boolean run() {
		final int N = 10; //#rows
		final int M = 100; //#columns
		final int EXPECTED_CHECKSUM = -402;
		Random r = new Random(1);
		RandCharStr c1 = new RandCharStr(r, N);
		RandCharStr c2 = new RandCharStr(r, M);
		// Create an output matrix for inputs c1 and c2
		EditDistMatrix m = new EditDistMatrix(c1, c2);
		// Print the matrix
		m.printMatrix();
		m.printMetrics();
		m.verify(EXPECTED_CHECKSUM);
		return true;
	}

	public static void main(String[] args) {
		new Edmiston_Parallel8().execute();
	}

	/**
	 * Class with data structures and operations for an edit distance matrix.
	 */
	static class EditDistMatrix {
		const int iGapPen  =  2;
		const int iMatch  =  -1 ;
		const int iMisMatch =  1;

		public final int[.] e; // the edit distance matrix
		final RandCharStr c1; // input string 1
		final RandCharStr c2; // input string 2
		final int N; // matrix dimensions
		final int M;

		/**
		 * Constructor method: create the edit distance matrix using Edmiston's algorithm,
		 * from the input strings cSeq1 and cSeq2.
		 */
		public EditDistMatrix(RandCharStr cSeq1, RandCharStr cSeq2) {
			c1 = cSeq1;
			c2 = cSeq2;
			N = c1.s.length-1;
			M = c2.s.length-1;

			final dist(:rank==2) D =  (dist(:rank==2)) starBlock((region(:rank==1)) [0:N], (region(:rank==1))[0:M]);

			e = new int[D];

			// compute required number of overlap columns
			// with preceding block
			final int overlap = ceilFrac(N*(-iMatch), iGapPen)+N;

			// SPMD computation at each place
			finish ateach (point [p]: dist.factory.unique(D.places())) {
				// get sub-distribution for this place
				final dist(:rank==2) myD = D|here;
				// extend it on the left with extra overlap columns
				int begin = myD.region.rank(1).low();
				int lb = Math.max(0, begin-overlap);
				int ub = Math.max(-1, begin-1);
				final dist myExtendedD = myD||([0:N,lb:ub]->here);

				// Create a local array including overlap columns
				final int [.] B = new int[myExtendedD];

				// Do sequence alignment on local array including the
				// overlap columns

				final dist myExtendedD_inner = getInnerDist(myExtendedD);
				for (point [i,j]: myExtendedD_inner) {
					B[i,j] = min4(0, B[i-1,j]+iGapPen, B[i,j-1]+iGapPen,
							B[i-1,j-1] + (c1.s[i] == c2.s[j] ? iMatch : iMisMatch));
				}

				// now copy the non-overlap columns to the answer array.
				for (point [i,j]: myD) e[i,j] = B[i,j];
			}
		}

		/**
		 * Return a (*,block) distribution for
		 * the region (r1*r2)
		 */
		static dist(:rank==2) starBlock(region(:rank==1) r1, region(:rank==1) r2) {
			dist column2Place = dist.factory.block(r2);
			dist(:rank==2) d = [0:-1,0:-1]->here;
			for (point [j]: r2) d = d|| ([r1,j:j]->column2Place[j]);
			return d;
		}

		/**
		 * Return the distribution D with row 0 and column 0 removed
		 */
		static dist getInnerDist(dist D) {
			int lb0 = D.region.rank(0).low();
			int ub0 = D.region.rank(0).high();
			int lb1 = D.region.rank(1).low();
			int ub1 = D.region.rank(1).high();
			return D|[(lb0+1):ub0,(lb1+1):ub1];
		}

		/**
		 * Return element i,j of matrix.
		 * This could possibly be a remote read.
		 */
		int rd(final int i, final int j) {
			return future(e.distribution[i,j]) { e[i,j] }.force();
		}

		/**
		 * Print the Edit Distance Matrix.
		 */
		public void printMatrix()
		{
			System.out.println("Minimum Matrix EditDistance is: " + rd(N, M));
			System.out.println("Matrix EditDistance is:");
			System.out.print(pad(' '));
			for (point [j]: [0:M]) System.out.print(pad(c2.s[j]));
			System.out.println();

			for (point [i]: [0:N]) {
				System.out.print(pad(c1.s[i]));
				for (point [j]: [0:M]) System.out.print(pad(rd(i, j)));
				System.out.println();
			}
		}

		void printMetrics() {
			System.out.println("**** START OF ABSTRACT PERFORMANCE METRICS ****");
			System.out.println("e.distribution.distributionEfficiency() = " + e.distribution.distributionEfficiency());
			System.out.println("N = " + N);
			System.out.println("M = " + M);
			System.out.println("nRows = " + e.region.rank(0).size());
			System.out.println("nCols = " + e.region.rank(1).size());
			final int P = place.MAX_PLACES;
			System.out.println("P = " + P);
			final int T = min4Count.sum();
			System.out.println("T = " + T);
			final int X = min4Count.max();
			System.out.println("X = " + X);
			final int Tpar = Math.max(ceilFrac(T, P), X);
			System.out.println("Tpar = " + Tpar);
			float S = (float) M*N / (float) Tpar;
			System.out.println("S = " + S);
			System.out.println();
			System.out.println("NOTES: 1) These metrics are only valid if your program satisfies the X10 Locality Rule");
			System.out.println("       2) It is recommended that you use -DUMP_STATS_ON_EXIT = true option with metrics");
			System.out.println("       3) In the current performance model, Tpar will always be the same as X.");
			System.out.println("          In a more sophisticated model, X will also include the effect of intra-place activities.");
			System.out.println("**** END OF ABSTRACT PERFORMANCE METRICS ***");
		}

		/**
		 * Verify that the sum of e is equal to the expected value
		 */
		public void verify(int expectedCheckSum) {
			chk(e.sum() == expectedCheckSum);
		}

		/*
		 * Utility methods.
		 */

		/**
		 * Return ceil(n/m) for positive integers n and m
		 */
		static int ceilFrac(int n, int m) {
			return (n+m-1)/m;
		}

		final int[.] min4Count = new int[dist.factory.unique()]; // Array to support counting of MIN4 operations.

		/**
		 * returns the minimum of 4 integers.
		 */
		int min4(int w, int x, int y, int z) {
			final place myP = here;
			finish async(myP) atomic min4Count[(myP).id] += 1;
			return Math.min(Math.min(w, x), Math.min(y, z));
		}

		/**
		 * pad() methods for different data types
		 * Output string = input value converted to a string of length >= 3, with a blank inserted at the start and end.
		 */
		static String pad(int x) { return pad(x + ""); }
		static String pad(char x) { return pad(x + ""); }
		static String pad(String s) {
			final int n = 3;
			while (s.length() < n) s = " "+s;
			return " "+s+" ";
		}
	}

	/**
	 * Common random number generator class.
	 */
	static class Random {

		int randomSeed;

		/**
		 * Create a new random number generator with seed x
		 */
		public Random(int x) {
			randomSeed = x;
		}

		/**
		 * Returns the next random number between 0 and 128,
		 * according to this random number generator's sequence.
		 */
		public int nextAsciiNumber() {
			randomSeed  = (randomSeed * 1103515245 +12345);
			return (int)(unsigned(randomSeed / 65536) % 128L);
		}

		/**
		 * Convert an int to an unsigned int (C-style).
		 */
		static long unsigned(int x) {
			return ((long)x & 0x00000000ffffffffL);
		}
	}

	/**
	 * A class pertaining to random character arrays (strings).
	 */
	static value class RandCharStr {
		public final char[] s; // the string (character array).

		/**
		 * Create a random character array of
		 * length len from the alphabet A,C,G,T,
		 * using the random number generator r.
		 * The array begins with an extra '-',
		 * thus it will have len+1 characters.
		 */
		public RandCharStr (Random r, int len) {
			s = new char[len+1];
			s[0] =  '-';
			int i = 1;
			while (i <= len) {
				int x = r.nextAsciiNumber();
				switch (x) {
					case 65: s[i++] = 'A';  break;
					case 67: s[i++] = 'C';  break;
					case 71: s[i++] = 'G';  break;
					case 84: s[i++] = 'T';  break;
					default:
				}
			}
		}
	}
}

