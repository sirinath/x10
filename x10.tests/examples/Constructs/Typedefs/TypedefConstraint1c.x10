// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * @author bdlucas 9/2008
 */

public class TypedefConstraint1c extends TypedefTest {

    class X           {def name(): String = "X";}
    class Y extends X {def name(): String = "Y";}
    class Z extends Y {def name(): String = "Z";}

    public def run(): boolean = {
        
        type C[T]{Y<:T} = T;
        c:C[X]! = new X();
        check("c.name()", c.name(), "X");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new TypedefConstraint1c().execute();
    }
}
