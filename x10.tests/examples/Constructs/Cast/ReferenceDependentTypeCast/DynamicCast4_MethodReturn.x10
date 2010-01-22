/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Purpose: Checks dynamic cast should fail at runtime.
 * Issue: Constraint is not meet.
 * Note: The following code will use java reflexion to dynamically checks constraints. 
 * @author vcave
 **/
public class DynamicCast4_MethodReturn extends x10Test {
   public def run(): boolean = {      
      try {                  
         // constraint not meet
         var convertedObject: X10DepTypeClassTwo{p==0&&q==2} = 
         this.objectReturner() as X10DepTypeClassTwo{p==0&&q==2};
         
      }catch(var e: ClassCastException) {
         return true;
      }

      return false;
   }
   
   public def objectReturner(): x10.lang.Object = {
      return new X10DepTypeClassTwo(0,1);
   }

   public static def main(var args: Rail[String]): void = {
      new DynamicCast4_MethodReturn().execute();
   }
}
