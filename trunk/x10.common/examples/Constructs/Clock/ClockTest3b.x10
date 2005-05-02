
// Automatically generated by the command
// m4 ClockTest3b.m4 > ClockTest3b.x10
// Do not edit
/**
 * Clock test for  barrier functions.
 * Alternate barrier version where parent activity terminates, and
 * finish is used to wait for the children
 * @author kemal 3/2005
 */
public class ClockTest3b {
	
	int val=0;
	static final int N=32;
	
	public boolean run() {
		
                 finish async {
		 final clock c = clock.factory.clock();
		 foreach (point [i]: 0:(N-1)) clocked(c) {
			async(here) clocked(c) finish async(here) {atomic val++;}
			next;
			if (val != N) {
			    throw new Error();
			}
			next;
			async(here) clocked(c) finish async(here) {atomic val++;}
			next;
		 }
		}
		if (val !=2*N) {
			throw new Error();
		}
		return true;
	}
	
	
    public static void main(String[] args) {
        final boxedBoolean b=new boxedBoolean();
        try {
                finish b.val=(new ClockTest3b()).run();
        } catch (Throwable e) {
                e.printStackTrace();
                b.val=false;
        }
        System.out.println("++++++ "+(b.val?"Test succeeded.":"Test failed."));
        x10.lang.Runtime.setExitCode(b.val?0:1);
    }
    static class boxedBoolean {
        boolean val=false;
    }

	
}
