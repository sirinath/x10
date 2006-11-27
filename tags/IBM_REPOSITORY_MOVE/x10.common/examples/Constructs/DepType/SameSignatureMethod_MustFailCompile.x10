/** Tests that two methods in a class cannot have the same signature.
 *@author pvarma
 *
 */

import harness.x10Test;

public class SameSignatureMethod_MustFailCompile extends x10Test { 

	class Test(int i, int j) {
		// the two tester methods with same signature are disallowed
		public this(:i==5) boolean tester (final int(: k == 0) k) {return true;}
		public this(:i==5) int tester (final int(: l == 0) l) {return l;}
		Test (final int i, final int j ) {
			this.i=i;
			this.j=j;
		}
	}
		
	public boolean run() {
	   return true;
	}  
	
    public static void main(String[] args) {
        new SameSignatureMethod_MustFailCompile().execute();
    }
   

		
}
