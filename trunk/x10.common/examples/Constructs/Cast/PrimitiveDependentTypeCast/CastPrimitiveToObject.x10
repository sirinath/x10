import harness.x10Test;

/**
 * Purpose:
 * Issue:
 * @author vcave
 **/
 public class CastPrimitiveToObject extends x10Test {

	public boolean run() {
		x10.lang.Object obj = ((x10.lang.Object) 3);
		return true;
	}

	public static void main(String[] args) {
		new CastPrimitiveToObject().execute();
	}
}