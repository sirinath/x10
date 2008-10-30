/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Check that a float literal can be cast to float.
 */
public class CharLitDepType_MustFailCompile extends x10Test {
	public boolean run() {
		char(:self=='b') f =  'a';
		return true;
	}

	public static void main(String[] args) {
		new CharLitDepType_MustFailCompile().execute();
	}


}

