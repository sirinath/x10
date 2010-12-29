package x10.wala.client;

import java.io.IOException;

import x10.wala.classLoader.X10ClassLoaderFactoryImpl;
import x10.wala.client.impl.X10ZeroXCFABuilderFactory;
import x10.wala.ipa.callgraph.X10SourceAnalysisScope;
import x10.wala.ipa.cha.X10ClassHierarchy;

import com.ibm.wala.cast.ir.ssa.AstIRFactory;
import com.ibm.wala.classLoader.ClassLoaderFactory;
import com.ibm.wala.ipa.callgraph.AnalysisCache;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilder;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.ssa.SSAOptions;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.util.CancelException;

public class X10SourceAnalysisEngine {
    /**
     * A representation of the analysis scope
     */
    protected final AnalysisScope scope = new X10SourceAnalysisScope();

    /**
     * Governing class hierarchy
     */
    protected X10ClassHierarchy cha;

    /**
     * A cache of IRs and stuff
     */
    protected final AnalysisCache cache = new AnalysisCache(AstIRFactory.makeDefaultFactory());

    public X10ClassHierarchy getClassHierarchy() {
        return cha;
    }

    public X10SourceAnalysisEngine() {
        try {
            cha = X10ClassHierarchy.make(scope, new X10ClassLoaderFactoryImpl(scope.getExclusions()));
        } catch (ClassHierarchyException e) {
            System.err.println("Class Hierarchy construction failed");
        }
    }

    public CallGraph buildCallGraph(Iterable<Entrypoint> eps) throws IllegalArgumentException, CancelException, IOException {
        AnalysisOptions options = new AnalysisOptions(scope, eps);
        SSAOptions ssaOptions = new SSAOptions();
        ssaOptions.setDefaultValues(new SSAOptions.DefaultValues() {
            public int getDefaultValue(SymbolTable symtab, int valueNumber) {
                return symtab.getDefaultValue(valueNumber);
            }
        });
        options.setSSAOptions(ssaOptions);
        return new X10ZeroXCFABuilderFactory().make(options, cache, cha, false).makeCallGraph(options, null);
    }

    public void consolidateClassHierarchy() {
        try {
            cha.consolidate();
        } catch (ClassHierarchyException e) {
            System.err.println("Class Hierarchy construction failed");
        }
    }
}
