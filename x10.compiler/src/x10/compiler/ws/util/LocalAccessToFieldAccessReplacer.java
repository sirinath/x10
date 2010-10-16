package x10.compiler.ws.util;

import polyglot.types.Name;
import polyglot.types.SemanticException;
import polyglot.visit.NodeVisitor;
import x10.ast.Expr;
import x10.ast.Local;
import x10.ast.LocalAssign;
import x10.ast.Node;
import x10.types.X10Context;
import x10.util.Synthesizer;

/**
 * @author Haichuan
 * 
 * This class is used to replace local var access by field access.
 * In WS code gen, the original code may contain local access.
 * e.g.
 *    static def fib(n:Int):Int {
 *       var t1:Int;
 *       t1 = async fib(n-1);
 *    }
 * After transformation, n/t1 are added as fields in a inner class.
 * Suppose tmp points to one instance, then we need change the last statement to
 *    tmp.t1 = async fib(t1.n - 1);
 * This work is done by the LocalAccessToFieldAccessReplacer
 * 
 *
 */
public class LocalAccessToFieldAccessReplacer extends NodeVisitor {
    protected Expr instanceRef;
    protected Synthesizer synth;
    protected X10Context context;
    public LocalAccessToFieldAccessReplacer(Expr instanceRef, Synthesizer synth, X10Context context){
        this.instanceRef = instanceRef;
        this.synth = synth;
        this.context = context;
    }
    
    
    public Node leave(Node parent, Node old, Node n, NodeVisitor v) {
        Node ret = n;
        if(n instanceof Local && !(parent instanceof LocalAssign)){
            Local l = (Local)n;
            Name name = l.name().id();
            try {
                ret = synth.makeFieldAccess(n.position(), instanceRef, name, context);
            } catch (SemanticException e) {
                System.err.println("[WSCodeGen_ERR]:cannot replace local access with field access");
                e.printStackTrace();
            }
        }
        if(n instanceof LocalAssign){
            LocalAssign ls = ((LocalAssign)n);
            Local l = ls.local();
            Name name = l.name().id();
            try {
                ret = synth.makeFieldAssign(n.position(), instanceRef, name, ls.right(), context);
            } catch (SemanticException e) {
                System.err.println("[WSCodeGen_ERR]:cannot replace local access with field access");
                e.printStackTrace();
            }
           
        }
        
        
        
        return ret;
    }
    
}
