/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

import harness.x10Test;

/**

 */
public class XTENLANG_614 extends x10Test {

    public def run(): boolean {
        val foo = true ? ()=>{} : ()=>{};
        return true;
    }
    
    public static def main(Rail[String]) {
        new XTENLANG_614().execute();
    }
}
