package com.ibm.domo.ast.x10.ssa;

import com.ibm.capa.impl.debug.Assertions;
import com.ibm.domo.ssa.SSAInstruction;
import com.ibm.domo.ssa.SymbolTable;
import com.ibm.domo.ssa.ValueDecorator;
import com.ibm.domo.types.TypeReference;

public class SSAForceInstruction extends SSAAbstractUnaryInstruction {

    private final TypeReference valueType;

    public SSAForceInstruction(int retValue, int targetValue, TypeReference valueType) {
	suer(retValue, targetValue);
	this.valueType= valueType;
    }

    public SSAInstruction copyForSSA(int[] defs, int[] uses) {
	return new SSAForceInstruction((defs != null) ? defs[0] : getDef(0), (uses != null) ? uses[0] : getUse(0), valueType);
    }

    public String toString(SymbolTable symbolTable, ValueDecorator d) {
	return getValueString(symbolTable, d, retValue) + " = force(" + getValueString(symbolTable, d, targetValue) + ")";
    }

    public void visit(Visitor v) {
	((AstX10InstructionVisitor) v).visitForce(this);
    }

    public TypeReference[] getExceptionTypes() {
	return null;
    }

    public TypeReference getValueType() {
        return valueType;
    }
}
