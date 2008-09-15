/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package polyglot.ext.x10.ast;

import java.util.List;

import polyglot.ast.TypeNode;

public interface X10ConstructorCall {
	List<TypeNode> typeArguments();

	X10ConstructorCall typeArguments(List<TypeNode> args);


}
