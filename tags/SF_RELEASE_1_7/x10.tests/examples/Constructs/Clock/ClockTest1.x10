/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
// Automatically generated by the command
// m4 ClockTest1.m4 > ClockTest1.x10
// Do not edit
import harness.x10Test;;

/**
 * Minimal test for clock.
 * run() method returns true if successful, false otherwise.
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 * @author vj -- Fixed use of clocks and finish.
 */
public class ClockTest1 extends x10Test {

	var flag: boolean;

	public def run(): boolean = {
		// main activity A0
	    val c = Clock.make();
		
	    // the fact of spawning this clocked activity increases 
	    // the number of registered activities to two.
	    async(here) clocked(c)  // activity A1
	    finish async(here) { // activity A2
		atomic { flag = true; }  
	    } // A1 drops all registered clocks
		
	    // According to thread scheduling two scenario are possibles:
	    // - A1 terminates before A0 reaches next. Hence A0 perform the next and is not blocked
	    // - A0 reaches next and is blocked until A1 finished. When A1 do so, the 
	    //   number of activities registered with clock c is decremented, which release A0
	    //   that becomes the only activity to wait on the clock "barrier"
	    next;
	    var b: boolean;
	    atomic { b = flag; }
	    return b;
	}

	public static def main(var args: Rail[String]): void = {
		new ClockTest1().executeAsync();
	}
}
