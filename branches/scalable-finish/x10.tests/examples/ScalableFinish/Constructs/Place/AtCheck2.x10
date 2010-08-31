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

 

/**
 * Testing that an at(b) c.x is legal, where val c = b;
 * @author vj
 */

public class AtCheck2   {
	var x:AtCheck =null;
    def m(b: AtCheck2) {
    	val c = b;
	    at ( b) {
	     val e = c.x;
	    }
    }
    
    public def run()=true;

    public static def main(Rail[String]) {
        new AtCheck2().run ();
    }
}
