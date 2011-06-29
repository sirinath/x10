/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2010.
 */
package x10.optimizations.inlining;


import polyglot.ast.Assign_c;
import polyglot.ast.Call_c;
import polyglot.ast.ConstructorCall;
import polyglot.ast.ConstructorCall_c;
import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Lit_c;
import polyglot.ast.Local_c;
import polyglot.ast.Node;
import polyglot.ast.Node_c;
import polyglot.ast.ProcedureDecl;
import polyglot.ast.Special;
import polyglot.frontend.Job;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.visit.NodeVisitor;
import x10.ast.AssignPropertyCall_c;
import x10.ast.Closure;
import x10.ast.ClosureCall_c;
import x10.ast.StmtExpr_c;
import x10.visit.ExpressionFlattener;
import x10.visit.X10DelegatingVisitor;

/**
 * @author Bowen Alpern
 *
 */
class DeclPackage extends NodeVisitor {
    
    static final boolean XTENLANG_2818 = true; // FIXME: Java back-end does not support non-virtual instance calls
    static final boolean XTENLANG_2819 = true; // FIXME: C++  back-end generates incorrect code for embedded fields

    boolean inlinable;
    String reason;
    final private Job job;
    final private ProcedureDecl decl;
    final private InlineUtils utils;
    final private CostDelegate delegate;
    final protected int cost[] = new int[1];

    DeclPackage(String r) {
        inlinable = false;
        reason    = r;
        job       = null;
        decl      = null;
        utils     = null;
        delegate  = null;
        
    }

    DeclPackage(Job j, ProcedureDecl pd) {
        inlinable = true;
        job       = j;
        decl      = pd;
        utils     = new InlineUtils(job);
        delegate  = new CostDelegate(this);
    }

    public ProcedureDecl getDecl(int budget) {
        if (inlinable && cost[0] <= budget)
            return decl;
        return null;
    }

    /**
     * @param r
     */
    private void cannotInline(String r) {
        inlinable = false;
        reason    = r;
    }

    /* (non-Javadoc)
     * @see polyglot.visit.NodeVisitor#override(polyglot.ast.Node)
     */
    @Override
    public Node override(Node n) {
        if (!inlinable) 
            return n; // don't visit
        return null;  // visit
    }

    /* (non-Javadoc)
     * @see polyglot.visit.NodeVisitor#leave(polyglot.ast.Node, polyglot.ast.Node, polyglot.visit.NodeVisitor)
     */
    @Override
    public Node leave(Node old, Node n, NodeVisitor v) {
        if (XTENLANG_2818 && n instanceof Special && ((Special) n).kind() == Special.SUPER && ExpressionFlattener.javaBackend(job)) {
            cannotInline("Java back-end cannot handle inlined super targets");
        } else if (XTENLANG_2818 && n instanceof ConstructorCall && ((ConstructorCall) n).kind() == ConstructorCall.SUPER && ExpressionFlattener.javaBackend(job)) {
            cannotInline("Java back-end cannot handle inlined super calls either");
        } else if (XTENLANG_2819 && utils.hasEmbedAnnotation(n) && !ExpressionFlattener.javaBackend(job)) {
            cannotInline("C++  back-end cannot handle embedded fields");
        } else if (utils.isNativeCode(n)) {
            cannotInline("Procedure body contains native code");
        } else {
            delegate.visitAppropriate(n);
        }
        return n;
    }

}

final class CostDelegate extends X10DelegatingVisitor{
    static final int CALL_COST        = 0x10;
    static final int CONSTRUCTOR_COST = 8;
    static final int OPERATION_COST   = 2;
    static final int SMALL_COST       = 1;
    static final int NO_COST          = 0;

    final DeclPackage pkg;

    CostDelegate(DeclPackage dp) {
        pkg = dp;
    }

    /**
     * Property calls are not charged.
     *
     * @see x10.visit.X10DelegatingVisitor#visit(x10.ast.AssignPropertyCall_c)
     */
    public final void visit(AssignPropertyCall_c c) {
        pkg.cost[0] += NO_COST;
    }

    /**
     * Closure calls are free, if the target is a closure literal.
     * 
     * @see x10.visit.X10DelegatingVisitor#visit(x10.ast.ClosureCall_c)
     */
    @Override
    public void visit(ClosureCall_c n) {
        if (!(n.target() instanceof Closure))
            pkg.cost[0] += CALL_COST;
    }

    /**
     * Constructor calls are charged 8.
     * 
     * @see x10.visit.X10DelegatingVisitor#visit(polyglot.ast.ConstructorCall_c)
     */
    public final void visit(ConstructorCall_c c) { 
        pkg.cost[0] += CONSTRUCTOR_COST;
    }

    /**
     * Method calls are charged 16. 
     * Intrinsic calls are charged 2.
     * 
     * @see x10.visit.X10DelegatingVisitor#visit(polyglot.ast.Call_c)
     */
    public final void visit(Call_c c) {
        if (c.target() instanceof Expr) { // TODO @intrinsic
            Type type = Types.baseType(((Expr) c.target()).type());
            TypeSystem ts = type.typeSystem();
            if (ts.isLongOrLess(type) || ts.isChar(type) || ts.isBoolean(type)) {
                pkg.cost[0] += OPERATION_COST;
                return;
            }
        }
        pkg.cost[0] += CALL_COST;
    }

    /**
     * Literals are not charged.
     * 
     * @see x10.visit.X10DelegatingVisitor#visit(polyglot.ast.Lit_c)
     */
    public final void visit(Lit_c l) {
        pkg.cost[0] += NO_COST;
    }

    /**
     * Locals are not charged.
     */
    public final void visit(Local_c l) {
        pkg.cost[0] += NO_COST;
    }

    /**
     * Assignments are not charged.
     * 
     * @see x10.visit.X10DelegatingVisitor#visit(polyglot.ast.Assign_c)
     */
    @Override
    public void visit(Assign_c n) {
        pkg.cost[0] += NO_COST;
    }

    /**
     * Statement Expressions are not charged.
     *
     * @see x10.visit.X10DelegatingVisitor#visit(x10.ast.StmtExpr_c)
     */
    @Override
    public void visit(StmtExpr_c n) {
        pkg.cost[0] += NO_COST;
    }

    /**
     * Other expressions are charged 2.
     *
     * @see x10.visit.X10DelegatingVisitor#visit(polyglot.ast.Expr_c)
     */
    public final void visit(Expr_c e) {
        pkg.cost[0] += OPERATION_COST;
    }

    /**
     * Other nodes are not charged.
     *
     * @see x10.visit.X10DelegatingVisitor#visit(polyglot.ast.Node_c)
     */
    public final void visit(Node_c n) {
        pkg.cost[0] += NO_COST;
    }

}
