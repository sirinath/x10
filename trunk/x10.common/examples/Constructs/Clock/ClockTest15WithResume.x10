/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Testing behavior of multiple clock barriers with resume
 *
 * This is the same as ClockTest15, but with explicit resume's
 * just before each next.
 *
 * With multiple clocks per activity, next's are not guaranteed
 * to be in lock step.
 *
 * Given activities A1,A2,A3, which are registered with
 * clocks a,b like this:
 *
 * <code>

    a   b
   / \ / \
  A1  A2 A3

 * </code>
 *
 * A1, A2, and A3 all increment a global counter x (initially 0), then
 * pass a next, and then read the global counter x.
 * While typically all will read x == 3, it is possible for
 * A1 to pass its next earlier than A2 and A3 and read x == 2
 * (before A3 has incremented x).
 * Similarly A3 can pass its next earlier than A1,A2 and read x == 2
 * (before A1 has incremented x).
 *
 * Thus the following parallel execution order is legitimate
 * I.e. A1 reads x == 2 by passing its next prematurely,
 * but A2 and A3 read x == 3
 *
 *
 * <code>

  A0: spawns A1 (registers A1 with a first);
  A0: spawns A2 (registers A2 with a,b first)
  A0: spawns A3 (registers A3 with b first)
  A0: terminates, no longer registered with a or b
  A3: start delay operation (wait for N milliseconds)  &lt;= [IP] Is this ok?
  A1: x++ (x == 1 now)
  A1: a.resume() // part of next
  A1: next (wait until A1,A2 have both resumed a)
  A2: x++ (x == 2 now)
  A2: a.resume(); // part of next
  A2: b.resume(); // part of next
  A2: next (wait until A2,A3 have both resumed b and A1,A2 have both resumed a)
  A1: next unblocks; // (since A2 resumed a)
  A1: read x (x == 2)  &lt;===== A1 passes next earlier than A2 and A3
  A3: return from delay operation
  A3: x++ (x == 3 now)
  A3: b.resume() // part of next
  A3: next (wait until A2,A3 have both resumed b)
  A3: next unblocks immediately
  A3: read x (x == 3)
  A2: next unblocks
  A2: read x (x == 3)

 * </code>
 *
 * This test case forces A1 to read x == 2 deterministically,
 * by delaying the execution of A3.
 *
 * @author kemal 4/2005
 */
public class ClockTest15WithResume extends x10Test {

	int x = 0; // global counter
	boolean advanced_A1 = false; // signals that A1 executed next and read x == 2
	public boolean run () {
		finish /* A0 */ async {
			final clock a = clock.factory.clock();
			final clock b = clock.factory.clock();
			/* A1 */ async (here) clocked (a) {
				atomic x++;
				next;
				int tmp;
				atomic tmp = x;
				System.out.println("A1 advanced, x = "+tmp);
				atomic advanced_A1 = true;
				chk (tmp == 2);
				next;
			}
			/* A2 */ async (here) clocked (a, b) {
				atomic x++;
				next;
				int tmp;
				atomic tmp = x;
				System.out.println("A2 advanced, x = "+tmp);
				chk (tmp == 3);
				next;
			}
			/* A3 */ async (here) clocked (b) {
				await(advanced_A1);
				atomic x++;
				next;
				int tmp;
				atomic tmp = x;
				System.out.println("A3 advanced, x = "+tmp);
				chk (tmp == 3);
				next;
			}
		} /* end A0 */
		return true;
	}

	public static void main(String[] args) {
		new ClockTest15WithResume().execute();
	}
}

