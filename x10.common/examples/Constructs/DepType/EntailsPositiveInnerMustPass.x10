import harness.x10Test;

/**
 * The test checks that property syntax is accepted.
 *
 * @author vj
 */
public class EntailsPositiveInnerMustPass extends x10Test {
    class Test(int i, int j) {
       public Test(int i, int j) { this.i=i; this.j=j;}
    }

	public boolean run() {
	    final int j = 0;
	    Test(: i==j) x =  (Test(:i==j)) new Test(1,1); 
	    return true;
	}
	public static void main(String[] args) {
		new EntailsPositiveInnerMustPass().execute();
	}
}

