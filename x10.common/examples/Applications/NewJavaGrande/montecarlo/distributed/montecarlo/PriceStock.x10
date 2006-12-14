/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
package montecarlo;

import java.lang.Double;

/**
 * X10 port of montecarlo benchmark from Section 2 of Java Grande Forum Benchmark Suite (Version 2.0).
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 *
 * Porting issues identified:
 */
public class PriceStock extends Universal {

	//------------------------------------------------------------------------
	// Class variables.
	//------------------------------------------------------------------------

	/**
	 * Class variable for determining whether to switch on debug output or
	 * not.
	 */
	public static final boolean DEBUG = true;

	/**
	 * Class variable for defining the debug message prompt.
	 */
	protected static final String prompt = "PriceStock> ";

	//------------------------------------------------------------------------
	// Instance variables.
	//------------------------------------------------------------------------

	/**
	 * The Monte Carlo path to be generated.
	 */
	private MonteCarloPath mcPath;

	/**
	 * String identifier for a given task.
	 */
	private String taskHeader;

	/**
	 * Random seed from which the Monte Carlo sequence is started.
	 */
	private long randomSeed =-1;

	/**
	 * Initial stock price value.
	 */
	private double pathStartValue = Double.NaN;

	/**
	 * Object which represents the results from a given computation task.
	 */
	private ToResult result;
	private double expectedReturnRate = Double.NaN;
	private double volatility = Double.NaN;
	private double volatility2 = Double.NaN;
	private double finalStockPrice = Double.NaN;
	private double[] pathValue;

	//------------------------------------------------------------------------
	// Constructors.
	//------------------------------------------------------------------------

	/**
	 * Default constructor.
	 */
	public PriceStock() {
		super();
		mcPath = new MonteCarloPath();
		set_prompt(prompt);
		set_DEBUG(DEBUG);
	}

	//------------------------------------------------------------------------
	// Methods.
	//------------------------------------------------------------------------
	//------------------------------------------------------------------------
	// Methods which implement the Slaveable interface.
	//------------------------------------------------------------------------

	/**
	 * Method which is passed in the initialisation data common to all tasks,
	 * and then unpacks them for use by this object.
	 *
	 * @param obj Object representing data which are common to all tasks.
	 */
	public void setInitAllTasks(ToInitAllTasks obj) {
		final ToInitAllTasks initAllTasks = (ToInitAllTasks) obj;
		finish async (mcPath) {
			mcPath.set_name(initAllTasks.get_name());
			mcPath.set_startDate(initAllTasks.get_startDate());
			mcPath.set_endDate(initAllTasks.get_endDate());
			mcPath.set_dTime(initAllTasks.get_dTime());
			mcPath.set_returnDefinition(initAllTasks.get_returnDefinition());
			mcPath.set_expectedReturnRate(initAllTasks.get_expectedReturnRate());
			mcPath.set_volatility(initAllTasks.get_volatility());
			int nTimeSteps = initAllTasks.get_nTimeSteps();
			mcPath.set_nTimeSteps(nTimeSteps);
			this.pathStartValue = initAllTasks.get_pathStartValue();
			mcPath.set_pathStartValue(pathStartValue);
			mcPath.set_pathValue(new double[nTimeSteps]);
			mcPath.set_fluctuations(new double[nTimeSteps]);
		}
	}

	/**
	 * Method which is passed in the data representing each task, which then
	 * unpacks it for use by this object.
	 *
	 * @param obj Object representing the data which defines a given task.
	 */
	public void setTask(x10.lang.Object obj) {
		ToTask task = (ToTask) obj;
		this.taskHeader     = task.get_header();
		this.randomSeed     = task.get_randomSeed();
	}

	/**
	 * The business end.  Invokes the necessary computation routine, for a
	 * a given task.
	 */
	public void run() {
		try {
			mcPath.computeFluctuationsGaussian(randomSeed);
			mcPath.computePathValue(pathStartValue);
			RatePath rateP = new RatePath(mcPath);
			ReturnPath returnP = rateP.getReturnCompounded();
			returnP.estimatePath();
			expectedReturnRate = returnP.get_expectedReturnRate();
			volatility = returnP.get_volatility();
			volatility2 = returnP.get_volatility2();
			finalStockPrice = rateP.getEndPathValue();
			pathValue = mcPath.get_pathValue();
		} catch (DemoException demoEx) {
			errPrintln(demoEx.toString());
		}
	}

	/**
	 * Method which returns the results of a computation back to the caller.
	 * @return An object representing the computed results.
	 */
	public x10.lang.Object getResult() {
		String resultHeader = "Result of task with Header = "+taskHeader+": randomSeed = "+randomSeed+": pathStartValue = "+pathStartValue;
		ToResult res = new ToResult(resultHeader,expectedReturnRate,volatility,
									volatility2,finalStockPrice,pathValue);
		return res;
	}
}

