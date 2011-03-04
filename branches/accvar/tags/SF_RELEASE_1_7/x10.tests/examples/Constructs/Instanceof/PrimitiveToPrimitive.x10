/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Purpose: Checks literal primitive is an instance of the same type.
 * @author vcave
 **/
public class PrimitiveToPrimitive extends x10Test {
	 
	public def run() = 3 instanceof Int;
	
	public static def main(var args: Rail[String]): void = {
		new PrimitiveToPrimitive().execute();
	}
}
