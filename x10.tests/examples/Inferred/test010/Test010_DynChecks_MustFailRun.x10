//OPTIONS: -STATIC_CHECKS=false -CONSTRAINT_INFERENCE=false -VERBOSE_INFERENCE=true



import harness.x10Test;

public class Test010_DynChecks_MustFailRun extends x10Test {

    public def run(): boolean {
	Test010_DynChecks.f(1, 1);
        return true;
    }

    public static def main(Rail[String]) {
    	new Test010_DynChecks_MustFailRun().execute();
    }

}
