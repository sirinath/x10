/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * 
 */

package polyglot.frontend;

import java.util.*;

import polyglot.types.TypeSystem;
import polyglot.util.CodeWriter;
import polyglot.visit.TypeBuilder;
import x10.ast.Node;
import x10.ast.NodeFactory;

/**
 * A <code>Job</code> encapsulates work done by the compiler for a single
 * compilation unit. A <code>Job</code> contains all information for a
 * particular compilation unit carried between phases of the compiler.
 * Only one pass should be run over a job at a time.
 * 
 * TODO: The class should probably be renamed to, say, CompilationUnit.
 */
public class Job
{
    /** Field used for storing extension-specific information. */
    protected JobExt ext;

    /** The language extension used for this job. */
    protected ExtensionInfo lang;

    /** The AST constructed from the source file. */
    protected Node ast;
    
    /** Map for memoizing nodes during type-checking. */
    protected Map<Node,Node> nodeMemo;

    /** True if all passes run so far have been successful. */
    protected boolean status;

    /** Initial count of errors before running the current pass over the job. */
    protected int initialErrorCount;

    /** True if the the job has reported an error. */
    protected boolean reportedErrors;

    /** The <code>Source</code> that this <code>Job</code> represents. */
    protected Source source;

    public Job(ExtensionInfo lang, JobExt ext, Source source, Node ast) {
        this.lang = lang;
        this.ext = ext;
        this.source = source;
        this.ast = ast;

        this.status = true;
        this.initialErrorCount = 0;
        this.reportedErrors = false;
    }
    
    public Map<Node,Node> nodeMemo() {
        if (nodeMemo == null) {
            nodeMemo = new HashMap<Node,Node>();
        }
        return nodeMemo;
    }
    
    public void setNodeMemo(Map<Node,Node> map) {
        this.nodeMemo = map;
    }
    
    public JobExt ext() {
      return ext;
    }

    /** Get the state's AST. */
    public Node ast() {
	return ast;
    }

    /** Set the state's AST. */
    public void ast(Node ast) {
        this.ast = ast;
    }

    /** True if some pass reported an error. */
    public boolean reportedErrors() {
        return reportedErrors;
    }

    public void dump(CodeWriter cw) {
	if (ast != null) {
	    ast.dump(cw);
	}
    }

    /**
     * Return the <code>Source</code> associated with the 
     * <code>SourceJob</code> returned by <code>sourceJob</code>.
     */
    public Source source() {
        return this.source;
    }
    
    /**
     * Returns whether the source for this job was explicitly specified
     * by the user, or if it was drawn into the compilation process due
     * to some dependency.
     */
    public boolean userSpecified() {
        return this.source().userSpecified();
    }

    public void updateStatus(boolean status) {
        if (! status) {
            this.status = false;
        }
    }
   
    public boolean status() {
        return status;
    }

    public ExtensionInfo extensionInfo() {
	return lang;
    }

    public Compiler compiler() {
	return lang.compiler();
    }
    
    public String toString() {
        return source.toString();
    }

    public int hashCode() {
        return source.hashCode();
    }
    
    public boolean equals(Object o) {
        return o instanceof Job && ((Job) o).source.equals(source);
    }

    Goal TypesInitialized;
    
    public Goal TypesInitialized(Scheduler scheduler) {
        if (TypesInitialized == null) {
            Job job = this;
            TypeSystem ts = job.extensionInfo().typeSystem();
            NodeFactory nf = job.extensionInfo().nodeFactory();
            TypesInitialized = new ForgivingVisitorGoal("TypesInitialized", job, new TypeBuilder(job, ts, nf)).intern(scheduler);
        }
        return TypesInitialized;
    }
}
