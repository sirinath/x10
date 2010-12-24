// Yoav added: IGNORE_FILE
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

//LIMITATION: closure type params are not supported (so this file doesn't even parse!)

import harness.x10Test;


/**
 * A call to a polymorphic method, closure, or constructor may omit
 * the explicit type arguments. If the method has a type parameter T,
 * the type argument corresponding to T is inferred to be the least
 * common ancestor of the types of any formal parameters of type T.
 *
 * @author nystrom 8/2008
 * 9/2009 -- Closures are no longer permitted to take type parameters.
 */

public class ClosureCall0b_MustFailCompile extends ClosureTest {

    public def run(): boolean = {

        // Omega = (\x.x x) (\x.x x)
        val f = [T](x:T){T <: T=>T} => x(x);
        val Omega = f(f);

        return true;
    }

    public static def main(var args: Array[String](1)): void = {
        new ClosureCall0b_MustFailCompile().execute();
    }
}
