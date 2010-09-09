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
 * Ensures char arrays are implemented.
 */
public class Array3Char   {

    public def run(): boolean = {
        val r  = [1..10, 1..10] as Region;
        val ia  = new Array[Char](r, (x:Point)=>'_');
        ia(1, 1) = 'a';
        return ('a' == ia(1, 1));
    }

    public static def main(var args: Rail[String]): void = {
        new Array3Char().run ();
    }
}
