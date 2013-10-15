//OPTIONS: -STATIC_CHECKS=false -CONSTRAINT_INFERENCE=false -VERBOSE_INFERENCE=true



import harness.x10Test;

public class Test066_DynCheckException extends x10Test {

    public def mustFailRun(): boolean {
	Test066_DynChecks.g(0, 0);
	Test066_DynChecks.f(false, 0, 1);
        return true;
    }

    public def run() {
        try { mustFailRun(); return false; } catch (FailedDynamicCheckException) {}
        return true;
    }

    public static def main(Rail[String]) {
    	new Test066_DynCheckException().execute();
    }

}
