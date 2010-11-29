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

/*
 * A method implements an interface, and the method contains concurrent
 */
public class InterfaceMethod3 {

	
	def getITest(flag:double):ITest{
	    val r:ITest;
        if(flag > 0.5){
	       r = new A();
        }
        else{
	       r = new B();
        }
        return r;
	}
	
	public def run() {
		val random = new x10.util.Random();
		var passed:boolean = true;
		
		val flag:double = random.nextDouble();
	    val a = getITest(flag);
	    val r = a.set(2);
	    if(flag > 0.5){
			passed &= (r == 2);
	    }
	    else{
			passed &= (r == -1);
	    }
		Console.OUT.println("r  = " + r);
		
		return passed;
	}

	public static def main(Array[String](1)) {
        val r = new InterfaceMethod3().run();
        if(r){
             x10.io.Console.OUT.println("++++++Test succeeded.");
        }
	}
	
	
}

interface ITest{
	def set(v:int):int;
}

class A implements ITest{
	
    public def set(v:int):int{
        val value:int;
	    finish{
		    async value = v;
	    }
	    return value;
    }
}

class B implements ITest{
	
    public def set(v:int):int{
    	return -1;
    }
}

