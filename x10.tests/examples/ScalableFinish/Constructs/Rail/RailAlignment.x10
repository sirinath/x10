import x10.io.Console;
import x10.util.Pair;
 

import x10.compiler.Native;

public class RailAlignment   {

    // well-defined only if alignment is a power of 2
    @Native("c++", "(((size_t)((#4)->raw()) & (#5 -1))==0)")
    public static def alignedTo[T](o:Rail[T], alignment:Int) : Boolean = true;

    // well-defined only if alignment is a power of 2
    @Native("c++", "(((size_t)((#4)->raw()) & (#5 -1))==0)")
    public static def alignedTo[T](o:ValRail[T], alignment:Int) : Boolean = true;

    public def run () : Boolean {
        var failures:Int = 0;
        for ((i) in 0..999) {
            {
                val r = Rail.makeAligned[float](555, (i:Int)=>0 as Float,16);
                if (!alignedTo(r, 16)) {
                    failures++;
                }
            }
            {
                val r = Rail.makeAligned[float](555, (i:Int)=>0 as Float,512);
                if (!alignedTo(r, 512)) {
                    failures++;
                }
            }
            {
                val r = ValRail.makeAligned[float](555, (i:Int)=>0 as Float,16);
                if (!alignedTo(r, 16)) {
                    failures++;
                }
            }
            {
                val r = ValRail.makeAligned[float](555, (i:Int)=>0 as Float,512);
                if (!alignedTo(r, 512)) {
                    failures++;
                }
            }
        }
        Console.ERR.println("Number of failures: "+failures);
        return failures == 0;
    }

    public static def main(Rail[String])  {
        new RailAlignment().run ();
    }

}



// vim: shiftwidth=4:tabstop=4:expandtab

