/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * Created by vj on Jan 23, 2005
 */
package polyglot.ext.x10.ast;

import java.util.List;

import polyglot.ast.Formal;
import polyglot.ast.NodeFactory;
import polyglot.ast.Stmt;
import polyglot.types.LocalInstance;
import polyglot.types.TypeSystem;

/**
 * @author vj Jan 23, 2005
 */
public interface X10Formal extends Formal, X10VarDecl {
   boolean isUnnamed();
   boolean hasExplodedVars();

   /** Get the local instances of the bound variables. */
   public LocalInstance[] localInstances();

   /** Set the local instances of the bound variables. */
   public X10Formal localInstances(LocalInstance[] lis);

   /**
    * Return a list of statements containing the initializations for the exploded vars,
    * with s appended.
    * 
    * @param s -- the list of statements to be appended.
    * @return
    */
   List<Stmt> explode(NodeFactory nf, TypeSystem ts, List<Stmt> s, boolean prepend);
   List<Stmt> explode(NodeFactory nf, TypeSystem ts, Stmt s);
   List<Stmt> explode(NodeFactory nf, TypeSystem ts);
   
   void setPositionInArgList(int i);
   int positionInArgList();
}
