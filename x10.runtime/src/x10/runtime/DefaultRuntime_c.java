package x10.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.Set;
import java.util.HashSet;

import x10.array.DoubleArray;
import x10.array.IntArray;

import x10.array.point_c;
import x10.array.sharedmemory.RegionFactory;
import x10.array.sharedmemory.DistributionFactory;
import x10.array.sharedmemory.IntArray_c;
import x10.array.sharedmemory.DoubleArray_c;

import x10.lang.Activity;
import x10.lang.Object;
import x10.lang.Runtime;
import x10.lang.clock;
import x10.lang.distribution;
import x10.lang.place;
import x10.lang.point;
import x10.lang.region;
import x10.lang.IntReferenceArray;
import x10.lang.intValueArray;
import x10.lang.DoubleReferenceArray;
import x10.lang.doubleArray;
import x10.lang.doubleValueArray;

/**
 * Default implementation of Runtime.
 * 
 * @author Christian Grothoff, Christoph von Praun
 */
public class DefaultRuntime_c 
    extends Runtime
    implements 
        ThreadRegistry,
        ActivityInformationProvider {

    /**
     * At what place is the given thread running (needed to support
     * running multiple Places within the same VM). Note that we're
     * using a weak hash map here since the threadpool code may decide
     * to reduce the number of threads by just letting one exit, and
     * we would in that case not want to hold on to the memory.
     */
    private final WeakHashMap thread2place_ = new WeakHashMap(); // <Thread,Place>

    /**
     * Which activity is the current thread executing?  This one does
     * not have to be a weak hash map since threads are explicitly 
     * removed from the map once they complete a particular activity.
     */
    private final HashMap thread2activity_ = new HashMap(); // <Thread,Activity>
    
    /**
     * What listeners are registered for termination/spawning events
     * for the given activity?  
     */
    private final HashMap activity2asl_ = new HashMap(); // <Activity,Vector<ActivitySpawnListener>>
    
    /**
     * The places of this X10 Runtime (for now a constant set).
     */
    private  Place[] places_;
    private Thread bootThread;
    
    public DefaultRuntime_c() {
    	
    	int pc = Configuration.NUMBER_OF_LOCAL_PLACES;
     	this.places_ = new Place[pc];

    }
    protected void initialize() {
    	int pc = Configuration.NUMBER_OF_LOCAL_PLACES;
    	x10.lang.place.MAX_PLACES = pc;
    
    	
    	for (int i=pc-1;i>=0;i--)
    		places_[i] = new LocalPlace_c(this, this);
    
    }

    /**
     * Run the X10 application.
     */
    protected void run(String[] args) throws Exception {
        // setup the main activity from the client...
        String[] appArgs = Configuration.parseCommandLine(args);
        
        // first: load libraries!
        if (null != Configuration.LOAD) {
            String[] libs = Configuration.LOAD.split(":");
            for (int i=libs.length-1;i>=0;i--)
                System.loadLibrary(libs[i]);
        }
        
        java.lang.Object[] tmp = { args };
        Activity atmp = null;
        try {	
            atmp = (Activity) Class.forName(Configuration.MAIN_CLASS_NAME+"$Main")
                .getDeclaredConstructor(new Class[] { String[].class })
                .newInstance(tmp);
        } catch (Exception e) {
            System.err.println("Could not find default constructor of main class '" + Configuration.MAIN_CLASS_NAME+ "$Main" + "'!");
            throw e;
        }
        final Activity appMain = atmp;
        // ok, some magic with the boot-thread here...
        //Place p0 = (Place) p[0];
        Place p0 = (Place) place.FIRST_PLACE;
        bootThread = Thread.currentThread();
        registerThread(bootThread, p0);
        Activity.Expr boot = new Activity.Expr() {
            public void run() {
                // initialize X10 runtime system
                if (Configuration.SAMPLING_FREQUENCY_MS >= 0)
                    Sampling.boot();

                // now run the actual client app (wrapped in this
                // Activity.Expr since we want to use a Clock to 
                // wait for the main app to exit, but we can't use
                // a clock directly without being a proper activity).
                Clock c = (Clock) factory.getClockFactory().clock();
                c.doNow(appMain);
                c.doNext();
            }
            public Object getResult() {
                return null;
            }
        };        
        
        // run the main app
        p0.runFuture(boot).force(); // use force to wait for termination!
        
        // and now the shutdown sequence!
        for (int i=places_.length-1;i>=0;i--)
            places_[i].shutdown();
        if (Configuration.SAMPLING_FREQUENCY_MS >= 0)
            Sampling.shutdown();
        if (Configuration.DUMP_STATS_ON_EXIT) 
            System.out.println(Sampling.SINGLETON.toString());
    }
    
    public void registerThread(Thread t, Place p) {
		if (p == null)
			throw new NullPointerException();
		thread2place_.put(t, p);
	}
    
    /**
     * Notify the asl via a callback whenever the given activity starts another
     * Activity (via async, future or now).
     */
    public synchronized void registerActivitySpawnListener(Activity i,
                                                           ActivitySpawnListener asl) {
        ArrayList v = (ArrayList) activity2asl_.get(i);
        if (v == null) {
            v = new ArrayList(2);
            activity2asl_.put(i,v);
        }
        v.add(asl);
    }

    /**
     * Notification that an activity completed.
     */
    public synchronized void registerActivityStop(Thread t,
                                                  Activity a) {
        ArrayList v = (ArrayList) activity2asl_.get(a);
        if (v == null) 
            return;
        for (int i=0;i<v.size();i++) {
            ActivitySpawnListener asl = (ActivitySpawnListener) v.get(i);
            asl.notifyActivityTerminated(a);
        }
        activity2asl_.remove(a);
        thread2activity_.remove(t);
    }
    
    /**
     * Notifiation that an activity was started.
     * 
     * @param t the thread that runs the activity
     * @param a the activity that is being run
     * @param i the activity that started a (null for boot/main).
     */
    public synchronized void registerActivityStart(Thread t,
                                                   Activity a,
                                                   Activity i) {
        assert a != i;
        thread2activity_.put(t,a);
        if (i == null)
            return;
        ArrayList v = (ArrayList) activity2asl_.get(i);
        if (v == null) 
            return;
        for (int j=0;j<v.size();j++) {
            ActivitySpawnListener asl = (ActivitySpawnListener) v.get(j);
            asl.notifyActivitySpawn(a, i);
        }
    }

    public synchronized Place currentPlace() {
        if (places_.length == 1)
            return places_[0]; // fast path for simple test environments!
    	Place p = (Place) thread2place_.get(Thread.currentThread());
    	if (p == null)
    		throw new Error("This thread is not an X10 thread!");
    	return p;
    }

    /**
     * this method should not be exposed to x10.lang and 
     * application programmers, because the X10 programming model 
     * does not know such construct (Places are obtained indirectly 
     * through distributions).
     *  
     * @return All places available in this VM.
     */
    public Place[] getPlaces() {
    	// return defensive copy
    	// return (Place[]) places_.clone();
	return places_;
    }
    
    public Factory getFactory() {
    	Factory f = new Factory() {
    		public region.factory getRegionFactory() {
    			return new RegionFactory();
    		}
    		public distribution.factory getDistributionFactory() {
    			return new DistributionFactory();
    		}
    		public point.factory getPointFactory() {
    	
    			return new point_c.factory();
    		}
    		public clock.factory getClockFactory() {
    			return new clock.factory() {
    				public clock clock() {
    					return new Clock_c(DefaultRuntime_c.this);
    				}
    			};
    		}
    		public IntArray.factory getIntArrayFactory() {
    			return new IntArray.factory() {
    				public IntReferenceArray IntReferenceArray(distribution d, int c) {
    					return new IntArray_c( d, c, true);
    				}
    				public IntReferenceArray IntReferenceArray(distribution d, IntArray.pointwiseOp f) {
    					return new IntArray_c( d, f, true);
    				}
    				public intValueArray intValueArray(distribution d, int c) {
    					throw new Error("TODO");
    				}
    				public intValueArray intValueArray(distribution d, IntArray.pointwiseOp f) {
    					throw new Error("TODO");
    				}
    			};
    		}
    		public DoubleArray.factory getDoubleArrayFactory() {
    			return new doubleArray.factory() {
    				public DoubleReferenceArray DoubleReferenceArray(distribution d, double c) {
    					return new DoubleArray_c( d, c, true);
    				}
    				public DoubleReferenceArray DoubleReferenceArray(distribution d, doubleArray.pointwiseOp f) {
    					return new DoubleArray_c( d, f, true);
    				}
    				public doubleValueArray doubleValueArray(distribution d, double c) {
    					throw new Error("TODO");
    				}
    				public doubleValueArray doubleValueArray(distribution d, doubleArray.pointwiseOp f) {
    					throw new Error("TODO");
    				}
    			};
    			
    		}
    		public place.factory getPlaceFactory() {
    			return new place.factory() {
    				public place place(int i ) {
    					int index =( i %  place.MAX_PLACES);
    					if (places_[index] == null) initialize();
    					return places_[index];
    				}
    				/** Return the set of places from place(0) to place(last) (inclusive).
    				 * 
    				 */
    				public Set/*<place>*/ places (int last) {
    					if (places_[0] == null) initialize();
    					Set result = new HashSet();
    					for (int i=0; i <= last % (place.MAX_PLACES); i++)
    						result.add(places_[i]);
    					return result;
    				}
    				public place here() {
    					return currentPlace();
    				}
    			};
    		}
    	};
    	return f;
    	
    }
    
    
    
				      
    /**
     * Get the Activity object that is executing this 
     * method.
     * @return
     */
    public synchronized Activity getCurrentActivity() {
        Activity a = (Activity) thread2activity_.get(Thread.currentThread());
        if (a == null) {
            if (Thread.currentThread() == bootThread)
                return null; // magic 'boot' thread!
            throw new Error("This Thread is not an X10 Thread running X10 code!");
        }
        return a;
    }

    /**
     * At which place is the given activity running?  Note that this particular
     * implementation is not very efficient (since we do not have an explicit
     * mapping in this direction).  The reason is that this method is currently
     * only used for profiling and we thus do not want to have any overhead on 
     * the common path.
     * 
     * @param a 
     * @return null if the activity is not running anywhere
     */
    public synchronized Place getPlaceOfActivity(Activity a) {
        Iterator it=thread2activity_.keySet().iterator();
        while (it.hasNext()) {
            Thread t = (Thread) it.next();
            Activity ta = (Activity) thread2activity_.get(t);
            if (ta == a)
                return (Place) thread2place_.get(t);
        }
        return null;
    }    

} // end of DefaultRuntime_c
