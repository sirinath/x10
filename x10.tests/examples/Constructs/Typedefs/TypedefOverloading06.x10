// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

/**
 * A compilation unit may have one or more type definitions or class
 * or interface declarations with the same name, as long as the
 * definitions have distinct parameters according to the method
 * overloading rules (§9.7.1).
 *
 * As the two definitions of Int demonstrate, type definitions may be
 * overloaded: two type definitions with different numbers of type
 * parameters or with different types of value parameters, according
 * to the method overloading rules (�9.7.1), define distinct types.
 *
 * @author bdlucas 9/2008
 */

public class TypedefOverloading06 extends TypedefTest {

    interface A/*C*/[T,U] {};

    public def run(): boolean = {
        
        class X {}
        class Y[T] {}
        class Z[T,U] {}

        class U {}
        class V(i:int) {def this(i:int):V{self.i==i} = property(i);}
        class W(i:int,s:String) {def this(i:int,s:String):W{self.i==i&&self.s==s} = property(i,s);}

        type A = String;
        type A/*B*/[T] = X;
        a1:A = "1";
        a2:A/*B*/[int] = new X();
        class Foo implements A/*C*/[int,String] {}
        a3:A/*C*/[int,String] = new Foo();

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new TypedefOverloading06().execute();
    }
}

