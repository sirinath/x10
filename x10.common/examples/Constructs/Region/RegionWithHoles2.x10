import harness.x10Test;

public class RegionWithHoles2 extends x10Test {

	public boolean run() {
		// all of those are contiguous
		region r = [0:10];
		region r1 = [1:2];
		region r2 = [5:6];

		// create wholes in r
		r = r - r1;
		r = r - r2;

		short[.] a = new short[r];

		// check if r is convex - it should not!
		boolean cv = r.isConvex();
		System.out.println("convex: " + cv + " (should be false)");
		chk(!cv);

		System.out.print("indexes: ");
		for (point[i] : r) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (point[i] : r) {
			System.out.println("val[" + i + "] = " + a[i]);
		}

		return true;
	}

	public static void main(String[] args) {
		new RegionWithHoles2().execute();
	}
}

