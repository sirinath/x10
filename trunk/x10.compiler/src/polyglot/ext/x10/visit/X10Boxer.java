package polyglot.ext.x10.visit;


import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ext.x10.extension.X10Ext;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.visit.AscriptionVisitor;
import polyglot.visit.NodeVisitor;
import polyglot.ast.Call;
import polyglot.ext.x10.types.X10Type;
import polyglot.types.PrimitiveType;
import polyglot.types.ParsedClassType;

/**
 * Visitor that inserts boxing and unboxing code into the AST.
 */
public class X10Boxer extends AscriptionVisitor
{
    public X10Boxer(Job job, TypeSystem ts, NodeFactory nf) {
        super(job, ts, nf);
    }

    /* 
     * This method rewrites an AST node. We have to be careful also to 
     * provide type information with the newly created node, because the 
     * type checker ran before this pass and the node must hence be annotated.
     * Just calling the node factory is not sufficient.
     */
    public Expr ascribe(Expr e, Type toType) {
        Type fromType = e.type();
        Expr ret_notype;
        
        if (toType == null) {
            return e;
        }

        Position p = e.position();

        
        
        if (e instanceof Call) {
            // for calls to method force() on Objects of type Future, 
            // make sure that the corresponding cast occurs (from Object 
            // to the primitive type),
        
            Call call_n = (Call) e;
            String m_name = call_n.name();
        	X10Type target_t = (X10Type) call_n.target().type();
        	if (m_name.equals("force") && target_t.isFuture()) {
        	    if (fromType.isPrimitive()) {
        	        Type boxed_t = ((X10TypeSystem) ts).boxedType((PrimitiveType) fromType);
            	    call_n = (Call) call_n.type(boxed_t);
            	    ret_notype = nf.Cast(p, nf.CanonicalTypeNode(p, fromType), call_n);
        	    } else {
        	        ret_notype = nf.Cast(p, nf.CanonicalTypeNode(p, fromType), call_n);
        	    }
        	    return ret_notype.type(target_t);
        	}
        }
                
        // This avoids that the int value in code like "String" + 2
        // is boxed. The toType of the IntLit node that represents "2"
        // is actually a ParsedClassType for java.lang.String.
        // While the boxing is OK in case "String" + 2 , it would break
        // the expression "String" + 2 + 3.

        // A better way to fix this problem would be to modify the type system and  
        // annotatioon such that it understand that operator "+" for Strings 
        // and primitives is fine and would not ask for a String here.
        // see line 405 in polyglot.ext.jl.as.Binary_c
        // TODO  I leave this up to whoever implements the type system/checker
        
        boolean is_String_type = ts.equals(toType, ts.String());
        // Insert a cast.  Translation of the cast will insert the
        // correct boxing/unboxing code.
        
        if (!is_String_type && fromType.isPrimitive() && toType.isReference()) {
            ret_notype = nf.Cast(p, nf.CanonicalTypeNode(p, toType), e);
            return ret_notype.type(toType);
        }
        return e;
    }
    
    public Node leaveCall(Node old, Node n, NodeVisitor v) throws SemanticException {
        n = super.leaveCall(old, n, v);

        if (n.ext() instanceof X10Ext) {
            return ((X10Ext) n.ext()).rewrite((X10TypeSystem) typeSystem(),
                                              nodeFactory());
        }

        return n;
    }
}
