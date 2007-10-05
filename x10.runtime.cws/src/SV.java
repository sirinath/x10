/**
 * (c) IBM Corporation 2007
 * Author: Vijay Saraswat
 * 
 * 
 * Shiloach Vishkin algorithm.
 */

import x10.runtime.cws.Frame;
import x10.runtime.cws.Pool;
import x10.runtime.cws.StealAbort;
import x10.runtime.cws.Worker;
import x10.runtime.cws.Job.GloballyQuiescentVoidJob;
import x10.runtime.cws.TaskBarrier;

public class SV {
	
	public class V   {
		public final int index;
		public int level=-1;
		public int degree;
		public V parent;
		public V [] neighbors;
		public V(int i){index=i;}
		public String toString() {
			return "V("+index+")";
		}
	}
	class E{
		public int v1,v2;
		public boolean inTree;
		public E(int u1, int u2){ v1=u1;v2=u2;inTree=false;}
		public String toString() { return v1 + "--" + v2;}
	}
	
	int m;
	final V[] G;
	final E[] El, El1;
	int ncomps=0;
	boolean changed=true;

	static int[] Ns = new int[] {10};//1000*1000, 2*1000*1000, 3*1000*1000, 4*1000*1000, 5*1000*1000};
	int N=1000, M=4000; //N is the number of vertexes and M is the number of edges

	int randSeed = 17673573; 
	int rand32() { return randSeed = 1664525 * randSeed + 1013904223;}
	static void print(int[] a, String prefix) { 
		  System.out.print(prefix + "={"); 
		  if (a.length > 0) {
			  System.out.print(a[0]); 
			  for (int i=1; i < a.length; i++) System.out.print(","+a[i]);
		  }
		  System.out.println("}"); 
		}
	public SV (int n, int m){
		N=n;
		M=m;
		/*constructing edges*/
		G = new V[N]; for(int i=0;i<N;i++) G[i]=new V(i);
		El = new E [M]; for (int i=0; i <M; i++) El[i] = new E(Math.abs(rand32())%N, Math.abs(rand32())%N);
		
		/* D[i] is the degree of vertex i (duplicate edges are counted).*/
		int[] D = new int [N];
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
			boolean r=false;
			E edge = El[i];
			int s = edge.v1, e = edge.v2;
			/* filtering out repeated edges*/
			for(int j=0;j<D[s] && !r ;j++) if(e==NB[s][j]) r=true;
			if(r){
				edge.v1=edge.v2=-1; /*mark as repeat*/
			} else {
				m++;
				NB[s][D[s]++]=e;
				NB[e][D[e]++]=s;
			}
		}  
		if (reporting || graphOnly) {
			System.out.println((m-1) + " edges.");
			for(int i=0;i<N;i++) {
				System.out.print(i + "-->");
				for (int j=0; j < D[i]; j++) System.out.print(NB[i][j] + " ");
				System.out.println();
			}     
		}
		/* now make the graph connected*/
		/* first we find all the connected comps*/
		
		//visitCount = new AtomicIntegerArray(N);
		int[] stack = new int [N]; 
		int[] cc  = new int [N]; 
		
		int top=-1;
		ncomps=0;
		for(int i=0;i<N ;i++) {
			if (G[i].level==1) continue;
			G[i].level=1;
			cc[ncomps++]=stack[++top]=i;
			while(top!=-1) {
				int v = stack[top--];
				for(int j=0;j<D[v];j++) {
					final int mm = NB[v][j];
					if(G[mm].level !=1){
						stack[++top]=mm;
						G[mm].level=1;
					}
				}
			}
		}
		
		if (reporting) System.out.println("ncomps="+ncomps);
		El1 = new E [m+ncomps-1]; 
		
		
		int j=0;
		//    Remove duplicated edges
		for(int i=0;i<M;i++) if(El[i].v1!=-1) El1[j++]=El[i]; 
		
		if (reporting) {
			if(j!=m) 
				System.out.println("Remove duplicates failed");
			else System.out.println("Remove duplicates succeeded,j=m="+j);
			
		}
		
		/*add edges between neighboring connected comps*/
		for(int i=0;i<ncomps-1;i++) {
			int s=cc[i], e=cc[i+1];
			NB[s][D[s]++]=e;
			NB[e][D[e]++]=s;
			El1[m+i]=new E (e,s);
		}
		for(int i=0;i<N;i++) {
			G[i].degree=D[i];
			G[i].level=-1;
			G[i].neighbors=new V [D[i]];
			for( j=0;j<D[i];j++) G[i].neighbors[j]=G[NB[i][j]];
			if (reporting || graphOnly) {
				System.out.print("G[" + i + "]=" + G[i]);
				for ( j=0; j < G[i].degree; j++) System.out.print(" " + G[i].neighbors[j]);
				System.out.println();
			}
		}     
	}
	
	class SVJob extends Frame {
		final int numWorkers;
		volatile int PC=0; 
		SVJob(int n) {
			numWorkers=n; 	
			// System.out.println("Created SVJob " + numWorkers);
		}
		public String toString() {  return "SVJob(" + numWorkers+")";}
		public void compute(Worker w){
			if (PC==1) {
				w.popFrame();
				return;
			}
			PC=1;
			final int [] D1 = new int [N]; for (int i = 0; i <N; i++) D1[i]= i;
			final int [] ID = new int [N]; for (int i = 0; i <N; i++) ID[i]= -1;
		
			final int localVertexSize = N/numWorkers;
			final int edgeSize =El1.length;
			final int localEdgeSize = edgeSize/numWorkers;
			
			final TaskBarrier barrier = new TaskBarrier() { public String name() { return "barrier";}};
			class SVWorker extends Frame {
				int j, vLow, vHigh, eLow, eHigh;
				
				SVWorker(int j) {
					this.j=j; 
					this.vLow=j*localVertexSize; 
					this.vHigh=(j+1)*localVertexSize-1;
					this.eLow = j*localEdgeSize;
					this.eHigh = (j+1)*localEdgeSize-1;
					if (j==numWorkers-1) {
						if (localEdgeSize*numWorkers != edgeSize) eHigh= edgeSize-1;
						if (localVertexSize*numWorkers !=N) vHigh = N-1;
					}
				}
				public String toString() { return "SVWorker " + j;}
				public void compute(Worker w)  throws StealAbort {
					if (j==0) changed=true;
					boolean locallyChanged = true;
					barrier.arriveAndAwait(); 
					while (locallyChanged) {
						locallyChanged = false;
						changed=false;
						for (int i=eLow; i <=eHigh; i++) {
							final int v1=El1[i].v1, 
							v2=El1[i].v2, 
							s=D1[v1], 
							e=D1[v2],
							ee=D1[e];
							if(s < e && e==ee) ID[e]=i;
							if(e < s && s==D1[s]) ID[s]=i;
						}
						barrier.arriveAndAwait(); 
						for (int i=eLow; i <=eHigh; ++i) {
							final int v1=El1[i].v1, v2=El1[i].v2,s=D1[v1], e=D1[v2], ee=D1[e];
							if(s < e && e==ee && ID[e]==i) {
								D1[e]=s; 
								El1[i].inTree=locallyChanged=true; 
							}
							if(e < s && s==D1[s] && ID[s]==i) {
								D1[s]=e; 
								El1[i].inTree=locallyChanged=true; 
							}                        
						}
						
						barrier.arriveAndAwait();
						if (locallyChanged) changed=true;
						barrier.arriveAndAwait();
						locallyChanged=changed;
						/*Make sure the labels of each group is the same.*/
						for (int i=vLow; i <=vHigh; i++) 
							while (D1[D1[i]]!=D1[i]) D1[i]=D1[D1[i]];
						barrier.arriveAndAwait();
						
					} // while
					barrier.arriveAndDeregister();
					w.popFrame();
							
				}
			}
			for (int i=0; i < numWorkers; i++) {
				barrier.register(); 
				// this is not a recursive call, it just pushes the frame.
				w.pushFrame(new SVWorker(i));
			}
			//System.out.println(w + " ends " + this);
			// return, must not w.popFrame() because some other frames are on the stack now.
			// when those are executed to completion, this frame will be left on the dequeue with PC=1.
			// a normal execution of it by this worker will result in the frame being popped...see 
			// the code at the beginning of this method.
		}
	}
	boolean verifySV() {
		int sum=0; for(E e : El1) if(e.inTree) sum++;
		if(sum<N-1){
			System.out.println("verifySV failed " + sum);
			return false;
		}
		return true;
	}

	static boolean reporting = false;
	static final long NPS = (1000L * 1000 * 1000);
	static boolean graphOnly =false;
	public static void main(String[] args) {
		int num=-1;
		int procs =1;
		int D=4;
		try {
			procs = Integer.parseInt(args[0]);
			System.out.println("P=" + procs);
			if (args.length > 1) {
				num = Integer.parseInt(args[1]);
				System.out.println("N=" + num);
			}
			if (args.length > 2) {
				D = Integer.parseInt(args[2]);
				System.out.println("D=" + D);
			}
			if (args.length > 3) {
				boolean b = Boolean.parseBoolean(args[3]);
				reporting=b;
			}
			if (args.length > 4) {
				boolean b = Boolean.parseBoolean(args[4]);
				graphOnly=b;
			}
		}
		catch (Exception e) {
			System.out.println("Usage: java SV <P>  [<N> [<Degree> [[false|true] [false|true]]]]");
			return;
		}
		Pool g = new Pool(procs);
		if (num >= 0) {
			Ns = new int[] {num};
		}
		for (int i=Ns.length-1; i >= 0; i--) {
			int N = Ns[i], M = D*N;
			System.gc();
			SV graph = new SV(N,M);
			if (graphOnly) return;
		
			//System.out.printf("N:%8d ", N);
			for (int k=0; k < 10; ++k) {
				long s = -System.nanoTime();
				final V root = graph.G[1];
				root.level=0;
				root.parent=root;
				
				GloballyQuiescentVoidJob job = 
					new GloballyQuiescentVoidJob(g, graph.new SVJob(procs));
				//System.out.println("Starting " + job);
				g.invoke(job);
				//System.out.println(" ...done with " + job);
				s += System.nanoTime();
				double secs = ((double) s)/NPS;
				System.out.printf("N=%d t=%5.3f", N, secs);
				System.out.println();
				if (! graph.verifySV())
					System.out.printf("%b ", false);
				graph.reset();
				
			}
			System.out.printf("Completed iterations for N=%d", N);
			System.out.println();
		}   
		
	}
	void reset() {
		for (int i = 0; i < El1.length; ++i) {
			El1[i].inTree = false;
		}
	}


}

