// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

/**
 * @author bdlucas 10/2008
 */

class XTENLANG_90 extends x10Test {

    val y:Box[int] = 3;
    val z = y to int;

    public def run(): boolean {
        return true;
    }

    public static def main(Rail[String]) {
        new XTENLANG_90().execute();
    }
}
