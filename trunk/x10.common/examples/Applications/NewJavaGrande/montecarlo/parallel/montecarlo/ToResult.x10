/**
  * X10 port of montecarlo benchmark from Section 2 of Java Grande Forum Benchmark Suite (Version 2.0)
  *
  * @author Vivek Sarkar (vsarkar@us.ibm.com)
  *
 
  */

package montecarlo;
import java.lang.Double;

public class ToResult  implements java.io.Serializable {
  private String header;
  private double expectedReturnRate=Double.NaN;
  private double volatility=Double.NaN;
  private double volatility2=Double.NaN;
  private double finalStockPrice=Double.NaN;
  private double[] pathValue;

  /**
    * Constructor, for the results from a computation.
    *
    * @param header Simple header string.
    * @param pathValue Data computed by the Monte Carlo generator.
    */
  public ToResult(String header, double expectedReturnRate, double volatility, 
  double volatility2, double finalStockPrice, double[] pathValue) {
    this.header=header;
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
  public String toString(){
    return(header);
  }
  //------------------------------------------------------------------------
  // Accessor methods for class ToResult.
  // Generated by 'makeJavaAccessor.pl' script.  HWY.  20th January 1999.
  //------------------------------------------------------------------------
  /**
    * Accessor method for private instance variable <code>header</code>.
    *
    * @return Value of instance variable <code>header</code>.
    */
  public String get_header() {
    return(this.header);
  }
  /**
    * Set method for private instance variable <code>header</code>.
    *
    * @param header the value to set for the instance variable <code>header</code>.
    */
  public void set_header(String header) {
    this.header = header;
  }
  /**
    * Accessor method for private instance variable <code>expectedReturnRate</code>.
    *
    * @return Value of instance variable <code>expectedReturnRate</code>.
    */
  public double get_expectedReturnRate() {
    return(this.expectedReturnRate);
  }
  /**
    * Set method for private instance variable <code>expectedReturnRate</code>.
    *
    * @param expectedReturnRate the value to set for the instance variable 
    * <code>expectedReturnRate</code>.
    */
  public void set_expectedReturnRate(double expectedReturnRate) {
    this.expectedReturnRate = expectedReturnRate;
  }
  /**
    * Accessor method for private instance variable <code>volatility</code>.
    *
    * @return Value of instance variable <code>volatility</code>.
    */
  public double get_volatility() {
    return(this.volatility);
  }
  /**
    * Set method for private instance variable <code>volatility</code>.
    *
    * @param volatility the value to set for the instance variable <code>volatility</code>.
    */
  public void set_volatility(double volatility) {
    this.volatility = volatility;
  }
  /**
    * Accessor method for private instance variable <code>volatility2</code>.
    *
    * @return Value of instance variable <code>volatility2</code>.
    */
  public double get_volatility2() {
    return(this.volatility2);
  }
  /**
    * Set method for private instance variable <code>volatility2</code>.
    *
    * @param volatility2 the value to set for the instance variable <code>volatility2</code>.
    */
  public void set_volatility2(double volatility2) {
    this.volatility2 = volatility2;
  }
  /**
    * Accessor method for private instance variable <code>finalStockPrice</code>.
    *
    * @return Value of instance variable <code>finalStockPrice</code>.
    */
  public double get_finalStockPrice() {
    return(this.finalStockPrice);
  }
  /**
    * Set method for private instance variable <code>finalStockPrice</code>.
    *
    * @param finalStockPrice the value to set for the instance variable 
    * <code>finalStockPrice</code>.
    */
  public void set_finalStockPrice(double finalStockPrice) {
    this.finalStockPrice = finalStockPrice;
  }
  /**
    * Accessor method for private instance variable <code>pathValue</code>.
    *
    * @return Value of instance variable <code>pathValue</code>.
    */
  public double[] get_pathValue() {
    return(this.pathValue);
  }
  /**
    * Set method for private instance variable <code>pathValue</code>.
    *
    * @param pathValue the value to set for the instance variable <code>pathValue</code>.
    */
  public void set_pathValue(double[] pathValue) {
    this.pathValue = pathValue;
  }
  //------------------------------------------------------------------------
}


