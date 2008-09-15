/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;


/**
 * Check the condition of an if is flattened.
 */
public class FlattenConditional3 extends x10Test {
   var a: Array[int](2);
    public def this(): FlattenConditional3 = {
      a = Array.make[int](([1..10, 1..10] to Region)->here, 
         ((i,j): Point) => { return i+j;});
    }
    
    def m(var a: int): int = {
     if (a == 2) throw new Error();
     return a;
    }
    
	public def run(): boolean = {
	var b: int = 0;
	if (a(2, 2) == 0)
		b=1;
	
	 return b==0;
	}

	public static def main(var args: Rail[String]): void = {
		new FlattenConditional3().execute();
	}
	
}
