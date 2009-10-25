/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package polyglot.ext.x10.types;

import java.util.ArrayList;
import java.util.List;

import polyglot.ast.Binary;
import polyglot.ast.Call;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.Formal;
import polyglot.ast.Lit;
import polyglot.ast.Local;
import polyglot.ast.Receiver;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.ast.Unary;
import polyglot.ast.Variable;
import polyglot.ext.x10.ast.Contains;
import polyglot.ext.x10.ast.Here;
import polyglot.ext.x10.ast.ParExpr;
import polyglot.ext.x10.ast.SubtypeTest;
import polyglot.ext.x10.ast.Tuple;
import polyglot.ext.x10.ast.X10Field_c;
import polyglot.ext.x10.ast.X10Special;
import polyglot.types.ClassDef;
import polyglot.types.CodeDef;
import polyglot.types.Context;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.LocalInstance;
import polyglot.types.MethodInstance;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.Types;
import polyglot.util.InternalCompilerError;
import x10.constraint.XConstraint;
import x10.constraint.XConstraint_c;
import x10.constraint.XEQV_c;
import x10.constraint.XFailure;
import x10.constraint.XLit;
import x10.constraint.XLit_c;
import x10.constraint.XLocal;
import x10.constraint.XName;
import x10.constraint.XRef_c;
import x10.constraint.XRoot;
import x10.constraint.XTerm;
import x10.constraint.XTerms;
import x10.constraint.XVar;

/**
 * Translate from a Expr or TypeNode to a XConstraint term that can be serialized.
 * @author nystrom
 */
public class XTypeTranslator {
	public static final boolean THIS_VAR = true;
	
    private final X10TypeSystem ts;

	public XTypeTranslator(X10TypeSystem xts) {
		super();
		ts = xts;
	}

	public void addTypeToEnv(XTerm self, final Type t) throws SemanticException {
	}

	private XTerm trans(XConstraint c, Unary t, X10Context xc) throws SemanticException {
		if (t.operator() == Unary.NOT)
			return XTerms.makeNot(trans(c, t.expr(), xc));
		XTerm v = XTerms.makeAtom(XTerms.makeName(t.operator()), trans(c, t.expr(), xc));
		addTypeToEnv(v, t.type());
		return v;
	}
	
	public XVar trans(XConstraint c, XVar target, FieldInstance fi) throws SemanticException {
	    String string = Types.get(fi.def().container()) + "#" + fi.name().toString();
	    XVar v = XTerms.makeField(target, XTerms.makeName(fi.def(), string));
	    addTypeToEnv(v, fi.type());
	    return v;
	}

	public XTerm trans(XConstraint c, XTerm target, FieldInstance fi) throws SemanticException {
		return trans(c, target, fi, fi.type());
	}
	
	public XTerm trans(XConstraint c, XTerm target, MethodInstance fi, Type t) throws SemanticException {
	    assert X10Flags.toX10Flags(fi.flags()).isProperty() && fi.formalTypes().size() == 0;
	    XTerm v;
	    XName field = XTerms.makeName(fi.def(), Types.get(fi.def().container()) + "#" + fi.name().toString() + "()");
	    if (target instanceof XVar) {
	        v = XTerms.makeField((XVar) target, field);
	    }
	    else {
	        v = XTerms.makeAtom(field, target);
	    }
	    addTypeToEnv(v, t);
	    return v;
	}
	
	public XTerm trans(XConstraint c, XTerm target, FieldInstance fi, Type t) throws SemanticException {
		XTerm v;
		XName field = XTerms.makeName(fi.def(), Types.get(fi.def().container()) + "#" + fi.name().toString());
		if (fi.flags().isStatic()) {
		    Type container = Types.get(fi.def().container());
		    container = X10TypeMixin.baseType(container);
		    if (container instanceof X10ClassType) {
		        target  = XTerms.makeLit(((X10ClassType) container).fullName());
		    }
		    else {
		        throw new SemanticException("Cannot translate a static field of non-class type " + container + ".");
		    }
		}
		if (target instanceof XVar) {
			v = XTerms.makeField((XVar) target, field);
		    }
		    else {
		        v = XTerms.makeAtom(field, target);
		    }
		addTypeToEnv(v, t);
		return v;
	}
	
	private XTerm trans(XConstraint c, Field t, X10Context xc) throws SemanticException {
		return trans(c, trans(c, t.target(), xc), t.fieldInstance(), t.type());
	}

	private XTerm trans(XConstraint c, X10Special t, X10Context xc0) throws SemanticException {
	    X10Context xc = xc0;
		if (t.kind() == X10Special.SELF) {
		        if (c == null)
		            throw new SemanticException("Cannot refer to self outside a dependent clause.");
			XVar v = (XVar) c.self().clone();
			addTypeToEnv(v, t.type());
			return v;
		}
		else {
		    TypeNode tn = t.qualifier();
		    if (tn != null) {
		        Type q = X10TypeMixin.baseType(tn.type());
		        if (q instanceof X10ClassType) {
		            X10ClassType ct = (X10ClassType) q;
		            while (xc != null) {
		                if (xc.inSuperTypeDeclaration()) {
		                    if (xc.supertypeDeclarationType() == ct.def())
		                        break;
		                }
		                else if (xc.currentClassDef() == ct.def()) {
		                    break;
		                }
		                xc = (X10Context) xc.pop();
		            }
		        }
		    }
		    XRoot thisVar = xc == null ? null : xc.thisVar();
		    if (thisVar == null) {
		        SemanticException e = new SemanticException("Cannot refer to this from this context.");
		        if (true)
		        throw new InternalCompilerError(e.getMessage());
		        throw e;
		    }
		    return thisVar;
		}
	}

	public XLocal trans(LocalInstance li) throws SemanticException {
		return trans(li, li.type());
	}
	
	public XLocal trans(LocalInstance li, Type t) throws SemanticException {
		XLocal v = XTerms.makeLocal(XTerms.makeName(li.def(), li.name().toString()));
		addTypeToEnv(v, t);
		return v;
	}

	private XLocal trans(XConstraint c, Local t) throws SemanticException {
		return trans(t.localInstance(), t.type());
	}

	private XTerm trans(XConstraint c, TypeNode t) {
		return trans(t.type());
	}
	
	public XLocal transTypeParam(ParameterType t) {
		return XTerms.makeLocal(XTerms.makeName(t));
	}
	
	public XTerm trans(Type t) {
		if (t instanceof ParameterType)
			return transTypeParam((ParameterType) t);
//		if (t instanceof X10ClassType)
//		    return transClassType((X10ClassType) t);
//		if (t instanceof ConstrainedType)
//		    return transConstrainedType((ConstrainedType) t);
		if (t instanceof MacroType) {
		    MacroType pt = (MacroType) t;
		    return trans(pt.definedType());
		}
		return new XTypeLit_c(t);
//		return XTerms.makeLit(t);
	}
	
	public static boolean hasVar(Type t, XVar x) {
	    if (t instanceof ConstrainedType) {
		ConstrainedType ct = (ConstrainedType) t;
		Type b = X10TypeMixin.baseType(t);
		XConstraint c = X10TypeMixin.xclause(t);
		if ( hasVar(b, x)) return true;
		for (XTerm term : c.constraints()) {
		    if (term.hasVar(x))
			return true;
		}
	    }
	    if (t instanceof MacroType) {
		MacroType pt = (MacroType) t;
		return hasVar(pt.definedType(), x);
	    }
	    return false;
	}
	
	public static Type subst(Type t, XTerm y, XRoot x) throws SemanticException {
	    return Subst.subst(t, y, x);
	}

	private XTerm transClassType(X10ClassType t) {
	    X10ClassDef def = t.x10Def();
	    
	    int n = def.typeParameters().size();
	    if (n == 0)
		return XTerms.makeLit(def);
	    
	    List<XTerm> terms = new ArrayList<XTerm>();

	    if (t.isIdentityInstantiation()) {
		for (int i = 0; i < n; i++) {
		    XTerm ti = trans(def.typeParameters().get(i));
		    terms.add(ti);
		}
		return XTerms.makeAtom(XTerms.makeName(def), terms);
	    }
	    
	    List<Type> args = t.typeArguments();
	    for (int i = 0; i < n; i++) {
		XTerm ti = trans(args.get(i));
		terms.add(ti);
	    }
	    return XTerms.makeAtom(XTerms.makeName(def), terms);
	}

	public XLit trans(int t) {
		return XTerms.makeLit(t);
	}
	
	public XLit trans(boolean t) {
		return XTerms.makeLit(t);
	}
	public XLit transNull() {
		return XTerms.makeLit(null);
	}
	
	public static class XTypeLit_c extends XLit_c {
	    private XTypeLit_c(Type l) {
		super(l);
	    }

	    public Type type() {
	    return (Type) val;
	    }

	    public boolean hasVar(XVar v) {
	    return XTypeTranslator.hasVar(type(), v);
	    }

	    public XTerm subst(XTerm y, XRoot x, boolean propagate) {
		XTypeLit_c n = (XTypeLit_c) super.subst(y, x, propagate);
		if (n == this) n = (XTypeLit_c) clone();
		try {
		    n.val = Subst.subst(type(), y, x);
		}
		catch (SemanticException e) {
		    return n;
		}
		return n;
	    }
	}

	static class HereToken {
		@Override
		public boolean equals(Object o) {
			return o instanceof HereToken;
		}
		@Override
		public String toString() {
			return "here";
		}
		@Override
		public int hashCode() {
			return "here".hashCode();
		}
	}
	
	HereToken here;
	
	public XLit transHere() {
		if (here == null) here = new HereToken();
		return XTerms.makeLit(here);
	}
	
	public XLit trans(Lit t) {
		return XTerms.makeLit(t.constantValue());
	}

	private void transType(TypeConstraint c, Binary t, X10Context xc) throws SemanticException {
	    Expr left = t.left();
	    Expr right = t.right();
	    XTerm v;
	    
	    if (t.operator() == Binary.COND_AND || (t.operator() == Binary.BIT_AND && ts.isImplicitCastValid(t.type(), ts.Boolean(), xc))) {
	        transType(c, left, xc);
	        transType(c, right, xc);
	    }
	    else {
	        throw new SemanticException("Cannot translate " + t + " into a type constraint.", t.position());
	    }
	}

	private void transType(TypeConstraint c, Expr t, X10Context xc) throws SemanticException {
	    if (t instanceof Binary) {
	        transType(c, (Binary) t, xc);
	    }
	    else if (t instanceof ParExpr) {
	        transType(c, ((ParExpr) t).expr(), xc);
	    }
	    else if (t instanceof SubtypeTest) {
	        transType(c, (SubtypeTest) t, xc);
	    }
	    else {
	        throw new SemanticException("Cannot translate " + t + " into a type constraint.", t.position());
	    }
	}
	

        private void transType(TypeConstraint c, SubtypeTest t, X10Context xc) throws SemanticException {
                TypeNode left = t.subtype();
                TypeNode right = t.supertype();
                c.addTerm(new SubtypeConstraint_c(left.type(), right.type(), t.equals()));
        }

	
	private XTerm trans(XConstraint c, Binary t, X10Context xc) throws SemanticException {
	    Expr left = t.left();
	    Expr right = t.right();
	    XTerm v;
	    XTerm lt = trans(c, left, xc);
	    XTerm rt = trans(c, right, xc);
	    if (lt == null || rt == null)
	        throw new SemanticException("Cannot translate " + t + " to constraint term.");
	    if (t.operator() == Binary.EQ) {
	        v = XTerms.makeEquals(lt, rt);
	    }
	    else if (t.operator() == Binary.COND_AND || (t.operator() == Binary.BIT_AND && ts.isImplicitCastValid(t.type(), ts.Boolean(), xc))) {
	        v = XTerms.makeAnd(lt, rt);
	    }
	    else {
			v = XTerms.makeAtom(XTerms.makeName(t.operator()), lt, rt);
		}
		addTypeToEnv(v, t.type());
		return v;
	}
	
	private XTerm trans(XConstraint c, Tuple t, X10Context xc) throws SemanticException {
	    List<XTerm> terms = new ArrayList<XTerm>();
	    for (Expr e : t.arguments()) {
		terms.add(trans(c, e, xc));
	    }
	    return XTerms.makeAtom(XTerms.makeName("tuple"), terms);
	}
	
	private XTerm trans(XConstraint c, Contains t, X10Context xc) throws SemanticException {
	    Expr left = t.item();
	    Expr right = t.collection();
	    boolean containsAll = t.isSubsetTest();
	    if (containsAll)
		return XTerms.makeAtom(XTerms.makeName("subset"), trans(c, left, xc), trans(c, right, xc));
	    else
		return XTerms.makeAtom(XTerms.makeName("in"), trans(c, left, xc), trans(c, right, xc));
	}
	
	private XTerm trans(XConstraint c, Call t, X10Context xc) throws SemanticException {
	    X10MethodInstance xmi = (X10MethodInstance) t.methodInstance();
	    Flags f = xmi.flags();
	    if (X10Flags.toX10Flags(f).isProperty()) {
		XTerm r = trans(c, t.target(), xc);
		// FIXME: should just return the atom, and add atom==body to the real clause of the class
		// FIXME: fold in class's real clause constraints on parameters into real clause of type parameters
		XTerm body = xmi.body();
		if (body != null) {
		    if (xmi.x10Def().thisVar() != null && t.target() instanceof Expr) {
		        body = body.subst(r, xmi.x10Def().thisVar());
		    }
		    for (int i = 0; i < t.arguments().size(); i++) {
			XRoot x = (XRoot) X10TypeMixin.selfVar(xmi.formalTypes().get(i));
			XTerm y = trans(c, t.arguments().get(i), xc);
			body = body.subst(y, x);
		    }
		    addTypeToEnv(body, xmi.returnType());
		    return body;
		}
		else {
		    if (t.arguments().size() == 0) {
			XName field = XTerms.makeName(xmi.def(), Types.get(xmi.def().container()) + "#" + xmi.name() + "()");
			XTerm v;
			if (r instanceof XVar) {
				v = XTerms.makeField((XVar) r, field);
			}
			else {
				v = XTerms.makeAtom(field, r);
			}
			addTypeToEnv(v, xmi.returnType());
			return v;
		    }
		    else {
			List<XTerm> terms = new ArrayList<XTerm>();
			terms.add(r);
			for (Expr e : t.arguments()) {
			    terms.add(trans(c, e, xc));
			}
			XTerm v = XTerms.makeAtom(XTerms.makeName(xmi, xmi.name().toString()), terms);
			addTypeToEnv(v, xmi.returnType());
			return v;
		    }
		}
	    }
	
	    return null;
//	    throw new SemanticException("Cannot translate call |" + t + "| into a constraint; it must be a property method call.");
	}

	private XTerm trans(XConstraint c, Variable term, X10Context xc) throws SemanticException {
		//Report.report(1, "TypeTranslator: translating Variable " + term);
		if (term instanceof Field)
			return trans(c, (Field) term, xc);
		if (term instanceof X10Special)
			return trans(c, (X10Special) term, xc);
		if (term instanceof Local)
			return trans(c, (Local) term);

		return null;
//		throw new SemanticException("Cannot translate variable |" + term + "| into a constraint; it must be a field, local, this, or self.");
	}
	
	public XTerm trans(XConstraint c, Receiver term, X10Context xc) throws SemanticException {
		// Report.report(1, "TypeTranslator: translating Receiver " + term);
		if (term == null)
			return null;
		if (term instanceof Lit)
			return trans((Lit) term);
		if (term instanceof Here)
			return transHere();
		if (term instanceof Variable)
			return trans(c, (Variable) term, xc);
		if (term instanceof X10Special)
			return trans(c, (X10Special) term, xc);
		if (term instanceof Expr) {
			Expr e = (Expr) term;
			if (e.isConstant())
				return XTerms.makeLit(e.constantValue());
		}
		if (term instanceof Call) {
		    return trans(c, (Call) term, xc);
		}
		if (term instanceof Tuple) {
		    return trans(c, (Tuple) term, xc);
		}
		if (term instanceof Unary) {
			Unary u = (Unary) term;
			Expr t2 = u.expr();
			Unary.Operator op = u.operator();
			if (op == Unary.POS)
				return trans(c, t2, xc);
			return trans(c, (Unary) term, xc);
		}
		if (term instanceof Binary)
			return trans(c, (Binary) term, xc);
		if (term instanceof Contains)
		    return trans(c, (Contains) term, xc);
		if (term instanceof TypeNode)
		    return trans(c, (TypeNode) term);
		if (term instanceof ParExpr)
			return trans(c, ((ParExpr) term).expr(), xc);

		return null;
//		throw new SemanticException("Cannot translate type or expression |" + term + "| (" + term.getClass().getName() + ")" + " to a term.");
	}
	
	/**
	 * Translate an expression into a XConstraint, throwing SemanticExceptions 
	 * if this is not possible.
	 * This must be called after type-checking of Expr.
	 * @param formals TODO
	 * @param term
	 * @param xc TODO
	 * @param c
	 * @return
	 * @throws SemanticException
	 */
	public XConstraint constraint(List<Formal> formals, Expr term, X10Context xc) throws SemanticException {
            XConstraint c = new XConstraint_c();
            if (term == null)
                return c;
            
            if (! term.type().isBoolean())
                throw new SemanticException("Cannot build constraint from expression |" + term + "| of type " + term.type() + "; not a boolean.");
            
            // TODO: handle the formals.
            XTerm t = trans(c, term, xc);
            
            if (t == null)
                throw new SemanticException("Cannot build constraint from expression |" + term + "|.");
            
            try {
                c.addTerm(t);
            }
            catch (XFailure e) {
                throw new SemanticException(e.getMessage());
            }
            return c;
	}
	
	public TypeConstraint typeConstraint(List<Formal> formals, Expr term, X10Context xc) throws SemanticException {
	    TypeConstraint c = new TypeConstraint_c();
        if (term == null)
        	return c;
        
        if (! term.type().isBoolean())
        	throw new SemanticException("Cannot build constraint from expression |" + term + "| of type " + term.type() + "; not a boolean.");
        
        // TODO: handle the formals.
        
        transType(c, term, xc);

        return c;
	}
	
	public static XTerm translate(XConstraint c, Receiver r, X10TypeSystem xts, X10Context xc) throws SemanticException {
		return xts.xtypeTranslator().trans(c, r, xc);
	}

	public static boolean isPureTerm(Term t) {
		boolean result = false;
		if (t instanceof Variable) {
			Variable v = (Variable) t;
			result = v.flags().isFinal();
		}
		return result;
	}

	public XLocal transThisWithoutTypeConstraint() {
	    XLocal v = XTerms.makeLocal(XTerms.makeName("this"));
	    return v;
	}
	
	public XLocal transThis(Type t) throws SemanticException {
	    XLocal v = transThisWithoutTypeConstraint();
		addTypeToEnv(v, t);
		return v;
	}

	public XRoot genEQV(XConstraint c, Type t) throws SemanticException {
		XRoot v = c.genEQV();
		addTypeToEnv(v, t);
		return v;
	}
	
	public XRoot genEQV(XConstraint c, Type t, boolean hidden) throws SemanticException {
		XRoot v = c.genEQV(hidden);
		addTypeToEnv(v, t);
		return v;
	}
	
	public XVar genEQV(XConstraint c, boolean hidden) throws SemanticException {
		XVar v = c.genEQV(hidden);

		return v;
	}

	public XConstraint binaryOp(Binary.Operator op, XConstraint cl, XConstraint cr) {
		return null;
	}
	
	public XConstraint unaryOp(Unary.Operator op, XConstraint ca) {
		return null;
	}
}
