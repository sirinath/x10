/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Purpose: Checks cast from nullable to non-nullable that implies 
 *          boxing/unboxing operation.
 * Issue: The nullable primitive is null which makes the cast to a non-nullable primitive fail.
 * @author vcave
 **/
 public class CastNullablePrimitiveToPrimitive extends x10Test {

	public def run(): boolean = {
		try {
         var k: x10.util.Box[int] = null; // ok
			var p: int = k as int; 
			// --> fails because 'k' is null which throws a ClassCastException
		} catch (e: ClassCastException) {
			return true;
		}
		return false;
	}

	public static def main(var args: Rail[String]): void = {
		new CastNullablePrimitiveToPrimitive().execute();
	}
}
