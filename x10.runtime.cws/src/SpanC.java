

/**
 * (c) IBM Corporation 2007
 * Author: Guojing Cong
 * Tong Wen
 * Vijay Saraswat
 */
import java.util.concurrent.atomic.AtomicInteger;
import x10.runtime.cws.*;
import x10.runtime.cws.Job.GFrame;
import x10.runtime.cws.Job.GloballyQuiescentJob;



public class SpanC {
	
	class V {
		public int parent;
		public int degree;
		public int [] neighbors;
		public V(){}
		public String toString() { 
			String s="[" + (neighbors.length==0? "]" : "" + neighbors[0]);
			for (int i=1; i < neighbors.length; i++) s += ","+neighbors[i];
			return "v(parent=" + parent + ",degree="+degree+ ",n=" + s+"])";}
	}
	
	
	class E{
		public int v1,v2;
		public boolean in_tree;
		public E(int u1, int u2){ v1=u1;v2=u2;in_tree=false;}
	}
	public static int N=100000,M=400000;
	int m;
	V[] G;
	E[] El;
	E[] El1;
	AtomicInteger [] color;
	int ncomps=0;
	
	static int[] Ns = new int[] {10*1000, 50*1000, 100*1000,
	500*1000, 1000*1000, 2*1000*1000, 3*1000*1000, 4*1000*1000};
	
	
	public SpanC (){
		
		/*constructing edges*/
		El = new E [M];
		double[] seeds = new double[] {
				0.21699440277541715, 0.9099040926714322, 0.5586793832519766,
				0.15656203486110076, 0.3716929310972751, 0.6327328452004045,
				0.9854204833301402, 0.8671652950975213, 0.1079976151083556,
				0.5993517714916581
		};
		El = new E [M];
		for(int i=0;i<M;i++){
			El[i]=new 
			//E ((int) (Math.random()*N)%N, (int) (Math.random()*N)%N);
			E ((int) (seeds[i%10]*(N+i))%N, (int) (seeds[(i+1)%10]*(N+i))%N);
		}
		
		int[] D = new int [N];
		/* D[i] is the degree of vertex i (duplicate edges are counted).*/
		for(int i=0;i<M;i++){
			D[El[i].v1]++;
			D[El[i].v2]++;
		}
		
		int[][] NB = new int[N][];/*NB[i][j] stores the jth neighbor of vertex i*/
		// leave room for making connected graph by +2
		for(int i=0;i<N;i++) NB[i]=new int [D[i]+2]; 
		
		/*Now D[i] is the index for storing the neighbors of vertex i
		 into NB[i] NB[i][D[i]] is the current neighbor*/
		for(int i=0;i<N;i++) D[i]=0;
		
		m=0;
		for(int i=0;i<M;i++) {
			boolean r=false;;  
			/* filtering out repeated edges*/
			for(int j=0;j<D[El[i].v1] && !r ;j++){ 
				if(El[i].v2==NB[El[i].v1][j]) r=true;
			}
			if(r){
				El[i].v1=El[i].v2=-1; /*mark as repeat*/
			} else {
				m++;
				NB[El[i].v1][D[El[i].v1]]=El[i].v2;
				NB[El[i].v2][D[El[i].v2]]=El[i].v1;
				D[El[i].v1]++;
				D[El[i].v2]++;
			}
		}  
		
		/* now make the graph connected*/
		/* first we find all the connected comps*/
		
		color = new AtomicInteger [N];
		for (int i=0; i < color.length; i++) color[i] = new AtomicInteger();
		int[] stack = new int [N]; 
		int[] connected_comps  = new int [N]; 
		
		int top=-1;
		ncomps=0;
		
		for(int i=0;i<N && color[i].get() !=1;i++) {
			connected_comps[ncomps++]=i;
			stack[++top]=i;
			color[i].set(1);
			while(top!=-1) {
				int v = stack[top];
				top--;
				
				for(int j=0;j<D[v];j++) {
					int m = NB[v][j];
					if(color[m].get()==0){
						top++;
						stack[top]=m;
						color[m].set(1);
					}
				}
			}
		}
		
		System.out.println("ncomps="+ncomps);
		El1 = new E [m+ncomps-1]; 
		
		for(int i=0;i<N;i++) color[i].set(0);
		
		int j=0;
		//    Remove duplicated edges
		for(int i=0;i<M;i++) if(El[i].v1!=-1) El1[j++]=El[i]; 
		
		if(j!=m) System.out.println("Remove duplicates failed");
		else System.out.println("Remove duplicates succeeded,j=m="+j);
		
		/*add edges between neighboring connected comps*/
		for(int i=0;i<ncomps-1;i++) {
			NB[connected_comps[i]][D[connected_comps[i]]++]=connected_comps[i+1];
			NB[connected_comps[i+1]][D[connected_comps[i+1]]++]=connected_comps[i];
			El1[i+m]=new E (connected_comps[i], connected_comps[i+1]);
		}
		
		G = new V[N];
		for(int i=0;i<N;i++) {
			G[i]=new V();
			G[i].degree=D[i];
			
			G[i].neighbors=new int [D[i]];
			for(j=0;j<D[i];j++)
				G[i].neighbors[j]=NB[i][j];
			//System.out.println("G["+i+"]=" + G[i]);
		}     
		
	}
	
	public static class TFrame extends Frame {
		final int u; // vertex
		volatile int k;
		public TFrame(int u) {
			this.u=u;
		}
		public void setOutlet(final Closure c) {
			// nothing to do
		}
		public Closure makeClosure() {
			return new Traverser(this);
		}
		public String toString() { return "SpanC " + "u=" + u + ",k=" + k;}
	}
	public static final int LABEL_0 = 0;
	static void traverse(final Worker w, V[] G, AtomicInteger[] c, final int u) throws StealAbort {
		TFrame frame = new TFrame(u);
		frame.k=1;
		//frame.PC=LABEL_0;
		w.pushFrame(frame);
		int k=0;
		while (k < G[u].degree) {
			int v=G[u].neighbors[k];
			boolean result = c[v].compareAndSet(0,1);
			if (result) {
				//System.out.println(w + " sets " + v + ".parent to " + u + ".");
				G[v].parent=u;
				traverse(w,G,c,v);
				w.abortOnSteal();
			}
			++k;
			frame.k=k;
			//frame.PC=LABEL_0; // to publish the f.k assignment.
		}
		w.popFrame();
		return;
	}
	public static class Traverser extends Closure {
		public Traverser(TFrame t) { 
			super(t);
		}
		
		public void compute(Worker w, Frame frame) throws StealAbort {
			TFrame f = (TFrame) frame;
			final int u = f.u;
			int k = f.k;
			final SpanC g = graph;
			final V[] G = graph.G;
			final AtomicInteger[] c = graph.color;
			while (k < G[u].degree) {
				int v=G[u].neighbors[k];
				boolean result = c[v].compareAndSet(0,1);
				if (result) {
					//System.out.println(w + "" + v + ".parent=" + u);
					G[v].parent=u;
					traverse(w,G,c,v);
					w.abortOnSteal();
				}
				++k;
				f.k=k;
			}
			setupGQReturnNoArg();
			
			return;
		}
	}
	boolean verifyTraverse(int root) {
		int[] X = new int [N];
		for(int i=0;i<N;i++) X[i]=G[i].parent;
		//for(int i=0;i<10;i++) System.out.print("parent["+i+"]=" + X[i]+",");
		//System.out.println();
		for(int i=0;i<N;i++) while(X[i]!=X[X[i]]) X[i]=X[X[i]];
		//for(int i=0;i<10;i++) System.out.print("root["+i+"]=" + X[i]+",");
		//System.out.println();
		for(int i=0;i<N;i++) {
			
			if(X[i]!=X[0])  
				return false;
		}
		return true;
	}
	static SpanC graph;
	public static void main(String[] args) {
		int procs;
		try {
			procs = Integer.parseInt(args[0]);
			System.out.println("Number of procs=" + procs);
		}
		catch (Exception e) {
			System.out.println("Usage: java SpanT <threads>");
			return;
		}
		Pool g = new Pool(procs);
		for (int i=0; i < Ns.length; i++) {
			
			N = Ns[i]; M = 3*N/5;
			graph = new SpanC();
			
			
			GloballyQuiescentJob job = new GloballyQuiescentJob(g) {
				
				@Override
				protected void compute(Worker w, Frame frame) throws StealAbort {
					GFrame f = (GFrame) frame;
					int PC = f.PC;
					f.PC=LABEL_1;
					if (PC==0) {
						// spawning
						graph.color[0].set(1);
						traverse(w,graph.G, graph.color, 0);
						w.abortOnSteal();
					}
					setupGQReturnNoArg();
				}
				@Override
				public int spawnTask(Worker ws) throws StealAbort { 
					
					return 0;
					
				}
				public String toString() { 
					return "GJob(SpanC,#" + hashCode() + ",status=" + status + ",frame=" + frame + ")";}
			};
			
			
			long s = System.nanoTime();
			g.submit(job);
			try {
				job.waitForCompletion();
			} catch (InterruptedException z) {}
			long t = System.nanoTime();
			System.out.println( "N=" + N + " " + graph.verifyTraverse(0) 
					+ " Time: "  + (t-s)/1000000  + " Steals=" + g.getStealCount());
			//for ( i=0; i < graph.G.length; i++) System.out.println("G["+i+"]="+graph.G[i]);
			System.gc();
			System.out.println("Finished gc.");
		}   
		g.shutdown(); 
	}
}

