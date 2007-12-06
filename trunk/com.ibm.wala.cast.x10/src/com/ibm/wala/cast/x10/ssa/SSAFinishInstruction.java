/*
 * Created on Oct 25, 2005
 */
package com.ibm.wala.cast.x10.ssa;

import java.util.Collection;
import java.util.Collections;
import com.ibm.wala.util.debug.Assertions;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.ssa.ValueDecorator;

public class SSAFinishInstruction extends SSAInstruction {
    private final boolean isEnter;
    public SSAFinishInstruction(boolean isEnter) {
      super();
      this.isEnter = isEnter;
    }
    public SSAInstruction copyForSSA(int[] defs, int[] uses) {
      return
        new SSAFinishInstruction(isEnter);
    }

    public String toString(SymbolTable symbolTable, ValueDecorator d) {
      return isEnter ? "atomic enter" : "atomic exit";
    }
    /**
     * @see com.ibm.domo.ssa.SSAInstruction#visit(IVisitor)
     */
    public void visit(IVisitor v) {
	((AstX10InstructionVisitor) v).visitFinish(this);
    }
    /**
     * @see com.ibm.domo.ssa.SSAInstruction#getNumberOfUses()
     */
    public int getNumberOfUses() {
      return 0;
    }

    /**
     * @see com.ibm.domo.ssa.SSAInstruction#getUse(int)
     */
    public int getUse(int j) {
	Assertions.UNREACHABLE();
	return -1;
    }

    public int hashCode() {
      return (isEnter ? 6173 ^ 4423 : 13);
    }
    /* (non-Javadoc)
     * @see com.ibm.domo.ssa.Instruction#isPEI()
     */
    public boolean isPEI() {
      return false;
    }
    /* (non-Javadoc)
     * @see com.ibm.domo.ssa.Instruction#isFallThrough()
     */
    public boolean isFallThrough() {
      return true;
    }
    /* (non-Javadoc)
     * @see com.ibm.domo.ssa.Instruction#getExceptionTypes()
     */
    public Collection getExceptionTypes() {
	return Collections.EMPTY_SET;
    }

    public boolean isFinishEnter() {
      return isEnter;
    }
}
