/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Tests point (p[i,j]) notation.
 */
public class Array1Exploded extends x10Test {

    public def select(p(i,j): point, (k,l): point)=i+k;

	public def run(): boolean = {
		var d: dist = Dist.makeConstant([1..10, 1..10], here);
		var ia: Array[int] = Array.make[int](d);

		for (val p(i,j): point in [1..10, 1..10]) {
			chk(ia(p) == 0);
			ia(p) = i+j;
		}

		for (val p(i,j): point in d) {
			var q1: point = [i, j];
			chk(i == q1(0));
			chk(j == q1(1));
			chk(ia(i, j) == i+j);
			chk(ia(i, j) == ia(p));
			chk(ia(q1) == ia(p));
		}

		chk(4 == select([1, 2], [3, 4]));

		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new Array1Exploded().execute();
		}
}
