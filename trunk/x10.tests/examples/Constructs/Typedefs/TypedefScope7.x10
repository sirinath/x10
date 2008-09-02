// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * If the type definition is a static member of a class or interface C,
 * then the type definition defines the type constructor C.X and C.X[S1,
 * . . ., Sm](e1, . . ., en) is a type.
 *
 * @author bdlucas 8/2008
 */

public class TypedefScope7 extends TypedefTest {

    class X[Y] {
        static type T[Y] = Y;
    }

    public def run(): boolean = {
        
        a:X.T[int] = 1;
        check("a", a, 1);

        b:X.T[String] = "1";
        check("b", b, "1");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new TypedefScope7().execute();
    }
}
