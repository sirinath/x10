/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Testing arrays with user-defined element types.
 *
 * @author Christian Grothoff
 * @author Kemal Ebcioglu
 */

public class UserDefinedArray extends x10Test {

    const R= 0..1;
    const D = Dist.makeBlock(R);

    public def run(): boolean = {
        chk(place.MAX_PLACES <= 1 || D(0) != D(1));
        // create an array a such that
        // a[0] is in D[0] but points to an object in D[1]
        // and a[1] is in D[1] but points to an object in D[0]
        val v1: E = (future(D(1)){new E(1)}).force();
        val v2: E = (future(D(0)){new E(2)}).force();
        val a  = Array.make[E](D, ((i): Point)=> (i==0) ? v1 : v2);

        chk(a.dist(0) == D(0) &&
            (future(a.dist(0)){a(0)}).force() == v1 &&
            v1.location() == D(1) &&
            (future(v1){v1.v}).force() == 1);

        chk(a.dist(1) == D(1) &&
            (future(a.dist(1)){a(1)}).force() == v2 &&
            v2.location() == D(0) &&
            (future(v2){v2.v}).force() == 2);

        //this top level future runs in D[1] since a[0]==v1 && v1.location()==D[1]
        var i0: int = (future((future(a.dist(0)){a(0)}).force().location())
           { (future(a.dist(0)){a(0)}).force().v }).force();

        //this top level future runs in D[0] since a[1]==v2 && v2.location()==D[0]
        var i1: int = (future((future(a.dist(1)){a(1)}).force())
            { (future(a.dist(1)){a(1)}).force().v }).force();

        return i0 + 1 == i1;
    }

    public static def main(var args: Rail[String]): void = {
        new UserDefinedArray().execute();
    }

    static class E {
        var v: int;
        def this(var i: int): E = { v = i; }
    }
}
