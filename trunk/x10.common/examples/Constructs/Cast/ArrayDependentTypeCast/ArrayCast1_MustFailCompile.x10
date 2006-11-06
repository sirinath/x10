import harness.x10Test;

/**
 * Purpose: Checks cast's code statically detects constraints are not meet.
 * Issue: Rank constraint is not meet.
 * (int[:rank==2&&zeroBased]) <-- (int[:rank==1&&zeroBased])
 * @author vcave
 **/
public class ArrayCast1_MustFailCompile extends x10Test {

	public boolean run() {
		region(:rank==1&&zeroBased) rank1Zero = 
			(region(:rank==1&&zeroBased)) region.factory.region(0, 10);
		
		dist(:rank==1&&zeroBased) d1 = 
			(dist(:rank==1&&zeroBased)) dist.factory.constant(rank1Zero, here);

		int[:rank==2&&zeroBased] ia = (int[:rank==2&&zeroBased]) new int[d1];
		
		return true;
	}

	public static void main(String[] args) {
		new ArrayCast1_MustFailCompile().execute();
	}

}
 