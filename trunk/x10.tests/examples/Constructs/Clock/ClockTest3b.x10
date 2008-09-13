/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
// Automatically generated by the command
// m4 ClockTest3b.m4 > ClockTest3b.x10
// Do not edit
import harness.x10Test;;

/**
 * Clock test for barrier functions.
 * Alternate barrier version where parent activity terminates, and
 * finish is used to wait for the children
 * @author kemal 3/2005
 */
public class ClockTest3b extends x10Test {

	var val: int = 0;
	const N: int = 32;

	public def run(): boolean = {
		finish async {
			val c: clock = clock.make();
			foreach (val (i): point in 0..(N-1)) {
				async(here) clocked(c) finish async(here) { atomic val++; }
				next;
				chk(val == N);
				next;
				async(here) clocked(c) finish async(here) { atomic val++; }
				next;
			}
		}
		chk(val == 2*N);
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new ClockTest3b().executeAsync();
	}
}
