/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * Copyright (c) 2007 IBM Corporation
 * 
 */

package polyglot.visit;

import java.util.*;

import polyglot.ast.*;
import polyglot.frontend.Globals;
import polyglot.frontend.Job;
import polyglot.types.*;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;

/**
 * Visitor which checks that all local variables must be defined before use, 
 * and that final variables and fields are initialized correctly.
 * 
 * The checking of the rules is implemented in the methods leaveCall(Node)
 * and check(FlowGraph, Term, Item, Item).
 * 
 * If language extensions have new constructs that use local variables, they can
 * override the method <code>checkOther</code> to check that the uses of these
 * local variables are correctly initialized. (The implementation of the method will
 * probably call checkLocalInstanceInit to see if the local used is initialized).
 * 
 * If language extensions have new constructs that assign to local variables,
 * they can override the method <code>flowOther</code> to capture the way 
 * the new construct's initialization behavior.
 * 
 */
public class InitChecker extends DataFlow
{
    public InitChecker(Job job, TypeSystem ts, NodeFactory nf) {
        super(job, ts, nf, 
              true /* forward analysis */,
              false /* perform dataflow when leaving CodeDecls, not when entering */);
    }
    
    protected ClassBodyInfo currCBI = null;
    
    /**
     * This class is just a data structure containing relevant information
     * needed for performing initialization checking of a class declaration.
     * 
     * These objects form a stack, since class declarations can be nested.
     */
    protected static class ClassBodyInfo {
        /** 
         * The info for the outer ClassBody. The <code>ClassBodyInfo</code>s
         * form a stack. 
         */
        public ClassBodyInfo outer = null;
        
        /** The current CodeNode being processed by the dataflow equations */
        public CodeNode currCodeDecl = null;
        
        /** The current class being processed. */
        public ClassDef currClass = null;
        
        /** 
         * A Map of all the final fields in the class currently being processed
         * to MinMaxInitCounts. This Map is used as the basis for the Maps returned
         * in createInitialItem(). 
         * */
        public Map<FieldDef, MinMaxInitCount> currClassFinalFieldInitCounts = new LinkedHashMap<FieldDef, MinMaxInitCount>();
        /**
         * List of all the constructors. These will be checked once all the
         * initializer blocks have been processed.
         */
        public List<ConstructorDecl> allConstructors = new ArrayList<ConstructorDecl>();

        /**
         * Map from ConstructorInstances to ConstructorInstances detailing
         * which constructors call which constructors.
         * This is used in checking the initialization of final fields.
         */
        public Map<ConstructorDef,ConstructorDef> constructorCalls = new LinkedHashMap<ConstructorDef, ConstructorDef>();
        
        /**
         * Map from ConstructorInstances to Sets of FieldInstances, detailing
         * which final non-static fields each constructor initializes. 
         * This is used in checking the initialization of final fields.
         */
        public Map<ConstructorDef, Set<FieldDef>> fieldsConstructorInitializes = new LinkedHashMap<ConstructorDef, Set<FieldDef>>();
        
        /**
         * Set of LocalInstances from the outer class body that were used
         * during the declaration of this class. We need to track this
         * in order to correctly populate <code>localsUsedInClassBodies</code>
         */
        public Set<LocalDef> outerLocalsUsed = new HashSet<LocalDef>();
        
        /**
         * Map from <code>ClassBody</code>s to <code>Set</code>s of 
         * <code>LocalInstance</code>s. If localsUsedInClassBodies(C) = S, then
         * the class body C is an inner class declared in the current code 
         * declaration, and S is the set of LocalInstances that are defined
         * in the current code declaration, but are used in the declaration
         * of the class C. We need this information in order to ensure that
         * these local variables are definitely assigned before the class
         * declaration of C. 
         */
        public Map<Node, Set<LocalDef>> localsUsedInClassBodies = new LinkedHashMap<Node, Set<LocalDef>>();
        
        /**
         * Set of LocalInstances that we have seen declarations for in this 
         * class. This set allows us to determine which local instances
         * are simply being used before they are declared (if they are used in
         * their own initialization) or are locals declared in an enclosing 
         * class.
         */
        public Set<LocalDef> localDeclarations = new HashSet<LocalDef>();
    }


    /**
     * Class representing the initialization counts of variables. The
     * different values of the counts that we are interested in are ZERO,
     * ONE and MANY.
     */
    protected static class InitCount {
        public static InitCount ZERO = new InitCount(0); 
        public static InitCount ONE = new InitCount(1); 
        public static InitCount MANY = new InitCount(2); 
        public int count;
        protected InitCount(int i) {
            count = i;
        }

        public int hashCode() {
            return count;
        }
        
        public boolean equals(Object o) {
            if (o instanceof InitCount) {
                return this.count == ((InitCount)o).count;
            }
            return false;
        }
        
        public String toString() {
            if (count == 0) {
                return "0";
            }
            else if (count == 1) {
                return "1";
            }
            else if (count == 2) {
                return "many";
            }
            throw new RuntimeException("Unexpected value for count");            
        }
        
        public InitCount increment() {
            if (count == 0) {
                return ONE;
            }
            return MANY;
        }
        public static InitCount min(InitCount a, InitCount b) {
            if (ZERO.equals(a) || ZERO.equals(b)) {
                return ZERO;
            }
            if (ONE.equals(a) || ONE.equals(b)) {
                return ONE;
            }
            return MANY;
        }
        public static InitCount max(InitCount a, InitCount b) {
            if (MANY.equals(a) || MANY.equals(b)) {
                return MANY;
            }
            if (ONE.equals(a) || ONE.equals(b)) {
                return ONE;
            }
            return ZERO;
        }
        
    }
    
    /**
     * Class to record counts of the minimum and maximum number of times
     * a variable or field has been initialized or assigned to.
     */
    protected static class MinMaxInitCount {
        protected InitCount min, max;
        MinMaxInitCount(InitCount min, InitCount max) {
            MinMaxInitCount.this.min = min;
            MinMaxInitCount.this.max = max;
        }
        InitCount getMin() { return min; }
        InitCount getMax() { return max; }
        public int hashCode() {
            return min.hashCode() * 4 + max.hashCode();
        }
        public String toString() {
            return "[ min: " + min + "; max: " + max + " ]";
        }
        public boolean equals(Object o) {
            if (o instanceof MinMaxInitCount) {
                return this.min.equals(((MinMaxInitCount)o).min) &&
                       this.max.equals(((MinMaxInitCount)o).max);
            }
            return false;
        }
        static MinMaxInitCount join(MinMaxInitCount initCount1, MinMaxInitCount initCount2) {
            if (initCount1 == null) {
                return initCount2;
            }
            if (initCount2 == null) {
                return initCount1;
            }
            MinMaxInitCount t = new MinMaxInitCount(
                                  InitCount.min(initCount1.getMin(), initCount2.getMin()),
                                  InitCount.max(initCount1.getMax(), initCount2.getMax()));
            return t;

        }
    }
        
    /**
     * Dataflow items for this dataflow are maps of VarInstances to counts
     * of the min and max number of times those variables/fields have
     * been initialized. These min and max counts are then used to determine
     * if variables have been initialized before use, and that final variables
     * are not initialized too many times.
     * 
     * This class is immutable.
     */
    protected static class DataFlowItem extends Item {
        public Map<VarDef, MinMaxInitCount> initStatus; // map of VarInstances to MinMaxInitCount

        DataFlowItem(Map<VarDef, MinMaxInitCount> m) {
            this.initStatus = Collections.unmodifiableMap(m);
        }

        public String toString() {
            return initStatus.toString();
        }

        public boolean equals(Object o) {
            if (o instanceof DataFlowItem) {
                return this.initStatus.equals(((DataFlowItem)o).initStatus);
            }
            return false;
        }
        
        public int hashCode() {
            return (initStatus.hashCode());
        }

    }
    
    protected static class BottomItem extends Item {
        public boolean equals(Object i) {
            return i == this;
        }
        public int hashCode() {
            return -5826349;
        }
    }

    protected static final Item BOTTOM = new BottomItem();

    /**
     * Initialise the FlowGraph to be used in the dataflow analysis.
     * @return null if no dataflow analysis should be performed for this
     *         code declaration; otherwise, an apropriately initialized
     *         FlowGraph.
     */
    protected FlowGraph initGraph(CodeNode code, Term root) {
        currCBI.currCodeDecl = code;
        return new FlowGraph(root, forward);
    }

    /**
     * Overridden superclass method.
     * 
     * Set up the state that must be tracked during a Class Declaration.
     */
    protected NodeVisitor enterCall(Node parent, Node n) throws SemanticException {
        if (n instanceof ClassBody) {
            // we are starting to process a class declaration, but have yet
            // to do any of the dataflow analysis.
            
            // set up the new ClassBodyInfo, and make sure that it forms
            // a stack.
            ClassDef ct = null;
            if (parent instanceof ClassDecl) {
                ct = ((ClassDecl) parent).classDef();
            }
            else if (parent instanceof New) {
                ct = ((New) parent).anonType();
            }
            if (ct == null) {
                throw new InternalCompilerError("ClassBody found but cannot find the class.", n.position());
            }
            setupClassBody(ct, (ClassBody)n);
        }
      
        return super.enterCall(n);
    }

    /**
     * Postpone the checking of constructors until the end of the class 
     * declaration is encountered, to ensure that all initializers are 
     * processed first.
     * 
     * Also, at the end of the class declaration, check that all static final
     * fields have been initialized at least once, and that for each constructor
     * all non-static final fields must have been initialized at least once,
     * taking into account the constructor calls.
     * 
     */
    protected Node leaveCall(Node old, Node n, NodeVisitor v) throws SemanticException {
        if (n instanceof ConstructorDecl) {
            // postpone the checking of the constructors until all the 
            // initializer blocks have been processed.
            currCBI.allConstructors.add((ConstructorDecl) n);
            return n;
        }
        
        if (n instanceof ClassBody) {
            // Now that we are at the end of the class declaration, and can
            // be sure that all of the initializer blocks have been processed,
            // we can now process the constructors.
            
            try {
                for (Iterator<ConstructorDecl> iter = currCBI.allConstructors.iterator(); 
                        iter.hasNext(); ) {
                    ConstructorDecl cd = iter.next();
                    
                    // rely on the fact that our dataflow does not change the
                    // AST, so we can discard the result of this call.

                    dataflow(cd);
                }
                
                // check that all static fields have been initialized exactly
                // once 
                checkStaticFinalFieldsInit((ClassBody)n);
                
                // check that at the end of each constructor all non-static
                // final fields are initialzed.
                checkNonStaticFinalFieldsInit((ClassBody)n);
                
                // copy the locals used to the outer scope
                if (currCBI.outer != null) {
                    currCBI.outer.localsUsedInClassBodies.put(n, 
                                                  currCBI.outerLocalsUsed);
                }
            }
            finally { 
                // pop the stack
                currCBI = currCBI.outer;
            }
        }

        return super.leaveCall(old, n, v);
    }

    protected void setupClassBody(ClassDef ct, ClassBody n) throws SemanticException {
        ClassBodyInfo newCDI = new ClassBodyInfo();
        newCDI.outer = currCBI;  
        newCDI.currClass = ct;
        currCBI = newCDI;
            
        // set up currClassFinalFieldInitCounts to contain mappings
        // for all the final fields of the class.            
        Iterator<ClassMember> classMembers = n.members().iterator();            
        while (classMembers.hasNext()) {
            ClassMember cm = (ClassMember)classMembers.next();
            if (cm instanceof FieldDecl) {
                FieldDecl fd = (FieldDecl)cm;
                if (fd.flags().flags().isFinal()) {
                    MinMaxInitCount initCount;
                    if (fd.init() != null) {
                        // the field has an initializer
                        initCount = new MinMaxInitCount(InitCount.ONE, InitCount.ONE);
                            
                        // do dataflow over the initialization expression
                        // to pick up any uses of outer local variables.
                        if (currCBI.outer != null)
                            dataflow(fd.init());                        
                    }
                    else {
                        // the field does not have an initializer
                        initCount = new MinMaxInitCount(InitCount.ZERO, InitCount.ZERO);
                    }
                    newCDI.currClassFinalFieldInitCounts.put(fd.fieldDef(),
                                                         initCount);
                }
            }
        }             
    }
    
    /**
     * Check that each static final field is initialized exactly once.
     * 
     * @param cb The ClassBody of the class declaring the fields to check.
     * @throws SemanticException
     */
    protected void checkStaticFinalFieldsInit(ClassBody cb) throws SemanticException {
        // check that all static fields have been initialized exactly once.             
        for (Iterator<Map.Entry<FieldDef,MinMaxInitCount>> iter = currCBI.currClassFinalFieldInitCounts.entrySet().iterator(); 
                iter.hasNext(); ) {
            Map.Entry<FieldDef,MinMaxInitCount> e = (Map.Entry<FieldDef,MinMaxInitCount>)iter.next();
            if (e.getKey() instanceof FieldDef) {
                FieldDef fi = (FieldDef)e.getKey();
                if (fi.flags().isStatic() && fi.flags().isFinal()) {
                    MinMaxInitCount initCount = (MinMaxInitCount)e.getValue();
                    if (InitCount.ZERO.equals(initCount.getMin())) {
                        throw new SemanticException("Final field \"" + fi.name() +
                            "\" might not have been initialized",
                            cb.position());                                
                    }
                }
            }
        }   
    }
    
    /**
     * Check that each non static final field has been initialized exactly once,
     * taking into account the fact that constructors may call other 
     * constructors. 
     * 
     * @param cb The ClassBody of the class declaring the fields to check.
     * @throws SemanticException
     */
    protected void checkNonStaticFinalFieldsInit(ClassBody cb) throws SemanticException {
        // for each non-static final field def, check that all 
        // constructors intialize it exactly once, taking into account constructor calls.
        for (Iterator<FieldDef> iter = currCBI.currClassFinalFieldInitCounts.keySet().iterator(); 
                iter.hasNext(); ) {
            FieldDef fi = iter.next();
            if (fi.flags().isFinal() && !fi.flags().isStatic()) {
                // the field is final and not static
                // it must be initialized exactly once.
                // navigate up through all of the the constructors
                // that this constructor calls.
                                    
                boolean fieldInitializedBeforeConstructors = false;
                MinMaxInitCount ic = currCBI.currClassFinalFieldInitCounts.get(fi);
                if (ic != null && !InitCount.ZERO.equals(ic.getMin())) {
                    fieldInitializedBeforeConstructors = true;
                }
                            
                for (Iterator<ConstructorDecl> iter2 = currCBI.allConstructors.iterator(); 
                        iter2.hasNext(); ) {
                    ConstructorDecl cd = (ConstructorDecl)iter2.next();
                    ConstructorDef ciStart = cd.constructorDef();
                    ConstructorDef ci = ciStart;
                    
                    boolean isInitialized = fieldInitializedBeforeConstructors;
                    
                    // A constructor with no body (a native constructor) is assumed to
                    // initialize all fields.
                    if (cd.body() == null) {
                	isInitialized = true;
                    }
                    
                    while (ci != null) {
                        Set<FieldDef> s = currCBI.fieldsConstructorInitializes.get(ci);
                        if (s != null && s.contains(fi)) {
                            if (isInitialized) {
                                throw new SemanticException("Final field \"" + fi.name() +
                                        "\" might have already been initialized",
                                        cd.position());                                                                        
                            }
                            isInitialized = true;
                        }                                
                        ci = (ConstructorDef)currCBI.constructorCalls.get(ci);
                    }
                    if (!isInitialized) {
                        throw new SemanticException("Final field \"" + fi.name() +
                                "\" might not have been initialized",
                                ciStart.position());                                
                                
                    }                            
                }
            }
        }        
    }
    
    /**
     * Construct a flow graph for the <code>Expr</code> provided, and call 
     * <code>dataflow(FlowGraph)</code>. Is also responsible for calling 
     * <code>post(FlowGraph, Term)</code> after
     * <code>dataflow(FlowGraph)</code> has been called.
     * There is no need to push a CFG onto the stack, as dataflow is not
     * performed on entry in this analysis. 
     */
    protected void dataflow(Expr root) throws SemanticException {
        // Build the control flow graph.
        FlowGraph g = new FlowGraph(root, forward);
        CFGBuilder v = createCFGBuilder(ts, g);
        v.visitGraph();
        dataflow(g);
        post(g, root);        
    }
    
    /**
     * The initial item to be given to the entry point of the dataflow contains
     * the init counts for the final fields.
     */
    public Item createInitialItem(FlowGraph graph, Term node, boolean entry) {
        if (node == graph.root() && entry) {
            return createInitDFI();
        }
        return BOTTOM;
    }

    private DataFlowItem createInitDFI() {
        return new DataFlowItem(new LinkedHashMap<VarDef, MinMaxInitCount>(currCBI.currClassFinalFieldInitCounts));
    }
    
    /**
     * The confluence operator for <code>Initializer</code>s and 
     * <code>Constructor</code>s needs to be a 
     * little special, as we are only concerned with non-exceptional flows in 
     * these cases.
     * This method ensures that a slightly different confluence is performed
     * for these <code>Term</code>s, otherwise 
     * <code>confluence(List, Term)</code> is called instead. 
     */
    protected Item confluence(List items, List itemKeys, 
            Term node, boolean entry, FlowGraph graph) {
        if (node instanceof Initializer || node instanceof ConstructorDecl) {
            List filtered = filterItemsNonException(items, itemKeys);
            if (filtered.isEmpty()) {
                return createInitDFI();
            }
            else if (filtered.size() == 1) {
                return (Item)filtered.get(0);
            }
            else {
               return confluence(filtered, node, entry, graph);
            } 
        }
        return confluence(items, node, entry, graph); 
    }

    /**
     * The confluence operator is essentially the union of all of the
     * inItems. However, if two or more of the initCount maps from
     * the inItems each have a MinMaxInitCounts entry for the same
     * VarInstance, the conflict must be resolved, by using the
     * minimum of all mins and the maximum of all maxs. 
     */
    public Item confluence(List inItems, Term node, boolean entry, FlowGraph graph) {        
        // Resolve any conflicts pairwise.
        Iterator<Item> iter = inItems.iterator();
        Map<VarDef, MinMaxInitCount> m = null;
        while (iter.hasNext()) {
            Item itm = (Item)iter.next();
            if (itm == BOTTOM) continue;
            if (m == null) {
                m = new LinkedHashMap<VarDef, MinMaxInitCount>(((DataFlowItem)itm).initStatus);
            } 
            else { 
                Map<VarDef, MinMaxInitCount> n = ((DataFlowItem)itm).initStatus;
                for (Iterator<Map.Entry<VarDef, MinMaxInitCount>> iter2 = n.entrySet().iterator(); iter2.hasNext(); ) {
                    Map.Entry<VarDef, MinMaxInitCount> e = (Map.Entry<VarDef, MinMaxInitCount>)iter2.next();
                    VarDef v = (VarDef)e.getKey();
                    MinMaxInitCount initCount1 = m.get(v);
                    MinMaxInitCount initCount2 = (MinMaxInitCount)e.getValue();
                    m.put(v, MinMaxInitCount.join(initCount1, initCount2));                                        
                }
            }
        }
        
        if (m == null) return BOTTOM;
        
        return new DataFlowItem(m);
    }
    

    protected Map flow(List inItems, List inItemKeys, FlowGraph graph, 
            Term n, boolean entry, Set edgeKeys) {
        return this.flowToBooleanFlow(inItems, inItemKeys, graph, n, entry, edgeKeys);
    }

    /**
     * Perform the appropriate flow operations for the Terms. This method
     * delegates to other appropriate methods in this class, for modularity.
     * 
     * To summarize:
     * - Formals: declaration of a Formal param, just insert a new 
     *              MinMaxInitCount for the LocalInstance.
     * - LocalDecl: a declaration of a local variable, just insert a new 
     *              MinMaxInitCount for the LocalInstance as appropriate
     *              based on whether the declaration has an initializer or not.
     * - Assign: if the LHS of the assign is a local var or a field that we
     *              are interested in, then increment the min and max counts
     *              for that local var or field.   
     */
    public Map flow(Item trueItem, Item falseItem, Item otherItem,
            FlowGraph graph, Term n, boolean entry, Set succEdgeKeys) {
        Item inItem = safeConfluence(trueItem, FlowGraph.EDGE_KEY_TRUE, 
                                     falseItem, FlowGraph.EDGE_KEY_FALSE,
                                     otherItem, FlowGraph.EDGE_KEY_OTHER,
                                     n, entry, graph);
        if (entry) {
            return itemToMap(inItem, succEdgeKeys);
        }
        
        if (inItem == BOTTOM) {
            return itemToMap(BOTTOM, succEdgeKeys);           
        }                                     
        
        DataFlowItem inDFItem = ((DataFlowItem)inItem);
        Map ret = null;        
        if (n instanceof Formal) {            
            // formal argument declaration.
            ret = flowFormal(inDFItem, graph, (Formal)n, succEdgeKeys);
        }
        else if (n instanceof LocalDecl) {
            // local variable declaration.
            ret = flowLocalDecl(inDFItem, graph, (LocalDecl)n, succEdgeKeys);
        }
        else if (n instanceof LocalAssign) {
            // assignment to a local variable
            ret = flowLocalAssign(inDFItem, graph, (LocalAssign)n, succEdgeKeys);
        }
        else if (n instanceof FieldAssign) {
            // assignment to a field
            ret = flowFieldAssign(inDFItem, graph, (FieldAssign)n, succEdgeKeys);
        }
        else if (n instanceof ConstructorCall) {
            // call to another constructor.
            ret = flowConstructorCall(inDFItem, graph, (ConstructorCall)n, succEdgeKeys);
        }
        else if (n instanceof Expr && ((Expr)n).type().isBoolean() && 
                    (n instanceof Binary || n instanceof Unary)) {
            if (trueItem == null) trueItem = inDFItem;
            if (falseItem == null) falseItem = inDFItem;
            ret = flowBooleanConditions(trueItem, falseItem, inDFItem, graph, (Expr)n, succEdgeKeys);                        
        } 
        else {
            ret = flowOther(inDFItem, graph, n, succEdgeKeys);
        }
        if (ret != null) {
            return ret;
        }
        return itemToMap(inItem, succEdgeKeys);
    }

    /**
     * Perform the appropriate flow operations for declaration of a formal 
     * parameter
     */
    protected Map flowFormal(DataFlowItem inItem, FlowGraph graph, Formal f, Set succEdgeKeys) {
        Map<VarDef, MinMaxInitCount> m = new LinkedHashMap<VarDef, MinMaxInitCount>(inItem.initStatus);
        // a formal argument is always defined.            
        m.put(f.localDef(), new MinMaxInitCount(InitCount.ONE,InitCount.ONE));
            
        // record the fact that we have seen the formal declaration
        currCBI.localDeclarations.add(f.localDef());

        return itemToMap(new DataFlowItem(m), succEdgeKeys);
    }
    
    /**
     * Perform the appropriate flow operations for declaration of a local 
     * variable
     */
    protected Map flowLocalDecl(DataFlowItem inItem, 
                                FlowGraph graph, 
                                LocalDecl ld, 
                                Set succEdgeKeys) {
        Map<VarDef, MinMaxInitCount> m = new LinkedHashMap<VarDef, MinMaxInitCount>(inItem.initStatus);
        MinMaxInitCount initCount = m.get(ld.localDef());
        //if (initCount == null) {
            if (ld.init() != null) {
                // declaration of local var with initialization.
                initCount = new MinMaxInitCount(InitCount.ONE,
                                                InitCount.ONE);
            }
            else {
                // declaration of local var with no initialization.
                initCount = new MinMaxInitCount(InitCount.ZERO,InitCount.ZERO);
            }     

            m.put(ld.localDef(), initCount);
//        }
//        else {
            // the initCount is not null. We now have a problem. Why is the
            // initCount not null? Has this variable been assigned in its own
            // initialization, or is this a declaration inside a loop body?
            // XXX@@@ THIS IS A BUG THAT NEEDS TO BE FIXED.
            // Currently, the declaration "final int i = (i=5);" will 
            // not be rejected, as we cannot distinguish between that and
            // "while (true) {final int i = 4;}"
//        }
                
        // record the fact that we have seen a local declaration
        currCBI.localDeclarations.add(ld.localDef());
        
        return itemToMap(new DataFlowItem(m), succEdgeKeys);
    }
    
    /**
     * Perform the appropriate flow operations for assignment to a local 
     * variable
     */
    protected Map flowLocalAssign(DataFlowItem inItem, 
                                  FlowGraph graph, 
                                  LocalAssign a, 
                                  Set succEdgeKeys) {
          Local l = (Local) a.local();
          Map<VarDef, MinMaxInitCount> m = new LinkedHashMap<VarDef, MinMaxInitCount>(inItem.initStatus);
          MinMaxInitCount initCount = m.get(l.localInstance().def());

          // initcount could be null if the local is defined in the outer
          // class, or if we have not yet seen its declaration (i.e. the
          // local is used in its own initialization)
          if (initCount == null) {
              initCount = new MinMaxInitCount(InitCount.ZERO,InitCount.ZERO);
          }

          initCount = new MinMaxInitCount(initCount.getMin().increment(),
                                          initCount.getMax().increment());

          m.put(l.localInstance().def(), initCount);
          return itemToMap(new DataFlowItem(m), succEdgeKeys);  
    }

    /**
     * Perform the appropriate flow operations for assignment to a field
     */
    protected Map flowFieldAssign(DataFlowItem inItem, 
                                  FlowGraph graph, 
                                  FieldAssign a, 
                                  Set succEdgeKeys) {
        FieldDef fi = a.fieldInstance().def();
        
        if (fi.flags().isFinal() && isFieldsTargetAppropriate(a)) {
            // this field is final and the target for this field is 
            // appropriate for what we are interested in.
            Map<VarDef, MinMaxInitCount> m = new LinkedHashMap<VarDef, MinMaxInitCount>(inItem.initStatus);
            MinMaxInitCount initCount = m.get(fi);
            // initCount may be null if the field is defined in an
            // outer class.
            if (initCount != null) {
                initCount = new MinMaxInitCount(initCount.getMin().increment(),
                          initCount.getMax().increment());
                m.put(fi, initCount);
                return itemToMap(new DataFlowItem(m), succEdgeKeys);
            }
        }
        return null;
    }
                                  
    /**
     * Perform the appropriate flow operations for a constructor call
     */
    protected Map flowConstructorCall(DataFlowItem inItem, 
                                      FlowGraph graph, 
                                      ConstructorCall cc, 
                                      Set succEdgeKeys) {
        if (ConstructorCall.THIS.equals(cc.kind())) {
            // currCodeDecl must be a ConstructorDecl, as that
            // is the only place constructor calls are allowed
            // record the fact that the current constructor calls the other
            // constructor
            currCBI.constructorCalls.put(
                ((ConstructorDecl)currCBI.currCodeDecl).constructorDef(), 
                                 cc.constructorInstance().def());
        }
        return null;
    }
    
    /**
     * Allow subclasses to override if necessary.
     */
    protected Map flowOther(DataFlowItem inItem, FlowGraph graph, Node n, Set succEdgeKeys) {
        return null;
    }
    
    /**
     * Determine if we are interested in this field on the basis of the
     * target of the field. To wit, if the field
     * is static, then the target of the field must be the current class; if
     * the field is not static then the target must be "this".
     */
    protected boolean isFieldsTargetAppropriate(Field f) {
        if (currCBI == null || currCBI.currCodeDecl == null)
            return false;
	CodeDef ci = currCBI.currCodeDecl.codeDef();
	ClassType containingClass = currCBI.currClass.asType();
	
	if (f.fieldInstance().flags().isStatic()) {
	    Type container = f.fieldInstance().def().container().get();
	    return container instanceof ClassType && containingClass.def().equals(((ClassType) container).def());
	}
	else {
	    if (f.target() instanceof Special) {
		Special s = (Special) f.target();
		if (Special.THIS.equals(s.kind())) {
		    return s.qualifier() == null
		    || (s.qualifier().type() instanceof ClassType &&
			    containingClass.def().equals(((ClassType) s.qualifier().type()).def()));
		}
	    }
	    return false;
	}
    }
    /**
     * Determine if we are interested in this field on the basis of the
     * target of the field. To wit, if the field
     * is static, then the target of the field must be the current class; if
     * the field is not static then the target must be "this".
     */
    protected boolean isFieldsTargetAppropriate(FieldAssign f) {
        if (currCBI == null || currCBI.currCodeDecl == null)
            return false;
        CodeDef ci = currCBI.currCodeDecl.codeDef();
        ClassType containingClass = currCBI.currClass.asType();

        if (f.fieldInstance().flags().isStatic()) {
            Type container = f.fieldInstance().def().container().get();
            return container instanceof ClassType && containingClass.def().equals(((ClassType) container).def());
        }
        else {
            if (f.target() instanceof Special) {
                Special s = (Special) f.target();
                if (Special.THIS.equals(s.kind())) {
                    return s.qualifier() == null
                    || (s.qualifier().type() instanceof ClassType &&
                         containingClass.def().equals(((ClassType) s.qualifier().type()).def()));
                }
            }
            return false;
        }
    }
    /**
     * Check that the conditions of initialization are not broken.
     * 
     * To summarize the conditions:
     * - Local variables must be initialized before use, (i.e. min count > 0)
     * - Final local variables (including Formals) cannot be assigned to more 
     *               than once (i.e. max count <= 1)
     * - Final non-static fields whose target is this cannot be assigned to
     *               more than once 
     * - Final static fields whose target is the current class cannot be 
     *               assigned to more than once
     *               
     * 
     * This method is also responsible for maintaining state between the 
     * dataflows over Initializers, by copying back the appropriate 
     * MinMaxInitCounts to the map currClassFinalFieldInitCounts.
     */
    public void check(FlowGraph graph, Term n, boolean entry, Item inItem, Map outItems) throws SemanticException {
        DataFlowItem dfIn = (DataFlowItem)inItem;        
        if (dfIn == null) {
            // There is no input data flow item. This can happen if we are 
            // checking an unreachable term, and so no Items have flowed 
            // through the term. For example, in the code fragment:
            //     a: do { break a; } while (++i < 10);
            // the expression "++i < 10" is unreachable, but the as there is
            // no unreachable statement, the Java Language Spec permits it.
            
            // Set inItem to a default Item
            dfIn = createInitDFI();
        }
        
        long t = System.currentTimeMillis();
        
        DataFlowItem dfOut = null;
        if (!entry && outItems != null && !outItems.isEmpty()) {
            // due to the flow equations, all DataFlowItems in the outItems map
            // are the same, so just take the first one.
            dfOut = (DataFlowItem)outItems.values().iterator().next(); 
        
            if (n instanceof Local) {
                checkLocal(graph, (Local)n, dfIn, dfOut);
            }
            else if (n instanceof Field) {
        	checkField(graph, (Field)n, dfIn, dfOut);
            }
            else if (n instanceof LocalAssign) {
                checkLocalAssign(graph, (LocalAssign)n, dfIn, dfOut);
            }
            else if (n instanceof FieldAssign) {
                checkFieldAssign(graph, (FieldAssign)n, dfIn, dfOut);
            }
            else if (n instanceof ClassBody) {
                checkClassBody(graph, (ClassBody)n, dfIn, dfOut);
            }
            else {
                checkOther(graph, n, dfIn, dfOut);            
            }
        }
        else {
            // this local assign node has not had data flow performed over it.
            // probably a node in a finally block. Just ignore it.
        }
        
        long t2 = System.currentTimeMillis();
        
        if (n == graph.root() && !entry) {            
            if (currCBI.currCodeDecl instanceof Initializer) {
                finishInitializer(graph, 
                                (Initializer)currCBI.currCodeDecl, 
                                dfIn, 
                                dfOut);
            }
            if (currCBI.currCodeDecl instanceof ConstructorDecl) {
                finishConstructorDecl(graph, 
                                    (ConstructorDecl)currCBI.currCodeDecl, 
                                    dfIn, 
                                    dfOut);
            }
        }
        
        long t3 = System.currentTimeMillis();
        
        Globals.Stats().accumulate("InitChecker.check", 1);
        Globals.Stats().accumulate("InitChecker.1", (t2-t));
        Globals.Stats().accumulate("InitChecker.2", (t3-t2));
        Globals.Stats().accumulate("InitChecker.1+2", (t3-t));
    }

    /**
     * Perform necessary actions upon seeing the Initializer 
     * <code>initializer</code>.
     */
    protected void finishInitializer(FlowGraph graph, 
                                    Initializer initializer, 
                                    DataFlowItem dfIn, 
                                    DataFlowItem dfOut) {
        // We are finishing the checking of an intializer.
        // We need to copy back the init counts of any fields back into
        // currClassFinalFieldInitCounts, so that the counts are 
        // correct for the next initializer or constructor.
        Iterator<Map.Entry<VarDef,MinMaxInitCount>> iter = dfOut.initStatus.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<VarDef,MinMaxInitCount> e = (Map.Entry<VarDef,MinMaxInitCount>) iter.next();
            if (e.getKey() instanceof FieldDef) {
                FieldDef fi = (FieldDef)e.getKey();
                if (fi.flags().isFinal()) {
                    // we don't need to join the init counts, as all
                    // dataflows will go through all of the 
                    // initializers
                    currCBI.currClassFinalFieldInitCounts.put(fi, 
                            e.getValue());
                }
            }
        }                
    }

    /**
     * Perform necessary actions upon seeing the ConstructorDecl 
     * <code>cd</code>.
     */
    protected void finishConstructorDecl(FlowGraph graph, 
                                        ConstructorDecl cd, 
                                        DataFlowItem dfIn, 
                                        DataFlowItem dfOut) {
        ConstructorDef ci = cd.constructorDef();
        
        // we need to set currCBI.fieldsConstructorInitializes correctly.
        // It is meant to contain the non-static final fields that the
        // constructor ci initializes.
        //
        // Note that dfOut.initStatus contains only the MinMaxInitCounts
        // for _normal_ termination of the constructor (see the
        // method confluence). This means that if dfOut says the min
        // count of the initialization for a final non-static field
        // is one, and that is different from what is recoreded in
        // currCBI.currClassFinalFieldInitCounts (which is the counts
        // of the initializations performed by initializers), then
        // the constructor does indeed initialize the field.

        Set<FieldDef> s = new HashSet<FieldDef>();
                
        // go through every final non-static field in dfOut.initStatus
        Iterator<Map.Entry<VarDef,MinMaxInitCount>> iter = dfOut.initStatus.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<VarDef,MinMaxInitCount> e = (Map.Entry<VarDef,MinMaxInitCount>)iter.next();
            if (e.getKey() instanceof FieldDef && 
                    ((FieldDef)e.getKey()).flags().isFinal() && 
                    !((FieldDef)e.getKey()).flags().isStatic()) {
                // we have a final non-static field                           
                FieldDef fi = (FieldDef)e.getKey();
                MinMaxInitCount initCount = (MinMaxInitCount)e.getValue();
                MinMaxInitCount origInitCount = currCBI.currClassFinalFieldInitCounts.get(fi);
                if (initCount.getMin() == InitCount.ONE &&
                     (origInitCount == null || origInitCount.getMin() == InitCount.ZERO)) {
                    // the constructor initialized this field
                    s.add(fi);
                }
            }
        }
        if (!s.isEmpty()) {
            currCBI.fieldsConstructorInitializes.put(ci, s);
        }
    }
    
    /**
     * Check that the field access <code>f</code> is used correctly.
     */
    protected void checkField(FlowGraph graph, 
	    Field f, 
	    DataFlowItem dfIn, 
	    DataFlowItem dfOut) 
    throws SemanticException {
	if (isFieldsTargetAppropriate(f) && f.flags().isFinal() && (currCBI.currCodeDecl instanceof ConstructorDecl || currCBI.currCodeDecl instanceof FieldDecl)) {
	    MinMaxInitCount initCount = dfIn.initStatus.get(f.fieldInstance().def());         
	    if (initCount != null && InitCount.ZERO.equals(initCount.getMin())) {
		// the field may not have been initialized. 
		// However, we only want to complain if the field is reachable
		if (currCBI.currCodeDecl instanceof ConstructorDecl) {
		    ConstructorDecl cd = (ConstructorDecl) currCBI.currCodeDecl;
		    if (cd.body() == null)
			return;
		    if (cd.body().statements().size() > 0 && cd.body().statements().get(0) instanceof ConstructorCall) {
			ConstructorCall cc = (ConstructorCall) cd.body().statements().get(0);
			if (cc.kind() == ConstructorCall.THIS)
			    return;
		    }
		}
		if (f.reachable()) {
		    throw new SemanticException("Field \"" + f.name().id() +
		                                "\" may not have been initialized",
		                                f.position());
		}
	    }
	}
    }
    
    
    /**
     * Check that the local variable <code>l</code> is used correctly.
     */
    protected void checkLocal(FlowGraph graph, 
                              Local l, 
                              DataFlowItem dfIn, 
                              DataFlowItem dfOut) 
        throws SemanticException {
        if (!currCBI.localDeclarations.contains(l.localInstance().def())) {
            // it's a local variable that has not been declared within
            // this scope. The only way this can arise is from an
            // inner class that is not a member of a class (typically
            // a local class, or an anonymous class declared in a method,
            // constructor or initializer).
            // We need to check that it is a final local, and also
            // keep track of it, to ensure that it has been definitely
            // assigned at this point.
            currCBI.outerLocalsUsed.add(l.localInstance().def());                
        }
        else { 
            MinMaxInitCount initCount = dfIn.initStatus.get(l.localInstance().def());         
            if (initCount != null && InitCount.ZERO.equals(initCount.getMin())) {
                // the local variable may not have been initialized. 
                // However, we only want to complain if the local is reachable
                if (l.reachable()) {
                    throw new SemanticException("Local variable \"" + l.name().id() +
                            "\" may not have been initialized",
                            l.position());
            	}
            }
        }
    }
    
    protected void checkLocalInstanceInit(LocalDef li, 
                                          DataFlowItem dfIn, 
                                          Position pos) 
    throws SemanticException {
        MinMaxInitCount initCount = dfIn.initStatus.get(li);         
        if (initCount != null && InitCount.ZERO.equals(initCount.getMin())) {
            // the local variable may not have been initialized. 
            throw new SemanticException("Local variable \"" + li.name() +
                                        "\" may not have been initialized",
                                        pos);
	}
    }
        
    /**
     * Check that the assignment to a local variable is correct.
     */
    protected void checkLocalAssign(FlowGraph graph, 
                                    LocalAssign a, 
                                    DataFlowItem dfIn, 
                                    DataFlowItem dfOut) 
        throws SemanticException {
        LocalDef li = ((Local)a.local()).localInstance().def();
        if (!currCBI.localDeclarations.contains(li)) {
            throw new SemanticException("Final local variable \"" + li.name() +
                    "\" cannot be assigned to in an inner class.",
                    a.position());                     
        }
        
        MinMaxInitCount initCount = dfOut.initStatus.get(li);                                

        if (li.flags().isFinal() && InitCount.MANY.equals(initCount.getMax())) {
            throw new SemanticException("Final variable \"" + li.name() +
                                        "\" might already have been initialized",
                                        a.position());
        }
    }

    /**
     * Check that the assignment to a field is correct.
     */
    protected void checkFieldAssign(FlowGraph graph, 
                                    FieldAssign a, 
                                    DataFlowItem dfIn, 
                                    DataFlowItem dfOut) 
        throws SemanticException {

        FieldDef fi = a.fieldInstance().def();
        if (fi.flags().isFinal()) {
            if ((currCBI.currCodeDecl instanceof ConstructorDecl ||
                    currCBI.currCodeDecl instanceof Initializer) &&
                    isFieldsTargetAppropriate(a)) {
                // we are in a constructor or initializer block and 
                // if the field is static then the target is the class
                // at hand, and if it is not static then the
                // target of the field is this. 
                // So a final field in this situation can be 
                // assigned to at most once.                    
                MinMaxInitCount initCount = dfOut.initStatus.get(fi);
                if (initCount == null) {
                    // This should not happen.
                    throw new InternalCompilerError(
                            "Dataflow information not found for field \"" +
                            fi.name() + "\".",
                            a.position());
                }
                if (InitCount.MANY.equals(initCount.getMax())) {
                    throw new SemanticException("Final field \"" + fi.name() +
                            "\" might already have been initialized",
                            a.position());
                }                                    
            }
            else {
                // not in a constructor or intializer, or the target is
                // not appropriate. So we cannot assign 
                // to a final field at all.
                throw new SemanticException("Cannot assign a value " +
                           "to final field \"" + fi.name() + "\" of \"" +
                           fi.container() + "\".",
                           a.position());
            }
        }                        
    }
    /**
     * Check that the set of <code>LocalInstance</code>s 
     * <code>localsUsed</code>, which is the set of locals used in the inner 
     * class declared by <code>cb</code>
     * are initialized before the class declaration.
     * @throws SemanticException
     */
    protected void checkClassBody(FlowGraph graph, 
                                  ClassBody cb, 
                                  DataFlowItem dfIn, 
                                  DataFlowItem dfOut) 
    throws SemanticException {  
        // we need to check that the locals used inside this class body
        // have all been defined at this point.
        Set localsUsed = currCBI.localsUsedInClassBodies.get(cb);
    
        if (localsUsed != null) {
            checkLocalsUsedByInnerClass(graph, 
                                        cb, 
                                        localsUsed,
                                        dfIn, 
                                        dfOut);
        }
    }            
    
    /**
     * Check that the set of <code>LocalInstance</code>s 
     * <code>localsUsed</code>, which is the set of locals used in the inner 
     * class declared by <code>cb</code>
     * are initialized before the class declaration.
     */
    protected void checkLocalsUsedByInnerClass(FlowGraph graph, 
                                               ClassBody cb,
                                               Set<LocalDef> localsUsed,
                                               DataFlowItem dfIn,
                                               DataFlowItem dfOut) 
    throws SemanticException {
        for (Iterator<LocalDef> iter = localsUsed.iterator(); iter.hasNext(); ) {
            LocalDef li = (LocalDef)iter.next();
            MinMaxInitCount initCount = dfOut.initStatus.get(li);                                
            if (!currCBI.localDeclarations.contains(li)) {
                // the local wasn't defined in this scope.
                currCBI.outerLocalsUsed.add(li);
            }
            else if (initCount == null || InitCount.ZERO.equals(initCount.getMin())) {
                // initCount will in general not be null, as the local variable
                // li is declared in the current class; however, if the inner
                // class is declared in the initializer of the local variable
                // declaration, then initCount could in fact be null, as we 
                // leave the inner class before we have performed flowLocalDecl
                // for the local variable declaration.
                
                throw new SemanticException("Local variable \"" + li.name() +
                        "\" must be initialized before the class " + 
                        "declaration.",
                        cb.position());
            }            
        }
    }

    /**
     * Allow subclasses to override the checking of other nodes, if needed.
     */
    protected void checkOther(FlowGraph graph, 
                              Node n, 
                              DataFlowItem dfIn, 
                              DataFlowItem dfOut) 
    throws SemanticException {
    }    
}
