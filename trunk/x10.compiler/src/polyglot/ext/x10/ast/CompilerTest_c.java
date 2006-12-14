/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.ast;

import polyglot.ast.FieldDecl;
import polyglot.ast.Node;
import polyglot.ast.FieldDecl_c;
import polyglot.ext.x10.types.X10TypeSystem_c;
import polyglot.types.Flags;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.PrettyPrinter;

public class CompilerTest_c extends FieldDecl_c implements FieldDecl {

    public CompilerTest_c(Position pos) {
        super(pos, Flags.PRIVATE, 
                new X10CanonicalTypeNode_c(pos, X10TypeSystem_c.getTypeSystem().X10Object()), 
                "$$INTERNAL___COMPILER_TEST", null);
       
        
    }

    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
       return super.disambiguate(ar);
    }
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
        // No code generated.
    }

}
