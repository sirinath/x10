package com.ibm.wala.cast.x10.ssa;

import java.util.Collection;

import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.ssa.ValueDecorator;
import com.ibm.wala.types.TypeReference;

public class X10ArrayStoreByPointInstruction extends X10ArrayReferenceByPointInstruction {
    private final int value;

    public X10ArrayStoreByPointInstruction(int arrayRef, int pointIndex, int value, TypeReference declaredType) {
	super(arrayRef, pointIndex, declaredType);
	this.value= value;
    }

    public int getStoreValue() {
	return value;
    }

    @Override
    public SSAInstruction copyForSSA(int[] defs, int[] uses) {
	return new X10ArrayStoreByPointInstruction(
		uses == null ? arrayRef : uses[0],
		uses == null ? pointIndex : uses[1],
		uses == null ? value : uses[2],
		declaredType);
    }

    @Override
    public Collection<TypeReference> getExceptionTypes() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String toString(SymbolTable symbolTable, ValueDecorator d) {
	return "x10arrayStoreByPoint " + getValueString(symbolTable, d, getArrayRef()) + "[" + getValueString(symbolTable, d, getPointIndex()) + "] = " + getValueString(symbolTable, d, value);
    }

    @Override
    public void visit(IVisitor v) {
	((AstX10InstructionVisitor) v).visitArrayStoreByPoint(this);
    }

    @Override
    public int hashCode() {
        return 4273 + 6007 * value + 7369 * super.hashCode();
    }
}
