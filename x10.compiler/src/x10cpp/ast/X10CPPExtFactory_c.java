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

package x10cpp.ast;

import x10.ast.Ext;
import x10.ast.X10ExtFactory_c;
import x10cpp.extension.X10ClassBodyExt_c;

public class X10CPPExtFactory_c extends X10ExtFactory_c {
	protected Ext extClassBodyImpl() {
		return new X10ClassBodyExt_c();
	}
}
