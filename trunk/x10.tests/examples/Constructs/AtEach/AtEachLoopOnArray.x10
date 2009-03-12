/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Test for an ateach loop on an array.
 *
 * @author vj
 */
public class AtEachLoopOnArray extends x10Test {
	var success: boolean = true;

	public def run(): boolean = {
		val A: Array[double] =
                        Array.make[double]([0..10]->here, ((i): Point): double => i as double);

		finish ateach (val (i): Point in A)
                        if (A(i) != i)
				async (this.location) atomic { success = false; }

		return success;
	}

	public static def main(var args: Rail[String]): void = {
		new AtEachLoopOnArray().execute();
	}
}
