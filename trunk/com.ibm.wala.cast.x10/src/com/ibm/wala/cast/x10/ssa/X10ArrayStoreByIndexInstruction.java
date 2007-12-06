package com.ibm.wala.cast.x10.ssa;

import java.util.Collection;

import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.ssa.ValueDecorator;
import com.ibm.wala.types.TypeReference;

public class X10ArrayStoreByIndexInstruction extends X10ArrayReferenceByIndexInstruction {
    private final int value;

    public X10ArrayStoreByIndexInstruction(int arrayRef, int[] indices, int value, TypeReference declaredType) {
	super(arrayRef, indices, declaredType);
	this.value = value;
    }

    public int getStoreValue() {
	return value;
    }

    @Override
    public SSAInstruction copyForSSA(int[] defs, int[] uses) {
	int newArray = uses == null ? getArrayRef() : uses[0];
	int[] newIndices = uses == null ? getIndices() : new int[uses.length - 2];
	int newValue = uses == null ? getStoreValue() : uses[uses.length - 1];

	for(int i= 0; i < newIndices.length; i++) {
	    newIndices[i]= uses[i-1];
	}
	return new X10ArrayStoreByIndexInstruction(newArray, newIndices, newValue, declaredType);
    }

    @Override
    public Collection<TypeReference> getExceptionTypes() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String toString(SymbolTable symbolTable, ValueDecorator d) {
	return "x10arrayStoreByIndex " + getValueString(symbolTable, d, getArrayRef()) + "[" + getIndexString(symbolTable, d) + "] = " + getValueString(symbolTable, d, value);
    }

    @Override
    public void visit(IVisitor v) {
	((AstX10InstructionVisitor) v).visitArrayStoreByIndex(this);
    }

    @Override
    public int hashCode() {
        return 5779 + 7411 * value + 2819 * super.hashCode();
    }
}
