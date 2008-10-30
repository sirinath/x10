/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Purpose: Checks boxed integer value is checks against primtive dependent type.
 * @author vcave
 **/
public class ObjectToPrimitiveConstrained1 extends x10Test {
	 
	public def run(): boolean = {
		var primitive: x10.lang.Object = 3;
		return (primitive instanceof Int(3));
	}
	
	public static def main(var args: Rail[String]): void = {
		new ObjectToPrimitiveConstrained1().execute();
	}
}
