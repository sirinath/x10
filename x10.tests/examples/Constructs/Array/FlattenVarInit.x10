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

public class FlattenVarInit extends x10Test {

    val a: Array[int](2);

    public def this(): FlattenVarInit = {
        a = Array.make[int](([1..10, 1..10] to Region)->here, ((i,j): Point): int => { return i+j;});
    }

    def m(var x: int): int = {
        return x;
    }
    
    public def run(): boolean = {
    var t0: int;
        t0 = m(a(1, 1));
        return t0==2;
    }

    public static def main(var args: Rail[String]): void = {
        new FlattenVarInit().execute();
    }
}
