//OPTIONS: -STATIC_CHECKS=false -CONSTRAINT_INFERENCE=false -VERBOSE_INFERENCE=true

package test071;

import harness.x10Test;

public class Test071_DynChecks_MustFailRun extends x10Test {

    public def run(): boolean {
	val b = new B(0);
	val a = new A(new B(0));
	Test071_DynChecks.f(a, b);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test071_DynChecks_MustFailRun().execute();
    }

}
