//OPTIONS: -STATIC_CHECKS=true -CONSTRAINT_INFERENCE=true -VERBOSE_INFERENCE=true

package test001;

import harness.x10Test;

public class Test001_MustFailCompile extends x10Test {

    public def run(): boolean {
	Test001.f(1);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test001_MustFailCompile().execute();
    }


}
