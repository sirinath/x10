//OPTIONS: -STATIC_CHECKS=true -CONSTRAINT_INFERENCE=true -VERBOSE_INFERENCE=true
package test035;

import harness.x10Test;

public class Test035_MustFailCompile extends x10Test {

    public def run(): boolean {
	Test035.f(new Pair(1, 1));
        return true;
    }

    public static def main(Rail[String]) {
    	new Test035_MustFailCompile().execute();
    }

}
