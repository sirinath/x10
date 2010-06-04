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
 * Type parameters may be constrained by a where clause on the
 * declaration (�4.3,�9.5,�9.7,�12.5).
 */

public class ClosureTypeParameters2c extends ClosureTest {

    class V           {const name = "V";}
    class W extends V {const name = "W";}
    class X extends V {const name = "X";}
    class Y extends X {const name = "Y";}
    class Z extends X {const name = "Z";}

    public def run(): boolean = {
        
        class C[T] {val f = (){T<:Y} => T.name;}
        check("new C[X]().f()", new C[X]().f(), "X");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureTypeParameters2c().execute();
    }
}
