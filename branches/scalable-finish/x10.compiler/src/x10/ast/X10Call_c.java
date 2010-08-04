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
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import polyglot.ast.AmbTypeNode;
import polyglot.ast.Call;
import polyglot.ast.Call_c;
import polyglot.ast.ClassBody;
import polyglot.ast.Expr;
import polyglot.ast.Id;
import polyglot.ast.Local;
import polyglot.ast.New;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Receiver;
import polyglot.ast.Special;
import polyglot.ast.TypeNode;
import polyglot.ast.Variable;
import polyglot.frontend.Job;
import polyglot.main.Report;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import polyglot.types.CodeDef;
import polyglot.types.Context;
import polyglot.types.ErrorRef_c;
import polyglot.types.LocalDef;
import polyglot.types.LocalInstance;
import polyglot.types.Matcher;
import polyglot.types.MethodDef;
import polyglot.types.MethodInstance;
import polyglot.types.Name;
import polyglot.types.SemanticException;
import polyglot.types.StructType;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.TypeSystem_c;
import polyglot.types.Types;
import polyglot.types.UnknownType;
import polyglot.types.TypeSystem_c.MethodMatcher;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.ErrorInfo;
import polyglot.util.InternalCompilerError;
import polyglot.util.Pair;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.ErrorHandlingVisitor;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;
import x10.constraint.XConstraint;
import x10.constraint.XLocal;
import x10.constraint.XVar;
import x10.errors.Errors;
import x10.errors.Errors.PlaceTypeErrorMethodShouldBeLocalOrGlobal;
import x10.parser.X10ParsedName;
import x10.types.ParameterType;
import x10.types.X10ClassType;
import x10.types.X10ConstructorInstance;
import x10.types.X10Context;
import x10.types.X10FieldInstance;
import x10.types.X10Flags;
import x10.types.X10LocalInstance;
import x10.types.X10MemberDef;
import x10.types.X10MethodDef;
import x10.types.X10MethodInstance;
import x10.types.X10TypeSystem_c;

import x10.types.X10TypeMixin;
import x10.types.X10TypeSystem;
import x10.types.XTypeTranslator;
import x10.types.checker.Checker;
import x10.types.checker.Converter;
import x10.types.checker.PlaceChecker;
import x10.types.constraints.XConstrainedTerm;
import x10.types.matcher.DumbMethodMatcher;
import x10.visit.X10TypeChecker;
import x10cpp.postcompiler.CXXCommandBuilder;


/**
 * Representation of an X10 method call.
 * @author Igor
 * @author vj
 */
public class X10Call_c extends Call_c implements X10Call, X10ProcedureCall {
	public X10Call_c(Position pos, Receiver target, Id name,
			List<TypeNode> typeArguments, List<Expr> arguments) {
		super(pos, target, name, arguments);
		this.typeArguments = new ArrayList<TypeNode>(typeArguments);
	}

	List<TypeNode> typeArguments;
	public List<TypeNode> typeArguments() { return typeArguments; }
	public X10Call typeArguments(List<TypeNode> args) {
		X10Call_c n = (X10Call_c) copy();
		n.typeArguments = new ArrayList<TypeNode>(args);
		return n;
	}
	public X10Call arguments(List<Expr> args) {
		X10Call_c n = (X10Call_c) copy();
		n.arguments = new ArrayList<Expr>(args);
		return n;
	}
	public Call_c reconstruct(Receiver target, Id name, List<Expr> arguments) {
		return super.reconstruct(target, name, arguments);
	}

	@Override
	public Node visitChildren(NodeVisitor v) {
		Receiver target = (Receiver) visitChild(this.target, v);
		Id name = (Id) visitChild(this.name, v);
		List<TypeNode> typeArguments = visitList(this.typeArguments, v);
		List<Expr> arguments = visitList(this.arguments, v);
		X10Call_c n = (X10Call_c) typeArguments(typeArguments);
		return n.reconstruct(target, name, arguments);
	}

	@Override
	public Node disambiguate(ContextVisitor tc) throws SemanticException {
		return this;
	}


	/**
	 * Looks up a method with given name and argument types.
	 */
	protected Pair<MethodInstance,List<Expr>> superFindMethod(ContextVisitor tc, X10Context xc,
			TypeSystem_c.MethodMatcher matcher, List<Type> typeArgs,
			List<Type> argTypes) throws SemanticException {
		return superFindMethod(tc, xc, matcher, typeArgs, argTypes, null);
	}
	protected Pair<MethodInstance,List<Expr>> superFindMethod(ContextVisitor tc, X10Context xc,
			TypeSystem_c.MethodMatcher matcher, List<Type> typeArgs,
			List<Type> argTypes, List<Expr> args) throws SemanticException {
		// Check for any method with the appropriate name.
		// If found, stop the search since it shadows any enclosing
		// classes method of the same name.
		TypeSystem ts = tc.typeSystem();
		ClassType currentClass = xc.currentClass();
		if (currentClass != null &&
				ts.hasMethodNamed(currentClass, matcher.name())) {

			// Override to change the type from C to C{self==this}.
			Type t = currentClass;
			X10TypeSystem xts = (X10TypeSystem) ts;
			XVar thisVar = null;
			if (XTypeTranslator.THIS_VAR) {
				CodeDef cd = xc.currentCode();
				if (cd instanceof X10MemberDef) {
					thisVar = ((X10MemberDef) cd).thisVar();
				}
			}
			else {
				//                  thisVar = xts.xtypeTranslator().transThis(currentClass);
				thisVar = xts.xtypeTranslator().transThisWithoutTypeConstraint();
			}

			if (thisVar != null)
				t = X10TypeMixin.setSelfVar(t, thisVar);

			// Found a class that has a method of the right name.
			// Now need to check if the method is of the correct type.

			X10MethodInstance mi = null;

			// First try to find the method without implicit conversions.
			try {
				mi = xts.findMethod(t, matcher.container(t));
				return new Pair<MethodInstance, List<Expr>>(mi, this.arguments);
			}
			catch (SemanticException e) {
				// Now, try to find the method with implicit conversions, making them explicit.
				try {
					Pair<MethodInstance,List<Expr>> p = tryImplicitConversions(this, tc, t, name().id(), typeArgs, argTypes);
					return p;
				}
				catch (SemanticException e2) {
					throw e;
				}
			}
		}

		if (xc.pop() != null) {
			return superFindMethod(tc, (X10Context) xc.pop(), matcher, typeArgs, argTypes);
		}

		throw new SemanticException("Method " + matcher.signature() + " not found.");
	}

    /**
     * Looks up a method with name "name" and arguments compatible with
     * "argTypes".
     */
        protected Pair<MethodInstance,List<Expr>> findMethod(ContextVisitor tc, TypeSystem_c.MethodMatcher matcher,
        		List<Type> typeArgs, List<Type> argTypes) throws SemanticException {
            X10Context xc = (X10Context) tc.context();
            Pair<MethodInstance,List<Expr>> result = xc.currentDepType() == null ?
            		superFindMethod(tc, xc, matcher, typeArgs, argTypes)
            		: superFindMethod(tc, (X10Context) xc.pop(), matcher, typeArgs, argTypes);
            return result;
    }



/**
 * First try for a struct constructor, and then look for a static method.
 * @param tc
 * @param typeArgs
 * @param argTypes
 * @param args
 * @return
 * @throws SemanticException
 */

        protected Node tryStructConstructor(ContextVisitor tc, Receiver r, List<Type> typeArgs, List<Type> argTypes,
    			List<Expr> args) throws SemanticException {
        	try {
				X10NodeFactory nf = (X10NodeFactory) tc.nodeFactory();
				X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
				Context c = tc.context();

				// TODO: Actually, determine if there is a struct with the given name,
				// and able to accept given typeargs and args.
				//ts.existsStructWithName(name(), tc);
				/*
				// ok so it is a struct type. Now create the return type.
				tn = nf.AmbDepTypeNode(position(), null, name(), typeArguments, Collections.EMPTY_LIST, null);
				ContextVisitor childtc = tc;
				tn = (TypeNode) this.visitChild(tn, childtc);
				if (tn.type() instanceof UnknownType) { // bail
					throw new SemanticException();
				}

				t = tn.type();
				t = ts.expandMacros(t);

				xc = X10TypeMixin.xclause(t);
				t = X10TypeMixin.baseType(t);

				if (!(ts.isStructType(t))) { // bail
					throw new SemanticException();
				}

				tn = (TypeNode) tn.visit(tb);
				// First ensure that there is a type associated with tn.
				tn = (TypeNode) tn.disambiguate(tc);
				tn = (TypeNode) tn.typeCheck(tc);
				Type t = tn.type();
				t = ts.expandMacros(t);
				tn = tn.typeRef(Types.lazyRef(t));
				*/

				TypeNode otn;
				if (typeArguments().size() > 0) {
					otn = nf.AmbMacroTypeNode(position(), r, name(), typeArguments(), Collections.EMPTY_LIST);
				}
				else {
				    otn = nf.AmbTypeNode(position(), r, name());
				}

				otn = otn.typeRef(Types.lazyRef(ts.unknownType(position())));
				TypeNode tn2 = (TypeNode) otn.del().typeCheckOverride(this, tc);
				if (tn2 == null) {
				    otn = (TypeNode) otn.del().disambiguate(tc).del().typeCheck(tc);
				}
				else {
				    otn = tn2;
				}
				if (X10TypeMixin.isX10Struct(otn.type())) {
				    X10New_c neu = (X10New_c) nf.X10New(position(), otn, Collections.EMPTY_LIST, args);
				    neu.setStructConstructorCall();

				    neu = (X10New_c) neu.del().disambiguate(tc).del().typeCheck(tc);
				    return neu;
				}
			} catch (SemanticException z) {
				// TBD: Sometimes this is the real error. e.g. the only legitimate target is the
				// struct call. We should record this exception and then come back and use this later
				// if necessary.
				// This may have caused some errors to print out.
				typeCheckNullTargetException = z;
			}
			return null;
        }
    SemanticException typeCheckNullTargetException = null;
	protected Node typeCheckNullTarget(ContextVisitor tc, List<Type> typeArgs, List<Type> argTypes,
			List<Expr> args) throws SemanticException {

		 Node n = tryStructConstructor(tc, null, typeArgs, argTypes, args);
		 if (n != null)
			 return n;
		// Otherwise try and find the usual null target method.

			 n = typeCheckNullTargetForMethod(tc, typeArgs, argTypes);
			 return n;

	}


	protected Node typeCheckNullTargetForMethod(ContextVisitor tc, List<Type> typeArgs, List<Type> argTypes) throws SemanticException {

		X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
		X10Context c = (X10Context) tc.context();

		// the target is null, and thus implicit
		// let's find the target, using the context, and
		// set the target appropriately, and then type check
		// the result
		Pair<MethodInstance, List<Expr>> p = findMethod(tc, ts.MethodMatcher(null, name.id(), typeArgs, argTypes, c), typeArgs, argTypes);
		X10MethodInstance mi = (X10MethodInstance) p.fst();
		List<Expr> args = p.snd();
		return typeCheckNullTargetForMethod(tc, typeArgs, argTypes, mi, args);
	}

	protected Node typeCheckNullTargetForMethod(ContextVisitor tc, List<Type> typeArgs, List<Type> argTypes, X10MethodInstance mi, List<Expr> args) throws SemanticException {

		X10TypeSystem xts = (X10TypeSystem) ((X10TypeSystem) tc.typeSystem());
		NodeFactory nf = tc.nodeFactory();
		X10Context c = (X10Context) tc.context();

		Receiver r;
		if (mi.flags().isStatic()) {
			Type container = findContainer(xts, mi);
			XVar this_ = getThis(container);
			if (this_ != null)
			    container = X10TypeMixin.setSelfVar(container, this_);
			r = nf.CanonicalTypeNode(position().startOf(), container).typeRef(Types.ref(container));
		} else {
			// The method is non-static, so we must prepend with "this", but we
			// need to determine if the "this" should be qualified.  Get the
			// enclosing class which brought the method into scope.  This is
			// different from mi.container().  mi.container() returns a super type
			// of the class we want.
			Type scope = c.findMethodScope(name.id());


			if (! xts.typeEquals(scope, c.currentClass(), c)) {
				XVar this_ = getThis(scope);
				if (this_ != null)
				    scope = X10TypeMixin.setSelfVar(scope, this_);
				r = (Special) nf.This(position().startOf(),
						nf.CanonicalTypeNode(position().startOf(), scope)).del().typeCheck(tc);
			}
			else {
				r = (Special) nf.This(position().startOf()).del().typeCheck(tc);
			}
		}

		X10Call_c call = (X10Call_c) this.targetImplicit(true).target(r).arguments(args);
		Type rt = X10Field_c.rightType(mi.rightType(), mi.x10Def(), r, c);
		call = (X10Call_c)call.methodInstance(mi).type(rt);

		call.checkProtoMethod();

		return call;
	}

	void checkProtoMethod() throws SemanticException {
		if (X10TypeMixin.isProto(target().type())
				&& ! X10Flags.toX10Flags(methodInstance().flags()).isProto()
			)
			throw new SemanticException(methodInstance()
					+ " must be declared as a proto method since it is called on a receiver " +
					target() + " with a proto type.");
	}
	XVar getThis(Type t) {
	    t = X10TypeMixin.baseType(t);
	    if (t instanceof X10ClassType) {
	        return ((X10ClassType) t).x10Def().thisVar();
	    }
	    return null;
	}

	public Node typeCheck(ContextVisitor tc) throws SemanticException {
	    Node n;
	    try {
	        n = typeCheck1(tc);
	    } catch (SemanticException e) {
	        X10TypeChecker xtc = X10TypeChecker.getTypeChecker(tc);
	        if (xtc.throwExceptions())
	            throw e;
	        Errors.issue(tc.job(), e, this);
	        X10TypeSystem_c ts = (X10TypeSystem_c) tc.typeSystem();
	        List<Type> typeArgs = new ArrayList<Type>(this.typeArguments.size());
	        for (TypeNode tn : this.typeArguments) {
	            typeArgs.add(tn.type());
	        }
	        List<Type> argTypes = new ArrayList<Type>(this.arguments.size());
	        for (Expr a : this.arguments) {
	            argTypes.add(a.type());
	        }
	        X10MethodInstance mi = ts.createFakeMethod(name.id(), typeArgs, argTypes, e);
	        Type rt = mi.rightType(); // X10Field_c.rightType(mi.rightType(), mi.x10Def(), n.target, c);
	        n = (X10Call_c) methodInstance(mi).type(rt);
	        try {
	            n = ((X10Call_c)n).typeCheckNullTargetForMethod(tc, typeArgs, argTypes, mi, this.arguments);
	        } catch (SemanticException e2) { }
	    }
	    return n;
	}
	public Node typeCheck1(ContextVisitor tc) throws SemanticException {
		X10NodeFactory xnf = (X10NodeFactory) tc.nodeFactory();
		X10TypeSystem xts = (X10TypeSystem) tc.typeSystem();
		X10Context c = (X10Context) tc.context();

		if (mi != null && ((X10MethodInstance)mi).isValid()) // already typechecked
		    return this;

        Name name = this.name().id();

        X10TypeChecker xtc = X10TypeChecker.getTypeChecker(tc).throwExceptions(true);

		Expr cc = null;

        {
		    // Check if target.name is a field or local of function type;
			// if so, convert to a closure call.
			X10NodeFactory nf = (X10NodeFactory) tc.nodeFactory();
			X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();

			Expr e = null;

			if (target() == null) {
			    X10LocalInstance li = X10Local_c.findAppropriateLocal(tc, name);
			    if (li.error() == null) {
			        //e = xnf.Local(name().position(), name()).localInstance(li).type(li.type());
			        try {
			            e = xnf.Local(name().position(), name()).localInstance(li);
			            e = (Expr) e.del().typeCheck(xtc);
			            e = (Expr) e.del().checkConstants(xtc);
			        } catch (SemanticException cause) {
			            throw new InternalCompilerError("Unexpected exception when typechecking "+e, e.position(), cause);
			        }
			    }
			}
			if (e == null) {
			    boolean isStatic = target() instanceof TypeNode || (target() == null && c.inStaticContext());
			    X10FieldInstance fi = null;
			    if (target() == null) {
			        fi = (X10FieldInstance) c.findVariableSilent(name);
		            if (fi != null && isStatic && !fi.flags().isStatic())
		                fi = null;
			    }
			    if (fi == null) {
			        Type targetType = target() == null ? c.currentClass() : target().type();
			        fi = X10Field_c.findAppropriateField(tc, targetType, name,
			                isStatic,
			                X10TypeMixin.contextKnowsType(target()));
			    }
			    if (fi.error() == null) {
			        try {
			            Receiver target = this.target() == null ?
			                    X10Disamb_c.makeMissingFieldTarget(fi, name().position(), xtc) :
			                        this.target();
			            //e = xnf.Field(name().position(), target,
			            //        name()).fieldInstance(fi).targetImplicit(target()==null).type(fi.type());
			            e = xnf.Field(name().position(), target,
			                    name()).fieldInstance(fi).targetImplicit(target()==null);
                        e = (Expr) e.del().typeCheck(xtc);
                        e = (Expr) e.del().checkConstants(xtc);
			        } catch (SemanticException cause) {
                        throw new InternalCompilerError("Unexpected exception when typechecking "+e, e.position(), cause);
			        }
			    }
			}

            if (e != null) {
                assert typeArguments().size() == 0;
                List<Type> typeArgs = Collections.emptyList();
                List<Type> actualTypes = new ArrayList<Type>(arguments().size());
                for (Expr ei : arguments()) {
                    actualTypes.add(ei.type());
                }
                // First try to find the method without implicit conversions.
                X10MethodInstance ci = ClosureCall_c.findAppropriateMethod(tc, e.type(), Name.make("apply"), typeArgs, actualTypes);
                List<Expr> args = this.arguments;
                if (ci.error() != null) {
                    // Now, try to find the method with implicit conversions, making them explicit.
                    try {
                        Pair<MethodInstance,List<Expr>> p = X10Call_c.tryImplicitConversions(this, tc, e.type(), Name.make("apply"), typeArgs, actualTypes);
                        ci = (X10MethodInstance) p.fst();
                        args = p.snd();
                    }
                    catch (SemanticException se) { }
                }
                if (ci.error() != null) {
                    // Check for this case:
                    // val x = x();
                    if (e instanceof Local && e.type() instanceof UnknownType) {
                        throw new SemanticException("Possible closure call on uninitialized variable " + ((Local) e).name() + ".", position());
                    }
                } else {
                    ClosureCall ccx = nf.ClosureCall(position(), e,  arguments()).closureInstance(ci);
                    Node n = ccx;
                    try {
                        //n = n.del().disambiguate(xtc);
                        n = n.del().typeCheck(xtc);
                        cc = (Expr) n;
                    }
                    catch (SemanticException cause) {
                        throw new InternalCompilerError("Unexpected exception when typechecking "+ccx, ccx.position(), cause);
                    }
                }
            }
		}

		// We have a cc and a valid call with no target. The one with //todo: fill in this comment
		if (cc instanceof ClosureCall) {
			ClosureCall call = (ClosureCall) cc;
			if (call.target() instanceof Local) {
				// cc is of the form r() where r is a local variable.
				// This overrides any other possibility for this call, e.g. a static or an instance method call.
				X10TypeMixin.checkMissingParameters(cc);
				Checker.checkOfferType(position(), call.closureInstance(), tc);
				return cc;
			}
		}
		/////////////////////////////////////////////////////////////////////
		// Inline the super call here and handle type arguments.
		/////////////////////////////////////////////////////////////////////

		List<Type> typeArgs = new ArrayList<Type>(this.typeArguments.size());

		for (TypeNode tn : this.typeArguments) {
		    typeArgs.add(tn.type());
		}

		List<Type> argTypes = new ArrayList<Type>(this.arguments.size());

		for (Expr e : this.arguments) {
		    Type et = e.type();
		    argTypes.add(et);
		}
		// TODO: Need to try struct call with implicit conversions as well.
		if (this.target == null) {
			Node n =  null;
			// First try to find the method without implicit conversions.
			try {
				n=this.typeCheckNullTarget(xtc, typeArgs, argTypes, arguments);
				// Success! n has the answer.
			}
			catch (SemanticException e) {
				// OK that didnt work. Try implicit conversions.
				X10MethodInstance mi = null;
				List<Expr> args = null;
				 // Now, try to find the method with implicit conversions, making them explicit.
				 try {
					 Pair<MethodInstance,List<Expr>> p = tryImplicitConversions(this, tc, null, name, typeArgs, argTypes);
					 mi = (X10MethodInstance) p.fst();
					 args = p.snd();
				 } catch (SemanticException e2) {
					 // Nothing worked. If you have a cc, thats the one. Exit with cc.
					 if (cc != null) {
						 Node result = cc.typeCheck(tc);
						 if (result instanceof Expr) {
							 X10TypeMixin.checkMissingParameters((Expr) result);
						 }
						// Checker.checkOfferType(position(), call.closureInstance(), tc);
						 return result;
					 }
					 // otherwise error.
					 MethodMatcher matcher = ((X10TypeSystem) tc.typeSystem()).MethodMatcher(null, name, typeArgs, argTypes, c);
					 throw new Errors.MethodOrStaticConstructorNotFound(matcher, position());
				 }

				 // OK so now we have mi and args that correspond to a success.
				 // Copy the method instance so we can modify it.
				 assert mi != null && args != null;
				 Type rt = mi.rightType(); // X10Field_c.rightType(mi.rightType(), mi.x10Def(), n.target, c);
				 X10Call_c result = (X10Call_c) methodInstance(mi).type(rt);
				 // Set up n with the answer.
				 n = result.arguments(args);
			}

			if (n instanceof X10Call_c) {
				n = PlaceChecker.makeReceiverLocalIfNecessary((X10Call) n, tc);
				Checker.checkOfferType(position(), (X10MethodInstance) ((X10Call_c) n).methodInstance(), tc);
			}


			// We have both!
			if (cc != null) {
			    throw new Errors.AmbiguousCall(((n instanceof X10New)
                                                           ? ((X10New) n).constructorInstance()
                                                                   : ((X10Call) n).methodInstance()), cc, position());
			}

			if (n instanceof Expr) {
				X10TypeMixin.checkMissingParameters((Expr) n);
			}
			return n;
		}

		Node structCall = null;
		if (target instanceof TypeNode) {
			Type t = ((TypeNode) target).type();
			t = X10TypeMixin.baseType(t);
			if (t instanceof ParameterType) {
				throw new SemanticException("Cannot invoke a static method of a type parameter.", position());
			}
			structCall = tryStructConstructor(xtc, target, typeArgs, argTypes, arguments);
			if (structCall != null) {
				Checker.checkOfferType(position(), (X10ConstructorInstance) ((X10New_c) structCall).constructorInstance(), tc);
				return structCall;
			}
		}

		X10Call_c n = this;

        Type targetType = this.target().type();

		ClassDef currentClassDef = c.currentClassDef();

		X10MethodInstance mi = null;
		List<Expr> args = null;

		// First try to find the method without implicit conversions.
		try {
		    mi = xts.findMethod(targetType, xts.MethodMatcher(targetType, name, typeArgs, argTypes, c));
		    args = n.arguments;
		}
		catch (SemanticException e) {
		    if (mi == null) {
		        // Now, try to find the method with implicit conversions, making them explicit.
		        try {
		            Pair<MethodInstance,List<Expr>> p = tryImplicitConversions(n, tc, targetType, n.name().id(), typeArgs, argTypes);
		            mi = (X10MethodInstance) p.fst();
		            args = p.snd();
		        }
		        catch (SemanticException e2) {
		            if (cc != null) {
		            	Node result = cc.typeCheck(tc);
		            	if (result instanceof Expr) {
		            		X10TypeMixin.checkMissingParameters((Expr) result);
		            	}
		                return result;
		            }

		            throw e;
		        }
		    }

		    assert mi != null && args != null;
		}

		if (cc != null)
		    throw new SemanticException("Ambiguous call; both " + mi + " and closure match.", position());

		/* This call is in a static context if and only if
		 * the target (possibly implicit) is a type node.
		 */
		boolean staticContext = (n.target instanceof TypeNode);

		if (staticContext && !mi.flags().isStatic()) {
		    throw new SemanticException("Cannot call non-static method " + name
		                                + " of " + n.target.type() + " in static "
		                                + "context.", this.position());
		}

		// If the target is super, but the method is abstract, then complain.
		if (n.target instanceof Special &&
		        ((Special) n.target).kind() == Special.SUPER &&
		        mi.flags().isAbstract()) {
		    throw new SemanticException("Cannot call an abstract method " +
		                                "of the super class", this.position());
		}

		// Copy the method instance so we can modify it.
		Type rt = X10Field_c.rightType(mi.rightType(), mi.x10Def(), n.target, c);
		X10Call_c result = (X10Call_c) n.methodInstance(mi).type(rt);
		result = (X10Call_c) result.arguments(args);

		/////////////////////////////////////////////////////////////////////
		// End inlined super call.
		/////////////////////////////////////////////////////////////////////

		result.checkProtoMethod();
		result = (X10Call_c) PlaceChecker.makeReceiverLocalIfNecessary(result, tc);
		// the arguments here.
		result.checkConsistency(c);
		//	        	result = result.adjustMI(tc);
		//	        	result.checkWhereClause(tc);
		result.checkAnnotations(tc);
		X10TypeMixin.checkMissingParameters(result);
		Checker.checkOfferType(position(), (X10MethodInstance) result.methodInstance(), tc);

		return result;
	}



	public static Pair<MethodInstance,List<Expr>> tryImplicitConversions(X10ProcedureCall n, ContextVisitor tc,
	        Type targetType, final Name name, List<Type> typeArgs, List<Type> argTypes) throws SemanticException {
	    final X10TypeSystem ts = (X10TypeSystem) tc.typeSystem();
	    final Context context = tc.context();

	    List<MethodInstance> methods = ts.findAcceptableMethods(targetType,
	            new DumbMethodMatcher(targetType, name, typeArgs, argTypes, context));

	    Pair<MethodInstance,List<Expr>> p = Converter.<MethodDef,MethodInstance>tryImplicitConversions(n, tc,
	            targetType, methods, new X10New_c.MatcherMaker<MethodInstance>() {
	        public Matcher<MethodInstance> matcher(Type ct, List<Type> typeArgs, List<Type> argTypes) {
	            return ts.MethodMatcher(ct, name, typeArgs, argTypes, context);
	        }
	    });

	    return p;
	}

	private void checkAnnotations(ContextVisitor tc) throws SemanticException {
		X10Context c = (X10Context) tc.context();
		X10MethodInstance mi = (X10MethodInstance) methodInstance();
		try {
		    if (mi !=null) {
		        X10Flags flags = X10Flags.toX10Flags(mi.flags());
		        if (c.inNonBlockingCode()
		                && ! (mi.isSafe() || flags.isNonBlocking() || flags.isExtern()))
		            throw new SemanticException(mi + ": Only nonblocking methods can be called from nonblocking code.",
		                                        position());
		        if (c.inSequentialCode()
		                && ! (mi.isSafe() || flags.isSequential() || flags.isExtern()))
		            throw new SemanticException(mi + ": Only sequential methods can be called from sequential code.",
		                                        position());
		        if (c.inLocalCode()
		                && ! (mi.isSafe() || flags.isPinned() || flags.isExtern()))
		            throw new SemanticException(mi + ": Only pinned methods can be called from pinned code.",
		                                        position());
		    }
		}
		catch (SemanticException e) {
		    tc.errorQueue().enqueue(ErrorInfo.WARNING, "WARNING (should be error, but method annotations in XRX are wrong): " + e.getMessage(), position());
		}
	}

	private Node superTypeCheck(ContextVisitor tc) throws SemanticException {
		return super.typeCheck(tc);
	}

	Object constantValue;
	public Object constantValue() { return constantValue; }
	public boolean isConstant() { return constantValue != null; }

	public X10Call_c constantValue(Object value) {
		X10Call_c n = (X10Call_c) copy();
		n.constantValue = value;
		return n;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(targetImplicit ? "" : target.toString() + ".");
		sb.append(name);
		if (typeArguments != null && typeArguments.size() > 0) {
			sb.append("[");
			sb.append(CollectionUtil.listToString(typeArguments));
			sb.append("]");
		}
		sb.append("(");
		sb.append(CollectionUtil.listToString(arguments));
		sb.append(")");
		return sb.toString();
	}


    /** Write the expression to an output file. */
   @Override
	  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
	    w.begin(0);
	    if (!targetImplicit) {
	        if (target instanceof Expr) {
	          printSubExpr((Expr) target, w, tr);
	        }
	        else if (target != null) {
	          print(target, w, tr);
	        }
	    w.write(".");
	    w.allowBreak(2, 3, "", 0);
	    }

        w.write(name + "");

	    if (typeArguments.size() > 0) {
	        w.write("[");
	        w.allowBreak(2, 2, "", 0); // miser mode
	        w.begin(0);
	                
	        for (Iterator<TypeNode> i = typeArguments.iterator(); i.hasNext(); ) {
	            TypeNode t = i.next();
	            t.prettyPrint(w, tr);
	            if (i.hasNext()) {
	            w.write(",");
	            w.allowBreak(0, " ");
	            }
	        }
            w.write("]");
	        w.end();
	    }
	    
	    w.write("(");
	    if (arguments.size() > 0) {
	    w.allowBreak(2, 2, "", 0); // miser mode
	    w.begin(0);
	            
	    for (Iterator<Expr> i = arguments.iterator(); i.hasNext(); ) {
	        Expr e = i.next();
	        print(e, w, tr);

	        if (i.hasNext()) {
	        w.write(",");
	        w.allowBreak(0, " ");
	        }
	    }

	    w.end();
	    }
	    w.write(")");
	    w.end();
	  }
}
