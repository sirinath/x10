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

import harness.x10Test;

/**
 * Check tht if the return type of a constructor is supplied explicitly,
 * then that is the return type. (The return type shold not be updated
 * with the computed return type.)
 *
 *See XTENLANG-2535.
 * @author vj
 */
public class PropertyAssign_MustFailCompile extends x10Test {
    class A(x:Int) {
        
        def this():A { // The computed return type is A{self.x==0}.
            property(0);
        }
        
        val y:A{self.x==0} = new A(); // ERR
    }
  
    public def run(): boolean = true;

    public static def main(Array[String](1)) {
        new PropertyAssign_MustFailCompile().execute();
    }
}
