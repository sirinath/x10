// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

/**
 * Not a test. Convenience class to simplify debugging the test
 * cases. Perhaps this should be in x10Test
 *
 * @author bdlucas 8/2008
 */

abstract class TypedefTest extends x10Test {

    var result:boolean = true;

    def check(test:String, actual:Object, expected:Object) = {

        var result:boolean = actual.equals(expected);

        if (!result) {
            pr(test + " fails: expected " + expected + ", got " + actual);
            this.result = false;
        } else
            pr(test + " succeeds: got " + actual);
    }

    def pr(s:String):void = {
        System.out.println(s);
    }

}
