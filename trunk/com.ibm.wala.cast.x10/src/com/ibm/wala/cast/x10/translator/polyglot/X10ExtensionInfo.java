/*
 * Created on Mar 25, 2006
 */
package com.ibm.domo.ast.x10.translator.polyglot;

import com.ibm.domo.ast.x10.analysis.AnalysisJobExt;

import polyglot.ext.x10.ExtensionInfo;
import polyglot.frontend.Job;
import polyglot.frontend.JobExt;
import polyglot.frontend.Scheduler;
import polyglot.frontend.goals.Goal;

import com.ibm.wala.cast.java.ipa.callgraph.JavaSourceAnalysisScope;
import com.ibm.wala.cast.java.translator.polyglot.PolyglotSourceLoaderImpl;
import com.ibm.wala.types.ClassLoaderReference;

public abstract class X10ExtensionInfo extends ExtensionInfo {
    protected X10SourceLoaderImpl fSourceLoader;

    public void setSourceLoader(PolyglotSourceLoaderImpl sourceLoader) {
	fSourceLoader = (X10SourceLoaderImpl) sourceLoader;
    }

    public X10SourceLoaderImpl getSourceLoader() {
        return fSourceLoader;
    }

    public ClassLoaderReference getSourceLoaderReference() {
	return JavaSourceAnalysisScope.SOURCE_REF;
    }

    protected Scheduler createScheduler() {
        return new X10DOMOScheduler(this);
    }

    public JobExt jobExt() {
	return new AnalysisJobExt();
    }

    public abstract Goal getCompileGoal(Job job);
}

