/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import x10.util.Box;
import harness.x10Test;

/**
 * Checks if creation and force of Future in different activities work.
 *
 * @author Christoph von Praun, 5/2005
 * @author kemal, 5/2005
 */
public class FutureTest5 extends x10Test {

	var fut: Box[Future[Int]];

	/**
	 * Create a future in one activity, and then
	 * force it in a different activity.
	 */
	public def run()=
			testUp_(false) &&
			testUp_(true) &&
			testDown_() &&
			testSibling_(false) &&
			testSibling_(true);
	

	/**
	 * Create future in child, force it in parent.
	 */
	private def testUp_(val del: boolean): boolean = {
		atomic fut = null;
		async (here) {
			val t1 = future (here) { 42 } ;
			atomic fut = t1 as Box[Future[Int]];
			if (del)
				Activity.sleep(500);
		};
		var t2: Future[Int];
		when (fut != null) { t2 = fut(); }
		var fortytwo: int = t2.force();
		x10.io.Console.OUT.println("up done");
		return fortytwo == 42;
	}

	/**
	 * Create future in parent, force it in child.
	 */
	private def testDown_(): boolean = {
		val fut_l = future (here) { 42 } ;
		finish async (here) {
			var fortytwo: int = fut_l.force();
			x10.io.Console.OUT.println("down done");
		};
		return true;
	}

	/**
	 * Create future in child 1, force it in child 2.
	 */
	private def testSibling_(val del: boolean): boolean = {
		atomic fut = null;
		async (here) {
			val t1= future (here) { 42 } ;
			atomic fut = t1 as Box[Future[Int]];
			if (del)
				Activity.sleep(500);
		}
		finish async (here) {
			var t2: Future[Int];
			when (fut != null) { t2 = fut(); }
			var fortytwo: int = t2.force();
			x10.io.Console.OUT.println("sibling done");
		};
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new FutureTest5().execute();
	}
}
