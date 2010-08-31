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
 * Closures are no longer permitted to take type parameters.
 */

public class ClosureCall0a_MustFailCompile   {

    public def run(): boolean = {
	val s= "hi";
        val a = ([T](x:T)=>x)(s);
        return a.equals(s);
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureCall0a_MustFailCompile().run ();
    }
}
