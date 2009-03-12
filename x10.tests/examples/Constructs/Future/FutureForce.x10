/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Checks force for grand-children.
 * @author Christoph von Praun
 */
public class FutureForce extends x10Test {

	var flag: Boolean;
	var foo: Int;

	public def bar(): Int = {
		System.out.print("waiting ...");
		x10.lang.Runtime.sleep(2000);
		System.out.println("done.");
		atomic flag = true;
		return 42;
	}

	public def foo(): Int = {
		var r2: Future[Int] = future(here) { bar() };
		return 42;
	}

	public def run(): Boolean = {
		atomic flag = false;
		var r1: Future[Int] = future(here) { foo() };
		r1();
		var b: Boolean;
		atomic b = flag;
		System.out.println("The flag is b=" + b + " (should be true).");
		return (b == true);
	}

	public static def main(var args: Rail[String]): void = {
		new FutureForce().execute();
	}
}
