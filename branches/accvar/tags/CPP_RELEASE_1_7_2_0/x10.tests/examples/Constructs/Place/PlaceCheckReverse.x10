// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test.

import harness.x10Test;

/**
 * @author bdlucas
 */

public class PlaceCheckReverse extends x10Test {

    class C {
        property p:int = 0;
        val x:int = 0;
        var y:int = 0;
        def foo() {}
        def foo(x:int) {}
        def foo[T](x:T) {}
    }

    val c = (future (Place.places(1)) new C()).force();

    public def run01(): boolean = {
        try {
            val a = c.p;
        } catch (e:BadPlaceException) {
            return true;
        }
        System.out.println("01 fails");
        return false;
    }

    public def run02(): boolean = {
        try {
            val a = c.x;
        } catch (e:BadPlaceException) {
            return true;
        }
        System.out.println("02 fails");
        return false;
    }

    public def run03(): boolean = {
        try {
            val a = c.y;
        } catch (e:BadPlaceException) {
            return true;
        }
        System.out.println("03 fails");
        return false;
    }

    public def run04(): boolean = {
        try {
            c.foo();
        } catch (e:BadPlaceException) {
            return true;
        }
        System.out.println("04 fails");
        return false;
    }

    public def run05(): boolean = {
        try {
            c.foo(0);
        } catch (e:BadPlaceException) {
            return true;
        }
        System.out.println("05 fails");
        return false;

    }

    public def run06(): boolean = {
        try {
            c.foo("1");
        } catch (e:BadPlaceException) {
            return true;
        }
        System.out.println("06 fails");
        return false;
    }

    public def run() = run01() && run02() && run03() && run04() && run05() && run06();

    //
    //
    //

    public static def main(var args: Rail[String]): void = {
        new PlaceCheckReverse().execute();
    }
}
