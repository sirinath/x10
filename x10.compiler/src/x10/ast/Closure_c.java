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
import polyglot.ast.NodeFactory;
import polyglot.ast.Precedence;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.frontend.Globals;
import polyglot.main.Report;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import polyglot.types.CodeDef;
import polyglot.types.Context;
import polyglot.types.Def;
import polyglot.types.FieldDef;
import polyglot.types.Flags;
import polyglot.types.LazyRef;
import polyglot.types.LocalDef;
import polyglot.types.MethodInstance;
import polyglot.types.Ref;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.Position;
import polyglot.util.SubtypeSet;
import polyglot.util.TypedList;
import polyglot.visit.CFGBuilder;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeCheckPreparer;
import polyglot.visit.TypeChecker;
import x10.constraint.XConstraint;
import x10.constraint.XVar;
import x10.constraint.XTerms;
import x10.errors.Errors;
import x10.types.ClosureDef;
import x10.types.X10ClassDef;
import x10.types.X10Context;
import x10.types.X10MemberDef;
import x10.types.X10TypeMixin;
import x10.types.X10TypeSystem;
import x10.types.X10TypeSystem_c;
import x10.types.checker.PlaceChecker;
import x10.types.checker.VarChecker;
import x10.util.ClosureSynthesizer;
import x10.visit.X10TypeChecker;

/**
 * An implementation of a closure literal in the source text.
 * 
 * It has associated with it a ClosurDef.
 * 
 * Its type is an anonymous class that implements the Fun_m_n synthesized interface associated with ClosureDef.
 * @author vj
 *
 */
public class Closure_c extends Expr_c implements Closure {
	//  List<TypeParamNode> typeParameters;
	List<Formal> formals;
	TypeNode returnType;
	Block body;
	MethodInstance container;
	ClosureDef closureDef;
	ClassType typeContainer;
	DepParameterExpr guard;

	private static final Collection<String> TOPICS = 
		CollectionUtil.list(Report.types, Report.context);

	public Closure_c(Position pos) {
		super(pos);
	}

	TypeNode hasType;
	TypeNode offerType;
	public Closure_c(X10NodeFactory nf, Position pos,  List<Formal> formals, 
			TypeNode returnType, DepParameterExpr guard,  TypeNode offerType, Block body) {
		super(pos);
		//	this.typeParameters = TypedList.copyAndCheck(typeParams, TypeParamNode.class, true);
		this.formals = TypedList.copyAndCheck(formals, Formal.class, true);
		this.returnType = returnType instanceof HasTypeNode_c ? nf.UnknownTypeNode(returnType.position()) : returnType;
		this.guard = guard;
		//this.throwTypes = TypedList.copyAndCheck(throwTypes, TypeNode.class, true);
		this.body = body;
		if (returnType instanceof HasTypeNode_c) 
			hasType = ((HasTypeNode_c) returnType).typeNode();
		this.offerType = offerType;
	}

	/* public List<TypeParamNode> typeParameters() {
	    return typeParameters;
    }
	 */
	public Closure position(Position pos) {
		Closure_c n = (Closure_c) copy();
		n.position=pos;
		return n;
	}
	/*  public Closure typeParameters(List<TypeParamNode> typeParams) {
	    Closure_c n = (Closure_c) copy();
	    n.typeParameters=TypedList.copyAndCheck(typeParams, TypeParamNode.class, true);
	    return n;
    }
	 */

	public List<Formal> formals() {
		return formals;
	}

	public Closure formals(List<Formal> formals) {
		Closure_c c= (Closure_c) copy();
		c.formals= formals;
		return c;
	}
	public Closure_c hasType(TypeNode htn) {
		if (htn != hasType) {
			Closure_c c= (Closure_c) copy();
			c.hasType = htn;
			return c;
		}
		return this;
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
	protected Closure_c reconstruct(/*List<TypeParamNode> typeParams,*/ List<Formal> formals, DepParameterExpr guard, TypeNode returnType,  Block body) {
		if (/*! CollectionUtil.allEqual(typeParams, this.typeParameters) ||*/
				!CollectionUtil.allEqual(formals, this.formals) 
				|| returnType != this.returnType 
				|| guard != this.guard 
			 || body != this.body) {
			Closure_c n = (Closure_c) copy();
			//    n.typeParameters = TypedList.copyAndCheck(typeParams, TypeParamNode.class, true);
			n.formals = TypedList.copyAndCheck(formals, Formal.class, true);
			n.guard = guard;
			n.returnType = returnType;
			n.body = body;
			return n;
		}
		return this;
	}

	/** Visit the children of the expression. */
	public Node visitChildren(NodeVisitor v) {
		//List<TypeParamNode> typeParams = visitList(this.typeParameters, v);
		List<Formal> formals = visitList(this.formals, v);
		DepParameterExpr guard = (DepParameterExpr) visitChild(this.guard, v);

		TypeNode returnType = (TypeNode) visitChild(this.returnType, v);
		Block body = (Block) visitChild(this.body, v);
		TypeNode htn = (TypeNode) visitChild(this.hasType, v);

		return reconstruct(/*typeParams,*/ formals, guard, returnType, body).hasType(htn);
	}

	public Node buildTypesOverride(TypeBuilder tb) {
		X10TypeSystem ts = (X10TypeSystem) tb.typeSystem();

		X10ClassDef ct = (X10ClassDef) tb.currentClass();
		assert ct != null;

		Def def = tb.def();

		if (def instanceof FieldDef) {
			FieldDef fd = (FieldDef) def;
			def = fd.initializer();
		}

		if (! (def instanceof CodeDef)) {
			Errors.issue(tb.job(),
			             new SemanticException("Closure cannot occur outside code body.", position()));
			// Fake it
			def = ts.initializerDef(position(), Types.ref(ct.asType()), Flags.STATIC);
		}

		CodeDef code = (CodeDef) def;

		// Get the enclosing this variable.
		XVar thisVar; // = XTerms.makeLocal(XTerms.makeFreshName("this"));

		if (code instanceof X10MemberDef) {
			thisVar = ((X10MemberDef) code).thisVar();
		}
		else {
			thisVar = ct.thisVar();
		}

		ClosureDef mi = ts.closureDef(position(), 
				Types.ref(ct.asType()), 
				Types.ref(code.asInstance()), 
				returnType.typeRef(),
				Collections.<Ref<? extends Type>>emptyList(),
				thisVar,
				Collections.<LocalDef>emptyList(), 
				null, 
				//null, 
				
				offerType == null ? null : offerType.typeRef());
		if (returnType() instanceof UnknownTypeNode) {
			mi.inferReturnType(true);
		}

		// Unlike methods and constructors, do not create new goals for resolving the signature and body separately;
		// since closures don't have names, we'll never have to resolve the signature.  Just push the code context.
		TypeBuilder tb2 = tb.pushCode(mi);

		Closure_c n = (Closure_c) this.del().visitChildren(tb2);

		if (n.guard() != null) {
			mi.setGuard(n.guard().valueConstraint());

			//mi.setTypeGuard(n.guard().typeConstraint());
		}

		/*  List<Ref<? extends Type>> typeParameters = new ArrayList<Ref<? extends Type>>(n.typeParameters().size());
        for (TypeParamNode tpn : n.typeParameters()) {
            typeParameters.add(Types.ref(tpn.type()));
        }
		 */
		List<Ref<? extends Type>> formalTypes = new ArrayList<Ref<? extends Type>>(n.formals().size());
		for (Formal f : n.formals()) {
			formalTypes.add(f.type().typeRef());
		}

		List<LocalDef> formalNames = new ArrayList<LocalDef>(n.formals().size());
		for (Formal f : n.formals()) {
			formalNames.add(f.localDef());
		}

		mi.setFormalNames(formalNames);
		mi.setReturnType(n.returnType().typeRef());
		// mi.setTypeParameters(Collections.EMPTY_LIST);
		mi.setFormalTypes(formalTypes);

		if (code instanceof X10MemberDef)
			assert mi.thisVar() == ((X10MemberDef) code).thisVar();

		if (returnType instanceof UnknownTypeNode && body == null) {
			Errors.issue(tb.job(),
			             new SemanticException("Cannot infer return type; closure has no body.", position()));
		}

		return n.closureDef(mi);
	}

	public Context enterScope(Context c) {
		if (Report.should_report(TOPICS, 5))
			Report.report(5, "enter scope of closure at " + position());
		// TODO maybe we want a new type of "code context thingy" that is not a type system object, but can live on the Context stack.
		c = c.pushCode(closureDef);
		if (c.pop().inStaticContext())
			c = c.pushStatic();
		return c;
	}

	@Override
	public Context enterChildScope(Node child, Context c) {
		// We should have entered the method scope already.
		if  ( c.currentCode() != this.closureDef())
			assert c.currentCode() == this.closureDef();

		if (child != body()) {
			// Push formals so they're in scope in the types of the other formals.
			c = c.pushBlock();
			/*   for (TypeParamNode f : typeParameters) {
                f.addDecls(c);
            }
			 */
			for (Formal f : formals) {
				f.addDecls(c);
			}
		}

		return super.enterChildScope(child, c);
	}

	@Override
	public Node setResolverOverride(Node parent, TypeCheckPreparer v) {
		if (returnType() instanceof UnknownTypeNode && body() != null) {
			UnknownTypeNode tn = (UnknownTypeNode) returnType();
			tn.setResolver(this, v);

			NodeVisitor childv = v.enter(parent, this);
			childv = childv.enter(this, tn);

			if (childv instanceof TypeCheckPreparer) {
				TypeCheckPreparer tcp = (TypeCheckPreparer) childv;
				final LazyRef<Type> r = (LazyRef<Type>) tn.typeRef();
				TypeChecker tc = new X10TypeChecker(v.job(), v.typeSystem(), v.nodeFactory(), v.getMemo());
				tc = (TypeChecker) tc.context(tcp.context().freeze());
				r.setResolver(new TypeCheckReturnTypeGoal(this, body(), tc, r));
			}
		}
		return super.setResolverOverride(parent, v);
	}

	@Override
	public Node typeCheck(ContextVisitor tc) {
		X10TypeSystem_c xts = (X10TypeSystem_c) tc.typeSystem();

		Context c = tc.context();
		Closure_c n = this;

		if (guard != null) {
			VarChecker ac = new VarChecker(tc.job());
			ac = (VarChecker) ac.context(tc.context());
			guard.visit(ac);
			if (ac.error != null) {
				Errors.issue(tc.job(), ac.error, this);
			}
		}

		if (n.returnType() instanceof UnknownTypeNode) {
			NodeFactory nf = tc.nodeFactory();
			X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
			Ref<Type> tr = ((Ref<Type>) n.returnType().typeRef());
			Type t = tr.getCached();
			if (!tr.known() && ts.isUnknown(t)) {
				// Body had no return statement.  Set to void.
				t = ts.Void();
			}
			tr.update(t);
			n = (Closure_c) n.returnType(nf.CanonicalTypeNode(n.returnType().position(), t));
		}

		// Create an anonymous subclass of the closure type.
		ClosureDef def = n.closureDef;
		ClassDef cd = ClosureSynthesizer.closureAnonymousClassDef(xts, def);
		n = (Closure_c) n.type(cd.asType());
		if (hasType != null) {
			final TypeNode h = (TypeNode) n.visitChild(n.hasType, tc);
			Type hasType = PlaceChecker.ReplaceHereByPlaceTerm(h.type(), ( X10Context ) tc.context());
			n = n.hasType(h);
			if (!xts.isSubtype(n.returnType().type(), hasType, tc.context())) {
				Errors.issue(tc.job(), new Errors.TypeIsNotASubtypeOfTypeBound(type, hasType, position()));
			}
		}
		return n;
	}

	@Override
	public Node conformanceCheck(ContextVisitor tc) {

		return this;
	}

	public Term firstChild() {
		return //listChild(/*typeParameters(), 
		listChild(formals(), returnType);
	}

	/**
	 * Visit this term in evaluation order.
	 * [IP] Treat this as a conditional to make sure the following
	 *      statements are always reachable.
	 * FIXME: We should really build our own CFG, push a new context,
	 * and disallow uses of "continue", "break", etc. in closures.
	 */
	@Override
	public <S> List<S> acceptCFG(CFGBuilder v, List<S> succs) {
		/*  if (formals().isEmpty()) {
		    v.visitCFGList(typeParameters(), returnType, ENTRY);
	    }
	    else {
		    v.visitCFGList(typeParameters(), formals.get(0), ENTRY);

	    }*/
		v.visitCFGList(formals(), returnType, ENTRY);

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
		// return new SubtypeSet(Globals.TS());
		return null;
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

		/*if (! typeParameters.isEmpty()) {
		sb.append("[");
		for(Iterator iter= typeParameters.iterator(); iter.hasNext(); ) {
			TypeParamNode tpn= (TypeParamNode) iter.next();
			sb.append(tpn.toString());
			if (iter.hasNext()) sb.append(", ");
		}
		sb.append("]");
	}*/
		sb.append(" (");
		for(Iterator<Formal> iter = formals.iterator(); iter.hasNext(); ) {
			Formal formal = iter.next();
			sb.append(formal.toString());
			if (iter.hasNext()) sb.append(", ");
		}
		sb.append(")");
		sb.append(guard==null?"{}":guard);
		sb.append(": ");
		sb.append(returnType.toString());
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
		for (Iterator<Formal> i = formals.iterator(); i.hasNext(); ) {
			Formal f = i.next();
			print(f, w, tr);
			if (i.hasNext()) {
				w.write(",");
				w.allowBreak(0, " ");
			}
		}
		w.end();
		w.write(") ");
		w.end();
		printSubStmt(body, w, tr);
	}
}
