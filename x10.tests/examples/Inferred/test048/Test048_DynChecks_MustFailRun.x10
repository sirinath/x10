/**
 * inconsistant constraints
 *
 */
//OPTIONS: -STATIC_CHECKS=false -CONSTRAINT_INFERENCE=false -VERBOSE_INFERENCE=true



import harness.x10Test;
import x10.compiler.InferGuard;

public class Test048_DynChecks_MustFailRun extends x10Test {
    static def assert0(x: Long{ self == 0 }){}
    static def assert1(x: Long{ self == 1 }){}

    @InferGuard
    static def f(y:Long) { /*??< y == 0, y == 1 >??*/ }{
        assert0(y);
        assert1(y);
    }

    public def run(): boolean {
	f(0);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test048_DynChecks_MustFailRun().execute();
    }

}
