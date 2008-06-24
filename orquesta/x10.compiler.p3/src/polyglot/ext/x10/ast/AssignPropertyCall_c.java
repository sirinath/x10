/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
/**
 * 
 */
package polyglot.ext.x10.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import polyglot.ast.Assign;
import polyglot.ast.Assign_c;
import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Return;
import polyglot.ast.Stmt;
import polyglot.ast.Stmt_c;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.ext.x10.types.X10ConstructorDef;
import polyglot.ext.x10.types.X10ConstructorInstance;
import polyglot.ext.x10.types.X10ParsedClassType;
import polyglot.frontend.Job;
import polyglot.parse.Name;
import polyglot.types.Context;
import polyglot.types.FieldInstance;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.util.TypedList;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

/**
 * @author vj
 *
 */
public class AssignPropertyCall_c extends Stmt_c implements AssignPropertyCall {

	List<TypeNode> typeArgs;
	List<Expr> arguments;

	
	/**
	 * @param pos
	 * @param target
	 * @param name
	 * @param arguments
	 */
	public AssignPropertyCall_c(Position pos, List<TypeNode> typeArgs, List<Expr> arguments) {
		super(pos);
		this.typeArgs = TypedList.copyAndCheck(typeArgs, TypeNode.class, true);
		this.arguments = TypedList.copyAndCheck(arguments, Expr.class, true);
		
	}
	  public Term firstChild() {
		  return listChild(typeArgs,  listChild(arguments, null));
	    }

	/* (non-Javadoc)
	 * @see polyglot.ast.Term#acceptCFG(polyglot.visit.CFGBuilder, java.util.List)
	 */
	  public List acceptCFG(CFGBuilder v, List succs) {
		  if (! typeArgs.isEmpty())
			  v.visitCFGList(typeArgs, listChild(arguments, this), ENTRY);
		  v.visitCFGList(arguments, this, EXIT);
		  return succs;
	    }

	  
	  /** Return a copy of this node with this.expr equal to the given expr.
	   * @see polyglot.ext.x10.ast.Await#expr(polyglot.ast.Expr)
	   */
	  public AssignPropertyCall args( List<Expr> args ) {
		  if (args == arguments) return this;
		  AssignPropertyCall_c n = (AssignPropertyCall_c) copy();
		  n.arguments = TypedList.copyAndCheck(args, Expr.class, true);
		  return n;
	  }
	  
	  public List<Expr> args() {
		  return arguments;
	  }
	
	/** Return a copy of this node with this.expr equal to the given expr.
	 * @see polyglot.ext.x10.ast.Await#expr(polyglot.ast.Expr)
	 */
	public AssignPropertyCall typeArgs( List<TypeNode> args ) {
		if (args == typeArgs) return this;
		AssignPropertyCall_c n = (AssignPropertyCall_c) copy();
		n.typeArgs = TypedList.copyAndCheck(args, TypeNode.class, true);
		return n;
	}
	
	public List<TypeNode> typeArgs() {
	    return typeArgs;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("property");
		if (! typeArgs.isEmpty())
			sb.append(typeArgs);
		sb.append("(");
		boolean first = true;
		for (Expr e : arguments) {
			if (first) {
				first = false;
			}
			else {
				sb.append(", ");
			}
			sb.append(e);
		}
		sb.append(");");
		return sb.toString();
	}
	
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		TypeSystem ts = tc.typeSystem();
		Context ctx = tc.context();
		NodeFactory nf = tc.nodeFactory();
		Position pos = position();
		Job job = tc.job();
		if (! (ctx.inCode()) || ! (ctx.currentCode() instanceof X10ConstructorDef))
			throw new SemanticException("The statement property(...) must occur only in the body of a constructor.",
					position());
		X10ConstructorDef thisConstructor = null;
		thisConstructor = (X10ConstructorDef) ctx.currentCode();
		// Now check that the types of each actual argument are subtypes of the corresponding
		// property for the class reachable through the constructor.
		List<FieldInstance> definedProperties = 
			((X10ParsedClassType) thisConstructor.asInstance().container()).definedProperties();
		int pSize = definedProperties.size();
		int aSize = arguments.size();
		if (aSize != pSize) {
			throw new SemanticException("The statement property(...) must have the same " 
					+ " number of arguments as properties for the class.",
					position());
		}
		List<Stmt> s = new ArrayList<Stmt>(pSize);
		
		for (int i=0; i < aSize; i++) {
			Expr l = nf.Field(pos,nf.This(pos), nf.Id(pos, definedProperties.get(i).name()));
			l = (Expr) this.visitChild(l, tc);
			
//			 We fudge typechecking of the generating code as follows.
			// X10 Typechecking of the assignment statement is problematic since 	
			// the type of the field may have references to other fields, hence may use this,
			// But this doesnt exist yet. We will check all the properties simultaneously
			// in AssignPropertyBody. So we do not need to check it here. 
			Expr arg = arguments.get(i);
			Expr as = nf.Assign(pos, l, Assign.ASSIGN, arg);
			as = (Expr) as.type(arg.type()); // Fake the type.
			Stmt a = (Stmt) nf.Eval(pos, as);
			a = (Stmt) a.disambiguate(new AmbiguityRemover(tc));
			// a = (Stmt) a.visit(tc); Do not typecheck the statement a.
			s.add(a);
		}
		Node n = ((X10NodeFactory) nf).AssignPropertyBody(pos,s, thisConstructor, definedProperties).del().typeCheck(tc);
		return n;
	}
	
	/** Visit the children of the statement. */
	
	    public Node visitChildren(NodeVisitor v) {
	        List<TypeNode> typeArgs = visitList(this.typeArgs, v);
	        List<Expr> args = visitList(this.arguments, v);
		return typeArgs(typeArgs).args(args);
	    }
	    
	    Expr expr;
	    
	   
}


