package com.ibm.wala.cast.x10.ssa;

import java.util.Collection;
import java.util.Collections;
import com.ibm.wala.ssa.SSAAbstractUnaryInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.ssa.ValueDecorator;

public class SSARegionIterNextInstruction extends SSAAbstractUnaryInstruction {

    public SSARegionIterNextInstruction(int nextValue, int regionIter) {
	super(nextValue, regionIter);
    }

    public SSAInstruction copyForSSA(int[] defs, int[] uses) {
	return new SSARegionIterNextInstruction((defs != null ? defs[0] : getDef(0)), (uses != null ? uses[0] : getUse(0)));
    }

    public String toString(SymbolTable symbolTable, ValueDecorator d) {
	return getValueString(symbolTable, d, getDef(0)) + " = regionIterNext(" + getValueString(symbolTable, d, getUse(0)) + ")";
    }

    public void visit(IVisitor v) {
	((AstX10InstructionVisitor) v).visitRegionIterNext(this);
    }

    public Collection getExceptionTypes() {
	return Collections.EMPTY_SET;
    }
}
