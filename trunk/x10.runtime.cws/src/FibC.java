
import x10.runtime.cws.Closure;
import x10.runtime.cws.Frame;
import x10.runtime.cws.Job;
import x10.runtime.cws.Closure.Outlet;
import x10.runtime.cws.Pool;
import x10.runtime.cws.Worker;
import x10.runtime.cws.StealAbort;


/**
 * A pointless recursive Fibonacci. Meant to be used
 * to test system performance. Implements recursive Fib
 * Cilk style
 * Notes:
 * -- the thread is always working on the current task which is also stored
 * at the bottom of its ready queue and is hence available to be stolen.
 * -- therefore the state of a task may simultaneouly be accessed by multiple
 * -- threads.
 * @author vj
 *
 */
public class FibC  extends Closure {
  static final int ENTRY=0, LABEL_1=1, LABEL_2=2,LABEL_3=3;

  public String toString() { 
      return "FibC(#"+hashCode()  +",status="+status + "," + frame + "result=" + result + ")";
    }
  public static int realfib(int n) {
    if (n < 2) return n;
    int y=0,x=1;
    for (int i=0; i <= n-2; i++) {
      int temp = x; x +=y; y=temp;
    }
    return x;
  }
  static class FibFrame extends Frame {
    final int n;
    int x,y;
    public FibFrame(int n) {
      super();
      this.n=n;
    }
    @Override public void setOutletOn(final Closure c) {
        assert PC==LABEL_1 || PC == LABEL_2;
        c.setOutlet((PC==LABEL_1) ?
            new Outlet() {
              public void run() {
                x = c.resultInt();
              }
              public String toString() { return "OutletInto x from " + c;}
              } 
        : new Outlet() {
          public void run() {
            y = c.resultInt();
          }
          public String toString() { return "OutletInto y from " + c;}
          });
      
      }
    
    public Closure makeClosure() {
      return new FibC(this);
    }
    public String toString() { return "FibFrame(n="+n+",x="+x+",y="+y+",PC=" + PC + ")";}
  }
  
  
  static int fib(Worker w, int n) throws StealAbort { // fast mode
    if (n < 2) return n;
    FibFrame frame = new FibFrame(n);
    frame.PC=LABEL_1; // continuation pointer
    w.pushFrame(frame);
    
    // this thread will definitely execute fib(n-1), and
    // hence set the value in the frame.
    final int x = fib(w, n-1);
    
    // Now need to figure out who is doing fib(n-2).
    // If frame has been stolen, then this thread wont do fib(n-2).
    // it should just return, and subsequent work will be done
    // by others. 
    w.abortOnSteal(x);
    
    
    // Now we are back in the current frame, it has not been stolen. 
    // Execute the local code to the next spawn. 
    
    // Now at the next spawn, exactly as before, set up the 
    // continuation pointer. 
    frame.x=x;
    frame.PC=LABEL_2;
  
    final int y=fib(w, n-2);
    w.abortOnSteal(y);
  
    // Now there is nothing more to spawn -- so no need for the frame.
    // i.e. since the worker has made it so far, it is going to complete 
    // execution of this procedure.
    
    // pop the task -- it is guaranteed to be garbage.
    w.popFrame();
    
    // the sync is a no-op.
    // return the computed value.
    int result = x+y;
    return result;
  }
  public FibC(Frame frame) { super(frame);}
  
  @Override
  public void compute(final Worker w, final Frame frame) throws StealAbort {
    // get the frame.
    // f must be a FibFrame.
    final FibFrame f = (FibFrame) frame;
    final int n = f.n;
    switch (f.PC) {
    case ENTRY: 
    	if (n < 2) {
    		result = n;
    		setupReturn();
    		return;
    	}
    	f.PC=LABEL_1;
    	final int x = fib(w, n-1);
    	w.abortOnSteal(x);
    	f.x=x;
    	
    case LABEL_1: 
    	f.PC=LABEL_2;
    	final int y=fib(w,n-2);
    	w.abortOnSteal(y);
    	f.y=y;
    	
    case LABEL_2: 
    	f.PC=LABEL_3;
    	if (sync(w)) {
    		return;
    	}
    case LABEL_3:
    	result=f.x+f.y;
    	setupReturn();
    }
    return;
  }
  

  public static void main(String[] args) throws Exception {
    int procs;
    try {
      procs = Integer.parseInt(args[0]);
      System.out.println("Number of procs=" + procs);
      if (args.length > 1) Worker.reporting = true;
    } catch (Exception e) {
      System.out.println("Usage: Fib <threads>");
      return;
    }
    final Pool g = new Pool(procs);
    final int[] points = new int[] {  1,5, 10, 15, 20, 25, 30, 35, 40, 45
    };
    
    for (int i = 0; i < points.length; i++) {
      final int n = points[i];
      Job job = new Job(g) { 
    	  int result;
    	  public void setResultInt(int x) { result = x;}
    	  public int resultInt() { return result;}
    	  public int spawnTask(Worker ws) throws StealAbort { return fib(ws, n);}
    	  public String toString() {
    		  return "Job(#" + hashCode() + ", fib(n=" + n +"," + status+ ")"+ frame+")";
    	  }};
    	  
    	  long s = System.nanoTime();
    	  g.submit(job);
    	  int result = job.getInt();
    	  
    	  long t = System.nanoTime();
    	  System.out.println(points[i] + " " + (t-s)/1000000 
    			  + " " + result + " " + (result==realfib(n)?"ok" : "fail") );
    }
    g.shutdown();
  }
  protected int result;
  @Override
  public int resultInt() { return result;}
  @Override
  public void setResultInt(int x) { result=x;}
}


