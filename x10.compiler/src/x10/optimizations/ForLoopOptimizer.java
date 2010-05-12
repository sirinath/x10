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
package x10.optimizations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import polyglot.ast.Assign;
import polyglot.ast.Binary;
import polyglot.ast.Block;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.For;
import polyglot.ast.ForInit;
import polyglot.ast.ForUpdate;
import polyglot.ast.Formal;
import polyglot.ast.IntLit;
import polyglot.ast.Local;
import polyglot.ast.LocalDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Stmt;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.ast.Assign.Operator;
import polyglot.frontend.Job;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.LocalDef;
import polyglot.types.LocalInstance;
import polyglot.types.Name;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import x10.ast.ForLoop;
import x10.ast.ForLoop_c;
import x10.ast.RegionMaker;
import x10.ast.X10Call;
import x10.ast.X10Cast;
import x10.ast.X10Formal;
import x10.ast.X10NodeFactory;
import x10.constraint.XFailure;
import x10.constraint.XTerm;
import x10.constraint.XTerms;
import x10.types.X10Context;
import x10.types.X10MethodInstance;
import x10.types.X10TypeMixin;
import x10.types.X10TypeSystem;
import x10.types.X10TypeSystem_c;
import x10.types.checker.Converter;
import x10.types.checker.PlaceChecker;
import x10.types.constraints.CConstraint;
import x10.util.Synthesizer;
import x10.visit.ConstantPropagator;

/**
 * Optimize loops of the form:  for (formal in domain) S.
 * If domain is, or can be lowered to, a Region, that is rectangular with know rank,
 * tranform into a nest of traditional for-loops with iterates of type int.
 * 
 * @author vj
 * @author Bowen Alpern
 */
public class ForLoopOptimizer extends ContextVisitor {


    private static final Name ITERATOR = Name.make("iterator");
    private static final Name HASNEXT  = Name.make("hasNext");
    private static final Name REGION   = Name.make("region");
    private static final Name DIST     = Name.make("dist");
    private static final Name NEXT     = Name.make("next");
    private static final Name MAKE     = Name.make("make");
    private static final Name RANK     = Name.make("rank");
    private static final Name MIN      = Name.make("min");
    private static final Name MAX      = Name.make("max");
    private static final Name SET      = Name.make("set");

    private final X10TypeSystem  xts;
    private final X10NodeFactory xnf;
    private final Synthesizer    syn;

    public ForLoopOptimizer(Job job, TypeSystem ts, NodeFactory nf) {
        super(job, ts, nf);
        xts = (X10TypeSystem) ts;
        xnf = (X10NodeFactory) nf; 
        syn = new Synthesizer(xnf, xts);
    }

    public Node leaveCall(Node old, Node n, NodeVisitor v) throws SemanticException {
        if (n instanceof ForLoop)
            return visitForLoop((ForLoop_c) n);
        return n;
    }

    // this is dead code left around to point out the need to better optimize Regions where the limits are compile-time constant
    private Node oldVisitForLoop(ForLoop_c loop) {
        Type domainType = loop.domainType();
        Type regionType = syn.addRankConstraintToSelf(xts.Region(), 1, xts);
        regionType = syn.addRectConstraintToSelf(regionType);

        if (xts.isSubtype(domainType, regionType, context())) {
            // Now check if domain is actually a RegionMaker, i.e. a parsing of
            // e1..e2
            if (loop.domain() instanceof RegionMaker) {
                List<Expr> args = ((RegionMaker) loop.domain()).arguments();
                if (args.size() == 2) {
                    Expr low = args.get(0);
                    Expr high = args.get(1);
                    X10Formal xf = (X10Formal) loop.formal();
                    // Only handle the case |for ((i) in e1..e2) S| for now
                    if (xf.isUnnamed()) {
                        X10Formal index = (X10Formal) xf.vars().get(0);
                        Node n = syn.makeForLoop(loop.position(), index, low,
                                high, loop.body(), (X10Context) context());
                        return n;
                    }

                }

            }
        }
        return loop;
    }

    private static final boolean VERBOSE = false;
    
    /**
     * Transform a ForLoop with a Point iterate over a rectangular region to a nest of simple For loops.
     * <pre>
     * for (p:Point(k+1) in r) S ->
     *     {                                      // k=r.rank-1
     *        point = Rail.make[Int](k+1);        // if p is named
     *        mink=r.min(k); maxk=r.max(k);
     *        ...
     *        min0=r.min(0); max0=r.max(0); 
     *        for (i0 = min0; i0<=max0; i0+=1) {
     *           point(0) = i0;                   // if p is named
     *           ...
     *              for (ik = mink; ik<=maxk; ik+=1) {
     *                 point(k) = ik;             // if p is named
     *                 p = Point.make(point);     // if p is named
     *                 S
     *              }
     *        }
     *     }
     * </pre>
     * <tt>r</tt> can be an Array, a DistArray, a Dist, or a Region.
     * 
     * Also, desugars untransformed ForLoops, TODO: move this to the desugarer
     * <pre>
     * for (x in y) S -> for (i=y.iterator(); i.hasNext(); ) { x=i.next(); S }
     * </pre>
     * 
     * @param loop the ForLoop to be transformed
     * @return the transformed loop
     */
    public Node visitForLoop(ForLoop_c loop) {
        
        Position     pos        = loop.position();
        X10Formal    formal     = (X10Formal) loop.formal();
        Expr         domain     = loop.domain();
        Stmt         body       = loop.body();
        
        if (VERBOSE) {
            System.out.println("\nOptimizing ForLoop at " +pos);
            loop.prettyPrint(System.out);
            System.out.println();
        }

        // if domain <: DistArray, transform to Distribution
        if (xts.isX10DistArray(domain.type())) {
            if (VERBOSE) System.out.println("  domain is DistArray, tranforming to Dist");
            domain = createFieldRef(pos, domain, DIST);
            assert (null != domain);
        }

        // if domain <: Distribution, transform to Region
        if (((X10TypeSystem_c) xts).isDistribution(domain.type())) {
            if (VERBOSE) System.out.println("  domain is Dist, transforming to Region");
            domain = createFieldRef(pos, domain, REGION);
            assert (null != domain);
        }
        
        // if domain <: Array, transform to Region
        if (xts.isX10Array(domain.type())) {
            if (VERBOSE) System.out.println("  domain is Array, tranforming to Region");
            domain = createFieldRef(pos, domain, REGION);
            assert (null != domain);
        }

        X10Context   context    = (X10Context) context();
        List<Formal> formalVars = formal.vars();
        boolean      named      = !formal.isUnnamed();
        boolean      isRect     = X10TypeMixin.isRect(domain.type(), context);
        Integer      domainRank = (Integer) getPropertyConstantValue(domain, RANK);
        int          rank       = (null != domainRank) ? (int) domainRank :
                                  (null != formalVars) ? formalVars.size() : 
                                  -1;
        assert null == formalVars || formalVars.isEmpty() || formalVars.size() == rank;

        // transform rectangular regions of known rank 
        if (xts.isRegion(domain.type()) && isRect && rank > 0) {
            assert xts.isPoint(formal.declType());
            if (VERBOSE) System.out.println("  rectangular region, rank=" +rank+ " point=" +formal);
            
            List<Stmt> stmts      = new ArrayList<Stmt>();
            Name       prefix     = named ? formal.name().id() : Name.make("p");
            
            // cache the value of domain in a local temporary variable
            LocalDecl  domLDecl   = createLocalDecl(domain.position(), Flags.FINAL, Name.makeFresh(prefix), domain);
            stmts.add(domLDecl);
            
            // Prepare to redeclare the formal iterate as local Point variable (if the formal is not anonymous)
            Type       indexType  = null; // type of the formal var initializer (if any)
            LocalDecl  indexLDecl = null; // redeclaration of the formal var (if it has a name)
            if (named) {
                // create a rail to contain the value of the formal at each iteration
                Name       indexName  = Name.makeFresh(prefix);
                           indexType  = PlaceChecker.AddIsHereClause(xts.Rail(xts.Int()), context);
                List<Type> railType   = Collections.<Type>singletonList(xts.Int());
                Expr       indexInit  = createStaticCall(pos, xts.Rail(), MAKE, railType, createIntLit(rank));
                           indexLDecl = createLocalDecl(pos, Flags.FINAL, indexName, indexType, indexInit);
                // add the declaration of the index rail to the list of statements to be executed before the loop nest
                stmts.add(indexLDecl);
            }
            
            // create the loop nest (from the inside out)
            for (int r=rank-1; 0<=r; r--) {
                
                // create new names for the r-th iterate and limits
                Name varName  = null == formalVars || formalVars.isEmpty() ? Name.makeFresh(prefix) 
                                                                           : Name.makeFresh(formalVars.get(r).name().id());
                Name minName  = Name.makeFresh(varName+ "min");
                Name maxName  = Name.makeFresh(varName+ "max");
                
                // create an AST node for the calls to domain.min() and domain.max()
                Expr minVal   = createInstanceCall(pos, createLocal(domain.position(), domLDecl), MIN, createIntLit(r));
                Expr maxVal   = createInstanceCall(pos, createLocal(domain.position(), domLDecl), MAX, createIntLit(r));
                
                // create an AST node for the declaration of the temporary locations for the r-th var, min, and max
                LocalDecl minLDecl = createLocalDecl(pos, Flags.FINAL, minName, minVal);
                LocalDecl maxLDecl = createLocalDecl(pos, Flags.FINAL, maxName, maxVal);
                LocalDecl varLDecl = createLocalDecl(pos, Flags.NONE, varName, createLocal(pos, minLDecl));
                
                // add the declarations for the r-th min and max to the list of statements to be executed before the loop nest
                stmts.add(minLDecl);
                stmts.add(maxLDecl);
                
                // create expressions for the second and third positions in the r-th for clause
                Expr cond = createBinary(domain.position(), createLocal(pos, varLDecl), Binary.LE, createLocal(pos, maxLDecl));
                Expr update = createAssign(domain.position(), createLocal(pos, varLDecl), Assign.ADD_ASSIGN, createIntLit(1));
                
                List<Stmt> bodyStmts = new ArrayList<Stmt>(); 
                
                if (null != formalVars && !formalVars.isEmpty()) {
                    bodyStmts.add(transformFormalToLocalDecl((X10Formal) formalVars.get(r), createLocal(pos, varLDecl)));
                }
                
                // concoct declaration for formal, in case it might be referenced in the body
                if (named) {
                    // set the r-th slot int the index rail to the current value of the r-th iterate
                    Expr setExpr = createInstanceCall( pos, 
                                                       createLocal(pos, indexLDecl), 
                                                       SET, 
                                                       createLocal(pos, varLDecl), 
                                                       createIntLit(r) );
                    bodyStmts.addAll(convertToStmtList(setExpr));
                    if (r+1 == rank) { // the innermost loop
                        // declare the formal variable as a local and initialize it to the index rail
                        Expr formExpr = createStaticCall(pos, formal.declType(), MAKE, createLocal(pos, indexLDecl));
                        bodyStmts.add(transformFormalToLocalDecl(formal, formExpr));
                    }
                }
                bodyStmts.add(body);
                body = createBlock(pos, bodyStmts);
                
                // create the AST node for the r-th concocted for-statement
                body = createStandardFor(pos, varLDecl, cond, update, body);
                
            }
            
            stmts.add(body);
            Block result = createBlock(pos, stmts);
            if (VERBOSE) result.dump(System.out);
            return result;
        }

        assert (xts.isSubtype(domain.type(), xts.Iterable(xts.Any()), context)); 
        Name iterName         = named ? Name.makeFresh(formal.name().id()) : Name.makeFresh();
        Expr iterInit         = createInstanceCall(pos, domain, ITERATOR);
        LocalDecl iterLDecl   = createLocalDecl(pos, Flags.FINAL, iterName, iterInit);
        Expr hasExpr          = createInstanceCall(pos, createLocal(pos, iterLDecl), HASNEXT);
        Expr nextExpr         = createInstanceCall(pos, createLocal(pos, iterLDecl), NEXT);
        if (!xts.typeEquals(nextExpr.type(), formal.declType(), context)) {
            Expr newNextExpr  = createCoercion(pos, nextExpr, formal.declType());
            if (null == newNextExpr)
                throw new InternalCompilerError("Unable to cast "+nextExpr+" to the iterated type "+formal.declType(), pos);
            nextExpr = newNextExpr;
        }
        List<Stmt> bodyStmts = new ArrayList<Stmt>();
        LocalDecl varLDecl    = transformFormalToLocalDecl(formal, nextExpr);
        bodyStmts.add(varLDecl);
        if (null != formalVars && !formalVars.isEmpty()) try {
            bodyStmts.addAll(formal.explode(this));
        } catch (SemanticException e) {
            throw new InternalCompilerError("We cannot explode the formal.  Huh?", formal.position(), e);
        }
        bodyStmts.add(body);
        Stmt result           = createStandardFor(pos, iterLDecl, hasExpr, createBlock(pos, bodyStmts));
        if (VERBOSE) result.dump(System.out);
        return result;
    }

    // General helper methods

    /** 
     * Obtain the constant value of a property of an expression, if that value is known at compile time.
     * 
     * @param expr the Expr whose property is to be extracted
     * @param name the Name of the property to extract
     * @return the value of the named property of expr if it is a compile-time constant, or null if none
     * TODO: move into ASTQuery
     */
    public Object getPropertyConstantValue(Expr expr, Name name) {
        FieldInstance propertyFI = X10TypeMixin.getProperty(expr.type(), name);
        if (null == propertyFI) return null;
        Expr propertyExpr = createFieldRef(expr.position(), expr, propertyFI);
        if (null == propertyExpr) return null;
        return ConstantPropagator.constantValue(propertyExpr);
    }

    /**
     * Add a constraint to the type that binds a given property to a given value.
     * 
     * @param type the Type to be constrained
     * @param name the Name of a property of type
     * @param value the value of the named property for this type
     * @return the type with the additional constraint {name==value}, or null if no such property
     * TODO: move into Synthesizer
     */
    public static Type addPropertyConstraint(Type type, Name name, XTerm value) {
        XTerm property = X10TypeMixin.findOrSynthesize(type, name);
        if (null == property) return null;
        return X10TypeMixin.addBinding(type, property, value);
    }

    /**
     * Add a constraint to the type that binds a given property to a given value.
     * 
     * @param type the Type to be constrained
     * @param name the Name of a property of type
     * @param value the value of the named property for this type
     * @return the type with the additional constraint {name==value}, or null if no such property
     * TODO: move into Synthesizer
     */
    public static Type addPropertyConstraint(Type type, Name name, Object value) {
        return addPropertyConstraint(type, name, XTerms.makeLit(value));
    }

    /**
     * Add a self constraint to the type that binds self to a given value.
     * 
     * @param type the Type to be constrained
     * @param value the value of self for this type
     * @return the type with the additional constraint {self==value}, or null if the proposed
     * binding is inconsistent
     * TODO: move into Synthesizer
     */
    public static Type addSelfConstraint(Type type, XTerm value) {
        try {
            return X10TypeMixin.addSelfBinding(type, value);
        } catch (XFailure e) {
            return null;
        }
    }

    // helper methods that build AST Nodes
    // These methods could move into the X10 Synthesizer.

    // helper method that create subclasses of Stmt

    /**
     * Turn a formal parameter into local variable declaration.
     * 
     * @param formal the parameter to be transformed
     * @param init an expression representing the initial value of new local variable
     * @return the declaration for a local variable with the same behavior as formal
     * TODO: move into Synthesizer
     */
    public LocalDecl transformFormalToLocalDecl(X10Formal formal, Expr init) {
        return xnf.LocalDecl(formal.position(), formal.flags(), formal.type(), formal.name(), init).localDef(formal.localDef());
    }

    /**
     * Create a declaration for a local variable from a local type definition.
     * 
     * @param pos the Position of the declaration
     * @param def the definition of the declared local variable
     * @param init the Expr representing the initial value of the declared local variable
     * @return the LocalDecl representing the declaration of the local variable
     * TODO: move into Synthesizer
     */
    public LocalDecl createLocalDecl(Position pos, LocalDef def, Expr init) {
        return xnf.LocalDecl( pos, 
                              xnf.FlagsNode(pos, def.flags()),
                              xnf.CanonicalTypeNode(pos, def.type().get()), 
                              xnf.Id(pos, def.name()),
                              init ).localDef(def);
    }

    /**
     * Create a declaration for a local variable from scratch.
     * (A local variable definition is created as a side-effect and may be retrieved from the result.)
     * 
     * @param pos the Position of the declaration
     * @param flags the Flags ("static", "public", "var") for the declared local variable
     * @param name the Name of the declared local variable
     * @param type the Type of the declared local variable
     * @param init an Expr representing the initial value of the declared local variable
     * @return the LocalDecl representing the declaration of the local variable
     * TODO: move into Synthesizer
     */
    public LocalDecl createLocalDecl(Position pos, Flags flags, Name name, Type type, Expr init) {
        LocalDef def = xts.localDef(pos, flags, Types.ref(type), name);
        return createLocalDecl(pos, def, init);
    }

    /**
     * Create a declaration for a local variable using the type of the initializer.
     * 
     * @param pos the Position of the declaration
     * @param flags the Flags ("static", "public", "var") for the declared local variable
     * @param name the Name of the declared local variable
     * @param init an Expr representing the initial value of the declared local variable
     * @return the LocalDecl representing the declaration of the local variable
     * TODO: move into Synthesizer
     */
    public LocalDecl createLocalDecl(Position pos, Flags flags, Name name, Expr init) {
        return createLocalDecl(pos, flags, name, init.type(), init);
    }

    /** 
     * Create a traditional C-style 'for' loop.
     * This form assumes that the update part of the for header is empty.
     * 
     * @param pos the Position of the loop is the source program
     * @param init the declaration that initializes the iterate
     * @param cond the condition governing whether the body should continue to be executed
     * @param body the body of the loop to be executed repeatedly
     * @return the synthesized 'for' loop
     * TODO: move into Synthesizer
     */
    public For createStandardFor(Position pos, LocalDecl init, Expr cond, Stmt body) {
        return xnf.For( pos, 
                        Collections.<ForInit>singletonList(init), 
                        cond, 
                        Collections.<ForUpdate>emptyList(), 
                        body );
    }

    /** 
     * Create a traditional C-style 'for' loop.
     * 
     * @param pos the Position of the loop is the source program
     * @param init the declaration that initializes the iterate
     * @param cond the condition governing whether the body should continue to be executed
     * @param update the statement to increment the iterate after each execution of the body
     * @param body the body of the loop to be executed repeatedly
     * @return the synthesized for-loop
     * TODO: move into Synthesizer
     */
    public For createStandardFor(Position pos, LocalDecl init, Expr cond, Expr update, Stmt body) {
        return xnf.For( pos, 
                        Collections.<ForInit>singletonList(init), 
                        cond, 
                        Collections.<ForUpdate>singletonList(xnf.Eval(pos, update)), 
                        body );
    }

    /** 
     * Create a block of statements from a list.
     * 
     * @param pos the Position of the block in the source code
     * @param stmts the Stmt's to be included in the block
     * @return the synthesized Block
     * TODO: move into Synthesizer
     */
    public Block createBlock(Position pos, List<Stmt> stmts) {
        return xnf.Block(pos, stmts);
    }

    /**
     * Create a block of statements from individual terms (statements and/or expressions).
     * If a term is already a Stmt, it is used as is.
     * If it is an Expr, the Stmt is its evaluation.
     * Otherwise an InvalidArgumentException is thrown.
     * 
     * @param pos the Position of the block in the source code
     * @param terms the sequence of terms to become statements of the block
     * @return the synthesized Block of terms turned to statements
     * @throws IllegalArgumentException if one of the terms is not a Stmt or an Expr
     * TODO: move into Synthesizer
     */
    public Block createBlock(Position pos, Term... terms) {
        return createBlock(pos, convertToStmtList(terms));
    }

    /**
     * Convert individual terms (statements and/or expressions) to a list of statements.
     * If a term is already a Stmt, it is used as is.
     * If it is an Expr, the Stmt is its evaluation.
     * Otherwise an InvalidArgumentException is thrown.
     * 
     * @param terms the sequence of terms
     * @return the newly constructed list of statements
     * @throws IllegalArgumentException if one of the terms is not a Stmt or an Expr
     * TODO: move into Synthesizer
     */
    public List<Stmt> convertToStmtList(Term... terms) {
        List<Stmt> stmts = new ArrayList<Stmt> (terms.length);
        for (Term term : terms) {
            if (term instanceof Expr) {
                term = xnf.Eval(term.position(), (Expr) term);
            } else if (!(term instanceof Stmt)) {
                throw new IllegalArgumentException("Invalid argument type: "+term.getClass());
            }
            stmts.add((Stmt) term);
        }
        return stmts;
    }

    // helper methods that create subclasses of Expr

    /**
     * Create an IntLit node representing a given integer literal.
     * 
     * @param val the int value to be represented
     * @return an IntLit node representing the literal integer val
     * TODO: move into Synthesizer
     */
    public IntLit createIntLit(int val) {
        try {
            return (IntLit) xnf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, val).typeCheck(this);
        } catch (SemanticException e) {
            throw new InternalCompilerError("Int literal with value "+val+" would not typecheck", e);
        }
    }

    /** 
     * Create a local variable reference.
     * 
     * @param pos the Position of the reference in the source code
     * @param decl the declaration of the local variable
     * @return the synthesized Local variable reference
     * TODO: move into Synthesizer
     */
    public Local createLocal(Position pos, LocalDecl decl) {
        return createLocal(pos, decl.localDef().asInstance());
    }

    /** 
     * Create a local variable reference.
     * 
     * @param pos the Position of the reference in the source code
     * @param li a type system object representing this local variable
     * @return the synthesized Local variable reference
     * TODO: move into Synthesizer
     */
    public Local createLocal(Position pos, LocalInstance li) {
        return (Local) xnf.Local(pos, xnf.Id(pos, li.name())).localInstance(li).type(li.type());
    }

    /** 
     * Create a binary expression.
     * 
     * @param pos the Position of the expression in the source code
     * @param left the first operand
     * @param op the operator
     * @param right the second operand
     * @return the synthesized Binary expression: (left op right)
     * TODO: move into Synthesizer
     */
    public Binary createBinary(Position pos, Expr left, polyglot.ast.Binary.Operator op, Expr right) {
        try {
            return (Binary) xnf.Binary(pos, left, op, right).typeCheck(this);
        } catch (SemanticException e) { 
           throw new InternalCompilerError("Attempting to synthesize a Binary that cannot be typed", pos, e);
        }
    }

    /**
     * Create an assignment expression.
     * 
     * @param pos the Position of the assignment in the source code
     * @param target the lval being assigned to
     * @param op the assignment operator
     * @param source the right-hand-side of the assignment
     * @return the synthesized Assign expression: (target op source)
     * TODO: move into Synthesizer
     */
    public Assign createAssign(Position pos, Expr target, Operator op, Expr source) {
        return (Assign) xnf.Assign(pos, target, op, source).type(target.type());
    }

    /**
     * Create a coercion (implicit conversion) expression.
     * 
     * @param pos the Position of the cast in the source code
     * @param expr the Expr being cast
     * @param toType the resultant type
     * @return the synthesized Cast expression: (expr as toType), or null if the conversion is invalid
     * TODO: move into Synthesizer
     */
    public X10Cast createCoercion(Position pos, Expr expr, Type toType) {
        try {
            // FIXME: Have to typeCheck, because the typechecker has already desugared this to a conversion chain
            return (X10Cast) xnf.X10Cast( pos, 
                                          xnf.CanonicalTypeNode(pos, toType), 
                                          expr, 
                                          Converter.ConversionType.UNKNOWN_IMPLICIT_CONVERSION ).typeCheck(this);
        } catch (SemanticException e) {
            // return null; // FIXME: the following code is a work around for XTENLANG-1335
            try {
                return (X10Cast) xnf.X10Cast( pos, 
                                              xnf.CanonicalTypeNode(pos, toType), 
                                              expr, 
                                              Converter.ConversionType.UNCHECKED ).typeCheck(this);
            } catch (SemanticException x) {
                return null;
            }
        }
    }

    /**
     * Create a reference to a field of an object or struct.
     * If the receiver has a self constraint, propagate the constraint appropriately.
     * 
     * @param pos the Position of the reference
     * @param receiver the object or struct containing the field
     * @param name the Name of the field
     * @return the synthesized Field expression: (container . name), or null if no such field
     * TODO: move into Synthesizer
     */
    public Field createFieldRef(Position pos, Expr receiver, Name name) {
        final Type type = receiver.type();
        FieldInstance fi = X10TypeMixin.getProperty(type, name);
        if (null == fi) {
            fi = type.toClass().fieldNamed(name);
        }
        if (null == fi) return null;
        return createFieldRef(pos, receiver, fi);
    }

    /** 
     * Create a reference to a field of an object or struct.
     * If the receiver has a self constraint, propagate the constraint appropriately.
     * 
     * @param pos the Position of the reference in the source code
     * @param receiver the object or struct containing the field
     * @param fi a type system object representing this field
     * @return the synthesized Field expression
     * TODO: move into Synthesizer
     */
    public Field createFieldRef(Position pos, Expr receiver, FieldInstance fi) {
        Field f       = xnf.Field(pos, receiver, xnf.Id(pos, fi.name())).fieldInstance(fi);
        Type type     = fi.type();
        // propagate self binding (if any)
        CConstraint c = X10TypeMixin.realX(receiver.type());
        XTerm term    = X10TypeMixin.selfVarBinding(c);  // the RHS of {self==x} in c
        if (term != null) {
            type = addSelfConstraint(type, xts.xtypeTranslator().trans(c, term, fi));
            assert (null != type);
        }
        return (Field) f.type(type);
    }

    /** 
     * Create a Call to a static method.
     * 
     * @param pos the Position of the call in the source code
     * @param container the class that defines the static method
     * @param name the name of the static method
     * @param args the arguments to the static method
     * @return the synthesized Call to the method of the given type with the required name taking the prescribed arguments,
     * or null if no such method
     * TODO: move to Synthesizer
     */
    public X10Call createStaticCall(Position pos, Type container, Name name, Expr... args) {
        X10MethodInstance mi = createMethodInstance(container, name, args);
        if (null == mi) return null;
        return createStaticCall(pos, mi, args);
    }

    /**
     * Create a Call to a generic static method.
     * 
     * @param pos the Position of the call in the source code
     * @param container the class of the generic static method
     * @param name the name of the generic static method
     * @param typeArgs the type arguments to the generic static method
     * @param args the arguments to the generic static method
     * @return the synthesized Call to the method of the given type
     * with the required name taking the prescribed type arguments and arguments,
     * or null if no such method
     * TODO: move to Synthesizer
     */
    public X10Call createStaticCall(Position pos, Type container, Name name, List<Type> typeArgs, Expr... args) {
        X10MethodInstance mi = createMethodInstance(container, name, typeArgs, args);
        if (null == mi) return null;
        return createStaticCall(pos, mi, args);
    }

    /**
     * Create a Call to a static method.
     * The name and type of method (and any generic type arguments) are contained in the method instance.
     * 
     * @param pos the Position of the call in the source code
     * @param mi a type system object representing the static method being called
     * @param args the arguments to the call to the static method
     * @return the synthesized Call to the specified method taking the prescribed arguments
     * TODO: move to Synthesizer
     */
    public X10Call createStaticCall(Position pos, X10MethodInstance mi, Expr... args) {
        List<Type> typeParams = mi.typeParameters();
        List<TypeNode> typeParamNodes = new ArrayList<TypeNode>();
        for (Type t : typeParams) {
            typeParamNodes.add(xnf.CanonicalTypeNode(pos, t));
        }
        return (X10Call) xnf.X10Call( pos, 
                                      xnf.CanonicalTypeNode(pos, mi.container()),
                                      xnf.Id(pos, mi.name()), 
                                      typeParamNodes,
                                      Arrays.asList(args) ).methodInstance(mi).type(mi.returnType());
    }

    /** 
     * Create a Call to an instance method.
     * 
     * @param pos the Position of the call in the source code
     * @param receiver the object on which the instance method is being called
     * @param name the Name of the instance method
     * @param args the arguments to the instance method
     * @return the synthesized Call to a method on the specified receiver with the required name and prescribed arguments,
     * or null if no such method
     * TODO: move to Synthesizer
     */
    public X10Call createInstanceCall(Position pos, Expr receiver, Name name, Expr... args) {
        X10MethodInstance mi = createMethodInstance(receiver, name, args);
        if (null == mi) return null;
        return createInstanceCall(pos, receiver, mi, args);
    }

    /** 
     * Create a Call to a generic instance method.
     * 
     * @param pos the Position of the call in the source code
     * @param receiver the object on which the generic instance method is being called
     * @param name the Name of the generic instance method
     * @param typeArgs the type arguments to the generic instance method
     * @param args the arguments to the generic instance method
     * @return the synthesized Call to the method on the specified receiver 
     * with the required name taking the prescribed type arguments and arguments, 
     * or null if no such method
     * TODO: move to Synthesizer 
     */
    public X10Call createInstanceCall(Position pos, Expr receiver, Name name, List<Type> typeArgs, Expr... args) {
        X10MethodInstance mi = createMethodInstance(receiver, name, typeArgs, args);
        if (null == mi) return null;
        return createInstanceCall(pos, receiver, mi, args);
    }

    /**
     * Create a Call to an instance method.
     * The Name and any type arguments to the instance method are encoded in the method instance.
     * 
     * @param pos the Position of the call in the source code
     * @param receiver the object on which the instance method is being called
     * @param mi a type system object representing the instance method being called
     * @param args the arguments to the instance method
     * @return the synthesized Call to the specified method on the given receiver taking the prescribed arguments,
     * or null if no such method
     * TODO: move to Synthesizer 
     */
    public X10Call createInstanceCall(Position pos, Expr receiver, X10MethodInstance mi, Expr... args) {
        List<Type> typeParams = mi.typeParameters();
        List<TypeNode> typeParamNodes = new ArrayList<TypeNode>();
        for (Type t : typeParams) {
            typeParamNodes.add(xnf.CanonicalTypeNode(pos, t));
        }
        return (X10Call) xnf.X10Call( pos, 
                                      receiver, 
                                      xnf.Id(pos, mi.name()),
                                      typeParamNodes,
                                      Arrays.asList(args) ).methodInstance(mi).type(mi.returnType());
    }

    // helper methods that return type system instances
    
    /**
     * Create a type system object representing a specified Generic method (either static or instance).
     * 
     * @param container the type (static method) or receiver (instance method) of the method call
     * @param name the Name of the method to be called
     * @param typeArgs the type arguments to the method
     * @param args the arguments to the method
     * @return the synthesized method instance for this method call
     * @throws InternalCompilerError if the required method instance cannot be created
     * TODO: move to a type system helper class
     */
    public X10MethodInstance createMethodInstance(Type container, Name name, List<Type> typeArgs, Expr... args) {
        List<Type> argTypes = getExprTypes(args);
        try {
            return xts.findMethod(container, xts.MethodMatcher(container, name, typeArgs, argTypes, context()));
        } catch (SemanticException e) {
            throw new InternalCompilerError("Unable to find required method instance", container.position(), e);
        }
    }

    /**
     * Create a type system object representing a specified method (either static or instance).
     * 
     * @param container the type (static method) or receiver (instance method) of the method call
     * @param name the Name of the method to be called
     * @param args the arguments to the method
     * @return the synthesized method instance for this method call
     * throws InternalCompilerError if the required method instance cannot be created
     * TODO: move to a type system helper class
     */
    public X10MethodInstance createMethodInstance(Type container, Name name, Expr... args) {
        List<Type> argTypes = getExprTypes(args);
        try {
            return xts.findMethod(container, xts.MethodMatcher(container, name, argTypes, context()) );
        } catch (SemanticException e) {
            throw new InternalCompilerError("Unable to find required method instance", container.position(), e);
        }
    }

    /**
     * Create a type system object representing a specified Generic instance method.
     * 
     * @param receiver the receiver of the (instance) method call
     * @param name the Name of the method to be called
     * @param typeArgs the type arguments to the method
     * @param args the arguments to the method
     * @return the synthesized method instance for this method call
     * @throws InternalCompilerError if the required method instance cannot be created
     * TODO: move to a type system helper class
     */
    public X10MethodInstance createMethodInstance(Expr receiver, Name name, List<Type> typeArgs, Expr... args) {
        return createMethodInstance(receiver.type(), name, typeArgs, args);
    }

    /**
     * Create a type system object representing a specified instance method.
     * 
     * @param receiver the receiver of the (instance) method call
     * @param name the Name of the method to be called
     * @param args the arguments to the method
     * @return the synthesized method instance for this method call
     * throws InternalCompilerError if the required method instance cannot be created
     * TODO: move to a type system helper class
     */
    public X10MethodInstance createMethodInstance(Expr receiver, Name name, Expr... args) {
        return createMethodInstance(receiver.type(), name, args);
    }

    /** 
     * Find the Types for a sequence of expressions.
     * 
     * @param args the sequence of expressions to be typed
     * @return a List of the Types of the args
     */
    private static List<Type> getExprTypes(Expr... args) {
        List<Type> argTypes = new ArrayList<Type> (args.length);
        for (Expr a : args) {
            argTypes.add(a.type());
        }
        return argTypes;
    }

}
