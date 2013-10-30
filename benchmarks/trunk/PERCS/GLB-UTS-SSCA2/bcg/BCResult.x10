package bcg;

/**
 * Class represents BC result,  which is essentially the betweeness map
 */
public class BCResult {
	val N:Int;
	val betweennessMap:Rail[Double];
	public def this(N:Int){
		this.N = N;
		this.betweennessMap = new Rail[Double](N, (i:Long)=>0d); // make sure it is initialized as 0
	}
	
	public def this(betweennessMap:Rail[Double]){
		this.betweennessMap = betweennessMap;
		this.N = betweennessMap.size as Int;
	}
	
	public static def getReducer(val NN:Int):Reducible[BCResult]{
		return new Reducible[BCResult](){
			public operator this(a:BCResult, b:BCResult){
				for(i in 0..(a.betweennessMap.size-1)){
					a.betweennessMap(i)+=b.betweennessMap(i);
				//based on the fact that we know a(i) & b(i) == 0, so we can save an allocation by resuing a (or b)
				}
				return a;
			}
			public def zero():BCResult = new BCResult(NN); 
		};
	}
}
