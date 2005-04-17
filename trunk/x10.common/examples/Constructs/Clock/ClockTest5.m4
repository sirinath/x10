define(`C',
`/*Activity_$1*/async(here)clocked$2{m("$1","$2",0);next;m("$1","$2",1);next;}')
define(`Now',
`async(here) clocked$1 finish async(here) $2')
// Automatically generated by the command
// m4 ClockTest5.m4 > ClockTest5.x10
// Do not edit
/**
 * Clock test for  multiple clocks.
 * Testing semantics of next with multiple clocks. 
 *
 * 
 * For a clock c: I cannot advance to my next phase 
 * until all activities registered with me have executed 
 * resume on me for the current phase,
 * and all activities scheduled for completion
 * in my current phase (with now(c)) have globally finished.
 *
 * My phase zero starts when I am declared/created.
 * 
 * For an activity a: My next cannot advance to the 
 * following statement, until all clocks
 * that I am currently registered with have advanced to 
 * their next phase.
 *
 * next will do an implicit resume first on each of the clocks
 * I am registered with.
 *
 * I get registered with a clock c by creating/declaring c,
 * or by being enclosed in a clocked(...,c,...) statement.
 *
 * I can register a child activity of mine with some of the clocks 
 * I am already registered with by
 * async(P) clocked(c1,..,cn) S
 * 
 * Expected result of this test: should not deadlock.
 *
 * Important: The next's do not go in lock step in this test case!
 *
 * For example  activities using (e) may pass their nexts
 * as soon as all other activities using e  have arrived
 * at their nexts: (c,e), (d,e) (c,d,e), although (c,d),(c),(d)
 * have not yet arrived at their nexts.
 *
 * Also see ClockTest15.
 * 

 * 
 *
 * @author kemal 4/2005
 */
public class ClockTest5 {


	public boolean run() {
      		final clock c = clock.factory.clock();
      		final clock d = clock.factory.clock();
      		final clock e = clock.factory.clock();
		C(1A,(c))
		C(1B,(c))
		C(2A,(d))
		C(2B,(d))
		C(3A,(e))
		C(3B,(e))
		C(4A,(c,d))
		C(4B,(c,d))
		C(5A,(c,e))
		C(5B,(c,e))
		C(6A,(d,e))
		C(6B,(d,e))
		C(7A,(c,d,e))
		C(7B,(c,d,e))
		Now((c,d,e),`System.out.println("Parent activity in phase 0 of (c,d,e)");')
		next; 
		Now((e,c,d),`System.out.println("Parent activity in phase 1 of (c,d,e)");')
		next;
		return true;
	}
	static void m(String a,String clocks,int phase) {
		System.out.println("Actitivity "+a+" in phase "+ phase+" of clocks " + clocks);
	}
	
	public static void main(String args[]) {
	   final boxedBoolean bb=new boxedBoolean(false);
	   try {
		finish bb.val= (new ClockTest5()).run();
           } catch (Throwable e) {
		bb.val=false;
		e.printStackTrace();
	   }
           System.out.println("++++++ "+(bb.val?"Test succeeded.":"Test failed."));
           System.exit(bb.val?0:1);
	}

}

class boxedBoolean {
	public boolean val;
	public boxedBoolean(boolean val) { this.val=val;}
}

