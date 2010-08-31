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

 
import x10.io.Console;
public class PlaceCast   {
    var nplaces: int = 0;

    public def run()  {
	  val d: Dist = Dist.makeUnique();
	  Console.OUT.println("num places = " + Place.MAX_PLACES);
	  val disagree = DistArray.make[BoxedBoolean](d, (Point)=> new BoxedBoolean());
	  finish ateach (p in d) {
	      // remember if here and d[p] disagree
	      // at any activity at any place
	      try {
		    val q  = d(p).next();
		    // Always throws ClassCastException
		    val x = disagree(p) as (BoxedBoolean!q);
		    at (this) atomic nplaces++; 
	      } catch (x: ClassCastException) {
	    	Console.OUT.println("Caught class cast exception for " + p);
	      }
	  }
	  Console.OUT.println("nplaces == " + nplaces);
	  return nplaces == 0;
    }

    public static def main(Rail[String]) {
	  new PlaceCast().run ();
    }

	static class BoxedBoolean {
	   var v: boolean = false;
	}
}
