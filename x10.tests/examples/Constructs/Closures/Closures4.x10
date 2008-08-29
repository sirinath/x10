
/*
 *
 * (C) Copyright IBM Corporation 2008
 *
 *  This file is part of X10 Test.
 *
 *
 * @author bdlucas 8/2008
 */
import harness.x10Test;

/**
 * Test closures.
 *
 * @author nystrom 8/2008
 */
public class Closures4 extends x10Test {
        static class C implements (int, int) => int {
                public def apply(i: int, j: int):int = {
                        return i+j;
                }
        }
                
	public def run(): boolean = {
                val x = new C();
                val j = x(3,4);
                return j == 7;
	}

	public static def main(var args: Rail[String]): void = {
		new Closures4().execute();
	}
}

