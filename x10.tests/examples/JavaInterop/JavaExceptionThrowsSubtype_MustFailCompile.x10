/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2012.
 */

import harness.x10Test;
import x10.interop.Java;
import x10.interop.java.Throws;

// MANAGED_X10_ONLY

public class JavaExceptionThrowsSubtype_MustFailCompile extends x10Test {

    public static class A {
        public def throws() { }
    }
    public static class B extends A {
        public def throws() @Throws[java.io.IOException] { }
    }


    public def run(): Boolean {
        return true;
    }

    public static def main(args: Array[String](1)) {
        new JavaExceptionThrowsSubtype_MustFailCompile().execute();
    }

}
