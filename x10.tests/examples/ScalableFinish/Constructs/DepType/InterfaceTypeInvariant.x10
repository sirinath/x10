/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

/** Tests that the properties of an interface are implemented by a compliant class 
 * and that the interface constraint is entailed by the compliant class.
 *@author pvarma
 *
 */


 

public class InterfaceTypeInvariant   { 

    public static interface Test (l:int, m:int){this.m == this.l} {
      def put():int;
    }
    
    class Tester(l:int, m:int){this.m == this.l} implements Test {
      public def this(arg:int):Tester { property(arg,arg); }
      public def put()=0;
	}
 
    public def run()=true;
   
	
    public static def main(Rail[String]) = {
        new InterfaceTypeInvariant().run ();
    }
   

		
}
