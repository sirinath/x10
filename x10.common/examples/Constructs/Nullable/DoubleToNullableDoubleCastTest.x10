public class DoubleToNullableDoubleCastTest {
		public boolean run() {
			nullable double data = (nullable double) 1.0; 
			return true;
		}

		public static void main(String[] args) {
			final boolean b = false;
			try {
				finish b =(new DoubleToNullableDoubleTest()).run();
			} catch (Throwable e) {
				e.printStackTrace();
				b.= false;
			}
			System.out.println("++++++ "+(b?"Test succeeded.":"Test failed."));
			x10.lang.Runtime.setExitCode(b?0:1);
		}
	}
}