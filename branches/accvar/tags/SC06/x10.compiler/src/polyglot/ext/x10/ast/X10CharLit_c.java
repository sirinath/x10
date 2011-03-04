/**
 * 
 */
package polyglot.ext.x10.ast;

import polyglot.ast.Node;
import polyglot.ext.jl.ast.CharLit_c;
import polyglot.ext.x10.types.X10Type;
import polyglot.ext.x10.types.constr.C_Lit_c;
import polyglot.ext.x10.types.constr.Constraint;
import polyglot.ext.x10.types.constr.Constraint_c;
import polyglot.main.Report;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.TypeChecker;

/**
 * An immutable representation of a char lit, modified from JL 
 * to support a self-clause in the dep type.
 * @author vj
 *
 */
public class X10CharLit_c extends CharLit_c {

	/**
	 * @param pos
	 * @param value
	 */
	public X10CharLit_c(Position pos, char value) {
		super(pos, value);
	}
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		  X10Type Type = (X10Type) tc.typeSystem().Char();
		 
			C_Lit_c literal = new C_Lit_c(new Character((char) value), Type);
			Constraint c = Constraint_c.addSelfBinding(literal,null);
		  X10Type newType  = Type.makeVariant(c, null);
		  //Report.report(1, "X10CharLit: type for " + this + " is " + newType+".");
	    return type(newType);
	  }
}
