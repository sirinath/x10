import harness.x10Test;

/**
 * Purpose: 
 * @author vcave
 **/
public class PrimitiveToNullablePrimitiveConstrained3 extends x10Test {
	 
	public boolean run() {
		int a = 3;
		return (a instanceof nullable<int(:self==3)>);
	}
	
	public static void main(String[] args) {
		new PrimitiveToNullablePrimitiveConstrained3().execute();
	}
}
 