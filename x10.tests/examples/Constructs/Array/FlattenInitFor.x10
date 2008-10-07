/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;


/**
 * 
 */

public class FlattenInitFor extends x10Test {

    val a: Array[int](2);

    public def this(): FlattenInitFor = {
        a = Array.make[int](([1..10, 1..10] to Region)->here, ((i,j): Point): int => { return i;});
    }
    
    public def run(): boolean = {
        for (var e: int = (future (a.dist(1, 1)) { a(1, 1) }).force(); e < 3 ; e++) 
            System.out.println("done.");        
        return true;
    }

    public static def main(var args: Rail[String]): void = {
        new FlattenInitFor().execute();
    }
}
