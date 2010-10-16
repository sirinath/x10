/**
 * 
 */
package x10.types.checker;

import java.util.List;

import polyglot.frontend.Globals;
import polyglot.frontend.Job;
import polyglot.visit.ContextVisitor;
import polyglot.visit.ErrorHandlingVisitor;
import x10.ast.Expr;
import x10.ast.Node;
import x10.ast.X10CanonicalTypeNode;
import x10.ast.X10Field_c;
import x10.ast.NodeFactory;
import x10.ast.X10Special;
import x10.types.X10TypeMixin;
import x10.types.X10TypeSystem;
import x10.types.constraints.CConstraint;
import x10.util.Synthesizer;

/**
 * Flags an error if visited node contains this. Used to flag inappropriate
 * uses of this in constructor declarations.
 * @author vj
 *
 */
public class ThisChecker extends ErrorHandlingVisitor {
    public ThisChecker(Job job) {
    	   super(job, job.extensionInfo().typeSystem(), job.extensionInfo().nodeFactory());
    }
    protected boolean catchErrors(Node n) { return false; }
    @Override
    public Node override(Node n) {
        if (n instanceof X10Special) {
            X10Special e = (X10Special) n;
            error = error || e.kind() == X10Special.THIS || e.kind() == X10Special.SUPER; 
            return n;
        }
        // The here field is a fake field generated by the type-checker.
        // Permit this.here to occur in types without tripping the This checker.
        if (n instanceof X10Field_c) {
        	X10Field_c f = (X10Field_c) n;
        	if (f.name().toString().equals("here"))
        		return n;
        	return null;
        }
        if (n instanceof X10CanonicalTypeNode) {
            CConstraint rc = X10TypeMixin.xclause(((X10CanonicalTypeNode) n).type());
            List<Expr> clauses = new Synthesizer((NodeFactory) nf, (X10TypeSystem) ts).makeExpr(rc, n.position());
            for (Expr c : clauses) {
                c.visit(this);
            }
        }
        return null;
    }
    public boolean error() {
        return error;
    }
    public void clearError() {
        error = false;
    }
}