//OPTIONS: -STATIC_CHECKS=false -CONSTRAINT_INFERENCE=false -VERBOSE_INFERENCE=true

package test060;

import harness.x10Test;

public class Test060_DynChecks_MustFailRun extends x10Test {

    public def run(): boolean {
	Test060_DynChecks.f(1, 2);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test060_DynChecks_MustFailRun().execute();
    }

}
