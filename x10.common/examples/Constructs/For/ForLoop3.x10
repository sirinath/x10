import x10.lang.*;
/**
 * Test for for loop with x10 for(point p: 1:N ) syntax
 *
 * @author: kemal, 1/2005
 */
public class ForLoop3 {
        static final int N=100;

	public boolean run() {
			
		//Ensure iterator works in lexicographic order
		int n = 0;
		int prev = -1;
                for(point p:(0:N-1)->here) {
			n += p[0];
			if (prev+1!=p[0]) return false;
			prev=p[0];
		}
		if (n != N*(N-1)/2) return false;

		// now iterate over a region
		n=0;
		prev = -1;
                for(point p: 0:N-1) {
			n += p[0];
			if (prev+1!=p[0]) return false;
			prev=p[0];
		}
		if (n != N*(N-1)/2) return false;
		return true;
	}

	public static void main(String args[]) {
		boolean b= (new ForLoop3()).run();
		System.out.println("++++++ "+(b?"Test succeeded.":"Test failed."));
		System.exit(b?0:1);
	}
}
