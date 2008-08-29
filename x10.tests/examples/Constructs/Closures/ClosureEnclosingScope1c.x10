// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * The body s in a function (x1: T1, . . ., xn: Tn) => { s } may access
 * fields of enclosing classes and local variable declared in an outer
 * scope.
 *
 * @author bdlucas 8/2008
 */

public class ClosureEnclosingScope1c extends ClosureTest {

    val a = 1;

    public def run(): boolean = {
        
        val b = 1;

        class C {
            val c = 1;
            def foo() = (()=>a+b+c)();
        }

        check("new C().foo()", new C().foo(), 3);


        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureEnclosingScope1c().execute();
    }
}
