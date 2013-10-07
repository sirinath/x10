/*
 * Fields. Variation of Test068.
 *
 * Inversion of asserts.
 *
 */
//OPTIONS: -STATIC_CHECKS=false -CONSTRAINT_INFERENCE=false -VERBOSE_INFERENCE=true

package test069;

import harness.x10Test;
import x10.compiler.InferGuard;

public class Test069_DynChecks extends x10Test {
    static def assertEq (x: Long, y: Long){ x == y } {}
    static def assertEqB (x: B, y: B){ x == y } {}

    @InferGuard
    static def f (a: A, b: B) {
	assertEqB(a.a, b);
	assertEq(a.a.b, b.b);
    }

    public def run(): boolean {
	val b = new B(0);
	val a = new A(b);
	Test069_DynChecks.f(a, b);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test069_DynChecks().execute();
    }

}
