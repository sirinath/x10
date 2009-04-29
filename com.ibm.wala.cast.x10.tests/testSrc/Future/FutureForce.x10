/**
 * Checks force for grand-children.
 * @author Christoph von Praun
 */
public class FutureForce {

	var flag: Boolean;
	var foo: Int;

	public def bar(): Int = {
		x10.io.Console.OUT.print("waiting ...");
		x10.lang.Runtime.sleep(2000);
		x10.io.Console.OUT.println("done.");
		atomic flag = true;
		return 42;
	}

	public def foo(): Int = {
		var r2: Future[Int] = future(here) { bar() };
		return 42;
	}

	public def run(): Boolean = {
		atomic flag = false;
		var r1: Future[Int] = future(here) { foo() };
		r1();
		var b: Boolean;
		atomic b = flag;
		x10.io.Console.OUT.println("The flag is b=" + b + " (should be true).");
		return (b == true);
	}

	public static def main(var args: Rail[String]): void = {
		new FutureForce().run();
	}
}
