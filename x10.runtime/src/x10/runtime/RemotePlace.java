package x10.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import x10.lang.Future;

/**
 * A RemotePlace (no _c because different javah programs tend
 * to tread _ characters in file names differently) is an object
 * on this VM that represents a LocalPlace_c object on some other
 * VM.
 *
 * @author Allan Kielstra
 **/

// this file is under construction....note that runFuture has not been
// started
public class RemotePlace extends Place {
   // firstInitArg is a HACK
   public native long runAsync_(final Activity a, String firstInitArg);
   public void runAsync(final Activity a) {
       assert a.getClass().getDeclaredConstructors().length == 1;
       String firstInitArg = 
          ((a.getClass().getDeclaredConstructors()[0].getParameterTypes().length > 0) &&
           (a.getClass().getDeclaredConstructors()[0].getParameterTypes()[0].toString().indexOf('$') >= 0)) ?
          (a.getClass().getDeclaredConstructors()[0].getParameterTypes()[0].toString().replaceFirst("class ","L") + ";") : "";
       
       Thread currentThread = Thread.currentThread();  
       if (currentThread instanceof ActivityRunner) {
           Activity parent = ((ActivityRunner) currentThread).getActivity();
           parent.finalizeActivitySpawn(a);
       } else {
           throw new Error("remote task invoked by non X10 thread");
       }
       
       a.initializeActivity();
       // this is now a surrogate for the remote activity
       a.activityAsSeenByInvokingVM = Activity.thisActivityIsASurrogate;
       a.placeWhereRealActivityIsRunning = this;
       a.hardAddr = runAsync_(a, firstInitArg);

   }
   RemotePlace(int vm_, int place_no_) {
      super(vm_, place_no_);
   }
   public Future runFuture(final Activity.Expr a) {
      return new Future_c();
   }
   public native void shutdown_();
   public void shutdown() {
      if (!Configuration.VM_[vm_].hasShutdownMsgBeenSent) {
         shutdown_();
         Configuration.VM_[vm_].hasShutdownMsgBeenSent = true;
      }
   }
}
