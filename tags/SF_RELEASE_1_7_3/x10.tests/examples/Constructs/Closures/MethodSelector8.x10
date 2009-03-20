// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * For a type T, a list of types (T1, . . . , Tn), a method name m and an
 * expression e of type T, e.m.(T1, . . ., Tn) denotes the function, if
 * any, bound to the instance method named m at type T whose argument
 * type is (T1, . . ., Tn), with this the method body bound to
 * the value obtained by evaluating e. The return type of the function is
 * specified in the method declaration.  Thus, the method selector
 * 
 * e.m.[X1, . . ., Xm](T1, . . ., Tn)
 * 
 * behaves as if it were the closure
 * 
 * (X1, . . ., Xm](x1: T1, . . ., xn: Tn) => e.m[X1, . . ., Xm](x1, . . ., xn)
 *
 * @author bdlucas 8/2008
 */

public class MethodSelector8 extends ClosureTest {

    def foo(): int = 10;
    def foo(i:int): int = i*10;

    static class C8[T] {
        def foo[U](j:U): U = j;
        def foo[U](i:T,j:U): U = j;
    }

    public def run(): boolean = {

        val c = new C8[String]();
        val f1 = c.foo.[int](int);
        val f2 = c.foo.[int](String,int);
        check("f1", f1(1), 1);
        check("f2", f2("1",2), 2);

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new MethodSelector8().execute();
    }
}
