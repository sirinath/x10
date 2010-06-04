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
 * @author bdlucas 9/2008
 */

public class TypedefConstraint2b_MustFailCompile extends TypedefTest {

    class X           {const name = "X";}
    class Y extends X {const name = "Y";}
    class Z extends Y {const name = "Z";}

    class FOO[T]{T<:X} {
        //val name = T.name;
    }

    public def run(): boolean = {
        
        type B[T]{T<:Y} = FOO[T];
        b1:B[X] = new FOO[X]();
        //check("b1.name", b1.name, "X");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new TypedefConstraint2b_MustFailCompile().execute();
    }
}
