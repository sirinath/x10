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
import x10.array.Region;

/**
 * @author bdlucas 10/2008
 */

class XTENLANG_60 extends x10Test {

    public class D implements (Point)=>Place, (Place)=>Region {
    
        incomplete public def apply(Point):Place;
        incomplete public def apply(Place):Region;
    }

    public def run(): boolean {
        return true;
    }

    public static def main(Rail[String]) {
        new XTENLANG_60().execute();
    }
}
