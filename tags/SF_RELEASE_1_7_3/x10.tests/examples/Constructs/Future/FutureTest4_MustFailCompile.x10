/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Tests that free variables used in e in future { e }
 * are declared final.
 *
 * Expected result: must fail at compile time.
 *
 * @author kemal 4/2005
 */
public class FutureTest4_MustFailCompile extends x10Test {

	public const N: int = 8;

	/**
	 * testing free variables in future expression
	 */
	public def run(): boolean = {
		val A: Array[int] = Array.make[int](Dist.makeBlock([0..N-1, 0..N-1]), 
		  (var (i,j): Point): int =>  N*i+j);
		var x: int;
		var s: int;
		x = 0;
		s = 0;
		for (val (i): Point in 0..N-1) {
			s += i;
			//=== >compiler error: i, s not final
			x += (future(A.dist([i, s%N] as Point)) { A(i, s%N) }).force();
		}
		x10.io.Console.OUT.println("x = "+x);
		if (x != 252) return false;
		x = 0;
		s = 0;
		for (val (i) :Point in 0..N-1) {
			s += i;
			val I: int = i; val S: int = s;
				// no compiler error
				x += (future(A.dist([I, S%N] as Point)) A(I, S%N) ).force();
			
		}
		x10.io.Console.OUT.println("x = "+x);
		return (x == 252);
	}

	public static def main(var args: Rail[String]): void = {
		new FutureTest4_MustFailCompile().execute();
	}
}
