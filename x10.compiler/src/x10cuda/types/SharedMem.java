package x10cuda.types;

import java.util.ArrayList;

import polyglot.ast.Expr;
import polyglot.ast.LocalDecl;
import polyglot.types.Name;

public class SharedMem {
    
    ArrayList<Decl> decls = new ArrayList<Decl>();
    
    private abstract static class Decl {
        public final LocalDecl ast;
        public Decl (LocalDecl ast) { this.ast = ast; }
    }
    
    private static class Rail extends Decl {
        public final Expr numElements;
        public final Expr init;
        public Rail (LocalDecl ast, Expr numElements, Expr init) {
            super(ast);
            this.numElements = numElements;
            this.init = init;
        }
    }
    private static class Var extends Decl {
        public Var (LocalDecl ast) { super(ast); }
    }
    
    public void addRail(LocalDecl ast, Expr numElements, Expr init) {
        decls.add(new Rail(ast,numElements,init));
    }

    public void addVar(LocalDecl ast) {
        decls.add(new Var(ast));
    }
    
    public boolean has(Name n) {
        for (Decl d : decls) {
            if (d.ast.name().id() == n) {
                return true;
            }
        }
        return false;
    }
    
    
}
