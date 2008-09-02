// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test. *

import harness.x10Test;

/**
 * Basic typdefs and type equivalence.
 *
 * @author bdlucas 9/2008
 */

public class TypedefBasic4 extends TypedefTest {

    public def run(): boolean = {
        
        class X(i:int,s:String) {def this(i:int,s:String):X(i,s) = property(i,s);}

        type A(i:int,s:String) = X(i,s);
        a:A(1,"1") = new X(1,"1");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new TypedefBasic4().execute();
    }
}
