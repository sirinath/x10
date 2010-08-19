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
 * A Region(rank) represents a set of points of class Point(rank). The
 * Region class defines a set of static factory methods for
 * constructing regions. There are properties and methods for
 * accessing basic information about of a region, such as its bounding
 * box, its size, whether or not it is convex, whether or not it is
 * empty. There are a set of methods for supporting algebraic
 * operations on regions, such as intersection, union, difference, and
 * so on. The set of points in a region may be iterated over.
 */
public abstract class Region(
    rank: int,
    rect: boolean,
    zeroBased: boolean
) implements Iterable[Point(rank)] {

    property rail = rank==1 && rect && zeroBased;
    property region = this; // structural affinity w/ Dist, Array for compiler

    //
    // factories
    //

    /**
     * Construct an empty region of the specified rank.
     */

    public static def makeEmpty(rank: int): Region(rank) = new EmptyRegion(rank);
    
    /**
     * Construct an unbounded region of a given rank that contains all
     * points of that rank.
     */

    public static def makeFull(rank: int): Region(rank) = new FullRegion(rank);
    
    /**
     * Construct a region of rank 0 that contains the single point of
     * rank 0. Useful as the identity region under Cartesian product.
     */
    public static def makeUnit(): Region(0) = new FullRegion(0);


    /**
     * Construct an unbounded halfspace region of rank normal.rank
     * that consists of all points p satisfying dot(p,normal) + k <= 0.
     */
    public static def makeHalfspace(normal:Point, k:int):Region(normal.rank) {
        val rank = normal.rank;
        val pmb = new PolyMatBuilder(rank);
        val r = new PolyRow(normal, k);
        pmb.add(r);
        val pm = pmb.toSortedPolyMat(false);
        return PolyRegion.make(pm) as Region(normal.rank); // XXXX Why is this cast here?
    }

    //
    // rectangular factories
    //

    /**
     * Construct a rectangular region whose bounds are specified as
     * rails of ints.
     */

    public static def makeRectangular(minArg: Rail[int]!, maxArg: Rail[int](minArg.length)!):Region(minArg.length){self.rect}
        = makeRectangular(minArg as ValRail[int], maxArg as ValRail[int](minArg.length));  
    public static def makeRectangular(minArg: ValRail[int], maxArg: ValRail[int](minArg.length)):Region(minArg.length){self.rect}
        = new RectRegion(minArg, maxArg);

    /**
     * Construct a rank-1 rectangular region with the specified bounds.
     */
    // XTENLANG-109 prevents zeroBased==(min==0)
    // Changed RegionMaker_c to add clause explicitly.
    public static def makeRectangular(min:int, max:int):Region(1){self.rect}
        = new RectRegion(min, max);

    /**
     * Construct a rank-1 rectangular region with the specified bounds.
     */
    public static def make(min: int, max: int): Region(1){self.rect} = new RectRegion(min, max);

    /**
     * Construct a rank-n rectangular region that is the Cartesian
     * product of the specified rank-1 rectangular regions.
     */
    public static def make(regions:ValRail[Region(1){self.rect}]):Region(regions.length){self.rect} {
        var r:Region = regions(0);
        for (var i: int = 1; i<regions.length; i++)
            r = r.product(regions(i));
	return r as Region(regions.length){self.rect};
    }

    //
    // non-rectangular factories
    //

    /**
     * Construct a banded region of the given size, with the specified
     * number of diagonals above and below the main diagonal
     * (inclusive of the main diagonal).
     */
    public static def makeBanded(size: int, upper: int, lower: int):Region(2)
        = PolyRegion.makeBanded(size, upper, lower);

    /**
     * Construct a banded region of the given size that includes only
     * the main diagonal.
     */
    public static def makeBanded(size: int):Region(2) = PolyRegion.makeBanded(size, 1, 1);
    
    /**
     * Construct an upper triangular region of the given size.
     */

    public static def makeUpperTriangular(size: int):Region(2) = makeUpperTriangular(0, 0, size);

    /**
     * Construct an upper triangular region of the given size with the
     * given lower bounds.
     */
    public static def makeUpperTriangular(rowMin: int, colMin: int, size: int): Region(2)
        = PolyRegion.makeUpperTriangular2(rowMin, colMin, size);
    
    /**
     * Construct a lower triangular region of the given size.
     */
    public static def makeLowerTriangular(size: int): Region(2) = makeLowerTriangular(0, 0, size);

    /**
     * Construct an lower triangular region of the given size with the
     * given lower bounds.
     */
    public static def makeLowerTriangular(rowMin: int, colMin: int, size: int):Region(2)
        = PolyRegion.makeLowerTriangular2(rowMin, colMin, size);


    //
    // Basic non-property information.
    //

    /**
     * Returns the number of points in this region.
     */

    public abstract global def size(): int;

    /**
     * Returns true iff this region is convex.
     */

    public abstract global def isConvex(): boolean;

    /**
     * Returns true iff this region is empty.
     */

    public abstract global def isEmpty(): boolean;



    //
    // bounding box
    //

    /**
     * The bounding box of a region r is the smallest rectangular region
     * that contains all the points of r.
     */
    public global def boundingBox(): Region(rank) = computeBoundingBox();


    abstract global protected  def computeBoundingBox(): Region(rank);

    /**
     * Returns the lower bounds of the bounding box of the region as a
     * Rail[int].
     */

    abstract public global def min(): ValRail[int];

    /**
     * Returns the upper bounds of the bounding box of the region as a
     * Rail[int].
     */

    abstract public global def max(): ValRail[int];
    
    /**
     * Returns the lower bound of the bounding box of the region along
     * the ith axis.
     */

    public global def min(i:Int) = min()(i);

    /**
     * Returns the upper bound of the bounding box of the region along
     * the ith axis.
     */

    public global def max(i:Int) = max()(i);    


    //
    // geometric ops
    //

    /**
     * Returns the complement of a region. The complement of a bounded
     * region will be unbounded.
   

    abstract public global def complement(): Region(rank);
  */
    
    /**
     * Returns the union of two regions: a region that contains all
     * points that are in either this region or that region.
     

    abstract public global def union(that: Region(rank)): Region(rank);

*/
    
    /**
     * Returns the union of two regions if they are disjoint,
     * otherwise throws an exception.
     *   abstract public global def disjointUnion(that: Region(rank)): Region(rank);
     */

  

    /**
     * Returns the intersection of two regions: a region that contains all
     * points that are in both this region and that region.
     * 
     */
    abstract public global def intersection(that: Region(rank)): Region(rank);
    

    /**
     * Returns the difference between two regions: a region that
     * contains all points that are in this region but are not in that
     * region.
     *  abstract public global def difference(that: Region(rank)): Region(rank);
     */

    /**
     * Returns true iff this region has no points in common with that
     * region.
     */
     public global def disjoint(that:Region(rank)) = intersection(that).isEmpty();
   

    /**
     * Returns the Cartesian product of two regions. The Cartesian
     * product has rank this.rank+that.rank. For every point p in the
     * Cartesian product, the first this.rank coordinates of p are a
     * point in this region, while the last that.rank coordinates of p
     * are a point in that.region.
     */

    abstract public global def product(that: Region): Region;

    /**
     * Returns the region shifted by a Point (vector). The Point has
     * to have the same rank as the region. For every point p in the
     * resulting region, each coordinate is that of the corresponding
     * point q shifted by the same coordinate of the given point.
     */

    abstract public global def translate(v: Point(rank)): Region(rank);

    /**
     * Returns the projection of a region onto the specified axis. The
     * projection is a rank-1 region such that for every point (i) in
     * the projection, there is some point p in this region such that
     * p(axis)==i.
     */

    abstract public global def projection(axis: int): Region(1);

    /**
     * Returns the projection of a region onto all axes but the
     * specified axis.
     */

   
    abstract public global def eliminate(axis: int): Region /*(rank-1)*/;


    /**
     * Return an iterator for this region. Normally accessed using the
     * syntax
     *
     *    for (p:Point in r)
     *        ... p ...
     */

    public abstract global def iterator(): Iterator[Point(rank)];


    /**
     * The Scanner class supports efficient scanning. Usage:
     *
     *    for (s:Scanner in r.scanners()) {
     *        int min0 = s.min(0);
     *        int max0 = s.max(0);
     *        for (var i0:int=min0; i0<=max0; i0++) {
     *            s.set(0,i0);
     *            int min1 = s.min(1);
     *            int max1 = s.max(1);
     *            for (var i1:int=min1; i1<=max1; i1++) {
     *                ...
     *            }
     *        }
     *    }
     *
     */

    public static interface Scanner {
        def set(axis: int, position: int): void;
        def min(axis: int): int;
        def max(axis: int): int;
    }

    public abstract global def scanners(): Iterator[Scanner]!;

    // public global def scan() = new x10.array.PolyScanner(this);


    //
    // conversion
    //

    public static operator (rs:ValRail[Region(1){self.rect}]):Region(rs.length){self.rect} = make(rs);


    //
    // ops
    //

   // public global operator ! this: Region(rank) = complement();
    public global operator this && (that: Region(rank)): Region(rank) = intersection(that);
    //public global operator this || (that: Region(rank)): Region(rank) = union(that);
    //public global operator this - (that: Region(rank)): Region(rank) = difference(that);

    public global operator this * (that: Region) = product(that);

    public global operator this + (v: Point(rank)) = translate(v);
    public global operator (v: Point(rank)) + this = translate(v);

    public global operator this - (v: Point(rank)) = translate(-v);


    //
    // comparison
    //

    public global safe def equals(that:Any):boolean {
	if (this == that) return true; // short-circuit
	if (!(that instanceof Region)) return false;
	val t1 = that as Region;
	if (rank != t1.rank) return false;
        val t2 = t1 as Region(rank);
        return this.contains(t2) && t2.contains(this);
    }

    abstract public global def contains(that: Region(rank)): boolean;


    abstract public global def contains(p:Point):boolean;
    
    public global def contains(i:int){rank==1} = contains(Point.make(i));

    public global def contains(i0:int, i1:int){rank==2} = contains(Point.make(i0,i1));

    public global def contains(i0:int, i1:int, i2:int){rank==3} = contains(Point.make(i0,i1,i2));

    public global def contains(i0:int, i1:int, i2:int, i3:int){rank==4} = contains(Point.make(i0,i1,i2,i3));



    protected def this(r: int, t: boolean, z: boolean)
        :Region{self.rank==r, self.rect==t, self.zeroBased==z} {
        property(r, t, z);
    }
}

