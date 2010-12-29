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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import polyglot.ast.AmbExpr;
import polyglot.ast.Binary;
import polyglot.ast.Binary_c;
import polyglot.ast.Call;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Prefix;
import polyglot.ast.Receiver;
import polyglot.ast.TypeNode;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import polyglot.types.Context;
import polyglot.types.Flags;
import polyglot.types.MethodDef;
import polyglot.types.Name;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.CollectionUtil;
import polyglot.util.InternalCompilerError;
import polyglot.util.Pair;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import x10.constraint.XFailure;
import x10.constraint.XLit;
import x10.constraint.XTerm;
import x10.constraint.XTerms;
import x10.errors.Errors;
import x10.types.MethodInstance;
import x10.types.X10TypeSystem_c;
import x10.types.checker.Checker;
import x10.types.checker.Converter;
import x10.types.checker.PlaceChecker;
import x10.types.constraints.BuiltInTypeRules;

/**
 * An immutable representation of a binary operation Expr op Expr.
 * Overridden from Java to allow distributions, regions, points and places to
 * participate in binary operations.
 *
 * @author vj Jan 21, 2005
 */
public class X10Binary_c extends Binary_c implements X10Binary {

    boolean invert;

    /**
     * @param pos
     * @param left
     * @param op
     * @param right
     */
    public X10Binary_c(Position pos, Expr left, Operator op, Expr right) {
        super(pos, left, op, right);
        invert = false;
    }

    // FIXME: need to figure out if the implementation is pure (can't assume that for user-overloadable operators)
    protected static boolean isPureOperation(Type left, Operator op, Type right) {
        switch (op) {
        case ADD:
        case SUB:
        case MUL:
            return true;
        case DIV:
        case MOD:
            return false; // division/modulus can throw an exception, thus is not pure
        case EQ:
        case NE:
        case GT:
        case LT:
        case GE:
        case LE:
            return true;
        case SHL:
        case SHR:
        case USHR:
            return true;
        case BIT_AND:
        case BIT_OR:
        case BIT_XOR:
            return true;
        case COND_AND:
        case COND_OR:
            return true;
        case ARROW:
        case DOT_DOT:
        case IN:
            return false;
        }
        return false;
    }

	public boolean isConstant() {
		if (super.isConstant() && isPureOperation(left.type(), op, right.type()))
			return true;
		// FIXME [IP] An optimization: an object of a non-nullable type and "null"
		// can never be equal.
		Type lt = left.type();
		Type rt = right.type();
		TypeSystem xts = (TypeSystem) lt.typeSystem();
		if (lt == null || rt == null)
			return false;
		return false;
	}

    // TODO: take care of the base cases for regions, distributions, points and places.
    public Object constantValue() {
        Object result = super.constantValue();
        if (result != null)
            return result;
        if (!isConstant())
            return null;
        
        Type lt = left.type();
        Type rt = right.type();
        TypeSystem xts = (TypeSystem) lt.typeSystem();
		Context context = (Context) xts.emptyContext();
		
		// [IP] An optimization: an value and null can never be equal
	
	 if (op == EQ) {
		    if (xts.isStructType(lt) && xts.isObjectOrInterfaceType(rt, context))
			return Boolean.FALSE;
		    if (xts.isObjectOrInterfaceType(lt, context) && xts.isStructType(rt))
			return Boolean.FALSE;
		    if ( xts.isStructType(lt) && rt.isNull())
			return Boolean.FALSE;
		    if ( xts.isStructType(rt) && lt.isNull())
			return Boolean.FALSE;
		}
		if (op == NE) {
		    if (xts.isStructType(lt) && xts.isObjectOrInterfaceType(rt, context))
			return Boolean.TRUE;
		    if (xts.isObjectOrInterfaceType(lt, context) && xts.isStructType(rt))
			return Boolean.TRUE;
		    if (xts.isStructType(lt) && rt.isNull())
			return Boolean.TRUE;
		    if (xts.isStructType(rt) && lt.isNull())
			return Boolean.TRUE;
		    return null;
		}
		
        return null;
    }

    /** If the expression was parsed as an ambiguous expression, return a Receiver that would have parsed the same way.  Otherwise, return null. */
    private static Receiver toReceiver(NodeFactory nf, Expr e) {
        if (e instanceof AmbExpr) {
            AmbExpr e1 = (AmbExpr) e;
            return nf.AmbReceiver(e.position(), null, e1.name());
        }
        if (e instanceof Field) {
            Field f = (Field) e;
            if (f.target() instanceof Expr) {
                Prefix p = toPrefix(nf, (Expr) f.target());
                if (p == null)
                    return null;
                return nf.AmbReceiver(e.position(), p, f.name());
            }
            else {
                return nf.AmbReceiver(e.position(), f.target(), f.name());
            }
        }
        return null;
    }

    /** If the expression was parsed as an ambiguous expression, return a Prefix that would have parsed the same way.  Otherwise, return null. */
    private static Prefix toPrefix(NodeFactory nf, Expr e) {
        if (e instanceof AmbExpr) {
            AmbExpr e1 = (AmbExpr) e;
            return nf.AmbPrefix(e.position(), null, e1.name());
        }
        if (e instanceof Field) {
            Field f = (Field) e;
            if (f.target() instanceof Expr) {
                Prefix p = toPrefix(nf, (Expr) f.target());
                if (p == null)
                    return null;
                return nf.AmbPrefix(e.position(), p, f.name());
            }
            else {
                return nf.AmbPrefix(e.position(), f.target(), f.name());
            }
        }
        return null;
    }

    // HACK: T1==T2 can sometimes be parsed as e1==e2.  Correct that.
    @Override
    public Node typeCheckOverride(Node parent, ContextVisitor tc) {
        if (op == EQ) {
            NodeFactory nf = (NodeFactory) tc.nodeFactory();
            Receiver t1 = toReceiver(nf, left);
            Receiver t2 = toReceiver(nf, right);

            if (t1 != null && t2 != null) {
                Node n1 = this.visitChild(t1, tc);
                Node n2 = this.visitChild(t2, tc);

                if (n1 instanceof TypeNode && n2 instanceof TypeNode) {
                    SubtypeTest n = nf.SubtypeTest(position(), (TypeNode) n1, (TypeNode) n2, true);
                    try {
                        n = (SubtypeTest) n.typeCheck(tc);
                    } catch (SemanticException e) {
                        throw new InternalCompilerError("Unexpected exception while typechecking "+n, position(), e);
                    }
                    return n;
                }
            }
        }

        return null;
    }

    public static Name binaryMethodName(Binary.Operator op) {
        Map<Binary.Operator,String> methodNameMap = new HashMap<Operator, String>();
        methodNameMap.put(ADD, "operator+");
        methodNameMap.put(SUB, "operator-");
        methodNameMap.put(MUL, "operator*");
        methodNameMap.put(DIV, "operator/");
        methodNameMap.put(MOD, "operator%");
        methodNameMap.put(BIT_AND, "operator&");
        methodNameMap.put(BIT_OR, "operator|");
        methodNameMap.put(BIT_XOR, "operator^");
        methodNameMap.put(COND_OR, "operator||");
        methodNameMap.put(COND_AND, "operator&&");
        methodNameMap.put(SHL, "operator<<");
        methodNameMap.put(SHR, "operator>>");
        methodNameMap.put(USHR, "operator>>>");
        methodNameMap.put(LT, "operator<");
        methodNameMap.put(GT, "operator>");
        methodNameMap.put(LE, "operator<=");
        methodNameMap.put(GE, "operator>=");
        methodNameMap.put(DOT_DOT, "operator..");
        methodNameMap.put(ARROW, "operator->");
        methodNameMap.put(IN, "operator in");

        String methodName = methodNameMap.get(op);
        if (methodName == null)
            return null;
        return Name.makeUnchecked(methodName);
    }

    public static final String INVERSE_OPERATOR_PREFIX = "inverse_";
    public static Name invBinaryMethodName(Binary.Operator op) {
        Name n = binaryMethodName(op);
        if (n == null)
            return null;
        return Name.makeUnchecked(INVERSE_OPERATOR_PREFIX + n.toString());
    }

    public static boolean isInv(Name name) {
        return name.toString().startsWith(INVERSE_OPERATOR_PREFIX);
    }
    
    private static Type promote(TypeSystem ts, Type t1, Type t2) {
        if (ts.isByte(t1)) {
            return t2;
        }
        if (ts.isShort(t1)) {
            if (ts.isByte(t2))
                return t1;
            return t2;
        }
        if (ts.isInt(t1)) {
            if (ts.isByte(t2) || ts.isShort(t2))
                return t1;
            return t2;
        }
        if (ts.isLong(t1)) {
            if (ts.isByte(t2) || ts.isShort(t2) || ts.isInt(t2))
                return t1;
            return t2;
        }

        if (ts.isUByte(t1)) {
            return t2;
        }
        if (ts.isUShort(t1)) {
            if (ts.isUByte(t2))
                return t1;
            return t2;
        }
        if (ts.isUInt(t1)) {
            if (ts.isUByte(t2) || ts.isUShort(t2))
                return t1;
            return t2;
        }
        if (ts.isULong(t1)) {
            if (ts.isUByte(t2) || ts.isUShort(t2) || ts.isUInt(t2))
                return t1;
            return t2;
        }

        if (ts.isFloat(t1)) {
            if (ts.isByte(t2) || ts.isShort(t2) || ts.isInt(t2) || ts.isLong(t2))
                return t1;
            if (ts.isUByte(t2) || ts.isUShort(t2) || ts.isUInt(t2) || ts.isULong(t2))
                return t1;
            return t2;
        }
        if (ts.isDouble(t1)) {
            return t1;
        }
        
        return null;
    }

    /**
     * Type check a binary expression. Must take care of various cases because
     * of operators on regions, distributions, points, places and arrays.
     * An alternative implementation strategy is to resolve each into a method
     * call.
     */
    public Node typeCheck(ContextVisitor tc) {
        TypeSystem xts = (TypeSystem) tc.typeSystem();
        Context context = (Context) tc.context();

        Type lbase = Types.baseType(left.type());
        Type rbase = Types.baseType(right.type());
        if (xts.hasUnknown(lbase) || xts.hasUnknown(rbase))
        	return this.type(xts.unknownType(position()));
        
        if (op == EQ || op == NE || op == LT || op == GT || op == LE || op == GE) {
            Object lv = left.isConstant() ? left.constantValue() : null;
            Object rv = right.isConstant() ? right.constantValue() : null;
            
            // If comparing signed vs. unsigned, check if one operand is a constant convertible to the other (base) type.
            // If so, insert the conversion and check again.
            
            if ((xts.isSigned(lbase) && xts.isUnsigned(rbase)) || (xts.isUnsigned(lbase) && xts.isSigned(rbase))) {
                try {
                    if (lv != null && xts.numericConversionValid(rbase, lbase, lv, context)) {
                        Expr e = Converter.attemptCoercion(tc, left, rbase);
                        if (e == left)
                            return this.type(xts.Boolean());
                        if (e != null)
                            return Converter.check(left(e), tc);
                    }
                    if (rv != null && xts.numericConversionValid(lbase, rbase, rv, context)) {
                        Expr e = Converter.attemptCoercion(tc, right, lbase);
                        if (e == right)
                            return this.type(xts.Boolean());
                        if (e != null)
                            return Converter.check(right(e), tc);
                    }
                } catch (SemanticException e) { } // FIXME
            }
            
            if (xts.isUnsigned(lbase) && xts.isSigned(rbase))
                Errors.issue(tc.job(),
                        new SemanticException("Cannot compare unsigned versus signed values.", position()));

            if (xts.isSigned(lbase) && xts.isUnsigned(rbase))
                Errors.issue(tc.job(),
                        new SemanticException("Cannot compare signed versus unsigned values.", position()));
            
            Type promoted = promote(xts, lbase, rbase);
            
            if (promoted != null &&
                (! xts.typeBaseEquals(lbase, promoted, context) ||
                 ! xts.typeBaseEquals(rbase, promoted, context)))
            {
                try {
                Expr el = Converter.attemptCoercion(tc, left, promoted);
                Expr er = Converter.attemptCoercion(tc, right, promoted);
                if (el != null && er != null && (el != left || er != right))
                	return Converter.check(left(el).right(er), tc);
                } catch (SemanticException e) { } // FIXME
            }
        }
        
        if (op == EQ || op == NE) {
            // == and != are allowed if the *unconstrained* types can be cast to each other without coercions.
            // Coercions are handled above for numerics.  No other coercions are allowed.
            // Constraints are ignored so that things like x==k will not cause compile-time errors
            // when x is a final variable initialized to a constant != k.
            if (xts.isCastValid(lbase, rbase, context) || xts.isCastValid(rbase, lbase, context)) {
                return type(xts.Boolean());
            }
//
//            if (xts.isImplicitCastValid(lbase, rbase, context) || xts.isImplicitCastValid(rbase, lbase, context)) {
//                assert false : "isCastValid but isImplicitCastValid not for " + lbase + " and " + rbase;
//                return type(xts.Boolean());
//            }

            Errors.issue(tc.job(),
                    new SemanticException("Operator must have operands of comparable type; the types " + lbase + " and " + rbase + " do not share any values.",position()));
            return type(xts.Boolean());
        }

        Call c = desugarBinaryOp(this, tc);

        if (c != null) {
            MethodInstance mi = (MethodInstance) c.methodInstance();
            if (mi.error() != null) {
                Errors.issue(tc.job(), mi.error(), this);
            }
            
            // rebuild the binary using the call's arguments.  We'll actually use the call node after desugaring.
            if (mi.flags().isStatic()) {
                return this.left(c.arguments().get(0)).right(c.arguments().get(1)).type(c.type());
            }
            else if (!c.name().id().equals(invBinaryMethodName(this.operator()))) {
                assert (c.name().id().equals(binaryMethodName(this.operator())));
                return this.left((Expr) c.target()).right(c.arguments().get(0)).type(c.type());
            }
            else {
                return this.left(c.arguments().get(0)).right((Expr) c.target()).type(c.type());
            }
        }
        
        Type l = left.type();
        Type r = right.type();

        if (!xts.hasUnknown(l) && !xts.hasUnknown(r)) {
            if (op == COND_OR || op == COND_AND) {
            	Type result = xts.Boolean();
            	if (op == COND_OR)
            		return type(xts.Boolean());
                if (l.isBoolean() && r.isBoolean()) {
                	return type(BuiltInTypeRules.adjustReturnTypeForConjunction(l,r, context));
                }
            }

            Errors.issue(tc.job(),
                    new SemanticException("No operation " + op + " found for operands " + l + " and " + r + ".", position()));
        }

        return this.type(xts.unknownType(position()));
    }

    public static X10Call_c typeCheckCall(ContextVisitor tc, X10Call_c call) {
        List<Type> typeArgs = new ArrayList<Type>(call.typeArguments().size());
        for (TypeNode tn : call.typeArguments()) {
            typeArgs.add(tn.type());
        }

        List<Type> argTypes = new ArrayList<Type>(call.arguments().size());
        for (Expr e : call.arguments()) {
            Type et = e.type();
            argTypes.add(et);
        }
        Type targetType = call.target().type();
        MethodInstance mi = null;
        List<Expr> args = null;
        // First try to find the method without implicit conversions.
        Pair<MethodInstance, List<Expr>> p = Checker.findMethod(tc, call, targetType, call.name().id(), typeArgs, argTypes);
        mi = p.fst();
        args = p.snd();
        if (mi.error() != null) {
            try {
                // Now, try to find the method with implicit conversions, making them explicit.
                p = Checker.tryImplicitConversions(call, tc, targetType, call.name().id(), typeArgs, argTypes);
                mi =  p.fst();
                args = p.snd();
            } catch (SemanticException e) {
                // FIXME: [IP] The exception may indicate that there's an ambiguity, which is better than reporting that a method is not found.
                int i = 3;
            }
        }
        Type rt = Checker.rightType(mi.rightType(), mi.x10Def(), call.target(), tc.context());
        call = (X10Call_c) call.methodInstance(mi).type(rt);
        call = (X10Call_c) call.arguments(args);
        return call;
    }

    public static X10Call_c searchInstance1(Name methodName, Position pos, ContextVisitor tc, Expr first, Expr second) {
        NodeFactory nf = tc.nodeFactory();
        // Check if there is a method with the appropriate name and type with the left operand as receiver.
        X10Call_c n2 = (X10Call_c) nf.X10Call(pos, first, nf.Id(pos, methodName), Collections.<TypeNode>emptyList(), Collections.singletonList(second));
        n2 = typeCheckCall(tc, n2);
        MethodInstance mi2 = (MethodInstance) n2.methodInstance();
        if (mi2.error() == null && !mi2.def().flags().isStatic())
            return n2;
        return null;
    }
    public static X10Call_c searchInstance(Name methodName, Position pos, ContextVisitor tc, Expr first, Expr second) {
        if (methodName != null) {
            X10Call_c res = searchInstance1(methodName,pos,tc,first,second);
            if (res!=null) return res;

            // maybe the left operand can be cast to the right operand (e.g., Byte+Int should use Int.operator+(Int) and not Byte.operator+(Byte))
            Expr newFirst = Converter.attemptCoercion(
                    false, // I do not want to report any warnings if coercion failed
                    tc, first,
                    Types.baseType(second.type())); // I use baseType because the constraints are irrelevant for resolution (and it can cause an error if the constraint contain "place23423423")
            if (newFirst!=first && newFirst!=null) {
                return searchInstance1(methodName,pos,tc,newFirst,second);
            }
        }
        return null;
    }
    public static X10Call_c desugarBinaryOp(Binary n, ContextVisitor tc) {
        Expr left = n.left();
        Expr right = n.right();
        Binary.Operator op = n.operator();
        Position pos = n.position();

        Type l = left.type();
        Type r = right.type();

        // Equality operators are special
        if (op == EQ || op == NE)
            return null;

        // Conditional operators on Booleans are special
        if ((op == COND_OR || op == COND_AND) && l.isBoolean() && r.isBoolean())
            return null;

        NodeFactory nf = tc.nodeFactory();
        Name methodName = X10Binary_c.binaryMethodName(op);
        Name invMethodName = X10Binary_c.invBinaryMethodName(op);

        // TODO: byte+byte should convert both bytes to int and search int
        // For now, we have to define byte+byte in byte.x10.

        X10Call_c virtual_left;
        X10Call_c virtual_right;
        X10Call_c static_left = null;
        X10Call_c static_right = null;

        virtual_right = searchInstance(invMethodName,pos,tc,right,left);
        virtual_left = searchInstance(methodName,pos,tc,left,right);

        if (methodName != null) {
            // Check if there is a static method of the left type with the appropriate name and type.   
            X10Call_c n4 = (X10Call_c) nf.X10Call(pos, nf.CanonicalTypeNode(pos, Types.ref(l)), nf.Id(pos, methodName), Collections.<TypeNode>emptyList(), CollectionUtil.list(left, right));
            n4 = typeCheckCall(tc, n4);
            MethodInstance mi4 = (MethodInstance) n4.methodInstance();
            if (mi4.error() == null && mi4.def().flags().isStatic())
                static_left = n4;
        }

        if (methodName != null) {
            // Check if there is a static method of the right type with the appropriate name and type.   
            X10Call_c n3 = (X10Call_c) nf.X10Call(pos, nf.CanonicalTypeNode(pos, Types.ref(r)), nf.Id(pos, methodName), Collections.<TypeNode>emptyList(), CollectionUtil.list(left, right));
            n3 = typeCheckCall(tc, n3);
            MethodInstance mi3 = (MethodInstance) n3.methodInstance();
            if (mi3.error() == null && mi3.def().flags().isStatic())
                static_right = n3;
        }


        List<X10Call_c> defs = new ArrayList<X10Call_c>();
        if (virtual_left != null) defs.add(virtual_left);
        if (virtual_right != null) defs.add(virtual_right);
        if (static_left != null) defs.add(static_left);
        if (static_right != null) defs.add(static_right);

        if (defs.size() == 0) {
            if (methodName == null)
                return null;
            // Create a fake static method in the left type with the appropriate name and type.   
            X10Call_c fake = (X10Call_c) nf.Call(pos, nf.CanonicalTypeNode(pos, left.type()), nf.Id(pos, methodName), left, right);
            fake = typeCheckCall(tc, fake);
            return fake;
        }

        X10TypeSystem_c xts = (X10TypeSystem_c) tc.typeSystem();

        List<X10Call_c> best = new ArrayList<X10Call_c>();
        X10Binary_c.Conversion bestConversion = X10Binary_c.Conversion.UNKNOWN;

        for (int i = 0; i < defs.size(); i++) {
            X10Call_c n1 = defs.get(i);

            // Check if n needs a conversion
            Expr[] actuals = new Expr[] {
                n1.arguments().size() != 2 ? (Expr) n1.target() : n1.arguments().get(0),
                n1.arguments().size() != 2 ? n1.arguments().get(0) : n1.arguments().get(1),
            };
            boolean inverse = isInv(n1.name().id());
            Expr[] original = new Expr[] {
                inverse ? right : left,
                inverse ? left : right,
            };
            X10Binary_c.Conversion conversion = X10Binary_c.conversionNeeded(actuals, original);

            if (bestConversion.harder(conversion)) {
                best.clear();
                best.add(n1);
                bestConversion = conversion;
            }
            else if (conversion.harder(bestConversion)) {
                // best is still the best
            }
            else {  // all other things being equal
                MethodDef md = n1.methodInstance().def();
                Type td = Types.get(md.container());
                ClassDef cd = def(td);

                boolean isBetter = false;
                for (Iterator<X10Call_c> ci = best.iterator(); ci.hasNext(); ) {
                    X10Call_c c = ci.next();
                    MethodDef bestmd = c.methodInstance().def();
                    if (bestmd == md) break;  // same method by a different path; already in best

                    Type besttd = Types.get(bestmd.container());
                    if (xts.isUnknown(besttd) || xts.isUnknown(td)) {
                        // going to create a fake one anyway; might as well get more data
                        isBetter = true;
                        continue;
                    }

                    ClassDef bestcd = def(besttd);
                    assert (bestcd != null && cd != null);

                    if (xts.descendsFrom(cd, bestcd)) {
                        // we found the method of a subclass; remove the superclass one
                        ci.remove();
                        isBetter = true;
                        assert (bestConversion == conversion);
                        bestConversion = conversion;
                    }
                    else if (xts.descendsFrom(bestcd, cd)) {
                        // best is still the best
                        isBetter = false;
                        break;
                    }
                    else {
                        isBetter = true;
                    }
                }
                if (isBetter)
                    best.add(n1);
            }
        }
        assert (best.size() != 0);

        X10Call_c result = best.get(0);
        if (best.size() > 1) {
            List<MethodInstance> bestmis = new ArrayList<MethodInstance>();
            Type rt = null;
            boolean rtset = false;
            ClassType ct = null;
            boolean ctset = false;
            // See if all matches have the same container and return type, and save that to avoid losing information.
            for (X10Call_c c : best) {
                MethodInstance xmi = c.methodInstance();
                bestmis.add(xmi);
                if (!rtset) {
                    rt = xmi.returnType();
                    rtset = true;
                } else if (rt != null && !xts.typeEquals(rt, xmi.returnType(), tc.context())) {
                    if (xts.typeBaseEquals(rt, xmi.returnType(), tc.context())) {
                        rt = Types.baseType(rt);
                    } else {
                        rt = null;
                    }
                }
                if (!ctset) {
                    ct = xmi.container().toClass();
                    ctset = true;
                } else if (ct != null && !xts.typeEquals(ct, xmi.container(), tc.context())) {
                    if (xts.typeBaseEquals(ct, xmi.container(), tc.context())) {
                        ct = Types.baseType(ct).toClass();
                    } else {
                        ct = null;
                    }
                }
            }
            if (ct == null) ct = l.toClass();  // arbitrarily pick the left operand
            SemanticException error = new Errors.AmbiguousOperator(op, bestmis, pos);
            MethodInstance mi = xts.createFakeMethod(ct, Flags.PUBLIC.Static(), methodName,
                    Collections.<Type>emptyList(), CollectionUtil.list(l, r), error);
            if (rt != null) mi = mi.returnType(rt);
            result = (X10Call_c) nf.X10Call(pos, nf.CanonicalTypeNode(pos, Types.ref(ct)),
                    nf.Id(pos, methodName), Collections.<TypeNode>emptyList(),
                    CollectionUtil.list(left, right)).methodInstance(mi).type(mi.returnType());
        } 
        {
        MethodInstance mi = result.methodInstance();
        Type lbase = Types.baseType(n.left().type());
        Type rbase = Types.baseType(n.right().type());
        Context context = (Context) tc.context();

        // Add support for patching up the return type of Region's operator*().
        // The rank of the result is a+b, if the rank of the left arg is a and of the right arg is b,
        // and a and b are literals. Further the result is rect if both args are rect.
        if (op == Binary.MUL && xts.typeEquals(xts.Region(), lbase, context)
        		&& xts.typeEquals(xts.Region(), rbase, context)) {
            Type type = result.type();
            type = BuiltInTypeRules.adjustReturnTypeForRegionMult(left, right, type, context);
            mi = mi.returnType(type);
            result = (X10Call_c) result.methodInstance(mi).type(type);
        }
        // Add support for patching up the return type of Int's operator..(),
        // The result is zeroBased if the left arg is 0.
        if (op == Binary.DOT_DOT && xts.typeEquals(xts.Int(), lbase, context)
                && xts.typeEquals(xts.Int(), rbase, context)) {
            Type type = result.type();
            type = BuiltInTypeRules.adjustReturnTypeForIntRange(left, right, type, context);
            mi = mi.returnType(type);
            result = (X10Call_c) result.methodInstance(mi).type(type);
        }
        }
        try {
            result = (X10Call_c) PlaceChecker.makeReceiverLocalIfNecessary(result, tc);
        } catch (SemanticException e) {
            MethodInstance mi = (MethodInstance) result.methodInstance();
            if (mi.error() == null)
                result = (X10Call_c) result.methodInstance(mi.error(e));
        }
        if (n.isConstant())
            result = result.constantValue(n.constantValue());
        return result;
    }

    /**
     * Get the class that defines type t.  Only returns null if
     * t is a parameter type or an unknown type.
     */
    public static ClassDef def(Type t) {
        Type base = Types.baseType(t);
        if (base instanceof ClassType)
            return ((ClassType) base).def();
        return null;
    }

    public static enum Conversion {
        NONE { boolean harder(Conversion a) { return false; } },
        IMPLICIT { boolean harder(Conversion a) { return a == NONE; } },
        EXPLICIT { boolean harder(Conversion a) { return a == NONE || a == IMPLICIT; } },
        UNKNOWN { boolean harder(Conversion a) { return a != UNKNOWN; } };
        abstract boolean harder(Conversion b);
    }
    public static Conversion conversionNeeded(Expr[] actuals, Expr[] original) {
        Conversion result = Conversion.NONE;
        for (int k = 0; k < actuals.length; k++) {
            if (actuals[k] != original[k]) {
                if (result == Conversion.NONE)
                    result = Conversion.IMPLICIT;
                if (actuals[k] instanceof X10Call) {
                    X10Call c = (X10Call) actuals[k];
                    if (c.methodInstance().name() == Converter.operator_as) {
                        result = Conversion.EXPLICIT;
                    }
                }
            }
        }
        return result;
    }

    public static Expr coerceToString(ContextVisitor tc, Expr e) throws SemanticException {
        TypeSystem ts = tc.typeSystem();

        if (!e.type().isSubtype(ts.String(), tc.context())) {
            NodeFactory nf = tc.nodeFactory();
            e = nf.X10Call(e.position(), nf.CanonicalTypeNode(e.position(), ts.String()),
                           nf.Id(e.position(), Name.make("valueOf")),
                           Collections.<TypeNode>emptyList(), Collections.singletonList(e));
            return (Expr) e.del().disambiguate(tc).typeCheck(tc).checkConstants(tc);
        }

        return e;
    }

    public boolean invert() {
        return invert;
    }

    public X10Binary_c invert(boolean invert) {
        X10Binary_c n = (X10Binary_c) copy();
        n.invert = invert;
        return n;
    }
}

