/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Simple test for operator assignment of array elements.
 */
public class ArrayOpAssign extends x10Test {

	public boolean run() {
		boolean result = true;
		int[.] ia = new int[[1:10,1:10]];
		ia[1,1] = 1;
		ia[1,1] += ia[1,1];
		result &= (2 == ia[1,1]);
		System.out.println("ia[1,1])" + ia[1,1]);
		ia[1,1] *= 2;
		System.out.println("ia[1,1])" + ia[1,1]);
		result &= (4 == ia[1,1]);
		double[.] id = new double[[1:10,1:10]];
		id[1,1] += 42;
		result &= (42 == id[1,1]);
		System.out.println("id[1,1])" + id[1,1]);
		id[1,1] *= 2;
		System.out.println("id[1,1])" + id[1,1]);
		result &= (84 == id[1,1]);
		return result;
	}

	public static void main(String[] args) {
		new ArrayOpAssign().execute();
	}
}

