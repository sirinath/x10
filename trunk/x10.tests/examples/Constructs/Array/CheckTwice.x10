/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Check that the deptype of a local variable declaration is propagated
 correctly to a reference to that variable.
 @author vj
 */
public class CheckTwice extends x10Test {

	public def run(): boolean = {
		val e  = 1..10;
		val f  = [e, e];
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new CheckTwice().execute();
	}

}
