package polyglot.ext.x10.ast;

import java.util.List;

import polyglot.ast.Expr;
import polyglot.ast.MethodDecl;
import polyglot.ast.TypeNode;

public interface X10MethodDecl extends MethodDecl {
	// The method's where clause.
	DepParameterExpr whereClause();
	X10MethodDecl whereClause(DepParameterExpr e);
	
	List<TypeParamNode> typeParameters();
	X10MethodDecl typeParameters(List<TypeParamNode> typeParams);
}
