package x10.lang;

/** The class of all multidimensional, settable distributed int arrays
 * in X10.  Specialized from ReferenceArray by replacing the type parameter
 * with char.

 * Handtranslated from the X10 code in x10/lang/CharReferenceArray.x10
 * 
 * @author vj 1/9/2005
 */

public abstract class CharReferenceArray extends charArray {
	
	public CharReferenceArray( dist D) {
		super( D );
	}

	abstract public char set( char v, point/*(region)*/ p);
	abstract /*value*/ public char set(char v, int p);
	abstract /*value*/ public char set(char v, int p, int q);
	abstract /*value*/ public char set(char v, int p, int q, int r);
	abstract /*value*/ public char set(char v, int p, int q, int r, int s);	
}
