/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Purpose: Checks a null value is an instance of a constrained nullable type.
 * @author vcave
 **/
public class NullToNullableConstrainedType extends x10Test {
	 
	public boolean run() {
		return !(null instanceof nullable<X10DepTypeClassOne(:p==1)>);
	}
	
	public static void main(String[] args) {
		new NullToNullableConstrainedType().execute();
	}
}
 