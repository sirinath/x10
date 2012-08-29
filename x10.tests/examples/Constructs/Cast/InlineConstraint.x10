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

import harness.x10Test;

/**
 */
public class InlineConstraint extends x10Test {
    public def run()  {
	val v:Any = (0..5) as Region;
	var result:boolean=true;
	val c = v instanceof Region{self.rank==1};
	if (!c) {
	    Console.OUT.println("v instanceof Region{self.rank==1} failed.");
	    result=false;
	}
	val d = v instanceof Region{self.rank==2};
	if (d) {
	    Console.OUT.println("WTF, it's a two-dimensional region?!");
	    result=false;
	}
	try {
	    val e = v as Region{self.rank==1};
	} catch (ClassCastException) {
	    Console.OUT.println("WTF, cast to a single-dimensional region failed?!");
	    result=false;
	}
	try {
	    val f = v as Region{self.rank==2};
	    Console.OUT.println("WTF, cast to a two-dimensional region succeeded?!");
	    result = false;
	} catch (ClassCastException) {
	}
	return result;
    }
    public static def main(args: Array[String](1)) {
	new InlineConstraint().execute();
    }
}
