/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Purpose: 
 * @author vcave
 **/
public class PrimitiveToNullablePrimitiveConstrained2_MustFailCompile extends x10Test {
	 
	public boolean run() {
		return !(3 instanceof nullable<int(:self==4)>);
	}
	
	public static void main(String[] args) {
		new PrimitiveToNullablePrimitiveConstrained2_MustFailCompile().execute();
	}
}
 