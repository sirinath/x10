/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Purpose: Checks null is not an instance of a primitive 
 * Issue: primitive has to be encapsulated in a nullable so that null can be an instanceof.
 * @author vcave
 **/
public class NullToPrimitive_MustFailCompile extends x10Test {
	 
	public boolean run() {
		return !(null instanceof int);
	}
	
	public static void main(String[] args) {
		new NullToPrimitive_MustFailCompile().execute();
	}
}
 