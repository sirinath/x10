/**
 * @author kemal 5/2005
 *
 *OLD SEMANTICS:
 *The x10 compiler must conservatively check if an activity can 
 *potentially
 *pass a clock it is not registered with,
 *to a subactivity.
 *If so, it must report a compile time error.
 *
 *Language clarification needed on disambiguation
 *algorithm to use.
 *
 *Compile time analysis may not be possible in some cases.
 *
 *NEW SEMANTICS: Clock Use Exception such as
 *
 * 'Transmission of c (to a child) requires that I am registered with c'
 *
 * 'Transmission of c requires that I am not between c.resume() and a next'
 *
 * 'The immediate body of finish  can never transmit any clocks'
 *
 * are now caught at run time. The compiler
 * can remove the run time checks using static techniques,
 * and can issue warnings when it is statically detected that
 * clock use exceptions will
 * definitely occur, or will likely occur.
 * 
 * Hence this file is renamed as *MustFailRun.x10
 
 *
 */
/**
 * A class to invoke a 'function pointer' inside of async
 */
class Y {
    static void test(final foo f, final clock c) {
        // Compiler analysis may not be possible here
	async clocked(c) {
          f.apply(); // it is hard to determine f does an async clocked(c2) S, where c2!=c
          next;
	}
    }
}

public class ClockTest16_MustFailRun {

	public boolean run() {
           final X x=new X();
           finish async {
		final clock c0=clock.factory.clock(); 
		final clock c1=clock.factory.clock();
		final clock[] ca= new clock[] {c0, c1};

                // Question:
                // Can an activity ever pass a clock it is not
                // registered with to a 
                // subactivity of itself, in statement async(cx) S?

                // Compiler answer: NO, actual runtime answer: NO
	        async clocked(c1) {
                    final clock cx=ca[1];
		    async clocked(cx) {// no clock use error 
			next;
                    }
                    next;
                }

                // Compiler: MAYBE, actual: NO
	        async clocked(c1) {
                    final clock cx=ca[x.one()];
		    async clocked(cx) { //no clock use error
                       next;
                    }
                    next;
                }

                // Compiler: MAYBE, actual: YES
	        async clocked(c1) {
                    final clock cx=ca[x.zero()];
		    async clocked(cx) { // clock use error 
                       next;
                    }
                    next;
                }

                final foo f0= new foo() {
                  public void apply() {
                    final clock cx=ca[x.zero()];
		    async clocked(cx) { // clock use error 
                       next;
                    }
                  }
                };

                final foo f1= new foo() {
                  public void apply() {
                    final clock cx=ca[x.one()];
		    async clocked(cx) { // no clock use error 
                       next;
                    }
                  }
                };

                final foo[] fooArray=new foo[] {f0,f1};
                    
                 
                // Compiler: MAYBE, Actual: NO
                Y.test(fooArray[x.one()],c1);

                // Compiler: MAYBE, Actual: YES
                Y.test(fooArray[x.zero()],c1);

                // Compiler: YES, actual:YES
	        async clocked(c1) {
                    final clock cx=ca[0];
		    async clocked(cx) { // clock use error 
                       next;
                    }
                    next;
                }

                next;
           }
                

	   return true;

	}
	
    public static void main(String[] args) {
        final boxedBoolean b=new boxedBoolean();
        try {
                finish async b.val=(new ClockTest16_MustFailRun()).run();
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

/**
 * An interface to use like a simple 'function pointer'
 *
 * foo f1=new foo(){public void apply() S1}; //assign body S1 to f1
 *
 * // values of free final variables of S1 are also captured in f1.
 *
 * f1.apply(); // invoke S1 indirectly using its captured
 *
 * // free variables 
 *
 */
interface foo {
    public void apply();
}

/** 
 * Dummy class to make static memory disambiguation difficult
 * for a typical compiler
 */
class X {
    public int[] z={1,0};
    int zero() { return z[z[z[1]]];} 
    int one() { return z[z[z[0]]];} 
    void modify() {z[0]+=1;}
}
