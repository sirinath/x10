// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * STATIC SEMANTICS RULE: In an expression (x1: T1, . . ., xn: Tn) =>
 * e, any outer local variable accessed by e must be final or must be
 * declared as shared (�14.9).
 *
 * @author bdlucas 8/2008
 */

public class ClosureEnclosingScope2d_MustFailCompile extends ClosureTest {

    val a = 1;

    public def run(): boolean = {
        
        val b = 1;

        class C {
            val c = 1;
            def foo() = {
                val fun = () => {
                    var d:int = 1;
                    (() => a+b+c+d)()
                };
                fun()
            }
        }

        check("new C().foo()", new C().foo(), 4);


        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureEnclosingScope2d_MustFailCompile().execute();
    }
}
