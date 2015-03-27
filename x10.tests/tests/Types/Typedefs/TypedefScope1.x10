/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

//LIMITATION:
// top-level typedefs not supported - therefore I moved it into the class

import harness.x10Test;


/**
 * All type definitions are members of their enclosing package or
 * class.
 *
 * @author bdlucas 8/2008
 */


public class TypedefScope1 extends TypedefTest {
	static type T = long;

    public def run(): boolean {
        
        a:T = 1;
        check("a", a, 1);

        return result;
    }

    public static def main(var args: Rail[String]): void {
        new TypedefScope1().execute();
    }
}
