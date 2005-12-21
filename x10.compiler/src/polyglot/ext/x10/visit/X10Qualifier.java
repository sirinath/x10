package polyglot.ext.x10.visit;

import polyglot.ast.Special;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Job;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.visit.AscriptionVisitor;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import polyglot.util.Position;
import polyglot.ext.x10.ast.Async;
import polyglot.ext.x10.ast.ForEach;
import polyglot.ext.x10.ast.Future;


/**
 * Visitor that qualifies uses of 'this' and 'super' inside Async statements.
 */
public class X10Qualifier extends ContextVisitor
{
    public X10Qualifier(Job job, TypeSystem ts, NodeFactory nf) {
        super(job, ts, nf);
    }

    public static class InnerWrapperQualifier extends AscriptionVisitor {
        public InnerWrapperQualifier(Job job, TypeSystem ts, NodeFactory nf) {
            super(job, ts, nf);
        }

        public Expr ascribe(Expr e, Type toType) {
            if (e instanceof Special) {
                Position p = e.position();
                Special sc = (Special) e;
                if (sc.qualifier() == null /* && sc.type() != toType */) {
                    //System.err.println("Setting qualifier on 'this'");
                    Special ret = sc.qualifier(nf.CanonicalTypeNode(p, sc.type()));
                    return ret;
                }
            }

            return e;
        }
    }

    protected NodeVisitor enterCall(Node parent, Node n) {
        if (parent instanceof Async || parent instanceof Future ||
	    parent instanceof ForEach)
	{
            return new InnerWrapperQualifier(job, ts, nf).context(context);
        }
        return this;
    }
}
