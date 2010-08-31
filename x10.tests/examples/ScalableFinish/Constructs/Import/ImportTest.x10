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
 * Minimal package and import test
 * @author kemal
 * 1/2005
 */
public class ImportTest   {

	public def run(): boolean = {
		return (future(here) { _T2.m2(49) }).force();
	}

	public static def main(var args: Rail[String]): void = {
		new ImportTest().run ();
	}
}
