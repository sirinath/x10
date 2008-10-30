/**
 * @author cmd
 * An embarassingly parallel program to test multi-node vm.
 * A simple manually coded reduction. Similar to BasicTest2, but with an uneven
 * distribution of elements
 */

public class BasicTest3 {
	
	private float[.] _sumarray;
	private float@places.FIRST_PLACE _totalSum;
	
	private void initialize(int size){
		long start = System.currentTimeMillis();
		_totalSum=0;
		region e= region.factory.region(0,size-1);
		region f = [0:4,e];
		dist d=dist.factory.block(f);
		final int theSize = size;
		_sumarray = new float[d] (point p[i,j]){return theSize*i +j;};
		long stop = System.currentTimeMillis();
		System.out.println("Initialization takes:"+((double)(stop-start)/1000)+" seconds");
		
	
	}
	
	
	/**
	 * hack to get future construct to work
	 */
	private float sumRegion(region my_region,float [.] theArray,int placeId,
			boolean trace,boolean verboseTrace){

		System.out.println("summing region over "+my_region+" for place "+placeId);
		float tempSum=0;
           	for(point p:my_region){
                   tempSum += theArray[p];
	           if(verboseTrace)System.out.println(placeId+":"+tempSum+"<-"+theArray[p]+" for "+p);
	
		}
		if(trace)System.out.println("sum is:"+tempSum);
		return tempSum; // this will be returned and stored in partialSums[i]
	}
	
	private void run(){
		final boolean trace = false;
		final boolean verboseTrace=false;
		System.out.println("Starting run with "+_sumarray.region.size()+" array elements");
		
		long start = System.currentTimeMillis();
		place p1 = place.FIRST_PLACE;
		final  region[] precomputedRegions = new region[place.MAX_PLACES];
		
		place my_place = place.FIRST_PLACE;
		int count=0;

		do{
			region tempRegion = (_sumarray.distribution | my_place).region;
			precomputedRegions[my_place.id] = tempRegion;
			my_place = my_place.next();
			++count;
		} while (my_place != place.FIRST_PLACE);
		
		int i=0;
		// will hold intermediate sums
		future <float>[] partialSums = new future<float>[count];
		
		do{
			
			if(trace)System.out.println("Spawning work for place:"+i+" p1:"+p1+"::"+p1.id);
			final int placeId = i;
			final region my_region = precomputedRegions[placeId];
			partialSums[i] = future(p1) {sumRegion(my_region,_sumarray,placeId,trace,verboseTrace)};
			
			p1 =p1.next();
			++i;
		} while(i < count);
		
		for(int k=0;k < partialSums.length;++k){
			_totalSum += partialSums[k].force();
		}
		long stop = System.currentTimeMillis();
		System.out.println("Total is:"+_totalSum);
		System.out.println("Elapsed time:"+((double)(stop-start)/1000)+" seconds");
	}
	private void verify(){
		// it should total (n^2 +n)/2
		float size =_sumarray.region.size()-1;//make it zero relative
		
		float expected_result = (size * size + size)/2;
		
		System.out.println("Expected result(size="+size+")="+expected_result);
		System.out.println("Calculated result:"+_totalSum);
		if(_totalSum != expected_result){
			throw new x10.lang.Exception(expected_result+"!="+_totalSum);
		}
	}
	
	public static void main(String[] args) {
		int arraySize=10;//0;
		
		boolean success = true;
		
		System.out.println("Starting Basic multi-node test with futures");
		try {
			if(args.length> 0){
				arraySize = java.lang.Integer.parseInt(args[0]);
			}
			
			BasicTest3 x = new BasicTest3();
			x.initialize(arraySize);
			x.run();
			x.verify();
			
		} catch (Throwable e) {
			e.printStackTrace();
			success=false;        
		}
		System.out.println("++++++ "+(success?"Test succeeded.":"Test failed."));
		x10.lang.Runtime.setExitCode(success?0:1);
	}
	
}
