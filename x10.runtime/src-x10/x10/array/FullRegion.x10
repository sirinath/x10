/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

package x10.array;

/**
 * A full region is the unbounded region that contains all points of its rank
 */
final class FullRegion extends Region{rect} {

    def this(val rank:int):FullRegion{self.rank==rank} {
        super(rank, true, false);
	if (rank<0) throw new IllegalArgumentException("Rank is negative ("+rank+")");
    }

    public global def isConvex() = true;
    public global def isEmpty() = false;
    public global def size():int {
        throw new IllegalOperationException("Full Region is infinite; size not supported");
    }
    public global def min() = ValRail.make(rank, (Int)=>Int.MIN_VALUE);
    public global def max() = ValRail.make(rank, (Int)=>Int.MAX_VALUE);
    public global def intersection(that: Region(rank)): Region(rank) = that;
    public global def product(that: Region): Region/*(this.rank+that.rank)*/ {
        if (that.isEmpty()) {
            return Region.makeEmpty(rank+that.rank);
        } else if (that instanceof FullRegion) {
            return new FullRegion(rank+that.rank);
        } else if (that instanceof RectRegion) {
            val thatMin = (that as RectRegion).min();
            val thatMax = (that as RectRegion).max();
            val newRank = rank+that.rank;
            val newMin = ValRail.make[int](newRank, (i:int)=>i<rank?Int.MIN_VALUE:thatMin(i-rank));
            val newMax = ValRail.make[int](newRank, (i:int)=>i<rank?Int.MAX_VALUE:thatMax(i-rank));
	    return Region.makeRectangular(newMin,newMax);
        } else {
	    throw new UnsupportedOperationException("haven't implemented FullRegion product with "+that.typeName());
        }
    }
    public global def projection(axis: int): Region(1) = new FullRegion(1);
    public global def translate(p:Point(rank)): Region(rank) = this;
    public global def eliminate(i:Int)= new FullRegion(rank-1);
    protected global def computeBoundingBox(): Region(rank) = this;
    public global def contains(that: Region(rank)):Boolean = true;
    public global def contains(p:Point):Boolean = true;
    public global safe def toString() = "full(" + rank + ")";


    public global def scanners():Iterator[Region.Scanner]! {
        throw new IllegalOperationException("Full Region is infinite: scanning is not supported");
    }

    public global def iterator():Iterator[Point(rank)] {
        throw new IllegalOperationException("Full Region is infinite: iteration is not supported");
    }
}
