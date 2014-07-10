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

/** Manages ghost cell updates of cell data for LULESH. */
public class GhostManager {
    static class LocalState {
        /** List of neighbors to which data must be sent. */
        public val neighborListSend:Rail[Long];
        /** List of neighbors from which data must be received. */
        public val neighborListRecv:Rail[Long];
        /** 
         * Flag for each neighbor in neighborListRecv indicating whether data
         * have already been received from that neighbor for the current cycle.
        */
        public val neighborsReceived:Rail[Boolean];
        /**
         * The current phase of the computation with regard to ghost cell updates.
         * Places are assumed to progress together; in even phases, ghost cells are
         * used; in odd phases, ghost cells are updated.  No place may start phase 
         * P+2 before neighboring places have completed phase P.
         */
        public var currentPhase:Long;

        public def this(neighborListSend:Rail[Long], neighborListRecv:Rail[Long]) {
            this.neighborListSend = neighborListSend;
            this.neighborListRecv = neighborListRecv;
            this.neighborsReceived = new Rail[Boolean](neighborListRecv.size);
            this.currentPhase = 0;
        }
    }

    private val localState:PlaceLocalHandle[LocalState];

    /**
     * Create a new GhostManager for ghost updates between all places.
     * @param initNeighborsSend a closure that, when executed at a given place,
     *     returns a list of the neighboring places to which to send
     * @param initNeighborsRecv a closure that, when executed at a given place,
     *     returns a list of the neighboring places from which to receive
     */
    public def this(initNeighborsSend:() => Rail[Long], initNeighborsRecv:() => Rail[Long]) {
        this.localState = PlaceLocalHandle.make[LocalState](Place.places(), () => new LocalState(initNeighborsSend(), initNeighborsRecv()));
    }

    /** 
     * Wait for all ghosts to be received and then return.
     * Used to switch ghost manager phase from sending to using ghost data.
     */
    public final def waitForGhosts() {
        when (allNeighborsReceived()) {
            localState().currentPhase++;
            resetNeighborsReceived();
        }
    }

    private def allNeighborsReceived():Boolean {
        val received = localState().neighborsReceived;
        for (i in 0..(received.size-1)) {
            if (! received(i)) return false;
        }
        return true;
    }

    private def setNeighborReceived(neighborId:Long) {
        val neighbors = localState().neighborListRecv;
        val received = localState().neighborsReceived;
        for (i in 0..(neighbors.size-1)) {

            if (neighborId == neighbors(i)) {
                atomic received(i) = true;
                break;
            }
        }
    }

    private def resetNeighborsReceived() {
        val received = localState().neighborsReceived;
        atomic {
            for (i in 0..(received.size-1)) {
                received(i) = false;
            }
        }
    }

    private def getNeighborNumber(neighborId:Long) {
        val neighbors = localState().neighborListRecv;
        for (i in 0..(neighbors.size-1)) {
            if (neighborId == neighbors(i)) {
                return i;
            }
        }
        throw new IllegalArgumentException(here + " getNeighborNumber for " + neighborId);
    }

    /**
     * Update boundary data at all neighboring places, overwriting with data
     * from this place's boundary region.
     */
    public def updateBoundaryData(domainPlh:PlaceLocalHandle[Domain], 
                        accessFields:(dom:Domain) => Rail[Rail[Double]],
                        perEdge:Long) {
        atomic localState().currentPhase++;
        val sourceId = here.id;
        val sourceDom = domainPlh();
        val phase = localState().currentPhase;
        val neighbors = localState().neighborListSend;
        for (i in 0..(neighbors.size-1)) {
            val boundaryData = sourceDom.gatherBoundaryData(neighbors(i), accessFields, perEdge);
            @Uncounted at(Place(neighbors(i))) async {
                when (localState().currentPhase == phase);
                domainPlh().updateBoundaryData(sourceId, boundaryData, accessFields, perEdge);
                setNeighborReceived(sourceId);
            }

        }
    }

    /**
     * Update ghost data for plane boundaries at neighboring places with plane
     * boundary data from this place.  Plane ghost data are stored contiguously
     * for each plane *after* all locally-managed data at each place. 
     */
    public def updatePlaneGhosts(domainPlh:PlaceLocalHandle[Domain], 
                        accessFields:(dom:Domain) => Rail[Rail[Double]],
                        perEdge:Long) {
        atomic localState().currentPhase++;
        val sourceId = here.id;
        val sourceDom = domainPlh();
        val phase = localState().currentPhase;
        val neighbors = localState().neighborListSend;
        for (i in 0..(neighbors.size-1)) {
            val ghosts = sourceDom.gatherGhosts(neighbors(i), accessFields, perEdge);
            @Uncounted at(Place(neighbors(i))) async {
                when (localState().currentPhase == phase);
                var ghostOffset:Long = perEdge*perEdge*perEdge;
                val ghostRegionSize = (perEdge)*(perEdge);
                ghostOffset += getNeighborNumber(sourceId) * ghostRegionSize;
                domainPlh().updateGhosts(ghosts, accessFields, ghostRegionSize, ghostOffset);
                setNeighborReceived(sourceId);
            }
        }
    }

    /**
     * Accumulate (add) boundary data from this place to boundary data at 
     * all neighboring places.
     */
    public def combineBoundaries(domainPlh:PlaceLocalHandle[Domain], 
                        accessFields:(dom:Domain) => Rail[Rail[Double]],
                        perEdge:Long) {
        atomic localState().currentPhase++;
        val sourceId = here.id;
        val sourceDom = domainPlh();
        val phase = localState().currentPhase;
        val neighbors = localState().neighborListSend;
        for (i in 0..(neighbors.size-1)) {
            val boundaryData = sourceDom.gatherBoundaryData(neighbors(i), accessFields, perEdge);
            @Uncounted at(Place(neighbors(i))) async {
                when (localState().currentPhase == phase);
                domainPlh().accumulateBoundaryData(sourceId, boundaryData, accessFields, perEdge);
                setNeighborReceived(sourceId);
            }

        }
    }
}
