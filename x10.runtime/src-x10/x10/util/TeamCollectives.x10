/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2013.
 */

package x10.util;

import x10.compiler.Native;
import x10.util.concurrent.AtomicInteger;
import x10.compiler.Uncounted;

/** Interface to low level collective operations.  A team is a collection of
 * activities that work together by simultaneously doing 'collective
 * operations', expressed as calls to methods in the Team struct.  All methods
 * are blocking operations.
 */
public struct TeamCollectives {

    private static struct DoubleIdx(value:Double, idx:Int) {}
        
    /** A team that has one member at each place. */
    public static val WORLD = TeamCollectives(0, PlaceGroup.WORLD);
    
    /** The underlying representation of a team's identity. */
    private val id:Int;
    public def id() = id;
    private static val state:GrowableRail[LocalTeamState] = new GrowableRail[LocalTeamState]();
    
    private def this (id:Int, places:PlaceGroup) {
    	this.id = id;
    	if (!nativeSupportsCollectives()) {
    		//Runtime.println(here+":team"+id+" Creating WORLD team using local implementation");
    		if (state.capacity() <= id) // TODO move this check into the GrowableRail.grow() method
    			state.grow(id+1);
    		state(id) = new LocalTeamState(places, id);
    		state(id).init();
    	}
    	//else Runtime.println(here+":team"+id+" Creating WORLD team using native collectives");
    }

    /** Create a team by defining the place where each member lives.  This would usually be called before creating an async for each member of the team.
     * @param places The place of each member in the team
     */
    public def this (places:PlaceGroup) {
        if (nativeSupportsCollectives()) {
        	val result = new Rail[Int](1);
        	val count = places.size();
        	val placeRail = new Rail[Place](places.size());
        	for (var i:Int=0; i<count; i++)
        		placeRail(i) = places(i);
        	finish nativeMake(placeRail, count, result);
        	this.id = result(0);
        }
        else {
        	atomic {
        		this.id = state.size() as Int;
        		state.add(new LocalTeamState(places, this.id));
        	}
            state(this.id).init();
        }
    }

    private static def nativeMake (places:Rail[Place], count:Int, result:Rail[Int]) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeMake(places, count, result);")
    	@Native("c++", "x10rt_team_new(count, (x10rt_place*)places->raw, x10aux::coll_handler2, x10aux::coll_enter2(result->raw));") {}
    }
    
    @Native("java", "x10.x10rt.X10RT.supportsCollectives()")
    private static def nativeSupportsCollectives () : Boolean {
	   return Boolean.TRUE; // c++ always has a native implementation, for now
    }

    /** Returns the number of elements in the team.
     */
    public def size () : Int {
    	if (nativeSupportsCollectives ())
    		return nativeSize(id);
    	else
    		return state(id).places.size();
    }

    private static def nativeSize (id:Int) : Int {
        @Native("java", "return x10.x10rt.TeamSupport.nativeSize(id);")
        @Native("c++", "return (x10_int)x10rt_team_sz(id);") { return -1; }
    }

    /** Blocks until all team members have reached the barrier.
     * @param role Our role in this collective operation
     */
    public def barrier () : void {
    	if (nativeSupportsCollectives())
        	finish nativeBarrier(id, Runtime.hereInt());
    	else
    		state(id).collective_impl[Int](LocalTeamState.COLL_BARRIER, 0, null, 0, null, 0, 0, 0);
    }

    private static def nativeBarrier (id:int, role:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeBarrier(id, role);")
        @Native("c++", "x10rt_barrier(id, role, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Blocks until all members have received their part of root's array.
     * Each member receives a contiguous and distinct portion of the src array.
     * src should be structured so that the portions are sorted in ascending
     * order, e.g., the first member gets the portion at offset src_off of sbuf, and the
     * last member gets the last portion.
     *
     * @param role Our role in the team
     *
     * @param root The member who is supplying the data
     *
     * @param src The data that will be sent (will only be used by the root
     * member)
     *
     * @param src_off The offset into src at which to start reading
     *
     * @param dst The rail into which the data will be received for this member
     *
     * @param dst_off The offset into dst at which to start writing
     *
     * @param count The number of elements being transferred
     */
    public def scatter[T] (root:Place, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int) : void {
    	if (nativeSupportsCollectives())
        	finish nativeScatter(id, Runtime.hereInt(), root.id(), src, src_off, dst, dst_off, count);
    	else
    		state(id).collective_impl[T](LocalTeamState.COLL_SCATTER, root.id(), src, src_off, dst, dst_off, count, 0);
    }

    private static def nativeScatter[T] (id:Int, role:Int, root:Int, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeScatter(id, role, root, src, src_off, dst, dst_off, count);")
        @Native("c++", "x10rt_scatter(id, role, root, &src->raw[src_off], &dst->raw[dst_off], sizeof(TPMGL(T)), count, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Blocks until all members have received root's array.
     *
     * @param role Our role in the team
     *
     * @param root The member who is supplying the data
     *
     * @param src The data that will be sent (will only be used by the root member)
     *
     * @param src_off The offset into src at which to start reading
     *
     * @param dst The rail into which the data will be received for this member
     *
     * @param dst_off The offset into dst at which to start writing
     *
     * @param count The number of elements being transferred
     */
     public def bcast[T] (root:Place, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int) : void {
     	if (nativeSupportsCollectives())
        	finish nativeBcast(id, Runtime.hereInt(), root.id(), src, src_off, dst, dst_off, count);
     	else
     		state(id).collective_impl[T](LocalTeamState.COLL_BROADCAST, root.id(), src, src_off, dst, dst_off, count, 0);
    }

    private static def nativeBcast[T] (id:Int, role:Int, root:Int, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeBcast(id, role, root, src, src_off, dst, dst_off, count);")
        @Native("c++", "x10rt_bcast(id, role, root, &src->raw[src_off], &dst->raw[dst_off], sizeof(TPMGL(T)), count, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Blocks until all members have received their part of each other member's array.
     * Each member receives a contiguous and distinct portion of the src array.
     * src should be structured so that the portions are sorted in ascending
     * order, e.g., the first member gets the portion at offset src_off of sbuf, and the
     * last member gets the last portion.
     *
     * @param role Our role in the team
     *
     * @param src The data that will be sent (will only be used by the root
     * member)
     *
     * @param src_off The offset into src at which to start reading
     *
     * @param dst The rail into which the data will be received for this member
     *
     * @param dst_off The offset into dst at which to start writing
     *
     * @param count The number of elements being transferred
     */
    public def alltoall[T] (src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int) : void {
    	if (nativeSupportsCollectives())
        	finish nativeAlltoall(id, Runtime.hereInt(), src, src_off, dst, dst_off, count);
    	else
    		state(id).collective_impl[T](LocalTeamState.COLL_ALLTOALL, 0, src, src_off, dst, dst_off, count, 0);
    }
    
    private static def nativeAlltoall[T](id:Int, role:Int, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeAllToAll(id, role, src, src_off, dst, dst_off, count);")
        @Native("c++", "x10rt_alltoall(id, role, &src->raw[src_off], &dst->raw[dst_off], sizeof(TPMGL(T)), count, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Indicates the operation to perform when reducing. */
    public static val ADD = 0;
    /** Indicates the operation to perform when reducing. */
    public static val MUL = 1;
    /** Indicates the operation to perform when reducing. */
    public static val AND = 3;
    /** Indicates the operation to perform when reducing. */
    public static val OR  = 4;
    /** Indicates the operation to perform when reducing. */
    public static val XOR = 5;
    /** Indicates the operation to perform when reducing. */
    public static val MAX = 6;
    /** Indicates the operation to perform when reducing. */
    public static val MIN = 7;

    /* using overloading is the correct thing to do here since the set of supported
     * types are finite, however the java backend will not be able to distinguish
     * these methods' prototypes so we use the unsafe generic approach for now.
     */

    /** Blocks until all members have received the computed result.  Note that not all values of T are valid.
     * The dst array is populated for all members with the result of the operation applied pointwise to all given src arrays.
     *
     * @param role Our role in the team
     *
     * @param src The data that will be sent (will only be used by the root
     * member)
     *
     * @param src_off The offset into src at which to start reading
     *
     * @param dst The rail into which the data will be received for this member
     *
     * @param dst_off The offset into dst at which to start writing
     *
     * @param count The number of elements being transferred
     *
     * @param op The operation to perform
     */
    public def reduce[T] (root:Place, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int, op:Int) : void {
    	if (nativeSupportsCollectives())
        	finish nativeReduce(id, Runtime.hereInt(), root.id(), src, src_off, dst, dst_off, count, op);
    	else
    		state(id).collective_impl[T](LocalTeamState.COLL_REDUCE, root.id(), src, src_off, dst, dst_off, count, op);
	}
	
    private static def nativeReduce[T](id:Int, role:Int, root:Int, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int, op:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeReduce(id, role, root, src, src_off, dst, dst_off, count, op);")
    	@Native("c++", "x10rt_reduce(id, role, root, &src->raw[src_off], &dst->raw[dst_off], (x10rt_red_op_type)op, x10rt_get_red_type<TPMGL(T)>(), count, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:Byte, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:UByte, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:Short, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:UShort, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:UInt, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:Int, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:Long, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:ULong, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:Float, op:Int) = genericReduce(root, src, op);
    /** Performs a reduction on a single value, returning the result at the root */
    public def reduce (root:Place, src:Double, op:Int) = genericReduce(root, src, op);

    private def genericReduce[T] (root:Place, src:T, op:Int) : T {
        val chk = new Rail[T](1, src);
        val dst = new Rail[T](1, src);
        if (nativeSupportsCollectives())
        	finish nativeReduce[T](id, Runtime.hereInt(), root.id(), chk, dst, op);
        else
        	state(id).collective_impl[T](LocalTeamState.COLL_REDUCE, root.id(), chk, 0, dst, 0, 1, op);
        return dst(0);
    }

    private static def nativeReduce[T](id:Int, role:Int, root:Int, src:Rail[T], dst:Rail[T], op:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeReduce(id, role, root, src, 0, dst, 0, 1, op);")
        @Native("c++", "x10rt_reduce(id, role, root, src->raw, dst->raw, (x10rt_red_op_type)op, x10rt_get_red_type<TPMGL(T)>(), 1, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Blocks until all members have received the computed result.  Note that not all values of T are valid.
     * The dst array is populated for all members with the result of the operation applied pointwise to all given src arrays.
     *
     * @param role Our role in the team
     *
     * @param src The data that will be sent (will only be used by the root
     * member)
     *
     * @param src_off The offset into src at which to start reading
     *
     * @param dst The rail into which the data will be received for this member
     *
     * @param dst_off The offset into dst at which to start writing
     *
     * @param count The number of elements being transferred
     *
     * @param op The operation to perform
     */
    public def allreduce[T] (src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int, op:Int) : void {
    	if (nativeSupportsCollectives())
        	finish nativeAllreduce(id, Runtime.hereInt(), src, src_off, dst, dst_off, count, op);
    	else
    		state(id).collective_impl[T](LocalTeamState.COLL_ALLREDUCE, 0, src, src_off, dst, dst_off, count, op);
    }

    private static def nativeAllreduce[T](id:Int, role:Int, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int, op:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeAllReduce(id, role, src, src_off, dst, dst_off, count, op);")
    	@Native("c++", "x10rt_allreduce(id, role, &src->raw[src_off], &dst->raw[dst_off], (x10rt_red_op_type)op, x10rt_get_red_type<TPMGL(T)>(), count, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:Byte, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:UByte, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:Short, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:UShort, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:UInt, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:Int, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:Long, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:ULong, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:Float, op:Int) = genericAllreduce(src, op);
    /** Performs a reduction on a single value, returning the result */
    public def allreduce (src:Double, op:Int) = genericAllreduce(src, op);

    private def genericAllreduce[T] (src:T, op:Int) : T {
        val chk = new Rail[T](1, src);
        val dst = new Rail[T](1, src);
        if (nativeSupportsCollectives())
        	finish nativeAllreduce[T](id, Runtime.hereInt(), chk, dst, op);
        else
        	state(id).collective_impl[T](LocalTeamState.COLL_ALLREDUCE, 0, chk, 0, dst, 0, 1, op);
        return dst(0);
    }

    private static def nativeAllreduce[T](id:Int, role:Int, src:Rail[T], dst:Rail[T], op:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeAllReduce(id, role, src, 0, dst, 0, 1, op);")
        @Native("c++", "x10rt_allreduce(id, role, src->raw, dst->raw, (x10rt_red_op_type)op, x10rt_get_red_type<TPMGL(T)>(), 1, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Returns the index of the biggest double value across the team */
    public def indexOfMax (v:Double, idx:Int) : Int {
        val src = new Rail[DoubleIdx](1, DoubleIdx(v, idx));
        val dst = new Rail[DoubleIdx](1);
        if (nativeSupportsCollectives())
        	finish nativeIndexOfMax(id, Runtime.hereInt(), src, dst);
        else
        	state(id).collective_impl[DoubleIdx](LocalTeamState.COLL_INDEXOFMAX, 0, src, 0, dst, 0, 1, 0);
        return dst(0).idx;
    }

    private static def nativeIndexOfMax(id:Int, role:Int, src:Rail[DoubleIdx], dst:Rail[DoubleIdx]) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeIndexOfMax(id, role, src, dst);")
        @Native("c++", "x10rt_allreduce(id, role, src->raw, dst->raw, X10RT_RED_OP_MAX, X10RT_RED_TYPE_DBL_S32, 1, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Returns the index of the smallest double value across the team */
    public def indexOfMin (v:Double, idx:Int) : Int {
        val src = new Rail[DoubleIdx](1, DoubleIdx(v, idx));
        val dst = new Rail[DoubleIdx](1);
        if (nativeSupportsCollectives())
        	finish nativeIndexOfMin(id, Runtime.hereInt(), src, dst);
        else
        	state(id).collective_impl[DoubleIdx](LocalTeamState.COLL_INDEXOFMIN, 0, src, 0, dst, 0, 1, 0);
        return dst(0).idx;
    }

    private static def nativeIndexOfMin(id:Int, role:Int, src:Rail[DoubleIdx], dst:Rail[DoubleIdx]) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeIndexOfMin(id, role, src, dst);")
        @Native("c++", "x10rt_allreduce(id, role, src->raw, dst->raw, X10RT_RED_OP_MIN, X10RT_RED_TYPE_DBL_S32, 1, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    /** Create new teams by subdividing an existing team.  This is called by each member
     * of an existing team, indicating which of the new teams it will be a member of, and its role
     * within that team.  The old team is still available after this call.  All the members
     * of the old team must collectively assign themselves to new teams such that there is exactly 1
     * member of the original team for each role of each new team.  It is undefined behaviour if two
     * members of the original team decide to play the same role in one of the new teams, or if one of
     * the roles of a new team is left unfilled.
     *
     * @param role The caller's role within the old team
     *
     * @param color The new team, must be a number between 0 and the number of new teams - 1
     *
     * @param new_role The caller's role within the new team
     */
    public def split (color:Int) : TeamCollectives {
        // TODO - without the role argument, we need a way to handle the 4th argument of the native split 
        val result = new Rail[Int](1);
//        if (nativeSupportsCollectives()) {
        	finish nativeSplit(id, Runtime.hereInt(), color, Runtime.hereInt(), result);
        	return TeamCollectives(result(0), null);
//        }
//        else {
        	// TODO
//        	return null;
//        }
    }

    private static def nativeSplit(id:Int, role:Int, color:Int, new_role:Int, result:Rail[Int]) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeSplit(id, role, color, new_role, result);")
        @Native("c++", "x10rt_team_split(id, role, color, new_role, x10aux::coll_handler2, x10aux::coll_enter2(result->raw));") {}
    }

    /** Destroy a team that is no-longer needed.  Called simultaneously by each member of
     * the team.  There should be no operations on the team after this.
     *
     * @param role Our role in this team
     */
    public def del () : void {
        if (this == WORLD) throw new IllegalArgumentException("Cannot delete TeamCollectives.WORLD");
        if (nativeSupportsCollectives())
        	finish nativeDel(id, Runtime.hereInt());
        else {
        	// TODO
        }
    }

    private static def nativeDel(id:Int, role:Int) : void {
        @Native("java", "x10.x10rt.TeamSupport.nativeDel(id, role);")
        @Native("c++", "x10rt_team_del(id, role, x10aux::coll_handler, x10aux::coll_enter());") {}
    }

    public def toString() = "TeamCollectives(" + this.id + ")";
    public def equals(that:TeamCollectives) = that.id==this.id;
    public def equals(that:Any) = that instanceof TeamCollectives && (that as TeamCollectives).id==this.id;
    public def hashCode()=id;
    
    
    private static class LocalTeamState(places:PlaceGroup, teamid:Int) {
	    /*
	     * State information for X10 collective operations
	     * All collectives are implemented as a tree operation, with all members of the team 
	     * communicating with a "parent" member in a gather phase up to the root of the team,
	     * followed by a scatter phase from the root to all members.  Data and reduction operations
	     * may be carried along as a part of these communication phases, depending on the collective.
	     * 
	     * All operations are initiated by leaf nodes, who push data to the parent's buffers.  The parent
	     * then initiates a push from to its parent, and so on, up to the root.  At the root, 
	     * the direction changes, and the root pushes data to children, who push it to their children, etc.
	     */
	    private static PHASE_IDLE:Int = 0;    // normal state, nothing in progress
	    private static PHASE_GATHER1:Int = 1; // waiting for signal/data from first child
	    private static PHASE_GATHER2:Int = 2; // waiting for signal/data from second child
	    private static PHASE_SCATTER:Int = 3; // waiting for signal/data from parent
	    private var phase:AtomicInteger = new AtomicInteger(PHASE_IDLE); // which of the above phases we're in
	    
	    private static COLL_BARRIER:Int = 0; // no data moved
	    private static COLL_SCATTER:Int = 1; // data out only
    	private static COLL_BROADCAST:Int = 2; // data out only
	    private static COLL_REDUCE:Int = 3; // data in only
	    private static COLL_ALLTOALL:Int = 4; // data in and out
	    private static COLL_ALLREDUCE:Int = 5; // data in and out
	    private static COLL_INDEXOFMIN:Int = 6; // data in and out
	    private static COLL_INDEXOFMAX:Int = 7; // data in and out
	    
	    /* Utility methods to traverse binary tree structure.  The tree is not built using the place id's 
	     * to determine the position in the tree, but rather the position in the places:PlaceGroup field to 
	     * determine the position.  The first place in 'places' is the root of the tree, the next two its children, 
	     * and so on.  For collective operations that specify a root, the tree will use that root's position in 
	     * 'places' as the tree root, shifting all of the parent/child relationships
	     * 
	     * A return value of Place.INVALID_PLACE means that the parent/child does not exist.
	     */
	    private def getParentId(root:Int):Place {
	        if (Runtime.hereInt() == root) return Place.INVALID_PLACE;
	        rootPosition:Int = places.indexOf(root);
	        if (rootPosition == -1) return Place.INVALID_PLACE;
	        myPosition:Int = places.indexOf(Runtime.hereInt());
	        parentPosition:Int = (((myPosition-1)/2)+rootPosition) % places.numPlaces();
	        return places(parentPosition);
	    }
	    private def getChildIds(root:Int):Pair[Place,Place] {
	    	rootPosition:Int = places.indexOf(root);
	        if (rootPosition == -1) return Pair[Place,Place](Place.INVALID_PLACE, Place.INVALID_PLACE); // invalid root specified
	        myPosition:Int = places.indexOf(Runtime.hereInt());
	        childPosition:Int = (myPosition*2) + 1;
	        if (childPosition >= places.numPlaces()) 
	        	return Pair[Place,Place](Place.INVALID_PLACE, Place.INVALID_PLACE); // no children
	        else if (childPosition+1 >= places.numPlaces())
	        	return Pair[Place,Place](places((childPosition+rootPosition) % places.numPlaces()), Place.INVALID_PLACE); // one child only
	        else
	        	return Pair[Place,Place](places((childPosition+rootPosition) % places.numPlaces()), places((childPosition+1+rootPosition) % places.numPlaces())); // two children
	    }
	    
	    
	    /*
	     * Collective operations, to be executed on this team
	     */
	    
	    // This is an internal barrier which can be called at the end of team creation.  The regular
	    // barrier method assumes that the team is already in place.  This method adds some pre-checks
	    // to ensure that the state information for the entire team is in place before running the 
	    // regular barrier, which does not have these checks.
	    
	    // implementation note: There are several instanves of "System.sleep(..)" in the code
	    // below, where we are waiting for a remote entity to update the state.  I have tried 
	    // using nohting in that position, as well as Runtime.probe(), and both of those options
	    // lead to deadlocks.  System.sleep(..), which internally increases then decreases 
	    // parallelism, seems to do the trick.
	    private def init() {
	    	//Runtime.println(here + " entering init phase");
		    parent:Place = getParentId(places(0).id());
	    	val teamidcopy = teamid; // needed to prevent serializing "this"
		    if (parent != Place.INVALID_PLACE)
			    at (parent) while (TeamCollectives.state.size() <= teamidcopy) System.sleep(10);
		    collective_impl[Int](COLL_BARRIER, 0, null, 0, null, 0, 0, 0); // barrier
		    //Runtime.println(here + " leaving init phase");
		}

	    /*
	     * This method contains the implementation for all collectives.  Some arguments are only valid
	     * for specific collectives.
	     */
	    private def collective_impl[T](collType:Int, root:Int, src:Rail[T], src_off:Int, dst:Rail[T], dst_off:Int, count:Int, operation:Int):void {
	        //Runtime.println(here+":team"+teamid+" entered barrier (by the way, phase = "+phase.get()+")");
	    	// block if some other collective is in progress.
	    	while (!this.phase.compareAndSet(PHASE_IDLE, PHASE_GATHER1))
	    		System.sleep(10);
	    	//Runtime.println(here+":team"+teamid+" entered barrier PHASE_GATHER1");
	    	parent:Place = getParentId(root);
	        children:Pair[Place,Place] = getChildIds(root);
	    	val teamidcopy = teamid; // needed to prevent serializing "this" in at() statements	    	
	    	//Runtime.println(here+":team"+teamidcopy+" has parent "+parent);
	    	//Runtime.println(here+":team"+teamidcopy+" has children "+children);

	    
	    	// Start out waiting for all children to update our state 
	    	if (children.first == Place.INVALID_PLACE) // no children to wait for
	    		this.phase.compareAndSet(PHASE_GATHER1, PHASE_SCATTER);
	    	else if (children.second == Place.INVALID_PLACE) { // only one child, so skip a phase waiting for the second child.
	    		if (!this.phase.compareAndSet(PHASE_GATHER1, PHASE_GATHER2)) 
	    			this.phase.compareAndSet(PHASE_GATHER2, PHASE_SCATTER); 
	    	}
	    	//Runtime.println(here+":team"+teamidcopy+" waiting for children");
	    	while (this.phase.get() != PHASE_SCATTER) // wait for updates from children, not already skipped
	    		System.sleep(10);
	    	    
	    
	        // all children have checked in.  Update our parent, and then wait for the parent to update us 
	    	if (parent == Place.INVALID_PLACE) 
	    		this.phase.set(PHASE_IDLE); // the root node has no parent, and can skip ahead
	    	else {
	    		at (parent) { // increment the phase of the parent
	    			while(!TeamCollectives.state(teamidcopy).phase.compareAndSet(PHASE_GATHER1, PHASE_GATHER2) && 
	    					!TeamCollectives.state(teamidcopy).phase.compareAndSet(PHASE_GATHER2, PHASE_SCATTER))
	    				System.sleep(10);
	    			//Runtime.println(here+" has been set to phase "+TeamCollectives.state(teamidcopy).phase.get());
	    		}
			    //Runtime.println(here+ " waiting for parent "+parent+":team"+teamidcopy+" to release us from phase "+phase.get());
			    while (this.phase.get() != PHASE_IDLE) // wait for parent to set us free
			    	System.sleep(10);
	    	}
	    
	    
	    	// our parent has updated us - update any children, and leave the barrier
	    	if (children.first != Place.INVALID_PLACE) { // free the first child, if it exists
	    		at (children.first) {
	    			if (!TeamCollectives.state(teamidcopy).phase.compareAndSet(PHASE_SCATTER, PHASE_IDLE))
	    				Runtime.println("ERROR root setting the first child "+here+":team"+teamidcopy+" to PHASE_IDLE");
	    			//else Runtime.println("set the first child "+here+":team"+teamidcopy+" to PHASE_IDLE");
	    		}
	    		if (children.second != Place.INVALID_PLACE) {
	    			at (children.second) {
	    				if (!TeamCollectives.state(teamidcopy).phase.compareAndSet(PHASE_SCATTER, PHASE_IDLE))
	    					Runtime.println("ERROR root setting the second child "+here+":team"+teamidcopy+" to PHASE_IDLE");
	    				//else Runtime.println("set the second child "+here+":team"+teamidcopy+" to PHASE_IDLE");
	    			}
	    		}
	    	}
	    
	        // done!
	    	//Runtime.println(here+":team"+teamidcopy+" leaving barrier");	    
	    }
	}
}

// vim: shiftwidth=4:tabstop=4:expandtab
