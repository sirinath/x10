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
public class FloatLit extends x10Test {
	public boolean run() {
		float f = (float) 0.001;
		return true;
	}

	public static void main(String[] args) {
		new FloatLit().execute();
	}


}

