/*
 * Variation on Test061.
 *
 * Change the order of the definition
 *
 */
//OPTIONS: -STATIC_CHECKS=true -CONSTRAINT_INFERENCE=true -VERBOSE_INFERENCE=true

package test062;

import harness.x10Test;
import x10.compiler.InferGuard;

public class Test062 extends x10Test {

    @InferGuard
    static def g(x: int) {
	f(x);
    }

    @InferGuard
    static def f(x: int) {
	val v: int{self == 0} = x;
    }

    public def run(): boolean {
	Test062.g(0);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test062().execute();
    }

}
