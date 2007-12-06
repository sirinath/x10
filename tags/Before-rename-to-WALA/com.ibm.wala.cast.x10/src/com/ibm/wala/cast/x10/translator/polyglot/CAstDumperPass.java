/*
 * Created on Feb 23, 2006
 */
package com.ibm.domo.ast.x10.translator.polyglot;

import java.io.PrintWriter;

import polyglot.frontend.AbstractPass;
import polyglot.frontend.goals.Goal;

import com.ibm.domo.ast.x10.analysis.AnalysisJobExt;
import com.ibm.domo.ast.x10.translator.X10CAstPrinter;
import com.ibm.wala.cast.tree.CAstEntity;
import com.ibm.wala.cast.tree.CAstNode;
import com.ibm.wala.cast.util.CAstPrinter;

public class CAstDumperPass extends AbstractPass {

    public CAstDumperPass(Goal goal) {
	super(goal);
    }

    public boolean run() {
	AnalysisJobExt je= (AnalysisJobExt) goal().job().ext();
	PrintWriter pw= new PrintWriter(System.out);

	X10CAstPrinter.printTo((CAstEntity) je.get(AnalysisJobExt.CAST_JOBEXT_KEY), pw);
	pw.close();
	return true;
    }
}
