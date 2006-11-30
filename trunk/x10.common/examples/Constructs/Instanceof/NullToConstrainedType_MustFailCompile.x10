import harness.x10Test;

/**
 * Purpose: Checks a null value is not an instance of a constrained nullable type
 * Issue: null is not an instance of a constrained nullable type.
 * @author vcave
 **/
public class NullToConstrainedType_MustFailCompile extends x10Test {
	 
	public boolean run() {
		return !(null instanceof X10DepTypeClassOne(:p==1));
	}
	
	public static void main(String[] args) {
		new NullToConstrainedType_MustFailCompile().execute();
	}
}
 