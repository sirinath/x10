/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Check that a float literal can be cast to float.
 */
public class StringLitDepType extends x10Test {
	public def run(): boolean = {
		var f: String("abc") = "abc";
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new StringLitDepType().execute();
	}


}
