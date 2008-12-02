/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Purpose: Checks a constrained cast leading to primitive unboxing works 
 *          actually checks the unboxed primitive.
 * @author vcave
 **/
 public class CastBoxedToNullablePrimitiveConstrained extends x10Test {

	public def run(): boolean = {
		// transformed to (nullable<int(:self==3)>) ((BoxedInteger) obj).intValue();
		// which means the actual value of the boxed integer will be checked.
		var i: Box[int{self==3}] = mth() to Box[int{self==3}];
		return true;
	}
	
	public def mth(): x10.lang.Object = {
		// boxed to BoxedInteger(3);
		return 3;
	}
	public static def main(var args: Rail[String]): void = {
		new CastBoxedToNullablePrimitiveConstrained().execute();
	}
}
