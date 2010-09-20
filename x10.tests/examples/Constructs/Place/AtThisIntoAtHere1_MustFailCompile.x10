/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

//OPTIONS: -STATIC_CALLS

import harness.x10Test;

/**
 * Changed for 2.1.
 * 
 * Test that if you have two fields with GlobalRef's pointing to the same location, you can do an at to
 * one field and deref the other provided that there is no intervening place shift.

 * @author vj
 */
public class AtThisIntoAtHere1_MustFailCompile extends x10Test {
	 class Test {
	    	private val x:GlobalRef[Test]  = GlobalRef[Test](this);
	        private val y:GlobalRef[Test]{self.home==x.home}  = GlobalRef[Test](this);

	     def n() { 
	    	 val p = Place.places(1);
	    	 at (x) {
	    		
	    		 at (p)
	    		  // this is not ok because of the place shift.
	    		    y();
	    	 }
	     }
	    }

    public def run() = true;

    public static def main(Array[String](1)) {
	  new AtThisIntoAtHere1_MustFailCompile().execute();
    }

}
