/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * A test case that illustrates that deadlock is possible
 * with futures, by creating a circular wait-for dependency.
 *
 * A0 spawns future activities A1 and A2 and saves the "future"
 * handles from A1 and A2 in global future variables f1 and f2,
 * respectively.
 *
 * A1 sleeps a sufficient time to ensure A0 is done setting f2, then
 * reads global variable f2 and waits for the corresponding activity
 * (A2) to finish.
 *
 * A2 also sleeps a sufficient time to ensure A0 is done setting f1,
 * and then reads global variable f1 and waits for the corresponding
 * activity (A1) to finish.
 *
 * In the meantime A0 starts waiting for A1 to finish.
 *
 * Expected result: must deadlock.
 *
 * NB: This test case uses a sleep timer to
 * choreograph events on second granularily.
 *
 * @author kemal 4/2005
 */
public class FutureDeadlock_MustFailTimeout extends x10Test {
	nullable<future<int>> f1 = null;
	nullable<future<int>> f2 = null;

	int a1() {
		x10.lang.Runtime.sleep(5000); // to make deadlock occur deterministically
		nullable<future<int>> tmpf = null;
		atomic tmpf = f2;
		System.out.println("Activity #1 about to force "+tmpf+" to wait for #2 to complete");
		return ((future<int>)tmpf).force();
	}

	int a2() {
		x10.lang.Runtime.sleep(5000); // to make deadlock occur deterministically
		nullable<future<int>> tmpf = null;
		atomic tmpf = f1;
		System.out.println("Activity #2 about to force "+tmpf+" to wait for #1 to complete");
		return ((future<int>)tmpf).force();
	}

	public boolean run() {
		future<int> tmpf1 = future(here) { a1() };
		atomic f1 = tmpf1;
		future<int> tmpf2 = future(here) { a2() };
		atomic f2 = tmpf2;
		System.out.println("Activity #0 spawned both activities #1 and #2, waiting for completion of #1");
		return tmpf1.force() == 42;
	}

	public static void main(String[] args) {
		new FutureDeadlock_MustFailTimeout().execute();
	}
}

