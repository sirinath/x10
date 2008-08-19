/*
 * Created on Feb 26, 2007
 */
package polyglot.ext.x10.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import polyglot.ast.Block;
import polyglot.ast.CodeBlock;
import polyglot.ast.Expr_c;
import polyglot.ast.Formal;
import polyglot.ast.Node;
import polyglot.ast.Precedence;
import polyglot.ast.Term;
import polyglot.ast.TypeCheckFragmentGoal;
import polyglot.ast.TypeNode;
import polyglot.ext.x10.extension.X10Del_c;
import polyglot.ext.x10.types.ClosureDef;
import polyglot.ext.x10.types.ClosureType;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.types.X10TypeSystem_c;
import polyglot.frontend.Globals;
import polyglot.main.Report;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import polyglot.types.CodeDef;
import polyglot.types.Context;
import polyglot.types.Def;
import polyglot.types.DerefTransform;
import polyglot.types.FieldDef;
import polyglot.types.LazyRef;
import polyglot.types.LocalDef;
import polyglot.types.MethodInstance;
import polyglot.types.Ref;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.Types;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.util.SubtypeSet;
import polyglot.util.TransformingList;
import polyglot.util.TypedList;
import polyglot.visit.CFGBuilder;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeCheckPreparer;
import polyglot.visit.TypeChecker;

public class Closure_c extends Expr_c implements Closure {
    List<TypeParamNode> typeParameters;
    List<Formal> formals;
    TypeNode returnType;
    List<TypeNode> throwTypes;
    Block body;
    MethodInstance container;
    ClosureDef closureDef;
    ClassType typeContainer;
    DepParameterExpr guard;

    private static final Collection TOPICS = 
        CollectionUtil.list(Report.types, Report.context);

    public Closure_c(Position pos) {
	super(pos);
    }

    public Closure_c(Position pos, List<TypeParamNode> typeParams, List<Formal> formals, TypeNode returnType, DepParameterExpr guard, List<TypeNode> throwTypes, Block body) {
	super(pos);
	this.typeParameters = TypedList.copyAndCheck(typeParams, TypeParamNode.class, true);
	this.formals = TypedList.copyAndCheck(formals, Formal.class, true);
	this.returnType = returnType;
	this.guard = guard;
	this.throwTypes = TypedList.copyAndCheck(throwTypes, TypeNode.class, true);
	this.body = body;
    }

    
    public List<TypeParamNode> typeParameters() {
	    return typeParameters;
    }
    
    public Closure typeParameters(List<TypeParamNode> typeParams) {
	    Closure_c n = (Closure_c) copy();
	    n.typeParameters=TypedList.copyAndCheck(typeParams, TypeParamNode.class, true);
	    return n;
    }

    public List<Formal> formals() {
	return formals;
    }

    public Closure formals(List<Formal> formals) {
	Closure_c c= (Closure_c) copy();
	c.formals= formals;
	return c;
    }

    public TypeNode returnType() {
	return returnType;
    }

    public Closure returnType(TypeNode returnType) {
	Closure_c c = (Closure_c) copy();
	c.returnType = returnType;
	return c;
    }
    
    public DepParameterExpr guard() {
	    return guard;
    }

    public Closure guard(DepParameterExpr guard) {
	    Closure_c n = (Closure_c) copy();
	    n.guard = guard;
	    return n;
    }

    public List<TypeNode> throwTypes() {
	return throwTypes;
    }

    public Closure throwTypes(List<TypeNode> throwTypes) {
	Closure_c c= (Closure_c) copy();
	c.throwTypes= throwTypes;
	return c;
    }

    public Block body() {
	return body;
    }

    public CodeBlock body(Block body) {
	Closure_c c= (Closure_c) copy();
	c.body= body;
	return c;
    }

    public Term codeBody() {
	return body;
    }

    public MethodInstance methodContainer() {
	return container;
    }

    public Closure methodContainer(MethodInstance methodInstance) {
	Closure_c c= (Closure_c) copy();
	c.container = methodInstance;
	return c;
    }

    public CodeDef codeDef() {
	return closureDef;
    }

    public ClosureDef closureDef() {
	return this.closureDef;
    }

    public Closure closureDef(ClosureDef ci) {
        if (ci == this.closureDef) return this;
	Closure_c n = (Closure_c) copy();
	n.closureDef = ci;
	return n;
    }

    /** Reconstruct the closure. */
    protected Closure_c reconstruct(List<TypeParamNode> typeParams, List<Formal> formals, TypeNode returnType, List<TypeNode> throwTypes, Block body) {
	if (! CollectionUtil.allEqual(typeParams, this.typeParameters) || !CollectionUtil.allEqual(formals, this.formals) || returnType != this.returnType || ! CollectionUtil.allEqual(throwTypes, this.throwTypes) || body != this.body) {
	    Closure_c n = (Closure_c) copy();
	    n.typeParameters = TypedList.copyAndCheck(typeParams, TypeParamNode.class, true);
	    n.formals = TypedList.copyAndCheck(formals, Formal.class, true);
	    n.returnType = returnType;
	    n.throwTypes = TypedList.copyAndCheck(throwTypes, TypeNode.class, true);
	    n.body = body;
	    return n;
	}
	return this;
    }

    /** Visit the children of the expression. */
    public Node visitChildren(NodeVisitor v) {
	List<TypeParamNode> typeParams = visitList(this.typeParameters, v);
	List<Formal> formals = visitList(this.formals, v);
	TypeNode returnType = (TypeNode) visitChild(this.returnType, v);
	List<TypeNode> throwTypes = visitList(this.throwTypes, v);
	Block body = (Block) visitChild(this.body, v);

	return reconstruct(typeParams, formals, returnType, throwTypes, body);
    }

    public Node buildTypesOverride(TypeBuilder tb) throws SemanticException {
        X10TypeSystem ts = (X10TypeSystem) tb.typeSystem();

        ClassDef ct = tb.currentClass();
        assert ct != null;

        Def def = tb.def();
        
        if (def instanceof FieldDef) {
            FieldDef fd = (FieldDef) def;
            def = fd.initializer();
        }
        
        if (! (def instanceof CodeDef)) {
            throw new SemanticException("Closure cannot occur outside code body.", position());
        }
        
        CodeDef code = (CodeDef) def;
        
        ClosureDef mi = ts.closureDef(position(), Types.ref(ct.asType()), Types.ref(code.asInstance()), returnType.typeRef(),
                                      Collections.<Ref<? extends Type>>emptyList(),
                                         Collections.<Ref<? extends Type>>emptyList(),
                                         Collections.<LocalDef>emptyList(),
                                         null, Collections.<Ref<? extends Type>>emptyList());

        if (returnType() instanceof UnknownTypeNode) {
            mi.inferReturnType(true);
        }

        // Unlike methods and constructors, do not create new goals for resolving the signature and body separately;
        // since closures don't have names, we'll never have to resolve the signature.  Just push the code context.
        TypeBuilder tb2 = tb.pushCode(mi);

        Closure_c n = (Closure_c) this.del().visitChildren(tb2);

        if (n.guard() != null)
        	mi.setGuard(n.guard().xconstraint());
        
        List<Ref<? extends Type>> typeParameters = new ArrayList<Ref<? extends Type>>(n.typeParameters().size());
        for (TypeParamNode tpn : n.typeParameters()) {
            typeParameters.add(Types.ref(tpn.type()));
        }

        List<Ref<? extends Type>> formalTypes = new ArrayList<Ref<? extends Type>>(n.formals().size());
        for (Formal f : n.formals()) {
             formalTypes.add(f.type().typeRef());
        }
        
        List<LocalDef> formalNames = new ArrayList<LocalDef>(n.formals().size());
        for (Formal f : n.formals()) {
            formalNames.add(f.localDef());
        }

        List<Ref<? extends Type>> throwTypes = new ArrayList<Ref<? extends Type>>(n.throwTypes().size());
        for (TypeNode tn : n.throwTypes()) {
            throwTypes.add(tn.typeRef());
        }
        
        mi.setFormalNames(formalNames);
        mi.setReturnType(n.returnType().typeRef());
        mi.setTypeParameters(typeParameters);
        mi.setFormalTypes(formalTypes);
        mi.setThrowTypes(throwTypes);

        if (returnType instanceof UnknownTypeNode && body == null)
        	throw new SemanticException("Cannot infer return type; closure has no body.", position());

        return n.closureDef(mi);
    }

    public Context enterScope(Context c) {
        if (Report.should_report(TOPICS, 5))
	    Report.report(5, "enter scope of closure at " + position());
        // TODO maybe we want a new type of "code context thingy" that is not a type system object, but can live on the Context stack.
        c = c.pushCode(closureDef);
        return c;
    }

    @Override
    public Node setResolverOverride(Node parent, TypeCheckPreparer v) {
	    if (returnType() instanceof UnknownTypeNode && body != null) {
		    UnknownTypeNode tn = (UnknownTypeNode) returnType();
		    tn.setResolver(this, v);

		    NodeVisitor childv = v.enter(parent, this);
    	            childv = childv.enter(this, returnType());
    		    		    
		    if (childv instanceof TypeCheckPreparer) {
			    TypeCheckPreparer tcp = (TypeCheckPreparer) childv;
			    final LazyRef<Type> r = (LazyRef<Type>) tn.typeRef();
			    TypeChecker tc = new TypeChecker(v.job(), v.typeSystem(), v.nodeFactory(), v.getMemo());
			    tc = (TypeChecker) tc.context(tcp.context().freeze());
			    r.setResolver(new TypeCheckFragmentGoal(this, body, tc, r, true));
		    }
	    }
	    return super.setResolverOverride(parent, v);
    }

    @Override
    public Node typeCheck(ContextVisitor tc) throws SemanticException {
        X10TypeSystem x10ts = (X10TypeSystem) tc.typeSystem();

        Context c = tc.context();
        Closure closure = this;

        for (Iterator<TypeNode> i = throwTypes().iterator(); i.hasNext(); ) {
            TypeNode tn = (TypeNode) i.next();
            Type t = tn.type();
            if (! t.isThrowable()) {
                throw new SemanticException("Type \"" + t +
                    "\" is not a subclass of \"" + x10ts.Throwable() + "\".",
                    tn.position());
            }
        }

        ClosureDef def = this.closureDef;
        return type(def.asType());
    }

    public Term firstChild() {
        return listChild(typeParameters(), listChild(formals(), returnType));
    }

    /**
     * Visit this term in evaluation order.
     * [IP] Treat this as a conditional to make sure the following
     *      statements are always reachable.
     * FIXME: We should really build our own CFG, push a new context,
     * and disallow uses of "continue", "break", etc. in closures.
     */
    @Override
    public List<Term> acceptCFG(CFGBuilder v, List<Term> succs) {
	    if (formals().isEmpty()) {
		    v.visitCFGList(typeParameters(), returnType, ENTRY);
	    }
	    else {
		    v.visitCFGList(typeParameters(), formals.get(0), ENTRY);
		    v.visitCFGList(formals(), returnType, ENTRY);
	    }

        // If building the CFG for the enclosing code, don't thread
        // in the closure body.  Otherwise, we're building the CFG
        // for the closure itself.
        if (! succs.isEmpty()) {
            v.visitCFG(returnType, this, EXIT);
        }
        else {
            v.visitCFG(returnType, body, ENTRY);
            v.visitCFG(body, this, EXIT);
        }

        /*
        v.visitCFG(returnType, FlowGraph.EDGE_KEY_TRUE, body, ENTRY,
                   FlowGraph.EDGE_KEY_FALSE, this, EXIT);
                   */
        return succs;
    }

    @Override
    public SubtypeSet exceptions() {
	    // The closure itself doesn't throw any exceptions, but a call to it might.
	    return new SubtypeSet(Globals.TS());
    }

    public Precedence precedence() {
	return Precedence.LITERAL;
    }

    public boolean constantValueSet() {
	return false;
    }

    public boolean isConstant() {
	return false;
    }

    public Object constantValue() {
	return null;
    }

    public String toString() {
	StringBuilder sb= new StringBuilder();
	if (! typeParameters.isEmpty()) {
		sb.append("[");
		for(Iterator iter= typeParameters.iterator(); iter.hasNext(); ) {
			TypeParamNode tpn= (TypeParamNode) iter.next();
			sb.append(tpn.toString());
			if (iter.hasNext()) sb.append(", ");
		}
		sb.append("]");
	}
	sb.append(" (");
	for(Iterator iter= formals.iterator(); iter.hasNext(); ) {
	    Formal formal= (Formal) iter.next();
	    sb.append(formal.toString());
	    if (iter.hasNext()) sb.append(", ");
	}
	sb.append("): ");
	sb.append(returnType.toString());
	if (throwTypes.size() > 0) {
	    sb.append("throws ");
	    sb.append(CollectionUtil.listToString(throwTypes));
	}
	sb.append(" => ");
	sb.append(body);
	return sb.toString();
    }

	/** Write the statement to an output file. */
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.begin(0);
		w.write("(");
		w.allowBreak(2, 2, "", 0);
		w.begin(0);
		for (Iterator i = formals.iterator(); i.hasNext(); ) {
			Formal f = (Formal) i.next();
			print(f, w, tr);
			if (i.hasNext()) {
				w.write(",");
				w.allowBreak(0, " ");
			}
		}
		w.end();
		w.write(") ");

		if (! throwTypes().isEmpty()) {
		    w.allowBreak(6);
		    w.write("throws ");
		    for (Iterator i = throwTypes().iterator(); i.hasNext(); ) {
		    	TypeNode tn = (TypeNode) i.next();
		    	print(tn, w, tr);
		    	
		    	if (i.hasNext()) {
		    		w.write(",");
		    		w.allowBreak(4, " ");
		    	}
		    }
		}
		w.end();
	    printSubStmt(body, w, tr);
	}
}
