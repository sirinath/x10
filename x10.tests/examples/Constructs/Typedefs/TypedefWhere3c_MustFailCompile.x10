// (C) Copyright IBM Corporation 2008
// This file is part of X10 Test. *

import harness.x10Test;

/**
 * @author bdlucas 9/2008
 */

class TypedefWhere3c_MustFailCompile extends TypedefTest {

    public def run():boolean = {

        type T(x:int){x==1} = int;
        var one:int = 1;
        var a:T(one);

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new TypedefWhere3c_MustFailCompile().execute();
    }
}
