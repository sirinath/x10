// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

//LIMITATION: closure type params

import harness.x10Test;


/**
 * A call to a polymorphic method, closure, or constructor may omit
 * the explicit type arguments. If the method has a type parameter T,
 * the type argument corresponding to T is inferred to be the least
 * common ancestor of the types of any formal parameters of type T.
 *
 * @author bdlucas 8/2008
 */

public class ClosureCall1d_MustFailCompile extends ClosureTest {

    class V           {const name = "V";}
    class W extends V {const name = "W";}
    class X extends V {const name = "X";}
    class Y extends X {const name = "Y";}
    class Z extends X {const name = "Z";}

    public def run(): boolean = {

        val d = ([T,U](t:T,u:U){T<:X && U<:X} => T.name + U.name)(new Y(), new Z());
        check("d", d, "YZ");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureCall1d()_MustFailCompile.execute();
    }
}
