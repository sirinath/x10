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

public class ClosureEnclosingScope1s extends ClosureTest {

    val a = 1;

    public def run(): boolean = {
        
        shared var b:int = 1;

        class C {
            val c = 1;
            def foo() = {
                val fun = () => {
                    shared var d:int = 1;
                    (() => a+b+c+d)()
                };
                fun()
            }
        }

        check("new C().foo()", new C().foo(), 4);


        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureEnclosingScope1s().execute();
    }
}
