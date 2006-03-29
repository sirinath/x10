package com.ibm.domo.ast.x10.visit;

import com.ibm.capa.ast.*;
import com.ibm.capa.ast.visit.*;
import com.ibm.domo.ast.x10.translator.*;

/**
 * @author Igor Peshansky
 * Extend DelegatingCAstVisitor to accommodate X10 CAst nodes and entities.
 * Doesn't work for visit/leaveEntity, visit/leaveNode, visit/leaveAssign...
 * TODO: document me.
 */
public abstract class X10CAstVisitor extends DelegatingCAstVisitor {

    /**
     * X10CAstVisitor constructor.
     * Needs to have a valid (non-null) delegate visitor.
     * @param delegate the visitor to delegate to for default implementation
     */
    protected X10CAstVisitor(CAstVisitor delegate) {
      super(delegate);
    }

    /**
     * X10CAstVisitor default constructor.
     */
    protected X10CAstVisitor() {
      this(new JavaCAstVisitor());
    }

    /**
     * A no-op Java CAst visitor.
     */
    private static final class JavaCAstVisitor extends CAstVisitor { }

    /**
     * Entity processing hook; sub-classes are expected to override if they
     * introduce new entity types.
     * Should invoke super.doVisitEntity() for unprocessed entities.
     * @return true if entity was handled
     */
    protected boolean doVisitEntity(CAstEntity n, Context context, CAstVisitor visitor_) {
	X10CAstVisitor visitor = (X10CAstVisitor)visitor_;
	switch (n.getKind()) {
	    case X10CAstEntity.ASYNC_BODY: {
		Context codeContext = visitor.makeCodeContext(context, n);
		visitor.visitAsyncBodyEntity(n, context, codeContext, visitor);
		// visit the AST if any
		if (n.getAST() != null)
		    visitor.visit(n.getAST(), codeContext, visitor);
		// process any remaining scoped children
		visitor.visitScopedEntities(n, n.getScopedEntities(null), codeContext, visitor);
		visitor.leaveAsyncBodyEntity(n, context, codeContext, visitor);
		break;
	    }
	    default:
		return super.doVisitEntity(n, context, visitor);
	}
	return true;
    }

    /**
     * Visit an AsyncBody entity.
     * @param n the entity to process
     * @param context a visitor-specific context
     * @param codeContext a visitor-specific context for this script
     * @return true if no further processing is needed
     */
    protected boolean visitAsyncBodyEntity(CAstEntity n, Context context, Context codeContext, CAstVisitor visitor) { return visitor.visitEntity(n, context, visitor); }
    /**
     * Leave an AsyncBody entity.
     * @param n the entity to process
     * @param context a visitor-specific context
     * @param codeContext a visitor-specific context for this script
     */
    protected void leaveAsyncBodyEntity(CAstEntity n, Context context, Context codeContext, CAstVisitor visitor) { visitor.leaveEntity(n, context, visitor); }

    /**
     * Node processing hook; sub-classes are expected to override if they
     * introduce new node types.
     * Should invoke super.doVisit() for unprocessed nodes.
     * @return true if node was handled
     */
    protected boolean doVisit(CAstNode n, Context context, CAstVisitor visitor_) {
	X10CAstVisitor visitor = (X10CAstVisitor)visitor_;
	switch (n.getKind()) {
	    case X10CastNode.ASYNC_INVOKE: {
		if (visitor.visitAsyncInvoke(n, context, visitor))
		    break;
		visitor.leaveAsyncInvoke(n, context, visitor);
		break;
	    }
	    case X10CastNode.ATOMIC_ENTER: {
		if (visitor.visitAtomicEnter(n, context, visitor))
		    break;
		visitor.leaveAtomicEnter(n, context, visitor);
		break;
	    }
	    case X10CastNode.ATOMIC_EXIT: {
		if (visitor.visitAtomicExit(n, context, visitor))
		    break;
		visitor.leaveAtomicExit(n, context, visitor);
		break;
	    }
	    case X10CastNode.FINISH_ENTER: {
		if (visitor.visitFinishEnter(n, context, visitor))
		    break;
		visitor.leaveFinishEnter(n, context, visitor);
		break;
	    }
	    case X10CastNode.FINISH_EXIT: {
		if (visitor.visitFinishExit(n, context, visitor))
		    break;
		visitor.leaveFinishExit(n, context, visitor);
		break;
	    }
	    case X10CastNode.FORCE: {
		if (visitor.visitForce(n, context, visitor))
		    break;
		visitor.visit(n.getChild(0), context, visitor);
		visitor.leaveForce(n, context, visitor);
		break;
	    }
	    case X10CastNode.REGION_ITER_INIT: {
		if (visitor.visitRegionIterInit(n, context, visitor))
		    break;
		visitor.visit(n.getChild(0), context, visitor);
		visitor.leaveRegionIterInit(n, context, visitor);
		break;
	    }
	    case X10CastNode.REGION_ITER_HASNEXT: {
		if (visitor.visitRegionIterHasNext(n, context, visitor))
		    break;
		visitor.visit(n.getChild(0), context, visitor);
		visitor.leaveRegionIterHasNext(n, context, visitor);
		break;
	    }
	    case X10CastNode.REGION_ITER_NEXT: {
		if (visitor.visitRegionIterNext(n, context, visitor))
		    break;
		visitor.visit(n.getChild(0), context, visitor);
		visitor.leaveRegionIterNext(n, context, visitor);
		break;
	    }
	    case X10CastNode.HERE: {
		if (visitor.visitHere(n, context, visitor))
		    break;
		visitor.leaveHere(n, context, visitor);
		break;
	    }
	    default:
		return super.doVisit(n, context, visitor);
	}
	return true;
    }

    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitAsyncInvoke(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveAsyncInvoke(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitAtomicEnter(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveAtomicEnter(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitAtomicExit(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveAtomicExit(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitFinishEnter(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveFinishEnter(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitFinishExit(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveFinishExit(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitForce(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveForce(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitRegionIterInit(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveRegionIterInit(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitRegionIterHasNext(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveRegionIterHasNext(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitRegionIterNext(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveRegionIterNext(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
    /**
     * Visit an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     * @return true if no further processing is needed
     */
    protected boolean visitHere(CAstNode n, Context c, CAstVisitor visitor) { return visitor.visitNode(n, c, visitor); }
    /**
     * Leave an Async node.
     * @param n the node to process
     * @param c a visitor-specific context
     */
    protected void leaveHere(CAstNode n, Context c, CAstVisitor visitor) { visitor.leaveNode(n, c, visitor); }
}

