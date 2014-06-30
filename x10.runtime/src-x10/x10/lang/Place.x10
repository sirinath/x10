/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

package x10.lang;

import x10.compiler.Native;
import x10.compiler.CompilerFlags;

/**
 * Representation of a place within the APGAS model.
 * 
 */
public final struct Place(
    /*
     * Implementation note.
     * The X10RT messaging layer and native XRC/XRJ native runtime
     * only support int (32 bit) Place id's. 
     * When entering/exiting the native layer we simply truncate/extend the id.
     * The truncation is unchecked.  This is safe because we check in the constructor
     * of Place that the id is between -1 and MAX_PLACES and MAX_PLACES will be limited
     * by the native runtime (which sets it) to a positive 32 bit int.
     */
    id:Long)  {
    public property id():Long = id;

    /** The number of places including accelerators.
     * Accelerator places have limitations on the kinds of code they can run.
     */
    @Native("java", "((long)x10.runtime.impl.java.Runtime.MAX_PLACES)")
    @Native("c++", "((x10_long)::x10aux::num_places)")
    public static ALL_PLACES:Long = 4;

    /** The number of places not including accelerators. */
    @Native("java", "((long)x10.runtime.impl.java.Runtime.MAX_PLACES)")
    @Native("c++", "((x10_long)::x10aux::num_hosts)")
    public static MAX_PLACES: Long = 4;

    /** The number of dead places not including accelerators. */
    @Native("java", "((long)x10.x10rt.X10RT.numDead())")
    @Native("c++", "((x10_long)x10rt_ndead())")
    public static numDead(): Long = 0;

    /**
     * Returns whether a place is dead.
     */
    @Native("java", "x10.x10rt.X10RT.isPlaceDead((int)#id)")
    @Native("c++", "x10rt_is_place_dead((x10_int)#id)")
    public static def isDead(id:Long):Boolean = false;

    /**
     * The number of places including accelerators.
     */
    @Native("java", "((long)x10.x10rt.X10RT.numPlaces())")
    @Native("c++", "((x10_long)::x10aux::num_places)")
    public static def numPlaces():Long = ALL_PLACES;
    
    /**
     * A convenience for iterating over all host places.
     */
    public static def places():PlaceGroup = PlaceGroup.WORLD;

    /**
     * The place that runs 'main'.
     */
    public static FIRST_PLACE:Place(0) = Place(0);
    
    /**
     * Special Place that encodes non-existent Places
     */
    public static INVALID_PLACE:Place(-1) = Place(-1);

    /**
     * Creates a Place struct from an place id.
     */
    public def this(id:Long):Place(id) { 
        property(id); 
        if (CompilerFlags.checkPlace() && (id < -1 || id >= numPlaces())) {
            throw new IllegalArgumentException(id+" is not a valid Place id");
        }
    }

    /**
     * Returns the place with the next higher integer index.
     */
    public def next():Place = next(1);

    /**
     * Returns the place with the next lower integer index.
     */
    public def prev():Place = next(-1);

    /**
     * Returns the same place as would be obtained by using prev() 'i' times.
     */
    public def prev(i:Long):Place = next(-i);

    /**
     * Returns the same place as would be obtained by using next() 'i' times.
     */
    public def next(i:Long):Place {
        val pt = PlaceTopology.getTopology();
        // -1 % n == -1, not n-1, so need to add n
        if (pt.isPrimary(Place(id))) {
            val k = (id + i % MAX_PLACES + MAX_PLACES) % MAX_PLACES;
            return Place(k);
        }
        // FIXME: iterate through peers
        return this;
    }

    /**
     * 
     */
    public def isFirst():Boolean = id == 0;
    public def isLast():Boolean = id == MAX_PLACES - 1;

    /** Is this place dead? */
    public def isDead():Boolean = isDead(id);

    // TODO: Should probably remove in favor of PlaceTopology
    @Native("c++", "::x10aux::is_cuda((x10_int)((#this)->FMGL(id)))")
    public def isCUDA():Boolean = false;

    // TODO: Should probably remove in favor of PlaceTopology
    @Native("c++", "::x10::lang::Place::_make(::x10aux::parent((x10_int)((#this)->FMGL(id))))")
    public def parent():Place = this;

    public def toString() = "Place(" + this.id + ")";
    public def equals(p:Place) = p.id==this.id;
    public def equals(p:Any) = p instanceof Place && (p as Place).id==this.id;
    public def hashCode() = id as Int;

    
    /**
     * Converts a GlobalRef to its home.
     */
    @Native("java", "(#r).home")
    @Native("c++", "::x10::lang::Place::_make(((x10_long)((#r)->location)))")
    public static native operator[T] (r:GlobalRef[T]){T isref}: Place{self==r.home};

}

public type Place(id:Long) = Place{self.id==id};
public type Place(p:Place) = Place{self==p};
