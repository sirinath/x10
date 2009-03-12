// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test.

import harness.x10Test;

/**
 * @author bdlucas
 */

public class PlaceCheckGenericClass extends x10Test {

    class C[S] {
        property p:int = 0;
        val x:S;
        var y:S;
        def foo() {}
        def foo(x:S) {}
        def foo[T](x:T) {}
        def this(s:S) {
            x = s;
            y = s;
        }
    }

    val c = new C[String]("0");

    public def run01(): boolean = {
        val f = future (Place.places(1)) {
            try {
                val a = c.p;
            } catch (e:BadPlaceException) {
                return true;
            }
            Console.OUT.println("01 fails");
            return false;
        };
        return f.force();
    }

    public def run02(): boolean = {
        val f = future (Place.places(1)) {
            try {
                val a = c.x;
            } catch (e:BadPlaceException) {
                return true;
            }
            Console.OUT.println("02 fails");
            return false;
        };
        return f.force();
    }

    public def run03(): boolean = {
        val f = future (Place.places(1)) {
            try {
                val a = c.y;
            } catch (e:BadPlaceException) {
                return true;
            }
            Console.OUT.println("03 fails");
            return false;
        };
        return f.force();
    }

    public def run04(): boolean = {
        val f = future (Place.places(1)) {
            try {
                c.foo();
            } catch (e:BadPlaceException) {
                return true;
            }
            Console.OUT.println("04 fails");
            return false;
        };
        return f.force();
    }

    public def run05(): boolean = {
        val f = future (Place.places(1)) {
            try {
                c.foo("1");
            } catch (e:BadPlaceException) {
                return true;
            }
            Console.OUT.println("05 fails");
            return false;
        };
        return f.force();

    }

    public def run06(): boolean = {
        val f = future (Place.places(1)) {
            try {
                c.foo(1.0);
            } catch (e:BadPlaceException) {
                return true;
            }
            Console.OUT.println("06 fails");
            return false;
        };
        return f.force();
    }

    public def run() = run01() && run02() && run03() && run04() && run05() && run06();

    //
    //
    //

    public static def main(var args: Rail[String]): void = {
        new PlaceCheckGenericClass().execute();
    }
}
