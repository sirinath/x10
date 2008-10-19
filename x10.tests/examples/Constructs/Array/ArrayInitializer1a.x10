/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Array Initializer test.
 */

public class ArrayInitializer1a extends x10Test {

    public def run(): boolean = {

        val e = 0..9;
        val r = [e, e, e] to Region;
        val d = r->here;
        val ia = Array.make[Int](d, ((i,j,k): Point)=> i);

        for (val p in ia.region) chk(ia(p) == i); // should infer p:Point(3)

        return true;
    }

    public static def main(Rail[String]): Void = {
        new ArrayInitializer1a().execute();
    }
}
