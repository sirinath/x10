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

import x10.io.Printer;

/**
 * The BaseRegion class is the base of the hierarchy of classes that
 * implement Region. BaseRegion provides a set of factory methods, and
 * also provides some function common to all Region implementations,
 * such as default implementations of some Region methods. Specific
 * Region implementation classes may override these methods with more
 * efficient implementations.
 *
 * @author bdlucas
 */
abstract public class BaseRegion extends Region {

    // XTENLANG-49
    static type PolyRegion(rank:Int) = PolyRegion{self.rank==rank};
  //  static type PolyRegionListBuilder(rank:Int) = PolyRegionListBuilder{self.rank==rank};
    static type PolyRow(rank:Int) = PolyRow{self.rank==rank};
    static type PolyMat(rank:Int) = PolyMat{self.rank==rank};
    static type BaseRegion(rank:int) = BaseRegion{self.rank==rank};


    //
    // basic information
    //

    abstract public global def isConvex(): boolean;
    abstract public global def isEmpty(): boolean;
    abstract public global def size(): int;


    //
    // region composition
    //

   /* public global def union(that: Region(rank)): Region(rank) {
        val rs = new PolyRegionListBuilder(rank);
        rs.add(this);
        rs.add(that.difference(this));
        return UnionRegion.make(rs);
    }
    

    public global def disjointUnion(that: Region(rank)): Region(rank) {
        if (!this.intersection(that).isEmpty())
            throw U.illegal("regions are not disjoint");
        val rs = new PolyRegionListBuilder(rank);
        rs.add(this);
        rs.add(that);
        return UnionRegion.make(rs);
    }

    public global def difference(that: Region(rank)): Region(rank) {
        // complement might be expensive so we do some special casing
        if (this.isEmpty() || that.isEmpty())
            return this;
        else
            return this.intersection(that.complement());
    }
*/
    public global def disjoint(that: Region(rank)): boolean {
        return this.intersection(that).isEmpty();
    }
    
    /*
    abstract public global def complement(): Region(rank);
    */
    abstract public global def intersection(that: Region(rank)): Region(rank);
    abstract public global def product(that: Region): Region;
    abstract public global def projection(axis: int): Region(1);

    // XTENLANG-571
    /**
     * Bounding box is computed by taking the projection on each
     * axis. This implementation is more efficient than computing
     * projection on each axis because it re-uses the FME results.
     */

   // global val boundingBoxException: RuntimeException;
   // global val boundingBox:Region(rank);

    public global def boundingBox(): Region(rank) {
    	return computeBoundingBox();
       /* if (boundingBoxException != null)
	    throw boundingBoxException;
        return boundingBox;*/
    }
  


    //
    // low-performance bounds checking support
    //
    // XXX do we care about high-performance bounds checking for
    // rectangular regions?
    //

    global def check(err:(Point)=>RuntimeException, pt: Point(this.rank)) {
        if (!contains(pt))
            throw err(pt);
    }

    global def check(err:(Point)=>RuntimeException, i0: int) {rank==1} {
        check(err, Point.make(i0)); 
    }

    global def check(err:(Point)=>RuntimeException, i0: int, i1: int) {rank==2} {
        check(err, Point.make(i0,i1)); 
    }

    global def check(err:(Point)=>RuntimeException, i0: int, i1: int, i2: int) {rank==3} {
        check(err, Point.make(i0,i1,i2)); 
    }

    global def check(err:(Point)=>RuntimeException, i0: int, i1: int, i2: int, i3: int) {rank==4} {
        check(err, Point.make(i0,i1,i2,i3)); 
    }


    //
    // region comparison operations
    //

  /*  public global def contains(that: Region(rank)): boolean {
        return that.difference(this).isEmpty();
    }
*/
    
    public global safe def equals(that:Any):boolean {
	if (!(that instanceof Region)) return false;
	val t1 = that as Region;
	if (rank != t1.rank) return false;
        val t2 = t1 as Region(rank);
        return this.contains(t2) && t2.contains(this);
    }


    //
    // pointwise
    //

    public global def contains(p: Point): boolean {
        throw U.unsupported(this, "contains(Point)");
    }



    //
    // scanning, iterating
    //
    // XXX - slight generalization, or wrappering, of
    // PolyRegion.Iterator gives us a BaseRegion.Iterator
    //

    public global def scanners(): Iterator[Scanner]! {
        throw U.unsupported(this, "scanners()");
    }

    public global def iterator(): Iterator[Point(rank)] {
        throw U.unsupported(this, "iterator()");
    }


    //
    // debugging
    //

    public global def printInfo(out: Printer): void {
        out.println("Region " + this/*.getClass().getName()*/);
    }

    protected def this(rank: int, rect: boolean, zeroBased: boolean): BaseRegion {
        super(rank, rect, zeroBased);
    }

    public global def min(): ValRail[int] {
        throw U.unsupported(this, "min()");
    }

    public global def max(): ValRail[int] {
        throw U.unsupported(this, "max()");

    }
}

