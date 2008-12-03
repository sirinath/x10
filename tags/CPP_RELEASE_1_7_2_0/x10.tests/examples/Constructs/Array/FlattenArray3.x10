/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Test for array reference flattening. Checks that after flattening
 * the variable x and y can still be referenced, i.e. are not 
 * declared within local blocks.
 *
 * To check that this test does what it was intended to, examine
 * the output Java file. It should have a series of local variables
 * pulling out the subters of m(a[1,1]).
 */
 
public class FlattenArray3 extends x10Test {

    var a: Array[int](2);

    public def this(): FlattenArray3 = {
        a = Array.make[int](([1..10, 1..10] to Region)->here, ((i,j): Point) => { return i+j;});
    }

    def m(var x: int): int = {
        return x;
    }

    public def run(): boolean = {
        var x: int = m(a(1, 1)); // being called in a method to force flattening.
        var y: int = m(a(2, 2));
        return true;
    }

    public static def main(var args: Rail[String]): void = {
        new FlattenArray3().execute();
    }
    
}
