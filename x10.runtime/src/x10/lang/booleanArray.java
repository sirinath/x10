package x10.lang;

import x10.array.Operator.Binary;
import x10.array.Operator.Unary;

/**
 * The interface for all multidimensional, distributed boolean arrays in X10.
 * Has no mutation operations.
 * Specialized from array by replacing the type parameter with boolean.
 *
 * Handtranslated from the X10 code in x10/lang/BooleanArray.x10
 * 
 * @author vj 12/24/2004
 * @author igor 09/13/2006 -- made an interface
 */
public interface booleanArray extends Array {
        abstract public boolean[] getBackingArray();
        abstract public int[]  getDescriptor();
	/**
	 * Return the value of the array at the given point in the
	 * region.
	 */
	abstract public boolean get(point/*(region)*/ p);
	abstract /*value*/ public boolean get(int p);
	abstract /*value*/ public boolean get(int p, int q);
	abstract /*value*/ public boolean get(int p, int q, int r);
	abstract /*value*/ public boolean get(int p, int q, int r, int s);
	abstract public boolean get(int[] p);

	public static final Binary xor = new Binary() { public boolean apply(boolean r, boolean s) { return r ^ s; }};
	public static final Binary or = new Binary() { public boolean apply(boolean r, boolean s) { return r | s; }};
	public static final Binary and = new Binary() { public boolean apply(boolean r, boolean s) { return r & s; }};

	public static final Unary neg = new Unary() { public boolean apply(boolean r) { return !r; }};

	/**
	 * Return an immutable copy of this array. Note: The implementation actually returns a copy
	 * at the representation of the X10 type x10.lang.booleanValueArray, which is booleanArray.
	 * @return an immutable version of this array.
	 */
	abstract public booleanArray toValueArray();
}
