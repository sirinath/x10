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
 * Issue: 
 * @author vcave
 **/
public class DynamicCast8_AssignmentNullToConstrainedNullable_MustFailCompile extends x10Test {
	public boolean run() {		
		nullable<X10DepTypeClassTwo(:p==0&&q==1)> nullObject1 = null;
		return false;
	}

	public static void main(String[] args) {
		new AssignmentNullToConstrainedNullable_MustFailCompile().execute();
	}
}
 