package x10.lang;

import java.util.Iterator;

import x10.lang.doubleArray.binaryOp;

/** The base class for all (value or reference) multidimensional,
 * distributed int arrays in X10.  Is a subclass-only mutable class
 * (has no mutable state, and all methods are value methods).
 * Specialized from array by replacing the type parameter with int.
 
 * Handtranslated from the X10 code in x10/lang/intArray.x10
 * 
 * @author vj 12/24/2004
 */

abstract public class intArray /*( distribution distribution )*/ implements Indexable {
	public final distribution distribution;
	/*parameter*/ public final /*nat*/long rank /*= distribution.rank*/;
	/*parameter*/ public final region/*(rank)*/ region /*= distribution.region*/;
	
	protected intArray( distribution D) {
		this.distribution = D;
		this.region = D.region;
		this.rank = D.rank;
	}
	
	public static interface binaryOp {
		int apply(int r, int s);
	}
	
	public static final binaryOp sub = new binaryOp() { public int apply(int r, int s) { return r-s;}};
	public static final binaryOp add = new binaryOp() { public int apply(int r, int s) { return r+s;}};
	public static final binaryOp max = new binaryOp() { public int apply(int r, int s) { return Math.max(r,s);}};
	public static final unaryOp abs = new unaryOp() { public int apply(int r) { return Math.abs(r);}};
	public static interface pointwiseOp/*(region r)*/ { 
		int apply(point/*(r)*/ p);
	}
    
    public static interface unaryOp {
        int apply(int r);
    }
	
	abstract public static /*value*/ class factory {
		
		/** Return the unique int value array initialized with 0 
		 * and defined over the distribution 0..k-1 -> here.
		 * vj: Note that in this implementation, returns intArray rather than
		 * intValueArray.
		 * @param k
		 * @return
		 */
		public intArray intValueArray( /*nat*/ int k) {
			return intValueArray(k, 0);
		}
		/** Return the unique int value array initialized with initVal 
		 * and defined over the distribution 0..k-1 -> here.
		 * 
		 * @param k
		 * @return
		 */
		public intArray/*(:rank=1)*/  intValueArray(/*nat*/ int k, int initVal) { 
			return intValueArray(x10.lang.distribution.factory.here(k), initVal);
		}
		/** Return the unique int value array initialized with init 
		 * and defined over the distribution 0..k-1 -> here.
		 * 
		 * @param k
		 * @return
		 */
		public intArray/*(:rank=1)*/ intValueArray(/*nat*/ int k, pointwiseOp init) {
			return intValueArray( x10.lang.distribution.factory.here(k), init);
		}
		
		abstract public 
		/*(distribution D)*/ intArray/*(D)*/ intValueArray(distribution D, int init);
		abstract public 
		/*(distribution D)*/ intArray/*(D)*/ intValueArray(distribution D, pointwiseOp/*(D.region)*/ init);
		
		
		/** Return the unique int value array initialized with 0 
		 * and defined over the distribution 0..k-1 -> here.
		 * 
		 * @param k
		 * @return
		 */
		public IntReferenceArray IntReferenceArray( /*nat*/ int k) {
			return IntReferenceArray(k, 0);
		}
		/** Return the unique int value array initialized with initVal 
		 * and defined over the distribution 0..k-1 -> here.
		 * 
		 * @param k
		 * @return
		 */
		public IntReferenceArray/*(:rank=1)*/  IntReferenceArray(/*nat*/ int k, int initVal) { 
			return IntReferenceArray(x10.lang.distribution.factory.here(k), initVal);
		}
		/** Return the unique int value array initialized with init 
		 * and defined over the distribution 0..k-1 -> here.
		 * 
		 * @param k
		 * @return
		 */
		public IntReferenceArray/*(:rank=1)*/ IntReferenceArray(/*nat*/ int k, pointwiseOp init) {
			return IntReferenceArray( x10.lang.distribution.factory.here(k), init);
		}
		
		
		abstract public 
		/*(distribution D)*/ IntReferenceArray/*(D)*/ IntReferenceArray(distribution D, int init);
		abstract public 
		/*(distribution D)*/ IntReferenceArray/*(D)*/ IntReferenceArray(distribution D, 
				pointwiseOp/*(D.region)*/ init);
		
	}
	
	public static final factory factory = Runtime.factory.getIntArrayFactory();
	
	/** Return the value of the array at the given point in the
	 * region.
	 */
	abstract /*value*/ public int get(point/*(region)*/ p);
	//TODO: interim support for multi-index access.
	abstract /*value*/ public int get(int p);
	abstract /*value*/ public int get(int p, int q);
	abstract /*value*/ public int get(int p, int q, int r);
	abstract /*value*/ public int get(int p, int q, int r, int s);
	
    abstract public void set( int v, point/*(region)*/ p );
    abstract /*value*/ public void set(int v, int p);
    abstract /*value*/ public void set(int v, int p, int q);
    abstract /*value*/ public void set(int v, int p, int q, int r);
    abstract /*value*/ public void set(int v, int p, int q, int r, int s);
    
    /** Convenience method for returning the sum of the array.
     * @return sum of the array.
     */
	public int sum() {
		return reduce(add, 0);
	}
	/**
	 * Convenience method for returning the max of the array.
	 * @return
	 */
	public int max() {
		return reduce(max, 0);
	}
	/**
	 * Convenience method for returning the max of the array after applying the given fun.
	 * @param fun
	 * @return
	 */
	public int max(unaryOp fun) {
		return lift(fun).reduce(max, 0);
	}
	/**
	 * Convenience method for applying abs to each element in the array.
	 * @return
	 */
	public intArray abs() {
		return lift(abs);
	}
	/**
	 * Convenience method for applying max after applying abs.
	 * @return
	 */
	public int maxAbs() {
		return max(abs);
	}
    /** Return the value obtained by reducing the given array with the
	 function fun, which is assumed to be associative and
	 commutative. unit should satisfy fun(unit,x)=x=fun(x,unit).
	 */
	abstract /*value*/ public int reduce(binaryOp fun, int unit);
	
	/** Return an IntArray with the same distribution as this, by 
	 scanning this with the function fun, and unit unit.
	 */
	abstract /*value*/ public intArray/*(distribution)*/ scan(binaryOp fun, int unit);
	
	/** Return an array of B@P defined on the intersection of the
	 region underlying the array and the parameter region R.
	 */
	abstract /*value*/ public /*(region(rank) R)*/
	intArray/*(distribution.restriction(R)())*/  restriction(region R);
	
	/** Return an array of B@P defined on the intersection of 
	 the region underlying this and the parametric distribution.
	 */    
	abstract /*value*/ public  /*(distribution(:rank=this.rank) D)*/ 
	intArray/*(distribution.restriction(D.region)())*/ restriction(distribution D);
	
	/** Take as parameter a distribution D of the same rank as *
	 * this, and defined over a disjoint region. Take as argument an *
	 * array other over D. Return an array whose distribution is the
	 * union of this and D and which takes on the value
	 * this.atValue(p) for p in this.region and other.atValue(p) for p
	 * in other.region.
	 */
	abstract /*value*/ public /*(distribution(:region.disjoint(this.region) &&
	rank=this.rank) D)*/
	intArray/*(distribution.union(D))*/ union( intArray/*(D)*/ other);
	
	/** Return the array obtained by overlaying this array on top of
	 other. The method takes as parameter a distribution D over the
	 same rank. It returns an array over the distribution
	 dist.asymmetricUnion(D).
	 */
	abstract /*value*/ public /*(distribution(:rank=this.rank) D)*/
	intArray/*(distribution.asymmetricUnion(D))*/ overlay( intArray/*(D)*/ other);
	
	
	/** Assume given an intArray a over the given distribution.
	 * Assume given a function fun: int -> int -> int.
	 * Return an intArray with distribution dist 
	 * containing fun(this.atValue(p),a.atValue(p)) for each p in
	 * dist.region.
	 */
	abstract /*value*/ public 
	intArray/*(distribution)*/ lift(binaryOp fun, intArray/*(distribution)*/ a);
	
	/** Apply the given fun: int -> int on each element of the array, returning
	 * an array with the same distribution.
	 * 
	 * @param fun -- the function to be applied pointwise.
	 * @return the array with fun applied pointwise
	 */
	abstract /*value*/ public 
	intArray/*(distribution)*/ lift(unaryOp fun);
	
	public Iterator iterator() {
	 	return region.iterator();
	 }
}
