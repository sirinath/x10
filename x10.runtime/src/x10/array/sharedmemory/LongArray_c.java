/*
 * Created on Oct 20, 2004
 */
package x10.array.sharedmemory;

import java.util.Iterator;
import x10.array.LongArray;
import x10.array.Operator;
import x10.array.Distribution_c;
import x10.base.Allocator;
import x10.base.MemoryBlock;
import x10.base.UnsafeContainer;
import x10.lang.Indexable;
import x10.lang.Runtime;
import x10.lang.place;
import x10.lang.point;
import x10.base.MemoryBlockSafeLongArray;
import x10.lang.dist;
import x10.lang.region;
import x10.lang.LongReferenceArray;
import x10.runtime.Configuration;
import x10.array.Helper;

/**
 * @author Christian Grothoff, Christoph von Praun
 */
public class LongArray_c extends LongArray implements UnsafeContainer {

	private final boolean safe_;
	private final MemoryBlock arr_;
	public final boolean mutable_;

	public boolean valueEquals(Indexable other) {
		return arr_.valueEquals(((LongArray_c)other).arr_);
	}

	/**
	 * This constructor must not be used directly by an application programmer.
	 * Arrays are constructed by the corresponding factory methods in
	 * x10.lang.Runtime.
	 */
	protected LongArray_c(dist d, Operator.Pointwise c, boolean safe) {
		this(d, c, safe, true);
	}
	public LongArray_c(dist d, Operator.Pointwise c, boolean safe, boolean mutable, boolean ignored) {
		this(d, c, safe, mutable);
	}
	protected LongArray_c(dist d, Operator.Pointwise c, boolean safe, boolean mutable) {
		this(d, safe, mutable, null);
		if (c != null)
			scan(this, c);
	}

	/**
	 * Create a new array per the given distribution, initialized to c.
	 *
	 * @param d
	 * @param c
	 * @param safe
	 */
	public LongArray_c(dist d, long c) {
		this(d, c, true);
	}
	public LongArray_c(dist d, long c, boolean safe) {
		this(d, c, safe, true);
	}
	public LongArray_c(dist d, long c, boolean safe, boolean mutable) {
		this(d, safe, mutable, null);
		scan(this, new Constant(c));
	}

	private LongArray_c(dist d, boolean safe, boolean mutable, long[] a) {
		super(d);
		assert (d instanceof Distribution_c);
		this.safe_ = safe;
		this.mutable_ = mutable;
		int count =  d.region.size();
		if (!safe) {
			int rank = d.region.rank;
			int ranks[] = new int[rank];
			for (int i = 0; i < rank; ++i)
				ranks[i] = d.region.rank(i).size();
			this.arr_ = Allocator.allocUnsafe(count, ranks, Long.TYPE);
		} else if (a != null) {
			this.arr_ = Allocator.allocSafeLongArray(a);
		} else {
			this.arr_ = Allocator.allocSafe(count, Long.TYPE);
		}
	}

	public LongArray_c(dist d, long[] a, boolean safe, boolean mutable) {
		this(d, safe, mutable, a);
	}

	/**
	 * Return a safe LongArray_c initialized with the given local 1-d (Java) long array.
	 * TODO: Expose this through the factory class.
	 *
	 * @param a
	 * @return
	 */
	public static LongArray_c LongArray_c(long[] a) {
		dist d = Runtime.factory.getDistributionFactory().local(a.length);
		return new LongArray_c(d, a, true, true);
	}

	public void keepItLive() {}

        public long[] getBackingArray() { 
        return (arr_ instanceof MemoryBlockSafeLongArray) ?
    		((MemoryBlockSafeLongArray) arr_).getBackingArray()
			: null; }

       public int[] getDescriptor() {
          return arr_.getDescriptor();
       }

	public long getUnsafeAddress() {
		return arr_.getUnsafeAddress();
	}

	public long getUnsafeDescriptor() {
		return arr_.getUnsafeDescriptor();
	}

	/* Overrides the superclass method - this implementation is more efficient */
	public void reduction(Operator.Reduction op) {
		int count = arr_.count();
		for (int i  = 0; i < count; ++i)
			op.apply(arr_.getLong(i));
	}

	/* Overrides the superclass method - this implementation is more efficient */
	protected void assign(LongArray rhs) {
		assert rhs instanceof LongArray_c;

		LongArray_c rhs_t = (LongArray_c) rhs;
		if (rhs.distribution.equals(distribution)) {
			int count = arr_.count();
			for (int i  = 0; i < count; ++i)
				arr_.setLong(rhs_t.arr_.getLong(i), i);
		} else
			// fall back to generic implementation
			super.assign(rhs);
	}

	protected LongArray newInstance(dist d) {
		assert d instanceof Distribution_c;
		return new LongArray_c(d, (Operator.Pointwise) null, safe_);
	}

	protected LongArray newInstance(dist d, Operator.Pointwise c) {
		assert d instanceof Distribution_c;
		return new LongArray_c(d, c, safe_);
	}

	public LongReferenceArray lift(Operator.Binary op, x10.lang.longArray arg) {
		assert arg.distribution.equals(distribution);
		LongArray arg1 = (LongArray)arg;
		LongArray result = newInstance(distribution);
		place here = x10.lang.Runtime.runtime.currentPlace();
		try {
			for (Iterator it = distribution.region.iterator(); it.hasNext();) {
				point p = (point) it.next();
				place pl = distribution.get(p);
				x10.lang.Runtime.runtime.setCurrentPlace(pl);
				result.set(op.apply(this.get(p), arg1.get(p)),p);
			}
		} finally {
			x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
		return result;
	}

	public LongReferenceArray lift(Operator.Unary op) {
		LongArray result = newInstance(distribution);
		place here = x10.lang.Runtime.runtime.currentPlace();
		try {
			for (Iterator it = distribution.region.iterator(); it.hasNext();) {
				point p = (point) it.next();
				place pl = distribution.get(p);
				x10.lang.Runtime.runtime.setCurrentPlace(pl);
				result.set(op.apply(this.get(p)),p);
			}
		} finally {
			x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
		return result;
	}

	public long reduce(Operator.Binary op, long unit) {
		long result = unit;
		place here = x10.lang.Runtime.runtime.currentPlace();
		try {
			for (Iterator it = distribution.region.iterator(); it.hasNext();) {
				point p = (point) it.next();
				place pl = distribution.get(p);
				x10.lang.Runtime.runtime.setCurrentPlace(pl);
				result = op.apply(this.get(p), result);
			}
		} finally {
			x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
		return result;
	}

	public LongReferenceArray scan(Operator.Binary op, long unit) {
		long temp = unit;
		LongArray result = newInstance(distribution);
		place here = x10.lang.Runtime.runtime.currentPlace();
		try {
			for (Iterator it = distribution.region.iterator(); it.hasNext();) {
				point p = (point) it.next();
				place pl = distribution.get(p);
				x10.lang.Runtime.runtime.setCurrentPlace(pl);
				temp = op.apply(this.get(p), temp);
				result.set(temp, p);
			}
		} finally {
			x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see x10.lang.LongArray#set(int, int[])
	 */
	public long set(long v, point pos) { return set(v,pos,true,true);}
	public long set(long v, point pos,boolean chkPl,boolean chkAOB) {
		if (Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(pos));

		return arr_.setLong(v, (int) distribution.region.ordinal(pos));
	}

	/**
	 * The cannonical index has already be calculated and adjusted.
	 * Can be used by any dimensioned array.
	 */
	public long setOrdinal(long v, int rawIndex) {
		return arr_.setLong(v,rawIndex);
	}

	public long set(long v, int d0) {return set(v,d0,true,true);}
	public long set(long v, int d0,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(d0));
		d0 = Helper.ordinal(distribution,d0,chkAOB);
		return arr_.setLong(v,d0);
	}


	public long set(long v, int d0,int d1) {return set(v,d0,d1,true,true);}
	public long set(long v, int d0, int d1,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(d0, d1));
		int	theIndex = Helper.ordinal(distribution,d0,d1,chkAOB);
		return arr_.setLong(v,theIndex);
	}

	public long set(long v, int d0,int d1,int d2) {return set(v,d0,d1,d2,true,true);}
	public long set(long v, int d0, int d1, int d2,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(d0, d1, d2));
		int	theIndex = Helper.ordinal(distribution,d0,d1,d2,chkAOB);
		return arr_.setLong(v,theIndex);
	}

	public long set(long v, int d0,int d1,int d2,int d3) {return set(v,d0,d1,d2,d3,true,true);}
	public long set(long v, int d0, int d1, int d2, int d3,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(d0, d1, d2, d3));
		int	theIndex = Helper.ordinal(distribution,d0,d1,d2,d3,chkAOB);
		return arr_.setLong(v,theIndex);
	}

	/* (non-Javadoc)
	 * @see x10.lang.LongArray#get(int[])
	 */
	public long get(point pos) {return get(pos,true,true);}
	public long get(point pos,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(pos));
		return arr_.getLong((int) distribution.region.ordinal(pos));
	}

	/**
	 * The cannonical index has already be calculated and adjusted.
	 * Can be used by any dimensioned array.
	 */
	public long getOrdinal(int rawIndex) {
		return arr_.getLong(rawIndex);
	}

	public long get(int d0) {return get(d0,true,true);}
	public long get(int d0,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(d0));
		d0 = Helper.ordinal(distribution,d0,chkAOB);
		return arr_.getLong(d0);
	}

	public long get(int d0,int d1) {return get(d0,d1,true,true);}
	public long get(int d0, int d1,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(d0, d1));
		int	theIndex = Helper.ordinal(distribution,d0,d1,chkAOB);
		return arr_.getLong(theIndex);
	}

	public long get(int d0,int d1,int d2) {return get(d0,d1,d2,true,true);}
	public long get(int d0, int d1, int d2,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(d0,d1,d2));
		int	theIndex = Helper.ordinal(distribution,d0,d1,d2,chkAOB);
		return arr_.getLong(theIndex);
	}

	public long get(int d0,int d1,int d2,int d3) {return get(d0,d1,d2,d3,true,true);}
	public long get(int d0, int d1, int d2, int d3,boolean chkPl,boolean chkAOB) {
		if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
			Runtime.hereCheckPlace(distribution.get(d0, d1, d2, d3));
		int	theIndex = Helper.ordinal(distribution,d0,d1,d2,d3,chkAOB);
		return arr_.getLong(theIndex);

	}

	public long get(int[] pos) {return get(pos,true,true);}
	public long get(int[] pos,boolean chkPl,boolean chkAOB) {
		final point p = Runtime.factory.getPointFactory().point(pos);
		return get(p);
	}

	public LongReferenceArray overlay(x10.lang.longArray d) {
		dist dist = distribution.overlay(d.distribution);
		LongArray_c ret = new LongArray_c(dist, 0, safe_);
		place here = x10.lang.Runtime.runtime.currentPlace();
		try {
			for (Iterator it = dist.iterator(); it.hasNext(); ) {
				point p = (point) it.next();
				place pl = dist.get(p);
				x10.lang.Runtime.runtime.setCurrentPlace(pl);
				long val = (d.distribution.region.contains(p)) ? d.get(p) : get(p);
				ret.set(val, p);
			}
		} finally {
			x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
		return ret;
	}

	public void update(x10.lang.longArray d) {
		assert (region.contains(d.region));
		place here = x10.lang.Runtime.runtime.currentPlace();
		try {
			for (Iterator it = d.iterator(); it.hasNext(); ) {
				point p = (point) it.next();
				place pl = distribution.get(p);
				x10.lang.Runtime.runtime.setCurrentPlace(pl);
				long val = (d.distribution.region.contains(p)) ? d.get(p) : get(p);
				set(d.get(p), p);
			}
		} finally {
			x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
	}

	public LongReferenceArray union(x10.lang.longArray d) {
		dist dist = distribution.union(d.distribution);
		LongArray_c ret = new LongArray_c(dist, 0, safe_);
		place here = x10.lang.Runtime.runtime.currentPlace();
		try {
			for (Iterator it = dist.iterator(); it.hasNext(); ) {
				point p = (point) it.next();
				place pl = dist.get(p);
				x10.lang.Runtime.runtime.setCurrentPlace(pl);
				long val = (distribution.region.contains(p)) ? get(p) : d.get(p);
				ret.set(val, p);
			}
		} finally {
			x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
		return ret;
	}

	public LongReferenceArray restriction(dist d) {
		return restriction(d.region);
	}

	public LongReferenceArray restriction(region r) {
		dist dist = distribution.restriction(r);
		LongArray_c ret = new LongArray_c(dist, 0, safe_);
		place here = x10.lang.Runtime.runtime.currentPlace();
		try {
			for (Iterator it = dist.iterator(); it.hasNext(); ) {
				point p = (point) it.next();
				place pl = dist.get(p);
				x10.lang.Runtime.runtime.setCurrentPlace(pl);
				ret.set(get(p), p);
			}
		} finally {
			x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
		return ret;
	}

	public x10.lang.longArray toValueArray() {
		if (! mutable_) return this;
		throw new Error("TODO: <T>ReferenceArray --> <T>ValueArray");

	}
	public boolean isValue() {
		return ! this.mutable_;
	}
}
