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

import x10.util.Box;
import harness.x10Test;

/**
 * @author bdlucas 12/2008
 */

class XTENLANG_210 extends x10Test {

    var os:Rail[Object] = Rail.make[Object](10);
    
    operator this(i0:int)=(vue:double): void = {
        os(i0) = vue as Box[double];
    }

    public def run(): boolean {
        return true;
    }

    public static def main(Array[String](1)) {
        new XTENLANG_210().execute();
    }
}
