package bcg;

import glb.TaskFrame;
import glb.TaskBag;
import x10.util.Team;

/*implemented on nov 08, 2013, modify on top of BCTaskFrame */
public class BCNBTaskFrame extends TaskFrame[BCResult]{
	/*task bag*/
	private val tb:BCTaskBag;
	
	/*vertice number*/
	private val verticesNum:Int;
	
	/*The interval of the graph to work on*/
	private val interval:Int;
	
	/*BC worker, work-horse*/
	private val bc_:BC;
	
	/*graph to work on*/
	private val graph:Graph;
	
	/*split threshold */
	private var splitThreshold:Int;
	/**
	 * @overide the initTask
	 */
	public def initTask():void {
		//this.tb.init(this.verticesNum, this.interval); // modified  on Nov 08, 2013
	}

	public def initTask(placeID: Long, maxPlace:Long):void{
		this.tb.init(this.verticesNum, this.interval, placeID, maxPlace);
	}
	
	/**
	 * Constructor
	 * @param rmat RMAT representation of the graph
	 * @param splitThreshold split threshold
	 * @permute 0 not to permute 1 to permute
	 */
	public def this(rmat:Rmat, interval:Int, splitThreshold:Int, permute:Int) {
		// init bc the workhorse
		val graph = rmat.generate();
		graph.compress();
		this.verticesNum = graph.numVertices();
		this.graph = graph;
		this.interval = interval;
		this.splitThreshold = splitThreshold;
		this.bc_ = new BC(this.graph);
		if (permute > 0) bc_.permuteVertices();
		// init the taskbag
		this.tb = new BCTaskBag(this.splitThreshold); // should just use a split threshold as a constructor, later on, more data will be either initialized or merged TODO
		this.tb.init(this.verticesNum, this.interval); // added on Nov 08, 2013
	}
	
	/**
	 * @Override
	 */
	public def runAtMostNTasks(var n:Long):Boolean {
		var tasksRun:Long = 0L;
		while((tb.size() > 0) && (tasksRun < n)){
			val vRange = tb.pop();
			val vStartIdx = vRange.startVertexIdx;
			val eStartIdx = vRange.endVertexIdx;
			bc_.bfsShortestPaths(vStartIdx, eStartIdx+1n, ()=>{this.yield();}); // NOTE: the +1n in the end, that is [start, end) is the region that SSCA2 works on when calling bfsShortestPaths
			tasksRun++;
		}
		return (tasksRun==n);
	}
	
	/**
	 * @Override
	 */
	public def getResult()=new BCResult(this.bc_.betweennessMap);
	public def getTaskBag()=tb;
	public def getReducer()= BCResult.getReducer(this.verticesNum);
	
	
	/**
	 * Override parent class method to print allocation and processing time
	 */
	public def printLog():void{
		Console.OUT.println("Place " + Runtime.hereLong()+ " Alloc time: " + bc_.allocTime/(1E9));
		Console.OUT.println("Place " + Runtime.hereLong()+ " processing time: " + (bc_.processingTime - bc_.yieldTime)/(1E9));
	        Console.OUT.println("Place " + Runtime.hereLong()+ " yielding time: " + (bc_.yieldTime)/(1E9));

	}
	
	
	public def reduce() {
		Team.WORLD.allreduce(bc_.betweennessMap, // Source buffer.
				0, // Offset into the source buffer.
				bc_.betweennessMap, // Destination buffer.
				0, // Offset into the destination buffer.
				bc_.N as long, // Number of elements.
				Team.ADD); // Operation to be performed.
	}
	
	
	
	
}
