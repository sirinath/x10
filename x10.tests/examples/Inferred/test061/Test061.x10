/*
 * Multiple functions
 *
 */
//OPTIONS: -STATIC_CHECKS=true -CONSTRAINT_INFERENCE=true -VERBOSE_INFERENCE=true

package test061;

import harness.x10Test;
import x10.compiler.InferGuard;

public class Test061 extends x10Test {

    @InferGuard
    static def f(x: int) {
	val v: int{self == 0} = x;
    }

    @InferGuard
    static def g(x: int) {
	f(x);
    }

    public def run(): boolean {
	Test061.g(0);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test061().execute();
    }

}
