/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2014.
 */

import x10.compiler.Uncounted;
import x10.util.Timer;
import x10.util.Stack;
import x10.compiler.Inline;

/** Manages updates of ghost data for LULESH. */
public final class GhostManager {
    static final class LocalState {
        /** List of neighbors to which data must be sent. */
        public val neighborListSend:Rail[Long];

        /** List of neighbors from which data must be received. */
        public val neighborListRecv:Rail[Long];

        /** 
         * Count of neighbors which have given us update functions this cycle.
         */
        public var neighborsReceivedCount:Long;

        /**
         * Pre-allocated buffers for sending ghost data.
         */
        public val sendBuffers:Rail[Rail[Double]{self!=null}]{self!=null};

        /**
         * Pre-allocated buffers into which neighbors will place their data.
         * Other Place's removeRecvBuffers contain GlobalRails pointing to these.
         */
        public val recvBuffers:Rail[Rail[Double]{self!=null}]{self!=null};

        /**
         * GlobalRails pointing to the recvBuffer entry
         * for each neighbor in neighbotListSend.
         */
        public val remoteRecvBuffers:Rail[GlobalRail[Double]];

        /**
         * The pending update functions recevied from neighbors for the current cycle
         */
        public val updateFunctions:Stack[()=>void];

        /**
         * The current phase of the computation with regard to ghost cell updates.
         * Places are assumed to progress together; in even phases, ghost cells are
         * used; in odd phases, ghost cells are updated.  No place may start phase 
         * P+2 before neighboring places have completed phase P.
         */
        public var currentPhase:Long;

        /**
         * Boundary data received from other places, held for later combination
         * with boundary data computed locally.
         * TODO: Eventually this should be done directly from the recvBuffers.
         */
        var boundaryData:Rail[Rail[Double]];

        public var sendTime:Long = 0;
        public var processTime:Long = 0;
        public var waitTime:Long = 0;

        final def getNeighborNumber(neighborId:Long) {
            for (i in 0..(neighborListRecv.size-1)) {
                if (neighborId == neighborListRecv(i)) {
                    return i;
                }
            }
            throw new IllegalArgumentException(here + " getNeighborNumber for " + neighborId);
        }

        public def this(neighborListSend:Rail[Long], 
                        neighborListRecv:Rail[Long],
                        recvBufferSize:(Long)=>Long) {
            this.neighborListSend = neighborListSend;
            this.neighborListRecv = neighborListRecv;
            this.neighborsReceivedCount = 0;
            this.updateFunctions = new Stack[()=>void]();
            this.currentPhase = 0;
            this.boundaryData = new Rail[Rail[Double]](neighborListRecv.size);
            this.recvBuffers = new Rail[Rail[Double]{self!=null}](neighborListRecv.size, 
                                                      (i:Long) => new Rail[Double](recvBufferSize(i)));
            this.sendBuffers = new Rail[Rail[Double]{self!=null}](neighborListRecv.size, 
                                                      (i:Long) => new Rail[Double](recvBufferSize(i)));
            val dummy = GlobalRail[Double](new Rail[Double](0));
            this.remoteRecvBuffers = new Rail[GlobalRail[Double]](neighborListSend.size, dummy);
        }
    }

    public val localState:PlaceLocalHandle[LocalState];

    /**
     * Create a new GhostManager for ghost updates between all places.
     * @param initNeighborsSend a closure that, when executed at a given place,
     *     returns a list of the neighboring places to which to send
     * @param initNeighborsRecv a closure that, when executed at a given place,
     *     returns a list of the neighboring places from which to receive
     * @param recvBufferSize a closure that, when executed at a given place and
     *     given a neighbor number returns the size (in elements) of the Rail[Double]
     *     that will be needed to receive updates from that neighbor
     */
    public def this(initNeighborsSend:() => Rail[Long], 
                    initNeighborsRecv:() => Rail[Long],
                    recvBufferSize:(Long)=>Long) {
        val ls = PlaceLocalHandle.make[LocalState](Place.places(), () => new LocalState(initNeighborsSend(), 
                                                                                        initNeighborsRecv(),
                                                                                        recvBufferSize));
        this.localState = ls;

        // Initialize remoteRecvBuffers with GlobalRails to target remote recvBuffer
        Place.places().broadcastFlat(()=> {
            val ls2 = ls();
            // The finish is needed to prevent interactions between the specialized
            // finish implementation used by broadcastFlat and the implementation of resilient at.
            finish for (i in ls2.neighborListSend.range) {
              val senderId = here.id;
              ls2.remoteRecvBuffers(i) = at (Place(ls2.neighborListSend(i))) {
                  val ls3 = ls();
                  val bufIdx = ls3.getNeighborNumber(senderId);
                  GlobalRail[Double](ls3.recvBuffers(bufIdx))
              };
            }
        });
    }



    /** 
     * Wait for all ghosts to be received and then return.
     * Used to switch ghost manager phase from sending to using ghost data.
     */
    public final def waitForGhosts() {
        val t1 = Timer.milliTime();
        processUpdateFunctions();
        val t2 = Timer.milliTime();
        localState().processTime += (t2 - t1);
        when (allNeighborsReceived()) {
            val t3 = Timer.milliTime();
            localState().waitTime += (t3 - t2);
            processUpdateFunctions();
            localState().currentPhase++;
            localState().neighborsReceivedCount = 0;
            val t4 = Timer.milliTime();
            localState().processTime += (t4 - t3);
        }
    }

    /** 
     * Wait for all boundary data to be received from neighboring places,
     * and then combine it with boundary data computed at this place.
     * Switch ghost manager phase from sending to using ghost data.
     */
    public final def waitAndCombineBoundaries(domainPlh:PlaceLocalHandle[Domain],
            accessFields:(dom:Domain) => Rail[Rail[Double]],
            sideLength:Long) {
        val t1 = Timer.milliTime();
        processUpdateFunctions();
        val t2 = Timer.milliTime();
        localState().processTime += (t2 - t1);
        when (allNeighborsReceived()) {
            val t3 = Timer.milliTime();
            localState().waitTime += (t3 - t2);
            processUpdateFunctions();
            val boundaryData = localState().boundaryData;
            for (i in 0..(boundaryData.size-1)) {
                if (boundaryData(i) != null) {
                    domainPlh().accumulateBoundaryData(localState().neighborListRecv(i), boundaryData(i), accessFields, sideLength);
                    boundaryData(i) = null;
                }
            }
            localState().currentPhase++;
            localState().neighborsReceivedCount = 0;
            val t4 = Timer.milliTime();
            localState().processTime += (t4 - t3);
        }
    }

    private def allNeighborsReceived():Boolean {
        val received = localState().neighborsReceivedCount;
        val expected = localState().neighborListRecv.size;
        return received == expected;
    }

    private def processUpdateFunctions() {
        val functions = localState().updateFunctions;
        while (true) {
            var f:()=>void = null;
            atomic { if (!functions.isEmpty()) f = functions.pop(); }
            if (f == null) break;
            f();
        }
    }

    private @Inline def postUpdateFunction(posterPhase:Long, updateFunction:()=>void) {
        val state = localState();
        if (posterPhase == state.currentPhase) {
            updateFunction();
            atomic { state.neighborsReceivedCount++; }
        } else {
            atomic {
                state.updateFunctions.push(updateFunction);
                state.neighborsReceivedCount++;
            }
        }
    }

    private def getNeighborNumber(neighborId:Long) = localState().getNeighborNumber(neighborId);

    /**
     * Update boundary data at all neighboring places, overwriting with data
     * from this place's boundary region.
     * @param domainPlh domain data at each place
     * @param accessFields a closure which returns an array of the fields to be
     *   updated as Rail[Rail[Double]]
     * @param sideLength the length of each side of the boundary region
     */
    public def updateBoundaryData(domainPlh:PlaceLocalHandle[Domain], 
                        accessFields:(dom:Domain) => Rail[Rail[Double]],
                        sideLength:Long) {
        val start = Timer.milliTime();
        atomic localState().currentPhase++;
        val sourceId = here.id;
        val sourceDom = domainPlh();
        val phase = localState().currentPhase;
        val neighbors = localState().neighborListSend;
        for (i in 0..(neighbors.size-1)) {
            val boundaryData = sourceDom.gatherBoundaryData(neighbors(i), accessFields, sideLength);
            at(Place(neighbors(i))) @Uncounted async {
                postUpdateFunction(phase, ()=>{ 
                    domainPlh().updateBoundaryData(sourceId, boundaryData, accessFields, sideLength); 
                });
            }
        }
        localState().sendTime += Timer.milliTime() - start;
    }

    /**
     * Update ghost data for plane boundaries at neighboring places with plane
     * boundary data from this place.  Plane ghost data are stored contiguously
     * for each plane *after* all locally-managed data at each place. 
     */
    public def updatePlaneGhosts(domainPlh:PlaceLocalHandle[Domain], 
                        accessFields:(dom:Domain) => Rail[Rail[Double]],
                        sideLength:Long) {
        val start = Timer.milliTime();
        atomic localState().currentPhase++;
        val sourceId = here.id;
        val sourceDom = domainPlh();
        val phase = localState().currentPhase;
        val neighbors = localState().neighborListSend;
        for (i in 0..(neighbors.size-1)) {
            val ghosts = localState().sendBuffers(i);
            sourceDom.gatherGhosts(neighbors(i), accessFields, sideLength, ghosts);
            val target = localState().remoteRecvBuffers(i);
            Rail.uncountedCopy(ghosts, 0, target, 0, ghosts.size, ()=> {
                postUpdateFunction(phase,  ()=>{
                    var ghostOffset:Long = sideLength*sideLength*sideLength;
                    val ghostRegionSize = (sideLength)*(sideLength);
                    val neighborIdx = localState().getNeighborNumber(sourceId);
                    ghostOffset += neighborIdx * ghostRegionSize;
                    domainPlh().updateGhosts(localState().recvBuffers(neighborIdx), 
                                             accessFields, ghostRegionSize, ghostOffset);
                });
            });
        }
        localState().sendTime += Timer.milliTime() - start;
    }

    /**
     * Send boundary data from this place to neighboring places to be combined
     * later by waitAndCombineBoundaries.
     * @see waitAndCombineBoundaries
     */
    public def gatherBoundariesToCombine(domainPlh:PlaceLocalHandle[Domain], 
                        accessFields:(dom:Domain) => Rail[Rail[Double]],
                        sideLength:Long) {
        val start = Timer.milliTime();
        atomic localState().currentPhase++;
        val sourceId = here.id;
        val sourceDom = domainPlh();
        val phase = localState().currentPhase;
        val neighbors = localState().neighborListSend;
        for (i in 0..(neighbors.size-1)) {
            val boundaryData = sourceDom.gatherBoundaryData(neighbors(i), accessFields, sideLength);
            at(Place(neighbors(i))) @Uncounted async {
                postUpdateFunction(phase, ()=>{ 
                    localState().boundaryData(getNeighborNumber(sourceId)) = boundaryData;
                });
            }
        }
        localState().sendTime += Timer.milliTime() - start;
    }
}
