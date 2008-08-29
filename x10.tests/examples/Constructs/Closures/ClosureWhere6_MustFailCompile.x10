// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * "Where" constraints on var parameters are not allowed.
 */

public class ClosureWhere6_MustFailCompile extends ClosureTest {

    public def run(): boolean = {
        
        // not allowed
        val f = (var x:int){x==1} => x;

        return result;
    }


    public static def main(var args: Rail[String]): void = {
        new ClosureWhere6_MustFailCompile().execute();
    }
}
