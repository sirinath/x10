/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Check that a float literal can be cast as float.
 */
public class CharLitDepType_MustFailCompile extends x10Test {
	public def run(): boolean = {
		var f: Char{self == 'b'} = 'a';
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new CharLitDepType_MustFailCompile().execute();
	}


}
