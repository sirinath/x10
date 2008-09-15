/*
 *
 * (C) Copyright IBM Corporation 2006-2008
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * Created by igor on Feb 15, 2006
 */
package polyglot.ext.x10.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import polyglot.ast.Binary;
import polyglot.ast.Call;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.Id;
import polyglot.ast.Local;
import polyglot.ast.Node;
import polyglot.ast.Precedence;
import polyglot.ast.Receiver;
import polyglot.ast.TypeNode;
import polyglot.ast.Unary;
import polyglot.ast.Unary_c;
import polyglot.ast.Binary.Operator;
import polyglot.ext.x10.types.X10MethodInstance;
import polyglot.ext.x10.types.X10Type;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.types.X10TypeSystem_c;
import polyglot.types.SemanticException;
import polyglot.types.Name;
import polyglot.types.StructType;
import polyglot.types.Type;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.PrettyPrinter;

/**
 * An immutable representation of a unary operation op Expr.
 * Overridden from Java to allow unary negation of points.
 *
 * @author igor Feb 15, 2006
 */
public class X10Unary_c extends Unary_c {

	/**
	 * @param pos
	 * @param op
	 * @param expr
	 */
	public X10Unary_c(Position pos, Operator op, Expr expr) {
		super(pos, op, expr);
	}

	// TODO: take care of constant points.
	public Object constantValue() {
		return super.constantValue();
	}
	
	/**
	 * Type check a binary expression. Must take care of various cases because
	 * of operators on regions, distributions, points, places and arrays.
	 * An alternative implementation strategy is to resolve each into a method
	 * call.
	 */
	public Node typeCheck(ContextVisitor tc) throws SemanticException {
	    X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();

	        Type t = expr.type();
		
	        Name methodName = unaryMethodName(op);
		if (methodName != null) {
		    // Check if there is a method with the appropriate name and type with the left operand as receiver.   
		    try {
			X10MethodInstance mi = ts.findMethod(t, ts.MethodMatcher(t, methodName, Collections.EMPTY_LIST), tc.context().currentClassDef());
			return type(mi.returnType());
		    }
		    catch (SemanticException e) {
			// Cannot find the method.  Fall through.
		    }
		}
		
		if (op == POST_INC || op == POST_DEC || op == PRE_INC || op == PRE_DEC) {
		    if (! expr.type().isNumeric()) {
		        throw new SemanticException("Operand of " + op +
		                                    " operator must be numeric.", expr.position());
		    }

		    if (expr instanceof Local) {
		        Local l = (Local) expr;
		        if (l.localInstance().flags().isFinal()) {
		            throw new SemanticException("Cannot apply " + op + " to a final variable.", position());
		        }
		        return type(expr.type());
		    }
		    else if (expr instanceof Field) {
		        Field l = (Field) expr;
		        if (l.fieldInstance().flags().isFinal()) {
		            throw new SemanticException("Cannot apply " + op + " to a final variable.", position());
		        }
		        return type(expr.type());
		    }
		    else {
		        Expr target = null;
		        List<Expr> args = null;
		        List<TypeNode> typeArgs = null;
		        
		        // Handle a(i)++ and a.apply(i)++
		        if (expr instanceof ClosureCall) {
		            ClosureCall e = (ClosureCall) expr;
		            target = e.target();
		            args = e.arguments();
		            typeArgs = e.typeArgs();
		        }
		        else if (expr instanceof X10Call) {
		            X10Call e = (X10Call) expr;
		            if (e.target() instanceof Expr && e.name().id() == Name.make("apply")) {
		                target = (Expr) e.target();
		                args = e.arguments();
		                typeArgs = e.typeArguments();
		            }
		        }
		        
		        if (target != null) {
		            List<Type> setArgTypes = new ArrayList<Type>();
		            List<Type> setTypeArgs = new ArrayList<Type>();
		            
		            // RHS goes before index
		            setArgTypes.add(expr.type());
		            for (Expr e : args) {
		                setArgTypes.add(e.type());
		            }
		            for (TypeNode tn : typeArgs) {
		                setTypeArgs.add(tn.type());
		            }
		            try {
		                X10MethodInstance mi = ts.findMethod(target.type(), ts.MethodMatcher(t, Name.make("set"), setTypeArgs, setArgTypes), tc.context().currentClassDef());
		                return type(mi.returnType());
		            }
		            catch (SemanticException e) {
		                throw e;
		            }
		        }
		    }
		}

		X10Unary_c n = (X10Unary_c) super.typeCheck(tc);

		Type resultType = n.type();
		resultType = ts.performUnaryOperation(resultType, t, op);
		if (resultType != n.type()) {
		    n = (X10Unary_c) n.type(resultType);
		}

		return n;
	}

	public static Name unaryMethodName(Unary.Operator op) {
	    Map<Unary.Operator,String> methodNameMap = new HashMap<Unary.Operator, String>();
	    methodNameMap.put(NEG, "$minus");
	    methodNameMap.put(POS, "$plus");
	    methodNameMap.put(NOT, "$not");
	    methodNameMap.put(BIT_NOT, "$tilde");
	    
	    String methodName = methodNameMap.get(op);
	    if (methodName == null)
	        return null;
	    return Name.make(methodName);
	}
}

