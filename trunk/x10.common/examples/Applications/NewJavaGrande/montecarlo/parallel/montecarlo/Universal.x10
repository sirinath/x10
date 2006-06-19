package montecarlo;

import x10.lang.Boolean;

/**
 * X10 port of montecarlo benchmark from Section 2 of Java Grande Forum Benchmark Suite (Version 2.0).
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 */
public class Universal {

	//------------------------------------------------------------------------
	// Class variables.
	//------------------------------------------------------------------------

	/**
	 * Class variable, for whether to print debug messages.  This one is
	 * unique to this class, and can hence be set in the one place.
	 */
	private static final Boolean universal_debug = new Boolean(true);

	//------------------------------------------------------------------------
	// Instance variables.
	//------------------------------------------------------------------------

	/**
	 * Variable, for whether to print debug messages.  This one can
	 * be set by subsequent child classes.
	 */
	private boolean debug;

	/**
	 * The prompt to write before any debug messages.
	 */
	private String prompt;

	//------------------------------------------------------------------------
	// Constructors.
	//------------------------------------------------------------------------

	/**
	 * Default constructor.
	 */
	public Universal() {
		super();
		this.debug = true;
		this.prompt = "Universal> ";
	}

	//------------------------------------------------------------------------
	// Methods.
	//------------------------------------------------------------------------
	//------------------------------------------------------------------------
	// Accessor methods for class AppDemo/Universal.
	// Generated by 'makeJavaAccessor.pl' script.  HWY.  20th January 1999.
	//------------------------------------------------------------------------

	/**
	 * Accessor method for private instance variable <code>debug</code>.
	 * @return Value of instance variable <code>debug</code>.
	 */
	public boolean get_DEBUG() {
		return (this.debug);
	}

	/**
	 * set method for private instance variable <code>DEBUG</code>.
	 * @param DEBUG the value to set for the instance variable <code>DEBUG</code>.
	 */
	public void set_DEBUG(boolean debug) {
		this.debug = debug;
	}

	/**
	 * Accessor method for private instance variable <code>UNIVERSAL_DEBUG</code>.
	 * @return Value of instance variable <code>UNIVERSAL_DEBUG</code>.
	 */
	public boolean get_UNIVERSAL_DEBUG() {
		return (this.universal_debug.val);
	}

	/**
	 * Set method for private instance variable <code>DEBUG</code>.
	 * @param UNIVERSAL_DEBUG the value to set for the instance
	 *        variable <code>UNIVERSAL_DEBUG</code>.
	 */
	public void set_UNIVERSAL_DEBUG(boolean UNIVERSAL_DEBUG) {
		this.universal_debug.val = UNIVERSAL_DEBUG;
	}

	/**
	 * Accessor method for private instance variable <code>prompt</code>.
	 * @return Value of instance variable <code>prompt</code>.
	 */
	public String get_prompt() {
		return (this.prompt);
	}

	/**
	 * Set method for private instance variable <code>prompt</code>.
	 * @param prompt the value to set for the instance variable <code>prompt</code>.
	 */
	public void set_prompt(String prompt) {
		this.prompt = prompt;
	}

	//------------------------------------------------------------------------

	/**
	 * Used to print debug messages.
	 * @param s The debug message to print out, to PrintStream "out".
	 */
	public void dbgPrintln(String s) {
		if (debug || universal_debug.val) {
			System.out.println("DBG "+prompt+s);
		}
	}

	/**
	 * Used to print debug messages.
	 * @param s The debug message to print out, to PrintStream "out".
	 */
	public void dbgPrint(String s) {
		if (debug || universal_debug.val) {
			System.out.print("DBG "+prompt+s);
		}
	}

	/**
	 * Used to print error messages.
	 * @param s The error message to print out, to PrintStream "err".
	 */
	public void errPrintln(String s) {
		System.err.println(prompt+s);
	}

	/**
	 * Used to print error messages.
	 * @param s The error message to print out, to PrintStream "err".
	 */
	public void errPrint(String s) {
		System.err.print(prompt+s);
	}
}

