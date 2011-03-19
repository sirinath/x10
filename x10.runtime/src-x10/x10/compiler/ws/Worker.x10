package x10.compiler.ws;

import x10.util.Random;
import x10.lang.Lock;
import x10.compiler.Abort;
import x10.compiler.SuppressTransientError;
import x10.compiler.RemoteInvocation;

public final class Worker {
    public val workers:Rail[Worker];
    private val random:Random;

    public val finished:BoxedBoolean;
    public val deque = new Deque();
    public var fifo:Deque = new Deque(); //The first one is used to prevent NPE from steal. will be overriden by X10 Worker.wsfifo;
    public val lock = new Lock();

    public def this(i:Int, workers:Rail[Worker], finished:BoxedBoolean) {
        random = new Random(i + (i << 8) + (i << 16) + (i << 24));
        this.workers = workers;
        this.finished = finished;
    }

    public def notifyStop(){
        finished.value = true;
        //Runtime.println(here +": was notified to stop()");
    }
    
    public def migrate() {
        var k:RegularFrame;
        lock.lock();
        while (!Frame.isNULL(k = Frame.cast[Object,RegularFrame](deque.steal()))) {
//            Runtime.println(k + " migrated by " + this);
            val r = k.remap();
            Runtime.atomicMonitor.lock(); r.ff.asyncs++; Runtime.atomicMonitor.unlock();
            fifo.push(r);
        }
        lock.unlock();

        /*

        //And try process remote msg: add jobs into fifo
        Runtime.wsProcessEvents();
        if(fifo.size() > 0){
            return; //at least there are some jobs in fifo, which the worker could get from find();
            //but other worker may still steal them.
        }
        
        //Try a mini steal from other threads
        k = Frame.cast[Object,RegularFrame](workers(random.nextInt(Runtime.NTHREADS)).fifo.steal());
        if (null != k){
            fifo.push(k);
            return;
        }
        
        if(fifo.size() > 0){
            return; //at least there are some jobs in fifo, which the worker could get from find();
            //but other worker may still steal them.
        }
        
        val i = random.nextInt(Runtime.NTHREADS);
        if (workers(i).lock.tryLock()) {
            k = Frame.cast[Object,RegularFrame](workers(i).deque.steal());
            if (null!= k) {
                val r = k.remap();
                Runtime.atomicMonitor.lock(); r.ff.asyncs++; Runtime.atomicMonitor.unlock();
                fifo.push(r);
            }
            workers(i).lock.unlock();
        }

        */

    }

    public def run() {
        try {
            while (true) {
                val k = find();
                if (Frame.isNULL(k)) {
                    //Runtime.println(here + " :Worker(" + id + ") terminated");
                    return;
                }
                if(k instanceof RegularFrame){
                    //could be a regular frame of local, or remote one. Just call wrapResume
                    val r:RegularFrame = Frame.cast[Object,RegularFrame](k);
                    try {
                        r.wrapResume(this);
                        unstack(r); // top frames are meant to be on the stack
                    } catch (Abort) {}
                    purge(r, r.ff); // needed because we did not stack allocate those frames
                }
                else if(k instanceof FinishFrame){
                    //finish frame, need run the finish frame's unroll
                    val p:FinishFrame = Frame.cast[Object, FinishFrame](k);
                    try{
                        //Runtime.println(here+" :Execute remote finish join");
                        unroll(p);
                    } catch (Abort){}
                }
                else if(k instanceof BoxedBoolean){
                    notifyStop(); //notify the finish flag
                    return; //self return
                }
            }
        } catch (t:Throwable) {
            Runtime.println(here + "Uncaught exception in worker: " + t);
            t.printStackTrace();
        }
    }

    /*
     * The find could return
     * - RegularFrame: continue execution
     * - RemoteMainFrame: start a new remote task
     * - FinishFrame: from remote join
     */
    public def find():Object {
        var k:Object = Frame.NULL[Object]();
//        if (deque.poll() != null) Runtime.println("deque.poll() != null");
    
        //The following sequence decides which type of task has the highest priority
        //right now: 1) cur thread fifo; 2) other thread fifo; 3) other thread deque; 4) remote job 
        //1) cur thread fifo
        k = fifo.steal();
        while (Frame.isNULL(k)) {
            if (finished.value) return Frame.NULL[Object](); // TODO: termination condition
            //2) other thread fifo
            k = workers(random.nextInt(Runtime.NTHREADS)).fifo.steal();
            if (!Frame.isNULL(k)) break;
            //3) other thread deque
            val i = random.nextInt(Runtime.NTHREADS);
            if (workers(i).lock.tryLock()) {
                k = workers(i).deque.steal();
                if (!Frame.isNULL(k)) {
                    val p = Frame.cast[Object,RegularFrame](k);
                    val r = p.remap();
                    // frames from k upto k.ff excluded should be stack-allocated but cannot because of @StackAllocate limitations
                    k = r;
                    Runtime.atomicMonitor.lock(); r.ff.asyncs++; Runtime.atomicMonitor.unlock();
                }
                workers(i).lock.unlock();
            }
            if (!Frame.isNULL(k)) break;
            //4) remote again
            Runtime.wsProcessEvents();    
            k = fifo.steal();
        }
//        Runtime.println(k + " stolen");
        return k;
    }

    public static def purge(var frame:Frame, ff:FinishFrame) {
        while (!Frame.eq(frame, ff)) {
            val up = frame.up;
            if (frame instanceof MainFrame || frame instanceof RootFinish) return;
            Runtime.deallocObject(frame);
            frame = up;
        }
    }

    public def unroll(var frame:Frame) {
        var up:Frame;
        while (true) {
            up = frame.up;
            if (Frame.isNULL(up)) return;
            if (frame instanceof FinishFrame) {
                var asyncs:Int;
                Runtime.atomicMonitor.lock(); asyncs = --Frame.cast[Frame,FinishFrame](frame).asyncs; Runtime.atomicMonitor.unlock();
                if (0 != asyncs) return;
                frame.throwable = MultipleExceptions.make(Frame.cast[Frame,FinishFrame](frame).stack);
            }
            up.wrapBack(this, frame);
            if (!(frame instanceof MainFrame) && !(frame instanceof RootFinish)) {
                Runtime.deallocObject(frame);
            }
            try {
                up.wrapResume(this);
            } catch (t:Abort) {
                if (up instanceof RegularFrame) {
                    purge(up, Frame.cast[Frame,RegularFrame](up).ff);
                }
                throw t;
            }
            frame = up;
        }
    }

    public def unstack(var frame:Frame) {
        var up:Frame;
        while (true) {
            up = frame.up;
            if (Frame.isNULL(up)) return;
            if (frame instanceof FinishFrame) {
                // moving to heap-allocated frames
                unroll(frame);
                return;
            }
            up.wrapBack(this, frame);
            up.wrapResume(this);
            frame = up;
        }
    }

    static def deref[T](root:GlobalRef[Worker]) = (root as GlobalRef[Worker]{home==here})() as T;
    static def derefFrame[T](ffRef:GlobalRef[FinishFrame]) = (ffRef as GlobalRef[FinishFrame]{home==here})() as T;
    static def derefBB[T](root:GlobalRef[BoxedBoolean]) = (root as GlobalRef[BoxedBoolean]{home==here})() as T;
    
    //the frame should be in heap, and could be copied deeply
    public def remoteRunFrame(place:Place, frame:RegularFrame, ff:FinishFrame){
        Runtime.atomicMonitor.lock(); ff.asyncs++; Runtime.atomicMonitor.unlock(); //need add the frame's structure
        val id:Int = place.id;
        val body:()=>void = ()=> {
            Runtime.wsFIFO().push(frame);
        };
        //Runtime.println(here + " :Run Remote job at place:" + id);
        Runtime.wsRunAsync(id, body);
        Runtime.dealloc(body);
        //need clean the heap allocated frame, too.
        //The RemoteMainFrame, the RemoteRootFinish & the RemoteRootFrame
        Runtime.deallocObject(frame.up.up);
        Runtime.deallocObject(frame.up);
        Runtime.deallocObject(frame);
    }

    public def remoteFinishJoin(ffRef:GlobalRef[FinishFrame]) {
        val id:Int = ffRef.home.id;
        val body:()=>void = ()=>{       
            Runtime.wsFIFO().push(derefFrame[FinishFrame](ffRef));
            //Runtime.println(here + " :FF join frame pushed");
        };
        //Runtime.println(here + " :Run Finish Join back to place:" + id);
        Runtime.wsRunCommand(id, body);
        Runtime.dealloc(body);
    }

    /*
     * Notify the remote at's finish flag:boxedBoolean
     * Set it as true. Just execute it
     * No need atomic, so no need push the boxedBoolean to que.
     */
    public static def remoteAtNotify(bbRef:GlobalRef[BoxedBoolean]) {
        val id:Int = bbRef.home.id;
        //need push the frame back to its inque
        //locate the remote worker
        val body:()=>void = ()=>{            
            derefBB[BoxedBoolean](bbRef).value = true;
            //Runtime.println(here + " :At Notify executed");
        };
        //Runtime.println(here + " :Run At Notify back to place:" + id);
        Runtime.wsRunCommand(id, body);
        Runtime.dealloc(body);
    }
    
    public static def allStop(worker:Worker){
        for(var id:Int = 0; id < Place.MAX_PLACES; id++ ) {
            val idd:Int = id;
            val body:()=>void = ()=>{
                //A boxed boolean in fifo means all stop
                Runtime.wsFIFO().push(new BoxedBoolean());
            };
            Runtime.wsRunCommand(idd, body);
            Runtime.dealloc(body);
        }
    }

    public static def initPerPlace() {
        Runtime.wsInit();
        val workers = Rail.make[Worker](Runtime.NTHREADS);
        val finished = new BoxedBoolean();
        for (var i:Int = 0; i<Runtime.NTHREADS; i++) {
            workers(i) = new Worker(i, workers, finished);
        }
        for(var i:Int = 1; i<Runtime.NTHREADS; i++) {
            val ii = i;
            async {
                workers(ii).fifo = Runtime.wsFIFO();
                workers(ii).run();
            }
        }
        workers(0).fifo = Runtime.wsFIFO();
        return workers(0);
    }
    
    public static def main(frame:MainFrame) {
        val worker00 = initPerPlace(); // init place 0 first
        for (var i:Int = 1; i<Place.MAX_PLACES; i++) { // init place >0
            val p = Place.place(i);
            async at(p) initPerPlace().run();
        }
        try {
            frame.fast(worker00); // run main activity
        } catch (t:Abort) {
            worker00.run(); // join the pool
        } catch (t:Throwable) {
            frame.ff.caught(t); // main terminated abnormally
        } finally {
            allStop(worker00);
        }
        frame.ff.rethrow();
        /*
        try {
            frame.fast(worker00);
            //If the app goes here, it means it always in fast path. 
            //We need process the ff.asyncs to make sure it can quit correctly
            var asyncs:Int;
            Runtime.atomicMonitor.lock(); asyncs = --frame.ff.asyncs; Runtime.atomicMonitor.unlock();
            if(asyncs > 0) {
                //Runtime.println(here + " :Worker(0) will start after main's fast..." );
                worker00.run();
            }
        } catch (Abort) {
            //Runtime.println(here + " :Worker(0) will start after main's fast's stolen..." );
            worker00.run();
        } finally {
            //global stop
            //Runtime.println(here+":Fire all stop msg from fast" );
            Worker.allStop(worker00);
            //Runtime.println(here + ":Worker(0) terminated in fast");    
        }
         */
    }
}
