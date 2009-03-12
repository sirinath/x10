/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Purpose: Checks assignment of primitive to constrained primitives
 *          that may produce an overflow behave as expected.
 * @author vcave
 **/
public class PrimitiveDepTypeAssignmentOverflow extends x10Test {
	 private val overIntMax: long = (Int.MAX_VALUE as long) + 10000;
	 private val overIntMaxAsInt: int = overIntMax as int;

	 
	 public def run(): boolean = {
		 Console.OUT.println("long " + overIntMax);
		 Console.OUT.println("long as int " + (overIntMax as int));
		
		 var res: boolean = false;
		 
		var l1: int{self==overIntMaxAsInt} = overIntMax as int{self==overIntMaxAsInt};
		
		var iNeg: int = -2147473649;

		var i0: int{self==-2147473649} = -2147473649 as int{self==-2147473649};
		res &= (i0 == overIntMaxAsInt);
		
		var i1: int{self==-2147473649} = iNeg as int{self==-2147473649};
		res = (i1 == overIntMaxAsInt);
		
		// The constraint on self is a long value converted as int
		// it makes that results in an overflow
		var i2: int{self==overIntMax} = -2147473649 as int{self==overIntMax};
		res = (i2 == overIntMaxAsInt);

		var i3: int{self==overIntMax} = overIntMax as int{self==overIntMax};
		res &= (i3 == overIntMaxAsInt);
		
		// constraint on self is a long value converted as int but isn't overflowed
		var i5: int{self==2L} = 2L as int{self==2L};
		res &= (i5 == 2);

		return res;
	}

	public static def main(var args: Rail[String]): void = {
		new PrimitiveDepTypeAssignmentOverflow().execute();
	}
}
