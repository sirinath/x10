/*
 * Created on Sep 26, 2005
 */
package com.ibm.domo.ast.x10.translator;

import java.io.PrintWriter;

import com.ibm.capa.ast.CAstEntity;
import com.ibm.domo.ast.java.translator.Java2IRTranslator;
import com.ibm.domo.ast.java.translator.TranslatorToCAst;
import com.ibm.domo.ast.x10.translator.polyglot.X10CAst2IRTranslator;
import com.ibm.domo.ast.x10.translator.polyglot.X10SourceLoaderImpl;

public class X10ToIRTranslator extends Java2IRTranslator {
    public X10ToIRTranslator(TranslatorToCAst sourceTranslator,
			     X10SourceLoaderImpl srcLoader) {
	super(sourceTranslator, srcLoader);
    }

    public void translate(Object srcFile, String N) {
      CAstEntity ce= fSourceTranslator.translate(srcFile, N);
      PrintWriter printWriter= new PrintWriter(System.out);

      X10CAstPrinter.printTo(ce, printWriter);
      printWriter.flush();

      new X10CAst2IRTranslator(ce, (X10SourceLoaderImpl) fLoader).translate();
    }
}
