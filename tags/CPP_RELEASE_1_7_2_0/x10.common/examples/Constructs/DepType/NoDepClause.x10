/*
 *
 * (C) Copyright IBM Corporation 2007
 *
 *  This file is part of X10 Test.
 *
 */
 /**  Check that a non-dependent type String is equivalent to String(:)
 
 *@author igorp,6/17/2007
 *@author nystrom,6/17/2007
 *
 */

import harness.x10Test;
 
public class NoDepClause extends x10Test {
	public boolean run() {
		// Make sure we main gets translated correctly.  We'll actually fail before this.
	    nullable<x10.lang.Runtime> r = x10.lang.Runtime.runtime;
	    return r != null;
	}
	public static void main(final String[] args) {
	new NoDepClause().execute();
} }