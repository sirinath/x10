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

import x10.regionarray.Region;
import x10.compiler.NonEscaping;
import x10.util.Random;

/**
 * Represents the simulation domain for LULESH as an unstructured hexahedral
 * mesh with two centerings. The element centering (at the center of each
 * hexahedron) stores thermodynamic variables, such as energy and pressure.
 * The nodal centering (where the corners of hexahedrons intersect) stores
 * kinematics values, such as positions and velocities. 
 * @see "I. Karlin et al. LULESH Programming Model and Performance Ports 
    Overview, December 2012, pages 1-17, LLNL-TR-608824."
 */
public class Domain {
    // TODO persistent region, element and node members should be val not var

    // Cutoffs (treat as constants)
    /** energy tolerance */
    public val e_cut = 1.0e-7;
    /** pressure tolerance */
    public val p_cut = 1.0e-7;
    /** viscosity tolerance */
    public val q_cut = 1.0e-7;
    /** relative volume tolerance */
    public val v_cut = 1.0e-10;
    /** velocity tolerance */
    public val u_cut = 1.0e-7;

    // Other constants (usually settable, but hardcoded in this proxy app)
    /** hourglass control */
    public val hgcoef = 3.0;
    public val ss4o3 = 4.0 / 3.0;
    /** excessive q indicator */
    public val qstop = 1.0e+12;
    public val monoq_max_slope = 1.0;
    public val monoq_limiter_mult = 2.0;
    /** linear term coef for q */
    public val qlc_monoq = 0.5;
    /** quadratic term coef for q */
    public val qqc_monoq = 2.0 / 3.0;
    public val qqc = 2.0;
    public val eosvmax = 1.0e+9;
    public val eosvmin = 1.0e-9;
    /** pressure floor */
    public val pmin = 0.0;
    /** energy floor */
    public val emin = -1.0e+15;
    /** maximum allowable volume change */
    public val dvovmax = 0.1;
    /** reference density */
    public val refdens = 1.0;

    // These can be changed (requires recompile) if you want to run
    // with a fixed timestep, or to a different end time, but it's
    // probably easier/better to just run a fixed number of timesteps
    // using the -i flag in 2.x

    /** fixed time increment */
    public val dtfixed = -1.0e-6; // Negative means use courant condition
    /** maximum allowable time increment */
    public val dtmax = 1.0e-2;
    /** end time for simulation */
    public val stopTime = 1.0e-2; // *Real_t(edgeElems*tp/45.0);

    public val sizeX:Long;
    public val sizeY:Long;
    public val sizeZ:Long;
    public val numElem:Long;
    public val numNode:Long;

    /** Courant time constraint */
    public var dtcourant:Double;
    /** volume change time constraint */
    public var dthydro:Double;
    /** variable time increment lower bound */
    public var deltatimemultlb:Double;
    /** variable time increment upper bound */
    public var deltatimemultub:Double;

    /** iteration count for simulation */
    public var cycle:Int;
    /** current time */
    public var time:Double;
    /** variable time increment */
    public var deltatime:Double;

    /** Elapsed time for computation TODO remove this from Domain */
    public var elapsedTimeMillis:Long;

    /** length of cube mesh along side */
    public val nx:Long;

    public val maxPlaneSize:Long;
    public val maxEdgeSize:Long;

    /** Location of this domain in the grid decomposition TODO use DistArray */
    public val loc:DomainLoc;

    // Node-centered kinematic variables
    // positions
    public var x:Rail[Double];
    public var y:Rail[Double];
    public var z:Rail[Double];
    // velocities
    public var xd:Rail[Double];
    public var yd:Rail[Double];
    public var zd:Rail[Double];
    // accelerations
    public var xdd:Rail[Double];
    public var ydd:Rail[Double];
    public var zdd:Rail[Double];
    // forces
    public var fx:Rail[Double];
    public var fy:Rail[Double];
    public var fz:Rail[Double];
    /** mass */
    public var nodalMass:Rail[Double];
    // symmetry plane nodesets
    public var symmX:Rail[Long];
    public var symmY:Rail[Long];
    public var symmZ:Rail[Long];

    // Element-centered thermodynamic variables
    /** Energy */
    public var e:Rail[Double];
    /** Pressure */
    public var p:Rail[Double];
    /** Artificial viscosity */
    public var q:Rail[Double];
    /** Linear term for q */
    public var ql:Rail[Double];
    /** Quadratic term for q */
    public var qq:Rail[Double];
    /** reference volume */
    public var volo:Rail[Double];
    /** Relative volume */
    public var v:Rail[Double];
    /** Relative volume change */
    public var delv:Rail[Double];
    /** volume derivative over volume */
    public var vdov:Rail[Double];
    /** Element characteristic length */
    public var arealg:Rail[Double];
    /** "sound speed" */
    public var ss:Rail[Double];
    /** Element mass */
    public var elemMass:Rail[Double];


    // Region information

    /** Number of distinct regions */
    public val numReg:Int;
    /** Extra cost of more expensive regions */
    public val cost:Int;
    /** Size of region sets */
    public var regElemSize:Rail[Long];
    /** Region (material) number per domain element */
    public var regNumList:Rail[Long];
    /** Region indexset */
    public var regElemList:Rail[Rail[Long]];

    /** elemToNode connectivity */
    public var nodeList:Rail[Long];

    /* element connectivity across each face */
    public var lxim:Rail[Long];
    public var lxip:Rail[Long];
    public var letam:Rail[Long];
    public var letap:Rail[Long];
    public var lzetam:Rail[Long];
    public var lzetap:Rail[Long];

    /** symmetry/free-surface flags for each element face */
    public var elemBC:Rail[Int];

    /* Diagonal terms of deviatoric strain -- temporary */
    public var dxx:Rail[Double];
    public var dyy:Rail[Double];
    public var dzz:Rail[Double];

    /* Position gradient -- temporary */
    public var delv_xi:Rail[Double];
    public var delv_eta:Rail[Double];
    public var delv_zeta:Rail[Double];

    /* Velocity gradient -- temporary */
    public var delx_xi:Rail[Double];
    public var delx_eta:Rail[Double];
    public var delx_zeta:Rail[Double];

    // Stuff needed for boundary conditions
    // 2 BCs on each of 6 hexahedral faces (12 bits)
    static val XI_M        :Int = 0x00007n;
    static val XI_M_SYMM   :Int = 0x00001n;
    static val XI_M_FREE   :Int = 0x00002n;
    static val XI_M_COMM   :Int = 0x00004n;

    static val XI_P        :Int = 0x00038n;
    static val XI_P_SYMM   :Int = 0x00008n;
    static val XI_P_FREE   :Int = 0x00010n;
    static val XI_P_COMM   :Int = 0x00020n;

    static val ETA_M       :Int = 0x001c0n;
    static val ETA_M_SYMM  :Int = 0x00040n;
    static val ETA_M_FREE  :Int = 0x00080n;
    static val ETA_M_COMM  :Int = 0x00100n;

    static val ETA_P       :Int = 0x00e00n;
    static val ETA_P_SYMM  :Int = 0x00200n;
    static val ETA_P_FREE  :Int = 0x00400n;
    static val ETA_P_COMM  :Int = 0x00800n;

    static val ZETA_M      :Int = 0x07000n;
    static val ZETA_M_SYMM :Int = 0x01000n;
    static val ZETA_M_FREE :Int = 0x02000n;
    static val ZETA_M_COMM :Int = 0x04000n;

    static val ZETA_P      :Int = 0x38000n;
    static val ZETA_P_SYMM :Int = 0x08000n;
    static val ZETA_P_FREE :Int = 0x10000n;
    static val ZETA_P_COMM :Int = 0x20000n;

    public def symmXempty():Boolean = (symmX == null);
    public def symmYempty():Boolean = (symmY == null);
    public def symmZempty():Boolean = (symmZ == null);

    public def this(nx:Long, nr:Int, balance:Int, cost:Int, placesPerSide:Int) {
        this.nx = nx;
        this.numReg = nr;
        this.cost = cost;
        val edgeElems = nx;
        val edgeNodes = edgeElems+1;
        val loc = DomainLoc.make(here.id, placesPerSide);
        this.loc = loc;

        // Initialize Sedov Mesh

        // construct a uniform box for this processor

        this.sizeX = edgeElems;
        this.sizeY = edgeElems;
        this.sizeZ = edgeElems;
        this.numElem = edgeElems*edgeElems*edgeElems;
        this.numNode = edgeNodes*edgeNodes*edgeNodes;

        // TODO cache alignment - required?
        this.maxEdgeSize = Math.max(sizeX, Math.max(sizeY, sizeZ)) + 1;
        this.maxPlaneSize = maxEdgeSize*maxEdgeSize;

        allocateElemPersistent(numElem);

        allocateNodePersistent(numNode);

        setupBoundaryNodesets(edgeNodes);

        buildMesh(nx, edgeNodes, edgeElems);

        createRegionIndexSets(nr, balance);

        setupSymmetryPlanes(edgeNodes);

        setupElementConnectivities(edgeElems);

        setupBoundaryConditions(edgeElems);

        // Initial conditions
        deltatimemultlb = 1.1;
        deltatimemultub = 1.2;
        dtcourant = 1.0e+20;
        dthydro   = 1.0e+20;
        time  = 0.0;
        cycle = 0n;

        // initialize field data 
        for (i in 0..(numElem-1)) {
            val x_local = new Rail[Double](8);
            val y_local = new Rail[Double](8);
            val z_local = new Rail[Double](8);
            val elemStart = 8*i;
            for(lnode in 0..7) {
                val gnode = nodeList(elemStart+lnode);
                x_local(lnode) = x(gnode);
                y_local(lnode) = y(gnode);
                z_local(lnode) = z(gnode);
            }

            // volume calculations
            val volume = Lulesh.calcElemVolume(x_local, y_local, z_local);
            volo(i) = volume;
            elemMass(i) = volume;
            for (j in 0..7) {
                val idx = nodeList(elemStart+j);
                nodalMass(idx) += volume / 8.0;
            }
        }

        // deposit initial energy
        // An energy of 3.948746e+7 is correct for a problem with
        // 45 zones along a side - we need to scale it
        val ebase = 3.948746e+7;
        val scale = (nx * loc.tp) / 45.0;
        val einit = ebase*scale*scale*scale;
        if (loc.isFirstDomain()) {
            // Dump into the first zone (which we know is in the corner)
            // of the domain that sits at the origin
            e(0) = einit;
        }
        // set initial deltatime based on analytic CFL calculation
        deltatime = (0.5 * Math.cbrt(volo(0))) / Math.sqrt(2.0 * einit);
    }

    /** 
     * Return the region of ghost elements for this place to receive from
     * the given neighboring place.
     * @neighborId the id of the neighboring domain in the grid
     * @edgeMax the maximum number of values per dimension of the boundary
     */
    public def getBoundaryRegion(neighborId:Long, edgeMax:Long) {
        val neighborLoc = DomainLoc.make(neighborId, loc.tp);
        val xDiff = neighborLoc.x - loc.x;
        val yDiff = neighborLoc.y - loc.y;
        val zDiff = neighborLoc.z - loc.z;

        val xRange:LongRange;
        if (xDiff == -1n) {
            xRange = 0..0;
        } else if (xDiff == 1n) {
            xRange = edgeMax..edgeMax;
        } else {
            xRange = 0..edgeMax;
        }

        val yRange:LongRange;
        if (yDiff == -1n) {
            yRange = 0..0;
        } else if (yDiff == 1n) {
            yRange = edgeMax..edgeMax;
        } else {
            yRange = 0..edgeMax;
        }

        val zRange:LongRange;
        if (zDiff == -1n) {
            zRange = 0..0;
        } else if (zDiff == 1n) {
            zRange = edgeMax..edgeMax;
        } else {
            zRange = 0..edgeMax;
        }

        return Region.makeRectangular(xRange, yRange, zRange);
    }

    /** 
     * Return the region of boundary elements to send from this place to the
     * given neighboring place.
     * @neighborId the id of the neighboring domain in the grid
     * @edgeMax the maximum number of values per dimension of the boundary
     */
    public def getGhostRegionToSend(neighborId:Long, edgeMax:Long) {
        val neighborLoc = DomainLoc.make(neighborId, loc.tp);
        val xDiff = neighborLoc.x - loc.x;
        val yDiff = neighborLoc.y - loc.y;
        val zDiff = neighborLoc.z - loc.z;

        val xRange:LongRange;
        if (xDiff == -1n) {
            xRange = 1..1;
        } else if (xDiff == 1n) {
            xRange = (edgeMax-1)..(edgeMax-1);
        } else {
            xRange = 1..(edgeMax-1);
        }

        val yRange:LongRange;
        if (yDiff == -1n) {
            yRange = 1..1;
        } else if (yDiff == 1n) {
            yRange = (edgeMax-1)..(edgeMax-1);
        } else {
            yRange = 1..(edgeMax-1);
        }

        val zRange:LongRange;
        if (zDiff == -1n) {
            zRange = 1..1;
        } else if (zDiff == 1n) {
            zRange = (edgeMax-1)..(edgeMax-1);
        } else {
            zRange = 1..(edgeMax-1);
        }

        return Region.makeRectangular(xRange, yRange, zRange);
    }

    public def gatherBoundaryData(destId:Long, 
                accessFields:(dom:Domain) => Rail[Rail[Double]],
                perEdge:Long):Rail[Double] {
        val fields = accessFields(this);
        val boundaryRegion = getBoundaryRegion(destId, perEdge-1);
        val transfer = new Rail[Double](boundaryRegion.size()*fields.size);

        var idx:Long = 0;
        for (field in fields) {
            for (z in boundaryRegion.min(2)..boundaryRegion.max(2)) {
                for (y in boundaryRegion.min(1)..boundaryRegion.max(1)) {
                    for (x in boundaryRegion.min(0)..boundaryRegion.max(0)) {
                        transfer(idx++) = field(x + y*perEdge + z*perEdge*perEdge);
                    }
                }
            }
        }

        return transfer;
    }

    public def gatherGhosts(destId:Long, 
                accessFields:(dom:Domain) => Rail[Rail[Double]],
                perEdge:Long):Rail[Double] {
        val fields = accessFields(this);
        val boundaryRegion = getGhostRegionToSend(destId, perEdge-1);
        val transfer = new Rail[Double](boundaryRegion.size()*fields.size);
        var idx:Long = 0;
        for (field in fields) {
            for (z in boundaryRegion.min(2)..boundaryRegion.max(2)) {
                for (y in boundaryRegion.min(1)..boundaryRegion.max(1)) {
                    for (x in boundaryRegion.min(0)..boundaryRegion.max(0)) {
                        transfer(idx++) = field(x + y*perEdge + z*perEdge*perEdge);
                    }
                }
            }
        }

        return transfer;
    }

    public def updateBoundaryData(sourceId:Long, data:Rail[Double], 
                accessFields:(dom:Domain) => Rail[Rail[Double]],
                perEdge:Long) {
        val fields = accessFields(this);
        val boundaryRegion = getBoundaryRegion(sourceId, perEdge-1);
        var idx:Long = 0;
        for (field in fields) {
            for (z in boundaryRegion.min(2)..boundaryRegion.max(2)) {
                for (y in boundaryRegion.min(1)..boundaryRegion.max(1)) {
                    for (x in boundaryRegion.min(0)..boundaryRegion.max(0)) {
                        field(x + y*perEdge + z*perEdge*perEdge) = data(idx++);
                    }
                }
            }
        }
    }


    public def accumulateBoundaryData(sourceId:Long, data:Rail[Double], 
                accessFields:(dom:Domain) => Rail[Rail[Double]],
                perEdge:Long) {
        val fields = accessFields(this);
        val boundaryRegion = getBoundaryRegion(sourceId, perEdge-1);
        var idx:Long = 0;
        for (field in fields) {
            for (z in boundaryRegion.min(2)..boundaryRegion.max(2)) {
                for (y in boundaryRegion.min(1)..boundaryRegion.max(1)) {
                    for (x in boundaryRegion.min(0)..boundaryRegion.max(0)) {
                        field(x + y*perEdge + z*perEdge*perEdge) += data(idx++);
                    }
                }
            }
        }
    }

    /**
     * Update ghost data for the given fields.  Ghost data are stored
     * contiguously in planes for each neighboring place after locally
     * managed data for each field.
     */
    public def updateGhosts(data:Rail[Double], 
                accessFields:(dom:Domain) => Rail[Rail[Double]],
                ghostRegionSize:Long, ghostOffset:Long) {
        val fields = accessFields(this);
        var idx:Long = 0;
        for (j in 0..(fields.size-1)) {
            val field = fields(j);
            for (i in 0..(ghostRegionSize-1)) {
                field(ghostOffset+i) = data(idx++);
            }
        }
    }

    private @NonEscaping def buildMesh(nx:Long, edgeNodes:Long, edgeElems:Long) {
        // TODO use Array_2
        nodeList = new Rail[Long](8*numElem);

        val meshEdgeElems = loc.tp*nx;

        // initialize nodal coordinates 
        var nidx:Long = 0;
        var tz:Double = 1.125 * (loc.z * nx) / meshEdgeElems;
        for (edgeZ in 0..(edgeNodes-1)) {
            var ty:Double = 1.125 * (loc.y * nx) / meshEdgeElems;
            for (edgeY in 0..(edgeNodes-1)) {
                var tx:Double = 1.125 * (loc.x * nx) / meshEdgeElems;
                for (edgeX in 0..(edgeNodes-1)) {
                    x(nidx) = tx;
                    y(nidx) = ty;
                    z(nidx) = tz;
                    ++nidx;
                    // tx += ds; // may accumulate roundoff... 
                    tx = 1.125 * (loc.x*nx + edgeX + 1) / meshEdgeElems;
                }
                // ty += ds;  // may accumulate roundoff... 
                ty = 1.125 * (loc.y*nx + edgeY + 1) / meshEdgeElems;
            }
            // tz += ds;  // may accumulate roundoff... 
            tz = 1.125 * (loc.z*nx + edgeZ + 1) / meshEdgeElems;
        }


        // embed hexehedral elements in nodal point lattice 
        var zidx:Long = 0;
        nidx = 0;
        for (edgeZ in 0..(edgeElems-1)) {
            for (edgeY in 0..(edgeElems-1)) {
                for (edgeX in 0..(edgeElems-1)) {
                    nodeList(zidx*8+0) = nidx                                      ;
                    nodeList(zidx*8+1) = nidx                                   + 1;
                    nodeList(zidx*8+2) = nidx                       + edgeNodes + 1;
                    nodeList(zidx*8+3) = nidx                       + edgeNodes    ;
                    nodeList(zidx*8+4) = nidx + edgeNodes*edgeNodes                ;
                    nodeList(zidx*8+5) = nidx + edgeNodes*edgeNodes             + 1;
                    nodeList(zidx*8+6) = nidx + edgeNodes*edgeNodes + edgeNodes + 1;
                    nodeList(zidx*8+7) = nidx + edgeNodes*edgeNodes + edgeNodes    ;
                    ++zidx;
                    ++nidx;
                }
                ++nidx;
            }
            nidx += edgeNodes;
        }
    }

    /** Allocate element-centered quantities */
    private @NonEscaping def allocateElemPersistent(numElem:Long):void {
        // elem connectivities through face
        lxim = new Rail[Long](numElem);
        lxip = new Rail[Long](numElem);
        letam = new Rail[Long](numElem);
        letap = new Rail[Long](numElem);
        lzetam = new Rail[Long](numElem);
        lzetap = new Rail[Long](numElem);

        e = new Rail[Double](numElem);
        p = new Rail[Double](numElem);

        q = new Rail[Double](numElem);
        ql = new Rail[Double](numElem);
        qq = new Rail[Double](numElem);

        // Note - v initializes to 1.0, not 0.0!
        v = new Rail[Double](numElem, 1.0);

        volo = new Rail[Double](numElem);
        delv = new Rail[Double](numElem);
        vdov = new Rail[Double](numElem);

        arealg = new Rail[Double](numElem);

        ss = new Rail[Double](numElem);
        elemMass = new Rail[Double](numElem);
    }

    /** Allocate node-centered quantities */
    private @NonEscaping def allocateNodePersistent(numNode:Long):void {
        x = new Rail[Double](numNode);  // coordinates
        y = new Rail[Double](numNode);
        z = new Rail[Double](numNode);

        xd = new Rail[Double](numNode); // velocities
        yd = new Rail[Double](numNode);
        zd = new Rail[Double](numNode);

        xdd = new Rail[Double](numNode); // accelerations
        ydd = new Rail[Double](numNode);
        zdd = new Rail[Double](numNode);

        fx = new Rail[Double](numNode); // forces
        fy = new Rail[Double](numNode);
        fz = new Rail[Double](numNode);

        nodalMass = new Rail[Double](numNode); // mass
    }

    /** 
     * Setup region index sets. For now, these are constant sized
     * throughout the run, but could be changed every cycle to 
     * simulate effects of ALE on the Lagrange solver.
     * @param balance the balance parameter used to determine the relative
     *    weights of all regions, sourced from the -b command line parameter
     */
    private @NonEscaping def createRegionIndexSets(nr:Int, balance:Int) {
        // TODO everything
        regNumList = new Rail[Long](numElem);
        regElemSize = new Rail[Long](nr);
        regElemList = new Rail[Rail[Long]](nr);

        val rand = new Random(here.id);

        var nextIndex:Long = 0;
        if (numReg == 1n) {
            // Fill out the regNumList with material numbers, which are always
            // the region index plus one 
            while (nextIndex < numElem) {
                this.regNumList(nextIndex) = 1;
                nextIndex++;
            }
            regElemSize(0) = 0;
        } else {
            // distribute the elements

            // Determine the relative weights of all the regions.
            var lastReg:Long = -1;
            var costDenominator:Int = 0n; // Total sum of all regions' weights
            val regBinEnd = new Rail[Int](numReg); // Chance of hitting a given region is (regBinEnd(i) - regBinEnd(i-1))/costDenominator
            for (i in 0..(numReg-1)) {
                regElemSize(i) = 0;
                costDenominator += Math.pow((i+1), balance);  
                regBinEnd(i) = costDenominator;  
            }
            // Until all elements are assigned
            while (nextIndex < numElem) {
                // pick the region
                var regionVar:Long = rand.nextInt() % costDenominator;
                var i:Long = 0;
                while(regionVar >= regBinEnd(i))
                    i++;
                // Rotate the regions based on place ID.  Rotation is 
                // here.id % NumRegions . This makes each domain have a
                // different region with the highest representation
                var regionNum:Long = ((i + here.id) % numReg) + 1;
                // make sure we don't pick the same region twice in a row
                while(regionNum == lastReg) {
                    regionVar = rand.nextInt() % costDenominator;
                    i = 0;
                    while(regionVar >= regBinEnd(i))
                        i++;
                    regionNum = ((i + here.id) % numReg) + 1;
                }

                // Pick the bin size of the region and determine the number of elements.
                val binSize = rand.nextInt() % 1000;
                var elements:Long;
                if (binSize < 773) {
                    elements = rand.nextInt() % 15 + 1;
                } else if (binSize < 937) {
                    elements = rand.nextInt() % 16 + 16;
                } else if (binSize < 970) {
                    elements = rand.nextInt() % 32 + 32;
                } else if (binSize < 974) {
                    elements = rand.nextInt() % 64 + 64;
                } else if (binSize < 978) {
                    elements = rand.nextInt() % 128 + 128;
                } else if (binSize < 981) {
                    elements = rand.nextInt() % 256 + 256;
                } else {
                    elements = rand.nextInt() % 1537 + 512;
                }
                val runTo = elements + nextIndex;
                // Store the elements.  If we hit the end before we run out of elements then just stop.
                while (nextIndex < runTo && nextIndex < numElem) {
                    this.regNumList(nextIndex) = regionNum;
                    nextIndex++;
                }
                lastReg = regionNum;
            }
        }
        // Convert regNumList to region index sets
        // First, count size of each region 
        for (i in 0..(numElem-1)) {
            val r = this.regNumList(i)-1;  // region index == regnum-1
            regElemSize(r)++;
        }
        // Second, allocate each region index set
        for (i in 0..(numReg-1)) {
            regElemList(i) = new Rail[Long](regElemSize(i));
            regElemSize(i) = 0;
        }
        // Third, fill index sets
        for (i in 0..(numElem-1)) {
            val r = regNumList(i)-1;       // region index == regnum-1
            val regndx = regElemSize(r)++; // Note increment
            regElemList(r)(regndx) = i;
        }
    }

    private @NonEscaping def setupBoundaryNodesets(edgeNodes:Long) {
        if (loc.x == 0n)
            symmX = new Rail[Long](edgeNodes*edgeNodes);
        if (loc.y == 0n)
            symmY = new Rail[Long](edgeNodes*edgeNodes);
        if (loc.z == 0n)
            symmZ = new Rail[Long](edgeNodes*edgeNodes);
    }

    /** Setup symmetry nodesets */
    private @NonEscaping def setupSymmetryPlanes(edgeNodes:Long) {
        var nidx:Long = 0;
        for (i in 0..(edgeNodes-1)) {
            val zOffset = i*edgeNodes*edgeNodes;
            val yOffset   = i*edgeNodes;
            for (j in 0..(edgeNodes-1)) {
                if (loc.z == 0n) {
                    symmZ(nidx) = yOffset + j;
                }
                if (loc.y == 0n) {
                    symmY(nidx) = zOffset + j;
                }
                if (loc.x == 0n) {
                    symmX(nidx) = zOffset + j*edgeNodes;
                }
                ++nidx;
            }
        }
    }

    /** Setup element connectivities */
    private @NonEscaping def setupElementConnectivities(edgeElems:Long) {
        lxim(0) = 0;
        for (i in 1..(numElem-1)) {
            lxim(i)   = i-1;
            lxip(i-1) = i;
        }
        lxip(numElem-1) = numElem-1;

        for (i in 0..(edgeElems-1)) {
            letam(i) = i; 
            letap(numElem-edgeElems+i) = numElem-edgeElems+i;
        }
        for (i in edgeElems..(numElem-1)) {
            letam(i) = i-edgeElems;
            letap(i-edgeElems) = i;
        }

        for (i in 0..(edgeElems*edgeElems-1)) {
            lzetam(i) = i;
            lzetap(numElem-edgeElems*edgeElems+i) = numElem-edgeElems*edgeElems+i;
        }
        for (i in (edgeElems*edgeElems)..(numElem-1)) {
            lzetam(i) = i - edgeElems*edgeElems;
            lzetap(i-edgeElems*edgeElems) = i;
        }
    }

    /** Setup symmetry planes and free surface boundary arrays */
    private @NonEscaping def setupBoundaryConditions(edgeElems:Long) {
        val xMin = (loc.y != 0n);
        val xMax = (loc.y != loc.tp-1n);
        val yMin = (loc.x != 0n);
        val yMax = (loc.x != loc.tp-1n);
        val zMin = (loc.z != 0n);
        val zMax = (loc.z != loc.tp-1n);

        // offsets to ghost locations
        val ghostIdx = new Rail[Long](6, Int.MIN_VALUE);

        var pidx:Long = numElem;
        if (zMin) {
            ghostIdx(0) = pidx;
            pidx += sizeX*sizeY;
        }

        if (zMax) {
            ghostIdx(1) = pidx;
            pidx += sizeX*sizeY;
        }

        if (xMin) {
            ghostIdx(2) = pidx;
            pidx += sizeX*sizeZ;
        }

        if (xMax) {
            ghostIdx(3) = pidx;
            pidx += sizeX*sizeZ;
        }

        if (yMin) {
            ghostIdx(4) = pidx;
            pidx += sizeY*sizeZ;
        }

        if (yMax) {
            ghostIdx(5) = pidx;
        }

        // symmetry plane or free surface BCs
        elemBC = new Rail[Int](numElem);
        for (i in 0..(edgeElems-1)) {
            val zOffset = i*edgeElems*edgeElems;
            val yOffset   = i*edgeElems;
            for (j in 0..(edgeElems-1)) {
                if (loc.z == 0n) {
                    elemBC(yOffset+j) |= ZETA_M_SYMM;
                } else {
                    elemBC(yOffset+j) |= ZETA_M_COMM;
                    lzetam(yOffset+j) = ghostIdx(0) + yOffset + j;
                }

                if (loc.z == loc.tp-1n) {
                    elemBC(yOffset+j+numElem-edgeElems*edgeElems) |=
                        ZETA_P_FREE;
                } else {
                    elemBC(yOffset+j+numElem-edgeElems*edgeElems) |=
                        ZETA_P_COMM;
                    lzetap(yOffset+j+numElem-edgeElems*edgeElems) =
                    ghostIdx(1) + yOffset + j;
                }

                if (loc.y == 0n) {
                    elemBC(zOffset+j) |= ETA_M_SYMM;
                } else {
                    elemBC(zOffset+j) |= ETA_M_COMM;
                    letam(zOffset+j) = ghostIdx(2) + yOffset + j;
                }

                if (loc.y == loc.tp-1n) {
                    elemBC(zOffset+j+edgeElems*edgeElems-edgeElems) |= 
                        ETA_P_FREE;
                } else {
                    elemBC(zOffset+j+edgeElems*edgeElems-edgeElems) |= 
                        ETA_P_COMM;
                    letap(zOffset+j+edgeElems*edgeElems-edgeElems) =
                    ghostIdx(3) +  yOffset + j;
                }

                if (loc.x == 0n) {
                    elemBC(zOffset+j*edgeElems) |= XI_M_SYMM;
                } else {
                    elemBC(zOffset+j*edgeElems) |= XI_M_COMM;
                    lxim(zOffset+j*edgeElems) = ghostIdx(4) + yOffset + j;
                }

                if (loc.x == loc.tp-1n) {
                    elemBC(zOffset+j*edgeElems+edgeElems-1) |= XI_P_FREE;
                } else {
                    elemBC(zOffset+j*edgeElems+edgeElems-1) |= XI_P_COMM;
                    lxip(zOffset+j*edgeElems+edgeElems-1) =
                        ghostIdx(5) + yOffset + j;
                }
            }
        }
    }

    public def allocateGradients(allElem:Long) {
        // Position gradients
        delx_xi = new Rail[Double](numElem);
        delx_eta = new Rail[Double](numElem);
        delx_zeta = new Rail[Double](numElem);

        // Velocity gradients
        delv_xi = new Rail[Double](allElem);
        delv_eta = new Rail[Double](allElem);
        delv_zeta = new Rail[Double](allElem);
    }

    public def deallocateGradients() {
        delx_zeta = null;
        delx_eta = null;
        delx_xi = null;

        delv_zeta = null;
        delv_eta = null;
        delv_xi = null;
    }

    public def allocateStrains() {
        dxx = new Rail[Double](numElem);
        dyy = new Rail[Double](numElem);
        dzz = new Rail[Double](numElem);
    }

    public def deallocateStrains() {
      dzz = null;
      dyy = null;
      dxx = null;
    }
}
// vim:tabstop=4:shiftwidth=4:expandtab
