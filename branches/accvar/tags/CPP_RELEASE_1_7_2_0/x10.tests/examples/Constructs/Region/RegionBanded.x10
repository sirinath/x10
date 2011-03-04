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
 * Tests upper and lower triangular, and banded regions.
 *
 * @author kemal 4/2005
 */
public class RegionBanded extends x10Test {

	public def run(): boolean = {
		val Universe: Region = [0..7, 0..7];
		var banded1: Region = Region.makeBanded(8, 1);
		pr("banded1", banded1);
		for (val (i,j): Point in Universe) chk(iff(i == j, banded1.contains([i, j])));

		// region banded2 = Region.makeBanded(8, 2);
		// pr("banded2", banded2);
		// not sure if 2nd band is to north or south of diagonal
		// for (point [i,j]: Universe)
		// chk(iff(j == i || j == i+1, banded2.contains([i,j])));

		var banded3: Region = Region.makeBanded(8, 3);
		pr("banded3", banded3);
		for (val (i,j): Point in Universe) chk(iff(j == i-1 || j == i || j == i+1, banded3.contains([i, j])));

		// region banded4 = Region.makeBanded(8, 4);
		// pr("banded4", banded4);
		// for (point [i,j]: Universe)
		// chk(iff((j == i-1 || j == i || j == i+1 || j == i+2),
		// banded4.contains([i,j])));

		return true;
	}

	static def iff(var x: boolean, var y: boolean): boolean = {
		return (x == y);
	}

	static def pr(var s: String, var r: Region): void = {
		System.out.println();
		System.out.println("printing region "+s);
		var k: int = 0;
		val N: int = 8;
		for (val (i,j): Point in [0..N-1, 0..N-1]) {

			System.out.print(" "+(r.contains([i, j]) ? "+" : "."));
			if ((++k) % N == 0) System.out.println();
		}
	}

	public static def main(var args: Rail[String]): void = {
		new RegionBanded().execute();
	}
}
