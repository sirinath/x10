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

import java.util.List;

import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ext.x10.types.X10ParsedClassType;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.types.constr.C_Here_c;
import polyglot.ext.x10.types.constr.C_Term;
import polyglot.ext.x10.types.constr.Constraint_c;
import polyglot.main.Report;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

/**
 * @author vj
 *
 */
public class ConstantDistMaker_c extends X10Call_c implements ConstantDistMaker {

	/**
	 * @param pos
	 * @param target
	 * @param name
	 * @param arguments
	 */
	public ConstantDistMaker_c(Position pos, Receiver target, String name,
			List arguments) {
		super(pos, target, name, arguments);
	}
	 
    public Node typeCheck(TypeChecker tc) throws SemanticException {
        X10TypeSystem xts = (X10TypeSystem) tc.typeSystem();
        ConstantDistMaker_c n = (ConstantDistMaker_c) super.typeCheck(tc);
        Expr left = (Expr) n.arguments.get(0);
        X10ParsedClassType lType = (X10ParsedClassType) left.type();
       // Report.report(1, "ConstantDistMaker..ltype " + left+ "(#" + left.hashCode() + ") is " + lType);
		X10ParsedClassType type = (X10ParsedClassType) ((X10ParsedClassType) xts.distribution())
		.makeVariant();
		C_Term rank = ((X10ParsedClassType) lType).rank();
		if (rank != null) type.setRank(rank);
		type.setOnePlace(C_Here_c.here);
		boolean zeroB = lType.isZeroBased();
		if (zeroB) type.setZeroBased();
		
		boolean isRect = lType.isRect();
		if (isRect) type.setRect();
	//Report.report(1, "ConstantDistMaker.. returning " + this + " with type "  + type);
		return type(type);
    }
    
   /* public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    	Report.report(1, "ConstantDistMaker outputting " + arguments.get(0)
    			+ " " + name + " " + arguments.get(1));
		X10NodeFactory_c.getNodeFactory()
		.Call(position(), (Expr) arguments.get(0), name, 
				(Expr) arguments.get(1)).prettyPrint(w, tr);
    }*/
}
