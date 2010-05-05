

/**
 * X10 port of montecarlo benchmark from Section 2 of Java Grande Forum Benchmark Suite (Version 2.0)
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 */
 
 class ToResult  {
	private var header: String;
	private var expectedReturnRate: double = Double.NaN;
	private var volatility: double = Double.NaN;
	private var volatility2: double = Double.NaN;
	private var finalStockPrice: double = Double.NaN;
	private var pathValue: Array[double];

	/**
	 * Constructor, for the results from a computation.
	 *
	 * @param header Simple header string.
	 * @param pathValue Data computed by the Monte Carlo generator.
	 */
	public def this(var header: String, var expectedReturnRate: double, var volatility: double, var volatility2: double, var finalStockPrice: double, var pathValue: Array[double]): ToResult = {
		this.header = header;
		this.expectedReturnRate = expectedReturnRate;
		this.volatility = volatility;
		this.volatility2 = volatility2;
		this.finalStockPrice = finalStockPrice;
		this.pathValue = pathValue;
	}

	/**
	 * Gives a simple string representation of this object.
	 *
	 * @return String representation of this object.
	 */
	public def myToString(): String = {
		return (header);
	}

	//------------------------------------------------------------------------
	// Accessor methods for class ToResult.
	// Generated by 'makeJavaAccessor.pl' script.  HWY.  20th January 1999.
	//------------------------------------------------------------------------

	/**
	 * Accessor method for private instance variable <code>header</code>.
	 * @return Value of instance variable <code>header</code>.
	 */
	public def get_header(): String = {
		return (this.header);
	}

	/**
	 * Set method for private instance variable <code>header</code>.
	 * @param header the value to set for the instance variable <code>header</code>.
	 */
	public def set_header(var header: String): void = {
		this.header = header;
	}

	/**
	 * Accessor method for private instance variable <code>expectedReturnRate</code>.
	 * @return Value of instance variable <code>expectedReturnRate</code>.
	 */
	public def get_expectedReturnRate(): double = {
		return (this.expectedReturnRate);
	}

	/**
	 * Set method for private instance variable <code>expectedReturnRate</code>.
	 * @param expectedReturnRate the value to set for the instance variable
	 * <code>expectedReturnRate</code>.
	 */
	public def set_expectedReturnRate(var expectedReturnRate: double): void = {
		this.expectedReturnRate = expectedReturnRate;
	}

	/**
	 * Accessor method for private instance variable <code>volatility</code>.
	 * @return Value of instance variable <code>volatility</code>.
	 */
	public def get_volatility(): double = {
		return (this.volatility);
	}

	/**
	 * Set method for private instance variable <code>volatility</code>.
	 * @param volatility the value to set for the instance variable <code>volatility</code>.
	 */
	public def set_volatility(var volatility: double): void = {
		this.volatility = volatility;
	}

	/**
	 * Accessor method for private instance variable <code>volatility2</code>.
	 * @return Value of instance variable <code>volatility2</code>.
	 */
	public def get_volatility2(): double = {
		return (this.volatility2);
	}

	/**
	 * Set method for private instance variable <code>volatility2</code>.
	 * @param volatility2 the value to set for the instance variable <code>volatility2</code>.
	 */
	public def set_volatility2(var volatility2: double): void = {
		this.volatility2 = volatility2;
	}

	/**
	 * Accessor method for private instance variable <code>finalStockPrice</code>.
	 * @return Value of instance variable <code>finalStockPrice</code>.
	 */
	public def get_finalStockPrice(): double = {
		return (this.finalStockPrice);
	}

	/**
	 * Set method for private instance variable <code>finalStockPrice</code>.
	 * @param finalStockPrice the value to set for the instance variable
	 * <code>finalStockPrice</code>.
	 */
	public def set_finalStockPrice(var finalStockPrice: double): void = {
		this.finalStockPrice = finalStockPrice;
	}

	/**
	 * Accessor method for private instance variable <code>pathValue</code>.
	 * @return Value of instance variable <code>pathValue</code>.
	 */
	public def get_pathValue(): Array[double] = {
		return (this.pathValue);
	}

	/**
	 * Set method for private instance variable <code>pathValue</code>.
	 * @param pathValue the value to set for the instance variable <code>pathValue</code>.
	 */
	public def set_pathValue(var pathValue: Array[double]): void = {
		this.pathValue = pathValue;
	}

	//------------------------------------------------------------------------
}


class ToTask {
	private var header: String;
	private var randomSeed: long;

	public def this(var header: String, var randomSeed: long): ToTask = {
		this.header         = header;
		this.randomSeed     = randomSeed;
	}

	//------------------------------------------------------------------------
	// Accessor methods for class ToTask.
	// Generated by 'makeJavaAccessor.pl' script.  HWY.  20th January 1999.
	//------------------------------------------------------------------------

	/**
	 * Accessor method for private instance variable <code>header</code>.
	 * @return Value of instance variable <code>header</code>.
	 */
	public def get_header(): String = {
		return (this.header);
	}

	/**
	 * Set method for private instance variable <code>header</code>.
	 * @param header the value to set for the instance variable <code>header</code>.
	 */
	public def set_header(var header: String): void = {
		this.header = header;
	}

	/**
	 * Accessor method for private instance variable <code>randomSeed</code>.
	 * @return Value of instance variable <code>randomSeed</code>.
	 */
	public def get_randomSeed(): long = {
		return (this.randomSeed);
	}

	/**
	 * Set method for private instance variable <code>randomSeed</code>.
	 * @param randomSeed the value to set for the instance variable <code>randomSeed</code>.
	 */
	public def set_randomSeed(var randomSeed: long): void = {
		this.randomSeed = randomSeed;
	}

	//------------------------------------------------------------------------
}


class Universal {

	//------------------------------------------------------------------------
	// Class variables.
	//------------------------------------------------------------------------

	/**
	 * Class variable, for whether to print debug messages.  This one is
	 * unique to this class, and can hence be set in the one place.
	 */
	private var universal_debug: boolean = true;

	//------------------------------------------------------------------------
	// Instance variables.
	//------------------------------------------------------------------------

	/**
	 * Variable, for whether to print debug messages.  This one can
	 * be set by subsequent child classes.
	 */
	private var debug: boolean;

	/**
	 * The prompt to write before any debug messages.
	 */
	private var prompt: String;

	//------------------------------------------------------------------------
	// Constructors.
	//------------------------------------------------------------------------

	/**
	 * Default constructor.
	 */
	public def this(): Universal = {
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
	public def get_DEBUG(): boolean = {
		return (this.debug);
	}

	/**
	 * set method for private instance variable <code>DEBUG</code>.
	 * @param DEBUG the value to set for the instance variable <code>DEBUG</code>.
	 */
	public def set_DEBUG(var debug: boolean): void = {
		this.debug = debug;
	}

	/**
	 * Accessor method for private instance variable <code>UNIVERSAL_DEBUG</code>.
	 * @return Value of instance variable <code>UNIVERSAL_DEBUG</code>.
	 */
	public def get_UNIVERSAL_DEBUG(): boolean = {
		return (this.universal_debug);
	}

	/**
	 * Set method for private instance variable <code>DEBUG</code>.
	 * @param UNIVERSAL_DEBUG the value to set for the instance
	 *        variable <code>UNIVERSAL_DEBUG</code>.
	 */
	public def set_UNIVERSAL_DEBUG(var UNIVERSAL_DEBUG: boolean): void = {
		this.universal_debug = UNIVERSAL_DEBUG;
	}

	/**
	 * Accessor method for private instance variable <code>prompt</code>.
	 * @return Value of instance variable <code>prompt</code>.
	 */
	public def get_prompt(): String = {
		return (this.prompt);
	}

	/**
	 * Set method for private instance variable <code>prompt</code>.
	 * @param prompt the value to set for the instance variable <code>prompt</code>.
	 */
	public def set_prompt(var prompt: String): void = {
		this.prompt = prompt;
	}

	//------------------------------------------------------------------------

	/**
	 * Used to print debug messages.
	 * @param s The debug message to print out, to PrintStream "out".
	 */
	public def dbgPrintln(var s: String): void = {
		if (debug || universal_debug) {
			Console.OUT.println("DBG "+prompt+s);
		}
	}

	/**
	 * Used to print debug messages.
	 * @param s The debug message to print out, to PrintStream "out".
	 */
	public def dbgPrint(var s: String): void = {
		if (debug || universal_debug) {
			Console.OUT.print("DBG "+prompt+s);
		}
	}

	/**
	 * Used to print error messages.
	 * @param s The error message to print out, to PrintStream "err".
	 */
	public def errPrintln(var s: String): void = {
		Console.OUT.println(prompt+s);
	}

	/**
	 * Used to print error messages.
	 * @param s The error message to print out, to PrintStream "err".
	 */
	public def errPrint(var s: String): void = {
		Console.OUT.print(prompt+s);
	}
}

class Utilities {
	public const debug: Boolean = false;
	private const className: String = "Utilities";

	/**
	 * Static method which behaves like the Unix `which' command.  OS
	 * specific dependencies are handled by the Java.lang.System
	 * properties.
	 *
	 * @param executable The executable to search for.
	 * @param pathEnv    The list of paths in which to search, in the style of the
	 *                   OS's PATH environment variable.
	 * @return           The full pathname of where the executable lives,
	 *                   or failing that the error message "<executable> not found.".
	 */
	/*public static def which(var executable: String, var pathEnv: String): String = {
		var executablePath: String;
		var paths: Rail[String];

		paths = splitString(Console.getProperty("path.separator"), pathEnv);
		for (var i: int = 0; i<paths.length; i++) {
			if (paths(i).length() > 0) {
				var pathFile: java.io.File = new java.io.File(paths(i));
				if (pathFile.isDirectory()) {
					var filesInDirectory: Rail[String];
					filesInDirectory = pathFile.list();
					for (var j: int = 0; j<filesInDirectory.length; j++) {
						if (debug.val) {
							Console.OUT.println("DBG: Matching "+filesInDirectory(j));
						}
						if (filesInDirectory(j).equals(executable)) {
							executablePath = paths(i)+System.getProperty("file.separator")+executable;
							return executablePath;
						}
					}
				} else {
					if (debug.val) {
						Console.OUT.println("DBG: path "+paths(i)+" is not a directory!");
					}
				}
			}
		} 
		executablePath = executable+" not found.";
		return executablePath;
	}*/

	/**
	 * Static method which behaves like the Perl join() function.
	 *
	 * @param joinChar    The character on which to join.
	 * @param stringArray The array of strings to join.
	 * @return            A string of the joined string array.
	 */
	public static def joinString(var joinChar: String, var stringArray: Rail[String]!): String = {
		return joinString(joinChar, stringArray, 0);
	}

	/**
	 * Static method which behaves like the Perl join() function.
	 *
	 * @param joinChar    The character on which to join.
	 * @param stringArray The array of strings to join.
	 * @param index       The array index on which to start joining.
	 * @return            A string of the joined string array.
	 */
	public static def joinString(var joinChar: String, var stringArray: Rail[String]!, var index: int): String = {
		var methodName: String = "join";
		var tmpString: String;

		var nStrings: int = stringArray.length;
		if (nStrings <= index) {
			tmpString = new String();
		} else {
			tmpString = new String(stringArray(index));
			for (var i: int = (index+1); i < nStrings; i++) {
				tmpString = tmpString + joinChar + stringArray(i);
			}
		}
		return tmpString.toString();
	}

	/**
	 * Static method which behaves like the Perl split() function.
	 *
	 * @param splitChar The character on which to split.
	 * @param arg       The string to be split.
	 * @return          A string array of the split string.
	 */
	/*public static def splitString(var splitChar: String, var arg: String): Rail[String] = {
		var methodName: String = "split";

		var myArgs: Rail[String];
		var nArgs: int = 0;
		var foundIndex: int = 0;
		var fromIndex: int = 0;

		while ((foundIndex = arg.indexOf(splitChar, fromIndex)) > -1) {
			nArgs++;
			fromIndex = foundIndex+1;
		}
		if (debug.val) {
			Console.OUT.println("DBG "+className+"."+methodName+": "+nArgs);
		}
		myArgs = new Array[String](nArgs+1);
		nArgs = 0;
		fromIndex = 0;
		while ((foundIndex = arg.indexOf(splitChar, fromIndex)) > -1) {
			if (debug.val) {
				Console.OUT.println("DBG "+className+"."+methodName+": "+fromIndex+" "+foundIndex);
			}
		// FIXME	myArgs(nArgs) = arg.substring(fromIndex, foundIndex);
			nArgs++;
			fromIndex = foundIndex+1;
		}
		myArgs(nArgs) = arg.substring(fromIndex);
		return myArgs;
	} */
}
