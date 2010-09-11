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
 * Purpose: A regular java array must be initialized.
 * Issue: Java arrays are not initialized. This is a contradiction with X10's nullable semantic
 * @author vcave
 **/

public class JavaArrayNotInitialized_MustFailCompile extends x10Test {

    public def run(): boolean = {
        var array: Rail[JavaArrayNotInitialized_MustFailCompile] = new Array[JavaArrayNotInitialized_MustFailCompile](1);
        return array(0) != null;
    }

    public static def main(Array[String](1)) {
        new JavaArrayNotInitialized_MustFailCompile().execute();
    }

}
