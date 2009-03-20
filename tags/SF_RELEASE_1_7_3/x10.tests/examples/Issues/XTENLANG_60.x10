// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

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
