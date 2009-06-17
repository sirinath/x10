/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import x10.lang.Object;
import harness.x10Test;

/**
 * 
 * Has generated  the following error in a CVS version, with x10c:
     
     C:\eclipse\ws3\x10.common\examples\Constructs\Array\NullableArrayAssign.x10:21:
         Method set(x10.compilergenerated.Parameter1, x10.lang.point) in
         x10.lang.GenericReferenceArray cannot be called with arguments
         (type(null), int).
     1 error.
 
 * @author shane
 * @author vj
 */
public class NullableArrayAssign extends x10Test {

        public boolean run() {
	    final nullable<Object>[.] table = new nullable<Object>[[1:5]];
	       foreach ( point p[i] : table )
			table[i] = null;
		return true;
	}
	
	public static void main(String[] args) {
		new NullableArrayAssign().execute();
	}

}

