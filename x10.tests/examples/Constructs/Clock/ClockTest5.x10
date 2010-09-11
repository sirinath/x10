/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

import harness.x10Test;

/**
 * Clock test for multiple clocks.
 * Testing semantics of next with multiple clocks.
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
 * For example: activities using (e) may pass their nexts
 * as soon as all other activities using e have arrived
 * at their nexts: (c,e), (d,e) (c,d,e), although (c,d),(c),(d)
 * have not yet arrived at their nexts.
 *
 * Also see ClockTest15.
 *
 * @author kemal 4/2005
 */
public class ClockTest5 extends x10Test {

	public def run(): boolean = {
		val c: Clock = Clock.make();
		val d: Clock = Clock.make();
		val e: Clock = Clock.make();
		/*Activity_1A*/async(here) clocked(c) { m("1A", "(c)", 0); next; m("1A", "(c)", 1); next; }
		/*Activity_1B*/async(here) clocked(c) { m("1B", "(c)", 0); next; m("1B", "(c)", 1); next; }
		/*Activity_2A*/async(here) clocked(d) { m("2A", "(d)", 0); next; m("2A", "(d)", 1); next; }
		/*Activity_2B*/async(here) clocked(d) { m("2B", "(d)", 0); next; m("2B", "(d)", 1); next; }
		/*Activity_3A*/async(here) clocked(e) { m("3A", "(e)", 0); next; m("3A", "(e)", 1); next; }
		/*Activity_3B*/async(here) clocked(e) { m("3B", "(e)", 0); next; m("3B", "(e)", 1); next; }
		/*Activity_4A*/async(here) clocked(c, d) { m("4A", "(c, d)", 0); next; m("4A", "(c, d)", 1); next; }
		/*Activity_4B*/async(here) clocked(c, d) { m("4B", "(c, d)", 0); next; m("4B", "(c, d)", 1); next; }
		/*Activity_5A*/async(here) clocked(c, e) { m("5A", "(c, e)", 0); next; m("5A", "(c, e)", 1); next; }
		/*Activity_5B*/async(here) clocked(c, e) { m("5B", "(c, e)", 0); next; m("5B", "(c, e)", 1); next; }
		/*Activity_6A*/async(here) clocked(d, e) { m("6A", "(d, e)", 0); next; m("6A", "(d, e)", 1); next; }
		/*Activity_6B*/async(here) clocked(d, e) { m("6B", "(d, e)", 0); next; m("6B", "(d, e)", 1); next; }
		/*Activity_7A*/async(here) clocked(c, d, e) { m("7A", "(c, d, e)", 0); next; m("7A", "(c, d, e)", 1); next; }
		/*Activity_7B*/async(here) clocked(c, d, e) { m("7B", "(c, d, e)", 0); next; m("7B", "(c, d, e)", 1); next; }
		async(here) clocked(c, d, e) finish async(here) x10.io.Console.OUT.println("Parent activity in phase 0 of (c,d,e)");
		next;
		async(here) clocked(e, c, d) finish async(here) x10.io.Console.OUT.println("Parent activity in phase 1 of (c,d,e)");
		next;
		return true;
	}

	static def m(var a: String, var clocks: String, var phase: int): void = {
		x10.io.Console.OUT.println("Actitivity "+a+" in phase "+phase+" of clocks "+clocks);
	}

	public static def main(var args: Array[String](1)): void = {
		new ClockTest5().executeAsync();
	}
}
