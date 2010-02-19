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
 * Purpose: Checks a null value is not an instance of a non-null type.
 * Issue: null is not an instance of a non-null type.
 * @author vcave
 */
public class NullToRegularType_MustFailCompile extends x10Test {

    // X10 forbids an instanceof test where the types are statically known to be incompatible.
    public def run(): Boolean = !(null instanceof X10DepTypeClassOne{self!=null});

    public static def main(var args: Rail[String]): void = {
        new NullToRegularType_MustFailCompile().execute();
    }
}
