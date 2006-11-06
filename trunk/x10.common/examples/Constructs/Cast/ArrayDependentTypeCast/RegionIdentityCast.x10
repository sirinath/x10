import harness.x10Test;

/**
 * Purpose: Check primitive dependent type assignment to primitive variable works.
 * @author vcave
 **/
public class RegionIdentityCast extends x10Test {

	public boolean run() {
		// identity cast
		region(:rank==1&&zeroBased) rank1Zero = (region(:rank==1&&zeroBased)) region.factory.region(0, 10);
		
		region(:rank==1&&zeroBased) rank1Zero_bis = (region(:rank==1&&zeroBased)) region.factory.region(0, 20);
		
		// Construct new region from rank==1&&zeroBased==true
		region(:rank==2&&rect&&zeroBased) rank2RectZero = 
			(region(:rank==2&&rect&&zeroBased)) region.factory.region(new region[] {rank1Zero, rank1Zero});

		return true;
	}

	public static void main(String[] args) {
		new RegionIdentityCast().execute();
	}

}
 