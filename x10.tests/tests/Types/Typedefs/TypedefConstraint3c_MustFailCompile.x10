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

import harness.x10Test;

/**
 * @author bdlucas 9/2008
 */

class TypedefConstraint3c_MustFailCompile extends TypedefTest {

    public def run():boolean {

        type T(x:long){x==1} = long;
        val one:long = 1; // fails because one: long, not long{self==1}
        var a:T(one); // ERR
        return true;
    }

    public static def main(var args: Rail[String]): void {
        new TypedefConstraint3c_MustFailCompile().execute();
    }
}
