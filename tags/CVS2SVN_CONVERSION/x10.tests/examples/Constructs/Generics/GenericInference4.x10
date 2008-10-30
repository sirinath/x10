// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * A call to a polymorphic method, closure, or constructor may omit
 * the explicit type arguments. If the method has a type parameter T,
 * the type argument corresponding to T is inferred to be the least
 * common ancestor of the types of any formal parameters of type T.
 *
 * @author bdlucas 8/2008
 */

public class GenericInference4 extends GenericTest {

    class V           {const name = "V";};
    class W extends V {const name = "W";}
    class X extends V {const name = "X";};
    class Y extends X {const name = "Y";};
    class Z extends X {const name = "Z";};

    def m[T,U](t:T,u:U){T<:X && U<:X} = X.name + X.name;

    public def run(): boolean = {

        val d = m(new Y(), new Z());;
        check("d", d, "XX");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new GenericInference4().execute();
    }
}
