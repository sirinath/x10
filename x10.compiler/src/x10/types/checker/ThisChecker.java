/**
 * 
 */
package x10.types.checker;

import java.util.List;

import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Globals;
import polyglot.frontend.Job;
import polyglot.visit.ContextVisitor;
import polyglot.visit.ErrorHandlingVisitor;
import polyglot.visit.NodeVisitor;
import x10.ast.X10CanonicalTypeNode;
import x10.ast.X10Field_c;
import x10.ast.X10Special;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.types.Types;
import x10.types.constraints.CConstraint;
import x10.util.Synthesizer;

/**
 * Flags an error if visited node contains this. Used to flag inappropriate
 * uses of this in constructor declarations.
 * @author vj
 *
 */
public class ThisChecker extends NodeVisitor {
    protected boolean error;
    protected TypeSystem ts;
    protected NodeFactory nf;
    public ThisChecker(Job job) {
        ts = (TypeSystem) job.extensionInfo().typeSystem();
        nf = (NodeFactory) job.extensionInfo().nodeFactory();
    }
    protected boolean catchErrors(Node n) { return false; }
    @Override
    public Node override(Node n) {
        if (n instanceof X10Special) {
            X10Special e = (X10Special) n;
            error = error || e.kind() == X10Special.THIS || e.kind() == X10Special.SUPER; 
            return n;
        }
        /**
        // The here field is a fake field generated by the type-checker.
        // Permit this.here to occur in types without tripping the This checker.
        if (n instanceof X10Field_c) {
        	X10Field_c f = (X10Field_c) n;
        	if (f.name().toString().equals(PlaceChecker.HOME_NAME))
        		return n;
        	return null;
        }
        */
        if (n instanceof X10CanonicalTypeNode) {
            Type type = ((X10CanonicalTypeNode) n).type();
            CConstraint rc = Types.xclause(type);
            List<Expr> clauses = new Synthesizer(nf, ts).makeExpr(rc, Types.baseType(type), n.position());
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