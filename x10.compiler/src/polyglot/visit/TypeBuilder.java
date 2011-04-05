/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * Copyright (c) 2007 IBM Corporation
 * 
 */

package polyglot.visit;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.frontend.*;
import polyglot.main.Reporter;
import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;
import x10.errors.Errors;
import x10.types.X10ClassType;

/** Visitor which traverses the AST constructing type objects. */
public class TypeBuilder extends NodeVisitor
{
    protected ImportTable importTable;
    protected Job job;
    protected TypeSystem ts;
    protected Reporter reporter;
    protected NodeFactory nf;
    protected TypeBuilder outer;
    protected boolean inCode; // true if the last scope pushed as not a class.
    protected boolean global; // true if all scopes pushed have been classes.
    protected Package package_;
    protected ClassDef type; // last class pushed.
    protected Def def;

    public TypeBuilder(Job job, TypeSystem ts, NodeFactory nf) {
        this.job = job;
        this.ts = ts;
        this.reporter = ts.extensionInfo().getOptions().reporter;
        this.nf = nf;
        this.outer = null;
    }
    
    public TypeBuilder push() {
        TypeBuilder tb = (TypeBuilder) this.shallowCopy();
        tb.outer = this;
        return tb;
    }
    
    public boolean inCode() {
	return inCode;
    }

    public TypeBuilder pop() {
        return outer;
    }
    
    public Def def() {
        return def;
    }
    
    public Job job() {
        return job;
    }
    
    public ErrorQueue errorQueue() {
        return job().compiler().errorQueue();
    }

    public NodeFactory nodeFactory() {
        return nf;
    }

    public TypeSystem typeSystem() {
        return ts;
    }

    public NodeVisitor begin() {
        return this;
    }
    
    public Node override(Node n) {
        return n.del().buildTypesOverride(this);
    }

    public NodeVisitor enter(Node n) {
	    return n.del().buildTypesEnter(this);
    }

    public Node leave(Node old, Node n, NodeVisitor v) {
	    return n.del().buildTypes((TypeBuilder) v);
    }

    /**
    @deprecated */
    public TypeBuilder pushContext(Context c) {
        LinkedList<Context> stack = new LinkedList<Context>();
        while (c != null) {
            stack.addFirst(c);
            c = c.pop();
        }
        
        TypeBuilder tb = this;
        boolean inCode = false;
        for (Iterator<Context> i = stack.iterator(); i.hasNext(); ) {
            c = (Context) i.next();
            if (c.inCode()) {
                if (! inCode) {
                    // entering code
                    inCode = true;
                    tb = tb.pushCode(c.currentCode());
                }
            }
            else {
                if (c.importTable() != null && tb.importTable() == null) {
                    // entering class file
                    tb.setImportTable(c.importTable());
                }
                if (c.importTable() != null && c.package_() != null &&
                    tb.currentPackage() == null) {
                    // entering package context in source
                    tb = tb.pushPackage(c.package_());
                }
                if (c.currentClassDef() != tb.currentClass()) {
                    // entering class
                    tb = tb.pushClass(c.currentClassDef());
                }
            }
        }
        
        return tb;
    }
    
    public TypeBuilder pushDef(Def def) {
        TypeBuilder tb = push();
        tb.def = def;
        return tb;
    }
    
    public TypeBuilder pushPackage(Package p) {
        if (reporter.should_report(Reporter.visit, 4))
            reporter.report(4, "TB pushing package " + p + ": " + context());
        TypeBuilder tb = push();
        tb.inCode = false;
        tb.package_ = p;
        return tb;
    }

    public TypeBuilder pushCode(CodeDef def) {
        if (reporter.should_report(Reporter.visit, 4))
            reporter.report(4, "TB pushing code: " + context());
        TypeBuilder tb = pushDef(def);
        tb.inCode = true;
        tb.global = false;
        return tb;
    }

    public TypeBuilder pushClass(ClassDef classDef) {
        if (reporter.should_report(Reporter.visit, 4))
            reporter.report(4, "TB pushing class " + classDef + ": " + context());

        TypeBuilder tb = pushDef(classDef);
        tb.inCode = false;
        tb.type = classDef;

	// Make sure the import table finds this class.
        if (importTable() != null && classDef.isTopLevel()) {
	    tb.importTable().addExplicitImport(QName.make(classDef.fullName()));
	}
        
        return tb;
    }

    // Duplicate class id counter
    private static int dupId = 0;

    /**
     * Do not fail on duplicate types, but create another instance of the type with a
     * dummy name, to allow proceeding with compilation.
     */
    protected ClassDef newClass(Position pos, Flags flags, Name name) {
        return newClass(pos, flags, name, null);
    }
    /**
     * Do not fail on duplicate types, but create another instance of the type with a
     * dummy name, to allow proceeding with compilation.
     */
    protected ClassDef newClass(Position pos, Flags flags, Name name, SemanticException error) {
        TypeSystem ts = typeSystem();

        ClassDef ct = ts.createClassDef(job().source());

        ct.position(pos);
        ct.flags(flags);
        ct.name(name);
        ct.superType(new ErrorRef_c<Type>(ts, pos, "Cannot get superclass before type-checking class declaration."));

	if (inCode) {
            ct.kind(ClassDef.LOCAL);
	    ct.outer(Types.ref(currentClass()));
	    ct.setJob(job());

	    if (currentPackage() != null) {
	      	ct.setPackage(Types.<Package>ref(currentPackage()));
	    }

	    return ct;
	}
	else if (currentClass() != null) {
            ct.kind(ClassDef.MEMBER);
            ct.outer(Types.ref(currentClass()));
	    ct.setJob(job());

	    currentClass().addMemberClass(Types.<ClassType>ref(ct.asType()));

	    if (currentPackage() != null) {
	      	ct.setPackage(Types.<Package>ref(currentPackage()));
	    }

            // if all the containing classes for this class are member
            // classes or top level classes, then add this class to the
            // parsed resolver.
            ClassDef container = currentClass();
            boolean allMembers = (container.isMember() || container.isTopLevel());
            while (container.isMember()) {
                container = container.outer().get();
                allMembers = allMembers && 
                (container.isMember() || container.isTopLevel());
            }

            if (allMembers) {
                try {
                    X10ClassType t = ct.asType();
                    if (error != null) t = t.error(error);
                    typeSystem().systemResolver().addNamed(QName.make(currentClass().fullName(), ct.name()), t);

                    // Save in the cache using the name a class file would use.
                    QName classFileName = typeSystem().getTransformedClassName(ct);
                    typeSystem().systemResolver().install(classFileName, t);
                } catch (SemanticException e) {
                    Errors.issue(job, e);
                }
            }

            return ct;
	}
	else {
            ct.kind(ClassDef.TOP_LEVEL);
            ct.setJob(job());

            QName fullName;

            if (currentPackage() != null) {
        	ct.setPackage(Types.<Package>ref(currentPackage()));
        	fullName = QName.make(currentPackage().fullName(), ct.name());
            }
            else {
        	fullName = QName.make(null, ct.name());
            }

            List<Type> dups = typeSystem().systemResolver().check(fullName);
            Type dup = null;
            if (dups != null) {
                for (Type q : dups) {
                    if (q instanceof ClassType && q.fullName().equals(fullName)) {
                        dup = q;
                    }
                }
            }

            if (dup != null && dup.fullName().equals(fullName)) {
                if (error == null) {
                    error = new SemanticException("Duplicate class \"" + ct.fullName() + "\".", pos);
                    Errors.issue(job, error);
                }
                Name newName = Name.make(name.toString()+"_dup"+(dupId++));
                ct.name(newName);
                fullName = QName.make(null, newName);
            }

            try {
                X10ClassType t = ct.asType();
                if (error != null) t = t.error(error);
                typeSystem().systemResolver().addNamed(fullName, t);
            } catch (SemanticException e) {
                Errors.issue(job, e);
            }

	    return ct;
	}
    }

    public TypeBuilder pushAnonClass(Position pos) {
        if (reporter.should_report(Reporter.visit, 4))
            reporter.report(4, "TB pushing anon class: " + this);

        if (! inCode) {
            throw new InternalCompilerError(
                "Can only push an anonymous class within code.");
        }

        ClassDef ct = createAnonClass(pos);

        return pushClass(ct);
    }

    protected ClassDef createAnonClass(Position pos) {
        TypeSystem ts = typeSystem();

        ClassDef ct = ts.createClassDef(this.job().source());
        ct.kind(ClassDef.ANONYMOUS);
        ct.outer(Types.ref(currentClass()));
        ct.position(pos);
        ct.setJob(job());

        if (currentPackage() != null) {
            ct.setPackage(Types.<Package>ref(currentPackage()));
        }
        
//        ct.superType(Types.ref(ts.unknownType(pos)));

        return ct;
    }

    public TypeBuilder pushClass(Position pos, Flags flags, Name name) {
        return pushClass(pos, flags, name, null);
    }

    public TypeBuilder pushClass(Position pos, Flags flags, Name name, SemanticException error) {
        ClassDef t = newClass(pos, flags, name, error);
        return pushClass(t);
    }

    public ClassDef currentClass() {
        return this.type;
    }

    public Package currentPackage() {
        return package_;
    }

    public ImportTable importTable() {
        return importTable;
    }

    public void setImportTable(ImportTable it) {
        this.importTable = it;
    }

    public String context() {
        return "(TB " + type +
                (inCode ? " inCode" : "") +
                (global ? " global" : "") +
                (outer == null ? ")" : " " + outer.context() + ")");
    }
}
