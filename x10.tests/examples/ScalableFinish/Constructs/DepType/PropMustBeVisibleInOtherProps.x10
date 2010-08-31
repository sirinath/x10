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

 

/**
 * The test checks that property syntax is accepted for value classes.
 *
 * @author pvarma, vj
 */
public class PropMustBeVisibleInOtherProps   {

    static class Value2(i:int, j:int{self==i})  {
        public def this(k:int):Value2{self.i==k} = {
            property(k,k);
        }
    }
    public def run():boolean = {
        new Value2(4);
        return true;
    }
    public static def main(var args: Rail[String]): void = {
        new PropMustBeVisibleInOtherProps().run ();
    }
}
