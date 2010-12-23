/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright Australian National University 2010.
 */
import harness.x10Test;

/**
 * Tests performance of DistArray creation
 * @author milthorpe 09/2010
 */
public class BenchmarkCreateDistArray(elementsPerPlace : Int) extends x10Test {

    public def this(elementsPerPlace : Int) {
        property(elementsPerPlace);
    }

	public def run(): Boolean = {
        // create a dummy array distributed to every place, to make sure they're all ready
        val m = DistArray.make[Int](Dist.makeUnique());

        val arraySize = elementsPerPlace * Place.MAX_PLACES;

        val start = System.nanoTime();
        val a = DistArray.make[Int](Dist.makeBlock(0..(arraySize-1)));
        val stop = System.nanoTime();

        // do something with elements afterwards
        ateach (p in a) {
            a(p) = here.id;
        }

        Console.OUT.printf("create DistArray: %g ms\n", ((stop-start) as Double) / 1e6);
        return true;
	}

	public static def main(var args: Array[String](1)): void = {
        var elementsPerPlace : Int = 1;
        if (args.size() > 0) {
            elementsPerPlace = Int.parse(args(0));
        }
		new BenchmarkCreateDistArray(elementsPerPlace).execute();
	}

}
