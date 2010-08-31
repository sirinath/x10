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

 



/**
 * @author bdlucas 8/2008
 */

public class GenericMethods4_MustFailCompile extends GenericTest {

    def m[T](T) = {};

    public def run() = {

        m[int]("1");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new GenericMethods4_MustFailCompile().run ();
    }
}
