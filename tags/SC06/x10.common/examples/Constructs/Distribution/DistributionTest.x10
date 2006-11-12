import harness.x10Test;

/**
 * Minimal test for dists.
 */
public class DistributionTest extends x10Test {

	public boolean run() {
		region r = region.factory.region(0, 100); //(low, high)
		final region R = region.factory.region(new region[] { r, r } );
		dist d = dist.factory.constant(R, here);
		return ((d.rank == 2) &&
				(R.rank == 2) &&
				(R.rank(1).high() - R.rank(1).low() + 1 == 101));
	}

	public static void main(String[] args) {
		new DistributionTest().execute();
	}
}

