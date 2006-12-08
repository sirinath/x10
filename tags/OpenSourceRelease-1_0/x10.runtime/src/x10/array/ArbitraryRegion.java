/*
 * Created on Jan 13, 2005
 */
package x10.array;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Set;

import x10.lang.point;
import x10.lang.region;
import x10.lang.RankMismatchException;

/**
 * @author Christoph von Praun
 */
public class ArbitraryRegion extends region {

	private final SortedSet points_;

	public ArbitraryRegion(region[] dims) {
		super(dims.length, false, false);
		int sz = dims[0].size();
		int sz_total = sz;
		for (int i = 1; i < dims.length; ++i) {
			assert dims[i].size() == sz;
			if (dims[i].rank != 1)
				throw new RankMismatchException(dims[i], 1);
			if (zeroBased)
				assert dims[i].zeroBased;
		}
		rankCache_ = new ArbitraryRegion[dims.length];
		points_ = new TreeSet();
		permutations_(points_, new int[] { }, dims);
	}

	/* create all points in the region that is spawned by the dimensions in var */
	private void permutations_(Set hs, int[] fix, region[] var) {
		if (var.length == 0) {
			point new_point = point.factory.point(fix);
			hs.add(new_point);
		} else {
			region[] var_new = new region[var.length - 1];
			System.arraycopy(var, 1, var_new, 0, var.length - 1);
			region cur_reg = var[0];
			int[] fix_new = new int[fix.length + 1];
			System.arraycopy(fix, 0, fix_new, 0, fix.length);
			for (Iterator it = cur_reg.iterator(); it.hasNext(); ) {
				point tmp = (point) it.next();
				int tmp_i = tmp.get(0);
				fix_new[fix.length] = tmp_i;
				permutations_(hs, fix_new, var_new);
			}
		}
	}

	/**
	 * Create a new ArbitraryRegion of the given rank, with the given set of points.
	 * Assume that if zeroBased is true then points contains the all-zero point of rank rank.
	 * @param rank
	 * @param points
	 * @param zeroBased
	 */
	public ArbitraryRegion(int rank, Set points) {
		super(rank, false, false);
		assert (points != null);

		for (Iterator it = points.iterator(); it.hasNext(); ) {
			java.lang.Object o = it.next();
			assert o instanceof point;
			point p = (point) o;
			if (p.rank != rank)
				throw new RankMismatchException(p, rank);
		}
		rankCache_ = new ArbitraryRegion[rank];
		points_ = new TreeSet();
		points_.addAll(points);
		
	}

	/* modifies this region - this method should be only called during the
	 * initialization or creation of an arbitrary region - hence it is private.
	 */
	private void add_(point p) {
		if (p.rank != rank)
			throw new RankMismatchException(p, rank);
		points_.add(p);
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#size()
	 */
	public int size() {
		return points_.size();
	}

	private final ArbitraryRegion[] rankCache_;

	/**
	 * @return a one-dimensional region (formerly called range)
	 */
	public region rank(int index) {
		//System.err.println("ArbitraryRegion vj index=" + index + "rank = " + rank);
		assert index >= 0;
		assert index < rank;
		ArbitraryRegion ret;

		synchronized (this) {
			ret = rankCache_[index];
		}
		if (ret == null) {
			TreeSet points = new TreeSet();
			
			for (Iterator it = iterator(); it.hasNext(); ) {
				point p = (point) it.next();
				point p_onedim = point.factory.point(new int[] { p.get(index) });
				
				points.add(p_onedim);
			}
			// add it to rankCache_ only after it is initialized
			// race on rankCache possible_!
			synchronized (this) {
				rankCache_[index] = ret;
			}
			ret = new ArbitraryRegion(1, points);
		}
		return ret;
	}

	public boolean isConvex() {
		boolean ret = true;
		if (rank == 1) {
			ret = (high() - low() + 1 == size());
		} else {
			int conv_size = 1;
			for (int i = 0; ret && i < rank; ++i) {
				region r = rank(i);
				ret &= r.isConvex();
				conv_size *= r.size();
			}
			ret &= size() == conv_size;
		}
		return ret;
	}

	/*
	 * method only menaingful for regions of one dimension (aka ranges)
	 */
	public int low() {
		if (rank != 1)
			throw new UnsupportedOperationException();
		if (points_.isEmpty()) {
			// this should never happen, beause an empty region
			// should have been constructed instead
			assert(false);
			throw new ArrayIndexOutOfBoundsException();
		}

		point p = (point) points_.first();
		return p.get(0);
	}

	/*
	 * method only meaningful for regions of one dimension (aka ranges)
	 */
	public int high() {
		if (rank != 1)
			throw new UnsupportedOperationException();
		if (points_.isEmpty())
			throw new ArrayIndexOutOfBoundsException();

		point p = (point) points_.last();
		return p.get(0);
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#union(x10.lang.region)
	 */
	public region union(region r) {
		assert r != null;
		// union() will check the rank
		return union(this, r);
	}

	protected static region union(region r1, region r2) {
		if (r1.rank != r2.rank)
			throw new RankMismatchException(r2, r1.rank);

		TreeSet points = new TreeSet();
		for (Iterator it = r1.iterator(); it.hasNext(); ) {
			point p = (point) it.next();
			points.add(p);
		}
		for (Iterator it = r2.iterator(); it.hasNext(); ) {
			point p = (point) it.next();
			points.add(p);
		}
		return new ArbitraryRegion(r1.rank, points);
	}

	/*
	 */
	public region intersection(region r) {
		assert r != null;
		// intersection() will check the rank
		return intersection(this, r);
	}

	protected static region intersection(region r1, region r2) {
		if (r1.rank != r2.rank)
			throw new RankMismatchException(r2, r1.rank);
		if (r1.size() > r2.size()) {
			// swap for efficiency
			region tmp = r2;
			r2 = r1;
			r1 = tmp;
		}

		TreeSet points = new TreeSet();
		for (Iterator it = r1.iterator(); it.hasNext(); ) {
			point p = (point) it.next();
			if (r2.contains(p))
				points.add(p);
		}
		return new ArbitraryRegion(r1.rank, points);
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#difference(x10.lang.region)
	 */
	public region difference(region r) {
		assert r != null;
		// difference() will check the rank
		return difference(this, r);
	}

	protected static region difference(region r1, region r2) {
		if (r1.rank != r2.rank)
			throw new RankMismatchException(r2, r1.rank);

		
		TreeSet points = new TreeSet();
		for (Iterator it = r1.iterator(); it.hasNext(); ) {
			point p = (point) it.next();
			if (! r2.contains(p))
				points.add(p);
		}
		ArbitraryRegion ret = new ArbitraryRegion(r1.rank, points);
		return ret;
	}

	public region convexHull() {
		region ret;
		if (rank == 1) {
			ret = new ContiguousRange(low(), high());
		} else {
			int[] mins = new int[rank];
			int[] maxs = new int[rank];
			for (int i = 0; i < rank; ++i) {
				region r = rank(i);
				mins[i] = r.low();
				maxs[i] = r.high();
			}

			region[] dims = new region[rank];
			boolean zeroBased = true;
			for (int i = 0; i < rank; ++i) {
				dims[i] = new ContiguousRange(mins[i], maxs[i]);
				zeroBased &= mins[i]==0;
			}
			ret = new MultiDimRegion(dims, zeroBased);
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#contains(x10.lang.point)
	 */
	public boolean contains(point p) {
		assert p != null;
		if (p.rank != rank)
			throw new RankMismatchException(p, rank);
		return points_.contains(p);
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#contains(int[])
	 */
	public boolean contains(int[] p) {
		assert p != null;

		boolean ret;
		if (p.length == rank) {
			point pp = point.factory.point(p);
			ret = contains(pp);
		} else
			ret = false;
		return ret;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#disjoint(x10.lang.region)
	 */
	public boolean disjoint(region r) {
		assert r != null;
		// intersection() will check the rank
		return intersection(r).size() == 0;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#ordinal(x10.lang.point)
	 */
	public int ordinal(point p) throws ArrayIndexOutOfBoundsException, ArrayIndexOutOfBoundsException {
		assert p != null;
		if (p.rank != rank)
			throw new RankMismatchException(p, rank);

		int ret = 0;
		if (size() == 0)
			throw new ArrayIndexOutOfBoundsException();
		if (!contains(p))
			throw new ArrayIndexOutOfBoundsException();
		else {
			for (Iterator it = iterator(); it.hasNext(); ) {
				point q = (point) it.next();
				if (q.equals(p))
					break;
				else
					ret++;
			}
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#coord(int)
	 */
	public point coord(int ord) throws ArrayIndexOutOfBoundsException {
		assert ord >= 0;

		point ret;
		if (ord >= size())
			throw new ArrayIndexOutOfBoundsException();
		else {
			int ctr = 0;
			Iterator it = iterator();
			while (ctr++ < ord) it.next();
			ret = (point) it.next();
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see x10.lang.region#iterator()
	 */
	public Iterator iterator() {
		return points_.iterator();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (Iterator it = iterator(); it.hasNext(); ) {
			sb.append(it.next().toString());
			if (it.hasNext())
				sb.append(",");
		}
		sb.append("}");
		return sb.toString();
	}

	

	
}

