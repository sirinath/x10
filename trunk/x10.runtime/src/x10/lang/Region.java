/*
 * Created on Oct 3, 2004
 */
package x10.lang;

import java.util.Iterator;

/**
 * Implementation of Region. The Points in a region, aka. tuples, 
 * are implemented as one-dimensional int arrays. Instance of 
 * this class are immutable!
 *
 * @author Christoph von Praun
 * @author Christian Grothoff
 */
public interface Region extends TypeArgument {

    public int rank();
    
    public Region sub(Range[] dims);
	
    public Region combine(Region r);
	
    public Range dim(int i);
	
    public boolean contains(Region r);
	
    public boolean contains(int[] p);
	
    public int ordinal(int[] p);
	
    public Iterator iterator();
	
}
