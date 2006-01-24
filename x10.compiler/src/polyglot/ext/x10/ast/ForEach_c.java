/*
 * Created by vj on Dec 9, 2004
 */
package polyglot.ext.x10.ast;

import java.util.List;
import java.util.Collections;

import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Stmt;
import polyglot.ast.Formal;
import polyglot.ext.jl.ast.Field_c;
import polyglot.util.Position;
import polyglot.util.TypedList;

import polyglot.util.CodeWriter;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;

/**
 * An immutable representation of the X10 statement: foreach (i : D) S
 * @author vj Dec 9, 2004
 * @author Christian Grothoff
 */
public class ForEach_c extends X10Loop_c implements ForEach, Clocked {

	protected List clocks;

	/**
	 * @param pos
	 */
	public ForEach_c(Position pos) {
		super(pos);
	}

	/**
	 * @param pos
	 * @param formal
	 * @param domain
	 * @param body
	 */
	public ForEach_c(Position pos, Formal formal, Expr domain, List clocks, Stmt body) {
		super(pos, formal, domain, body);
		this.clocks = TypedList.copyAndCheck(clocks, Expr.class, true);
	}

	/** Expression */
	public List clocks() {
		return Collections.unmodifiableList(this.clocks);
	}

	/** clock */
	public Clocked clocks(List clocks) {
		ForEach_c n = (ForEach_c) copy();
		n.clocks = TypedList.copyAndCheck(clocks, Expr.class, true);
		return n;
	}

	public String toString() {
		return "foreach (" + formal + ":" + domain + ")" + body;
	}

	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.write("foreach(");
		printBlock(formal, w, tr);
		w.write(" : ");
		printBlock(domain, w, tr);
		w.write(") ");
		printSubStmt(body, w, tr);
	}

	public Node visitChildren(NodeVisitor v) {
		Formal formal = (Formal) visitChild(this.formal, v);
		Expr domain = (Expr) visitChild(this.domain, v);
		List clocks = visitList(this.clocks, v);
		Stmt body = (Stmt) visitChild(this.body, v);
		return ((Clocked) reconstruct(formal, domain, body)).clocks(clocks);
	}
}
