/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * The test checks that property syntax is accepted for value classes.
 *
 * @author pvarma, vj
 */
public class PropsMustBeVisibleInClassInvariant extends x10Test {

   class Value2(i:int, j:int){i==j}  {

	public def this(k:int):Value2{i==j} = {
	    property(k,k);
	}
	}
	public def run():boolean = {
	     new Value2(4);
	    return true;
	}
	public static def main(var args: Rail[String]): void = {
		new PropsMustBeVisibleInClassInvariant().execute();
	}
}
