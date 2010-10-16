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

import harness.x10Test;

/**
 * Simple test for operator assignment of array elements.
 */

public class ArrayOpAssign extends x10Test {

    public def run(): boolean = {
        var result: boolean = true;
        val R = (1..10)*(1..10);
        var ia: Array[int](2) = new Array[int](R, (Point)=>0);
        ia(1, 1) = 1;
        ia(1, 1) += ia(1, 1);
        result &= (2 == ia(1, 1));
        x10.io.Console.OUT.println("ia[1,1])" + ia(1, 1));
        ia(1, 1) *= 2;
        x10.io.Console.OUT.println("ia[1,1])" + ia(1, 1));
        result &= (4 == ia(1, 1));
        var id: Array[double](2) = new Array[double](R, (Point)=>0D);
        id(1, 1) += 42;
        result &= (42 == id(1, 1));
        x10.io.Console.OUT.println("id[1,1])" + id(1, 1));
        id(1, 1) *= 2;
        x10.io.Console.OUT.println("id[1,1])" + id(1, 1));
        result &= (84 == id(1, 1));
        return result;
    }

    public static def main(var args: Array[String](1)): void = {
        new ArrayOpAssign().execute();
    }
}
