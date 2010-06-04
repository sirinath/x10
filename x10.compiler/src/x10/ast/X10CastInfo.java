/*
 *
 * (C) Copyright IBM Corporation 2006-2008
 *
 *  This file is part of X10 Language.
 *
 */
package x10.ast;

import polyglot.ast.Expr;
import polyglot.ast.TypeNode;
import polyglot.types.Type;

public interface X10CastInfo {
    public Type type();
    public TypeNode getTypeNode();
    public Expr expr();
}
