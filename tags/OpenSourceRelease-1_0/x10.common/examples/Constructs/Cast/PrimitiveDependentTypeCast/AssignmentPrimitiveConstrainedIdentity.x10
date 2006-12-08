import harness.x10Test;

/**
 * Purpose: Cast's dependent type constraint must be satisfied by the primitive.
 * Issue: Value to cast does not meet constraint requirement of target type.
 * @author vcave
 **/
public class AssignmentPrimitiveConstrainedIdentity extends x10Test {

	public boolean run() {
		
		try { 
			int (: self == 0) i = 0;
			int (: self == 0) j = 0;
			i = j;
		}catch(Throwable e) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		new AssignmentPrimitiveConstrainedIdentity().execute();
	}

}
 