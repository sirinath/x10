/*
 * Created on Oct 20, 2004
 */
package x10.array.sharedmemory;

import x10.array.Distribution_c;
import x10.array.FloatArray;
import x10.array.Operator;
import x10.base.UnsafeContainer;
import x10.lang.Indexable;
import x10.lang.Runtime;
import x10.lang.dist;
import x10.runtime.Configuration;

/**
 * @author Christian Grothoff, Christoph von Praun, Igor Peshansky
 */
public class FloatArray_c extends FloatArray implements UnsafeContainer, Cloneable {

	public final float[] arr_;
	public final int[] descriptor_;

	/**
	 * Constructor that implements constant promotion (Chap. 10.4.2).
	 * @param d  Distribution of the array
	 * @param c  constant used to intialize all values of the array
	 */
	protected FloatArray_c(dist d, float c, boolean mutable) {
		this(d, new Constant(c), mutable);
	}

	/**
	 * This constructor must not be used directly by an application programmer.
	 * Arrays are constructed by the corresponding factory methods in
	 * x10.lang.Runtime.
	 */
	public FloatArray_c(dist d, Operator.Pointwise c, boolean mutable, boolean ignored) {
		this(d, c, mutable);
	}

	protected FloatArray_c(dist d, Operator.Pointwise c, boolean mutable) {
		this(d, mutable, null);
		if (c != null)
			scan(this, c);
	}

	private FloatArray_c(dist d, boolean mutable, float[] ignored) {
		super(d, mutable);
		assert (d instanceof Distribution_c);
		int count = d.region.size();
		int rank = d.region.rank;
		descriptor_ = new int[rank+1];
		descriptor_[0] = rank;
		for (int i = 1; i <= rank; ++i)
			descriptor_[i] = d.region.rank(i-1).size();
		this.arr_ = new float[count];
	}

	private FloatArray_c(dist d, float[] a, boolean mutable) {
		this(d, mutable, a);
	}

	/**
	 * Return a FloatArray_c initialized with the given local 1-d (Java) float array.
	 * FIXME: unimplemented.
	 * TODO: Expose this through the factory class.
	 *
	 * @param a
	 * @return
	 */
	public static FloatArray_c FloatArray_c(float[] a, boolean mutable) {
		dist d = Runtime.factory.getDistributionFactory().local(a.length);
		return new FloatArray_c(d, a, mutable);
	}

	public boolean valueEquals(Indexable other) {
		FloatArray_c o = (FloatArray_c)other;
		if (o.arr_.length != arr_.length)
			return false;
		for (int i = arr_.length-1; i >= 0; i--) 
			if (arr_[i] != o.arr_[i])
				return false;
		return true;
	}

	public float[] getBackingArray() { 
		return arr_;
	}

	public int[] getDescriptor() {
		return descriptor_;
	}

	public float setOrdinal(float v, int rawIndex) {
		return arr_[rawIndex] = v;
	}

	public float getOrdinal(int rawIndex) {
		return arr_[rawIndex];
	}

	protected FloatArray newInstance(dist d) {
		return new FloatArray_c(d, (Operator.Pointwise) null, true);
	}

	protected FloatArray newInstance(dist d, float c) {
		return new FloatArray_c(d, c, true);
	}

	/* Overrides the superclass method - this implementation is more efficient */
	protected void assign(FloatArray rhs) {
		assert rhs.getClass() == this.getClass();

		FloatArray_c rhs_t = (FloatArray_c) rhs;
		if (!Configuration.BAD_PLACE_RUNTIME_CHECK &&
				rhs.distribution.equals(distribution)) {
			int count = arr_.length;
			for (int i = 0; i < count; ++i)
				arr_[i] = rhs_t.arr_[i];
		} else
			// fall back to generic implementation
			super.assign(rhs);
	}

	/* Overrides the superclass method - this implementation is more efficient */
	public void reduction(Operator.Reduction op) {
		if (!Configuration.BAD_PLACE_RUNTIME_CHECK) {
			int count = arr_.length;
			for (int i = 0; i < count; ++i)
				op.apply(arr_[i]);
		} else {
			super.reduction(op);
		}
	}
}
