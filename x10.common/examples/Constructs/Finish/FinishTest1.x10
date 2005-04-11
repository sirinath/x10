import x10.lang.*;
/**
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 * Minimal test for finish, using an async.
 * run() method returns true if successful, false otherwise.
 */
public class FinishTest1  {

	boolean flag;

	public boolean run() {
		finish {
			async (here) { atomic{flag = true;} }
		}
          boolean b;
          atomic{b=flag;}
	  return b;
	}
	public static void main(String args[]) {
		boolean b= (new FinishTest1()).run();
		System.out.println("++++++ "+(b?"Test succeeded.":"Test failed."));
		System.exit(b?0:1);
	}
}
