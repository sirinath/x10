/*
 * Created on Apr 28, 2006
 */
package com.ibm.domo.ast.x10.translator.polyglot;

import com.ibm.capa.ast.*;
import com.ibm.domo.ast.loader.AstFunctionClass;
import com.ibm.wala.classLoader.IClassLoader;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.cha.*;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;

/**
 * Represents the synthesized type that contains a single synthesized method that houses the async code body
 * @author rfuhrer
 */
public class X10AsyncObject extends AstFunctionClass {
    private final ClassHierarchy cha;

    // TODO RMF 1/12/2007 - Should probably take enough info to compute a proper method Selector
    // for the method that this async pseudo-type contains. The name seems to contain the filename,
    // and the descriptor/signature is for a no-arg method returning either void (if a syntactic
    // async) or, if a syntactic future, the type produced by the future.
    public X10AsyncObject(TypeReference reference, TypeReference superReference, IClassLoader loader, CAstSourcePositionMap.Position fileName, ClassHierarchy cha) {
	super(reference, superReference, loader, fileName);
	this.cha = cha;
    }

    public X10AsyncObject(TypeReference reference, IClassLoader loader, CAstSourcePositionMap.Position fileName, ClassHierarchy cha) {
	super(reference, loader, fileName);
	this.cha = cha;
    }

    @Override
    public IMethod getMethod(Selector selector) {
	// TODO RMF 1/12/2007 - Hack: if we had enough info at ctor time, we could
	// properly compare the selector passed in to what we expect...
	return functionBody;
    }

    public void setCodeBody(IMethod method) {
	functionBody= method;
    }

    public String toString() {
      return "Async@" + getSourcePosition();
    }

    public ClassHierarchy getClassHierarchy() {
      return cha;
    }
}
