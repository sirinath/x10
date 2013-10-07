//OPTIONS: -STATIC_CHECKS=true -CONSTRAINT_INFERENCE=true -VERBOSE_INFERENCE=true

package test006;

import harness.x10Test;
import x10.compiler.InferGuard;

public class Test006 extends x10Test {

    public def run(): boolean {
	val v = new Vec(42);
	Vec.cp(v, 42);
        return true;
    }

    public static def main(Array[String](1)) {
    	new Test006().execute();
    }

}
