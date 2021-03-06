/*
 * Variation on Test061.
 *
 * Change the order of the definition
 *
 */
//OPTIONS: -STATIC_CHECKS=false -CONSTRAINT_INFERENCE=false -VERBOSE_INFERENCE=true



import harness.x10Test;
import x10.compiler.InferGuard;

public class Test062_DynChecks extends x10Test {

    @InferGuard
    static def g(x: Long) {
	f(x);
    }

    @InferGuard
    static def f(x: Long) {
	val v: Long{self == 0} = x;
    }

    public def run(): boolean {
	Test062_DynChecks.g(0);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test062_DynChecks().execute();
    }

}
