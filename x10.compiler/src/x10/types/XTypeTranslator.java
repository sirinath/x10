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

package x10.types;

import java.util.ArrayList;
import java.util.List;

import polyglot.ast.Binary;
import polyglot.ast.Binary.Operator;
import polyglot.ast.Call;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.Formal;
import polyglot.ast.Lit;
import polyglot.ast.Local;
import polyglot.ast.Receiver;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.ast.Typed;
import polyglot.ast.Unary;
import polyglot.ast.Variable;
import polyglot.types.ClassType;
import polyglot.types.Context;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.LocalDef;
import polyglot.types.LocalInstance;
import polyglot.types.MethodDef;
import polyglot.types.QName;
import polyglot.types.Ref;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.InternalCompilerError;
import x10.ast.HasZeroTest;
import x10.ast.Here;
import x10.ast.Here_c;
import x10.ast.IsRefTest;
import x10.ast.ParExpr;
import x10.ast.SubtypeTest;
import x10.ast.Tuple;
import x10.ast.X10Cast;
import x10.ast.X10Special;
import x10.constraint.XDef;
import x10.constraint.XExpr;
import x10.constraint.XFailure;
import x10.constraint.XLabeledOp;
import x10.constraint.XLit;
import x10.constraint.XOp;
import x10.constraint.XStringDef;
import x10.constraint.XTerm;
import x10.constraint.XUQV;
import x10.constraint.XVar;
import x10.constraint.xnative.XNativeField;
import x10.errors.Errors;
import x10.errors.Errors.IllegalConstraint;
import x10.types.checker.PlaceChecker;
import x10.types.constants.ClosureValue;
import x10.types.constants.ConstantValue;
import x10.types.constraints.CConstraint;
import x10.types.constraints.CLocal;
import x10.types.constraints.CQualifiedVar;
import x10.types.constraints.ConstraintManager;
import x10.types.constraints.QualifierDef;
import x10.types.constraints.SubtypeConstraint;
import x10.types.constraints.TypeConstraint;
import x10.types.constraints.XConstrainedTerm;

/**
 * This is the bridge from Expr or TypeNode to a CConstraint. The CConstraint
 * generated may keep a reference to type objects obtained from the Expr or TypeNode.
 *  
 * @author nystrom
 * @author vj
 */
public class XTypeTranslator {

	private final TypeSystem ts;

    public XTypeTranslator(TypeSystem xts) {
        ts = xts;
    }
    
    /**
     * Translate the given AST term to an XTerm<Type> using information in the constraint
     * to resolve self, and using the context to determine AST type information 
     * (e.g. for Specials).
     * @param c --- a constraint used for context, e.g. self information. If term
     * is a this (e.g. this, Foo.this etc), then the constraint's this Var is set
     * on return. 
     * @param term -- the term to be translated
     * @param xc -- the context in which the term is to be translated
     * @return null if the translation is not possible. Caller must always check.
     * FIX: Remove the need for the constraint to be passed into translate.
     * 
     * If toplevel is true, then boolean connectives, && are permitted.
     */
    public XTerm<Type> translate(CConstraint c, Receiver term, Context xc) throws IllegalConstraint {
    	return translate(c, term, xc, false);
    }
    public XTerm<Type> translate(CConstraint c, Receiver term, Context xc, boolean tl) throws IllegalConstraint {
        if (term == null)
            return null;
        
        if (term instanceof Lit) {
        	Object val = ConstantValue.toJavaObject(((Lit)term).constantValue());
            return ConstraintManager.getConstraintSystem().makeLit(term.type(), val);
        } else if (term instanceof Here_c) {
        	return xc.currentPlace(term.type().typeSystem());
        } else if (term instanceof Field) {
		    Field t = (Field) term;
			XTerm<Type> receiver = translate(c, t.target(), xc, tl);
			if (receiver == null)
			    return null;
			return translate(receiver, t.fieldInstance(), tl);
        } else if (term instanceof X10Special) {
			return translateSpecialAsQualified(c,(X10Special)term,xc);
        } else if (term instanceof Local) {
		    return translate(((Local) term).localInstance());
		} else if (term instanceof Expr && ts.isUnknown(term.type())) {
            return null;
        } else if (term instanceof Expr && ((Expr)term).isConstant() && !(((Expr)term).constantValue() instanceof ClosureValue)) {
            Object val = ConstantValue.toJavaObject(((Expr)term).constantValue());
            return ConstraintManager.getConstraintSystem().makeLit(term.type(), val);
        } else if (term instanceof X10Cast) {
            X10Cast cast = ((X10Cast) term);
            return translate(c, cast.expr().type(cast.type()), xc, tl);
        } else if (term instanceof Call) {
            return trans(c, (Call) term, xc, tl);
        } else if (term instanceof Tuple) {
            return trans(c, (Tuple) term, xc, tl);
        } else if (term instanceof Unary) {
            Unary u = (Unary) term;
            Expr t2 = u.expr();
            Unary.Operator op = u.operator();
            if (op != Unary.POS) return null; // no other unary operator supported
            return translate(c, t2, xc, tl);
        } else if (term instanceof Binary) {
            return trans(c, (Binary) term, xc, tl);
        } else if (term instanceof TypeNode) {
			return translate(((TypeNode) term).type());
        } else if (term instanceof ParExpr) {
            return translate(c, ((ParExpr) term).expr(), xc, tl);
        } else {
        	return null;
        }
    }


    /**
     * Return the term target.field, where information about the field is obtained from fi.
     * The returned term carriers a reference to fi.def(), and field is fi.name().toString().
     * Note that if target is a formula f, then the atom field(f) is returned instead.
     * @param target
     * @param fi
     * @return
     */
    public XTerm<Type> translate(XTerm<Type> target, FieldInstance fi) {
    	return translate(target, fi, false);
    }
    XTerm<Type> translate(XTerm<Type> target, FieldInstance fi, boolean ignore) {
        if (fi == null)
            return null;
        try {
            if (fi.flags().isStatic()) {
                Type container = Types.get(fi.def().container());
                container = Types.baseType(container);
                if (container instanceof X10ClassType) {
                    target = ConstraintManager.getConstraintSystem().makeLit(container, ((X10ClassType) container).fullName());
                }
                else {
                    throw new Errors.CannotTranslateStaticField(container, fi.position());
                }
            }
        	assert target != null;
            return ConstraintManager.getConstraintSystem().makeField(target, fi.def());
        } catch (SemanticException z) {
            return null;
        }
    }


    /**
     * Return target.prop, where information about the property is obtained from fi.
     * It must be the case that fi corresponds to a property that takes no arguments. 
     *  Note that if target is a formula f, then the atom prop(f) is returned instead.
     * TODO: Determine why the name cant be just fi.name().toString().
     * @param target
     * @param fi
     * @return
     */
    public XTerm<Type> translate(XTerm<Type> target, MethodInstance mi) {
        assert mi.flags().isProperty() && mi.formalTypes().size() == 0;
        //lshadare not sure this is right, look at CField vs CAtom differences
        return ConstraintManager.getConstraintSystem().makeField(target, mi.def());
    }
    
    static public XTerm<Type> expandSelfPropertyMethod(CConstraint c, XTerm<Type> term) {
        return expandPropertyMethod(c, term,false,null,null,null);
    }
    // todo: merge this code with Checker.expandCall and try to get rid of ts.expandMacros
    @SuppressWarnings("unchecked")
	static public XTerm<Type> expandPropertyMethod(CConstraint originalConst, XTerm<Type> term, boolean isThisOrSelf,
                        // these three formals help us search for a concrete implementation of the property method
                        // they can be null (then we don't search for an implementation)
                        TypeSystem ts, ClassType classType, Context context) {
        XDef<Type> aDef = null;
        List<? extends XTerm<Type>> args = null; // the first arg is the this-receiver
        if (term.isProjection()) {
        	XExpr<Type> exp = (XExpr<Type>)term; 
        	aDef = ((XLabeledOp<Type, XDef<Type>>)exp.op()).getLabel(); 
        	args = exp.children();
        }
        
        if (aDef==null || !(aDef instanceof X10MethodDef)) return term;
        XTerm<Type> receiver = args.get(0);
        if (isThisOrSelf) {
            // for methods (checking overriding) we replace "this.p(...)"
            if (!(receiver.isUQVOfName("this"))) return term;
        } else {
            // for subtyping tests we replace "self.p(...)"
        	if (!receiver.equals(originalConst.self())) return term;
        }
        X10MethodDef def = (X10MethodDef) aDef;
        if (classType!=null) {
            // find the correct def, and return a clone of the XTerm
            final MethodInstance method = ts.findImplementingMethod(classType, def.asInstance(), false, context);
            if (method==null) // the property is abstract in t1
                return term;
            def = (X10MethodDef) method.def();
        }
        final Ref<XTerm<Type>> bodyRef = def.body();
        if (bodyRef==null)
            return term;
        XTerm<Type> body = bodyRef.get();
        if (body==null)
            return term;
        // currently we only support nullary property methods that are not CAtoms
        List<LocalDef> formals = def.formalNames();
        if (formals.size()!=args.size()-1)
            throw new InternalCompilerError("The number of arguments in the property method didn't match the property defintiion.");
        int pos=1;
        for (LocalDef formal : formals) {
            XVar<Type> x =  ConstraintManager.getConstraintSystem().makeLocal((X10LocalDef)formal);
            XTerm<Type> y = args.get(pos++);
            body = body.subst(ConstraintManager.getConstraintSystem(), y, x);
        }
        body = body.subst(ConstraintManager.getConstraintSystem(), receiver, def.thisVar());
        return body;
    }

    /**
     * A fake field is one which exists purely for compilation purposes
     * and has no run-time existence. The main example is "home". It used to exist
     * for all objects, but is now used merely to track the location of the current
     * object statically.
     * @param target
     * @param name
     * @return
     */
    public XTerm<Type> translateFakeField(XTerm<Type> target, String name, Type t)  {
        return ConstraintManager.getConstraintSystem().makeField(target, new XStringDef<Type>(name,t),true);
    }

    /** 
     * Return an XLocal which contains a reference to the type object li.def(),
     * and whose name is li.name().
     * @param li
     * @return
     */
     public CLocal translate(LocalInstance li) {
        return ConstraintManager.getConstraintSystem().makeLocal((X10LocalDef) li.def());
       
    }
    
    /**
     * Translate a type t into an XTerm. A type parameter
     * is translated into an XLocal. Other types are converted
     * into XTypeLit_c.
     * @param t
     * @return
     */
    public XTerm<Type> translate(Type t) {
        if (t instanceof ParameterType)
            return translateTypeParam((ParameterType) t);
        if (t instanceof MacroType) {
            MacroType pt = (MacroType) t;
            return translate(pt.definedType());
        }
        return ConstraintManager.getConstraintSystem().makeTypeLit(t);
    }
    
    public XUQV<Type> translateTypeParam(ParameterType t) {
        return ConstraintManager.getConstraintSystem().makeUQV(Types.baseTypeRec(t), t.toString()); 
    }

    
    /**
     * Translate into the literal t.
     * @param t
     * @param ts TODO
     * @return
     */
    public static XLit<Type, Integer> translate(int t, TypeSystem ts) {
        return ConstraintManager.getConstraintSystem().makeLit(ts.Int(), t);
    }

    /**
     * Translate into the literal t.
     * @param t
     * @param ts TODO
     * @return
     */
    public static XLit<Type, Boolean> translate(boolean t, TypeSystem ts) {
        return ConstraintManager.getConstraintSystem().makeLit(ts.Boolean(), t);
    }

    /**
     * Return the XLit<Type,> representing null.
     * @param ts TODO
     * @return
     */
    public static XLit<Type, ? extends Object> transNull(TypeSystem ts) {
        return ConstraintManager.getConstraintSystem().xnull(ts);
    }

    /**
     * Translate an expression into a CConstraint, throwing SemanticExceptions 
     * if this is not possible.
     * This must be called after type-checking of Expr.
     * @param formals TODO
     * @param term
     * @param xc TODO
     * @param c
     * @return
     * @throws SemanticException
     */
    public CConstraint constraint(List<Formal> ignore, Expr term, Context xc) throws SemanticException {
        CConstraint c = ConstraintManager.getConstraintSystem().makeCConstraintNoSelf(xc.typeSystem());
        if (term == null)
            return c;

        if (!term.type().isBoolean())
            throw new SemanticException("Cannot build constraint from expression since" 
            		+ term + " (of type " + term.type() + ") is not a boolean.", term.position());

        // TODO: handle the formals.
        XTerm<Type> t= translate(c, term, xc, true);
        

        if (t == null)
            throw new SemanticException("Cannot build constraint from expression " + term + " (translate() returned null).", term.position());

        try {
            c.addTerm(t);
        } catch (XFailure e) {
            c.setInconsistent();
        }
        
        return c;
    }

    public TypeConstraint typeConstraint(List<Formal> ignore, Expr term, Context xc) throws SemanticException {
        TypeConstraint c = new TypeConstraint();
        if (term == null)
            return c;

        if (!term.type().isBoolean())
            throw new SemanticException("Cannot build constraint from expression since" 
            		+ term + " (of type " + term.type() + ") is not a boolean.", term.position());

        // TODO: handle the formals.
        transType(c, term, xc);

        return c;
    }

    public CConstraint binaryOp(Binary.Operator op, CConstraint cl, CConstraint cr) {
        return null; // none supported
    }

    public CConstraint unaryOp(Unary.Operator op, CConstraint ca) {
        return null; // none supported
    }
    
    
    
    // *********************************************************************************************
    // *********************************** private help routines for translation********************

    private void transType(TypeConstraint c, Binary t, Context xc) throws SemanticException {
        Expr left = t.left();
        Expr right = t.right();
        XTerm<Type> v;

        if (t.operator() == Binary.COND_AND 
                || (t.operator() == Binary.BIT_AND 
                		&& ts.isImplicitCastValid(t.type(), ts.Boolean(), xc))) {
            transType(c, left, xc);
            transType(c, right, xc);
        }
        else {
            throw new SemanticException("Cannot translate " + t 
            		+ " into a type constraint.", t.position());
        }
    }

    private void transType(TypeConstraint c, Expr t, Context xc) throws SemanticException {
        if (t instanceof Binary) {
            transType(c, (Binary) t, xc);
        }
        else if (t instanceof ParExpr) {
            transType(c, ((ParExpr) t).expr(), xc);
        }
        else if (t instanceof SubtypeTest) {
            transType(c, (SubtypeTest) t, xc);
        } else if (t instanceof HasZeroTest) {
            transType(c, (HasZeroTest) t, xc);
	    } else if (t instanceof IsRefTest) {
	        transType(c, (IsRefTest) t, xc);
	    }
        else {
            throw new SemanticException("Cannot translate " + t 
            		+ " into a type constraint.", t.position());
        }
    }


    private void transType(TypeConstraint c, HasZeroTest t, Context xc) throws SemanticException {
        TypeNode left = t.parameter();
        c.addTerm(new SubtypeConstraint(left.type(), null, SubtypeConstraint.Kind.HASZERO));
    }
    private void transType(TypeConstraint c, IsRefTest t, Context xc) throws SemanticException {
        TypeNode left = t.parameter();
        c.addTerm(new SubtypeConstraint(left.type(), null, SubtypeConstraint.Kind.ISREF));
    }
    private void transType(TypeConstraint c, SubtypeTest t, Context xc) throws SemanticException {
        TypeNode left = t.subtype();
        TypeNode right = t.supertype();
        c.addTerm(new SubtypeConstraint(left.type(), right.type(), t.equals()));
    }

    private XTerm<Type> simplify(Binary rb, XTerm<Type> v) {
        XTerm<Type> result = v;
        Expr r1 = rb.left();
        Expr r2  = rb.right();

        // Determine if their types force them to be equal or disequal.

        CConstraint c1 = Types.xclause(r1.type()).copy();
        XVar<Type> x = ConstraintManager.getConstraintSystem().makeUQV(Types.baseTypeRec(r1.type()));
        c1.addSelfEquality(x);
        CConstraint c2 = Types.xclause(x, r2.type()).copy();
        if (rb.operator()== Binary.EQ) {
            c1.addIn(c2);
            if (! c1.consistent())
                result = ConstraintManager.getConstraintSystem().xfalse(r1.type().typeSystem());
            if (c1.entails(c2) && c2.entails(c1)) {
                result = ConstraintManager.getConstraintSystem().xtrue(r1.type().typeSystem());
            }
        }
        return result;
    }

    
    private XTerm<Type> trans(CConstraint c, Binary t, Context xc, boolean tl) throws IllegalConstraint {
        Expr left = t.left();
        Expr right = t.right();
        XTerm<Type> v = null;
      
        Operator op = t.operator();
        XTerm<Type> lt = translate(c, left, xc, op==Binary.COND_AND); // Not top-level, unless op==&&
        XTerm<Type> rt = translate(c, right, xc,op==Binary.COND_AND); // Not top-level, unless op==&&
        if (lt == null || rt == null)
            return null;
        if (op == Binary.EQ || op == Binary.NE) {
            if (right instanceof ParExpr) {
                right = ((ParExpr)right).expr();
            }
            if (right instanceof Binary && ((Binary) right).operator() == Binary.EQ) {
                rt = simplify((Binary) right, rt);
            }
            if (left instanceof Binary && ((Binary) right).operator() == Binary.EQ) {
                lt = simplify((Binary) left, lt);
            }
        	if (! tl)
        		throw new IllegalConstraint(t);
            v = op == Binary.EQ ? ConstraintManager.getConstraintSystem().makeEquals(xc.typeSystem(), lt, rt): 
            					  ConstraintManager.getConstraintSystem().makeDisEquals(xc.typeSystem(), lt, rt);
        }
        else if (op == Binary.COND_AND 
                || (op == Binary.BIT_AND && ts.isImplicitCastValid(t.type(), ts.Boolean(), xc))) {
        	if (! tl)
        		throw new IllegalConstraint(t);
        	v = ConstraintManager.getConstraintSystem().makeAnd(xc.typeSystem(), lt, rt);
        }
        else  {
            //v = ConstraintManager.getConstraintSystem().makeAtom(t.operator(), lt, rt);
            throw new IllegalConstraint(t);
           // return null;
        }
        return v;
    }

    private XTerm<Type> trans(CConstraint c, Tuple t, Context xc, boolean tl) throws IllegalConstraint {
        List<XTerm<Type>> terms = new ArrayList<XTerm<Type>>();
        for (Expr e : t.arguments()) {
            XTerm<Type> v = translate(c, e, xc, tl);
            if (v == null)
                return null;
            terms.add(v);
        }
        return ConstraintManager.getConstraintSystem().makeExpr(XOp.makeLabelOp(new XStringDef<Type>("tuple", t.type())), terms);
    }

	private Type getType(TypeSystem ts, String name) throws SemanticException {
		return ts.systemResolver().findOne(QName.make(name));
	}

	private String nodeHasOpaqueAnnotation(TypeSystem ts, MethodDef d) {
		List<Type> anns;
		try {
			anns = d.annotationsMatching(getType(ts, "x10.compiler.Opaque"));
		} catch (SemanticException e) {
			// in case Opaque.x10 does not exist
			return null;
		}
		if (anns.size() == 0) return null;
		Type the_ann = anns.get(0);
		X10ClassType the_ann2 = the_ann.toClass();
	
        Expr e = the_ann2.propertyInitializer(0);
        if (e.isConstant()) {
            return (String) ConstantValue.toJavaObject(e.constantValue());
        }
	    return null;
	}
	
/**
     * This used to be a key routine that contained special code for handling at constraints.
     * It translates a call t into what the body of the called method would translate to,
     * assuming that the method represents a property.
     * @param c
     * @param t
     * @param xc
     * @return
     */
    private XTerm<Type> trans(CConstraint c, Call t, Context xc, boolean tl) throws IllegalConstraint {
        MethodInstance xmi = (MethodInstance) t.methodInstance();
        Flags f = xmi.flags();
        if (f.isProperty()) {
            XTerm<Type> r = translate(c, t.target(), xc, tl);
            if (r == null)
                return null;
            // FIXME: should just return the atom, and add atom==body to the real clause of the class
            // FIXME: fold in class's real clause constraints on parameters into real clause of type parameters
            XTerm<Type> body = xmi.body();
            if (body != null) {
                if (xmi.x10Def().thisVar() != null && t.target() instanceof Expr) {
                    //XName This = ConstraintManager.getConstraintSystem().makeName(new Object(), Types.get(xmi.def().container()) + "#this");
                    //body = body.subst(r, ConstraintManager.getConstraintSystem().makeLocal(This));
                    body = body.subst(ConstraintManager.getConstraintSystem(), r, xmi.x10Def().thisVar());
                }
                if ((! tl) && ! (body.okAsNestedTerm()))
                	throw new IllegalConstraint(t, body, t.position());
                for (int i = 0; i < t.arguments().size(); i++) {
                    //XVar<Type> x = (XVar) X10TypeMixin.selfVarBinding(xmi.formalTypes().get(i));
                    //XVar<Type> x = (XVar) xmi.formalTypes().get(i);
                    XVar<Type> x =  ConstraintManager.getConstraintSystem().makeLocal((X10LocalDef) xmi.def().formalNames().get(i));  // we get the def first because the formalNames were renamed in Matcher.instantiate, see XTENLANG-2582
                    XTerm<Type> y = translate(c, t.arguments().get(i), xc, tl);
                    if (y == null)
                        assert y != null : "XTypeTranslator: translation of arg " + i + " of " + t + " yields null (pos=" 
                        + t.position() + ")";
                    body = body.subst(ConstraintManager.getConstraintSystem(), y, x);
                }
                return body;
            } else {
            	String n = nodeHasOpaqueAnnotation(xc.typeSystem(), xmi.def());
            	if (n != null) {
	            	System.out.println("making atom \""+n+"\" for "+t);
	            	// translating arguments
	            	List<XTerm<Type>> args = new ArrayList<XTerm<Type>>(); 
	            	for (Expr arg : t.arguments()) {
	            		XTerm<Type> x = translate(c, arg, xc, tl); 
	            		if (x== null)
	            			throw new IllegalConstraint(t); 
	            		args.add(x);
	            	}
	            	boolean isatom = xmi.returnType().isBoolean();
	            	return ConstraintManager.getConstraintSystem().makeOpaque(xmi.def().name(), isatom, r, args);
            	}
            }

            if (t.arguments().size() == 0) {
            	//lshadare not sure this is right
            	return ConstraintManager.getConstraintSystem().makeField(r, xmi.def());
            }
            
            if ((! tl))
               	throw new IllegalConstraint(t);
            List<XTerm<Type>> terms = new ArrayList<XTerm<Type>>();
            for (Expr e : t.arguments()) {
                XTerm<Type> v = translate(c, e, xc, tl);
                if (v == null)
                    return null;
                terms.add(v);
            }
            XTerm<Type> v = ConstraintManager.getConstraintSystem().makeMethod(xmi.def(), r, terms);
            return v;
        }
        Type type = t.type();
        return Types.selfVarBinding(type); // maybe null.
    }

	@SuppressWarnings("unchecked")
	public XTerm<Type> translateSpecialAsQualified(CConstraint c, X10Special t, Context xc0) {
        Context xc = xc0;
        if (t.kind() == X10Special.SELF) {
            if (c == null) {
                //throw new SemanticException("Cannot refer to self outside a dependent clause.");
                return null;
            }
            if (c.self() == null) {
            	// FIXME: this is done because sometimes we do not know the type the Constraint is associated to
            	XVar<Type> newself = ConstraintManager.getConstraintSystem().makeSelf(Types.baseType(t.type())); 
            	// no need to substitute as null could not have occured in the constraint
            	c.setSelf(newself);
            }
            XVar<Type> v = c.self();
            // Need to deal with qualified guy as well.
            TypeNode tn = t.qualifier();
            if (tn != null) {
                Type q = Types.baseType(tn.type());
                return ConstraintManager.getConstraintSystem().makeQualifiedVar(q, v);
            }
            return v;
        }

        if (xc0.inStaticContext()) {
            return null;
        }
        
        // this. why are we doing nothing about super..?
        //[DC] seems like super is useless in constraints, since we do not have virtual property methods
        XVar<Type> baseThisVar = null;
        for (Context outer = xc; outer != null && baseThisVar == null; outer = outer.pop()) {
            baseThisVar = outer.thisVar();
        }
        if (baseThisVar == null) {
            SemanticException e = new SemanticException("Cannot refer to |this| from the context " + xc);
            return null;
        }
       if (baseThisVar instanceof CQualifiedVar) {
			XNativeField<Type,QualifierDef> qVar = (XNativeField<Type,QualifierDef>) baseThisVar;
            if (qVar.field() == ((Typed) qVar.receiver()).type())
                baseThisVar = (XVar<Type>)qVar.receiver();
        }
        assert (baseThisVar instanceof XVar<?>);
        XTerm<Type> thisVar = baseThisVar;
        try {
            TypeNode tn = t.qualifier();
            if (tn == null) {
                return thisVar;
            }
            Type q = Types.baseType(tn.type());
            // So we need to translate A.this in a deptype.
            // The result should not be the this-associated-with-
            // the-outer-A in the context.
            // Rather it needs to be a QualifiedVar capturing
            // A as a qualifier. 
            // Return the qualified version of the base this.
            thisVar = (baseThisVar.type()==q)
            ? baseThisVar : ConstraintManager.getConstraintSystem().makeQualifiedVar(q, baseThisVar);
            return thisVar;
        } finally {
            if (c != null)
                c.setThisVar(baseThisVar);
        }
    }

}
