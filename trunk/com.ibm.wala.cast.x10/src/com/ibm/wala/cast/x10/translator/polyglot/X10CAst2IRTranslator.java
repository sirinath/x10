/*
 * Created on Sep 26, 2005
 */
package com.ibm.domo.ast.x10.translator.polyglot;

import com.ibm.domo.ast.x10.ssa.AsyncCallSiteReference;
import com.ibm.domo.ast.x10.ssa.AsyncInvokeInstruction;
import com.ibm.domo.ast.x10.ssa.SSAAtomicInstruction;
import com.ibm.domo.ast.x10.ssa.SSAFinishInstruction;
import com.ibm.domo.ast.x10.ssa.SSAForceInstruction;
import com.ibm.domo.ast.x10.ssa.SSAHereInstruction;
import com.ibm.domo.ast.x10.ssa.SSARegionIterHasNextInstruction;
import com.ibm.domo.ast.x10.ssa.SSARegionIterInitInstruction;
import com.ibm.domo.ast.x10.ssa.SSARegionIterNextInstruction;
import com.ibm.domo.ast.x10.ssa.X10ArrayLoadByIndexInstruction;
import com.ibm.domo.ast.x10.ssa.X10ArrayStoreByIndexInstruction;
import com.ibm.domo.ast.x10.translator.X10CAstEntity;
import com.ibm.domo.ast.x10.visit.X10CAstVisitor;
import com.ibm.wala.cast.ir.translator.ArrayOpHandler;
import com.ibm.wala.cast.ir.translator.AstTranslator.DefaultContext;
import com.ibm.wala.cast.ir.translator.AstTranslator.WalkContext;
import com.ibm.wala.cast.java.translator.JavaCAst2IRTranslator;
import com.ibm.wala.cast.java.types.JavaPrimitiveTypeMap;
import com.ibm.wala.cast.tree.CAstEntity;
import com.ibm.wala.cast.tree.CAstNode;
import com.ibm.wala.cast.tree.CAstType;
import com.ibm.wala.cast.tree.visit.CAstVisitor;
import com.ibm.wala.classLoader.NewSiteReference;
import com.ibm.wala.ssa.SSAInstructionFactory;
import com.ibm.wala.types.Descriptor;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.TypeName;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.Atom;
import com.ibm.wala.util.debug.Assertions;
import com.ibm.wala.util.debug.Trace;

public class X10CAst2IRTranslator extends X10CAstVisitor implements ArrayOpHandler {
    public X10CAst2IRTranslator(CAstEntity sourceFileEntity, X10SourceLoaderImpl loader) {
	this(new JavaCAst2IRTranslator(sourceFileEntity, loader));
    }

    private final JavaCAst2IRTranslator translator;

    private X10CAst2IRTranslator(JavaCAst2IRTranslator translator) {
	super(translator);
	this.translator = translator;
	this.translator.setArrayOpHandler(this);
    }

    protected boolean visitFunctionExpr(CAstNode n, Context c, CAstVisitor visitor) {
	CAstEntity fn= (CAstEntity) n.getChild(0).getValue();

	if (fn.getKind() == X10CAstEntity.ASYNC_BODY)
	    declareAsync(fn, (WalkContext) c);
	else if (fn.getKind() == X10CAstEntity.CLOSURE_BODY)
	    declareClosure(fn, (WalkContext) c);
	return false;
    }

    protected void leaveFunctionExpr(CAstNode n, Context c, CAstVisitor visitor) {
	int result;
	CAstEntity fn= (CAstEntity) n.getChild(0).getValue();

	if (fn.getKind() == X10CAstEntity.ASYNC_BODY)
	    result= processAsyncExpr(n, c);
	else if (fn.getKind() == X10CAstEntity.CLOSURE_BODY)
	    result= processClosureExpr(n, c);
	else {
	    Assertions.UNREACHABLE("FUNCTION_EXPR neither async nor closure in leaveFunctionExpr().");
	    return;
	}
        translator.setValue(n, result);
    }

    private int processAsyncExpr(CAstNode n, Context c) {
	WalkContext context= (WalkContext) c;
	CAstEntity fn= (CAstEntity) n.getChild(0).getValue();
	int result= context.currentScope().allocateTempValue();
	int ex= context.currentScope().allocateTempValue();
	doMaterializeAsync(context, result, ex, fn);
	return result;
    }

    private void doMaterializeAsync(WalkContext context, int result, int ex, CAstEntity fn) {
	TypeReference asyncRef= asyncTypeReference(fn);

	context.cfg().addInstruction(SSAInstructionFactory.NewInstruction(result,
		NewSiteReference.make(context.cfg().getCurrentInstruction(), asyncRef)));
    }

    private void declareAsync(CAstEntity fn, WalkContext context) {
	TypeReference asyncRef= asyncTypeReference(fn);

	((X10SourceLoaderImpl) translator.loader()).defineAsync(fn, asyncRef, fn.getPosition());
    }

    private int processClosureExpr(CAstNode n, Context c) {
	WalkContext context= (WalkContext) c;
	CAstEntity fn= (CAstEntity) n.getChild(0).getValue();
	int result= context.currentScope().allocateTempValue();
	int ex= context.currentScope().allocateTempValue();
	doMaterializeClosure(context, result, ex, fn);
	return result;
    }

    private void doMaterializeClosure(WalkContext context, int result, int ex, CAstEntity fn) {
	TypeReference closureRef= closureTypeReference(fn);

	context.cfg().addInstruction(SSAInstructionFactory.NewInstruction(result,
		NewSiteReference.make(context.cfg().getCurrentInstruction(), closureRef)));
    }

    private void declareClosure(CAstEntity fn, WalkContext context) {
	TypeReference asyncRef= closureTypeReference(fn);

	((X10SourceLoaderImpl) translator.loader()).defineClosure(fn, asyncRef, fn.getPosition());
    }

    private TypeReference asyncTypeReference(CAstEntity fn) {
	return TypeReference.findOrCreate(translator.loader().getReference(), "LA" + fn.getName());
    }

    public MethodReference asyncEntityToMethodReference(CAstEntity asyncEntity) {
	CAstType.Method bodyType= (CAstType.Method) asyncEntity.getType();
	CAstType retType= bodyType.getReturnType();
//	CAstType owningType= bodyType.getDeclaringType();
//	JavaSourceLoaderImpl fLoader = translator.loader();

	Atom asyncName= Atom.findOrCreateUnicodeAtom(asyncEntity.getName());
	Descriptor asyncDesc= Descriptor.findOrCreate(null, TypeName.string2TypeName(retType.getName()));
	// RMF 1/12/07 - Type ref must agree with what's used when the async type is defined!
	// The following commented-out version didn't do that...
//	TypeReference owningTypeRef= TypeReference.findOrCreate(fLoader.getReference(), TypeName.string2TypeName(owningType.getName()));
	TypeReference owningTypeRef= asyncTypeReference(asyncEntity);

	return MethodReference.findOrCreate(owningTypeRef, asyncName, asyncDesc);
    }

    protected boolean visitAsyncBodyEntity(CAstEntity n, Context context, Context codeContext, CAstVisitor visitor) {
	translator.initFunctionEntity(n, (WalkContext)context, (WalkContext)codeContext);
	((X10SourceLoaderImpl) translator.loader()).defineAsync(n, asyncTypeReference(n), n.getPosition());
	return false;
    }
    protected void leaveAsyncBodyEntity(CAstEntity n, Context context, Context codeContext, CAstVisitor visitor) {
	translator.closeFunctionEntity(n, (WalkContext)context, (WalkContext)codeContext);
    }

    protected boolean visitAsyncInvoke(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveAsyncInvoke(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	CAstEntity bodyEntity = (CAstEntity) n.getChild(1).getChild(0).getValue();
	CAstNode placeExpr = n.getChild(0);
	// Figure out whether this is a future or an async
	int exceptValue = context.currentScope().allocateTempValue();
	AsyncCallSiteReference acsr = new AsyncCallSiteReference(asyncEntityToMethodReference(bodyEntity), context.cfg().getCurrentInstruction());
        int rcvrValue = translator.getValue(n.getChild(1));
        int placeValue = translator.getValue(placeExpr);

	if (((CAstType.Function) bodyEntity.getType()).getReturnType() == JavaPrimitiveTypeMap.VoidType)
	    context.cfg().addInstruction(new AsyncInvokeInstruction(new int[] { rcvrValue }, exceptValue, acsr, placeValue));
	else {
	    int retValue = context.currentScope().allocateTempValue();

	    context.cfg().addInstruction(new AsyncInvokeInstruction(retValue, new int[] { rcvrValue }, exceptValue, acsr, placeValue));
	    translator.setValue(n, retValue);
	}
    }

    private TypeReference closureTypeReference(CAstEntity fn) {
	return TypeReference.findOrCreate(translator.loader().getReference(), "Lclosure" + fn.getPosition());
    }

    protected boolean visitClosureBodyEntity(CAstEntity n, Context context, Context codeContext, CAstVisitor visitor) {
	translator.initFunctionEntity(n, (WalkContext)context, (WalkContext)codeContext);
	((X10SourceLoaderImpl) translator.loader()).defineClosure(n, closureTypeReference(n), n.getPosition());
	return false;
    }
    protected void leaveClosureBodyEntity(CAstEntity n, Context context, Context codeContext, CAstVisitor visitor) {
	translator.closeFunctionEntity(n, (WalkContext)context, (WalkContext)codeContext);
    }

    protected boolean visitAtomicEnter(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveAtomicEnter(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	context.cfg().addInstruction(new SSAAtomicInstruction(true));
    }
    protected boolean visitAtomicExit(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveAtomicExit(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	context.cfg().addInstruction(new SSAAtomicInstruction(false));
    }
    protected boolean visitFinishEnter(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveFinishEnter(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	context.cfg().addInstruction(new SSAFinishInstruction(true));
    }
    protected boolean visitFinishExit(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveFinishExit(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	context.cfg().addInstruction(new SSAFinishInstruction(false));
    }
    protected boolean visitForce(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveForce(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	int targetValue = translator.getValue(n.getChild(0));
	int retValue = context.currentScope().allocateTempValue();
	context.cfg().addInstruction(new SSAForceInstruction(retValue, targetValue, (TypeReference) n.getChild(1).getValue()));
	translator.setValue(n, retValue);
    }
    protected boolean visitRegionIterInit(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveRegionIterInit(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	int targetValue = translator.getValue(n.getChild(0));
	int retValue = context.currentScope().allocateTempValue();
	context.cfg().addInstruction(new SSARegionIterInitInstruction(retValue, targetValue));
	translator.setValue(n, retValue);
    }
    protected boolean visitRegionIterHasNext(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveRegionIterHasNext(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	int targetValue = translator.getValue(n.getChild(0));
	int retValue = context.currentScope().allocateTempValue();
	context.cfg().addInstruction(new SSARegionIterHasNextInstruction(retValue, targetValue));
	translator.setValue(n, retValue);
    }
    protected boolean visitRegionIterNext(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveRegionIterNext(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	int targetValue = translator.getValue(n.getChild(0));
	int retValue = context.currentScope().allocateTempValue();
	context.cfg().addInstruction(new SSARegionIterNextInstruction(retValue, targetValue));
	translator.setValue(n, retValue);
    }
    protected boolean visitHere(CAstNode n, Context c, CAstVisitor visitor) { /* empty */ return false; }
    protected void leaveHere(CAstNode n, Context c, CAstVisitor visitor) {
	WalkContext context = (WalkContext)c;
	int retValue = context.currentScope().allocateTempValue();
	context.cfg().addInstruction(new SSAHereInstruction(retValue));
	translator.setValue(n, retValue);
    }

    private void translate(final CAstEntity N, final String nm) {
	if (translator.DEBUG_TOP)
	    Trace.println("translating " + nm);
//	PrintWriter printWriter= new PrintWriter(System.out);
//	X10CAstPrinter.printTo(N, printWriter);
//	printWriter.flush();
	visitEntities(N, new DefaultContext(translator, N, nm), this);
    }

    /* UGH! */
    public void translate() {
	CAstEntity fSourceEntity = translator.sourceFileEntity();
	translate(fSourceEntity, fSourceEntity.getName());
    }
    
    public JavaCAst2IRTranslator getCAst2IRTranslator() {
    	return translator;
    }

    public void doArrayRead(WalkContext context, int result, int arrayValue, CAstNode arrayRefNode, int[] dimValues) {
	TypeReference arrayTypeRef= (TypeReference) arrayRefNode.getChild(1).getValue();
	// TODO figure out whether the index is a point or an array of ints and act accordingly
//	context.cfg().addInstruction(SSAInstructionFactory.ArrayLoadInstruction(result, arrayValue, dimValues[0], arrayTypeRef));
	context.cfg().addInstruction(
		new X10ArrayLoadByIndexInstruction(result, arrayValue, dimValues, arrayTypeRef));
    }

    public void doArrayWrite(WalkContext context, int arrayValue, CAstNode arrayRefNode, int[] dimValues, int rval) {
	TypeReference arrayTypeRef =
	    arrayRefNode.getKind() == CAstNode.ARRAY_LITERAL ?
		    ((TypeReference) arrayRefNode.getChild(0).getChild(0).getValue()).getArrayElementType() :
		    (TypeReference) arrayRefNode.getChild(1).getValue();

//	context.cfg().addInstruction(
//		SSAInstructionFactory.ArrayStoreInstruction(
//			arrayValue,
//			dimValues[0], 
//			rval, 
//			arrayTypeRef));
	context.cfg().addInstruction(
		new X10ArrayStoreByIndexInstruction(arrayValue, dimValues, rval, arrayTypeRef));
    }
}
