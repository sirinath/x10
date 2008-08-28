/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
//LIMITATION:
//This test case will not meet expectations. It is a limitation of the current release.
import harness.x10Test;;

/**
 *Tests upper and lower triangular, and banded regions.
 *
 * @author kemal 4/2005
 */
public class RegionTriangular extends x10Test {

	public def run(): boolean = {
		val Universe: region = [0..7, 0..7];
		var upperT: region = region.makeUpperTriangular(8);
		pr("upperT", upperT);
		for (val (i,j): point in Universe) chk(iff(i <= j, upperT.contains([i, j])));
		var lowerT: region = region.makeLowerTriangular(8);
		pr("lowerT", lowerT);
		for (val (i,j): point in Universe) chk(iff(i >= j, lowerT.contains([i, j])));
		return true;
	}

	static def iff(var x: boolean, var y: boolean): boolean = {
		return (x == y);
	}

	static def pr(var s: String, var r: region): void = {
		System.out.println();
		System.out.println("printing region "+s);
		var k: int = 0;
		val N: int = 8;
		for (val (i,j): point in [0..N-1, 0..N-1]) {
			System.out.print(" "+(r.contains([i, j]) ? "+" : "."));
			if ((++k) % N == 0) System.out.println();
		}
	}

	public static def main(var args: Rail[String]): void = {
		new RegionTriangular().execute();
	}
}
