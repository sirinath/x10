/**
 * 
 */
package x10.types.checker;

import polyglot.ast.Node;
import polyglot.frontend.Globals;
import polyglot.frontend.Job;
import polyglot.visit.ContextVisitor;
import x10.ast.X10Special;

/**
 * Flags an error if visited node contsains a mutable variable or field.
 * @author vj
 *
 */
public class ThisChecker extends ContextVisitor {
    public ThisChecker(Job job) {
    	   super(job, job.extensionInfo().typeSystem(), job.extensionInfo().nodeFactory());
    }
    @Override
    public Node override(Node n) {
        if (n instanceof X10Special) {
            X10Special e = (X10Special) n;
            error = error || e.kind() == X10Special.THIS || e.kind() == X10Special.SUPER; 
            return n;
        }
        return null;
    }
    public boolean error() { return error;}
    public void clearError() { error = false;}
}