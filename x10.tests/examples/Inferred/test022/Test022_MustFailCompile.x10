//OPTIONS: -STATIC_CHECKS=true -CONSTRAINT_INFERENCE=true -VERBOSE_INFERENCE=true

package test022;

import harness.x10Test;

public class Test022_MustFailCompile extends x10Test {

    public def run(): boolean {
	Test022.f(true, 1, 1, 1);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test022_MustFailCompile().execute();
    }

}
