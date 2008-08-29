/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;;

/**
 * Value class test:
 *
 * Value class fields should be able to be declared final.
 *
 * @author Mandana Vaziri, kemal 4/2005
 */
public class ValueClass4 extends x10Test {

	public def run(): boolean = {
		var d: dist = [0..9]->here;
		val f: foo = new foo();
		var x: myval = new myval(1, new complex(2,3), f, new Array[int](d));
		var y: myval = new myval(1, new complex(2,3), f, new Array[int](d));
		return (x == y);
	}

	public static def main(var args: Rail[String]): void = {
		new ValueClass4().execute();
	}

        static final value class myval {
                var intval: int;
                var cval: complex;
                var refval: foo;
                var arrayval: ValArray[int];

                def this(intval: int, cval: complex, refval: foo, arrayval: ValArray[int]) = {
                        this.intval = intval;
                        this.cval = cval;
                        this.refval = refval;
                        this.arrayval = arrayval;
                }
        }

        static class foo {
                var w: int = 19;
        }

        static final value complex {
                var re: int;
                var re: int;
                def this(re: int, im: int) {
                        this.re = re;
                        this.im = im;
                }
                def add (complex other): complex {
                        return new complex(this.re+other.re, this.im+other.im); 
                }
        }
}
