/*
 * Created by vj on Jan 23, 2005
 */
package polyglot.ext.x10.ast;

import java.util.ArrayList;
import java.util.List;

import polyglot.ast.AmbExpr;
import polyglot.ast.Expr;
import polyglot.ast.IntLit;
import polyglot.ast.LocalDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Stmt;
import polyglot.ast.TypeNode;
import polyglot.ext.jl.ast.Formal_c;
import polyglot.main.Report;
import polyglot.types.Context;
import polyglot.types.Flags;
import polyglot.types.LocalInstance;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.TypedList;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

import polyglot.ext.x10.types.X10Type;
import polyglot.ext.x10.types.constr.C_Local_c;
import polyglot.ext.x10.types.constr.Constraint;
import polyglot.ext.x10.types.constr.Constraint_c;
import polyglot.ext.x10.visit.X10PrettyPrinterVisitor;

/**
 * An immutable representation of an X10Formal, which is of the form
 *   Flag Type VarDeclaratorId
 * Recall that a VarDeclaratorId may have additional variable bindings.
 * @author vj Jan 23, 2005
 * @author igor Jan 13, 2006
 */
public class X10Formal_c extends Formal_c implements X10Formal {
	public static final AmbExpr[] NO_VARS = new AmbExpr[0];
	public static final LocalInstance[] NO_LOCALS = new LocalInstance[0];

	/* Invariant: vars != null */
	protected AmbExpr[] vars;
	/* Invariant after disambiguation:
	     lis != null && lis.length == vars.length */
    protected LocalInstance[] lis;

	boolean unnamed;

	public X10Formal_c(Position pos, Flags flags, TypeNode type,
	                   String name, AmbExpr[] vars)
	{
		super(pos, flags, type,
				name == null ? X10PrettyPrinterVisitor.getId() : name);
		this.vars = (vars == null) ? NO_VARS : vars;
		this.unnamed = name == null;
	}

	public boolean isUnnamed() {
		return unnamed;
	}

	public boolean isDisambiguated() {
		return (type == null || type.isDisambiguated()) && lis != null && super.isDisambiguated();
	}

	/** Get the local instances of the bound variables. */
	public LocalInstance[] localInstances() {
		return lis;
	}

	/** Set the local instances of the bound variables. */
	public X10Formal localInstances(LocalInstance[] lis) {
		if (this.lis == lis) return this;
		X10Formal_c n = (X10Formal_c) copy();
		n.lis = lis;
		return n;
	}

	/* (non-Javadoc)
	 * @see polyglot.ext.jl.ast.Formal#addDecls()
	 */
	public void addDecls(Context c) {
		super.addDecls(c);
		for (int i = 0; i < lis.length; i++)
			c.addVariable(lis[i]);
	}

	public Node buildTypes(TypeBuilder tb) throws SemanticException {
		X10Formal_c n = (X10Formal_c) super.buildTypes(tb);
		TypeSystem ts = tb.typeSystem();
		if (vars == NO_VARS)
			return n.localInstances(NO_LOCALS);
		LocalInstance[] lis = new LocalInstance[vars.length];
		for (int i = 0; i < lis.length; i++) {
			AmbExpr v = vars[i];
			lis[i] = ts.localInstance(v.position(), flags(), ts.Int(), v.name());
		}
		return n.localInstances(lis);
	}

	 public Node typeCheck(TypeChecker tc) throws SemanticException {
	 
     // Ensure that the LocalInstance is updated with the possibly new type (w/ depclause)
			X10Formal_c result= (X10Formal_c) super.typeCheck(tc);
			// Ensure that the LocalInstance is updated with the 
			// possibly new type (w/ depclause)
			LocalInstance li = result.li;
			li.setType(declType());
			// If the local variable is final, replace T by T(:self==t)
			if (li.flags().isFinal()) {
				X10Type oldType = (X10Type) li.type();
				Constraint c = Constraint_c.addVarWhoseTypeThisIs(C_Local_c.makeSelfVar(li),oldType.depClause());
				X10Type newType = oldType.makeVariant(c,oldType.typeParameters());
				li.setType(newType);
			}
			
			return result;
		
	 }
	public void dump(CodeWriter w) {
		super.dump(w);
		if (vars != NO_VARS) {
			w.write("(vars [");
			for (int i = 0; i < vars.length; i++) {
				if (lis != null) {
					w.allowBreak(4, " ");
					w.begin(0);
					w.write("(instance " + lis[i] + ")");
					w.end();
				}
				w.allowBreak(4, " ");
				w.begin(0);
				w.write("(name " + vars[i] + ")");
				w.end();
			}
			w.write("])");
		}
	}

	private String translateVars() {
		StringBuffer sb = new StringBuffer();
		if (vars != NO_VARS) {
			sb.append("[");
			for (int i = 0; i < vars.length; i++)
				sb.append(i > 0 ? "," : "").append(vars[i].name());
			sb.append("]");
		}
		return sb.toString();
	}

    public String toString() {
        return super.toString() + translateVars();
    }

	/* (non-Javadoc)
	 * @see polyglot.ext.x10.ast.X10Formal#hasExplodedVars()
	 */
	public boolean hasExplodedVars() {
		return vars != NO_VARS;
	}

	/**
	 * Create a local variable declaration for an exploded var,
	 * at the given type, name and with the given initializer.
	 * The exploded variable is implicitly final.
	 *
	 * @param nf
	 * @param pos
	 * @param type
	 * @param name
	 * @param li
	 * @param init
	 * @return
	 */
	protected static LocalDecl makeLocalDecl(NodeFactory nf, Position pos,
											 Flags flags, TypeNode type,
											 String name, LocalInstance li,
											 Expr init)
	{
		/* boolean allCapitals = name.equals(name.toUpperCase());
		// vj: disable until we have more support for declarative programming in X10.
		Flags f = (false || allCapitals ? flags.set(Flags.FINAL) : flags);
		 */
		return nf.LocalDecl(pos, flags.set(Flags.FINAL), type, name, init)
					.localInstance(li);
	}

	/**
	 * Return the initialization statements for the exploding variables.
	 *
	 * @param nf
	 * @param ts
	 * @return
	 */
	public List/*<Stmt>*/ explode(NodeFactory nf, TypeSystem ts) {
		return explode(nf, ts, name(), position(), flags(), vars, localInstance(), lis);
	}

	/* (non-Javadoc)
	 * @see polyglot.ext.x10.ast.X10Formal#explode(polyglot.ast.NodeFactory, polyglot.types.TypeSystem)
	 */
	public List/*<Stmt>*/ explode(NodeFactory nf, TypeSystem ts, Stmt s) {
		List/*<Stmt>*/ init = this.explode(nf, ts);
		if (s != null)
			init.add(s);
		return init;
	}

	public List/*<Stmt>*/ explode(NodeFactory nf, TypeSystem ts, List/*<Stmt>*/ s, boolean prepend) {
		List/*<Stmt>*/ init = this.explode(nf, ts);
		if (s != null) {
			if (prepend) init.addAll(s);
			else init.addAll(0, s);
		}
		return init;
	}

	/**
	 * Return the initialization statements for the exploding variables.
	 *
	 * @param nf
	 * @param ts
	 * @param name
	 * @param pos
	 * @param flags
	 * @param vars
	 * @param lis
	 * @return
	 */
	private static List/*<Stmt>*/ explode(NodeFactory nf, TypeSystem ts,
										  String name, Position pos,
										  Flags flags, AmbExpr[] vars,
										  LocalInstance bli,
										  LocalInstance[] lis)
	{
		if (vars == null || vars == NO_VARS) return null;
		X10NodeFactory x10nf = (X10NodeFactory) nf;
		List/*<Stmt>*/ stmts = new TypedList(new ArrayList(vars.length), Stmt.class, false);
		Expr arrayBase =
			(bli == null) ? nf.AmbExpr(pos, name)
						  : (Expr) nf.Local(pos, name).localInstance(bli).type(bli.type());
		TypeNode intType = x10nf.CanonicalTypeNode(pos, ts.Int());
		for (int i = 0; i < vars.length; i++) {
			// int arglist(i) = name[i];
			AmbExpr var = vars[i];
			Expr index = x10nf.IntLit(var.position(), IntLit.INT, i).type(ts.Int());
			Expr init = x10nf.X10ArrayAccess1(var.position(), arrayBase, index).type(ts.Int());
			LocalInstance li = lis != null
				? lis[i]
				: ts.localInstance(var.position(), flags, ts.Int(), var.name());
			Stmt d = makeLocalDecl(nf, var.position(), flags, intType, var.name(), li, init);
			stmts.add(d);
		}
		return stmts;
	}

	/**
	 * Return the initialization statements for the exploding variables
	 * early.
	 *
	 * @param nf
	 * @param ts
	 * @param name
	 * @param pos
	 * @param flags
	 * @param vars
	 * @return
	 */
	public static List/*<Stmt>*/ explode(NodeFactory nf, TypeSystem ts,
										 String name, Position pos,
										 Flags flags, AmbExpr[] vars)
	{
		return explode(nf, ts, name, pos, flags, vars, null, null);
	}
}

