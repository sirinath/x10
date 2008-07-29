package x10.array;

import java.util.Iterator;

import x10.lang.Point;
import x10.lang.Region;
import x10.lang.IllegalOperationException;


public abstract class BaseRegion extends Region {

    //
    // public static API - basic region construction
    //

    static public Region makeEmpty(int rank) {
        return new EmptyRegion(rank);
    }

    static public Region makeFull(int rank) {
        return new FullRegion(rank);
    }

    static public Region makeUnit() {
        return new FullRegion(0);
    }

    static public Region makeRectangular(int [] min, int [] max) {
        return new RectRegion(min, max);
    }        

    static public Region makeRectangular(int min, int max) {
        return new RectRegion(min, max);
    }        

    static public Region makeDiagonal(int size, int upper, int lower) {
        return PolyRegion.makeDiagonal(size, upper, lower);
    }

    static public Region makeDiagonal(int size) {
        return PolyRegion.makeDiagonal(size, 1, 1);
    }

    // XXX testing only; add to Region if needed externally
    static public Region makeDiagonal(int rowMin, int colMin, int rowMax, int colMax, int upper, int lower) {
        return PolyRegion.makeDiagonal(rowMin, colMin, rowMax, colMax, upper, lower);
    }

    static public Region makeUpperTriangular(int size) {
        // XXX
        return PolyRegion.makeDiagonal(size, size, 1);
    }

    static public Region makeLowerTriangular(int size) {
        // XXX
        return PolyRegion.makeDiagonal(size, 1, size);
    }

    static public Region makeUpperTriangular(int rowMin, int colMin, int size) {
        return PolyRegion.makeDiagonal(rowMin, colMin, size, true);
    }

    static public Region makeLowerTriangular(int rowMin, int colMin, int size) {
        return PolyRegion.makeDiagonal(rowMin, colMin, size, false);
    }

    static public Region make(Region [] regions) {
        Region r = regions[0];
        for (int i=1; i<regions.length; i++)
            r = r.product(regions[i]);
        return r;
    }


    //
    // public region API - basic information
    //

    
    public boolean isRect() {
        return false;
    }

    public boolean isZeroBased() {
        return false;
    }


    public boolean disjoint(Region r) {
        throw U.unsupported();
    }

    public boolean isConvex() {
        throw U.unsupported();
    }

    public int size() {
        throw U.unsupported();
    }


    //
    // public region API - composite region construction
    //

    public Region union(Region r) {
        throw U.unsupported();
    }

    public Region intersection(Region r) {
        throw U.unsupported();
    }

    public Region difference(Region r) {
        throw U.unsupported();
    }

    public Region product(Region r) {
        throw U.unsupported();
    }

    public Region projection(int axis) {
        throw U.unsupported();
    }

    public Region boundingBox() {
        throw U.unsupported();
    }



    //
    // public region API - comparison operations
    //

    public boolean contains(Region r) {
        throw U.unsupported();
    }

    public boolean equals(Region r) {
        throw U.unsupported();
    }


    public java.util.Iterator scanners() {
        throw U.unsupported();
    }

    public Region.Iterator iterator() {
        throw U.unsupported();
    }


    //
    // debugging
    //

    public void printInfo(String label) {
        U.pr(label + ": BaseRegion");
    }


    //
    //
    //

    protected BaseRegion(int rank) {
        super(rank);
    }
}

