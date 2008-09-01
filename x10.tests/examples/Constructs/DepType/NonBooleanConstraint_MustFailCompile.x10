/**
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * The test checks that a nonboolean constraint is rejected.
 *
 * @author pvarma
 */
public class NonBooleanConstraint_MustFailCompile(i:int, j:int){this.i} extends x10Test {

	public def this(k:int):NonBooleanConstraint_MustFailCompile = {
	    property(k,k);
	}
	public def run()=true;
	
	public static def main(Rail[String])  = {
		new NonBooleanConstraint_MustFailCompile(2).execute();
	}
}
