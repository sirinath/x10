/*
 * Created on Oct 6, 2005
 */
package com.ibm.wala.cast.x10.translator.polyglot;

import polyglot.ext.x10.ast.*;

import com.ibm.wala.cast.tree.CAstNode;
import com.ibm.wala.cast.java.translator.polyglot.TranslatingVisitor;
import com.ibm.wala.cast.java.translator.polyglot.PolyglotJava2CAstTranslator.*;

public interface X10TranslatorVisitor extends TranslatingVisitor {
    // PORT1.7 ArrayConstructor in 1.5 was "new T[] { initialize-code }"; now this is represented as an array with a closure for an initializer(?)
//    CAstNode visit(ArrayConstructor ac, WalkContext context);
    CAstNode visit(Async a, WalkContext context);
    CAstNode visit(AtEach a, WalkContext context);
    CAstNode visit(Atomic a, WalkContext context);
    CAstNode visit(Await a, WalkContext context);
    CAstNode visit(Clocked c, WalkContext context);
    CAstNode visit(Closure closure, WalkContext context);
    CAstNode visit(ClosureCall closureCall, WalkContext context);
    CAstNode visit(Finish f, WalkContext context);
    CAstNode visit(ForLoop f, WalkContext context);
    CAstNode visit(ForEach f, WalkContext context);
    CAstNode visit(Future f, WalkContext context);
    // PORT1.7 X10 1.7 now has proper parametric types
//    CAstNode visit(GenParameterExpr gpe, WalkContext context);
    CAstNode visit(Here h, WalkContext context);
    CAstNode visit(Next n, WalkContext context);
    CAstNode visit(ParExpr expr, WalkContext wc);
    CAstNode visit(PlaceCast pc, WalkContext context);
    CAstNode visit(Point p, WalkContext context);
    CAstNode visit(Region r, WalkContext context);
    CAstNode visit(Range r, WalkContext context);
    CAstNode visit(Tuple t, WalkContext context);
    CAstNode visit(When w, WalkContext context);
    // PORT1.7 - Array accesses are now represented by ordinary method calls
//    CAstNode visit(X10ArrayAccess aa, WalkContext context);
//    CAstNode visit(X10ArrayAccess1 aa, WalkContext context);
    CAstNode visit(X10Formal f, WalkContext context);
    CAstNode visit(SettableAssign n, WalkContext wc);
}
