// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test.

import harness.x10Test;

/**
 * @author bdlucas
 */

import x10.util.StringBuilder;

public class PlaceCheckStringBuilder_MustFailCompile extends x10Test {

    public def run(): boolean {
        val sb = new StringBuilder();
            (future (Place.places(1)) {
                sb.add("foo");
                return sb.toString();
            }).force();
            return true;
    }

    public static def main(var args: Rail[String]): void = {
        new PlaceCheckStringBuilder_MustFailCompile().execute();
    }
}
