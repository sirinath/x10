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
 * Testing that an at spawned at some other place cannot access a remote field.
 */
public class AtFieldWrite_MustFailCompile   {
	var t: T;
public def run() {
	val Second = Place.FIRST_PLACE.next();
	val newT = new T();
	at (Second) { 
		this.t = newT; 
	}
return true;
}

public static def main(Rail[String]) {
	new AtFieldWrite_MustFailCompile().run ();
}

static class T {
	public var i: int;
}
}
