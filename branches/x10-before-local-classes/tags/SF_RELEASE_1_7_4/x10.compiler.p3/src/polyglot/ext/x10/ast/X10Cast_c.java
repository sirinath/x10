/*
 *
 * (C) Copyright IBM Corporation 2006-2008
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import polyglot.ast.Block;
import polyglot.ast.Call;
import polyglot.ast.Cast;
import polyglot.ast.Cast_c;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassMember;
import polyglot.ast.ConstructorCall;
import polyglot.ast.Eval;
import polyglot.ast.Expr;
import polyglot.ast.Formal;
import polyglot.ast.Local;
import polyglot.ast.LocalDecl;
import polyglot.ast.MethodDecl;
import polyglot.ast.New;
import polyglot.ast.New_c;
import polyglot.ast.Node;
import polyglot.ast.Precedence;
import polyglot.ast.Special;
import polyglot.ast.Stmt;
import polyglot.ast.TypeNode;
import polyglot.ext.x10.types.ParameterType;
import polyglot.ext.x10.types.TypeProperty;
import polyglot.ext.x10.types.X10ClassDef;
import polyglot.ext.x10.types.X10ClassType;
import polyglot.ext.x10.types.X10ConstructorDef;
import polyglot.ext.x10.types.X10Def;
import polyglot.ext.x10.types.X10Flags;
import polyglot.ext.x10.types.X10LocalDef;
import polyglot.ext.x10.types.X10MethodInstance;
import polyglot.ext.x10.types.X10TypeMixin;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.types.ClassDef;
import polyglot.types.ConstructorDef;
import polyglot.types.ConstructorInstance;
import polyglot.types.ErrorRef_c;
import polyglot.types.Flags;
import polyglot.types.LocalInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Name;
import polyglot.types.ObjectType;
import polyglot.types.Package;
import polyglot.types.Ref;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.CollectionUtil;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeCheckPreparer;
import polyglot.visit.TypeChecker;
import x10.constraint.XConstraint;

/**
 * Represent java cast operation.
 * (CastType) expression
 * This class is compliant with dependent type constraint.
 * If a dynamic cast is needed, then some code is generated to check 
 * instance's value, field, etc... are valid against declared type constraint.
 *
 * @author vcave
 *
 */
public class X10Cast_c extends Cast_c implements X10Cast, X10CastInfo {
    protected ConversionType convert;

    public X10Cast_c(Position pos, TypeNode castType, Expr expr, ConversionType convert) {
        super(pos, castType, expr);
        this.convert = convert;
    }

    public ConversionType conversionType() {
        return convert;
    }

    public X10Cast conversionType(ConversionType convert) {
        X10Cast_c n = (X10Cast_c) copy();
        n.convert = convert;
        return n;
    }

    @Override
    public Precedence precedence() {
        switch (convert) {
        case PRIMITIVE:
        case SUBTYPE:
            return Precedence.CAST;
        default:
            return Precedence.UNKNOWN;
        }
    }

    public static <T extends Node> T check(T n, ContextVisitor tc) throws SemanticException {
        return (T) n.del().disambiguate(tc).del().typeCheck(tc).del().checkConstants(tc);
    }

    /** Return list of conversion functions needed to convert from fromType to toType */
    public Expr converterChain(final ContextVisitor tc) throws SemanticException {
        try {
            return checkCast(tc);
        }
        catch (SemanticException e) {
        }

        X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
        final X10NodeFactory nf = (X10NodeFactory) tc.nodeFactory();

        class Helper {
            Expr attempt(X10ClassType ct, int i, List<Type>[] alternatives, Type fromType, List<Type> accum, Type toType, boolean changed) throws SemanticException {
                assert alternatives.length == accum.size();

                if (i < alternatives.length) {
                    try {
                        accum.set(i, ct.typeArguments().get(i));
                        return attempt(ct, i+1, alternatives, fromType, accum, toType, changed);
                    }
                    catch (SemanticException e) {
                    }
                    for (Type ti : alternatives[i]) {
                        try {
                            accum.set(i, ti);
                            return attempt(ct, i+1, alternatives, fromType, accum, toType, true);
                        }
                        catch (SemanticException e) {
                        }
                    }
                }
                else if (changed) {
                    X10ClassType ct2 = ct.typeArguments(accum);
                    Type newFrom = X10TypeMixin.xclause(X10TypeMixin.baseType(ct2), X10TypeMixin.xclause(fromType));
                    if (fromType.typeEquals(newFrom)) {
                        assert false;
                    }
                    if (newFrom.isSubtype(toType))
                        return X10Cast_c.this.expr();
                    X10Cast_c newCast = (X10Cast_c) nf.X10Cast(position(), nf.CanonicalTypeNode(position(), newFrom), X10Cast_c.this.expr(), ConversionType.UNKNOWN_IMPLICIT_CONVERSION);
                    Expr newE = newCast.converterChain(tc);
                    assert newE.type() != null;
                    X10Cast_c newC = (X10Cast_c) X10Cast_c.this.expr(newE);
                    return newC.checkCast(tc);
                }

                throw new SemanticException("Cannot convert from " + fromType + " to " + toType + ".", position());
            }

            void addSuperTypes(List<Type> l, Type t) {
                Type b = X10TypeMixin.baseType(t);
                if (! b.typeSystem().typeEquals(b, t)) {
                    l.add(b);
                }
                else
                    if (t instanceof ObjectType) {
                        ObjectType o = (ObjectType) t;
                        if (o.superClass() != null) {
                            l.add(o.superClass());
                            //                        addSuperTypes(l, o.superClass());
                        }
                        for (Type ti : o.interfaces()) {
                            l.add(ti);
                            //                        addSuperTypes(l, ti);
                        }
                    }
            }
        }

        Type fromType = expr.type();
        Type toType = castType.type();

        // If the fromType has a covariant parameter,
        // try supertypes of the corresponding argument type.
        Type baseFrom = X10TypeMixin.baseType(fromType);

        if (baseFrom instanceof X10ClassType) {
            X10ClassType ct = (X10ClassType) baseFrom;
            if (ct.typeArguments().size() > 0) {
                List<Type>[] alternatives = new List[ct.typeArguments().size()];
                List<Type> newArgs = new ArrayList<Type>(ct.typeArguments().size());
                for (int i = 0; i < ct.typeArguments().size(); i++) {
                    TypeProperty.Variance v = ct.x10Def().variances().get(i);
                    Type ti = ct.typeArguments().get(i);
                    switch (v) {
                    case COVARIANT:
                        alternatives[i] = new ArrayList<Type>();
                        new Helper().addSuperTypes(alternatives[i], ti);
                        break;
                    default:
                        alternatives[i] = Collections.EMPTY_LIST;
                    break;                              
                    }
                }

                // Now, try all possible combinations of the alternative type arguments.
                try {
                    return new Helper().attempt(ct, 0, alternatives, fromType, new ArrayList<Type>(ct.typeArguments()), toType, false);
                }
                catch (SemanticException e) {
                    // Fall through.
                }
            }
        }

        String c = convert == ConversionType.UNKNOWN_CONVERSION ? "cast" : "implicitly convert";

        throw new SemanticException("Cannot " + c + " expression of type \"" 
                                    + fromType + "\" to type \"" 
                                    + toType + "\".",
                                    position());
    }

    public Node typeCheck(ContextVisitor tc) throws SemanticException {
        X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
        //        if (ts.isBox(castType.type()) && expr.type() instanceof ParameterType)
        //            System.out.println(this);
        Expr e = converterChain(tc);
        assert e.type() != null;
        assert ! (e instanceof X10Cast_c) || ((X10Cast_c) e).convert != ConversionType.UNKNOWN_CONVERSION;
        assert ! (e instanceof X10Cast_c) || ((X10Cast_c) e).convert != ConversionType.UNKNOWN_IMPLICIT_CONVERSION;
        return e;
    }

    public Expr checkCast(ContextVisitor tc) throws SemanticException {
        Type toType = castType.type();
        Type fromType = expr.type();
        X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
        X10NodeFactory nf = (X10NodeFactory) tc.nodeFactory();

        if (ts.isVoid(toType) || ts.isVoid(fromType))
            throw new SemanticException("Cannot cast from " + toType + " to " + fromType + ".", position());

        if (ts.isSubtype(fromType, toType)) {
            X10Cast_c n = (X10Cast_c) copy();
            n.convert = ConversionType.SUBTYPE;
            return n.type(toType);
        }

        Type baseFrom = X10TypeMixin.baseType(fromType);
        Type baseTo = X10TypeMixin.baseType(toType);
        XConstraint cFrom = X10TypeMixin.xclause(fromType);
        XConstraint cTo = X10TypeMixin.xclause(toType);

        if (convert != ConversionType.UNKNOWN_IMPLICIT_CONVERSION) {
            if (! ts.isParameterType(fromType) && ts.isCastValid(fromType, toType)) {
                X10Cast_c n = (X10Cast_c) copy();
                n.convert = ConversionType.CHECKED;
                return n.type(toType);
            }

            if (ts.isBoolean(fromType) && ts.isBoolean(toType)) {
                if (cFrom == null || cTo == null || ts.clausesConsistent(cFrom, cTo)) {
                    X10Cast_c n = (X10Cast_c) copy();
                    n.convert = ConversionType.PRIMITIVE;
                    return n.type(toType);
                }
            }

            if (ts.isNumeric(fromType) && ts.isNumeric(toType)) {
                if (cFrom == null || cTo == null || ts.clausesConsistent(cFrom, cTo)) {
                    X10Cast_c n = (X10Cast_c) copy();
                    n.convert = ConversionType.PRIMITIVE;
                    return n.type(toType);
                }
            }

            if (ts.isChar(fromType) && ts.isChar(toType)) {
                if (cFrom == null || cTo == null || ts.clausesConsistent(cFrom, cTo)) {
                    X10Cast_c n = (X10Cast_c) copy();
                    n.convert = ConversionType.PRIMITIVE;
                    return n.type(toType);
                }
            }
        }
        else {
            if (ts.isNumeric(fromType) && ts.isNumeric(toType)) {
                if (ts.isImplicitNumericCastValid(fromType, toType)) {
                    X10Cast_c n = (X10Cast_c) copy();
                    n.convert = ConversionType.PRIMITIVE;
                    return n.type(toType);
                }
            }
        }

        {
            MethodInstance converter = null;

            // Can convert if there is a static method toType.$convert(fromType)
            if (converter == null) {
                try {
                    MethodInstance mi = ts.findMethod(toType, ts.MethodMatcher(toType, Name.make("$convert"), Collections.singletonList(fromType)), (ClassDef) null);
                    Type baseMiType = X10TypeMixin.baseType(mi.returnType());
                    if (mi.flags().isStatic() && baseMiType.isSubtype(baseTo)) {
                        converter = mi;
                    }
                }
                catch (SemanticException e) {
                }
            }

            if (converter != null) {
                MethodInstance mi = converter;
                Position p = position();
                Expr e = expr;

                // Do the conversion.
                Call c = nf.Call(p, nf.CanonicalTypeNode(p, toType), nf.Id(p, mi.name()), e);
                c = c.methodInstance(mi);
                c = (Call) c.type(mi.returnType());

                // Now, do a coercion if needed to check any additional constraints on the type.
                if (! ts.isParameterType(fromType) && ! mi.returnType().isSubtype(toType)) {
                    X10Cast_c n = (X10Cast_c) copy();
                    n.expr = c;
                    n.convert = ConversionType.CHECKED;
                    return n.type(toType);
                }
                else {
                    return c;
                }
            }
        }

        if (convert != ConversionType.UNKNOWN_IMPLICIT_CONVERSION) {
            if (ts.isParameterType(fromType) && ts.isCastValid(fromType, toType)) {
                X10Cast_c n = (X10Cast_c) copy();
                n.convert = ConversionType.CHECKED;
                return n.type(toType);
            }
        }

        //          if (ts.isValueType(fromType) && ts.isReferenceOrInterfaceType(toType)) {
        //          if (ts.isSubtypeWithValueInterfaces(fromType, toType, Collections.EMPTY_LIST)) {
        //              Expr boxed = wrap(expr, toType, tc);
        //              return boxed;
        //          }
        //      }

        Type boxOfTo = ts.boxOf(Types.ref(toType));
        Type boxOfFrom = ts.boxOf(Types.ref(fromType));

        // v: Ref
        // v as Value
        // ->
        // (v as Box[Ref]).value as Value
        if (ts.isReferenceOrInterfaceType(fromType) && (ts.isValueType(toType) || ts.isParameterType(toType))) {
            Expr boxed = expr;
            if (! ts.typeEquals(fromType, boxOfTo)) {
                boxed = check(nf.X10Cast(position(), nf.CanonicalTypeNode(position(), boxOfTo), expr, convert), tc);
                return check(nf.X10Cast(position(), nf.CanonicalTypeNode(position(), toType), boxed, convert), tc);
            }
        }

        if (convert != ConversionType.UNKNOWN_IMPLICIT_CONVERSION && ts.typeEquals(fromType, boxOfTo)) {
            //            System.out.println("UNBOXING " + expr + " from " + fromType + " to " + toType);
            Expr unboxed = check(nf.Field(position(), expr, nf.Id(position(), Name.make("value"))), tc);
            return check(nf.X10Cast(position(), nf.CanonicalTypeNode(position(), toType), unboxed, convert), tc);
        }

        // v to I, where I is not a value interface (i.e., a function type)
        if (ts.isParameterType(fromType) && ts.typeBaseEquals(toType, ts.Object())) {
            if (ts.isSubtypeWithValueInterfaces(fromType, toType, Collections.EMPTY_LIST)) {
                //                TypeBuilder tb = new TypeBuilder(tc.job(), ts, nf);
                //                tb = tb.pushPackage(tc.context().package_());
                //                tb = tb.pushClass(tc.context().currentClassDef());
                //                tb = tb.pushCode(tc.context().currentCode());
                //                
                //                TypeCheckPreparer sr = new TypeCheckPreparer(tc.job(), ts, nf, tc instanceof TypeChecker ? ((TypeChecker) tc).memo() : new HashMap<Node, Node>());
                //                sr = (TypeCheckPreparer) sr.context(tc.context());

                X10New_c boxed = (X10New_c) nf.X10New(position(), nf.CanonicalTypeNode(position(), Types.ref(boxOfFrom)), Collections.EMPTY_LIST, Collections.singletonList(expr));

                ConstructorInstance ci = ts.createConstructorInstance(position(), new ErrorRef_c<ConstructorDef>(ts, position(), "Cannot get ConstructorDef before type-checking new expression."));
                boxed = (X10New_c) boxed.constructorInstance(ci);

                return check(boxed, tc);
            }
        }

        // v to I, where I is not a value interface (i.e., a function type)
        if ((ts.isValueType(fromType) || ts.isParameterType(fromType)) && ts.isInterfaceType(toType) && ! ts.isValueType(toType)) {
            if (ts.isSubtypeWithValueInterfaces(fromType, toType, Collections.EMPTY_LIST)) {
                return new X10Boxed_c(position(), castType, expr, ConversionType.BOXING).type(toType);
            }
        }

        if (convert != ConversionType.UNKNOWN_IMPLICIT_CONVERSION) {
            if (ts.isParameterType(fromType) || ts.isParameterType(toType)) {
                X10Cast_c n = (X10Cast_c) copy();
                n.convert = ConversionType.CHECKED;
                return n.type(toType);
            }
        }

        throw new SemanticException("Cannot convert expression of type \"" 
                                    + fromType + "\" to type \"" 
                                    + toType + "\".",
                                    position());
    }

    public TypeNode getTypeNode() {
        return (TypeNode) this.castType().copy();
    }

    public String toString() {
        return expr.toString() + " as " + castType.toString();
    }

    @Override
    public List<Type> throwTypes(TypeSystem ts) {
        // 'e as T' and 'e to T' can throw ClassCastException
        return super.throwTypes(ts);
    }
}
