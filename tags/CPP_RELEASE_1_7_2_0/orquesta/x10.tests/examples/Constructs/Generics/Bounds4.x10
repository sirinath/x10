/*
 *
 * (C) Copyright IBM Corporation 2008
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Test type parameter bounds.
 *
 * @author nystrom 8/2008
 */
public class Bounds4 extends x10Test {
        class A { }
        class B extends A { }

        class C[T]{T<:A} {
                var x: T;
                def this(y: T) = { x = y; }
        }

	public def run(): boolean = {
                val a = new A();
                return new C[A](a).x == a;
	}

	public static def main(var args: Rail[String]): void = {
		new Bounds4().execute();
	}
}

