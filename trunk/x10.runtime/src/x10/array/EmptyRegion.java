/*
 * Created by vj on Jan 4, 2005
 *
 * 
 */
package x10.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import x10.lang.Object;
import x10.lang.point;
import x10.lang.region;
import x10.lang.PointOutOfRegionError;

/** The empty region of rank k. There is only one unique object in each such type.
 * @author vj Jan 4, 2005
 * 
 */
public class EmptyRegion extends region {

	/**
	 * @param rank
	 */
	public EmptyRegion(long rank) {
		super(rank);
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#size()
	 */
	public long size() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#rank(long)
	 */
	public region rank(long index) {
		return new EmptyRegion(1);
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#isConvex()
	 */
	public boolean isConvex() {
		return true;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#low()
	 */
	public int low() throws EmptyRegionError {
		throw new EmptyRegionError();
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#high()
	 */
	public int high() throws EmptyRegionError {
		throw new EmptyRegionError();
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#union(x10.lang.region)
	 */
	public region union(region r) {
		assert this.rank == r.rank;
		return r;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#intersection(x10.lang.region)
	 */
	public region intersection(region r) {
		assert this.rank == r.rank;
		return this;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#difference(x10.lang.region)
	 */
	public region difference(region r) {
		assert this.rank == r.rank;
		return this;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#convexHull()
	 */
	public region convexHull() {
		return this;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#contains(x10.lang.region)
	 */
	public boolean contains(region r) {
		assert this.rank == r.rank;
		return false;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#contains(x10.lang.point)
	 */
	public boolean contains(point p) {
		assert this.rank == p.region.rank;
		return false;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#disjoint(x10.lang.region)
	 */
	public boolean disjoint(region r) {
		assert this.rank == r.rank;
		return true;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#ordinal(x10.lang.point)
	 */
	public long ordinal(point p) throws EmptyRegionError {
		throw new EmptyRegionError();
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#coord(long)
	 */
	public point coord(long ord) throws PointOutOfRegionError {
		throw new PointOutOfRegionError();
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#iterator()
	 */
	public Iterator iterator() {

		class RegionIterator implements Iterator {
			
			public boolean hasNext() {
				return false;
			}
			
			public void remove() {
				throw new Error("not implemented");
			}
			
			public java.lang.Object next() {
				throw new Error("No such element.");
			}
		}

		return new Iterator() {
			public boolean hasNext() { 
				return false; 
			}
			public void remove() throws UnsupportedOperationException { 
				throw new UnsupportedOperationException();
			}
			public java.lang.Object next() throws NoSuchElementException { 
				throw new NoSuchElementException();
				}
		};
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "EmptyRegion("+rank+")";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (! (o instanceof EmptyRegion)) return false;
		EmptyRegion oe = (EmptyRegion)o;
		return oe.rank == this.rank;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (int) this.rank;
	}

}
