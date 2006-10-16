/**
 * 
 */
package polyglot.ext.x10.ast;

import java.util.List;

import polyglot.ast.Call;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ext.x10.types.X10ParsedClassType;
import polyglot.ext.x10.types.X10Type;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.types.constr.C_Lit;
import polyglot.ext.x10.types.constr.C_Lit_c;
import polyglot.ext.x10.types.constr.C_Term;
import polyglot.ext.x10.types.constr.Constraint_c;
import polyglot.main.Report;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.TypeChecker;

/**
 * This needs to be pulled out in a separate class because the typeCheck code
 * may be run multiple times. Hence it does not work to run this once and then
 * produce a call. If the typecheck code is called again on the call all information
 * about the RectRegionMaker is lost.
 * @author vj
 *
 */
public class RectRegionMaker_c extends X10Call_c implements RectRegionMaker {

	/**
	 * @param pos
	 * @param target
	 * @param name
	 * @param arguments
	 */
	public RectRegionMaker_c(Position pos, Receiver target, String name,
			List arguments) {
		super(pos, target, name, arguments);
		
	}
	public Node typeCheck(TypeChecker tc) throws SemanticException {
        X10TypeSystem xts = (X10TypeSystem) tc.typeSystem();
        RectRegionMaker_c n = (RectRegionMaker_c) super.typeCheck(tc);
       // Report.report(1, "Tuple_c.typecheck result had type " + n.type());
		X10ParsedClassType type = (X10ParsedClassType)((X10ParsedClassType) n.type()).makeVariant();
		type.setRank(new C_Lit_c(new Integer(arguments.size()), xts.Int()));
		boolean isZeroBased=true;
		for (int i=0; i < arguments.size(); i++) {
			Expr e = (Expr) arguments.get(i);
			X10Type t = (X10Type) e.type();
			
			if (! xts.equals(t, xts.region())) {
				throw new SemanticException("Expected a value of type region instead of " + t
						+".", position());
			}
			X10ParsedClassType tp = (X10ParsedClassType) t;
			C_Term rank = tp.rank();
			if (! C_Lit_c.ONE.equals(rank)) {
				throw new SemanticException("Expected a value of type region(:rank==1) instead of " + t
						+".", position());
			}
			isZeroBased &= tp.isZeroBased();
			
		}
		if (isZeroBased) type.setZeroBased();
		type.setRect();
		Node ret = n.type(type);
		//Report.report(1, "RectRegionMaker: returning  " + ret + "(#" + hashCode() + ") with type "  + ((Call) ret).type());
		return ret;
    }
}
