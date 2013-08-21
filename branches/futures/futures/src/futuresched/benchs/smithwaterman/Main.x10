package futuresched.benchs.smithwaterman;

import futuresched.core.*;

public class Main {

	public static def test(i: Int, j: Int) {

      Console.OUT.println("---------------------------------------");
//      t1 = System.currentTimeMillis();
//      v = SmithWatermanRec.m(i, j);
//      t2 = System.currentTimeMillis();
//      Console.OUT.println("Rec: " + v);
//      Console.OUT.println("Time = " + (t2 - t1));
//      Console.OUT.println("");

      var t1: Long;
      var t2: Long;
      var v: Int;

      Console.OUT.println("SmithWaterman");
      Console.OUT.println("M(" + i + ", " + j + ")");
      Console.OUT.println("");

      t1 = System.currentTimeMillis();
      v = SmithWatermanDyn.m(i, j);
      t2 = System.currentTimeMillis();
      Console.OUT.println("Dyn: " + v);
      Console.OUT.println("Time = " + (t2 - t1));
      Console.OUT.println("");

      t1 = System.currentTimeMillis();
      v = SmithWatermanFut.m(i, j);
      t2 = System.currentTimeMillis();
      Console.OUT.println("Future: " + v);
      Console.OUT.println("Time = " + (t2 - t1));
      Console.OUT.println("");

      t1 = System.currentTimeMillis();
	   v = SmithWatermanSFut.m(i, j);
	   t2 = System.currentTimeMillis();
	   Console.OUT.println("SFuture: " + v);
      Console.OUT.println("Time = " + (t2 - t1));

      Console.OUT.println("---------------------------------------");

	}


	public static def main(Rail[String]) {
	   FTask.init();

//        test(4, 4);
//        test(7, 7);
//        test(9, 9);
        test(20, 20);
        test(100, 100);
   }
}

