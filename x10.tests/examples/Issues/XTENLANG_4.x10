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
 * @author bdlucas 10/2008
 */

class XTENLANG_4 extends x10Test {

    class R(rank:int) {
        def this(r:int) { property(r); }
        incomplete def m(val r: int): R{self.rank==r};
    }
    
    class B extends R {
        def this(r:int) { super(r); }
        incomplete def m(val r: int): R{self.rank==r};
    }

    public def run(): boolean {
        return true;
    }

    public static def main(Rail[String]) {
        new XTENLANG_4().execute();
    }
}
