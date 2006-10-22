package polyglot.ext.x10.ast;

import polyglot.ast.Expr;
import polyglot.ast.LocalDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ext.jl.ast.LocalDecl_c;
import polyglot.ext.x10.types.X10Type;
import polyglot.ext.x10.types.constr.C_Local_c;
import polyglot.ext.x10.types.constr.Constraint;
import polyglot.ext.x10.types.constr.Constraint_c;
import polyglot.main.Report;
import polyglot.types.Flags;
import polyglot.types.LocalInstance;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

public class X10LocalDecl_c extends LocalDecl_c {
	
	public X10LocalDecl_c(Position pos, Flags flags, TypeNode type,
			String name, Expr init) {
		super(pos, flags, type, name, init);
	}
	
	public String shortToString() {
		return "<X10LocalDecl_c #" + hashCode() 
		// + " flags= |" + flags + "|"
		+(type() == null ? "" : " <TypeNode #" + type().hashCode()+"type=" + type().type() + ">")
		+ " name=|" + name() + "|"
		+ (init() == null ? "" : " <Expr #" + init().hashCode() +">")
		+ (localInstance() == null ? "" : " <LocalInstance #" + localInstance().hashCode() +">")
		+ ">";
	}
	
	public LocalDecl type(TypeNode type) {
		// System.out.println("[LocalDecl_c] ... setting type on |" + this.shortToString() + "|.");
		// System.out.println("[LocalDecl_c] ... to |" + type + "|.");
		LocalDecl d= super.type(type);
		// System.out.println("[LocalDecl_c] ... returns|" + d + "|.");
		return d;
	}
	public LocalDecl localInstance(LocalInstance li) {
		// System.out.println("[LocalDecl_c] ... setting localInstance |" + this.shortToString() + "|.");
		// System.out.println("[LocalDecl_c] ... to |" + li + "|.");
		// new SemanticException("", position()).printStackTrace(System.out);
		LocalDecl d= super.localInstance(li);
		// System.out.println("[LocalDecl_c] ... returning|" + d.shortToString() + "|.");
		return d;
	}
	public Node buildTypes(TypeBuilder tb) throws SemanticException {
		// System.out.println("[LocalDecl_c] Building type " + this.shortToString() + ":");
		Node result= super.buildTypes(tb);
		// System.out.println("[LocalDecl_c] ... produces li=|" + result.localInstance() + "|(#"+li.hashCode()+")");
		return result;
	}
	public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
		// System.out.println("[LocalDecl_c] Disambiguating |" + this.shortToString() + ":");
		// System.out.println("[LocalDecl_c] ... i.e. |" + this + "|.");
		// System.out.println("[LocalDecl_c] ... declType=|" + declType() + "|.");
		// System.out.println("[LocalDecl_c] ... li=|" + li + "|.");
		Node result= super.disambiguate(ar);
		// System.out.println("[LocalDecl_c] ... returning with li=|" + result.localInstance() + "|.");
		// System.out.println("[LocalDecl_c] ... returning node |" + result + "|.");
		return result;
	}
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		X10LocalDecl_c result= (X10LocalDecl_c) super.typeCheck(tc);
		result.updateLI(tc);
		//Report.report(1, "X10LocalDecl_c: returning node with type " + name + " " + result.type() + "(#" + result.hashCode() + ")");
		return result;
	}
	public void pickUpTypeFromTypeNode(TypeChecker tc) {
		X10Type newType = (X10Type) type.type();
		if (li.flags().isFinal()) {
			Constraint c = Constraint_c.addVarWhoseTypeThisIs(C_Local_c.makeSelfVar(li),newType.depClause());
			 newType = newType.makeVariant(c,newType.typeParameters());
		
			//nli  = nli.type(newType);
		}
		li.setType(newType);
	//	X10LocalDecl_c result =  (X10LocalDecl_c) localInstance(nli);
		
		
	}
	public void  updateLI(TypeChecker tc)  {
		TypeSystem ts = tc.typeSystem();
		// Ensure that the LocalInstance is updated with the 
		// possibly new type (w/ depclause)
		
		//LocalInstance tli = ts.localInstance(li.position(),li.flags(), type.type(), li.name());
		//LocalInstance nli = tli;
		// If the local variable is final, replace T by T(:self==t)
		if (li.flags().isFinal()) {
			X10Type oldType = (X10Type) li.type();
			
			Constraint c = Constraint_c.addVarWhoseTypeThisIs(C_Local_c.makeSelfVar(li),oldType.depClause());
			X10Type newType = oldType.makeVariant(c,oldType.typeParameters());
			li.setType(newType);
			//nli  = nli.type(newType);
		}
	//	X10LocalDecl_c result =  (X10LocalDecl_c) localInstance(nli);
	
	}
}
