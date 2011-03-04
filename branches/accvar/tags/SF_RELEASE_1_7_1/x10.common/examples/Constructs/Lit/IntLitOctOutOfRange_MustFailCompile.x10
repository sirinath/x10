/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */

import harness.x10Test;

/**
 * An error must be thrown by the compiler on encountering a lit with type int and the value
 of the literal cannot be stored in an int. 
 *
 * @author vj 1/2006
 */
public class IntLitOctOutOfRange_MustFailCompile extends x10Test {

	public boolean run() {
		System.out.println(0x77777777777777777777);
		return true;
	}

	public static void main(String[] args) {
		new IntLitOctOutOfRange_MustFailCompile().execute();
	}

	
}

