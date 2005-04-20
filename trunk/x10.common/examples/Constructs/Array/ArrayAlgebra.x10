/*
 * @author kemal 4/2005
 *
 * Constant promotions to arrays: (D n)
 * 
 * disjoint union and overlay of arrays
 *
 * array scan and reduce. 
 *
 *
 */
public class ArrayAlgebra {

	const int N=24;

	public boolean run() {
		
		distribution D=distribution.factory.blockcyclic([0:N-1],2);
		distribution D01=D|[0:N/2-1];
		distribution D23=D|[N/2:N-1];
		distribution D0=D|[0:N/4-1];
		distribution D1=D|[N/4:N/2-1];
		distribution D2=D|[N/2:3*N/4-1];
		distribution D3=D|[3*N/4:N-1];
		int[.] ia1=
		 (D -99).overlay((D01 -1)||(D23 -2)).overlay(D3 3).overlay(D0 9);
		arrEq(ia1|D0,(D0 9));
		arrEq(ia1|D1,(D1 -1));
		arrEq(ia1|D2,(D2 -2));
		arrEq(ia1|D3,(D3 3));
		chk(ia1.sum()==9*N/4);
		arrEq(ia1.scan(intArray,0),
		      new int[D](point [i])
		      {return (ia1|(0:i)).reduce(intArray,0);});
		return true;
	}

	/**
	 * Throw an error if x and y are not arrays with same
	 * content and distribution
	 */

	static void arrEq(int[.] x,int[.] y) {
		chk(x.distribution.equals(y.distribution));
		ateach(point p:x) {
			chk(x[p]==y[p]);
		}
	}	

 	/**
	 * Throw an error if b is false
	 */
	static void chk(boolean b) {

		if(!b) throw new Error();
	}

	/**
	 * Main method
	 */
	public static void main(String args[]) {
		boolean b= (new ArrayAlgebra()).run();
		System.out.println("++++++ "+(b?"Test succeeded.":"Test failed."));
		System.exit(b?0:1);
	}

}
