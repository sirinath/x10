/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Purpose: Checks a null value is not an instance of a constrained type
 * Issue: null is not an instance of a constrained type.
 * @author vcave
 **/
public class NullToConstrainedType_MustFailCompile extends x10Test {
	 
	public def run(): boolean = {
		return !(null instanceof X10DepTypeClassOne{p==1});
	}
	
	public static def main(var args: Rail[String]): void = {
		new NullToConstrainedType_MustFailCompile().execute();
	}
}
