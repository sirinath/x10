// Licensed Materials - Property of IBM
// (C) Copyright IBM Corporation 2004-2008. All Rights Reserved.
// Note to U.S. Government Users Restricted Rights:  Use, duplication or
// disclosure restricted by GSA ADP  Schedule Contract with IBM Corp.
//
// ---------------------------------------------------------------------------

/*
 * Created on Sep 28, 2004
 */
package polyglot.ext.x10;

import x10.config.ConfigurationError;
import x10.config.OptionError;

/**
 * This class provides the configuration for the X10 compiler.
 * The configuration is a set of values that can be used to
 * configure the compiler, for example in order to tune performance
 * of the generated code.
 *
 * @see x10.config.Configuration
 *
 * @author Christian Grothoff
 * @author Igor Peshansky
 * 
 * @author barikr: added LOOP_OPTIMIZATIONS 26th Aug 2006
 */
public final class Configuration extends x10.config.Configuration {

	/**
	 * The error received when attempting to load the configuration from
	 * the specified resource, or null if successful.
	 */
	public static final ConfigurationError LOAD_ERROR;

	public static String COMPILER_FRAGMENT_DATA_DIRECTORY = "data/";
	private static final String COMPILER_FRAGMENT_DATA_DIRECTORY_desc = "Advanced functionality: Directory for compiler templates";


	public static boolean BAD_PLACE_RUNTIME_CHECK = true;
	private static final String BAD_PLACE_RUNTIME_CHECK_desc = "Generate runtime place checks";
    
	public static boolean LOOP_OPTIMIZATIONS = true;
	private static final String LOOP_OPTIMIZATIONS_desc = "Generate specialized Java version of for-loop X10 code";

	public static boolean ARRAY_OPTIMIZATIONS = true;
	private static final String ARRAY_OPTIMIZATIONS_desc = "Generate allocation of specialized Java array classes";

	public static boolean INLINE_OPTIMIZATIONS = false;
	private static final String INLINE_OPTIMIZATIONS_desc = "Perform inlining optimizatios";

	public static String PLUGINS = "";
	private static final String PLUGINS_desc = "Comma-separated list of compiler plugins to run.";
	
	public static String PLUGIN_COMPILER = "";
	private static final String PLUGIN_COMPILER_desc = "Javac-like compiler to use to compile plugins";

	public static String XML_PROCESSOR = "";
	private static final String XML_PROCESSOR_desc = "External program to transform ASTs externalized as XML";

	public static boolean EXTERNALIZE_ASTS = false;
	private static final String EXTERNALIZE_ASTS_desc = "Externalize ASTs to XML";

	/**
	 * Parses one argument from the command line.  This allows the user
	 * to specify options also on the command line (in addition to the
	 * configuration file and the defaults).
	 *
	 * @param arg the current argument, e.g., -STATISTICS_DISABLE=all
	 * @throws OptionError if the argument is not recognized
	 * @throws ConfigurationError if there was a problem processing the argument
	 */
	public static void parseArgument(String arg) throws OptionError, ConfigurationError {
		parseArgument(Configuration.class, arg);
	}

	/**
	 * Return an array of (option,description) pairs.
	 */
	public static String[][] options() {
		return options(Configuration.class);
	}

	static {
		String cfg = getConfigurationResource();
		ConfigurationError loadError = null;
		try {
			readConfiguration(Configuration.class, cfg);
		} catch (ConfigurationError err) {
			System.err.println("Failed to read configuration file " + cfg + ": " + err);
			System.err.println("Using defaults");
			loadError = err;
		}
		LOAD_ERROR = loadError;
	}
}

