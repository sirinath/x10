/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Test for foreach.
 *
 * @author kemal, 12/2004
 */
public class Foreach2 extends x10Test {

	public const N: int = 100;
	var nActivities: int = 0;

	public def run(): boolean = {
		final val P0: place = here; // save current place
		final val r: region = [0..N-1];
		final val d: dist = r->P0;

		finish
			foreach (val p: point in d.region) {
				// Ensure each activity spawned by foreach
				// runs at P0
				// and that the hasbug array was
				// all false initially
				if (P0 != d(p) || P0 != here)
					throw new Error("Test failed.");
				atomic { nActivities++; }
			}
		return nActivities == N;
	}

	public static def main(var args: Rail[String]): void = {
		new Foreach2().execute();
	}
}
