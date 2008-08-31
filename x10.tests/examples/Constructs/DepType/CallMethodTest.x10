/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
 /**  Check that the return type of a call to a method which has a deptype as its return
 type is handled correctly. 
 
 *@author vj,10/20/2006
 *
 */

import harness.x10Test;

 public class CallMethodTest extends x10Test { 
  
  public def run(): boolean = { 
       val r:Region{rank==2} = Region.makeRectangular([1,1], [10,10]);
        return true;
    }
   public static def main(var args: Rail[String]): void = {
        new CallMethodTest().execute();
    }
    }
