// (C) Copyright IBM Corporation 2006
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

public class ClosureCall1b extends ClosureTest {

    class V           {static val name = "V";};
    class W extends V {static val name = "W";}
    class X extends V {static val name = "X";};
    class Y extends X {static val name = "Y";};
    class Z extends X {static val name = "Z";};

    public def run(): boolean = {

        val b = [T](t:T){T<:X} => T.name;
        check("b(new Y())", b(new Y()), "Y");
        check("b(new Z())", b(new Z()), "Z");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureCall1b().execute();
    }
}
