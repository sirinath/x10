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

/**
 * Construct a diamond-shaped region from halfspaces, make an array
 * out of it, and examine it.
 */

class PolyHalfspace1 extends TestRegion {

    public def run() {

        val ROW = X(0);
        val COL = X(1);

        val r =  
            reg(2, COL+ROW, LE, 7) &&
            reg(2, COL+ROW, GE, 3) &&
            reg(2, COL-ROW, LE, 1) &&
            reg(2, COL-ROW, GE, -1);
        prArray("diamond", r);

        return status();
    }

    def expected() =
        "--- PolyHalfspace1: diamond\n"+
        "rank 2\n"+
        "rect false\n"+
        "zeroBased false\n"+
        "rail false\n"+
        "isConvex() true\n"+
        "size() 8\n"+
        "region: (x0+x1>=3 && x0-x1>=-1 && x0-x1<=1 && x0+x1<=7)\n"+
        "  poly\n"+
        "    1  . . 2 . . . . . . . \n"+
        "    2  . 2 4 6 . . . . . . \n"+
        "    3  . . 6 9 2 . . . . . \n"+
        "    4  . . . 2 . . . . . . \n"+
        "  iterator\n"+
        "    1  . . 2 . . . . . . . \n"+
        "    2  . 2 4 6 . . . . . . \n"+
        "    3  . . 6 9 2 . . . . . \n"+
        "    4  . . . 2 . . . . . . \n";

    public static def main(Array[String](1)) {
        new PolyHalfspace1().execute();
    }
    
}

