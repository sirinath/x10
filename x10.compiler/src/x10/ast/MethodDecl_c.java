/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2007 Polyglot project group, Cornell University
 * Copyright (c) 2006-2007 IBM Corporation
 * 
 */

package x10.ast;

import java.util.*;

import polyglot.main.Report;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;
import x10.errors.Errors;
import x10.types.ClassDef;
import x10.types.ClassType;
import x10.types.CodeDef;
import x10.types.Context;
import x10.types.Def;
import x10.types.Flags;
import x10.types.MemberDef;
import x10.types.MethodDef;
import x10.types.MethodInstance;
import x10.types.ProcedureDef;
import x10.types.Ref;
import x10.types.SemanticException;
import x10.types.Type;
import x10.types.TypeSystem;
import x10.types.Types;
import x10.types.X10Flags;

/**
 * A method declaration.
 */
public class MethodDecl_c extends Term_c implements MethodDecl
{
    protected FlagsNode flags;
    protected TypeNode returnType;
    protected Id name;
    protected List<Formal> formals;
   // protected List<TypeNode> throwTypes;
    protected Block body;
    protected MethodDef mi;

    public MethodDecl_c(Position pos, FlagsNode flags, TypeNode returnType, Id name, List<Formal> formals, Block body) {
	super(pos);
	assert(flags != null && returnType != null && name != null && formals != null); // body may be null
	this.flags = flags;
	this.returnType = returnType;
	this.name = name;
	this.formals = TypedList.copyAndCheck(formals, Formal.class, true);
	this.body = body;
    }

    public List<Def> defs() {
        return Collections.<Def>singletonList(mi);
    }

    public MemberDef memberDef() {
        return mi;
    }

    /** Get the flags of the method. */
    public FlagsNode flags() {
	return this.flags;
    }

    /** Set the flags of the method. */
    public MethodDecl flags(FlagsNode flags) {
	MethodDecl_c n = (MethodDecl_c) copy();
	n.flags = flags;
	return n;
    }

    /** Get the return type of the method. */
    public TypeNode returnType() {
	return this.returnType;
    }

    /** Set the return type of the method. */
    public MethodDecl returnType(TypeNode returnType) {
	MethodDecl_c n = (MethodDecl_c) copy();
	n.returnType = returnType;
	return n;
    }

    /** Get the name of the method. */
    public Id name() {
        return this.name;
    }
    
    /** Set the name of the method. */
    public MethodDecl name(Id name) {
        MethodDecl_c n = (MethodDecl_c) copy();
        n.name = name;
        return n;
    }
    
    /** Get the formals of the method. */
    public List<Formal> formals() {
	return Collections.<Formal>unmodifiableList(this.formals);
    }

    /** Set the formals of the method. */
    public MethodDecl formals(List<Formal> formals) {
	MethodDecl_c n = (MethodDecl_c) copy();
	n.formals = TypedList.copyAndCheck(formals, Formal.class, true);
	return n;
    }

    public Term codeBody() {
        return this.body;
    }
    
    /** Get the body of the method. */
    public Block body() {
	return this.body;
    }

    /** Set the body of the method. */
    public CodeBlock body(Block body) {
	MethodDecl_c n = (MethodDecl_c) copy();
	n.body = body;
	return n;
    }

    /** Get the method instance of the method. */
    public MethodDef methodDef() {
	return mi;
    }

    /** Set the method instance of the method. */
    public MethodDecl methodDef(MethodDef mi) {
        if (mi == this.mi) return this;
	MethodDecl_c n = (MethodDecl_c) copy();
	n.mi = mi;
	return n;
    }

    public CodeDef codeDef() {
	return procedureInstance();
    }

    /** Get the procedure instance of the method. */
    public ProcedureDef procedureInstance() {
	return mi;
    }

    /** Reconstruct the method. */
    protected MethodDecl_c reconstruct(FlagsNode flags, TypeNode returnType, Id name, List<Formal> formals, Block body) {
	if (flags != this.flags || returnType != this.returnType || name != this.name || ! CollectionUtil.<Formal>allEqual(formals, this.formals)  || body != this.body) {
	    MethodDecl_c n = (MethodDecl_c) copy();
	    n.flags = flags;
	    n.returnType = returnType;
            n.name = name;
	    n.formals = TypedList.copyAndCheck(formals, Formal.class, true);
	    n.body = body;
	    return n;
	}

	return this;
    }

    /** Visit the children of the method. */
    public Node visitChildren(NodeVisitor v) {
        MethodDecl_c n = (MethodDecl_c) visitSignature(v);
	Block body = (Block) n.visitChild(n.body, v);
	return body == n.body ? n : n.body(body);
    }

    public Node buildTypesOverride(TypeBuilder tb) {
        TypeSystem ts = tb.typeSystem();

        ClassDef ct = tb.currentClass();
        assert ct != null;

	Flags flags = this.flags.flags();

	if (ct.flags().isInterface()) {
	    flags = flags.Public().Abstract();
	}

    // If the class is safe, mark all the methods safe. (what about ctors? this code should also be in ctors) 
    // The method inherits the flags of the enclosing containers (only for safe. The other modifiers will be removed: pinned, nonblocking, sequential)
    boolean shouldAddSafe = false;
    if (!X10Flags.toX10Flags(flags).isSafe()) {
        ClassDef container = ct;
        while (container!=null) {
            if (X10Flags.toX10Flags(container.flags()).isSafe())
                shouldAddSafe = true;
            Ref<? extends ClassDef> ref = container.outer();
            if (ref==null) break;
            container = ref.get();
        }
    }
    MethodDecl_c n = this;
    if (shouldAddSafe) {
        // we need to change both the def and the decl
        flags = X10Flags.toX10Flags(flags).Safe(); // these flags are for the def
        n = (MethodDecl_c) n.flags(n.flags().flags(flags)); // changing the decl
    }


	MethodDef mi = createMethodDef(ts, ct, flags);
        ct.addMethod(mi);
	
	TypeBuilder tbChk = tb.pushCode(mi);

	final TypeBuilder tbx = tb;
	final MethodDef mix = mi;
	
	n = (MethodDecl_c) n.visitSignature(new NodeVisitor() {
            public Node override(Node n) {
                return MethodDecl_c.this.visitChild(n, tbx.pushCode(mix));
            }
        });

	List<Ref<? extends Type>> formalTypes = new ArrayList<Ref<? extends Type>>(n.formals().size());
        for (Formal f : n.formals()) {
             formalTypes.add(f.type().typeRef());
        }


        mi.setReturnType(n.returnType().typeRef());
        mi.setFormalTypes(formalTypes);
    
        Block body = (Block) n.visitChild(n.body, tbChk);
        
        n = (MethodDecl_c) n.body(body);
        return n.methodDef(mi);
    }

    protected MethodDef createMethodDef(TypeSystem ts, ClassDef ct, Flags flags) {
	MethodDef mi = ts.methodDef(position(), Types.ref(ct.asType()), flags, returnType.typeRef(), name.id(),
	                                 Collections.<Ref<? extends Type>>emptyList());
	return mi;
    }

    public Context enterScope(Context c) {
        if (Report.should_report(TOPICS, 5))
	    Report.report(5, "enter scope of method " + name);
        c = c.pushCode(mi);
        return c;
    }
    
    public Node visitSignature(NodeVisitor v) {
        TypeNode returnType = (TypeNode) this.visitChild(this.returnType, v);
        FlagsNode flags = (FlagsNode) this.visitChild(this.flags, v);
        Id name = (Id) this.visitChild(this.name, v);
        List<Formal> formals = this.visitList(this.formals, v);
        return reconstruct(flags, returnType, name, formals,  this.body);
    }
    
    /** Type check the declaration. */
    public Node typeCheckBody(Node parent, TypeChecker tc, TypeChecker childtc) throws SemanticException {
        MethodDecl_c n = this;
        Block body = (Block) n.visitChild(n.body, childtc);
        n = (MethodDecl_c) n.body(body);
        return n;
    }

    /** Type check the method. */
    public Node typeCheck(ContextVisitor tc) throws SemanticException {
        TypeSystem ts = tc.typeSystem();
/*
        for (Iterator<TypeNode> i = throwTypes().iterator(); i.hasNext(); ) {
            TypeNode tn = (TypeNode) i.next();
            Type t = tn.type();
            if (! t.isThrowable()) {
                throw new SemanticException("Type \"" + t +
                    "\" is not a subclass of \"" + ts.Throwable() + "\".",
                    tn.position());
            }
        }
*/

	return this;
    }
    
    @Override
    public Node conformanceCheck(ContextVisitor tc) {
	// Get the mi flags, not the node flags since the mi flags
	// account for being nested within an interface.
	Flags flags = mi.flags();
	checkFlags(tc, flags);
	
	overrideMethodCheck(tc);
	
	return this;
    }

    protected void checkFlags(ContextVisitor tc, Flags flags) {
	TypeSystem ts = tc.typeSystem();

	if (tc.context().currentClass().flags().isInterface()) {
            if (flags.isProtected() || flags.isPrivate()) {
                Errors.issue(tc.job(), new SemanticException("Interface methods must be public.", position()));
            }
            
            if (flags.isStatic()) {
        	Errors.issue(tc.job(), new SemanticException("Interface methods cannot be static.", position()));
            }
        }

        try {
            ts.checkMethodFlags(flags);
        }
        catch (SemanticException e) {
            Errors.issue(tc.job(), e, this);
        }

        Type container = Types.get(methodDef().container());
        ClassType ct = container.toClass();

	if (body == null && ! (flags.isAbstract() || flags.isNative())) {
	    Errors.issue(tc.job(), new SemanticException("Missing method body.", position()));
	}

        if (body != null && ct.flags().isInterface()) {
	    Errors.issue(tc.job(), new SemanticException("Interface methods cannot have a body.", position()));
        }

	if (body != null && flags.isAbstract()) {
	    Errors.issue(tc.job(), new SemanticException("An abstract method cannot have a body.", position()));
	}

	if (body != null && flags.isNative()) {
	    Errors.issue(tc.job(), new SemanticException("A native method cannot have a body.", position()));
	}

        // check that inner classes do not declare static methods
        if (ct != null && flags.isStatic() && ct.isInnerClass()) {
            // it's a static method in an inner class.
            Errors.issue(tc.job(),
                    new SemanticException("Inner classes cannot declare static methods.", position()));             
        }
    }

    protected void overrideMethodCheck(ContextVisitor tc) {
        TypeSystem ts = tc.typeSystem();

        MethodInstance mi = this.mi.asInstance();
        for (Iterator<MethodInstance> j = mi.implemented(tc.context()).iterator(); j.hasNext(); ) {
            MethodInstance mj = (MethodInstance) j.next();

            if (! ts.isAccessible(mj, tc.context())) {
                continue;
            }

            try {
                ts.checkOverride(mi, mj, tc.context());
            } catch (SemanticException e) {
                Errors.issue(tc.job(), e, this);
            }
        }
    }

  /*  public NodeVisitor exceptionCheckEnter(ExceptionChecker ec) throws SemanticException {
        return ec.push(new ExceptionChecker.CodeTypeReporter("Method " + mi.signature())).push(methodDef().asInstance().throwTypes());
    }
*/
    public String toString() {
	return flags.flags().translate() + returnType + " " + name + "(...)";
    }

    /** Write the method to an output file. */
    public void prettyPrintHeader(CodeWriter w, PrettyPrinter tr) {
	w.begin(0);
	print(flags, w, tr);
	print(returnType, w, tr);
	w.allowBreak(2, 2, " ", 1);
	w.write(name + "(");

	w.allowBreak(2, 2, "", 0);
	w.begin(0);

	for (Iterator<Formal> i = formals.iterator(); i.hasNext(); ) {
	    Formal f = i.next();
	    
	    print(f, w, tr);

	    if (i.hasNext()) {
		w.write(",");
		w.allowBreak(0, " ");
	    }
	}

	w.end();
	w.write(")");
/*
	if (! throwTypes().isEmpty()) {
	    w.allowBreak(6);
	    w.write("throws ");

	    for (Iterator<TypeNode> i = throwTypes().iterator(); i.hasNext(); ) {
	        TypeNode tn = (TypeNode) i.next();
		print(tn, w, tr);

		if (i.hasNext()) {
		    w.write(",");
		    w.allowBreak(4, " ");
		}
	    }
	}
*/
	w.end();
    }

    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
        prettyPrintHeader(w, tr);

	if (body != null) {
	    printSubStmt(body, w, tr);
	}
	else {
	    w.write(";");
	}
    }

    public void dump(CodeWriter w) {
	super.dump(w);

	if (mi != null) {
	    w.allowBreak(4, " ");
	    w.begin(0);
	    w.write("(instance " + mi + ")");
	    w.end();
	}

        w.allowBreak(4, " ");
        w.begin(0);
        w.write("(name " + name + ")");
        w.end();
    }

    public Term firstChild() {
        return listChild(formals(), returnType());
    }

    public <S> List<S> acceptCFG(CFGBuilder v, List<S> succs) {
        v.visitCFGList(formals(), returnType(), ENTRY);
        
        if (body() == null) {
            v.visitCFG(returnType(), this, EXIT);
        }
        else {
            v.visitCFG(returnType(), body(), ENTRY);
            v.visitCFG(body(), this, EXIT);
        }
        
        return succs;
    }

    private static final Collection<String> TOPICS = 
            CollectionUtil.list(Report.types, Report.context);
}
