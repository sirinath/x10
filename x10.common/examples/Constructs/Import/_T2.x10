/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import ImportTestPackage1.SubPackage.T3;

/**
 * Auxiliary class for ImportTest
 */
public class _T2 {
	public static boolean m2(final int x) {
		return future(here) { T3.m3(x) }.force();
	}
}

