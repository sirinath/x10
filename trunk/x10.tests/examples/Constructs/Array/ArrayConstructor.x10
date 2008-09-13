/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * This gives the error:
	 C:\eclipse\ws2\x10.common\examples\Constructs\Array\ArrayConstructor.x10:18:
		    Local variable "x" multiply defined. Previous definition at :16,13-19.
		1 error.
@author vj
 */
public class ArrayConstructor extends x10Test {

	public def run(): boolean = {
		var x: double = 1.0;
		var a: Array[double] = Array.make[double](0..5->here, (p: point)=>2.0);
		return true;
	}

	public static def main(var args: Rail[String]): void = {
		new ArrayConstructor().execute();
	}
}
