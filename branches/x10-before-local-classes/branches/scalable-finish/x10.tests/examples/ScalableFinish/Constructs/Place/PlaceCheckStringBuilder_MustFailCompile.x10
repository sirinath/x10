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

//OPTIONS: -STATIC_CALLS

 

/**
 * @author bdlucas
 */

import x10.util.StringBuilder;

public class PlaceCheckStringBuilder_MustFailCompile   {

    public def run(): boolean {
        val sb = new StringBuilder();
            (future (Place.places(1)) {
                sb.add("foo");
                return sb.toString();
            }).force();
            return true;
    }

    public static def main(var args: Rail[String]): void = {
        new PlaceCheckStringBuilder_MustFailCompile().run ();
    }
}
