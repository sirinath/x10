package polyglot.ext.x10.ast;

import java.util.List;

import polyglot.ast.Block;
import polyglot.ast.Expr;
import polyglot.ast.ConstructorDecl_c;
import polyglot.types.Flags;
import polyglot.util.Position;

public class X10ConstructorDecl_c extends ConstructorDecl_c implements X10ConstructorDecl {
    protected Expr retWhereClause;
    protected Expr argWhereClause;
    
    public X10ConstructorDecl_c(Position pos, Flags flags, 
            String name, List formals, List throwTypes, Block body) {
        super(pos, flags, name, formals, throwTypes, body);
    }
    public X10ConstructorDecl_c(Position pos, Flags flags, 
            String name, Expr retWhereClause, 
            List formals, Expr argWhereClause, 
            List throwTypes, Block body) {
        super(pos, flags,  name, formals, throwTypes, body);
        this.retWhereClause=retWhereClause;
        this.argWhereClause=argWhereClause;
        
    }
    
}
