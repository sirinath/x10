// (C) Copyright IBM Corporation 2006
// This file is part of X10 Test. *

import harness.x10Test;


/**
 * Closure expressions have zero or more type parameters,
 * 
 * Methods, constructors, closures, and type definitions may have type
 * parameters, which are instantiated with concrete types at invocation
 * (�4.2).
 * 
 * A method, constructor, or closure may have type parameters whose scope
 * is the signature and body of the declaring method, constructor or
 * closure.
 * 
 *
 * @author bdlucas 8/2008
 */

public class ClosureTypeParameters1f extends ClosureTest {

    public def run(): boolean = {
        
        val f = [T](x:T,y:int) => x.toString() + y.toString();
        check("([T](x:T,y:int)=>x+y)[int](1,1)",  f[int](1,1), "11");

        return result;
    }

    public static def main(var args: Rail[String]): void = {
        new ClosureTypeParameters1f().execute();
    }
}
