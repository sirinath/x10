/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */
package x10.emitter;

import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import polyglot.frontend.ExtensionInfo;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.SubtypeSet;
import polyglot.visit.AscriptionVisitor;
import polyglot.visit.CFGBuilder;
import polyglot.visit.ContextVisitor;
import polyglot.visit.ExceptionChecker;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.Translator;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeCheckPreparer;
import polyglot.visit.TypeChecker;
import x10.ast.Block;
import x10.ast.Catch;
import x10.ast.Expr;
import x10.ast.Ext;
import x10.ast.JL;
import x10.ast.Node;
import x10.ast.NodeFactory;
import x10.ast.Precedence;
import x10.ast.Term;
import x10.types.Context;
import x10.types.SemanticException;
import x10.types.Type;
import x10.types.TypeSystem;
import x10.visit.X10PrettyPrinterVisitor;

public class TryCatchExpander extends Expander {

	private class CatchBlock {
		private final String exClass;
		private final String exInstName;
		private final Expander bodyExpander;
		private final Catch catchBlock;

		private CatchBlock(String exClass, String exInstName,
				Expander bodyExpander) {
			this.exClass = exClass;
			this.exInstName = exInstName;
			this.bodyExpander = bodyExpander;
			this.catchBlock = null;
		}

		public CatchBlock(Catch catchBlock) {
			this.exClass = null;
			this.exInstName = null;
			this.bodyExpander = null;
			this.catchBlock = catchBlock;
		}

		void prettyPrint(Translator tr) {
			if (catchBlock != null) {
				er.prettyPrint(catchBlock, tr);
			} else {
				w.write("catch (");
				w.write(exClass);
				w.write(" ");
				w.write(exInstName);
				w.write(") {");
				bodyExpander.expand(tr);
				w.write("}");
			}
		}

		int conversionRequired() {
		    String catchExClassName = (catchBlock != null) ? catchBlock.catchType().toString() : exClass;
		    int rc = 0;
		    for (String exc : x10RuntimeExceptions)
		        if (catchExClassName.equals(exc)) {
		            rc |= EXC_CONVERSION;
		            break;
		        }
            for (String exc : x10Errors)
                if (catchExClassName.equals(exc)) {
                    rc |= ERROR_CONVERSION;
                    break;
                }
            return rc;
		}

	}

	private final CodeWriter w;
	private final Block block;
	private final Expander child;
	private final List<CatchBlock> catches = new ArrayList<CatchBlock>();
	private final Block finalBlock;

	public TryCatchExpander(CodeWriter w, Emitter er, Block block,
			Block finalBlock) {
		super(er);
		this.w = w;
		this.block = block;
		this.child = null;
		this.finalBlock = finalBlock;
	}

	public TryCatchExpander(CodeWriter w, Emitter er,
			TryCatchExpander expander, Block finalBlock) {
		super(er);
		this.w = w;
		this.block = null;
		this.child = expander;
		this.finalBlock = finalBlock;
	}

	public void addCatchBlock(String exClass, String exInstName,
			Expander expander) {
		catches.add(new CatchBlock(exClass, exInstName, expander));
	}

	public void addCatchBlock(Catch catchBlock) {
		catches.add(new CatchBlock(catchBlock));
	}

	@Override
	public void expand(Translator tr) {
		w.write("try {");
		
		int additionalTryCatchForConversion = checkConversionRequired();
		if (additionalTryCatchForConversion != NO_CONVERSION) {
		    // inner try-catch generation for exception type conversion
            w.write("try {");
        }


		if (block != null) {
			er.prettyPrint(block, tr);
		} else if (child != null) {
			child.expand(tr);
		}

		if (additionalTryCatchForConversion != NO_CONVERSION) {
	        w.write("}");

            w.write("catch (x10.core.Throwable __t__) {");
            w.write("throw __t__;");
            w.write("}");

            if ((additionalTryCatchForConversion & EXC_CONVERSION) != 0) {
                w.write("catch (java.lang.RuntimeException __e__) {");
                w.write("throw x10.core.ThrowableUtilities.getCorrespondingX10Exception(__e__);");
                w.write("}");
            }

            if ((additionalTryCatchForConversion & ERROR_CONVERSION) != 0) {
                w.write("catch (java.lang.Error __e__) {");
                w.write("throw x10.core.ThrowableUtilities.getCorrespondingX10Error(__e__);");
                w.write("}");
            }
		}

		w.write("}");

		for (CatchBlock catchBlock : catches) {
			catchBlock.prettyPrint(tr);
		}

		if (finalBlock != null) {
			w.begin(0);
			w.write("finally {");
			er.prettyPrint(finalBlock, tr);
			w.write("}");
		}
	}

	static final String[] x10RuntimeExceptions = {
//	    "x10.array.UnboundedRegionException",
//	    "x10.io.IORuntimeException",
	    "x10.lang.ArithmeticException",
	    "x10.lang.ArrayIndexOutOfBoundsException",
//	    "x10.lang.BadPlaceException",
	    "x10.lang.ClassCastException",
//	    "x10.lang.ClockUseException",
	    "x10.lang.IllegalArgumentException",
//	    "x10.lang.IllegalOperationException",
        "x10.util.NoSuchElementException",
        "x10.lang.NullPointerException",
        "x10.lang.UnsupportedOperationException",
	    "x10.lang.RuntimeException",
	    "x10.lang.Exception",
	    "x10.lang.Throwable"
	};
    static final String[] x10Errors = {
        "x10.lang.OutOfMemoryError",
        "x10.lang.Error",
        "x10.lang.Throwable"
    };
    static final int NO_CONVERSION = 0;
    static final int EXC_CONVERSION = 0x01;
    static final int ERROR_CONVERSION = 0x10;
    
	private int checkConversionRequired() {
	    int rc = 0;
        for (CatchBlock catchBlock : catches) {
            rc |= catchBlock.conversionRequired();
        }
	    return rc;
	}
}