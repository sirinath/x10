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
 * Inference for local variables.
 *
 * @author nystrom 8/2008
 */
public class Inference1   {
	public def run(): boolean = {
                val x = 1;
                val y: int = x;
		return x == y;
	}

	public static def main(var args: Rail[String]): void = {
		new Inference1().run ();
	}
}

