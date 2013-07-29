package x10.lang;

//import x10.compiler.Native;
//import x10.compiler.Inline;
//import x10.compiler.Pragma;
//import x10.compiler.NativeCPPInclude;

import x10.util.concurrent.AtomicReference;
import x10.util.concurrent.AtomicInteger;

//@NativeCPPInclude("x10/lang/RuntimeNatives.h")
public final class Future[T]{T isref, T haszero} {
  
  // The holder for the value of the future.
  var data: AtomicReference[T];
  // ---------------------------------------------
  // The set of tasks.
  // Improvement spot:
  // We can try alternative implementations
  // of this set such as a small Hashset.  
  static class Node[T2]{T2 isref} {
    var element: T2;
    var next: Node[T2];

    def this(element: T2, next: Node[T2]) {
      this.element = element;
      this.next = next;
    }
  }
  var head: AtomicReference[Node[TentTask]];
  private def addTask(task: TentTask) {
    var done: Boolean = false;
    while (!done) {
      val currentHead: Node[TentTask] = head.get(); 
      val node = new Node[TentTask](task, currentHead);
      done = head.compareAndSet(currentHead, node);
    }
  }
  private def notifyTasks() {
    var node: Node[TentTask] = head.get();
    while (node != null) {
      val task = node.element;
      notifyTask(task);
      node = node.next;
    }      
  }
  // ---------------------------------------------

  def this() {
    super();
    this.data = new AtomicReference[T]();
    //this.data.set(null);
    this.head = new AtomicReference[Node[TentTask]]();
    //this.head.set(null);
  }

  static class TentTask {
    var task: WaitingTask;
    // Improvement spot:
    // Is it safe to use a basic variable?
    var flag: AtomicInteger;
    
    static val TENTATIVE = 1;
    static val STABLE = 2;
    static val REMOVED = 3;
    
    def this(task: WaitingTask) {
      this.task = task;
      flag = new AtomicInteger();
      flag.set(TENTATIVE);
    }
  }

  // Adds the task to the set of tasks if the future
  // is not set. Returns true if added.
  public def addIfNotSet(task: WaitingTask): Boolean {
    if (data.get() != null)
      return false;
    val tentTask = new TentTask(task);
    addTask(tentTask);
    if (data.get() != null) {
      // We know that data.compareAndSet() is linearized between the
      // two data.get(). Thus, the value is just set.
      // The future is ready.
      // We remove the tentative task and return not added.
      tentTask.flag.set(TentTask.REMOVED);
      return false;
    } else {
      // We know that there has been no data.compareAndSet() before
      // the second data.get(). 
      // Thus, data.compareAndSet() is after second data.get(). 
      // addTask() if before data.get().
      // data.compareAndSet() is before notifyTasks().
      // Thus, addTask() if before notifyTasks().
      // Thus, this task is properly added.
      tentTask.flag.set(TentTask.STABLE);
      return true;
    }
  }
    
  public def put(v: T) {
    if (data.compareAndSet(null, v))
      notifyTasks();
    else
      throw new Exception("Future is already set.");
  }

  private def notifyTask(tentTask: TentTask) {
    val flag = tentTask.flag;
    var state: Int = flag.get();
    while (state == TentTask.TENTATIVE)
      state = flag.get(); // Spin for a short time
    if (state == TentTask.STABLE) {
      val waitingTask = tentTask.task;
      waitingTask.inform();
    }
  }

  public def get(): T {
    val d = data.get();
    if (d == null)
      throw new Exception("Future is not ready yet.");
    else
      return d;
  }
  
  //@Native("java", "java.lang.System.err.println(#any)")
  //@Native("c++", "x10::lang::RuntimeNatives::println(x10aux::to_string(#any)->c_str())")
  //public native static def println(any:Any):void;
  
}


