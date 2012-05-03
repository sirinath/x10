/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
package montecarlo.parallel.montecarlo;

import x10.util.*;

/**
 * X10 port of montecarlo benchmark from Section 2 of Java Grande Forum Benchmark Suite (Version 2.0).
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 *
 * Porting issues identified:
 * 1) Variables/fields starting with an upper case letter have to be renamed if not final (debug, datumfield)
 * 2) Remove null checks on non-nullable fields (fluctuations, pathValue)
 *    NOTE: comparison of array with null leads to a Polyglot crash!
 * 3) No need to extend x10.lang.Object because PathId already does that.
 * 4) Replace unary -1 by binary 0-1
 */
public class MonteCarloPath extends PathId {

	//------------------------------------------------------------------------
	// Class variables.
	//------------------------------------------------------------------------

	/**
	 * Class variable for determining whether to switch on debug output or
	 * not.
	 */
	public static val debug: boolean = true;

	/**
	 * Class variable for defining the debug message prompt.
	 */
	protected static val prompt: String = "MonteCarloPath> ";

	/**
	 * Class variable for determining which field in the stock data should be
	 * used.  This is currently set to point to the 'closing price', as
	 * defined in class RatePath.
	 */
	public static val datumfield: int = RatePath.datumfield;

	//------------------------------------------------------------------------
	// Instance variables.
	//------------------------------------------------------------------------

	/**
	 * Random fluctuations generated as a series of random numbers with
	 * given distribution.
	 */
	private var fluctuations: Array[double];

	/**
	 * The path values from which the random fluctuations are used to update.
	 */
	private var pathValue: Array[double];

	/**
	 * Integer flag for determining how the return was calculated, when
	 * used to calculate the mean drift and volatility parameters.
	 */
	private var returnDefinition: int = 0;

	/**
	 * Value for the mean drift, for use in the generation of the random path.
	 */
	private var expectedReturnRate: double = Double.NaN;

	/**
	 * Value for the volatility, for use in the generation of the random path.
	 */
	private var volatility: double = Double.NaN;

	/**
	 * Number of time steps for which the simulation should act over.
	 */
	private var nTimeSteps: int = 0;

	/**
	 * The starting value for of the security.
	 */
	private var pathStartValue: double = Double.NaN;

	//------------------------------------------------------------------------
	// Constructors.
	//------------------------------------------------------------------------

	/**
	 * Default constructor.  Needed by the HPT library to start create
	 * new instances of this class.  The instance variables for this should
	 * then be initialised with the <code>setInitAllTasks()</code> method.
	 */
	public def this(): MonteCarloPath = {
		super();
		set_prompt(prompt);
		set_DEBUG(debug);
	}

	/**
	 * Constructor, using the <code>ReturnPath</code> object to initialise
	 * the necessary instance variables.
	 *
	 * @param returnPath Object used to define the instance variables in
	 *                   this object.
	 * @param nTimeSteps The number of time steps for which to generate the
	 *                   random path.
	 * @exception DemoException Thrown if there is a problem initialising the
	 *                          object's instance variables.
	 */
	public def this(var returnPath: ReturnPath, var nTimeSteps: int): MonteCarloPath = {
		/**
		 * These instance variables are members of PathId class.
		 */
		copyInstanceVariables(returnPath);
		this.nTimeSteps = nTimeSteps;
		this.pathValue = new Array[double](nTimeSteps);
		this.fluctuations = new Array[double](nTimeSteps);

		/**
		 * Whether to debug, and how.
		 */
		set_prompt(prompt);
		set_DEBUG(debug);
	}

	/**
	 * Constructor, where the <code>PathId</code> objects is used to ease
	 * the number of instance variables to pass in.
	 *
	 * @param pathId Object used to define the identity of this Path.
	 * @param returnDefinition How the statistic variables were defined,
	 *                         according to the definitions in
	 *                         <code>ReturnPath</code>'s two class variables
	 *                         <code>COMPOUNDED</code> and
	 *                         <code>NONCOMPOUNDED</code>.
	 * @param expectedReturnRate The measured expected return rate for which to generate.
	 * @param volatility The measured volatility for which to generate.
	 * @param nTimeSteps The number of time steps for which to generate.
	 * @exception DemoException Thrown if there is a problem initialising the
	 *                          object's instance variables.
	 */
	public def this(var pathId: PathId, var returnDefinition: int, var expectedReturnRate: double, var volatility: double, var nTimeSteps: int): MonteCarloPath = {
		/**
		 * These instance variables are members of PathId class.
		 * Invoking with this particular signature should point to the
		 * definition in the PathId class.
		 */
		copyInstanceVariables(pathId);
		this.returnDefinition   = returnDefinition;
		this.expectedReturnRate = expectedReturnRate;
		this.volatility         = volatility;
		this.nTimeSteps         = nTimeSteps;
		this.pathValue          = new Array[double](nTimeSteps);
		this.fluctuations       = new Array[double](nTimeSteps);

		/**
		 * Whether to debug, and how.
		 */
		set_prompt(prompt);
		set_DEBUG(debug);
	}

	/**
	 * Constructor, for when the user wishes to define each of the instance
	 * variables individually.
	 *
	 * @param name The name of the security which this Monte Carlo path
	 *             should represent.
	 * @param startDate The date when the path starts, in 'YYYYMMDD' format.
	 * @param endDate The date when the path ends, in 'YYYYMMDD' format.
	 * @param dTime The interval in the data between successive data points
	 *              in the generated path.
	 * @param returnDefinition How the statistic variables were defined,
	 *                         according to the definitions in
	 *                         <code>ReturnPath</code>'s two class variables
	 *                         <code>COMPOUNDED</code> and
	 *                         <code>NONCOMPOUNDED</code>.
	 * @param expectedReturnRate The measured mean drift for which to generate.
	 * @param volatility The measured volatility for which to generate.
	 * @param nTimeSteps The number of time steps for which to generate.
	 */
	public def this(var name: String, var startDate: int, var endDate: int, var dTime: double, var returnDefinition: int, var expectedReturnRate: double, var volatility: double, var nTimeSteps: int): MonteCarloPath = {
		/**
		 * These instance variables are members of PathId class.
		 */
		set_name(name);
		set_startDate(startDate);
		set_endDate(endDate);
		set_dTime(dTime);
		this.returnDefinition   = returnDefinition;
		this.expectedReturnRate = expectedReturnRate;
		this.volatility         = volatility;
		this.nTimeSteps         = nTimeSteps;
		this.pathValue          = new Array[double](nTimeSteps);
		this.fluctuations       = new Array[double](nTimeSteps);

		/**
		 * Whether to debug, and how.
		 */
		set_prompt(prompt);
		set_DEBUG(debug);
	}

	//------------------------------------------------------------------------
	// Methods.
	//------------------------------------------------------------------------
	//------------------------------------------------------------------------
	// Accessor methods for class MonteCarloPath.
	// Generated by 'makeJavaAccessor.pl' script.  HWY.  20th January 1999.
	//------------------------------------------------------------------------

	/**
	 * Accessor method for private instance variable <code>fluctuations</code>.
	 * @return Value of instance variable <code>fluctuations</code>.
	 * @exception DemoException thrown if instance variable <code>fluctuations</code>
	 * is undefined.
	 */
	public def get_fluctuations(): Array[double] = {
		// if ( this.fluctuations == null )
		//   throw new DemoException("Variable fluctuations is undefined!");
		return (this.fluctuations);
	}

	/**
	 * Set method for private instance variable <code>fluctuations</code>.
	 * @param fluctuations the value to set for the instance variable
	 * <code>fluctuations</code>.
	 */
	public def set_fluctuations(var fluctuations: Array[double]): void = {
		this.fluctuations = fluctuations;
	}

	/**
	 * Accessor method for private instance variable <code>pathValue</code>.
	 * @return Value of instance variable <code>pathValue</code>.
	 * @exception DemoException thrown if instance variable <code>pathValue</code>
	 * is undefined.
	 */
	public def get_pathValue(): Array[double] = {
		// if ( this.pathValue == null )
		//  throw new DemoException("Variable pathValue is undefined!");
		return (this.pathValue);
	}

	/**
	 * Set method for private instance variable <code>pathValue</code>.
	 * @param pathValue the value to set for the instance variable <code>pathValue</code>.
	 */
	public def set_pathValue(var pathValue: Array[double]): void = {
		this.pathValue = pathValue;
	}

	/**
	 * Accessor method for private instance variable <code>returnDefinition</code>.
	 * @return Value of instance variable <code>returnDefinition</code>.
	 * @exception DemoException thrown if instance variable <code>returnDefinition</code>
	 * is undefined.
	 */
	public def get_returnDefinition(): int = {
		if ( this.returnDefinition == 0 )
			throw new DemoException("Variable returnDefinition is undefined!");
		return (this.returnDefinition);
	}

	/**
	 * Set method for private instance variable <code>returnDefinition</code>.
	 * @param returnDefinition the value to set for the instance variable
	 * <code>returnDefinition</code>.
	 */
	public def set_returnDefinition(var returnDefinition: int): void = {
		this.returnDefinition = returnDefinition;
	}

	/**
	 * Accessor method for private instance variable <code>expectedReturnRate</code>.
	 * @return Value of instance variable <code>expectedReturnRate</code>.
	 * @exception DemoException thrown if instance variable <code>expectedReturnRate</code>
	 * is undefined.
	 */
	public def get_expectedReturnRate(): double = {
		if ( this.expectedReturnRate == Double.NaN )
			throw new DemoException("Variable expectedReturnRate is undefined!");
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
	 * @exception DemoException thrown if instance variable <code>volatility</code>
	 * is undefined.
	 */
	public def get_volatility(): double = {
		if ( this.volatility == Double.NaN )
			throw new DemoException("Variable volatility is undefined!");
		return (this.volatility);
	}

	/**
	 * Set method for private instance variable <code>volatility</code>.
	 * @param volatility the value to set for the instance variable
	 * <code>volatility</code>.
	 */
	public def set_volatility(var volatility: double): void = {
		this.volatility = volatility;
	}

	/**
	 * Accessor method for private instance variable <code>nTimeSteps</code>.
	 * @return Value of instance variable <code>nTimeSteps</code>.
	 * @exception DemoException thrown if instance variable <code>nTimeSteps</code>
	 * is undefined.
	 */
	public def get_nTimeSteps(): int = {
		if ( this.nTimeSteps == 0 )
			throw new DemoException("Variable nTimeSteps is undefined!");
		return (this.nTimeSteps);
	}

	/**
	 * Set method for private instance variable <code>nTimeSteps</code>.
	 * @param nTimeSteps the value to set for the instance variable
	 * <code>nTimeSteps</code>.
	 */
	public def set_nTimeSteps(var nTimeSteps: int): void = {
		this.nTimeSteps = nTimeSteps;
	}

	/**
	 * Accessor method for private instance variable <code>pathStartValue</code>.
	 * @return Value of instance variable <code>pathStartValue</code>.
	 * @exception DemoException thrown if instance variable <code>pathStartValue</code>
	 * is undefined.
	 */
	public def get_pathStartValue(): double = {
		if ( this.pathStartValue == Double.NaN )
			throw new DemoException("Variable pathStartValue is undefined!");
		return (this.pathStartValue);
	}

	/**
	 * Set method for private instance variable <code>pathStartValue</code>.
	 * @param pathStartValue the value to set for the instance variable
	 * <code>pathStartValue</code>.
	 */
	public def set_pathStartValue(var pathStartValue: double): void = {
		this.pathStartValue = pathStartValue;
	}

	//------------------------------------------------------------------------

	/**
	 * Method for copying the suitable instance variable from a
	 * <code>ReturnPath</code> object.
	 *
	 * @param obj Object used to define the instance variables which
	 *            should be carried over to this object.
	 * @exception DemoException thrown if there is a problem accessing the
	 *                          instance variables from the target objetct.
	 */
	private def copyInstanceVariables(var obj: ReturnPath): void = {
		//
		// Instance variables defined in the PathId object.
		set_name(obj.get_name());
		set_startDate(obj.get_startDate());
		set_endDate(obj.get_endDate());
		set_dTime(obj.get_dTime());
		//
		// Instance variables defined in this object.
		this.returnDefinition   = obj.get_returnDefinition();
		this.expectedReturnRate = obj.get_expectedReturnRate();
		this.volatility         = obj.get_volatility();
	}

	/**
	 * Method for writing out the values from a Monte Carlo path into a
	 * data file.  This can then be fed back in as a test.
	 * The data are written in the following format:
	 <pre>
	 881003,0.0000,14.1944,13.9444,14.0832,2200050,0
	 881004,0.0000,14.1668,14.0556,14.1668,1490850,0
	 ...
	 990108,35.8125,36.7500,35.5625,35.8125,4381200,0
	 990111,35.8125,35.8750,34.8750,35.1250,3920800,0
	 990112,34.8750,34.8750,34.0000,34.0625,3577500,0
	 </pre>
	 * <p>Where the fields represent, one believes, the following:
	 * <ol>
	 *   <li>The date in 'YYMMDD' format</li>
	 *   <li>Open</li>
	 *   <li>High</li>
	 *   <li>Low</li>
	 *   <li>Last</li>
	 *   <li>Volume</li>
	 *   <li>Open Interest</li>
	 * </ol>
	 * One will probably make use of the closing price, but this can be
	 * redefined via the class variable <code>DATUMFIELD</code>.  Note that
	 * since the read in data are then used to compute the return, this would
	 * be a good place to trap for zero values in the data, which will cause
	 * all sorts of problems.
	 *
	 * @param dirName the directory in which to write the data file.
	 * @param filename the data filename itself.
	 * @exception DemoException thrown if there was a problem with the data
	 *                          file.
	 */
	public def writeFile(var dirName: String, var filename: String): void = {
		try {
			var ratesFile: x10.io.File = new File(dirName, filename);
			if ( ratesFile.exists() && ! ratesFile.canWrite() )
				throw new DemoException("Cannot write to specified filename!");
			var out: x10.io.PrintWriter = new PrintWriter(new BufferedWriter(
						new FileWriter(ratesFile)));
			for (var i: int = 0; i < nTimeSteps; i++) {
				out.print("19990101,");
				for (var j: int = 1; j<datumfield; j++ ) {
					out.print("0.0000,");
				}
				out.print(pathValue(i)+",");
				out.println("0.0000,0.0000");
			}
			out.close();
		} catch (var ioex: x10.io.IOException) {
			throw new DemoException(ioex.toString());
		}
	}

	/**
	 * Method for returning a RatePath object from the Monte Carlo data
	 * generated.
	 *
	 * @return a <code>RatePath</code> object representing the generated
	 *         data.
	 * @exception DemoException thrown if there was a problem creating
	 *            the RatePath object.
	 */
	public def getRatePath(): RatePath = {
		return (new RatePath(this));
	}

	/**
	 * Method for calculating the sequence of fluctuations, based around
	 * a Gaussian distribution of given mean and variance, as defined
	 * in this class' instance variables.  Mapping from Gaussian
	 * distribution of (0,1) to (mean-drift,volatility) is done via
	 * Ito's lemma on the log of the stock price.
	 *
	 * @param randomSeed The psuedo-random number seed value, to start off a
	 *                   given sequence of Gaussian fluctuations.
	 * @exception DemoException thrown if there are any problems with
	 *                          the computation.
	 */
	public def computeFluctuationsGaussian(var randomSeed: long): void = {
		if ( nTimeSteps > fluctuations.size )
			throw new DemoException("Number of timesteps requested is greater than the allocated array!");
		//
		// First, make use of the passed in seed value.
		var rnd: Random;
		if ( randomSeed == -1 as long ) {
			rnd = new Random();
		} else {
			rnd = new Random(randomSeed);
		}
		//
		// Determine the mean and standard-deviation, from the mean-drift and volatility.
		var mean: double = (expectedReturnRate-0.5*volatility*volatility)*get_dTime();
		var sd: double = volatility*Math.sqrt(get_dTime());
		var gauss: double;
		var meanGauss: double = 0.0;
		var variance: double = 0.0;
		for ( var i: int = 0; i < nTimeSteps; i++ ) {
			gauss = rnd.nextGaussian();
			meanGauss += gauss;
			variance += (gauss*gauss);
			//
			// Now map this onto a general Gaussian of given mean and variance.
			fluctuations(i) = mean + sd*gauss;
			//      dbgPrintln("gauss = "+gauss+" fluctuations = "+fluctuations[i]);
		}
		meanGauss /= nTimeSteps as double;
		variance /= nTimeSteps as double;
		//    dbgPrintln("meanGauss = "+meanGauss+" variance = "+variance);
	}

	/**
	 * Method for calculating the sequence of fluctuations, based around
	 * a Gaussian distribution of given mean and variance, as defined
	 * in this class' instance variables.  Mapping from Gaussian
	 * distribution of (0,1) to (mean-drift,volatility) is done via
	 * Ito's lemma on the log of the stock price.  This overloaded method
	 * is for when the random seed should be decided by the system.
	 *
	 * @exception DemoException thrown if there are any problems with
	 *                          the computation.
	 */
	public def computeFluctuationsGaussian(): void = {
		// TODO: support unary operators in X10
		computeFluctuationsGaussian(-1L);
	}

	/**
	 * Method for calculating the corresponding rate path, given the
	 * fluctuations and starting rate value.
	 *
	 * @param startValue the starting value of the rate path, to be
	 *                   updated with the precomputed fluctuations.
	 * @exception DemoException thrown if there are any problems with
	 *                          the computation.
	 */
	public def computePathValue(var startValue: double): void = {
		pathValue(0) = startValue;
		if ( returnDefinition == ReturnPath.COMPOUNDED ||
				returnDefinition == ReturnPath.NONCOMPOUNDED) {
			for (var i: int = 1; i < nTimeSteps; i++ ) {
				pathValue(i) = pathValue(i-1) * Math.exp(fluctuations(i));
			}
		} else {
			throw new DemoException("Unknown or undefined update method.");
		}
	}
}
