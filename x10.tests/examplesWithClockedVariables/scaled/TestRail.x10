import x10.util.Random;
import x10.io.Console;
import clocked.Clocked;

public class TestRail {	

    public static def main(args:Rail[String]!) {
    val c = Clock.make();
    val op = Float.+;
    val b = Rail.make[float @ Clocked[float](c,op)](5);
   //val b: Rail[float @ Clocked [float] (c,op)]! = Rail.make[float](5); /* Works */
    async clocked(c) b(0) = 1.0F;
    next;
    Console.OUT.println(b(0));
    }
}
