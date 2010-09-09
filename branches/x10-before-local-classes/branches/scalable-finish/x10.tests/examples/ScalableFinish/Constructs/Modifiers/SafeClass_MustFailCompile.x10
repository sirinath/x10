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
 * Check that the safe annotation is recognized on classes, 
 * and automatically added to methods. Therefore methods of
 * such classes can be called from inside atomic.
 * Methods of classes not annotated safe, that are not themselves
 * annotated safe should give a compile-time error if called from
 * within an atomic.
 * @author vj  9/2006
 */
public safe class SafeClass_MustFailCompile   {
        class Foo {
            // n cannot be called from within an atomic
            def n(): void = { }
        }

        public def m(): void = { }

	public def run(): boolean = {
		atomic { m(); }
		var foo: Foo = new Foo();
		atomic { foo.n();} // compiler must throw an error.
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new SafeClass_MustFailCompile().run ();
	}

	
}
