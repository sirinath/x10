import harness.x10Test;
/**
** The partition method of region is called, which should not be. Whether the run time error occors or not
** depends on the number of places  used (for instance, 3)and the value of k. 
** @author Tong
** @date 10/31/06
**/
public class RegionDifference extends x10Test {

	public boolean run() {
		final int size=10;
	    	final int k=5;
	    	final int factor=8;
	    	final region r=[k:size-1, k:k];
	    	//System.out.println(([k:k+factor-1,k:k]-r).toString());
	    	final dist d=dist.factory.cyclic([k:k+factor-1,k:k]-r);
	    	
		return true;
	}

	public static void main(String[] args) {
		new RegionDifference().execute();
	}
}

