//OPTIONS: -STATIC_CHECKS=false -CONSTRAINT_INFERENCE=false -VERBOSE_INFERENCE=true

package test004;

import harness.x10Test;

public class Test004_DynChecks_MustFailRun extends x10Test {

    public def run(): boolean {
	val v = new Vec(42);
	Vec.cp(v, 4012);
        return true;
    }

    public static def main(Array[String](1)) {
    	new Test004_DynChecks_MustFailRun().execute();
    }

}
