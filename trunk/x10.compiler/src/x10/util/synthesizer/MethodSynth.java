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

import polyglot.ast.Block;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassMember;
import polyglot.ast.Expr;
import polyglot.ast.FlagsNode;
import polyglot.ast.Formal;
import polyglot.ast.TypeNode;
import polyglot.types.ClassDef;
import polyglot.types.Flags;
import polyglot.types.LocalDef;
import polyglot.types.Name;
import polyglot.types.Ref;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.Types;
import polyglot.util.Position;
import x10.ast.X10ClassDecl;
import x10.ast.X10MethodDecl;
import x10.ast.X10NodeFactory;
import x10.types.X10Context;
import x10.types.X10Flags;
import x10.types.X10MethodDef;
import x10.types.X10TypeSystem;

/**
 * Method synthesizer to construct a method
 *
 */
public class MethodSynth extends AbstractStateSynth implements IClassMemberSynth{

    CodeBlockSynth codeBlockSynth;
    List<Formal> formals;
    X10MethodDef methodDef; //only be created once;
    X10MethodDecl methodDecl; //only be created once;
    
    
    public MethodSynth(X10NodeFactory xnf, X10Context xct, Position pos, ClassDef classDef, Name methodName,
                       Flags flags, List<Formal> formals, List<Type> throwTypes, Type returnType){
        super(xnf, xct, pos);

        this.formals = formals;
        
        List<Ref<? extends Type>> formalTypeRefs = new ArrayList<Ref<? extends Type>>();
        List<LocalDef> formalNames = new ArrayList<LocalDef>();
        List<Ref<? extends Type>> throwTypeRefs = new ArrayList<Ref<? extends Type>>();
        for (Formal f : formals) {
            formalTypeRefs.add(f.type().typeRef());
            formalNames.add(f.localDef());
        }
        for (Type t : throwTypes) {
            throwTypeRefs.add(Types.ref(t));
        }
        methodDef = (X10MethodDef) xts.methodDef(pos, 
                Types.ref(classDef.asType()),                
                flags, 
                Types.ref(returnType), 
                methodName, 
                formalTypeRefs, 
                throwTypeRefs);//this constructor will not set formal names
        methodDef.setFormalNames(formalNames);
        classDef.addMethod(methodDef);
    }
    
    public MethodSynth(X10NodeFactory xnf, X10Context xct, Position pos, ClassDef classDef, String methodName){
        this(xnf, xct, pos, classDef, Name.make(methodName),
             Flags.NONE, new ArrayList<Formal>(), new ArrayList<Type>(), xct.typeSystem().Void());
    }
    
    public MethodSynth(X10NodeFactory xnf, X10Context xct, ClassDef classDef, String methodName){
        this(xnf, xct, compilerPos, classDef, methodName);
    }
     
    public void setFlag(Flags flags) {
        try {
            checkClose();
            methodDef.setFlags(flags);
        } catch (StateSynthClosedException e) {
            e.printStackTrace();
        }
    }

    public void setReturnType(Type returnType){
        try {
            checkClose();
            methodDef.setReturnType(Types.ref(returnType));
        } catch (StateSynthClosedException e) {
            e.printStackTrace();
        }

    }
    
    
    /**
     * Add a formal to this method, and return a ref to this formal. Flag is None
     * @param pos
     * @param type
     * @param name
     * @return
     */
    public Expr addFormal(Position pos, Type type, String name){
        return addFormal(pos, Flags.NONE, type, Name.make(name));
    }
 
    /**
     * Add a formal to this method, and return a ref to this formal
     * @param pos
     * @param flags
     * @param type
     * @param name
     * @return
     */
    public Expr addFormal(Position pos, Flags flags, Type type, String name){
        return addFormal(pos, flags, type, Name.make(name));
    }
    
    /**
     * Add a formal to this method, and return a ref to this formal
     * @param pos
     * @param flags
     * @param type
     * @param name
     * @return
     */
    public Expr addFormal(Position pos, Flags flags, Type type, Name name){
        X10TypeSystem xts = (X10TypeSystem) xct.typeSystem();
        LocalDef lDef = xts.localDef(pos, X10Flags.NONE, Types.ref(type), name);
        Formal f = xnf.Formal(pos,
                              xnf.FlagsNode(pos, flags), 
                              xnf.CanonicalTypeNode(pos, type), 
                              xnf.Id(pos, name)).localDef(lDef);
        return addFormal(f);
    }
    

    public Expr addFormal(Formal formal) {
        try {
            checkClose();
            ArrayList<LocalDef> formalNames = new ArrayList<LocalDef>(methodDef.formalNames());
            ArrayList<Ref<? extends Type>> formalRefs = 
                new  ArrayList<Ref<? extends Type>>(methodDef.formalTypes());

            formalNames.add(formal.localDef());
            formalRefs.add(formal.type().typeRef());
            
            methodDef.setFormalNames(formalNames);
            methodDef.setFormalTypes(formalRefs);
            formals.add(formal);
            
            //now prepare the local ref
            Name name = formal.name().id();
            LocalDef lDef = formal.localDef();
            Type type = formal.type().type();
            return xnf.Local(pos, xnf.Id(pos, name)).localInstance(lDef.asInstance()).type(type);
        } catch (StateSynthClosedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public CodeBlockSynth createMethodBody(Position pos) {
        codeBlockSynth = new CodeBlockSynth(xnf, xct, pos);
        return codeBlockSynth;
    }
    
    public X10MethodDef getDef(){        
        return methodDef;
    }
    
    
    
    public X10MethodDecl close() throws SemanticException{

        if (closed) {
            return methodDecl; // just return the field
        }
        closed = true;
        
        // Method Decl
        List<TypeNode> throwTypeNodes = new ArrayList<TypeNode>();
        for (Ref<? extends Type> t : methodDef.throwTypes()) {
            throwTypeNodes.add(xnf.CanonicalTypeNode(pos, t.get()));
        }
        FlagsNode flagNode = xnf.FlagsNode(pos, methodDef.flags());
        TypeNode returnTypeNode = xnf.CanonicalTypeNode(pos, methodDef.returnType());
        
        Block block;
        if(codeBlockSynth == null){
            block = xnf.Block(pos);
        }
        else{
            block = codeBlockSynth.genCodeGen();
        }
        methodDecl = (X10MethodDecl) xnf.MethodDecl(pos, flagNode, returnTypeNode, xnf.Id(pos, methodDef.name()), 
                formals, throwTypeNodes, block);

        methodDecl = (X10MethodDecl) methodDecl.methodDef(methodDef); //Need set the method def to the method instance
        
        return methodDecl;
    }
    
    @Deprecated 
    public X10ClassDecl insertMethodIntoClass(X10ClassDecl classDecl) throws SemanticException{
        List<ClassMember> cm = new ArrayList<ClassMember>();
        cm.addAll(classDecl.body().members());
        cm.add(close());
        ClassBody cb = classDecl.body();
        return (X10ClassDecl) classDecl.body(cb.members(cm));
    }
    
}
