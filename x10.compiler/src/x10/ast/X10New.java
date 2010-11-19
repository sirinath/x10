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

package x10.ast;

import java.util.List;

import polyglot.ast.New;
import polyglot.ast.TypeNode;
import x10.types.X10ClassDef;

public interface X10New extends New, X10ProcedureCall {
	List<TypeNode> typeArguments();

	X10New typeArguments(List<TypeNode> args);

	boolean newOmitted();

	X10New newOmitted(boolean val);

	X10ClassDef anonType();
}
