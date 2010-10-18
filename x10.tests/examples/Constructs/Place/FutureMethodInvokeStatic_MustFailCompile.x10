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
 * @author bdlucas
 */

public class FutureMethodInvokeStatic_MustFailCompile extends x10Test {

	static class C[S](home:Place) {
        property p:int = 0;
        val x:S;
        var y:S;
        def foo(){here == this.home} {}
        def foo(x:S) {}
        final def foo[T](x:T) {}
        def this(s:S) {
        	property(here);
            x = s;
            y = s;
        }
    }

    val c = new C[String]("0");

    public def run02(): boolean = {
    		val p = Place.place(1);
    		val cc = this.c;
            val f = future (p) {
            	// cannot access a method with a local guard
                val a = cc.foo();
            return true;
        };
        return f.force();
    }

    public def run(): boolean {
    	if (Place.MAX_PLACES == 1) {
    		x10.io.Console.OUT.println("not enough places to run this test");
    		return false;
    	}
    	return run02();
	}

    public static def main(Array[String](1)) {
        new FutureMethodInvokeStatic_MustFailCompile().execute();
    }
}
