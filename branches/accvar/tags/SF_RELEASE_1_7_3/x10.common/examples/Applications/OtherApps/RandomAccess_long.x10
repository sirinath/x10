/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * RandomAccess benchmark.
 *
 * Based on HPCC 0.5beta
 *
 * @author kemal
 * @author vj approx 7/2004
 * New version, 11/2004
 */
public class RandomAccess_long extends x10Test {
	// Set places.MAX_PLACES to 128 to match original
	// Set LOG2_TABLE_SIZE to 25 to match original

	const int  MAX_PLACES = x10.lang.place.MAX_PLACES;
	const int  LOG2_TABLE_SIZE = 5;
	const int  LOG2_S_TABLE_SIZE = 4;
	const int  TABLE_SIZE = (1 << LOG2_TABLE_SIZE);
	const int  S_TABLE_SIZE = (1 << LOG2_S_TABLE_SIZE);
	const int  N_UPDATES = (4*TABLE_SIZE);
	const int  N_UPDATES_PER_PLACE = (N_UPDATES/MAX_PLACES);
	const int  WORD_SIZE = 64;
	const long POLY = 7;
	const long S_TABLE_INIT = 0x0123456789abcdefL;
	// expected result with LOG2_S_TABLE_SIZE = 5, MAX_PLACES = 2
	// LOG2_S_TABLE_SIZE = 4
	const long EXPECTED_RESULT = 1973902911463121104L;

	/**
	 * Get the value as a table index.
	 */
	int f(long val) {
		return (int) (val & (TABLE_SIZE-1));
	}

	/**
	 * Get the value as an index into the small table.
	 */
	int g(long val) {
		return (int)(val >>>
				(WORD_SIZE-LOG2_S_TABLE_SIZE)); }

	/**
	 * Return the next number following this one.
	 * Actually the next item in the sequence generated
	 * by a primitive polynomial over GF(2).)
	 */
	long nextRandom(long val) {
		return
			((val<<1) ^ (val < 0 ? POLY : 0)); }

	/*
	 * Utility routines to create simple common dists
	 */

	/**
	 * create a simple 1D blocked dist
	 */
	dist block (int arraySize) {
		return dist.factory.block([0:(arraySize-1)]);
	}

	/**
	 * create a unique dist (mapping each i to place i)
	 */
	dist unique () {
		return dist.factory.unique(x10.lang.place.places);
	}

	final dist tableDist = block(TABLE_SIZE); // same as table.distribution

	/**
	 * main RandomAccess routine
	 */
	public boolean run() {
		// A small value table that will be copied to all processors
		final long value[.] smallTable =
			new long value[[0:S_TABLE_SIZE-1]->here]
			(point p[i]) { return i*S_TABLE_INIT; };
		// distributed histogram table
		final long[.] table = new long[block(TABLE_SIZE)]
			(point p[i]) { return i; };
		// random number starting seeds for each place
		final long[.] ranStarts = new long[unique()]
			(point p[i]) { return C.starts(N_UPDATES_PER_PLACE*i); };

		// In all places in parallel, repeatedly generate random indices
		// and do remote atomic updates on corresponding table elements
		finish ateach (point p[i]: ranStarts.distribution) {
			long ran = nextRandom(ranStarts[i]);
			for (point count: [1:N_UPDATES_PER_PLACE]) {
				final int  j = f(ran);
				final long k = smallTable[g(ran)];
				async(table.distribution[j])
					atomic {
						table[j] = table[j] ^ k;
					}
				ran = nextRandom(ran);
			}
		}

		final long sum = table.sum();
		System.out.println("Check sum = "+sum);
		return sum == EXPECTED_RESULT;
	}

	public static void main(String[] args) {
		new RandomAccess_long().execute();
	}

	void msg(int i, int j) {
		System.out.println("Place "+i+" updating table index "+j+" in place "+tableDist[j].id);
	}

	/**
	 * C routines to be linked with X10, written in X10 for now
	 */
	static class C {
		// self contained constants for C routines

		private static final long POLY = 0x0000000000000007L;
		private static final long PERIOD = 1317624576693539401L;

		private static long nextRandom(long temp) {
			return (temp << 1) ^ (temp < 0 ? POLY : 0);
		}

		private static boolean getBit(long n, int i) {
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
		public static long starts(long n) {
			int i, j;
			long[] m2 = new long[64];
			long temp, ran;

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

