/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */
package x10.util.synthesizer;

import java.util.ArrayList;
import java.util.List;

import polyglot.util.Position;
import x10.ast.AnnotationNode;
import x10.ast.Expr;
import x10.ast.Id;
import x10.ast.Local;
import x10.ast.LocalDecl;
import x10.ast.Stmt;
import x10.ast.TypeNode;
import x10.ast.NodeFactory;
import x10.extension.X10Del;
import x10.types.Flags;
import x10.types.LocalDef;
import x10.types.Name;
import x10.types.SemanticException;
import x10.types.Type;
import x10.types.Types;
import x10.types.Context;

/**
 * A synthesizer to create a local variable
 *
 */
public class NewLocalVarSynth extends AbstractStateSynth implements IStmtSynth{

    LocalDef localDef;
    Local localRef;
    
    Name name;
    Flags flags;
    Expr initializer;
    List<AnnotationNode> annotations;
    
    public NewLocalVarSynth(NodeFactory xnf, Context xct, Position pos,
                            Name name, Flags flags, Expr initializer, Type type, List<AnnotationNode> annotations) {
        super(xnf, xct, pos);
        this.name = name;
        this.flags = flags;
        this.initializer = initializer;
        this.annotations = annotations;
        
        if (!xts.typeEquals(type, xts.Void(), xct)) {
            localDef = xts.localDef(pos, flags, Types.ref(type), name);
            final Id varId = xnf.Id(pos, name);
            localRef = (Local) xnf.Local(pos, varId).localInstance(localDef.asInstance()).type(type);
        }
    }

    public NewLocalVarSynth(NodeFactory xnf, Context xct, Position pos,
                            Type type) {
        this(xnf, xct, pos, xct.getNewVarName(), 
             Flags.NONE, null, type, new ArrayList<AnnotationNode>());
    }
    
    public NewLocalVarSynth(NodeFactory xnf, Context xct, Position pos,
                            Flags flags, Expr initializer) {
        this(xnf, xct, pos, xct.getNewVarName(), 
             flags, initializer, initializer.type(), new ArrayList<AnnotationNode>());
    }
    
    public Local getLocal(){
        return localRef;
    }
    
    public void addAnnotation(AnnotationNode annotation){
        annotations.add(annotation);
    }

    public void addAnnotations(List<AnnotationNode> annotations){
        this.annotations.addAll(annotations);
    }
    
    public Stmt genStmt() throws SemanticException {
        LocalDecl localDecl = null;
        if(localDef != null && localRef != null){
            final TypeNode tn = xnf.CanonicalTypeNode(pos, localDef.type().get());
            localDecl = xnf.LocalDecl(pos, xnf.FlagsNode(pos, flags), tn, localRef.name(), initializer).localDef(localDef);
            if(annotations != null && annotations.size() > 0){
                localDecl = (LocalDecl)((X10Del) localDecl.del()).annotations(annotations); 
            }
        }
        return localDecl;
    }
    
    

}
