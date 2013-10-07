/*
 * Variation on Test057
 *
 * Lost of information through type constraint.
 *
 */
//OPTIONS: -STATIC_CHECKS=true -CONSTRAINT_INFERENCE=true -VERBOSE_INFERENCE=true

package test059;

import harness.x10Test;
import x10.compiler.InferGuard;

public class Test059_MustFailCompile extends x10Test {

    @InferGuard
    static def f (x: int, y: int) {
	val v1: Long = x;
	val v2: Long{self == v1} = y;
    }

    public def run(): boolean {
	f(0, 0);
	return true;
    }

    public static def main(Rail[String]) {
    	new Test059_MustFailCompile().execute();
    }

}
