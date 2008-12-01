/*
 *
 * (C) Copyright IBM Corporation 2006-2008
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.visit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import polyglot.ast.Block;
import polyglot.ast.Catch;
import polyglot.ast.Expr;
import polyglot.ast.Formal;
import polyglot.ast.LocalDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.StringLit;
import polyglot.ast.Stmt;
import polyglot.ast.Try;
import polyglot.ast.TypeNode;
import polyglot.ast.Unary;
import polyglot.ext.x10.ast.Async;
import polyglot.ext.x10.ast.AtEach;
import polyglot.ext.x10.ast.AtExpr;
import polyglot.ext.x10.ast.Atomic;
import polyglot.ext.x10.ast.AtStmt;
import polyglot.ext.x10.ast.Await;
import polyglot.ext.x10.ast.Closure;
import polyglot.ext.x10.ast.Closure_c;
import polyglot.ext.x10.ast.Finish;
import polyglot.ext.x10.ast.ForEach;
import polyglot.ext.x10.ast.Future;
import polyglot.ext.x10.ast.Here;
import polyglot.ext.x10.ast.Next;
import polyglot.ext.x10.ast.Tuple;
import polyglot.ext.x10.ast.When;
import polyglot.ext.x10.ast.X10Formal;
import polyglot.ext.x10.ast.X10NodeFactory;
import polyglot.ext.x10.types.ClosureDef;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.frontend.Job;
import polyglot.types.LocalDef;
import polyglot.types.MethodInstance;
import polyglot.types.Name;
import polyglot.types.QName;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;

/**
 * Visitor to desugar the AST before code gen.
 */
public class Desugarer extends ContextVisitor {
    private final X10TypeSystem xts;
    private final X10NodeFactory xnf;

    public Desugarer(Job job, TypeSystem ts, NodeFactory nf) {
        super(job, ts, nf);
        xts = (X10TypeSystem) ts;
        xnf = (X10NodeFactory) nf;
    }

    private static int count;

    private static Name getTmp() {
        return Name.make("__desugarer__var__" + (count++) + "__");
    }

    private static final Name EVAL_AT = Name.make("evalAt");
    private static final Name EVAL_FUTURE = Name.make("evalFuture");
    private static final Name RUN_ASYNC = Name.make("runAsync");
    private static final Name HERE = Name.make("here");
    private static final Name NEXT = Name.make("next");
    private static final Name LOCK = Name.make("lock");
    private static final Name AWAIT = Name.make("await");
    private static final Name RELEASE = Name.make("release");
    private static final Name START_FINISH = Name.make("startFinish");
    private static final Name PUSH_EXCEPTION = Name.make("pushException");
    private static final Name STOP_FINISH = Name.make("stopFinish");
    private static final Name APPLY = Name.make("apply");

    public Node leaveCall(Node old, Node n, NodeVisitor v) throws SemanticException {
        if (n instanceof Future)
            return visitFuture((Future) n);
        if (n instanceof Async)
            return visitAsync((Async) n);
        if (n instanceof AtStmt) {
            assert (false) : ("At statements are deprecated");
            return n;
        }
        if (n instanceof AtExpr)
            return visitAtExpr((AtExpr) n);
        if (n instanceof Here)
            return visitHere((Here) n);
        if (n instanceof Next)
            return visitNext((Next) n);
        if (n instanceof Atomic)
            return visitAtomic((Atomic) n);
        if (n instanceof Await)
            return visitAwait((Await) n);
        if (n instanceof When)
            return visitWhen((When) n);
        if (n instanceof Finish)
            return visitFinish((Finish) n);
        if (n instanceof ForEach)
            return visitForEach((ForEach) n);
        if (n instanceof AtEach)
            return visitAtEach((AtEach) n);
        return n;
    }

    private Expr visitFuture(Future f) throws SemanticException {
        return visitRemoteClosure(f, EVAL_FUTURE, f.place(), true);
    }

    private Expr visitAtExpr(AtExpr e) throws SemanticException {
        return visitRemoteClosure(e, EVAL_AT, e.place(), false);
    }

    private Expr visitRemoteClosure(Closure c, Name implName, Expr place, boolean named) throws SemanticException {
        Position pos = c.position();
        List<TypeNode> typeArgs = Arrays.asList(new TypeNode[] { c.returnType() });
        ClosureDef fDef = c.closureDef();
        ClosureDef cDef = xts.closureDef(c.body().position(), fDef.typeContainer(),
                fDef.methodContainer(), fDef.returnType(),
                fDef.typeParameters(), fDef.formalTypes(),
                fDef.formalNames(), fDef.guard(), fDef.throwTypes());
        Closure closure = (Closure_c) ((Closure_c) xnf.Closure(c.body().position(), c.typeParameters(),
                c.formals(), c.guard(), c.returnType(),
                c.throwTypes(), c.body())).closureDef(cDef).type(xts.closureAnonymousClassDef(cDef).asType());
        List<Expr> args = new ArrayList<Expr>(Arrays.asList(new Expr[] { place, closure }));
        List<Type> mArgs = new ArrayList<Type>(Arrays.asList(new Type[] { xts.Place(), cDef.asType() }));
        if (named) {
            args.add(xnf.StringLit(pos, pos.nameAndLineString()));
            mArgs.add(xts.String());
        }
        List<Type> tArgs = Arrays.asList(new Type[] { fDef.returnType().get() });
        MethodInstance implMI = xts.findMethod(xts.Runtime(),
                xts.MethodMatcher(xts.Runtime(), implName, mArgs),
                context.currentClassDef());
        return xnf.X10Call(pos, xnf.CanonicalTypeNode(pos, xts.Runtime()),
                xnf.Id(pos, implName), typeArgs, args).methodInstance(implMI).type(c.type());
    }

    private Stmt async(Position pos, Stmt body, List clocks, Expr place, String prefix) throws SemanticException {
        ClosureDef cDef = xts.closureDef(body.position(), Types.ref(context.currentClass()),
                Types.ref(context.currentCode().asInstance()),
                Types.ref(xts.Void()), Collections.EMPTY_LIST,
                Collections.EMPTY_LIST, Collections.EMPTY_LIST, null,
                Collections.EMPTY_LIST);
        Type clockRailType = xts.ValRail(xts.Clock());
        Tuple clockRail = (Tuple) xnf.Tuple(pos, clocks).type(clockRailType);
        Block block = body instanceof Block ? (Block) body : xnf.Block(body.position(), body);
        Closure closure = (Closure_c) ((Closure_c) xnf.Closure(body.position(), Collections.EMPTY_LIST,
                Collections.EMPTY_LIST, null, xnf.CanonicalTypeNode(pos, xts.Void()),
                Collections.EMPTY_LIST, block)).closureDef(cDef).type(xts.closureAnonymousClassDef(cDef).asType());
        StringLit pString = xnf.StringLit(pos, prefix + pos.nameAndLineString());
        List<Expr> args = Arrays.asList(new Expr[] { place, clockRail, closure, pString });
        List<Type> mArgs = Arrays.asList(new Type[] {
            xts.Place(), clockRailType, cDef.asType(), xts.String()
        });
        MethodInstance implMI = xts.findMethod(xts.Runtime(),
                xts.MethodMatcher(xts.Runtime(), RUN_ASYNC, mArgs),
                context.currentClassDef());
        return xnf.Eval(pos, xnf.X10Call(pos, xnf.CanonicalTypeNode(pos, xts.Runtime()),
                xnf.Id(pos, RUN_ASYNC), Collections.EMPTY_LIST,
                args).methodInstance(implMI).type(xts.Void()));
    }

    private Stmt visitAsync(Async a) throws SemanticException {
        Position pos = a.position();
        return async(pos, a.body(), a.clocks(), a.place(), "async-");
    }

    private Expr visitHere(Here h) throws SemanticException {
        Position pos = h.position();
        return call(pos, HERE, xts.Place());
    }

    private Stmt visitNext(Next n) throws SemanticException {
        Position pos = n.position();
        return xnf.Eval(pos, call(pos, NEXT, xts.Void()));
    }

    private Stmt visitAtomic(Atomic a) throws SemanticException {
        Position pos = a.position();
        Block tryBlock = xnf.Block(pos, xnf.Eval(pos, call(pos, LOCK, xts.Void())), a.body());
        Block finallyBlock = xnf.Block(pos, xnf.Eval(pos, call(pos, RELEASE, xts.Void())));
        return xnf.Try(pos, tryBlock, Collections.EMPTY_LIST, finallyBlock);
    }
    
    private Stmt visitAwait(Await a) throws SemanticException {
        Position pos = a.position();
        Block tryBlock = xnf.Block(pos, xnf.Eval(pos, call(pos, LOCK, xts.Void())), 
                xnf.While(pos, xnf.Unary(pos, a.expr(), Unary.NOT), xnf.Eval(pos, call(pos, AWAIT, xts.Void()))));
        Block finallyBlock = xnf.Block(pos, xnf.Eval(pos, call(pos, RELEASE, xts.Void())));
        return xnf.Try(pos, tryBlock, Collections.EMPTY_LIST, finallyBlock);
    }
    
    private Stmt wrap(Position pos, Stmt s) {
        return s.reachable() ? xnf.Block(pos, s, xnf.Break(pos)) : s;
    }
    
    private Stmt visitWhen(When w) throws SemanticException {
        Position pos = w.position();
        Block body = xnf.Block(pos, xnf.If(pos, w.expr(), wrap(pos, w.stmt())));
        for(int i=0; i<w.stmts().size(); i++) {
            body = body.append(xnf.If(pos, (Expr) w.exprs().get(i), wrap(pos, (Stmt) w.stmts().get(i))));
        }
        body = body.append(xnf.Eval(pos, call(pos, AWAIT, xts.Void())));
        Block tryBlock = xnf.Block(pos, xnf.Eval(pos, call(pos, LOCK, xts.Void())), 
                xnf.While(pos, xnf.BooleanLit(pos, true), body));
        Block finallyBlock = xnf.Block(pos, xnf.Eval(pos, call(pos, RELEASE, xts.Void())));
        return xnf.Try(pos, tryBlock, Collections.EMPTY_LIST, finallyBlock);
    }
    
    private Expr call(Position pos, Name name, Type t) throws SemanticException {
        MethodInstance mi = xts.findMethod(xts.Runtime(),
                xts.MethodMatcher(xts.Runtime(), name, Collections.EMPTY_LIST),
                context.currentClassDef());
        return xnf.X10Call(pos, xnf.CanonicalTypeNode(pos, xts.Runtime()),
                xnf.Id(pos, name), Collections.EMPTY_LIST,
                Collections.EMPTY_LIST).methodInstance(mi).type(t);
    }

    private Stmt visitFinish(Finish f) throws SemanticException {
        Position pos = f.position();
        Name tmp = getTmp();

        MethodInstance mi = xts.findMethod(xts.Runtime(),
                xts.MethodMatcher(xts.Runtime(), PUSH_EXCEPTION, Collections.singletonList(xts.Throwable())),
                context.currentClassDef());
        LocalDef lDef = xts.localDef(pos, xts.NoFlags(), Types.ref(xts.Throwable()), tmp);
        Formal formal = xnf.Formal(pos, xnf.FlagsNode(pos, xts.NoFlags()), 
                xnf.CanonicalTypeNode(pos, xts.Throwable()), xnf.Id(pos, tmp)).localDef(lDef);
        Expr local = xnf.Local(pos, xnf.Id(pos, tmp)).localInstance(lDef.asInstance()).type(xts.Throwable());
        Expr call = xnf.X10Call(pos, xnf.CanonicalTypeNode(pos, xts.Runtime()),
                xnf.Id(pos, PUSH_EXCEPTION), Collections.EMPTY_LIST,
                Collections.singletonList(local)).methodInstance(mi).type(xts.Void());

        Block tryBlock = xnf.Block(pos, xnf.Eval(pos, call(pos, START_FINISH, xts.Void())), f.body());
        Catch catchBlock = xnf.Catch(pos, formal, xnf.Block(pos, xnf.Eval(pos, call)));
        Block finallyBlock = xnf.Block(pos, xnf.Eval(pos, call(pos, STOP_FINISH, xts.Void())));

        return xnf.Try(pos, tryBlock, Collections.singletonList(catchBlock), finallyBlock);
    }

    private Stmt visitForEach(ForEach f) throws SemanticException {
        Position pos = f.position();
        // Have to desugar some newly-created nodes
        Expr here = visitHere(xnf.Here(f.body().position()));
        Stmt body = async(f.body().position(), f.body(), f.clocks(), here, "foreach-");
        X10Formal formal = (X10Formal) f.formal();
        return xnf.ForLoop(pos, formal, f.domain(), body).locals(formal.explode(this));
    }

    private Stmt visitAtEach(AtEach a) throws SemanticException {
        Position pos = a.position();
        Position bpos = a.body().position();
        Name tmp = getTmp();

        LocalDef lDef = xts.localDef(pos, xts.NoFlags(), Types.ref(a.domain().type()), tmp);
        LocalDecl local = xnf.LocalDecl(pos, xnf.FlagsNode(pos, xts.NoFlags()), 
                xnf.CanonicalTypeNode(pos, a.domain().type()), xnf.Id(pos, tmp),
                a.domain()).localDef(lDef);
        X10Formal formal = (X10Formal) a.formal();
        MethodInstance mi = xts.findMethod(a.domain().type(),
                xts.MethodMatcher(a.domain().type(), APPLY, Collections.singletonList(xts.Int())),
                context.currentClassDef());
        Expr place = xnf.Call(bpos,
                a.domain(),
                xnf.Id(bpos, APPLY),
                xnf.Local(bpos, xnf.Id(bpos, formal.name().id())).localInstance(formal.localDef().asInstance()).type(formal.type().type())).methodInstance(mi).type(xts.Place());
        Stmt body = async(bpos, a.body(), a.clocks(), place, "ateach-");
        return xnf.Block(pos,
                local,
                xnf.ForLoop(pos, formal,
                        nf.Local(pos, nf.Id(pos, tmp)).localInstance(lDef.asInstance()).type(a.domain().type()),
                        body).locals(formal.explode(this)));
    }
}
