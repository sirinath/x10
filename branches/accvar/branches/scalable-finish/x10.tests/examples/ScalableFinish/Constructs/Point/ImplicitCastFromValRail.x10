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
 * Test that there is an implicit conversion from ValRail[Int] to Point.
 *
 * @author vj
 */

public class ImplicitCastFromValRail   {

	def m(p:Point(2))=p;
    public def run(): boolean = {

        val p =[1,2];
       
        return m(p).equals(Point.make(p));
    }

    public static def main(Rail[String]) {
        new  ImplicitCastFromValRail().run ();
    }
}
