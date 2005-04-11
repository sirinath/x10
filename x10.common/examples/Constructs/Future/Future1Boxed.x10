import x10.lang.*;
public class Future1Boxed {
	public boolean run() {
		future<x10.compilergenerated.BoxedInteger> x = future (here) {new x10.compilergenerated.BoxedInteger(42)};
		return (x.force()).intValue()==42;
	}
	public static void main(String args[]) {
		boolean b= (new Future1Boxed()).run();
		System.out.println("++++++ "+(b?"Test succeeded.":"Test failed."));
		System.exit(b?0:1);
	}
}
