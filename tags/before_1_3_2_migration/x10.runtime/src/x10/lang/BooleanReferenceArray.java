package x10.lang;

/** The class of all multidimensional, settable distributed int arrays
 * in X10.  Specialized from ReferenceArray by replacing the type parameter
 * with boolean.

 * Handtranslated from the X10 code in x10/lang/BooleanReferenceArray.x10
 * 
 * @author vj 1/9/2005
 */

public abstract class BooleanReferenceArray extends booleanArray {
	
	public BooleanReferenceArray( dist D) {
		super( D );
	}

	abstract public boolean set( boolean v, point/*(region)*/ p);
	abstract /*value*/ public boolean set(boolean v, int p);
	abstract /*value*/ public boolean set(boolean v, int p, int q);
	abstract /*value*/ public boolean set(boolean v, int p, int q, int r);
	abstract /*value*/ public boolean set(boolean v, int p, int q, int r, int s);	
}
