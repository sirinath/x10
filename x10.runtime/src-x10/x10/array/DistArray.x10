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

import x10.compiler.Header;
import x10.compiler.Inline;
import x10.compiler.Native;
import x10.compiler.NoInline;
import x10.compiler.NoReturn;

import x10.util.IndexedMemoryChunk;

/**
 * This class represents an array with raw chunk in each place,
 * initialized at its place of access via a PlaceLocalHandle.
 */
public class DistArray[T] (
    /**
     * The distribution of this array.
     */
    dist:Dist
) implements (Point(dist.region.rank))=>T,
             Iterable[Point(dist.region.rank)]
{

    private static class LocalState[T] {
        val layout:RectLayout;
        val raw:IndexedMemoryChunk[T];

        def this(l:RectLayout, r:IndexedMemoryChunk[T]) {
            layout = l;
            raw = r;
        }
    };


   //
    // properties
    //

    // region via dist
    /**
     * The region this array is defined over.
     */
    public global property region: Region(rank) = dist.region;

    /**
     * The rank of this array.
     */
    public global property rank: int = dist.rank;

    /**
     * Is this array defined over a rectangular region?
     */
    public global property rect: boolean = dist.rect;

    /**
     * Is this array's region zero-based?
     */
    public global property zeroBased: boolean = dist.zeroBased;

    // dist
    /**
     * Is this array's region a "rail" (one-dimensional contiguous zero-based)?
     */
    public global property rail: boolean = dist.rail;

    /**
     * Is this array's distribution "unique" (at most one point per place)?
     */
    public global property unique: boolean = dist.unique;

    /**
     * Is this array's distribution "constant" (all points map to the same place)?
     */
    public global property constant: boolean = dist.constant;

    /**
     * If this array's distribution is "constant", the place all points map to (or null).
     */
    public global property onePlace: Place = dist.onePlace;


 

    //
    // factories for dist arrays 
    //

    /**
     * Create a mutable array over the given distribution and default initial values for elements.
     *
     * @param T the element type
     * @param dist the given distribution
     * @return a mutable array with the given distribution.
     * @see #make[T](Region)
     * @see #make[T](Dist, (Point)=>T)
     */
    public static def make[T](dist: Dist)= new DistArray[T](dist);

    /**
     * Create a mutable array over the given distribution.
     * Executes the given initializer function for each element of the array.
     *
     * @param T the element type
     * @param dist the given distribution
     * @param init the initializer function
     * @return a mutable array with the given distribution.
     * @see #make[T](Dist)
     * @see #make[T](Region, (Point)=>T)
     */
    public static def make[T](dist: Dist, init: (Point(dist.rank))=>T)= new DistArray[T](dist, init);




    private global val localHandle:PlaceLocalHandle[LocalState[T]];
    final protected global def raw():IndexedMemoryChunk[T] = localHandle().raw;
    final protected global def layout() = localHandle().layout;


    @Native("java", "(!`NO_CHECKS`)")
    @Native("c++", "BOUNDS_CHECK_BOOL")
    private global safe native def checkBounds():boolean;

    @Native("java", "(!`NO_CHECKS`)")
    @Native("c++", "PLACE_CHECK_BOOL")
    private global safe native def checkPlace():boolean;


    public final safe global def apply(pt: Point(rank)): T {
        if (checkBounds() && !region.contains(pt)) {
            raiseBoundsError(pt);
        }
        if (checkPlace() && dist(pt) != here) {
            raisePlaceError(pt);
        }
        return raw()(layout().offset(pt));
    }

    /**
     * @deprecated
     */
    public final safe global def get(pt: Point(rank)): T = apply(pt);

    final public safe global def apply(i0: int){rank==1}: T {
        if (checkBounds() && !region.contains(i0)) {
            raiseBoundsError(i0);
        }
        if (checkPlace() && dist(i0) != here) {
            raisePlaceError(i0);
        }
        return raw()(layout().offset(i0));
    }

    final public safe global def apply(i0: int, i1: int){rank==2}: T {
        if (checkBounds() && !region.contains(i0, i1)) {
            raiseBoundsError(i0, i1);
        }
        if (checkPlace() && dist(i0, i1) != here) {
            raisePlaceError(i0,i1);
        }
        return raw()(layout().offset(i0,i1));
    }

    final public safe global def apply(i0: int, i1: int, i2: int){rank==3}: T {
        if (checkBounds() && !region.contains(i0, i1, i2)) {
            raiseBoundsError(i0, i1, i2);
        }
        if (checkPlace() && dist(i0,i1,i2) != here) {
            raisePlaceError(i0,i1,i2);
        }
        return raw()(layout().offset(i0,i1,i2));
    }

    final public safe global def apply(i0: int, i1: int, i2: int, i3: int){rank==4}: T {
        if (checkBounds() && !region.contains(i0, i1, i2, i3)) {
            raiseBoundsError(i0, i1, i2, i3);
        }
        if (checkPlace() && dist(i0,i1,i2,i3) != here) {
            raisePlaceError(i0,i1,i2,i3);
        }
        return raw()(layout().offset(i0,i1,i2,i3));
    }


    // XXXX settable order
    public final safe global def set(v: T, pt: Point(rank)): T {
        if (checkBounds() && !region.contains(pt)) {
            raiseBoundsError(pt);
        }
        if (checkPlace() && dist(pt) != here) {
            raisePlaceError(pt);
        }
        val r = raw();
        r(layout().offset(pt)) = v;
        return v;
    }

    final public safe global def set(v: T, i0: int){rank==1}: T {
        if (checkBounds() && !region.contains(i0)) {
            raiseBoundsError(i0);
        }
        if (checkPlace() && dist(i0) != here) {
            raisePlaceError(i0);
        }
        raw()(layout().offset(i0)) = v;
        return v;
    }

    final public safe global def set(v: T, i0: int, i1: int){rank==2}: T {
        if (checkBounds() && !region.contains(i0, i1)) {
            raiseBoundsError(i0, i1);
        }
        if (checkPlace() && dist(i0,i1) != here) {
            raisePlaceError(i0,i1);
        }
        raw()(layout().offset(i0,i1)) = v;
        return v;
    }

    final public safe global def set(v: T, i0: int, i1: int, i2: int){rank==3}: T {
        if (checkBounds() && !region.contains(i0, i1, i2)) {
            raiseBoundsError(i0, i1, i2);
        }
        if (checkPlace() && dist(i0,i1,i2) != here) {
            raisePlaceError(i0,i1,i2);
        }
        raw()(layout().offset(i0,i1,i2)) = v;
        return v;
    }

    final public safe global def set(v: T, i0: int, i1: int, i2: int, i3: int){rank==4}: T {
        if (checkBounds() && !region.contains(i0, i1, i2, i3)) {
            raiseBoundsError(i0, i1, i2, i3);
        }
        if (checkPlace() && dist(i0,i1,i2,i3) != here) {
            raisePlaceError(i0,i1,i2,i3);
        }
        raw()(layout().offset(i0,i1,i2,i3)) = v;
        return v;
    }

    def this(dist: Dist, init: (Point(dist.rank))=>T): DistArray[T]{self.dist==dist} {
        property(dist);

        val plsInit:()=>LocalState[T]! = () => {
            val region = dist.get(here);
            val localLayout = layout(region);
            val localRaw = IndexedMemoryChunk.allocate[T](localLayout.size());

            for (pt  in region) {
               localRaw(localLayout.offset(pt)) = init(pt as Point(dist.rank));
            }

            return new LocalState[T](localLayout, localRaw);
        };

        localHandle = PlaceLocalHandle.make[LocalState[T]](dist, plsInit);
    }
    def this(dist: Dist): DistArray[T]{self.dist==dist} {
        property(dist);

        val plsInit:()=>LocalState[T]! = () => {
            val region = dist.get(here);
            val localLayout = layout(region);
            val localRaw = IndexedMemoryChunk.allocate[T](localLayout.size());

	    return new LocalState[T](localLayout, localRaw);
        };

        localHandle = PlaceLocalHandle.make[LocalState[T]](dist, plsInit);
    }


    /*
     * restriction view
     */

    public safe global def restriction(d: Dist(rank)) {
        return new DistArray[T](this, d) as DistArray[T](rank);
    }

    def this(a: DistArray[T], d: Dist):DistArray{self.dist==d} {
    	property(d);
    	localHandle = PlaceLocalHandle.make[LocalState[T]](d,
    			() => a.localHandle());
    }


///// TODO: BELOW HERE IS CODE PULLED IN FROM BaseArray.  Need to reorgzinze this.

    //
    // views
    //

    public safe global def restriction(r: Region(rank)): DistArray[T](rank) {
        return restriction(dist.restriction(r) as Dist(rank));
    }

    public safe global def restriction(p: Place): DistArray[T](rank) {
        return restriction(dist.restriction(p) as Dist(rank));
    }


    //
    // operations
    //

    public global def map(op:(T)=>T): DistArray[T](dist)
        = make[T](dist, ((p:Point)=>op(this(p as Point(rank)))));

    public global def map(r:Region(rank), op:(T)=>T): DistArray[T]
        = make[T](dist | r, ((p:Point)=>op(this(p as Point(rank)))));


    public global def map(src:DistArray[T](this.dist), op:(T,T)=>T):DistArray[T](dist)
        = make[T](dist, ((p:Point)=>op(this(p as Point(rank)), src(p as Point(rank)))));

    public global def map(src:DistArray[T](this.dist), r:Region(rank), op:(T,T)=>T):DistArray[T](rank)
        = make[T]((dist | r) as Dist(rank), ((p:Point)=>op(this(p as Point(rank)), src(p as Point(rank)))));

    public global def reduce(op:(T,T)=>T, unit:T):T {

        // scatter
        val ps:ValRail[Place] = dist.places();
        val results = Rail.make[T](ps.length, (p:Int) => unit);
        val r = 0..(ps.length-1);
        
        
	finish foreach (p:Point(1)  in r) {
        	results(p(0)) = at (ps(p(0))) {
        	    var result: T = unit;
                val a = (this | here) as DistArray[T](rank);
                for (pt:Point(dist.region.rank)  in a.region)
                    result = op(result, a(pt));
                return result;
            };
        }

        // gather
        var result: T = unit;
        for (var i:int = 0; i < results.length; i++) 
            result = op(result, results(i));

        return result;
    }            

/*
    public global def reduce(op:(T,T)=>T, unit:T):T {

        // scatter
        val ps = dist.places();
        val results = ValRail.make[Future[T]](ps.length, (p:Int) => {
            future(ps(p)) {
                var result: T = unit;
                val a = (this | here) as DistArray[T](rank);
                for (pt:Point(rank) in a)
                    result = op(result, a(pt));
                return result;
            }
        });

        // gather
        var result: T = unit;
        for (var i:int = 0; i < results.length; i++) 
            result = op(result, results(i).force());

        return result;
    }            
*/

    incomplete public global def scan(op:(T,T)=>T, unit:T): DistArray[T](dist);


    //
    // ops
    //

    public safe global operator this | (r: Region(rank)) = restriction(r);
    public safe global operator this | (p: Place) = restriction(p);



    /**
     * for now since we only have RectLayouts we hard-code that here
     * for efficiency, since RectLayout is a final class.
     *
     * if/when we have other layouts, this might need to be a generic
     * type parameter, i.e. BaseArray[T,L] where L is a layout class
     */

    // safe to call from witin a constructor, does not read fields.
    //protected proto global def layout(r: Region): RectLayout {
    protected static def layout(r: Region): RectLayout {
        if (r.isEmpty()) {
            // XXX EmptyLayout class?
            val min = ValRail.make[int](r.rank, (Int)=>0);
            val max = ValRail.make[int](r.rank, (Int)=>-1);
            return RectLayout(min, max);
        } else {
            return RectLayout(r.min(), r.max());
        }
    }

    public global safe def toString(): String {
        return "Array(" + dist + ")";
    }


    /**
     * Return an iterator over the points in the region of this array.
     *
     * @return an iterator over the points in the region of this array.
     * @see x10.lang.Iterable[T]#iterator()
     */
    public global def iterator(): Iterator[Point(rank)] = region.iterator() as Iterator[Point(rank)];


    private global safe @NoInline @NoReturn def raiseBoundsError(i0:int) {
        throw new ArrayIndexOutOfBoundsException("point (" + i0 + ") not contained in array");
    }    
    private global safe @NoInline @NoReturn def raiseBoundsError(i0:int, i1:int) {
        throw new ArrayIndexOutOfBoundsException("point (" + i0 + ", "+i1+") not contained in array");
    }    
    private global safe @NoInline @NoReturn def raiseBoundsError(i0:int, i1:int, i2:int) {
        throw new ArrayIndexOutOfBoundsException("point (" + i0 + ", "+i1+", "+i2+") not contained in array");
    }    
    private global safe @NoInline @NoReturn def raiseBoundsError(i0:int, i1:int, i2:int, i3:int) {
        throw new ArrayIndexOutOfBoundsException("point (" + i0 + ", "+i1+", "+i2+", "+i3+") not contained in array");
    }    
    private global safe @NoInline @NoReturn def raiseBoundsError(pt:Point(rank)) {
        throw new ArrayIndexOutOfBoundsException("point " + pt + " not contained in array");
    }    


    private global safe @NoInline @NoReturn def raisePlaceError(i0:int) {
        throw new BadPlaceException("point (" + i0 + ") not defined at " + here);
    }    
    private global safe @NoInline @NoReturn def raisePlaceError(i0:int, i1:int) {
        throw new BadPlaceException("point (" + i0 + ", "+i1+") not defined at " + here);
    }    
    private global safe @NoInline @NoReturn def raisePlaceError(i0:int, i1:int, i2:int) {
        throw new BadPlaceException("point (" + i0 + ", "+i1+", "+i2+") not defined at " + here);
    }    
    private global safe @NoInline @NoReturn def raisePlaceError(i0:int, i1:int, i2:int, i3:int) {
        throw new BadPlaceException("point (" + i0 + ", "+i1+", "+i2+", "+i3+") not defined at " + here);
    }    
    private global safe @NoInline @NoReturn def raisePlaceError(pt:Point(rank)) {
        throw new BadPlaceException("point " + pt + " not defined at " + here);
    }    
}

// vim:tabstop=4:shiftwidth=4:expandtab
