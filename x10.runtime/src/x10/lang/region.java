package x10.lang;

import java.util.Iterator;
import java.util.List;

import x10.array.Range;
import x10.array.ContiguousRange;

/**
 *  A region represents a (sparse or dense) k-dimensional space of
 * points. A region is a dependent class, with the value parameter
 * specifying the dimension of the region.  A convex k-dimensional
 * region is easy to represent, e.g. as a list of k (min, max)
 * pairs. In general, regions may not be convex. (For instance
 * difference( region(dimension) r) produces non-convex regions.)
 * Non-convex regions are very important for many physical
 * problems. For instance the region of the halo around a 2-d array is
 * non-convex.
 
 * @author vj
 */
public abstract /*value*/ class region extends Object  {
	// nat is translated to long for now.
	public final /*nat*/ long rank;
	
	public static class EmptyRegionError extends Error {}
	public static abstract /*value*/ class factory {
		/** Create a region of zero ranks. This is an empty
		 * region of size 0.
		 */
		public abstract region /*(k)*/ emptyRegion(/*nat*/ long k);
		
		/**  Construct a 1-dimensional region 1..high, with stride 1.
		 */
		public region/*(1)*/ region( int high ) {
			return region(1, high, 1);
		}
		/** Construct a 1-dimensional region low..high with stride 1. 
		 */
		public region/*(1)*/ region( int low, int high ) {
			final region/*(1)*/ result = region(low, high, 1);
			assert result.rank == 1;
			return region(low, high, 1);
		}
		
		/** Construct a 1-dimensional region, low..high with the given stride.
		 */
		public abstract region/*(1)*/ region(int low, int high, int stride);
		
		public region/*(2)*/ upperTriangular( /*nat*/ long size ) {
			final region/*(2)*/ result = upperTriangular(2, size);
			assert result.rank==2;
			return result;
		}
		public region/*(2)*/ lowerTriangular( /*nat*/ long size ) {
			final region/*2*/ result = lowerTriangular(2, size);
			assert result.rank==2;
			return result;
		}
		public region/*(2)*/ banded( /*nat*/ long  size, /*nat*/ long width) {
			final region/*(2)*/ result = banded(2, size, width);
			assert result.rank==2;
			return result;
		}
		
		/** Construct a region, using the list of region(1)'s passed as
		 * arguments to the constructor.
		 */
		public abstract region/*(regions.length)*/ region(region/*(1)*/[] regions );
		
		/** Return an \code{upperTriangular} region for a dim-dimensional
		 * space of size \code{size} in each dimension.
		 */
		public abstract region/*(dim)*/ upperTriangular( /*nat*/ long dim, /*nat*/ long size );
		
		/** Return a lowerTriangular region for a dim-dimensional space of
		 * size \code{size} in each dimension.
		 */
		public abstract region/*(dim)*/ lowerTriangular( /*nat*/ long dim, /*nat*/ long size );
		
		/** Return a banded region of width {\code width} for a
		 * dim-dimensional space of size {\code size} in each dimension.
		 */
		public abstract region/*(dim)*/ banded( /*nat*/ long dim, /*nat*/ long size, /*nat*/ long width);
		
	}
	
	public static final factory factory = Runtime.factory.getRegionFactory();
	
	protected region( /*nat*/long rank ) {
		this.rank = rank;
	}
	
	
	/**  Return the number of points in this region.
	 */
	abstract public /*nat*/long size();
	
	/** Use modular arithmetic on the index to determine the dimension
	 to return.
	 */
	abstract public region/*(1)*/ rank( /*nat*/long  index ); 
	
	/** Returns true iff the region contains every point between two
	 * points in the region.
	 */
	abstract public boolean isConvex();
	
	/** Return the low bound for a 1-dimensional region. Can only be
	 * invoked on 1-dimensional objects.
	 */
	abstract public /*(:rank==1)*/ int low() throws EmptyRegionError;
	
	/** Return the high bound for a 1-dimensional region. Can only be
	 * invoked on 1-dimensional objects.
	 */
	abstract public /*(:rank==1)*/ int high() throws EmptyRegionError;
	
	abstract public region/*(rank)*/ union( region/*(rank)*/ r);
	abstract public region/*(rank)*/ intersection( region/*(rank)*/ r);
	abstract public region/*(rank)*/ difference( region/*(rank)*/ r);
	abstract public region/*(rank)*/ convexHull();
	
	/**
	 Returns true iff this is a superset of r.
	 */
	abstract public boolean contains( region/*(rank)*/ r);
	/**
	 Returns true iff this region contains this point.
	 */
	abstract public boolean contains( point/*(rank)*/ p);
	/**
	 Returns true iff this is disjoint from r.
	 */
	abstract public boolean disjoint( region/*(rank)*/ r);
	
	/** Returns true iff the set of points in r and this are equal.
	 */
	public boolean equal( region/*(rank)*/ r) {
		return this.contains(r) && r.contains(this);
	}
	
	/**
	 * @param p a point in the coordinate space
	 * @return the ordinal number of the point [0 ... size()[
	 */
	abstract public /*nat*/long ordinal(point/*(rank)*/ p) throws EmptyRegionError;
	
	/**
	 * @param ord the ordinal number, must be smaller than size()
	 * @return the coordinate that has ordinal number ord
	 */
	public abstract point/*(rank)*/ coord(/*nat*/ long ord) throws PointOutOfRegionError;
	
	/**
	 * @return Iterator that yields the individual points of a region in lexicographical
	 * order. 
	 */
	abstract public Iterator iterator();
	abstract public String toString(); 
	abstract public boolean equals(Object o);
	abstract public  int hashCode();
	
}
