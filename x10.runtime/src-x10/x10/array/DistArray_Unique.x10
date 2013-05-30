/*
 *  This file is part of the X10 project (http://x10-lang.org).
 * 
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 *  (C) Copyright IBM Corporation 2006-2013.
 */

package x10.array;

import x10.compiler.Inline;
import x10.compiler.CompilerFlags;
import x10.io.CustomSerialization;
import x10.io.SerialData;

/**
 * Implementation of DistArray that has exactly one element at each 
 * place in its PlaceGroup such that element i is stored at the
 * ith place as computed by the {@link x10.lang.PlaceGroup#indexOf(Place)}.
 */
public final class DistArray_Unique[T] extends DistArray[T] implements (Long)=>T {
    
    public property rank() = 1;

    /**
     * Construct a zero-initialized DistArray_Unique object on the WORLD PlaceGroup
     */
    public def this(){T haszero} {
        super(PlaceGroup.WORLD, () => new LocalState(PlaceGroup.WORLD, new Rail[T](1), PlaceGroup.WORLD.size()));
    }

    // Custom Serialization: for now just delegate to superclass as we have no state ourselves.
    public def serialize():SerialData = super.serialize();
    def this(sd:SerialData) { super(sd); }

    public def globalIndices():IterationSpace{self.rank==this.rank(),self.rect,self!=null} {
        return new DenseIterationSpace_1(0L, placeGroup.size()-1L) as IterationSpace{self.rank==1,self.rect,self!=null}; // FIXME: Constraint system weakness. This cast should not be needed.
    }

    /**
     * Get an IterationSpace that represents all Points contained in
     * the local iteration space (valid indices) of the DistArray at the current Place.
     * @return an IterationSpace for the Array
     */
    public def localIndices():IterationSpace{self.rank==this.rank(),self.rect,self!=null} {
        val idx = placeGroup.indexOf(here);
        return new DenseIterationSpace_1(idx, idx) as IterationSpace{self.rank==1,self.rect,self!=null}; // FIXME: Constraint system weakness. This cast should not be needed.
    }

    /**
     * Return the element of this array corresponding to the given index.
     * 
     * @param i the given index in the first dimension
     * @return the element of this array corresponding to the given index.
     * @see #set(T, Int)
     */
    public @Inline operator this(i:long):T {
        if (CompilerFlags.checkPlace() || CompilerFlags.checkBounds()) validateIndex(i);
        val rail = raw();
        return rail(0);
    }

    /**
     * Return the element of this array corresponding to the given Point.
     * 
     * @param p the given Point
     * @return the element of this array corresponding to the given Point.
     * @see #set(T, Point)
     */
    public @Inline operator this(p:Point(this.rank())):T  = this(p(0));

    
    /**
     * Set the element of this array corresponding to the given index to the given value.
     * Return the new value of the element.
     * 
     * @param v the given value
     * @param i the given index in the first dimension
     * @return the new value of the element of this array corresponding to the given index.
     * @see #operator(Int)
     */
    public @Inline operator this(i:long)=(v:T):T{self==v} {
        if (CompilerFlags.checkPlace() || CompilerFlags.checkBounds()) validateIndex(i);
        val rail = raw();
	rail(0) = v;
        return v;
    }

    /**
     * Set the element of this array corresponding to the given Point to the given value.
     * Return the new value of the element.
     * 
     * @param v the given value
     * @param p the given Point
     * @return the new value of the element of this array corresponding to the given Point.
     * @see #operator(Int)
     */
    public @Inline operator this(p:Point(this.rank()))=(v:T):T{self==v} = this(p(0)) = v;

    private @Inline def validateIndex(i:long) {
        if (CompilerFlags.checkBounds() && (i < 0 || i >= placeGroup.size())) raiseBoundsError(i);
        if (CompilerFlags.checkPlace() && placeGroup.indexOf(here) != i) raisePlaceError(i);
    }

}

// vim:tabstop=4:shiftwidth=4:expandtab
