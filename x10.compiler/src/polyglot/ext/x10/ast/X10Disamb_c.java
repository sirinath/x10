package polyglot.ext.x10.ast;

import polyglot.ast.Receiver;
import polyglot.ext.jl.ast.Disamb_c;
import polyglot.ext.x10.types.X10Context;
import polyglot.main.Report;
import polyglot.types.ClassType;
import polyglot.types.FieldInstance;
import polyglot.types.SemanticException;

public class X10Disamb_c extends Disamb_c implements X10Disamb {

	
	public String toString() {
        return "X10Disamb(" + amb.getClass().getName() + ": " + amb + ")";
    }
	protected Receiver makeMissingFieldTarget(FieldInstance fi) throws SemanticException {
        Receiver r;

        if (fi.flags().isStatic()) {
            r = nf.CanonicalTypeNode(pos, fi.container());
        } else {
            // The field is non-static, so we must prepend with
        	// self or 
            // "this", but we need to determine if the "this"
            // should be qualified.  Get the enclosing class which
            // brought the field into scope.  This is different
            // from fi.container().  fi.container() returns a super
            // type of the class we want.
            ClassType scope = c.findFieldScope(name);
            X10Context xc = (X10Context) c;
           
            if (xc.isDepType()) {
            	if (! ts.equals(scope, xc.currentDepType())) {
            		r = nf.This(pos, nf.CanonicalTypeNode(pos, scope));
            	} else {
            		r = ((X10NodeFactory) nf).Self(pos);
            	}
            } else 
            	if (! ts.equals(scope, c.currentClass())) {
            		r = nf.This(pos, nf.CanonicalTypeNode(pos, scope));
            	} else {
            		
            		r =  nf.This(pos);
            		
            	}
        }
      
        		
        return r;
    }
}
