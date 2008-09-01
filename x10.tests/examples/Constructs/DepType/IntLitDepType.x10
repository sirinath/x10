/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Check that an int lit generates the correct dep clause.
 */
public class IntLitDepType extends x10Test {
	public def run(): boolean = {
		var f: int(1) = 1;
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new IntLitDepType().execute();
	}


}
