/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * The test checks that final fields can be accessed in a depclause.
 *
 * @author vj
 */
public class FieldsMustBeFinalInDepClause extends x10Test {
	class Test(int i) {
	   public boolean bad;
	   public Test(int ii) {
	     i = ii;
	   }
	}
	
	public boolean run() {
	   Test (:self.i==52) a =  (Test(:self.i==52)) new Test(52);
	    return true;
	}
	public static void main(String[] args) {
		new FieldsMustBeFinalInDepClause().execute();
	}
}

