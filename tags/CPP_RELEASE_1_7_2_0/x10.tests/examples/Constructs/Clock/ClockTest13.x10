/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Out of phase clocks due to resume:
 *
 * Given clocks a,b,c, and activities A,B,C,D registered with these
 * clocks as follows:
 *
 * <code>
   a   b   c   clocks
  / \ / \ / \
  A  B   C   D activities
 * </code>
 *
 * This test first forces D to move 3 clock periods ahead of A and
 * then forces A to move 3 clock periods ahead of D.
 *
 * Example script of parallel activities:
 * <code>
  a, b, etc. = a.resume(), b.resume()
  -- n ( n'th next un-blocked here)

  A  B  C  D
  a  a
  b  b
  c  c
  ---------    1
  a              A waits for D's period 3
  b  b
  c  c
  ------    2

  b
  c  c
  ---    3

  c        D ack period 3 to A's period 0
  -               1
  a
  ----            2
  a  a
  b
  -------         3
  a  a
  b  b
  c
  ------------    4
  c  c
  b  b
  a  a
  -------         5
  c           D waits for A's period 7
  b  b
  a  a
  ----            6

  b
  a  a
  -               7

  a                 A ack period 7 to D's period 4
 * </code>
 *
 * Desired behavior: should not deadlock
 *
 * @author kemal 4/2005
 */
public class ClockTest13 extends x10Test {

	public const N: int = 20; // total clock periods per activity
	public const M: int = N/2; // when to change from forward to reverse
	public const chainLength: int = 3;
	var phaseA: int = 0;
	var phaseB: int = 0;
	var phaseC: int = 0;
	var phaseD: int = 0;

	public def run(): boolean = {
		finish async {
			val a = Clock.make();
			val b = Clock.make();
			val c = Clock.make();
			async clocked(a) taskA(a);
			async clocked(a, b) taskB(a, b);
			async clocked(b, c) taskC(b, c);
			async clocked(c) taskD(c);
		}
		return true;
	}

	def taskA(val a: Clock): void = {
		for (val (k): Point in 1..N) {
			System.out.println(k+" A new phase");
			atomic phaseA++;
			System.out.println(k+" A resuming a");
			a.resume();
			if (k <= M-chainLength) {
				System.out.println(k+" Waiting for forward phase shift");
				when (phaseB == phaseA+1 &&
						phaseC == phaseB+1 &&
						phaseD == phaseC+1)
				{
					System.out.println(k+" Max forward phase shift reached");
				}
			}
			next;
		}
	}
	def taskB(val a: Clock, val b: Clock): void = {
		for (val (k): Point in 1..N) {
			System.out.println(k+" B new phase");
			atomic phaseB++;
			System.out.println(k+" B resuming a");
			a.resume();
			System.out.println(k+" B resuming b");
			b.resume();
			System.out.println(k+" B before next");
			next;
		}
	}
	def taskC(val b: Clock, val c: Clock): void = {
		for (val (k): Point in 1..N) {
			System.out.println(k+" C new phase");
			atomic phaseC++;
			System.out.println(k+" C resuming b");
			b.resume();
			System.out.println(k+" C resuming c");
			c.resume();
			System.out.println(k+" C before next");
			next;
		}
	}
	def taskD(val c: Clock): void = {
		for (val (k): Point in 1..N) {
			System.out.println(k+" D new phase");
			atomic phaseD++;
			System.out.println(k+" D resuming c");
			c.resume();
			if (k >= M && k <= N-chainLength) {
				System.out.println(k+" Waiting for reverse phase shift");
				when (phaseC == phaseD+1 &&
						phaseB == phaseC+1 &&
						phaseA == phaseB+1)
				{
					System.out.println(k+" Max reverse phase shift reached");
				}
			}
			System.out.println(k+" D before next");
			next;
		}
	}

	public static def main(var args: Rail[String]): void = {
		new ClockTest13().execute();
	}
}
