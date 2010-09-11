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
 * The supertype cannot be a proto type. This is actually going to be a parser error.
 * @author vj
 */
public class ProtoSuper_MustFailCompile extends x10Test {

	class A{}
	class B extends proto A{
		var x:B;
	}
    public def run()=true;

    public static def main(Array[String](1))  {
	new ProtoSuper_MustFailCompile().execute();
    }
}
