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

import polyglot.util.Position;
import x10.types.ClosureDef;

/**
 * A Closure AST node represents a closure literal in the source text. Its type is a Closuredef.
 * 
 * @author vj
 *
 */
public interface Closure extends Expr, CodeBlock, Guarded {
	    
   // List<TypeParamNode> typeParameters();
   // Closure typeParameters(List<TypeParamNode> typeParams);

    /** The closure's formal parameters.
     * @return A list of {@link x10.ast.Formal Formal}
     */
    List<Formal> formals();

    /**
     * @return the closure's return type
     */
    TypeNode returnType();
    
    DepParameterExpr guard();
    Closure guard(DepParameterExpr guard);

    ClosureDef closureDef();
    Closure closureDef(ClosureDef ci);
    
    Closure position(Position p);
}
