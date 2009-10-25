/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;


/**
 * Java and X10 permit a call to a method which returns a value to occur as a statement.
 * The returned value is discarded. However, Java does not permit a variable standing alone
 * as a statement. Thus the x10 compiler must check that as a result of flattening it does
 * not produce a variable standing alone. 
 * In an earlier implementation this would give a t0 not reachable error.
 */

public class FlattenAsyncExpr2 extends x10Test {

    global var a: Array[int](1);

    public def this(): FlattenAsyncExpr2 = {
        a = Array.make[int](1..10 -> here, ((j): Point): int => { return 2*j;});
    }

    global def m(var x: int): int = {
        return x;
    }
    
    public def run(): boolean = {
        finish async(a.dist(1)) {
            m(50000);
            atomic { a(1) = (a(1)^2);}
        }
        return a(1)== (2^2);
    }

    public static def main(var args: Rail[String]): void = {
        new FlattenAsyncExpr2().execute();
    }
}
