/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * GUPS - RandomAccess_int benchmark.
 *
 * version using "int" instead of ranNum
 * This is not a meaningful program since the original
 * algorithm was for 64 bit long integers, it is just
 * for staging the testing.
 *
 * @author kemal
 * @author vj approx 7/2004
 * New version, 11/2004
 */
public class RandomAccess_int extends x10Test {
	// Set places.MAX_PLACES to 128 to match original
	// Set LOG2_TABLE_SIZE to 25 to match original

	private  static final int MAX_PLACES = place.MAX_PLACES;
	private  static final int LOG2_TABLE_SIZE = 5;
	private  static final int LOG2_SMALL_TABLE_SIZE = 4;
	private  static final int TABLE_SIZE = (1 << LOG2_TABLE_SIZE);
	private  static final int SMALL_TABLE_SIZE = (1 << LOG2_SMALL_TABLE_SIZE);
	private  static final int N_UPDATES = (4*TABLE_SIZE);
	private  static final int N_UPDATES_PER_PLACE = (N_UPDATES/MAX_PLACES);
	private  static final int WORD_SIZE = 64;
	private  static final int POLY = 7;
	private  static final int SMALL_TABLE_INIT = 123456789; // (int)0x0123456789abcdefL;
	// expected result with LOG2_SMALL_TABLE_SIZE = 10, MAX_PLACES = 4
	private static final int EXPECTED_RESULT = -1068434730;

	/**
	 * Get the value as a table index.
	 */
	private static int f(int val) {
		return (int) (val & (TABLE_SIZE-1));
	}

	/**
	 * Get the value as an index into the small table.
	 */
	private static int g(int val) {
		return (int)(val >>>
				(WORD_SIZE-LOG2_SMALL_TABLE_SIZE)); }

	/**
	 * Return the next number following this one.
	 * Actually the next item in the sequence generated
	 * by a primitive polynomial over GF(2).)
	 */
	private static int nextRandom(int val) {
		return
			((val<<1) ^ (val < 0 ? POLY : 0)); }

	/**
	 * Return a new int exclusively-oring this value with k.
	 */
	private static safe int update(int val, int k) {
		return val^k;
	}

	/*
	 * Utility routines for common functions on integers and points
	 * Should be placed in a library class
	 */

	/**
	 * Convert an integer to a 1D point
	 */
	private static point Pt(int i) {
		return point.factory.point(new int[] { i });
	}

	/*
	 * Utility routines to create simple common dists
	 * should be placed in a library class
	 */

	/**
	 * create a simple 1D blocked dist
	 */
	private static dist block (int arraySize) {
		final region r = region.factory.region(0, arraySize-1);
		return dist.factory.block(r);
	}

	/**
	 * create a unique dist (mapping each i to place i)
	 */
	private static dist unique () {
		return dist.factory.unique(place.places);
	}

	/**
	 * main GUPS routine
	 */
	public boolean run() {
		// distributed histogram table
		final dist d = block(TABLE_SIZE);
		final int[.] table = new int[d];
		finish ateach (point p: d) { table[p] = p[0]; }

		// random number starting seeds for each place
		final dist d2 = unique();
		final int[.] ranStarts = new int[d2];
		finish ateach (point p: d2) { ranStarts[p] = C.starts(N_UPDATES_PER_PLACE*p[0]); }

		// A small value table that will be copied to all processors
		// Used in generating the update value
		final dist d3 = [0: (SMALL_TABLE_SIZE-1)]->here;
		final int value[.] smallTable = new int value[d3]
			(point p) { return p[0]*SMALL_TABLE_INIT; };
		// for (point p: d3) { smallTable[p] = i0(p)*SMALL_TABLE_INIT; }

		// In all places in parallel,
		// repeatedly generate random table indices
		// and do remote atomic updates on corresponding table elements
		finish
			ateach (point p : d2) {
				int ran = nextRandom(ranStarts[p]);
				for (int count = 1; count <= N_UPDATES_PER_PLACE; count++) {
					final int j = f(ran);
					final int k = smallTable[g(ran)];
					final point q = Pt(j);
					async(table.distribution[q]) { atomic { table[q] = update(table[q], k); } }
					ran = nextRandom(ran);
				}
			}

		int sum = table.reduce(intArray.add, 0);
		System.out.println(sum);
		return sum == EXPECTED_RESULT;
	}

	public static void main(String[] args) {
		new RandomAccess_int().execute();
	}

	/**
	 * C routines to be linked with X10, written in X10 for now
	 */
	static class C {
		// self contained constants for C routines

		private static final int POLY = 7; //(int)0x0000000000000007L;
		private static final int PERIOD = 13176245;      // (int)1317624576693539401L;

		private static int nextRandom(int temp) {
			return (temp << 1) ^ (temp < 0 ? POLY : 0);
		}

		private static boolean getBit(int n, int i) {
			return ((n>>>i)&1) != 0;
		}

		/**
		 * Utility routine to start random number generator at Nth step
		 * (original "starts" routine from RandomAccess).
		 * <code>
		 Functional synopsis:
		 long starts(long n) : =
		 long n1 = for (long t = n; t < 0 return t; next t = t+PERIOD) { };
		 long n2 = for (long t = n1; t > PERIOD return t; next t = t-PERIOD) { };
		 if (n2 == 0) return 0x1;
		 long m2[] = new long[0..63](i) { i == 0 ? 1 : (nextRandom**2)(m2[i-1]); }
		 int lastSetBit = findFirstSatisfying(int j: 62..0)(getBit(n2, j));
		 mutable long ran = 2;
		 for (int i = lastSetBit..1) {
			 long ranXor = Xor(int j: 0..63 where getBit(ran, j))(m2[j]);
			 ran = getBit(n2, i-1) ? nextRandom(ranXor) : ranXor; }
		 return ran;
		 * </code>
		 */
		public static int starts(int n) {
			int i, j;
			int[] m2 = new int[64];
			int temp, ran;

			while (n < 0) n += PERIOD;
			while (n > PERIOD) n -= PERIOD;
			if (n == 0) return 1;

			temp = 1;
			for (i = 0; i < 64; i++) {
				m2[i] = temp;
				temp = nextRandom(temp);
				temp = nextRandom(temp);
			}

			for (i = 62; i >= 0; i--)
				if (getBit(n, i))
					break;

			ran = 2;
			while (i > 0) {
				temp = 0;
				for (j = 0; j < 64; j++)
					if (getBit(ran, j))
						temp ^= m2[j];
				ran = temp;
				i -= 1;
				if (getBit(n, i))
					ran = nextRandom(ran);
			}

			return ran;
		}
	} // end C
}

