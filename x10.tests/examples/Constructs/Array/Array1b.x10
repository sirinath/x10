/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Simple array test.
 *
 * Same as Array1 but shorthand forms (such as ia[p]) are used.
 */
public class Array1b extends x10Test {

	public def run(): boolean = {
		val e: region{rank==1} = 1..10;
		val r: region = [e, e];
		chk(r.equals([1..10, 1..10]));
		//final dist d = r->here;
		val d: dist = Dist.makeConstant([1..10, 1..10], here);
		chk(d.equals(Dist.makeConstant([1..10, 1..10], here)));
		chk(d.equals(Dist.makeConstant([e, e], here)));
		chk(d.equals(Dist.makeConstant(r, here)));
		val ia: Array[int] = Array.make[int](d, (point)=>0);

		for (val p: point in e) for (val q: point in e) {
				var i: int = p(0);
				var j: int = q(0);
				chk(ia(i, j) == 0);
				ia(i, j) = i+j;
			}

		for (val p: point(2) in ia) {
			var i: int = p(0);
			var j: int = p(1);
			var q1: point = [i, j];
			chk(i == q1(0));
			chk(j == q1(1));
			chk(ia(i, j) == i+j);
			chk(ia(i, j) == ia(p));
			chk(ia(q1) == ia(p));
			ia(p) = ia(p) - 1;
			chk(ia(p) == i + j - 1);
			chk(ia(q1) == ia(p));
		}

		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new Array1b().execute();
	}
}
