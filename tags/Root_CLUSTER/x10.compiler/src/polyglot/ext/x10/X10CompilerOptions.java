/*
 */
package polyglot.ext.x10;

import java.io.PrintStream;
import java.util.Set;
import polyglot.frontend.ExtensionInfo;
import polyglot.main.Main;
import polyglot.main.UsageError;

public class X10CompilerOptions extends polyglot.main.Options {

	public X10CompilerOptions(ExtensionInfo extension) {
		super(extension);
	}

	protected int parseCommand(String args[], int index, Set source) 
		throws UsageError, Main.TerminationException
	{
		int i = super.parseCommand(args, index, source);
		if (i != index) return i;

		if (Configuration.parseArgument(args[index]))
			return ++index;
		return index;
	}

	/**
	 * Print usage information
	 */
	public void usage(PrintStream out) {
		super.usage(out);
		String[][] options = Configuration.options();
		for (int i = 0; i < options.length; i++) {
			usageForFlag(out, options[i][0], options[i][1]);
		}
	}
}
