
/**
 *
 * Testing if c.resume() declares quiescence of current activity on c
 * 
 * c.resume() without next can advance clock c?
 * TODO: Need to clarify definition.
 *
 * next advances when all clocks have advanced to their next phase 
 *
 *
 * @author kemal 4/2005
 */
public class ClockTest12 {

int phase=0;

public boolean run() {
   finish{
	final clock c = clock.factory.clock();
	async clocked(c) taskA(1,c);
	async clocked(c) taskA(2,c);
	async clocked(c) taskB(c);
   }
   return true;
}

void taskA(int id,final clock c) {
	int tmp;
	delay(1000);
	atomic tmp=phase;
	System.out.println(id+ " observed current phase="+tmp);
	chk(tmp==0);
	c.resume(); //  1st next advances in activity #2
	delay(1000);
	c.resume(); // not an error, still in phase 0
	when(phase>0) {
		System.out.println(id+ " observed future phase="+phase);
		chk(phase==1);
		delay(5000);
		chk(phase==1); // cannot go beyond next phase
	}
	next;
	delay(1000);
	atomic tmp=phase;
	System.out.println(id+" observed current phase="+tmp);
	chk(tmp==1);
	c.resume(); // 2nd next advances in activity #2
	c.resume(); // not an error still in phase 1
	c.resume();
	when(phase>1) {
		System.out.println(id+" observed future phase="+phase);
		chk(phase==2);
		delay(5000);
		chk(phase==2); // cannot go beyond next phase
	}
	next;
	next;
}

void taskB(final clock c) {
	int tmp;
	atomic tmp=phase;
	System.out.println("now in phase "+tmp);
	c.resume();
	next;
	atomic phase++;
	atomic tmp=phase;
	System.out.println("now in phase "+tmp);
	c.resume();
	next;
	atomic phase++;
	atomic tmp=phase;
	System.out.println("now in phase "+tmp);
	c.resume();
	next;
}
			
static void chk(boolean b) {
	if (!b) throw new Error();
}

static void delay(int millis) {	
	try{	
		java.lang.Thread.sleep(millis);
	} catch (InterruptedException e) {
	}
}

public static void main(String args[]) {
	boolean b= (new ClockTest12()).run();
	System.out.println("++++++ "+(b?"Test succeeded.":"Test failed."));
	System.exit(b?0:1);
}

}
